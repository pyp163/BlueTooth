package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NativePooledByteBufferOutputStream extends PooledByteBufferOutputStream
{
  private CloseableReference<NativeMemoryChunk> mBufRef;
  private int mCount;
  private final NativeMemoryChunkPool mPool;

  public NativePooledByteBufferOutputStream(NativeMemoryChunkPool paramNativeMemoryChunkPool)
  {
    this(paramNativeMemoryChunkPool, paramNativeMemoryChunkPool.getMinBufferSize());
  }

  public NativePooledByteBufferOutputStream(NativeMemoryChunkPool paramNativeMemoryChunkPool, int paramInt)
  {
    boolean bool;
    if (paramInt > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    this.mPool = ((NativeMemoryChunkPool)Preconditions.checkNotNull(paramNativeMemoryChunkPool));
    this.mCount = 0;
    this.mBufRef = CloseableReference.of(this.mPool.get(paramInt), this.mPool);
  }

  private void ensureValid()
  {
    if (!CloseableReference.isValid(this.mBufRef))
      throw new InvalidStreamException();
  }

  public void close()
  {
    CloseableReference.closeSafely(this.mBufRef);
    this.mBufRef = null;
    this.mCount = -1;
    super.close();
  }

  @VisibleForTesting
  void realloc(int paramInt)
  {
    ensureValid();
    if (paramInt <= ((NativeMemoryChunk)this.mBufRef.get()).getSize())
      return;
    NativeMemoryChunk localNativeMemoryChunk = (NativeMemoryChunk)this.mPool.get(paramInt);
    ((NativeMemoryChunk)this.mBufRef.get()).copy(0, localNativeMemoryChunk, 0, this.mCount);
    this.mBufRef.close();
    this.mBufRef = CloseableReference.of(localNativeMemoryChunk, this.mPool);
  }

  public int size()
  {
    return this.mCount;
  }

  public NativePooledByteBuffer toByteBuffer()
  {
    ensureValid();
    return new NativePooledByteBuffer(this.mBufRef, this.mCount);
  }

  public void write(int paramInt)
    throws IOException
  {
    write(new byte[] { (byte)paramInt });
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfByte.length))
    {
      ensureValid();
      realloc(this.mCount + paramInt2);
      ((NativeMemoryChunk)this.mBufRef.get()).write(this.mCount, paramArrayOfByte, paramInt1, paramInt2);
      this.mCount += paramInt2;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("length=");
    localStringBuilder.append(paramArrayOfByte.length);
    localStringBuilder.append("; regionStart=");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("; regionLength=");
    localStringBuilder.append(paramInt2);
    throw new ArrayIndexOutOfBoundsException(localStringBuilder.toString());
  }

  public static class InvalidStreamException extends RuntimeException
  {
    public InvalidStreamException()
    {
      super();
    }
  }
}