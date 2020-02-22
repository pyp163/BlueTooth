package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class ViewOffsetHelper
{
  private int layoutLeft;
  private int layoutTop;
  private int offsetLeft;
  private int offsetTop;
  private final View view;

  public ViewOffsetHelper(View paramView)
  {
    this.view = paramView;
  }

  private void updateOffsets()
  {
    ViewCompat.offsetTopAndBottom(this.view, this.offsetTop - (this.view.getTop() - this.layoutTop));
    ViewCompat.offsetLeftAndRight(this.view, this.offsetLeft - (this.view.getLeft() - this.layoutLeft));
  }

  public int getLayoutLeft()
  {
    return this.layoutLeft;
  }

  public int getLayoutTop()
  {
    return this.layoutTop;
  }

  public int getLeftAndRightOffset()
  {
    return this.offsetLeft;
  }

  public int getTopAndBottomOffset()
  {
    return this.offsetTop;
  }

  public void onViewLayout()
  {
    this.layoutTop = this.view.getTop();
    this.layoutLeft = this.view.getLeft();
    updateOffsets();
  }

  public boolean setLeftAndRightOffset(int paramInt)
  {
    if (this.offsetLeft != paramInt)
    {
      this.offsetLeft = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }

  public boolean setTopAndBottomOffset(int paramInt)
  {
    if (this.offsetTop != paramInt)
    {
      this.offsetTop = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }
}