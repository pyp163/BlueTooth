package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBufferFactory
  implements PooledByteBufferFactory
{
  private final NativeMemoryChunkPool mPool;
  private final PooledByteStreams mPooledByteStreams;

  public NativePooledByteBufferFactory(NativeMemoryChunkPool paramNativeMemoryChunkPool, PooledByteStreams paramPooledByteStreams)
  {
    this.mPool = paramNativeMemoryChunkPool;
    this.mPooledByteStreams = paramPooledByteStreams;
  }

  @VisibleForTesting
  NativePooledByteBuffer newByteBuf(InputStream paramInputStream, NativePooledByteBufferOutputStream paramNativePooledByteBufferOutputStream)
    throws IOException
  {
    this.mPooledByteStreams.copy(paramInputStream, paramNativePooledByteBufferOutputStream);
    return paramNativePooledByteBufferOutputStream.toByteBuffer();
  }

  public NativePooledByteBuffer newByteBuffer(int paramInt)
  {
    boolean bool;
    if (paramInt > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    CloseableReference localCloseableReference = CloseableReference.of(this.mPool.get(paramInt), this.mPool);
    try
    {
      NativePooledByteBuffer localNativePooledByteBuffer = new NativePooledByteBuffer(localCloseableReference, paramInt);
      return localNativePooledByteBuffer;
    }
    finally
    {
      localCloseableReference.close();
    }
  }

  public NativePooledByteBuffer newByteBuffer(InputStream paramInputStream)
    throws IOException
  {
    NativePooledByteBufferOutputStream localNativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool);
    try
    {
      paramInputStream = newByteBuf(paramInputStream, localNativePooledByteBufferOutputStream);
      return paramInputStream;
    }
    finally
    {
      localNativePooledByteBufferOutputStream.close();
    }
    throw paramInputStream;
  }

  public NativePooledByteBuffer newByteBuffer(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    NativePooledByteBufferOutputStream localNativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, paramInt);
    try
    {
      paramInputStream = newByteBuf(paramInputStream, localNativePooledByteBufferOutputStream);
      return paramInputStream;
    }
    finally
    {
      localNativePooledByteBufferOutputStream.close();
    }
    throw paramInputStream;
  }

  // ERROR //
  public NativePooledByteBuffer newByteBuffer(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 34	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream
    //   3: dup
    //   4: aload_0
    //   5: getfield 18	com/facebook/imagepipeline/memory/NativePooledByteBufferFactory:mPool	Lcom/facebook/imagepipeline/memory/NativeMemoryChunkPool;
    //   8: aload_1
    //   9: arraylength
    //   10: invokespecial 92	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream:<init>	(Lcom/facebook/imagepipeline/memory/NativeMemoryChunkPool;I)V
    //   13: astore_2
    //   14: aload_2
    //   15: aload_1
    //   16: iconst_0
    //   17: aload_1
    //   18: arraylength
    //   19: invokevirtual 96	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream:write	([BII)V
    //   22: aload_2
    //   23: invokevirtual 38	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream:toByteBuffer	()Lcom/facebook/imagepipeline/memory/NativePooledByteBuffer;
    //   26: astore_1
    //   27: aload_2
    //   28: invokevirtual 89	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream:close	()V
    //   31: aload_1
    //   32: areturn
    //   33: astore_1
    //   34: goto +9 -> 43
    //   37: astore_1
    //   38: aload_1
    //   39: invokestatic 102	com/facebook/common/internal/Throwables:propagate	(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   42: athrow
    //   43: aload_2
    //   44: invokevirtual 89	com/facebook/imagepipeline/memory/NativePooledByteBufferOutputStream:close	()V
    //   47: aload_1
    //   48: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	27	33	finally
    //   38	43	33	finally
    //   14	27	37	java/io/IOException
  }

  public NativePooledByteBufferOutputStream newOutputStream()
  {
    return new NativePooledByteBufferOutputStream(this.mPool);
  }

  public NativePooledByteBufferOutputStream newOutputStream(int paramInt)
  {
    return new NativePooledByteBufferOutputStream(this.mPool, paramInt);
  }
}