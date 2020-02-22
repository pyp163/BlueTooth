package com.facebook.imagepipeline.animated.factory;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.image.CloseableImage;
import java.lang.reflect.Constructor;

public class AnimatedFactoryProvider
{
  private static AnimatedFactory sImpl;
  private static boolean sImplLoaded;

  public static AnimatedFactory getAnimatedFactory(PlatformBitmapFactory paramPlatformBitmapFactory, ExecutorSupplier paramExecutorSupplier, CountingMemoryCache<CacheKey, CloseableImage> paramCountingMemoryCache)
  {
    if (!sImplLoaded);
    try
    {
      sImpl = (AnimatedFactory)Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(new Class[] { PlatformBitmapFactory.class, ExecutorSupplier.class, CountingMemoryCache.class }).newInstance(new Object[] { paramPlatformBitmapFactory, paramExecutorSupplier, paramCountingMemoryCache });
      label58: if (sImpl != null)
        sImplLoaded = true;
      return sImpl;
    }
    catch (Throwable paramPlatformBitmapFactory)
    {
      break label58;
    }
  }
}