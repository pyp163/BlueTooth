package android.support.transition;

import android.support.annotation.NonNull;
import android.view.View;

abstract interface ViewGroupOverlayImpl extends ViewOverlayImpl
{
  public abstract void add(@NonNull View paramView);

  public abstract void remove(@NonNull View paramView);
}