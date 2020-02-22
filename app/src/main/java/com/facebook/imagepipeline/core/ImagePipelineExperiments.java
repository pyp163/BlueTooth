package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpBitmapFactory.WebpErrorLogger;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;

public class ImagePipelineExperiments
{
  private boolean mBitmapPrepareToDrawForPrefetch;
  private final int mBitmapPrepareToDrawMaxSizeBytes;
  private final int mBitmapPrepareToDrawMinSizeBytes;
  private final boolean mDecodeCancellationEnabled;
  private final Supplier<Boolean> mLazyDataSource;
  private final boolean mPartialImageCachingEnabled;
  private final ProducerFactoryMethod mProducerFactoryMethod;
  private final boolean mUseBitmapPrepareToDraw;
  private final boolean mUseDownsamplingRatioForResizing;
  private final WebpBitmapFactory mWebpBitmapFactory;
  private final WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
  private final boolean mWebpSupportEnabled;

  private ImagePipelineExperiments(Builder paramBuilder)
  {
    this.mWebpSupportEnabled = paramBuilder.mWebpSupportEnabled;
    this.mWebpErrorLogger = paramBuilder.mWebpErrorLogger;
    this.mDecodeCancellationEnabled = paramBuilder.mDecodeCancellationEnabled;
    this.mWebpBitmapFactory = paramBuilder.mWebpBitmapFactory;
    this.mUseDownsamplingRatioForResizing = paramBuilder.mUseDownsamplingRatioForResizing;
    this.mUseBitmapPrepareToDraw = paramBuilder.mUseBitmapPrepareToDraw;
    this.mBitmapPrepareToDrawMinSizeBytes = paramBuilder.mBitmapPrepareToDrawMinSizeBytes;
    this.mBitmapPrepareToDrawMaxSizeBytes = paramBuilder.mBitmapPrepareToDrawMaxSizeBytes;
    this.mBitmapPrepareToDrawForPrefetch = paramBuilder.mBitmapPrepareToDrawForPrefetch;
    this.mPartialImageCachingEnabled = paramBuilder.mPartialImageCachingEnabled;
    if (paramBuilder.mProducerFactoryMethod == null)
      this.mProducerFactoryMethod = new DefaultProducerFactoryMethod();
    else
      this.mProducerFactoryMethod = paramBuilder.mProducerFactoryMethod;
    this.mLazyDataSource = paramBuilder.mLazyDataSource;
  }

  public static Builder newBuilder(ImagePipelineConfig.Builder paramBuilder)
  {
    return new Builder(paramBuilder);
  }

  public boolean getBitmapPrepareToDrawForPrefetch()
  {
    return this.mBitmapPrepareToDrawForPrefetch;
  }

  public int getBitmapPrepareToDrawMaxSizeBytes()
  {
    return this.mBitmapPrepareToDrawMaxSizeBytes;
  }

  public int getBitmapPrepareToDrawMinSizeBytes()
  {
    return this.mBitmapPrepareToDrawMinSizeBytes;
  }

  public ProducerFactoryMethod getProducerFactoryMethod()
  {
    return this.mProducerFactoryMethod;
  }

  public boolean getUseBitmapPrepareToDraw()
  {
    return this.mUseBitmapPrepareToDraw;
  }

  public boolean getUseDownsamplingRatioForResizing()
  {
    return this.mUseDownsamplingRatioForResizing;
  }

  public WebpBitmapFactory getWebpBitmapFactory()
  {
    return this.mWebpBitmapFactory;
  }

  public WebpBitmapFactory.WebpErrorLogger getWebpErrorLogger()
  {
    return this.mWebpErrorLogger;
  }

  public boolean isDecodeCancellationEnabled()
  {
    return this.mDecodeCancellationEnabled;
  }

  public Supplier<Boolean> isLazyDataSource()
  {
    return this.mLazyDataSource;
  }

  public boolean isPartialImageCachingEnabled()
  {
    return this.mPartialImageCachingEnabled;
  }

  public boolean isWebpSupportEnabled()
  {
    return this.mWebpSupportEnabled;
  }

  public static class Builder
  {
    public boolean mBitmapPrepareToDrawForPrefetch = false;
    private int mBitmapPrepareToDrawMaxSizeBytes = 0;
    private int mBitmapPrepareToDrawMinSizeBytes = 0;
    private final ImagePipelineConfig.Builder mConfigBuilder;
    private boolean mDecodeCancellationEnabled = false;
    public Supplier<Boolean> mLazyDataSource;
    private boolean mPartialImageCachingEnabled = false;
    private ImagePipelineExperiments.ProducerFactoryMethod mProducerFactoryMethod;
    private boolean mUseBitmapPrepareToDraw = false;
    private boolean mUseDownsamplingRatioForResizing = false;
    private WebpBitmapFactory mWebpBitmapFactory;
    private WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
    private boolean mWebpSupportEnabled = false;

    public Builder(ImagePipelineConfig.Builder paramBuilder)
    {
      this.mConfigBuilder = paramBuilder;
    }

    public ImagePipelineExperiments build()
    {
      return new ImagePipelineExperiments(this, null);
    }

    public boolean isPartialImageCachingEnabled()
    {
      return this.mPartialImageCachingEnabled;
    }

    public ImagePipelineConfig.Builder setBitmapPrepareToDraw(boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2)
    {
      this.mUseBitmapPrepareToDraw = paramBoolean1;
      this.mBitmapPrepareToDrawMinSizeBytes = paramInt1;
      this.mBitmapPrepareToDrawMaxSizeBytes = paramInt2;
      this.mBitmapPrepareToDrawForPrefetch = paramBoolean2;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setDecodeCancellationEnabled(boolean paramBoolean)
    {
      this.mDecodeCancellationEnabled = paramBoolean;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setLazyDataSource(Supplier<Boolean> paramSupplier)
    {
      this.mLazyDataSource = paramSupplier;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setPartialImageCachingEnabled(boolean paramBoolean)
    {
      this.mPartialImageCachingEnabled = paramBoolean;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setProducerFactoryMethod(ImagePipelineExperiments.ProducerFactoryMethod paramProducerFactoryMethod)
    {
      this.mProducerFactoryMethod = paramProducerFactoryMethod;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setUseDownsampligRatioForResizing(boolean paramBoolean)
    {
      this.mUseDownsamplingRatioForResizing = paramBoolean;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setWebpBitmapFactory(WebpBitmapFactory paramWebpBitmapFactory)
    {
      this.mWebpBitmapFactory = paramWebpBitmapFactory;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger paramWebpErrorLogger)
    {
      this.mWebpErrorLogger = paramWebpErrorLogger;
      return this.mConfigBuilder;
    }

    public ImagePipelineConfig.Builder setWebpSupportEnabled(boolean paramBoolean)
    {
      this.mWebpSupportEnabled = paramBoolean;
      return this.mConfigBuilder;
    }
  }

  public static class DefaultProducerFactoryMethod
    implements ImagePipelineExperiments.ProducerFactoryMethod
  {
    public ProducerFactory createProducerFactory(Context paramContext, ByteArrayPool paramByteArrayPool, ImageDecoder paramImageDecoder, ProgressiveJpegConfig paramProgressiveJpegConfig, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, ExecutorSupplier paramExecutorSupplier, PooledByteBufferFactory paramPooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> paramMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> paramMemoryCache1, BufferedDiskCache paramBufferedDiskCache1, BufferedDiskCache paramBufferedDiskCache2, CacheKeyFactory paramCacheKeyFactory, PlatformBitmapFactory paramPlatformBitmapFactory, int paramInt1, int paramInt2, boolean paramBoolean4)
    {
      return new ProducerFactory(paramContext, paramByteArrayPool, paramImageDecoder, paramProgressiveJpegConfig, paramBoolean1, paramBoolean2, paramBoolean3, paramExecutorSupplier, paramPooledByteBufferFactory, paramMemoryCache, paramMemoryCache1, paramBufferedDiskCache1, paramBufferedDiskCache2, paramCacheKeyFactory, paramPlatformBitmapFactory, paramInt1, paramInt2, paramBoolean4);
    }
  }

  public static abstract interface ProducerFactoryMethod
  {
    public abstract ProducerFactory createProducerFactory(Context paramContext, ByteArrayPool paramByteArrayPool, ImageDecoder paramImageDecoder, ProgressiveJpegConfig paramProgressiveJpegConfig, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, ExecutorSupplier paramExecutorSupplier, PooledByteBufferFactory paramPooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> paramMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> paramMemoryCache1, BufferedDiskCache paramBufferedDiskCache1, BufferedDiskCache paramBufferedDiskCache2, CacheKeyFactory paramCacheKeyFactory, PlatformBitmapFactory paramPlatformBitmapFactory, int paramInt1, int paramInt2, boolean paramBoolean4);
  }
}