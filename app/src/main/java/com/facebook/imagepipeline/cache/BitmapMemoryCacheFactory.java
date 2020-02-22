package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapMemoryCacheFactory
{
  public static InstrumentedMemoryCache<CacheKey, CloseableImage> get(CountingMemoryCache<CacheKey, CloseableImage> paramCountingMemoryCache, ImageCacheStatsTracker paramImageCacheStatsTracker)
  {
    paramImageCacheStatsTracker.registerBitmapMemoryCache(paramCountingMemoryCache);
    return new InstrumentedMemoryCache(paramCountingMemoryCache, new MemoryCacheTracker()
    {
      public void onCacheHit(CacheKey paramAnonymousCacheKey)
      {
        this.val$imageCacheStatsTracker.onBitmapCacheHit(paramAnonymousCacheKey);
      }

      public void onCacheMiss()
      {
        this.val$imageCacheStatsTracker.onBitmapCacheMiss();
      }

      public void onCachePut()
      {
        this.val$imageCacheStatsTracker.onBitmapCachePut();
      }
    });
  }
}