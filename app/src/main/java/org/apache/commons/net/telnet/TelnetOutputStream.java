package org.apache.commons.net.telnet;

import java.io.IOException;
import java.io.OutputStream;

final class TelnetOutputStream extends OutputStream
{
  private final TelnetClient __client;
  private boolean __convertCRtoCRLF = true;
  private boolean __lastWasCR = false;

  TelnetOutputStream(TelnetClient paramTelnetClient)
  {
    this.__client = paramTelnetClient;
  }

  public void close()
    throws IOException
  {
    this.__client._closeOutputStream();
  }

  public void flush()
    throws IOException
  {
    this.__client._flushOutputStream();
  }

  public void write(int paramInt)
    throws IOException
  {
    TelnetClient localTelnetClient = this.__client;
    paramInt &= 255;
    try
    {
      if (this.__client._requestedWont(0))
      {
        if (this.__lastWasCR)
          if (this.__convertCRtoCRLF)
          {
            this.__client._sendByte(10);
            if (paramInt == 10)
              this.__lastWasCR = false;
          }
          else if (paramInt != 10)
          {
            this.__client._sendByte(0);
          }
        this.__lastWasCR = false;
        if (paramInt != 13)
        {
          if (paramInt != 255)
          {
            this.__client._sendByte(paramInt);
          }
          else
          {
            this.__client._sendByte(255);
            this.__client._sendByte(255);
          }
        }
        else
        {
          this.__client._sendByte(13);
          this.__lastWasCR = true;
        }
      }
      else if (paramInt == 255)
      {
        this.__client._sendByte(paramInt);
        this.__client._sendByte(255);
      }
      else
      {
        this.__client._sendByte(paramInt);
      }
      return;
    }
    finally
    {
    }
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    TelnetClient localTelnetClient = this.__client;
    while (true)
    {
      if (paramInt2 > 0);
      try
      {
        write(paramArrayOfByte[paramInt1]);
        paramInt1 += 1;
        paramInt2 -= 1;
        continue;
        return;
        label35: throw paramArrayOfByte;
      }
      finally
      {
        break label35;
      }
    }
  }
}