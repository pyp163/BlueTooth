package com.qx.qgbox.gamemouse;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.qx.qgbox.R;

public class greenDialog extends AlertDialog
{
  private Context mContext;
  private SeekBar sb_speed;
  private TextView tv_cancel;
  private TextView tv_speed;
  private TextView tv_sure;

  public greenDialog(Context paramContext)
  {
    super(paramContext, R.style.custom_window_dialog);
    this.mContext = paramContext;
  }

  private void init()
  {
    this.sb_speed = ((SeekBar)findViewById(R.id.sb_speed));
    this.tv_speed = ((TextView)findViewById(R.id.tv_speed));
    this.tv_sure = ((TextView)findViewById(R.id.tv_sure));
    this.tv_cancel = ((TextView)findViewById(R.id.tv_cancel));
    if (newSetupdialog.mousespeed > 0)
      this.sb_speed.setProgress(newSetupdialog.mousespeed - 1);
    else
      this.sb_speed.setProgress(newSetupdialog.mousespeed);
    TextView localTextView = this.tv_speed;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(newSetupdialog.mousespeed);
    localTextView.setText(localStringBuilder.toString());
    this.sb_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = greenDialog.this.tv_speed;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt + 1);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.tv_sure.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        newSetupdialog.mousespeed = greenDialog.this.sb_speed.getProgress() + 1;
        greenDialog.this.dismiss();
      }
    });
    this.tv_cancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        greenDialog.this.dismiss();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.greendialog);
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
    setCancelable(false);
    setCanceledOnTouchOutside(false);
    init();
  }
}
