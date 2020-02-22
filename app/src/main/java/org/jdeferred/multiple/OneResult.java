package org.jdeferred.multiple;

import org.jdeferred.Promise;

public class OneResult
{
  private final int index;
  private final Promise promise;
  private final Object result;

  public OneResult(int paramInt, Promise paramPromise, Object paramObject)
  {
    this.index = paramInt;
    this.promise = paramPromise;
    this.result = paramObject;
  }

  public int getIndex()
  {
    return this.index;
  }

  public Promise getPromise()
  {
    return this.promise;
  }

  public Object getResult()
  {
    return this.result;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneResult [index=");
    localStringBuilder.append(this.index);
    localStringBuilder.append(", promise=");
    localStringBuilder.append(this.promise);
    localStringBuilder.append(", result=");
    localStringBuilder.append(this.result);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}