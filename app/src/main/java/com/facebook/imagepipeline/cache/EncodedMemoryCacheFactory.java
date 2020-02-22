package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.PooledByteBuffer;

public class EncodedMemoryCacheFactory
{
  public static InstrumentedMemoryCache<CacheKey, PooledByteBuffer> get(CountingMemoryCache<CacheKey, PooledByteBuffer> paramCountingMemoryCache, ImageCacheStatsTracker paramImageCacheStatsTracker)
  {
    paramImageCacheStatsTracker.registerEncodedMemoryCache(paramCountingMemoryCache);
    return new InstrumentedMemoryCache(paramCountingMemoryCache, new MemoryCacheTracker()
    {
      public void onCacheHit(CacheKey paramAnonymousCacheKey)
      {
        this.val$imageCacheStatsTracker.onMemoryCacheHit(paramAnonymousCacheKey);
      }

      public void onCacheMiss()
      {
        this.val$imageCacheStatsTracker.onMemoryCacheMiss();
      }

      public void onCachePut()
      {
        this.val$imageCacheStatsTracker.onMemoryCachePut();
      }
    });
  }
}