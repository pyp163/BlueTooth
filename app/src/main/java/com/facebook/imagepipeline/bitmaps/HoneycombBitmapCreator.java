package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;

public class HoneycombBitmapCreator
  implements BitmapCreator
{
  private final FlexByteArrayPool mFlexByteArrayPool;
  private final EmptyJpegGenerator mJpegGenerator;

  public HoneycombBitmapCreator(PoolFactory paramPoolFactory)
  {
    this.mFlexByteArrayPool = paramPoolFactory.getFlexByteArrayPool();
    this.mJpegGenerator = new EmptyJpegGenerator(paramPoolFactory.getPooledByteBufferFactory());
  }

  private static BitmapFactory.Options getBitmapFactoryOptions(int paramInt, Bitmap.Config paramConfig)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inDither = true;
    localOptions.inPreferredConfig = paramConfig;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    localOptions.inSampleSize = paramInt;
    if (Build.VERSION.SDK_INT >= 11)
      localOptions.inMutable = true;
    return localOptions;
  }

  // ERROR //
  @android.annotation.TargetApi(12)
  public android.graphics.Bitmap createNakedBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 34	com/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator:mJpegGenerator	Lcom/facebook/imagepipeline/bitmaps/EmptyJpegGenerator;
    //   4: iload_1
    //   5: i2s
    //   6: iload_2
    //   7: i2s
    //   8: invokevirtual 75	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:generate	(SS)Lcom/facebook/common/references/CloseableReference;
    //   11: astore 7
    //   13: aconst_null
    //   14: astore 5
    //   16: new 77	com/facebook/imagepipeline/image/EncodedImage
    //   19: dup
    //   20: aload 7
    //   22: invokespecial 80	com/facebook/imagepipeline/image/EncodedImage:<init>	(Lcom/facebook/common/references/CloseableReference;)V
    //   25: astore 4
    //   27: aload 4
    //   29: getstatic 86	com/facebook/imageformat/DefaultImageFormats:JPEG	Lcom/facebook/imageformat/ImageFormat;
    //   32: invokevirtual 90	com/facebook/imagepipeline/image/EncodedImage:setImageFormat	(Lcom/facebook/imageformat/ImageFormat;)V
    //   35: aload 4
    //   37: invokevirtual 94	com/facebook/imagepipeline/image/EncodedImage:getSampleSize	()I
    //   40: aload_3
    //   41: invokestatic 96	com/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator:getBitmapFactoryOptions	(ILandroid/graphics/Bitmap$Config;)Landroid/graphics/BitmapFactory$Options;
    //   44: astore_3
    //   45: aload 7
    //   47: invokevirtual 102	com/facebook/common/references/CloseableReference:get	()Ljava/lang/Object;
    //   50: checkcast 104	com/facebook/common/memory/PooledByteBuffer
    //   53: invokeinterface 107 1 0
    //   58: istore_1
    //   59: aload 7
    //   61: invokevirtual 102	com/facebook/common/references/CloseableReference:get	()Ljava/lang/Object;
    //   64: checkcast 104	com/facebook/common/memory/PooledByteBuffer
    //   67: astore 8
    //   69: aload_0
    //   70: getfield 23	com/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator:mFlexByteArrayPool	Lcom/facebook/imagepipeline/memory/FlexByteArrayPool;
    //   73: iload_1
    //   74: iconst_2
    //   75: iadd
    //   76: invokevirtual 112	com/facebook/imagepipeline/memory/FlexByteArrayPool:get	(I)Lcom/facebook/common/references/CloseableReference;
    //   79: astore 6
    //   81: aload 6
    //   83: invokevirtual 102	com/facebook/common/references/CloseableReference:get	()Ljava/lang/Object;
    //   86: checkcast 114	[B
    //   89: astore 5
    //   91: aload 8
    //   93: iconst_0
    //   94: aload 5
    //   96: iconst_0
    //   97: iload_1
    //   98: invokeinterface 118 5 0
    //   103: pop
    //   104: aload 5
    //   106: iconst_0
    //   107: iload_1
    //   108: aload_3
    //   109: invokestatic 124	android/graphics/BitmapFactory:decodeByteArray	([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   112: astore_3
    //   113: aload_3
    //   114: iconst_1
    //   115: invokevirtual 130	android/graphics/Bitmap:setHasAlpha	(Z)V
    //   118: aload_3
    //   119: iconst_0
    //   120: invokevirtual 134	android/graphics/Bitmap:eraseColor	(I)V
    //   123: aload 6
    //   125: invokestatic 137	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   128: aload 4
    //   130: invokestatic 140	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
    //   133: aload 7
    //   135: invokestatic 137	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   138: aload_3
    //   139: areturn
    //   140: astore_3
    //   141: aload 6
    //   143: astore 5
    //   145: goto +11 -> 156
    //   148: astore_3
    //   149: goto +7 -> 156
    //   152: astore_3
    //   153: aconst_null
    //   154: astore 4
    //   156: aload 5
    //   158: invokestatic 137	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   161: aload 4
    //   163: invokestatic 140	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
    //   166: aload 7
    //   168: invokestatic 137	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   171: aload_3
    //   172: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   81	123	140	finally
    //   27	81	148	finally
    //   16	27	152	finally
  }
}