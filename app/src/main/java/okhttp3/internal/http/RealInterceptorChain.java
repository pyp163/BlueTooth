package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.Transmitter;

public final class RealInterceptorChain
  implements Interceptor.Chain
{
  private final Call call;
  private int calls;
  private final int connectTimeout;

  @Nullable
  private final Exchange exchange;
  private final int index;
  private final List<Interceptor> interceptors;
  private final int readTimeout;
  private final Request request;
  private final Transmitter transmitter;
  private final int writeTimeout;

  public RealInterceptorChain(List<Interceptor> paramList, Transmitter paramTransmitter, @Nullable Exchange paramExchange, int paramInt1, Request paramRequest, Call paramCall, int paramInt2, int paramInt3, int paramInt4)
  {
    this.interceptors = paramList;
    this.transmitter = paramTransmitter;
    this.exchange = paramExchange;
    this.index = paramInt1;
    this.request = paramRequest;
    this.call = paramCall;
    this.connectTimeout = paramInt2;
    this.readTimeout = paramInt3;
    this.writeTimeout = paramInt4;
  }

  public Call call()
  {
    return this.call;
  }

  public int connectTimeoutMillis()
  {
    return this.connectTimeout;
  }

  @Nullable
  public Connection connection()
  {
    if (this.exchange != null)
      return this.exchange.connection();
    return null;
  }

  public Exchange exchange()
  {
    if (this.exchange == null)
      throw new IllegalStateException();
    return this.exchange;
  }

  public Response proceed(Request paramRequest)
    throws IOException
  {
    return proceed(paramRequest, this.transmitter, this.exchange);
  }

  public Response proceed(Request paramRequest, Transmitter paramTransmitter, @Nullable Exchange paramExchange)
    throws IOException
  {
    if (this.index >= this.interceptors.size())
      throw new AssertionError();
    this.calls += 1;
    if ((this.exchange != null) && (!this.exchange.connection().supportsUrl(paramRequest.url())))
    {
      paramRequest = new StringBuilder();
      paramRequest.append("network interceptor ");
      paramRequest.append(this.interceptors.get(this.index - 1));
      paramRequest.append(" must retain the same host and port");
      throw new IllegalStateException(paramRequest.toString());
    }
    if ((this.exchange != null) && (this.calls > 1))
    {
      paramRequest = new StringBuilder();
      paramRequest.append("network interceptor ");
      paramRequest.append(this.interceptors.get(this.index - 1));
      paramRequest.append(" must call proceed() exactly once");
      throw new IllegalStateException(paramRequest.toString());
    }
    paramTransmitter = new RealInterceptorChain(this.interceptors, paramTransmitter, paramExchange, this.index + 1, paramRequest, this.call, this.connectTimeout, this.readTimeout, this.writeTimeout);
    paramRequest = (Interceptor)this.interceptors.get(this.index);
    Response localResponse = paramRequest.intercept(paramTransmitter);
    if ((paramExchange != null) && (this.index + 1 < this.interceptors.size()) && (paramTransmitter.calls != 1))
    {
      paramTransmitter = new StringBuilder();
      paramTransmitter.append("network interceptor ");
      paramTransmitter.append(paramRequest);
      paramTransmitter.append(" must call proceed() exactly once");
      throw new IllegalStateException(paramTransmitter.toString());
    }
    if (localResponse == null)
    {
      paramTransmitter = new StringBuilder();
      paramTransmitter.append("interceptor ");
      paramTransmitter.append(paramRequest);
      paramTransmitter.append(" returned null");
      throw new NullPointerException(paramTransmitter.toString());
    }
    if (localResponse.body() == null)
    {
      paramTransmitter = new StringBuilder();
      paramTransmitter.append("interceptor ");
      paramTransmitter.append(paramRequest);
      paramTransmitter.append(" returned a response with no body");
      throw new IllegalStateException(paramTransmitter.toString());
    }
    return localResponse;
  }

  public int readTimeoutMillis()
  {
    return this.readTimeout;
  }

  public Request request()
  {
    return this.request;
  }

  public Transmitter transmitter()
  {
    return this.transmitter;
  }

  public Interceptor.Chain withConnectTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, paramInt, this.readTimeout, this.writeTimeout);
  }

  public Interceptor.Chain withReadTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, paramInt, this.writeTimeout);
  }

  public Interceptor.Chain withWriteTimeout(int paramInt, TimeUnit paramTimeUnit)
  {
    paramInt = Util.checkDuration("timeout", paramInt, paramTimeUnit);
    return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, this.readTimeout, paramInt);
  }

  public int writeTimeoutMillis()
  {
    return this.writeTimeout;
  }
}