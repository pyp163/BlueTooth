package com.qx.qgbox.gamepad;

public class Setupitem
{
  private boolean isSelected = false;
  private String itemName;

  public Setupitem()
  {
  }

  public Setupitem(String paramString)
  {
    this.itemName = paramString;
  }

  public String getitemName()
  {
    return this.itemName;
  }

  public boolean isSelected()
  {
    return this.isSelected;
  }

  public void setItemName(String paramString)
  {
    this.itemName = paramString;
  }

  public void setSelected(boolean paramBoolean)
  {
    this.isSelected = paramBoolean;
  }
}
