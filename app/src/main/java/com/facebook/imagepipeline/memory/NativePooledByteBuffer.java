package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBuffer.ClosedException;
import com.facebook.common.references.CloseableReference;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBuffer
  implements PooledByteBuffer
{

  @VisibleForTesting
  @GuardedBy("this")
  CloseableReference<NativeMemoryChunk> mBufRef;
  private final int mSize;

  public NativePooledByteBuffer(CloseableReference<NativeMemoryChunk> paramCloseableReference, int paramInt)
  {
    Preconditions.checkNotNull(paramCloseableReference);
    boolean bool;
    if ((paramInt >= 0) && (paramInt <= ((NativeMemoryChunk)paramCloseableReference.get()).getSize()))
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    this.mBufRef = paramCloseableReference.clone();
    this.mSize = paramInt;
  }

  public void close()
  {
    try
    {
      CloseableReference.closeSafely(this.mBufRef);
      this.mBufRef = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void ensureValid()
  {
    try
    {
      if (isClosed())
        throw new PooledByteBuffer.ClosedException();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long getNativePtr()
  {
    try
    {
      ensureValid();
      long l = ((NativeMemoryChunk)this.mBufRef.get()).getNativePtr();
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isClosed()
  {
    try
    {
      boolean bool = CloseableReference.isValid(this.mBufRef);
      return bool ^ true;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte read(int paramInt)
  {
    while (true)
    {
      try
      {
        ensureValid();
        boolean bool2 = false;
        if (paramInt >= 0)
        {
          bool1 = true;
          Preconditions.checkArgument(bool1);
          bool1 = bool2;
          if (paramInt < this.mSize)
            bool1 = true;
          Preconditions.checkArgument(bool1);
          byte b = ((NativeMemoryChunk)this.mBufRef.get()).read(paramInt);
          return b;
        }
      }
      finally
      {
      }
      boolean bool1 = false;
    }
  }

  public int read(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    while (true)
    {
      try
      {
        ensureValid();
        if (paramInt1 + paramInt3 <= this.mSize)
        {
          bool = true;
          Preconditions.checkArgument(bool);
          paramInt1 = ((NativeMemoryChunk)this.mBufRef.get()).read(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
          return paramInt1;
        }
      }
      finally
      {
      }
      boolean bool = false;
    }
  }

  public int size()
  {
    try
    {
      ensureValid();
      int i = this.mSize;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}