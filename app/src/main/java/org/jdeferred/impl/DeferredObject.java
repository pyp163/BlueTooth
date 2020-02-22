package org.jdeferred.impl;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.Promise.State;

public class DeferredObject<D, F, P> extends AbstractPromise<D, F, P>
  implements Deferred<D, F, P>
{
  public Deferred<D, F, P> notify(P paramP)
  {
    try
    {
      if (!isPending())
        throw new IllegalStateException("Deferred object already finished, cannot notify progress");
      triggerProgress(paramP);
      return this;
    }
    finally
    {
    }
    throw paramP;
  }

  public Promise<D, F, P> promise()
  {
    return this;
  }

  public Deferred<D, F, P> reject(F paramF)
  {
    try
    {
      if (!isPending())
        throw new IllegalStateException("Deferred object already finished, cannot reject again");
      this.state = Promise.State.REJECTED;
      this.rejectResult = paramF;
      try
      {
        triggerFail(paramF);
        triggerAlways(this.state, null, paramF);
        return this;
      }
      finally
      {
        localObject = finally;
        triggerAlways(this.state, null, paramF);
        throw localObject;
      }
    }
    finally
    {
    }
    throw paramF;
  }

  public Deferred<D, F, P> resolve(D paramD)
  {
    try
    {
      if (!isPending())
        throw new IllegalStateException("Deferred object already finished, cannot resolve again");
      this.state = Promise.State.RESOLVED;
      this.resolveResult = paramD;
      try
      {
        triggerDone(paramD);
        triggerAlways(this.state, paramD, null);
        return this;
      }
      finally
      {
        localObject = finally;
        triggerAlways(this.state, paramD, null);
        throw localObject;
      }
    }
    finally
    {
    }
    throw paramD;
  }
}