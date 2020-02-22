package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractSessionOutputBuffer
  implements SessionOutputBuffer, BufferInfo
{
  private static final byte[] CRLF = { 13, 10 };
  private boolean ascii = true;
  private ByteArrayBuffer buffer;
  private String charset = "US-ASCII";
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit = 512;
  private OutputStream outstream;

  public int available()
  {
    return capacity() - length();
  }

  public int capacity()
  {
    return this.buffer.capacity();
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  public void flush()
    throws IOException
  {
    flushBuffer();
    this.outstream.flush();
  }

  protected void flushBuffer()
    throws IOException
  {
    int i = this.buffer.length();
    if (i > 0)
    {
      this.outstream.write(this.buffer.buffer(), 0, i);
      this.buffer.clear();
      this.metrics.incrementBytesTransferred(i);
    }
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }

  protected void init(OutputStream paramOutputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.outstream = paramOutputStream;
    this.buffer = new ByteArrayBuffer(paramInt);
    this.charset = HttpProtocolParams.getHttpElementCharset(paramHttpParams);
    boolean bool;
    if ((!this.charset.equalsIgnoreCase("US-ASCII")) && (!this.charset.equalsIgnoreCase("ASCII")))
      bool = false;
    else
      bool = true;
    this.ascii = bool;
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
  }

  public int length()
  {
    return this.buffer.length();
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.buffer.isFull())
      flushBuffer();
    this.buffer.append(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt2 <= this.minChunkLimit) && (paramInt2 <= this.buffer.capacity()))
    {
      if (paramInt2 > this.buffer.capacity() - this.buffer.length())
        flushBuffer();
      this.buffer.append(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    flushBuffer();
    this.outstream.write(paramArrayOfByte, paramInt1, paramInt2);
    this.metrics.incrementBytesTransferred(paramInt2);
  }

  public void writeLine(String paramString)
    throws IOException
  {
    if (paramString == null)
      return;
    if (paramString.length() > 0)
      write(paramString.getBytes(this.charset));
    write(CRLF);
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      return;
    if (this.ascii)
    {
      int j = 0;
      int i = paramCharArrayBuffer.length();
      while (i > 0)
      {
        int k = Math.min(this.buffer.capacity() - this.buffer.length(), i);
        if (k > 0)
          this.buffer.append(paramCharArrayBuffer, j, k);
        if (this.buffer.isFull())
          flushBuffer();
        j += k;
        i -= k;
      }
    }
    write(paramCharArrayBuffer.toString().getBytes(this.charset));
    write(CRLF);
  }
}