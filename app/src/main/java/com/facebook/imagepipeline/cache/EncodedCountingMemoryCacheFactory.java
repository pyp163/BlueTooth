package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;

public class EncodedCountingMemoryCacheFactory
{
  public static CountingMemoryCache<CacheKey, PooledByteBuffer> get(Supplier<MemoryCacheParams> paramSupplier, MemoryTrimmableRegistry paramMemoryTrimmableRegistry)
  {
    paramSupplier = new CountingMemoryCache(new ValueDescriptor()
    {
      public int getSizeInBytes(PooledByteBuffer paramAnonymousPooledByteBuffer)
      {
        return paramAnonymousPooledByteBuffer.size();
      }
    }
    , new NativeMemoryCacheTrimStrategy(), paramSupplier);
    paramMemoryTrimmableRegistry.registerMemoryTrimmable(paramSupplier);
    return paramSupplier;
  }
}