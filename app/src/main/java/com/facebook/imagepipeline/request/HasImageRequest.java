package com.facebook.imagepipeline.request;

import javax.annotation.Nullable;

public abstract interface HasImageRequest
{
  @Nullable
  public abstract ImageRequest getImageRequest();
}