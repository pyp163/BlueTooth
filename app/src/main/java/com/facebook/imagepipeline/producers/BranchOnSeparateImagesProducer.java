package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class BranchOnSeparateImagesProducer
  implements Producer<EncodedImage>
{
  private final Producer<EncodedImage> mInputProducer1;
  private final Producer<EncodedImage> mInputProducer2;

  public BranchOnSeparateImagesProducer(Producer<EncodedImage> paramProducer1, Producer<EncodedImage> paramProducer2)
  {
    this.mInputProducer1 = paramProducer1;
    this.mInputProducer2 = paramProducer2;
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    paramConsumer = new OnFirstImageConsumer(paramConsumer, paramProducerContext, null);
    this.mInputProducer1.produceResults(paramConsumer, paramProducerContext);
  }

  private class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private ProducerContext mProducerContext;

    private OnFirstImageConsumer(ProducerContext arg2)
    {
      super();
      Object localObject;
      this.mProducerContext = localObject;
    }

    protected void onFailureImpl(Throwable paramThrowable)
    {
      BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
    }

    protected void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      ImageRequest localImageRequest = this.mProducerContext.getImageRequest();
      boolean bool1 = isLast(paramInt);
      boolean bool2 = ThumbnailSizeChecker.isImageBigEnough(paramEncodedImage, localImageRequest.getResizeOptions());
      if ((paramEncodedImage != null) && ((bool2) || (localImageRequest.getLocalThumbnailPreviewsEnabled())))
        if ((bool1) && (bool2))
        {
          getConsumer().onNewResult(paramEncodedImage, paramInt);
        }
        else
        {
          paramInt = turnOffStatusFlag(paramInt, 1);
          getConsumer().onNewResult(paramEncodedImage, paramInt);
        }
      if ((bool1) && (!bool2))
      {
        EncodedImage.closeSafely(paramEncodedImage);
        BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
      }
    }
  }
}