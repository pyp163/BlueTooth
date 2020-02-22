package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public abstract class ResponseBody
  implements Closeable
{

  @Nullable
  private Reader reader;

  private Charset charset()
  {
    MediaType localMediaType = contentType();
    if (localMediaType != null)
      return localMediaType.charset(StandardCharsets.UTF_8);
    return StandardCharsets.UTF_8;
  }

  public static ResponseBody create(@Nullable MediaType paramMediaType, final long paramLong, BufferedSource paramBufferedSource)
  {
    if (paramBufferedSource == null)
      throw new NullPointerException("source == null");
    return new ResponseBody()
    {
      public long contentLength()
      {
        return paramLong;
      }

      @Nullable
      public MediaType contentType()
      {
        return ResponseBody.this;
      }

      public BufferedSource source()
      {
        return this.val$content;
      }
    };
  }

  public static ResponseBody create(@Nullable MediaType paramMediaType, String paramString)
  {
    Object localObject1 = StandardCharsets.UTF_8;
    Object localObject2 = paramMediaType;
    if (paramMediaType != null)
    {
      Charset localCharset = paramMediaType.charset();
      localObject1 = localCharset;
      localObject2 = paramMediaType;
      if (localCharset == null)
      {
        localObject1 = StandardCharsets.UTF_8;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramMediaType);
        ((StringBuilder)localObject2).append("; charset=utf-8");
        localObject2 = MediaType.parse(((StringBuilder)localObject2).toString());
      }
    }
    paramMediaType = new Buffer().writeString(paramString, (Charset)localObject1);
    return create((MediaType)localObject2, paramMediaType.size(), paramMediaType);
  }

  public static ResponseBody create(@Nullable MediaType paramMediaType, ByteString paramByteString)
  {
    Buffer localBuffer = new Buffer().write(paramByteString);
    return create(paramMediaType, paramByteString.size(), localBuffer);
  }

  public static ResponseBody create(@Nullable MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    Buffer localBuffer = new Buffer().write(paramArrayOfByte);
    return create(paramMediaType, paramArrayOfByte.length, localBuffer);
  }

  public final InputStream byteStream()
  {
    return source().inputStream();
  }

  // ERROR //
  public final byte[] bytes()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 130	okhttp3/ResponseBody:contentLength	()J
    //   4: lstore_1
    //   5: lload_1
    //   6: ldc2_w 131
    //   9: lcmp
    //   10: ifle +36 -> 46
    //   13: new 67	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   20: astore_3
    //   21: aload_3
    //   22: ldc 134
    //   24: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_3
    //   29: lload_1
    //   30: invokevirtual 137	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: new 127	java/io/IOException
    //   37: dup
    //   38: aload_3
    //   39: invokevirtual 81	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokespecial 138	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   45: athrow
    //   46: aload_0
    //   47: invokevirtual 118	okhttp3/ResponseBody:source	()Lokio/BufferedSource;
    //   50: astore 5
    //   52: aconst_null
    //   53: astore_3
    //   54: aload 5
    //   56: invokeinterface 141 1 0
    //   61: astore 4
    //   63: aload 5
    //   65: ifnull +9 -> 74
    //   68: aconst_null
    //   69: aload 5
    //   71: invokestatic 143	okhttp3/ResponseBody:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   74: lload_1
    //   75: ldc2_w 144
    //   78: lcmp
    //   79: ifeq +67 -> 146
    //   82: lload_1
    //   83: aload 4
    //   85: arraylength
    //   86: i2l
    //   87: lcmp
    //   88: ifeq +58 -> 146
    //   91: new 67	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   98: astore_3
    //   99: aload_3
    //   100: ldc 147
    //   102: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload_3
    //   107: lload_1
    //   108: invokevirtual 137	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_3
    //   113: ldc 149
    //   115: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_3
    //   120: aload 4
    //   122: arraylength
    //   123: invokevirtual 152	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload_3
    //   128: ldc 154
    //   130: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: pop
    //   134: new 127	java/io/IOException
    //   137: dup
    //   138: aload_3
    //   139: invokevirtual 81	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: invokespecial 138	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   145: athrow
    //   146: aload 4
    //   148: areturn
    //   149: astore 4
    //   151: goto +11 -> 162
    //   154: astore 4
    //   156: aload 4
    //   158: astore_3
    //   159: aload 4
    //   161: athrow
    //   162: aload 5
    //   164: ifnull +9 -> 173
    //   167: aload_3
    //   168: aload 5
    //   170: invokestatic 143	okhttp3/ResponseBody:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   173: aload 4
    //   175: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   54	63	149	finally
    //   159	162	149	finally
    //   54	63	154	java/lang/Throwable
  }

  public final Reader charStream()
  {
    Object localObject = this.reader;
    if (localObject != null)
      return localObject;
    localObject = new BomAwareReader(source(), charset());
    this.reader = ((Reader)localObject);
    return localObject;
  }

  public void close()
  {
    Util.closeQuietly(source());
  }

  public abstract long contentLength();

  @Nullable
  public abstract MediaType contentType();

  public abstract BufferedSource source();

  // ERROR //
  public final String string()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 118	okhttp3/ResponseBody:source	()Lokio/BufferedSource;
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_1
    //   7: aload_3
    //   8: aload_3
    //   9: aload_0
    //   10: invokespecial 160	okhttp3/ResponseBody:charset	()Ljava/nio/charset/Charset;
    //   13: invokestatic 174	okhttp3/internal/Util:bomAwareCharset	(Lokio/BufferedSource;Ljava/nio/charset/Charset;)Ljava/nio/charset/Charset;
    //   16: invokeinterface 178 2 0
    //   21: astore_2
    //   22: aload_3
    //   23: ifnull +8 -> 31
    //   26: aconst_null
    //   27: aload_3
    //   28: invokestatic 143	okhttp3/ResponseBody:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   31: aload_2
    //   32: areturn
    //   33: astore_2
    //   34: goto +8 -> 42
    //   37: astore_2
    //   38: aload_2
    //   39: astore_1
    //   40: aload_2
    //   41: athrow
    //   42: aload_3
    //   43: ifnull +8 -> 51
    //   46: aload_1
    //   47: aload_3
    //   48: invokestatic 143	okhttp3/ResponseBody:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   51: aload_2
    //   52: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	22	33	finally
    //   40	42	33	finally
    //   7	22	37	java/lang/Throwable
  }

  static final class BomAwareReader extends Reader
  {
    private final Charset charset;
    private boolean closed;

    @Nullable
    private Reader delegate;
    private final BufferedSource source;

    BomAwareReader(BufferedSource paramBufferedSource, Charset paramCharset)
    {
      this.source = paramBufferedSource;
      this.charset = paramCharset;
    }

    public void close()
      throws IOException
    {
      this.closed = true;
      if (this.delegate != null)
      {
        this.delegate.close();
        return;
      }
      this.source.close();
    }

    public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      if (this.closed)
        throw new IOException("Stream closed");
      Reader localReader = this.delegate;
      Object localObject = localReader;
      if (localReader == null)
      {
        localObject = Util.bomAwareCharset(this.source, this.charset);
        localObject = new InputStreamReader(this.source.inputStream(), (Charset)localObject);
        this.delegate = ((Reader)localObject);
      }
      return ((Reader)localObject).read(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
}