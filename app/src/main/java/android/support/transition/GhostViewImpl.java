package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

abstract interface GhostViewImpl
{
  public abstract void reserveEndViewTransition(ViewGroup paramViewGroup, View paramView);

  public abstract void setVisibility(int paramInt);
}