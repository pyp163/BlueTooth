package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PooledByteStreams
{
  private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
  private final ByteArrayPool mByteArrayPool;
  private final int mTempBufSize;

  public PooledByteStreams(ByteArrayPool paramByteArrayPool)
  {
    this(paramByteArrayPool, 16384);
  }

  @VisibleForTesting
  public PooledByteStreams(ByteArrayPool paramByteArrayPool, int paramInt)
  {
    boolean bool;
    if (paramInt > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    this.mTempBufSize = paramInt;
    this.mByteArrayPool = paramByteArrayPool;
  }

  public long copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = (byte[])this.mByteArrayPool.get(this.mTempBufSize);
    long l = 0L;
    try
    {
      while (true)
      {
        int i = paramInputStream.read(arrayOfByte, 0, this.mTempBufSize);
        if (i == -1)
          return l;
        paramOutputStream.write(arrayOfByte, 0, i);
        l += i;
      }
    }
    finally
    {
      this.mByteArrayPool.release(arrayOfByte);
    }
    throw paramInputStream;
  }

  public long copy(InputStream paramInputStream, OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    long l = 0L;
    boolean bool;
    if (paramLong > 0L)
      bool = true;
    else
      bool = false;
    Preconditions.checkState(bool);
    byte[] arrayOfByte = (byte[])this.mByteArrayPool.get(this.mTempBufSize);
    while (l < paramLong)
      try
      {
        int i = paramInputStream.read(arrayOfByte, 0, (int)Math.min(this.mTempBufSize, paramLong - l));
        if (i == -1)
          return l;
        paramOutputStream.write(arrayOfByte, 0, i);
        l += i;
      }
      finally
      {
        this.mByteArrayPool.release(arrayOfByte);
      }
    this.mByteArrayPool.release(arrayOfByte);
    return l;
  }
}