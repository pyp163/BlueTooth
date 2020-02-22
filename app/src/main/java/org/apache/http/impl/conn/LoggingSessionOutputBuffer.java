package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.annotation.Immutable;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionOutputBuffer
  implements SessionOutputBuffer
{
  private final String charset;
  private final SessionOutputBuffer out;
  private final Wire wire;

  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire)
  {
    this(paramSessionOutputBuffer, paramWire, null);
  }

  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire, String paramString)
  {
    this.out = paramSessionOutputBuffer;
    this.wire = paramWire;
    if (paramString == null)
      paramString = "ASCII";
    this.charset = paramString;
  }

  public void flush()
    throws IOException
  {
    this.out.flush();
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.out.getMetrics();
  }

  public void write(int paramInt)
    throws IOException
  {
    this.out.write(paramInt);
    if (this.wire.enabled())
      this.wire.output(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    this.out.write(paramArrayOfByte);
    if (this.wire.enabled())
      this.wire.output(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    if (this.wire.enabled())
      this.wire.output(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeLine(String paramString)
    throws IOException
  {
    this.out.writeLine(paramString);
    if (this.wire.enabled())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("\r\n");
      paramString = localStringBuilder.toString();
      this.wire.output(paramString.getBytes(this.charset));
    }
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    this.out.writeLine(paramCharArrayBuffer);
    if (this.wire.enabled())
    {
      paramCharArrayBuffer = new String(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length());
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramCharArrayBuffer);
      localStringBuilder.append("\r\n");
      paramCharArrayBuffer = localStringBuilder.toString();
      this.wire.output(paramCharArrayBuffer.getBytes(this.charset));
    }
  }
}