package org.jdeferred.multiple;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MultipleResults
  implements Iterable<OneResult>
{
  private final List<OneResult> results;

  public MultipleResults(int paramInt)
  {
    this.results = new CopyOnWriteArrayList(new OneResult[paramInt]);
  }

  public OneResult get(int paramInt)
  {
    return (OneResult)this.results.get(paramInt);
  }

  public Iterator<OneResult> iterator()
  {
    return this.results.iterator();
  }

  protected void set(int paramInt, OneResult paramOneResult)
  {
    this.results.set(paramInt, paramOneResult);
  }

  public int size()
  {
    return this.results.size();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MultipleResults [results=");
    localStringBuilder.append(this.results);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}