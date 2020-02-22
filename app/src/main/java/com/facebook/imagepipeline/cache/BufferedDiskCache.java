package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class BufferedDiskCache
{
  private static final Class<?> TAG = BufferedDiskCache.class;
  private final FileCache mFileCache;
  private final ImageCacheStatsTracker mImageCacheStatsTracker;
  private final PooledByteBufferFactory mPooledByteBufferFactory;
  private final PooledByteStreams mPooledByteStreams;
  private final Executor mReadExecutor;
  private final StagingArea mStagingArea;
  private final Executor mWriteExecutor;

  public BufferedDiskCache(FileCache paramFileCache, PooledByteBufferFactory paramPooledByteBufferFactory, PooledByteStreams paramPooledByteStreams, Executor paramExecutor1, Executor paramExecutor2, ImageCacheStatsTracker paramImageCacheStatsTracker)
  {
    this.mFileCache = paramFileCache;
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
    this.mPooledByteStreams = paramPooledByteStreams;
    this.mReadExecutor = paramExecutor1;
    this.mWriteExecutor = paramExecutor2;
    this.mImageCacheStatsTracker = paramImageCacheStatsTracker;
    this.mStagingArea = StagingArea.getInstance();
  }

  private boolean checkInStagingAreaAndFileCache(CacheKey paramCacheKey)
  {
    EncodedImage localEncodedImage = this.mStagingArea.get(paramCacheKey);
    if (localEncodedImage != null)
    {
      localEncodedImage.close();
      FLog.v(TAG, "Found image for %s in staging area", paramCacheKey.getUriString());
      this.mImageCacheStatsTracker.onStagingAreaHit(paramCacheKey);
      return true;
    }
    FLog.v(TAG, "Did not find image for %s in staging area", paramCacheKey.getUriString());
    this.mImageCacheStatsTracker.onStagingAreaMiss();
    try
    {
      boolean bool = this.mFileCache.hasKey(paramCacheKey);
      return bool;
      label79: return false;
    }
    catch (Exception paramCacheKey)
    {
      break label79;
    }
  }

  private Task<Boolean> containsAsync(final CacheKey paramCacheKey)
  {
    try
    {
      Task localTask = Task.call(new Callable()
      {
        public Boolean call()
          throws Exception
        {
          return Boolean.valueOf(BufferedDiskCache.this.checkInStagingAreaAndFileCache(paramCacheKey));
        }
      }
      , this.mReadExecutor);
      return localTask;
    }
    catch (Exception localException)
    {
      FLog.w(TAG, localException, "Failed to schedule disk-cache read for %s", new Object[] { paramCacheKey.getUriString() });
      return Task.forError(localException);
    }
  }

  private Task<EncodedImage> foundPinnedImage(CacheKey paramCacheKey, EncodedImage paramEncodedImage)
  {
    FLog.v(TAG, "Found image for %s in staging area", paramCacheKey.getUriString());
    this.mImageCacheStatsTracker.onStagingAreaHit(paramCacheKey);
    return Task.forResult(paramEncodedImage);
  }

  private Task<EncodedImage> getAsync(final CacheKey paramCacheKey, final AtomicBoolean paramAtomicBoolean)
  {
    try
    {
      paramAtomicBoolean = Task.call(new Callable()
      {
        public EncodedImage call()
          throws Exception
        {
          if (paramAtomicBoolean.get())
            throw new CancellationException();
          EncodedImage localEncodedImage = BufferedDiskCache.this.mStagingArea.get(paramCacheKey);
          if (localEncodedImage != null)
          {
            FLog.v(BufferedDiskCache.TAG, "Found image for %s in staging area", paramCacheKey.getUriString());
            BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaHit(paramCacheKey);
          }
          else
          {
            FLog.v(BufferedDiskCache.TAG, "Did not find image for %s in staging area", paramCacheKey.getUriString());
            BufferedDiskCache.this.mImageCacheStatsTracker.onStagingAreaMiss();
          }
          try
          {
            CloseableReference localCloseableReference = CloseableReference.of(BufferedDiskCache.this.readFromDiskCache(paramCacheKey));
            try
            {
              localEncodedImage = new EncodedImage(localCloseableReference);
              CloseableReference.closeSafely(localCloseableReference);
              if (Thread.interrupted())
              {
                FLog.v(BufferedDiskCache.TAG, "Host thread was interrupted, decreasing reference count");
                if (localEncodedImage != null)
                  localEncodedImage.close();
                throw new InterruptedException();
              }
              return localEncodedImage;
            }
            finally
            {
              CloseableReference.closeSafely(localCloseableReference);
            }
            label169: return null;
          }
          catch (Exception localException)
          {
            break label169;
          }
        }
      }
      , this.mReadExecutor);
      return paramAtomicBoolean;
    }
    catch (Exception paramAtomicBoolean)
    {
      FLog.w(TAG, paramAtomicBoolean, "Failed to schedule disk-cache read for %s", new Object[] { paramCacheKey.getUriString() });
    }
    return Task.forError(paramAtomicBoolean);
  }

  private PooledByteBuffer readFromDiskCache(CacheKey paramCacheKey)
    throws IOException
  {
    try
    {
      FLog.v(TAG, "Disk cache read for %s", paramCacheKey.getUriString());
      Object localObject1 = this.mFileCache.getResource(paramCacheKey);
      if (localObject1 == null)
      {
        FLog.v(TAG, "Disk cache miss for %s", paramCacheKey.getUriString());
        this.mImageCacheStatsTracker.onDiskCacheMiss();
        return null;
      }
      FLog.v(TAG, "Found entry in disk cache for %s", paramCacheKey.getUriString());
      this.mImageCacheStatsTracker.onDiskCacheHit();
      InputStream localInputStream = ((BinaryResource)localObject1).openStream();
      try
      {
        localObject1 = this.mPooledByteBufferFactory.newByteBuffer(localInputStream, (int)((BinaryResource)localObject1).size());
        localInputStream.close();
        FLog.v(TAG, "Successful read from disk cache for %s", paramCacheKey.getUriString());
        return localObject1;
      }
      finally
      {
        localInputStream.close();
      }
    }
    catch (IOException localIOException)
    {
      FLog.w(TAG, localIOException, "Exception reading from cache for %s", new Object[] { paramCacheKey.getUriString() });
      this.mImageCacheStatsTracker.onDiskCacheGetFail();
      throw localIOException;
    }
  }

  private void writeToDiskCache(CacheKey paramCacheKey, final EncodedImage paramEncodedImage)
  {
    FLog.v(TAG, "About to write to disk-cache for key %s", paramCacheKey.getUriString());
    try
    {
      this.mFileCache.insert(paramCacheKey, new WriterCallback()
      {
        public void write(OutputStream paramAnonymousOutputStream)
          throws IOException
        {
          BufferedDiskCache.this.mPooledByteStreams.copy(paramEncodedImage.getInputStream(), paramAnonymousOutputStream);
        }
      });
      FLog.v(TAG, "Successful disk-cache write for key %s", paramCacheKey.getUriString());
      return;
    }
    catch (IOException paramEncodedImage)
    {
      FLog.w(TAG, paramEncodedImage, "Failed to write to disk-cache for key %s", new Object[] { paramCacheKey.getUriString() });
    }
  }

  public Task<Void> clearAll()
  {
    this.mStagingArea.clearAll();
    try
    {
      Task localTask = Task.call(new Callable()
      {
        public Void call()
          throws Exception
        {
          BufferedDiskCache.this.mStagingArea.clearAll();
          BufferedDiskCache.this.mFileCache.clearAll();
          return null;
        }
      }
      , this.mWriteExecutor);
      return localTask;
    }
    catch (Exception localException)
    {
      FLog.w(TAG, localException, "Failed to schedule disk-cache clear", new Object[0]);
      return Task.forError(localException);
    }
  }

  public Task<Boolean> contains(CacheKey paramCacheKey)
  {
    if (containsSync(paramCacheKey))
      return Task.forResult(Boolean.valueOf(true));
    return containsAsync(paramCacheKey);
  }

  public boolean containsSync(CacheKey paramCacheKey)
  {
    return (this.mStagingArea.containsKey(paramCacheKey)) || (this.mFileCache.hasKeySync(paramCacheKey));
  }

  public boolean diskCheckSync(CacheKey paramCacheKey)
  {
    if (containsSync(paramCacheKey))
      return true;
    return checkInStagingAreaAndFileCache(paramCacheKey);
  }

  public Task<EncodedImage> get(CacheKey paramCacheKey, AtomicBoolean paramAtomicBoolean)
  {
    EncodedImage localEncodedImage = this.mStagingArea.get(paramCacheKey);
    if (localEncodedImage != null)
      return foundPinnedImage(paramCacheKey, localEncodedImage);
    return getAsync(paramCacheKey, paramAtomicBoolean);
  }

  public void put(final CacheKey paramCacheKey, EncodedImage paramEncodedImage)
  {
    Preconditions.checkNotNull(paramCacheKey);
    Preconditions.checkArgument(EncodedImage.isValid(paramEncodedImage));
    this.mStagingArea.put(paramCacheKey, paramEncodedImage);
    final EncodedImage localEncodedImage = EncodedImage.cloneOrNull(paramEncodedImage);
    try
    {
      this.mWriteExecutor.execute(new Runnable()
      {
        public void run()
        {
          try
          {
            BufferedDiskCache.this.writeToDiskCache(paramCacheKey, localEncodedImage);
            return;
          }
          finally
          {
            BufferedDiskCache.this.mStagingArea.remove(paramCacheKey, localEncodedImage);
            EncodedImage.closeSafely(localEncodedImage);
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      FLog.w(TAG, localException, "Failed to schedule disk-cache write for %s", new Object[] { paramCacheKey.getUriString() });
      this.mStagingArea.remove(paramCacheKey, paramEncodedImage);
      EncodedImage.closeSafely(localEncodedImage);
    }
  }

  public Task<Void> remove(final CacheKey paramCacheKey)
  {
    Preconditions.checkNotNull(paramCacheKey);
    this.mStagingArea.remove(paramCacheKey);
    try
    {
      Task localTask = Task.call(new Callable()
      {
        public Void call()
          throws Exception
        {
          BufferedDiskCache.this.mStagingArea.remove(paramCacheKey);
          BufferedDiskCache.this.mFileCache.remove(paramCacheKey);
          return null;
        }
      }
      , this.mWriteExecutor);
      return localTask;
    }
    catch (Exception localException)
    {
      FLog.w(TAG, localException, "Failed to schedule disk-cache remove for %s", new Object[] { paramCacheKey.getUriString() });
      return Task.forError(localException);
    }
  }
}