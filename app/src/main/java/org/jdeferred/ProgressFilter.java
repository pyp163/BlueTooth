package org.jdeferred;

public abstract interface ProgressFilter<P, P_OUT>
{
  public abstract P_OUT filterProgress(P paramP);
}