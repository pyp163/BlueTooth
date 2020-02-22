package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PooledByteBufferInputStream extends InputStream
{

  @VisibleForTesting
  int mMark;

  @VisibleForTesting
  int mOffset;

  @VisibleForTesting
  final PooledByteBuffer mPooledByteBuffer;

  public PooledByteBufferInputStream(PooledByteBuffer paramPooledByteBuffer)
  {
    Preconditions.checkArgument(paramPooledByteBuffer.isClosed() ^ true);
    this.mPooledByteBuffer = ((PooledByteBuffer)Preconditions.checkNotNull(paramPooledByteBuffer));
    this.mOffset = 0;
    this.mMark = 0;
  }

  public int available()
  {
    return this.mPooledByteBuffer.size() - this.mOffset;
  }

  public void mark(int paramInt)
  {
    this.mMark = this.mOffset;
  }

  public boolean markSupported()
  {
    return true;
  }

  public int read()
  {
    if (available() <= 0)
      return -1;
    PooledByteBuffer localPooledByteBuffer = this.mPooledByteBuffer;
    int i = this.mOffset;
    this.mOffset = (i + 1);
    return localPooledByteBuffer.read(i) & 0xFF;
  }

  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfByte.length))
    {
      int i = available();
      if (i <= 0)
        return -1;
      if (paramInt2 <= 0)
        return 0;
      paramInt2 = Math.min(i, paramInt2);
      this.mPooledByteBuffer.read(this.mOffset, paramArrayOfByte, paramInt1, paramInt2);
      this.mOffset += paramInt2;
      return paramInt2;
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

  public void reset()
  {
    this.mOffset = this.mMark;
  }

  public long skip(long paramLong)
  {
    boolean bool;
    if (paramLong >= 0L)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    int i = Math.min((int)paramLong, available());
    this.mOffset += i;
    return i;
  }
}