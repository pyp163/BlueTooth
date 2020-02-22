package com.qx.qgbox.utils;

import android.util.Log;

public class MyLog
{
  public static boolean isDebug = false;

  public static void d(String paramString1, String paramString2)
  {
    if (isDebug)
      Log.d(paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (isDebug)
      Log.e(paramString1, paramString2);
  }

  public static void i(String paramString1, String paramString2)
  {
    if (isDebug)
      Log.i(paramString1, paramString2);
  }

  public static void w(String paramString1, String paramString2)
  {
    if (isDebug)
      Log.w(paramString1, paramString2);
  }
}
