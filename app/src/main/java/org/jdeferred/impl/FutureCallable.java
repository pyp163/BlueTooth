package org.jdeferred.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureCallable<V>
  implements Callable<V>
{
  private final Future<V> future;

  public FutureCallable(Future<V> paramFuture)
  {
    this.future = paramFuture;
  }

  public V call()
    throws Exception
  {
    try
    {
      Object localObject = this.future.get();
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
}