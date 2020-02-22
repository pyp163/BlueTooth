package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache<K, V>
{
  private int createCount;
  private int evictionCount;
  private int hitCount;
  private final LinkedHashMap<K, V> map;
  private int maxSize;
  private int missCount;
  private int putCount;
  private int size;

  public LruCache(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    this.maxSize = paramInt;
    this.map = new LinkedHashMap(0, 0.75F, true);
  }

  private int safeSizeOf(K paramK, V paramV)
  {
    int i = sizeOf(paramK, paramV);
    if (i < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Negative size: ");
      localStringBuilder.append(paramK);
      localStringBuilder.append("=");
      localStringBuilder.append(paramV);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return i;
  }

  @Nullable
  protected V create(@NonNull K paramK)
  {
    return null;
  }

  public final int createCount()
  {
    try
    {
      int i = this.createCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void entryRemoved(boolean paramBoolean, @NonNull K paramK, @NonNull V paramV1, @Nullable V paramV2)
  {
  }

  public final void evictAll()
  {
    trimToSize(-1);
  }

  public final int evictionCount()
  {
    try
    {
      int i = this.evictionCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public final V get(@NonNull K paramK)
  {
    if (paramK == null)
      throw new NullPointerException("key == null");
    try
    {
      Object localObject1 = this.map.get(paramK);
      if (localObject1 != null)
      {
        this.hitCount += 1;
        return localObject1;
      }
      this.missCount += 1;
      localObject1 = create(paramK);
      if (localObject1 == null)
        return null;
      try
      {
        this.createCount += 1;
        Object localObject2 = this.map.put(paramK, localObject1);
        if (localObject2 != null)
          this.map.put(paramK, localObject2);
        else
          this.size += safeSizeOf(paramK, localObject1);
        if (localObject2 != null)
        {
          entryRemoved(false, paramK, localObject1, localObject2);
          return localObject2;
        }
        trimToSize(this.maxSize);
        return localObject1;
      }
      finally
      {
      }
    }
    finally
    {
    }
    throw paramK;
  }

  public final int hitCount()
  {
    try
    {
      int i = this.hitCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final int maxSize()
  {
    try
    {
      int i = this.maxSize;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final int missCount()
  {
    try
    {
      int i = this.missCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public final V put(@NonNull K paramK, @NonNull V paramV)
  {
    if ((paramK != null) && (paramV != null))
      try
      {
        this.putCount += 1;
        this.size += safeSizeOf(paramK, paramV);
        Object localObject = this.map.put(paramK, paramV);
        if (localObject != null)
          this.size -= safeSizeOf(paramK, localObject);
        if (localObject != null)
          entryRemoved(false, paramK, localObject, paramV);
        trimToSize(this.maxSize);
        return localObject;
      }
      finally
      {
      }
    throw new NullPointerException("key == null || value == null");
  }

  public final int putCount()
  {
    try
    {
      int i = this.putCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public final V remove(@NonNull K paramK)
  {
    if (paramK == null)
      throw new NullPointerException("key == null");
    try
    {
      Object localObject = this.map.remove(paramK);
      if (localObject != null)
        this.size -= safeSizeOf(paramK, localObject);
      if (localObject != null)
        entryRemoved(false, paramK, localObject, null);
      return localObject;
    }
    finally
    {
    }
    throw paramK;
  }

  public void resize(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    try
    {
      this.maxSize = paramInt;
      trimToSize(paramInt);
      return;
    }
    finally
    {
    }
  }

  public final int size()
  {
    try
    {
      int i = this.size;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected int sizeOf(@NonNull K paramK, @NonNull V paramV)
  {
    return 1;
  }

  public final Map<K, V> snapshot()
  {
    try
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(this.map);
      return localLinkedHashMap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final String toString()
  {
    while (true)
    {
      try
      {
        i = this.hitCount + this.missCount;
        if (i != 0)
        {
          i = this.hitCount * 100 / i;
          String str = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i) });
          return str;
        }
      }
      finally
      {
      }
      int i = 0;
    }
  }

  public void trimToSize(int paramInt)
  {
    while (true)
      try
      {
        if ((this.size >= 0) && ((!this.map.isEmpty()) || (this.size == 0)))
        {
          if ((this.size > paramInt) && (!this.map.isEmpty()))
          {
            Object localObject3 = (Map.Entry)this.map.entrySet().iterator().next();
            localObject1 = ((Map.Entry)localObject3).getKey();
            localObject3 = ((Map.Entry)localObject3).getValue();
            this.map.remove(localObject1);
            this.size -= safeSizeOf(localObject1, localObject3);
            this.evictionCount += 1;
            entryRemoved(true, localObject1, localObject3, null);
            continue;
          }
          return;
        }
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(getClass().getName());
        ((StringBuilder)localObject1).append(".sizeOf() is reporting inconsistent results!");
        throw new IllegalStateException(((StringBuilder)localObject1).toString());
      }
      finally
      {
      }
  }
}