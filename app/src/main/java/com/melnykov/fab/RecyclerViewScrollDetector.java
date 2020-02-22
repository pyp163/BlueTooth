package com.melnykov.fab;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener
{
  private int mScrollThreshold;

  abstract void onScrollDown();

  abstract void onScrollUp();

  public void onScrolled(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    if (Math.abs(paramInt2) > this.mScrollThreshold)
      paramInt1 = 1;
    else
      paramInt1 = 0;
    if (paramInt1 != 0)
    {
      if (paramInt2 > 0)
      {
        onScrollUp();
        return;
      }
      onScrollDown();
    }
  }

  public void setScrollThreshold(int paramInt)
  {
    this.mScrollThreshold = paramInt;
  }
}