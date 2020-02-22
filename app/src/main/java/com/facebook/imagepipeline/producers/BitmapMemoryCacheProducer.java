package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import java.util.Map;

public class BitmapMemoryCacheProducer
  implements Producer<CloseableReference<CloseableImage>>
{
  public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
  public static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
  private final CacheKeyFactory mCacheKeyFactory;
  private final Producer<CloseableReference<CloseableImage>> mInputProducer;
  private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

  public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> paramMemoryCache, CacheKeyFactory paramCacheKeyFactory, Producer<CloseableReference<CloseableImage>> paramProducer)
  {
    this.mMemoryCache = paramMemoryCache;
    this.mCacheKeyFactory = paramCacheKeyFactory;
    this.mInputProducer = paramProducer;
  }

  protected String getProducerName()
  {
    return "BitmapMemoryCacheProducer";
  }

  public void produceResults(Consumer<CloseableReference<CloseableImage>> paramConsumer, ProducerContext paramProducerContext)
  {
    ProducerListener localProducerListener = paramProducerContext.getListener();
    String str1 = paramProducerContext.getId();
    localProducerListener.onProducerStart(str1, getProducerName());
    Object localObject1 = paramProducerContext.getImageRequest();
    Object localObject2 = paramProducerContext.getCallerContext();
    Object localObject3 = this.mCacheKeyFactory.getBitmapCacheKey((ImageRequest)localObject1, localObject2);
    CloseableReference localCloseableReference = this.mMemoryCache.get(localObject3);
    localObject2 = null;
    if (localCloseableReference != null)
    {
      boolean bool = ((CloseableImage)localCloseableReference.get()).getQualityInfo().isOfFullQuality();
      if (bool)
      {
        String str2 = getProducerName();
        if (localProducerListener.requiresExtraMap(str1))
          localObject1 = ImmutableMap.of("cached_value_found", "true");
        else
          localObject1 = null;
        localProducerListener.onProducerFinishWithSuccess(str1, str2, (Map)localObject1);
        localProducerListener.onUltimateProducerReached(str1, getProducerName(), true);
        paramConsumer.onProgressUpdate(1.0F);
      }
      paramConsumer.onNewResult(localCloseableReference, BaseConsumer.simpleStatusForIsLast(bool));
      localCloseableReference.close();
      if (bool)
        return;
    }
    if (paramProducerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE.getValue())
    {
      localObject1 = getProducerName();
      if (localProducerListener.requiresExtraMap(str1))
        paramProducerContext = ImmutableMap.of("cached_value_found", "false");
      else
        paramProducerContext = null;
      localProducerListener.onProducerFinishWithSuccess(str1, (String)localObject1, paramProducerContext);
      localProducerListener.onUltimateProducerReached(str1, getProducerName(), false);
      paramConsumer.onNewResult(null, 1);
      return;
    }
    localObject1 = wrapConsumer(paramConsumer, (CacheKey)localObject3, paramProducerContext.getImageRequest().isMemoryCacheEnabled());
    localObject3 = getProducerName();
    paramConsumer = localObject2;
    if (localProducerListener.requiresExtraMap(str1))
      paramConsumer = ImmutableMap.of("cached_value_found", "false");
    localProducerListener.onProducerFinishWithSuccess(str1, (String)localObject3, paramConsumer);
    this.mInputProducer.produceResults((Consumer)localObject1, paramProducerContext);
  }

  protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> paramConsumer, final CacheKey paramCacheKey, final boolean paramBoolean)
  {
    return new DelegatingConsumer(paramConsumer)
    {
      public void onNewResultImpl(CloseableReference<CloseableImage> paramAnonymousCloseableReference, int paramAnonymousInt)
      {
        boolean bool = isLast(paramAnonymousInt);
        CloseableReference localCloseableReference = null;
        if (paramAnonymousCloseableReference == null)
        {
          if (bool)
            getConsumer().onNewResult(null, paramAnonymousInt);
          return;
        }
        Object localObject;
        if ((!((CloseableImage)paramAnonymousCloseableReference.get()).isStateful()) && (!statusHasFlag(paramAnonymousInt, 8)))
        {
          if (!bool)
          {
            localObject = BitmapMemoryCacheProducer.this.mMemoryCache.get(paramCacheKey);
            if (localObject != null)
              try
              {
                QualityInfo localQualityInfo1 = ((CloseableImage)paramAnonymousCloseableReference.get()).getQualityInfo();
                QualityInfo localQualityInfo2 = ((CloseableImage)((CloseableReference)localObject).get()).getQualityInfo();
                if (!localQualityInfo2.isOfFullQuality())
                {
                  int i = localQualityInfo2.getQuality();
                  int j = localQualityInfo1.getQuality();
                  if (i < j)
                  {
                    CloseableReference.closeSafely((CloseableReference)localObject);
                    break label178;
                  }
                }
                getConsumer().onNewResult(localObject, paramAnonymousInt);
                return;
              }
              finally
              {
                CloseableReference.closeSafely((CloseableReference)localObject);
              }
          }
          label178: if (paramBoolean)
            localCloseableReference = BitmapMemoryCacheProducer.this.mMemoryCache.cache(paramCacheKey, paramAnonymousCloseableReference);
          if (!bool);
        }
        try
        {
          getConsumer().onProgressUpdate(1.0F);
          localObject = getConsumer();
          if (localCloseableReference != null)
            paramAnonymousCloseableReference = localCloseableReference;
          ((Consumer)localObject).onNewResult(paramAnonymousCloseableReference, paramAnonymousInt);
          CloseableReference.closeSafely(localCloseableReference);
          return;
          label251: CloseableReference.closeSafely(localCloseableReference);
          throw paramAnonymousCloseableReference;
          getConsumer().onNewResult(paramAnonymousCloseableReference, paramAnonymousInt);
          return;
        }
        finally
        {
          break label251;
        }
      }
    };
  }
}