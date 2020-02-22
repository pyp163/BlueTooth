package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class FromNetASCIIOutputStream extends FilterOutputStream
{
  private boolean __lastWasCR = false;

  public FromNetASCIIOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }

  private void __write(int paramInt)
    throws IOException
  {
    if (paramInt != 10)
    {
      if (paramInt != 13)
      {
        if (this.__lastWasCR)
        {
          this.out.write(13);
          this.__lastWasCR = false;
        }
        this.out.write(paramInt);
        return;
      }
      this.__lastWasCR = true;
      return;
    }
    if (this.__lastWasCR)
    {
      this.out.write(FromNetASCIIInputStream._lineSeparatorBytes);
      this.__lastWasCR = false;
      return;
    }
    this.__lastWasCR = false;
    this.out.write(10);
  }

  public void close()
    throws IOException
  {
    try
    {
      if (FromNetASCIIInputStream._noConversionRequired)
      {
        super.close();
        return;
      }
      if (this.__lastWasCR)
        this.out.write(13);
      super.close();
      return;
    }
    finally
    {
    }
  }

  public void write(int paramInt)
    throws IOException
  {
    try
    {
      if (FromNetASCIIInputStream._noConversionRequired)
      {
        this.out.write(paramInt);
        return;
      }
      __write(paramInt);
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
    int i = paramInt1;
    int j = paramInt2;
    try
    {
      if (FromNetASCIIInputStream._noConversionRequired)
      {
        this.out.write(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      while (j > 0)
      {
        __write(paramArrayOfByte[i]);
        i += 1;
        j -= 1;
      }
      return;
    }
    finally
    {
    }
    throw paramArrayOfByte;
  }
}