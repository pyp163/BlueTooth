package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class ResizeAndRotateProducer
  implements Producer<EncodedImage>
{

  @VisibleForTesting
  static final int DEFAULT_JPEG_QUALITY = 85;
  private static final String DOWNSAMPLE_ENUMERATOR_KEY = "downsampleEnumerator";
  private static final String FRACTION_KEY = "Fraction";
  private static final int FULL_ROUND = 360;
  private static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS = ImmutableList.of(new Integer[] { Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(4), Integer.valueOf(5) });

  @VisibleForTesting
  static final int MAX_JPEG_SCALE_NUMERATOR = 8;

  @VisibleForTesting
  static final int MIN_TRANSFORM_INTERVAL_MS = 100;
  private static final String ORIGINAL_SIZE_KEY = "Original size";
  public static final String PRODUCER_NAME = "ResizeAndRotateProducer";
  private static final String REQUESTED_SIZE_KEY = "Requested size";
  private static final String ROTATION_ANGLE_KEY = "rotationAngle";
  private static final String SOFTWARE_ENUMERATOR_KEY = "softwareEnumerator";
  private final Executor mExecutor;
  private final Producer<EncodedImage> mInputProducer;
  private final PooledByteBufferFactory mPooledByteBufferFactory;
  private final boolean mResizingEnabled;
  private final boolean mUseDownsamplingRatio;

  public ResizeAndRotateProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, boolean paramBoolean1, Producer<EncodedImage> paramProducer, boolean paramBoolean2)
  {
    this.mExecutor = ((Executor)Preconditions.checkNotNull(paramExecutor));
    this.mPooledByteBufferFactory = ((PooledByteBufferFactory)Preconditions.checkNotNull(paramPooledByteBufferFactory));
    this.mResizingEnabled = paramBoolean1;
    this.mInputProducer = ((Producer)Preconditions.checkNotNull(paramProducer));
    this.mUseDownsamplingRatio = paramBoolean2;
  }

  @VisibleForTesting
  static int calculateDownsampleNumerator(int paramInt)
  {
    return Math.max(1, 8 / paramInt);
  }

  @VisibleForTesting
  static float determineResizeRatio(ResizeOptions paramResizeOptions, int paramInt1, int paramInt2)
  {
    if (paramResizeOptions == null)
      return 1.0F;
    float f1 = paramResizeOptions.width;
    float f4 = paramInt1;
    f1 /= f4;
    float f2 = paramResizeOptions.height;
    float f3 = paramInt2;
    f2 = Math.max(f1, f2 / f3);
    f1 = f2;
    if (f4 * f2 > paramResizeOptions.maxBitmapSize)
      f1 = paramResizeOptions.maxBitmapSize / f4;
    f2 = f1;
    if (f3 * f1 > paramResizeOptions.maxBitmapSize)
      f2 = paramResizeOptions.maxBitmapSize / f3;
    return f2;
  }

  private static int extractOrientationFromMetadata(EncodedImage paramEncodedImage)
  {
    int i = paramEncodedImage.getRotationAngle();
    if ((i != 90) && (i != 180) && (i != 270))
      return 0;
    return paramEncodedImage.getRotationAngle();
  }

  private static int getForceRotatedInvertedExifOrientation(RotationOptions paramRotationOptions, EncodedImage paramEncodedImage)
  {
    int i = paramEncodedImage.getExifOrientation();
    int j = INVERTED_EXIF_ORIENTATIONS.indexOf(Integer.valueOf(i));
    if (j < 0)
      throw new IllegalArgumentException("Only accepts inverted exif orientations");
    i = 0;
    if (!paramRotationOptions.useImageMetadata())
      i = paramRotationOptions.getForcedAngle();
    i /= 90;
    return ((Integer)INVERTED_EXIF_ORIENTATIONS.get((j + i) % INVERTED_EXIF_ORIENTATIONS.size())).intValue();
  }

  private static int getRotationAngle(RotationOptions paramRotationOptions, EncodedImage paramEncodedImage)
  {
    if (!paramRotationOptions.rotationEnabled())
      return 0;
    int i = extractOrientationFromMetadata(paramEncodedImage);
    if (paramRotationOptions.useImageMetadata())
      return i;
    return (i + paramRotationOptions.getForcedAngle()) % 360;
  }

  private static int getSoftwareNumerator(ImageRequest paramImageRequest, EncodedImage paramEncodedImage, boolean paramBoolean)
  {
    if (!paramBoolean)
      return 8;
    ResizeOptions localResizeOptions = paramImageRequest.getResizeOptions();
    if (localResizeOptions == null)
      return 8;
    int k = getRotationAngle(paramImageRequest.getRotationOptions(), paramEncodedImage);
    paramBoolean = INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(paramEncodedImage.getExifOrientation()));
    int i = 0;
    if (paramBoolean)
      j = getForceRotatedInvertedExifOrientation(paramImageRequest.getRotationOptions(), paramEncodedImage);
    else
      j = 0;
    if ((k == 90) || (k == 270) || (j == 5) || (j == 7))
      i = 1;
    if (i != 0)
      j = paramEncodedImage.getHeight();
    else
      j = paramEncodedImage.getWidth();
    if (i != 0)
      i = paramEncodedImage.getWidth();
    else
      i = paramEncodedImage.getHeight();
    int j = roundNumerator(determineResizeRatio(localResizeOptions, j, i), localResizeOptions.roundUpFraction);
    if (j > 8)
      return 8;
    i = j;
    if (j < 1)
      i = 1;
    return i;
  }

  @VisibleForTesting
  static int roundNumerator(float paramFloat1, float paramFloat2)
  {
    return (int)(paramFloat2 + paramFloat1 * 8.0F);
  }

  private static boolean shouldResize(int paramInt)
  {
    return paramInt < 8;
  }

  private static boolean shouldRotate(RotationOptions paramRotationOptions, EncodedImage paramEncodedImage)
  {
    return (!paramRotationOptions.canDeferUntilRendered()) && ((getRotationAngle(paramRotationOptions, paramEncodedImage) != 0) || (shouldRotateUsingExifOrientation(paramRotationOptions, paramEncodedImage)));
  }

  private static boolean shouldRotateUsingExifOrientation(RotationOptions paramRotationOptions, EncodedImage paramEncodedImage)
  {
    if ((paramRotationOptions.rotationEnabled()) && (!paramRotationOptions.canDeferUntilRendered()))
      return INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(paramEncodedImage.getExifOrientation()));
    paramEncodedImage.setExifOrientation(0);
    return false;
  }

  private static TriState shouldTransform(ImageRequest paramImageRequest, EncodedImage paramEncodedImage, boolean paramBoolean)
  {
    if ((paramEncodedImage != null) && (paramEncodedImage.getImageFormat() != ImageFormat.UNKNOWN))
    {
      if (paramEncodedImage.getImageFormat() != DefaultImageFormats.JPEG)
        return TriState.NO;
      if ((!shouldRotate(paramImageRequest.getRotationOptions(), paramEncodedImage)) && (!shouldResize(getSoftwareNumerator(paramImageRequest, paramEncodedImage, paramBoolean))))
        paramBoolean = false;
      else
        paramBoolean = true;
      return TriState.valueOf(paramBoolean);
    }
    return TriState.UNSET;
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    this.mInputProducer.produceResults(new TransformingConsumer(paramConsumer, paramProducerContext), paramProducerContext);
  }

  private class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private boolean mIsCancelled = false;
    private final JobScheduler mJobScheduler;
    private final ProducerContext mProducerContext;

    public TransformingConsumer(ProducerContext arg2)
    {
      super();
      this.mProducerContext = local1;
      JobScheduler.JobRunnable local1 = new JobScheduler.JobRunnable()
      {
        public void run(EncodedImage paramAnonymousEncodedImage, int paramAnonymousInt)
        {
          ResizeAndRotateProducer.TransformingConsumer.this.doTransform(paramAnonymousEncodedImage, paramAnonymousInt);
        }
      };
      this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, local1, 100);
      this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks()
      {
        public void onCancellationRequested()
        {
          ResizeAndRotateProducer.TransformingConsumer.this.mJobScheduler.clearJob();
          ResizeAndRotateProducer.TransformingConsumer.access$402(ResizeAndRotateProducer.TransformingConsumer.this, true);
          localConsumer.onCancellation();
        }

        public void onIsIntermediateResultExpectedChanged()
        {
          if (ResizeAndRotateProducer.TransformingConsumer.this.mProducerContext.isIntermediateResultExpected())
            ResizeAndRotateProducer.TransformingConsumer.this.mJobScheduler.scheduleJob();
        }
      });
    }

    // ERROR //
    private void doTransform(EncodedImage paramEncodedImage, int paramInt)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   4: invokeinterface 75 1 0
      //   9: aload_0
      //   10: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   13: invokeinterface 79 1 0
      //   18: ldc 81
      //   20: invokeinterface 87 3 0
      //   25: aload_0
      //   26: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   29: invokeinterface 91 1 0
      //   34: astore 14
      //   36: aload_0
      //   37: getfield 24	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:this$0	Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;
      //   40: invokestatic 95	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$700	(Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;)Lcom/facebook/common/memory/PooledByteBufferFactory;
      //   43: invokeinterface 101 1 0
      //   48: astore 13
      //   50: aconst_null
      //   51: astore 11
      //   53: aconst_null
      //   54: astore 12
      //   56: aload 14
      //   58: aload_1
      //   59: aload_0
      //   60: getfield 24	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:this$0	Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;
      //   63: invokestatic 105	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$500	(Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;)Z
      //   66: invokestatic 109	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$800	(Lcom/facebook/imagepipeline/request/ImageRequest;Lcom/facebook/imagepipeline/image/EncodedImage;Z)I
      //   69: istore 4
      //   71: aload 14
      //   73: aload_1
      //   74: invokestatic 115	com/facebook/imagepipeline/producers/DownsampleUtil:determineSampleSize	(Lcom/facebook/imagepipeline/request/ImageRequest;Lcom/facebook/imagepipeline/image/EncodedImage;)I
      //   77: istore 6
      //   79: iload 6
      //   81: invokestatic 119	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:calculateDownsampleNumerator	(I)I
      //   84: istore 5
      //   86: aload_0
      //   87: getfield 24	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:this$0	Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;
      //   90: invokestatic 122	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$900	(Lcom/facebook/imagepipeline/producers/ResizeAndRotateProducer;)Z
      //   93: ifeq +462 -> 555
      //   96: iload 5
      //   98: istore_3
      //   99: goto +3 -> 102
      //   102: aload_1
      //   103: invokevirtual 128	com/facebook/imagepipeline/image/EncodedImage:getInputStream	()Ljava/io/InputStream;
      //   106: astore 10
      //   108: aload 12
      //   110: astore 9
      //   112: aload 10
      //   114: astore 8
      //   116: invokestatic 132	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$1000	()Lcom/facebook/common/internal/ImmutableList;
      //   119: aload_1
      //   120: invokevirtual 136	com/facebook/imagepipeline/image/EncodedImage:getExifOrientation	()I
      //   123: invokestatic 142	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   126: invokevirtual 148	com/facebook/common/internal/ImmutableList:contains	(Ljava/lang/Object;)Z
      //   129: ifeq +75 -> 204
      //   132: aload 12
      //   134: astore 9
      //   136: aload 10
      //   138: astore 8
      //   140: aload 14
      //   142: invokevirtual 154	com/facebook/imagepipeline/request/ImageRequest:getRotationOptions	()Lcom/facebook/imagepipeline/common/RotationOptions;
      //   145: aload_1
      //   146: invokestatic 158	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$1100	(Lcom/facebook/imagepipeline/common/RotationOptions;Lcom/facebook/imagepipeline/image/EncodedImage;)I
      //   149: istore 7
      //   151: aload 12
      //   153: astore 9
      //   155: aload 10
      //   157: astore 8
      //   159: aload_0
      //   160: aload_1
      //   161: aload 14
      //   163: iload_3
      //   164: iload 5
      //   166: iload 4
      //   168: iconst_0
      //   169: invokespecial 162	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:getExtraMap	(Lcom/facebook/imagepipeline/image/EncodedImage;Lcom/facebook/imagepipeline/request/ImageRequest;IIII)Ljava/util/Map;
      //   172: astore 11
      //   174: aload 11
      //   176: astore_1
      //   177: aload 10
      //   179: astore 8
      //   181: aload 10
      //   183: aload 13
      //   185: iload 7
      //   187: iload_3
      //   188: bipush 85
      //   190: invokestatic 168	com/facebook/imagepipeline/nativecode/JpegTranscoder:transcodeJpegWithExifOrientation	(Ljava/io/InputStream;Ljava/io/OutputStream;III)V
      //   193: aload 11
      //   195: astore_1
      //   196: goto +73 -> 269
      //   199: astore 8
      //   201: goto +251 -> 452
      //   204: aload 12
      //   206: astore 9
      //   208: aload 10
      //   210: astore 8
      //   212: aload 14
      //   214: invokevirtual 154	com/facebook/imagepipeline/request/ImageRequest:getRotationOptions	()Lcom/facebook/imagepipeline/common/RotationOptions;
      //   217: aload_1
      //   218: invokestatic 171	com/facebook/imagepipeline/producers/ResizeAndRotateProducer:access$1200	(Lcom/facebook/imagepipeline/common/RotationOptions;Lcom/facebook/imagepipeline/image/EncodedImage;)I
      //   221: istore 7
      //   223: aload 12
      //   225: astore 9
      //   227: aload 10
      //   229: astore 8
      //   231: aload_0
      //   232: aload_1
      //   233: aload 14
      //   235: iload_3
      //   236: iload 5
      //   238: iload 4
      //   240: iload 7
      //   242: invokespecial 162	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:getExtraMap	(Lcom/facebook/imagepipeline/image/EncodedImage;Lcom/facebook/imagepipeline/request/ImageRequest;IIII)Ljava/util/Map;
      //   245: astore 11
      //   247: aload 11
      //   249: astore_1
      //   250: aload 10
      //   252: astore 8
      //   254: aload 10
      //   256: aload 13
      //   258: iload 7
      //   260: iload_3
      //   261: bipush 85
      //   263: invokestatic 174	com/facebook/imagepipeline/nativecode/JpegTranscoder:transcodeJpeg	(Ljava/io/InputStream;Ljava/io/OutputStream;III)V
      //   266: aload 11
      //   268: astore_1
      //   269: aload_1
      //   270: astore 9
      //   272: aload 10
      //   274: astore 8
      //   276: aload 13
      //   278: invokevirtual 180	com/facebook/common/memory/PooledByteBufferOutputStream:toByteBuffer	()Lcom/facebook/common/memory/PooledByteBuffer;
      //   281: invokestatic 186	com/facebook/common/references/CloseableReference:of	(Ljava/io/Closeable;)Lcom/facebook/common/references/CloseableReference;
      //   284: astore 11
      //   286: new 124	com/facebook/imagepipeline/image/EncodedImage
      //   289: dup
      //   290: aload 11
      //   292: invokespecial 189	com/facebook/imagepipeline/image/EncodedImage:<init>	(Lcom/facebook/common/references/CloseableReference;)V
      //   295: astore 9
      //   297: aload 9
      //   299: getstatic 195	com/facebook/imageformat/DefaultImageFormats:JPEG	Lcom/facebook/imageformat/ImageFormat;
      //   302: invokevirtual 199	com/facebook/imagepipeline/image/EncodedImage:setImageFormat	(Lcom/facebook/imageformat/ImageFormat;)V
      //   305: aload 9
      //   307: invokevirtual 203	com/facebook/imagepipeline/image/EncodedImage:parseMetaData	()V
      //   310: aload_0
      //   311: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   314: invokeinterface 75 1 0
      //   319: aload_0
      //   320: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   323: invokeinterface 79 1 0
      //   328: ldc 81
      //   330: aload_1
      //   331: invokeinterface 207 4 0
      //   336: iload 6
      //   338: iconst_1
      //   339: if_icmpeq +11 -> 350
      //   342: iload_2
      //   343: bipush 16
      //   345: ior
      //   346: istore_2
      //   347: goto +3 -> 350
      //   350: aload_0
      //   351: invokevirtual 211	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   354: aload 9
      //   356: iload_2
      //   357: invokeinterface 217 3 0
      //   362: aload 9
      //   364: invokestatic 221	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
      //   367: iload_2
      //   368: istore_3
      //   369: aload 10
      //   371: astore 8
      //   373: aload 11
      //   375: invokestatic 223	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   378: aload 10
      //   380: invokestatic 229	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
      //   383: aload 13
      //   385: invokevirtual 232	com/facebook/common/memory/PooledByteBufferOutputStream:close	()V
      //   388: return
      //   389: astore 8
      //   391: goto +25 -> 416
      //   394: astore 8
      //   396: goto +5 -> 401
      //   399: astore 8
      //   401: aload 9
      //   403: invokestatic 221	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
      //   406: aload 8
      //   408: athrow
      //   409: astore 9
      //   411: goto +9 -> 420
      //   414: astore 8
      //   416: aload 8
      //   418: astore 9
      //   420: iload_2
      //   421: istore_3
      //   422: aload 10
      //   424: astore 8
      //   426: aload 11
      //   428: invokestatic 223	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   431: iload_2
      //   432: istore_3
      //   433: aload 10
      //   435: astore 8
      //   437: aload 9
      //   439: athrow
      //   440: astore 8
      //   442: iload_3
      //   443: istore_2
      //   444: goto +8 -> 452
      //   447: astore 8
      //   449: aload 9
      //   451: astore_1
      //   452: aload 8
      //   454: astore 9
      //   456: aload_1
      //   457: astore 11
      //   459: aload 10
      //   461: astore_1
      //   462: goto +14 -> 476
      //   465: astore_1
      //   466: aconst_null
      //   467: astore 8
      //   469: goto +74 -> 543
      //   472: astore 9
      //   474: aconst_null
      //   475: astore_1
      //   476: aload_1
      //   477: astore 8
      //   479: aload_0
      //   480: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   483: invokeinterface 75 1 0
      //   488: aload_0
      //   489: getfield 31	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:mProducerContext	Lcom/facebook/imagepipeline/producers/ProducerContext;
      //   492: invokeinterface 79 1 0
      //   497: ldc 81
      //   499: aload 9
      //   501: aload 11
      //   503: invokeinterface 236 5 0
      //   508: aload_1
      //   509: astore 8
      //   511: iload_2
      //   512: invokestatic 240	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:isLast	(I)Z
      //   515: ifeq +17 -> 532
      //   518: aload_1
      //   519: astore 8
      //   521: aload_0
      //   522: invokevirtual 211	com/facebook/imagepipeline/producers/ResizeAndRotateProducer$TransformingConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   525: aload 9
      //   527: invokeinterface 244 2 0
      //   532: aload_1
      //   533: invokestatic 229	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
      //   536: aload 13
      //   538: invokevirtual 232	com/facebook/common/memory/PooledByteBufferOutputStream:close	()V
      //   541: return
      //   542: astore_1
      //   543: aload 8
      //   545: invokestatic 229	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
      //   548: aload 13
      //   550: invokevirtual 232	com/facebook/common/memory/PooledByteBufferOutputStream:close	()V
      //   553: aload_1
      //   554: athrow
      //   555: iload 4
      //   557: istore_3
      //   558: goto -456 -> 102
      //
      // Exception table:
      //   from	to	target	type
      //   181	193	199	java/lang/Exception
      //   254	266	199	java/lang/Exception
      //   362	367	389	finally
      //   350	362	394	finally
      //   305	336	399	finally
      //   401	409	409	finally
      //   286	305	414	finally
      //   373	378	440	java/lang/Exception
      //   426	431	440	java/lang/Exception
      //   437	440	440	java/lang/Exception
      //   116	132	447	java/lang/Exception
      //   140	151	447	java/lang/Exception
      //   159	174	447	java/lang/Exception
      //   212	223	447	java/lang/Exception
      //   231	247	447	java/lang/Exception
      //   276	286	447	java/lang/Exception
      //   56	96	465	finally
      //   102	108	465	finally
      //   56	96	472	java/lang/Exception
      //   102	108	472	java/lang/Exception
      //   116	132	542	finally
      //   140	151	542	finally
      //   159	174	542	finally
      //   181	193	542	finally
      //   212	223	542	finally
      //   231	247	542	finally
      //   254	266	542	finally
      //   276	286	542	finally
      //   373	378	542	finally
      //   426	431	542	finally
      //   437	440	542	finally
      //   479	508	542	finally
      //   511	518	542	finally
      //   521	532	542	finally
    }

    private Map<String, String> getExtraMap(EncodedImage paramEncodedImage, ImageRequest paramImageRequest, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId()))
        return null;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramEncodedImage.getWidth());
      ((StringBuilder)localObject).append("x");
      ((StringBuilder)localObject).append(paramEncodedImage.getHeight());
      localObject = ((StringBuilder)localObject).toString();
      if (paramImageRequest.getResizeOptions() != null)
      {
        paramEncodedImage = new StringBuilder();
        paramEncodedImage.append(paramImageRequest.getResizeOptions().width);
        paramEncodedImage.append("x");
        paramEncodedImage.append(paramImageRequest.getResizeOptions().height);
        paramEncodedImage = paramEncodedImage.toString();
      }
      else
      {
        paramEncodedImage = "Unspecified";
      }
      if (paramInt1 > 0)
      {
        paramImageRequest = new StringBuilder();
        paramImageRequest.append(paramInt1);
        paramImageRequest.append("/8");
        paramImageRequest = paramImageRequest.toString();
      }
      else
      {
        paramImageRequest = "";
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("Original size", localObject);
      localHashMap.put("Requested size", paramEncodedImage);
      localHashMap.put("Fraction", paramImageRequest);
      localHashMap.put("queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
      localHashMap.put("downsampleEnumerator", Integer.toString(paramInt2));
      localHashMap.put("softwareEnumerator", Integer.toString(paramInt3));
      localHashMap.put("rotationAngle", Integer.toString(paramInt4));
      return ImmutableMap.copyOf(localHashMap);
    }

    private EncodedImage moveImage(EncodedImage paramEncodedImage)
    {
      EncodedImage localEncodedImage = EncodedImage.cloneOrNull(paramEncodedImage);
      paramEncodedImage.close();
      return localEncodedImage;
    }

    protected void onNewResultImpl(@Nullable EncodedImage paramEncodedImage, int paramInt)
    {
      if (this.mIsCancelled)
        return;
      boolean bool = isLast(paramInt);
      if (paramEncodedImage == null)
      {
        if (bool)
          getConsumer().onNewResult(null, 1);
        return;
      }
      Object localObject = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), paramEncodedImage, ResizeAndRotateProducer.this.mResizingEnabled);
      if ((!bool) && (localObject == TriState.UNSET))
        return;
      if (localObject != TriState.YES)
      {
        localObject = paramEncodedImage;
        if (!this.mProducerContext.getImageRequest().getRotationOptions().canDeferUntilRendered())
        {
          localObject = paramEncodedImage;
          if (paramEncodedImage.getRotationAngle() != 0)
          {
            localObject = paramEncodedImage;
            if (paramEncodedImage.getRotationAngle() != -1)
            {
              localObject = moveImage(paramEncodedImage);
              ((EncodedImage)localObject).setRotationAngle(0);
            }
          }
        }
        getConsumer().onNewResult(localObject, paramInt);
        return;
      }
      if (!this.mJobScheduler.updateJob(paramEncodedImage, paramInt))
        return;
      if ((bool) || (this.mProducerContext.isIntermediateResultExpected()))
        this.mJobScheduler.scheduleJob();
    }
  }
}