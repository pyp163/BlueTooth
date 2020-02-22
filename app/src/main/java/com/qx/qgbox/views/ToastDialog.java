package com.qx.qgbox.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qx.qgbox.R;

public class ToastDialog extends AlertDialog
{
  private Context mContext;
  private Handler myhandler = null;
  private String test;

  public ToastDialog(Context paramContext, String paramString)
  {
    super(paramContext, R.style.custom_toast_dialog);
    this.mContext = paramContext;
    this.test = paramString;
  }

  @SuppressLint({"HandlerLeak"})
  private void init()
  {
    ((TextView)findViewById(R.id.toast_text)).setText(this.test);
    this.myhandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what != 0)
          return;
        ToastDialog.this.dismiss();
      }
    };
    this.myhandler.sendEmptyMessageDelayed(0, 800L);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.toast_layout1);
    getWindow().setLayout(-2, -2);
    getWindow().setFlags(8, 8);
    if (Build.VERSION.SDK_INT < 23)
      getWindow().setType(2005);
    else if ((Build.VERSION.SDK_INT != 28) && (Build.VERSION.SDK_INT != 26) && (Build.VERSION.SDK_INT != 27))
    {
      if (Build.VERSION.SDK_INT > 28)
        getWindow().setType(2038);
      else
        getWindow().setType(2003);
    }
    else
      getWindow().setType(2038);
    getWindow().setGravity(80);
    WindowManager.LayoutParams attributes = getWindow().getAttributes();
    attributes.alpha = 1.0F;
    attributes.dimAmount = 0.0F;
    getWindow().setAttributes(attributes);
    setCancelable(false);
    setCanceledOnTouchOutside(false);
    init();
  }
}
