package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class ThumbnailBranchProducer
  implements Producer<EncodedImage>
{
  private final ThumbnailProducer<EncodedImage>[] mThumbnailProducers;

  public ThumbnailBranchProducer(ThumbnailProducer<EncodedImage>[] paramArrayOfThumbnailProducer)
  {
    this.mThumbnailProducers = ((ThumbnailProducer[])Preconditions.checkNotNull(paramArrayOfThumbnailProducer));
    Preconditions.checkElementIndex(0, this.mThumbnailProducers.length);
  }

  private int findFirstProducerForSize(int paramInt, ResizeOptions paramResizeOptions)
  {
    while (paramInt < this.mThumbnailProducers.length)
    {
      if (this.mThumbnailProducers[paramInt].canProvideImageForSize(paramResizeOptions))
        return paramInt;
      paramInt += 1;
    }
    return -1;
  }

  private boolean produceResultsFromThumbnailProducer(int paramInt, Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    paramInt = findFirstProducerForSize(paramInt, paramProducerContext.getImageRequest().getResizeOptions());
    if (paramInt == -1)
      return false;
    this.mThumbnailProducers[paramInt].produceResults(new ThumbnailConsumer(paramConsumer, paramProducerContext, paramInt), paramProducerContext);
    return true;
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    if (paramProducerContext.getImageRequest().getResizeOptions() == null)
    {
      paramConsumer.onNewResult(null, 1);
      return;
    }
    if (!produceResultsFromThumbnailProducer(0, paramConsumer, paramProducerContext))
      paramConsumer.onNewResult(null, 1);
  }

  private class ThumbnailConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private final ProducerContext mProducerContext;
    private final int mProducerIndex;
    private final ResizeOptions mResizeOptions;

    public ThumbnailConsumer(ProducerContext paramInt, int arg3)
    {
      super();
      Object localObject;
      this.mProducerContext = localObject;
      int i;
      this.mProducerIndex = i;
      this.mResizeOptions = this.mProducerContext.getImageRequest().getResizeOptions();
    }

    protected void onFailureImpl(Throwable paramThrowable)
    {
      if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext))
        getConsumer().onFailure(paramThrowable);
    }

    protected void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      if ((paramEncodedImage != null) && ((isNotLast(paramInt)) || (ThumbnailSizeChecker.isImageBigEnough(paramEncodedImage, this.mResizeOptions))))
      {
        getConsumer().onNewResult(paramEncodedImage, paramInt);
        return;
      }
      if (isLast(paramInt))
      {
        EncodedImage.closeSafely(paramEncodedImage);
        if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext))
          getConsumer().onNewResult(null, 1);
      }
    }
  }
}