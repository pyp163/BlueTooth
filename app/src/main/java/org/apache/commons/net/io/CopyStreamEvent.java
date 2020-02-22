package org.apache.commons.net.io;

import java.util.EventObject;

public class CopyStreamEvent extends EventObject
{
  public static final long UNKNOWN_STREAM_SIZE = -1L;
  private static final long serialVersionUID = -964927635655051867L;
  private final int bytesTransferred;
  private final long streamSize;
  private final long totalBytesTransferred;

  public CopyStreamEvent(Object paramObject, long paramLong1, int paramInt, long paramLong2)
  {
    super(paramObject);
    this.bytesTransferred = paramInt;
    this.totalBytesTransferred = paramLong1;
    this.streamSize = paramLong2;
  }

  public int getBytesTransferred()
  {
    return this.bytesTransferred;
  }

  public long getStreamSize()
  {
    return this.streamSize;
  }

  public long getTotalBytesTransferred()
  {
    return this.totalBytesTransferred;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getName());
    localStringBuilder.append("[source=");
    localStringBuilder.append(this.source);
    localStringBuilder.append(", total=");
    localStringBuilder.append(this.totalBytesTransferred);
    localStringBuilder.append(", bytes=");
    localStringBuilder.append(this.bytesTransferred);
    localStringBuilder.append(", size=");
    localStringBuilder.append(this.streamSize);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}