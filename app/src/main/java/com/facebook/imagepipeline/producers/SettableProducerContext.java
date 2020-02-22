package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SettableProducerContext extends BaseProducerContext
{
  public SettableProducerContext(ProducerContext paramProducerContext)
  {
    this(paramProducerContext.getImageRequest(), paramProducerContext.getId(), paramProducerContext.getListener(), paramProducerContext.getCallerContext(), paramProducerContext.getLowestPermittedRequestLevel(), paramProducerContext.isPrefetch(), paramProducerContext.isIntermediateResultExpected(), paramProducerContext.getPriority());
  }

  public SettableProducerContext(ImageRequest paramImageRequest, ProducerContext paramProducerContext)
  {
    this(paramImageRequest, paramProducerContext.getId(), paramProducerContext.getListener(), paramProducerContext.getCallerContext(), paramProducerContext.getLowestPermittedRequestLevel(), paramProducerContext.isPrefetch(), paramProducerContext.isIntermediateResultExpected(), paramProducerContext.getPriority());
  }

  public SettableProducerContext(ImageRequest paramImageRequest, String paramString, ProducerListener paramProducerListener, Object paramObject, ImageRequest.RequestLevel paramRequestLevel, boolean paramBoolean1, boolean paramBoolean2, Priority paramPriority)
  {
    super(paramImageRequest, paramString, paramProducerListener, paramObject, paramRequestLevel, paramBoolean1, paramBoolean2, paramPriority);
  }

  public void setIsIntermediateResultExpected(boolean paramBoolean)
  {
    BaseProducerContext.callOnIsIntermediateResultExpectedChanged(setIsIntermediateResultExpectedNoCallbacks(paramBoolean));
  }

  public void setIsPrefetch(boolean paramBoolean)
  {
    BaseProducerContext.callOnIsPrefetchChanged(setIsPrefetchNoCallbacks(paramBoolean));
  }

  public void setPriority(Priority paramPriority)
  {
    BaseProducerContext.callOnPriorityChanged(setPriorityNoCallbacks(paramPriority));
  }
}