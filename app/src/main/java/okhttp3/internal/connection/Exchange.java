package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Exchange
{
  final Call call;
  final ExchangeCodec codec;
  private boolean duplex;
  final EventListener eventListener;
  final ExchangeFinder finder;
  final Transmitter transmitter;

  public Exchange(Transmitter paramTransmitter, Call paramCall, EventListener paramEventListener, ExchangeFinder paramExchangeFinder, ExchangeCodec paramExchangeCodec)
  {
    this.transmitter = paramTransmitter;
    this.call = paramCall;
    this.eventListener = paramEventListener;
    this.finder = paramExchangeFinder;
    this.codec = paramExchangeCodec;
  }

  @Nullable
  IOException bodyComplete(long paramLong, boolean paramBoolean1, boolean paramBoolean2, @Nullable IOException paramIOException)
  {
    if (paramIOException != null)
      trackFailure(paramIOException);
    if (paramBoolean2)
      if (paramIOException != null)
        this.eventListener.requestFailed(this.call, paramIOException);
      else
        this.eventListener.requestBodyEnd(this.call, paramLong);
    if (paramBoolean1)
      if (paramIOException != null)
        this.eventListener.responseFailed(this.call, paramIOException);
      else
        this.eventListener.responseBodyEnd(this.call, paramLong);
    return this.transmitter.exchangeMessageDone(this, paramBoolean2, paramBoolean1, paramIOException);
  }

  public void cancel()
  {
    this.codec.cancel();
  }

  public RealConnection connection()
  {
    return this.codec.connection();
  }

  public Sink createRequestBody(Request paramRequest, boolean paramBoolean)
    throws IOException
  {
    this.duplex = paramBoolean;
    long l = paramRequest.body().contentLength();
    this.eventListener.requestBodyStart(this.call);
    return new RequestBodySink(this.codec.createRequestBody(paramRequest, l), l);
  }

  public void detachWithViolence()
  {
    this.codec.cancel();
    this.transmitter.exchangeMessageDone(this, true, true, null);
  }

  public void finishRequest()
    throws IOException
  {
    try
    {
      this.codec.finishRequest();
      return;
    }
    catch (IOException localIOException)
    {
      this.eventListener.requestFailed(this.call, localIOException);
      trackFailure(localIOException);
      throw localIOException;
    }
  }

  public void flushRequest()
    throws IOException
  {
    try
    {
      this.codec.flushRequest();
      return;
    }
    catch (IOException localIOException)
    {
      this.eventListener.requestFailed(this.call, localIOException);
      trackFailure(localIOException);
      throw localIOException;
    }
  }

  public boolean isDuplex()
  {
    return this.duplex;
  }

  public RealWebSocket.Streams newWebSocketStreams()
    throws SocketException
  {
    this.transmitter.timeoutEarlyExit();
    return this.codec.connection().newWebSocketStreams(this);
  }

  public void noNewExchangesOnConnection()
  {
    this.codec.connection().noNewExchanges();
  }

  public void noRequestBody()
  {
    this.transmitter.exchangeMessageDone(this, true, false, null);
  }

  public ResponseBody openResponseBody(Response paramResponse)
    throws IOException
  {
    try
    {
      this.eventListener.responseBodyStart(this.call);
      String str = paramResponse.header("Content-Type");
      long l = this.codec.reportedContentLength(paramResponse);
      paramResponse = new RealResponseBody(str, l, Okio.buffer(new ResponseBodySource(this.codec.openResponseBodySource(paramResponse), l)));
      return paramResponse;
    }
    catch (IOException paramResponse)
    {
      this.eventListener.responseFailed(this.call, paramResponse);
      trackFailure(paramResponse);
    }
    throw paramResponse;
  }

  @Nullable
  public Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException
  {
    try
    {
      Response.Builder localBuilder = this.codec.readResponseHeaders(paramBoolean);
      if (localBuilder != null)
        Internal.instance.initExchange(localBuilder, this);
      return localBuilder;
    }
    catch (IOException localIOException)
    {
      this.eventListener.responseFailed(this.call, localIOException);
      trackFailure(localIOException);
      throw localIOException;
    }
  }

  public void responseHeadersEnd(Response paramResponse)
  {
    this.eventListener.responseHeadersEnd(this.call, paramResponse);
  }

  public void responseHeadersStart()
  {
    this.eventListener.responseHeadersStart(this.call);
  }

  public void timeoutEarlyExit()
  {
    this.transmitter.timeoutEarlyExit();
  }

  void trackFailure(IOException paramIOException)
  {
    this.finder.trackFailure();
    this.codec.connection().trackFailure(paramIOException);
  }

  public Headers trailers()
    throws IOException
  {
    return this.codec.trailers();
  }

  public void webSocketUpgradeFailed()
  {
    bodyComplete(-1L, true, true, null);
  }

  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    try
    {
      this.eventListener.requestHeadersStart(this.call);
      this.codec.writeRequestHeaders(paramRequest);
      this.eventListener.requestHeadersEnd(this.call, paramRequest);
      return;
    }
    catch (IOException paramRequest)
    {
      this.eventListener.requestFailed(this.call, paramRequest);
      trackFailure(paramRequest);
    }
    throw paramRequest;
  }

  private final class RequestBodySink extends ForwardingSink
  {
    private long bytesReceived;
    private boolean closed;
    private boolean completed;
    private long contentLength;

    RequestBodySink(Sink paramLong, long arg3)
    {
      super();
      Object localObject;
      this.contentLength = localObject;
    }

    @Nullable
    private IOException complete(@Nullable IOException paramIOException)
    {
      if (this.completed)
        return paramIOException;
      this.completed = true;
      return Exchange.this.bodyComplete(this.bytesReceived, false, true, paramIOException);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      this.closed = true;
      if ((this.contentLength != -1L) && (this.bytesReceived != this.contentLength))
        throw new ProtocolException("unexpected end of stream");
      try
      {
        super.close();
        complete(null);
        return;
      }
      catch (IOException localIOException)
      {
        throw complete(localIOException);
      }
    }

    public void flush()
      throws IOException
    {
      try
      {
        super.flush();
        return;
      }
      catch (IOException localIOException)
      {
        throw complete(localIOException);
      }
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      if ((this.contentLength != -1L) && (this.bytesReceived + paramLong > this.contentLength))
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("expected ");
        paramBuffer.append(this.contentLength);
        paramBuffer.append(" bytes but received ");
        paramBuffer.append(this.bytesReceived + paramLong);
        throw new ProtocolException(paramBuffer.toString());
      }
      try
      {
        super.write(paramBuffer, paramLong);
        this.bytesReceived += paramLong;
        return;
      }
      catch (IOException paramBuffer)
      {
      }
      throw complete(paramBuffer);
    }
  }

  final class ResponseBodySource extends ForwardingSource
  {
    private long bytesReceived;
    private boolean closed;
    private boolean completed;
    private final long contentLength;

    ResponseBodySource(Source paramLong, long arg3)
    {
      super();
      Object localObject;
      this.contentLength = localObject;
      if (localObject == 0L)
        complete(null);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      this.closed = true;
      try
      {
        super.close();
        complete(null);
        return;
      }
      catch (IOException localIOException)
      {
        throw complete(localIOException);
      }
    }

    @Nullable
    IOException complete(@Nullable IOException paramIOException)
    {
      if (this.completed)
        return paramIOException;
      this.completed = true;
      return Exchange.this.bodyComplete(this.bytesReceived, true, false, paramIOException);
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      try
      {
        paramLong = delegate().read(paramBuffer, paramLong);
        if (paramLong == -1L)
        {
          complete(null);
          return -1L;
        }
        long l = this.bytesReceived + paramLong;
        if ((this.contentLength != -1L) && (l > this.contentLength))
        {
          paramBuffer = new StringBuilder();
          paramBuffer.append("expected ");
          paramBuffer.append(this.contentLength);
          paramBuffer.append(" bytes but received ");
          paramBuffer.append(l);
          throw new ProtocolException(paramBuffer.toString());
        }
        this.bytesReceived = l;
        if (l == this.contentLength)
          complete(null);
        return paramLong;
      }
      catch (IOException paramBuffer)
      {
      }
      throw complete(paramBuffer);
    }
  }
}