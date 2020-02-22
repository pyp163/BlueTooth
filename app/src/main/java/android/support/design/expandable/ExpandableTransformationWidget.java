package android.support.design.expandable;

import android.support.annotation.IdRes;

public abstract interface ExpandableTransformationWidget extends ExpandableWidget
{
  @IdRes
  public abstract int getExpandedComponentIdHint();

  public abstract void setExpandedComponentIdHint(@IdRes int paramInt);
}