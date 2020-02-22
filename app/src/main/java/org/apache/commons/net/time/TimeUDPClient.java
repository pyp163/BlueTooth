package org.apache.commons.net.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import org.apache.commons.net.DatagramSocketClient;

public final class TimeUDPClient extends DatagramSocketClient
{
  public static final int DEFAULT_PORT = 37;
  public static final long SECONDS_1900_TO_1970 = 2208988800L;
  private final byte[] __dummyData = new byte[1];
  private final byte[] __timeData = new byte[4];

  public Date getDate(InetAddress paramInetAddress)
    throws IOException
  {
    return new Date((getTime(paramInetAddress, 37) - 2208988800L) * 1000L);
  }

  public Date getDate(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    return new Date((getTime(paramInetAddress, paramInt) - 2208988800L) * 1000L);
  }

  public long getTime(InetAddress paramInetAddress)
    throws IOException
  {
    return getTime(paramInetAddress, 37);
  }

  public long getTime(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    paramInetAddress = new DatagramPacket(this.__dummyData, this.__dummyData.length, paramInetAddress, paramInt);
    DatagramPacket localDatagramPacket = new DatagramPacket(this.__timeData, this.__timeData.length);
    this._socket_.send(paramInetAddress);
    this._socket_.receive(localDatagramPacket);
    return (this.__timeData[0] & 0xFF) << 24 & 0xFFFFFFFF | 0L | (this.__timeData[1] & 0xFF) << 16 & 0xFFFFFFFF | (this.__timeData[2] & 0xFF) << 8 & 0xFFFFFFFF | 0xFFFFFFFF & (this.__timeData[3] & 0xFF);
  }
}