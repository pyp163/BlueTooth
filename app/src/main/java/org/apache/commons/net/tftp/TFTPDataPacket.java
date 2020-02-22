package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPDataPacket extends TFTPPacket
{
  public static final int MAX_DATA_LENGTH = 512;
  public static final int MIN_DATA_LENGTH = 0;
  int _blockNumber;
  byte[] _data;
  int _length;
  int _offset;

  TFTPDataPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(3, paramDatagramPacket.getAddress(), paramDatagramPacket.getPort());
    this._data = paramDatagramPacket.getData();
    this._offset = 4;
    if (getType() != this._data[1])
      throw new TFTPPacketException("TFTP operator code does not match type.");
    this._blockNumber = ((this._data[2] & 0xFF) << 8 | this._data[3] & 0xFF);
    this._length = (paramDatagramPacket.getLength() - 4);
    if (this._length > 512)
      this._length = 512;
  }

  public TFTPDataPacket(InetAddress paramInetAddress, int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    this(paramInetAddress, paramInt1, paramInt2, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public TFTPDataPacket(InetAddress paramInetAddress, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4)
  {
    super(3, paramInetAddress, paramInt1);
    this._blockNumber = paramInt2;
    this._data = paramArrayOfByte;
    this._offset = paramInt3;
    if (paramInt4 > 512)
    {
      this._length = 512;
      return;
    }
    this._length = paramInt4;
  }

  DatagramPacket _newDatagram(DatagramPacket paramDatagramPacket, byte[] paramArrayOfByte)
  {
    paramArrayOfByte[0] = 0;
    paramArrayOfByte[1] = ((byte)this._type);
    paramArrayOfByte[2] = ((byte)((this._blockNumber & 0xFFFF) >> 8));
    paramArrayOfByte[3] = ((byte)(this._blockNumber & 0xFF));
    if (paramArrayOfByte != this._data)
      System.arraycopy(this._data, this._offset, paramArrayOfByte, 4, this._length);
    paramDatagramPacket.setAddress(this._address);
    paramDatagramPacket.setPort(this._port);
    paramDatagramPacket.setData(paramArrayOfByte);
    paramDatagramPacket.setLength(this._length + 4);
    return paramDatagramPacket;
  }

  public int getBlockNumber()
  {
    return this._blockNumber;
  }

  public byte[] getData()
  {
    return this._data;
  }

  public int getDataLength()
  {
    return this._length;
  }

  public int getDataOffset()
  {
    return this._offset;
  }

  public DatagramPacket newDatagram()
  {
    byte[] arrayOfByte = new byte[this._length + 4];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = ((byte)this._type);
    arrayOfByte[2] = ((byte)((this._blockNumber & 0xFFFF) >> 8));
    arrayOfByte[3] = ((byte)(this._blockNumber & 0xFF));
    System.arraycopy(this._data, this._offset, arrayOfByte, 4, this._length);
    return new DatagramPacket(arrayOfByte, this._length + 4, this._address, this._port);
  }

  public void setBlockNumber(int paramInt)
  {
    this._blockNumber = paramInt;
  }

  public void setData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this._data = paramArrayOfByte;
    this._offset = paramInt1;
    this._length = paramInt2;
    if (paramInt2 > 512)
    {
      this._length = 512;
      return;
    }
    this._length = paramInt2;
  }
}