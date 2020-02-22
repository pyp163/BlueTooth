package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;

public class EncodedMemoryCacheProducer
  implements Producer<EncodedImage>
{
  public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
  public static final String PRODUCER_NAME = "EncodedMemoryCacheProducer";
  private final CacheKeyFactory mCacheKeyFactory;
  private final Producer<EncodedImage> mInputProducer;
  private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;

  public EncodedMemoryCacheProducer(MemoryCache<CacheKey, PooledByteBuffer> paramMemoryCache, CacheKeyFactory paramCacheKeyFactory, Producer<EncodedImage> paramProducer)
  {
    this.mMemoryCache = paramMemoryCache;
    this.mCacheKeyFactory = paramCacheKeyFactory;
    this.mInputProducer = paramProducer;
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    String str = paramProducerContext.getId();
    ProducerListener localProducerListener = paramProducerContext.getListener();
    localProducerListener.onProducerStart(str, "EncodedMemoryCacheProducer");
    Object localObject = paramProducerContext.getImageRequest();
    CacheKey localCacheKey = this.mCacheKeyFactory.getEncodedCacheKey((ImageRequest)localObject, paramProducerContext.getCallerContext());
    CloseableReference localCloseableReference = this.mMemoryCache.get(localCacheKey);
    EncodedImage localEncodedImage = null;
    localObject = null;
    if (localCloseableReference != null);
    try
    {
      localEncodedImage = new EncodedImage(localCloseableReference);
      paramProducerContext = (ProducerContext)localObject;
      try
      {
        if (localProducerListener.requiresExtraMap(str))
          paramProducerContext = ImmutableMap.of("cached_value_found", "true");
        localProducerListener.onProducerFinishWithSuccess(str, "EncodedMemoryCacheProducer", paramProducerContext);
        localProducerListener.onUltimateProducerReached(str, "EncodedMemoryCacheProducer", true);
        paramConsumer.onProgressUpdate(1.0F);
        paramConsumer.onNewResult(localEncodedImage, 1);
        EncodedImage.closeSafely(localEncodedImage);
        return;
      }
      finally
      {
        EncodedImage.closeSafely(localEncodedImage);
      }
      if (paramProducerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue())
      {
        if (localProducerListener.requiresExtraMap(str))
        {
          paramProducerContext = ImmutableMap.of("cached_value_found", "false");
          localProducerListener.onProducerFinishWithSuccess(str, "EncodedMemoryCacheProducer", paramProducerContext);
          localProducerListener.onUltimateProducerReached(str, "EncodedMemoryCacheProducer", false);
          paramConsumer.onNewResult(null, 1);
          CloseableReference.closeSafely(localCloseableReference);
        }
      }
      else
      {
        boolean bool = paramProducerContext.getImageRequest().isMemoryCacheEnabled();
        localObject = new EncodedMemoryCacheConsumer(paramConsumer, this.mMemoryCache, localCacheKey, bool);
        paramConsumer = localEncodedImage;
        if (localProducerListener.requiresExtraMap(str))
          paramConsumer = ImmutableMap.of("cached_value_found", "false");
        localProducerListener.onProducerFinishWithSuccess(str, "EncodedMemoryCacheProducer", paramConsumer);
        this.mInputProducer.produceResults((Consumer)localObject, paramProducerContext);
        CloseableReference.closeSafely(localCloseableReference);
        return;
        CloseableReference.closeSafely(localCloseableReference);
        throw paramConsumer;
      }
    }
    finally
    {
      while (true)
      {
        continue;
        paramProducerContext = null;
      }
    }
  }

  private static class EncodedMemoryCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private final boolean mIsMemoryCacheEnabled;
    private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;
    private final CacheKey mRequestedCacheKey;

    public EncodedMemoryCacheConsumer(Consumer<EncodedImage> paramConsumer, MemoryCache<CacheKey, PooledByteBuffer> paramMemoryCache, CacheKey paramCacheKey, boolean paramBoolean)
    {
      super();
      this.mMemoryCache = paramMemoryCache;
      this.mRequestedCacheKey = paramCacheKey;
      this.mIsMemoryCacheEnabled = paramBoolean;
    }

    public void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      if ((!isNotLast(paramInt)) && (paramEncodedImage != null) && (!statusHasAnyFlag(paramInt, 10)))
      {
        Object localObject = paramEncodedImage.getByteBufferRef();
        if (localObject != null)
        {
          CloseableReference localCloseableReference = null;
          try
          {
            if (this.mIsMemoryCacheEnabled)
              localCloseableReference = this.mMemoryCache.cache(this.mRequestedCacheKey, (CloseableReference)localObject);
            CloseableReference.closeSafely((CloseableReference)localObject);
            if (localCloseableReference != null)
              try
              {
                localObject = new EncodedImage(localCloseableReference);
                ((EncodedImage)localObject).copyMetaDataFrom(paramEncodedImage);
                CloseableReference.closeSafely(localCloseableReference);
                try
                {
                  getConsumer().onProgressUpdate(1.0F);
                  getConsumer().onNewResult(localObject, paramInt);
                  return;
                }
                finally
                {
                  EncodedImage.closeSafely((EncodedImage)localObject);
                }
              }
              finally
              {
                CloseableReference.closeSafely(localCloseableReference);
              }
          }
          finally
          {
            CloseableReference.closeSafely((CloseableReference)localObject);
          }
        }
        getConsumer().onNewResult(paramEncodedImage, paramInt);
        return;
      }
      getConsumer().onNewResult(paramEncodedImage, paramInt);
    }
  }
}