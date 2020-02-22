package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract interface MonotonicClock
{
  @DoNotStrip
  public abstract long now();
}