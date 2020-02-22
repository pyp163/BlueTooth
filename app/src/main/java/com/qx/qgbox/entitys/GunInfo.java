package com.qx.qgbox.entitys;

import java.io.Serializable;

public class GunInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int autoFireRate;
  private String fireMode;
  private String gunName;
  private String mode;
  private int range;

  public GunInfo()
  {
  }

  public GunInfo(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.gunName = paramString1;
    this.mode = paramString2;
    this.fireMode = paramString3;
    this.range = paramInt1;
    this.autoFireRate = paramInt2;
  }

  public int getAutoFireRate()
  {
    return this.autoFireRate;
  }

  public String getFireMode()
  {
    return this.fireMode;
  }

  public String getGunName()
  {
    return this.gunName;
  }

  public String getMode()
  {
    return this.mode;
  }

  public int getRange()
  {
    return this.range;
  }

  public void setAutoFireRate(int paramInt)
  {
    this.autoFireRate = paramInt;
  }

  public void setFireMode(String paramString)
  {
    this.fireMode = paramString;
  }

  public void setGunName(String paramString)
  {
    this.gunName = paramString;
  }

  public void setMode(String paramString)
  {
    this.mode = paramString;
  }

  public void setRange(int paramInt)
  {
    this.range = paramInt;
  }
}
