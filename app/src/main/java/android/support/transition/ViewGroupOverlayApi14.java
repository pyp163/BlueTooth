package android.support.transition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

class ViewGroupOverlayApi14 extends ViewOverlayApi14
  implements ViewGroupOverlayImpl
{
  ViewGroupOverlayApi14(Context paramContext, ViewGroup paramViewGroup, View paramView)
  {
    super(paramContext, paramViewGroup, paramView);
  }

  static ViewGroupOverlayApi14 createFrom(ViewGroup paramViewGroup)
  {
    return (ViewGroupOverlayApi14)ViewOverlayApi14.createFrom(paramViewGroup);
  }

  public void add(@NonNull View paramView)
  {
    this.mOverlayViewGroup.add(paramView);
  }

  public void remove(@NonNull View paramView)
  {
    this.mOverlayViewGroup.remove(paramView);
  }
}