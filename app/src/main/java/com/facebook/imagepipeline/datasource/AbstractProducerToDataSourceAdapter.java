package com.facebook.imagepipeline.datasource;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.request.HasImageRequest;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class AbstractProducerToDataSourceAdapter<T> extends AbstractDataSource<T>
  implements HasImageRequest
{
  private final RequestListener mRequestListener;
  private final SettableProducerContext mSettableProducerContext;

  protected AbstractProducerToDataSourceAdapter(Producer<T> paramProducer, SettableProducerContext paramSettableProducerContext, RequestListener paramRequestListener)
  {
    this.mSettableProducerContext = paramSettableProducerContext;
    this.mRequestListener = paramRequestListener;
    this.mRequestListener.onRequestStart(paramSettableProducerContext.getImageRequest(), this.mSettableProducerContext.getCallerContext(), this.mSettableProducerContext.getId(), this.mSettableProducerContext.isPrefetch());
    paramProducer.produceResults(createConsumer(), paramSettableProducerContext);
  }

  private Consumer<T> createConsumer()
  {
    return new BaseConsumer()
    {
      protected void onCancellationImpl()
      {
        AbstractProducerToDataSourceAdapter.this.onCancellationImpl();
      }

      protected void onFailureImpl(Throwable paramAnonymousThrowable)
      {
        AbstractProducerToDataSourceAdapter.this.onFailureImpl(paramAnonymousThrowable);
      }

      protected void onNewResultImpl(@Nullable T paramAnonymousT, int paramAnonymousInt)
      {
        AbstractProducerToDataSourceAdapter.this.onNewResultImpl(paramAnonymousT, paramAnonymousInt);
      }

      protected void onProgressUpdateImpl(float paramAnonymousFloat)
      {
        AbstractProducerToDataSourceAdapter.this.setProgress(paramAnonymousFloat);
      }
    };
  }

  private void onCancellationImpl()
  {
    try
    {
      Preconditions.checkState(isClosed());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void onFailureImpl(Throwable paramThrowable)
  {
    if (super.setFailure(paramThrowable))
      this.mRequestListener.onRequestFailure(this.mSettableProducerContext.getImageRequest(), this.mSettableProducerContext.getId(), paramThrowable, this.mSettableProducerContext.isPrefetch());
  }

  public boolean close()
  {
    if (!super.close())
      return false;
    if (!super.isFinished())
    {
      this.mRequestListener.onRequestCancellation(this.mSettableProducerContext.getId());
      this.mSettableProducerContext.cancel();
    }
    return true;
  }

  public ImageRequest getImageRequest()
  {
    return this.mSettableProducerContext.getImageRequest();
  }

  protected void onNewResultImpl(@Nullable T paramT, int paramInt)
  {
    boolean bool = BaseConsumer.isLast(paramInt);
    if ((super.setResult(paramT, bool)) && (bool))
      this.mRequestListener.onRequestSuccess(this.mSettableProducerContext.getImageRequest(), this.mSettableProducerContext.getId(), this.mSettableProducerContext.isPrefetch());
  }
}