package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.annotation.Nullable;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter;

public final class RetryAndFollowUpInterceptor
  implements Interceptor
{
  private static final int MAX_FOLLOW_UPS = 20;
  private final OkHttpClient client;

  public RetryAndFollowUpInterceptor(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }

  private Request followUpRequest(Response paramResponse, @Nullable Route paramRoute)
    throws IOException
  {
    if (paramResponse == null)
      throw new IllegalStateException();
    int i = paramResponse.code();
    String str = paramResponse.request().method();
    Proxy localProxy = null;
    switch (i)
    {
    default:
      return null;
    case 503:
      if ((paramResponse.priorResponse() != null) && (paramResponse.priorResponse().code() == 503))
        return null;
      if (retryAfter(paramResponse, 2147483647) == 0)
        return paramResponse.request();
      return null;
    case 408:
      if (!this.client.retryOnConnectionFailure())
        return null;
      paramRoute = paramResponse.request().body();
      if ((paramRoute != null) && (paramRoute.isOneShot()))
        return null;
      if ((paramResponse.priorResponse() != null) && (paramResponse.priorResponse().code() == 408))
        return null;
      if (retryAfter(paramResponse, 0) > 0)
        return null;
      return paramResponse.request();
    case 407:
      if (paramRoute != null)
        localProxy = paramRoute.proxy();
      else
        localProxy = this.client.proxy();
      if (localProxy.type() != Proxy.Type.HTTP)
        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
      return this.client.proxyAuthenticator().authenticate(paramRoute, paramResponse);
    case 401:
      return this.client.authenticator().authenticate(paramRoute, paramResponse);
    case 307:
    case 308:
      if ((!str.equals("GET")) && (!str.equals("HEAD")))
        return null;
      break;
    case 300:
    case 301:
    case 302:
    case 303:
    }
    if (!this.client.followRedirects())
      return null;
    paramRoute = paramResponse.header("Location");
    if (paramRoute == null)
      return null;
    HttpUrl localHttpUrl = paramResponse.request().url().resolve(paramRoute);
    if (localHttpUrl == null)
      return null;
    if ((!localHttpUrl.scheme().equals(paramResponse.request().url().scheme())) && (!this.client.followSslRedirects()))
      return null;
    Request.Builder localBuilder = paramResponse.request().newBuilder();
    if (HttpMethod.permitsRequestBody(str))
    {
      boolean bool = HttpMethod.redirectsWithBody(str);
      if (HttpMethod.redirectsToGet(str))
      {
        localBuilder.method("GET", null);
      }
      else
      {
        paramRoute = localProxy;
        if (bool)
          paramRoute = paramResponse.request().body();
        localBuilder.method(str, paramRoute);
      }
      if (!bool)
      {
        localBuilder.removeHeader("Transfer-Encoding");
        localBuilder.removeHeader("Content-Length");
        localBuilder.removeHeader("Content-Type");
      }
    }
    if (!Util.sameConnection(paramResponse.request().url(), localHttpUrl))
      localBuilder.removeHeader("Authorization");
    return localBuilder.url(localHttpUrl).build();
  }

  private boolean isRecoverable(IOException paramIOException, boolean paramBoolean)
  {
    boolean bool1 = paramIOException instanceof ProtocolException;
    boolean bool2 = false;
    if (bool1)
      return false;
    if ((paramIOException instanceof InterruptedIOException))
    {
      bool1 = bool2;
      if ((paramIOException instanceof SocketTimeoutException))
      {
        bool1 = bool2;
        if (!paramBoolean)
          bool1 = true;
      }
      return bool1;
    }
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException)))
      return false;
    return !(paramIOException instanceof SSLPeerUnverifiedException);
  }

  private boolean recover(IOException paramIOException, Transmitter paramTransmitter, boolean paramBoolean, Request paramRequest)
  {
    if (!this.client.retryOnConnectionFailure())
      return false;
    if ((paramBoolean) && (requestIsOneShot(paramIOException, paramRequest)))
      return false;
    if (!isRecoverable(paramIOException, paramBoolean))
      return false;
    return paramTransmitter.canRetry();
  }

  private boolean requestIsOneShot(IOException paramIOException, Request paramRequest)
  {
    paramRequest = paramRequest.body();
    return ((paramRequest != null) && (paramRequest.isOneShot())) || ((paramIOException instanceof FileNotFoundException));
  }

  private int retryAfter(Response paramResponse, int paramInt)
  {
    paramResponse = paramResponse.header("Retry-After");
    if (paramResponse == null)
      return paramInt;
    if (paramResponse.matches("\\d+"))
      return Integer.valueOf(paramResponse).intValue();
    return 2147483647;
  }

  // ERROR //
  public Response intercept(okhttp3.Interceptor.Chain paramChain)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 242 1 0
    //   6: astore 4
    //   8: aload_1
    //   9: checkcast 244	okhttp3/internal/http/RealInterceptorChain
    //   12: astore 7
    //   14: aload 7
    //   16: invokevirtual 248	okhttp3/internal/http/RealInterceptorChain:transmitter	()Lokhttp3/internal/connection/Transmitter;
    //   19: astore 6
    //   21: aconst_null
    //   22: astore_1
    //   23: iconst_0
    //   24: istore_2
    //   25: aload 6
    //   27: aload 4
    //   29: invokevirtual 252	okhttp3/internal/connection/Transmitter:prepareToConnect	(Lokhttp3/Request;)V
    //   32: aload 6
    //   34: invokevirtual 255	okhttp3/internal/connection/Transmitter:isCanceled	()Z
    //   37: ifeq +14 -> 51
    //   40: new 23	java/io/IOException
    //   43: dup
    //   44: ldc_w 257
    //   47: invokespecial 258	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   50: athrow
    //   51: aload 7
    //   53: aload 4
    //   55: aload 6
    //   57: aconst_null
    //   58: invokevirtual 262	okhttp3/internal/http/RealInterceptorChain:proceed	(Lokhttp3/Request;Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/Exchange;)Lokhttp3/Response;
    //   61: astore 5
    //   63: aload_1
    //   64: ifnull +29 -> 93
    //   67: aload 5
    //   69: invokevirtual 265	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   72: aload_1
    //   73: invokevirtual 265	okhttp3/Response:newBuilder	()Lokhttp3/Response$Builder;
    //   76: aconst_null
    //   77: invokevirtual 270	okhttp3/Response$Builder:body	(Lokhttp3/ResponseBody;)Lokhttp3/Response$Builder;
    //   80: invokevirtual 272	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   83: invokevirtual 275	okhttp3/Response$Builder:priorResponse	(Lokhttp3/Response;)Lokhttp3/Response$Builder;
    //   86: invokevirtual 272	okhttp3/Response$Builder:build	()Lokhttp3/Response;
    //   89: astore_1
    //   90: goto +6 -> 96
    //   93: aload 5
    //   95: astore_1
    //   96: getstatic 281	okhttp3/internal/Internal:instance	Lokhttp3/internal/Internal;
    //   99: aload_1
    //   100: invokevirtual 285	okhttp3/internal/Internal:exchange	(Lokhttp3/Response;)Lokhttp3/internal/connection/Exchange;
    //   103: astore 5
    //   105: aload 5
    //   107: ifnull +16 -> 123
    //   110: aload 5
    //   112: invokevirtual 291	okhttp3/internal/connection/Exchange:connection	()Lokhttp3/internal/connection/RealConnection;
    //   115: invokevirtual 297	okhttp3/internal/connection/RealConnection:route	()Lokhttp3/Route;
    //   118: astore 4
    //   120: goto +6 -> 126
    //   123: aconst_null
    //   124: astore 4
    //   126: aload_0
    //   127: aload_1
    //   128: aload 4
    //   130: invokespecial 299	okhttp3/internal/http/RetryAndFollowUpInterceptor:followUpRequest	(Lokhttp3/Response;Lokhttp3/Route;)Lokhttp3/Request;
    //   133: astore 4
    //   135: aload 4
    //   137: ifnonnull +23 -> 160
    //   140: aload 5
    //   142: ifnull +16 -> 158
    //   145: aload 5
    //   147: invokevirtual 302	okhttp3/internal/connection/Exchange:isDuplex	()Z
    //   150: ifeq +8 -> 158
    //   153: aload 6
    //   155: invokevirtual 305	okhttp3/internal/connection/Transmitter:timeoutEarlyExit	()V
    //   158: aload_1
    //   159: areturn
    //   160: aload 4
    //   162: invokevirtual 62	okhttp3/Request:body	()Lokhttp3/RequestBody;
    //   165: astore 8
    //   167: aload 8
    //   169: ifnull +13 -> 182
    //   172: aload 8
    //   174: invokevirtual 67	okhttp3/RequestBody:isOneShot	()Z
    //   177: ifeq +5 -> 182
    //   180: aload_1
    //   181: areturn
    //   182: aload_1
    //   183: invokevirtual 308	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   186: invokestatic 312	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   189: aload 6
    //   191: invokevirtual 315	okhttp3/internal/connection/Transmitter:hasExchange	()Z
    //   194: ifeq +8 -> 202
    //   197: aload 5
    //   199: invokevirtual 318	okhttp3/internal/connection/Exchange:detachWithViolence	()V
    //   202: iload_2
    //   203: iconst_1
    //   204: iadd
    //   205: istore_2
    //   206: iload_2
    //   207: bipush 20
    //   209: if_icmple +37 -> 246
    //   212: new 320	java/lang/StringBuilder
    //   215: dup
    //   216: invokespecial 321	java/lang/StringBuilder:<init>	()V
    //   219: astore_1
    //   220: aload_1
    //   221: ldc_w 323
    //   224: invokevirtual 327	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: aload_1
    //   229: iload_2
    //   230: invokevirtual 330	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: new 88	java/net/ProtocolException
    //   237: dup
    //   238: aload_1
    //   239: invokevirtual 333	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokespecial 93	java/net/ProtocolException:<init>	(Ljava/lang/String;)V
    //   245: athrow
    //   246: goto -221 -> 25
    //   249: astore_1
    //   250: goto +68 -> 318
    //   253: astore 5
    //   255: aload 5
    //   257: instanceof 335
    //   260: ifne +65 -> 325
    //   263: iconst_1
    //   264: istore_3
    //   265: goto +3 -> 268
    //   268: aload_0
    //   269: aload 5
    //   271: aload 6
    //   273: iload_3
    //   274: aload 4
    //   276: invokespecial 337	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;Lokhttp3/internal/connection/Transmitter;ZLokhttp3/Request;)Z
    //   279: ifne +31 -> 310
    //   282: aload 5
    //   284: athrow
    //   285: astore 5
    //   287: aload_0
    //   288: aload 5
    //   290: invokevirtual 341	okhttp3/internal/connection/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   293: aload 6
    //   295: iconst_0
    //   296: aload 4
    //   298: invokespecial 337	okhttp3/internal/http/RetryAndFollowUpInterceptor:recover	(Ljava/io/IOException;Lokhttp3/internal/connection/Transmitter;ZLokhttp3/Request;)Z
    //   301: ifne +9 -> 310
    //   304: aload 5
    //   306: invokevirtual 344	okhttp3/internal/connection/RouteException:getFirstConnectException	()Ljava/io/IOException;
    //   309: athrow
    //   310: aload 6
    //   312: invokevirtual 347	okhttp3/internal/connection/Transmitter:exchangeDoneDueToException	()V
    //   315: goto -290 -> 25
    //   318: aload 6
    //   320: invokevirtual 347	okhttp3/internal/connection/Transmitter:exchangeDoneDueToException	()V
    //   323: aload_1
    //   324: athrow
    //   325: iconst_0
    //   326: istore_3
    //   327: goto -59 -> 268
    //
    // Exception table:
    //   from	to	target	type
    //   51	63	249	finally
    //   255	263	249	finally
    //   268	285	249	finally
    //   287	310	249	finally
    //   51	63	253	java/io/IOException
    //   51	63	285	okhttp3/internal/connection/RouteException
  }
}