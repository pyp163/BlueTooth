package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.Address;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1ExchangeCodec
  implements ExchangeCodec
{
  private static final int HEADER_LIMIT = 262144;
  private static final int STATE_CLOSED = 6;
  private static final int STATE_IDLE = 0;
  private static final int STATE_OPEN_REQUEST_BODY = 1;
  private static final int STATE_OPEN_RESPONSE_BODY = 4;
  private static final int STATE_READING_RESPONSE_BODY = 5;
  private static final int STATE_READ_RESPONSE_HEADERS = 3;
  private static final int STATE_WRITING_REQUEST_BODY = 2;
  private final OkHttpClient client;
  private long headerLimit = 262144L;
  private final RealConnection realConnection;
  private final BufferedSink sink;
  private final BufferedSource source;
  private int state = 0;
  private Headers trailers;

  public Http1ExchangeCodec(OkHttpClient paramOkHttpClient, RealConnection paramRealConnection, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
  {
    this.client = paramOkHttpClient;
    this.realConnection = paramRealConnection;
    this.source = paramBufferedSource;
    this.sink = paramBufferedSink;
  }

  private void detachTimeout(ForwardingTimeout paramForwardingTimeout)
  {
    Timeout localTimeout = paramForwardingTimeout.delegate();
    paramForwardingTimeout.setDelegate(Timeout.NONE);
    localTimeout.clearDeadline();
    localTimeout.clearTimeout();
  }

  private Sink newChunkedSink()
  {
    if (this.state != 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(this.state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.state = 2;
    return new ChunkedSink();
  }

  private Source newChunkedSource(HttpUrl paramHttpUrl)
  {
    if (this.state != 4)
    {
      paramHttpUrl = new StringBuilder();
      paramHttpUrl.append("state: ");
      paramHttpUrl.append(this.state);
      throw new IllegalStateException(paramHttpUrl.toString());
    }
    this.state = 5;
    return new ChunkedSource(paramHttpUrl);
  }

  private Source newFixedLengthSource(long paramLong)
  {
    if (this.state != 4)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(this.state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.state = 5;
    return new FixedLengthSource(paramLong);
  }

  private Sink newKnownLengthSink()
  {
    if (this.state != 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(this.state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.state = 2;
    return new KnownLengthSink(null);
  }

  private Source newUnknownLengthSource()
  {
    if (this.state != 4)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("state: ");
      localStringBuilder.append(this.state);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.state = 5;
    this.realConnection.noNewExchanges();
    return new UnknownLengthSource(null);
  }

  private String readHeaderLine()
    throws IOException
  {
    String str = this.source.readUtf8LineStrict(this.headerLimit);
    this.headerLimit -= str.length();
    return str;
  }

  private Headers readHeaders()
    throws IOException
  {
    Headers.Builder localBuilder = new Headers.Builder();
    while (true)
    {
      String str = readHeaderLine();
      if (str.length() == 0)
        break;
      Internal.instance.addLenient(localBuilder, str);
    }
    return localBuilder.build();
  }

  public void cancel()
  {
    if (this.realConnection != null)
      this.realConnection.cancel();
  }

  public RealConnection connection()
  {
    return this.realConnection;
  }

  public Sink createRequestBody(Request paramRequest, long paramLong)
    throws IOException
  {
    if ((paramRequest.body() != null) && (paramRequest.body().isDuplex()))
      throw new ProtocolException("Duplex connections are not supported for HTTP/1");
    if ("chunked".equalsIgnoreCase(paramRequest.header("Transfer-Encoding")))
      return newChunkedSink();
    if (paramLong != -1L)
      return newKnownLengthSink();
    throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
  }

  public void finishRequest()
    throws IOException
  {
    this.sink.flush();
  }

  public void flushRequest()
    throws IOException
  {
    this.sink.flush();
  }

  public boolean isClosed()
  {
    return this.state == 6;
  }

  public Source openResponseBodySource(Response paramResponse)
  {
    if (!HttpHeaders.hasBody(paramResponse))
      return newFixedLengthSource(0L);
    if ("chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding")))
      return newChunkedSource(paramResponse.request().url());
    long l = HttpHeaders.contentLength(paramResponse);
    if (l != -1L)
      return newFixedLengthSource(l);
    return newUnknownLengthSource();
  }

  public Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException
  {
    Object localObject1;
    if ((this.state != 1) && (this.state != 3))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("state: ");
      ((StringBuilder)localObject1).append(this.state);
      throw new IllegalStateException(((StringBuilder)localObject1).toString());
    }
    try
    {
      localObject1 = StatusLine.parse(readHeaderLine());
      localObject2 = new Response.Builder().protocol(((StatusLine)localObject1).protocol).code(((StatusLine)localObject1).code).message(((StatusLine)localObject1).message).headers(readHeaders());
      if ((paramBoolean) && (((StatusLine)localObject1).code == 100))
        return null;
      if (((StatusLine)localObject1).code == 100)
      {
        this.state = 3;
        return localObject2;
      }
      this.state = 4;
      return localObject2;
    }
    catch (EOFException localEOFException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("unexpected end of stream on ");
      ((StringBuilder)localObject2).append(this.realConnection.route().address().url().redact());
      throw new IOException(((StringBuilder)localObject2).toString(), localEOFException);
    }
  }

  public long reportedContentLength(Response paramResponse)
  {
    if (!HttpHeaders.hasBody(paramResponse))
      return 0L;
    if ("chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding")))
      return -1L;
    return HttpHeaders.contentLength(paramResponse);
  }

  public void skipConnectBody(Response paramResponse)
    throws IOException
  {
    long l = HttpHeaders.contentLength(paramResponse);
    if (l == -1L)
      return;
    paramResponse = newFixedLengthSource(l);
    Util.skipAll(paramResponse, 2147483647, TimeUnit.MILLISECONDS);
    paramResponse.close();
  }

  public Headers trailers()
  {
    if (this.state != 6)
      throw new IllegalStateException("too early; can't read the trailers yet");
    if (this.trailers != null)
      return this.trailers;
    return Util.EMPTY_HEADERS;
  }

  public void writeRequest(Headers paramHeaders, String paramString)
    throws IOException
  {
    if (this.state != 0)
    {
      paramHeaders = new StringBuilder();
      paramHeaders.append("state: ");
      paramHeaders.append(this.state);
      throw new IllegalStateException(paramHeaders.toString());
    }
    this.sink.writeUtf8(paramString).writeUtf8("\r\n");
    int i = 0;
    int j = paramHeaders.size();
    while (i < j)
    {
      this.sink.writeUtf8(paramHeaders.name(i)).writeUtf8(": ").writeUtf8(paramHeaders.value(i)).writeUtf8("\r\n");
      i += 1;
    }
    this.sink.writeUtf8("\r\n");
    this.state = 1;
  }

  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    String str = RequestLine.get(paramRequest, this.realConnection.route().proxy().type());
    writeRequest(paramRequest.headers(), str);
  }

  private abstract class AbstractSource
    implements Source
  {
    protected boolean closed;
    protected final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.source.timeout());

    private AbstractSource()
    {
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      try
      {
        paramLong = Http1ExchangeCodec.this.source.read(paramBuffer, paramLong);
        return paramLong;
      }
      catch (IOException paramBuffer)
      {
        Http1ExchangeCodec.this.realConnection.noNewExchanges();
        responseBodyComplete();
      }
      throw paramBuffer;
    }

    final void responseBodyComplete()
    {
      if (Http1ExchangeCodec.this.state == 6)
        return;
      if (Http1ExchangeCodec.this.state != 5)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("state: ");
        localStringBuilder.append(Http1ExchangeCodec.this.state);
        throw new IllegalStateException(localStringBuilder.toString());
      }
      Http1ExchangeCodec.this.detachTimeout(this.timeout);
      Http1ExchangeCodec.access$402(Http1ExchangeCodec.this, 6);
    }

    public Timeout timeout()
    {
      return this.timeout;
    }
  }

  private final class ChunkedSink
    implements Sink
  {
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());

    ChunkedSink()
    {
    }

    public void close()
      throws IOException
    {
      try
      {
        boolean bool = this.closed;
        if (bool)
          return;
        this.closed = true;
        Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n");
        Http1ExchangeCodec.this.detachTimeout(this.timeout);
        Http1ExchangeCodec.access$402(Http1ExchangeCodec.this, 3);
        return;
      }
      finally
      {
      }
    }

    public void flush()
      throws IOException
    {
      try
      {
        boolean bool = this.closed;
        if (bool)
          return;
        Http1ExchangeCodec.this.sink.flush();
        return;
      }
      finally
      {
      }
    }

    public Timeout timeout()
    {
      return this.timeout;
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      if (paramLong == 0L)
        return;
      Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(paramLong);
      Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
      Http1ExchangeCodec.this.sink.write(paramBuffer, paramLong);
      Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
    }
  }

  private class ChunkedSource extends Http1ExchangeCodec.AbstractSource
  {
    private static final long NO_CHUNK_YET = -1L;
    private long bytesRemainingInChunk = -1L;
    private boolean hasMoreChunks = true;
    private final HttpUrl url;

    ChunkedSource(HttpUrl arg2)
    {
      super(null);
      Object localObject;
      this.url = localObject;
    }

    private void readChunkSize()
      throws IOException
    {
      if (this.bytesRemainingInChunk != -1L)
        Http1ExchangeCodec.this.source.readUtf8LineStrict();
      try
      {
        this.bytesRemainingInChunk = Http1ExchangeCodec.this.source.readHexadecimalUnsignedLong();
        String str = Http1ExchangeCodec.this.source.readUtf8LineStrict().trim();
        if (this.bytesRemainingInChunk >= 0L)
          if (!str.isEmpty())
          {
            boolean bool = str.startsWith(";");
            if (!bool);
          }
          else
          {
            if (this.bytesRemainingInChunk == 0L)
            {
              this.hasMoreChunks = false;
              Http1ExchangeCodec.access$802(Http1ExchangeCodec.this, Http1ExchangeCodec.this.readHeaders());
              HttpHeaders.receiveHeaders(Http1ExchangeCodec.this.client.cookieJar(), this.url, Http1ExchangeCodec.this.trailers);
              responseBodyComplete();
            }
            return;
          }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("expected chunk size and optional extensions but was \"");
        localStringBuilder.append(this.bytesRemainingInChunk);
        localStringBuilder.append(str);
        localStringBuilder.append("\"");
        throw new ProtocolException(localStringBuilder.toString());
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new ProtocolException(localNumberFormatException.getMessage());
      }
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if ((this.hasMoreChunks) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS)))
      {
        Http1ExchangeCodec.this.realConnection.noNewExchanges();
        responseBodyComplete();
      }
      this.closed = true;
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (this.closed)
        throw new IllegalStateException("closed");
      if (!this.hasMoreChunks)
        return -1L;
      if ((this.bytesRemainingInChunk == 0L) || (this.bytesRemainingInChunk == -1L))
      {
        readChunkSize();
        if (!this.hasMoreChunks)
          return -1L;
      }
      paramLong = super.read(paramBuffer, Math.min(paramLong, this.bytesRemainingInChunk));
      if (paramLong == -1L)
      {
        Http1ExchangeCodec.this.realConnection.noNewExchanges();
        paramBuffer = new ProtocolException("unexpected end of stream");
        responseBodyComplete();
        throw paramBuffer;
      }
      this.bytesRemainingInChunk -= paramLong;
      return paramLong;
    }
  }

  private class FixedLengthSource extends Http1ExchangeCodec.AbstractSource
  {
    private long bytesRemaining;

    FixedLengthSource(long arg2)
    {
      super(null);
      Object localObject;
      this.bytesRemaining = localObject;
      if (this.bytesRemaining == 0L)
        responseBodyComplete();
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if ((this.bytesRemaining != 0L) && (!Util.discard(this, 100, TimeUnit.MILLISECONDS)))
      {
        Http1ExchangeCodec.this.realConnection.noNewExchanges();
        responseBodyComplete();
      }
      this.closed = true;
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (this.closed)
        throw new IllegalStateException("closed");
      if (this.bytesRemaining == 0L)
        return -1L;
      paramLong = super.read(paramBuffer, Math.min(this.bytesRemaining, paramLong));
      if (paramLong == -1L)
      {
        Http1ExchangeCodec.this.realConnection.noNewExchanges();
        paramBuffer = new ProtocolException("unexpected end of stream");
        responseBodyComplete();
        throw paramBuffer;
      }
      this.bytesRemaining -= paramLong;
      if (this.bytesRemaining == 0L)
        responseBodyComplete();
      return paramLong;
    }
  }

  private final class KnownLengthSink
    implements Sink
  {
    private boolean closed;
    private final ForwardingTimeout timeout = new ForwardingTimeout(Http1ExchangeCodec.this.sink.timeout());

    private KnownLengthSink()
    {
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      this.closed = true;
      Http1ExchangeCodec.this.detachTimeout(this.timeout);
      Http1ExchangeCodec.access$402(Http1ExchangeCodec.this, 3);
    }

    public void flush()
      throws IOException
    {
      if (this.closed)
        return;
      Http1ExchangeCodec.this.sink.flush();
    }

    public Timeout timeout()
    {
      return this.timeout;
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      Util.checkOffsetAndCount(paramBuffer.size(), 0L, paramLong);
      Http1ExchangeCodec.this.sink.write(paramBuffer, paramLong);
    }
  }

  private class UnknownLengthSource extends Http1ExchangeCodec.AbstractSource
  {
    private boolean inputExhausted;

    private UnknownLengthSource()
    {
      super(null);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if (!this.inputExhausted)
        responseBodyComplete();
      this.closed = true;
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
      {
        paramBuffer = new StringBuilder();
        paramBuffer.append("byteCount < 0: ");
        paramBuffer.append(paramLong);
        throw new IllegalArgumentException(paramBuffer.toString());
      }
      if (this.closed)
        throw new IllegalStateException("closed");
      if (this.inputExhausted)
        return -1L;
      paramLong = super.read(paramBuffer, paramLong);
      if (paramLong == -1L)
      {
        this.inputExhausted = true;
        responseBodyComplete();
        return -1L;
      }
      return paramLong;
    }
  }
}