package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public final class Http2Connection
  implements Closeable
{
  static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
  private static final ExecutorService listenerExecutor = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
  private boolean awaitingPong;
  long bytesLeftInWriteWindow;
  final boolean client;
  final String connectionName;
  final Set<Integer> currentPushRequests = new LinkedHashSet();
  int lastGoodStreamId;
  final Listener listener;
  int nextStreamId;
  Settings okHttpSettings = new Settings();
  final Settings peerSettings = new Settings();
  private final ExecutorService pushExecutor;
  final PushObserver pushObserver;
  final ReaderRunnable readerRunnable;
  boolean receivedInitialPeerSettings = false;
  boolean shutdown;
  final Socket socket;
  final Map<Integer, Http2Stream> streams = new LinkedHashMap();
  long unacknowledgedBytesRead = 0L;
  final Http2Writer writer;
  private final ScheduledExecutorService writerExecutor;

  Http2Connection(Builder paramBuilder)
  {
    this.pushObserver = paramBuilder.pushObserver;
    this.client = paramBuilder.client;
    this.listener = paramBuilder.listener;
    int i;
    if (paramBuilder.client)
      i = 1;
    else
      i = 2;
    this.nextStreamId = i;
    if (paramBuilder.client)
      this.nextStreamId += 2;
    if (paramBuilder.client)
      this.okHttpSettings.set(7, 16777216);
    this.connectionName = paramBuilder.connectionName;
    this.writerExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(Util.format("OkHttp %s Writer", new Object[] { this.connectionName }), false));
    if (paramBuilder.pingIntervalMillis != 0)
      this.writerExecutor.scheduleAtFixedRate(new PingRunnable(false, 0, 0), paramBuilder.pingIntervalMillis, paramBuilder.pingIntervalMillis, TimeUnit.MILLISECONDS);
    this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", new Object[] { this.connectionName }), true));
    this.peerSettings.set(7, 65535);
    this.peerSettings.set(5, 16384);
    this.bytesLeftInWriteWindow = this.peerSettings.getInitialWindowSize();
    this.socket = paramBuilder.socket;
    this.writer = new Http2Writer(paramBuilder.sink, this.client);
    this.readerRunnable = new ReaderRunnable(new Http2Reader(paramBuilder.source, this.client));
  }

  private void failConnection(@Nullable IOException paramIOException)
  {
    close(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, paramIOException);
  }

  private Http2Stream newStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramBoolean ^ true;
    while (true)
    {
      synchronized (this.writer)
      {
        try
        {
          if (this.nextStreamId > 1073741823)
            shutdown(ErrorCode.REFUSED_STREAM);
          if (this.shutdown)
            throw new ConnectionShutdownException();
          int j = this.nextStreamId;
          this.nextStreamId += 2;
          Http2Stream localHttp2Stream = new Http2Stream(j, this, bool, false, null);
          if ((!paramBoolean) || (this.bytesLeftInWriteWindow == 0L))
            break label215;
          if (localHttp2Stream.bytesLeftInWriteWindow == 0L)
          {
            break label215;
            if (localHttp2Stream.isOpen())
              this.streams.put(Integer.valueOf(j), localHttp2Stream);
            if (paramInt == 0)
            {
              this.writer.headers(bool, j, paramList);
            }
            else
            {
              if (this.client)
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
              this.writer.pushPromise(paramInt, j, paramList);
            }
            if (i != 0)
              this.writer.flush();
            return localHttp2Stream;
          }
        }
        finally
        {
        }
      }
      int i = 0;
      continue;
      label215: i = 1;
    }
  }

  private void pushExecutorExecute(NamedRunnable paramNamedRunnable)
  {
    try
    {
      if (!isShutdown())
        this.pushExecutor.execute(paramNamedRunnable);
      return;
    }
    finally
    {
      paramNamedRunnable = finally;
    }
    throw paramNamedRunnable;
  }

  void awaitPong()
    throws InterruptedException
  {
    try
    {
      while (this.awaitingPong)
        wait();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void close()
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
  }

  // ERROR //
  void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2, @Nullable IOException paramIOException)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 259	okhttp3/internal/http2/Http2Connection:shutdown	(Lokhttp3/internal/http2/ErrorCode;)V
    //   5: aconst_null
    //   6: astore_1
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 118	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   13: invokeinterface 333 1 0
    //   18: ifne +42 -> 60
    //   21: aload_0
    //   22: getfield 118	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   25: invokeinterface 337 1 0
    //   30: aload_0
    //   31: getfield 118	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   34: invokeinterface 340 1 0
    //   39: anewarray 266	okhttp3/internal/http2/Http2Stream
    //   42: invokeinterface 346 2 0
    //   47: checkcast 348	[Lokhttp3/internal/http2/Http2Stream;
    //   50: astore_1
    //   51: aload_0
    //   52: getfield 118	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   55: invokeinterface 351 1 0
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: ifnull +39 -> 102
    //   66: aload_1
    //   67: arraylength
    //   68: istore 5
    //   70: iconst_0
    //   71: istore 4
    //   73: iload 4
    //   75: iload 5
    //   77: if_icmpge +25 -> 102
    //   80: aload_1
    //   81: iload 4
    //   83: aaload
    //   84: astore 6
    //   86: aload 6
    //   88: aload_2
    //   89: aload_3
    //   90: invokevirtual 354	okhttp3/internal/http2/Http2Stream:close	(Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V
    //   93: iload 4
    //   95: iconst_1
    //   96: iadd
    //   97: istore 4
    //   99: goto -26 -> 73
    //   102: aload_0
    //   103: getfield 208	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   106: invokevirtual 356	okhttp3/internal/http2/Http2Writer:close	()V
    //   109: aload_0
    //   110: getfield 197	okhttp3/internal/http2/Http2Connection:socket	Ljava/net/Socket;
    //   113: invokevirtual 359	java/net/Socket:close	()V
    //   116: aload_0
    //   117: getfield 165	okhttp3/internal/http2/Http2Connection:writerExecutor	Ljava/util/concurrent/ScheduledExecutorService;
    //   120: invokeinterface 361 1 0
    //   125: aload_0
    //   126: getfield 187	okhttp3/internal/http2/Http2Connection:pushExecutor	Ljava/util/concurrent/ExecutorService;
    //   129: invokeinterface 362 1 0
    //   134: return
    //   135: astore_1
    //   136: aload_0
    //   137: monitorexit
    //   138: aload_1
    //   139: athrow
    //   140: astore_1
    //   141: goto -136 -> 5
    //   144: astore 6
    //   146: goto -53 -> 93
    //   149: astore_1
    //   150: goto -41 -> 109
    //   153: astore_1
    //   154: goto -38 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   9	60	135	finally
    //   60	62	135	finally
    //   136	138	135	finally
    //   0	5	140	java/io/IOException
    //   86	93	144	java/io/IOException
    //   102	109	149	java/io/IOException
    //   109	116	153	java/io/IOException
  }

  public void flush()
    throws IOException
  {
    this.writer.flush();
  }

  Http2Stream getStream(int paramInt)
  {
    try
    {
      Http2Stream localHttp2Stream = (Http2Stream)this.streams.get(Integer.valueOf(paramInt));
      return localHttp2Stream;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isShutdown()
  {
    try
    {
      boolean bool = this.shutdown;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int maxConcurrentStreams()
  {
    try
    {
      int i = this.peerSettings.getMaxConcurrentStreams(2147483647);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Http2Stream newStream(List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    return newStream(0, paramList, paramBoolean);
  }

  public int openStreamCount()
  {
    try
    {
      int i = this.streams.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void pushDataLater(final int paramInt1, BufferedSource paramBufferedSource, final int paramInt2, final boolean paramBoolean)
    throws IOException
  {
    final Buffer localBuffer = new Buffer();
    long l = paramInt2;
    paramBufferedSource.require(l);
    paramBufferedSource.read(localBuffer, l);
    if (localBuffer.size() != l)
    {
      paramBufferedSource = new StringBuilder();
      paramBufferedSource.append(localBuffer.size());
      paramBufferedSource.append(" != ");
      paramBufferedSource.append(paramInt2);
      throw new IOException(paramBufferedSource.toString());
    }
    pushExecutorExecute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[] { this.connectionName, Integer.valueOf(paramInt1) })
    {
      public void execute()
      {
        try
        {
          boolean bool = Http2Connection.this.pushObserver.onData(paramInt1, localBuffer, paramInt2, paramBoolean);
          if (bool)
            Http2Connection.this.writer.rstStream(paramInt1, ErrorCode.CANCEL);
          if ((bool) || (paramBoolean))
            synchronized (Http2Connection.this)
            {
              Http2Connection.this.currentPushRequests.remove(Integer.valueOf(paramInt1));
              return;
            }
          return;
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  void pushHeadersLater(final int paramInt, final List<Header> paramList, final boolean paramBoolean)
  {
    try
    {
      pushExecutorExecute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[] { this.connectionName, Integer.valueOf(paramInt) })
      {
        public void execute()
        {
          boolean bool = Http2Connection.this.pushObserver.onHeaders(paramInt, paramList, paramBoolean);
          if (bool);
          try
          {
            Http2Connection.this.writer.rstStream(paramInt, ErrorCode.CANCEL);
            if ((bool) || (paramBoolean))
              synchronized (Http2Connection.this)
              {
                Http2Connection.this.currentPushRequests.remove(Integer.valueOf(paramInt));
                return;
              }
            return;
          }
          catch (IOException localIOException)
          {
          }
        }
      });
      return;
    }
    catch (RejectedExecutionException paramList)
    {
    }
  }

  // ERROR //
  void pushRequestLater(final int paramInt, final List<Header> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 134	okhttp3/internal/http2/Http2Connection:currentPushRequests	Ljava/util/Set;
    //   6: iload_1
    //   7: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   10: invokeinterface 441 2 0
    //   15: ifeq +14 -> 29
    //   18: aload_0
    //   19: iload_1
    //   20: getstatic 243	okhttp3/internal/http2/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/http2/ErrorCode;
    //   23: invokevirtual 445	okhttp3/internal/http2/Http2Connection:writeSynResetLater	(ILokhttp3/internal/http2/ErrorCode;)V
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: aload_0
    //   30: getfield 134	okhttp3/internal/http2/Http2Connection:currentPushRequests	Ljava/util/Set;
    //   33: iload_1
    //   34: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   37: invokeinterface 448 2 0
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_0
    //   46: new 12	okhttp3/internal/http2/Http2Connection$3
    //   49: dup
    //   50: aload_0
    //   51: ldc_w 450
    //   54: iconst_2
    //   55: anewarray 4	java/lang/Object
    //   58: dup
    //   59: iconst_0
    //   60: aload_0
    //   61: getfield 152	okhttp3/internal/http2/Http2Connection:connectionName	Ljava/lang/String;
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: iload_1
    //   68: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   71: aastore
    //   72: iload_1
    //   73: aload_2
    //   74: invokespecial 453	okhttp3/internal/http2/Http2Connection$3:<init>	(Lokhttp3/internal/http2/Http2Connection;Ljava/lang/String;[Ljava/lang/Object;ILjava/util/List;)V
    //   77: invokespecial 423	okhttp3/internal/http2/Http2Connection:pushExecutorExecute	(Lokhttp3/internal/NamedRunnable;)V
    //   80: return
    //   81: astore_2
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    //   86: astore_2
    //   87: return
    //
    // Exception table:
    //   from	to	target	type
    //   2	28	81	finally
    //   29	45	81	finally
    //   82	84	81	finally
    //   45	80	86	java/util/concurrent/RejectedExecutionException
  }

  void pushResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    pushExecutorExecute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[] { this.connectionName, Integer.valueOf(paramInt) })
    {
      public void execute()
      {
        Http2Connection.this.pushObserver.onReset(paramInt, paramErrorCode);
        synchronized (Http2Connection.this)
        {
          Http2Connection.this.currentPushRequests.remove(Integer.valueOf(paramInt));
          return;
        }
      }
    });
  }

  public Http2Stream pushStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    if (this.client)
      throw new IllegalStateException("Client cannot push requests.");
    return newStream(paramInt, paramList, paramBoolean);
  }

  boolean pushedStream(int paramInt)
  {
    return (paramInt != 0) && ((paramInt & 0x1) == 0);
  }

  Http2Stream removeStream(int paramInt)
  {
    try
    {
      Http2Stream localHttp2Stream = (Http2Stream)this.streams.remove(Integer.valueOf(paramInt));
      notifyAll();
      return localHttp2Stream;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setSettings(Settings paramSettings)
    throws IOException
  {
    synchronized (this.writer)
    {
      try
      {
        if (this.shutdown)
          throw new ConnectionShutdownException();
        this.okHttpSettings.merge(paramSettings);
        this.writer.settings(paramSettings);
        return;
      }
      finally
      {
      }
    }
  }

  public void shutdown(ErrorCode paramErrorCode)
    throws IOException
  {
    synchronized (this.writer)
    {
      try
      {
        if (this.shutdown)
          return;
        this.shutdown = true;
        int i = this.lastGoodStreamId;
        this.writer.goAway(i, paramErrorCode, Util.EMPTY_BYTE_ARRAY);
        return;
      }
      finally
      {
      }
    }
  }

  public void start()
    throws IOException
  {
    start(true);
  }

  void start(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      this.writer.connectionPreface();
      this.writer.settings(this.okHttpSettings);
      int i = this.okHttpSettings.getInitialWindowSize();
      if (i != 65535)
        this.writer.windowUpdate(0, i - 65535);
    }
    new Thread(this.readerRunnable).start();
  }

  void updateConnectionFlowControl(long paramLong)
  {
    try
    {
      this.unacknowledgedBytesRead += paramLong;
      if (this.unacknowledgedBytesRead >= this.okHttpSettings.getInitialWindowSize() / 2)
      {
        writeWindowUpdateLater(0, this.unacknowledgedBytesRead);
        this.unacknowledgedBytesRead = 0L;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void writeData(int paramInt, boolean paramBoolean, Buffer paramBuffer, long paramLong)
    throws IOException
  {
    // Byte code:
    //   0: lload 4
    //   2: lstore 8
    //   4: lload 4
    //   6: lconst_0
    //   7: lcmp
    //   8: ifne +15 -> 23
    //   11: aload_0
    //   12: getfield 208	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   15: iload_2
    //   16: iload_1
    //   17: aload_3
    //   18: iconst_0
    //   19: invokevirtual 520	okhttp3/internal/http2/Http2Writer:data	(ZILokio/Buffer;I)V
    //   22: return
    //   23: lload 8
    //   25: lconst_0
    //   26: lcmp
    //   27: ifle +161 -> 188
    //   30: aload_0
    //   31: monitorenter
    //   32: aload_0
    //   33: getfield 194	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   36: lconst_0
    //   37: lcmp
    //   38: ifgt +37 -> 75
    //   41: aload_0
    //   42: getfield 118	okhttp3/internal/http2/Http2Connection:streams	Ljava/util/Map;
    //   45: iload_1
    //   46: invokestatic 280	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   49: invokeinterface 523 2 0
    //   54: ifne +14 -> 68
    //   57: new 252	java/io/IOException
    //   60: dup
    //   61: ldc_w 525
    //   64: invokespecial 416	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   67: athrow
    //   68: aload_0
    //   69: invokevirtual 324	java/lang/Object:wait	()V
    //   72: goto -40 -> 32
    //   75: lload 8
    //   77: aload_0
    //   78: getfield 194	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   81: invokestatic 531	java/lang/Math:min	(JJ)J
    //   84: l2i
    //   85: aload_0
    //   86: getfield 208	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   89: invokevirtual 534	okhttp3/internal/http2/Http2Writer:maxDataLength	()I
    //   92: invokestatic 537	java/lang/Math:min	(II)I
    //   95: istore 6
    //   97: aload_0
    //   98: getfield 194	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   101: lstore 4
    //   103: iload 6
    //   105: i2l
    //   106: lstore 10
    //   108: aload_0
    //   109: lload 4
    //   111: lload 10
    //   113: lsub
    //   114: putfield 194	okhttp3/internal/http2/Http2Connection:bytesLeftInWriteWindow	J
    //   117: aload_0
    //   118: monitorexit
    //   119: lload 8
    //   121: lload 10
    //   123: lsub
    //   124: lstore 8
    //   126: aload_0
    //   127: getfield 208	okhttp3/internal/http2/Http2Connection:writer	Lokhttp3/internal/http2/Http2Writer;
    //   130: astore 12
    //   132: iload_2
    //   133: ifeq +16 -> 149
    //   136: lload 8
    //   138: lconst_0
    //   139: lcmp
    //   140: ifne +9 -> 149
    //   143: iconst_1
    //   144: istore 7
    //   146: goto +6 -> 152
    //   149: iconst_0
    //   150: istore 7
    //   152: aload 12
    //   154: iload 7
    //   156: iload_1
    //   157: aload_3
    //   158: iload 6
    //   160: invokevirtual 520	okhttp3/internal/http2/Http2Writer:data	(ZILokio/Buffer;I)V
    //   163: goto -140 -> 23
    //   166: astore_3
    //   167: goto +17 -> 184
    //   170: invokestatic 541	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   173: invokevirtual 544	java/lang/Thread:interrupt	()V
    //   176: new 546	java/io/InterruptedIOException
    //   179: dup
    //   180: invokespecial 547	java/io/InterruptedIOException:<init>	()V
    //   183: athrow
    //   184: aload_0
    //   185: monitorexit
    //   186: aload_3
    //   187: athrow
    //   188: return
    //   189: astore_3
    //   190: goto -20 -> 170
    //
    // Exception table:
    //   from	to	target	type
    //   32	68	166	finally
    //   68	72	166	finally
    //   75	103	166	finally
    //   108	119	166	finally
    //   170	184	166	finally
    //   184	186	166	finally
    //   32	68	189	java/lang/InterruptedException
    //   68	72	189	java/lang/InterruptedException
  }

  void writeHeaders(int paramInt, boolean paramBoolean, List<Header> paramList)
    throws IOException
  {
    this.writer.headers(paramBoolean, paramInt, paramList);
  }

  void writePing(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (!paramBoolean)
      try
      {
        boolean bool = this.awaitingPong;
        this.awaitingPong = true;
        if (bool)
        {
          failConnection(null);
          return;
        }
      }
      finally
      {
      }
    try
    {
      this.writer.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    catch (IOException localIOException)
    {
      failConnection(localIOException);
    }
  }

  void writePingAndAwaitPong()
    throws InterruptedException
  {
    writePing(false, 1330343787, -257978967);
    awaitPong();
  }

  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    this.writer.rstStream(paramInt, paramErrorCode);
  }

  void writeSynResetLater(final int paramInt, final ErrorCode paramErrorCode)
  {
    try
    {
      this.writerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { this.connectionName, Integer.valueOf(paramInt) })
      {
        public void execute()
        {
          try
          {
            Http2Connection.this.writeSynReset(paramInt, paramErrorCode);
            return;
          }
          catch (IOException localIOException)
          {
            Http2Connection.this.failConnection(localIOException);
          }
        }
      });
      return;
    }
    catch (RejectedExecutionException paramErrorCode)
    {
    }
  }

  void writeWindowUpdateLater(final int paramInt, final long paramLong)
  {
    try
    {
      this.writerExecutor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[] { this.connectionName, Integer.valueOf(paramInt) })
      {
        public void execute()
        {
          try
          {
            Http2Connection.this.writer.windowUpdate(paramInt, paramLong);
            return;
          }
          catch (IOException localIOException)
          {
            Http2Connection.this.failConnection(localIOException);
          }
        }
      });
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
    }
  }

  public static class Builder
  {
    boolean client;
    String connectionName;
    Http2Connection.Listener listener = Http2Connection.Listener.REFUSE_INCOMING_STREAMS;
    int pingIntervalMillis;
    PushObserver pushObserver = PushObserver.CANCEL;
    BufferedSink sink;
    Socket socket;
    BufferedSource source;

    public Builder(boolean paramBoolean)
    {
      this.client = paramBoolean;
    }

    public Http2Connection build()
    {
      return new Http2Connection(this);
    }

    public Builder listener(Http2Connection.Listener paramListener)
    {
      this.listener = paramListener;
      return this;
    }

    public Builder pingIntervalMillis(int paramInt)
    {
      this.pingIntervalMillis = paramInt;
      return this;
    }

    public Builder pushObserver(PushObserver paramPushObserver)
    {
      this.pushObserver = paramPushObserver;
      return this;
    }

    public Builder socket(Socket paramSocket)
      throws IOException
    {
      Object localObject = paramSocket.getRemoteSocketAddress();
      if ((localObject instanceof InetSocketAddress))
        localObject = ((InetSocketAddress)localObject).getHostName();
      else
        localObject = localObject.toString();
      return socket(paramSocket, (String)localObject, Okio.buffer(Okio.source(paramSocket)), Okio.buffer(Okio.sink(paramSocket)));
    }

    public Builder socket(Socket paramSocket, String paramString, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      this.socket = paramSocket;
      this.connectionName = paramString;
      this.source = paramBufferedSource;
      this.sink = paramBufferedSink;
      return this;
    }
  }

  public static abstract class Listener
  {
    public static final Listener REFUSE_INCOMING_STREAMS = new Listener()
    {
      public void onStream(Http2Stream paramAnonymousHttp2Stream)
        throws IOException
      {
        paramAnonymousHttp2Stream.close(ErrorCode.REFUSED_STREAM, null);
      }
    };

    public void onSettings(Http2Connection paramHttp2Connection)
    {
    }

    public abstract void onStream(Http2Stream paramHttp2Stream)
      throws IOException;
  }

  final class PingRunnable extends NamedRunnable
  {
    final int payload1;
    final int payload2;
    final boolean reply;

    PingRunnable(boolean paramInt1, int paramInt2, int arg4)
    {
      super(new Object[] { Http2Connection.this.connectionName, Integer.valueOf(paramInt2), Integer.valueOf(i) });
      this.reply = paramInt1;
      this.payload1 = paramInt2;
      this.payload2 = i;
    }

    public void execute()
    {
      Http2Connection.this.writePing(this.reply, this.payload1, this.payload2);
    }
  }

  class ReaderRunnable extends NamedRunnable
    implements Http2Reader.Handler
  {
    final Http2Reader reader;

    ReaderRunnable(Http2Reader arg2)
    {
      super(new Object[] { Http2Connection.this.connectionName });
      Object localObject;
      this.reader = localObject;
    }

    private void applyAndAckSettings(final Settings paramSettings)
    {
      try
      {
        Http2Connection.this.writerExecutor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[] { Http2Connection.this.connectionName })
        {
          public void execute()
          {
            try
            {
              Http2Connection.this.writer.applyAndAckSettings(paramSettings);
              return;
            }
            catch (IOException localIOException)
            {
              Http2Connection.this.failConnection(localIOException);
            }
          }
        });
        return;
      }
      catch (RejectedExecutionException paramSettings)
      {
      }
    }

    public void ackSettings()
    {
    }

    public void alternateService(int paramInt1, String paramString1, ByteString paramByteString, String paramString2, int paramInt2, long paramLong)
    {
    }

    public void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
      throws IOException
    {
      if (Http2Connection.this.pushedStream(paramInt1))
      {
        Http2Connection.this.pushDataLater(paramInt1, paramBufferedSource, paramInt2, paramBoolean);
        return;
      }
      Object localObject = Http2Connection.this.getStream(paramInt1);
      if (localObject == null)
      {
        Http2Connection.this.writeSynResetLater(paramInt1, ErrorCode.PROTOCOL_ERROR);
        localObject = Http2Connection.this;
        long l = paramInt2;
        ((Http2Connection)localObject).updateConnectionFlowControl(l);
        paramBufferedSource.skip(l);
        return;
      }
      ((Http2Stream)localObject).receiveData(paramBufferedSource, paramInt2);
      if (paramBoolean)
        ((Http2Stream)localObject).receiveHeaders(Util.EMPTY_HEADERS, true);
    }

    // ERROR //
    protected void execute()
    {
      // Byte code:
      //   0: getstatic 115	okhttp3/internal/http2/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   3: astore 4
      //   5: getstatic 115	okhttp3/internal/http2/ErrorCode:INTERNAL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   8: astore 7
      //   10: aconst_null
      //   11: astore 5
      //   13: aconst_null
      //   14: astore 6
      //   16: aconst_null
      //   17: astore_1
      //   18: aload 4
      //   20: astore_3
      //   21: aload_1
      //   22: astore_2
      //   23: aload_0
      //   24: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   27: aload_0
      //   28: invokevirtual 121	okhttp3/internal/http2/Http2Reader:readConnectionPreface	(Lokhttp3/internal/http2/Http2Reader$Handler;)V
      //   31: aload 4
      //   33: astore_3
      //   34: aload_1
      //   35: astore_2
      //   36: aload_0
      //   37: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   40: iconst_0
      //   41: aload_0
      //   42: invokevirtual 125	okhttp3/internal/http2/Http2Reader:nextFrame	(ZLokhttp3/internal/http2/Http2Reader$Handler;)Z
      //   45: ifeq +6 -> 51
      //   48: goto -17 -> 31
      //   51: aload 4
      //   53: astore_3
      //   54: aload_1
      //   55: astore_2
      //   56: getstatic 128	okhttp3/internal/http2/ErrorCode:NO_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   59: astore_1
      //   60: aload 6
      //   62: astore_2
      //   63: aload_1
      //   64: astore_3
      //   65: getstatic 131	okhttp3/internal/http2/ErrorCode:CANCEL	Lokhttp3/internal/http2/ErrorCode;
      //   68: astore 4
      //   70: aload 4
      //   72: astore_2
      //   73: aload 5
      //   75: astore_3
      //   76: aload_1
      //   77: astore 4
      //   79: goto +41 -> 120
      //   82: astore_2
      //   83: aload_1
      //   84: astore_3
      //   85: aload_2
      //   86: astore_1
      //   87: goto +11 -> 98
      //   90: astore_1
      //   91: goto +49 -> 140
      //   94: astore_1
      //   95: aload 4
      //   97: astore_3
      //   98: aload_1
      //   99: astore_2
      //   100: getstatic 82	okhttp3/internal/http2/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   103: astore 4
      //   105: aload_1
      //   106: astore_2
      //   107: aload 4
      //   109: astore_3
      //   110: getstatic 82	okhttp3/internal/http2/ErrorCode:PROTOCOL_ERROR	Lokhttp3/internal/http2/ErrorCode;
      //   113: astore 5
      //   115: aload_1
      //   116: astore_3
      //   117: aload 5
      //   119: astore_2
      //   120: aload_0
      //   121: getfield 23	okhttp3/internal/http2/Http2Connection$ReaderRunnable:this$0	Lokhttp3/internal/http2/Http2Connection;
      //   124: aload 4
      //   126: aload_2
      //   127: aload_3
      //   128: invokevirtual 135	okhttp3/internal/http2/Http2Connection:close	(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V
      //   131: aload_0
      //   132: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   135: invokestatic 139	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   138: return
      //   139: astore_1
      //   140: aload_0
      //   141: getfield 23	okhttp3/internal/http2/Http2Connection$ReaderRunnable:this$0	Lokhttp3/internal/http2/Http2Connection;
      //   144: aload_3
      //   145: aload 7
      //   147: aload_2
      //   148: invokevirtual 135	okhttp3/internal/http2/Http2Connection:close	(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V
      //   151: aload_0
      //   152: getfield 36	okhttp3/internal/http2/Http2Connection$ReaderRunnable:reader	Lokhttp3/internal/http2/Http2Reader;
      //   155: invokestatic 139	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   158: aload_1
      //   159: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   65	70	82	java/io/IOException
      //   23	31	90	finally
      //   36	48	90	finally
      //   56	60	90	finally
      //   100	105	90	finally
      //   23	31	94	java/io/IOException
      //   36	48	94	java/io/IOException
      //   56	60	94	java/io/IOException
      //   65	70	139	finally
      //   110	115	139	finally
    }

    public void goAway(int paramInt, ErrorCode arg2, ByteString paramByteString)
    {
      paramByteString.size();
      synchronized (Http2Connection.this)
      {
        paramByteString = (Http2Stream[])Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
        Http2Connection.this.shutdown = true;
        int j = paramByteString.length;
        int i = 0;
        while (i < j)
        {
          ??? = paramByteString[i];
          if ((???.getId() > paramInt) && (???.isLocallyInitiated()))
          {
            ???.receiveRstStream(ErrorCode.REFUSED_STREAM);
            Http2Connection.this.removeStream(???.getId());
          }
          i += 1;
        }
        return;
      }
    }

    public void headers(boolean paramBoolean, int paramInt1, int paramInt2, final List<Header> paramList)
    {
      if (Http2Connection.this.pushedStream(paramInt1))
      {
        Http2Connection.this.pushHeadersLater(paramInt1, paramList, paramBoolean);
        return;
      }
      synchronized (Http2Connection.this)
      {
        Http2Stream localHttp2Stream = Http2Connection.this.getStream(paramInt1);
        if (localHttp2Stream == null)
        {
          if (Http2Connection.this.shutdown)
            return;
          if (paramInt1 <= Http2Connection.this.lastGoodStreamId)
            return;
          if (paramInt1 % 2 == Http2Connection.this.nextStreamId % 2)
            return;
          paramList = Util.toHeaders(paramList);
          paramList = new Http2Stream(paramInt1, Http2Connection.this, false, paramBoolean, paramList);
          Http2Connection.this.lastGoodStreamId = paramInt1;
          Http2Connection.this.streams.put(Integer.valueOf(paramInt1), paramList);
          Http2Connection.listenerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { Http2Connection.this.connectionName, Integer.valueOf(paramInt1) })
          {
            public void execute()
            {
              try
              {
                Http2Connection.this.listener.onStream(paramList);
                return;
              }
              catch (IOException localIOException1)
              {
                Platform localPlatform = Platform.get();
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Http2Connection.Listener failure for ");
                localStringBuilder.append(Http2Connection.this.connectionName);
                localPlatform.log(4, localStringBuilder.toString(), localIOException1);
                try
                {
                  paramList.close(ErrorCode.PROTOCOL_ERROR, localIOException1);
                  return;
                }
                catch (IOException localIOException2)
                {
                }
              }
            }
          });
          return;
        }
        localHttp2Stream.receiveHeaders(Util.toHeaders(paramList), paramBoolean);
        return;
      }
    }

    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
        synchronized (Http2Connection.this)
        {
          Http2Connection.access$302(Http2Connection.this, false);
          Http2Connection.this.notifyAll();
          return;
        }
      try
      {
        Http2Connection.this.writerExecutor.execute(new Http2Connection.PingRunnable(Http2Connection.this, true, paramInt1, paramInt2));
        return;
      }
      catch (RejectedExecutionException localRejectedExecutionException)
      {
      }
    }

    public void priority(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
    }

    public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    {
      Http2Connection.this.pushRequestLater(paramInt2, paramList);
    }

    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      if (Http2Connection.this.pushedStream(paramInt))
      {
        Http2Connection.this.pushResetLater(paramInt, paramErrorCode);
        return;
      }
      Http2Stream localHttp2Stream = Http2Connection.this.removeStream(paramInt);
      if (localHttp2Stream != null)
        localHttp2Stream.receiveRstStream(paramErrorCode);
    }

    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      while (true)
      {
        synchronized (Http2Connection.this)
        {
          int i = Http2Connection.this.peerSettings.getInitialWindowSize();
          if (paramBoolean)
            Http2Connection.this.peerSettings.clear();
          Http2Connection.this.peerSettings.merge(paramSettings);
          applyAndAckSettings(paramSettings);
          int j = Http2Connection.this.peerSettings.getInitialWindowSize();
          paramSettings = null;
          if ((j != -1) && (j != i))
          {
            long l2 = j - i;
            if (!Http2Connection.this.receivedInitialPeerSettings)
              Http2Connection.this.receivedInitialPeerSettings = true;
            l1 = l2;
            if (!Http2Connection.this.streams.isEmpty())
            {
              paramSettings = (Http2Stream[])Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
              l1 = l2;
            }
            ExecutorService localExecutorService = Http2Connection.listenerExecutor;
            String str = Http2Connection.this.connectionName;
            i = 0;
            localExecutorService.execute(new NamedRunnable("OkHttp %s settings", new Object[] { str })
            {
              public void execute()
              {
                Http2Connection.this.listener.onSettings(Http2Connection.this);
              }
            });
            if ((paramSettings != null) && (l1 != 0L))
            {
              j = paramSettings.length;
              if (i < j)
                synchronized (paramSettings[i])
                {
                  ???.addBytesToWriteWindow(l1);
                  i += 1;
                }
            }
            return;
          }
        }
        long l1 = 0L;
      }
    }

    public void windowUpdate(int paramInt, long paramLong)
    {
      if (paramInt == 0)
        synchronized (Http2Connection.this)
        {
          Http2Connection localHttp2Connection = Http2Connection.this;
          localHttp2Connection.bytesLeftInWriteWindow += paramLong;
          Http2Connection.this.notifyAll();
          return;
        }
      ??? = Http2Connection.this.getStream(paramInt);
      if (??? != null)
        try
        {
          ((Http2Stream)???).addBytesToWriteWindow(paramLong);
          return;
        }
        finally
        {
        }
    }
  }
}