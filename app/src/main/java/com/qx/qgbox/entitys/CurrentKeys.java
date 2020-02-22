package com.qx.qgbox.entitys;

import com.qx.qgbox.views.NumImageView;
import java.io.Serializable;

public class CurrentKeys
  implements Serializable
{
  private static final long serialVersionUID = -5762676188243353L;
  private int keyCode;
  private NumImageView mNumImageView;
  private String name;
  private int x;
  private int y;

  public int getKeyCode()
  {
    return this.keyCode;
  }

  public String getName()
  {
    return this.name;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public NumImageView getmNumImageView()
  {
    return this.mNumImageView;
  }

  public void setKeyCode(int paramInt)
  {
    this.keyCode = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setX(int paramInt)
  {
    this.x = paramInt;
  }

  public void setY(int paramInt)
  {
    this.y = paramInt;
  }

  public void setmNumImageView(NumImageView paramNumImageView)
  {
    this.mNumImageView = paramNumImageView;
  }
}
