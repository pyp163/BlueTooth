package com.facebook.imagepipeline.producers;

import android.content.res.Resources;
import android.net.Uri;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalResourceFetchProducer extends LocalFetchProducer
{
  public static final String PRODUCER_NAME = "LocalResourceFetchProducer";
  private final Resources mResources;

  public LocalResourceFetchProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, Resources paramResources)
  {
    super(paramExecutor, paramPooledByteBufferFactory);
    this.mResources = paramResources;
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
    //   7: getfield 17	com/facebook/imagepipeline/producers/LocalResourceFetchProducer:mResources	Landroid/content/res/Resources;
    //   10: aload_1
    //   11: invokestatic 27	com/facebook/imagepipeline/producers/LocalResourceFetchProducer:getResourceId	(Lcom/facebook/imagepipeline/request/ImageRequest;)I
    //   14: invokevirtual 33	android/content/res/Resources:openRawResourceFd	(I)Landroid/content/res/AssetFileDescriptor;
    //   17: astore_1
    //   18: aload_1
    //   19: invokevirtual 38	android/content/res/AssetFileDescriptor:getLength	()J
    //   22: lstore_3
    //   23: lload_3
    //   24: l2i
    //   25: istore_2
    //   26: aload_1
    //   27: ifnull +7 -> 34
    //   30: aload_1
    //   31: invokevirtual 42	android/content/res/AssetFileDescriptor:close	()V
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
    //   58: invokevirtual 42	android/content/res/AssetFileDescriptor:close	()V
    //   61: aload_1
    //   62: athrow
    //   63: aload_1
    //   64: ifnull +7 -> 71
    //   67: aload_1
    //   68: invokevirtual 42	android/content/res/AssetFileDescriptor:close	()V
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
    //   6	18	73	android/content/res/Resources$NotFoundException
    //   18	23	80	android/content/res/Resources$NotFoundException
    //   30	34	85	java/io/IOException
    //   56	61	88	java/io/IOException
    //   67	71	93	java/io/IOException
  }

  private static int getResourceId(ImageRequest paramImageRequest)
  {
    return Integer.parseInt(paramImageRequest.getSourceUri().getPath().substring(1));
  }

  protected EncodedImage getEncodedImage(ImageRequest paramImageRequest)
    throws IOException
  {
    return getEncodedImage(this.mResources.openRawResource(getResourceId(paramImageRequest)), getLength(paramImageRequest));
  }

  protected String getProducerName()
  {
    return "LocalResourceFetchProducer";
  }
}