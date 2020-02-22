package com.facebook.imagepipeline.core;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Objects.ToStringHelper;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ImagePipeline
{
  private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
  private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
  private final CacheKeyFactory mCacheKeyFactory;
  private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
  private AtomicLong mIdCounter = new AtomicLong();
  private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
  private final Supplier<Boolean> mLazyDataSource;
  private final BufferedDiskCache mMainBufferedDiskCache;
  private final ProducerSequenceFactory mProducerSequenceFactory;
  private final RequestListener mRequestListener;
  private final BufferedDiskCache mSmallImageBufferedDiskCache;
  private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
  private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

  public ImagePipeline(ProducerSequenceFactory paramProducerSequenceFactory, Set<RequestListener> paramSet, Supplier<Boolean> paramSupplier1, MemoryCache<CacheKey, CloseableImage> paramMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> paramMemoryCache1, BufferedDiskCache paramBufferedDiskCache1, BufferedDiskCache paramBufferedDiskCache2, CacheKeyFactory paramCacheKeyFactory, ThreadHandoffProducerQueue paramThreadHandoffProducerQueue, Supplier<Boolean> paramSupplier2, Supplier<Boolean> paramSupplier3)
  {
    this.mProducerSequenceFactory = paramProducerSequenceFactory;
    this.mRequestListener = new ForwardingRequestListener(paramSet);
    this.mIsPrefetchEnabledSupplier = paramSupplier1;
    this.mBitmapMemoryCache = paramMemoryCache;
    this.mEncodedMemoryCache = paramMemoryCache1;
    this.mMainBufferedDiskCache = paramBufferedDiskCache1;
    this.mSmallImageBufferedDiskCache = paramBufferedDiskCache2;
    this.mCacheKeyFactory = paramCacheKeyFactory;
    this.mThreadHandoffProducerQueue = paramThreadHandoffProducerQueue;
    this.mSuppressBitmapPrefetchingSupplier = paramSupplier2;
    this.mLazyDataSource = paramSupplier3;
  }

  private String generateUniqueFutureId()
  {
    return String.valueOf(this.mIdCounter.getAndIncrement());
  }

  private RequestListener getRequestListenerForRequest(ImageRequest paramImageRequest, @Nullable RequestListener paramRequestListener)
  {
    if (paramRequestListener == null)
    {
      if (paramImageRequest.getRequestListener() == null)
        return this.mRequestListener;
      return new ForwardingRequestListener(new RequestListener[] { this.mRequestListener, paramImageRequest.getRequestListener() });
    }
    if (paramImageRequest.getRequestListener() == null)
      return new ForwardingRequestListener(new RequestListener[] { this.mRequestListener, paramRequestListener });
    return new ForwardingRequestListener(new RequestListener[] { this.mRequestListener, paramRequestListener, paramImageRequest.getRequestListener() });
  }

  private Predicate<CacheKey> predicateForUri(final Uri paramUri)
  {
    return new Predicate()
    {
      public boolean apply(CacheKey paramAnonymousCacheKey)
      {
        return paramAnonymousCacheKey.containsUri(paramUri);
      }
    };
  }

  private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> paramProducer, ImageRequest paramImageRequest, ImageRequest.RequestLevel paramRequestLevel, Object paramObject, @Nullable RequestListener paramRequestListener)
  {
    paramRequestListener = getRequestListenerForRequest(paramImageRequest, paramRequestListener);
    while (true)
    {
      try
      {
        paramRequestLevel = ImageRequest.RequestLevel.getMax(paramImageRequest.getLowestPermittedRequestLevel(), paramRequestLevel);
        String str = generateUniqueFutureId();
        if (paramImageRequest.getProgressiveRenderingEnabled())
          break label87;
        if (!UriUtil.isNetworkUri(paramImageRequest.getSourceUri()))
        {
          break label87;
          paramProducer = CloseableProducerToDataSourceAdapter.create(paramProducer, new SettableProducerContext(paramImageRequest, str, paramRequestListener, paramObject, paramRequestLevel, false, bool, paramImageRequest.getPriority()), paramRequestListener);
          return paramProducer;
        }
      }
      catch (Exception paramProducer)
      {
        return DataSources.immediateFailedDataSource(paramProducer);
      }
      boolean bool = false;
      continue;
      label87: bool = true;
    }
  }

  private DataSource<Void> submitPrefetchRequest(Producer<Void> paramProducer, ImageRequest paramImageRequest, ImageRequest.RequestLevel paramRequestLevel, Object paramObject, Priority paramPriority)
  {
    RequestListener localRequestListener = getRequestListenerForRequest(paramImageRequest, null);
    try
    {
      paramRequestLevel = ImageRequest.RequestLevel.getMax(paramImageRequest.getLowestPermittedRequestLevel(), paramRequestLevel);
      paramProducer = ProducerToDataSourceAdapter.create(paramProducer, new SettableProducerContext(paramImageRequest, generateUniqueFutureId(), localRequestListener, paramObject, paramRequestLevel, true, false, paramPriority), localRequestListener);
      return paramProducer;
    }
    catch (Exception paramProducer)
    {
    }
    return DataSources.immediateFailedDataSource(paramProducer);
  }

  public void clearCaches()
  {
    clearMemoryCaches();
    clearDiskCaches();
  }

  public void clearDiskCaches()
  {
    this.mMainBufferedDiskCache.clearAll();
    this.mSmallImageBufferedDiskCache.clearAll();
  }

  public void clearMemoryCaches()
  {
    Predicate local4 = new Predicate()
    {
      public boolean apply(CacheKey paramAnonymousCacheKey)
      {
        return true;
      }
    };
    this.mBitmapMemoryCache.removeAll(local4);
    this.mEncodedMemoryCache.removeAll(local4);
  }

  public void evictFromCache(Uri paramUri)
  {
    evictFromMemoryCache(paramUri);
    evictFromDiskCache(paramUri);
  }

  public void evictFromDiskCache(Uri paramUri)
  {
    evictFromDiskCache(ImageRequest.fromUri(paramUri));
  }

  public void evictFromDiskCache(ImageRequest paramImageRequest)
  {
    paramImageRequest = this.mCacheKeyFactory.getEncodedCacheKey(paramImageRequest, null);
    this.mMainBufferedDiskCache.remove(paramImageRequest);
    this.mSmallImageBufferedDiskCache.remove(paramImageRequest);
  }

  public void evictFromMemoryCache(Uri paramUri)
  {
    paramUri = predicateForUri(paramUri);
    this.mBitmapMemoryCache.removeAll(paramUri);
    this.mEncodedMemoryCache.removeAll(paramUri);
  }

  public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest paramImageRequest, Object paramObject)
  {
    return fetchDecodedImage(paramImageRequest, paramObject, ImageRequest.RequestLevel.FULL_FETCH);
  }

  public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest paramImageRequest, Object paramObject, @Nullable RequestListener paramRequestListener)
  {
    return fetchDecodedImage(paramImageRequest, paramObject, ImageRequest.RequestLevel.FULL_FETCH, paramRequestListener);
  }

  public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest paramImageRequest, Object paramObject, ImageRequest.RequestLevel paramRequestLevel)
  {
    return fetchDecodedImage(paramImageRequest, paramObject, paramRequestLevel, null);
  }

  public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest paramImageRequest, Object paramObject, ImageRequest.RequestLevel paramRequestLevel, @Nullable RequestListener paramRequestListener)
  {
    try
    {
      paramImageRequest = submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(paramImageRequest), paramImageRequest, paramRequestLevel, paramObject, paramRequestListener);
      return paramImageRequest;
    }
    catch (Exception paramImageRequest)
    {
    }
    return DataSources.immediateFailedDataSource(paramImageRequest);
  }

  public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest paramImageRequest, Object paramObject)
  {
    return fetchEncodedImage(paramImageRequest, paramObject, null);
  }

  public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest paramImageRequest, Object paramObject, @Nullable RequestListener paramRequestListener)
  {
    Preconditions.checkNotNull(paramImageRequest.getSourceUri());
    try
    {
      Producer localProducer = this.mProducerSequenceFactory.getEncodedImageProducerSequence(paramImageRequest);
      ImageRequest localImageRequest = paramImageRequest;
      if (paramImageRequest.getResizeOptions() != null)
        localImageRequest = ImageRequestBuilder.fromRequest(paramImageRequest).setResizeOptions(null).build();
      paramImageRequest = submitFetchRequest(localProducer, localImageRequest, ImageRequest.RequestLevel.FULL_FETCH, paramObject, paramRequestListener);
      return paramImageRequest;
    }
    catch (Exception paramImageRequest)
    {
    }
    return DataSources.immediateFailedDataSource(paramImageRequest);
  }

  public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest paramImageRequest, Object paramObject)
  {
    return fetchDecodedImage(paramImageRequest, paramObject, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
  }

  public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache()
  {
    return this.mBitmapMemoryCache;
  }

  public CacheKeyFactory getCacheKeyFactory()
  {
    return this.mCacheKeyFactory;
  }

  public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest paramImageRequest, final Object paramObject, final ImageRequest.RequestLevel paramRequestLevel)
  {
    return new Supplier()
    {
      public DataSource<CloseableReference<CloseableImage>> get()
      {
        return ImagePipeline.this.fetchDecodedImage(paramImageRequest, paramObject, paramRequestLevel);
      }

      public String toString()
      {
        return Objects.toStringHelper(this).add("uri", paramImageRequest.getSourceUri()).toString();
      }
    };
  }

  public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest paramImageRequest, final Object paramObject, final ImageRequest.RequestLevel paramRequestLevel, @Nullable final RequestListener paramRequestListener)
  {
    return new Supplier()
    {
      public DataSource<CloseableReference<CloseableImage>> get()
      {
        return ImagePipeline.this.fetchDecodedImage(paramImageRequest, paramObject, paramRequestLevel, paramRequestListener);
      }

      public String toString()
      {
        return Objects.toStringHelper(this).add("uri", paramImageRequest.getSourceUri()).toString();
      }
    };
  }

  public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest paramImageRequest, final Object paramObject)
  {
    return new Supplier()
    {
      public DataSource<CloseableReference<PooledByteBuffer>> get()
      {
        return ImagePipeline.this.fetchEncodedImage(paramImageRequest, paramObject);
      }

      public String toString()
      {
        return Objects.toStringHelper(this).add("uri", paramImageRequest.getSourceUri()).toString();
      }
    };
  }

  public boolean isInBitmapMemoryCache(Uri paramUri)
  {
    if (paramUri == null)
      return false;
    paramUri = predicateForUri(paramUri);
    return this.mBitmapMemoryCache.contains(paramUri);
  }

  public boolean isInBitmapMemoryCache(ImageRequest paramImageRequest)
  {
    if (paramImageRequest == null)
      return false;
    paramImageRequest = this.mCacheKeyFactory.getBitmapCacheKey(paramImageRequest, null);
    paramImageRequest = this.mBitmapMemoryCache.get(paramImageRequest);
    try
    {
      boolean bool = CloseableReference.isValid(paramImageRequest);
      return bool;
    }
    finally
    {
      CloseableReference.closeSafely(paramImageRequest);
    }
  }

  public DataSource<Boolean> isInDiskCache(Uri paramUri)
  {
    return isInDiskCache(ImageRequest.fromUri(paramUri));
  }

  public DataSource<Boolean> isInDiskCache(final ImageRequest paramImageRequest)
  {
    paramImageRequest = this.mCacheKeyFactory.getEncodedCacheKey(paramImageRequest, null);
    final SimpleDataSource localSimpleDataSource = SimpleDataSource.create();
    this.mMainBufferedDiskCache.contains(paramImageRequest).continueWithTask(new Continuation()
    {
      public Task<Boolean> then(Task<Boolean> paramAnonymousTask)
        throws Exception
      {
        if ((!paramAnonymousTask.isCancelled()) && (!paramAnonymousTask.isFaulted()) && (((Boolean)paramAnonymousTask.getResult()).booleanValue()))
          return Task.forResult(Boolean.valueOf(true));
        return ImagePipeline.this.mSmallImageBufferedDiskCache.contains(paramImageRequest);
      }
    }).continueWith(new Continuation()
    {
      public Void then(Task<Boolean> paramAnonymousTask)
        throws Exception
      {
        SimpleDataSource localSimpleDataSource = localSimpleDataSource;
        boolean bool;
        if ((!paramAnonymousTask.isCancelled()) && (!paramAnonymousTask.isFaulted()) && (((Boolean)paramAnonymousTask.getResult()).booleanValue()))
          bool = true;
        else
          bool = false;
        localSimpleDataSource.setResult(Boolean.valueOf(bool));
        return null;
      }
    });
    return localSimpleDataSource;
  }

  public boolean isInDiskCacheSync(Uri paramUri)
  {
    return (isInDiskCacheSync(paramUri, ImageRequest.CacheChoice.SMALL)) || (isInDiskCacheSync(paramUri, ImageRequest.CacheChoice.DEFAULT));
  }

  public boolean isInDiskCacheSync(Uri paramUri, ImageRequest.CacheChoice paramCacheChoice)
  {
    return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(paramUri).setCacheChoice(paramCacheChoice).build());
  }

  public boolean isInDiskCacheSync(ImageRequest paramImageRequest)
  {
    CacheKey localCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(paramImageRequest, null);
    paramImageRequest = paramImageRequest.getCacheChoice();
    switch (8.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[paramImageRequest.ordinal()])
    {
    default:
      return false;
    case 2:
      return this.mSmallImageBufferedDiskCache.diskCheckSync(localCacheKey);
    case 1:
    }
    return this.mMainBufferedDiskCache.diskCheckSync(localCacheKey);
  }

  public Supplier<Boolean> isLazyDataSource()
  {
    return this.mLazyDataSource;
  }

  public boolean isPaused()
  {
    return this.mThreadHandoffProducerQueue.isQueueing();
  }

  public void pause()
  {
    this.mThreadHandoffProducerQueue.startQueueing();
  }

  public DataSource<Void> prefetchToBitmapCache(ImageRequest paramImageRequest, Object paramObject)
  {
    if (!((Boolean)this.mIsPrefetchEnabledSupplier.get()).booleanValue())
      return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
    try
    {
      Producer localProducer;
      if (((Boolean)this.mSuppressBitmapPrefetchingSupplier.get()).booleanValue())
        localProducer = this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(paramImageRequest);
      else
        localProducer = this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(paramImageRequest);
      paramImageRequest = submitPrefetchRequest(localProducer, paramImageRequest, ImageRequest.RequestLevel.FULL_FETCH, paramObject, Priority.MEDIUM);
      return paramImageRequest;
    }
    catch (Exception paramImageRequest)
    {
    }
    return DataSources.immediateFailedDataSource(paramImageRequest);
  }

  public DataSource<Void> prefetchToDiskCache(ImageRequest paramImageRequest, Object paramObject)
  {
    return prefetchToDiskCache(paramImageRequest, paramObject, Priority.MEDIUM);
  }

  public DataSource<Void> prefetchToDiskCache(ImageRequest paramImageRequest, Object paramObject, Priority paramPriority)
  {
    if (!((Boolean)this.mIsPrefetchEnabledSupplier.get()).booleanValue())
      return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
    try
    {
      paramImageRequest = submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(paramImageRequest), paramImageRequest, ImageRequest.RequestLevel.FULL_FETCH, paramObject, paramPriority);
      return paramImageRequest;
    }
    catch (Exception paramImageRequest)
    {
    }
    return DataSources.immediateFailedDataSource(paramImageRequest);
  }

  public void resume()
  {
    this.mThreadHandoffProducerQueue.stopQueuing();
  }
}