package com.qx.qgbox.entitys;

import java.io.Serializable;

public class DirKey
  implements Serializable
{
  private static final long serialVersionUID = -3582937068889447805L;
  private byte dirRadiusHigh;
  private byte dirRadiusLow;
  private byte down;
  private byte left;
  private byte originXHigh;
  private byte originXLow;
  private byte originYHigh;
  private byte originYLow;
  private byte right;
  private byte up;

  public DirKey()
  {
  }

  public DirKey(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10)
  {
    this.originXLow = paramByte1;
    this.originXHigh = paramByte2;
    this.originYLow = paramByte3;
    this.originYHigh = paramByte4;
    this.dirRadiusLow = paramByte5;
    this.dirRadiusHigh = paramByte6;
    this.up = paramByte7;
    this.down = paramByte8;
    this.left = paramByte9;
    this.right = paramByte10;
  }

  public byte getDirRadiusHigh()
  {
    return this.dirRadiusHigh;
  }

  public byte getDirRadiusLow()
  {
    return this.dirRadiusLow;
  }

  public byte getDown()
  {
    return this.down;
  }

  public byte getLeft()
  {
    return this.left;
  }

  public byte getOriginXHigh()
  {
    return this.originXHigh;
  }

  public byte getOriginXLow()
  {
    return this.originXLow;
  }

  public byte getOriginYHigh()
  {
    return this.originYHigh;
  }

  public byte getOriginYLow()
  {
    return this.originYLow;
  }

  public byte getRight()
  {
    return this.right;
  }

  public byte getUp()
  {
    return this.up;
  }

  public void setDirRadiusHigh(byte paramByte)
  {
    this.dirRadiusHigh = paramByte;
  }

  public void setDirRadiusLow(byte paramByte)
  {
    this.dirRadiusLow = paramByte;
  }

  public void setDown(byte paramByte)
  {
    this.down = paramByte;
  }

  public void setLeft(byte paramByte)
  {
    this.left = paramByte;
  }

  public void setOriginXHigh(byte paramByte)
  {
    this.originXHigh = paramByte;
  }

  public void setOriginXLow(byte paramByte)
  {
    this.originXLow = paramByte;
  }

  public void setOriginYHigh(byte paramByte)
  {
    this.originYHigh = paramByte;
  }

  public void setOriginYLow(byte paramByte)
  {
    this.originYLow = paramByte;
  }

  public void setRight(byte paramByte)
  {
    this.right = paramByte;
  }

  public void setUp(byte paramByte)
  {
    this.up = paramByte;
  }
}
