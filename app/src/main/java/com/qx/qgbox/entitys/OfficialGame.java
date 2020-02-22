package com.qx.qgbox.entitys;

import java.io.Serializable;

public class OfficialGame
  implements Serializable
{
  public static final String ANDROID_URL = "android_url";
  public static final String GAMEID = "gameId";
  public static final String HANDLEPIC = "handlePic";
  public static final String LOGO = "logo";
  public static final String NAME = "name";
  public static final String THRONEPIC = "thronePic";
  private static final long serialVersionUID = -3531833323726692015L;
  private String android_url;
  private int gameId;
  private String handlePic;
  private String logo;
  private String name;
  private String thronePic;

  public OfficialGame()
  {
  }

  public OfficialGame(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
  {
    this.logo = paramString1;
    this.name = paramString2;
    this.gameId = paramInt;
    this.handlePic = paramString3;
    this.thronePic = paramString4;
    this.android_url = paramString5;
  }

  // ERROR //
  public OfficialGame(org.json.JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 34	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: aload_1
    //   6: ldc 19
    //   8: invokevirtual 57	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   11: putfield 38	com/qx/qgbox/entitys/OfficialGame:logo	Ljava/lang/String;
    //   14: aload_0
    //   15: aload_1
    //   16: ldc 22
    //   18: invokevirtual 57	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   21: putfield 40	com/qx/qgbox/entitys/OfficialGame:name	Ljava/lang/String;
    //   24: aload_0
    //   25: aload_1
    //   26: ldc 16
    //   28: invokevirtual 57	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   31: putfield 44	com/qx/qgbox/entitys/OfficialGame:handlePic	Ljava/lang/String;
    //   34: aload_0
    //   35: aload_1
    //   36: ldc 25
    //   38: invokevirtual 57	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: putfield 46	com/qx/qgbox/entitys/OfficialGame:thronePic	Ljava/lang/String;
    //   44: aload_0
    //   45: aload_1
    //   46: ldc 13
    //   48: invokevirtual 61	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   51: putfield 42	com/qx/qgbox/entitys/OfficialGame:gameId	I
    //   54: goto +8 -> 62
    //   57: aload_0
    //   58: iconst_0
    //   59: putfield 42	com/qx/qgbox/entitys/OfficialGame:gameId	I
    //   62: aload_0
    //   63: aload_1
    //   64: ldc 10
    //   66: invokevirtual 57	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: putfield 48	com/qx/qgbox/entitys/OfficialGame:android_url	Ljava/lang/String;
    //   72: return
    //   73: astore_1
    //   74: aload_1
    //   75: invokevirtual 64	org/json/JSONException:printStackTrace	()V
    //   78: return
    //   79: astore_2
    //   80: goto -23 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   4	44	73	org/json/JSONException
    //   57	62	73	org/json/JSONException
    //   62	72	73	org/json/JSONException
    //   44	54	79	org/json/JSONException
  }

  public String getAndroid_url()
  {
    return this.android_url;
  }

  public int getGameId()
  {
    return this.gameId;
  }

  public String getHandlePic()
  {
    return this.handlePic;
  }

  public String getLogo()
  {
    return this.logo;
  }

  public String getName()
  {
    return this.name;
  }

  public String getThronePic()
  {
    return this.thronePic;
  }

  public void setAndroid_url(String paramString)
  {
    this.android_url = paramString;
  }

  public void setGameId(int paramInt)
  {
    this.gameId = paramInt;
  }

  public void setHandlePic(String paramString)
  {
    this.handlePic = paramString;
  }

  public void setLogo(String paramString)
  {
    this.logo = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setThronePic(String paramString)
  {
    this.thronePic = paramString;
  }
}
