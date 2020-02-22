package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Relay
{
  private static final long FILE_HEADER_SIZE = 32L;
  static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
  static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
  private static final int SOURCE_FILE = 2;
  private static final int SOURCE_UPSTREAM = 1;
  final Buffer buffer = new Buffer();
  final long bufferMaxSize;
  boolean complete;
  RandomAccessFile file;
  private final ByteString metadata;
  int sourceCount;
  Source upstream;
  final Buffer upstreamBuffer = new Buffer();
  long upstreamPos;
  Thread upstreamReader;

  private Relay(RandomAccessFile paramRandomAccessFile, Source paramSource, long paramLong1, ByteString paramByteString, long paramLong2)
  {
    this.file = paramRandomAccessFile;
    this.upstream = paramSource;
    boolean bool;
    if (paramSource == null)
      bool = true;
    else
      bool = false;
    this.complete = bool;
    this.upstreamPos = paramLong1;
    this.metadata = paramByteString;
    this.bufferMaxSize = paramLong2;
  }

  public static Relay edit(File paramFile, Source paramSource, ByteString paramByteString, long paramLong)
    throws IOException
  {
    paramFile = new RandomAccessFile(paramFile, "rw");
    paramSource = new Relay(paramFile, paramSource, 0L, paramByteString, paramLong);
    paramFile.setLength(0L);
    paramSource.writeHeader(PREFIX_DIRTY, -1L, -1L);
    return paramSource;
  }

  public static Relay read(File paramFile)
    throws IOException
  {
    paramFile = new RandomAccessFile(paramFile, "rw");
    FileOperator localFileOperator = new FileOperator(paramFile.getChannel());
    Buffer localBuffer = new Buffer();
    localFileOperator.read(0L, localBuffer, 32L);
    if (!localBuffer.readByteString(PREFIX_CLEAN.size()).equals(PREFIX_CLEAN))
      throw new IOException("unreadable cache file");
    long l1 = localBuffer.readLong();
    long l2 = localBuffer.readLong();
    localBuffer = new Buffer();
    localFileOperator.read(l1 + 32L, localBuffer, l2);
    return new Relay(paramFile, null, l1, localBuffer.readByteString(), 0L);
  }

  private void writeHeader(ByteString paramByteString, long paramLong1, long paramLong2)
    throws IOException
  {
    Buffer localBuffer = new Buffer();
    localBuffer.write(paramByteString);
    localBuffer.writeLong(paramLong1);
    localBuffer.writeLong(paramLong2);
    if (localBuffer.size() != 32L)
      throw new IllegalArgumentException();
    new FileOperator(this.file.getChannel()).write(0L, localBuffer, 32L);
  }

  private void writeMetadata(long paramLong)
    throws IOException
  {
    Buffer localBuffer = new Buffer();
    localBuffer.write(this.metadata);
    new FileOperator(this.file.getChannel()).write(32L + paramLong, localBuffer, this.metadata.size());
  }

  void commit(long paramLong)
    throws IOException
  {
    writeMetadata(paramLong);
    this.file.getChannel().force(false);
    writeHeader(PREFIX_CLEAN, paramLong, this.metadata.size());
    this.file.getChannel().force(false);
    try
    {
      this.complete = true;
      Util.closeQuietly(this.upstream);
      this.upstream = null;
      return;
    }
    finally
    {
    }
  }

  boolean isClosed()
  {
    return this.file == null;
  }

  public ByteString metadata()
  {
    return this.metadata;
  }

  public Source newSource()
  {
    try
    {
      if (this.file == null)
        return null;
      this.sourceCount += 1;
      return new RelaySource();
    }
    finally
    {
    }
  }

  class RelaySource
    implements Source
  {
    private FileOperator fileOperator = new FileOperator(Relay.this.file.getChannel());
    private long sourcePos;
    private final Timeout timeout = new Timeout();

    RelaySource()
    {
    }

    public void close()
      throws IOException
    {
      if (this.fileOperator == null)
        return;
      RandomAccessFile localRandomAccessFile = null;
      this.fileOperator = null;
      synchronized (Relay.this)
      {
        Relay localRelay2 = Relay.this;
        localRelay2.sourceCount -= 1;
        if (Relay.this.sourceCount == 0)
        {
          localRandomAccessFile = Relay.this.file;
          Relay.this.file = null;
        }
        if (localRandomAccessFile != null)
          Util.closeQuietly(localRandomAccessFile);
        return;
      }
    }

    // ERROR //
    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 46	okhttp3/internal/cache2/Relay$RelaySource:fileOperator	Lokhttp3/internal/cache2/FileOperator;
      //   4: ifnonnull +13 -> 17
      //   7: new 65	java/lang/IllegalStateException
      //   10: dup
      //   11: ldc 67
      //   13: invokespecial 70	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   16: athrow
      //   17: aload_0
      //   18: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   21: astore 9
      //   23: aload 9
      //   25: monitorenter
      //   26: aload_0
      //   27: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   30: lstore 7
      //   32: aload_0
      //   33: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   36: getfield 75	okhttp3/internal/cache2/Relay:upstreamPos	J
      //   39: lstore 5
      //   41: lload 7
      //   43: lload 5
      //   45: lcmp
      //   46: ifne +63 -> 109
      //   49: aload_0
      //   50: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   53: getfield 79	okhttp3/internal/cache2/Relay:complete	Z
      //   56: ifeq +10 -> 66
      //   59: aload 9
      //   61: monitorexit
      //   62: ldc2_w 80
      //   65: lreturn
      //   66: aload_0
      //   67: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   70: getfield 85	okhttp3/internal/cache2/Relay:upstreamReader	Ljava/lang/Thread;
      //   73: ifnull +17 -> 90
      //   76: aload_0
      //   77: getfield 29	okhttp3/internal/cache2/Relay$RelaySource:timeout	Lokio/Timeout;
      //   80: aload_0
      //   81: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   84: invokevirtual 89	okio/Timeout:waitUntilNotified	(Ljava/lang/Object;)V
      //   87: goto -61 -> 26
      //   90: aload_0
      //   91: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   94: invokestatic 95	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   97: putfield 85	okhttp3/internal/cache2/Relay:upstreamReader	Ljava/lang/Thread;
      //   100: iconst_1
      //   101: istore 4
      //   103: aload 9
      //   105: monitorexit
      //   106: goto +34 -> 140
      //   109: lload 5
      //   111: aload_0
      //   112: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   115: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   118: invokevirtual 105	okio/Buffer:size	()J
      //   121: lsub
      //   122: lstore 7
      //   124: aload_0
      //   125: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   128: lload 7
      //   130: lcmp
      //   131: ifge +365 -> 496
      //   134: aload 9
      //   136: monitorexit
      //   137: iconst_2
      //   138: istore 4
      //   140: iload 4
      //   142: iconst_2
      //   143: if_icmpne +44 -> 187
      //   146: lload_2
      //   147: lload 5
      //   149: aload_0
      //   150: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   153: lsub
      //   154: invokestatic 111	java/lang/Math:min	(JJ)J
      //   157: lstore_2
      //   158: aload_0
      //   159: getfield 46	okhttp3/internal/cache2/Relay$RelaySource:fileOperator	Lokhttp3/internal/cache2/FileOperator;
      //   162: aload_0
      //   163: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   166: ldc2_w 112
      //   169: ladd
      //   170: aload_1
      //   171: lload_2
      //   172: invokevirtual 116	okhttp3/internal/cache2/FileOperator:read	(JLokio/Buffer;J)V
      //   175: aload_0
      //   176: aload_0
      //   177: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   180: lload_2
      //   181: ladd
      //   182: putfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   185: lload_2
      //   186: lreturn
      //   187: aload_0
      //   188: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   191: getfield 120	okhttp3/internal/cache2/Relay:upstream	Lokio/Source;
      //   194: aload_0
      //   195: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   198: getfield 123	okhttp3/internal/cache2/Relay:upstreamBuffer	Lokio/Buffer;
      //   201: aload_0
      //   202: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   205: getfield 126	okhttp3/internal/cache2/Relay:bufferMaxSize	J
      //   208: invokeinterface 128 4 0
      //   213: lstore 7
      //   215: lload 7
      //   217: ldc2_w 80
      //   220: lcmp
      //   221: ifne +47 -> 268
      //   224: aload_0
      //   225: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   228: lload 5
      //   230: invokevirtual 132	okhttp3/internal/cache2/Relay:commit	(J)V
      //   233: aload_0
      //   234: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   237: astore_1
      //   238: aload_1
      //   239: monitorenter
      //   240: aload_0
      //   241: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   244: aconst_null
      //   245: putfield 85	okhttp3/internal/cache2/Relay:upstreamReader	Ljava/lang/Thread;
      //   248: aload_0
      //   249: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   252: invokevirtual 135	java/lang/Object:notifyAll	()V
      //   255: aload_1
      //   256: monitorexit
      //   257: ldc2_w 80
      //   260: lreturn
      //   261: astore 9
      //   263: aload_1
      //   264: monitorexit
      //   265: aload 9
      //   267: athrow
      //   268: lload 7
      //   270: lload_2
      //   271: invokestatic 111	java/lang/Math:min	(JJ)J
      //   274: lstore_2
      //   275: aload_0
      //   276: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   279: getfield 123	okhttp3/internal/cache2/Relay:upstreamBuffer	Lokio/Buffer;
      //   282: aload_1
      //   283: lconst_0
      //   284: lload_2
      //   285: invokevirtual 139	okio/Buffer:copyTo	(Lokio/Buffer;JJ)Lokio/Buffer;
      //   288: pop
      //   289: aload_0
      //   290: aload_0
      //   291: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   294: lload_2
      //   295: ladd
      //   296: putfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   299: aload_0
      //   300: getfield 46	okhttp3/internal/cache2/Relay$RelaySource:fileOperator	Lokhttp3/internal/cache2/FileOperator;
      //   303: lload 5
      //   305: ldc2_w 112
      //   308: ladd
      //   309: aload_0
      //   310: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   313: getfield 123	okhttp3/internal/cache2/Relay:upstreamBuffer	Lokio/Buffer;
      //   316: invokevirtual 143	okio/Buffer:clone	()Lokio/Buffer;
      //   319: lload 7
      //   321: invokevirtual 146	okhttp3/internal/cache2/FileOperator:write	(JLokio/Buffer;J)V
      //   324: aload_0
      //   325: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   328: astore_1
      //   329: aload_1
      //   330: monitorenter
      //   331: aload_0
      //   332: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   335: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   338: aload_0
      //   339: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   342: getfield 123	okhttp3/internal/cache2/Relay:upstreamBuffer	Lokio/Buffer;
      //   345: lload 7
      //   347: invokevirtual 149	okio/Buffer:write	(Lokio/Buffer;J)V
      //   350: aload_0
      //   351: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   354: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   357: invokevirtual 105	okio/Buffer:size	()J
      //   360: aload_0
      //   361: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   364: getfield 126	okhttp3/internal/cache2/Relay:bufferMaxSize	J
      //   367: lcmp
      //   368: ifle +31 -> 399
      //   371: aload_0
      //   372: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   375: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   378: aload_0
      //   379: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   382: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   385: invokevirtual 105	okio/Buffer:size	()J
      //   388: aload_0
      //   389: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   392: getfield 126	okhttp3/internal/cache2/Relay:bufferMaxSize	J
      //   395: lsub
      //   396: invokevirtual 152	okio/Buffer:skip	(J)V
      //   399: aload_0
      //   400: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   403: astore 9
      //   405: aload 9
      //   407: aload 9
      //   409: getfield 75	okhttp3/internal/cache2/Relay:upstreamPos	J
      //   412: lload 7
      //   414: ladd
      //   415: putfield 75	okhttp3/internal/cache2/Relay:upstreamPos	J
      //   418: aload_1
      //   419: monitorexit
      //   420: aload_0
      //   421: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   424: astore_1
      //   425: aload_1
      //   426: monitorenter
      //   427: aload_0
      //   428: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   431: aconst_null
      //   432: putfield 85	okhttp3/internal/cache2/Relay:upstreamReader	Ljava/lang/Thread;
      //   435: aload_0
      //   436: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   439: invokevirtual 135	java/lang/Object:notifyAll	()V
      //   442: aload_1
      //   443: monitorexit
      //   444: lload_2
      //   445: lreturn
      //   446: astore 9
      //   448: aload_1
      //   449: monitorexit
      //   450: aload 9
      //   452: athrow
      //   453: astore 9
      //   455: aload_1
      //   456: monitorexit
      //   457: aload 9
      //   459: athrow
      //   460: astore 9
      //   462: aload_0
      //   463: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   466: astore_1
      //   467: aload_1
      //   468: monitorenter
      //   469: aload_0
      //   470: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   473: aconst_null
      //   474: putfield 85	okhttp3/internal/cache2/Relay:upstreamReader	Ljava/lang/Thread;
      //   477: aload_0
      //   478: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   481: invokevirtual 135	java/lang/Object:notifyAll	()V
      //   484: aload_1
      //   485: monitorexit
      //   486: aload 9
      //   488: athrow
      //   489: astore 9
      //   491: aload_1
      //   492: monitorexit
      //   493: aload 9
      //   495: athrow
      //   496: lload_2
      //   497: lload 5
      //   499: aload_0
      //   500: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   503: lsub
      //   504: invokestatic 111	java/lang/Math:min	(JJ)J
      //   507: lstore_2
      //   508: aload_0
      //   509: getfield 21	okhttp3/internal/cache2/Relay$RelaySource:this$0	Lokhttp3/internal/cache2/Relay;
      //   512: getfield 99	okhttp3/internal/cache2/Relay:buffer	Lokio/Buffer;
      //   515: aload_1
      //   516: aload_0
      //   517: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   520: lload 7
      //   522: lsub
      //   523: lload_2
      //   524: invokevirtual 139	okio/Buffer:copyTo	(Lokio/Buffer;JJ)Lokio/Buffer;
      //   527: pop
      //   528: aload_0
      //   529: aload_0
      //   530: getfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   533: lload_2
      //   534: ladd
      //   535: putfield 72	okhttp3/internal/cache2/Relay$RelaySource:sourcePos	J
      //   538: aload 9
      //   540: monitorexit
      //   541: lload_2
      //   542: lreturn
      //   543: astore_1
      //   544: aload 9
      //   546: monitorexit
      //   547: aload_1
      //   548: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   240	257	261	finally
      //   263	265	261	finally
      //   427	444	446	finally
      //   448	450	446	finally
      //   331	399	453	finally
      //   399	420	453	finally
      //   455	457	453	finally
      //   187	215	460	finally
      //   224	233	460	finally
      //   268	331	460	finally
      //   457	460	460	finally
      //   469	486	489	finally
      //   491	493	489	finally
      //   26	41	543	finally
      //   49	62	543	finally
      //   66	87	543	finally
      //   90	100	543	finally
      //   103	106	543	finally
      //   109	137	543	finally
      //   496	541	543	finally
      //   544	547	543	finally
    }

    public Timeout timeout()
    {
      return this.timeout;
    }
  }
}