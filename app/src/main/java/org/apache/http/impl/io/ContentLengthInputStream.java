package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;

public class ContentLengthInputStream extends InputStream
{
  private static final int BUFFER_SIZE = 2048;
  private boolean closed = false;
  private long contentLength;
  private SessionInputBuffer in = null;
  private long pos = 0L;

  public ContentLengthInputStream(SessionInputBuffer paramSessionInputBuffer, long paramLong)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("Content length may not be negative");
    this.in = paramSessionInputBuffer;
    this.contentLength = paramLong;
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
      return Math.min(((BufferInfo)this.in).length(), (int)(this.contentLength - this.pos));
    return 0;
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
      try
      {
        if (this.pos < this.contentLength)
        {
          byte[] arrayOfByte = new byte[2048];
          int i;
          do
            i = read(arrayOfByte);
          while (i >= 0);
        }
        return;
      }
      finally
      {
        this.closed = true;
      }
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    int i = this.in.read();
    if (i == -1)
    {
      if (this.pos < this.contentLength)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("Premature end of Content-Length delimited message body (expected: ");
        localStringBuffer.append(this.contentLength);
        localStringBuffer.append("; received: ");
        localStringBuffer.append(this.pos);
        throw new ConnectionClosedException(localStringBuffer.toString());
      }
    }
    else
      this.pos += 1L;
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.pos >= this.contentLength)
      return -1;
    int i = paramInt2;
    if (this.pos + paramInt2 > this.contentLength)
      i = (int)(this.contentLength - this.pos);
    paramInt1 = this.in.read(paramArrayOfByte, paramInt1, i);
    if ((paramInt1 == -1) && (this.pos < this.contentLength))
    {
      paramArrayOfByte = new StringBuffer();
      paramArrayOfByte.append("Premature end of Content-Length delimited message body (expected: ");
      paramArrayOfByte.append(this.contentLength);
      paramArrayOfByte.append("; received: ");
      paramArrayOfByte.append(this.pos);
      throw new ConnectionClosedException(paramArrayOfByte.toString());
    }
    if (paramInt1 > 0)
      this.pos += paramInt1;
    return paramInt1;
  }

  public long skip(long paramLong)
    throws IOException
  {
    if (paramLong <= 0L)
      return 0L;
    byte[] arrayOfByte = new byte[2048];
    paramLong = Math.min(paramLong, this.contentLength - this.pos);
    long l1 = 0L;
    while (paramLong > 0L)
    {
      int i = read(arrayOfByte, 0, (int)Math.min(2048L, paramLong));
      if (i == -1)
        return l1;
      long l2 = i;
      l1 += l2;
      paramLong -= l2;
    }
    return l1;
  }
}