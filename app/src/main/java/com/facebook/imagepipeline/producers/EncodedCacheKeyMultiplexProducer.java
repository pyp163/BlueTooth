package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;

public class EncodedCacheKeyMultiplexProducer extends MultiplexProducer<Pair<CacheKey, ImageRequest.RequestLevel>, EncodedImage>
{
  private final CacheKeyFactory mCacheKeyFactory;

  public EncodedCacheKeyMultiplexProducer(CacheKeyFactory paramCacheKeyFactory, Producer paramProducer)
  {
    super(paramProducer);
    this.mCacheKeyFactory = paramCacheKeyFactory;
  }

  public EncodedImage cloneOrNull(EncodedImage paramEncodedImage)
  {
    return EncodedImage.cloneOrNull(paramEncodedImage);
  }

  protected Pair<CacheKey, ImageRequest.RequestLevel> getKey(ProducerContext paramProducerContext)
  {
    return Pair.create(this.mCacheKeyFactory.getEncodedCacheKey(paramProducerContext.getImageRequest(), paramProducerContext.getCallerContext()), paramProducerContext.getLowestPermittedRequestLevel());
  }
}