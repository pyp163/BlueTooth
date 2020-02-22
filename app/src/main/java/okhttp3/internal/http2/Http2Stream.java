package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream
{
  long bytesLeftInWriteWindow;
  final Http2Connection connection;

  @Nullable
  ErrorCode errorCode;

  @Nullable
  IOException errorException;
  private boolean hasResponseHeaders;
  private final Deque<Headers> headersQueue = new ArrayDeque();
  final int id;
  final StreamTimeout readTimeout = new StreamTimeout();
  final FramingSink sink;
  private final FramingSource source;
  long unacknowledgedBytesRead = 0L;
  final StreamTimeout writeTimeout = new StreamTimeout();

  Http2Stream(int paramInt, Http2Connection paramHttp2Connection, boolean paramBoolean1, boolean paramBoolean2, @Nullable Headers paramHeaders)
  {
    if (paramHttp2Connection == null)
      throw new NullPointerException("connection == null");
    this.id = paramInt;
    this.connection = paramHttp2Connection;
    this.bytesLeftInWriteWindow = paramHttp2Connection.peerSettings.getInitialWindowSize();
    this.source = new FramingSource(paramHttp2Connection.okHttpSettings.getInitialWindowSize());
    this.sink = new FramingSink();
    this.source.finished = paramBoolean2;
    this.sink.finished = paramBoolean1;
    if (paramHeaders != null)
      this.headersQueue.add(paramHeaders);
    if ((isLocallyInitiated()) && (paramHeaders != null))
      throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
    if ((!isLocallyInitiated()) && (paramHeaders == null))
      throw new IllegalStateException("remotely-initiated streams should have headers");
  }

  private boolean closeInternal(ErrorCode paramErrorCode, @Nullable IOException paramIOException)
  {
    try
    {
      if (this.errorCode != null)
        return false;
      if ((this.source.finished) && (this.sink.finished))
        return false;
      this.errorCode = paramErrorCode;
      this.errorException = paramIOException;
      notifyAll();
      this.connection.removeStream(this.id);
      return true;
    }
    finally
    {
    }
    throw paramErrorCode;
  }

  void addBytesToWriteWindow(long paramLong)
  {
    this.bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L)
      notifyAll();
  }

  void cancelStreamIfNecessary()
    throws IOException
  {
    while (true)
    {
      try
      {
        if ((this.source.finished) || (!this.source.closed))
          break label92;
        if (!this.sink.finished)
        {
          if (!this.sink.closed)
            break label92;
          break label87;
          boolean bool = isOpen();
          if (i != 0)
          {
            close(ErrorCode.CANCEL, null);
            return;
          }
          if (!bool)
            this.connection.removeStream(this.id);
          return;
        }
      }
      finally
      {
      }
      label87: int i = 1;
      continue;
      label92: i = 0;
    }
  }

  void checkOutNotClosed()
    throws IOException
  {
    if (this.sink.closed)
      throw new IOException("stream closed");
    if (this.sink.finished)
      throw new IOException("stream finished");
    if (this.errorCode != null)
    {
      Object localObject;
      if (this.errorException != null)
        localObject = this.errorException;
      else
        localObject = new StreamResetException(this.errorCode);
      throw ((Throwable)localObject);
    }
  }

  public void close(ErrorCode paramErrorCode, @Nullable IOException paramIOException)
    throws IOException
  {
    if (!closeInternal(paramErrorCode, paramIOException))
      return;
    this.connection.writeSynReset(this.id, paramErrorCode);
  }

  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode, null))
      return;
    this.connection.writeSynResetLater(this.id, paramErrorCode);
  }

  public void enqueueTrailers(Headers paramHeaders)
  {
    try
    {
      if (this.sink.finished)
        throw new IllegalStateException("already finished");
      if (paramHeaders.size() == 0)
        throw new IllegalArgumentException("trailers.size() == 0");
      FramingSink.access$302(this.sink, paramHeaders);
      return;
    }
    finally
    {
    }
    throw paramHeaders;
  }

  public Http2Connection getConnection()
  {
    return this.connection;
  }

  public ErrorCode getErrorCode()
  {
    try
    {
      ErrorCode localErrorCode = this.errorCode;
      return localErrorCode;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getId()
  {
    return this.id;
  }

  public Sink getSink()
  {
    try
    {
      if ((!this.hasResponseHeaders) && (!isLocallyInitiated()))
        throw new IllegalStateException("reply before requesting the sink");
      return this.sink;
    }
    finally
    {
    }
  }

  public Source getSource()
  {
    return this.source;
  }

  public boolean isLocallyInitiated()
  {
    int i;
    if ((this.id & 0x1) == 1)
      i = 1;
    else
      i = 0;
    return this.connection.client == i;
  }

  public boolean isOpen()
  {
    try
    {
      ErrorCode localErrorCode = this.errorCode;
      if (localErrorCode != null)
        return false;
      if (((this.source.finished) || (this.source.closed)) && ((this.sink.finished) || (this.sink.closed)))
      {
        boolean bool = this.hasResponseHeaders;
        if (bool)
          return false;
      }
      return true;
    }
    finally
    {
    }
  }

  public Timeout readTimeout()
  {
    return this.readTimeout;
  }

  void receiveData(BufferedSource paramBufferedSource, int paramInt)
    throws IOException
  {
    this.source.receive(paramBufferedSource, paramInt);
  }

  void receiveHeaders(Headers paramHeaders, boolean paramBoolean)
  {
    try
    {
      if ((this.hasResponseHeaders) && (paramBoolean))
      {
        FramingSource.access$202(this.source, paramHeaders);
      }
      else
      {
        this.hasResponseHeaders = true;
        this.headersQueue.add(paramHeaders);
      }
      if (paramBoolean)
        this.source.finished = true;
      paramBoolean = isOpen();
      notifyAll();
      if (!paramBoolean)
        this.connection.removeStream(this.id);
      return;
    }
    finally
    {
    }
    throw paramHeaders;
  }

  void receiveRstStream(ErrorCode paramErrorCode)
  {
    try
    {
      if (this.errorCode == null)
      {
        this.errorCode = paramErrorCode;
        notifyAll();
      }
      return;
    }
    finally
    {
      paramErrorCode = finally;
    }
    throw paramErrorCode;
  }

  public Headers takeHeaders()
    throws IOException
  {
    try
    {
      this.readTimeout.enter();
      try
      {
        while ((this.headersQueue.isEmpty()) && (this.errorCode == null))
          waitForIo();
        this.readTimeout.exitAndThrowIfTimedOut();
        Object localObject1;
        if (!this.headersQueue.isEmpty())
        {
          localObject1 = (Headers)this.headersQueue.removeFirst();
          return localObject1;
        }
        if (this.errorException != null)
          localObject1 = this.errorException;
        else
          localObject1 = new StreamResetException(this.errorCode);
        throw ((Throwable)localObject1);
      }
      finally
      {
        this.readTimeout.exitAndThrowIfTimedOut();
      }
    }
    finally
    {
    }
  }

  public Headers trailers()
    throws IOException
  {
    try
    {
      Object localObject1;
      if (this.errorCode != null)
      {
        if (this.errorException != null)
          localObject1 = this.errorException;
        else
          localObject1 = new StreamResetException(this.errorCode);
        throw ((Throwable)localObject1);
      }
      if ((this.source.finished) && (this.source.receiveBuffer.exhausted()) && (this.source.readBuffer.exhausted()))
      {
        if (this.source.trailers != null)
          localObject1 = this.source.trailers;
        else
          localObject1 = Util.EMPTY_HEADERS;
        return localObject1;
      }
      throw new IllegalStateException("too early; can't read the trailers yet");
    }
    finally
    {
    }
  }

  void waitForIo()
    throws InterruptedIOException
  {
    try
    {
      wait();
      return;
      label5: Thread.currentThread().interrupt();
      throw new InterruptedIOException();
    }
    catch (InterruptedException localInterruptedException)
    {
      break label5;
    }
  }

  public void writeHeaders(List<Header> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    if (paramList == null)
      throw new NullPointerException("headers == null");
    while (true)
    {
      try
      {
        this.hasResponseHeaders = true;
        if (paramBoolean1)
          this.sink.finished = true;
        boolean bool = paramBoolean2;
        if (!paramBoolean2)
          synchronized (this.connection)
          {
            if (this.connection.bytesLeftInWriteWindow != 0L)
              break label115;
            paramBoolean2 = true;
            bool = paramBoolean2;
          }
        this.connection.writeHeaders(this.id, paramBoolean1, paramList);
        if (bool)
          this.connection.flush();
        return;
      }
      finally
      {
      }
      label115: paramBoolean2 = false;
    }
  }

  public Timeout writeTimeout()
  {
    return this.writeTimeout;
  }

  final class FramingSink
    implements Sink
  {
    private static final long EMIT_BUFFER_SIZE = 16384L;
    boolean closed;
    boolean finished;
    private final Buffer sendBuffer = new Buffer();
    private Headers trailers;

    FramingSink()
    {
    }

    // ERROR //
    private void emitFrame(boolean paramBoolean)
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   4: astore 4
      //   6: aload 4
      //   8: monitorenter
      //   9: aload_0
      //   10: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   13: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   16: invokevirtual 55	okhttp3/internal/http2/Http2Stream$StreamTimeout:enter	()V
      //   19: aload_0
      //   20: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   23: getfield 58	okhttp3/internal/http2/Http2Stream:bytesLeftInWriteWindow	J
      //   26: lconst_0
      //   27: lcmp
      //   28: ifgt +37 -> 65
      //   31: aload_0
      //   32: getfield 60	okhttp3/internal/http2/Http2Stream$FramingSink:finished	Z
      //   35: ifne +30 -> 65
      //   38: aload_0
      //   39: getfield 62	okhttp3/internal/http2/Http2Stream$FramingSink:closed	Z
      //   42: ifne +23 -> 65
      //   45: aload_0
      //   46: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   49: getfield 66	okhttp3/internal/http2/Http2Stream:errorCode	Lokhttp3/internal/http2/ErrorCode;
      //   52: ifnonnull +13 -> 65
      //   55: aload_0
      //   56: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   59: invokevirtual 69	okhttp3/internal/http2/Http2Stream:waitForIo	()V
      //   62: goto -43 -> 19
      //   65: aload_0
      //   66: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   69: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   72: invokevirtual 72	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
      //   75: aload_0
      //   76: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   79: invokevirtual 75	okhttp3/internal/http2/Http2Stream:checkOutNotClosed	()V
      //   82: aload_0
      //   83: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   86: getfield 58	okhttp3/internal/http2/Http2Stream:bytesLeftInWriteWindow	J
      //   89: aload_0
      //   90: getfield 38	okhttp3/internal/http2/Http2Stream$FramingSink:sendBuffer	Lokio/Buffer;
      //   93: invokevirtual 79	okio/Buffer:size	()J
      //   96: invokestatic 85	java/lang/Math:min	(JJ)J
      //   99: lstore_2
      //   100: aload_0
      //   101: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   104: astore 5
      //   106: aload 5
      //   108: aload 5
      //   110: getfield 58	okhttp3/internal/http2/Http2Stream:bytesLeftInWriteWindow	J
      //   113: lload_2
      //   114: lsub
      //   115: putfield 58	okhttp3/internal/http2/Http2Stream:bytesLeftInWriteWindow	J
      //   118: aload 4
      //   120: monitorexit
      //   121: aload_0
      //   122: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   125: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   128: invokevirtual 55	okhttp3/internal/http2/Http2Stream$StreamTimeout:enter	()V
      //   131: iload_1
      //   132: ifeq +95 -> 227
      //   135: lload_2
      //   136: aload_0
      //   137: getfield 38	okhttp3/internal/http2/Http2Stream$FramingSink:sendBuffer	Lokio/Buffer;
      //   140: invokevirtual 79	okio/Buffer:size	()J
      //   143: lcmp
      //   144: ifne +83 -> 227
      //   147: iconst_1
      //   148: istore_1
      //   149: goto +3 -> 152
      //   152: aload_0
      //   153: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   156: getfield 89	okhttp3/internal/http2/Http2Stream:connection	Lokhttp3/internal/http2/Http2Connection;
      //   159: aload_0
      //   160: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   163: getfield 93	okhttp3/internal/http2/Http2Stream:id	I
      //   166: iload_1
      //   167: aload_0
      //   168: getfield 38	okhttp3/internal/http2/Http2Stream$FramingSink:sendBuffer	Lokio/Buffer;
      //   171: lload_2
      //   172: invokevirtual 99	okhttp3/internal/http2/Http2Connection:writeData	(IZLokio/Buffer;J)V
      //   175: aload_0
      //   176: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   179: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   182: invokevirtual 72	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
      //   185: return
      //   186: aload_0
      //   187: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   190: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   193: invokevirtual 72	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
      //   196: aload 4
      //   198: athrow
      //   199: astore 5
      //   201: aload_0
      //   202: getfield 31	okhttp3/internal/http2/Http2Stream$FramingSink:this$0	Lokhttp3/internal/http2/Http2Stream;
      //   205: getfield 50	okhttp3/internal/http2/Http2Stream:writeTimeout	Lokhttp3/internal/http2/Http2Stream$StreamTimeout;
      //   208: invokevirtual 72	okhttp3/internal/http2/Http2Stream$StreamTimeout:exitAndThrowIfTimedOut	()V
      //   211: aload 5
      //   213: athrow
      //   214: astore 5
      //   216: aload 4
      //   218: monitorexit
      //   219: aload 5
      //   221: athrow
      //   222: astore 4
      //   224: goto -38 -> 186
      //   227: iconst_0
      //   228: istore_1
      //   229: goto -77 -> 152
      //
      // Exception table:
      //   from	to	target	type
      //   19	62	199	finally
      //   9	19	214	finally
      //   65	121	214	finally
      //   201	214	214	finally
      //   216	219	214	finally
      //   135	147	222	finally
      //   152	175	222	finally
    }

    public void close()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        if (this.closed)
          return;
        if (!Http2Stream.this.sink.finished)
        {
          int i;
          if (this.sendBuffer.size() > 0L)
            i = 1;
          else
            i = 0;
          int j;
          if (this.trailers != null)
            j = 1;
          else
            j = 0;
          if (j != 0)
          {
            while (this.sendBuffer.size() > 0L)
              emitFrame(false);
            Http2Stream.this.connection.writeHeaders(Http2Stream.this.id, true, Util.toHeaderBlock(this.trailers));
          }
          else
          {
            if (i != 0)
              while (this.sendBuffer.size() > 0L)
                emitFrame(true);
            Http2Stream.this.connection.writeData(Http2Stream.this.id, true, null, 0L);
          }
        }
        synchronized (Http2Stream.this)
        {
          this.closed = true;
          Http2Stream.this.connection.flush();
          Http2Stream.this.cancelStreamIfNecessary();
          return;
        }
      }
    }

    public void flush()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        Http2Stream.this.checkOutNotClosed();
        while (this.sendBuffer.size() > 0L)
        {
          emitFrame(false);
          Http2Stream.this.connection.flush();
        }
        return;
      }
    }

    public Timeout timeout()
    {
      return Http2Stream.this.writeTimeout;
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      this.sendBuffer.write(paramBuffer, paramLong);
      while (this.sendBuffer.size() >= 16384L)
        emitFrame(false);
    }
  }

  private final class FramingSource
    implements Source
  {
    boolean closed;
    boolean finished;
    private final long maxByteCount;
    private final Buffer readBuffer = new Buffer();
    private final Buffer receiveBuffer = new Buffer();
    private Headers trailers;

    FramingSource(long arg2)
    {
      Object localObject;
      this.maxByteCount = localObject;
    }

    private void updateConnectionFlowControl(long paramLong)
    {
      Http2Stream.this.connection.updateConnectionFlowControl(paramLong);
    }

    public void close()
      throws IOException
    {
      synchronized (Http2Stream.this)
      {
        this.closed = true;
        long l = this.readBuffer.size();
        this.readBuffer.clear();
        Http2Stream.this.notifyAll();
        if (l > 0L)
          updateConnectionFlowControl(l);
        Http2Stream.this.cancelStreamIfNecessary();
        return;
      }
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
      Object localObject = null;
      while (true)
      {
        synchronized (Http2Stream.this)
        {
          while (true)
          {
            Http2Stream.this.readTimeout.enter();
            try
            {
              if (Http2Stream.this.errorCode != null)
                if (Http2Stream.this.errorException != null)
                  localObject = Http2Stream.this.errorException;
                else
                  localObject = new StreamResetException(Http2Stream.this.errorCode);
              if (this.closed)
                throw new IOException("stream closed");
              if (this.readBuffer.size() > 0L)
              {
                long l = this.readBuffer.read(paramBuffer, Math.min(paramLong, this.readBuffer.size()));
                paramBuffer = Http2Stream.this;
                paramBuffer.unacknowledgedBytesRead += l;
                paramLong = l;
                if (localObject == null)
                {
                  paramLong = l;
                  if (Http2Stream.this.unacknowledgedBytesRead >= Http2Stream.this.connection.okHttpSettings.getInitialWindowSize() / 2)
                  {
                    Http2Stream.this.connection.writeWindowUpdateLater(Http2Stream.this.id, Http2Stream.this.unacknowledgedBytesRead);
                    Http2Stream.this.unacknowledgedBytesRead = 0L;
                    paramLong = l;
                  }
                }
              }
              else
              {
                if ((this.finished) || (localObject != null))
                  break label345;
                Http2Stream.this.waitForIo();
                Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                break;
              }
              Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
              if (paramLong != -1L)
              {
                updateConnectionFlowControl(paramLong);
                return paramLong;
              }
              if (localObject != null)
                throw ((Throwable)localObject);
              return -1L;
            }
            finally
            {
              Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
            }
          }
        }
        label345: paramLong = -1L;
      }
    }

    void receive(BufferedSource paramBufferedSource, long paramLong)
      throws IOException
    {
      if (paramLong > 0L);
      while (true)
      {
        synchronized (Http2Stream.this)
        {
          while (true)
          {
            boolean bool = this.finished;
            long l1 = this.readBuffer.size();
            long l2 = this.maxByteCount;
            int j = 0;
            if (l1 + paramLong <= l2)
              break label200;
            i = 1;
            if (i != 0)
            {
              paramBufferedSource.skip(paramLong);
              Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
              return;
            }
            if (bool)
            {
              paramBufferedSource.skip(paramLong);
              return;
            }
            l1 = paramBufferedSource.read(this.receiveBuffer, paramLong);
            if (l1 == -1L)
              throw new EOFException();
            paramLong -= l1;
            ??? = Http2Stream.this;
            i = j;
            try
            {
              if (this.readBuffer.size() == 0L)
                i = 1;
              this.readBuffer.writeAll(this.receiveBuffer);
              if (i != 0)
                Http2Stream.this.notifyAll();
              break;
            }
            finally
            {
            }
          }
        }
        return;
        label200: int i = 0;
      }
    }

    public Timeout timeout()
    {
      return Http2Stream.this.readTimeout;
    }
  }

  class StreamTimeout extends AsyncTimeout
  {
    StreamTimeout()
    {
    }

    public void exitAndThrowIfTimedOut()
      throws IOException
    {
      if (exit())
        throw newTimeoutException(null);
    }

    protected IOException newTimeoutException(IOException paramIOException)
    {
      SocketTimeoutException localSocketTimeoutException = new SocketTimeoutException("timeout");
      if (paramIOException != null)
        localSocketTimeoutException.initCause(paramIOException);
      return localSocketTimeoutException;
    }

    protected void timedOut()
    {
      Http2Stream.this.closeLater(ErrorCode.CANCEL);
    }
  }
}