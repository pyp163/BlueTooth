package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;

public class DiskCacheWriteProducer
  implements Producer<EncodedImage>
{

  @VisibleForTesting
  static final String PRODUCER_NAME = "DiskCacheProducer";
  private final CacheKeyFactory mCacheKeyFactory;
  private final BufferedDiskCache mDefaultBufferedDiskCache;
  private final Producer<EncodedImage> mInputProducer;
  private final BufferedDiskCache mSmallImageBufferedDiskCache;

  public DiskCacheWriteProducer(BufferedDiskCache paramBufferedDiskCache1, BufferedDiskCache paramBufferedDiskCache2, CacheKeyFactory paramCacheKeyFactory, Producer<EncodedImage> paramProducer)
  {
    this.mDefaultBufferedDiskCache = paramBufferedDiskCache1;
    this.mSmallImageBufferedDiskCache = paramBufferedDiskCache2;
    this.mCacheKeyFactory = paramCacheKeyFactory;
    this.mInputProducer = paramProducer;
  }

  private void maybeStartInputProducer(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    if (paramProducerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue())
    {
      paramConsumer.onNewResult(null, 1);
      return;
    }
    Object localObject = paramConsumer;
    if (paramProducerContext.getImageRequest().isDiskCacheEnabled())
      localObject = new DiskCacheWriteConsumer(paramConsumer, paramProducerContext, this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, null);
    this.mInputProducer.produceResults((Consumer)localObject, paramProducerContext);
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    maybeStartInputProducer(paramConsumer, paramProducerContext);
  }

  private static class DiskCacheWriteConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final ProducerContext mProducerContext;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    private DiskCacheWriteConsumer(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext, BufferedDiskCache paramBufferedDiskCache1, BufferedDiskCache paramBufferedDiskCache2, CacheKeyFactory paramCacheKeyFactory)
    {
      super();
      this.mProducerContext = paramProducerContext;
      this.mDefaultBufferedDiskCache = paramBufferedDiskCache1;
      this.mSmallImageBufferedDiskCache = paramBufferedDiskCache2;
      this.mCacheKeyFactory = paramCacheKeyFactory;
    }

    public void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      if ((!isNotLast(paramInt)) && (paramEncodedImage != null) && (!statusHasAnyFlag(paramInt, 10)) && (paramEncodedImage.getImageFormat() != ImageFormat.UNKNOWN))
      {
        ImageRequest localImageRequest = this.mProducerContext.getImageRequest();
        CacheKey localCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(localImageRequest, this.mProducerContext.getCallerContext());
        if (localImageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL)
          this.mSmallImageBufferedDiskCache.put(localCacheKey, paramEncodedImage);
        else
          this.mDefaultBufferedDiskCache.put(localCacheKey, paramEncodedImage);
        getConsumer().onNewResult(paramEncodedImage, paramInt);
        return;
      }
      getConsumer().onNewResult(paramEncodedImage, paramInt);
    }
  }
}