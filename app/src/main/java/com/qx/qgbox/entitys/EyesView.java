package com.qx.qgbox.entitys;

import java.io.Serializable;

public class EyesView
  implements Serializable
{
  private static final long serialVersionUID = 1764408491512715641L;
  private byte eyesViewXHigh;
  private byte eyesViewXLow;
  private byte eyesViewYHigh;
  private byte eyesViewYLow;
  private byte mouseSpeed;

  public EyesView()
  {
  }

  public EyesView(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5)
  {
    this.eyesViewXLow = paramByte1;
    this.eyesViewXHigh = paramByte2;
    this.eyesViewYLow = paramByte3;
    this.eyesViewYHigh = paramByte4;
    this.mouseSpeed = paramByte5;
  }

  public byte getEyesViewXHigh()
  {
    return this.eyesViewXHigh;
  }

  public byte getEyesViewXLow()
  {
    return this.eyesViewXLow;
  }

  public byte getEyesViewYHigh()
  {
    return this.eyesViewYHigh;
  }

  public byte getEyesViewYLow()
  {
    return this.eyesViewYLow;
  }

  public byte getMouseSpeed()
  {
    return this.mouseSpeed;
  }

  public void setEyesViewXHigh(byte paramByte)
  {
    this.eyesViewXHigh = paramByte;
  }

  public void setEyesViewXLow(byte paramByte)
  {
    this.eyesViewXLow = paramByte;
  }

  public void setEyesViewYHigh(byte paramByte)
  {
    this.eyesViewYHigh = paramByte;
  }

  public void setEyesViewYLow(byte paramByte)
  {
    this.eyesViewYLow = paramByte;
  }

  public void setMouseSpeed(byte paramByte)
  {
    this.mouseSpeed = paramByte;
  }
}
