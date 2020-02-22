package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.ExceptionUtils;

public class ChunkedInputStream extends InputStream
{
  private static final int BUFFER_SIZE = 2048;
  private static final int CHUNK_CRLF = 3;
  private static final int CHUNK_DATA = 2;
  private static final int CHUNK_LEN = 1;
  private final CharArrayBuffer buffer;
  private int chunkSize;
  private boolean closed = false;
  private boolean eof = false;
  private Header[] footers = new Header[0];
  private final SessionInputBuffer in;
  private int pos;
  private int state;

  public ChunkedInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.in = paramSessionInputBuffer;
    this.pos = 0;
    this.buffer = new CharArrayBuffer(16);
    this.state = 1;
  }

  private int getChunkSize()
    throws IOException
  {
    int i = this.state;
    if (i != 1)
    {
      if (i != 3)
        throw new IllegalStateException("Inconsistent codec state");
      this.buffer.clear();
      if (this.in.readLine(this.buffer) == -1)
        return 0;
      if (!this.buffer.isEmpty())
        throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
      this.state = 1;
    }
    this.buffer.clear();
    if (this.in.readLine(this.buffer) == -1)
      return 0;
    int j = this.buffer.indexOf(59);
    i = j;
    if (j < 0)
      i = this.buffer.length();
    try
    {
      i = Integer.parseInt(this.buffer.substringTrimmed(0, i), 16);
      return i;
      label143: throw new MalformedChunkCodingException("Bad chunk header");
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label143;
    }
  }

  private void nextChunk()
    throws IOException
  {
    this.chunkSize = getChunkSize();
    if (this.chunkSize < 0)
      throw new MalformedChunkCodingException("Negative chunk size");
    this.state = 2;
    this.pos = 0;
    if (this.chunkSize == 0)
    {
      this.eof = true;
      parseTrailerHeaders();
    }
  }

  private void parseTrailerHeaders()
    throws IOException
  {
    try
    {
      this.footers = AbstractMessageParser.parseHeaders(this.in, -1, -1, null);
      return;
    }
    catch (HttpException localHttpException)
    {
      Object localObject = new StringBuffer();
      ((StringBuffer)localObject).append("Invalid footer: ");
      ((StringBuffer)localObject).append(localHttpException.getMessage());
      localObject = new MalformedChunkCodingException(((StringBuffer)localObject).toString());
      ExceptionUtils.initCause((Throwable)localObject, localHttpException);
      throw ((Throwable)localObject);
    }
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
      return Math.min(((BufferInfo)this.in).length(), this.chunkSize - this.pos);
    return 0;
  }

  public void close()
    throws IOException
  {
    if (!this.closed)
      try
      {
        if (!this.eof)
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
        this.eof = true;
        this.closed = true;
      }
  }

  public Header[] getFooters()
  {
    return (Header[])this.footers.clone();
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof)
      return -1;
    if (this.state != 2)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    int i = this.in.read();
    if (i != -1)
    {
      this.pos += 1;
      if (this.pos >= this.chunkSize)
        this.state = 3;
    }
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
    if (this.eof)
      return -1;
    if (this.state != 2)
    {
      nextChunk();
      if (this.eof)
        return -1;
    }
    paramInt2 = Math.min(paramInt2, this.chunkSize - this.pos);
    paramInt1 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 != -1)
    {
      this.pos += paramInt1;
      if (this.pos >= this.chunkSize)
        this.state = 3;
      return paramInt1;
    }
    this.eof = true;
    paramArrayOfByte = new StringBuffer();
    paramArrayOfByte.append("Truncated chunk ( expected size: ");
    paramArrayOfByte.append(this.chunkSize);
    paramArrayOfByte.append("; actual size: ");
    paramArrayOfByte.append(this.pos);
    paramArrayOfByte.append(")");
    throw new TruncatedChunkException(paramArrayOfByte.toString());
  }
}