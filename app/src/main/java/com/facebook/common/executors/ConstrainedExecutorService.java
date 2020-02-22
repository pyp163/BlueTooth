package com.facebook.common.executors;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstrainedExecutorService extends AbstractExecutorService
{
  private static final Class<?> TAG = ConstrainedExecutorService.class;
  private final Executor mExecutor;
  private volatile int mMaxConcurrency;
  private final AtomicInteger mMaxQueueSize;
  private final String mName;
  private final AtomicInteger mPendingWorkers;
  private final Worker mTaskRunner;
  private final BlockingQueue<Runnable> mWorkQueue;

  public ConstrainedExecutorService(String paramString, int paramInt, Executor paramExecutor, BlockingQueue<Runnable> paramBlockingQueue)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("max concurrency must be > 0");
    this.mName = paramString;
    this.mExecutor = paramExecutor;
    this.mMaxConcurrency = paramInt;
    this.mWorkQueue = paramBlockingQueue;
    this.mTaskRunner = new Worker(null);
    this.mPendingWorkers = new AtomicInteger(0);
    this.mMaxQueueSize = new AtomicInteger(0);
  }

  public static ConstrainedExecutorService newConstrainedExecutor(String paramString, int paramInt1, int paramInt2, Executor paramExecutor)
  {
    return new ConstrainedExecutorService(paramString, paramInt1, paramExecutor, new LinkedBlockingQueue(paramInt2));
  }

  private void startWorkerIfNeeded()
  {
    for (int i = this.mPendingWorkers.get(); i < this.mMaxConcurrency; i = this.mPendingWorkers.get())
    {
      int j = i + 1;
      if (this.mPendingWorkers.compareAndSet(i, j))
      {
        FLog.v(TAG, "%s: starting worker %d of %d", this.mName, Integer.valueOf(j), Integer.valueOf(this.mMaxConcurrency));
        this.mExecutor.execute(this.mTaskRunner);
        return;
      }
      FLog.v(TAG, "%s: race in startWorkerIfNeeded; retrying", this.mName);
    }
  }

  public boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    throw new UnsupportedOperationException();
  }

  public void execute(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      throw new NullPointerException("runnable parameter is null");
    if (!this.mWorkQueue.offer(paramRunnable))
    {
      paramRunnable = new StringBuilder();
      paramRunnable.append(this.mName);
      paramRunnable.append(" queue is full, size=");
      paramRunnable.append(this.mWorkQueue.size());
      throw new RejectedExecutionException(paramRunnable.toString());
    }
    int i = this.mWorkQueue.size();
    int j = this.mMaxQueueSize.get();
    if ((i > j) && (this.mMaxQueueSize.compareAndSet(j, i)))
      FLog.v(TAG, "%s: max pending work in queue = %d", this.mName, Integer.valueOf(i));
    startWorkerIfNeeded();
  }

  public boolean isIdle()
  {
    return (this.mWorkQueue.isEmpty()) && (this.mPendingWorkers.get() == 0);
  }

  public boolean isShutdown()
  {
    return false;
  }

  public boolean isTerminated()
  {
    return false;
  }

  public void shutdown()
  {
    throw new UnsupportedOperationException();
  }

  public List<Runnable> shutdownNow()
  {
    throw new UnsupportedOperationException();
  }

  private class Worker
    implements Runnable
  {
    private Worker()
    {
    }

    public void run()
    {
      try
      {
        Runnable localRunnable = (Runnable)ConstrainedExecutorService.this.mWorkQueue.poll();
        if (localRunnable != null)
          localRunnable.run();
        else
          FLog.v(ConstrainedExecutorService.TAG, "%s: Worker has nothing to run", ConstrainedExecutorService.this.mName);
        i = ConstrainedExecutorService.this.mPendingWorkers.decrementAndGet();
        if (!ConstrainedExecutorService.this.mWorkQueue.isEmpty())
        {
          ConstrainedExecutorService.this.startWorkerIfNeeded();
          return;
        }
        FLog.v(ConstrainedExecutorService.TAG, "%s: worker finished; %d workers left", ConstrainedExecutorService.this.mName, Integer.valueOf(i));
        return;
      }
      finally
      {
        int i = ConstrainedExecutorService.this.mPendingWorkers.decrementAndGet();
        if (!ConstrainedExecutorService.this.mWorkQueue.isEmpty())
          ConstrainedExecutorService.this.startWorkerIfNeeded();
        else
          FLog.v(ConstrainedExecutorService.TAG, "%s: worker finished; %d workers left", ConstrainedExecutorService.this.mName, Integer.valueOf(i));
      }
    }
  }
}