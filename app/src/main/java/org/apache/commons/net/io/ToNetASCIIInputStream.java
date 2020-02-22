package org.apache.commons.net.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ToNetASCIIInputStream extends FilterInputStream
{
  private static final int __LAST_WAS_CR = 1;
  private static final int __LAST_WAS_NL = 2;
  private static final int __NOTHING_SPECIAL = 0;
  private int __status = 0;

  public ToNetASCIIInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public int available()
    throws IOException
  {
    int i = this.in.available();
    if (this.__status == 2)
      return i + 1;
    return i;
  }

  public boolean markSupported()
  {
    return false;
  }

  public int read()
    throws IOException
  {
    if (this.__status == 2)
    {
      this.__status = 0;
      return 10;
    }
    int i = this.in.read();
    if (i != 10)
    {
      if (i == 13)
      {
        this.__status = 1;
        return 13;
      }
    }
    else if (this.__status != 1)
    {
      this.__status = 2;
      return 13;
    }
    this.__status = 0;
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
    if (paramInt2 < 1)
      return 0;
    int j = available();
    int i = paramInt2;
    if (paramInt2 > j)
      i = j;
    paramInt2 = i;
    if (i < 1)
      paramInt2 = 1;
    int k = read();
    if (k == -1)
      return -1;
    j = paramInt1;
    i = paramInt2;
    paramInt2 = k;
    while (true)
    {
      k = j + 1;
      paramArrayOfByte[j] = ((byte)paramInt2);
      i -= 1;
      if (i <= 0)
        break;
      paramInt2 = read();
      if (paramInt2 == -1)
        break;
      j = k;
    }
    return k - paramInt1;
  }
}