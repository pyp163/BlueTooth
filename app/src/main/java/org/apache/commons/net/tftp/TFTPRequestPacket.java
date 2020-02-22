package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Locale;

public abstract class TFTPRequestPacket extends TFTPPacket
{
  private static final byte[][] _modeBytes = { { 110, 101, 116, 97, 115, 99, 105, 105, 0 }, { 111, 99, 116, 101, 116, 0 } };
  static final String[] _modeStrings = { "netascii", "octet" };
  private final String _filename;
  private final int _mode;

  TFTPRequestPacket(int paramInt, DatagramPacket paramDatagramPacket)
    throws TFTPPacketException
  {
    super(paramInt, paramDatagramPacket.getAddress(), paramDatagramPacket.getPort());
    Object localObject = paramDatagramPacket.getData();
    if (getType() != localObject[1])
      throw new TFTPPacketException("TFTP operator code does not match type.");
    StringBuilder localStringBuilder = new StringBuilder();
    paramInt = 2;
    int i = paramDatagramPacket.getLength();
    while ((paramInt < i) && (localObject[paramInt] != 0))
    {
      localStringBuilder.append((char)localObject[paramInt]);
      paramInt += 1;
    }
    this._filename = localStringBuilder.toString();
    if (paramInt >= i)
      throw new TFTPPacketException("Bad filename and mode format.");
    int j = 0;
    localStringBuilder.setLength(0);
    paramInt += 1;
    while ((paramInt < i) && (localObject[paramInt] != 0))
    {
      localStringBuilder.append((char)localObject[paramInt]);
      paramInt += 1;
    }
    paramDatagramPacket = localStringBuilder.toString().toLowerCase(Locale.ENGLISH);
    int k = _modeStrings.length;
    paramInt = 0;
    while (true)
    {
      i = j;
      if (paramInt >= k)
        break;
      if (paramDatagramPacket.equals(_modeStrings[paramInt]))
      {
        i = paramInt;
        break;
      }
      paramInt += 1;
    }
    this._mode = i;
    if (paramInt >= k)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unrecognized TFTP transfer mode: ");
      ((StringBuilder)localObject).append(paramDatagramPacket);
      throw new TFTPPacketException(((StringBuilder)localObject).toString());
    }
  }

  TFTPRequestPacket(InetAddress paramInetAddress, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    super(paramInt2, paramInetAddress, paramInt1);
    this._filename = paramString;
    this._mode = paramInt3;
  }

  final DatagramPacket _newDatagram(DatagramPacket paramDatagramPacket, byte[] paramArrayOfByte)
  {
    int i = this._filename.length();
    int j = _modeBytes[this._mode].length;
    paramArrayOfByte[0] = 0;
    paramArrayOfByte[1] = ((byte)this._type);
    System.arraycopy(this._filename.getBytes(), 0, paramArrayOfByte, 2, i);
    paramArrayOfByte[(i + 2)] = 0;
    System.arraycopy(_modeBytes[this._mode], 0, paramArrayOfByte, i + 3, j);
    paramDatagramPacket.setAddress(this._address);
    paramDatagramPacket.setPort(this._port);
    paramDatagramPacket.setData(paramArrayOfByte);
    paramDatagramPacket.setLength(i + j + 3);
    return paramDatagramPacket;
  }

  public final String getFilename()
  {
    return this._filename;
  }

  public final int getMode()
  {
    return this._mode;
  }

  public final DatagramPacket newDatagram()
  {
    int i = this._filename.length();
    int j = _modeBytes[this._mode].length;
    byte[] arrayOfByte = new byte[i + j + 4];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = ((byte)this._type);
    System.arraycopy(this._filename.getBytes(), 0, arrayOfByte, 2, i);
    arrayOfByte[(i + 2)] = 0;
    System.arraycopy(_modeBytes[this._mode], 0, arrayOfByte, i + 3, j);
    return new DatagramPacket(arrayOfByte, arrayOfByte.length, this._address, this._port);
  }
}