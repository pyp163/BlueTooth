package org.jdeferred.multiple;

import org.jdeferred.Promise;

public class OneProgress extends MasterProgress
{
  private final int index;
  private final Object progress;
  private final Promise promise;

  public OneProgress(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Promise paramPromise, Object paramObject)
  {
    super(paramInt1, paramInt2, paramInt3);
    this.index = paramInt4;
    this.promise = paramPromise;
    this.progress = paramObject;
  }

  public int getIndex()
  {
    return this.index;
  }

  public Object getProgress()
  {
    return this.progress;
  }

  public Promise getPromise()
  {
    return this.promise;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneProgress [index=");
    localStringBuilder.append(this.index);
    localStringBuilder.append(", promise=");
    localStringBuilder.append(this.promise);
    localStringBuilder.append(", progress=");
    localStringBuilder.append(this.progress);
    localStringBuilder.append(", getDone()=");
    localStringBuilder.append(getDone());
    localStringBuilder.append(", getFail()=");
    localStringBuilder.append(getFail());
    localStringBuilder.append(", getTotal()=");
    localStringBuilder.append(getTotal());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}