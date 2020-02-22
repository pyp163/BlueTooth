package org.apache.commons.net;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public abstract class DatagramSocketClient
{
  private static final DatagramSocketFactory __DEFAULT_SOCKET_FACTORY = new DefaultDatagramSocketFactory();
  protected boolean _isOpen_ = false;
  protected DatagramSocketFactory _socketFactory_ = __DEFAULT_SOCKET_FACTORY;
  protected DatagramSocket _socket_ = null;
  protected int _timeout_ = 0;

  public void close()
  {
    if (this._socket_ != null)
      this._socket_.close();
    this._socket_ = null;
    this._isOpen_ = false;
  }

  public int getDefaultTimeout()
  {
    return this._timeout_;
  }

  public InetAddress getLocalAddress()
  {
    return this._socket_.getLocalAddress();
  }

  public int getLocalPort()
  {
    return this._socket_.getLocalPort();
  }

  public int getSoTimeout()
    throws SocketException
  {
    return this._socket_.getSoTimeout();
  }

  public boolean isOpen()
  {
    return this._isOpen_;
  }

  public void open()
    throws SocketException
  {
    this._socket_ = this._socketFactory_.createDatagramSocket();
    this._socket_.setSoTimeout(this._timeout_);
    this._isOpen_ = true;
  }

  public void open(int paramInt)
    throws SocketException
  {
    this._socket_ = this._socketFactory_.createDatagramSocket(paramInt);
    this._socket_.setSoTimeout(this._timeout_);
    this._isOpen_ = true;
  }

  public void open(int paramInt, InetAddress paramInetAddress)
    throws SocketException
  {
    this._socket_ = this._socketFactory_.createDatagramSocket(paramInt, paramInetAddress);
    this._socket_.setSoTimeout(this._timeout_);
    this._isOpen_ = true;
  }

  public void setDatagramSocketFactory(DatagramSocketFactory paramDatagramSocketFactory)
  {
    if (paramDatagramSocketFactory == null)
    {
      this._socketFactory_ = __DEFAULT_SOCKET_FACTORY;
      return;
    }
    this._socketFactory_ = paramDatagramSocketFactory;
  }

  public void setDefaultTimeout(int paramInt)
  {
    this._timeout_ = paramInt;
  }

  public void setSoTimeout(int paramInt)
    throws SocketException
  {
    this._socket_.setSoTimeout(paramInt);
  }
}