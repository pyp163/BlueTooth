package com.facebook.imagepipeline.producers;

import android.content.res.AssetManager;
import android.net.Uri;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalAssetFetchProducer extends LocalFetchProducer
{
  public static final String PRODUCER_NAME = "LocalAssetFetchProducer";
  private final AssetManager mAssetManager;

  public LocalAssetFetchProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, AssetManager paramAssetManager)
  {
    super(paramExecutor, paramPooledByteBufferFactory);
    this.mAssetManager = paramAssetManager;
  }

  private static String getAssetName(ImageRequest paramImageRequest)
  {
    return paramImageRequest.getSourceUri().getPath().substring(1);
  }

  // ERROR //
  private int getLength(ImageRequest paramImageRequest)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: getfield 17	com/facebook/imagepipeline/producers/LocalAssetFetchProducer:mAssetManager	Landroid/content/res/AssetManager;
    //   10: aload_1
    //   11: invokestatic 44	com/facebook/imagepipeline/producers/LocalAssetFetchProducer:getAssetName	(Lcom/facebook/imagepipeline/request/ImageRequest;)Ljava/lang/String;
    //   14: invokevirtual 50	android/content/res/AssetManager:openFd	(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
    //   17: astore_1
    //   18: aload_1
    //   19: invokevirtual 55	android/content/res/AssetFileDescriptor:getLength	()J
    //   22: lstore_3
    //   23: lload_3
    //   24: l2i
    //   25: istore_2
    //   26: aload_1
    //   27: ifnull +7 -> 34
    //   30: aload_1
    //   31: invokevirtual 59	android/content/res/AssetFileDescriptor:close	()V
    //   34: iload_2
    //   35: ireturn
    //   36: astore 5
    //   38: aload_1
    //   39: astore 6
    //   41: aload 5
    //   43: astore_1
    //   44: goto +7 -> 51
    //   47: goto +16 -> 63
    //   50: astore_1
    //   51: aload 6
    //   53: ifnull +8 -> 61
    //   56: aload 6
    //   58: invokevirtual 59	android/content/res/AssetFileDescriptor:close	()V
    //   61: aload_1
    //   62: athrow
    //   63: aload_1
    //   64: ifnull +7 -> 71
    //   67: aload_1
    //   68: invokevirtual 59	android/content/res/AssetFileDescriptor:close	()V
    //   71: iconst_m1
    //   72: ireturn
    //   73: astore_1
    //   74: aload 5
    //   76: astore_1
    //   77: goto -14 -> 63
    //   80: astore 5
    //   82: goto -35 -> 47
    //   85: astore_1
    //   86: iload_2
    //   87: ireturn
    //   88: astore 5
    //   90: goto -29 -> 61
    //   93: astore_1
    //   94: iconst_m1
    //   95: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   18	23	36	finally
    //   6	18	50	finally
    //   6	18	73	java/io/IOException
    //   18	23	80	java/io/IOException
    //   30	34	85	java/io/IOException
    //   56	61	88	java/io/IOException
    //   67	71	93	java/io/IOException
  }

  protected EncodedImage getEncodedImage(ImageRequest paramImageRequest)
    throws IOException
  {
    return getEncodedImage(this.mAssetManager.open(getAssetName(paramImageRequest), 2), getLength(paramImageRequest));
  }

  protected String getProducerName()
  {
    return "LocalAssetFetchProducer";
  }
}