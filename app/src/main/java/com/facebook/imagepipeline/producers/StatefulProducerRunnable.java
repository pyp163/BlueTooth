package com.facebook.imagepipeline.producers;

import com.facebook.common.executors.StatefulRunnable;
import java.util.Map;

public abstract class StatefulProducerRunnable<T> extends StatefulRunnable<T>
{
  private final Consumer<T> mConsumer;
  private final ProducerListener mProducerListener;
  private final String mProducerName;
  private final String mRequestId;

  public StatefulProducerRunnable(Consumer<T> paramConsumer, ProducerListener paramProducerListener, String paramString1, String paramString2)
  {
    this.mConsumer = paramConsumer;
    this.mProducerListener = paramProducerListener;
    this.mProducerName = paramString1;
    this.mRequestId = paramString2;
    this.mProducerListener.onProducerStart(this.mRequestId, this.mProducerName);
  }

  protected abstract void disposeResult(T paramT);

  protected Map<String, String> getExtraMapOnCancellation()
  {
    return null;
  }

  protected Map<String, String> getExtraMapOnFailure(Exception paramException)
  {
    return null;
  }

  protected Map<String, String> getExtraMapOnSuccess(T paramT)
  {
    return null;
  }

  protected void onCancellation()
  {
    ProducerListener localProducerListener = this.mProducerListener;
    String str1 = this.mRequestId;
    String str2 = this.mProducerName;
    Map localMap;
    if (this.mProducerListener.requiresExtraMap(this.mRequestId))
      localMap = getExtraMapOnCancellation();
    else
      localMap = null;
    localProducerListener.onProducerFinishWithCancellation(str1, str2, localMap);
    this.mConsumer.onCancellation();
  }

  protected void onFailure(Exception paramException)
  {
    ProducerListener localProducerListener = this.mProducerListener;
    String str1 = this.mRequestId;
    String str2 = this.mProducerName;
    Map localMap;
    if (this.mProducerListener.requiresExtraMap(this.mRequestId))
      localMap = getExtraMapOnFailure(paramException);
    else
      localMap = null;
    localProducerListener.onProducerFinishWithFailure(str1, str2, paramException, localMap);
    this.mConsumer.onFailure(paramException);
  }

  protected void onSuccess(T paramT)
  {
    ProducerListener localProducerListener = this.mProducerListener;
    String str1 = this.mRequestId;
    String str2 = this.mProducerName;
    Map localMap;
    if (this.mProducerListener.requiresExtraMap(this.mRequestId))
      localMap = getExtraMapOnSuccess(paramT);
    else
      localMap = null;
    localProducerListener.onProducerFinishWithSuccess(str1, str2, localMap);
    this.mConsumer.onNewResult(paramT, 1);
  }
}