package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import java.io.Closeable;

@DoNotStrip
public class NativeMemoryChunk
  implements Closeable
{
  private static final String TAG = "NativeMemoryChunk";
  private boolean mClosed;
  private final long mNativePtr;
  private final int mSize;

  static
  {
    ImagePipelineNativeLoader.load();
  }

  @VisibleForTesting
  public NativeMemoryChunk()
  {
    this.mSize = 0;
    this.mNativePtr = 0L;
    this.mClosed = true;
  }

  public NativeMemoryChunk(int paramInt)
  {
    boolean bool;
    if (paramInt > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    this.mSize = paramInt;
    this.mNativePtr = nativeAllocate(this.mSize);
    this.mClosed = false;
  }

  private int adjustByteCount(int paramInt1, int paramInt2)
  {
    return Math.min(Math.max(0, this.mSize - paramInt1), paramInt2);
  }

  private void checkBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool2 = false;
    if (paramInt4 >= 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt1 >= 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt3 >= 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt1 + paramInt4 <= this.mSize)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    boolean bool1 = bool2;
    if (paramInt3 + paramInt4 <= paramInt2)
      bool1 = true;
    Preconditions.checkArgument(bool1);
  }

  private void doCopy(int paramInt1, NativeMemoryChunk paramNativeMemoryChunk, int paramInt2, int paramInt3)
  {
    Preconditions.checkState(isClosed() ^ true);
    Preconditions.checkState(paramNativeMemoryChunk.isClosed() ^ true);
    checkBounds(paramInt1, paramNativeMemoryChunk.mSize, paramInt2, paramInt3);
    nativeMemcpy(paramNativeMemoryChunk.mNativePtr + paramInt2, this.mNativePtr + paramInt1, paramInt3);
  }

  @DoNotStrip
  private static native long nativeAllocate(int paramInt);

  @DoNotStrip
  private static native void nativeCopyFromByteArray(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  @DoNotStrip
  private static native void nativeCopyToByteArray(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  @DoNotStrip
  private static native void nativeFree(long paramLong);

  @DoNotStrip
  private static native void nativeMemcpy(long paramLong1, long paramLong2, int paramInt);

  @DoNotStrip
  private static native byte nativeReadByte(long paramLong);

  public void close()
  {
    try
    {
      if (!this.mClosed)
      {
        this.mClosed = true;
        nativeFree(this.mNativePtr);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void copy(int paramInt1, NativeMemoryChunk paramNativeMemoryChunk, int paramInt2, int paramInt3)
  {
    Preconditions.checkNotNull(paramNativeMemoryChunk);
    if (paramNativeMemoryChunk.mNativePtr == this.mNativePtr)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Copying from NativeMemoryChunk ");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" to NativeMemoryChunk ");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(paramNativeMemoryChunk)));
      localStringBuilder.append(" which share the same address ");
      localStringBuilder.append(Long.toHexString(this.mNativePtr));
      Log.w("NativeMemoryChunk", localStringBuilder.toString());
      Preconditions.checkArgument(false);
    }
    if (paramNativeMemoryChunk.mNativePtr < this.mNativePtr)
      try
      {
        try
        {
          doCopy(paramInt1, paramNativeMemoryChunk, paramInt2, paramInt3);
          return;
        }
        finally
        {
        }
      }
      finally
      {
      }
    try
    {
      try
      {
        doCopy(paramInt1, paramNativeMemoryChunk, paramInt2, paramInt3);
        return;
      }
      finally
      {
      }
    }
    finally
    {
    }
    throw paramNativeMemoryChunk;
  }

  protected void finalize()
    throws Throwable
  {
    if (isClosed())
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("finalize: Chunk ");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" still active. Underlying address = ");
    localStringBuilder.append(Long.toHexString(this.mNativePtr));
    Log.w("NativeMemoryChunk", localStringBuilder.toString());
    try
    {
      close();
      return;
    }
    finally
    {
      super.finalize();
    }
  }

  public long getNativePtr()
  {
    return this.mNativePtr;
  }

  public int getSize()
  {
    return this.mSize;
  }

  public boolean isClosed()
  {
    try
    {
      boolean bool = this.mClosed;
      return bool;
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
        Preconditions.checkState(isClosed() ^ true);
        boolean bool2 = false;
        if (paramInt >= 0)
        {
          bool1 = true;
          Preconditions.checkArgument(bool1);
          bool1 = bool2;
          if (paramInt < this.mSize)
            bool1 = true;
          Preconditions.checkArgument(bool1);
          byte b = nativeReadByte(this.mNativePtr + paramInt);
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
    try
    {
      Preconditions.checkNotNull(paramArrayOfByte);
      Preconditions.checkState(isClosed() ^ true);
      paramInt3 = adjustByteCount(paramInt1, paramInt3);
      checkBounds(paramInt1, paramArrayOfByte.length, paramInt2, paramInt3);
      nativeCopyToByteArray(this.mNativePtr + paramInt1, paramArrayOfByte, paramInt2, paramInt3);
      return paramInt3;
    }
    finally
    {
      paramArrayOfByte = finally;
    }
    throw paramArrayOfByte;
  }

  public int write(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    try
    {
      Preconditions.checkNotNull(paramArrayOfByte);
      Preconditions.checkState(isClosed() ^ true);
      paramInt3 = adjustByteCount(paramInt1, paramInt3);
      checkBounds(paramInt1, paramArrayOfByte.length, paramInt2, paramInt3);
      nativeCopyFromByteArray(this.mNativePtr + paramInt1, paramArrayOfByte, paramInt2, paramInt3);
      return paramInt3;
    }
    finally
    {
      paramArrayOfByte = finally;
    }
    throw paramArrayOfByte;
  }
}