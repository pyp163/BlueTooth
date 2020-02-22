package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

public final class ThumbnailSizeChecker
{
  public static final float ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO = 1.333333F;
  private static final int ROTATED_90_DEGREES_CLOCKWISE = 90;
  private static final int ROTATED_90_DEGREES_COUNTER_CLOCKWISE = 270;

  public static int getAcceptableSize(int paramInt)
  {
    return (int)(paramInt * 1.333333F);
  }

  public static boolean isImageBigEnough(int paramInt1, int paramInt2, ResizeOptions paramResizeOptions)
  {
    boolean bool3 = false;
    boolean bool2 = false;
    if (paramResizeOptions == null)
    {
      bool1 = bool2;
      if (getAcceptableSize(paramInt1) >= 2048.0F)
      {
        bool1 = bool2;
        if (getAcceptableSize(paramInt2) >= 2048)
          bool1 = true;
      }
      return bool1;
    }
    boolean bool1 = bool3;
    if (getAcceptableSize(paramInt1) >= paramResizeOptions.width)
    {
      bool1 = bool3;
      if (getAcceptableSize(paramInt2) >= paramResizeOptions.height)
        bool1 = true;
    }
    return bool1;
  }

  public static boolean isImageBigEnough(EncodedImage paramEncodedImage, ResizeOptions paramResizeOptions)
  {
    if (paramEncodedImage == null)
      return false;
    int i = paramEncodedImage.getRotationAngle();
    if ((i != 90) && (i != 270))
      return isImageBigEnough(paramEncodedImage.getWidth(), paramEncodedImage.getHeight(), paramResizeOptions);
    return isImageBigEnough(paramEncodedImage.getHeight(), paramEncodedImage.getWidth(), paramResizeOptions);
  }
}