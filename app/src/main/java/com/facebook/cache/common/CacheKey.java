package com.facebook.cache.common;

import android.net.Uri;

public abstract interface CacheKey
{
  public abstract boolean containsUri(Uri paramUri);

  public abstract boolean equals(Object paramObject);

  public abstract String getUriString();

  public abstract int hashCode();

  public abstract String toString();
}