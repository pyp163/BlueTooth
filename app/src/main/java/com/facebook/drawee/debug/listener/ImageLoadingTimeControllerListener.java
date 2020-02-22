package com.facebook.drawee.debug.listener;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import javax.annotation.Nullable;

public class ImageLoadingTimeControllerListener extends BaseControllerListener
{
  private long mFinalImageSetTimeMs = -1L;

  @Nullable
  private ImageLoadingTimeListener mImageLoadingTimeListener;
  private long mRequestSubmitTimeMs = -1L;

  public ImageLoadingTimeControllerListener(@Nullable ImageLoadingTimeListener paramImageLoadingTimeListener)
  {
    this.mImageLoadingTimeListener = paramImageLoadingTimeListener;
  }

  public void onFinalImageSet(String paramString, @Nullable Object paramObject, @Nullable Animatable paramAnimatable)
  {
    this.mFinalImageSetTimeMs = System.currentTimeMillis();
    if (this.mImageLoadingTimeListener != null)
      this.mImageLoadingTimeListener.onFinalImageSet(this.mFinalImageSetTimeMs - this.mRequestSubmitTimeMs);
  }

  public void onSubmit(String paramString, Object paramObject)
  {
    this.mRequestSubmitTimeMs = System.currentTimeMillis();
  }
}