package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealConnection;
import okio.BufferedSink;
import okio.Okio;

public final class CallServerInterceptor
  implements Interceptor
{
  private final boolean forWebSocket;

  public CallServerInterceptor(boolean paramBoolean)
  {
    this.forWebSocket = paramBoolean;
  }

  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    paramChain = (RealInterceptorChain)paramChain;
    Exchange localExchange = paramChain.exchange();
    Request localRequest = paramChain.request();
    long l = System.currentTimeMillis();
    localExchange.writeRequestHeaders(localRequest);
    boolean bool = HttpMethod.permitsRequestBody(localRequest.method());
    Object localObject = null;
    paramChain = null;
    if ((bool) && (localRequest.body() != null))
    {
      if ("100-continue".equalsIgnoreCase(localRequest.header("Expect")))
      {
        localExchange.flushRequest();
        localExchange.responseHeadersStart();
        paramChain = localExchange.readResponseHeaders(true);
        i = 1;
      }
      else
      {
        i = 0;
      }
      if (paramChain == null)
      {
        if (localRequest.body().isDuplex())
        {
          localExchange.flushRequest();
          localObject = Okio.buffer(localExchange.createRequestBody(localRequest, true));
          localRequest.body().writeTo((BufferedSink)localObject);
          j = i;
          localObject = paramChain;
        }
        else
        {
          localObject = Okio.buffer(localExchange.createRequestBody(localRequest, false));
          localRequest.body().writeTo((BufferedSink)localObject);
          ((BufferedSink)localObject).close();
          j = i;
          localObject = paramChain;
        }
      }
      else
      {
        localExchange.noRequestBody();
        j = i;
        localObject = paramChain;
        if (!localExchange.connection().isMultiplexed())
        {
          localExchange.noNewExchangesOnConnection();
          j = i;
          localObject = paramChain;
        }
      }
    }
    else
    {
      localExchange.noRequestBody();
      j = 0;
    }
    if ((localRequest.body() == null) || (!localRequest.body().isDuplex()))
      localExchange.finishRequest();
    if (j == 0)
      localExchange.responseHeadersStart();
    paramChain = (Interceptor.Chain)localObject;
    if (localObject == null)
      paramChain = localExchange.readResponseHeaders(false);
    paramChain = paramChain.request(localRequest).handshake(localExchange.connection().handshake()).sentRequestAtMillis(l).receivedResponseAtMillis(System.currentTimeMillis()).build();
    int j = paramChain.code();
    int i = j;
    if (j == 100)
    {
      paramChain = localExchange.readResponseHeaders(false).request(localRequest).handshake(localExchange.connection().handshake()).sentRequestAtMillis(l).receivedResponseAtMillis(System.currentTimeMillis()).build();
      i = paramChain.code();
    }
    localExchange.responseHeadersEnd(paramChain);
    if ((this.forWebSocket) && (i == 101))
      paramChain = paramChain.newBuilder().body(Util.EMPTY_RESPONSE).build();
    else
      paramChain = paramChain.newBuilder().body(localExchange.openResponseBody(paramChain)).build();
    if (("close".equalsIgnoreCase(paramChain.request().header("Connection"))) || ("close".equalsIgnoreCase(paramChain.header("Connection"))))
      localExchange.noNewExchangesOnConnection();
    if (((i == 204) || (i == 205)) && (paramChain.body().contentLength() > 0L))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("HTTP ");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(" had non-zero Content-Length: ");
      ((StringBuilder)localObject).append(paramChain.body().contentLength());
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    return paramChain;
  }
}