package android.support.v7.util;

import android.support.annotation.Nullable;

public abstract interface ListUpdateCallback
{
  public abstract void onChanged(int paramInt1, int paramInt2, @Nullable Object paramObject);

  public abstract void onInserted(int paramInt1, int paramInt2);

  public abstract void onMoved(int paramInt1, int paramInt2);

  public abstract void onRemoved(int paramInt1, int paramInt2);
}