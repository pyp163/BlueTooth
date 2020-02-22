package com.qx.qgbox.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AssistService extends Service
{
  public IBinder onBind(Intent paramIntent)
  {
    return new LocalBinder();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }

  public class LocalBinder extends Binder
  {
    public LocalBinder()
    {
    }

    public AssistService getService()
    {
      return AssistService.this;
    }
  }
}
