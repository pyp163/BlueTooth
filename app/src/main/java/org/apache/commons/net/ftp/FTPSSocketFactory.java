package org.apache.commons.net.ftp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class FTPSSocketFactory extends SocketFactory
{
  private final SSLContext context;

  public FTPSSocketFactory(SSLContext paramSSLContext)
  {
    this.context = paramSSLContext;
  }

  @Deprecated
  public ServerSocket createServerSocket(int paramInt)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt));
  }

  @Deprecated
  public ServerSocket createServerSocket(int paramInt1, int paramInt2)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt1, paramInt2));
  }

  @Deprecated
  public ServerSocket createServerSocket(int paramInt1, int paramInt2, InetAddress paramInetAddress)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt1, paramInt2, paramInetAddress));
  }

  public Socket createSocket()
    throws IOException
  {
    return this.context.getSocketFactory().createSocket();
  }

  public Socket createSocket(String paramString, int paramInt)
    throws UnknownHostException, IOException
  {
    return this.context.getSocketFactory().createSocket(paramString, paramInt);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws UnknownHostException, IOException
  {
    return this.context.getSocketFactory().createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
  }

  public Socket createSocket(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    return this.context.getSocketFactory().createSocket(paramInetAddress, paramInt);
  }

  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws IOException
  {
    return this.context.getSocketFactory().createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
  }

  @Deprecated
  public ServerSocket init(ServerSocket paramServerSocket)
    throws IOException
  {
    ((SSLServerSocket)paramServerSocket).setUseClientMode(true);
    return paramServerSocket;
  }
}