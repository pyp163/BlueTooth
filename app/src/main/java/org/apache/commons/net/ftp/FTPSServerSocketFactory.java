package org.apache.commons.net.ftp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class FTPSServerSocketFactory extends ServerSocketFactory
{
  private final SSLContext context;

  public FTPSServerSocketFactory(SSLContext paramSSLContext)
  {
    this.context = paramSSLContext;
  }

  public ServerSocket createServerSocket()
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket());
  }

  public ServerSocket createServerSocket(int paramInt)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt));
  }

  public ServerSocket createServerSocket(int paramInt1, int paramInt2)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt1, paramInt2));
  }

  public ServerSocket createServerSocket(int paramInt1, int paramInt2, InetAddress paramInetAddress)
    throws IOException
  {
    return init(this.context.getServerSocketFactory().createServerSocket(paramInt1, paramInt2, paramInetAddress));
  }

  public ServerSocket init(ServerSocket paramServerSocket)
  {
    ((SSLServerSocket)paramServerSocket).setUseClientMode(true);
    return paramServerSocket;
  }
}