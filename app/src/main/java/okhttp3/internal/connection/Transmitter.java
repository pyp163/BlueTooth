package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.EventListener.Factory;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;

public final class Transmitter
{
  private final Call call;

  @Nullable
  private Object callStackTrace;
  private boolean canceled;
  private final OkHttpClient client;
  public RealConnection connection;
  private final RealConnectionPool connectionPool;
  private final EventListener eventListener;

  @Nullable
  private Exchange exchange;
  private ExchangeFinder exchangeFinder;
  private boolean exchangeRequestDone;
  private boolean exchangeResponseDone;
  private boolean noMoreExchanges;
  private Request request;
  private final AsyncTimeout timeout = new AsyncTimeout()
  {
    protected void timedOut()
    {
      Transmitter.this.cancel();
    }
  };
  private boolean timeoutEarlyExit;

  public Transmitter(OkHttpClient paramOkHttpClient, Call paramCall)
  {
    this.client = paramOkHttpClient;
    this.connectionPool = Internal.instance.realConnectionPool(paramOkHttpClient.connectionPool());
    this.call = paramCall;
    this.eventListener = paramOkHttpClient.eventListenerFactory().create(paramCall);
    this.timeout.timeout(paramOkHttpClient.callTimeoutMillis(), TimeUnit.MILLISECONDS);
  }

  private Address createAddress(HttpUrl paramHttpUrl)
  {
    Object localObject2;
    Object localObject1;
    CertificatePinner localCertificatePinner;
    Object localObject3;
    if (paramHttpUrl.isHttps())
    {
      localObject2 = this.client.sslSocketFactory();
      localObject1 = this.client.hostnameVerifier();
      localCertificatePinner = this.client.certificatePinner();
      localObject3 = localCertificatePinner;
    }
    else
    {
      localCertificatePinner = null;
      localObject1 = localCertificatePinner;
      localObject3 = localObject1;
      localObject2 = localCertificatePinner;
    }
    return new Address(paramHttpUrl.host(), paramHttpUrl.port(), this.client.dns(), this.client.socketFactory(), (SSLSocketFactory)localObject2, (HostnameVerifier)localObject1, (CertificatePinner)localObject3, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
  }

  @Nullable
  private IOException maybeReleaseConnection(@Nullable IOException paramIOException, boolean paramBoolean)
  {
    RealConnectionPool localRealConnectionPool = this.connectionPool;
    if (paramBoolean);
    try
    {
      if (this.exchange != null)
        throw new IllegalStateException("cannot release connection while it is in use");
      RealConnection localRealConnection = this.connection;
      if ((this.connection != null) && (this.exchange == null) && ((paramBoolean) || (this.noMoreExchanges)))
      {
        localObject = releaseConnectionNoEvents();
        if (this.connection != null)
          localRealConnection = null;
        paramBoolean = this.noMoreExchanges;
        int j = 0;
        if ((!paramBoolean) || (this.exchange != null))
          break label202;
        i = 1;
        Util.closeQuietly((Socket)localObject);
        if (localRealConnection != null)
          this.eventListener.connectionReleased(this.call, localRealConnection);
        localObject = paramIOException;
        if (i != 0)
        {
          i = j;
          if (paramIOException != null)
            i = 1;
          localObject = timeoutExit(paramIOException);
          if (i != 0)
          {
            this.eventListener.callFailed(this.call, (IOException)localObject);
            return localObject;
          }
          this.eventListener.callEnd(this.call);
        }
        return localObject;
        throw paramIOException;
      }
    }
    finally
    {
      while (true)
      {
        continue;
        Object localObject = null;
        continue;
        label202: int i = 0;
      }
    }
  }

  @Nullable
  private IOException timeoutExit(@Nullable IOException paramIOException)
  {
    if (this.timeoutEarlyExit)
      return paramIOException;
    if (!this.timeout.exit())
      return paramIOException;
    InterruptedIOException localInterruptedIOException = new InterruptedIOException("timeout");
    if (paramIOException != null)
      localInterruptedIOException.initCause(paramIOException);
    return localInterruptedIOException;
  }

  void acquireConnectionNoEvents(RealConnection paramRealConnection)
  {
    if (this.connection != null)
      throw new IllegalStateException();
    this.connection = paramRealConnection;
    paramRealConnection.transmitters.add(new TransmitterReference(this, this.callStackTrace));
  }

  public void callStart()
  {
    this.callStackTrace = Platform.get().getStackTraceForCloseable("response.body().close()");
    this.eventListener.callStart(this.call);
  }

  public boolean canRetry()
  {
    return (this.exchangeFinder.hasStreamFailure()) && (this.exchangeFinder.hasRouteToTry());
  }

  public void cancel()
  {
    synchronized (this.connectionPool)
    {
      this.canceled = true;
      Exchange localExchange = this.exchange;
      RealConnection localRealConnection;
      if ((this.exchangeFinder != null) && (this.exchangeFinder.connectingConnection() != null))
        localRealConnection = this.exchangeFinder.connectingConnection();
      else
        localRealConnection = this.connection;
      if (localExchange != null)
      {
        localExchange.cancel();
        return;
      }
      if (localRealConnection != null)
        localRealConnection.cancel();
      return;
    }
  }

  public void exchangeDoneDueToException()
  {
    synchronized (this.connectionPool)
    {
      if (this.noMoreExchanges)
        throw new IllegalStateException();
      this.exchange = null;
      return;
    }
  }

  @Nullable
  IOException exchangeMessageDone(Exchange paramExchange, boolean paramBoolean1, boolean paramBoolean2, @Nullable IOException paramIOException)
  {
    while (true)
    {
      synchronized (this.connectionPool)
      {
        if (paramExchange != this.exchange)
          return paramIOException;
        boolean bool3 = true;
        if (paramBoolean1)
        {
          bool1 = this.exchangeRequestDone ^ true;
          this.exchangeRequestDone = true;
          boolean bool2 = bool1;
          if (paramBoolean2)
          {
            if (!this.exchangeResponseDone)
              bool1 = true;
            this.exchangeResponseDone = true;
            bool2 = bool1;
          }
          if ((!this.exchangeRequestDone) || (!this.exchangeResponseDone) || (!bool2))
            break label155;
          paramExchange = this.exchange.connection();
          paramExchange.successCount += 1;
          this.exchange = null;
          bool1 = bool3;
          paramExchange = paramIOException;
          if (bool1)
            paramExchange = maybeReleaseConnection(paramIOException, false);
          return paramExchange;
        }
      }
      boolean bool1 = false;
      continue;
      label155: bool1 = false;
    }
  }

  public boolean hasExchange()
  {
    while (true)
    {
      synchronized (this.connectionPool)
      {
        if (this.exchange != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }

  public boolean isCanceled()
  {
    synchronized (this.connectionPool)
    {
      boolean bool = this.canceled;
      return bool;
    }
  }

  Exchange newExchange(Interceptor.Chain arg1, boolean paramBoolean)
  {
    synchronized (this.connectionPool)
    {
      if (this.noMoreExchanges)
        throw new IllegalStateException("released");
      if (this.exchange != null)
        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
      ??? = this.exchangeFinder.find(this.client, ???, paramBoolean);
      ??? = new Exchange(this, this.call, this.eventListener, this.exchangeFinder, ???);
      synchronized (this.connectionPool)
      {
        this.exchange = ((Exchange)???);
        this.exchangeRequestDone = false;
        this.exchangeResponseDone = false;
        return ???;
      }
    }
  }

  @Nullable
  public IOException noMoreExchanges(@Nullable IOException paramIOException)
  {
    synchronized (this.connectionPool)
    {
      this.noMoreExchanges = true;
      return maybeReleaseConnection(paramIOException, false);
    }
  }

  public void prepareToConnect(Request paramRequest)
  {
    if (this.request != null)
    {
      if ((Util.sameConnection(this.request.url(), paramRequest.url())) && (this.exchangeFinder.hasRouteToTry()))
        return;
      if (this.exchange != null)
        throw new IllegalStateException();
      if (this.exchangeFinder != null)
      {
        maybeReleaseConnection(null, true);
        this.exchangeFinder = null;
      }
    }
    this.request = paramRequest;
    this.exchangeFinder = new ExchangeFinder(this, this.connectionPool, createAddress(paramRequest.url()), this.call, this.eventListener);
  }

  @Nullable
  Socket releaseConnectionNoEvents()
  {
    int i = 0;
    int j = this.connection.transmitters.size();
    while (i < j)
    {
      if (((Reference)this.connection.transmitters.get(i)).get() == this)
        break label55;
      i += 1;
    }
    i = -1;
    label55: if (i == -1)
      throw new IllegalStateException();
    RealConnection localRealConnection = this.connection;
    localRealConnection.transmitters.remove(i);
    this.connection = null;
    if (localRealConnection.transmitters.isEmpty())
    {
      localRealConnection.idleAtNanos = System.nanoTime();
      if (this.connectionPool.connectionBecameIdle(localRealConnection))
        return localRealConnection.socket();
    }
    return null;
  }

  public Timeout timeout()
  {
    return this.timeout;
  }

  public void timeoutEarlyExit()
  {
    if (this.timeoutEarlyExit)
      throw new IllegalStateException();
    this.timeoutEarlyExit = true;
    this.timeout.exit();
  }

  public void timeoutEnter()
  {
    this.timeout.enter();
  }

  static final class TransmitterReference extends WeakReference<Transmitter>
  {
    final Object callStackTrace;

    TransmitterReference(Transmitter paramTransmitter, Object paramObject)
    {
      super();
      this.callStackTrace = paramObject;
    }
  }
}