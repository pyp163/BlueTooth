package com.qx.qgbox.views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Window;

public class newLoadingDialog extends AlertDialog
{
  private Context mContext;

  public newLoadingDialog(Context paramContext)
  {
    super(paramContext, R.style.custom_window_dialog);
    this.mContext = paramContext;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.my_progress_dialog4);
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
    paramBundle = getWindow().getAttributes();
    paramBundle.alpha = 1.0F;
    paramBundle.dimAmount = 0.0F;
    getWindow().setAttributes(paramBundle);
    setCancelable(false);
    setCanceledOnTouchOutside(false);
  }
}
