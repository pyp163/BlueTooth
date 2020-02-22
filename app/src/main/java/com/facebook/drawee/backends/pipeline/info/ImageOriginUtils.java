package com.facebook.drawee.backends.pipeline.info;

public class ImageOriginUtils
{
  public static int mapProducerNameToImageOrigin(String paramString)
  {
    switch (paramString.hashCode())
    {
    default:
      break;
    case 957714404:
      if (paramString.equals("BitmapMemoryCacheProducer"))
        i = 1;
      break;
    case 656304759:
      if (paramString.equals("DiskCacheProducer"))
        i = 3;
      break;
    case -1224383234:
      if (paramString.equals("NetworkFetchProducer"))
        i = 4;
      break;
    case -1307634203:
      if (paramString.equals("EncodedMemoryCacheProducer"))
        i = 2;
      break;
    case -1914072202:
      if (paramString.equals("BitmapMemoryCacheGetProducer"))
        i = 0;
      break;
    }
    int i = -1;
    switch (i)
    {
    default:
      return -1;
    case 4:
      return 0;
    case 3:
      return 1;
    case 2:
      return 2;
    case 0:
    case 1:
    }
    return 3;
  }

  public static String toString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "unknown";
    case 3:
      return "memory_bitmap";
    case 2:
      return "memory_encoded";
    case 1:
      return "disk";
    case 0:
    }
    return "network";
  }
}