package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.listener.BaseRequestListener;
import javax.annotation.Nullable;

public class ImageOriginRequestListener extends BaseRequestListener
{
  private String mControllerId;

  @Nullable
  private final ImageOriginListener mImageOriginLister;

  public ImageOriginRequestListener(String paramString, @Nullable ImageOriginListener paramImageOriginListener)
  {
    this.mImageOriginLister = paramImageOriginListener;
    init(paramString);
  }

  public void init(String paramString)
  {
    this.mControllerId = paramString;
  }

  public void onUltimateProducerReached(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (this.mImageOriginLister != null)
      this.mImageOriginLister.onImageLoaded(this.mControllerId, ImageOriginUtils.mapProducerNameToImageOrigin(paramString2), paramBoolean);
  }
}