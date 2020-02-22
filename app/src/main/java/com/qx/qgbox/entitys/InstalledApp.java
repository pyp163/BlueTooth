package com.qx.qgbox.entitys;

import android.graphics.drawable.Drawable;

public class InstalledApp
{
  private String appName;
  private Drawable icon;
  private String packageName;

  public String getAppName()
  {
    return this.appName;
  }

  public Drawable getIcon()
  {
    return this.icon;
  }

  public String getPackageName()
  {
    return this.packageName;
  }

  public void setAppName(String paramString)
  {
    this.appName = paramString;
  }

  public void setIcon(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }

  public void setPackageName(String paramString)
  {
    this.packageName = paramString;
  }
}
