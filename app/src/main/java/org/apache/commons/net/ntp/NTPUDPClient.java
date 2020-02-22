package org.apache.commons.net.ntp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

public final class NTPUDPClient extends DatagramSocketClient
{
  public static final int DEFAULT_PORT = 123;
  private int _version = 3;

  public TimeInfo getTime(InetAddress paramInetAddress)
    throws IOException
  {
    return getTime(paramInetAddress, 123);
  }

  public TimeInfo getTime(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    if (!isOpen())
      open();
    NtpV3Impl localNtpV3Impl = new NtpV3Impl();
    localNtpV3Impl.setMode(3);
    localNtpV3Impl.setVersion(this._version);
    DatagramPacket localDatagramPacket1 = localNtpV3Impl.getDatagramPacket();
    localDatagramPacket1.setAddress(paramInetAddress);
    localDatagramPacket1.setPort(paramInt);
    paramInetAddress = new NtpV3Impl();
    DatagramPacket localDatagramPacket2 = paramInetAddress.getDatagramPacket();
    localNtpV3Impl.setTransmitTime(TimeStamp.getCurrentTime());
    this._socket_.send(localDatagramPacket1);
    this._socket_.receive(localDatagramPacket2);
    return new TimeInfo(paramInetAddress, System.currentTimeMillis(), false);
  }

  public int getVersion()
  {
    return this._version;
  }

  public void setVersion(int paramInt)
  {
    this._version = paramInt;
  }
}