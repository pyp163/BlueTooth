package org.jdeferred;

public abstract interface FailFilter<F, F_OUT>
{
  public abstract F_OUT filterFail(F paramF);
}