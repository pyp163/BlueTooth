package com.facebook.imagepipeline.cache;

import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;

public class BitmapMemoryCacheTrimStrategy
  implements CountingMemoryCache.CacheTrimStrategy
{
  private static final String TAG = "BitmapMemoryCacheTrimStrategy";

  public double getTrimRatio(MemoryTrimType paramMemoryTrimType)
  {
    switch (1.$SwitchMap$com$facebook$common$memory$MemoryTrimType[paramMemoryTrimType.ordinal()])
    {
    default:
      FLog.wtf("BitmapMemoryCacheTrimStrategy", "unknown trim type: %s", new Object[] { paramMemoryTrimType });
      return 0.0D;
    case 2:
    case 3:
    case 4:
      return 1.0D;
    case 1:
    }
    if (Build.VERSION.SDK_INT >= 21)
      return MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio();
    return 0.0D;
  }
}