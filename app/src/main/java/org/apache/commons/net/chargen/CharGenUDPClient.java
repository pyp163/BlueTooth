package org.apache.commons.net.chargen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

public final class CharGenUDPClient extends DatagramSocketClient
{
  public static final int CHARGEN_PORT = 19;
  public static final int DEFAULT_PORT = 19;
  public static final int NETSTAT_PORT = 15;
  public static final int QUOTE_OF_DAY_PORT = 17;
  public static final int SYSTAT_PORT = 11;
  private final byte[] __receiveData = new byte[512];
  private final DatagramPacket __receivePacket = new DatagramPacket(this.__receiveData, this.__receiveData.length);
  private final DatagramPacket __sendPacket = new DatagramPacket(new byte[0], 0);

  public byte[] receive()
    throws IOException
  {
    this._socket_.receive(this.__receivePacket);
    int i = this.__receivePacket.getLength();
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(this.__receiveData, 0, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  public void send(InetAddress paramInetAddress)
    throws IOException
  {
    send(paramInetAddress, 19);
  }

  public void send(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    this.__sendPacket.setAddress(paramInetAddress);
    this.__sendPacket.setPort(paramInt);
    this._socket_.send(this.__sendPacket);
  }
}