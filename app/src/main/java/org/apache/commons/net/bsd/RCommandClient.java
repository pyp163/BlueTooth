package org.apache.commons.net.bsd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import org.apache.commons.net.io.SocketInputStream;

public class RCommandClient extends RExecClient
{
  public static final int DEFAULT_PORT = 514;
  public static final int MAX_CLIENT_PORT = 1023;
  public static final int MIN_CLIENT_PORT = 512;

  public RCommandClient()
  {
    setDefaultPort(514);
  }

  InputStream _createErrorStream()
    throws IOException
  {
    int i = 1023;
    while (true)
    {
      if (i >= 512);
      try
      {
        Object localObject = this._serverSocketFactory_.createServerSocket(i, 1, getLocalAddress());
        break label37;
        label28: i -= 1;
        continue;
        localObject = null;
        label37: if (localObject == null)
          throw new BindException("All ports in use.");
        this._output_.write(Integer.toString(((ServerSocket)localObject).getLocalPort()).getBytes());
        this._output_.write(0);
        this._output_.flush();
        Socket localSocket = ((ServerSocket)localObject).accept();
        ((ServerSocket)localObject).close();
        if ((isRemoteVerificationEnabled()) && (!verifyRemote(localSocket)))
        {
          localSocket.close();
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Security violation: unexpected connection attempt by ");
          ((StringBuilder)localObject).append(localSocket.getInetAddress().getHostAddress());
          throw new IOException(((StringBuilder)localObject).toString());
        }
        return new SocketInputStream(localSocket, localSocket.getInputStream());
      }
      catch (SocketException localSocketException)
      {
        break label28;
      }
    }
  }

  public void connect(String paramString, int paramInt)
    throws SocketException, IOException, UnknownHostException
  {
    connect(InetAddress.getByName(paramString), paramInt, InetAddress.getLocalHost());
  }

  public void connect(String paramString, int paramInt, InetAddress paramInetAddress)
    throws SocketException, IOException
  {
    connect(InetAddress.getByName(paramString), paramInt, paramInetAddress);
  }

  public void connect(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws SocketException, IOException, IllegalArgumentException, UnknownHostException
  {
    if ((paramInt2 >= 512) && (paramInt2 <= 1023))
    {
      super.connect(paramString, paramInt1, paramInetAddress, paramInt2);
      return;
    }
    paramString = new StringBuilder();
    paramString.append("Invalid port number ");
    paramString.append(paramInt2);
    throw new IllegalArgumentException(paramString.toString());
  }

  public void connect(InetAddress paramInetAddress, int paramInt)
    throws SocketException, IOException
  {
    connect(paramInetAddress, paramInt, InetAddress.getLocalHost());
  }

  public void connect(InetAddress paramInetAddress1, int paramInt, InetAddress paramInetAddress2)
    throws SocketException, BindException, IOException
  {
    int i = 1023;
    while (true)
    {
      if (i >= 512);
      try
      {
        this._socket_ = this._socketFactory_.createSocket(paramInetAddress1, paramInt, paramInetAddress2, i);
        break label41;
        label32: i -= 1;
        continue;
        label41: if (i < 512)
          throw new BindException("All ports in use or insufficient permssion.");
        _connectAction_();
        return;
      }
      catch (BindException|SocketException localBindException)
      {
        break label32;
      }
    }
  }

  public void connect(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws SocketException, IOException, IllegalArgumentException
  {
    if ((paramInt2 >= 512) && (paramInt2 <= 1023))
    {
      super.connect(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
      return;
    }
    paramInetAddress1 = new StringBuilder();
    paramInetAddress1.append("Invalid port number ");
    paramInetAddress1.append(paramInt2);
    throw new IllegalArgumentException(paramInetAddress1.toString());
  }

  public void rcommand(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    rcommand(paramString1, paramString2, paramString3, false);
  }

  public void rcommand(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws IOException
  {
    rexec(paramString1, paramString2, paramString3, paramBoolean);
  }
}