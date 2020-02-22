package org.jdeferred;

import org.jdeferred.impl.DeferredObject;

public abstract class DeferredRunnable<P>
  implements Runnable
{
  private final Deferred<Void, Throwable, P> deferred = new DeferredObject();
  private final DeferredManager.StartPolicy startPolicy;

  public DeferredRunnable()
  {
    this.startPolicy = DeferredManager.StartPolicy.DEFAULT;
  }

  public DeferredRunnable(DeferredManager.StartPolicy paramStartPolicy)
  {
    this.startPolicy = paramStartPolicy;
  }

  protected Deferred<Void, Throwable, P> getDeferred()
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