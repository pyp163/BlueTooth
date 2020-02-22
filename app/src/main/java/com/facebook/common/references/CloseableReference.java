package com.facebook.common.references;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class CloseableReference<T>
  implements Cloneable, Closeable
{
  private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser()
  {
    public void release(Closeable paramAnonymousCloseable)
    {
      try
      {
        Closeables.close(paramAnonymousCloseable, true);
        return;
      }
      catch (IOException paramAnonymousCloseable)
      {
      }
    }
  };
  private static Class<CloseableReference> TAG = CloseableReference.class;

  @GuardedBy("this")
  private boolean mIsClosed = false;
  private final SharedReference<T> mSharedReference;

  private CloseableReference(SharedReference<T> paramSharedReference)
  {
    this.mSharedReference = ((SharedReference)Preconditions.checkNotNull(paramSharedReference));
    paramSharedReference.addReference();
  }

  private CloseableReference(T paramT, ResourceReleaser<T> paramResourceReleaser)
  {
    this.mSharedReference = new SharedReference(paramT, paramResourceReleaser);
  }

  @Nullable
  public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> paramCloseableReference)
  {
    if (paramCloseableReference != null)
      return paramCloseableReference.cloneOrNull();
    return null;
  }

  public static <T> List<CloseableReference<T>> cloneOrNull(@PropagatesNullable Collection<CloseableReference<T>> paramCollection)
  {
    if (paramCollection == null)
      return null;
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
      localArrayList.add(cloneOrNull((CloseableReference)paramCollection.next()));
    return localArrayList;
  }

  public static void closeSafely(@Nullable CloseableReference<?> paramCloseableReference)
  {
    if (paramCloseableReference != null)
      paramCloseableReference.close();
  }

  public static void closeSafely(@Nullable Iterable<? extends CloseableReference<?>> paramIterable)
  {
    if (paramIterable != null)
    {
      paramIterable = paramIterable.iterator();
      while (paramIterable.hasNext())
        closeSafely((CloseableReference)paramIterable.next());
    }
  }

  public static boolean isValid(@Nullable CloseableReference<?> paramCloseableReference)
  {
    return (paramCloseableReference != null) && (paramCloseableReference.isValid());
  }

  public static <T extends Closeable> CloseableReference<T> of(@PropagatesNullable T paramT)
  {
    if (paramT == null)
      return null;
    return new CloseableReference(paramT, DEFAULT_CLOSEABLE_RELEASER);
  }

  public static <T> CloseableReference<T> of(@PropagatesNullable T paramT, ResourceReleaser<T> paramResourceReleaser)
  {
    if (paramT == null)
      return null;
    return new CloseableReference(paramT, paramResourceReleaser);
  }

  public CloseableReference<T> clone()
  {
    try
    {
      Preconditions.checkState(isValid());
      CloseableReference localCloseableReference = new CloseableReference(this.mSharedReference);
      return localCloseableReference;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public CloseableReference<T> cloneOrNull()
  {
    try
    {
      if (isValid())
      {
        CloseableReference localCloseableReference = clone();
        return localCloseableReference;
      }
      return null;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void close()
  {
    try
    {
      if (this.mIsClosed)
        return;
      this.mIsClosed = true;
      this.mSharedReference.deleteReference();
      return;
    }
    finally
    {
    }
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      try
      {
        if (this.mIsClosed)
          return;
        FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", new Object[] { Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mSharedReference)), this.mSharedReference.get().getClass().getName() });
        close();
        return;
      }
      finally
      {
      }
    }
    finally
    {
      super.finalize();
    }
  }

  public T get()
  {
    try
    {
      Preconditions.checkState(this.mIsClosed ^ true);
      Object localObject1 = this.mSharedReference.get();
      return localObject1;
    }
    finally
    {
      localObject2 = finally;
      throw localObject2;
    }
  }

  @VisibleForTesting
  public SharedReference<T> getUnderlyingReferenceTestOnly()
  {
    try
    {
      SharedReference localSharedReference = this.mSharedReference;
      return localSharedReference;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getValueHash()
  {
    if (isValid())
      return System.identityHashCode(this.mSharedReference.get());
    return 0;
  }

  public boolean isValid()
  {
    try
    {
      boolean bool = this.mIsClosed;
      return bool ^ true;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}