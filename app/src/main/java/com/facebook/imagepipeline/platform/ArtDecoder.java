package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(21)
@ThreadSafe
public class ArtDecoder
  implements PlatformDecoder
{
  private static final int DECODE_BUFFER_SIZE = 16384;
  private static final byte[] EOI_TAIL = { -1, -39 };
  private static final Class<?> TAG = ArtDecoder.class;
  private final BitmapPool mBitmapPool;

  @VisibleForTesting
  final Pools.SynchronizedPool<ByteBuffer> mDecodeBuffers;

  public ArtDecoder(BitmapPool paramBitmapPool, int paramInt, Pools.SynchronizedPool paramSynchronizedPool)
  {
    this.mBitmapPool = paramBitmapPool;
    this.mDecodeBuffers = paramSynchronizedPool;
    int i = 0;
    while (i < paramInt)
    {
      this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
      i += 1;
    }
  }

  private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage paramEncodedImage, Bitmap.Config paramConfig)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = paramEncodedImage.getSampleSize();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramEncodedImage.getInputStream(), null, localOptions);
    if ((localOptions.outWidth != -1) && (localOptions.outHeight != -1))
    {
      localOptions.inJustDecodeBounds = false;
      localOptions.inDither = true;
      localOptions.inPreferredConfig = paramConfig;
      localOptions.inMutable = true;
      return localOptions;
    }
    throw new IllegalArgumentException();
  }

  public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage paramEncodedImage, Bitmap.Config paramConfig, @Nullable Rect paramRect)
  {
    paramConfig = getDecodeOptionsForStream(paramEncodedImage, paramConfig);
    int i;
    if (paramConfig.inPreferredConfig != Bitmap.Config.ARGB_8888)
      i = 1;
    else
      i = 0;
    try
    {
      paramConfig = decodeStaticImageFromStream(paramEncodedImage.getInputStream(), paramConfig, paramRect);
      return paramConfig;
    }
    catch (RuntimeException paramConfig)
    {
      if (i != 0)
        return decodeFromEncodedImage(paramEncodedImage, Bitmap.Config.ARGB_8888, paramRect);
    }
    throw paramConfig;
  }

  public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage paramEncodedImage, Bitmap.Config paramConfig, @Nullable Rect paramRect, int paramInt)
  {
    boolean bool = paramEncodedImage.isCompleteAt(paramInt);
    BitmapFactory.Options localOptions = getDecodeOptionsForStream(paramEncodedImage, paramConfig);
    InputStream localInputStream = paramEncodedImage.getInputStream();
    Preconditions.checkNotNull(localInputStream);
    paramConfig = localInputStream;
    if (paramEncodedImage.getSize() > paramInt)
      paramConfig = new LimitedInputStream(localInputStream, paramInt);
    if (!bool)
      paramConfig = new TailAppendingInputStream(paramConfig, EOI_TAIL);
    if (localOptions.inPreferredConfig != Bitmap.Config.ARGB_8888)
      paramInt = 1;
    else
      paramInt = 0;
    try
    {
      paramConfig = decodeStaticImageFromStream(paramConfig, localOptions, paramRect);
      return paramConfig;
    }
    catch (RuntimeException paramConfig)
    {
      if (paramInt != 0)
        return decodeFromEncodedImage(paramEncodedImage, Bitmap.Config.ARGB_8888, paramRect);
    }
    throw paramConfig;
  }

  // ERROR //
  protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream paramInputStream, BitmapFactory.Options paramOptions, @Nullable Rect paramRect)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 133	com/facebook/common/internal/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_2
    //   6: getfield 84	android/graphics/BitmapFactory$Options:outWidth	I
    //   9: istore 4
    //   11: aload_2
    //   12: getfield 87	android/graphics/BitmapFactory$Options:outHeight	I
    //   15: istore 5
    //   17: aload_3
    //   18: ifnull +15 -> 33
    //   21: aload_3
    //   22: invokevirtual 154	android/graphics/Rect:width	()I
    //   25: istore 4
    //   27: aload_3
    //   28: invokevirtual 157	android/graphics/Rect:height	()I
    //   31: istore 5
    //   33: iload 4
    //   35: iload 5
    //   37: aload_2
    //   38: getfield 94	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   41: invokestatic 163	com/facebook/imageutils/BitmapUtil:getSizeInByteForBitmap	(IILandroid/graphics/Bitmap$Config;)I
    //   44: istore 6
    //   46: aload_0
    //   47: getfield 39	com/facebook/imagepipeline/platform/ArtDecoder:mBitmapPool	Lcom/facebook/imagepipeline/memory/BitmapPool;
    //   50: iload 6
    //   52: invokevirtual 169	com/facebook/imagepipeline/memory/BitmapPool:get	(I)Ljava/lang/Object;
    //   55: checkcast 171	android/graphics/Bitmap
    //   58: astore 11
    //   60: aload 11
    //   62: ifnonnull +13 -> 75
    //   65: new 173	java/lang/NullPointerException
    //   68: dup
    //   69: ldc 175
    //   71: invokespecial 178	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   74: athrow
    //   75: aload_2
    //   76: aload 11
    //   78: putfield 182	android/graphics/BitmapFactory$Options:inBitmap	Landroid/graphics/Bitmap;
    //   81: aload_0
    //   82: getfield 41	com/facebook/imagepipeline/platform/ArtDecoder:mDecodeBuffers	Landroid/support/v4/util/Pools$SynchronizedPool;
    //   85: invokevirtual 186	android/support/v4/util/Pools$SynchronizedPool:acquire	()Ljava/lang/Object;
    //   88: checkcast 43	java/nio/ByteBuffer
    //   91: astore 7
    //   93: aload 7
    //   95: astore 9
    //   97: aload 7
    //   99: ifnonnull +11 -> 110
    //   102: sipush 16384
    //   105: invokestatic 47	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   108: astore 9
    //   110: aload_2
    //   111: aload 9
    //   113: invokevirtual 190	java/nio/ByteBuffer:array	()[B
    //   116: putfield 193	android/graphics/BitmapFactory$Options:inTempStorage	[B
    //   119: aload_3
    //   120: ifnull +267 -> 387
    //   123: aload 11
    //   125: iload 4
    //   127: iload 5
    //   129: aload_2
    //   130: getfield 94	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   133: invokevirtual 197	android/graphics/Bitmap:reconfigure	(IILandroid/graphics/Bitmap$Config;)V
    //   136: aload_1
    //   137: iconst_1
    //   138: invokestatic 203	android/graphics/BitmapRegionDecoder:newInstance	(Ljava/io/InputStream;Z)Landroid/graphics/BitmapRegionDecoder;
    //   141: astore 8
    //   143: aload 8
    //   145: astore 7
    //   147: aload 8
    //   149: aload_3
    //   150: aload_2
    //   151: invokevirtual 207	android/graphics/BitmapRegionDecoder:decodeRegion	(Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   154: astore 10
    //   156: aload 10
    //   158: astore_3
    //   159: aload 8
    //   161: ifnull +69 -> 230
    //   164: aload 8
    //   166: invokevirtual 210	android/graphics/BitmapRegionDecoder:recycle	()V
    //   169: aload 10
    //   171: astore_3
    //   172: goto +58 -> 230
    //   175: astore_2
    //   176: aconst_null
    //   177: astore 7
    //   179: goto +39 -> 218
    //   182: aconst_null
    //   183: astore 8
    //   185: aload 8
    //   187: astore 7
    //   189: getstatic 28	com/facebook/imagepipeline/platform/ArtDecoder:TAG	Ljava/lang/Class;
    //   192: ldc 212
    //   194: iconst_1
    //   195: anewarray 4	java/lang/Object
    //   198: dup
    //   199: iconst_0
    //   200: aload_3
    //   201: aastore
    //   202: invokestatic 218	com/facebook/common/logging/FLog:e	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V
    //   205: aload 8
    //   207: ifnull +180 -> 387
    //   210: aload 8
    //   212: invokevirtual 210	android/graphics/BitmapRegionDecoder:recycle	()V
    //   215: goto +172 -> 387
    //   218: aload 7
    //   220: ifnull +8 -> 228
    //   223: aload 7
    //   225: invokevirtual 210	android/graphics/BitmapRegionDecoder:recycle	()V
    //   228: aload_2
    //   229: athrow
    //   230: aload_3
    //   231: astore 7
    //   233: aload_3
    //   234: ifnonnull +11 -> 245
    //   237: aload_1
    //   238: aconst_null
    //   239: aload_2
    //   240: invokestatic 81	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   243: astore 7
    //   245: aload_0
    //   246: getfield 41	com/facebook/imagepipeline/platform/ArtDecoder:mDecodeBuffers	Landroid/support/v4/util/Pools$SynchronizedPool;
    //   249: aload 9
    //   251: invokevirtual 53	android/support/v4/util/Pools$SynchronizedPool:release	(Ljava/lang/Object;)Z
    //   254: pop
    //   255: aload 11
    //   257: aload 7
    //   259: if_acmpeq +25 -> 284
    //   262: aload_0
    //   263: getfield 39	com/facebook/imagepipeline/platform/ArtDecoder:mBitmapPool	Lcom/facebook/imagepipeline/memory/BitmapPool;
    //   266: aload 11
    //   268: invokevirtual 221	com/facebook/imagepipeline/memory/BitmapPool:release	(Ljava/lang/Object;)V
    //   271: aload 7
    //   273: invokevirtual 222	android/graphics/Bitmap:recycle	()V
    //   276: new 224	java/lang/IllegalStateException
    //   279: dup
    //   280: invokespecial 225	java/lang/IllegalStateException:<init>	()V
    //   283: athrow
    //   284: aload 7
    //   286: aload_0
    //   287: getfield 39	com/facebook/imagepipeline/platform/ArtDecoder:mBitmapPool	Lcom/facebook/imagepipeline/memory/BitmapPool;
    //   290: invokestatic 231	com/facebook/common/references/CloseableReference:of	(Ljava/lang/Object;Lcom/facebook/common/references/ResourceReleaser;)Lcom/facebook/common/references/CloseableReference;
    //   293: areturn
    //   294: astore_1
    //   295: goto +62 -> 357
    //   298: astore_1
    //   299: aload_0
    //   300: getfield 39	com/facebook/imagepipeline/platform/ArtDecoder:mBitmapPool	Lcom/facebook/imagepipeline/memory/BitmapPool;
    //   303: aload 11
    //   305: invokevirtual 221	com/facebook/imagepipeline/memory/BitmapPool:release	(Ljava/lang/Object;)V
    //   308: aload_1
    //   309: athrow
    //   310: astore_2
    //   311: aload_0
    //   312: getfield 39	com/facebook/imagepipeline/platform/ArtDecoder:mBitmapPool	Lcom/facebook/imagepipeline/memory/BitmapPool;
    //   315: aload 11
    //   317: invokevirtual 221	com/facebook/imagepipeline/memory/BitmapPool:release	(Ljava/lang/Object;)V
    //   320: aload_1
    //   321: invokevirtual 236	java/io/InputStream:reset	()V
    //   324: aload_1
    //   325: invokestatic 239	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   328: astore_1
    //   329: aload_1
    //   330: ifnonnull +5 -> 335
    //   333: aload_2
    //   334: athrow
    //   335: aload_1
    //   336: invokestatic 245	com/facebook/imagepipeline/bitmaps/SimpleBitmapReleaser:getInstance	()Lcom/facebook/imagepipeline/bitmaps/SimpleBitmapReleaser;
    //   339: invokestatic 231	com/facebook/common/references/CloseableReference:of	(Ljava/lang/Object;Lcom/facebook/common/references/ResourceReleaser;)Lcom/facebook/common/references/CloseableReference;
    //   342: astore_1
    //   343: aload_0
    //   344: getfield 41	com/facebook/imagepipeline/platform/ArtDecoder:mDecodeBuffers	Landroid/support/v4/util/Pools$SynchronizedPool;
    //   347: aload 9
    //   349: invokevirtual 53	android/support/v4/util/Pools$SynchronizedPool:release	(Ljava/lang/Object;)Z
    //   352: pop
    //   353: aload_1
    //   354: areturn
    //   355: aload_2
    //   356: athrow
    //   357: aload_0
    //   358: getfield 41	com/facebook/imagepipeline/platform/ArtDecoder:mDecodeBuffers	Landroid/support/v4/util/Pools$SynchronizedPool;
    //   361: aload 9
    //   363: invokevirtual 53	android/support/v4/util/Pools$SynchronizedPool:release	(Ljava/lang/Object;)Z
    //   366: pop
    //   367: aload_1
    //   368: athrow
    //   369: astore 7
    //   371: goto -189 -> 182
    //   374: astore 7
    //   376: goto -191 -> 185
    //   379: astore_1
    //   380: goto -25 -> 355
    //   383: astore_2
    //   384: goto -166 -> 218
    //   387: aconst_null
    //   388: astore_3
    //   389: goto -159 -> 230
    //
    // Exception table:
    //   from	to	target	type
    //   123	143	175	finally
    //   110	119	294	finally
    //   164	169	294	finally
    //   210	215	294	finally
    //   223	228	294	finally
    //   228	230	294	finally
    //   237	245	294	finally
    //   299	310	294	finally
    //   311	320	294	finally
    //   320	329	294	finally
    //   333	335	294	finally
    //   335	343	294	finally
    //   355	357	294	finally
    //   110	119	298	java/lang/RuntimeException
    //   164	169	298	java/lang/RuntimeException
    //   210	215	298	java/lang/RuntimeException
    //   223	228	298	java/lang/RuntimeException
    //   228	230	298	java/lang/RuntimeException
    //   237	245	298	java/lang/RuntimeException
    //   110	119	310	java/lang/IllegalArgumentException
    //   164	169	310	java/lang/IllegalArgumentException
    //   210	215	310	java/lang/IllegalArgumentException
    //   223	228	310	java/lang/IllegalArgumentException
    //   228	230	310	java/lang/IllegalArgumentException
    //   237	245	310	java/lang/IllegalArgumentException
    //   123	143	369	java/io/IOException
    //   147	156	374	java/io/IOException
    //   320	329	379	java/io/IOException
    //   333	335	379	java/io/IOException
    //   335	343	379	java/io/IOException
    //   147	156	383	finally
    //   189	205	383	finally
  }
}