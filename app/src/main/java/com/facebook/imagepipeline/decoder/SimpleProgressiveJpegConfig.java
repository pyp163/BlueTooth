package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.Collections;
import java.util.List;

public class SimpleProgressiveJpegConfig
  implements ProgressiveJpegConfig
{
  private final DynamicValueConfig mDynamicValueConfig;

  public SimpleProgressiveJpegConfig()
  {
    this(new DefaultDynamicValueConfig(null));
  }

  public SimpleProgressiveJpegConfig(DynamicValueConfig paramDynamicValueConfig)
  {
    this.mDynamicValueConfig = ((DynamicValueConfig)Preconditions.checkNotNull(paramDynamicValueConfig));
  }

  public int getNextScanNumberToDecode(int paramInt)
  {
    List localList = this.mDynamicValueConfig.getScansToDecode();
    if ((localList != null) && (!localList.isEmpty()))
    {
      int i = 0;
      while (i < localList.size())
      {
        if (((Integer)localList.get(i)).intValue() > paramInt)
          return ((Integer)localList.get(i)).intValue();
        i += 1;
      }
      return 2147483647;
    }
    return paramInt + 1;
  }

  public QualityInfo getQualityInfo(int paramInt)
  {
    boolean bool;
    if (paramInt >= this.mDynamicValueConfig.getGoodEnoughScanNumber())
      bool = true;
    else
      bool = false;
    return ImmutableQualityInfo.of(paramInt, bool, false);
  }

  private static class DefaultDynamicValueConfig
    implements SimpleProgressiveJpegConfig.DynamicValueConfig
  {
    public int getGoodEnoughScanNumber()
    {
      return 0;
    }

    public List<Integer> getScansToDecode()
    {
      return Collections.EMPTY_LIST;
    }
  }

  public static abstract interface DynamicValueConfig
  {
    public abstract int getGoodEnoughScanNumber();

    public abstract List<Integer> getScansToDecode();
  }
}