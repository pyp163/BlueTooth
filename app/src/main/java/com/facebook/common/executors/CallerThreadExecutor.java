package com.facebook.common.executors;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class CallerThreadExecutor extends AbstractExecutorService
{
  private static final CallerThreadExecutor sInstance = new CallerThreadExecutor();

  public static CallerThreadExecutor getInstance()
  {
    return sInstance;
  }

  public boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    return true;
  }

  public void execute(Runnable paramRunnable)
  {
    paramRunnable.run();
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
  }

  public List<Runnable> shutdownNow()
  {
    shutdown();
    return Collections.emptyList();
  }
}