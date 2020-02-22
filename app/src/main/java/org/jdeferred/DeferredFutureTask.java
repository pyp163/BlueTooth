package org.jdeferred;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.jdeferred.impl.DeferredObject;

public class DeferredFutureTask<D, P> extends FutureTask<D>
{
  protected final Deferred<D, Throwable, P> deferred;
  protected final DeferredManager.StartPolicy startPolicy;

  public DeferredFutureTask(Runnable paramRunnable)
  {
    super(paramRunnable, null);
    this.deferred = new DeferredObject();
    this.startPolicy = DeferredManager.StartPolicy.DEFAULT;
  }

  public DeferredFutureTask(Callable<D> paramCallable)
  {
    super(paramCallable);
    this.deferred = new DeferredObject();
    this.startPolicy = DeferredManager.StartPolicy.DEFAULT;
  }

  public DeferredFutureTask(DeferredCallable<D, P> paramDeferredCallable)
  {
    super(paramDeferredCallable);
    this.deferred = paramDeferredCallable.getDeferred();
    this.startPolicy = paramDeferredCallable.getStartPolicy();
  }

  public DeferredFutureTask(DeferredRunnable<P> paramDeferredRunnable)
  {
    super(paramDeferredRunnable, null);
    this.deferred = paramDeferredRunnable.getDeferred();
    this.startPolicy = paramDeferredRunnable.getStartPolicy();
  }

  protected void done()
  {
    try
    {
      if (isCancelled())
        this.deferred.reject(new CancellationException());
      Object localObject = get();
      this.deferred.resolve(localObject);
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      this.deferred.reject(localExecutionException.getCause());
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  public DeferredManager.StartPolicy getStartPolicy()
  {
    return this.startPolicy;
  }

  public Promise<D, Throwable, P> promise()
  {
    return this.deferred.promise();
  }
}