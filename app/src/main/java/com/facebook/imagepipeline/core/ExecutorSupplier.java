package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;

public abstract interface ExecutorSupplier
{
  public abstract Executor forBackgroundTasks();

  public abstract Executor forDecode();

  public abstract Executor forLightweightBackgroundTasks();

  public abstract Executor forLocalStorageRead();

  public abstract Executor forLocalStorageWrite();
}