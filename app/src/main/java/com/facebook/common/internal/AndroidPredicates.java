package com.facebook.common.internal;

public class AndroidPredicates
{
  public static <T> Predicate<T> False()
  {
    return new Predicate()
    {
      public boolean apply(T paramAnonymousT)
      {
        return false;
      }
    };
  }

  public static <T> Predicate<T> True()
  {
    return new Predicate()
    {
      public boolean apply(T paramAnonymousT)
      {
        return true;
      }
    };
  }
}