package org.jdeferred;

public abstract interface ProgressPipe<P, D_OUT, F_OUT, P_OUT>
{
  public abstract Promise<D_OUT, F_OUT, P_OUT> pipeProgress(P paramP);
}