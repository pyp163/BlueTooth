package com.facebook.common.internal;

public class Suppliers
{
  public static final Supplier<Boolean> BOOLEAN_FALSE = new Supplier()
  {
    public Boolean get()
    {
      return Boolean.valueOf(false);
    }
  };
  public static final Supplier<Boolean> BOOLEAN_TRUE = new Supplier()
  {
    public Boolean get()
    {
      return Boolean.valueOf(true);
    }
  };

  public static <T> Supplier<T> of(T paramT)
  {
    return new Supplier()
    {
      public T get()
      {
        return this.val$instance;
      }
    };
  }
}