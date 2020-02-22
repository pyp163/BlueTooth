package com.facebook.common.internal;

public class Ints
{
  public static int max(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 1;
    boolean bool;
    if (j > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    int k;
    for (j = paramArrayOfInt[0]; i < paramArrayOfInt.length; j = k)
    {
      k = j;
      if (paramArrayOfInt[i] > j)
        k = paramArrayOfInt[i];
      i += 1;
    }
    return j;
  }
}