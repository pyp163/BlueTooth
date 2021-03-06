package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

final class RealBufferedSink
  implements BufferedSink
{
  public final Buffer buffer = new Buffer();
  boolean closed;
  public final Sink sink;

  RealBufferedSink(Sink paramSink)
  {
    if (paramSink == null)
      throw new NullPointerException("sink == null");
    this.sink = paramSink;
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
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (this.buffer.size > 0L)
      {
        this.sink.write(this.buffer, this.buffer.size);
        localObject1 = localObject2;
      }
    }
    catch (Throwable localThrowable1)
    {
    }
    try
    {
      this.sink.close();
      localObject2 = localThrowable1;
    }
    catch (Throwable localThrowable2)
    {
      localObject2 = localThrowable1;
      if (localThrowable1 == null)
        localObject2 = localThrowable2;
    }
    this.closed = true;
    if (localObject2 != null)
      Util.sneakyRethrow(localObject2);
  }

  public BufferedSink emit()
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    long l = this.buffer.size();
    if (l > 0L)
      this.sink.write(this.buffer, l);
    return this;
  }

  public BufferedSink emitCompleteSegments()
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    long l = this.buffer.completeSegmentByteCount();
    if (l > 0L)
      this.sink.write(this.buffer, l);
    return this;
  }

  public void flush()
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    if (this.buffer.size > 0L)
      this.sink.write(this.buffer, this.buffer.size);
    this.sink.flush();
  }

  public boolean isOpen()
  {
    return this.closed ^ true;
  }

  public OutputStream outputStream()
  {
    return new OutputStream()
    {
      public void close()
        throws IOException
      {
        RealBufferedSink.this.close();
      }

      public void flush()
        throws IOException
      {
        if (!RealBufferedSink.this.closed)
          RealBufferedSink.this.flush();
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(RealBufferedSink.this);
        localStringBuilder.append(".outputStream()");
        return localStringBuilder.toString();
      }

      public void write(int paramAnonymousInt)
        throws IOException
      {
        if (RealBufferedSink.this.closed)
          throw new IOException("closed");
        RealBufferedSink.this.buffer.writeByte((byte)paramAnonymousInt);
        RealBufferedSink.this.emitCompleteSegments();
      }

      public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        if (RealBufferedSink.this.closed)
          throw new IOException("closed");
        RealBufferedSink.this.buffer.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        RealBufferedSink.this.emitCompleteSegments();
      }
    };
  }

  public Timeout timeout()
  {
    return this.sink.timeout();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buffer(");
    localStringBuilder.append(this.sink);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  public int write(ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    int i = this.buffer.write(paramByteBuffer);
    emitCompleteSegments();
    return i;
  }

  public BufferedSink write(ByteString paramByteString)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.write(paramByteString);
    return emitCompleteSegments();
  }

  public BufferedSink write(Source paramSource, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      long l = paramSource.read(this.buffer, paramLong);
      if (l == -1L)
        throw new EOFException();
      paramLong -= l;
      emitCompleteSegments();
    }
    return this;
  }

  public BufferedSink write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.write(paramArrayOfByte);
    return emitCompleteSegments();
  }

  public BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.write(paramArrayOfByte, paramInt1, paramInt2);
    return emitCompleteSegments();
  }

  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.write(paramBuffer, paramLong);
    emitCompleteSegments();
  }

  public long writeAll(Source paramSource)
    throws IOException
  {
    if (paramSource == null)
      throw new IllegalArgumentException("source == null");
    long l1 = 0L;
    while (true)
    {
      long l2 = paramSource.read(this.buffer, 8192L);
      if (l2 == -1L)
        break;
      l1 += l2;
      emitCompleteSegments();
    }
    return l1;
  }

  public BufferedSink writeByte(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeByte(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeDecimalLong(long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeDecimalLong(paramLong);
    return emitCompleteSegments();
  }

  public BufferedSink writeHexadecimalUnsignedLong(long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeHexadecimalUnsignedLong(paramLong);
    return emitCompleteSegments();
  }

  public BufferedSink writeInt(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeInt(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeIntLe(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeIntLe(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeLong(long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeLong(paramLong);
    return emitCompleteSegments();
  }

  public BufferedSink writeLongLe(long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeLongLe(paramLong);
    return emitCompleteSegments();
  }

  public BufferedSink writeShort(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeShort(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeShortLe(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeShortLe(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeString(String paramString, int paramInt1, int paramInt2, Charset paramCharset)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeString(paramString, paramInt1, paramInt2, paramCharset);
    return emitCompleteSegments();
  }

  public BufferedSink writeString(String paramString, Charset paramCharset)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeString(paramString, paramCharset);
    return emitCompleteSegments();
  }

  public BufferedSink writeUtf8(String paramString)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeUtf8(paramString);
    return emitCompleteSegments();
  }

  public BufferedSink writeUtf8(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeUtf8(paramString, paramInt1, paramInt2);
    return emitCompleteSegments();
  }

  public BufferedSink writeUtf8CodePoint(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    this.buffer.writeUtf8CodePoint(paramInt);
    return emitCompleteSegments();
  }
}