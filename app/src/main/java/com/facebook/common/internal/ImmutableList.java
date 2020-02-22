package com.facebook.common.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableList<E> extends ArrayList<E>
{
  private ImmutableList(int paramInt)
  {
    super(paramInt);
  }

  private ImmutableList(List<E> paramList)
  {
    super(paramList);
  }

  public static <E> ImmutableList<E> copyOf(List<E> paramList)
  {
    return new ImmutableList(paramList);
  }

  public static <E> ImmutableList<E> of(E[] paramArrayOfE)
  {
    ImmutableList localImmutableList = new ImmutableList(paramArrayOfE.length);
    Collections.addAll(localImmutableList, paramArrayOfE);
    return localImmutableList;
  }
}