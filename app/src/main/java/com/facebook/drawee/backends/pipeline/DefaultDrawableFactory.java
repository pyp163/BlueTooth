package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;

public class DefaultDrawableFactory
  implements DrawableFactory
{
  private final DrawableFactory mAnimatedDrawableFactory;
  private final Resources mResources;

  public DefaultDrawableFactory(Resources paramResources, DrawableFactory paramDrawableFactory)
  {
    this.mResources = paramResources;
    this.mAnimatedDrawableFactory = paramDrawableFactory;
  }

  private static boolean hasTransformableExifOrientation(CloseableStaticBitmap paramCloseableStaticBitmap)
  {
    return (paramCloseableStaticBitmap.getExifOrientation() != 1) && (paramCloseableStaticBitmap.getExifOrientation() != 0);
  }

  private static boolean hasTransformableRotationAngle(CloseableStaticBitmap paramCloseableStaticBitmap)
  {
    return (paramCloseableStaticBitmap.getRotationAngle() != 0) && (paramCloseableStaticBitmap.getRotationAngle() != -1);
  }

  public Drawable createDrawable(CloseableImage paramCloseableImage)
  {
    if ((paramCloseableImage instanceof CloseableStaticBitmap))
    {
      paramCloseableImage = (CloseableStaticBitmap)paramCloseableImage;
      BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.mResources, paramCloseableImage.getUnderlyingBitmap());
      if ((!hasTransformableRotationAngle(paramCloseableImage)) && (!hasTransformableExifOrientation(paramCloseableImage)))
        return localBitmapDrawable;
      return new OrientedDrawable(localBitmapDrawable, paramCloseableImage.getRotationAngle(), paramCloseableImage.getExifOrientation());
    }
    if ((this.mAnimatedDrawableFactory != null) && (this.mAnimatedDrawableFactory.supportsImageType(paramCloseableImage)))
      return this.mAnimatedDrawableFactory.createDrawable(paramCloseableImage);
    return null;
  }

  public boolean supportsImageType(CloseableImage paramCloseableImage)
  {
    return true;
  }
}