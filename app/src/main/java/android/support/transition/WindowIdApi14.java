package android.support.transition;

import android.os.IBinder;

class WindowIdApi14
  implements WindowIdImpl
{
  private final IBinder mToken;

  WindowIdApi14(IBinder paramIBinder)
  {
    this.mToken = paramIBinder;
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WindowIdApi14)) && (((WindowIdApi14)paramObject).mToken.equals(this.mToken));
  }

  public int hashCode()
  {
    return this.mToken.hashCode();
  }
}