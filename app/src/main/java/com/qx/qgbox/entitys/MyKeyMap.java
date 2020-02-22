package com.qx.qgbox.entitys;

public class MyKeyMap
{
  public int _id;
  public String name;
  public int value;

  public MyKeyMap()
  {
  }

  public MyKeyMap(String paramString, int paramInt1, int paramInt2)
  {
    this.name = paramString;
    this.value = paramInt1;
    this._id = paramInt2;
  }
}
