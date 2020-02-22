package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class JobScheduler
{
  static final String QUEUE_TIME_KEY = "queueTime";
  private final Runnable mDoJobRunnable;

  @VisibleForTesting
  @GuardedBy("this")
  EncodedImage mEncodedImage;
  private final Executor mExecutor;
  private final JobRunnable mJobRunnable;

  @VisibleForTesting
  @GuardedBy("this")
  long mJobStartTime;

  @VisibleForTesting
  @GuardedBy("this")
  JobState mJobState;

  @VisibleForTesting
  @GuardedBy("this")
  long mJobSubmitTime;
  private final int mMinimumJobIntervalMs;

  @VisibleForTesting
  @GuardedBy("this")
  int mStatus;
  private final Runnable mSubmitJobRunnable;

  public JobScheduler(Executor paramExecutor, JobRunnable paramJobRunnable, int paramInt)
  {
    this.mExecutor = paramExecutor;
    this.mJobRunnable = paramJobRunnable;
    this.mMinimumJobIntervalMs = paramInt;
    this.mDoJobRunnable = new Runnable()
    {
      public void run()
      {
        JobScheduler.this.doJob();
      }
    };
    this.mSubmitJobRunnable = new Runnable()
    {
      public void run()
      {
        JobScheduler.this.submitJob();
      }
    };
    this.mEncodedImage = null;
    this.mStatus = 0;
    this.mJobState = JobState.IDLE;
    this.mJobSubmitTime = 0L;
    this.mJobStartTime = 0L;
  }

  private void doJob()
  {
    long l = SystemClock.uptimeMillis();
    try
    {
      EncodedImage localEncodedImage = this.mEncodedImage;
      int i = this.mStatus;
      this.mEncodedImage = null;
      this.mStatus = 0;
      this.mJobState = JobState.RUNNING;
      this.mJobStartTime = l;
      try
      {
        if (shouldProcess(localEncodedImage, i))
          this.mJobRunnable.run(localEncodedImage, i);
        return;
      }
      finally
      {
        EncodedImage.closeSafely(localEncodedImage);
        onJobFinished();
      }
    }
    finally
    {
    }
  }

  private void enqueueJob(long paramLong)
  {
    if (paramLong > 0L)
    {
      JobStartExecutorSupplier.get().schedule(this.mSubmitJobRunnable, paramLong, TimeUnit.MILLISECONDS);
      return;
    }
    this.mSubmitJobRunnable.run();
  }

  private void onJobFinished()
  {
    long l2 = SystemClock.uptimeMillis();
    try
    {
      long l1;
      int i;
      if (this.mJobState == JobState.RUNNING_AND_PENDING)
      {
        l1 = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, l2);
        i = 1;
        this.mJobSubmitTime = l2;
        this.mJobState = JobState.QUEUED;
      }
      else
      {
        this.mJobState = JobState.IDLE;
        l1 = 0L;
        i = 0;
      }
      if (i != 0)
        enqueueJob(l1 - l2);
      return;
    }
    finally
    {
    }
  }

  private static boolean shouldProcess(EncodedImage paramEncodedImage, int paramInt)
  {
    return (BaseConsumer.isLast(paramInt)) || (BaseConsumer.statusHasFlag(paramInt, 4)) || (EncodedImage.isValid(paramEncodedImage));
  }

  private void submitJob()
  {
    this.mExecutor.execute(this.mDoJobRunnable);
  }

  public void clearJob()
  {
    try
    {
      EncodedImage localEncodedImage = this.mEncodedImage;
      this.mEncodedImage = null;
      this.mStatus = 0;
      EncodedImage.closeSafely(localEncodedImage);
      return;
    }
    finally
    {
    }
  }

  public long getQueuedTime()
  {
    try
    {
      long l1 = this.mJobStartTime;
      long l2 = this.mJobSubmitTime;
      return l1 - l2;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean scheduleJob()
  {
    long l2 = SystemClock.uptimeMillis();
    while (true)
    {
      try
      {
        boolean bool = shouldProcess(this.mEncodedImage, this.mStatus);
        int i = 0;
        if (!bool)
          return false;
        switch (3.$SwitchMap$com$facebook$imagepipeline$producers$JobScheduler$JobState[this.mJobState.ordinal()])
        {
        case 3:
          this.mJobState = JobState.RUNNING_AND_PENDING;
          break;
        case 1:
          l1 = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, l2);
          this.mJobSubmitTime = l2;
          this.mJobState = JobState.QUEUED;
          i = 1;
          if (i != 0)
            enqueueJob(l1 - l2);
          return true;
        case 2:
        }
      }
      finally
      {
      }
      long l1 = 0L;
    }
  }

  public boolean updateJob(EncodedImage paramEncodedImage, int paramInt)
  {
    if (!shouldProcess(paramEncodedImage, paramInt))
      return false;
    try
    {
      EncodedImage localEncodedImage = this.mEncodedImage;
      this.mEncodedImage = EncodedImage.cloneOrNull(paramEncodedImage);
      this.mStatus = paramInt;
      EncodedImage.closeSafely(localEncodedImage);
      return true;
    }
    finally
    {
    }
    throw paramEncodedImage;
  }

  public static abstract interface JobRunnable
  {
    public abstract void run(EncodedImage paramEncodedImage, int paramInt);
  }

  @VisibleForTesting
  static class JobStartExecutorSupplier
  {
    private static ScheduledExecutorService sJobStarterExecutor;

    static ScheduledExecutorService get()
    {
      if (sJobStarterExecutor == null)
        sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
      return sJobStarterExecutor;
    }
  }

  @VisibleForTesting
  static enum JobState
  {
    IDLE, QUEUED, RUNNING, RUNNING_AND_PENDING;
  }
}