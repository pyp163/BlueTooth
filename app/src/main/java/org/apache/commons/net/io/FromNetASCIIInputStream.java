package org.apache.commons.net.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public final class FromNetASCIIInputStream extends PushbackInputStream
{
  static final String _lineSeparator = System.getProperty("line.separator");
  static final byte[] _lineSeparatorBytes = _lineSeparator.getBytes();
  static final boolean _noConversionRequired = _lineSeparator.equals("\r\n");
  private int __length = 0;

  public FromNetASCIIInputStream(InputStream paramInputStream)
  {
    super(paramInputStream, _lineSeparatorBytes.length + 1);
  }

  private int __read()
    throws IOException
  {
    int i = super.read();
    if (i == 13)
    {
      i = super.read();
      if (i == 10)
      {
        unread(_lineSeparatorBytes);
        i = super.read();
        this.__length -= 1;
        return i;
      }
      if (i != -1)
        unread(i);
      return 13;
    }
    return i;
  }

  public static final boolean isConversionRequired()
  {
    return _noConversionRequired ^ true;
  }

  public int available()
    throws IOException
  {
    if (this.in == null)
      throw new IOException("Stream closed");
    return this.buf.length - this.pos + this.in.available();
  }

  public int read()
    throws IOException
  {
    if (_noConversionRequired)
      return super.read();
    return __read();
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_noConversionRequired)
      return super.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt2 < 1)
      return 0;
    int j = available();
    int i = paramInt2;
    if (paramInt2 > j)
      i = j;
    this.__length = i;
    if (this.__length < 1)
      this.__length = 1;
    paramInt2 = __read();
    if (paramInt2 == -1)
      return -1;
    for (i = paramInt1; ; i = j)
    {
      j = i + 1;
      paramArrayOfByte[i] = ((byte)paramInt2);
      paramInt2 = this.__length - 1;
      this.__length = paramInt2;
      if (paramInt2 <= 0)
        break;
      paramInt2 = __read();
      if (paramInt2 == -1)
        break;
    }
    return j - paramInt1;
  }
}