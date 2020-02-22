package android.support.transition;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class ViewGroupUtils
{
  static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup paramViewGroup)
  {
    if (Build.VERSION.SDK_INT >= 18)
      return new ViewGroupOverlayApi18(paramViewGroup);
    return ViewGroupOverlayApi14.createFrom(paramViewGroup);
  }

  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      ViewGroupUtilsApi18.suppressLayout(paramViewGroup, paramBoolean);
      return;
    }
    ViewGroupUtilsApi14.suppressLayout(paramViewGroup, paramBoolean);
  }
}