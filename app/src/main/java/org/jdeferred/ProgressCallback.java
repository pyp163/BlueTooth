package org.jdeferred;

public abstract interface ProgressCallback<P>
{
  public abstract void onProgress(P paramP);
}