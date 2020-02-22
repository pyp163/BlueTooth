package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public abstract class TFTPPacket
{
  public static final int ACKNOWLEDGEMENT = 4;
  public static final int DATA = 3;
  public static final int ERROR = 5;
  static final int MIN_PACKET_SIZE = 4;
  public static final int READ_REQUEST = 1;
  public static final int SEGMENT_SIZE = 512;
  public static final int WRITE_REQUEST = 2;
  InetAddress _address;
  int _port;
  int _type;

  TFTPPacket(int paramInt1, InetAddress paramInetAddress, int paramInt2)
  {
    this._type = paramInt1;
    this._address = paramInetAddress;
    this._port = paramInt2;
  }

  public static final TFTPPacket newTFTPPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    if (paramDatagramPacket.getLength() < 4)
      throw new TFTPPacketException("Bad packet. Datagram data length is too short.");
    switch (paramDatagramPacket.getData()[1])
    {
    default:
      throw new TFTPPacketException("Bad packet.  Invalid TFTP operator code.");
    case 5:
      return new TFTPErrorPacket(paramDatagramPacket);
    case 4:
      return new TFTPAckPacket(paramDatagramPacket);
    case 3:
      return new TFTPDataPacket(paramDatagramPacket);
    case 2:
      return new TFTPWriteRequestPacket(paramDatagramPacket);
    case 1:
    }
    return new TFTPReadRequestPacket(paramDatagramPacket);
  }

  abstract DatagramPacket _newDatagram(DatagramPacket paramDatagramPacket, byte[] paramArrayOfByte);

  public final InetAddress getAddress()
  {
    return this._address;
  }

  public final int getPort()
  {
    return this._port;
  }

  public final int getType()
  {
    return this._type;
  }

  public abstract DatagramPacket newDatagram();

  public final void setAddress(InetAddress paramInetAddress)
  {
    this._address = paramInetAddress;
  }

  public final void setPort(int paramInt)
  {
    this._port = paramInt;
  }
}