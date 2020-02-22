package com.melnykov.fab;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

abstract class AbsListViewScrollDetector
  implements AbsListView.OnScrollListener
{
  private int mLastScrollY;
  private AbsListView mListView;
  private int mPreviousFirstVisibleItem;
  private int mScrollThreshold;

  private int getTopItemScrollY()
  {
    if (this.mListView != null)
    {
      if (this.mListView.getChildAt(0) == null)
        return 0;
      return this.mListView.getChildAt(0).getTop();
    }
    return 0;
  }

  private boolean isSameRow(int paramInt)
  {
    return paramInt == this.mPreviousFirstVisibleItem;
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 == 0)
      return;
    if (isSameRow(paramInt1))
    {
      paramInt2 = getTopItemScrollY();
      if (Math.abs(this.mLastScrollY - paramInt2) > this.mScrollThreshold)
        paramInt1 = 1;
      else
        paramInt1 = 0;
      if (paramInt1 != 0)
        if (this.mLastScrollY > paramInt2)
          onScrollUp();
        else
          onScrollDown();
      this.mLastScrollY = paramInt2;
      return;
    }
    if (paramInt1 > this.mPreviousFirstVisibleItem)
      onScrollUp();
    else
      onScrollDown();
    this.mLastScrollY = getTopItemScrollY();
    this.mPreviousFirstVisibleItem = paramInt1;
  }

  abstract void onScrollDown();

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
  }

  abstract void onScrollUp();

  public void setListView(@NonNull AbsListView paramAbsListView)
  {
    this.mListView = paramAbsListView;
  }

  public void setScrollThreshold(int paramInt)
  {
    this.mScrollThreshold = paramInt;
  }
}