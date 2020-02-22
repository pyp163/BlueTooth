package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class StagingArea
{
  private static final Class<?> TAG = StagingArea.class;

  @GuardedBy("this")
  private Map<CacheKey, EncodedImage> mMap = new HashMap();

  public static StagingArea getInstance()
  {
    return new StagingArea();
  }

  private void logStats()
  {
    try
    {
      FLog.v(TAG, "Count = %d", Integer.valueOf(this.mMap.size()));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void clearAll()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.mMap.values());
      this.mMap.clear();
      int i = 0;
      while (i < localArrayList.size())
      {
        EncodedImage localEncodedImage = (EncodedImage)localArrayList.get(i);
        if (localEncodedImage != null)
          localEncodedImage.close();
        i += 1;
      }
      return;
    }
    finally
    {
    }
  }

  public boolean containsKey(CacheKey paramCacheKey)
  {
    try
    {
      Preconditions.checkNotNull(paramCacheKey);
      boolean bool = this.mMap.containsKey(paramCacheKey);
      if (!bool)
        return false;
      synchronized ((EncodedImage)this.mMap.get(paramCacheKey))
      {
        if (!EncodedImage.isValid(???))
        {
          this.mMap.remove(paramCacheKey);
          FLog.w(TAG, "Found closed reference %d for key %s (%d)", new Object[] { Integer.valueOf(System.identityHashCode(???)), paramCacheKey.getUriString(), Integer.valueOf(System.identityHashCode(paramCacheKey)) });
          return false;
        }
        return true;
      }
    }
    finally
    {
    }
  }

  public EncodedImage get(CacheKey paramCacheKey)
  {
    try
    {
      Preconditions.checkNotNull(paramCacheKey);
      EncodedImage localEncodedImage = (EncodedImage)this.mMap.get(paramCacheKey);
      if (localEncodedImage != null)
        try
        {
          if (!EncodedImage.isValid(localEncodedImage))
          {
            this.mMap.remove(paramCacheKey);
            FLog.w(TAG, "Found closed reference %d for key %s (%d)", new Object[] { Integer.valueOf(System.identityHashCode(localEncodedImage)), paramCacheKey.getUriString(), Integer.valueOf(System.identityHashCode(paramCacheKey)) });
            return null;
          }
          paramCacheKey = EncodedImage.cloneOrNull(localEncodedImage);
        }
        finally
        {
        }
      else
        paramCacheKey = localEncodedImage;
      return paramCacheKey;
    }
    finally
    {
    }
    throw paramCacheKey;
  }

  public void put(CacheKey paramCacheKey, EncodedImage paramEncodedImage)
  {
    try
    {
      Preconditions.checkNotNull(paramCacheKey);
      Preconditions.checkArgument(EncodedImage.isValid(paramEncodedImage));
      EncodedImage.closeSafely((EncodedImage)this.mMap.put(paramCacheKey, EncodedImage.cloneOrNull(paramEncodedImage)));
      logStats();
      return;
    }
    finally
    {
      paramCacheKey = finally;
    }
    throw paramCacheKey;
  }

  public boolean remove(CacheKey paramCacheKey)
  {
    Preconditions.checkNotNull(paramCacheKey);
    try
    {
      paramCacheKey = (EncodedImage)this.mMap.remove(paramCacheKey);
      if (paramCacheKey == null)
        return false;
      try
      {
        boolean bool = paramCacheKey.isValid();
        return bool;
      }
      finally
      {
        paramCacheKey.close();
      }
    }
    finally
    {
    }
    throw paramCacheKey;
  }

  public boolean remove(CacheKey paramCacheKey, EncodedImage paramEncodedImage)
  {
    try
    {
      Preconditions.checkNotNull(paramCacheKey);
      Preconditions.checkNotNull(paramEncodedImage);
      Preconditions.checkArgument(EncodedImage.isValid(paramEncodedImage));
      EncodedImage localEncodedImage = (EncodedImage)this.mMap.get(paramCacheKey);
      if (localEncodedImage == null)
        return false;
      CloseableReference localCloseableReference = localEncodedImage.getByteBufferRef();
      paramEncodedImage = paramEncodedImage.getByteBufferRef();
      if ((localCloseableReference != null) && (paramEncodedImage != null))
        try
        {
          if (localCloseableReference.get() == paramEncodedImage.get())
          {
            this.mMap.remove(paramCacheKey);
            CloseableReference.closeSafely(paramEncodedImage);
            CloseableReference.closeSafely(localCloseableReference);
            EncodedImage.closeSafely(localEncodedImage);
            logStats();
            return true;
          }
        }
        finally
        {
          CloseableReference.closeSafely(paramEncodedImage);
          CloseableReference.closeSafely(localCloseableReference);
          EncodedImage.closeSafely(localEncodedImage);
        }
      CloseableReference.closeSafely(paramEncodedImage);
      CloseableReference.closeSafely(localCloseableReference);
      EncodedImage.closeSafely(localEncodedImage);
      return false;
    }
    finally
    {
    }
    throw paramCacheKey;
  }
}