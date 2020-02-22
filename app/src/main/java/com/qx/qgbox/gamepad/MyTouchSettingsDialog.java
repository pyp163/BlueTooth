package com.qx.qgbox.gamepad;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.utils.MyPreferencesService;

public class MyTouchSettingsDialog extends PopupWindow
{
  private Button btn_cancel;
  private Button btn_sure;
  private LinearLayout ll_view;
  private MyPreferencesService mMyPreferencesService = null;
  private SeekBar pg_eyeview;
  private RadioButton rbtn_abxy;
  private RadioButton rbtn_eyeview;
  private int touchMode = 10001;
  private int touchRatio = -1;
  private TextView tv_ratio;

  public MyTouchSettingsDialog(Context paramContext)
  {
    super(paramContext);
    Object localObject = LayoutInflater.from(paramContext).inflate(R.layout.gptouchsettingsdialog, null);
    this.pg_eyeview = ((SeekBar)((View)localObject).findViewById(R.id.pg_eyeview));
    this.tv_ratio = ((TextView)((View)localObject).findViewById(R.id.tv_ratio));
    this.rbtn_eyeview = ((RadioButton)((View)localObject).findViewById(R.id.rbtn_eyeview));
    this.rbtn_abxy = ((RadioButton)((View)localObject).findViewById(R.id.rbtn_abxy));
    this.ll_view = ((LinearLayout)((View)localObject).findViewById(R.id.ll_view));
    this.btn_sure = ((Button)((View)localObject).findViewById(R.id.btn_sure));
    this.btn_cancel = ((Button)((View)localObject).findViewById(R.id.btn_cancel));
    setContentView((View)localObject);
    setWidth(-2);
    setHeight(-2);
    setFocusable(false);
    this.mMyPreferencesService = new MyPreferencesService(paramContext);
    if (this.touchMode == 10001)
    {
      this.ll_view.setVisibility(0);
      this.rbtn_eyeview.setChecked(true);
      this.rbtn_abxy.setChecked(false);
    }
    else if (this.touchMode == 10002)
    {
      this.ll_view.setVisibility(0);
      this.rbtn_eyeview.setChecked(false);
      this.rbtn_abxy.setChecked(true);
    }
    if (this.touchRatio == -1)
    {
      this.pg_eyeview.setProgress(1);
    }
    else
    {
      paramContext = this.tv_ratio;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(this.touchRatio + 1);
      paramContext.setText(((StringBuilder)localObject).toString());
      this.pg_eyeview.setProgress(this.touchRatio);
    }
    this.pg_eyeview.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = MyTouchSettingsDialog.this.tv_ratio;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt + 1);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
        MyTouchSettingsDialog.access$102(MyTouchSettingsDialog.this, paramAnonymousInt);
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.rbtn_eyeview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MyTouchSettingsDialog.this.ll_view.setVisibility(0);
          MyTouchSettingsDialog.this.rbtn_abxy.setChecked(false);
          MyTouchSettingsDialog.access$402(MyTouchSettingsDialog.this, 10001);
        }
      }
    });
    this.rbtn_abxy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MyTouchSettingsDialog.this.ll_view.setVisibility(0);
          MyTouchSettingsDialog.this.rbtn_eyeview.setChecked(false);
          MyTouchSettingsDialog.access$402(MyTouchSettingsDialog.this, 10002);
        }
      }
    });
    this.btn_sure.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyTouchSettingsDialog.this.rbtn_eyeview.isChecked())
        {
          if (MyTouchSettingsDialog.this.touchRatio == 0)
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, BlueCmdManager.TOUCH_MODE_EYE_VIEW_RATIO_1);
          else if (MyTouchSettingsDialog.this.touchRatio == 1)
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, BlueCmdManager.TOUCH_MODE_EYE_VIEW_RATIO_2);
          else if (MyTouchSettingsDialog.this.touchRatio == 2)
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, BlueCmdManager.TOUCH_MODE_EYE_VIEW_RATIO_3);
          else
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, BlueCmdManager.TOUCH_MODE_EYE_VIEW_RATIO_4);
          if (gpsetupdialog.myhandler != null)
            gpsetupdialog.myhandler.sendEmptyMessage(20009);
        }
        MyTouchSettingsDialog.this.rbtn_abxy.isChecked();
        MyTouchSettingsDialog.this.dismiss();
      }
    });
    this.btn_cancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyTouchSettingsDialog.this.dismiss();
      }
    });
  }
}
