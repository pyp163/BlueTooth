package com.facebook.imagepipeline.cache;

public abstract interface MemoryCacheTracker<K>
{
  public abstract void onCacheHit(K paramK);

  public abstract void onCacheMiss();

  public abstract void onCachePut();
}