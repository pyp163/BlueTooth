package com.facebook.cache.common;

import android.net.Uri;
import javax.annotation.Nullable;

public class DebuggingCacheKey extends SimpleCacheKey
{
  private final Object mCallerContext;
  private final Uri mSourceUri;

  public DebuggingCacheKey(String paramString, @Nullable Object paramObject, Uri paramUri)
  {
    super(paramString);
    this.mCallerContext = paramObject;
    this.mSourceUri = paramUri;
  }

  @Nullable
  public Object getCallerContext()
  {
    return this.mCallerContext;
  }

  public Uri getSourceUri()
  {
    return this.mSourceUri;
  }
}