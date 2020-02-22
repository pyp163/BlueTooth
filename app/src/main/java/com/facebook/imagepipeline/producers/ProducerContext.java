package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;

public abstract interface ProducerContext
{
  public abstract void addCallbacks(ProducerContextCallbacks paramProducerContextCallbacks);

  public abstract Object getCallerContext();

  public abstract String getId();

  public abstract ImageRequest getImageRequest();

  public abstract ProducerListener getListener();

  public abstract ImageRequest.RequestLevel getLowestPermittedRequestLevel();

  public abstract Priority getPriority();

  public abstract boolean isIntermediateResultExpected();

  public abstract boolean isPrefetch();
}