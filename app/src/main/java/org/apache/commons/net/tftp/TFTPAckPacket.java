package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPAckPacket extends TFTPPacket
{
  int _blockNumber;

  TFTPAckPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(4, paramDatagramPacket.getAddress(), paramDatagramPacket.getPort());
    paramDatagramPacket = paramDatagramPacket.getData();
    if (getType() != paramDatagramPacket[1])
      throw new TFTPPacketException("TFTP operator code does not match type.");
    int i = paramDatagramPacket[2];
    this._blockNumber = (paramDatagramPacket[3] & 0xFF | (i & 0xFF) << 8);
  }

  public TFTPAckPacket(InetAddress paramInetAddress, int paramInt1, int paramInt2)
  {
    super(4, paramInetAddress, paramInt1);
    this._blockNumber = paramInt2;
  }

  DatagramPacket _newDatagram(DatagramPacket paramDatagramPacket, byte[] paramArrayOfByte)
  {
    paramArrayOfByte[0] = 0;
    paramArrayOfByte[1] = ((byte)this._type);
    paramArrayOfByte[2] = ((byte)((this._blockNumber & 0xFFFF) >> 8));
    paramArrayOfByte[3] = ((byte)(this._blockNumber & 0xFF));
    paramDatagramPacket.setAddress(this._address);
    paramDatagramPacket.setPort(this._port);
    paramDatagramPacket.setData(paramArrayOfByte);
    paramDatagramPacket.setLength(4);
    return paramDatagramPacket;
  }

  public int getBlockNumber()
  {
    return this._blockNumber;
  }

  public DatagramPacket newDatagram()
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = ((byte)this._type);
    arrayOfByte[2] = ((byte)((this._blockNumber & 0xFFFF) >> 8));
    arrayOfByte[3] = ((byte)(this._blockNumber & 0xFF));
    return new DatagramPacket(arrayOfByte, arrayOfByte.length, this._address, this._port);
  }

  public void setBlockNumber(int paramInt)
  {
    this._blockNumber = paramInt;
  }
}