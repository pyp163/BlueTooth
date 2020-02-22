package org.apache.commons.net.discard;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

public class DiscardUDPClient extends DatagramSocketClient
{
  public static final int DEFAULT_PORT = 9;
  DatagramPacket _sendPacket = new DatagramPacket(new byte[0], 0);

  public void send(byte[] paramArrayOfByte, int paramInt, InetAddress paramInetAddress)
    throws IOException
  {
    send(paramArrayOfByte, paramInt, paramInetAddress, 9);
  }

  public void send(byte[] paramArrayOfByte, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
    this._sendPacket.setData(paramArrayOfByte);
    this._sendPacket.setLength(paramInt1);
    this._sendPacket.setAddress(paramInetAddress);
    this._sendPacket.setPort(paramInt2);
    this._socket_.send(this._sendPacket);
  }

  public void send(byte[] paramArrayOfByte, InetAddress paramInetAddress)
    throws IOException
  {
    send(paramArrayOfByte, paramArrayOfByte.length, paramInetAddress, 9);
  }
}