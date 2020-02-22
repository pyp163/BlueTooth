package com.qx.qgbox.entitys;

import android.graphics.drawable.Drawable;
import java.io.Serializable;

public class SunyesMaxGamePreset
  implements Serializable
{
  private static final long serialVersionUID = -2219506719488823242L;
  public String addTime;
  public String appName;
  public String appPackageName;
  public int deviceType;
  public Drawable icon;
  public String iconPath;
  public String presetPath;
  public int smgpId;

  public SunyesMaxGamePreset()
  {
  }

  public SunyesMaxGamePreset(String paramString1, Drawable paramDrawable, String paramString2, String paramString3, int paramInt)
  {
    this.presetPath = paramString1;
    this.icon = paramDrawable;
    this.appName = paramString2;
    this.appPackageName = paramString3;
    this.deviceType = paramInt;
  }

  public String getAddTime()
  {
    return this.addTime;
  }

  public String getAppName()
  {
    return this.appName;
  }

  public String getAppPackageName()
  {
    return this.appPackageName;
  }

  public int getDeviceType()
  {
    return this.deviceType;
  }

  public Drawable getIcon()
  {
    return this.icon;
  }

  public String getIconPath()
  {
    return this.iconPath;
  }

  public String getPresetPath()
  {
    return this.presetPath;
  }

  public int getSmgpId()
  {
    return this.smgpId;
  }

  public void setAddTime(String paramString)
  {
    this.addTime = paramString;
  }

  public void setAppName(String paramString)
  {
    this.appName = paramString;
  }

  public void setAppPackageName(String paramString)
  {
    this.appPackageName = paramString;
  }

  public void setDeviceType(int paramInt)
  {
    this.deviceType = paramInt;
  }

  public void setIcon(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }

  public void setIconPath(String paramString)
  {
    this.iconPath = paramString;
  }

  public void setPresetPath(String paramString)
  {
    this.presetPath = paramString;
  }

  public void setSmgpId(int paramInt)
  {
    this.smgpId = paramInt;
  }
}
