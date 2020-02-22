package com.facebook.imagepipeline.producers;

public abstract interface Producer<T>
{
  public abstract void produceResults(Consumer<T> paramConsumer, ProducerContext paramProducerContext);
}