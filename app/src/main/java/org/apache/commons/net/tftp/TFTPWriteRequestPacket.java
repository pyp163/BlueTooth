package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPWriteRequestPacket extends TFTPRequestPacket
{
  TFTPWriteRequestPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(2, paramDatagramPacket);
  }

  public TFTPWriteRequestPacket(InetAddress paramInetAddress, int paramInt1, String paramString, int paramInt2)
  {
    super(paramInetAddress, paramInt1, 2, paramString, paramInt2);
  }
}