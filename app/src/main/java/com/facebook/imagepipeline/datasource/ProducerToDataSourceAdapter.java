package com.facebook.imagepipeline.datasource;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<T>
{
  private ProducerToDataSourceAdapter(Producer<T> paramProducer, SettableProducerContext paramSettableProducerContext, RequestListener paramRequestListener)
  {
    super(paramProducer, paramSettableProducerContext, paramRequestListener);
  }

  public static <T> DataSource<T> create(Producer<T> paramProducer, SettableProducerContext paramSettableProducerContext, RequestListener paramRequestListener)
  {
    return new ProducerToDataSourceAdapter(paramProducer, paramSettableProducerContext, paramRequestListener);
  }
}