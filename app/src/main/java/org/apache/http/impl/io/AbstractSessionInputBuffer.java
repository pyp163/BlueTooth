package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractSessionInputBuffer
  implements SessionInputBuffer, BufferInfo
{
  private boolean ascii = true;
  private byte[] buffer;
  private int bufferlen;
  private int bufferpos;
  private String charset = "US-ASCII";
  private InputStream instream;
  private ByteArrayBuffer linebuffer = null;
  private int maxLineLen = -1;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit = 512;

  private int lineFromLineBuffer(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int j = this.linebuffer.length();
    if (j > 0)
    {
      i = j;
      if (this.linebuffer.byteAt(j - 1) == 10)
      {
        i = j - 1;
        this.linebuffer.setLength(i);
      }
      if ((i > 0) && (this.linebuffer.byteAt(i - 1) == 13))
        this.linebuffer.setLength(i - 1);
    }
    int i = this.linebuffer.length();
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.linebuffer, 0, i);
    }
    else
    {
      String str = new String(this.linebuffer.buffer(), 0, i, this.charset);
      i = str.length();
      paramCharArrayBuffer.append(str);
    }
    this.linebuffer.clear();
    return i;
  }

  private int lineFromReadBuffer(CharArrayBuffer paramCharArrayBuffer, int paramInt)
    throws IOException
  {
    int j = this.bufferpos;
    this.bufferpos = (paramInt + 1);
    int i = paramInt;
    if (paramInt > 0)
    {
      i = paramInt;
      if (this.buffer[(paramInt - 1)] == 13)
        i = paramInt - 1;
    }
    paramInt = i - j;
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.buffer, j, paramInt);
      return paramInt;
    }
    String str = new String(this.buffer, j, paramInt, this.charset);
    paramCharArrayBuffer.append(str);
    return str.length();
  }

  private int locateLF()
  {
    int i = this.bufferpos;
    while (i < this.bufferlen)
    {
      if (this.buffer[i] == 10)
        return i;
      i += 1;
    }
    return -1;
  }

  public int available()
  {
    return capacity() - length();
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  protected int fillBuffer()
    throws IOException
  {
    if (this.bufferpos > 0)
    {
      i = this.bufferlen - this.bufferpos;
      if (i > 0)
        System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, i);
      this.bufferpos = 0;
      this.bufferlen = i;
    }
    int i = this.bufferlen;
    int j = this.buffer.length;
    j = this.instream.read(this.buffer, i, j - i);
    if (j == -1)
      return -1;
    this.bufferlen = (i + j);
    this.metrics.incrementBytesTransferred(j);
    return j;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }

  protected boolean hasBufferedData()
  {
    return this.bufferpos < this.bufferlen;
  }

  protected void init(InputStream paramInputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.instream = paramInputStream;
    this.buffer = new byte[paramInt];
    boolean bool = false;
    this.bufferpos = 0;
    this.bufferlen = 0;
    this.linebuffer = new ByteArrayBuffer(paramInt);
    this.charset = HttpProtocolParams.getHttpElementCharset(paramHttpParams);
    if ((this.charset.equalsIgnoreCase("US-ASCII")) || (this.charset.equalsIgnoreCase("ASCII")))
      bool = true;
    this.ascii = bool;
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
  }

  public int length()
  {
    return this.bufferlen - this.bufferpos;
  }

  public int read()
    throws IOException
  {
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    byte[] arrayOfByte = this.buffer;
    int i = this.bufferpos;
    this.bufferpos = (i + 1);
    return arrayOfByte[i] & 0xFF;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    if (hasBufferedData())
    {
      paramInt2 = Math.min(paramInt2, this.bufferlen - this.bufferpos);
      System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, paramInt2);
      this.bufferpos += paramInt2;
      return paramInt2;
    }
    if (paramInt2 > this.minChunkLimit)
    {
      paramInt1 = this.instream.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 > 0)
        this.metrics.incrementBytesTransferred(paramInt1);
      return paramInt1;
    }
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    paramInt2 = Math.min(paramInt2, this.bufferlen - this.bufferpos);
    System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, paramInt2);
    this.bufferpos += paramInt2;
    return paramInt2;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int k = 1;
    int i = 0;
    while (k != 0)
    {
      int j = locateLF();
      if (j != -1)
      {
        if (this.linebuffer.isEmpty())
          return lineFromReadBuffer(paramCharArrayBuffer, j);
        j += 1;
        k = this.bufferpos;
        this.linebuffer.append(this.buffer, this.bufferpos, j - k);
        this.bufferpos = j;
      }
      int m;
      do
      {
        m = 0;
        j = i;
        break;
        if (hasBufferedData())
        {
          i = this.bufferlen;
          j = this.bufferpos;
          this.linebuffer.append(this.buffer, this.bufferpos, i - j);
          this.bufferpos = this.bufferlen;
        }
        i = fillBuffer();
        m = k;
        j = i;
      }
      while (i == -1);
      k = m;
      i = j;
      if (this.maxLineLen > 0)
      {
        k = m;
        i = j;
        if (this.linebuffer.length() >= this.maxLineLen)
          throw new IOException("Maximum line length limit exceeded");
      }
    }
    if ((i == -1) && (this.linebuffer.isEmpty()))
      return -1;
    return lineFromLineBuffer(paramCharArrayBuffer);
  }

  public String readLine()
    throws IOException
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(64);
    if (readLine(localCharArrayBuffer) != -1)
      return localCharArrayBuffer.toString();
    return null;
  }
}