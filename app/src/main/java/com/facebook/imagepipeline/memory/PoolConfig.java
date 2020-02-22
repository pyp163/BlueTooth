package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import javax.annotation.concurrent.Immutable;

@Immutable
public class PoolConfig
{
  private final PoolParams mBitmapPoolParams;
  private final PoolStatsTracker mBitmapPoolStatsTracker;
  private final PoolParams mFlexByteArrayPoolParams;
  private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
  private final PoolParams mNativeMemoryChunkPoolParams;
  private final PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
  private final PoolParams mSmallByteArrayPoolParams;
  private final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

  private PoolConfig(Builder paramBuilder)
  {
    Object localObject;
    if (paramBuilder.mBitmapPoolParams == null)
      localObject = DefaultBitmapPoolParams.get();
    else
      localObject = paramBuilder.mBitmapPoolParams;
    this.mBitmapPoolParams = ((PoolParams)localObject);
    if (paramBuilder.mBitmapPoolStatsTracker == null)
      localObject = NoOpPoolStatsTracker.getInstance();
    else
      localObject = paramBuilder.mBitmapPoolStatsTracker;
    this.mBitmapPoolStatsTracker = ((PoolStatsTracker)localObject);
    if (paramBuilder.mFlexByteArrayPoolParams == null)
      localObject = DefaultFlexByteArrayPoolParams.get();
    else
      localObject = paramBuilder.mFlexByteArrayPoolParams;
    this.mFlexByteArrayPoolParams = ((PoolParams)localObject);
    if (paramBuilder.mMemoryTrimmableRegistry == null)
      localObject = NoOpMemoryTrimmableRegistry.getInstance();
    else
      localObject = paramBuilder.mMemoryTrimmableRegistry;
    this.mMemoryTrimmableRegistry = ((MemoryTrimmableRegistry)localObject);
    if (paramBuilder.mNativeMemoryChunkPoolParams == null)
      localObject = DefaultNativeMemoryChunkPoolParams.get();
    else
      localObject = paramBuilder.mNativeMemoryChunkPoolParams;
    this.mNativeMemoryChunkPoolParams = ((PoolParams)localObject);
    if (paramBuilder.mNativeMemoryChunkPoolStatsTracker == null)
      localObject = NoOpPoolStatsTracker.getInstance();
    else
      localObject = paramBuilder.mNativeMemoryChunkPoolStatsTracker;
    this.mNativeMemoryChunkPoolStatsTracker = ((PoolStatsTracker)localObject);
    if (paramBuilder.mSmallByteArrayPoolParams == null)
      localObject = DefaultByteArrayPoolParams.get();
    else
      localObject = paramBuilder.mSmallByteArrayPoolParams;
    this.mSmallByteArrayPoolParams = ((PoolParams)localObject);
    if (paramBuilder.mSmallByteArrayPoolStatsTracker == null)
      paramBuilder = NoOpPoolStatsTracker.getInstance();
    else
      paramBuilder = paramBuilder.mSmallByteArrayPoolStatsTracker;
    this.mSmallByteArrayPoolStatsTracker = paramBuilder;
  }

  public static Builder newBuilder()
  {
    return new Builder(null);
  }

  public PoolParams getBitmapPoolParams()
  {
    return this.mBitmapPoolParams;
  }

  public PoolStatsTracker getBitmapPoolStatsTracker()
  {
    return this.mBitmapPoolStatsTracker;
  }

  public PoolParams getFlexByteArrayPoolParams()
  {
    return this.mFlexByteArrayPoolParams;
  }

  public MemoryTrimmableRegistry getMemoryTrimmableRegistry()
  {
    return this.mMemoryTrimmableRegistry;
  }

  public PoolParams getNativeMemoryChunkPoolParams()
  {
    return this.mNativeMemoryChunkPoolParams;
  }

  public PoolStatsTracker getNativeMemoryChunkPoolStatsTracker()
  {
    return this.mNativeMemoryChunkPoolStatsTracker;
  }

  public PoolParams getSmallByteArrayPoolParams()
  {
    return this.mSmallByteArrayPoolParams;
  }

  public PoolStatsTracker getSmallByteArrayPoolStatsTracker()
  {
    return this.mSmallByteArrayPoolStatsTracker;
  }

  public static class Builder
  {
    private PoolParams mBitmapPoolParams;
    private PoolStatsTracker mBitmapPoolStatsTracker;
    private PoolParams mFlexByteArrayPoolParams;
    private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private PoolParams mNativeMemoryChunkPoolParams;
    private PoolStatsTracker mNativeMemoryChunkPoolStatsTracker;
    private PoolParams mSmallByteArrayPoolParams;
    private PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    public PoolConfig build()
    {
      return new PoolConfig(this, null);
    }

    public Builder setBitmapPoolParams(PoolParams paramPoolParams)
    {
      this.mBitmapPoolParams = ((PoolParams)Preconditions.checkNotNull(paramPoolParams));
      return this;
    }

    public Builder setBitmapPoolStatsTracker(PoolStatsTracker paramPoolStatsTracker)
    {
      this.mBitmapPoolStatsTracker = ((PoolStatsTracker)Preconditions.checkNotNull(paramPoolStatsTracker));
      return this;
    }

    public Builder setFlexByteArrayPoolParams(PoolParams paramPoolParams)
    {
      this.mFlexByteArrayPoolParams = paramPoolParams;
      return this;
    }

    public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry paramMemoryTrimmableRegistry)
    {
      this.mMemoryTrimmableRegistry = paramMemoryTrimmableRegistry;
      return this;
    }

    public Builder setNativeMemoryChunkPoolParams(PoolParams paramPoolParams)
    {
      this.mNativeMemoryChunkPoolParams = ((PoolParams)Preconditions.checkNotNull(paramPoolParams));
      return this;
    }

    public Builder setNativeMemoryChunkPoolStatsTracker(PoolStatsTracker paramPoolStatsTracker)
    {
      this.mNativeMemoryChunkPoolStatsTracker = ((PoolStatsTracker)Preconditions.checkNotNull(paramPoolStatsTracker));
      return this;
    }

    public Builder setSmallByteArrayPoolParams(PoolParams paramPoolParams)
    {
      this.mSmallByteArrayPoolParams = ((PoolParams)Preconditions.checkNotNull(paramPoolParams));
      return this;
    }

    public Builder setSmallByteArrayPoolStatsTracker(PoolStatsTracker paramPoolStatsTracker)
    {
      this.mSmallByteArrayPoolStatsTracker = ((PoolStatsTracker)Preconditions.checkNotNull(paramPoolStatsTracker));
      return this;
    }
  }
}