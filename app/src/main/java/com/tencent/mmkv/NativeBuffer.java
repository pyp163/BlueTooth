package com.tencent.mmkv;

public final class NativeBuffer
{
  public long pointer;
  public int size;

  public NativeBuffer(long paramLong, int paramInt)
  {
    this.pointer = paramLong;
    this.size = paramInt;
  }
}