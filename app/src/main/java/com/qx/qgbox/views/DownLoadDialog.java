package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class DownLoadDialog extends Dialog
{
  private Context context;

  public DownLoadDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.my_progress_dialog, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(17170445);
    Log.i("my_tag", "---------DownLoadDialog----------");
  }

  public void show()
  {
    super.show();
  }
}
