package org.jdeferred;

public abstract interface DoneCallback<D>
{
  public abstract void onDone(D paramD);
}