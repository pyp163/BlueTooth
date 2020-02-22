package android.support.design.internal;

import android.graphics.PorterDuff.Mode;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.view.View;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ViewUtils
{
  public static boolean isLayoutRtl(View paramView)
  {
    return ViewCompat.getLayoutDirection(paramView) == 1;
  }

  public static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
  {
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 9)
        {
          switch (paramInt)
          {
          default:
            return paramMode;
          case 16:
            return PorterDuff.Mode.ADD;
          case 15:
            return PorterDuff.Mode.SCREEN;
          case 14:
          }
          return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_ATOP;
      }
      return PorterDuff.Mode.SRC_IN;
    }
    return PorterDuff.Mode.SRC_OVER;
  }
}