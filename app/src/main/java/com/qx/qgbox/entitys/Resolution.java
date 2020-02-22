package com.qx.qgbox.entitys;

import java.io.Serializable;

public class Resolution
  implements Serializable
{
  private static final long serialVersionUID = -643463534810805972L;
  private byte resolutionXHigh;
  private byte resolutionXLow;
  private byte resolutionYHigh;
  private byte resolutionYLow;

  public Resolution()
  {
  }

  public Resolution(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    this.resolutionXLow = paramByte1;
    this.resolutionXHigh = paramByte2;
    this.resolutionYLow = paramByte3;
    this.resolutionYHigh = paramByte4;
  }

  public byte getResolutionXHigh()
  {
    return this.resolutionXHigh;
  }

  public byte getResolutionXLow()
  {
    return this.resolutionXLow;
  }

  public byte getResolutionYHigh()
  {
    return this.resolutionYHigh;
  }

  public byte getResolutionYLow()
  {
    return this.resolutionYLow;
  }

  public void setResolutionXHigh(byte paramByte)
  {
    this.resolutionXHigh = paramByte;
  }

  public void setResolutionXLow(byte paramByte)
  {
    this.resolutionXLow = paramByte;
  }

  public void setResolutionYHigh(byte paramByte)
  {
    this.resolutionYHigh = paramByte;
  }

  public void setResolutionYLow(byte paramByte)
  {
    this.resolutionYLow = paramByte;
  }
}
