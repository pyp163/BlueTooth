package org.jdeferred;

public abstract interface FailCallback<F>
{
  public abstract void onFail(F paramF);
}