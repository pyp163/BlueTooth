package okhttp3;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.connection.Transmitter;
import okio.Timeout;

final class RealCall
  implements Call
{
  final OkHttpClient client;
  private boolean executed;
  final boolean forWebSocket;
  final Request originalRequest;
  private Transmitter transmitter;

  private RealCall(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    this.client = paramOkHttpClient;
    this.originalRequest = paramRequest;
    this.forWebSocket = paramBoolean;
  }

  static RealCall newRealCall(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    paramRequest = new RealCall(paramOkHttpClient, paramRequest, paramBoolean);
    paramRequest.transmitter = new Transmitter(paramOkHttpClient, paramRequest);
    return paramRequest;
  }

  public void cancel()
  {
    this.transmitter.cancel();
  }

  public RealCall clone()
  {
    return newRealCall(this.client, this.originalRequest, this.forWebSocket);
  }

  public void enqueue(Callback paramCallback)
  {
    try
    {
      if (this.executed)
        throw new IllegalStateException("Already Executed");
      this.executed = true;
      this.transmitter.callStart();
      this.client.dispatcher().enqueue(new AsyncCall(paramCallback));
      return;
    }
    finally
    {
    }
    throw paramCallback;
  }

  public Response execute()
    throws IOException
  {
    try
    {
      if (this.executed)
        throw new IllegalStateException("Already Executed");
      this.executed = true;
      this.transmitter.timeoutEnter();
      this.transmitter.callStart();
      try
      {
        this.client.dispatcher().executed(this);
        Response localResponse = getResponseWithInterceptorChain();
        return localResponse;
      }
      finally
      {
        this.client.dispatcher().finished(this);
      }
    }
    finally
    {
    }
  }

  // ERROR //
  Response getResponseWithInterceptorChain()
    throws IOException
  {
    // Byte code:
    //   0: new 103	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 104	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   13: invokevirtual 108	okhttp3/OkHttpClient:interceptors	()Ljava/util/List;
    //   16: invokeinterface 114 2 0
    //   21: pop
    //   22: aload_2
    //   23: new 116	okhttp3/internal/http/RetryAndFollowUpInterceptor
    //   26: dup
    //   27: aload_0
    //   28: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   31: invokespecial 119	okhttp3/internal/http/RetryAndFollowUpInterceptor:<init>	(Lokhttp3/OkHttpClient;)V
    //   34: invokeinterface 123 2 0
    //   39: pop
    //   40: aload_2
    //   41: new 125	okhttp3/internal/http/BridgeInterceptor
    //   44: dup
    //   45: aload_0
    //   46: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   49: invokevirtual 129	okhttp3/OkHttpClient:cookieJar	()Lokhttp3/CookieJar;
    //   52: invokespecial 132	okhttp3/internal/http/BridgeInterceptor:<init>	(Lokhttp3/CookieJar;)V
    //   55: invokeinterface 123 2 0
    //   60: pop
    //   61: aload_2
    //   62: new 134	okhttp3/internal/cache/CacheInterceptor
    //   65: dup
    //   66: aload_0
    //   67: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   70: invokevirtual 138	okhttp3/OkHttpClient:internalCache	()Lokhttp3/internal/cache/InternalCache;
    //   73: invokespecial 141	okhttp3/internal/cache/CacheInterceptor:<init>	(Lokhttp3/internal/cache/InternalCache;)V
    //   76: invokeinterface 123 2 0
    //   81: pop
    //   82: aload_2
    //   83: new 143	okhttp3/internal/connection/ConnectInterceptor
    //   86: dup
    //   87: aload_0
    //   88: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   91: invokespecial 144	okhttp3/internal/connection/ConnectInterceptor:<init>	(Lokhttp3/OkHttpClient;)V
    //   94: invokeinterface 123 2 0
    //   99: pop
    //   100: aload_0
    //   101: getfield 29	okhttp3/RealCall:forWebSocket	Z
    //   104: ifne +17 -> 121
    //   107: aload_2
    //   108: aload_0
    //   109: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   112: invokevirtual 147	okhttp3/OkHttpClient:networkInterceptors	()Ljava/util/List;
    //   115: invokeinterface 114 2 0
    //   120: pop
    //   121: aload_2
    //   122: new 149	okhttp3/internal/http/CallServerInterceptor
    //   125: dup
    //   126: aload_0
    //   127: getfield 29	okhttp3/RealCall:forWebSocket	Z
    //   130: invokespecial 152	okhttp3/internal/http/CallServerInterceptor:<init>	(Z)V
    //   133: invokeinterface 123 2 0
    //   138: pop
    //   139: new 154	okhttp3/internal/http/RealInterceptorChain
    //   142: dup
    //   143: aload_2
    //   144: aload_0
    //   145: getfield 34	okhttp3/RealCall:transmitter	Lokhttp3/internal/connection/Transmitter;
    //   148: aconst_null
    //   149: iconst_0
    //   150: aload_0
    //   151: getfield 27	okhttp3/RealCall:originalRequest	Lokhttp3/Request;
    //   154: aload_0
    //   155: aload_0
    //   156: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   159: invokevirtual 158	okhttp3/OkHttpClient:connectTimeoutMillis	()I
    //   162: aload_0
    //   163: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   166: invokevirtual 161	okhttp3/OkHttpClient:readTimeoutMillis	()I
    //   169: aload_0
    //   170: getfield 25	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
    //   173: invokevirtual 164	okhttp3/OkHttpClient:writeTimeoutMillis	()I
    //   176: invokespecial 167	okhttp3/internal/http/RealInterceptorChain:<init>	(Ljava/util/List;Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/Exchange;ILokhttp3/Request;Lokhttp3/Call;III)V
    //   179: astore_2
    //   180: iconst_0
    //   181: istore_1
    //   182: aload_2
    //   183: aload_0
    //   184: getfield 27	okhttp3/RealCall:originalRequest	Lokhttp3/Request;
    //   187: invokeinterface 173 2 0
    //   192: astore_2
    //   193: aload_0
    //   194: getfield 34	okhttp3/RealCall:transmitter	Lokhttp3/internal/connection/Transmitter;
    //   197: invokevirtual 177	okhttp3/internal/connection/Transmitter:isCanceled	()Z
    //   200: ifeq +17 -> 217
    //   203: aload_2
    //   204: invokestatic 183	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   207: new 89	java/io/IOException
    //   210: dup
    //   211: ldc 185
    //   213: invokespecial 186	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   216: athrow
    //   217: aload_0
    //   218: getfield 34	okhttp3/RealCall:transmitter	Lokhttp3/internal/connection/Transmitter;
    //   221: aconst_null
    //   222: invokevirtual 190	okhttp3/internal/connection/Transmitter:noMoreExchanges	(Ljava/io/IOException;)Ljava/io/IOException;
    //   225: pop
    //   226: aload_2
    //   227: areturn
    //   228: astore_2
    //   229: goto +16 -> 245
    //   232: astore_2
    //   233: aload_0
    //   234: getfield 34	okhttp3/RealCall:transmitter	Lokhttp3/internal/connection/Transmitter;
    //   237: aload_2
    //   238: invokevirtual 190	okhttp3/internal/connection/Transmitter:noMoreExchanges	(Ljava/io/IOException;)Ljava/io/IOException;
    //   241: athrow
    //   242: astore_2
    //   243: iconst_1
    //   244: istore_1
    //   245: iload_1
    //   246: ifne +12 -> 258
    //   249: aload_0
    //   250: getfield 34	okhttp3/RealCall:transmitter	Lokhttp3/internal/connection/Transmitter;
    //   253: aconst_null
    //   254: invokevirtual 190	okhttp3/internal/connection/Transmitter:noMoreExchanges	(Ljava/io/IOException;)Ljava/io/IOException;
    //   257: pop
    //   258: aload_2
    //   259: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   182	217	228	finally
    //   182	217	232	java/io/IOException
    //   233	242	242	finally
  }

  public boolean isCanceled()
  {
    return this.transmitter.isCanceled();
  }

  public boolean isExecuted()
  {
    try
    {
      boolean bool = this.executed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  String redactedUrl()
  {
    return this.originalRequest.url().redact();
  }

  public Request request()
  {
    return this.originalRequest;
  }

  public Timeout timeout()
  {
    return this.transmitter.timeout();
  }

  String toLoggableString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (isCanceled())
      str = "canceled ";
    else
      str = "";
    localStringBuilder.append(str);
    if (this.forWebSocket)
      str = "web socket";
    else
      str = "call";
    localStringBuilder.append(str);
    localStringBuilder.append(" to ");
    localStringBuilder.append(redactedUrl());
    return localStringBuilder.toString();
  }

  final class AsyncCall extends NamedRunnable
  {
    private volatile AtomicInteger callsPerHost = new AtomicInteger(0);
    private final Callback responseCallback;

    AsyncCall(Callback arg2)
    {
      super(new Object[] { RealCall.this.redactedUrl() });
      Object localObject;
      this.responseCallback = localObject;
    }

    AtomicInteger callsPerHost()
    {
      return this.callsPerHost;
    }

    // ERROR //
    protected void execute()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   4: invokestatic 51	okhttp3/RealCall:access$000	(Lokhttp3/RealCall;)Lokhttp3/internal/connection/Transmitter;
      //   7: invokevirtual 56	okhttp3/internal/connection/Transmitter:timeoutEnter	()V
      //   10: iconst_0
      //   11: istore_1
      //   12: aload_0
      //   13: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   16: invokevirtual 60	okhttp3/RealCall:getResponseWithInterceptorChain	()Lokhttp3/Response;
      //   19: astore_2
      //   20: iconst_1
      //   21: istore_1
      //   22: aload_0
      //   23: getfield 43	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   26: aload_0
      //   27: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   30: aload_2
      //   31: invokeinterface 66 3 0
      //   36: aload_0
      //   37: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   40: getfield 70	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   43: invokevirtual 76	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   46: aload_0
      //   47: invokevirtual 82	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   50: return
      //   51: astore_2
      //   52: goto +73 -> 125
      //   55: astore_2
      //   56: iload_1
      //   57: ifeq +51 -> 108
      //   60: invokestatic 88	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
      //   63: astore_3
      //   64: new 90	java/lang/StringBuilder
      //   67: dup
      //   68: invokespecial 92	java/lang/StringBuilder:<init>	()V
      //   71: astore 4
      //   73: aload 4
      //   75: ldc 94
      //   77: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   80: pop
      //   81: aload 4
      //   83: aload_0
      //   84: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   87: invokevirtual 101	okhttp3/RealCall:toLoggableString	()Ljava/lang/String;
      //   90: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   93: pop
      //   94: aload_3
      //   95: iconst_4
      //   96: aload 4
      //   98: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   101: aload_2
      //   102: invokevirtual 108	okhttp3/internal/platform/Platform:log	(ILjava/lang/String;Ljava/lang/Throwable;)V
      //   105: goto -69 -> 36
      //   108: aload_0
      //   109: getfield 43	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   112: aload_0
      //   113: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   116: aload_2
      //   117: invokeinterface 112 3 0
      //   122: goto -86 -> 36
      //   125: aload_0
      //   126: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   129: getfield 70	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   132: invokevirtual 76	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   135: aload_0
      //   136: invokevirtual 82	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   139: aload_2
      //   140: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   12	20	51	finally
      //   22	36	51	finally
      //   60	105	51	finally
      //   108	122	51	finally
      //   12	20	55	java/io/IOException
      //   22	36	55	java/io/IOException
    }

    // ERROR //
    void executeOn(java.util.concurrent.ExecutorService paramExecutorService)
    {
      // Byte code:
      //   0: aload_1
      //   1: aload_0
      //   2: invokeinterface 121 2 0
      //   7: return
      //   8: astore_1
      //   9: goto +61 -> 70
      //   12: astore_1
      //   13: new 123	java/io/InterruptedIOException
      //   16: dup
      //   17: ldc 125
      //   19: invokespecial 128	java/io/InterruptedIOException:<init>	(Ljava/lang/String;)V
      //   22: astore_2
      //   23: aload_2
      //   24: aload_1
      //   25: invokevirtual 132	java/io/InterruptedIOException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
      //   28: pop
      //   29: aload_0
      //   30: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   33: invokestatic 51	okhttp3/RealCall:access$000	(Lokhttp3/RealCall;)Lokhttp3/internal/connection/Transmitter;
      //   36: aload_2
      //   37: invokevirtual 136	okhttp3/internal/connection/Transmitter:noMoreExchanges	(Ljava/io/IOException;)Ljava/io/IOException;
      //   40: pop
      //   41: aload_0
      //   42: getfield 43	okhttp3/RealCall$AsyncCall:responseCallback	Lokhttp3/Callback;
      //   45: aload_0
      //   46: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   49: aload_2
      //   50: invokeinterface 112 3 0
      //   55: aload_0
      //   56: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   59: getfield 70	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   62: invokevirtual 76	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   65: aload_0
      //   66: invokevirtual 82	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   69: return
      //   70: aload_0
      //   71: getfield 23	okhttp3/RealCall$AsyncCall:this$0	Lokhttp3/RealCall;
      //   74: getfield 70	okhttp3/RealCall:client	Lokhttp3/OkHttpClient;
      //   77: invokevirtual 76	okhttp3/OkHttpClient:dispatcher	()Lokhttp3/Dispatcher;
      //   80: aload_0
      //   81: invokevirtual 82	okhttp3/Dispatcher:finished	(Lokhttp3/RealCall$AsyncCall;)V
      //   84: aload_1
      //   85: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   0	7	8	finally
      //   13	55	8	finally
      //   0	7	12	java/util/concurrent/RejectedExecutionException
    }

    RealCall get()
    {
      return RealCall.this;
    }

    String host()
    {
      return RealCall.this.originalRequest.url().host();
    }

    Request request()
    {
      return RealCall.this.originalRequest;
    }

    void reuseCallsPerHostFrom(AsyncCall paramAsyncCall)
    {
      this.callsPerHost = paramAsyncCall.callsPerHost;
    }
  }
}