package com.qx.qgbox.views;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyWindowManager
{
  private static FloatWindowBigView bigWindow;
  private static WindowManager.LayoutParams bigWindowParams;
  private static ActivityManager mActivityManager;
  private static WindowManager mWindowManager;
  public static FloatWindowSmallView smallWindow;
  private static WindowManager.LayoutParams smallWindowParams;

  public static void createBigWindow(Context paramContext)
  {
    WindowManager localWindowManager = getWindowManager(paramContext);
    int i = localWindowManager.getDefaultDisplay().getWidth();
    int j = localWindowManager.getDefaultDisplay().getHeight();
    if (bigWindow == null)
    {
      bigWindow = new FloatWindowBigView(paramContext);
      if (bigWindowParams == null)
      {
        bigWindowParams = new WindowManager.LayoutParams();
        bigWindowParams.x = (i / 2 - FloatWindowBigView.viewWidth / 2);
        bigWindowParams.y = (j / 2 - FloatWindowBigView.viewHeight / 2);
        if (Build.VERSION.SDK_INT < 23)
          bigWindowParams.type = 2002;
        else
          bigWindowParams.type = 2038;
        bigWindowParams.format = 1;
        bigWindowParams.gravity = 51;
        bigWindowParams.width = FloatWindowBigView.viewWidth;
        bigWindowParams.height = FloatWindowBigView.viewHeight;
      }
      localWindowManager.addView(bigWindow, bigWindowParams);
    }
  }

  public static void createSmallWindow(Context paramContext)
  {
    WindowManager localWindowManager = getWindowManager(paramContext);
    localWindowManager.getDefaultDisplay().getWidth();
    localWindowManager.getDefaultDisplay().getHeight();
    if (smallWindow == null)
    {
      smallWindow = new FloatWindowSmallView(paramContext);
      if (smallWindowParams == null)
      {
        smallWindowParams = new WindowManager.LayoutParams();
        paramContext = new StringBuilder();
        paramContext.append("----SDK_INT = ");
        paramContext.append(Build.VERSION.SDK_INT);
        Log.i("sdk_tag", paramContext.toString());
        if (Build.VERSION.SDK_INT < 23)
          smallWindowParams.type = 2002;
        else if ((Build.VERSION.SDK_INT != 25) && (Build.VERSION.SDK_INT != 23) && (Build.VERSION.SDK_INT != 24))
          smallWindowParams.type = 2038;
        else
          smallWindowParams.type = 2002;
        smallWindowParams.format = 1;
        smallWindowParams.flags = 40;
        smallWindowParams.gravity = 51;
        smallWindowParams.width = FloatWindowSmallView.viewWidth;
        smallWindowParams.height = FloatWindowSmallView.viewHeight;
        smallWindowParams.x = 0;
        smallWindowParams.y = 2;
      }
      smallWindow.setParams(smallWindowParams);
      localWindowManager.addView(smallWindow, smallWindowParams);
    }
  }

  private static ActivityManager getActivityManager(Context paramContext)
  {
    if (mActivityManager == null)
      mActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    return mActivityManager;
  }

  private static long getAvailableMemory(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    getActivityManager(paramContext).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem;
  }

  public static String getUsedPercentValue(Context paramContext)
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
      String str = localBufferedReader.readLine();
      str = str.substring(str.indexOf("MemTotal:"));
      localBufferedReader.close();
      long l = Integer.parseInt(str.replaceAll("\\D+", ""));
      int i = (int)((float)(l - getAvailableMemory(paramContext) / 1024L) / (float)l * 100.0F);
      paramContext = new StringBuilder();
      paramContext.append(i);
      paramContext.append("%");
      paramContext = paramContext.toString();
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "悬浮窗";
  }

  private static WindowManager getWindowManager(Context paramContext)
  {
    if (mWindowManager == null)
      mWindowManager = (WindowManager)paramContext.getSystemService("window");
    return mWindowManager;
  }

  public static boolean isWindowShowing()
  {
    return (smallWindow != null) || (bigWindow != null);
  }

  public static void removeBigWindow(Context paramContext)
  {
    if (bigWindow != null)
    {
      getWindowManager(paramContext).removeView(bigWindow);
      bigWindow = null;
    }
  }

  public static void removeSmallWindow(Context paramContext)
  {
    if (smallWindow != null)
    {
      getWindowManager(paramContext).removeView(smallWindow);
      smallWindow = null;
    }
  }

  public static void updateUsedPercent(Context paramContext)
  {
    paramContext = smallWindow;
  }
}
