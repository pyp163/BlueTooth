package org.apache.commons.net.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.net.discard.DiscardUDPClient;

public final class EchoUDPClient extends DiscardUDPClient
{
  public static final int DEFAULT_PORT = 7;
  private final DatagramPacket __receivePacket = new DatagramPacket(new byte[0], 0);

  public int receive(byte[] paramArrayOfByte)
    throws IOException
  {
    return receive(paramArrayOfByte, paramArrayOfByte.length);
  }

  public int receive(byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    this.__receivePacket.setData(paramArrayOfByte);
    this.__receivePacket.setLength(paramInt);
    this._socket_.receive(this.__receivePacket);
    return this.__receivePacket.getLength();
  }

  public void send(byte[] paramArrayOfByte, int paramInt, InetAddress paramInetAddress)
    throws IOException
  {
    send(paramArrayOfByte, paramInt, paramInetAddress, 7);
  }

  public void send(byte[] paramArrayOfByte, InetAddress paramInetAddress)
    throws IOException
  {
    send(paramArrayOfByte, paramArrayOfByte.length, paramInetAddress, 7);
  }
}