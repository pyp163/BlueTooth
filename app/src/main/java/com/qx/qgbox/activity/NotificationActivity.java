package com.qx.qgbox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (isTaskRoot())
    {


      Intent localIntent = new Intent(this, MainActivity.class);
      localIntent.putExtras(getIntent().getExtras());
      localIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
      //localIntent.putExtras(paramBundle);
      startActivities(new Intent[] {localIntent},paramBundle);
    }
    finish();
  }
}
