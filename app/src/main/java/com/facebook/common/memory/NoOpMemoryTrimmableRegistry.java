package com.facebook.common.memory;

public class NoOpMemoryTrimmableRegistry
  implements MemoryTrimmableRegistry
{
  private static NoOpMemoryTrimmableRegistry sInstance;

  public static NoOpMemoryTrimmableRegistry getInstance()
  {
    try
    {
      if (sInstance == null)
        sInstance = new NoOpMemoryTrimmableRegistry();
      NoOpMemoryTrimmableRegistry localNoOpMemoryTrimmableRegistry = sInstance;
      return localNoOpMemoryTrimmableRegistry;
    }
    finally
    {
    }
  }

  public void registerMemoryTrimmable(MemoryTrimmable paramMemoryTrimmable)
  {
  }

  public void unregisterMemoryTrimmable(MemoryTrimmable paramMemoryTrimmable)
  {
  }
}