package org.jdeferred;

public abstract interface AlwaysCallback<D, R>
{
  public abstract void onAlways(Promise.State paramState, D paramD, R paramR);
}