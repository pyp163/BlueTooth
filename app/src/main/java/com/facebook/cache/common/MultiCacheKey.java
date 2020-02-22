package com.facebook.cache.common;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;
import java.util.List;

public class MultiCacheKey
  implements CacheKey
{
  final List<CacheKey> mCacheKeys;

  public MultiCacheKey(List<CacheKey> paramList)
  {
    this.mCacheKeys = ((List)Preconditions.checkNotNull(paramList));
  }

  public boolean containsUri(Uri paramUri)
  {
    int i = 0;
    while (i < this.mCacheKeys.size())
    {
      if (((CacheKey)this.mCacheKeys.get(i)).containsUri(paramUri))
        return true;
      i += 1;
    }
    return false;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof MultiCacheKey))
    {
      paramObject = (MultiCacheKey)paramObject;
      return this.mCacheKeys.equals(paramObject.mCacheKeys);
    }
    return false;
  }

  public List<CacheKey> getCacheKeys()
  {
    return this.mCacheKeys;
  }

  public String getUriString()
  {
    return ((CacheKey)this.mCacheKeys.get(0)).getUriString();
  }

  public int hashCode()
  {
    return this.mCacheKeys.hashCode();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MultiCacheKey:");
    localStringBuilder.append(this.mCacheKeys.toString());
    return localStringBuilder.toString();
  }
}