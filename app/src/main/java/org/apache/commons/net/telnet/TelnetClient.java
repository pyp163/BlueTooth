package org.apache.commons.net.telnet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TelnetClient extends Telnet
{
  private InputStream __input = null;
  private OutputStream __output = null;
  private TelnetInputListener inputListener;
  protected boolean readerThread = true;

  public TelnetClient()
  {
    super("VT100");
  }

  public TelnetClient(String paramString)
  {
    super(paramString);
  }

  void _closeOutputStream()
    throws IOException
  {
    this._output_.close();
  }

  protected void _connectAction_()
    throws IOException
  {
    super._connectAction_();
    TelnetInputStream localTelnetInputStream = new TelnetInputStream(this._input_, this, this.readerThread);
    if (this.readerThread)
      localTelnetInputStream._start();
    this.__input = new BufferedInputStream(localTelnetInputStream);
    this.__output = new TelnetOutputStream(this);
  }

  void _flushOutputStream()
    throws IOException
  {
    this._output_.flush();
  }

  public void addOptionHandler(TelnetOptionHandler paramTelnetOptionHandler)
    throws InvalidTelnetOptionException, IOException
  {
    super.addOptionHandler(paramTelnetOptionHandler);
  }

  public void deleteOptionHandler(int paramInt)
    throws InvalidTelnetOptionException, IOException
  {
    super.deleteOptionHandler(paramInt);
  }

  public void disconnect()
    throws IOException
  {
    if (this.__input != null)
      this.__input.close();
    if (this.__output != null)
      this.__output.close();
    super.disconnect();
  }

  public InputStream getInputStream()
  {
    return this.__input;
  }

  public boolean getLocalOptionState(int paramInt)
  {
    return (_stateIsWill(paramInt)) && (_requestedWill(paramInt));
  }

  public OutputStream getOutputStream()
  {
    return this.__output;
  }

  public boolean getReaderThread()
  {
    return this.readerThread;
  }

  public boolean getRemoteOptionState(int paramInt)
  {
    return (_stateIsDo(paramInt)) && (_requestedDo(paramInt));
  }

  void notifyInputListener()
  {
    try
    {
      TelnetInputListener localTelnetInputListener = this.inputListener;
      if (localTelnetInputListener != null)
        localTelnetInputListener.telnetInputAvailable();
      return;
    }
    finally
    {
    }
  }

  public void registerInputListener(TelnetInputListener paramTelnetInputListener)
  {
    try
    {
      this.inputListener = paramTelnetInputListener;
      return;
    }
    finally
    {
      paramTelnetInputListener = finally;
    }
    throw paramTelnetInputListener;
  }

  public void registerNotifHandler(TelnetNotificationHandler paramTelnetNotificationHandler)
  {
    super.registerNotifHandler(paramTelnetNotificationHandler);
  }

  public void registerSpyStream(OutputStream paramOutputStream)
  {
    super._registerSpyStream(paramOutputStream);
  }

  public boolean sendAYT(long paramLong)
    throws IOException, IllegalArgumentException, InterruptedException
  {
    return _sendAYT(paramLong);
  }

  public void sendCommand(byte paramByte)
    throws IOException, IllegalArgumentException
  {
    _sendCommand(paramByte);
  }

  public void sendSubnegotiation(int[] paramArrayOfInt)
    throws IOException, IllegalArgumentException
  {
    if (paramArrayOfInt.length < 1)
      throw new IllegalArgumentException("zero length message");
    _sendSubnegotiation(paramArrayOfInt);
  }

  public void setReaderThread(boolean paramBoolean)
  {
    this.readerThread = paramBoolean;
  }

  public void stopSpyStream()
  {
    super._stopSpyStream();
  }

  public void unregisterInputListener()
  {
    try
    {
      this.inputListener = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void unregisterNotifHandler()
  {
    super.unregisterNotifHandler();
  }
}