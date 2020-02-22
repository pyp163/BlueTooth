package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class BasePool<V>
  implements Pool<V>
{
  private final Class<?> TAG = getClass();
  private boolean mAllowNewBuckets;

  @VisibleForTesting
  final SparseArray<Bucket<V>> mBuckets;

  @VisibleForTesting
  @GuardedBy("this")
  final Counter mFree;

  @VisibleForTesting
  final Set<V> mInUseValues;
  final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
  final PoolParams mPoolParams;
  private final PoolStatsTracker mPoolStatsTracker;

  @VisibleForTesting
  @GuardedBy("this")
  final Counter mUsed;

  public BasePool(MemoryTrimmableRegistry paramMemoryTrimmableRegistry, PoolParams paramPoolParams, PoolStatsTracker paramPoolStatsTracker)
  {
    this.mMemoryTrimmableRegistry = ((MemoryTrimmableRegistry)Preconditions.checkNotNull(paramMemoryTrimmableRegistry));
    this.mPoolParams = ((PoolParams)Preconditions.checkNotNull(paramPoolParams));
    this.mPoolStatsTracker = ((PoolStatsTracker)Preconditions.checkNotNull(paramPoolStatsTracker));
    this.mBuckets = new SparseArray();
    if (this.mPoolParams.fixBucketsReinitialization)
      initBuckets();
    else
      legacyInitBuckets(new SparseIntArray(0));
    this.mInUseValues = Sets.newIdentityHashSet();
    this.mFree = new Counter();
    this.mUsed = new Counter();
  }

  private void ensurePoolSizeInvariant()
  {
    while (true)
    {
      try
      {
        if (!isMaxSizeSoftCapExceeded())
          break label39;
        if (this.mFree.mNumBytes == 0)
        {
          break label39;
          Preconditions.checkState(bool);
        }
      }
      finally
      {
      }
      boolean bool = false;
      continue;
      label39: bool = true;
    }
  }

  private void fillBuckets(SparseIntArray paramSparseIntArray)
  {
    this.mBuckets.clear();
    int i = 0;
    while (i < paramSparseIntArray.size())
    {
      int j = paramSparseIntArray.keyAt(i);
      int k = paramSparseIntArray.valueAt(i);
      this.mBuckets.put(j, new Bucket(getSizeInBytes(j), k, 0, this.mPoolParams.fixBucketsReinitialization));
      i += 1;
    }
  }

  private Bucket<V> getBucketIfPresent(int paramInt)
  {
    try
    {
      Bucket localBucket = (Bucket)this.mBuckets.get(paramInt);
      return localBucket;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void initBuckets()
  {
    try
    {
      SparseIntArray localSparseIntArray = this.mPoolParams.bucketSizes;
      if (localSparseIntArray != null)
      {
        fillBuckets(localSparseIntArray);
        this.mAllowNewBuckets = false;
      }
      else
      {
        this.mAllowNewBuckets = true;
      }
      return;
    }
    finally
    {
    }
  }

  private void legacyInitBuckets(SparseIntArray paramSparseIntArray)
  {
    try
    {
      Preconditions.checkNotNull(paramSparseIntArray);
      this.mBuckets.clear();
      SparseIntArray localSparseIntArray = this.mPoolParams.bucketSizes;
      if (localSparseIntArray != null)
      {
        int i = 0;
        while (i < localSparseIntArray.size())
        {
          int j = localSparseIntArray.keyAt(i);
          int k = localSparseIntArray.valueAt(i);
          int m = paramSparseIntArray.get(j, 0);
          this.mBuckets.put(j, new Bucket(getSizeInBytes(j), k, m, this.mPoolParams.fixBucketsReinitialization));
          i += 1;
        }
        this.mAllowNewBuckets = false;
      }
      else
      {
        this.mAllowNewBuckets = true;
      }
      return;
    }
    finally
    {
    }
    throw paramSparseIntArray;
  }

  @SuppressLint({"InvalidAccessToGuardedField"})
  private void logStats()
  {
    if (FLog.isLoggable(2))
      FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
  }

  private List<Bucket<V>> refillBuckets()
  {
    ArrayList localArrayList = new ArrayList(this.mBuckets.size());
    int j = this.mBuckets.size();
    int i = 0;
    while (i < j)
    {
      Bucket localBucket = (Bucket)this.mBuckets.valueAt(i);
      int k = localBucket.mItemSize;
      int m = localBucket.mMaxLength;
      int n = localBucket.getInUseCount();
      if (localBucket.getFreeListSize() > 0)
        localArrayList.add(localBucket);
      this.mBuckets.setValueAt(i, new Bucket(getSizeInBytes(k), m, n, this.mPoolParams.fixBucketsReinitialization));
      i += 1;
    }
    return localArrayList;
  }

  protected abstract V alloc(int paramInt);

  @VisibleForTesting
  boolean canAllocate(int paramInt)
  {
    try
    {
      int i = this.mPoolParams.maxSizeHardCap;
      if (paramInt > i - this.mUsed.mNumBytes)
      {
        this.mPoolStatsTracker.onHardCapReached();
        return false;
      }
      int j = this.mPoolParams.maxSizeSoftCap;
      if (paramInt > j - (this.mUsed.mNumBytes + this.mFree.mNumBytes))
        trimToSize(j - paramInt);
      if (paramInt > i - (this.mUsed.mNumBytes + this.mFree.mNumBytes))
      {
        this.mPoolStatsTracker.onHardCapReached();
        return false;
      }
      return true;
    }
    finally
    {
    }
  }

  @VisibleForTesting
  protected abstract void free(V paramV);

  public V get(int paramInt)
  {
    ensurePoolSizeInvariant();
    paramInt = getBucketedSize(paramInt);
    try
    {
      Object localObject1 = getBucket(paramInt);
      Object localObject5;
      if (localObject1 != null)
      {
        localObject5 = ((Bucket)localObject1).get();
        if (localObject5 != null)
        {
          Preconditions.checkState(this.mInUseValues.add(localObject5));
          paramInt = getBucketedSizeForValue(localObject5);
          i = getSizeInBytes(paramInt);
          this.mUsed.increment(i);
          this.mFree.decrement(i);
          this.mPoolStatsTracker.onValueReuse(i);
          logStats();
          if (FLog.isLoggable(2))
            FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(localObject5)), Integer.valueOf(paramInt));
          return localObject5;
        }
      }
      int i = getSizeInBytes(paramInt);
      if (!canAllocate(i))
        throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, i);
      this.mUsed.increment(i);
      if (localObject1 != null)
        ((Bucket)localObject1).incrementInUseCount();
      localObject1 = null;
      try
      {
        localObject5 = alloc(paramInt);
        localObject1 = localObject5;
      }
      catch (Throwable localThrowable)
      {
      }
      try
      {
        this.mUsed.decrement(i);
        Bucket localBucket = getBucket(paramInt);
        if (localBucket != null)
          localBucket.decrementInUseCount();
        Throwables.propagateIfPossible(localThrowable);
        try
        {
          Preconditions.checkState(this.mInUseValues.add(localObject1));
          trimToSoftCap();
          this.mPoolStatsTracker.onAlloc(i);
          logStats();
          if (FLog.isLoggable(2))
            FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(localObject1)), Integer.valueOf(paramInt));
          return localObject1;
        }
        finally
        {
        }
      }
      finally
      {
      }
    }
    finally
    {
    }
  }

  @VisibleForTesting
  Bucket<V> getBucket(int paramInt)
  {
    try
    {
      Bucket localBucket = (Bucket)this.mBuckets.get(paramInt);
      if ((localBucket == null) && (this.mAllowNewBuckets))
      {
        if (FLog.isLoggable(2))
          FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(paramInt));
        localBucket = newBucket(paramInt);
        this.mBuckets.put(paramInt, localBucket);
        return localBucket;
      }
      return localBucket;
    }
    finally
    {
    }
  }

  protected abstract int getBucketedSize(int paramInt);

  protected abstract int getBucketedSizeForValue(V paramV);

  protected abstract int getSizeInBytes(int paramInt);

  public Map<String, Integer> getStats()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      int i = 0;
      while (i < this.mBuckets.size())
      {
        int j = this.mBuckets.keyAt(i);
        Bucket localBucket = (Bucket)this.mBuckets.valueAt(i);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("buckets_used_");
        localStringBuilder.append(getSizeInBytes(j));
        localHashMap.put(localStringBuilder.toString(), Integer.valueOf(localBucket.getInUseCount()));
        i += 1;
      }
      localHashMap.put("soft_cap", Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
      localHashMap.put("hard_cap", Integer.valueOf(this.mPoolParams.maxSizeHardCap));
      localHashMap.put("used_count", Integer.valueOf(this.mUsed.mCount));
      localHashMap.put("used_bytes", Integer.valueOf(this.mUsed.mNumBytes));
      localHashMap.put("free_count", Integer.valueOf(this.mFree.mCount));
      localHashMap.put("free_bytes", Integer.valueOf(this.mFree.mNumBytes));
      return localHashMap;
    }
    finally
    {
    }
  }

  protected void initialize()
  {
    this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
    this.mPoolStatsTracker.setBasePool(this);
  }

  @VisibleForTesting
  boolean isMaxSizeSoftCapExceeded()
  {
    while (true)
    {
      try
      {
        if (this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap)
        {
          bool = true;
          if (bool)
            this.mPoolStatsTracker.onSoftCapReached();
          return bool;
        }
      }
      finally
      {
      }
      boolean bool = false;
    }
  }

  protected boolean isReusable(V paramV)
  {
    Preconditions.checkNotNull(paramV);
    return true;
  }

  Bucket<V> newBucket(int paramInt)
  {
    return new Bucket(getSizeInBytes(paramInt), 2147483647, 0, this.mPoolParams.fixBucketsReinitialization);
  }

  protected void onParamsChanged()
  {
  }

  public void release(V paramV)
  {
    Preconditions.checkNotNull(paramV);
    int i = getBucketedSizeForValue(paramV);
    int j = getSizeInBytes(i);
    try
    {
      Bucket localBucket = getBucketIfPresent(i);
      if (!this.mInUseValues.remove(paramV))
      {
        FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", new Object[] { Integer.valueOf(System.identityHashCode(paramV)), Integer.valueOf(i) });
        free(paramV);
        this.mPoolStatsTracker.onFree(j);
      }
      else if ((localBucket != null) && (!localBucket.isMaxLengthExceeded()) && (!isMaxSizeSoftCapExceeded()) && (isReusable(paramV)))
      {
        localBucket.release(paramV);
        this.mFree.increment(j);
        this.mUsed.decrement(j);
        this.mPoolStatsTracker.onValueRelease(j);
        if (FLog.isLoggable(2))
          FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(paramV)), Integer.valueOf(i));
      }
      else
      {
        if (localBucket != null)
          localBucket.decrementInUseCount();
        if (FLog.isLoggable(2))
          FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(paramV)), Integer.valueOf(i));
        free(paramV);
        this.mUsed.decrement(j);
        this.mPoolStatsTracker.onFree(j);
      }
      logStats();
      return;
    }
    finally
    {
    }
    throw paramV;
  }

  public void trim(MemoryTrimType paramMemoryTrimType)
  {
    trimToNothing();
  }

  @VisibleForTesting
  void trimToNothing()
  {
    try
    {
      boolean bool = this.mPoolParams.fixBucketsReinitialization;
      int j = 0;
      Object localObject1;
      Object localObject3;
      Object localObject4;
      if (bool)
      {
        localObject1 = refillBuckets();
      }
      else
      {
        localObject1 = new ArrayList(this.mBuckets.size());
        localObject3 = new SparseIntArray();
        i = 0;
        while (i < this.mBuckets.size())
        {
          localObject4 = (Bucket)this.mBuckets.valueAt(i);
          if (((Bucket)localObject4).getFreeListSize() > 0)
            ((List)localObject1).add(localObject4);
          ((SparseIntArray)localObject3).put(this.mBuckets.keyAt(i), ((Bucket)localObject4).getInUseCount());
          i += 1;
        }
        legacyInitBuckets((SparseIntArray)localObject3);
      }
      this.mFree.reset();
      logStats();
      onParamsChanged();
      int i = j;
      if (i < ((List)localObject1).size())
      {
        localObject3 = (Bucket)((List)localObject1).get(i);
        while (true)
        {
          localObject4 = ((Bucket)localObject3).pop();
          if (localObject4 == null)
          {
            i += 1;
            break;
          }
          free(localObject4);
        }
      }
      return;
    }
    finally
    {
    }
  }

  @VisibleForTesting
  void trimToSize(int paramInt)
  {
    while (true)
    {
      int i;
      try
      {
        int j = Math.min(this.mUsed.mNumBytes + this.mFree.mNumBytes - paramInt, this.mFree.mNumBytes);
        if (j <= 0)
          return;
        if (FLog.isLoggable(2))
          FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(paramInt), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(j));
        logStats();
        i = 0;
        if ((i < this.mBuckets.size()) && (j > 0))
        {
          Bucket localBucket = (Bucket)this.mBuckets.valueAt(i);
          if (j > 0)
          {
            Object localObject2 = localBucket.pop();
            if (localObject2 != null)
            {
              free(localObject2);
              j -= localBucket.mItemSize;
              this.mFree.decrement(localBucket.mItemSize);
              continue;
            }
          }
        }
        else
        {
          logStats();
          if (FLog.isLoggable(2))
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(paramInt), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
          return;
        }
      }
      finally
      {
      }
      i += 1;
    }
  }

  @VisibleForTesting
  void trimToSoftCap()
  {
    try
    {
      if (isMaxSizeSoftCapExceeded())
        trimToSize(this.mPoolParams.maxSizeSoftCap);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @VisibleForTesting
  @NotThreadSafe
  static class Counter
  {
    private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
    int mCount;
    int mNumBytes;

    public void decrement(int paramInt)
    {
      if ((this.mNumBytes >= paramInt) && (this.mCount > 0))
      {
        this.mCount -= 1;
        this.mNumBytes -= paramInt;
        return;
      }
      FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount) });
    }

    public void increment(int paramInt)
    {
      this.mCount += 1;
      this.mNumBytes += paramInt;
    }

    public void reset()
    {
      this.mCount = 0;
      this.mNumBytes = 0;
    }
  }

  public static class InvalidSizeException extends RuntimeException
  {
    public InvalidSizeException(Object paramObject)
    {
      super();
    }
  }

  public static class InvalidValueException extends RuntimeException
  {
    public InvalidValueException(Object paramObject)
    {
      super();
    }
  }

  public static class PoolSizeViolationException extends RuntimeException
  {
    public PoolSizeViolationException(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super();
    }
  }

  public static class SizeTooLargeException extends BasePool.InvalidSizeException
  {
    public SizeTooLargeException(Object paramObject)
    {
      super();
    }
  }
}