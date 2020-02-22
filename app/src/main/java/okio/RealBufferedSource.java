package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

final class RealBufferedSource
  implements BufferedSource
{
  public final Buffer buffer = new Buffer();
  boolean closed;
  public final Source source;

  RealBufferedSource(Source paramSource)
  {
    if (paramSource == null)
      throw new NullPointerException("source == null");
    this.source = paramSource;
  }

  public Buffer buffer()
  {
    return this.buffer;
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    this.closed = true;
    this.source.close();
    this.buffer.clear();
  }

  public boolean exhausted()
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    return (this.buffer.exhausted()) && (this.source.read(this.buffer, 8192L) == -1L);
  }

  public Buffer getBuffer()
  {
    return this.buffer;
  }

  public long indexOf(byte paramByte)
    throws IOException
  {
    return indexOf(paramByte, 0L, 9223372036854775807L);
  }

  public long indexOf(byte paramByte, long paramLong)
    throws IOException
  {
    return indexOf(paramByte, paramLong, 9223372036854775807L);
  }

  public long indexOf(byte paramByte, long paramLong1, long paramLong2)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    if (paramLong1 >= 0L)
    {
      long l = paramLong1;
      if (paramLong2 >= paramLong1)
      {
        while (l < paramLong2)
        {
          paramLong1 = this.buffer.indexOf(paramByte, l, paramLong2);
          if (paramLong1 != -1L)
            return paramLong1;
          paramLong1 = this.buffer.size;
          if (paramLong1 < paramLong2)
          {
            if (this.source.read(this.buffer, 8192L) == -1L)
              return -1L;
            l = Math.max(l, paramLong1);
          }
          else
          {
            return -1L;
          }
        }
        return -1L;
      }
    }
    throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2) }));
  }

  public long indexOf(ByteString paramByteString)
    throws IOException
  {
    return indexOf(paramByteString, 0L);
  }

  public long indexOf(ByteString paramByteString, long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    while (true)
    {
      long l = this.buffer.indexOf(paramByteString, paramLong);
      if (l != -1L)
        return l;
      l = this.buffer.size;
      if (this.source.read(this.buffer, 8192L) == -1L)
        return -1L;
      paramLong = Math.max(paramLong, l - paramByteString.size() + 1L);
    }
  }

  public long indexOfElement(ByteString paramByteString)
    throws IOException
  {
    return indexOfElement(paramByteString, 0L);
  }

  public long indexOfElement(ByteString paramByteString, long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    while (true)
    {
      long l = this.buffer.indexOfElement(paramByteString, paramLong);
      if (l != -1L)
        return l;
      l = this.buffer.size;
      if (this.source.read(this.buffer, 8192L) == -1L)
        return -1L;
      paramLong = Math.max(paramLong, l);
    }
  }

  public InputStream inputStream()
  {
    return new InputStream()
    {
      public int available()
        throws IOException
      {
        if (RealBufferedSource.this.closed)
          throw new IOException("closed");
        return (int)Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
      }

      public void close()
        throws IOException
      {
        RealBufferedSource.this.close();
      }

      public int read()
        throws IOException
      {
        if (RealBufferedSource.this.closed)
          throw new IOException("closed");
        if ((RealBufferedSource.this.buffer.size == 0L) && (RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L) == -1L))
          return -1;
        return RealBufferedSource.this.buffer.readByte() & 0xFF;
      }

      public int read(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        if (RealBufferedSource.this.closed)
          throw new IOException("closed");
        Util.checkOffsetAndCount(paramAnonymousArrayOfByte.length, paramAnonymousInt1, paramAnonymousInt2);
        if ((RealBufferedSource.this.buffer.size == 0L) && (RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L) == -1L))
          return -1;
        return RealBufferedSource.this.buffer.read(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(RealBufferedSource.this);
        localStringBuilder.append(".inputStream()");
        return localStringBuilder.toString();
      }
    };
  }

  public boolean isOpen()
  {
    return this.closed ^ true;
  }

  public BufferedSource peek()
  {
    return Okio.buffer(new PeekSource(this));
  }

  public boolean rangeEquals(long paramLong, ByteString paramByteString)
    throws IOException
  {
    return rangeEquals(paramLong, paramByteString, 0, paramByteString.size());
  }

  public boolean rangeEquals(long paramLong, ByteString paramByteString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    if ((paramLong >= 0L) && (paramInt1 >= 0) && (paramInt2 >= 0))
    {
      if (paramByteString.size() - paramInt1 < paramInt2)
        return false;
      int i = 0;
      while (i < paramInt2)
      {
        long l = i + paramLong;
        if (!request(1L + l))
          return false;
        if (this.buffer.getByte(l) != paramByteString.getByte(paramInt1 + i))
          return false;
        i += 1;
      }
      return true;
    }
    return false;
  }

  public int read(ByteBuffer paramByteBuffer)
    throws IOException
  {
    if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 8192L) == -1L))
      return -1;
    return this.buffer.read(paramByteBuffer);
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    long l1 = paramArrayOfByte.length;
    long l2 = paramInt1;
    long l3 = paramInt2;
    Util.checkOffsetAndCount(l1, l2, l3);
    if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 8192L) == -1L))
      return -1;
    paramInt2 = (int)Math.min(l3, this.buffer.size);
    return this.buffer.read(paramArrayOfByte, paramInt1, paramInt2);
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (paramBuffer == null)
      throw new IllegalArgumentException("sink == null");
    if (paramLong < 0L)
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    if (this.closed)
      throw new IllegalStateException("closed");
    if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 8192L) == -1L))
      return -1L;
    paramLong = Math.min(paramLong, this.buffer.size);
    return this.buffer.read(paramBuffer, paramLong);
  }

  public long readAll(Sink paramSink)
    throws IOException
  {
    if (paramSink == null)
      throw new IllegalArgumentException("sink == null");
    long l1 = 0L;
    while (this.source.read(this.buffer, 8192L) != -1L)
    {
      l2 = this.buffer.completeSegmentByteCount();
      if (l2 > 0L)
      {
        l1 += l2;
        paramSink.write(this.buffer, l2);
      }
    }
    long l2 = l1;
    if (this.buffer.size() > 0L)
    {
      l2 = l1 + this.buffer.size();
      paramSink.write(this.buffer, this.buffer.size());
    }
    return l2;
  }

  public byte readByte()
    throws IOException
  {
    require(1L);
    return this.buffer.readByte();
  }

  public byte[] readByteArray()
    throws IOException
  {
    this.buffer.writeAll(this.source);
    return this.buffer.readByteArray();
  }

  public byte[] readByteArray(long paramLong)
    throws IOException
  {
    require(paramLong);
    return this.buffer.readByteArray(paramLong);
  }

  public ByteString readByteString()
    throws IOException
  {
    this.buffer.writeAll(this.source);
    return this.buffer.readByteString();
  }

  public ByteString readByteString(long paramLong)
    throws IOException
  {
    require(paramLong);
    return this.buffer.readByteString(paramLong);
  }

  public long readDecimalLong()
    throws IOException
  {
    require(1L);
    int j;
    byte b;
    for (int i = 0; ; i = j)
    {
      j = i + 1;
      if (!request(j))
        break label88;
      b = this.buffer.getByte(i);
      if (((b < 48) || (b > 57)) && ((i != 0) || (b != 45)))
        break;
    }
    if (i == 0)
      throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[] { Byte.valueOf(b) }));
    label88: return this.buffer.readDecimalLong();
  }

  public void readFully(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    try
    {
      require(paramLong);
      this.buffer.readFully(paramBuffer, paramLong);
      return;
    }
    catch (EOFException localEOFException)
    {
      paramBuffer.writeAll(this.buffer);
      throw localEOFException;
    }
  }

  public void readFully(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      require(paramArrayOfByte.length);
      this.buffer.readFully(paramArrayOfByte);
      return;
    }
    catch (EOFException localEOFException)
    {
      int i = 0;
      while (this.buffer.size > 0L)
      {
        int j = this.buffer.read(paramArrayOfByte, i, (int)this.buffer.size);
        if (j == -1)
          throw new AssertionError();
        i += j;
      }
      throw localEOFException;
    }
  }

  public long readHexadecimalUnsignedLong()
    throws IOException
  {
    require(1L);
    int j;
    byte b;
    for (int i = 0; ; i = j)
    {
      j = i + 1;
      if (!request(j))
        break label102;
      b = this.buffer.getByte(i);
      if (((b < 48) || (b > 57)) && ((b < 97) || (b > 102)) && ((b < 65) || (b > 70)))
        break;
    }
    if (i == 0)
      throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[] { Byte.valueOf(b) }));
    label102: return this.buffer.readHexadecimalUnsignedLong();
  }

  public int readInt()
    throws IOException
  {
    require(4L);
    return this.buffer.readInt();
  }

  public int readIntLe()
    throws IOException
  {
    require(4L);
    return this.buffer.readIntLe();
  }

  public long readLong()
    throws IOException
  {
    require(8L);
    return this.buffer.readLong();
  }

  public long readLongLe()
    throws IOException
  {
    require(8L);
    return this.buffer.readLongLe();
  }

  public short readShort()
    throws IOException
  {
    require(2L);
    return this.buffer.readShort();
  }

  public short readShortLe()
    throws IOException
  {
    require(2L);
    return this.buffer.readShortLe();
  }

  public String readString(long paramLong, Charset paramCharset)
    throws IOException
  {
    require(paramLong);
    if (paramCharset == null)
      throw new IllegalArgumentException("charset == null");
    return this.buffer.readString(paramLong, paramCharset);
  }

  public String readString(Charset paramCharset)
    throws IOException
  {
    if (paramCharset == null)
      throw new IllegalArgumentException("charset == null");
    this.buffer.writeAll(this.source);
    return this.buffer.readString(paramCharset);
  }

  public String readUtf8()
    throws IOException
  {
    this.buffer.writeAll(this.source);
    return this.buffer.readUtf8();
  }

  public String readUtf8(long paramLong)
    throws IOException
  {
    require(paramLong);
    return this.buffer.readUtf8(paramLong);
  }

  public int readUtf8CodePoint()
    throws IOException
  {
    require(1L);
    int i = this.buffer.getByte(0L);
    if ((i & 0xE0) == 192)
      require(2L);
    else if ((i & 0xF0) == 224)
      require(3L);
    else if ((i & 0xF8) == 240)
      require(4L);
    return this.buffer.readUtf8CodePoint();
  }

  @Nullable
  public String readUtf8Line()
    throws IOException
  {
    long l = indexOf((byte)10);
    if (l == -1L)
    {
      if (this.buffer.size != 0L)
        return readUtf8(this.buffer.size);
      return null;
    }
    return this.buffer.readUtf8Line(l);
  }

  public String readUtf8LineStrict()
    throws IOException
  {
    return readUtf8LineStrict(9223372036854775807L);
  }

  public String readUtf8LineStrict(long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("limit < 0: ");
      ((StringBuilder)localObject).append(paramLong);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    long l1;
    if (paramLong == 9223372036854775807L)
      l1 = 9223372036854775807L;
    else
      l1 = paramLong + 1L;
    long l2 = indexOf((byte)10, 0L, l1);
    if (l2 != -1L)
      return this.buffer.readUtf8Line(l2);
    if ((l1 < 9223372036854775807L) && (request(l1)) && (this.buffer.getByte(l1 - 1L) == 13) && (request(1L + l1)) && (this.buffer.getByte(l1) == 10))
      return this.buffer.readUtf8Line(l1);
    Object localObject = new Buffer();
    this.buffer.copyTo((Buffer)localObject, 0L, Math.min(32L, this.buffer.size()));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\\n not found: limit=");
    localStringBuilder.append(Math.min(this.buffer.size(), paramLong));
    localStringBuilder.append(" content=");
    localStringBuilder.append(((Buffer)localObject).readByteString().hex());
    localStringBuilder.append('…');
    throw new EOFException(localStringBuilder.toString());
  }

  public boolean request(long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("byteCount < 0: ");
      localStringBuilder.append(paramLong);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (this.closed)
      throw new IllegalStateException("closed");
    while (this.buffer.size < paramLong)
      if (this.source.read(this.buffer, 8192L) == -1L)
        return false;
    return true;
  }

  public void require(long paramLong)
    throws IOException
  {
    if (!request(paramLong))
      throw new EOFException();
  }

  public int select(Options paramOptions)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    int i;
    do
    {
      i = this.buffer.selectPrefix(paramOptions, true);
      if (i == -1)
        return -1;
      if (i != -2)
        break;
    }
    while (this.source.read(this.buffer, 8192L) != -1L);
    return -1;
    int j = paramOptions.byteStrings[i].size();
    this.buffer.skip(j);
    return i;
  }

  public void skip(long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    while (paramLong > 0L)
    {
      if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 8192L) == -1L))
        throw new EOFException();
      long l = Math.min(paramLong, this.buffer.size());
      this.buffer.skip(l);
      paramLong -= l;
    }
  }

  public Timeout timeout()
  {
    return this.source.timeout();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buffer(");
    localStringBuilder.append(this.source);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}