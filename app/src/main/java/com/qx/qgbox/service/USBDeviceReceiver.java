package com.qx.qgbox.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class USBDeviceReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.HDMI_PLUGGED"))
      if (paramIntent.getBooleanExtra("state", false))
      {
        paramContext = new Message();
        paramContext.what = 1000;
        paramContext.obj = "HDMI已连接";
        if (bluetoothdevmanager.servicehandle != null)
          bluetoothdevmanager.servicehandle.sendMessage(paramContext);
      }
      else
      {
        paramContext = new Message();
        paramContext.what = 1000;
        paramContext.obj = "HDMI已断开";
        if (bluetoothdevmanager.servicehandle != null)
          bluetoothdevmanager.servicehandle.sendMessage(paramContext);
      }
  }
}
