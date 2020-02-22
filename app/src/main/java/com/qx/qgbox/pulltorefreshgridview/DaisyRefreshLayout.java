package com.qx.qgbox.pulltorefreshgridview;

import android.content.Context;
import android.util.AttributeSet;

public class DaisyRefreshLayout extends BaseRefreshLayout
{
  private DaisyFooterView mDaisyFooterView;
  private DaisyHeaderView mDaisyHeaderView;

  public DaisyRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mDaisyHeaderView = new DaisyHeaderView(paramContext);
    this.mDaisyFooterView = new DaisyFooterView(paramContext);
    setHeaderView(this.mDaisyHeaderView);
    setFooterView(this.mDaisyFooterView);
    setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener()
    {
      public void onPullDistance(int paramAnonymousInt)
      {
      }

      public void onPullEnable(boolean paramAnonymousBoolean)
      {
        DaisyRefreshLayout.this.mDaisyHeaderView.onPullEnable(paramAnonymousBoolean);
      }

      public void onRefresh()
      {
        DaisyRefreshLayout.this.mDaisyHeaderView.onRefresh();
        if (DaisyRefreshLayout.this.mOnRefreshListener != null)
          DaisyRefreshLayout.this.mOnRefreshListener.onRefresh();
      }
    });
    setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener()
    {
      public void onLoadMore()
      {
        DaisyRefreshLayout.this.mDaisyFooterView.onLoadMore();
        if (DaisyRefreshLayout.this.mOnLoadMoreListener != null)
          DaisyRefreshLayout.this.mOnLoadMoreListener.onLoadMore();
      }

      public void onPushDistance(int paramAnonymousInt)
      {
      }

      public void onPushEnable(boolean paramAnonymousBoolean)
      {
        DaisyRefreshLayout.this.mDaisyFooterView.onPushEnable(paramAnonymousBoolean);
      }
    });
  }

  public void setLoadMore(boolean paramBoolean)
  {
    this.mDaisyFooterView.setLoadMore(paramBoolean);
    super.setLoadMore(paramBoolean);
  }

  public void setRefreshing(boolean paramBoolean)
  {
    this.mDaisyHeaderView.setRefreshing(paramBoolean);
    super.setRefreshing(paramBoolean);
  }

  public void showRefresh()
  {
    if (this.mDaisyHeaderView != null)
      this.mDaisyHeaderView.onRefresh();
  }
}
