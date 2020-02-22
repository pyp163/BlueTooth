package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPReadRequestPacket extends TFTPRequestPacket
{
  TFTPReadRequestPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(1, paramDatagramPacket);
  }

  public TFTPReadRequestPacket(InetAddress paramInetAddress, int paramInt1, String paramString, int paramInt2)
  {
    super(paramInetAddress, paramInt1, 1, paramString, paramInt2);
  }
}