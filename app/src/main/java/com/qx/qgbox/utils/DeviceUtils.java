package com.qx.qgbox.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceUtils
{
  public static String getDeviceBrand()
  {
    return Build.BRAND;
  }

  public static String getSystemModel()
  {
    return Build.MODEL;
  }

  public static String getUniqueId(Context paramContext)
  {
    paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext);
    ((StringBuilder)localObject).append(Build.SERIAL);
    paramContext = ((StringBuilder)localObject).toString();
    try
    {
      localObject = toMD5(paramContext);
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return paramContext;
  }

  private static String toMD5(String paramString)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length)
    {
      String str = Integer.toHexString(paramString[i] & 0xFF);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
