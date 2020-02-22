package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class ImagePerfState
{

  @Nullable
  private Object mCallerContext;
  private long mControllerCancelTimeMs = -1L;
  private long mControllerFailureTimeMs = -1L;
  private long mControllerFinalImageSetTimeMs = -1L;

  @Nullable
  private String mControllerId;
  private long mControllerIntermediateImageSetTimeMs = -1L;
  private long mControllerSubmitTimeMs = -1L;

  @Nullable
  private ImageInfo mImageInfo;
  private int mImageLoadStatus = -1;
  private int mImageOrigin = -1;

  @Nullable
  private ImageRequest mImageRequest;
  private long mImageRequestEndTimeMs = -1L;
  private long mImageRequestStartTimeMs = -1L;
  private boolean mIsCanceled;
  private boolean mIsPrefetch;
  private boolean mIsSuccessful;

  @Nullable
  private String mRequestId;

  public int getImageLoadStatus()
  {
    return this.mImageLoadStatus;
  }

  public void reset()
  {
    this.mRequestId = null;
    this.mImageRequest = null;
    this.mCallerContext = null;
    this.mImageInfo = null;
    this.mControllerSubmitTimeMs = -1L;
    this.mControllerFinalImageSetTimeMs = -1L;
    this.mControllerFailureTimeMs = -1L;
    this.mControllerCancelTimeMs = -1L;
    this.mImageRequestStartTimeMs = -1L;
    this.mImageRequestEndTimeMs = -1L;
    this.mImageOrigin = -1;
    this.mIsCanceled = false;
    this.mIsSuccessful = false;
    this.mIsPrefetch = false;
    this.mImageLoadStatus = -1;
  }

  public void setCallerContext(@Nullable Object paramObject)
  {
    this.mCallerContext = paramObject;
  }

  public void setCanceled(boolean paramBoolean)
  {
    this.mIsCanceled = paramBoolean;
  }

  public void setControllerCancelTimeMs(long paramLong)
  {
    this.mControllerCancelTimeMs = paramLong;
  }

  public void setControllerFailureTimeMs(long paramLong)
  {
    this.mControllerFailureTimeMs = paramLong;
  }

  public void setControllerFinalImageSetTimeMs(long paramLong)
  {
    this.mControllerFinalImageSetTimeMs = paramLong;
  }

  public void setControllerId(@Nullable String paramString)
  {
    this.mControllerId = paramString;
  }

  public void setControllerIntermediateImageSetTimeMs(long paramLong)
  {
    this.mControllerIntermediateImageSetTimeMs = paramLong;
  }

  public void setControllerSubmitTimeMs(long paramLong)
  {
    this.mControllerSubmitTimeMs = paramLong;
  }

  public void setImageInfo(@Nullable ImageInfo paramImageInfo)
  {
    this.mImageInfo = paramImageInfo;
  }

  public void setImageLoadStatus(int paramInt)
  {
    this.mImageLoadStatus = paramInt;
  }

  public void setImageOrigin(int paramInt)
  {
    this.mImageOrigin = paramInt;
  }

  public void setImageRequest(@Nullable ImageRequest paramImageRequest)
  {
    this.mImageRequest = paramImageRequest;
  }

  public void setImageRequestEndTimeMs(long paramLong)
  {
    this.mImageRequestEndTimeMs = paramLong;
  }

  public void setImageRequestStartTimeMs(long paramLong)
  {
    this.mImageRequestStartTimeMs = paramLong;
  }

  public void setPrefetch(boolean paramBoolean)
  {
    this.mIsPrefetch = paramBoolean;
  }

  public void setRequestId(@Nullable String paramString)
  {
    this.mRequestId = paramString;
  }

  public void setSuccessful(boolean paramBoolean)
  {
    this.mIsSuccessful = paramBoolean;
  }

  public ImagePerfData snapshot()
  {
    return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mImageOrigin, this.mIsCanceled, this.mIsSuccessful, this.mIsPrefetch);
  }
}