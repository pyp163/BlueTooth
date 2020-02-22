package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CloseableProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<CloseableReference<T>>
{
  private CloseableProducerToDataSourceAdapter(Producer<CloseableReference<T>> paramProducer, SettableProducerContext paramSettableProducerContext, RequestListener paramRequestListener)
  {
    super(paramProducer, paramSettableProducerContext, paramRequestListener);
  }

  public static <T> DataSource<CloseableReference<T>> create(Producer<CloseableReference<T>> paramProducer, SettableProducerContext paramSettableProducerContext, RequestListener paramRequestListener)
  {
    return new CloseableProducerToDataSourceAdapter(paramProducer, paramSettableProducerContext, paramRequestListener);
  }

  protected void closeResult(CloseableReference<T> paramCloseableReference)
  {
    CloseableReference.closeSafely(paramCloseableReference);
  }

  @Nullable
  public CloseableReference<T> getResult()
  {
    return CloseableReference.cloneOrNull((CloseableReference)super.getResult());
  }

  protected void onNewResultImpl(CloseableReference<T> paramCloseableReference, int paramInt)
  {
    super.onNewResultImpl(CloseableReference.cloneOrNull(paramCloseableReference), paramInt);
  }
}