package org.jdeferred;

public abstract interface FailPipe<F, D_OUT, F_OUT, P_OUT>
{
  public abstract Promise<D_OUT, F_OUT, P_OUT> pipeFail(F paramF);
}