package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache
  implements Closeable, Flushable
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  final DiskLruCache cache;
  private int hitCount;
  final InternalCache internalCache = new InternalCache()
  {
    @Nullable
    public Response get(Request paramAnonymousRequest)
      throws IOException
    {
      return Cache.this.get(paramAnonymousRequest);
    }

    @Nullable
    public CacheRequest put(Response paramAnonymousResponse)
      throws IOException
    {
      return Cache.this.put(paramAnonymousResponse);
    }

    public void remove(Request paramAnonymousRequest)
      throws IOException
    {
      Cache.this.remove(paramAnonymousRequest);
    }

    public void trackConditionalCacheHit()
    {
      Cache.this.trackConditionalCacheHit();
    }

    public void trackResponse(CacheStrategy paramAnonymousCacheStrategy)
    {
      Cache.this.trackResponse(paramAnonymousCacheStrategy);
    }

    public void update(Response paramAnonymousResponse1, Response paramAnonymousResponse2)
    {
      Cache.this.update(paramAnonymousResponse1, paramAnonymousResponse2);
    }
  };
  private int networkCount;
  private int requestCount;
  int writeAbortCount;
  int writeSuccessCount;

  public Cache(File paramFile, long paramLong)
  {
    this(paramFile, paramLong, FileSystem.SYSTEM);
  }

  Cache(File paramFile, long paramLong, FileSystem paramFileSystem)
  {
    this.cache = DiskLruCache.create(paramFileSystem, paramFile, 201105, 2, paramLong);
  }

  private void abortQuietly(@Nullable DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null);
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException paramEditor)
    {
    }
  }

  public static String key(HttpUrl paramHttpUrl)
  {
    return ByteString.encodeUtf8(paramHttpUrl.toString()).md5().hex();
  }

  static int readInt(BufferedSource paramBufferedSource)
    throws IOException
  {
    long l;
    try
    {
      l = paramBufferedSource.readDecimalLong();
      paramBufferedSource = paramBufferedSource.readUtf8LineStrict();
      if ((l >= 0L) && (l <= 2147483647L))
        if (paramBufferedSource.isEmpty())
          break label97;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("expected an int but was \"");
      localStringBuilder.append(l);
      localStringBuilder.append(paramBufferedSource);
      localStringBuilder.append("\"");
      throw new IOException(localStringBuilder.toString());
    }
    catch (NumberFormatException paramBufferedSource)
    {
      throw new IOException(paramBufferedSource.getMessage());
    }
    label97: return (int)l;
  }

  public void close()
    throws IOException
  {
    this.cache.close();
  }

  public void delete()
    throws IOException
  {
    this.cache.delete();
  }

  public File directory()
  {
    return this.cache.getDirectory();
  }

  public void evictAll()
    throws IOException
  {
    this.cache.evictAll();
  }

  public void flush()
    throws IOException
  {
    this.cache.flush();
  }

  // ERROR //
  @Nullable
  Response get(Request paramRequest)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 171	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   4: invokestatic 173	okhttp3/Cache:key	(Lokhttp3/HttpUrl;)Ljava/lang/String;
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 71	okhttp3/Cache:cache	Lokhttp3/internal/cache/DiskLruCache;
    //   12: aload_2
    //   13: invokevirtual 176	okhttp3/internal/cache/DiskLruCache:get	(Ljava/lang/String;)Lokhttp3/internal/cache/DiskLruCache$Snapshot;
    //   16: astore_2
    //   17: aload_2
    //   18: ifnonnull +5 -> 23
    //   21: aconst_null
    //   22: areturn
    //   23: new 24	okhttp3/Cache$Entry
    //   26: dup
    //   27: aload_2
    //   28: iconst_0
    //   29: invokevirtual 182	okhttp3/internal/cache/DiskLruCache$Snapshot:getSource	(I)Lokio/Source;
    //   32: invokespecial 185	okhttp3/Cache$Entry:<init>	(Lokio/Source;)V
    //   35: astore_3
    //   36: aload_3
    //   37: aload_2
    //   38: invokevirtual 189	okhttp3/Cache$Entry:response	(Lokhttp3/internal/cache/DiskLruCache$Snapshot;)Lokhttp3/Response;
    //   41: astore_2
    //   42: aload_3
    //   43: aload_1
    //   44: aload_2
    //   45: invokevirtual 193	okhttp3/Cache$Entry:matches	(Lokhttp3/Request;Lokhttp3/Response;)Z
    //   48: ifne +12 -> 60
    //   51: aload_2
    //   52: invokevirtual 199	okhttp3/Response:body	()Lokhttp3/ResponseBody;
    //   55: invokestatic 205	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   58: aconst_null
    //   59: areturn
    //   60: aload_2
    //   61: areturn
    //   62: aload_2
    //   63: invokestatic 205	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   66: aconst_null
    //   67: areturn
    //   68: astore_1
    //   69: aconst_null
    //   70: areturn
    //   71: astore_1
    //   72: goto -10 -> 62
    //
    // Exception table:
    //   from	to	target	type
    //   8	17	68	java/io/IOException
    //   23	36	71	java/io/IOException
  }

  public int hitCount()
  {
    try
    {
      int i = this.hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void initialize()
    throws IOException
  {
    this.cache.initialize();
  }

  public boolean isClosed()
  {
    return this.cache.isClosed();
  }

  public long maxSize()
  {
    return this.cache.getMaxSize();
  }

  public int networkCount()
  {
    try
    {
      int i = this.networkCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  @Nullable
  CacheRequest put(Response paramResponse)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 227	okhttp3/Response:request	()Lokhttp3/Request;
    //   4: invokevirtual 230	okhttp3/Request:method	()Ljava/lang/String;
    //   7: astore_2
    //   8: aload_1
    //   9: invokevirtual 227	okhttp3/Response:request	()Lokhttp3/Request;
    //   12: invokevirtual 230	okhttp3/Request:method	()Ljava/lang/String;
    //   15: invokestatic 236	okhttp3/internal/http/HttpMethod:invalidatesCache	(Ljava/lang/String;)Z
    //   18: ifeq +13 -> 31
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 227	okhttp3/Response:request	()Lokhttp3/Request;
    //   26: invokevirtual 240	okhttp3/Cache:remove	(Lokhttp3/Request;)V
    //   29: aconst_null
    //   30: areturn
    //   31: aload_2
    //   32: ldc 242
    //   34: invokevirtual 246	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifne +5 -> 42
    //   40: aconst_null
    //   41: areturn
    //   42: aload_1
    //   43: invokestatic 252	okhttp3/internal/http/HttpHeaders:hasVaryAll	(Lokhttp3/Response;)Z
    //   46: ifeq +5 -> 51
    //   49: aconst_null
    //   50: areturn
    //   51: new 24	okhttp3/Cache$Entry
    //   54: dup
    //   55: aload_1
    //   56: invokespecial 255	okhttp3/Cache$Entry:<init>	(Lokhttp3/Response;)V
    //   59: astore_2
    //   60: aload_0
    //   61: getfield 71	okhttp3/Cache:cache	Lokhttp3/internal/cache/DiskLruCache;
    //   64: aload_1
    //   65: invokevirtual 227	okhttp3/Response:request	()Lokhttp3/Request;
    //   68: invokevirtual 171	okhttp3/Request:url	()Lokhttp3/HttpUrl;
    //   71: invokestatic 173	okhttp3/Cache:key	(Lokhttp3/HttpUrl;)Ljava/lang/String;
    //   74: invokevirtual 259	okhttp3/internal/cache/DiskLruCache:edit	(Ljava/lang/String;)Lokhttp3/internal/cache/DiskLruCache$Editor;
    //   77: astore_1
    //   78: aload_1
    //   79: ifnonnull +5 -> 84
    //   82: aconst_null
    //   83: areturn
    //   84: aload_2
    //   85: aload_1
    //   86: invokevirtual 262	okhttp3/Cache$Entry:writeTo	(Lokhttp3/internal/cache/DiskLruCache$Editor;)V
    //   89: new 14	okhttp3/Cache$CacheRequestImpl
    //   92: dup
    //   93: aload_0
    //   94: aload_1
    //   95: invokespecial 265	okhttp3/Cache$CacheRequestImpl:<init>	(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V
    //   98: astore_2
    //   99: aload_2
    //   100: areturn
    //   101: aconst_null
    //   102: astore_1
    //   103: aload_0
    //   104: aload_1
    //   105: invokespecial 267	okhttp3/Cache:abortQuietly	(Lokhttp3/internal/cache/DiskLruCache$Editor;)V
    //   108: aconst_null
    //   109: areturn
    //   110: astore_1
    //   111: aconst_null
    //   112: areturn
    //   113: astore_1
    //   114: goto -13 -> 101
    //   117: astore_2
    //   118: goto -15 -> 103
    //
    // Exception table:
    //   from	to	target	type
    //   21	29	110	java/io/IOException
    //   60	78	113	java/io/IOException
    //   84	99	117	java/io/IOException
  }

  void remove(Request paramRequest)
    throws IOException
  {
    this.cache.remove(key(paramRequest.url()));
  }

  public int requestCount()
  {
    try
    {
      int i = this.requestCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long size()
    throws IOException
  {
    return this.cache.size();
  }

  void trackConditionalCacheHit()
  {
    try
    {
      this.hitCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void trackResponse(CacheStrategy paramCacheStrategy)
  {
    try
    {
      this.requestCount += 1;
      if (paramCacheStrategy.networkRequest != null)
        this.networkCount += 1;
      else if (paramCacheStrategy.cacheResponse != null)
        this.hitCount += 1;
      return;
    }
    finally
    {
    }
    throw paramCacheStrategy;
  }

  void update(Response paramResponse1, Response paramResponse2)
  {
    paramResponse2 = new Entry(paramResponse2);
    paramResponse1 = ((CacheResponseBody)paramResponse1.body()).snapshot;
    try
    {
      paramResponse1 = paramResponse1.edit();
      if (paramResponse1 == null);
    }
    catch (IOException paramResponse1)
    {
      try
      {
        paramResponse2.writeTo(paramResponse1);
        paramResponse1.commit();
        return;
        while (true)
        {
          paramResponse1 = null;
          label41: abortQuietly(paramResponse1);
          return;
          paramResponse1 = paramResponse1;
        }
      }
      catch (IOException paramResponse2)
      {
        break label41;
      }
    }
  }

  public Iterator<String> urls()
    throws IOException
  {
    return new Iterator()
    {
      boolean canRemove;
      final Iterator<DiskLruCache.Snapshot> delegate = Cache.this.cache.snapshots();

      @Nullable
      String nextUrl;

      // ERROR //
      public boolean hasNext()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 50	okhttp3/Cache$2:nextUrl	Ljava/lang/String;
        //   4: ifnull +5 -> 9
        //   7: iconst_1
        //   8: ireturn
        //   9: aload_0
        //   10: iconst_0
        //   11: putfield 52	okhttp3/Cache$2:canRemove	Z
        //   14: aload_0
        //   15: getfield 42	okhttp3/Cache$2:delegate	Ljava/util/Iterator;
        //   18: invokeinterface 54 1 0
        //   23: ifeq +84 -> 107
        //   26: aload_0
        //   27: getfield 42	okhttp3/Cache$2:delegate	Ljava/util/Iterator;
        //   30: invokeinterface 58 1 0
        //   35: checkcast 60	okhttp3/internal/cache/DiskLruCache$Snapshot
        //   38: astore_3
        //   39: aconst_null
        //   40: astore_1
        //   41: aload_0
        //   42: aload_3
        //   43: iconst_0
        //   44: invokevirtual 64	okhttp3/internal/cache/DiskLruCache$Snapshot:getSource	(I)Lokio/Source;
        //   47: invokestatic 70	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
        //   50: invokeinterface 76 1 0
        //   55: putfield 50	okhttp3/Cache$2:nextUrl	Ljava/lang/String;
        //   58: aload_3
        //   59: ifnull +7 -> 66
        //   62: aload_3
        //   63: invokevirtual 79	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   66: iconst_1
        //   67: ireturn
        //   68: astore_2
        //   69: goto +8 -> 77
        //   72: astore_2
        //   73: aload_2
        //   74: astore_1
        //   75: aload_2
        //   76: athrow
        //   77: aload_3
        //   78: ifnull +27 -> 105
        //   81: aload_1
        //   82: ifnull +19 -> 101
        //   85: aload_3
        //   86: invokevirtual 79	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   89: goto +16 -> 105
        //   92: astore_3
        //   93: aload_1
        //   94: aload_3
        //   95: invokevirtual 83	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
        //   98: goto +7 -> 105
        //   101: aload_3
        //   102: invokevirtual 79	okhttp3/internal/cache/DiskLruCache$Snapshot:close	()V
        //   105: aload_2
        //   106: athrow
        //   107: iconst_0
        //   108: ireturn
        //   109: astore_1
        //   110: goto -96 -> 14
        //
        // Exception table:
        //   from	to	target	type
        //   41	58	68	finally
        //   75	77	68	finally
        //   41	58	72	java/lang/Throwable
        //   85	89	92	java/lang/Throwable
        //   26	39	109	java/io/IOException
        //   62	66	109	java/io/IOException
        //   85	89	109	java/io/IOException
        //   93	98	109	java/io/IOException
        //   101	105	109	java/io/IOException
        //   105	107	109	java/io/IOException
      }

      public String next()
      {
        if (!hasNext())
          throw new NoSuchElementException();
        String str = this.nextUrl;
        this.nextUrl = null;
        this.canRemove = true;
        return str;
      }

      public void remove()
      {
        if (!this.canRemove)
          throw new IllegalStateException("remove() before next()");
        this.delegate.remove();
      }
    };
  }

  public int writeAbortCount()
  {
    try
    {
      int i = this.writeAbortCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int writeSuccessCount()
  {
    try
    {
      int i = this.writeSuccessCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private final class CacheRequestImpl
    implements CacheRequest
  {
    private Sink body;
    private Sink cacheOut;
    boolean done;
    private final DiskLruCache.Editor editor;

    CacheRequestImpl(DiskLruCache.Editor arg2)
    {
      final DiskLruCache.Editor localEditor;
      this.editor = localEditor;
      this.cacheOut = localEditor.newSink(1);
      this.body = new ForwardingSink(this.cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (Cache.this)
          {
            if (Cache.CacheRequestImpl.this.done)
              return;
            Cache.CacheRequestImpl.this.done = true;
            Cache localCache2 = Cache.this;
            localCache2.writeSuccessCount += 1;
            super.close();
            localEditor.commit();
            return;
          }
        }
      };
    }

    public void abort()
    {
      synchronized (Cache.this)
      {
        if (this.done)
          return;
        this.done = true;
        Cache localCache2 = Cache.this;
        localCache2.writeAbortCount += 1;
        Util.closeQuietly(this.cacheOut);
      }
      try
      {
        this.editor.abort();
        return;
        localObject = finally;
        throw localObject;
      }
      catch (IOException localIOException)
      {
      }
    }

    public Sink body()
    {
      return this.body;
    }
  }

  private static class CacheResponseBody extends ResponseBody
  {
    private final BufferedSource bodySource;

    @Nullable
    private final String contentLength;

    @Nullable
    private final String contentType;
    final DiskLruCache.Snapshot snapshot;

    CacheResponseBody(final DiskLruCache.Snapshot paramSnapshot, String paramString1, String paramString2)
    {
      this.snapshot = paramSnapshot;
      this.contentType = paramString1;
      this.contentLength = paramString2;
      this.bodySource = Okio.buffer(new ForwardingSource(paramSnapshot.getSource(1))
      {
        public void close()
          throws IOException
        {
          paramSnapshot.close();
          super.close();
        }
      });
    }

    public long contentLength()
    {
      long l = -1L;
      try
      {
        if (this.contentLength != null)
          l = Long.parseLong(this.contentLength);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
      return -1L;
    }

    public MediaType contentType()
    {
      if (this.contentType != null)
        return MediaType.parse(this.contentType);
      return null;
    }

    public BufferedSource source()
    {
      return this.bodySource;
    }
  }

  private static final class Entry
  {
    private static final String RECEIVED_MILLIS = localStringBuilder.toString();
    private static final String SENT_MILLIS;
    private final int code;

    @Nullable
    private final Handshake handshake;
    private final String message;
    private final Protocol protocol;
    private final long receivedResponseMillis;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final long sentRequestMillis;
    private final String url;
    private final Headers varyHeaders;

    static
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Sent-Millis");
      SENT_MILLIS = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Received-Millis");
    }

    Entry(Response paramResponse)
    {
      this.url = paramResponse.request().url().toString();
      this.varyHeaders = HttpHeaders.varyHeaders(paramResponse);
      this.requestMethod = paramResponse.request().method();
      this.protocol = paramResponse.protocol();
      this.code = paramResponse.code();
      this.message = paramResponse.message();
      this.responseHeaders = paramResponse.headers();
      this.handshake = paramResponse.handshake();
      this.sentRequestMillis = paramResponse.sentRequestAtMillis();
      this.receivedResponseMillis = paramResponse.receivedResponseAtMillis();
    }

    Entry(Source paramSource)
      throws IOException
    {
      while (true)
      {
        try
        {
          Object localObject1 = Okio.buffer(paramSource);
          this.url = ((BufferedSource)localObject1).readUtf8LineStrict();
          this.requestMethod = ((BufferedSource)localObject1).readUtf8LineStrict();
          Object localObject3 = new Headers.Builder();
          int k = Cache.readInt((BufferedSource)localObject1);
          int j = 0;
          int i = 0;
          if (i < k)
          {
            ((Headers.Builder)localObject3).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
            i += 1;
            continue;
          }
          this.varyHeaders = ((Headers.Builder)localObject3).build();
          localObject3 = StatusLine.parse(((BufferedSource)localObject1).readUtf8LineStrict());
          this.protocol = ((StatusLine)localObject3).protocol;
          this.code = ((StatusLine)localObject3).code;
          this.message = ((StatusLine)localObject3).message;
          localObject3 = new Headers.Builder();
          k = Cache.readInt((BufferedSource)localObject1);
          i = j;
          if (i < k)
          {
            ((Headers.Builder)localObject3).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
            i += 1;
            continue;
          }
          Object localObject4 = ((Headers.Builder)localObject3).get(SENT_MILLIS);
          Object localObject5 = ((Headers.Builder)localObject3).get(RECEIVED_MILLIS);
          ((Headers.Builder)localObject3).removeAll(SENT_MILLIS);
          ((Headers.Builder)localObject3).removeAll(RECEIVED_MILLIS);
          long l2 = 0L;
          if (localObject4 != null)
          {
            l1 = Long.parseLong((String)localObject4);
            this.sentRequestMillis = l1;
            l1 = l2;
            if (localObject5 != null)
              l1 = Long.parseLong((String)localObject5);
            this.receivedResponseMillis = l1;
            this.responseHeaders = ((Headers.Builder)localObject3).build();
            if (isHttps())
            {
              localObject3 = ((BufferedSource)localObject1).readUtf8LineStrict();
              if (((String)localObject3).length() > 0)
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append("expected \"\" but was \"");
                ((StringBuilder)localObject1).append((String)localObject3);
                ((StringBuilder)localObject1).append("\"");
                throw new IOException(((StringBuilder)localObject1).toString());
              }
              localObject3 = CipherSuite.forJavaName(((BufferedSource)localObject1).readUtf8LineStrict());
              localObject4 = readCertificateList((BufferedSource)localObject1);
              localObject5 = readCertificateList((BufferedSource)localObject1);
              if (!((BufferedSource)localObject1).exhausted())
                localObject1 = TlsVersion.forJavaName(((BufferedSource)localObject1).readUtf8LineStrict());
              else
                localObject1 = TlsVersion.SSL_3_0;
              this.handshake = Handshake.get((TlsVersion)localObject1, (CipherSuite)localObject3, (List)localObject4, (List)localObject5);
            }
            else
            {
              this.handshake = null;
            }
            return;
          }
        }
        finally
        {
          paramSource.close();
        }
        long l1 = 0L;
      }
    }

    private boolean isHttps()
    {
      return this.url.startsWith("https://");
    }

    private List<Certificate> readCertificateList(BufferedSource paramBufferedSource)
      throws IOException
    {
      int j = Cache.readInt(paramBufferedSource);
      if (j == -1)
        return Collections.emptyList();
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ArrayList localArrayList = new ArrayList(j);
        int i = 0;
        while (i < j)
        {
          String str = paramBufferedSource.readUtf8LineStrict();
          Buffer localBuffer = new Buffer();
          localBuffer.write(ByteString.decodeBase64(str));
          localArrayList.add(localCertificateFactory.generateCertificate(localBuffer.inputStream()));
          i += 1;
        }
        return localArrayList;
      }
      catch (CertificateException paramBufferedSource)
      {
      }
      throw new IOException(paramBufferedSource.getMessage());
    }

    private void writeCertList(BufferedSink paramBufferedSink, List<Certificate> paramList)
      throws IOException
    {
      try
      {
        paramBufferedSink.writeDecimalLong(paramList.size()).writeByte(10);
        int i = 0;
        int j = paramList.size();
        while (i < j)
        {
          paramBufferedSink.writeUtf8(ByteString.of(((Certificate)paramList.get(i)).getEncoded()).base64()).writeByte(10);
          i += 1;
        }
        return;
      }
      catch (CertificateEncodingException paramBufferedSink)
      {
      }
      throw new IOException(paramBufferedSink.getMessage());
    }

    public boolean matches(Request paramRequest, Response paramResponse)
    {
      return (this.url.equals(paramRequest.url().toString())) && (this.requestMethod.equals(paramRequest.method())) && (HttpHeaders.varyMatches(paramResponse, this.varyHeaders, paramRequest));
    }

    public Response response(DiskLruCache.Snapshot paramSnapshot)
    {
      String str1 = this.responseHeaders.get("Content-Type");
      String str2 = this.responseHeaders.get("Content-Length");
      Request localRequest = new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build();
      return new Response.Builder().request(localRequest).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new Cache.CacheResponseBody(paramSnapshot, str1, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
    }

    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      int j = 0;
      paramEditor = Okio.buffer(paramEditor.newSink(0));
      paramEditor.writeUtf8(this.url).writeByte(10);
      paramEditor.writeUtf8(this.requestMethod).writeByte(10);
      paramEditor.writeDecimalLong(this.varyHeaders.size()).writeByte(10);
      int k = this.varyHeaders.size();
      int i = 0;
      while (i < k)
      {
        paramEditor.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
      paramEditor.writeDecimalLong(this.responseHeaders.size() + 2).writeByte(10);
      k = this.responseHeaders.size();
      i = j;
      while (i < k)
      {
        paramEditor.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
      paramEditor.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
      if (isHttps())
      {
        paramEditor.writeByte(10);
        paramEditor.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
        writeCertList(paramEditor, this.handshake.peerCertificates());
        writeCertList(paramEditor, this.handshake.localCertificates());
        paramEditor.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
      }
      paramEditor.close();
    }
  }
}