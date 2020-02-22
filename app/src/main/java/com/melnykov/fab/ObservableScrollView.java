package com.melnykov.fab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView
{
  private OnScrollChangedListener mOnScrollChangedListener;

  public ObservableScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public ObservableScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ObservableScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangedListener != null)
      this.mOnScrollChangedListener.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setOnScrollChangedListener(OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.mOnScrollChangedListener = paramOnScrollChangedListener;
  }

  public static abstract interface OnScrollChangedListener
  {
    public abstract void onScrollChanged(ScrollView paramScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}