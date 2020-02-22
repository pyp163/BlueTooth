package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CountingMemoryCache<K, V>
  implements MemoryCache<K, V>, MemoryTrimmable
{

  @VisibleForTesting
  static final long PARAMS_INTERCHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5L);
  private final CacheTrimStrategy mCacheTrimStrategy;

  @VisibleForTesting
  @GuardedBy("this")
  final CountingLruMap<K, Entry<K, V>> mCachedEntries;

  @VisibleForTesting
  @GuardedBy("this")
  final CountingLruMap<K, Entry<K, V>> mExclusiveEntries;

  @GuardedBy("this")
  private long mLastCacheParamsCheck;

  @GuardedBy("this")
  protected MemoryCacheParams mMemoryCacheParams;
  private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;

  @VisibleForTesting
  @GuardedBy("this")
  final Map<Bitmap, Object> mOtherEntries = new WeakHashMap();
  private final ValueDescriptor<V> mValueDescriptor;

  public CountingMemoryCache(ValueDescriptor<V> paramValueDescriptor, CacheTrimStrategy paramCacheTrimStrategy, Supplier<MemoryCacheParams> paramSupplier)
  {
    this.mValueDescriptor = paramValueDescriptor;
    this.mExclusiveEntries = new CountingLruMap(wrapValueDescriptor(paramValueDescriptor));
    this.mCachedEntries = new CountingLruMap(wrapValueDescriptor(paramValueDescriptor));
    this.mCacheTrimStrategy = paramCacheTrimStrategy;
    this.mMemoryCacheParamsSupplier = paramSupplier;
    this.mMemoryCacheParams = ((MemoryCacheParams)this.mMemoryCacheParamsSupplier.get());
    this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
  }

  private boolean canCacheNewValue(V paramV)
  {
    try
    {
      int i = this.mValueDescriptor.getSizeInBytes(paramV);
      int j = this.mMemoryCacheParams.maxCacheEntrySize;
      boolean bool = true;
      if ((i <= j) && (getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1))
      {
        j = getInUseSizeInBytes();
        int k = this.mMemoryCacheParams.maxCacheSize;
        if (j <= k - i);
      }
      else
      {
        bool = false;
      }
      return bool;
    }
    finally
    {
    }
    throw paramV;
  }

  private void decreaseClientCount(Entry<K, V> paramEntry)
  {
    while (true)
    {
      try
      {
        Preconditions.checkNotNull(paramEntry);
        if (paramEntry.clientCount > 0)
        {
          bool = true;
          Preconditions.checkState(bool);
          paramEntry.clientCount -= 1;
          return;
        }
      }
      finally
      {
      }
      boolean bool = false;
    }
  }

  private void increaseClientCount(Entry<K, V> paramEntry)
  {
    try
    {
      Preconditions.checkNotNull(paramEntry);
      Preconditions.checkState(paramEntry.isOrphan ^ true);
      paramEntry.clientCount += 1;
      return;
    }
    finally
    {
      paramEntry = finally;
    }
    throw paramEntry;
  }

  private void makeOrphan(Entry<K, V> paramEntry)
  {
    try
    {
      Preconditions.checkNotNull(paramEntry);
      Preconditions.checkState(paramEntry.isOrphan ^ true);
      paramEntry.isOrphan = true;
      return;
    }
    finally
    {
      paramEntry = finally;
    }
    throw paramEntry;
  }

  private void makeOrphans(@Nullable ArrayList<Entry<K, V>> paramArrayList)
  {
    if (paramArrayList != null)
      try
      {
        paramArrayList = paramArrayList.iterator();
        while (paramArrayList.hasNext())
          makeOrphan((Entry)paramArrayList.next());
      }
      finally
      {
      }
  }

  private boolean maybeAddToExclusives(Entry<K, V> paramEntry)
  {
    try
    {
      if ((!paramEntry.isOrphan) && (paramEntry.clientCount == 0))
      {
        this.mExclusiveEntries.put(paramEntry.key, paramEntry);
        return true;
      }
      return false;
    }
    finally
    {
      paramEntry = finally;
    }
    throw paramEntry;
  }

  private void maybeClose(@Nullable ArrayList<Entry<K, V>> paramArrayList)
  {
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
        CloseableReference.closeSafely(referenceToClose((Entry)paramArrayList.next()));
    }
  }

  private void maybeEvictEntries()
  {
    try
    {
      ArrayList localArrayList = trimExclusivelyOwnedEntries(Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount()), Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes()));
      makeOrphans(localArrayList);
      maybeClose(localArrayList);
      maybeNotifyExclusiveEntryRemoval(localArrayList);
      return;
    }
    finally
    {
    }
  }

  private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable Entry<K, V> paramEntry)
  {
    if ((paramEntry != null) && (paramEntry.observer != null))
      paramEntry.observer.onExclusivityChanged(paramEntry.key, true);
  }

  private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable Entry<K, V> paramEntry)
  {
    if ((paramEntry != null) && (paramEntry.observer != null))
      paramEntry.observer.onExclusivityChanged(paramEntry.key, false);
  }

  private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<Entry<K, V>> paramArrayList)
  {
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
        maybeNotifyExclusiveEntryRemoval((Entry)paramArrayList.next());
    }
  }

  private void maybeUpdateCacheParams()
  {
    try
    {
      long l1 = this.mLastCacheParamsCheck;
      long l2 = PARAMS_INTERCHECK_INTERVAL_MS;
      long l3 = SystemClock.uptimeMillis();
      if (l1 + l2 > l3)
        return;
      this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
      this.mMemoryCacheParams = ((MemoryCacheParams)this.mMemoryCacheParamsSupplier.get());
      return;
    }
    finally
    {
    }
  }

  private CloseableReference<V> newClientReference(final Entry<K, V> paramEntry)
  {
    try
    {
      increaseClientCount(paramEntry);
      paramEntry = CloseableReference.of(paramEntry.valueRef.get(), new ResourceReleaser()
      {
        public void release(V paramAnonymousV)
        {
          CountingMemoryCache.this.releaseClientReference(paramEntry);
        }
      });
      return paramEntry;
    }
    finally
    {
      paramEntry = finally;
    }
    throw paramEntry;
  }

  @Nullable
  private CloseableReference<V> referenceToClose(Entry<K, V> paramEntry)
  {
    try
    {
      Preconditions.checkNotNull(paramEntry);
      if ((paramEntry.isOrphan) && (paramEntry.clientCount == 0))
        paramEntry = paramEntry.valueRef;
      else
        paramEntry = null;
      return paramEntry;
    }
    finally
    {
      paramEntry = finally;
    }
    throw paramEntry;
  }

  private void releaseClientReference(Entry<K, V> paramEntry)
  {
    Preconditions.checkNotNull(paramEntry);
    try
    {
      decreaseClientCount(paramEntry);
      boolean bool = maybeAddToExclusives(paramEntry);
      CloseableReference localCloseableReference = referenceToClose(paramEntry);
      CloseableReference.closeSafely(localCloseableReference);
      if (!bool)
        paramEntry = null;
      maybeNotifyExclusiveEntryInsertion(paramEntry);
      maybeUpdateCacheParams();
      maybeEvictEntries();
      return;
    }
    finally
    {
    }
    throw paramEntry;
  }

  @Nullable
  private ArrayList<Entry<K, V>> trimExclusivelyOwnedEntries(int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = Math.max(paramInt1, 0);
      paramInt2 = Math.max(paramInt2, 0);
      int i;
      if (this.mExclusiveEntries.getCount() <= paramInt1)
      {
        i = this.mExclusiveEntries.getSizeInBytes();
        if (i <= paramInt2)
          return null;
      }
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        if (this.mExclusiveEntries.getCount() <= paramInt1)
        {
          i = this.mExclusiveEntries.getSizeInBytes();
          if (i <= paramInt2)
            return localArrayList;
        }
        Object localObject2 = this.mExclusiveEntries.getFirstKey();
        this.mExclusiveEntries.remove(localObject2);
        localArrayList.add(this.mCachedEntries.remove(localObject2));
      }
    }
    finally
    {
    }
  }

  private ValueDescriptor<Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> paramValueDescriptor)
  {
    return new ValueDescriptor()
    {
      public int getSizeInBytes(CountingMemoryCache.Entry<K, V> paramAnonymousEntry)
      {
        return paramValueDescriptor.getSizeInBytes(paramAnonymousEntry.valueRef.get());
      }
    };
  }

  public CloseableReference<V> cache(K paramK, CloseableReference<V> paramCloseableReference)
  {
    return cache(paramK, paramCloseableReference, null);
  }

  public CloseableReference<V> cache(K paramK, CloseableReference<V> paramCloseableReference, EntryStateObserver<K> paramEntryStateObserver)
  {
    Preconditions.checkNotNull(paramK);
    Preconditions.checkNotNull(paramCloseableReference);
    maybeUpdateCacheParams();
    while (true)
    {
      try
      {
        Entry localEntry = (Entry)this.mExclusiveEntries.remove(paramK);
        localObject = (Entry)this.mCachedEntries.remove(paramK);
        CloseableReference localCloseableReference = null;
        if (localObject != null)
        {
          makeOrphan((Entry)localObject);
          localObject = referenceToClose((Entry)localObject);
          if (canCacheNewValue(paramCloseableReference.get()))
          {
            paramCloseableReference = Entry.of(paramK, paramCloseableReference, paramEntryStateObserver);
            this.mCachedEntries.put(paramK, paramCloseableReference);
            localCloseableReference = newClientReference(paramCloseableReference);
          }
          CloseableReference.closeSafely((CloseableReference)localObject);
          maybeNotifyExclusiveEntryRemoval(localEntry);
          maybeEvictEntries();
          return localCloseableReference;
        }
      }
      finally
      {
      }
      Object localObject = null;
    }
  }

  public void clear()
  {
    try
    {
      ArrayList localArrayList1 = this.mExclusiveEntries.clear();
      ArrayList localArrayList2 = this.mCachedEntries.clear();
      makeOrphans(localArrayList2);
      maybeClose(localArrayList2);
      maybeNotifyExclusiveEntryRemoval(localArrayList1);
      maybeUpdateCacheParams();
      return;
    }
    finally
    {
    }
  }

  public boolean contains(Predicate<K> paramPredicate)
  {
    try
    {
      boolean bool = this.mCachedEntries.getMatchingEntries(paramPredicate).isEmpty();
      return bool ^ true;
    }
    finally
    {
      paramPredicate = finally;
    }
    throw paramPredicate;
  }

  public boolean contains(K paramK)
  {
    try
    {
      boolean bool = this.mCachedEntries.contains(paramK);
      return bool;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  @Nullable
  public CloseableReference<V> get(K paramK)
  {
    Preconditions.checkNotNull(paramK);
    while (true)
    {
      try
      {
        Entry localEntry = (Entry)this.mExclusiveEntries.remove(paramK);
        paramK = (Entry)this.mCachedEntries.get(paramK);
        if (paramK != null)
        {
          paramK = newClientReference(paramK);
          maybeNotifyExclusiveEntryRemoval(localEntry);
          maybeUpdateCacheParams();
          maybeEvictEntries();
          return paramK;
        }
      }
      finally
      {
      }
      paramK = null;
    }
  }

  public int getCount()
  {
    try
    {
      int i = this.mCachedEntries.getCount();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getEvictionQueueCount()
  {
    try
    {
      int i = this.mExclusiveEntries.getCount();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getEvictionQueueSizeInBytes()
  {
    try
    {
      int i = this.mExclusiveEntries.getSizeInBytes();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getInUseCount()
  {
    try
    {
      int i = this.mCachedEntries.getCount();
      int j = this.mExclusiveEntries.getCount();
      return i - j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getInUseSizeInBytes()
  {
    try
    {
      int i = this.mCachedEntries.getSizeInBytes();
      int j = this.mExclusiveEntries.getSizeInBytes();
      return i - j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getSizeInBytes()
  {
    try
    {
      int i = this.mCachedEntries.getSizeInBytes();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int removeAll(Predicate<K> paramPredicate)
  {
    try
    {
      ArrayList localArrayList = this.mExclusiveEntries.removeAll(paramPredicate);
      paramPredicate = this.mCachedEntries.removeAll(paramPredicate);
      makeOrphans(paramPredicate);
      maybeClose(paramPredicate);
      maybeNotifyExclusiveEntryRemoval(localArrayList);
      maybeUpdateCacheParams();
      maybeEvictEntries();
      return paramPredicate.size();
    }
    finally
    {
    }
    throw paramPredicate;
  }

  @Nullable
  public CloseableReference<V> reuse(K paramK)
  {
    Preconditions.checkNotNull(paramK);
    while (true)
    {
      try
      {
        Entry localEntry = (Entry)this.mExclusiveEntries.remove(paramK);
        i = 1;
        boolean bool = false;
        if (localEntry != null)
        {
          paramK = (Entry)this.mCachedEntries.remove(paramK);
          Preconditions.checkNotNull(paramK);
          if (paramK.clientCount == 0)
            bool = true;
          Preconditions.checkState(bool);
          paramK = paramK.valueRef;
          if (i != 0)
            maybeNotifyExclusiveEntryRemoval(localEntry);
          return paramK;
        }
      }
      finally
      {
      }
      paramK = null;
      int i = 0;
    }
  }

  public void trim(MemoryTrimType paramMemoryTrimType)
  {
    double d = this.mCacheTrimStrategy.getTrimRatio(paramMemoryTrimType);
    try
    {
      paramMemoryTrimType = trimExclusivelyOwnedEntries(2147483647, Math.max(0, (int)(this.mCachedEntries.getSizeInBytes() * (1.0D - d)) - getInUseSizeInBytes()));
      makeOrphans(paramMemoryTrimType);
      maybeClose(paramMemoryTrimType);
      maybeNotifyExclusiveEntryRemoval(paramMemoryTrimType);
      maybeUpdateCacheParams();
      maybeEvictEntries();
      return;
    }
    finally
    {
    }
    throw paramMemoryTrimType;
  }

  public static abstract interface CacheTrimStrategy
  {
    public abstract double getTrimRatio(MemoryTrimType paramMemoryTrimType);
  }

  @VisibleForTesting
  static class Entry<K, V>
  {
    public int clientCount;
    public boolean isOrphan;
    public final K key;

    @Nullable
    public final CountingMemoryCache.EntryStateObserver<K> observer;
    public final CloseableReference<V> valueRef;

    private Entry(K paramK, CloseableReference<V> paramCloseableReference, @Nullable CountingMemoryCache.EntryStateObserver<K> paramEntryStateObserver)
    {
      this.key = Preconditions.checkNotNull(paramK);
      this.valueRef = ((CloseableReference)Preconditions.checkNotNull(CloseableReference.cloneOrNull(paramCloseableReference)));
      this.clientCount = 0;
      this.isOrphan = false;
      this.observer = paramEntryStateObserver;
    }

    @VisibleForTesting
    static <K, V> Entry<K, V> of(K paramK, CloseableReference<V> paramCloseableReference, @Nullable CountingMemoryCache.EntryStateObserver<K> paramEntryStateObserver)
    {
      return new Entry(paramK, paramCloseableReference, paramEntryStateObserver);
    }
  }

  public static abstract interface EntryStateObserver<K>
  {
    public abstract void onExclusivityChanged(K paramK, boolean paramBoolean);
  }
}