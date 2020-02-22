package com.facebook.drawee.backends.pipeline.info.internal;

import android.graphics.drawable.Animatable;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import javax.annotation.Nullable;

public class ImagePerfControllerListener extends BaseControllerListener<ImageInfo>
{
  private final MonotonicClock mClock;
  private final ImagePerfMonitor mImagePerfMonitor;
  private final ImagePerfState mImagePerfState;

  public ImagePerfControllerListener(MonotonicClock paramMonotonicClock, ImagePerfState paramImagePerfState, ImagePerfMonitor paramImagePerfMonitor)
  {
    this.mClock = paramMonotonicClock;
    this.mImagePerfState = paramImagePerfState;
    this.mImagePerfMonitor = paramImagePerfMonitor;
  }

  public void onFailure(String paramString, Throwable paramThrowable)
  {
    this.mImagePerfState.setControllerFailureTimeMs(this.mClock.now());
    this.mImagePerfState.setControllerId(paramString);
    this.mImagePerfState.setSuccessful(false);
    this.mImagePerfMonitor.notifyListeners(this.mImagePerfState, 5);
  }

  public void onFinalImageSet(String paramString, @Nullable ImageInfo paramImageInfo, @Nullable Animatable paramAnimatable)
  {
    this.mImagePerfState.setControllerFinalImageSetTimeMs(this.mClock.now());
    this.mImagePerfState.setControllerId(paramString);
    this.mImagePerfState.setImageInfo(paramImageInfo);
    this.mImagePerfState.setSuccessful(true);
    this.mImagePerfMonitor.notifyListeners(this.mImagePerfState, 3);
  }

  public void onIntermediateImageSet(String paramString, @Nullable ImageInfo paramImageInfo)
  {
    this.mImagePerfState.setControllerIntermediateImageSetTimeMs(this.mClock.now());
    this.mImagePerfState.setControllerId(paramString);
    this.mImagePerfState.setImageInfo(paramImageInfo);
    this.mImagePerfMonitor.notifyListeners(this.mImagePerfState, 2);
  }

  public void onRelease(String paramString)
  {
    super.onRelease(paramString);
    int i = this.mImagePerfState.getImageLoadStatus();
    if ((i != 3) && (i != 5))
    {
      this.mImagePerfState.setControllerCancelTimeMs(this.mClock.now());
      this.mImagePerfState.setControllerId(paramString);
      this.mImagePerfState.setCanceled(true);
      this.mImagePerfMonitor.notifyListeners(this.mImagePerfState, 4);
    }
  }

  public void onSubmit(String paramString, Object paramObject)
  {
    this.mImagePerfState.setControllerSubmitTimeMs(this.mClock.now());
    this.mImagePerfState.setControllerId(paramString);
    this.mImagePerfState.setCallerContext(paramObject);
    this.mImagePerfMonitor.notifyListeners(this.mImagePerfState, 0);
  }
}