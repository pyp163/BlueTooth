package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.CacheEvent;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.statfs.StatFsHelper.StorageType;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DiskStorageCache
  implements FileCache, DiskTrimmable
{
  private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30L);
  private static final long FUTURE_TIMESTAMP_THRESHOLD_MS;
  private static final String SHARED_PREFS_FILENAME_PREFIX = "disk_entries_list";
  public static final int START_OF_VERSIONING = 1;
  private static final Class<?> TAG = DiskStorageCache.class;
  private static final double TRIMMING_LOWER_BOUND = 0.02D;
  private static final long UNINITIALIZED = -1L;
  private final CacheErrorLogger mCacheErrorLogger;
  private final CacheEventListener mCacheEventListener;
  private long mCacheSizeLastUpdateTime;
  private long mCacheSizeLimit;
  private final long mCacheSizeLimitMinimum;
  private final CacheStats mCacheStats;
  private final Clock mClock;
  private final CountDownLatch mCountDownLatch;
  private final long mDefaultCacheSizeLimit;
  private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
  private final boolean mIndexPopulateAtStartupEnabled;
  private boolean mIndexReady;
  private final Object mLock = new Object();
  private final long mLowDiskSpaceCacheSizeLimit;

  @VisibleForTesting
  @GuardedBy("mLock")
  final Set<String> mResourceIndex;
  private final StatFsHelper mStatFsHelper;
  private final DiskStorage mStorage;

  static
  {
    FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2L);
  }

  public DiskStorageCache(DiskStorage paramDiskStorage, EntryEvictionComparatorSupplier paramEntryEvictionComparatorSupplier, Params paramParams, CacheEventListener paramCacheEventListener, CacheErrorLogger paramCacheErrorLogger, @Nullable DiskTrimmableRegistry paramDiskTrimmableRegistry, Context paramContext, Executor paramExecutor, boolean paramBoolean)
  {
    this.mLowDiskSpaceCacheSizeLimit = paramParams.mLowDiskSpaceCacheSizeLimit;
    this.mDefaultCacheSizeLimit = paramParams.mDefaultCacheSizeLimit;
    this.mCacheSizeLimit = paramParams.mDefaultCacheSizeLimit;
    this.mStatFsHelper = StatFsHelper.getInstance();
    this.mStorage = paramDiskStorage;
    this.mEntryEvictionComparatorSupplier = paramEntryEvictionComparatorSupplier;
    this.mCacheSizeLastUpdateTime = -1L;
    this.mCacheEventListener = paramCacheEventListener;
    this.mCacheSizeLimitMinimum = paramParams.mCacheSizeLimitMinimum;
    this.mCacheErrorLogger = paramCacheErrorLogger;
    this.mCacheStats = new CacheStats();
    if (paramDiskTrimmableRegistry != null)
      paramDiskTrimmableRegistry.registerDiskTrimmable(this);
    this.mClock = SystemClock.get();
    this.mIndexPopulateAtStartupEnabled = paramBoolean;
    this.mResourceIndex = new HashSet();
    if (this.mIndexPopulateAtStartupEnabled)
    {
      this.mCountDownLatch = new CountDownLatch(1);
      paramExecutor.execute(new Runnable()
      {
        public void run()
        {
          synchronized (DiskStorageCache.this.mLock)
          {
            DiskStorageCache.this.maybeUpdateFileCacheSize();
            DiskStorageCache.access$202(DiskStorageCache.this, true);
            DiskStorageCache.this.mCountDownLatch.countDown();
            return;
          }
        }
      });
      return;
    }
    this.mCountDownLatch = new CountDownLatch(0);
  }

  private BinaryResource endInsert(DiskStorage.Inserter paramInserter, CacheKey paramCacheKey, String paramString)
    throws IOException
  {
    synchronized (this.mLock)
    {
      paramInserter = paramInserter.commit(paramCacheKey);
      this.mResourceIndex.add(paramString);
      this.mCacheStats.increment(paramInserter.size(), 1L);
      return paramInserter;
    }
  }

  @GuardedBy("mLock")
  private void evictAboveSize(long paramLong, CacheEventListener.EvictionReason paramEvictionReason)
    throws IOException
  {
    try
    {
      localObject1 = getSortedEntries(this.mStorage.getEntries());
      long l2 = this.mCacheStats.getSize();
      int i = 0;
      localObject1 = ((Collection)localObject1).iterator();
      long l1 = 0L;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (DiskStorage.Entry)((Iterator)localObject1).next();
        if (l1 > l2 - paramLong)
          break;
        long l3 = this.mStorage.remove((DiskStorage.Entry)localObject2);
        this.mResourceIndex.remove(((DiskStorage.Entry)localObject2).getId());
        if (l3 > 0L)
        {
          i += 1;
          l1 += l3;
          localObject2 = SettableCacheEvent.obtain().setResourceId(((DiskStorage.Entry)localObject2).getId()).setEvictionReason(paramEvictionReason).setItemSize(l3).setCacheSize(l2 - l1).setCacheLimit(paramLong);
          this.mCacheEventListener.onEviction((CacheEvent)localObject2);
          ((SettableCacheEvent)localObject2).recycle();
        }
      }
      this.mCacheStats.increment(-l1, -i);
      this.mStorage.purgeUnexpectedResources();
      return;
    }
    catch (IOException paramEvictionReason)
    {
      Object localObject1 = this.mCacheErrorLogger;
      Object localObject2 = CacheErrorLogger.CacheErrorCategory.EVICTION;
      Class localClass = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("evictAboveSize: ");
      localStringBuilder.append(paramEvictionReason.getMessage());
      ((CacheErrorLogger)localObject1).logError((CacheErrorLogger.CacheErrorCategory)localObject2, localClass, localStringBuilder.toString(), paramEvictionReason);
    }
    throw paramEvictionReason;
  }

  private Collection<DiskStorage.Entry> getSortedEntries(Collection<DiskStorage.Entry> paramCollection)
  {
    long l1 = this.mClock.now();
    long l2 = FUTURE_TIMESTAMP_THRESHOLD_MS;
    ArrayList localArrayList1 = new ArrayList(paramCollection.size());
    ArrayList localArrayList2 = new ArrayList(paramCollection.size());
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      DiskStorage.Entry localEntry = (DiskStorage.Entry)paramCollection.next();
      if (localEntry.getTimestamp() > l1 + l2)
        localArrayList1.add(localEntry);
      else
        localArrayList2.add(localEntry);
    }
    Collections.sort(localArrayList2, this.mEntryEvictionComparatorSupplier.get());
    localArrayList1.addAll(localArrayList2);
    return localArrayList1;
  }

  private void maybeEvictFilesInCacheDir()
    throws IOException
  {
    synchronized (this.mLock)
    {
      boolean bool = maybeUpdateFileCacheSize();
      updateFileCacheSizeLimit();
      long l = this.mCacheStats.getSize();
      if ((l > this.mCacheSizeLimit) && (!bool))
      {
        this.mCacheStats.reset();
        maybeUpdateFileCacheSize();
      }
      if (l > this.mCacheSizeLimit)
        evictAboveSize(this.mCacheSizeLimit * 9L / 10L, CacheEventListener.EvictionReason.CACHE_FULL);
      return;
    }
  }

  @GuardedBy("mLock")
  private boolean maybeUpdateFileCacheSize()
  {
    long l = this.mClock.now();
    if ((this.mCacheStats.isInitialized()) && (this.mCacheSizeLastUpdateTime != -1L) && (l - this.mCacheSizeLastUpdateTime <= FILECACHE_SIZE_UPDATE_PERIOD_MS))
      return false;
    return maybeUpdateFileCacheSizeAndIndex();
  }

  @GuardedBy("mLock")
  private boolean maybeUpdateFileCacheSizeAndIndex()
  {
    long l5 = this.mClock.now();
    long l2 = FUTURE_TIMESTAMP_THRESHOLD_MS + l5;
    Object localObject1;
    if ((this.mIndexPopulateAtStartupEnabled) && (this.mResourceIndex.isEmpty()))
      localObject1 = this.mResourceIndex;
    else if (this.mIndexPopulateAtStartupEnabled)
      localObject1 = new HashSet();
    else
      localObject1 = null;
    label533: 
    while (true)
    {
      int i2;
      long l4;
      int m;
      int i1;
      try
      {
        localObject2 = this.mStorage.getEntries().iterator();
        l3 = -1L;
        j = 0;
        i = 0;
        l1 = 0L;
        n = 0;
        k = 0;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (DiskStorage.Entry)((Iterator)localObject2).next();
          i2 = k + 1;
          l4 = l1 + ((DiskStorage.Entry)localObject3).getSize();
          if (((DiskStorage.Entry)localObject3).getTimestamp() > l2)
          {
            m = j + 1;
            i1 = (int)(i + ((DiskStorage.Entry)localObject3).getSize());
            l1 = Math.max(((DiskStorage.Entry)localObject3).getTimestamp() - l5, l3);
            k = 1;
          }
          else
          {
            l1 = l3;
            k = n;
            m = j;
            i1 = i;
            if (this.mIndexPopulateAtStartupEnabled)
            {
              ((Set)localObject1).add(((DiskStorage.Entry)localObject3).getId());
              l1 = l3;
              k = n;
              m = j;
              i1 = i;
            }
          }
        }
        else
        {
          if (n == 0)
            break label533;
          localObject2 = this.mCacheErrorLogger;
          localObject3 = CacheErrorLogger.CacheErrorCategory.READ_INVALID_ENTRY;
          localClass = TAG;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Future timestamp found in ");
          localStringBuilder.append(j);
          localStringBuilder.append(" files , with a total size of ");
          localStringBuilder.append(i);
          localStringBuilder.append(" bytes, and a maximum time delta of ");
          localStringBuilder.append(l3);
          localStringBuilder.append("ms");
          ((CacheErrorLogger)localObject2).logError((CacheErrorLogger.CacheErrorCategory)localObject3, localClass, localStringBuilder.toString(), null);
          l2 = this.mCacheStats.getCount();
          l3 = k;
          if ((l2 != l3) || (this.mCacheStats.getSize() != l1))
          {
            if ((this.mIndexPopulateAtStartupEnabled) && (this.mResourceIndex != localObject1))
            {
              this.mResourceIndex.clear();
              this.mResourceIndex.addAll((Collection)localObject1);
            }
            this.mCacheStats.set(l1, l3);
          }
          this.mCacheSizeLastUpdateTime = l5;
          return true;
        }
      }
      catch (IOException localIOException)
      {
        Object localObject2 = this.mCacheErrorLogger;
        Object localObject3 = CacheErrorLogger.CacheErrorCategory.GENERIC_IO;
        Class localClass = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("calcFileCacheSize: ");
        localStringBuilder.append(localIOException.getMessage());
        ((CacheErrorLogger)localObject2).logError((CacheErrorLogger.CacheErrorCategory)localObject3, localClass, localStringBuilder.toString(), localIOException);
        return false;
      }
      long l3 = l1;
      int n = k;
      int k = i2;
      long l1 = l4;
      int j = m;
      int i = i1;
    }
  }

  private DiskStorage.Inserter startInsert(String paramString, CacheKey paramCacheKey)
    throws IOException
  {
    maybeEvictFilesInCacheDir();
    return this.mStorage.insert(paramString, paramCacheKey);
  }

  private void trimBy(double paramDouble)
  {
    try
    {
      synchronized (this.mLock)
      {
        this.mCacheStats.reset();
        maybeUpdateFileCacheSize();
        long l = this.mCacheStats.getSize();
        evictAboveSize(l - ()(paramDouble * l), CacheEventListener.EvictionReason.CACHE_MANAGER_TRIMMED);
      }
    }
    catch (IOException localIOException)
    {
      CacheErrorLogger localCacheErrorLogger = this.mCacheErrorLogger;
      CacheErrorLogger.CacheErrorCategory localCacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
      Class localClass = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("trimBy: ");
      localStringBuilder.append(localIOException.getMessage());
      localCacheErrorLogger.logError(localCacheErrorCategory, localClass, localStringBuilder.toString(), localIOException);
      return;
    }
  }

  @GuardedBy("mLock")
  private void updateFileCacheSizeLimit()
  {
    StatFsHelper.StorageType localStorageType;
    if (this.mStorage.isExternal())
      localStorageType = StatFsHelper.StorageType.EXTERNAL;
    else
      localStorageType = StatFsHelper.StorageType.INTERNAL;
    if (this.mStatFsHelper.testLowDiskSpace(localStorageType, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize()))
    {
      this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
      return;
    }
    this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
  }

  @VisibleForTesting
  protected void awaitIndex()
  {
    try
    {
      this.mCountDownLatch.await();
      return;
      label8: FLog.e(TAG, "Memory Index is not ready yet. ");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label8;
    }
  }

  public void clearAll()
  {
    try
    {
      synchronized (this.mLock)
      {
        this.mStorage.clearAll();
        this.mResourceIndex.clear();
        this.mCacheEventListener.onCleared();
      }
    }
    catch (IOException localIOException)
    {
      CacheErrorLogger localCacheErrorLogger = this.mCacheErrorLogger;
      CacheErrorLogger.CacheErrorCategory localCacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
      Class localClass = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("clearAll: ");
      localStringBuilder.append(localIOException.getMessage());
      localCacheErrorLogger.logError(localCacheErrorCategory, localClass, localStringBuilder.toString(), localIOException);
      this.mCacheStats.reset();
      return;
    }
  }

  public long clearOldEntries(long paramLong)
  {
    while (true)
    {
      long l4;
      int j;
      long l5;
      try
      {
        synchronized (this.mLock)
        {
          long l3 = this.mClock.now();
          Object localObject1 = this.mStorage.getEntries();
          long l6 = this.mCacheStats.getSize();
          i = 0;
          localObject1 = ((Collection)localObject1).iterator();
          l2 = 0L;
          l1 = 0L;
          try
          {
            if (((Iterator)localObject1).hasNext())
            {
              localObject4 = (DiskStorage.Entry)((Iterator)localObject1).next();
              l4 = Math.max(1L, Math.abs(l3 - ((DiskStorage.Entry)localObject4).getTimestamp()));
              if (l4 >= paramLong)
              {
                long l7 = this.mStorage.remove((DiskStorage.Entry)localObject4);
                this.mResourceIndex.remove(((DiskStorage.Entry)localObject4).getId());
                j = i;
                l4 = l2;
                l5 = l1;
                if (l7 <= 0L)
                  break label373;
                j = i + 1;
                l4 = l2 + l7;
                localObject4 = SettableCacheEvent.obtain().setResourceId(((DiskStorage.Entry)localObject4).getId()).setEvictionReason(CacheEventListener.EvictionReason.CONTENT_STALE).setItemSize(l7).setCacheSize(l6 - l4);
                this.mCacheEventListener.onEviction((CacheEvent)localObject4);
                ((SettableCacheEvent)localObject4).recycle();
                l5 = l1;
                break label373;
              }
              l5 = Math.max(l1, l4);
              j = i;
              l4 = l2;
              break label373;
            }
            this.mStorage.purgeUnexpectedResources();
            paramLong = l1;
            if (i > 0)
            {
              maybeUpdateFileCacheSize();
              this.mCacheStats.increment(-l2, -i);
              paramLong = l1;
            }
          }
          catch (IOException localIOException1)
          {
          }
        }
      }
      catch (IOException localIOException2)
      {
        l1 = 0L;
        Object localObject4 = this.mCacheErrorLogger;
        CacheErrorLogger.CacheErrorCategory localCacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
        Class localClass = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("clearOldEntries: ");
        localStringBuilder.append(localIOException2.getMessage());
        ((CacheErrorLogger)localObject4).logError(localCacheErrorCategory, localClass, localStringBuilder.toString(), localIOException2);
        paramLong = l1;
        return paramLong;
      }
      throw localIOException2;
      label373: int i = j;
      long l2 = l4;
      long l1 = l5;
    }
  }

  public long getCount()
  {
    return this.mCacheStats.getCount();
  }

  public DiskStorage.DiskDumpInfo getDumpInfo()
    throws IOException
  {
    return this.mStorage.getDumpInfo();
  }

  // ERROR //
  public BinaryResource getResource(CacheKey paramCacheKey)
  {
    // Byte code:
    //   0: invokestatic 261	com/facebook/cache/disk/SettableCacheEvent:obtain	()Lcom/facebook/cache/disk/SettableCacheEvent;
    //   3: aload_1
    //   4: invokevirtual 490	com/facebook/cache/disk/SettableCacheEvent:setCacheKey	(Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   7: astore 5
    //   9: aload_0
    //   10: getfield 102	com/facebook/cache/disk/DiskStorageCache:mLock	Ljava/lang/Object;
    //   13: astore 6
    //   15: aload 6
    //   17: monitorenter
    //   18: aload_1
    //   19: invokestatic 496	com/facebook/cache/common/CacheKeyUtil:getResourceIds	(Lcom/facebook/cache/common/CacheKey;)Ljava/util/List;
    //   22: astore 7
    //   24: iconst_0
    //   25: istore_2
    //   26: aconst_null
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: iload_2
    //   33: aload 7
    //   35: invokeinterface 499 1 0
    //   40: if_icmpge +44 -> 84
    //   43: aload 7
    //   45: iload_2
    //   46: invokeinterface 502 2 0
    //   51: checkcast 504	java/lang/String
    //   54: astore 4
    //   56: aload 5
    //   58: aload 4
    //   60: invokevirtual 265	com/facebook/cache/disk/SettableCacheEvent:setResourceId	(Ljava/lang/String;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   63: pop
    //   64: aload_0
    //   65: getfield 120	com/facebook/cache/disk/DiskStorageCache:mStorage	Lcom/facebook/cache/disk/DiskStorage;
    //   68: aload 4
    //   70: aload_1
    //   71: invokeinterface 507 3 0
    //   76: astore_3
    //   77: aload_3
    //   78: ifnull +131 -> 209
    //   81: goto +3 -> 84
    //   84: aload_3
    //   85: ifnonnull +29 -> 114
    //   88: aload_0
    //   89: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   92: aload 5
    //   94: invokeinterface 510 2 0
    //   99: aload_0
    //   100: getfield 155	com/facebook/cache/disk/DiskStorageCache:mResourceIndex	Ljava/util/Set;
    //   103: aload 4
    //   105: invokeinterface 255 2 0
    //   110: pop
    //   111: goto +26 -> 137
    //   114: aload_0
    //   115: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   118: aload 5
    //   120: invokeinterface 513 2 0
    //   125: aload_0
    //   126: getfield 155	com/facebook/cache/disk/DiskStorageCache:mResourceIndex	Ljava/util/Set;
    //   129: aload 4
    //   131: invokeinterface 202 2 0
    //   136: pop
    //   137: aload 6
    //   139: monitorexit
    //   140: aload 5
    //   142: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   145: aload_3
    //   146: areturn
    //   147: astore_1
    //   148: aload 6
    //   150: monitorexit
    //   151: aload_1
    //   152: athrow
    //   153: astore_1
    //   154: goto +48 -> 202
    //   157: astore_1
    //   158: aload_0
    //   159: getfield 131	com/facebook/cache/disk/DiskStorageCache:mCacheErrorLogger	Lcom/facebook/cache/common/CacheErrorLogger;
    //   162: getstatic 416	com/facebook/cache/common/CacheErrorLogger$CacheErrorCategory:GENERIC_IO	Lcom/facebook/cache/common/CacheErrorLogger$CacheErrorCategory;
    //   165: getstatic 73	com/facebook/cache/disk/DiskStorageCache:TAG	Ljava/lang/Class;
    //   168: ldc_w 514
    //   171: aload_1
    //   172: invokeinterface 318 5 0
    //   177: aload 5
    //   179: aload_1
    //   180: invokevirtual 518	com/facebook/cache/disk/SettableCacheEvent:setException	(Ljava/io/IOException;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   183: pop
    //   184: aload_0
    //   185: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   188: aload 5
    //   190: invokeinterface 521 2 0
    //   195: aload 5
    //   197: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   200: aconst_null
    //   201: areturn
    //   202: aload 5
    //   204: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   207: aload_1
    //   208: athrow
    //   209: iload_2
    //   210: iconst_1
    //   211: iadd
    //   212: istore_2
    //   213: goto -181 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   18	24	147	finally
    //   32	77	147	finally
    //   88	111	147	finally
    //   114	137	147	finally
    //   137	140	147	finally
    //   148	151	147	finally
    //   9	18	153	finally
    //   151	153	153	finally
    //   158	195	153	finally
    //   9	18	157	java/io/IOException
    //   151	153	157	java/io/IOException
  }

  public long getSize()
  {
    return this.mCacheStats.getSize();
  }

  public boolean hasKey(CacheKey paramCacheKey)
  {
    synchronized (this.mLock)
    {
      if (hasKeySync(paramCacheKey))
        return true;
    }
    try
    {
      List localList = CacheKeyUtil.getResourceIds(paramCacheKey);
      i = 0;
      if (i < localList.size())
      {
        String str = (String)localList.get(i);
        if (this.mStorage.contains(str, paramCacheKey))
        {
          this.mResourceIndex.add(str);
          return true;
        }
      }
      else
      {
        return false;
        return false;
        paramCacheKey = finally;
        throw paramCacheKey;
      }
    }
    catch (IOException paramCacheKey)
    {
      while (true)
      {
        int i;
        continue;
        i += 1;
      }
    }
  }

  public boolean hasKeySync(CacheKey paramCacheKey)
  {
    while (true)
    {
      int i;
      synchronized (this.mLock)
      {
        paramCacheKey = CacheKeyUtil.getResourceIds(paramCacheKey);
        i = 0;
        if (i < paramCacheKey.size())
        {
          String str = (String)paramCacheKey.get(i);
          if (this.mResourceIndex.contains(str))
            return true;
        }
        else
        {
          return false;
        }
      }
      i += 1;
    }
  }

  // ERROR //
  public BinaryResource insert(CacheKey paramCacheKey, com.facebook.cache.common.WriterCallback paramWriterCallback)
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 261	com/facebook/cache/disk/SettableCacheEvent:obtain	()Lcom/facebook/cache/disk/SettableCacheEvent;
    //   3: aload_1
    //   4: invokevirtual 490	com/facebook/cache/disk/SettableCacheEvent:setCacheKey	(Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   12: aload_3
    //   13: invokeinterface 536 2 0
    //   18: aload_0
    //   19: getfield 102	com/facebook/cache/disk/DiskStorageCache:mLock	Ljava/lang/Object;
    //   22: astore 4
    //   24: aload 4
    //   26: monitorenter
    //   27: aload_1
    //   28: invokestatic 540	com/facebook/cache/common/CacheKeyUtil:getFirstResourceId	(Lcom/facebook/cache/common/CacheKey;)Ljava/lang/String;
    //   31: astore 5
    //   33: aload 4
    //   35: monitorexit
    //   36: aload_3
    //   37: aload 5
    //   39: invokevirtual 265	com/facebook/cache/disk/SettableCacheEvent:setResourceId	(Ljava/lang/String;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   42: pop
    //   43: aload_0
    //   44: aload 5
    //   46: aload_1
    //   47: invokespecial 542	com/facebook/cache/disk/DiskStorageCache:startInsert	(Ljava/lang/String;Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/DiskStorage$Inserter;
    //   50: astore 4
    //   52: aload 4
    //   54: aload_2
    //   55: aload_1
    //   56: invokeinterface 546 3 0
    //   61: aload_0
    //   62: aload 4
    //   64: aload_1
    //   65: aload 5
    //   67: invokespecial 548	com/facebook/cache/disk/DiskStorageCache:endInsert	(Lcom/facebook/cache/disk/DiskStorage$Inserter;Lcom/facebook/cache/common/CacheKey;Ljava/lang/String;)Lcom/facebook/binaryresource/BinaryResource;
    //   70: astore_1
    //   71: aload_3
    //   72: aload_1
    //   73: invokeinterface 208 1 0
    //   78: invokevirtual 273	com/facebook/cache/disk/SettableCacheEvent:setItemSize	(J)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   81: aload_0
    //   82: getfield 134	com/facebook/cache/disk/DiskStorageCache:mCacheStats	Lcom/facebook/cache/disk/DiskStorageCache$CacheStats;
    //   85: invokevirtual 228	com/facebook/cache/disk/DiskStorageCache$CacheStats:getSize	()J
    //   88: invokevirtual 276	com/facebook/cache/disk/SettableCacheEvent:setCacheSize	(J)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   91: pop
    //   92: aload_0
    //   93: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   96: aload_3
    //   97: invokeinterface 551 2 0
    //   102: aload 4
    //   104: invokeinterface 554 1 0
    //   109: ifne +12 -> 121
    //   112: getstatic 73	com/facebook/cache/disk/DiskStorageCache:TAG	Ljava/lang/Class;
    //   115: ldc_w 556
    //   118: invokestatic 463	com/facebook/common/logging/FLog:e	(Ljava/lang/Class;Ljava/lang/String;)V
    //   121: aload_3
    //   122: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   125: aload_1
    //   126: areturn
    //   127: astore_1
    //   128: aload 4
    //   130: invokeinterface 554 1 0
    //   135: ifne +12 -> 147
    //   138: getstatic 73	com/facebook/cache/disk/DiskStorageCache:TAG	Ljava/lang/Class;
    //   141: ldc_w 556
    //   144: invokestatic 463	com/facebook/common/logging/FLog:e	(Ljava/lang/Class;Ljava/lang/String;)V
    //   147: aload_1
    //   148: athrow
    //   149: astore_1
    //   150: goto +32 -> 182
    //   153: astore_1
    //   154: aload_3
    //   155: aload_1
    //   156: invokevirtual 518	com/facebook/cache/disk/SettableCacheEvent:setException	(Ljava/io/IOException;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   159: pop
    //   160: aload_0
    //   161: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   164: aload_3
    //   165: invokeinterface 559 2 0
    //   170: getstatic 73	com/facebook/cache/disk/DiskStorageCache:TAG	Ljava/lang/Class;
    //   173: ldc_w 561
    //   176: aload_1
    //   177: invokestatic 564	com/facebook/common/logging/FLog:e	(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   180: aload_1
    //   181: athrow
    //   182: aload_3
    //   183: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   186: aload_1
    //   187: athrow
    //   188: astore_1
    //   189: aload 4
    //   191: monitorexit
    //   192: aload_1
    //   193: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   52	102	127	finally
    //   43	52	149	finally
    //   102	121	149	finally
    //   128	147	149	finally
    //   147	149	149	finally
    //   154	182	149	finally
    //   43	52	153	java/io/IOException
    //   102	121	153	java/io/IOException
    //   128	147	153	java/io/IOException
    //   147	149	153	java/io/IOException
    //   27	36	188	finally
    //   189	192	188	finally
  }

  public boolean isEnabled()
  {
    return this.mStorage.isEnabled();
  }

  public boolean isIndexReady()
  {
    return (this.mIndexReady) || (!this.mIndexPopulateAtStartupEnabled);
  }

  // ERROR //
  public boolean probe(CacheKey paramCacheKey)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 102	com/facebook/cache/disk/DiskStorageCache:mLock	Ljava/lang/Object;
    //   6: astore 6
    //   8: aload 6
    //   10: monitorenter
    //   11: aload_1
    //   12: invokestatic 496	com/facebook/cache/common/CacheKeyUtil:getResourceIds	(Lcom/facebook/cache/common/CacheKey;)Ljava/util/List;
    //   15: astore 7
    //   17: aconst_null
    //   18: astore_3
    //   19: iconst_0
    //   20: istore_2
    //   21: iload_2
    //   22: aload 7
    //   24: invokeinterface 499 1 0
    //   29: if_icmpge +70 -> 99
    //   32: aload 7
    //   34: iload_2
    //   35: invokeinterface 502 2 0
    //   40: checkcast 504	java/lang/String
    //   43: astore 4
    //   45: aload 4
    //   47: astore 5
    //   49: aload_0
    //   50: getfield 120	com/facebook/cache/disk/DiskStorageCache:mStorage	Lcom/facebook/cache/disk/DiskStorage;
    //   53: aload 4
    //   55: aload_1
    //   56: invokeinterface 572 3 0
    //   61: ifeq +28 -> 89
    //   64: aload 4
    //   66: astore 5
    //   68: aload_0
    //   69: getfield 155	com/facebook/cache/disk/DiskStorageCache:mResourceIndex	Ljava/util/Set;
    //   72: aload 4
    //   74: invokeinterface 202 2 0
    //   79: pop
    //   80: aload 4
    //   82: astore 5
    //   84: aload 6
    //   86: monitorexit
    //   87: iconst_1
    //   88: ireturn
    //   89: iload_2
    //   90: iconst_1
    //   91: iadd
    //   92: istore_2
    //   93: aload 4
    //   95: astore_3
    //   96: goto -75 -> 21
    //   99: aload 6
    //   101: monitorexit
    //   102: iconst_0
    //   103: ireturn
    //   104: astore 4
    //   106: goto +7 -> 113
    //   109: astore 4
    //   111: aconst_null
    //   112: astore_3
    //   113: aload_3
    //   114: astore 5
    //   116: aload 6
    //   118: monitorexit
    //   119: aload 4
    //   121: athrow
    //   122: astore 4
    //   124: goto +13 -> 137
    //   127: astore 4
    //   129: aload 5
    //   131: astore_3
    //   132: goto -19 -> 113
    //   135: astore 4
    //   137: invokestatic 261	com/facebook/cache/disk/SettableCacheEvent:obtain	()Lcom/facebook/cache/disk/SettableCacheEvent;
    //   140: aload_1
    //   141: invokevirtual 490	com/facebook/cache/disk/SettableCacheEvent:setCacheKey	(Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   144: aload_3
    //   145: invokevirtual 265	com/facebook/cache/disk/SettableCacheEvent:setResourceId	(Ljava/lang/String;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   148: aload 4
    //   150: invokevirtual 518	com/facebook/cache/disk/SettableCacheEvent:setException	(Ljava/io/IOException;)Lcom/facebook/cache/disk/SettableCacheEvent;
    //   153: astore_1
    //   154: aload_0
    //   155: getfield 126	com/facebook/cache/disk/DiskStorageCache:mCacheEventListener	Lcom/facebook/cache/common/CacheEventListener;
    //   158: aload_1
    //   159: invokeinterface 521 2 0
    //   164: aload_1
    //   165: invokevirtual 288	com/facebook/cache/disk/SettableCacheEvent:recycle	()V
    //   168: iconst_0
    //   169: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   21	45	104	finally
    //   99	102	104	finally
    //   11	17	109	finally
    //   119	122	122	java/io/IOException
    //   49	64	127	finally
    //   68	80	127	finally
    //   84	87	127	finally
    //   116	119	127	finally
    //   2	11	135	java/io/IOException
  }

  public void remove(CacheKey paramCacheKey)
  {
    try
    {
      synchronized (this.mLock)
      {
        paramCacheKey = CacheKeyUtil.getResourceIds(paramCacheKey);
        int i = 0;
        if (i < paramCacheKey.size())
        {
          localObject2 = (String)paramCacheKey.get(i);
          this.mStorage.remove((String)localObject2);
          this.mResourceIndex.remove(localObject2);
          i += 1;
        }
      }
    }
    catch (IOException paramCacheKey)
    {
      Object localObject2 = this.mCacheErrorLogger;
      CacheErrorLogger.CacheErrorCategory localCacheErrorCategory = CacheErrorLogger.CacheErrorCategory.DELETE_FILE;
      Class localClass = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("delete: ");
      localStringBuilder.append(paramCacheKey.getMessage());
      ((CacheErrorLogger)localObject2).logError(localCacheErrorCategory, localClass, localStringBuilder.toString(), paramCacheKey);
      return;
    }
  }

  public void trimToMinimum()
  {
    synchronized (this.mLock)
    {
      maybeUpdateFileCacheSize();
      long l = this.mCacheStats.getSize();
      if ((this.mCacheSizeLimitMinimum > 0L) && (l > 0L) && (l >= this.mCacheSizeLimitMinimum))
      {
        double d = 1.0D - this.mCacheSizeLimitMinimum / l;
        if (d > 0.02D)
          trimBy(d);
        return;
      }
      return;
    }
  }

  public void trimToNothing()
  {
    clearAll();
  }

  @VisibleForTesting
  static class CacheStats
  {
    private long mCount = -1L;
    private boolean mInitialized = false;
    private long mSize = -1L;

    public long getCount()
    {
      try
      {
        long l = this.mCount;
        return l;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public long getSize()
    {
      try
      {
        long l = this.mSize;
        return l;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void increment(long paramLong1, long paramLong2)
    {
      try
      {
        if (this.mInitialized)
        {
          this.mSize += paramLong1;
          this.mCount += paramLong2;
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public boolean isInitialized()
    {
      try
      {
        boolean bool = this.mInitialized;
        return bool;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void reset()
    {
      try
      {
        this.mInitialized = false;
        this.mCount = -1L;
        this.mSize = -1L;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void set(long paramLong1, long paramLong2)
    {
      try
      {
        this.mCount = paramLong2;
        this.mSize = paramLong1;
        this.mInitialized = true;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }

  public static class Params
  {
    public final long mCacheSizeLimitMinimum;
    public final long mDefaultCacheSizeLimit;
    public final long mLowDiskSpaceCacheSizeLimit;

    public Params(long paramLong1, long paramLong2, long paramLong3)
    {
      this.mCacheSizeLimitMinimum = paramLong1;
      this.mLowDiskSpaceCacheSizeLimit = paramLong2;
      this.mDefaultCacheSizeLimit = paramLong3;
    }
  }
}