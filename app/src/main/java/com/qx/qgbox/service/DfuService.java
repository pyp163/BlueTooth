package com.qx.qgbox.service;

import android.app.Activity;
import com.qx.qgbox.activity.NotificationActivity;
import no.nordicsemi.android.dfu.DfuBaseService;

public class DfuService extends DfuBaseService
{
  protected Class<? extends Activity> getNotificationTarget()
  {
    return NotificationActivity.class;
  }
}
