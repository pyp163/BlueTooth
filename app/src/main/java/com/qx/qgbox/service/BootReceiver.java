package com.qx.qgbox.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class BootReceiver extends BroadcastReceiver
{
  private void startmyservice(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, bluetoothdevmanager.class);
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramContext.startForegroundService(localIntent);
      return;
    }
    paramContext.startService(localIntent);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("com.qx.qgbox.service.bluetoothdevmanager.destroy"))
      startmyservice(paramContext);
  }
}
