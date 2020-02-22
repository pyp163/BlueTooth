package org.apache.commons.net;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DefaultDatagramSocketFactory
  implements DatagramSocketFactory
{
  public DatagramSocket createDatagramSocket()
    throws SocketException
  {
    return new DatagramSocket();
  }

  public DatagramSocket createDatagramSocket(int paramInt)
    throws SocketException
  {
    return new DatagramSocket(paramInt);
  }

  public DatagramSocket createDatagramSocket(int paramInt, InetAddress paramInetAddress)
    throws SocketException
  {
    return new DatagramSocket(paramInt, paramInetAddress);
  }
}