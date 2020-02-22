package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

@DoNotStrip
public class NativeRoundingFilter
{
  static
  {
    ImagePipelineNativeLoader.load();
  }

  @DoNotStrip
  private static native void nativeToCircleFilter(Bitmap paramBitmap);

  public static void toCircle(Bitmap paramBitmap)
  {
    Preconditions.checkNotNull(paramBitmap);
    nativeToCircleFilter(paramBitmap);
  }
}