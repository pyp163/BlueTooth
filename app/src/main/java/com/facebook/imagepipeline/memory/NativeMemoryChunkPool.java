package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativeMemoryChunkPool extends BasePool<NativeMemoryChunk>
{
  private final int[] mBucketSizes;

  public NativeMemoryChunkPool(MemoryTrimmableRegistry paramMemoryTrimmableRegistry, PoolParams paramPoolParams, PoolStatsTracker paramPoolStatsTracker)
  {
    super(paramMemoryTrimmableRegistry, paramPoolParams, paramPoolStatsTracker);
    paramMemoryTrimmableRegistry = paramPoolParams.bucketSizes;
    this.mBucketSizes = new int[paramMemoryTrimmableRegistry.size()];
    int i = 0;
    while (i < this.mBucketSizes.length)
    {
      this.mBucketSizes[i] = paramMemoryTrimmableRegistry.keyAt(i);
      i += 1;
    }
    initialize();
  }

  protected NativeMemoryChunk alloc(int paramInt)
  {
    return new NativeMemoryChunk(paramInt);
  }

  protected void free(NativeMemoryChunk paramNativeMemoryChunk)
  {
    Preconditions.checkNotNull(paramNativeMemoryChunk);
    paramNativeMemoryChunk.close();
  }

  protected int getBucketedSize(int paramInt)
  {
    if (paramInt <= 0)
      throw new BasePool.InvalidSizeException(Integer.valueOf(paramInt));
    int[] arrayOfInt = this.mBucketSizes;
    int j = arrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      int k = arrayOfInt[i];
      if (k >= paramInt)
        return k;
      i += 1;
    }
    return paramInt;
  }

  protected int getBucketedSizeForValue(NativeMemoryChunk paramNativeMemoryChunk)
  {
    Preconditions.checkNotNull(paramNativeMemoryChunk);
    return paramNativeMemoryChunk.getSize();
  }

  public int getMinBufferSize()
  {
    return this.mBucketSizes[0];
  }

  protected int getSizeInBytes(int paramInt)
  {
    return paramInt;
  }

  protected boolean isReusable(NativeMemoryChunk paramNativeMemoryChunk)
  {
    Preconditions.checkNotNull(paramNativeMemoryChunk);
    return paramNativeMemoryChunk.isClosed() ^ true;
  }
}