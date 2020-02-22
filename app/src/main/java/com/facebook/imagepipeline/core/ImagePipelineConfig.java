package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.cache.disk.DiskCacheConfig.Builder;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.bitmaps.HoneycombBitmapCreator;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache.CacheTrimStrategy;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolConfig.Builder;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class ImagePipelineConfig
{
  private static DefaultImageRequestConfig sDefaultImageRequestConfig = new DefaultImageRequestConfig(null);
  private final Bitmap.Config mBitmapConfig;
  private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
  private final CountingMemoryCache.CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
  private final CacheKeyFactory mCacheKeyFactory;
  private final Context mContext;
  private final boolean mDiskCacheEnabled;
  private final boolean mDownsampleEnabled;
  private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
  private final ExecutorSupplier mExecutorSupplier;
  private final FileCacheFactory mFileCacheFactory;
  private final int mHttpNetworkTimeout;
  private final ImageCacheStatsTracker mImageCacheStatsTracker;

  @Nullable
  private final ImageDecoder mImageDecoder;

  @Nullable
  private final ImageDecoderConfig mImageDecoderConfig;
  private final ImagePipelineExperiments mImagePipelineExperiments;
  private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
  private final DiskCacheConfig mMainDiskCacheConfig;
  private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
  private final NetworkFetcher mNetworkFetcher;

  @Nullable
  private final PlatformBitmapFactory mPlatformBitmapFactory;
  private final PoolFactory mPoolFactory;
  private final ProgressiveJpegConfig mProgressiveJpegConfig;
  private final Set<RequestListener> mRequestListeners;
  private final boolean mResizeAndRotateEnabledForNetwork;
  private final DiskCacheConfig mSmallImageDiskCacheConfig;

  private ImagePipelineConfig(Builder paramBuilder)
  {
    this.mImagePipelineExperiments = paramBuilder.mExperimentsBuilder.build();
    Object localObject;
    if (paramBuilder.mBitmapMemoryCacheParamsSupplier == null)
      localObject = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager)paramBuilder.mContext.getSystemService("activity"));
    else
      localObject = paramBuilder.mBitmapMemoryCacheParamsSupplier;
    this.mBitmapMemoryCacheParamsSupplier = ((Supplier)localObject);
    if (paramBuilder.mBitmapMemoryCacheTrimStrategy == null)
      localObject = new BitmapMemoryCacheTrimStrategy();
    else
      localObject = paramBuilder.mBitmapMemoryCacheTrimStrategy;
    this.mBitmapMemoryCacheTrimStrategy = ((CountingMemoryCache.CacheTrimStrategy)localObject);
    if (paramBuilder.mBitmapConfig == null)
      localObject = Bitmap.Config.ARGB_8888;
    else
      localObject = paramBuilder.mBitmapConfig;
    this.mBitmapConfig = ((Bitmap.Config)localObject);
    if (paramBuilder.mCacheKeyFactory == null)
      localObject = DefaultCacheKeyFactory.getInstance();
    else
      localObject = paramBuilder.mCacheKeyFactory;
    this.mCacheKeyFactory = ((CacheKeyFactory)localObject);
    this.mContext = ((Context)Preconditions.checkNotNull(paramBuilder.mContext));
    if (paramBuilder.mFileCacheFactory == null)
      localObject = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
    else
      localObject = paramBuilder.mFileCacheFactory;
    this.mFileCacheFactory = ((FileCacheFactory)localObject);
    this.mDownsampleEnabled = paramBuilder.mDownsampleEnabled;
    if (paramBuilder.mEncodedMemoryCacheParamsSupplier == null)
      localObject = new DefaultEncodedMemoryCacheParamsSupplier();
    else
      localObject = paramBuilder.mEncodedMemoryCacheParamsSupplier;
    this.mEncodedMemoryCacheParamsSupplier = ((Supplier)localObject);
    if (paramBuilder.mImageCacheStatsTracker == null)
      localObject = NoOpImageCacheStatsTracker.getInstance();
    else
      localObject = paramBuilder.mImageCacheStatsTracker;
    this.mImageCacheStatsTracker = ((ImageCacheStatsTracker)localObject);
    this.mImageDecoder = paramBuilder.mImageDecoder;
    if (paramBuilder.mIsPrefetchEnabledSupplier == null)
      localObject = new Supplier()
      {
        public Boolean get()
        {
          return Boolean.valueOf(true);
        }
      };
    else
      localObject = paramBuilder.mIsPrefetchEnabledSupplier;
    this.mIsPrefetchEnabledSupplier = ((Supplier)localObject);
    if (paramBuilder.mMainDiskCacheConfig == null)
      localObject = getDefaultMainDiskCacheConfig(paramBuilder.mContext);
    else
      localObject = paramBuilder.mMainDiskCacheConfig;
    this.mMainDiskCacheConfig = ((DiskCacheConfig)localObject);
    if (paramBuilder.mMemoryTrimmableRegistry == null)
      localObject = NoOpMemoryTrimmableRegistry.getInstance();
    else
      localObject = paramBuilder.mMemoryTrimmableRegistry;
    this.mMemoryTrimmableRegistry = ((MemoryTrimmableRegistry)localObject);
    if (paramBuilder.mHttpConnectionTimeout < 0)
      i = 30000;
    else
      i = paramBuilder.mHttpConnectionTimeout;
    this.mHttpNetworkTimeout = i;
    if (paramBuilder.mNetworkFetcher == null)
      localObject = new HttpUrlConnectionNetworkFetcher(this.mHttpNetworkTimeout);
    else
      localObject = paramBuilder.mNetworkFetcher;
    this.mNetworkFetcher = ((NetworkFetcher)localObject);
    this.mPlatformBitmapFactory = paramBuilder.mPlatformBitmapFactory;
    if (paramBuilder.mPoolFactory == null)
      localObject = new PoolFactory(PoolConfig.newBuilder().build());
    else
      localObject = paramBuilder.mPoolFactory;
    this.mPoolFactory = ((PoolFactory)localObject);
    if (paramBuilder.mProgressiveJpegConfig == null)
      localObject = new SimpleProgressiveJpegConfig();
    else
      localObject = paramBuilder.mProgressiveJpegConfig;
    this.mProgressiveJpegConfig = ((ProgressiveJpegConfig)localObject);
    if (paramBuilder.mRequestListeners == null)
      localObject = new HashSet();
    else
      localObject = paramBuilder.mRequestListeners;
    this.mRequestListeners = ((Set)localObject);
    this.mResizeAndRotateEnabledForNetwork = paramBuilder.mResizeAndRotateEnabledForNetwork;
    if (paramBuilder.mSmallImageDiskCacheConfig == null)
      localObject = this.mMainDiskCacheConfig;
    else
      localObject = paramBuilder.mSmallImageDiskCacheConfig;
    this.mSmallImageDiskCacheConfig = ((DiskCacheConfig)localObject);
    this.mImageDecoderConfig = paramBuilder.mImageDecoderConfig;
    int i = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
    if (paramBuilder.mExecutorSupplier == null)
      localObject = new DefaultExecutorSupplier(i);
    else
      localObject = paramBuilder.mExecutorSupplier;
    this.mExecutorSupplier = ((ExecutorSupplier)localObject);
    this.mDiskCacheEnabled = paramBuilder.mDiskCacheEnabled;
    paramBuilder = this.mImagePipelineExperiments.getWebpBitmapFactory();
    if (paramBuilder != null)
    {
      localObject = new HoneycombBitmapCreator(getPoolFactory());
      setWebpBitmapFactory(paramBuilder, this.mImagePipelineExperiments, (BitmapCreator)localObject);
      return;
    }
    if ((this.mImagePipelineExperiments.isWebpSupportEnabled()) && (WebpSupportStatus.sIsWebpSupportRequired))
    {
      paramBuilder = WebpSupportStatus.loadWebpBitmapFactoryIfExists();
      if (paramBuilder != null)
      {
        localObject = new HoneycombBitmapCreator(getPoolFactory());
        setWebpBitmapFactory(paramBuilder, this.mImagePipelineExperiments, (BitmapCreator)localObject);
      }
    }
  }

  public static DefaultImageRequestConfig getDefaultImageRequestConfig()
  {
    return sDefaultImageRequestConfig;
  }

  private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context paramContext)
  {
    return DiskCacheConfig.newBuilder(paramContext).build();
  }

  public static Builder newBuilder(Context paramContext)
  {
    return new Builder(paramContext, null);
  }

  @VisibleForTesting
  static void resetDefaultRequestConfig()
  {
    sDefaultImageRequestConfig = new DefaultImageRequestConfig(null);
  }

  private static void setWebpBitmapFactory(WebpBitmapFactory paramWebpBitmapFactory, ImagePipelineExperiments paramImagePipelineExperiments, BitmapCreator paramBitmapCreator)
  {
    WebpSupportStatus.sWebpBitmapFactory = paramWebpBitmapFactory;
    paramImagePipelineExperiments = paramImagePipelineExperiments.getWebpErrorLogger();
    if (paramImagePipelineExperiments != null)
      paramWebpBitmapFactory.setWebpErrorLogger(paramImagePipelineExperiments);
    if (paramBitmapCreator != null)
      paramWebpBitmapFactory.setBitmapCreator(paramBitmapCreator);
  }

  public Bitmap.Config getBitmapConfig()
  {
    return this.mBitmapConfig;
  }

  public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier()
  {
    return this.mBitmapMemoryCacheParamsSupplier;
  }

  public CountingMemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy()
  {
    return this.mBitmapMemoryCacheTrimStrategy;
  }

  public CacheKeyFactory getCacheKeyFactory()
  {
    return this.mCacheKeyFactory;
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier()
  {
    return this.mEncodedMemoryCacheParamsSupplier;
  }

  public ExecutorSupplier getExecutorSupplier()
  {
    return this.mExecutorSupplier;
  }

  public ImagePipelineExperiments getExperiments()
  {
    return this.mImagePipelineExperiments;
  }

  public FileCacheFactory getFileCacheFactory()
  {
    return this.mFileCacheFactory;
  }

  public ImageCacheStatsTracker getImageCacheStatsTracker()
  {
    return this.mImageCacheStatsTracker;
  }

  @Nullable
  public ImageDecoder getImageDecoder()
  {
    return this.mImageDecoder;
  }

  @Nullable
  public ImageDecoderConfig getImageDecoderConfig()
  {
    return this.mImageDecoderConfig;
  }

  public Supplier<Boolean> getIsPrefetchEnabledSupplier()
  {
    return this.mIsPrefetchEnabledSupplier;
  }

  public DiskCacheConfig getMainDiskCacheConfig()
  {
    return this.mMainDiskCacheConfig;
  }

  public MemoryTrimmableRegistry getMemoryTrimmableRegistry()
  {
    return this.mMemoryTrimmableRegistry;
  }

  public NetworkFetcher getNetworkFetcher()
  {
    return this.mNetworkFetcher;
  }

  @Nullable
  public PlatformBitmapFactory getPlatformBitmapFactory()
  {
    return this.mPlatformBitmapFactory;
  }

  public PoolFactory getPoolFactory()
  {
    return this.mPoolFactory;
  }

  public ProgressiveJpegConfig getProgressiveJpegConfig()
  {
    return this.mProgressiveJpegConfig;
  }

  public Set<RequestListener> getRequestListeners()
  {
    return Collections.unmodifiableSet(this.mRequestListeners);
  }

  public DiskCacheConfig getSmallImageDiskCacheConfig()
  {
    return this.mSmallImageDiskCacheConfig;
  }

  public boolean isDiskCacheEnabled()
  {
    return this.mDiskCacheEnabled;
  }

  public boolean isDownsampleEnabled()
  {
    return this.mDownsampleEnabled;
  }

  public boolean isResizeAndRotateEnabledForNetwork()
  {
    return this.mResizeAndRotateEnabledForNetwork;
  }

  public static class Builder
  {
    private Bitmap.Config mBitmapConfig;
    private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private CountingMemoryCache.CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
    private CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private boolean mDiskCacheEnabled = true;
    private boolean mDownsampleEnabled = false;
    private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private ExecutorSupplier mExecutorSupplier;
    private final ImagePipelineExperiments.Builder mExperimentsBuilder = new ImagePipelineExperiments.Builder(this);
    private FileCacheFactory mFileCacheFactory;
    private int mHttpConnectionTimeout = -1;
    private ImageCacheStatsTracker mImageCacheStatsTracker;
    private ImageDecoder mImageDecoder;
    private ImageDecoderConfig mImageDecoderConfig;
    private Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private DiskCacheConfig mMainDiskCacheConfig;
    private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private NetworkFetcher mNetworkFetcher;
    private PlatformBitmapFactory mPlatformBitmapFactory;
    private PoolFactory mPoolFactory;
    private ProgressiveJpegConfig mProgressiveJpegConfig;
    private Set<RequestListener> mRequestListeners;
    private boolean mResizeAndRotateEnabledForNetwork = true;
    private DiskCacheConfig mSmallImageDiskCacheConfig;

    private Builder(Context paramContext)
    {
      this.mContext = ((Context)Preconditions.checkNotNull(paramContext));
    }

    public ImagePipelineConfig build()
    {
      return new ImagePipelineConfig(this, null);
    }

    public ImagePipelineExperiments.Builder experiment()
    {
      return this.mExperimentsBuilder;
    }

    public boolean isDiskCacheEnabled()
    {
      return this.mDiskCacheEnabled;
    }

    public boolean isDownsampleEnabled()
    {
      return this.mDownsampleEnabled;
    }

    public Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> paramSupplier)
    {
      this.mBitmapMemoryCacheParamsSupplier = ((Supplier)Preconditions.checkNotNull(paramSupplier));
      return this;
    }

    public Builder setBitmapMemoryCacheTrimStrategy(CountingMemoryCache.CacheTrimStrategy paramCacheTrimStrategy)
    {
      this.mBitmapMemoryCacheTrimStrategy = paramCacheTrimStrategy;
      return this;
    }

    public Builder setBitmapsConfig(Bitmap.Config paramConfig)
    {
      this.mBitmapConfig = paramConfig;
      return this;
    }

    public Builder setCacheKeyFactory(CacheKeyFactory paramCacheKeyFactory)
    {
      this.mCacheKeyFactory = paramCacheKeyFactory;
      return this;
    }

    public Builder setDiskCacheEnabled(boolean paramBoolean)
    {
      this.mDiskCacheEnabled = paramBoolean;
      return this;
    }

    public Builder setDownsampleEnabled(boolean paramBoolean)
    {
      this.mDownsampleEnabled = paramBoolean;
      return this;
    }

    public Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> paramSupplier)
    {
      this.mEncodedMemoryCacheParamsSupplier = ((Supplier)Preconditions.checkNotNull(paramSupplier));
      return this;
    }

    public Builder setExecutorSupplier(ExecutorSupplier paramExecutorSupplier)
    {
      this.mExecutorSupplier = paramExecutorSupplier;
      return this;
    }

    public Builder setFileCacheFactory(FileCacheFactory paramFileCacheFactory)
    {
      this.mFileCacheFactory = paramFileCacheFactory;
      return this;
    }

    public Builder setHttpConnectionTimeout(int paramInt)
    {
      this.mHttpConnectionTimeout = paramInt;
      return this;
    }

    public Builder setImageCacheStatsTracker(ImageCacheStatsTracker paramImageCacheStatsTracker)
    {
      this.mImageCacheStatsTracker = paramImageCacheStatsTracker;
      return this;
    }

    public Builder setImageDecoder(ImageDecoder paramImageDecoder)
    {
      this.mImageDecoder = paramImageDecoder;
      return this;
    }

    public Builder setImageDecoderConfig(ImageDecoderConfig paramImageDecoderConfig)
    {
      this.mImageDecoderConfig = paramImageDecoderConfig;
      return this;
    }

    public Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> paramSupplier)
    {
      this.mIsPrefetchEnabledSupplier = paramSupplier;
      return this;
    }

    public Builder setMainDiskCacheConfig(DiskCacheConfig paramDiskCacheConfig)
    {
      this.mMainDiskCacheConfig = paramDiskCacheConfig;
      return this;
    }

    public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry paramMemoryTrimmableRegistry)
    {
      this.mMemoryTrimmableRegistry = paramMemoryTrimmableRegistry;
      return this;
    }

    public Builder setNetworkFetcher(NetworkFetcher paramNetworkFetcher)
    {
      this.mNetworkFetcher = paramNetworkFetcher;
      return this;
    }

    public Builder setPlatformBitmapFactory(PlatformBitmapFactory paramPlatformBitmapFactory)
    {
      this.mPlatformBitmapFactory = paramPlatformBitmapFactory;
      return this;
    }

    public Builder setPoolFactory(PoolFactory paramPoolFactory)
    {
      this.mPoolFactory = paramPoolFactory;
      return this;
    }

    public Builder setProgressiveJpegConfig(ProgressiveJpegConfig paramProgressiveJpegConfig)
    {
      this.mProgressiveJpegConfig = paramProgressiveJpegConfig;
      return this;
    }

    public Builder setRequestListeners(Set<RequestListener> paramSet)
    {
      this.mRequestListeners = paramSet;
      return this;
    }

    public Builder setResizeAndRotateEnabledForNetwork(boolean paramBoolean)
    {
      this.mResizeAndRotateEnabledForNetwork = paramBoolean;
      return this;
    }

    public Builder setSmallImageDiskCacheConfig(DiskCacheConfig paramDiskCacheConfig)
    {
      this.mSmallImageDiskCacheConfig = paramDiskCacheConfig;
      return this;
    }
  }

  public static class DefaultImageRequestConfig
  {
    private boolean mProgressiveRenderingEnabled = false;

    public boolean isProgressiveRenderingEnabled()
    {
      return this.mProgressiveRenderingEnabled;
    }

    public void setProgressiveRenderingEnabled(boolean paramBoolean)
    {
      this.mProgressiveRenderingEnabled = paramBoolean;
    }
  }
}