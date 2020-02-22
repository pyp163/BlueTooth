package org.jdeferred;

public abstract interface DonePipe<D, D_OUT, F_OUT, P_OUT>
{
  public abstract Promise<D_OUT, F_OUT, P_OUT> pipeDone(D paramD);
}