package com.nineoldandroids.animation;

public class ArgbEvaluator
  implements TypeEvaluator
{
  public Object evaluate(float paramFloat, Object paramObject1, Object paramObject2)
  {
    int m = ((Integer)paramObject1).intValue();
    int i = m >> 24;
    int j = m >> 16 & 0xFF;
    int k = m >> 8 & 0xFF;
    m &= 255;
    int n = ((Integer)paramObject2).intValue();
    return Integer.valueOf(i + (int)(((n >> 24) - i) * paramFloat) << 24 | j + (int)(((n >> 16 & 0xFF) - j) * paramFloat) << 16 | k + (int)(((n >> 8 & 0xFF) - k) * paramFloat) << 8 | m + (int)(paramFloat * ((n & 0xFF) - m)));
  }
}