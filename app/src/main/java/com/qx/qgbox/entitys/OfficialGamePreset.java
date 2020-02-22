package com.qx.qgbox.entitys;

import android.graphics.drawable.Drawable;
import java.io.Serializable;

public class OfficialGamePreset
  implements Serializable
{
  private static final long serialVersionUID = -764067079560860418L;
  private String addTime;
  private String android_url;
  private String appName;
  private String appPackageName;
  private int dataOrg;
  private int deviceType;
  private int gameId;
  private String gameLogoUrl;
  private String gameName;
  private String handlePicUrl;
  private Drawable icon;
  private String iconPath;
  private String presetPath;
  private String presetTxtFileUrl;
  private String presetUrl;
  private int smgpId;
  private String thronePicUrl;

  public OfficialGamePreset()
  {
  }

  public OfficialGamePreset(SunyesMaxGamePreset paramSunyesMaxGamePreset, int paramInt)
  {
    this.presetPath = paramSunyesMaxGamePreset.getPresetPath();
    this.icon = paramSunyesMaxGamePreset.getIcon();
    this.appName = paramSunyesMaxGamePreset.getAppName();
    this.appPackageName = paramSunyesMaxGamePreset.getAppPackageName();
    this.iconPath = paramSunyesMaxGamePreset.getIconPath();
    this.addTime = paramSunyesMaxGamePreset.getAddTime();
    this.deviceType = paramSunyesMaxGamePreset.getDeviceType();
    this.smgpId = paramSunyesMaxGamePreset.getSmgpId();
    this.dataOrg = paramInt;
  }

  public OfficialGamePreset(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt2)
  {
    this.presetTxtFileUrl = paramString1;
    this.presetUrl = paramString2;
    this.gameLogoUrl = paramString3;
    this.gameId = paramInt1;
    this.handlePicUrl = paramString4;
    this.thronePicUrl = paramString5;
    this.gameName = paramString6;
    this.android_url = paramString7;
    this.dataOrg = paramInt2;
  }

  public String getAddTime()
  {
    return this.addTime;
  }

  public String getAndroid_url()
  {
    return this.android_url;
  }

  public String getAppName()
  {
    return this.appName;
  }

  public String getAppPackageName()
  {
    return this.appPackageName;
  }

  public int getDataOrg()
  {
    return this.dataOrg;
  }

  public int getDeviceType()
  {
    return this.deviceType;
  }

  public int getGameId()
  {
    return this.gameId;
  }

  public String getGameLogoUrl()
  {
    return this.gameLogoUrl;
  }

  public String getGameName()
  {
    return this.gameName;
  }

  public String getHandlePicUrl()
  {
    return this.handlePicUrl;
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

  public String getPresetTxtFileUrl()
  {
    return this.presetTxtFileUrl;
  }

  public String getPresetUrl()
  {
    return this.presetUrl;
  }

  public int getSmgpId()
  {
    return this.smgpId;
  }

  public String getThronePicUrl()
  {
    return this.thronePicUrl;
  }

  public void setAddTime(String paramString)
  {
    this.addTime = paramString;
  }

  public void setAndroid_url(String paramString)
  {
    this.android_url = paramString;
  }

  public void setAppName(String paramString)
  {
    this.appName = paramString;
  }

  public void setAppPackageName(String paramString)
  {
    this.appPackageName = paramString;
  }

  public void setDataOrg(int paramInt)
  {
    this.dataOrg = paramInt;
  }

  public void setDeviceType(int paramInt)
  {
    this.deviceType = paramInt;
  }

  public void setGameId(int paramInt)
  {
    this.gameId = paramInt;
  }

  public void setGameLogoUrl(String paramString)
  {
    this.gameLogoUrl = paramString;
  }

  public void setGameName(String paramString)
  {
    this.gameName = paramString;
  }

  public void setHandlePicUrl(String paramString)
  {
    this.handlePicUrl = paramString;
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

  public void setPresetTxtFileUrl(String paramString)
  {
    this.presetTxtFileUrl = paramString;
  }

  public void setPresetUrl(String paramString)
  {
    this.presetUrl = paramString;
  }

  public void setSmgpId(int paramInt)
  {
    this.smgpId = paramInt;
  }

  public void setThronePicUrl(String paramString)
  {
    this.thronePicUrl = paramString;
  }
}
