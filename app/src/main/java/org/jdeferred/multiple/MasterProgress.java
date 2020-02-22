package org.jdeferred.multiple;

public class MasterProgress
{
  private final int done;
  private final int fail;
  private final int total;

  public MasterProgress(int paramInt1, int paramInt2, int paramInt3)
  {
    this.done = paramInt1;
    this.fail = paramInt2;
    this.total = paramInt3;
  }

  public int getDone()
  {
    return this.done;
  }

  public int getFail()
  {
    return this.fail;
  }

  public int getTotal()
  {
    return this.total;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MasterProgress [done=");
    localStringBuilder.append(this.done);
    localStringBuilder.append(", fail=");
    localStringBuilder.append(this.fail);
    localStringBuilder.append(", total=");
    localStringBuilder.append(this.total);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}