package android.arch.lifecycle;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public abstract interface GenericLifecycleObserver extends LifecycleObserver
{
  public abstract void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent);
}