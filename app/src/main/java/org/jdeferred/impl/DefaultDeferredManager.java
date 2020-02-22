package org.jdeferred.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DefaultDeferredManager extends AbstractDeferredManager
{
  public static final boolean DEFAULT_AUTO_SUBMIT = true;
  private boolean autoSubmit = true;
  private final ExecutorService executorService;

  public DefaultDeferredManager()
  {
    this.executorService = Executors.newCachedThreadPool();
  }

  public DefaultDeferredManager(ExecutorService paramExecutorService)
  {
    this.executorService = paramExecutorService;
  }

  public boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException
  {
    return this.executorService.awaitTermination(paramLong, paramTimeUnit);
  }

  public ExecutorService getExecutorService()
  {
    return this.executorService;
  }

  public boolean isAutoSubmit()
  {
    return this.autoSubmit;
  }

  public boolean isShutdown()
  {
    return this.executorService.isShutdown();
  }

  public boolean isTerminated()
  {
    return this.executorService.isTerminated();
  }

  public void setAutoSubmit(boolean paramBoolean)
  {
    this.autoSubmit = paramBoolean;
  }

  public void shutdown()
  {
    this.executorService.shutdown();
  }

  public List<Runnable> shutdownNow()
  {
    return this.executorService.shutdownNow();
  }

  protected void submit(Runnable paramRunnable)
  {
    this.executorService.submit(paramRunnable);
  }

  protected void submit(Callable paramCallable)
  {
    this.executorService.submit(paramCallable);
  }
}