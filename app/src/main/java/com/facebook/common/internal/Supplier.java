package com.facebook.common.internal;

public abstract interface Supplier<T>
{
  public abstract T get();
}