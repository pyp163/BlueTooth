package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public final class TFTPErrorPacket extends TFTPPacket
{
  public static final int ACCESS_VIOLATION = 2;
  public static final int FILE_EXISTS = 6;
  public static final int FILE_NOT_FOUND = 1;
  public static final int ILLEGAL_OPERATION = 4;
  public static final int NO_SUCH_USER = 7;
  public static final int OUT_OF_SPACE = 3;
  public static final int UNDEFINED = 0;
  public static final int UNKNOWN_TID = 5;
  int _error;
  String _message;

  TFTPErrorPacket(DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(5, paramDatagramPacket.getAddress(), paramDatagramPacket.getPort());
    byte[] arrayOfByte = paramDatagramPacket.getData();
    int j = paramDatagramPacket.getLength();
    if (getType() != arrayOfByte[1])
      throw new TFTPPacketException("TFTP operator code does not match type.");
    this._error = ((arrayOfByte[2] & 0xFF) << 8 | arrayOfByte[3] & 0xFF);
    if (j < 5)
      throw new TFTPPacketException("Bad error packet. No message.");
    int i = 4;
    paramDatagramPacket = new StringBuilder();
    while ((i < j) && (arrayOfByte[i] != 0))
    {
      paramDatagramPacket.append((char)arrayOfByte[i]);
      i += 1;
    }
    this._message = paramDatagramPacket.toString();
  }

  public TFTPErrorPacket(InetAddress paramInetAddress, int paramInt1, int paramInt2, String paramString)
  {
    super(5, paramInetAddress, paramInt1);
    this._error = paramInt2;
    this._message = paramString;
  }

  DatagramPacket _newDatagram(DatagramPacket paramDatagramPacket, byte[] paramArrayOfByte)
  {
    int i = this._message.length();
    paramArrayOfByte[0] = 0;
    paramArrayOfByte[1] = ((byte)this._type);
    paramArrayOfByte[2] = ((byte)((this._error & 0xFFFF) >> 8));
    paramArrayOfByte[3] = ((byte)(this._error & 0xFF));
    System.arraycopy(this._message.getBytes(), 0, paramArrayOfByte, 4, i);
    i += 4;
    paramArrayOfByte[i] = 0;
    paramDatagramPacket.setAddress(this._address);
    paramDatagramPacket.setPort(this._port);
    paramDatagramPacket.setData(paramArrayOfByte);
    paramDatagramPacket.setLength(i);
    return paramDatagramPacket;
  }

  public int getError()
  {
    return this._error;
  }

  public String getMessage()
  {
    return this._message;
  }

  public DatagramPacket newDatagram()
  {
    int i = this._message.length();
    byte[] arrayOfByte = new byte[i + 5];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = ((byte)this._type);
    arrayOfByte[2] = ((byte)((this._error & 0xFFFF) >> 8));
    arrayOfByte[3] = ((byte)(this._error & 0xFF));
    System.arraycopy(this._message.getBytes(), 0, arrayOfByte, 4, i);
    arrayOfByte[(i + 4)] = 0;
    return new DatagramPacket(arrayOfByte, arrayOfByte.length, this._address, this._port);
  }
}