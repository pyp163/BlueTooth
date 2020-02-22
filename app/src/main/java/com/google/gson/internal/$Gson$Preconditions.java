package com.google.gson.internal;

public final class $Gson$Preconditions
{
  private $Gson$Preconditions()
  {
    throw new UnsupportedOperationException();
  }

  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    return paramT;
  }
}