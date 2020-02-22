package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

public final class ConnectInterceptor
  implements Interceptor
{
  public final OkHttpClient client;

  public ConnectInterceptor(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }

  public Response intercept(Interceptor.Chain paramChain)
    throws IOException
  {
    RealInterceptorChain localRealInterceptorChain = (RealInterceptorChain)paramChain;
    Request localRequest = localRealInterceptorChain.request();
    Transmitter localTransmitter = localRealInterceptorChain.transmitter();
    return localRealInterceptorChain.proceed(localRequest, localTransmitter, localTransmitter.newExchange(paramChain, localRequest.method().equals("GET") ^ true));
  }
}