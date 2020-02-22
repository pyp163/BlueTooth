package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public abstract class LocalFetchProducer
  implements Producer<EncodedImage>
{
  private final Executor mExecutor;
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  protected LocalFetchProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory)
  {
    this.mExecutor = paramExecutor;
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
  }

  protected EncodedImage getByteBufferBackedEncodedImage(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    Object localObject1 = null;
    if (paramInt <= 0);
    try
    {
      Object localObject2 = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(paramInputStream));
      localObject1 = localObject2;
      break label86;
      localObject2 = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(paramInputStream, paramInt));
      localObject1 = localObject2;
      break label86;
      localObject1 = localObject2;
      EncodedImage localEncodedImage = new EncodedImage((CloseableReference)localObject2);
      Closeables.closeQuietly(paramInputStream);
      CloseableReference.closeSafely((CloseableReference)localObject2);
      return localEncodedImage;
      label75: Closeables.closeQuietly(paramInputStream);
      CloseableReference.closeSafely(localObject1);
      throw ((Throwable)localObject2);
      label86: localObject2 = localObject1;
    }
    finally
    {
      break label75;
    }
  }

  protected abstract EncodedImage getEncodedImage(ImageRequest paramImageRequest)
    throws IOException;

  protected EncodedImage getEncodedImage(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    return getByteBufferBackedEncodedImage(paramInputStream, paramInt);
  }

  protected abstract String getProducerName();

  public void produceResults(final Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    final ProducerListener localProducerListener = paramProducerContext.getListener();
    final String str = paramProducerContext.getId();
    final ImageRequest localImageRequest = paramProducerContext.getImageRequest();
    paramConsumer = new StatefulProducerRunnable(paramConsumer, localProducerListener, getProducerName(), str)
    {
      protected void disposeResult(EncodedImage paramAnonymousEncodedImage)
      {
        EncodedImage.closeSafely(paramAnonymousEncodedImage);
      }

      protected EncodedImage getResult()
        throws Exception
      {
        EncodedImage localEncodedImage = LocalFetchProducer.this.getEncodedImage(localImageRequest);
        if (localEncodedImage == null)
        {
          localProducerListener.onUltimateProducerReached(str, LocalFetchProducer.this.getProducerName(), false);
          return null;
        }
        localEncodedImage.parseMetaData();
        localProducerListener.onUltimateProducerReached(str, LocalFetchProducer.this.getProducerName(), true);
        return localEncodedImage;
      }
    };
    paramProducerContext.addCallbacks(new BaseProducerContextCallbacks()
    {
      public void onCancellationRequested()
      {
        paramConsumer.cancel();
      }
    });
    this.mExecutor.execute(paramConsumer);
  }
}