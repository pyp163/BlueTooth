package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

@DoNotStrip
public class NativeBlurFilter
{
  static
  {
    ImagePipelineNativeLoader.load();
  }

  public static void iterativeBoxBlur(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    Preconditions.checkNotNull(paramBitmap);
    boolean bool2 = false;
    if (paramInt1 > 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    boolean bool1 = bool2;
    if (paramInt2 > 0)
      bool1 = true;
    Preconditions.checkArgument(bool1);
    nativeIterativeBoxBlur(paramBitmap, paramInt1, paramInt2);
  }

  @DoNotStrip
  private static native void nativeIterativeBoxBlur(Bitmap paramBitmap, int paramInt1, int paramInt2);
}