package org.jdeferred;

import java.util.concurrent.Callable;
import org.jdeferred.impl.DeferredObject;

public abstract class DeferredCallable<D, P>
  implements Callable<D>
{
  private final Deferred<D, Throwable, P> deferred = new DeferredObject();
  private final DeferredManager.StartPolicy startPolicy;

  public DeferredCallable()
  {
    this.startPolicy = DeferredManager.StartPolicy.DEFAULT;
  }

  public DeferredCallable(DeferredManager.StartPolicy paramStartPolicy)
  {
    this.startPolicy = paramStartPolicy;
  }

  protected Deferred<D, Throwable, P> getDeferred()
  {
    return this.deferred;
  }

  public DeferredManager.StartPolicy getStartPolicy()
  {
    return this.startPolicy;
  }

  protected void notify(P paramP)
  {
    this.deferred.notify(paramP);
  }
}