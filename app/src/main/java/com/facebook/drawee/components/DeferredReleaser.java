package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DeferredReleaser
{
  private static DeferredReleaser sInstance;
  private final Set<Releasable> mPendingReleasables = new HashSet();
  private final Handler mUiHandler = new Handler(Looper.getMainLooper());
  private final Runnable releaseRunnable = new Runnable()
  {
    public void run()
    {
      DeferredReleaser.access$000();
      Iterator localIterator = DeferredReleaser.this.mPendingReleasables.iterator();
      while (localIterator.hasNext())
        ((DeferredReleaser.Releasable)localIterator.next()).release();
      DeferredReleaser.this.mPendingReleasables.clear();
    }
  };

  private static void ensureOnUiThread()
  {
    boolean bool;
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
      bool = true;
    else
      bool = false;
    Preconditions.checkState(bool);
  }

  public static DeferredReleaser getInstance()
  {
    try
    {
      if (sInstance == null)
        sInstance = new DeferredReleaser();
      DeferredReleaser localDeferredReleaser = sInstance;
      return localDeferredReleaser;
    }
    finally
    {
    }
  }

  public void cancelDeferredRelease(Releasable paramReleasable)
  {
    ensureOnUiThread();
    this.mPendingReleasables.remove(paramReleasable);
  }

  public void scheduleDeferredRelease(Releasable paramReleasable)
  {
    ensureOnUiThread();
    if (!this.mPendingReleasables.add(paramReleasable))
      return;
    if (this.mPendingReleasables.size() == 1)
      this.mUiHandler.post(this.releaseRunnable);
  }

  public static abstract interface Releasable
  {
    public abstract void release();
  }
}