package com.facebook.imagepipeline.memory;

public class BitmapCounterConfig
{
  public static final int DEFAULT_MAX_BITMAP_COUNT = 384;
  private int mMaxBitmapCount = 384;

  public BitmapCounterConfig(Builder paramBuilder)
  {
    this.mMaxBitmapCount = paramBuilder.getMaxBitmapCount();
  }

  public static Builder newBuilder()
  {
    return new Builder(null);
  }

  public int getMaxBitmapCount()
  {
    return this.mMaxBitmapCount;
  }

  public void setMaxBitmapCount(int paramInt)
  {
    this.mMaxBitmapCount = paramInt;
  }

  public static class Builder
  {
    private int mMaxBitmapCount = 384;

    public BitmapCounterConfig build()
    {
      return new BitmapCounterConfig(this);
    }

    public int getMaxBitmapCount()
    {
      return this.mMaxBitmapCount;
    }

    public Builder setMaxBitmapCount(int paramInt)
    {
      this.mMaxBitmapCount = paramInt;
      return this;
    }
  }
}