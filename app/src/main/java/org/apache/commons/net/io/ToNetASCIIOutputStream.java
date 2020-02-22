package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class ToNetASCIIOutputStream extends FilterOutputStream
{
  private boolean __lastWasCR = false;

  public ToNetASCIIOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }

  public void write(int paramInt)
    throws IOException
  {
    if ((paramInt == 10) || (paramInt == 13));
    try
    {
      this.__lastWasCR = true;
      this.out.write(13);
      return;
      if (!this.__lastWasCR)
        this.out.write(13);
      this.__lastWasCR = false;
      this.out.write(paramInt);
      return;
    }
    finally
    {
    }
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      write(paramArrayOfByte, 0, paramArrayOfByte.length);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
    }
    throw paramArrayOfByte;
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    while (true)
    {
      if (paramInt2 > 0);
      try
      {
        write(paramArrayOfByte[paramInt1]);
        paramInt1 += 1;
        paramInt2 -= 1;
      }
      finally
      {
      }
    }
  }
}