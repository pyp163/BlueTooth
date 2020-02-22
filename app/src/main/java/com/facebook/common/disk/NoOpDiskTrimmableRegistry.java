package com.facebook.common.disk;

import javax.annotation.Nullable;

public class NoOpDiskTrimmableRegistry
  implements DiskTrimmableRegistry
{

  @Nullable
  private static NoOpDiskTrimmableRegistry sInstance;

  public static NoOpDiskTrimmableRegistry getInstance()
  {
    try
    {
      if (sInstance == null)
        sInstance = new NoOpDiskTrimmableRegistry();
      NoOpDiskTrimmableRegistry localNoOpDiskTrimmableRegistry = sInstance;
      return localNoOpDiskTrimmableRegistry;
    }
    finally
    {
    }
  }

  public void registerDiskTrimmable(DiskTrimmable paramDiskTrimmable)
  {
  }

  public void unregisterDiskTrimmable(DiskTrimmable paramDiskTrimmable)
  {
  }
}