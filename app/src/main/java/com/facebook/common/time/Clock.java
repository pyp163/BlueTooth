package com.facebook.common.time;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract interface Clock
{
  public static final long MAX_TIME = 9223372036854775807L;

  public abstract long now();
}