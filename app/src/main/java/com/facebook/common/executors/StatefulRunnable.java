package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class StatefulRunnable<T>
  implements Runnable
{
  protected static final int STATE_CANCELLED = 2;
  protected static final int STATE_CREATED = 0;
  protected static final int STATE_FAILED = 4;
  protected static final int STATE_FINISHED = 3;
  protected static final int STATE_STARTED = 1;
  protected final AtomicInteger mState = new AtomicInteger(0);

  public void cancel()
  {
    if (this.mState.compareAndSet(0, 2))
      onCancellation();
  }

  protected void disposeResult(T paramT)
  {
  }

  protected abstract T getResult()
    throws Exception;

  protected void onCancellation()
  {
  }

  protected void onFailure(Exception paramException)
  {
  }

  protected void onSuccess(T paramT)
  {
  }

  public final void run()
  {
    if (!this.mState.compareAndSet(0, 1))
      return;
    try
    {
      Object localObject1 = getResult();
      this.mState.set(3);
      try
      {
        onSuccess(localObject1);
        return;
      }
      finally
      {
        disposeResult(localObject1);
      }
    }
    catch (Exception localException)
    {
      this.mState.set(4);
      onFailure(localException);
    }
  }
}