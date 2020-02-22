package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.annotation.Immutable;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionInputBuffer
  implements SessionInputBuffer, EofSensor
{
  private final String charset;
  private final EofSensor eofSensor;
  private final SessionInputBuffer in;
  private final Wire wire;

  public LoggingSessionInputBuffer(SessionInputBuffer paramSessionInputBuffer, Wire paramWire)
  {
    this(paramSessionInputBuffer, paramWire, null);
  }

  public LoggingSessionInputBuffer(SessionInputBuffer paramSessionInputBuffer, Wire paramWire, String paramString)
  {
    this.in = paramSessionInputBuffer;
    if ((paramSessionInputBuffer instanceof EofSensor))
      paramSessionInputBuffer = (EofSensor)paramSessionInputBuffer;
    else
      paramSessionInputBuffer = null;
    this.eofSensor = paramSessionInputBuffer;
    this.wire = paramWire;
    if (paramString == null)
      paramString = "ASCII";
    this.charset = paramString;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.in.getMetrics();
  }

  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    return this.in.isDataAvailable(paramInt);
  }

  public boolean isEof()
  {
    if (this.eofSensor != null)
      return this.eofSensor.isEof();
    return false;
  }

  public int read()
    throws IOException
  {
    int i = this.in.read();
    if ((this.wire.enabled()) && (i != -1))
      this.wire.input(i);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = this.in.read(paramArrayOfByte);
    if ((this.wire.enabled()) && (i > 0))
      this.wire.input(paramArrayOfByte, 0, i);
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt2 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    if ((this.wire.enabled()) && (paramInt2 > 0))
      this.wire.input(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = this.in.readLine(paramCharArrayBuffer);
    if ((this.wire.enabled()) && (i >= 0))
    {
      int j = paramCharArrayBuffer.length();
      paramCharArrayBuffer = new String(paramCharArrayBuffer.buffer(), j - i, i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramCharArrayBuffer);
      localStringBuilder.append("\r\n");
      paramCharArrayBuffer = localStringBuilder.toString();
      this.wire.input(paramCharArrayBuffer.getBytes(this.charset));
    }
    return i;
  }

  public String readLine()
    throws IOException
  {
    String str = this.in.readLine();
    if ((this.wire.enabled()) && (str != null))
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append("\r\n");
      localObject = ((StringBuilder)localObject).toString();
      this.wire.input(((String)localObject).getBytes(this.charset));
    }
    return str;
  }
}