package com.facebook.common.executors;

import android.os.Handler;
import android.os.Looper;

public class UiThreadImmediateExecutorService extends HandlerExecutorServiceImpl
{
  private static UiThreadImmediateExecutorService sInstance;

  private UiThreadImmediateExecutorService()
  {
    super(new Handler(Looper.getMainLooper()));
  }

  public static UiThreadImmediateExecutorService getInstance()
  {
    if (sInstance == null)
      sInstance = new UiThreadImmediateExecutorService();
    return sInstance;
  }

  public void execute(Runnable paramRunnable)
  {
    if (isHandlerThread())
    {
      paramRunnable.run();
      return;
    }
    super.execute(paramRunnable);
  }
}