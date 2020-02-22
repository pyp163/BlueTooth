package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class NetworkFetchProducer
  implements Producer<EncodedImage>
{
  public static final String INTERMEDIATE_RESULT_PRODUCER_EVENT = "intermediate_result";
  public static final String PRODUCER_NAME = "NetworkFetchProducer";
  private static final int READ_SIZE = 16384;

  @VisibleForTesting
  static final long TIME_BETWEEN_PARTIAL_RESULTS_MS = 100L;
  private final ByteArrayPool mByteArrayPool;
  private final NetworkFetcher mNetworkFetcher;
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  public NetworkFetchProducer(PooledByteBufferFactory paramPooledByteBufferFactory, ByteArrayPool paramByteArrayPool, NetworkFetcher paramNetworkFetcher)
  {
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
    this.mByteArrayPool = paramByteArrayPool;
    this.mNetworkFetcher = paramNetworkFetcher;
  }

  protected static float calculateProgress(int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0)
      return paramInt1 / paramInt2;
    return 1.0F - (float)Math.exp(-paramInt1 / 50000.0D);
  }

  @Nullable
  private Map<String, String> getExtraMap(FetchState paramFetchState, int paramInt)
  {
    if (!paramFetchState.getListener().requiresExtraMap(paramFetchState.getId()))
      return null;
    return this.mNetworkFetcher.getExtraMap(paramFetchState, paramInt);
  }

  // ERROR //
  private void notifyConsumer(PooledByteBufferOutputStream paramPooledByteBufferOutputStream, int paramInt, @Nullable com.facebook.imagepipeline.common.BytesRange paramBytesRange, Consumer<EncodedImage> paramConsumer)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 98	com/facebook/common/memory/PooledByteBufferOutputStream:toByteBuffer	()Lcom/facebook/common/memory/PooledByteBuffer;
    //   4: invokestatic 104	com/facebook/common/references/CloseableReference:of	(Ljava/io/Closeable;)Lcom/facebook/common/references/CloseableReference;
    //   7: astore 5
    //   9: new 106	com/facebook/imagepipeline/image/EncodedImage
    //   12: dup
    //   13: aload 5
    //   15: invokespecial 109	com/facebook/imagepipeline/image/EncodedImage:<init>	(Lcom/facebook/common/references/CloseableReference;)V
    //   18: astore_1
    //   19: aload_1
    //   20: aload_3
    //   21: invokevirtual 113	com/facebook/imagepipeline/image/EncodedImage:setBytesRange	(Lcom/facebook/imagepipeline/common/BytesRange;)V
    //   24: aload_1
    //   25: invokevirtual 116	com/facebook/imagepipeline/image/EncodedImage:parseMetaData	()V
    //   28: aload 4
    //   30: aload_1
    //   31: iload_2
    //   32: invokeinterface 122 3 0
    //   37: aload_1
    //   38: invokestatic 126	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
    //   41: aload 5
    //   43: invokestatic 128	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   46: return
    //   47: astore_3
    //   48: goto +6 -> 54
    //   51: astore_3
    //   52: aconst_null
    //   53: astore_1
    //   54: aload_1
    //   55: invokestatic 126	com/facebook/imagepipeline/image/EncodedImage:closeSafely	(Lcom/facebook/imagepipeline/image/EncodedImage;)V
    //   58: aload 5
    //   60: invokestatic 128	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
    //   63: aload_3
    //   64: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   19	37	47	finally
    //   9	19	51	finally
  }

  private void onCancellation(FetchState paramFetchState)
  {
    paramFetchState.getListener().onProducerFinishWithCancellation(paramFetchState.getId(), "NetworkFetchProducer", null);
    paramFetchState.getConsumer().onCancellation();
  }

  private void onFailure(FetchState paramFetchState, Throwable paramThrowable)
  {
    paramFetchState.getListener().onProducerFinishWithFailure(paramFetchState.getId(), "NetworkFetchProducer", paramThrowable, null);
    paramFetchState.getListener().onUltimateProducerReached(paramFetchState.getId(), "NetworkFetchProducer", false);
    paramFetchState.getConsumer().onFailure(paramThrowable);
  }

  private boolean shouldPropagateIntermediateResults(FetchState paramFetchState)
  {
    if (!paramFetchState.getContext().isIntermediateResultExpected())
      return false;
    return this.mNetworkFetcher.shouldPropagate(paramFetchState);
  }

  protected void handleFinalResult(PooledByteBufferOutputStream paramPooledByteBufferOutputStream, FetchState paramFetchState)
  {
    Map localMap = getExtraMap(paramFetchState, paramPooledByteBufferOutputStream.size());
    ProducerListener localProducerListener = paramFetchState.getListener();
    localProducerListener.onProducerFinishWithSuccess(paramFetchState.getId(), "NetworkFetchProducer", localMap);
    localProducerListener.onUltimateProducerReached(paramFetchState.getId(), "NetworkFetchProducer", true);
    notifyConsumer(paramPooledByteBufferOutputStream, paramFetchState.getOnNewResultStatusFlags() | 0x1, paramFetchState.getResponseBytesRange(), paramFetchState.getConsumer());
  }

  protected void maybeHandleIntermediateResult(PooledByteBufferOutputStream paramPooledByteBufferOutputStream, FetchState paramFetchState)
  {
    long l = SystemClock.uptimeMillis();
    if ((shouldPropagateIntermediateResults(paramFetchState)) && (l - paramFetchState.getLastIntermediateResultTimeMs() >= 100L))
    {
      paramFetchState.setLastIntermediateResultTimeMs(l);
      paramFetchState.getListener().onProducerEvent(paramFetchState.getId(), "NetworkFetchProducer", "intermediate_result");
      notifyConsumer(paramPooledByteBufferOutputStream, paramFetchState.getOnNewResultStatusFlags(), paramFetchState.getResponseBytesRange(), paramFetchState.getConsumer());
    }
  }

  protected void onResponse(FetchState paramFetchState, InputStream paramInputStream, int paramInt)
    throws IOException
  {
    PooledByteBufferOutputStream localPooledByteBufferOutputStream;
    if (paramInt > 0)
      localPooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream(paramInt);
    else
      localPooledByteBufferOutputStream = this.mPooledByteBufferFactory.newOutputStream();
    byte[] arrayOfByte = (byte[])this.mByteArrayPool.get(16384);
    try
    {
      while (true)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i < 0)
          break;
        if (i > 0)
        {
          localPooledByteBufferOutputStream.write(arrayOfByte, 0, i);
          maybeHandleIntermediateResult(localPooledByteBufferOutputStream, paramFetchState);
          float f = calculateProgress(localPooledByteBufferOutputStream.size(), paramInt);
          paramFetchState.getConsumer().onProgressUpdate(f);
        }
      }
      this.mNetworkFetcher.onFetchCompletion(paramFetchState, localPooledByteBufferOutputStream.size());
      handleFinalResult(localPooledByteBufferOutputStream, paramFetchState);
      return;
    }
    finally
    {
      this.mByteArrayPool.release(arrayOfByte);
      localPooledByteBufferOutputStream.close();
    }
    throw paramFetchState;
  }

  public void produceResults(final Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    paramProducerContext.getListener().onProducerStart(paramProducerContext.getId(), "NetworkFetchProducer");
    paramConsumer = this.mNetworkFetcher.createFetchState(paramConsumer, paramProducerContext);
    this.mNetworkFetcher.fetch(paramConsumer, new NetworkFetcher.Callback()
    {
      public void onCancellation()
      {
        NetworkFetchProducer.this.onCancellation(paramConsumer);
      }

      public void onFailure(Throwable paramAnonymousThrowable)
      {
        NetworkFetchProducer.this.onFailure(paramConsumer, paramAnonymousThrowable);
      }

      public void onResponse(InputStream paramAnonymousInputStream, int paramAnonymousInt)
        throws IOException
      {
        NetworkFetchProducer.this.onResponse(paramConsumer, paramAnonymousInputStream, paramAnonymousInt);
      }
    });
  }
}