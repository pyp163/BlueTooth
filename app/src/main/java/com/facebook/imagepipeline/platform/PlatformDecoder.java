package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;

public abstract interface PlatformDecoder
{
  public abstract CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage paramEncodedImage, Bitmap.Config paramConfig, @Nullable Rect paramRect);

  public abstract CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage paramEncodedImage, Bitmap.Config paramConfig, @Nullable Rect paramRect, int paramInt);
}