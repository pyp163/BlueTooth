package org.apache.commons.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

public class DefaultSocketFactory extends SocketFactory
{
  public ServerSocket createServerSocket(int paramInt)
    throws IOException
  {
    return new ServerSocket(paramInt);
  }

  public ServerSocket createServerSocket(int paramInt1, int paramInt2)
    throws IOException
  {
    return new ServerSocket(paramInt1, paramInt2);
  }

  public ServerSocket createServerSocket(int paramInt1, int paramInt2, InetAddress paramInetAddress)
    throws IOException
  {
    return new ServerSocket(paramInt1, paramInt2, paramInetAddress);
  }

  public Socket createSocket(String paramString, int paramInt)
    throws UnknownHostException, IOException
  {
    return new Socket(paramString, paramInt);
  }

  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws UnknownHostException, IOException
  {
    return new Socket(paramString, paramInt1, paramInetAddress, paramInt2);
  }

  public Socket createSocket(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    return new Socket(paramInetAddress, paramInt);
  }

  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws IOException
  {
    return new Socket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
  }
}