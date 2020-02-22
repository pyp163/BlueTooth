package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Pair<F, S>
{

  @Nullable
  public final F first;

  @Nullable
  public final S second;

  public Pair(@Nullable F paramF, @Nullable S paramS)
  {
    this.first = paramF;
    this.second = paramS;
  }

  @NonNull
  public static <A, B> Pair<A, B> create(@Nullable A paramA, @Nullable B paramB)
  {
    return new Pair(paramA, paramB);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Pair;
    boolean bool2 = false;
    if (!bool1)
      return false;
    paramObject = (Pair)paramObject;
    bool1 = bool2;
    if (ObjectsCompat.equals(paramObject.first, this.first))
    {
      bool1 = bool2;
      if (ObjectsCompat.equals(paramObject.second, this.second))
        bool1 = true;
    }
    return bool1;
  }

  public int hashCode()
  {
    Object localObject = this.first;
    int j = 0;
    int i;
    if (localObject == null)
      i = 0;
    else
      i = this.first.hashCode();
    if (this.second != null)
      j = this.second.hashCode();
    return i ^ j;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Pair{");
    localStringBuilder.append(String.valueOf(this.first));
    localStringBuilder.append(" ");
    localStringBuilder.append(String.valueOf(this.second));
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}