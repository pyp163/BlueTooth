package org.apache.commons.net.telnet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

final class TelnetInputStream extends BufferedInputStream
  implements Runnable
{
  private static final int EOF = -1;
  private static final int WOULD_BLOCK = -2;
  static final int _STATE_CR = 8;
  static final int _STATE_DATA = 0;
  static final int _STATE_DO = 4;
  static final int _STATE_DONT = 5;
  static final int _STATE_IAC = 1;
  static final int _STATE_IAC_SB = 9;
  static final int _STATE_SB = 6;
  static final int _STATE_SE = 7;
  static final int _STATE_WILL = 2;
  static final int _STATE_WONT = 3;
  private int __bytesAvailable;
  private final TelnetClient __client;
  private boolean __hasReachedEOF;
  private IOException __ioException;
  private volatile boolean __isClosed;
  private final int[] __queue;
  private int __queueHead;
  private int __queueTail;
  private boolean __readIsWaiting;
  private int __receiveState;
  private final int[] __suboption = new int[256];
  private int __suboption_count = 0;
  private final Thread __thread;
  private volatile boolean __threaded;

  TelnetInputStream(InputStream paramInputStream, TelnetClient paramTelnetClient)
  {
    this(paramInputStream, paramTelnetClient, true);
  }

  TelnetInputStream(InputStream paramInputStream, TelnetClient paramTelnetClient, boolean paramBoolean)
  {
    super(paramInputStream);
    this.__client = paramTelnetClient;
    this.__receiveState = 0;
    this.__isClosed = true;
    this.__hasReachedEOF = false;
    this.__queue = new int[2049];
    this.__queueHead = 0;
    this.__queueTail = 0;
    this.__bytesAvailable = 0;
    this.__ioException = null;
    this.__readIsWaiting = false;
    this.__threaded = false;
    if (paramBoolean)
    {
      this.__thread = new Thread(this);
      return;
    }
    this.__thread = null;
  }

  private boolean __processChar(int paramInt)
    throws InterruptedException
  {
    while (true)
    {
      synchronized (this.__queue)
      {
        if (this.__bytesAvailable == 0)
        {
          bool = true;
          if (this.__bytesAvailable >= this.__queue.length - 1)
          {
            if (this.__threaded)
            {
              this.__queue.notify();
              try
              {
                this.__queue.wait();
              }
              catch (InterruptedException localInterruptedException)
              {
                throw localInterruptedException;
              }
            }
            throw new IllegalStateException("Queue is full! Cannot process another character.");
          }
          if ((this.__readIsWaiting) && (this.__threaded))
            this.__queue.notify();
          this.__queue[this.__queueTail] = paramInt;
          this.__bytesAvailable += 1;
          paramInt = this.__queueTail + 1;
          this.__queueTail = paramInt;
          if (paramInt >= this.__queue.length)
            this.__queueTail = 0;
          return bool;
        }
      }
      boolean bool = false;
    }
  }

  private int __read(boolean paramBoolean)
    throws IOException
  {
    while (true)
    {
      if ((!paramBoolean) && (super.available() == 0))
        return -2;
      int i = super.read();
      if (i < 0)
        return -1;
      i &= 255;
      synchronized (this.__client)
      {
        this.__client._processAYTResponse();
        this.__client._spyRead(i);
        int j;
        switch (this.__receiveState)
        {
        case 7:
        default:
          return i;
        case 9:
          if (i != 240)
          {
            if ((i == 255) && (this.__suboption_count < this.__suboption.length))
            {
              ??? = this.__suboption;
              j = this.__suboption_count;
              this.__suboption_count = (j + 1);
              ???[j] = i;
            }
            this.__receiveState = 6;
            continue;
          }
          synchronized (this.__client)
          {
            this.__client._processSuboption(this.__suboption, this.__suboption_count);
            this.__client._flushOutputStream();
            this.__receiveState = 0;
          }
        case 8:
          if (i != 0);
          break;
        case 6:
          if (i != 255)
          {
            if (this.__suboption_count < this.__suboption.length)
            {
              ??? = this.__suboption;
              j = this.__suboption_count;
              this.__suboption_count = (j + 1);
              ???[j] = i;
            }
            this.__receiveState = 6;
            continue;
          }
          this.__receiveState = 9;
          break;
        case 5:
          synchronized (this.__client)
          {
            this.__client._processDont(i);
            this.__client._flushOutputStream();
            this.__receiveState = 0;
          }
        case 4:
          synchronized (this.__client)
          {
            this.__client._processDo(i);
            this.__client._flushOutputStream();
            this.__receiveState = 0;
          }
        case 3:
          synchronized (this.__client)
          {
            this.__client._processWont(i);
            this.__client._flushOutputStream();
            this.__receiveState = 0;
          }
        case 2:
          synchronized (this.__client)
          {
            this.__client._processWill(i);
            this.__client._flushOutputStream();
            this.__receiveState = 0;
          }
        case 1:
          if (i != 240)
          {
            switch (i)
            {
            default:
              this.__receiveState = 0;
              this.__client._processCommand(i);
              break;
            case 255:
              this.__receiveState = 0;
              return i;
            case 254:
              this.__receiveState = 5;
              break;
            case 253:
              this.__receiveState = 4;
              break;
            case 252:
              this.__receiveState = 3;
              break;
            case 251:
              this.__receiveState = 2;
              break;
            case 250:
            }
            this.__suboption_count = 0;
            this.__receiveState = 6;
            continue;
          }
          this.__receiveState = 0;
          break;
        case 0:
        }
        if (i == 255)
        {
          this.__receiveState = 1;
          continue;
        }
        if (i == 13)
          synchronized (this.__client)
          {
            if (this.__client._requestedDont(0))
              this.__receiveState = 8;
            else
              this.__receiveState = 0;
            return i;
          }
        this.__receiveState = 0;
        return i;
      }
    }
  }

  void _start()
  {
    if (this.__thread == null)
      return;
    this.__isClosed = false;
    int j = Thread.currentThread().getPriority() + 1;
    int i = j;
    if (j > 10)
      i = 10;
    this.__thread.setPriority(i);
    this.__thread.setDaemon(true);
    this.__thread.start();
  }

  public int available()
    throws IOException
  {
    synchronized (this.__queue)
    {
      int i = this.__bytesAvailable;
      return i;
    }
  }

  public void close()
    throws IOException
  {
    super.close();
    synchronized (this.__queue)
    {
      this.__hasReachedEOF = true;
      this.__isClosed = true;
      if ((this.__thread != null) && (this.__thread.isAlive()))
        this.__thread.interrupt();
      this.__queue.notifyAll();
      this.__threaded = false;
      return;
    }
  }

  public boolean markSupported()
  {
    return false;
  }

  // ERROR //
  public int read()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   4: astore 4
    //   6: aload 4
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   13: ifnull +17 -> 30
    //   16: aload_0
    //   17: getfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   20: astore 5
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   27: aload 5
    //   29: athrow
    //   30: aload_0
    //   31: getfield 79	org/apache/commons/net/telnet/TelnetInputStream:__bytesAvailable	I
    //   34: ifne +196 -> 230
    //   37: aload_0
    //   38: getfield 71	org/apache/commons/net/telnet/TelnetInputStream:__hasReachedEOF	Z
    //   41: ifeq +8 -> 49
    //   44: aload 4
    //   46: monitorexit
    //   47: iconst_m1
    //   48: ireturn
    //   49: aload_0
    //   50: getfield 85	org/apache/commons/net/telnet/TelnetInputStream:__threaded	Z
    //   53: ifeq +40 -> 93
    //   56: aload_0
    //   57: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   60: invokevirtual 102	java/lang/Object:notify	()V
    //   63: aload_0
    //   64: iconst_1
    //   65: putfield 83	org/apache/commons/net/telnet/TelnetInputStream:__readIsWaiting	Z
    //   68: aload_0
    //   69: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   72: invokevirtual 105	java/lang/Object:wait	()V
    //   75: aload_0
    //   76: iconst_0
    //   77: putfield 83	org/apache/commons/net/telnet/TelnetInputStream:__readIsWaiting	Z
    //   80: goto -71 -> 9
    //   83: new 192	java/io/InterruptedIOException
    //   86: dup
    //   87: ldc 194
    //   89: invokespecial 195	java/io/InterruptedIOException:<init>	(Ljava/lang/String;)V
    //   92: athrow
    //   93: aload_0
    //   94: iconst_1
    //   95: putfield 83	org/apache/commons/net/telnet/TelnetInputStream:__readIsWaiting	Z
    //   98: iconst_1
    //   99: istore_3
    //   100: aload_0
    //   101: iload_3
    //   102: invokespecial 197	org/apache/commons/net/telnet/TelnetInputStream:__read	(Z)I
    //   105: istore_1
    //   106: iload_1
    //   107: ifge +14 -> 121
    //   110: iload_1
    //   111: bipush 254
    //   113: if_icmpeq +8 -> 121
    //   116: aload 4
    //   118: monitorexit
    //   119: iload_1
    //   120: ireturn
    //   121: iload_1
    //   122: bipush 254
    //   124: if_icmpeq +24 -> 148
    //   127: aload_0
    //   128: iload_1
    //   129: invokespecial 199	org/apache/commons/net/telnet/TelnetInputStream:__processChar	(I)Z
    //   132: pop
    //   133: goto +15 -> 148
    //   136: aload_0
    //   137: getfield 69	org/apache/commons/net/telnet/TelnetInputStream:__isClosed	Z
    //   140: ifeq +8 -> 148
    //   143: aload 4
    //   145: monitorexit
    //   146: iconst_m1
    //   147: ireturn
    //   148: aload_0
    //   149: invokespecial 121	java/io/BufferedInputStream:available	()I
    //   152: ifle +20 -> 172
    //   155: aload_0
    //   156: getfield 79	org/apache/commons/net/telnet/TelnetInputStream:__bytesAvailable	I
    //   159: aload_0
    //   160: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   163: arraylength
    //   164: iconst_1
    //   165: isub
    //   166: if_icmplt +159 -> 325
    //   169: goto +3 -> 172
    //   172: aload_0
    //   173: iconst_0
    //   174: putfield 83	org/apache/commons/net/telnet/TelnetInputStream:__readIsWaiting	Z
    //   177: goto -168 -> 9
    //   180: astore 6
    //   182: aload_0
    //   183: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   186: astore 5
    //   188: aload 5
    //   190: monitorenter
    //   191: aload_0
    //   192: aload 6
    //   194: putfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   197: aload_0
    //   198: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   201: invokevirtual 189	java/lang/Object:notifyAll	()V
    //   204: aload_0
    //   205: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   208: ldc2_w 200
    //   211: invokevirtual 204	java/lang/Object:wait	(J)V
    //   214: aload 5
    //   216: monitorexit
    //   217: aload 4
    //   219: monitorexit
    //   220: iconst_m1
    //   221: ireturn
    //   222: astore 6
    //   224: aload 5
    //   226: monitorexit
    //   227: aload 6
    //   229: athrow
    //   230: aload_0
    //   231: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   234: aload_0
    //   235: getfield 75	org/apache/commons/net/telnet/TelnetInputStream:__queueHead	I
    //   238: iaload
    //   239: istore_1
    //   240: aload_0
    //   241: getfield 75	org/apache/commons/net/telnet/TelnetInputStream:__queueHead	I
    //   244: iconst_1
    //   245: iadd
    //   246: istore_2
    //   247: aload_0
    //   248: iload_2
    //   249: putfield 75	org/apache/commons/net/telnet/TelnetInputStream:__queueHead	I
    //   252: iload_2
    //   253: aload_0
    //   254: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   257: arraylength
    //   258: if_icmplt +8 -> 266
    //   261: aload_0
    //   262: iconst_0
    //   263: putfield 75	org/apache/commons/net/telnet/TelnetInputStream:__queueHead	I
    //   266: aload_0
    //   267: aload_0
    //   268: getfield 79	org/apache/commons/net/telnet/TelnetInputStream:__bytesAvailable	I
    //   271: iconst_1
    //   272: isub
    //   273: putfield 79	org/apache/commons/net/telnet/TelnetInputStream:__bytesAvailable	I
    //   276: aload_0
    //   277: getfield 79	org/apache/commons/net/telnet/TelnetInputStream:__bytesAvailable	I
    //   280: ifne +17 -> 297
    //   283: aload_0
    //   284: getfield 85	org/apache/commons/net/telnet/TelnetInputStream:__threaded	Z
    //   287: ifeq +10 -> 297
    //   290: aload_0
    //   291: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   294: invokevirtual 102	java/lang/Object:notify	()V
    //   297: aload 4
    //   299: monitorexit
    //   300: iload_1
    //   301: ireturn
    //   302: astore 5
    //   304: aload 4
    //   306: monitorexit
    //   307: aload 5
    //   309: athrow
    //   310: astore 5
    //   312: goto -229 -> 83
    //   315: astore 5
    //   317: goto -181 -> 136
    //   320: astore 6
    //   322: goto -108 -> 214
    //   325: iconst_0
    //   326: istore_3
    //   327: goto -227 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   100	106	180	java/io/InterruptedIOException
    //   191	204	222	finally
    //   204	214	222	finally
    //   214	217	222	finally
    //   224	227	222	finally
    //   9	30	302	finally
    //   30	47	302	finally
    //   49	63	302	finally
    //   63	80	302	finally
    //   83	93	302	finally
    //   93	98	302	finally
    //   100	106	302	finally
    //   116	119	302	finally
    //   127	133	302	finally
    //   136	146	302	finally
    //   148	169	302	finally
    //   172	177	302	finally
    //   182	191	302	finally
    //   217	220	302	finally
    //   227	230	302	finally
    //   230	266	302	finally
    //   266	297	302	finally
    //   297	300	302	finally
    //   304	307	302	finally
    //   63	80	310	java/lang/InterruptedException
    //   127	133	315	java/lang/InterruptedException
    //   204	214	320	java/lang/InterruptedException
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 < 1)
      return 0;
    int[] arrayOfInt = this.__queue;
    int i = paramInt2;
    try
    {
      if (paramInt2 > this.__bytesAvailable)
        i = this.__bytesAvailable;
      paramInt2 = read();
      if (paramInt2 == -1)
        return -1;
      int k;
      for (int j = paramInt1; ; j = k)
      {
        k = j + 1;
        paramArrayOfByte[j] = ((byte)paramInt2);
        i -= 1;
        if (i <= 0)
          break;
        paramInt2 = read();
        if (paramInt2 == -1)
          break;
      }
      return k - paramInt1;
    }
    finally
    {
    }
    throw paramArrayOfByte;
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 85	org/apache/commons/net/telnet/TelnetInputStream:__threaded	Z
    //   5: aload_0
    //   6: getfield 69	org/apache/commons/net/telnet/TelnetInputStream:__isClosed	Z
    //   9: istore_2
    //   10: iload_2
    //   11: ifne +139 -> 150
    //   14: aload_0
    //   15: iconst_1
    //   16: invokespecial 197	org/apache/commons/net/telnet/TelnetInputStream:__read	(Z)I
    //   19: istore_1
    //   20: iload_1
    //   21: ifge +6 -> 27
    //   24: goto +126 -> 150
    //   27: aload_0
    //   28: iload_1
    //   29: invokespecial 199	org/apache/commons/net/telnet/TelnetInputStream:__processChar	(I)Z
    //   32: istore_2
    //   33: goto +13 -> 46
    //   36: aload_0
    //   37: getfield 69	org/apache/commons/net/telnet/TelnetInputStream:__isClosed	Z
    //   40: ifeq +169 -> 209
    //   43: goto +107 -> 150
    //   46: iload_2
    //   47: ifeq -42 -> 5
    //   50: aload_0
    //   51: getfield 65	org/apache/commons/net/telnet/TelnetInputStream:__client	Lorg/apache/commons/net/telnet/TelnetClient;
    //   54: invokevirtual 215	org/apache/commons/net/telnet/TelnetClient:notifyInputListener	()V
    //   57: goto -52 -> 5
    //   60: aload_0
    //   61: invokespecial 179	java/io/BufferedInputStream:close	()V
    //   64: goto +86 -> 150
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   73: astore_3
    //   74: aload_3
    //   75: monitorenter
    //   76: aload_0
    //   77: aload 4
    //   79: putfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   82: aload_0
    //   83: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   86: invokevirtual 189	java/lang/Object:notifyAll	()V
    //   89: aload_0
    //   90: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   93: ldc2_w 200
    //   96: invokevirtual 204	java/lang/Object:wait	(J)V
    //   99: goto +15 -> 114
    //   102: aload_0
    //   103: getfield 69	org/apache/commons/net/telnet/TelnetInputStream:__isClosed	Z
    //   106: ifeq +8 -> 114
    //   109: aload_3
    //   110: monitorexit
    //   111: goto +39 -> 150
    //   114: aload_3
    //   115: monitorexit
    //   116: goto -111 -> 5
    //   119: astore 4
    //   121: aload_3
    //   122: monitorexit
    //   123: aload 4
    //   125: athrow
    //   126: astore 4
    //   128: aload_0
    //   129: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   132: astore_3
    //   133: aload_3
    //   134: monitorenter
    //   135: aload_0
    //   136: aload 4
    //   138: putfield 81	org/apache/commons/net/telnet/TelnetInputStream:__ioException	Ljava/io/IOException;
    //   141: aload_3
    //   142: monitorexit
    //   143: aload_0
    //   144: getfield 65	org/apache/commons/net/telnet/TelnetInputStream:__client	Lorg/apache/commons/net/telnet/TelnetClient;
    //   147: invokevirtual 215	org/apache/commons/net/telnet/TelnetClient:notifyInputListener	()V
    //   150: aload_0
    //   151: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   154: astore_3
    //   155: aload_3
    //   156: monitorenter
    //   157: aload_0
    //   158: iconst_1
    //   159: putfield 69	org/apache/commons/net/telnet/TelnetInputStream:__isClosed	Z
    //   162: aload_0
    //   163: iconst_1
    //   164: putfield 71	org/apache/commons/net/telnet/TelnetInputStream:__hasReachedEOF	Z
    //   167: aload_0
    //   168: getfield 73	org/apache/commons/net/telnet/TelnetInputStream:__queue	[I
    //   171: invokevirtual 102	java/lang/Object:notify	()V
    //   174: aload_3
    //   175: monitorexit
    //   176: aload_0
    //   177: iconst_0
    //   178: putfield 85	org/apache/commons/net/telnet/TelnetInputStream:__threaded	Z
    //   181: return
    //   182: astore 4
    //   184: aload_3
    //   185: monitorexit
    //   186: aload 4
    //   188: athrow
    //   189: astore 4
    //   191: aload_3
    //   192: monitorexit
    //   193: aload 4
    //   195: athrow
    //   196: astore_3
    //   197: goto -137 -> 60
    //   200: astore_3
    //   201: goto -165 -> 36
    //   204: astore 4
    //   206: goto -104 -> 102
    //   209: iconst_0
    //   210: istore_2
    //   211: goto -165 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   14	20	67	java/io/InterruptedIOException
    //   76	89	119	finally
    //   89	99	119	finally
    //   102	111	119	finally
    //   114	116	119	finally
    //   121	123	119	finally
    //   5	10	126	java/io/IOException
    //   14	20	126	java/io/IOException
    //   27	33	126	java/io/IOException
    //   36	43	126	java/io/IOException
    //   50	57	126	java/io/IOException
    //   60	64	126	java/io/IOException
    //   69	76	126	java/io/IOException
    //   123	126	126	java/io/IOException
    //   157	176	182	finally
    //   184	186	182	finally
    //   135	143	189	finally
    //   191	193	189	finally
    //   14	20	196	java/lang/RuntimeException
    //   27	33	200	java/lang/InterruptedException
    //   89	99	204	java/lang/InterruptedException
  }
}