package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
public class SharedReference<T>
{

  @GuardedBy("itself")
  private static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();

  @GuardedBy("this")
  private int mRefCount;
  private final ResourceReleaser<T> mResourceReleaser;

  @GuardedBy("this")
  private T mValue;

  public SharedReference(T paramT, ResourceReleaser<T> paramResourceReleaser)
  {
    this.mValue = Preconditions.checkNotNull(paramT);
    this.mResourceReleaser = ((ResourceReleaser)Preconditions.checkNotNull(paramResourceReleaser));
    this.mRefCount = 1;
    addLiveReference(paramT);
  }

  private static void addLiveReference(Object paramObject)
  {
    synchronized (sLiveObjects)
    {
      Integer localInteger = (Integer)sLiveObjects.get(paramObject);
      if (localInteger == null)
        sLiveObjects.put(paramObject, Integer.valueOf(1));
      else
        sLiveObjects.put(paramObject, Integer.valueOf(localInteger.intValue() + 1));
      return;
    }
  }

  private int decreaseRefCount()
  {
    while (true)
    {
      try
      {
        ensureValid();
        if (this.mRefCount > 0)
        {
          bool = true;
          Preconditions.checkArgument(bool);
          this.mRefCount -= 1;
          int i = this.mRefCount;
          return i;
        }
      }
      finally
      {
      }
      boolean bool = false;
    }
  }

  private void ensureValid()
  {
    if (!isValid(this))
      throw new NullReferenceException();
  }

  public static boolean isValid(SharedReference<?> paramSharedReference)
  {
    return (paramSharedReference != null) && (paramSharedReference.isValid());
  }

  private static void removeLiveReference(Object paramObject)
  {
    synchronized (sLiveObjects)
    {
      Integer localInteger = (Integer)sLiveObjects.get(paramObject);
      if (localInteger == null)
        FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", new Object[] { paramObject.getClass() });
      else if (localInteger.intValue() == 1)
        sLiveObjects.remove(paramObject);
      else
        sLiveObjects.put(paramObject, Integer.valueOf(localInteger.intValue() - 1));
      return;
    }
  }

  public void addReference()
  {
    try
    {
      ensureValid();
      this.mRefCount += 1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void deleteReference()
  {
    if (decreaseRefCount() == 0)
      try
      {
        Object localObject1 = this.mValue;
        this.mValue = null;
        this.mResourceReleaser.release(localObject1);
        removeLiveReference(localObject1);
        return;
      }
      finally
      {
      }
  }

  public T get()
  {
    try
    {
      Object localObject1 = this.mValue;
      return localObject1;
    }
    finally
    {
      localObject2 = finally;
      throw localObject2;
    }
  }

  public int getRefCountTestOnly()
  {
    try
    {
      int i = this.mRefCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isValid()
  {
    try
    {
      int i = this.mRefCount;
      boolean bool;
      if (i > 0)
        bool = true;
      else
        bool = false;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static class NullReferenceException extends RuntimeException
  {
    public NullReferenceException()
    {
      super();
    }
  }
}