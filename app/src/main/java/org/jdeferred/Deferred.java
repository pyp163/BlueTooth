package org.jdeferred;

public abstract interface Deferred<D, F, P> extends Promise<D, F, P>
{
  public abstract Deferred<D, F, P> notify(P paramP);

  public abstract Promise<D, F, P> promise();

  public abstract Deferred<D, F, P> reject(F paramF);

  public abstract Deferred<D, F, P> resolve(D paramD);
}