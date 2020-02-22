package com.qx.qgbox.entitys;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class FWInfo
  implements Serializable
{
  private static final String CONTENT = "content";
  private static final String ENGLISH_CONTENT = "english_content";
  private static final String FILE_NAME = "fileName";
  private static final String UPDATE_DATE = "update_date";
  private static final String URL = "url";
  private static final String VERSION_CODE = "versionCode";
  private static final long serialVersionUID = -6072794355032667711L;
  private String content;
  private String english_content;
  private String fileName;
  private String update_date;
  private String url;
  private String versionCode;

  public FWInfo()
  {
  }

  public FWInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.fileName = paramString1;
    this.update_date = paramString2;
    this.versionCode = paramString3;
    this.content = paramString4;
    this.url = paramString5;
    this.english_content = paramString6;
  }

  public FWInfo(JSONObject paramJSONObject)
  {
    try
    {
      this.fileName = paramJSONObject.getString("fileName");
      this.update_date = paramJSONObject.getString("update_date");
      this.versionCode = paramJSONObject.getString("versionCode");
      this.content = paramJSONObject.getString("content");
      this.url = paramJSONObject.getString("url");
      this.english_content = paramJSONObject.getString("english_content");
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }

  public String getContent()
  {
    return this.content;
  }

  public String getEnglish_content()
  {
    return this.english_content;
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public String getUpdate_date()
  {
    return this.update_date;
  }

  public String getUrl()
  {
    return this.url;
  }

  public String getVersionCode()
  {
    return this.versionCode;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setEnglish_content(String paramString)
  {
    this.english_content = paramString;
  }

  public void setFileName(String paramString)
  {
    this.fileName = paramString;
  }

  public void setUpdate_date(String paramString)
  {
    this.update_date = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public void setVersionCode(String paramString)
  {
    this.versionCode = paramString;
  }
}
