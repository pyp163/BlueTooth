package com.facebook.imagepipeline.producers;

public class NullProducer<T>
  implements Producer<T>
{
  public void produceResults(Consumer<T> paramConsumer, ProducerContext paramProducerContext)
  {
    paramConsumer.onNewResult(null, 1);
  }
}