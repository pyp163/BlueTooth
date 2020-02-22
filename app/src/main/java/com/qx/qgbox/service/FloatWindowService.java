package com.qx.qgbox.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.qx.qgbox.activity.FirstPageActivity;
import com.qx.qgbox.views.MyWindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FloatWindowService extends Service
{
  public static final int MSG_ON_RESET_FLOAT_BTN = 0;
  public static Handler myHandle;
  private final int PID = 999;
  private Handler handler = new Handler();
  private ServiceConnection mConnection;
  String notificationId = "channelId";
  NotificationManager notificationManager;
  String notificationName = "channelName";
  private Timer timer;

  private List<String> getHomes()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localObject = ((PackageManager)localObject).queryIntentActivities(localIntent, 65536).iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add(((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName);
    return localArrayList;
  }

  private Notification getNotification()
  {
    Notification.Builder localBuilder = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher);
    if (Build.VERSION.SDK_INT >= 26)
      localBuilder.setChannelId(this.notificationId);
    return localBuilder.build();
  }

  private boolean isHome()
  {
    List localList = ((ActivityManager)getSystemService("activity")).getRunningTasks(1);
    return getHomes().contains(((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName());
  }

  private void startForeground()
  {
    if (Build.VERSION.SDK_INT < 18)
    {
      startForeground(999, getNotification());
      return;
    }
    if (this.mConnection == null)
      this.mConnection = new CoverServiceConnection(null);
    bindService(new Intent(this, AssistService.class), this.mConnection, 1);
  }

  private void startForegroundService()
  {
    this.notificationManager = ((NotificationManager)getSystemService("notification"));
    if (Build.VERSION.SDK_INT >= 26)
    {
      NotificationChannel localNotificationChannel = new NotificationChannel(this.notificationId, this.notificationName, 1);
      this.notificationManager.createNotificationChannel(localNotificationChannel);
    }
    startForeground(1, getNotification());
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    if (myHandle == null)
      myHandle = new Handler(getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          if (paramAnonymousMessage.what != 0)
            return;
          if (FirstPageActivity.isShowFBtn)
          {
            MyWindowManager.removeSmallWindow(FloatWindowService.this.getApplicationContext());
            MyWindowManager.createSmallWindow(FloatWindowService.this.getApplicationContext());
          }
        }
      };
    startForegroundService();
    super.onCreate();
  }

  public void onDestroy()
  {
    super.onDestroy();
    stopForeground(true);
    if (this.timer != null)
    {
      this.timer.cancel();
      this.timer = null;
    }
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.timer == null)
    {
      this.timer = new Timer();
      this.timer.scheduleAtFixedRate(new RefreshTask(), 0L, 500L);
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  private class CoverServiceConnection
    implements ServiceConnection
  {
    private CoverServiceConnection()
    {
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      paramComponentName = ((AssistService.LocalBinder)paramIBinder).getService();
      FloatWindowService.this.startForeground(999, FloatWindowService.this.getNotification());
      paramComponentName.startForeground(999, FloatWindowService.this.getNotification());
      paramComponentName.stopForeground(true);
      FloatWindowService.this.unbindService(FloatWindowService.this.mConnection);
      FloatWindowService.access$202(FloatWindowService.this, null);
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
    }
  }

  class RefreshTask extends TimerTask
  {
    RefreshTask()
    {
    }

    public void run()
    {
      if ((FloatWindowService.this.isHome()) && (!MyWindowManager.isWindowShowing()))
      {
        FloatWindowService.this.handler.post(new Runnable()
        {
          public void run()
          {
            if (FirstPageActivity.isShowFBtn)
              MyWindowManager.createSmallWindow(FloatWindowService.this.getApplicationContext());
          }
        });
        return;
      }
      if ((FloatWindowService.this.isHome()) && (MyWindowManager.isWindowShowing()))
        FloatWindowService.this.handler.post(new Runnable()
        {
          public void run()
          {
            MyWindowManager.updateUsedPercent(FloatWindowService.this.getApplicationContext());
          }
        });
    }
  }
}
