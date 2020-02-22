package org.apache.commons.net.daytime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

public final class DaytimeUDPClient extends DatagramSocketClient
{
  public static final int DEFAULT_PORT = 13;
  private final byte[] __dummyData = new byte[1];
  private final byte[] __timeData = new byte[256];

  public String getTime(InetAddress paramInetAddress)
    throws IOException
  {
    return getTime(paramInetAddress, 13);
  }

  public String getTime(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    paramInetAddress = new DatagramPacket(this.__dummyData, this.__dummyData.length, paramInetAddress, paramInt);
    DatagramPacket localDatagramPacket = new DatagramPacket(this.__timeData, this.__timeData.length);
    this._socket_.send(paramInetAddress);
    this._socket_.receive(localDatagramPacket);
    return new String(localDatagramPacket.getData(), 0, localDatagramPacket.getLength());
  }
}