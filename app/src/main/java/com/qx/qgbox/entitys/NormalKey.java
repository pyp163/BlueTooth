package com.qx.qgbox.entitys;

import java.io.Serializable;

public class NormalKey
  implements Serializable
{
  private static final long serialVersionUID = 8184573505684883008L;
  private byte code;
  private byte p1xh;
  private byte p1xl;
  private byte p1yh;
  private byte p1yl;
  private byte p2xh;
  private byte p2xl;
  private byte p2yh;
  private byte p2yl;
  private byte type;

  public NormalKey()
  {
  }

  public NormalKey(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10)
  {
    this.type = paramByte1;
    this.code = paramByte2;
    this.p1xl = paramByte3;
    this.p1xh = paramByte4;
    this.p1yl = paramByte5;
    this.p1yh = paramByte6;
    this.p2xl = paramByte7;
    this.p2xh = paramByte8;
    this.p2yl = paramByte9;
    this.p2yh = paramByte10;
  }

  public byte getCode()
  {
    return this.code;
  }

  public byte getP1xh()
  {
    return this.p1xh;
  }

  public byte getP1xl()
  {
    return this.p1xl;
  }

  public byte getP1yh()
  {
    return this.p1yh;
  }

  public byte getP1yl()
  {
    return this.p1yl;
  }

  public byte getP2xh()
  {
    return this.p2xh;
  }

  public byte getP2xl()
  {
    return this.p2xl;
  }

  public byte getP2yh()
  {
    return this.p2yh;
  }

  public byte getP2yl()
  {
    return this.p2yl;
  }

  public byte getType()
  {
    return this.type;
  }

  public void setCode(byte paramByte)
  {
    this.code = paramByte;
  }

  public void setP1xh(byte paramByte)
  {
    this.p1xh = paramByte;
  }

  public void setP1xl(byte paramByte)
  {
    this.p1xl = paramByte;
  }

  public void setP1yh(byte paramByte)
  {
    this.p1yh = paramByte;
  }

  public void setP1yl(byte paramByte)
  {
    this.p1yl = paramByte;
  }

  public void setP2xh(byte paramByte)
  {
    this.p2xh = paramByte;
  }

  public void setP2xl(byte paramByte)
  {
    this.p2xl = paramByte;
  }

  public void setP2yh(byte paramByte)
  {
    this.p2yh = paramByte;
  }

  public void setP2yl(byte paramByte)
  {
    this.p2yl = paramByte;
  }

  public void setType(byte paramByte)
  {
    this.type = paramByte;
  }
}
