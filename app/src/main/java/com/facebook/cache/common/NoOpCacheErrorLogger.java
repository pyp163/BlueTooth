package com.facebook.cache.common;

import javax.annotation.Nullable;

public class NoOpCacheErrorLogger
  implements CacheErrorLogger
{
  private static NoOpCacheErrorLogger sInstance;

  public static NoOpCacheErrorLogger getInstance()
  {
    try
    {
      if (sInstance == null)
        sInstance = new NoOpCacheErrorLogger();
      NoOpCacheErrorLogger localNoOpCacheErrorLogger = sInstance;
      return localNoOpCacheErrorLogger;
    }
    finally
    {
    }
  }

  public void logError(CacheErrorLogger.CacheErrorCategory paramCacheErrorCategory, Class<?> paramClass, String paramString, @Nullable Throwable paramThrowable)
  {
  }
}