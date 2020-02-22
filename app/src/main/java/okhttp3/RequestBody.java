package okhttp3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;

public abstract class RequestBody
{
  public static RequestBody create(@Nullable MediaType paramMediaType, final File paramFile)
  {
    if (paramFile == null)
      throw new NullPointerException("file == null");
    return new RequestBody()
    {
      public long contentLength()
      {
        return paramFile.length();
      }

      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }

      // ERROR //
      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	okhttp3/RequestBody$3:val$file	Ljava/io/File;
        //   4: invokestatic 44	okio/Okio:source	(Ljava/io/File;)Lokio/Source;
        //   7: astore_3
        //   8: aload_1
        //   9: aload_3
        //   10: invokeinterface 50 2 0
        //   15: pop2
        //   16: aload_3
        //   17: ifnull +9 -> 26
        //   20: aload_3
        //   21: invokeinterface 55 1 0
        //   26: return
        //   27: astore_2
        //   28: aconst_null
        //   29: astore_1
        //   30: goto +7 -> 37
        //   33: astore_1
        //   34: aload_1
        //   35: athrow
        //   36: astore_2
        //   37: aload_3
        //   38: ifnull +31 -> 69
        //   41: aload_1
        //   42: ifnull +21 -> 63
        //   45: aload_3
        //   46: invokeinterface 55 1 0
        //   51: goto +18 -> 69
        //   54: astore_3
        //   55: aload_1
        //   56: aload_3
        //   57: invokevirtual 59	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
        //   60: goto +9 -> 69
        //   63: aload_3
        //   64: invokeinterface 55 1 0
        //   69: aload_2
        //   70: athrow
        //
        // Exception table:
        //   from	to	target	type
        //   8	16	27	finally
        //   8	16	33	java/lang/Throwable
        //   34	36	36	finally
        //   45	51	54	java/lang/Throwable
      }
    };
  }

  public static RequestBody create(@Nullable MediaType paramMediaType, String paramString)
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
    return create((MediaType)localObject2, paramString.getBytes((Charset)localObject1));
  }

  public static RequestBody create(@Nullable MediaType paramMediaType, final ByteString paramByteString)
  {
    return new RequestBody()
    {
      public long contentLength()
        throws IOException
      {
        return paramByteString.size();
      }

      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }

      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        paramAnonymousBufferedSink.write(paramByteString);
      }
    };
  }

  public static RequestBody create(@Nullable MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    return create(paramMediaType, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static RequestBody create(@Nullable MediaType paramMediaType, final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException("content == null");
    Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    return new RequestBody()
    {
      public long contentLength()
      {
        return paramInt2;
      }

      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }

      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        paramAnonymousBufferedSink.write(paramArrayOfByte, paramInt1, paramInt2);
      }
    };
  }

  public long contentLength()
    throws IOException
  {
    return -1L;
  }

  @Nullable
  public abstract MediaType contentType();

  public boolean isDuplex()
  {
    return false;
  }

  public boolean isOneShot()
  {
    return false;
  }

  public abstract void writeTo(BufferedSink paramBufferedSink)
    throws IOException;
}