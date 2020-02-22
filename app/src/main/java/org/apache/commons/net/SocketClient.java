package org.apache.commons.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

public abstract class SocketClient
{
  private static final int DEFAULT_CONNECT_TIMEOUT = 0;
  public static final String NETASCII_EOL = "\r\n";
  private static final ServerSocketFactory __DEFAULT_SERVER_SOCKET_FACTORY = ServerSocketFactory.getDefault();
  private static final SocketFactory __DEFAULT_SOCKET_FACTORY = SocketFactory.getDefault();
  private ProtocolCommandSupport __commandSupport;
  protected int _defaultPort_ = 0;
  protected InputStream _input_ = null;
  protected OutputStream _output_ = null;
  protected ServerSocketFactory _serverSocketFactory_ = __DEFAULT_SERVER_SOCKET_FACTORY;
  protected SocketFactory _socketFactory_ = __DEFAULT_SOCKET_FACTORY;
  protected Socket _socket_ = null;
  protected int _timeout_ = 0;
  protected int connectTimeout = 0;
  private int receiveBufferSize = -1;
  private int sendBufferSize = -1;

  private void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
    }
  }

  private void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null);
    try
    {
      paramSocket.close();
      return;
    }
    catch (IOException paramSocket)
    {
    }
  }

  protected void _connectAction_()
    throws IOException
  {
    this._socket_.setSoTimeout(this._timeout_);
    this._input_ = this._socket_.getInputStream();
    this._output_ = this._socket_.getOutputStream();
  }

  public void addProtocolCommandListener(ProtocolCommandListener paramProtocolCommandListener)
  {
    getCommandSupport().addProtocolCommandListener(paramProtocolCommandListener);
  }

  public void connect(String paramString)
    throws SocketException, IOException
  {
    connect(paramString, this._defaultPort_);
  }

  public void connect(String paramString, int paramInt)
    throws SocketException, IOException
  {
    connect(InetAddress.getByName(paramString), paramInt);
  }

  public void connect(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws SocketException, IOException
  {
    connect(InetAddress.getByName(paramString), paramInt1, paramInetAddress, paramInt2);
  }

  public void connect(InetAddress paramInetAddress)
    throws SocketException, IOException
  {
    connect(paramInetAddress, this._defaultPort_);
  }

  public void connect(InetAddress paramInetAddress, int paramInt)
    throws SocketException, IOException
  {
    this._socket_ = this._socketFactory_.createSocket();
    if (this.receiveBufferSize != -1)
      this._socket_.setReceiveBufferSize(this.receiveBufferSize);
    if (this.sendBufferSize != -1)
      this._socket_.setSendBufferSize(this.sendBufferSize);
    this._socket_.connect(new InetSocketAddress(paramInetAddress, paramInt), this.connectTimeout);
    _connectAction_();
  }

  public void connect(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws SocketException, IOException
  {
    this._socket_ = this._socketFactory_.createSocket();
    if (this.receiveBufferSize != -1)
      this._socket_.setReceiveBufferSize(this.receiveBufferSize);
    if (this.sendBufferSize != -1)
      this._socket_.setSendBufferSize(this.sendBufferSize);
    this._socket_.bind(new InetSocketAddress(paramInetAddress2, paramInt2));
    this._socket_.connect(new InetSocketAddress(paramInetAddress1, paramInt1), this.connectTimeout);
    _connectAction_();
  }

  protected void createCommandSupport()
  {
    this.__commandSupport = new ProtocolCommandSupport(this);
  }

  public void disconnect()
    throws IOException
  {
    closeQuietly(this._socket_);
    closeQuietly(this._input_);
    closeQuietly(this._output_);
    this._socket_ = null;
    this._input_ = null;
    this._output_ = null;
  }

  protected void fireCommandSent(String paramString1, String paramString2)
  {
    if (getCommandSupport().getListenerCount() > 0)
      getCommandSupport().fireCommandSent(paramString1, paramString2);
  }

  protected void fireReplyReceived(int paramInt, String paramString)
  {
    if (getCommandSupport().getListenerCount() > 0)
      getCommandSupport().fireReplyReceived(paramInt, paramString);
  }

  protected ProtocolCommandSupport getCommandSupport()
  {
    return this.__commandSupport;
  }

  public int getConnectTimeout()
  {
    return this.connectTimeout;
  }

  public int getDefaultPort()
  {
    return this._defaultPort_;
  }

  public int getDefaultTimeout()
  {
    return this._timeout_;
  }

  public boolean getKeepAlive()
    throws SocketException
  {
    return this._socket_.getKeepAlive();
  }

  public InetAddress getLocalAddress()
  {
    return this._socket_.getLocalAddress();
  }

  public int getLocalPort()
  {
    return this._socket_.getLocalPort();
  }

  protected int getReceiveBufferSize()
  {
    return this.receiveBufferSize;
  }

  public InetAddress getRemoteAddress()
  {
    return this._socket_.getInetAddress();
  }

  public int getRemotePort()
  {
    return this._socket_.getPort();
  }

  protected int getSendBufferSize()
  {
    return this.sendBufferSize;
  }

  public ServerSocketFactory getServerSocketFactory()
  {
    return this._serverSocketFactory_;
  }

  public int getSoLinger()
    throws SocketException
  {
    return this._socket_.getSoLinger();
  }

  public int getSoTimeout()
    throws SocketException
  {
    return this._socket_.getSoTimeout();
  }

  public boolean getTcpNoDelay()
    throws SocketException
  {
    return this._socket_.getTcpNoDelay();
  }

  public boolean isAvailable()
  {
    if (isConnected());
    try
    {
      if (this._socket_.getInetAddress() == null)
        return false;
      if (this._socket_.getPort() == 0)
        return false;
      if (this._socket_.getRemoteSocketAddress() == null)
        return false;
      if (this._socket_.isClosed())
        return false;
      if (this._socket_.isInputShutdown())
        return false;
      if (this._socket_.isOutputShutdown())
        return false;
      this._socket_.getInputStream();
      this._socket_.getOutputStream();
      return true;
      return false;
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  public boolean isConnected()
  {
    if (this._socket_ == null)
      return false;
    return this._socket_.isConnected();
  }

  public void removeProtocolCommandListener(ProtocolCommandListener paramProtocolCommandListener)
  {
    getCommandSupport().removeProtocolCommandListener(paramProtocolCommandListener);
  }

  public void setConnectTimeout(int paramInt)
  {
    this.connectTimeout = paramInt;
  }

  public void setDefaultPort(int paramInt)
  {
    this._defaultPort_ = paramInt;
  }

  public void setDefaultTimeout(int paramInt)
  {
    this._timeout_ = paramInt;
  }

  public void setKeepAlive(boolean paramBoolean)
    throws SocketException
  {
    this._socket_.setKeepAlive(paramBoolean);
  }

  public void setReceiveBufferSize(int paramInt)
    throws SocketException
  {
    this.receiveBufferSize = paramInt;
  }

  public void setSendBufferSize(int paramInt)
    throws SocketException
  {
    this.sendBufferSize = paramInt;
  }

  public void setServerSocketFactory(ServerSocketFactory paramServerSocketFactory)
  {
    if (paramServerSocketFactory == null)
    {
      this._serverSocketFactory_ = __DEFAULT_SERVER_SOCKET_FACTORY;
      return;
    }
    this._serverSocketFactory_ = paramServerSocketFactory;
  }

  public void setSoLinger(boolean paramBoolean, int paramInt)
    throws SocketException
  {
    this._socket_.setSoLinger(paramBoolean, paramInt);
  }

  public void setSoTimeout(int paramInt)
    throws SocketException
  {
    this._socket_.setSoTimeout(paramInt);
  }

  public void setSocketFactory(SocketFactory paramSocketFactory)
  {
    if (paramSocketFactory == null)
    {
      this._socketFactory_ = __DEFAULT_SOCKET_FACTORY;
      return;
    }
    this._socketFactory_ = paramSocketFactory;
  }

  public void setTcpNoDelay(boolean paramBoolean)
    throws SocketException
  {
    this._socket_.setTcpNoDelay(paramBoolean);
  }

  public boolean verifyRemote(Socket paramSocket)
  {
    return paramSocket.getInetAddress().equals(getRemoteAddress());
  }
}