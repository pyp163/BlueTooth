package com.qx.qgbox.pulltorefreshgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public abstract class BaseRefreshLayout extends SuperSwipeRefreshLayout
{
  private boolean isEnableLoadMore = true;
  private boolean isEnableRefresh = true;
  protected OnAutoLoadListener mOnAutoLoadListener;
  protected OnLoadMoreListener mOnLoadMoreListener;
  protected OnRefreshListener mOnRefreshListener;

  public BaseRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void autoRefresh()
  {
    postDelayed(new Runnable()
    {
      public void run()
      {
        BaseRefreshLayout.this.showRefresh();
        BaseRefreshLayout.this.setRefreshing(true);
        if (BaseRefreshLayout.this.mOnAutoLoadListener != null)
          BaseRefreshLayout.this.mOnAutoLoadListener.onAutoLoad();
      }
    }
    , 1000L);
  }

  protected boolean handlerPullTouchEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    if (!this.isEnableRefresh)
      return false;
    return super.handlerPullTouchEvent(paramMotionEvent, paramInt);
  }

  protected boolean handlerPushTouchEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    if (!this.isEnableLoadMore)
      return false;
    return super.handlerPushTouchEvent(paramMotionEvent, paramInt);
  }

  public void setEnableLoadMore(boolean paramBoolean)
  {
    this.isEnableLoadMore = paramBoolean;
  }

  public void setEnableRefresh(boolean paramBoolean)
  {
    this.isEnableRefresh = paramBoolean;
  }

  public void setOnAutoLoadListener(OnAutoLoadListener paramOnAutoLoadListener)
  {
    this.mOnAutoLoadListener = paramOnAutoLoadListener;
  }

  public void setOnLoadMoreListener(OnLoadMoreListener paramOnLoadMoreListener)
  {
    this.mOnLoadMoreListener = paramOnLoadMoreListener;
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mOnRefreshListener = paramOnRefreshListener;
  }

  public abstract void showRefresh();

  public static abstract interface OnAutoLoadListener
  {
    public abstract void onAutoLoad();
  }

  public static abstract interface OnLoadMoreListener
  {
    public abstract void onLoadMore();
  }

  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh();
  }
}
