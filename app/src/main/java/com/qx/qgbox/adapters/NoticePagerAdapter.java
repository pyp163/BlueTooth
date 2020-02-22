package com.qx.qgbox.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class NoticePagerAdapter extends PagerAdapter
{
  private List<View> views;

  public NoticePagerAdapter(List<View> paramList)
  {
    this.views = paramList;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)this.views.get(paramInt));
  }

  public int getCount()
  {
    return this.views.size();
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup.addView((View)this.views.get(paramInt), 0);
    return this.views.get(paramInt);
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}
