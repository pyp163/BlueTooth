package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.qx.qgbox.R;
import com.qx.qgbox.views.CustomDialog;

public class MainActivity extends AppCompatActivity
{
  public static final int MSG_ON_GO_TO_FIRST_ACTIVITY = 1;
  private Activity mActivity;
  private Context mContext;
  private CustomDialog mCustomDialog = null;

  @SuppressLint({"HandlerLeak"})
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what != 1)
        return;
      FirstPageActivity.goHome(mActivity);
      MainActivity.this.finish();
    }
  };

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1234)
      this.mHandler.sendEmptyMessageDelayed(1, 1500L);
  }

  protected void onCreate(Bundle paramBundle)
  {
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_main);
    this.mContext = this;
    this.mActivity = this;
    if (this.mCustomDialog != null)
    {
      if (this.mCustomDialog.isShowing())
        this.mCustomDialog.dismiss();
      this.mCustomDialog = null;
    }
    this.mCustomDialog = new CustomDialog(this.mContext, getResources().getString(R.string.ask_for_permission), getResources().getString(R.string.permission_info), getResources().getDrawable(R.mipmap.ic_launcher), false, false);
    this.mCustomDialog.setCanceledOnTouchOutside(false);
    this.mCustomDialog.setClicklistener(new CustomDialog.ClickListenerInterface()
    {
      public void doCancel()
      {
        MainActivity.this.mCustomDialog.dismiss();
        MainActivity.this.mHandler.sendEmptyMessageDelayed(1, 2500L);
      }

      public void doConfirm()
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("package:");
        ((StringBuilder)localObject).append(MainActivity.this.getPackageName());
        localObject = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(((StringBuilder)localObject).toString()));
        MainActivity.this.startActivityForResult((Intent)localObject, 1234);
        MainActivity.this.mCustomDialog.dismiss();
      }
    });
    if (Build.VERSION.SDK_INT >= 23)
    {
      if (!Settings.canDrawOverlays(this.mContext))
      {
        this.mCustomDialog.show();
        return;
      }
      this.mHandler.sendEmptyMessageDelayed(1, 3500L);
      return;
    }
    this.mHandler.sendEmptyMessageDelayed(1, 3500L);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}
