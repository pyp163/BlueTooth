package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Address;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Timeout;

public final class RealConnection extends Http2Connection.Listener
  implements Connection
{
  private static final int MAX_TUNNEL_ATTEMPTS = 21;
  private static final String NPE_THROW_WITH_NULL = "throw with null exception";
  private int allocationLimit = 1;
  public final RealConnectionPool connectionPool;
  private Handshake handshake;
  private Http2Connection http2Connection;
  long idleAtNanos = 9223372036854775807L;
  boolean noNewExchanges;
  private Protocol protocol;
  private Socket rawSocket;
  private int refusedStreamCount;
  private final Route route;
  int routeFailureCount;
  private BufferedSink sink;
  private Socket socket;
  private BufferedSource source;
  int successCount;
  final List<Reference<Transmitter>> transmitters = new ArrayList();

  public RealConnection(RealConnectionPool paramRealConnectionPool, Route paramRoute)
  {
    this.connectionPool = paramRealConnectionPool;
    this.route = paramRoute;
  }

  private void connectSocket(int paramInt1, int paramInt2, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    Proxy localProxy = this.route.proxy();
    Object localObject = this.route.address();
    if ((localProxy.type() != Proxy.Type.DIRECT) && (localProxy.type() != Proxy.Type.HTTP))
      localObject = new Socket(localProxy);
    else
      localObject = ((Address)localObject).socketFactory().createSocket();
    this.rawSocket = ((Socket)localObject);
    paramEventListener.connectStart(paramCall, this.route.socketAddress(), localProxy);
    this.rawSocket.setSoTimeout(paramInt2);
    try
    {
      Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), paramInt1);
      try
      {
        this.source = Okio.buffer(Okio.source(this.rawSocket));
        this.sink = Okio.buffer(Okio.sink(this.rawSocket));
        return;
      }
      catch (NullPointerException paramCall)
      {
        if ("throw with null exception".equals(paramCall.getMessage()))
          throw new IOException(paramCall);
        return;
      }
    }
    catch (ConnectException paramCall)
    {
      paramEventListener = new StringBuilder();
      paramEventListener.append("Failed to connect to ");
      paramEventListener.append(this.route.socketAddress());
      paramEventListener = new ConnectException(paramEventListener.toString());
      paramEventListener.initCause(paramCall);
    }
    throw paramEventListener;
  }

  // ERROR //
  private void connectTls(ConnectionSpecSelector paramConnectionSpecSelector)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 67	okhttp3/internal/connection/RealConnection:route	Lokhttp3/Route;
    //   4: invokevirtual 85	okhttp3/Route:address	()Lokhttp3/Address;
    //   7: astore 6
    //   9: aload 6
    //   11: invokevirtual 205	okhttp3/Address:sslSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   14: astore_3
    //   15: aconst_null
    //   16: astore_2
    //   17: aconst_null
    //   18: astore 5
    //   20: aconst_null
    //   21: astore 4
    //   23: aload_3
    //   24: aload_0
    //   25: getfield 119	okhttp3/internal/connection/RealConnection:rawSocket	Ljava/net/Socket;
    //   28: aload 6
    //   30: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   33: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   36: aload 6
    //   38: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   41: invokevirtual 218	okhttp3/HttpUrl:port	()I
    //   44: iconst_1
    //   45: invokevirtual 223	javax/net/ssl/SSLSocketFactory:createSocket	(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
    //   48: checkcast 225	javax/net/ssl/SSLSocket
    //   51: astore_3
    //   52: aload_1
    //   53: aload_3
    //   54: invokevirtual 231	okhttp3/internal/connection/ConnectionSpecSelector:configureSecureSocket	(Ljavax/net/ssl/SSLSocket;)Lokhttp3/ConnectionSpec;
    //   57: astore 5
    //   59: aload 5
    //   61: invokevirtual 237	okhttp3/ConnectionSpec:supportsTlsExtensions	()Z
    //   64: ifeq +23 -> 87
    //   67: invokestatic 139	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   70: aload_3
    //   71: aload 6
    //   73: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   76: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   79: aload 6
    //   81: invokevirtual 241	okhttp3/Address:protocols	()Ljava/util/List;
    //   84: invokevirtual 245	okhttp3/internal/platform/Platform:configureTlsExtensions	(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    //   87: aload_3
    //   88: invokevirtual 248	javax/net/ssl/SSLSocket:startHandshake	()V
    //   91: aload_3
    //   92: invokevirtual 252	javax/net/ssl/SSLSocket:getSession	()Ljavax/net/ssl/SSLSession;
    //   95: astore_1
    //   96: aload_1
    //   97: invokestatic 257	okhttp3/Handshake:get	(Ljavax/net/ssl/SSLSession;)Lokhttp3/Handshake;
    //   100: astore_2
    //   101: aload 6
    //   103: invokevirtual 261	okhttp3/Address:hostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
    //   106: aload 6
    //   108: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   111: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   114: aload_1
    //   115: invokeinterface 267 3 0
    //   120: ifne +174 -> 294
    //   123: aload_2
    //   124: invokevirtual 270	okhttp3/Handshake:peerCertificates	()Ljava/util/List;
    //   127: astore_1
    //   128: aload_1
    //   129: invokeinterface 275 1 0
    //   134: ifne +111 -> 245
    //   137: aload_1
    //   138: iconst_0
    //   139: invokeinterface 278 2 0
    //   144: checkcast 280	java/security/cert/X509Certificate
    //   147: astore_1
    //   148: new 176	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   155: astore_2
    //   156: aload_2
    //   157: ldc_w 282
    //   160: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_2
    //   165: aload 6
    //   167: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   170: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   173: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: aload_2
    //   178: ldc_w 284
    //   181: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: pop
    //   185: aload_2
    //   186: aload_1
    //   187: invokestatic 290	okhttp3/CertificatePinner:pin	(Ljava/security/cert/Certificate;)Ljava/lang/String;
    //   190: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload_2
    //   195: ldc_w 292
    //   198: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload_2
    //   203: aload_1
    //   204: invokevirtual 296	java/security/cert/X509Certificate:getSubjectDN	()Ljava/security/Principal;
    //   207: invokeinterface 301 1 0
    //   212: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload_2
    //   217: ldc_w 303
    //   220: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: aload_2
    //   225: aload_1
    //   226: invokestatic 309	okhttp3/internal/tls/OkHostnameVerifier:allSubjectAltNames	(Ljava/security/cert/X509Certificate;)Ljava/util/List;
    //   229: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: new 311	javax/net/ssl/SSLPeerUnverifiedException
    //   236: dup
    //   237: aload_2
    //   238: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokespecial 312	javax/net/ssl/SSLPeerUnverifiedException:<init>	(Ljava/lang/String;)V
    //   244: athrow
    //   245: new 176	java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   252: astore_1
    //   253: aload_1
    //   254: ldc_w 282
    //   257: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: aload_1
    //   262: aload 6
    //   264: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   267: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   270: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: aload_1
    //   275: ldc_w 314
    //   278: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: new 311	javax/net/ssl/SSLPeerUnverifiedException
    //   285: dup
    //   286: aload_1
    //   287: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: invokespecial 312	javax/net/ssl/SSLPeerUnverifiedException:<init>	(Ljava/lang/String;)V
    //   293: athrow
    //   294: aload 6
    //   296: invokevirtual 318	okhttp3/Address:certificatePinner	()Lokhttp3/CertificatePinner;
    //   299: aload 6
    //   301: invokevirtual 209	okhttp3/Address:url	()Lokhttp3/HttpUrl;
    //   304: invokevirtual 214	okhttp3/HttpUrl:host	()Ljava/lang/String;
    //   307: aload_2
    //   308: invokevirtual 270	okhttp3/Handshake:peerCertificates	()Ljava/util/List;
    //   311: invokevirtual 322	okhttp3/CertificatePinner:check	(Ljava/lang/String;Ljava/util/List;)V
    //   314: aload 4
    //   316: astore_1
    //   317: aload 5
    //   319: invokevirtual 237	okhttp3/ConnectionSpec:supportsTlsExtensions	()Z
    //   322: ifeq +11 -> 333
    //   325: invokestatic 139	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   328: aload_3
    //   329: invokevirtual 326	okhttp3/internal/platform/Platform:getSelectedProtocol	(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
    //   332: astore_1
    //   333: aload_0
    //   334: aload_3
    //   335: putfield 328	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   338: aload_0
    //   339: aload_0
    //   340: getfield 328	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   343: invokestatic 147	okio/Okio:source	(Ljava/net/Socket;)Lokio/Source;
    //   346: invokestatic 151	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   349: putfield 153	okhttp3/internal/connection/RealConnection:source	Lokio/BufferedSource;
    //   352: aload_0
    //   353: aload_0
    //   354: getfield 328	okhttp3/internal/connection/RealConnection:socket	Ljava/net/Socket;
    //   357: invokestatic 156	okio/Okio:sink	(Ljava/net/Socket;)Lokio/Sink;
    //   360: invokestatic 159	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   363: putfield 161	okhttp3/internal/connection/RealConnection:sink	Lokio/BufferedSink;
    //   366: aload_0
    //   367: aload_2
    //   368: putfield 330	okhttp3/internal/connection/RealConnection:handshake	Lokhttp3/Handshake;
    //   371: aload_1
    //   372: ifnull +11 -> 383
    //   375: aload_1
    //   376: invokestatic 335	okhttp3/Protocol:get	(Ljava/lang/String;)Lokhttp3/Protocol;
    //   379: astore_1
    //   380: goto +7 -> 387
    //   383: getstatic 338	okhttp3/Protocol:HTTP_1_1	Lokhttp3/Protocol;
    //   386: astore_1
    //   387: aload_0
    //   388: aload_1
    //   389: putfield 340	okhttp3/internal/connection/RealConnection:protocol	Lokhttp3/Protocol;
    //   392: aload_3
    //   393: ifnull +10 -> 403
    //   396: invokestatic 139	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   399: aload_3
    //   400: invokevirtual 344	okhttp3/internal/platform/Platform:afterHandshake	(Ljavax/net/ssl/SSLSocket;)V
    //   403: return
    //   404: astore_1
    //   405: goto +45 -> 450
    //   408: astore_2
    //   409: aload_3
    //   410: astore_1
    //   411: aload_2
    //   412: astore_3
    //   413: goto +13 -> 426
    //   416: astore_1
    //   417: aload_2
    //   418: astore_3
    //   419: goto +31 -> 450
    //   422: astore_3
    //   423: aload 5
    //   425: astore_1
    //   426: aload_1
    //   427: astore_2
    //   428: aload_3
    //   429: invokestatic 350	okhttp3/internal/Util:isAndroidGetsocknameError	(Ljava/lang/AssertionError;)Z
    //   432: ifeq +14 -> 446
    //   435: aload_1
    //   436: astore_2
    //   437: new 71	java/io/IOException
    //   440: dup
    //   441: aload_3
    //   442: invokespecial 174	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   445: athrow
    //   446: aload_1
    //   447: astore_2
    //   448: aload_3
    //   449: athrow
    //   450: aload_3
    //   451: ifnull +10 -> 461
    //   454: invokestatic 139	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   457: aload_3
    //   458: invokevirtual 344	okhttp3/internal/platform/Platform:afterHandshake	(Ljavax/net/ssl/SSLSocket;)V
    //   461: aload_3
    //   462: invokestatic 354	okhttp3/internal/Util:closeQuietly	(Ljava/net/Socket;)V
    //   465: aload_1
    //   466: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   52	87	404	finally
    //   87	245	404	finally
    //   245	294	404	finally
    //   294	314	404	finally
    //   317	333	404	finally
    //   333	371	404	finally
    //   375	380	404	finally
    //   383	387	404	finally
    //   387	392	404	finally
    //   52	87	408	java/lang/AssertionError
    //   87	245	408	java/lang/AssertionError
    //   245	294	408	java/lang/AssertionError
    //   294	314	408	java/lang/AssertionError
    //   317	333	408	java/lang/AssertionError
    //   333	371	408	java/lang/AssertionError
    //   375	380	408	java/lang/AssertionError
    //   383	387	408	java/lang/AssertionError
    //   387	392	408	java/lang/AssertionError
    //   23	52	416	finally
    //   428	435	416	finally
    //   437	446	416	finally
    //   448	450	416	finally
    //   23	52	422	java/lang/AssertionError
  }

  private void connectTunnel(int paramInt1, int paramInt2, int paramInt3, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    Request localRequest = createTunnelRequest();
    HttpUrl localHttpUrl = localRequest.url();
    int i = 0;
    while (i < 21)
    {
      connectSocket(paramInt1, paramInt2, paramCall, paramEventListener);
      localRequest = createTunnel(paramInt2, paramInt3, localRequest, localHttpUrl);
      if (localRequest == null)
        return;
      Util.closeQuietly(this.rawSocket);
      this.rawSocket = null;
      this.sink = null;
      this.source = null;
      paramEventListener.connectEnd(paramCall, this.route.socketAddress(), this.route.proxy(), null);
      i += 1;
    }
  }

  private Request createTunnel(int paramInt1, int paramInt2, Request paramRequest, HttpUrl paramHttpUrl)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("CONNECT ");
    ((StringBuilder)localObject).append(Util.hostHeader(paramHttpUrl, true));
    ((StringBuilder)localObject).append(" HTTP/1.1");
    paramHttpUrl = ((StringBuilder)localObject).toString();
    while (true)
    {
      Http1ExchangeCodec localHttp1ExchangeCodec = new Http1ExchangeCodec(null, null, this.source, this.sink);
      this.source.timeout().timeout(paramInt1, TimeUnit.MILLISECONDS);
      this.sink.timeout().timeout(paramInt2, TimeUnit.MILLISECONDS);
      localHttp1ExchangeCodec.writeRequest(paramRequest.headers(), paramHttpUrl);
      localHttp1ExchangeCodec.finishRequest();
      localObject = localHttp1ExchangeCodec.readResponseHeaders(false).request(paramRequest).build();
      localHttp1ExchangeCodec.skipConnectBody((Response)localObject);
      int i = ((Response)localObject).code();
      if (i == 200)
        break;
      if (i != 407)
      {
        paramRequest = new StringBuilder();
        paramRequest.append("Unexpected response code for CONNECT: ");
        paramRequest.append(((Response)localObject).code());
        throw new IOException(paramRequest.toString());
      }
      paramRequest = this.route.address().proxyAuthenticator().authenticate(this.route, (Response)localObject);
      if (paramRequest == null)
        throw new IOException("Failed to authenticate with proxy");
      if ("close".equalsIgnoreCase(((Response)localObject).header("Connection")))
        return paramRequest;
    }
    if ((this.source.getBuffer().exhausted()) && (this.sink.buffer().exhausted()))
      return null;
    throw new IOException("TLS tunnel buffered too many bytes!");
  }

  private Request createTunnelRequest()
    throws IOException
  {
    Object localObject1 = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    Object localObject2 = new Response.Builder().request((Request)localObject1).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build();
    localObject2 = this.route.address().proxyAuthenticator().authenticate(this.route, (Response)localObject2);
    if (localObject2 != null)
      localObject1 = localObject2;
    return localObject1;
  }

  private void establishProtocol(ConnectionSpecSelector paramConnectionSpecSelector, int paramInt, Call paramCall, EventListener paramEventListener)
    throws IOException
  {
    if (this.route.address().sslSocketFactory() == null)
    {
      if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE))
      {
        this.socket = this.rawSocket;
        this.protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        startHttp2(paramInt);
        return;
      }
      this.socket = this.rawSocket;
      this.protocol = Protocol.HTTP_1_1;
      return;
    }
    paramEventListener.secureConnectStart(paramCall);
    connectTls(paramConnectionSpecSelector);
    paramEventListener.secureConnectEnd(paramCall, this.handshake);
    if (this.protocol == Protocol.HTTP_2)
      startHttp2(paramInt);
  }

  private boolean routeMatchesAny(List<Route> paramList)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      Route localRoute = (Route)paramList.get(i);
      if ((localRoute.proxy().type() == Proxy.Type.DIRECT) && (this.route.proxy().type() == Proxy.Type.DIRECT) && (this.route.socketAddress().equals(localRoute.socketAddress())))
        return true;
      i += 1;
    }
    return false;
  }

  private void startHttp2(int paramInt)
    throws IOException
  {
    this.socket.setSoTimeout(0);
    this.http2Connection = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(paramInt).build();
    this.http2Connection.start();
  }

  static RealConnection testConnection(RealConnectionPool paramRealConnectionPool, Route paramRoute, Socket paramSocket, long paramLong)
  {
    paramRealConnectionPool = new RealConnection(paramRealConnectionPool, paramRoute);
    paramRealConnectionPool.socket = paramSocket;
    paramRealConnectionPool.idleAtNanos = paramLong;
    return paramRealConnectionPool;
  }

  public void cancel()
  {
    Util.closeQuietly(this.rawSocket);
  }

  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, Call arg6, EventListener paramEventListener)
  {
    if (this.protocol != null)
      throw new IllegalStateException("already connected");
    Object localObject1 = this.route.address().connectionSpecs();
    ConnectionSpecSelector localConnectionSpecSelector = new ConnectionSpecSelector((List)localObject1);
    if (this.route.address().sslSocketFactory() == null)
    {
      if (!((List)localObject1).contains(ConnectionSpec.CLEARTEXT))
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
      localObject1 = this.route.address().url().host();
      if (!Platform.get().isCleartextTrafficPermitted((String)localObject1))
      {
        ??? = new StringBuilder();
        ???.append("CLEARTEXT communication to ");
        ???.append((String)localObject1);
        ???.append(" not permitted by network security policy");
        throw new RouteException(new UnknownServiceException(???.toString()));
      }
    }
    else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE))
    {
      throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
    }
    Object localObject2 = null;
    label293: Object localObject3;
    do
    {
      try
      {
        if (this.route.requiresTunnel())
        {
          connectTunnel(paramInt1, paramInt2, paramInt3, ???, paramEventListener);
          localObject1 = this.rawSocket;
          if (localObject1 == null)
            break label293;
        }
        try
        {
          connectSocket(paramInt1, paramInt2, ???, paramEventListener);
          try
          {
            establishProtocol(localConnectionSpecSelector, paramInt4, ???, paramEventListener);
            paramEventListener.connectEnd(???, this.route.socketAddress(), this.route.proxy(), this.protocol);
            if ((this.route.requiresTunnel()) && (this.rawSocket == null))
              throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
            if (this.http2Connection != null)
              synchronized (this.connectionPool)
              {
                this.allocationLimit = this.http2Connection.maxConcurrentStreams();
                return;
              }
            return;
          }
          catch (IOException localIOException1)
          {
          }
        }
        catch (IOException localIOException2)
        {
        }
      }
      catch (IOException localIOException3)
      {
      }
      Util.closeQuietly(this.socket);
      Util.closeQuietly(this.rawSocket);
      this.socket = null;
      this.rawSocket = null;
      this.source = null;
      this.sink = null;
      this.handshake = null;
      this.protocol = null;
      this.http2Connection = null;
      paramEventListener.connectFailed(???, this.route.socketAddress(), this.route.proxy(), null, localIOException3);
      if (localObject2 == null)
      {
        localObject3 = new RouteException(localIOException3);
      }
      else
      {
        localObject2.addConnectException(localIOException3);
        localObject3 = localObject2;
      }
      if (!paramBoolean)
        break;
      localObject2 = localObject3;
    }
    while (localConnectionSpecSelector.connectionFailed(localIOException3));
    throw ((Throwable)localObject3);
  }

  public Handshake handshake()
  {
    return this.handshake;
  }

  boolean isEligible(Address paramAddress, @Nullable List<Route> paramList)
  {
    if (this.transmitters.size() < this.allocationLimit)
    {
      if (this.noNewExchanges)
        return false;
      if (!Internal.instance.equalsNonHost(this.route.address(), paramAddress))
        return false;
      if (paramAddress.url().host().equals(route().address().url().host()))
        return true;
      if (this.http2Connection == null)
        return false;
      if (paramList != null)
      {
        if (!routeMatchesAny(paramList))
          return false;
        if (paramAddress.hostnameVerifier() != OkHostnameVerifier.INSTANCE)
          return false;
        if (!supportsUrl(paramAddress.url()))
          return false;
      }
    }
    try
    {
      paramAddress.certificatePinner().check(paramAddress.url().host(), handshake().peerCertificates());
      return true;
      return false;
      return false;
    }
    catch (SSLPeerUnverifiedException paramAddress)
    {
    }
    return false;
  }

  public boolean isHealthy(boolean paramBoolean)
  {
    if ((!this.socket.isClosed()) && (!this.socket.isInputShutdown()))
    {
      if (this.socket.isOutputShutdown())
        return false;
      if (this.http2Connection != null)
        return this.http2Connection.isShutdown() ^ true;
      if (!paramBoolean);
    }
    try
    {
      int i = this.socket.getSoTimeout();
      try
      {
        this.socket.setSoTimeout(1);
        paramBoolean = this.source.exhausted();
        return !paramBoolean;
      }
      finally
      {
        this.socket.setSoTimeout(i);
      }
      return true;
      return false;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
      return true;
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  public boolean isMultiplexed()
  {
    return this.http2Connection != null;
  }

  ExchangeCodec newCodec(OkHttpClient paramOkHttpClient, Interceptor.Chain paramChain)
    throws SocketException
  {
    if (this.http2Connection != null)
      return new Http2ExchangeCodec(paramOkHttpClient, this, paramChain, this.http2Connection);
    this.socket.setSoTimeout(paramChain.readTimeoutMillis());
    this.source.timeout().timeout(paramChain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
    this.sink.timeout().timeout(paramChain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    return new Http1ExchangeCodec(paramOkHttpClient, this, this.source, this.sink);
  }

  RealWebSocket.Streams newWebSocketStreams(final Exchange paramExchange)
    throws SocketException
  {
    this.socket.setSoTimeout(0);
    noNewExchanges();
    return new RealWebSocket.Streams(true, this.source, this.sink)
    {
      public void close()
        throws IOException
      {
        paramExchange.bodyComplete(-1L, true, true, null);
      }
    };
  }

  public void noNewExchanges()
  {
    synchronized (this.connectionPool)
    {
      this.noNewExchanges = true;
      return;
    }
  }

  public void onSettings(Http2Connection paramHttp2Connection)
  {
    synchronized (this.connectionPool)
    {
      this.allocationLimit = paramHttp2Connection.maxConcurrentStreams();
      return;
    }
  }

  public void onStream(Http2Stream paramHttp2Stream)
    throws IOException
  {
    paramHttp2Stream.close(ErrorCode.REFUSED_STREAM, null);
  }

  public Protocol protocol()
  {
    return this.protocol;
  }

  public Route route()
  {
    return this.route;
  }

  public Socket socket()
  {
    return this.socket;
  }

  public boolean supportsUrl(HttpUrl paramHttpUrl)
  {
    if (paramHttpUrl.port() != this.route.address().url().port())
      return false;
    if (!paramHttpUrl.host().equals(this.route.address().url().host()))
      return (this.handshake != null) && (OkHostnameVerifier.INSTANCE.verify(paramHttpUrl.host(), (X509Certificate)this.handshake.peerCertificates().get(0)));
    return true;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Connection{");
    localStringBuilder.append(this.route.address().url().host());
    localStringBuilder.append(":");
    localStringBuilder.append(this.route.address().url().port());
    localStringBuilder.append(", proxy=");
    localStringBuilder.append(this.route.proxy());
    localStringBuilder.append(" hostAddress=");
    localStringBuilder.append(this.route.socketAddress());
    localStringBuilder.append(" cipherSuite=");
    Object localObject;
    if (this.handshake != null)
      localObject = this.handshake.cipherSuite();
    else
      localObject = "none";
    localStringBuilder.append(localObject);
    localStringBuilder.append(" protocol=");
    localStringBuilder.append(this.protocol);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  void trackFailure(@Nullable IOException paramIOException)
  {
    synchronized (this.connectionPool)
    {
      if ((paramIOException instanceof StreamResetException))
      {
        paramIOException = ((StreamResetException)paramIOException).errorCode;
        if (paramIOException == ErrorCode.REFUSED_STREAM)
        {
          this.refusedStreamCount += 1;
          if (this.refusedStreamCount > 1)
          {
            this.noNewExchanges = true;
            this.routeFailureCount += 1;
          }
        }
        else if (paramIOException != ErrorCode.CANCEL)
        {
          this.noNewExchanges = true;
          this.routeFailureCount += 1;
        }
      }
      else if ((!isMultiplexed()) || ((paramIOException instanceof ConnectionShutdownException)))
      {
        this.noNewExchanges = true;
        if (this.successCount == 0)
        {
          if (paramIOException != null)
            this.connectionPool.connectFailed(this.route, paramIOException);
          this.routeFailureCount += 1;
        }
      }
      return;
    }
  }
}