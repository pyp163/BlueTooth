package com.facebook.drawee.backends.pipeline.info.internal;

import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;

public class ImagePerfRequestListener extends BaseRequestListener
{
  private final MonotonicClock mClock;
  private final ImagePerfState mImagePerfState;

  public ImagePerfRequestListener(MonotonicClock paramMonotonicClock, ImagePerfState paramImagePerfState)
  {
    this.mClock = paramMonotonicClock;
    this.mImagePerfState = paramImagePerfState;
  }

  public void onRequestCancellation(String paramString)
  {
    this.mImagePerfState.setImageRequestEndTimeMs(this.mClock.now());
    this.mImagePerfState.setRequestId(paramString);
    this.mImagePerfState.setCanceled(true);
  }

  public void onRequestFailure(ImageRequest paramImageRequest, String paramString, Throwable paramThrowable, boolean paramBoolean)
  {
    this.mImagePerfState.setImageRequestEndTimeMs(this.mClock.now());
    this.mImagePerfState.setImageRequest(paramImageRequest);
    this.mImagePerfState.setRequestId(paramString);
    this.mImagePerfState.setPrefetch(paramBoolean);
    this.mImagePerfState.setSuccessful(false);
  }

  public void onRequestStart(ImageRequest paramImageRequest, Object paramObject, String paramString, boolean paramBoolean)
  {
    this.mImagePerfState.setImageRequestStartTimeMs(this.mClock.now());
    this.mImagePerfState.setImageRequest(paramImageRequest);
    this.mImagePerfState.setCallerContext(paramObject);
    this.mImagePerfState.setRequestId(paramString);
    this.mImagePerfState.setPrefetch(paramBoolean);
  }

  public void onRequestSuccess(ImageRequest paramImageRequest, String paramString, boolean paramBoolean)
  {
    this.mImagePerfState.setImageRequestEndTimeMs(this.mClock.now());
    this.mImagePerfState.setImageRequest(paramImageRequest);
    this.mImagePerfState.setRequestId(paramString);
    this.mImagePerfState.setPrefetch(paramBoolean);
    this.mImagePerfState.setSuccessful(true);
  }
}