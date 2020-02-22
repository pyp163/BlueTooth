package com.qx.qgbox.entitys;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class FactoryInfo
  implements Serializable
{
  public static final String CONTENT = "content";
  public static final String DETAIL = "detail";
  public static final String DETAIL_ENGLISH = "detail_english";
  public static final String MODEL = "model";
  public static final String MODEL_NAME = "modelname";
  public static final String NAME = "name";
  public static final String PHONE = "phone";
  public static final String PIC = "pic";
  public static final String PIC_FIFTH = "pic_fifth";
  public static final String PIC_FOURTH = "pic_fourth";
  public static final String PIC_SECOND = "pic_second";
  public static final String PIC_THIRD = "pic_third";
  public static final String PLATFORM = "platform";
  public static final String SMALLPIC = "smallPic";
  public static final String TYPE = "type";
  private static final long serialVersionUID = 4322874147625130241L;
  private String content;
  private String detail;
  private String detail_english;
  private String model;
  private String modelname;
  private String name;
  private String phone;
  private String pic;
  private String pic_fifth;
  private String pic_fourth;
  private String pic_second;
  private String pic_third;
  private int platform;
  private String smallPic;
  private int type;

  public FactoryInfo()
  {
  }

  public FactoryInfo(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt2, String paramString10, String paramString11, String paramString12, String paramString13)
  {
    this.smallPic = paramString1;
    this.content = paramString2;
    this.modelname = paramString3;
    this.platform = paramInt1;
    this.detail = paramString4;
    this.detail_english = paramString5;
    this.phone = paramString6;
    this.model = paramString7;
    this.name = paramString8;
    this.pic = paramString9;
    this.type = paramInt2;
    this.pic_second = paramString10;
    this.pic_third = paramString11;
    this.pic_fourth = paramString12;
    this.pic_fifth = paramString13;
  }

  public FactoryInfo(JSONObject paramJSONObject)
  {
    try
    {
      this.smallPic = paramJSONObject.getString("smallPic");
      this.content = paramJSONObject.getString("content");
      this.modelname = paramJSONObject.getString("modelname");
      this.platform = paramJSONObject.getInt("platform");
      this.detail = paramJSONObject.getString("detail");
      this.detail_english = paramJSONObject.getString("detail_english");
      this.phone = paramJSONObject.getString("phone");
      this.model = paramJSONObject.getString("model");
      this.name = paramJSONObject.getString("name");
      this.pic = paramJSONObject.getString("pic");
      this.type = paramJSONObject.getInt("type");
      this.pic_second = paramJSONObject.getString("pic_second");
      this.pic_third = paramJSONObject.getString("pic_third");
      this.pic_fourth = paramJSONObject.getString("pic_fourth");
      this.pic_fifth = paramJSONObject.getString("pic_fifth");
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

  public String getDetail()
  {
    return this.detail;
  }

  public String getDetail_english()
  {
    return this.detail_english;
  }

  public String getModel()
  {
    return this.model;
  }

  public String getModelname()
  {
    return this.modelname;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public String getPic()
  {
    return this.pic;
  }

  public String getPic_fifth()
  {
    return this.pic_fifth;
  }

  public String getPic_fourth()
  {
    return this.pic_fourth;
  }

  public String getPic_second()
  {
    return this.pic_second;
  }

  public String getPic_third()
  {
    return this.pic_third;
  }

  public int getPlatform()
  {
    return this.platform;
  }

  public String getSmallPic()
  {
    return this.smallPic;
  }

  public int getType()
  {
    return this.type;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setDetail(String paramString)
  {
    this.detail = paramString;
  }

  public void setDetail_english(String paramString)
  {
    this.detail_english = paramString;
  }

  public void setModel(String paramString)
  {
    this.model = paramString;
  }

  public void setModelname(String paramString)
  {
    this.modelname = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }

  public void setPic(String paramString)
  {
    this.pic = paramString;
  }

  public void setPic_fifth(String paramString)
  {
    this.pic_fifth = paramString;
  }

  public void setPic_fourth(String paramString)
  {
    this.pic_fourth = paramString;
  }

  public void setPic_second(String paramString)
  {
    this.pic_second = paramString;
  }

  public void setPic_third(String paramString)
  {
    this.pic_third = paramString;
  }

  public void setPlatform(int paramInt)
  {
    this.platform = paramInt;
  }

  public void setSmallPic(String paramString)
  {
    this.smallPic = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}
