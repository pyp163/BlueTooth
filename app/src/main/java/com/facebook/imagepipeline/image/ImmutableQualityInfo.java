package com.facebook.imagepipeline.image;

public class ImmutableQualityInfo
  implements QualityInfo
{
  public static final QualityInfo FULL_QUALITY = of(2147483647, true, true);
  boolean mIsOfFullQuality;
  boolean mIsOfGoodEnoughQuality;
  int mQuality;

  private ImmutableQualityInfo(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mQuality = paramInt;
    this.mIsOfGoodEnoughQuality = paramBoolean1;
    this.mIsOfFullQuality = paramBoolean2;
  }

  public static QualityInfo of(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new ImmutableQualityInfo(paramInt, paramBoolean1, paramBoolean2);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof ImmutableQualityInfo))
      return false;
    paramObject = (ImmutableQualityInfo)paramObject;
    return (this.mQuality == paramObject.mQuality) && (this.mIsOfGoodEnoughQuality == paramObject.mIsOfGoodEnoughQuality) && (this.mIsOfFullQuality == paramObject.mIsOfFullQuality);
  }

  public int getQuality()
  {
    return this.mQuality;
  }

  public int hashCode()
  {
    int k = this.mQuality;
    boolean bool = this.mIsOfGoodEnoughQuality;
    int j = 0;
    int i;
    if (bool)
      i = 4194304;
    else
      i = 0;
    if (this.mIsOfFullQuality)
      j = 8388608;
    return k ^ i ^ j;
  }

  public boolean isOfFullQuality()
  {
    return this.mIsOfFullQuality;
  }

  public boolean isOfGoodEnoughQuality()
  {
    return this.mIsOfGoodEnoughQuality;
  }
}