package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Window;

public class LoadingDialog extends Dialog
{
  private Context context;

  public LoadingDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.loading_dialog);
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

  public void show()
  {
    super.show();
  }
}
