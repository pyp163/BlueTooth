package com.qx.qgbox.entitys;

import java.io.Serializable;

public class pFileInfo
  implements Serializable
{
  private static final long serialVersionUID = -6126300684998649216L;
  private byte type;
  private byte versionCodeHigh;
  private byte versionCodeLow;

  public pFileInfo()
  {
  }

  public pFileInfo(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.type = paramByte1;
    this.versionCodeLow = paramByte2;
    this.versionCodeHigh = paramByte3;
  }

  public byte getType()
  {
    return this.type;
  }

  public byte getVersionCodeHigh()
  {
    return this.versionCodeHigh;
  }

  public byte getVersionCodeLow()
  {
    return this.versionCodeLow;
  }

  public void setType(byte paramByte)
  {
    this.type = paramByte;
  }

  public void setVersionCodeHigh(byte paramByte)
  {
    this.versionCodeHigh = paramByte;
  }

  public void setVersionCodeLow(byte paramByte)
  {
    this.versionCodeLow = paramByte;
  }
}
