package com.facebook.imagepipeline.producers;

import java.util.Map;
import javax.annotation.Nullable;

public abstract interface ProducerListener
{
  public abstract void onProducerEvent(String paramString1, String paramString2, String paramString3);

  public abstract void onProducerFinishWithCancellation(String paramString1, String paramString2, @Nullable Map<String, String> paramMap);

  public abstract void onProducerFinishWithFailure(String paramString1, String paramString2, Throwable paramThrowable, @Nullable Map<String, String> paramMap);

  public abstract void onProducerFinishWithSuccess(String paramString1, String paramString2, @Nullable Map<String, String> paramMap);

  public abstract void onProducerStart(String paramString1, String paramString2);

  public abstract void onUltimateProducerReached(String paramString1, String paramString2, boolean paramBoolean);

  public abstract boolean requiresExtraMap(String paramString);
}