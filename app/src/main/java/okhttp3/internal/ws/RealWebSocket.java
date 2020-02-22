package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket
  implements WebSocket, WebSocketReader.FrameCallback
{
  private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
  private static final long MAX_QUEUE_SIZE = 16777216L;
  private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
  private boolean awaitingPong;
  private Call call;
  private ScheduledFuture<?> cancelFuture;
  private boolean enqueuedClose;
  private ScheduledExecutorService executor;
  private boolean failed;
  private final String key;
  final WebSocketListener listener;
  private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque();
  private final Request originalRequest;
  private final long pingIntervalMillis;
  private final ArrayDeque<ByteString> pongQueue = new ArrayDeque();
  private long queueSize;
  private final Random random;
  private WebSocketReader reader;
  private int receivedCloseCode = -1;
  private String receivedCloseReason;
  private int receivedPingCount;
  private int receivedPongCount;
  private int sentPingCount;
  private Streams streams;
  private WebSocketWriter writer;
  private final Runnable writerRunnable;

  public RealWebSocket(Request paramRequest, WebSocketListener paramWebSocketListener, Random paramRandom, long paramLong)
  {
    if (!"GET".equals(paramRequest.method()))
    {
      paramWebSocketListener = new StringBuilder();
      paramWebSocketListener.append("Request must be GET: ");
      paramWebSocketListener.append(paramRequest.method());
      throw new IllegalArgumentException(paramWebSocketListener.toString());
    }
    this.originalRequest = paramRequest;
    this.listener = paramWebSocketListener;
    this.random = paramRandom;
    this.pingIntervalMillis = paramLong;
    paramRequest = new byte[16];
    paramRandom.nextBytes(paramRequest);
    this.key = ByteString.of(paramRequest).base64();
    this.writerRunnable = new RealWebSocket..Lambda.0(this);
  }

  private void runWriter()
  {
    if (this.executor != null)
      this.executor.execute(this.writerRunnable);
  }

  private boolean send(ByteString paramByteString, int paramInt)
  {
    try
    {
      if ((!this.failed) && (!this.enqueuedClose))
      {
        if (this.queueSize + paramByteString.size() > 16777216L)
        {
          close(1001, null);
          return false;
        }
        this.queueSize += paramByteString.size();
        this.messageAndCloseQueue.add(new Message(paramInt, paramByteString));
        runWriter();
        return true;
      }
      return false;
    }
    finally
    {
    }
    throw paramByteString;
  }

  void awaitTermination(int paramInt, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    this.executor.awaitTermination(paramInt, paramTimeUnit);
  }

  public void cancel()
  {
    this.call.cancel();
  }

  void checkUpgradeSuccess(Response paramResponse, @Nullable Exchange paramExchange)
    throws IOException
  {
    if (paramResponse.code() != 101)
    {
      paramExchange = new StringBuilder();
      paramExchange.append("Expected HTTP 101 response but was '");
      paramExchange.append(paramResponse.code());
      paramExchange.append(" ");
      paramExchange.append(paramResponse.message());
      paramExchange.append("'");
      throw new ProtocolException(paramExchange.toString());
    }
    Object localObject = paramResponse.header("Connection");
    if (!"Upgrade".equalsIgnoreCase((String)localObject))
    {
      paramResponse = new StringBuilder();
      paramResponse.append("Expected 'Connection' header value 'Upgrade' but was '");
      paramResponse.append((String)localObject);
      paramResponse.append("'");
      throw new ProtocolException(paramResponse.toString());
    }
    localObject = paramResponse.header("Upgrade");
    if (!"websocket".equalsIgnoreCase((String)localObject))
    {
      paramResponse = new StringBuilder();
      paramResponse.append("Expected 'Upgrade' header value 'websocket' but was '");
      paramResponse.append((String)localObject);
      paramResponse.append("'");
      throw new ProtocolException(paramResponse.toString());
    }
    paramResponse = paramResponse.header("Sec-WebSocket-Accept");
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(this.key);
    ((StringBuilder)localObject).append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
    localObject = ByteString.encodeUtf8(((StringBuilder)localObject).toString()).sha1().base64();
    if (!((String)localObject).equals(paramResponse))
    {
      paramExchange = new StringBuilder();
      paramExchange.append("Expected 'Sec-WebSocket-Accept' header value '");
      paramExchange.append((String)localObject);
      paramExchange.append("' but was '");
      paramExchange.append(paramResponse);
      paramExchange.append("'");
      throw new ProtocolException(paramExchange.toString());
    }
    if (paramExchange == null)
      throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
  }

  public boolean close(int paramInt, String paramString)
  {
    return close(paramInt, paramString, 60000L);
  }

  boolean close(int paramInt, String paramString, long paramLong)
  {
    try
    {
      WebSocketProtocol.validateCloseCode(paramInt);
      Object localObject = null;
      if (paramString != null)
      {
        ByteString localByteString = ByteString.encodeUtf8(paramString);
        localObject = localByteString;
        if (localByteString.size() > 123L)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("reason.size() > 123: ");
          ((StringBuilder)localObject).append(paramString);
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      if ((!this.failed) && (!this.enqueuedClose))
      {
        this.enqueuedClose = true;
        this.messageAndCloseQueue.add(new Close(paramInt, (ByteString)localObject, paramLong));
        runWriter();
        return true;
      }
      return false;
    }
    finally
    {
    }
    throw paramString;
  }

  public void connect(OkHttpClient paramOkHttpClient)
  {
    paramOkHttpClient = paramOkHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
    final Request localRequest = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
    this.call = Internal.instance.newWebSocketCall(paramOkHttpClient, localRequest);
    this.call.enqueue(new Callback()
    {
      public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
      {
        RealWebSocket.this.failWebSocket(paramAnonymousIOException, null);
      }

      public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
      {
        Object localObject = Internal.instance.exchange(paramAnonymousResponse);
        try
        {
          RealWebSocket.this.checkUpgradeSuccess(paramAnonymousResponse, (Exchange)localObject);
          paramAnonymousCall = ((Exchange)localObject).newWebSocketStreams();
          try
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("OkHttp WebSocket ");
            ((StringBuilder)localObject).append(localRequest.url().redact());
            localObject = ((StringBuilder)localObject).toString();
            RealWebSocket.this.initReaderAndWriter((String)localObject, paramAnonymousCall);
            RealWebSocket.this.listener.onOpen(RealWebSocket.this, paramAnonymousResponse);
            RealWebSocket.this.loopReader();
            return;
          }
          catch (Exception paramAnonymousCall)
          {
            RealWebSocket.this.failWebSocket(paramAnonymousCall, null);
            return;
          }
        }
        catch (IOException paramAnonymousCall)
        {
          if (localObject != null)
            ((Exchange)localObject).webSocketUpgradeFailed();
          RealWebSocket.this.failWebSocket(paramAnonymousCall, paramAnonymousResponse);
          Util.closeQuietly(paramAnonymousResponse);
        }
      }
    });
  }

  public void failWebSocket(Exception paramException, @Nullable Response paramResponse)
  {
    try
    {
      if (this.failed)
        return;
      this.failed = true;
      Streams localStreams = this.streams;
      this.streams = null;
      if (this.cancelFuture != null)
        this.cancelFuture.cancel(false);
      if (this.executor != null)
        this.executor.shutdown();
      try
      {
        this.listener.onFailure(this, paramException, paramResponse);
        return;
      }
      finally
      {
        Util.closeQuietly(localStreams);
      }
    }
    finally
    {
    }
    throw paramException;
  }

  public void initReaderAndWriter(String paramString, Streams paramStreams)
    throws IOException
  {
    try
    {
      this.streams = paramStreams;
      this.writer = new WebSocketWriter(paramStreams.client, paramStreams.sink, this.random);
      this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(paramString, false));
      if (this.pingIntervalMillis != 0L)
        this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
      if (!this.messageAndCloseQueue.isEmpty())
        runWriter();
      this.reader = new WebSocketReader(paramStreams.client, paramStreams.source, this);
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void loopReader()
    throws IOException
  {
    while (this.receivedCloseCode == -1)
      this.reader.processNextFrame();
  }

  public void onReadClose(int paramInt, String paramString)
  {
    if (paramInt == -1)
      throw new IllegalArgumentException();
    while (true)
    {
      try
      {
        if (this.receivedCloseCode != -1)
          throw new IllegalStateException("already closed");
        this.receivedCloseCode = paramInt;
        this.receivedCloseReason = paramString;
        if ((this.enqueuedClose) && (this.messageAndCloseQueue.isEmpty()))
        {
          localStreams = this.streams;
          this.streams = null;
          if (this.cancelFuture != null)
            this.cancelFuture.cancel(false);
          this.executor.shutdown();
          try
          {
            this.listener.onClosing(this, paramInt, paramString);
            if (localStreams != null)
              this.listener.onClosed(this, paramInt, paramString);
            return;
          }
          finally
          {
            Util.closeQuietly(localStreams);
          }
        }
      }
      finally
      {
      }
      Streams localStreams = null;
    }
  }

  public void onReadMessage(String paramString)
    throws IOException
  {
    this.listener.onMessage(this, paramString);
  }

  public void onReadMessage(ByteString paramByteString)
    throws IOException
  {
    this.listener.onMessage(this, paramByteString);
  }

  public void onReadPing(ByteString paramByteString)
  {
    try
    {
      if ((!this.failed) && ((!this.enqueuedClose) || (!this.messageAndCloseQueue.isEmpty())))
      {
        this.pongQueue.add(paramByteString);
        runWriter();
        this.receivedPingCount += 1;
        return;
      }
      return;
    }
    finally
    {
    }
    throw paramByteString;
  }

  public void onReadPong(ByteString paramByteString)
  {
    try
    {
      this.receivedPongCount += 1;
      this.awaitingPong = false;
      return;
    }
    finally
    {
      paramByteString = finally;
    }
    throw paramByteString;
  }

  boolean pong(ByteString paramByteString)
  {
    try
    {
      if ((!this.failed) && ((!this.enqueuedClose) || (!this.messageAndCloseQueue.isEmpty())))
      {
        this.pongQueue.add(paramByteString);
        runWriter();
        return true;
      }
      return false;
    }
    finally
    {
    }
    throw paramByteString;
  }

  boolean processNextFrame()
    throws IOException
  {
    boolean bool = false;
    try
    {
      this.reader.processNextFrame();
      int i = this.receivedCloseCode;
      if (i == -1)
        bool = true;
      return bool;
    }
    catch (Exception localException)
    {
      failWebSocket(localException, null);
    }
    return false;
  }

  public long queueSize()
  {
    try
    {
      long l = this.queueSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  int receivedPingCount()
  {
    try
    {
      int i = this.receivedPingCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  int receivedPongCount()
  {
    try
    {
      int i = this.receivedPongCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Request request()
  {
    return this.originalRequest;
  }

  public boolean send(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("text == null");
    return send(ByteString.encodeUtf8(paramString), 1);
  }

  public boolean send(ByteString paramByteString)
  {
    if (paramByteString == null)
      throw new NullPointerException("bytes == null");
    return send(paramByteString, 2);
  }

  int sentPingCount()
  {
    try
    {
      int i = this.sentPingCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void tearDown()
    throws InterruptedException
  {
    if (this.cancelFuture != null)
      this.cancelFuture.cancel(false);
    this.executor.shutdown();
    this.executor.awaitTermination(10L, TimeUnit.SECONDS);
  }

  // ERROR //
  boolean writeOneFrame()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 183	okhttp3/internal/ws/RealWebSocket:failed	Z
    //   6: ifeq +7 -> 13
    //   9: aload_0
    //   10: monitorexit
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_0
    //   14: getfield 400	okhttp3/internal/ws/RealWebSocket:writer	Lokhttp3/internal/ws/WebSocketWriter;
    //   17: astore 5
    //   19: aload_0
    //   20: getfield 103	okhttp3/internal/ws/RealWebSocket:pongQueue	Ljava/util/ArrayDeque;
    //   23: invokevirtual 506	java/util/ArrayDeque:poll	()Ljava/lang/Object;
    //   26: checkcast 154	okio/ByteString
    //   29: astore 6
    //   31: aconst_null
    //   32: astore_3
    //   33: aload 6
    //   35: ifnonnull +263 -> 298
    //   38: aload_0
    //   39: getfield 105	okhttp3/internal/ws/RealWebSocket:messageAndCloseQueue	Ljava/util/ArrayDeque;
    //   42: invokevirtual 506	java/util/ArrayDeque:poll	()Ljava/lang/Object;
    //   45: astore_2
    //   46: aload_2
    //   47: instanceof 15
    //   50: ifeq +75 -> 125
    //   53: aload_0
    //   54: getfield 107	okhttp3/internal/ws/RealWebSocket:receivedCloseCode	I
    //   57: istore_1
    //   58: aload_0
    //   59: getfield 455	okhttp3/internal/ws/RealWebSocket:receivedCloseReason	Ljava/lang/String;
    //   62: astore 4
    //   64: iload_1
    //   65: iconst_m1
    //   66: if_icmpeq +25 -> 91
    //   69: aload_0
    //   70: getfield 362	okhttp3/internal/ws/RealWebSocket:streams	Lokhttp3/internal/ws/RealWebSocket$Streams;
    //   73: astore_3
    //   74: aload_0
    //   75: aconst_null
    //   76: putfield 362	okhttp3/internal/ws/RealWebSocket:streams	Lokhttp3/internal/ws/RealWebSocket$Streams;
    //   79: aload_0
    //   80: getfield 173	okhttp3/internal/ws/RealWebSocket:executor	Ljava/util/concurrent/ScheduledExecutorService;
    //   83: invokeinterface 372 1 0
    //   88: goto +45 -> 133
    //   91: aload_0
    //   92: aload_0
    //   93: getfield 173	okhttp3/internal/ws/RealWebSocket:executor	Ljava/util/concurrent/ScheduledExecutorService;
    //   96: new 12	okhttp3/internal/ws/RealWebSocket$CancelRunnable
    //   99: dup
    //   100: aload_0
    //   101: invokespecial 507	okhttp3/internal/ws/RealWebSocket$CancelRunnable:<init>	(Lokhttp3/internal/ws/RealWebSocket;)V
    //   104: aload_2
    //   105: checkcast 15	okhttp3/internal/ws/RealWebSocket$Close
    //   108: getfield 510	okhttp3/internal/ws/RealWebSocket$Close:cancelAfterCloseMillis	J
    //   111: getstatic 416	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   114: invokeinterface 514 5 0
    //   119: putfield 364	okhttp3/internal/ws/RealWebSocket:cancelFuture	Ljava/util/concurrent/ScheduledFuture;
    //   122: goto +11 -> 133
    //   125: aload_2
    //   126: ifnonnull +169 -> 295
    //   129: aload_0
    //   130: monitorexit
    //   131: iconst_0
    //   132: ireturn
    //   133: aload_0
    //   134: monitorexit
    //   135: aload 6
    //   137: ifnull +13 -> 150
    //   140: aload 5
    //   142: aload 6
    //   144: invokevirtual 517	okhttp3/internal/ws/WebSocketWriter:writePong	(Lokio/ByteString;)V
    //   147: goto +123 -> 270
    //   150: aload_2
    //   151: instanceof 18
    //   154: ifeq +76 -> 230
    //   157: aload_2
    //   158: checkcast 18	okhttp3/internal/ws/RealWebSocket$Message
    //   161: getfield 521	okhttp3/internal/ws/RealWebSocket$Message:data	Lokio/ByteString;
    //   164: astore 4
    //   166: aload 5
    //   168: aload_2
    //   169: checkcast 18	okhttp3/internal/ws/RealWebSocket$Message
    //   172: getfield 524	okhttp3/internal/ws/RealWebSocket$Message:formatOpcode	I
    //   175: aload 4
    //   177: invokevirtual 191	okio/ByteString:size	()I
    //   180: i2l
    //   181: invokevirtual 528	okhttp3/internal/ws/WebSocketWriter:newMessageSink	(IJ)Lokio/Sink;
    //   184: invokestatic 534	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   187: astore_2
    //   188: aload_2
    //   189: aload 4
    //   191: invokeinterface 540 2 0
    //   196: pop
    //   197: aload_2
    //   198: invokeinterface 542 1 0
    //   203: aload_0
    //   204: monitorenter
    //   205: aload_0
    //   206: aload_0
    //   207: getfield 187	okhttp3/internal/ws/RealWebSocket:queueSize	J
    //   210: aload 4
    //   212: invokevirtual 191	okio/ByteString:size	()I
    //   215: i2l
    //   216: lsub
    //   217: putfield 187	okhttp3/internal/ws/RealWebSocket:queueSize	J
    //   220: aload_0
    //   221: monitorexit
    //   222: goto +48 -> 270
    //   225: astore_2
    //   226: aload_0
    //   227: monitorexit
    //   228: aload_2
    //   229: athrow
    //   230: aload_2
    //   231: instanceof 15
    //   234: ifeq +42 -> 276
    //   237: aload_2
    //   238: checkcast 15	okhttp3/internal/ws/RealWebSocket$Close
    //   241: astore_2
    //   242: aload 5
    //   244: aload_2
    //   245: getfield 544	okhttp3/internal/ws/RealWebSocket$Close:code	I
    //   248: aload_2
    //   249: getfield 547	okhttp3/internal/ws/RealWebSocket$Close:reason	Lokio/ByteString;
    //   252: invokevirtual 550	okhttp3/internal/ws/WebSocketWriter:writeClose	(ILokio/ByteString;)V
    //   255: aload_3
    //   256: ifnull +14 -> 270
    //   259: aload_0
    //   260: getfield 142	okhttp3/internal/ws/RealWebSocket:listener	Lokhttp3/WebSocketListener;
    //   263: aload_0
    //   264: iload_1
    //   265: aload 4
    //   267: invokevirtual 462	okhttp3/WebSocketListener:onClosed	(Lokhttp3/WebSocket;ILjava/lang/String;)V
    //   270: aload_3
    //   271: invokestatic 384	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   274: iconst_1
    //   275: ireturn
    //   276: new 552	java/lang/AssertionError
    //   279: dup
    //   280: invokespecial 553	java/lang/AssertionError:<init>	()V
    //   283: athrow
    //   284: aload_3
    //   285: invokestatic 384	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   288: aload_2
    //   289: athrow
    //   290: astore_2
    //   291: aload_0
    //   292: monitorexit
    //   293: aload_2
    //   294: athrow
    //   295: goto +5 -> 300
    //   298: aconst_null
    //   299: astore_2
    //   300: aconst_null
    //   301: astore 4
    //   303: iconst_m1
    //   304: istore_1
    //   305: goto -172 -> 133
    //   308: astore_2
    //   309: goto -25 -> 284
    //
    // Exception table:
    //   from	to	target	type
    //   205	222	225	finally
    //   226	228	225	finally
    //   2	11	290	finally
    //   13	31	290	finally
    //   38	64	290	finally
    //   69	88	290	finally
    //   91	122	290	finally
    //   129	131	290	finally
    //   133	135	290	finally
    //   291	293	290	finally
    //   140	147	308	finally
    //   150	205	308	finally
    //   228	230	308	finally
    //   230	255	308	finally
    //   259	270	308	finally
    //   276	284	308	finally
  }

  void writePingFrame()
  {
    while (true)
    {
      try
      {
        if (this.failed)
          return;
        Object localObject1 = this.writer;
        if (this.awaitingPong)
        {
          i = this.sentPingCount;
          this.sentPingCount += 1;
          this.awaitingPong = true;
          if (i != -1)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("sent ping but didn't receive pong within ");
            ((StringBuilder)localObject1).append(this.pingIntervalMillis);
            ((StringBuilder)localObject1).append("ms (after ");
            ((StringBuilder)localObject1).append(i - 1);
            ((StringBuilder)localObject1).append(" successful ping/pongs)");
            failWebSocket(new SocketTimeoutException(((StringBuilder)localObject1).toString()), null);
            return;
          }
          try
          {
            ((WebSocketWriter)localObject1).writePing(ByteString.EMPTY);
            return;
          }
          catch (IOException localIOException)
          {
            failWebSocket(localIOException, null);
            return;
          }
        }
      }
      finally
      {
      }
      int i = -1;
    }
  }

  final class CancelRunnable
    implements Runnable
  {
    CancelRunnable()
    {
    }

    public void run()
    {
      RealWebSocket.this.cancel();
    }
  }

  static final class Close
  {
    final long cancelAfterCloseMillis;
    final int code;
    final ByteString reason;

    Close(int paramInt, ByteString paramByteString, long paramLong)
    {
      this.code = paramInt;
      this.reason = paramByteString;
      this.cancelAfterCloseMillis = paramLong;
    }
  }

  static final class Message
  {
    final ByteString data;
    final int formatOpcode;

    Message(int paramInt, ByteString paramByteString)
    {
      this.formatOpcode = paramInt;
      this.data = paramByteString;
    }
  }

  private final class PingRunnable
    implements Runnable
  {
    PingRunnable()
    {
    }

    public void run()
    {
      RealWebSocket.this.writePingFrame();
    }
  }

  public static abstract class Streams
    implements Closeable
  {
    public final boolean client;
    public final BufferedSink sink;
    public final BufferedSource source;

    public Streams(boolean paramBoolean, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      this.client = paramBoolean;
      this.source = paramBufferedSource;
      this.sink = paramBufferedSink;
    }
  }
}