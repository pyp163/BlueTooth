package com.qx.qgbox.adapters;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.qx.qgbox.R;

import java.util.ArrayList;
import java.util.LinkedList;
import me.relex.photodraweeview.PhotoDraweeView;

public class CarImagesBrowseAdapter extends PagerAdapter
{
  private int mChildCount;
  private Context mContext;
  private ArrayList<String> mDatas;
  private LayoutInflater mInflater;
  private LinkedList<View> mViewCache;

  public CarImagesBrowseAdapter(Context paramContext, ArrayList<String> paramArrayList)
  {
    this.mContext = paramContext;
    this.mDatas = paramArrayList;
    this.mViewCache = new LinkedList();
    this.mInflater = LayoutInflater.from(this.mContext);
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramObject = (View)paramObject;
    paramViewGroup.removeView(paramObject);
    this.mViewCache.add(paramObject);
  }

  public int getCount()
  {
    return this.mDatas.size();
  }

  public int getItemPosition(Object paramObject)
  {
    if (this.mChildCount > 0)
    {
      this.mChildCount -= 1;
      return -2;
    }
    return super.getItemPosition(paramObject);
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    Object localObject;
    if (this.mViewCache.size() == 0)
    {
      localObject = new PhotoDraweeView(this.mContext);
      ((PhotoDraweeView)localObject).setBackgroundColor(-16777216);//-16777216
    }
    else
    {
      localObject = (View)this.mViewCache.removeFirst();
    }
    final PhotoDraweeView localPhotoDraweeView = (PhotoDraweeView)localObject;
    PipelineDraweeControllerBuilder localPipelineDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
    localPipelineDraweeControllerBuilder.setUri(Uri.parse((String)this.mDatas.get(paramInt)));
    localPipelineDraweeControllerBuilder.setOldController(localPhotoDraweeView.getController());
    localPipelineDraweeControllerBuilder.setControllerListener(new BaseControllerListener()
    {
      public void onFinalImageSet(String paramAnonymousString, ImageInfo paramAnonymousImageInfo, Animatable paramAnonymousAnimatable)
      {
        super.onFinalImageSet(paramAnonymousString, paramAnonymousImageInfo, paramAnonymousAnimatable);
        if (paramAnonymousImageInfo == null)
          return;
        localPhotoDraweeView.update(paramAnonymousImageInfo.getWidth(), paramAnonymousImageInfo.getHeight());
      }
    });
    localPhotoDraweeView.setController(localPipelineDraweeControllerBuilder.build());
    paramViewGroup.addView((View)localObject, -1, -1);
    return localObject;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }

  public void notifyDataSetChanged()
  {
    this.mChildCount = getCount();
    super.notifyDataSetChanged();
  }
}
