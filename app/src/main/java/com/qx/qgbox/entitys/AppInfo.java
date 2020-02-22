package com.qx.qgbox.entitys;

import java.io.Serializable;

public class AppInfo
  implements Serializable
{
  private static final String CONTENT = "content";
  private static final String ENGLISH_CONTENT = "english_content";
  private static final String FILE_NAME = "fileName";
  private static final String UPDATE_DATE = "update_date";
  private static final String URL = "url";
  private static final String VERSION_CODE = "versionCode";
  private static final long serialVersionUID = 9085538385753311363L;
  private String content;
  private String english_content;
  private String fileName;
  private String update_date;
  private String url;
  private int versionCode;

  public AppInfo()
  {
  }

  public AppInfo(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
  {
    this.fileName = paramString1;
    this.update_date = paramString2;
    this.versionCode = paramInt;
    this.content = paramString3;
    this.url = paramString4;
    this.english_content = paramString5;
  }

  // ERROR //
  public AppInfo(org.json.JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 34	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: aload_1
    //   6: ldc 16
    //   8: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   11: putfield 38	com/qx/qgbox/entitys/AppInfo:fileName	Ljava/lang/String;
    //   14: aload_0
    //   15: aload_1
    //   16: ldc 19
    //   18: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   21: putfield 40	com/qx/qgbox/entitys/AppInfo:update_date	Ljava/lang/String;
    //   24: aload_0
    //   25: aload_1
    //   26: ldc 25
    //   28: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   31: invokestatic 65	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   34: invokevirtual 69	java/lang/Integer:intValue	()I
    //   37: putfield 42	com/qx/qgbox/entitys/AppInfo:versionCode	I
    //   40: goto +16 -> 56
    //   43: aload_0
    //   44: iconst_0
    //   45: putfield 42	com/qx/qgbox/entitys/AppInfo:versionCode	I
    //   48: goto +8 -> 56
    //   51: aload_0
    //   52: iconst_0
    //   53: putfield 42	com/qx/qgbox/entitys/AppInfo:versionCode	I
    //   56: aload_0
    //   57: aload_1
    //   58: ldc 10
    //   60: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   63: putfield 44	com/qx/qgbox/entitys/AppInfo:content	Ljava/lang/String;
    //   66: aload_0
    //   67: aload_1
    //   68: ldc 13
    //   70: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   73: putfield 48	com/qx/qgbox/entitys/AppInfo:english_content	Ljava/lang/String;
    //   76: aload_0
    //   77: aload_1
    //   78: ldc 22
    //   80: invokevirtual 59	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   83: putfield 46	com/qx/qgbox/entitys/AppInfo:url	Ljava/lang/String;
    //   86: return
    //   87: astore_1
    //   88: aload_1
    //   89: invokevirtual 72	org/json/JSONException:printStackTrace	()V
    //   92: return
    //   93: astore_2
    //   94: goto -43 -> 51
    //   97: astore_2
    //   98: goto -55 -> 43
    //
    // Exception table:
    //   from	to	target	type
    //   4	24	87	org/json/JSONException
    //   43	48	87	org/json/JSONException
    //   51	56	87	org/json/JSONException
    //   56	86	87	org/json/JSONException
    //   24	40	93	java/lang/NumberFormatException
    //   24	40	97	org/json/JSONException
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

  public int getVersionCode()
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

  public void setVersionCode(int paramInt)
  {
    this.versionCode = paramInt;
  }
}
