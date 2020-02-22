package com.qx.qgbox.utils;

import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotchSizeUtil
{
  public static final int FLAG_NOTCH_SUPPORT = 65536;

  public static int[] getNotchSize(Context paramContext)
  {
    int[] arrayOfInt = new int[2];
    int[] tmp5_4 = arrayOfInt;
    tmp5_4[0] = 0;
    int[] tmp9_5 = tmp5_4;
    tmp9_5[1] = 0;
    tmp9_5;
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
      paramContext = (int[])paramContext.getMethod("getNotchSize", new Class[0]).invoke(paramContext, new Object[0]);
      return paramContext;
      Log.e("test", "getNotchSize Exception");
      return arrayOfInt;
      Log.e("test", "getNotchSize NoSuchMethodException");
      return arrayOfInt;
      Log.e("test", "getNotchSize ClassNotFoundException");
      return arrayOfInt;
    }
    catch (ClassNotFoundException paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    catch (NoSuchMethodException paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    finally
    {
    }
    return arrayOfInt;
  }

  public static boolean hasNotchInScreen(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
      boolean bool = ((Boolean)paramContext.getMethod("hasNotchInScreen", new Class[0]).invoke(paramContext, new Object[0])).booleanValue();
      return bool;
      Log.i("test", "hasNotchInScreen Exception");
      return false;
      Log.i("test", "hasNotchInScreen NoSuchMethodException");
      return false;
      Log.i("test", "hasNotchInScreen ClassNotFoundException");
      return false;
    }
    catch (ClassNotFoundException paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    catch (NoSuchMethodException paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext = paramContext;
    }
    finally
    {
    }
    return false;
  }

  public static void setFullScreenWindowLayoutInDisplayCutout(Window paramWindow)
  {
    if (paramWindow == null)
      return;
    Object localObject = paramWindow.getAttributes();
    try
    {
      paramWindow = Class.forName("com.huawei.android.view.LayoutParamsEx");
      localObject = paramWindow.getConstructor(new Class[] { WindowManager.LayoutParams.class }).newInstance(new Object[] { localObject });
      paramWindow.getMethod("addHwFlags", new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(65536) });
      Log.i("test", "invoke");
      return;
      label83: Log.i("test", "other Exception");
      return;
      label92: Log.i("test", "hw add notch screen flag api error");
      return;
    }
    catch (ClassNotFoundException|NoSuchMethodException|IllegalAccessException|InstantiationException|InvocationTargetException paramWindow)
    {
      break label92;
    }
    catch (Exception paramWindow)
    {
      break label83;
    }
  }

  public static void setNotFullScreenWindowLayoutInDisplayCutout(Window paramWindow)
  {
    if (paramWindow == null)
      return;
    Object localObject = paramWindow.getAttributes();
    try
    {
      paramWindow = Class.forName("com.huawei.android.view.LayoutParamsEx");
      localObject = paramWindow.getConstructor(new Class[] { WindowManager.LayoutParams.class }).newInstance(new Object[] { localObject });
      paramWindow.getMethod("clearHwFlags", new Class[] { Integer.TYPE }).invoke(localObject, new Object[] { Integer.valueOf(65536) });
      return;
      label75: Log.e("test", "other Exception");
      return;
      label84: Log.e("test", "hw clear notch screen flag api error");
      return;
    }
    catch (ClassNotFoundException|NoSuchMethodException|IllegalAccessException|InstantiationException|InvocationTargetException paramWindow)
    {
      break label84;
    }
    catch (Exception paramWindow)
    {
      break label75;
    }
  }
}
