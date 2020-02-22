package org.jdeferred.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.jdeferred.DeferredCallable;
import org.jdeferred.DeferredFutureTask;
import org.jdeferred.DeferredManager;
import org.jdeferred.DeferredManager.StartPolicy;
import org.jdeferred.DeferredRunnable;
import org.jdeferred.Promise;
import org.jdeferred.multiple.MasterDeferredObject;
import org.jdeferred.multiple.MasterProgress;
import org.jdeferred.multiple.MultipleResults;
import org.jdeferred.multiple.OneReject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDeferredManager
  implements DeferredManager
{
  protected final Logger log = LoggerFactory.getLogger(AbstractDeferredManager.class);

  protected void assertNotEmpty(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
      return;
    throw new IllegalArgumentException("Arguments is null or its length is empty");
  }

  public abstract boolean isAutoSubmit();

  protected abstract void submit(Runnable paramRunnable);

  protected abstract void submit(Callable paramCallable);

  public Promise<Void, Throwable, Void> when(Runnable paramRunnable)
  {
    return when(new DeferredFutureTask(paramRunnable));
  }

  public <D> Promise<D, Throwable, Void> when(Callable<D> paramCallable)
  {
    return when(new DeferredFutureTask(paramCallable));
  }

  public <D> Promise<D, Throwable, Void> when(final Future<D> paramFuture)
  {
    return when(new DeferredCallable(DeferredManager.StartPolicy.AUTO)
    {
      public D call()
        throws Exception
      {
        try
        {
          Object localObject = paramFuture.get();
          return localObject;
        }
        catch (ExecutionException localExecutionException)
        {
          if ((localExecutionException.getCause() instanceof Exception))
            throw ((Exception)localExecutionException.getCause());
          throw localExecutionException;
        }
        catch (InterruptedException localInterruptedException)
        {
          throw localInterruptedException;
        }
      }
    });
  }

  public <D, P> Promise<D, Throwable, P> when(DeferredCallable<D, P> paramDeferredCallable)
  {
    return when(new DeferredFutureTask(paramDeferredCallable));
  }

  public <D, P> Promise<D, Throwable, P> when(DeferredFutureTask<D, P> paramDeferredFutureTask)
  {
    if ((paramDeferredFutureTask.getStartPolicy() == DeferredManager.StartPolicy.AUTO) || ((paramDeferredFutureTask.getStartPolicy() == DeferredManager.StartPolicy.DEFAULT) && (isAutoSubmit())))
      submit(paramDeferredFutureTask);
    return paramDeferredFutureTask.promise();
  }

  public <P> Promise<Void, Throwable, P> when(DeferredRunnable<P> paramDeferredRunnable)
  {
    return when(new DeferredFutureTask(paramDeferredRunnable));
  }

  public <D, F, P> Promise<D, F, P> when(Promise<D, F, P> paramPromise)
  {
    return paramPromise;
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(Runnable[] paramArrayOfRunnable)
  {
    assertNotEmpty(paramArrayOfRunnable);
    Promise[] arrayOfPromise = new Promise[paramArrayOfRunnable.length];
    int i = 0;
    while (i < paramArrayOfRunnable.length)
    {
      if ((paramArrayOfRunnable[i] instanceof DeferredRunnable))
        arrayOfPromise[i] = when((DeferredRunnable)paramArrayOfRunnable[i]);
      else
        arrayOfPromise[i] = when(paramArrayOfRunnable[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(Callable<?>[] paramArrayOfCallable)
  {
    assertNotEmpty(paramArrayOfCallable);
    Promise[] arrayOfPromise = new Promise[paramArrayOfCallable.length];
    int i = 0;
    while (i < paramArrayOfCallable.length)
    {
      if ((paramArrayOfCallable[i] instanceof DeferredCallable))
        arrayOfPromise[i] = when((DeferredCallable)paramArrayOfCallable[i]);
      else
        arrayOfPromise[i] = when(paramArrayOfCallable[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(Future<?>[] paramArrayOfFuture)
  {
    assertNotEmpty(paramArrayOfFuture);
    Promise[] arrayOfPromise = new Promise[paramArrayOfFuture.length];
    int i = 0;
    while (i < paramArrayOfFuture.length)
    {
      arrayOfPromise[i] = when(paramArrayOfFuture[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(DeferredCallable<?, ?>[] paramArrayOfDeferredCallable)
  {
    assertNotEmpty(paramArrayOfDeferredCallable);
    Promise[] arrayOfPromise = new Promise[paramArrayOfDeferredCallable.length];
    int i = 0;
    while (i < paramArrayOfDeferredCallable.length)
    {
      arrayOfPromise[i] = when(paramArrayOfDeferredCallable[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(DeferredFutureTask<?, ?>[] paramArrayOfDeferredFutureTask)
  {
    assertNotEmpty(paramArrayOfDeferredFutureTask);
    Promise[] arrayOfPromise = new Promise[paramArrayOfDeferredFutureTask.length];
    int i = 0;
    while (i < paramArrayOfDeferredFutureTask.length)
    {
      arrayOfPromise[i] = when(paramArrayOfDeferredFutureTask[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(DeferredRunnable<?>[] paramArrayOfDeferredRunnable)
  {
    assertNotEmpty(paramArrayOfDeferredRunnable);
    Promise[] arrayOfPromise = new Promise[paramArrayOfDeferredRunnable.length];
    int i = 0;
    while (i < paramArrayOfDeferredRunnable.length)
    {
      arrayOfPromise[i] = when(paramArrayOfDeferredRunnable[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(Promise[] paramArrayOfPromise)
  {
    assertNotEmpty(paramArrayOfPromise);
    return new MasterDeferredObject(paramArrayOfPromise).promise();
  }
}