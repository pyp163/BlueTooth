package org.jdeferred.multiple;

import org.jdeferred.Promise;

public class OneReject
{
  private final int index;
  private final Promise promise;
  private final Object reject;

  public OneReject(int paramInt, Promise paramPromise, Object paramObject)
  {
    this.index = paramInt;
    this.promise = paramPromise;
    this.reject = paramObject;
  }

  public int getIndex()
  {
    return this.index;
  }

  public Promise getPromise()
  {
    return this.promise;
  }

  public Object getReject()
  {
    return this.reject;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneReject [index=");
    localStringBuilder.append(this.index);
    localStringBuilder.append(", promise=");
    localStringBuilder.append(this.promise);
    localStringBuilder.append(", reject=");
    localStringBuilder.append(this.reject);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}