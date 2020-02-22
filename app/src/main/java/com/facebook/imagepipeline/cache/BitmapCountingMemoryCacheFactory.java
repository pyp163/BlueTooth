package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.image.CloseableImage;

public class BitmapCountingMemoryCacheFactory
{
  public static CountingMemoryCache<CacheKey, CloseableImage> get(Supplier<MemoryCacheParams> paramSupplier, MemoryTrimmableRegistry paramMemoryTrimmableRegistry)
  {
    return get(paramSupplier, paramMemoryTrimmableRegistry, new BitmapMemoryCacheTrimStrategy());
  }

  public static CountingMemoryCache<CacheKey, CloseableImage> get(Supplier<MemoryCacheParams> paramSupplier, MemoryTrimmableRegistry paramMemoryTrimmableRegistry, CountingMemoryCache.CacheTrimStrategy paramCacheTrimStrategy)
  {
    paramSupplier = new CountingMemoryCache(new ValueDescriptor()
    {
      public int getSizeInBytes(CloseableImage paramAnonymousCloseableImage)
      {
        return paramAnonymousCloseableImage.getSizeInBytes();
      }
    }
    , paramCacheTrimStrategy, paramSupplier);
    paramMemoryTrimmableRegistry.registerMemoryTrimmable(paramSupplier);
    return paramSupplier;
  }
}