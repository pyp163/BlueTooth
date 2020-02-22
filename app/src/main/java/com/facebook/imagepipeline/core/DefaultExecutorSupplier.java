package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DefaultExecutorSupplier
  implements ExecutorSupplier
{
  private static final int NUM_IO_BOUND_THREADS = 2;
  private static final int NUM_LIGHTWEIGHT_BACKGROUND_THREADS = 1;
  private final Executor mBackgroundExecutor;
  private final Executor mDecodeExecutor;
  private final Executor mIoBoundExecutor = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
  private final Executor mLightWeightBackgroundExecutor;

  public DefaultExecutorSupplier(int paramInt)
  {
    this.mDecodeExecutor = Executors.newFixedThreadPool(paramInt, new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
    this.mBackgroundExecutor = Executors.newFixedThreadPool(paramInt, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
    this.mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));
  }

  public Executor forBackgroundTasks()
  {
    return this.mBackgroundExecutor;
  }

  public Executor forDecode()
  {
    return this.mDecodeExecutor;
  }

  public Executor forLightweightBackgroundTasks()
  {
    return this.mLightWeightBackgroundExecutor;
  }

  public Executor forLocalStorageRead()
  {
    return this.mIoBoundExecutor;
  }

  public Executor forLocalStorageWrite()
  {
    return this.mIoBoundExecutor;
  }
}