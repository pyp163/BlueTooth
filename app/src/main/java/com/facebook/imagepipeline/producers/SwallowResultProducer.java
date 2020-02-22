package com.facebook.imagepipeline.producers;

public class SwallowResultProducer<T>
  implements Producer<Void>
{
  private final Producer<T> mInputProducer;

  public SwallowResultProducer(Producer<T> paramProducer)
  {
    this.mInputProducer = paramProducer;
  }

  public void produceResults(Consumer<Void> paramConsumer, ProducerContext paramProducerContext)
  {
    paramConsumer = new DelegatingConsumer(paramConsumer)
    {
      protected void onNewResultImpl(T paramAnonymousT, int paramAnonymousInt)
      {
        if (isLast(paramAnonymousInt))
          getConsumer().onNewResult(null, paramAnonymousInt);
      }
    };
    this.mInputProducer.produceResults(paramConsumer, paramProducerContext);
  }
}