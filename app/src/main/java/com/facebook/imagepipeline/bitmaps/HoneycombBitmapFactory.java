package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(11)
@ThreadSafe
public class HoneycombBitmapFactory extends PlatformBitmapFactory
{
  private static final String TAG = "HoneycombBitmapFactory";
  private boolean mImmutableBitmapFallback;
  private final EmptyJpegGenerator mJpegGenerator;
  private final PlatformDecoder mPurgeableDecoder;

  public HoneycombBitmapFactory(EmptyJpegGenerator paramEmptyJpegGenerator, PlatformDecoder paramPlatformDecoder)
  {
    this.mJpegGenerator = paramEmptyJpegGenerator;
    this.mPurgeableDecoder = paramPlatformDecoder;
  }

  private static CloseableReference<Bitmap> createFallbackBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return CloseableReference.of(Bitmap.createBitmap(paramInt1, paramInt2, paramConfig), SimpleBitmapReleaser.getInstance());
  }

  @TargetApi(12)
  public CloseableReference<Bitmap> createBitmapInternal(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    if (this.mImmutableBitmapFallback)
      return createFallbackBitmap(paramInt1, paramInt2, paramConfig);
    CloseableReference localCloseableReference1 = this.mJpegGenerator.generate((short)paramInt1, (short)paramInt2);
    try
    {
      EncodedImage localEncodedImage = new EncodedImage(localCloseableReference1);
      localEncodedImage.setImageFormat(DefaultImageFormats.JPEG);
      try
      {
        CloseableReference localCloseableReference2 = this.mPurgeableDecoder.decodeJPEGFromEncodedImage(localEncodedImage, paramConfig, null, ((PooledByteBuffer)localCloseableReference1.get()).size());
        if (!((Bitmap)localCloseableReference2.get()).isMutable())
        {
          CloseableReference.closeSafely(localCloseableReference2);
          this.mImmutableBitmapFallback = true;
          FLog.wtf(TAG, "Immutable bitmap returned by decoder");
          paramConfig = createFallbackBitmap(paramInt1, paramInt2, paramConfig);
          EncodedImage.closeSafely(localEncodedImage);
          return paramConfig;
        }
        ((Bitmap)localCloseableReference2.get()).setHasAlpha(true);
        ((Bitmap)localCloseableReference2.get()).eraseColor(0);
        EncodedImage.closeSafely(localEncodedImage);
        return localCloseableReference2;
      }
      finally
      {
        EncodedImage.closeSafely(localEncodedImage);
      }
    }
    finally
    {
      localCloseableReference1.close();
    }
    throw paramConfig;
  }
}