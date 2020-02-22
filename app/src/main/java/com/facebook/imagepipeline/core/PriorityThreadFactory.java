package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityThreadFactory
  implements ThreadFactory
{
  private final boolean mAddThreadNumber;
  private final String mPrefix;
  private final AtomicInteger mThreadNumber = new AtomicInteger(1);
  private final int mThreadPriority;

  public PriorityThreadFactory(int paramInt)
  {
    this(paramInt, "PriorityThreadFactory", true);
  }

  public PriorityThreadFactory(int paramInt, String paramString, boolean paramBoolean)
  {
    this.mThreadPriority = paramInt;
    this.mPrefix = paramString;
    this.mAddThreadNumber = paramBoolean;
  }

  public Thread newThread(final Runnable paramRunnable)
  {
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        try
        {
          Process.setThreadPriority(PriorityThreadFactory.this.mThreadPriority);
          label10: paramRunnable.run();
          return;
        }
        catch (Throwable localThrowable)
        {
          break label10;
        }
      }
    };
    if (this.mAddThreadNumber)
    {
      paramRunnable = new StringBuilder();
      paramRunnable.append(this.mPrefix);
      paramRunnable.append("-");
      paramRunnable.append(this.mThreadNumber.getAndIncrement());
      paramRunnable = paramRunnable.toString();
    }
    else
    {
      paramRunnable = this.mPrefix;
    }
    return new Thread(local1, paramRunnable);
  }
}