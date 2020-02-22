package com.qx.qgbox.utils;

public class HttpUrlConfig
{
  private static String GET_DEVICE_MODEL_URL = "http://shootingplus.com.cn/shootingplus/open/games/getModel?type=";
  public static final String GET_DOWNLOAD_SHARE_FILE_URL = "http://shootingplus.com.cn/shootingplus/open/games/getSharePreset?pwd=";
  public static final String GET_EPST_MODE_CODE = "http://shootingplus.com.cn/shootingplus/open/games/getEpstpPwd?model=SWT";
  private static final String GET_OFFICIAL_FILE_DOWNLOAD_RUL = "http://shootingplus.com.cn/shootingplus/open/games/getOfficialPreset?";
  private static final String GET_OFFICIAL_FILE_DOWNLOAD_RULBY_GAME_ID = "http://shootingplus.com.cn/shootingplus/open/games/getOfficialPresetByGameID?";
  public static String GET_OFFICIAL_GAME_LIST = "http://shootingplus.com.cn/shootingplus/open/games/getGameList";
  private static String GET_OFFICIAL_GAME_PRESET_LIST = "http://shootingplus.com.cn/shootingplus/open/games/getGamePreset?platform=1&type=";
  public static final String GET_SERVER_APP_INFO_URL = "http://shootingplus.com.cn/shootingplus/open/games/getAppVersionInfo?type=0";
  public static final String GET_UNSUPPORTED_RESOLUTION_URL = "http://shootingplus.com.cn/shootingplus/open/games/getNoResolutionList?platform=1";
  public static final String POST_OFFICIAL_FILE_DOWNLOAD_URL = "http://shootingplus.com.cn/shootingplus/open/games/addOfficialPreset";
  public static final String POST_OFFICIAL_GAME_PRESET_FILE_URL = "http://shootingplus.com.cn/shootingplus/open/games/addGamePreset";
  public static String POST_PHONE_INFO_URL = "http://shootingplus.com.cn/shootingplus/open/games/setImei";
  public static final String POST_SHARE_FILE_URL_URL = "http://shootingplus.com.cn/shootingplus/open/games/addSharePreset";
  public static final String UPLOAD_FILE_URL = "http://shootingplus.com.cn/shootingplus/open/upload/upload";
  private static String baseUrl = "http://shootingplus.com.cn/shootingplus/open/games/getFirmware?type=";
  private static String getConteneUrl = "http://shootingplus.com.cn/shootingplus/open/games/getContent?model=";
  private static final String serverIp = "http://shootingplus.com.cn/shootingplus/";

  public static String getConteneUrl(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getConteneUrl);
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static String getGetDeviceModelUrl(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(GET_DEVICE_MODEL_URL);
    localStringBuilder.append(paramInt);
    return localStringBuilder.toString();
  }

  public static String getOfficialFileDownloadRul(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://shootingplus.com.cn/shootingplus/open/games/getOfficialPreset?platform=");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("&type=");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&location=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("&resolution=");
    localStringBuilder.append(paramString3);
    paramString1 = localStringBuilder.toString();
    paramString2 = new StringBuilder();
    paramString2.append("----getOfficialFileDownloadRul = ");
    paramString2.append(paramString1);
    MyLog.i("locationPath", paramString2.toString());
    return paramString1;
  }

  public static String getOfficialFileDownloadRulByGameId(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://shootingplus.com.cn/shootingplus/open/games/getOfficialPresetByGameID?platform=");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("&type=");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&gameId=");
    localStringBuilder.append(paramInt3);
    localStringBuilder.append("&resolution=");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }

  public static String getOfficialGamePresetList(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-----resolution = ");
    localStringBuilder.append(paramString);
    MyLog.i("my_tag", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(GET_OFFICIAL_GAME_PRESET_LIST);
    localStringBuilder.append(paramInt);
    localStringBuilder.append("&resolution=");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static String getShareFileDownloadUrl(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://shootingplus.com.cn/shootingplus/open/games/getSharePreset?pwd=");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static String getUrlByPname(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(baseUrl);
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }

  public static String getUrlByPname(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(baseUrl);
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("&pid=");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("&vid=");
    localStringBuilder.append(paramString4);
    return localStringBuilder.toString();
  }

  public static String getUrlByPname(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(baseUrl);
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("&rows=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("&pid=");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("&vid=");
    localStringBuilder.append(paramString4);
    return localStringBuilder.toString();
  }

  public static String getUrlByPname(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(baseUrl);
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("&rows=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("&pid=");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("&vid=");
    localStringBuilder.append(paramString4);
    localStringBuilder.append("&icname=");
    localStringBuilder.append(paramString5);
    return localStringBuilder.toString();
  }

  public static String getUrlByPname(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(baseUrl);
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&model=");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("&icname=");
    localStringBuilder.append(paramString5);
    localStringBuilder.append("&pid=");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("&vid=");
    localStringBuilder.append(paramString4);
    return localStringBuilder.toString();
  }
}
