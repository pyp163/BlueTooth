package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class BitmapUtil
{
  public static final int ALPHA_8_BYTES_PER_PIXEL = 1;
  public static final int ARGB_4444_BYTES_PER_PIXEL = 2;
  public static final int ARGB_8888_BYTES_PER_PIXEL = 4;
  private static final Pools.SynchronizedPool<ByteBuffer> DECODE_BUFFERS = new Pools.SynchronizedPool(12);
  private static final int DECODE_BUFFER_SIZE = 16384;
  public static final float MAX_BITMAP_SIZE = 2048.0F;
  private static final int POOL_SIZE = 12;
  public static final int RGB_565_BYTES_PER_PIXEL = 2;

  @Nullable
  public static Pair<Integer, Integer> decodeDimensions(InputStream paramInputStream)
  {
    Preconditions.checkNotNull(paramInputStream);
    ByteBuffer localByteBuffer2 = (ByteBuffer)DECODE_BUFFERS.acquire();
    ByteBuffer localByteBuffer1 = localByteBuffer2;
    if (localByteBuffer2 == null)
      localByteBuffer1 = ByteBuffer.allocate(16384);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    try
    {
      localOptions.inTempStorage = localByteBuffer1.array();
      localByteBuffer2 = null;
      BitmapFactory.decodeStream(paramInputStream, null, localOptions);
      paramInputStream = localByteBuffer2;
      if (localOptions.outWidth != -1)
        if (localOptions.outHeight == -1)
          paramInputStream = localByteBuffer2;
        else
          paramInputStream = new Pair(Integer.valueOf(localOptions.outWidth), Integer.valueOf(localOptions.outHeight));
      return paramInputStream;
    }
    finally
    {
      DECODE_BUFFERS.release(localByteBuffer1);
    }
    throw paramInputStream;
  }

  @Nullable
  public static Pair<Integer, Integer> decodeDimensions(byte[] paramArrayOfByte)
  {
    return decodeDimensions(new ByteArrayInputStream(paramArrayOfByte));
  }

  public static int getPixelSizeForBitmapConfig(Bitmap.Config paramConfig)
  {
    switch (1.$SwitchMap$android$graphics$Bitmap$Config[paramConfig.ordinal()])
    {
    default:
      throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
    case 4:
      return 2;
    case 3:
      return 2;
    case 2:
      return 1;
    case 1:
    }
    return 4;
  }

  public static int getSizeInByteForBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return paramInt1 * paramInt2 * getPixelSizeForBitmapConfig(paramConfig);
  }

  @SuppressLint({"NewApi"})
  public static int getSizeInBytes(@Nullable Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return 0;
    if (Build.VERSION.SDK_INT > 19);
    try
    {
      int i = paramBitmap.getAllocationByteCount();
      return i;
      label21: if (Build.VERSION.SDK_INT >= 12)
        return paramBitmap.getByteCount();
      return paramBitmap.getRowBytes() * paramBitmap.getHeight();
    }
    catch (NullPointerException localNullPointerException)
    {
      break label21;
    }
  }
}