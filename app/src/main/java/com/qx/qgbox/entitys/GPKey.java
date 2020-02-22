package com.qx.qgbox.entitys;

public class GPKey
{
  public int _id;
  public String name;
  public int scenes;

  public GPKey()
  {
  }

  public GPKey(int paramInt1, String paramString, int paramInt2)
  {
    this.name = paramString;
    this._id = paramInt1;
    this.scenes = paramInt2;
  }
}
