package com.qx.qgbox.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class ImagesPagerAdapter2 extends PagerAdapter
{
  private Context context;
  private SimpleDraweeView simpleDraweeView;
  private List<SimpleDraweeView> simpleDraweeViewList;
  private ViewPager viewPager;

  public ImagesPagerAdapter2(List<SimpleDraweeView> paramList, ViewPager paramViewPager, Context paramContext)
  {
    this.simpleDraweeViewList = paramList;
    this.viewPager = paramViewPager;
    this.context = paramContext;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    this.viewPager.removeView((View)this.simpleDraweeViewList.get(paramInt % this.simpleDraweeViewList.size()));
  }

  public int getCount()
  {
    return this.simpleDraweeViewList.size();
  }

  public int getItemPosition(Object paramObject)
  {
    return -2;
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    this.simpleDraweeView = ((SimpleDraweeView)this.simpleDraweeViewList.get(paramInt % this.simpleDraweeViewList.size()));
    this.viewPager.addView(this.simpleDraweeView);
    return this.simpleDraweeView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}
