package com.facebook.cache.common;

public class NoOpCacheEventListener
  implements CacheEventListener
{
  private static NoOpCacheEventListener sInstance;

  public static NoOpCacheEventListener getInstance()
  {
    try
    {
      if (sInstance == null)
        sInstance = new NoOpCacheEventListener();
      NoOpCacheEventListener localNoOpCacheEventListener = sInstance;
      return localNoOpCacheEventListener;
    }
    finally
    {
    }
  }

  public void onCleared()
  {
  }

  public void onEviction(CacheEvent paramCacheEvent)
  {
  }

  public void onHit(CacheEvent paramCacheEvent)
  {
  }

  public void onMiss(CacheEvent paramCacheEvent)
  {
  }

  public void onReadException(CacheEvent paramCacheEvent)
  {
  }

  public void onWriteAttempt(CacheEvent paramCacheEvent)
  {
  }

  public void onWriteException(CacheEvent paramCacheEvent)
  {
  }

  public void onWriteSuccess(CacheEvent paramCacheEvent)
  {
  }
}