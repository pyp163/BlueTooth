package com.qx.qgbox.entitys;

import java.io.Serializable;

public class Device
  implements Serializable
{
  private static final long serialVersionUID = -7296053878459753412L;
  private String deviceName;
  private boolean isChecked;

  public Device()
  {
  }

  public Device(String paramString, boolean paramBoolean)
  {
    this.deviceName = paramString;
    this.isChecked = paramBoolean;
  }

  public String getDeviceName()
  {
    return this.deviceName;
  }

  public boolean isChecked()
  {
    return this.isChecked;
  }

  public void setChecked(boolean paramBoolean)
  {
    this.isChecked = paramBoolean;
  }

  public void setDeviceName(String paramString)
  {
    this.deviceName = paramString;
  }
}
