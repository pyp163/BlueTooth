package com.facebook.imagepipeline.cache;

import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;

public class NativeMemoryCacheTrimStrategy
  implements CountingMemoryCache.CacheTrimStrategy
{
  private static final String TAG = "NativeMemoryCacheTrimStrategy";

  public double getTrimRatio(MemoryTrimType paramMemoryTrimType)
  {
    switch (1.$SwitchMap$com$facebook$common$memory$MemoryTrimType[paramMemoryTrimType.ordinal()])
    {
    default:
      FLog.wtf("NativeMemoryCacheTrimStrategy", "unknown trim type: %s", new Object[] { paramMemoryTrimType });
      return 0.0D;
    case 2:
    case 3:
    case 4:
      return 1.0D;
    case 1:
    }
    return 0.0D;
  }
}