package com.facebook.drawee.view;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import javax.annotation.Nullable;

public class AspectRatioMeasure
{
  private static boolean shouldAdjust(int paramInt)
  {
    return (paramInt == 0) || (paramInt == -2);
  }

  public static void updateMeasureSpec(Spec paramSpec, float paramFloat, @Nullable ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
  {
    if (paramFloat > 0.0F)
    {
      if (paramLayoutParams == null)
        return;
      if (shouldAdjust(paramLayoutParams.height))
      {
        paramSpec.height = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int)((View.MeasureSpec.getSize(paramSpec.width) - paramInt1) / paramFloat + paramInt2), paramSpec.height), 1073741824);
        return;
      }
      if (shouldAdjust(paramLayoutParams.width))
        paramSpec.width = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int)((View.MeasureSpec.getSize(paramSpec.height) - paramInt2) * paramFloat + paramInt1), paramSpec.width), 1073741824);
      return;
    }
  }

  public static class Spec
  {
    public int height;
    public int width;
  }
}