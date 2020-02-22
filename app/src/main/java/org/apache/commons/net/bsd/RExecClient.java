package org.apache.commons.net.bsd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ServerSocketFactory;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.SocketInputStream;

public class RExecClient extends SocketClient
{
  public static final int DEFAULT_PORT = 512;
  private boolean __remoteVerificationEnabled;
  protected InputStream _errorStream_ = null;

  public RExecClient()
  {
    setDefaultPort(512);
  }

  InputStream _createErrorStream()
    throws IOException
  {
    Object localObject = this._serverSocketFactory_.createServerSocket(0, 1, getLocalAddress());
    this._output_.write(Integer.toString(((ServerSocket)localObject).getLocalPort()).getBytes());
    this._output_.write(0);
    this._output_.flush();
    Socket localSocket = ((ServerSocket)localObject).accept();
    ((ServerSocket)localObject).close();
    if ((this.__remoteVerificationEnabled) && (!verifyRemote(localSocket)))
    {
      localSocket.close();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Security violation: unexpected connection attempt by ");
      ((StringBuilder)localObject).append(localSocket.getInetAddress().getHostAddress());
      throw new IOException(((StringBuilder)localObject).toString());
    }
    return new SocketInputStream(localSocket, localSocket.getInputStream());
  }

  public void disconnect()
    throws IOException
  {
    if (this._errorStream_ != null)
      this._errorStream_.close();
    this._errorStream_ = null;
    super.disconnect();
  }

  public InputStream getErrorStream()
  {
    return this._errorStream_;
  }

  public InputStream getInputStream()
  {
    return this._input_;
  }

  public OutputStream getOutputStream()
  {
    return this._output_;
  }

  public final boolean isRemoteVerificationEnabled()
  {
    return this.__remoteVerificationEnabled;
  }

  public void rexec(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    rexec(paramString1, paramString2, paramString3, false);
  }

  public void rexec(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      this._errorStream_ = _createErrorStream();
    else
      this._output_.write(0);
    this._output_.write(paramString1.getBytes());
    this._output_.write(0);
    this._output_.write(paramString2.getBytes());
    this._output_.write(0);
    this._output_.write(paramString3.getBytes());
    this._output_.write(0);
    this._output_.flush();
    int i = this._input_.read();
    if (i > 0)
    {
      paramString1 = new StringBuilder();
      while (true)
      {
        i = this._input_.read();
        if ((i == -1) || (i == 10))
          break;
        paramString1.append((char)i);
      }
      throw new IOException(paramString1.toString());
    }
    if (i < 0)
      throw new IOException("Server closed connection.");
  }

  public final void setRemoteVerificationEnabled(boolean paramBoolean)
  {
    this.__remoteVerificationEnabled = paramBoolean;
  }
}