package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Objects.ToStringHelper;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class ImagePerfData
{
  public static final int UNSET = -1;

  @Nullable
  private final Object mCallerContext;
  private final long mControllerCancelTimeMs;
  private final long mControllerFailureTimeMs;
  private final long mControllerFinalImageSetTimeMs;

  @Nullable
  private final String mControllerId;
  private final long mControllerIntermediateImageSetTimeMs;
  private final long mControllerSubmitTimeMs;

  @Nullable
  private final ImageInfo mImageInfo;
  private final int mImageOrigin;

  @Nullable
  private final ImageRequest mImageRequest;
  private final long mImageRequestEndTimeMs;
  private final long mImageRequestStartTimeMs;
  private final boolean mIsCanceled;
  private final boolean mIsPrefetch;
  private final boolean mIsSuccessful;

  @Nullable
  private final String mRequestId;

  public ImagePerfData(@Nullable String paramString1, @Nullable String paramString2, @Nullable ImageRequest paramImageRequest, @Nullable Object paramObject, @Nullable ImageInfo paramImageInfo, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.mControllerId = paramString1;
    this.mRequestId = paramString2;
    this.mImageRequest = paramImageRequest;
    this.mCallerContext = paramObject;
    this.mImageInfo = paramImageInfo;
    this.mControllerSubmitTimeMs = paramLong1;
    this.mControllerIntermediateImageSetTimeMs = paramLong2;
    this.mControllerFinalImageSetTimeMs = paramLong3;
    this.mControllerFailureTimeMs = paramLong4;
    this.mControllerCancelTimeMs = paramLong5;
    this.mImageRequestStartTimeMs = paramLong6;
    this.mImageRequestEndTimeMs = paramLong7;
    this.mImageOrigin = paramInt;
    this.mIsCanceled = paramBoolean1;
    this.mIsSuccessful = paramBoolean2;
    this.mIsPrefetch = paramBoolean3;
  }

  public String createDebugString()
  {
    return Objects.toStringHelper(this).add("controller ID", this.mControllerId).add("request ID", this.mRequestId).add("controller submit", this.mControllerSubmitTimeMs).add("controller final image", this.mControllerFinalImageSetTimeMs).add("controller failure", this.mControllerFailureTimeMs).add("controller cancel", this.mControllerCancelTimeMs).add("start time", this.mImageRequestStartTimeMs).add("end time", this.mImageRequestEndTimeMs).add("origin", ImageOriginUtils.toString(this.mImageOrigin)).add("canceled", this.mIsCanceled).add("successful", this.mIsSuccessful).add("prefetch", this.mIsPrefetch).add("caller context", this.mCallerContext).add("image request", this.mImageRequest).add("image info", this.mImageInfo).toString();
  }

  @Nullable
  public Object getCallerContext()
  {
    return this.mCallerContext;
  }

  public long getControllerFailureTimeMs()
  {
    return this.mControllerFailureTimeMs;
  }

  public long getControllerFinalImageSetTimeMs()
  {
    return this.mControllerFinalImageSetTimeMs;
  }

  @Nullable
  public String getControllerId()
  {
    return this.mControllerId;
  }

  public long getControllerIntermediateImageSetTimeMs()
  {
    return this.mControllerIntermediateImageSetTimeMs;
  }

  public long getControllerSubmitTimeMs()
  {
    return this.mControllerSubmitTimeMs;
  }

  public long getFinalImageLoadTimeMs()
  {
    if (isSuccessful())
      return getImageRequestEndTimeMs() - getImageRequestStartTimeMs();
    return -1L;
  }

  @Nullable
  public ImageInfo getImageInfo()
  {
    return this.mImageInfo;
  }

  public int getImageOrigin()
  {
    return this.mImageOrigin;
  }

  @Nullable
  public ImageRequest getImageRequest()
  {
    return this.mImageRequest;
  }

  public long getImageRequestEndTimeMs()
  {
    return this.mImageRequestEndTimeMs;
  }

  public long getImageRequestStartTimeMs()
  {
    return this.mImageRequestStartTimeMs;
  }

  public long getIntermediateImageLoadTimeMs()
  {
    if (isSuccessful())
      return getControllerIntermediateImageSetTimeMs() - getControllerSubmitTimeMs();
    return -1L;
  }

  @Nullable
  public String getRequestId()
  {
    return this.mRequestId;
  }

  public boolean isCanceled()
  {
    return this.mIsCanceled;
  }

  public boolean isPrefetch()
  {
    return this.mIsPrefetch;
  }

  public boolean isSuccessful()
  {
    return this.mIsSuccessful;
  }
}