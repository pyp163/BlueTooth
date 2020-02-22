package com.melnykov.fab;

import android.widget.ScrollView;

abstract class ScrollViewScrollDetector
  implements ObservableScrollView.OnScrollChangedListener
{
  private int mLastScrollY;
  private int mScrollThreshold;

  public void onScrollChanged(ScrollView paramScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Math.abs(paramInt2 - this.mLastScrollY) > this.mScrollThreshold)
      paramInt1 = 1;
    else
      paramInt1 = 0;
    if (paramInt1 != 0)
      if (paramInt2 > this.mLastScrollY)
        onScrollUp();
      else
        onScrollDown();
    this.mLastScrollY = paramInt2;
  }

  abstract void onScrollDown();

  abstract void onScrollUp();

  public void setScrollThreshold(int paramInt)
  {
    this.mScrollThreshold = paramInt;
  }
}