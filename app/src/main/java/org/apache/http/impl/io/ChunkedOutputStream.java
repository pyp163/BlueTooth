package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;

public class ChunkedOutputStream extends OutputStream
{
  private byte[] cache;
  private int cachePosition = 0;
  private boolean closed = false;
  private final SessionOutputBuffer out;
  private boolean wroteLastChunk = false;

  public ChunkedOutputStream(SessionOutputBuffer paramSessionOutputBuffer)
    throws IOException
  {
    this(paramSessionOutputBuffer, 2048);
  }

  public ChunkedOutputStream(SessionOutputBuffer paramSessionOutputBuffer, int paramInt)
    throws IOException
  {
    this.cache = new byte[paramInt];
    this.out = paramSessionOutputBuffer;
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
    {
      this.closed = true;
      finish();
      this.out.flush();
    }
  }

  public void finish()
    throws IOException
  {
    if (!this.wroteLastChunk)
    {
      flushCache();
      writeClosingChunk();
      this.wroteLastChunk = true;
    }
  }

  public void flush()
    throws IOException
  {
    flushCache();
    this.out.flush();
  }

  protected void flushCache()
    throws IOException
  {
    if (this.cachePosition > 0)
    {
      this.out.writeLine(Integer.toHexString(this.cachePosition));
      this.out.write(this.cache, 0, this.cachePosition);
      this.out.writeLine("");
      this.cachePosition = 0;
    }
  }

  protected void flushCacheWithAppend(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.writeLine(Integer.toHexString(this.cachePosition + paramInt2));
    this.out.write(this.cache, 0, this.cachePosition);
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    this.out.writeLine("");
    this.cachePosition = 0;
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted write to closed stream.");
    this.cache[this.cachePosition] = ((byte)paramInt);
    this.cachePosition += 1;
    if (this.cachePosition == this.cache.length)
      flushCache();
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted write to closed stream.");
    if (paramInt2 >= this.cache.length - this.cachePosition)
    {
      flushCacheWithAppend(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.cache, this.cachePosition, paramInt2);
    this.cachePosition += paramInt2;
  }

  protected void writeClosingChunk()
    throws IOException
  {
    this.out.writeLine("0");
    this.out.writeLine("");
  }
}