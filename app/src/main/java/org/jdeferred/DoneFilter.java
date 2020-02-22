package org.jdeferred;

public abstract interface DoneFilter<D, D_OUT>
{
  public abstract D_OUT filterDone(D paramD);
}