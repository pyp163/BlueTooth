package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.net.Uri.Builder;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class PartialDiskCacheProducer
  implements Producer<EncodedImage>
{
  public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
  public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
  public static final String PRODUCER_NAME = "PartialDiskCacheProducer";
  private final ByteArrayPool mByteArrayPool;
  private final CacheKeyFactory mCacheKeyFactory;
  private final BufferedDiskCache mDefaultBufferedDiskCache;
  private final Producer<EncodedImage> mInputProducer;
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  public PartialDiskCacheProducer(BufferedDiskCache paramBufferedDiskCache, CacheKeyFactory paramCacheKeyFactory, PooledByteBufferFactory paramPooledByteBufferFactory, ByteArrayPool paramByteArrayPool, Producer<EncodedImage> paramProducer)
  {
    this.mDefaultBufferedDiskCache = paramBufferedDiskCache;
    this.mCacheKeyFactory = paramCacheKeyFactory;
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
    this.mByteArrayPool = paramByteArrayPool;
    this.mInputProducer = paramProducer;
  }

  private static Uri createUriForPartialCacheKey(ImageRequest paramImageRequest)
  {
    return paramImageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial", "true").build();
  }

  @Nullable
  @VisibleForTesting
  static Map<String, String> getExtraMap(ProducerListener paramProducerListener, String paramString, boolean paramBoolean, int paramInt)
  {
    if (!paramProducerListener.requiresExtraMap(paramString))
      return null;
    if (paramBoolean)
      return ImmutableMap.of("cached_value_found", String.valueOf(paramBoolean), "encodedImageSize", String.valueOf(paramInt));
    return ImmutableMap.of("cached_value_found", String.valueOf(paramBoolean));
  }

  private static boolean isTaskCancelled(Task<?> paramTask)
  {
    return (paramTask.isCancelled()) || ((paramTask.isFaulted()) && ((paramTask.getError() instanceof CancellationException)));
  }

  private Continuation<EncodedImage, Void> onFinishDiskReads(final Consumer<EncodedImage> paramConsumer, final ProducerContext paramProducerContext, final CacheKey paramCacheKey)
  {
    final String str = paramProducerContext.getId();
    return new Continuation()
    {
      public Void then(Task<EncodedImage> paramAnonymousTask)
        throws Exception
      {
        if (PartialDiskCacheProducer.isTaskCancelled(paramAnonymousTask))
        {
          this.val$listener.onProducerFinishWithCancellation(str, "PartialDiskCacheProducer", null);
          paramConsumer.onCancellation();
          return null;
        }
        if (paramAnonymousTask.isFaulted())
        {
          this.val$listener.onProducerFinishWithFailure(str, "PartialDiskCacheProducer", paramAnonymousTask.getError(), null);
          PartialDiskCacheProducer.this.startInputProducer(paramConsumer, paramProducerContext, paramCacheKey, null);
          return null;
        }
        paramAnonymousTask = (EncodedImage)paramAnonymousTask.getResult();
        if (paramAnonymousTask != null)
        {
          this.val$listener.onProducerFinishWithSuccess(str, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(this.val$listener, str, true, paramAnonymousTask.getSize()));
          Object localObject = BytesRange.toMax(paramAnonymousTask.getSize() - 1);
          paramAnonymousTask.setBytesRange((BytesRange)localObject);
          int i = paramAnonymousTask.getSize();
          ImageRequest localImageRequest = paramProducerContext.getImageRequest();
          if (((BytesRange)localObject).contains(localImageRequest.getBytesRange()))
          {
            this.val$listener.onUltimateProducerReached(str, "PartialDiskCacheProducer", true);
            paramConsumer.onNewResult(paramAnonymousTask, 9);
            return null;
          }
          paramConsumer.onNewResult(paramAnonymousTask, 8);
          localObject = new SettableProducerContext(ImageRequestBuilder.fromRequest(localImageRequest).setBytesRange(BytesRange.from(i - 1)).build(), paramProducerContext);
          PartialDiskCacheProducer.this.startInputProducer(paramConsumer, (ProducerContext)localObject, paramCacheKey, paramAnonymousTask);
          return null;
        }
        this.val$listener.onProducerFinishWithSuccess(str, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(this.val$listener, str, false, 0));
        PartialDiskCacheProducer.this.startInputProducer(paramConsumer, paramProducerContext, paramCacheKey, paramAnonymousTask);
        return null;
      }
    };
  }

  private void startInputProducer(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext, CacheKey paramCacheKey, @Nullable EncodedImage paramEncodedImage)
  {
    paramConsumer = new PartialDiskCacheConsumer(paramConsumer, this.mDefaultBufferedDiskCache, paramCacheKey, this.mPooledByteBufferFactory, this.mByteArrayPool, paramEncodedImage, null);
    this.mInputProducer.produceResults(paramConsumer, paramProducerContext);
  }

  private void subscribeTaskForRequestCancellation(final AtomicBoolean paramAtomicBoolean, ProducerContext paramProducerContext)
  {
    paramProducerContext.addCallbacks(new BaseProducerContextCallbacks()
    {
      public void onCancellationRequested()
      {
        paramAtomicBoolean.set(true);
      }
    });
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    Object localObject1 = paramProducerContext.getImageRequest();
    if (!((ImageRequest)localObject1).isDiskCacheEnabled())
    {
      this.mInputProducer.produceResults(paramConsumer, paramProducerContext);
      return;
    }
    paramProducerContext.getListener().onProducerStart(paramProducerContext.getId(), "PartialDiskCacheProducer");
    Object localObject2 = createUriForPartialCacheKey((ImageRequest)localObject1);
    localObject1 = this.mCacheKeyFactory.getEncodedCacheKey((ImageRequest)localObject1, (Uri)localObject2, paramProducerContext.getCallerContext());
    localObject2 = new AtomicBoolean(false);
    this.mDefaultBufferedDiskCache.get((CacheKey)localObject1, (AtomicBoolean)localObject2).continueWith(onFinishDiskReads(paramConsumer, paramProducerContext, (CacheKey)localObject1));
    subscribeTaskForRequestCancellation((AtomicBoolean)localObject2, paramProducerContext);
  }

  private static class PartialDiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private static final int READ_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final BufferedDiskCache mDefaultBufferedDiskCache;

    @Nullable
    private final EncodedImage mPartialEncodedImageFromCache;
    private final CacheKey mPartialImageCacheKey;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    private PartialDiskCacheConsumer(Consumer<EncodedImage> paramConsumer, BufferedDiskCache paramBufferedDiskCache, CacheKey paramCacheKey, PooledByteBufferFactory paramPooledByteBufferFactory, ByteArrayPool paramByteArrayPool, @Nullable EncodedImage paramEncodedImage)
    {
      super();
      this.mDefaultBufferedDiskCache = paramBufferedDiskCache;
      this.mPartialImageCacheKey = paramCacheKey;
      this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
      this.mByteArrayPool = paramByteArrayPool;
      this.mPartialEncodedImageFromCache = paramEncodedImage;
    }

    private void copy(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
      throws IOException
    {
      byte[] arrayOfByte = (byte[])this.mByteArrayPool.get(16384);
      int i = paramInt;
      while (i > 0)
        try
        {
          int j = paramInputStream.read(arrayOfByte, 0, Math.min(16384, i));
          if (j >= 0)
          {
            if (j <= 0)
              continue;
            paramOutputStream.write(arrayOfByte, 0, j);
            i -= j;
          }
        }
        finally
        {
          this.mByteArrayPool.release(arrayOfByte);
        }
      this.mByteArrayPool.release(arrayOfByte);
      if (i > 0)
        throw new IOException(String.format((Locale)null, "Failed to read %d bytes - finished %d short", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i) }));
    }

    private PooledByteBufferOutputStream merge(EncodedImage paramEncodedImage1, EncodedImage paramEncodedImage2)
      throws IOException
    {
      int i = paramEncodedImage2.getSize();
      int j = paramEncodedImage2.getBytesRange().from;
      PooledByteBufferOutputStream localPooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream(i + j);
      i = paramEncodedImage2.getBytesRange().from;
      copy(paramEncodedImage1.getInputStream(), localPooledByteBufferOutputStream, i);
      copy(paramEncodedImage2.getInputStream(), localPooledByteBufferOutputStream, paramEncodedImage2.getSize());
      return localPooledByteBufferOutputStream;
    }

    // ERROR //
    private void sendFinalResultToConsumer(PooledByteBufferOutputStream paramPooledByteBufferOutputStream)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual 137	com/facebook/common/memory/PooledByteBufferOutputStream:toByteBuffer	()Lcom/facebook/common/memory/PooledByteBuffer;
      //   4: invokestatic 143	com/facebook/common/references/CloseableReference:of	(Ljava/io/Closeable;)Lcom/facebook/common/references/CloseableReference;
      //   7: astore_3
      //   8: new 104	com/facebook/imagepipeline/image/EncodedImage
      //   11: dup
      //   12: aload_3
      //   13: invokespecial 146	com/facebook/imagepipeline/image/EncodedImage:<init>	(Lcom/facebook/common/references/CloseableReference;)V
      //   16: astore_2
      //   17: aload_2
      //   18: invokevirtual 150	com/facebook/imagepipeline/image/EncodedImage:parseMetaData	()V
      //   21: aload_0
      //   22: invokevirtual 154	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   25: aload_2
      //   26: iconst_1
      //   27: invokeinterface 160 3 0
      //   32: aload_2
      //   33: invokestatic 164	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
      //   36: aload_3
      //   37: invokestatic 166	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   40: return
      //   41: astore_1
      //   42: goto +6 -> 48
      //   45: astore_1
      //   46: aconst_null
      //   47: astore_2
      //   48: aload_2
      //   49: invokestatic 164	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
      //   52: aload_3
      //   53: invokestatic 166	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   56: aload_1
      //   57: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   17	32	41	finally
      //   8	17	45	finally
    }

    // ERROR //
    public void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      // Byte code:
      //   0: iload_2
      //   1: invokestatic 172	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:isNotLast	(I)Z
      //   4: ifeq +4 -> 8
      //   7: return
      //   8: aload_0
      //   9: getfield 37	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialEncodedImageFromCache	Lcom/facebook/imagepipeline/image/EncodedImage;
      //   12: ifnull +89 -> 101
      //   15: aload_1
      //   16: invokevirtual 112	com/facebook/imagepipeline/image/EncodedImage:getBytesRange	()Lcom/facebook/imagepipeline/common/BytesRange;
      //   19: ifnull +82 -> 101
      //   22: aload_0
      //   23: aload_0
      //   24: aload_0
      //   25: getfield 37	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialEncodedImageFromCache	Lcom/facebook/imagepipeline/image/EncodedImage;
      //   28: aload_1
      //   29: invokespecial 174	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:merge	(Lcom/facebook/imagepipeline/image/EncodedImage;Lcom/facebook/imagepipeline/image/EncodedImage;)Lcom/facebook/common/memory/PooledByteBufferOutputStream;
      //   32: invokespecial 176	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:sendFinalResultToConsumer	(Lcom/facebook/common/memory/PooledByteBufferOutputStream;)V
      //   35: aload_1
      //   36: invokevirtual 179	com/facebook/imagepipeline/image/EncodedImage:close	()V
      //   39: aload_0
      //   40: getfield 37	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialEncodedImageFromCache	Lcom/facebook/imagepipeline/image/EncodedImage;
      //   43: invokevirtual 179	com/facebook/imagepipeline/image/EncodedImage:close	()V
      //   46: goto +29 -> 75
      //   49: astore_3
      //   50: goto +38 -> 88
      //   53: astore_3
      //   54: ldc 181
      //   56: ldc 183
      //   58: aload_3
      //   59: invokestatic 189	com/facebook/common/logging/FLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   62: aload_0
      //   63: invokevirtual 154	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   66: aload_3
      //   67: invokeinterface 193 2 0
      //   72: goto -37 -> 35
      //   75: aload_0
      //   76: getfield 29	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mDefaultBufferedDiskCache	Lcom/facebook/imagepipeline/cache/BufferedDiskCache;
      //   79: aload_0
      //   80: getfield 31	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialImageCacheKey	Lcom/facebook/cache/common/CacheKey;
      //   83: invokevirtual 199	com/facebook/imagepipeline/cache/BufferedDiskCache:remove	(Lcom/facebook/cache/common/CacheKey;)Lbolts/Task;
      //   86: pop
      //   87: return
      //   88: aload_1
      //   89: invokevirtual 179	com/facebook/imagepipeline/image/EncodedImage:close	()V
      //   92: aload_0
      //   93: getfield 37	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialEncodedImageFromCache	Lcom/facebook/imagepipeline/image/EncodedImage;
      //   96: invokevirtual 179	com/facebook/imagepipeline/image/EncodedImage:close	()V
      //   99: aload_3
      //   100: athrow
      //   101: iload_2
      //   102: bipush 8
      //   104: invokestatic 203	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:statusHasFlag	(II)Z
      //   107: ifeq +44 -> 151
      //   110: iload_2
      //   111: invokestatic 206	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:isLast	(I)Z
      //   114: ifeq +37 -> 151
      //   117: aload_1
      //   118: invokevirtual 210	com/facebook/imagepipeline/image/EncodedImage:getImageFormat	()Lcom/facebook/imageformat/ImageFormat;
      //   121: getstatic 216	com/facebook/imageformat/ImageFormat:UNKNOWN	Lcom/facebook/imageformat/ImageFormat;
      //   124: if_acmpeq +27 -> 151
      //   127: aload_0
      //   128: getfield 29	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mDefaultBufferedDiskCache	Lcom/facebook/imagepipeline/cache/BufferedDiskCache;
      //   131: aload_0
      //   132: getfield 31	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:mPartialImageCacheKey	Lcom/facebook/cache/common/CacheKey;
      //   135: aload_1
      //   136: invokevirtual 220	com/facebook/imagepipeline/cache/BufferedDiskCache:put	(Lcom/facebook/cache/common/CacheKey;Lcom/facebook/imagepipeline/image/EncodedImage;)V
      //   139: aload_0
      //   140: invokevirtual 154	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   143: aload_1
      //   144: iload_2
      //   145: invokeinterface 160 3 0
      //   150: return
      //   151: aload_0
      //   152: invokevirtual 154	com/facebook/imagepipeline/producers/PartialDiskCacheProducer$PartialDiskCacheConsumer:getConsumer	()Lcom/facebook/imagepipeline/producers/Consumer;
      //   155: aload_1
      //   156: iload_2
      //   157: invokeinterface 160 3 0
      //   162: return
      //
      // Exception table:
      //   from	to	target	type
      //   22	35	49	finally
      //   54	72	49	finally
      //   22	35	53	java/io/IOException
    }
  }
}