package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CountingLruMap<K, V>
{

  @GuardedBy("this")
  private final LinkedHashMap<K, V> mMap = new LinkedHashMap();

  @GuardedBy("this")
  private int mSizeInBytes = 0;
  private final ValueDescriptor<V> mValueDescriptor;

  public CountingLruMap(ValueDescriptor<V> paramValueDescriptor)
  {
    this.mValueDescriptor = paramValueDescriptor;
  }

  private int getValueSizeInBytes(V paramV)
  {
    if (paramV == null)
      return 0;
    return this.mValueDescriptor.getSizeInBytes(paramV);
  }

  public ArrayList<V> clear()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.mMap.values());
      this.mMap.clear();
      this.mSizeInBytes = 0;
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean contains(K paramK)
  {
    try
    {
      boolean bool = this.mMap.containsKey(paramK);
      return bool;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  @Nullable
  public V get(K paramK)
  {
    try
    {
      paramK = this.mMap.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  public int getCount()
  {
    try
    {
      int i = this.mMap.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public K getFirstKey()
  {
    try
    {
      Object localObject1;
      if (this.mMap.isEmpty())
        localObject1 = null;
      else
        localObject1 = this.mMap.keySet().iterator().next();
      return localObject1;
    }
    finally
    {
    }
  }

  @VisibleForTesting
  ArrayList<K> getKeys()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.mMap.keySet());
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public ArrayList<Map.Entry<K, V>> getMatchingEntries(@Nullable Predicate<K> paramPredicate)
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.mMap.entrySet().size());
      Iterator localIterator = this.mMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if ((paramPredicate == null) || (paramPredicate.apply(localEntry.getKey())))
          localArrayList.add(localEntry);
      }
      return localArrayList;
    }
    finally
    {
    }
    throw paramPredicate;
  }

  public int getSizeInBytes()
  {
    try
    {
      int i = this.mSizeInBytes;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @VisibleForTesting
  ArrayList<V> getValues()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.mMap.values());
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public V put(K paramK, V paramV)
  {
    try
    {
      Object localObject = this.mMap.remove(paramK);
      this.mSizeInBytes -= getValueSizeInBytes(localObject);
      this.mMap.put(paramK, paramV);
      this.mSizeInBytes += getValueSizeInBytes(paramV);
      return localObject;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  @Nullable
  public V remove(K paramK)
  {
    try
    {
      paramK = this.mMap.remove(paramK);
      this.mSizeInBytes -= getValueSizeInBytes(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  public ArrayList<V> removeAll(@Nullable Predicate<K> paramPredicate)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.mMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if ((paramPredicate == null) || (paramPredicate.apply(localEntry.getKey())))
        {
          localArrayList.add(localEntry.getValue());
          this.mSizeInBytes -= getValueSizeInBytes(localEntry.getValue());
          localIterator.remove();
        }
      }
      return localArrayList;
    }
    finally
    {
    }
    throw paramPredicate;
  }
}