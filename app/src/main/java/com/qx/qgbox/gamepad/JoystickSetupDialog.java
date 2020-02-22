package com.qx.qgbox.gamepad;

import android.content.Context;
import android.content.res.Resources;
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
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.views.ToastDialog;

public class JoystickSetupDialog extends PopupWindow
{
  private Button btn_no;
  private Button btn_yes;
  int flaglr = 0;
  private LinearLayout ll_setup;
  private LinearLayout ll_setup2;
  private LinearLayout llviewjoystick;
  private Context mContext;
  private int myScreenFlag = 0;
  private SeekBar progress1;
  private SeekBar progress2;
  private SeekBar progress3;
  private SeekBar progress4;
  int property = -1;
  private RadioButton radioButton1;
  private RadioButton radioButton2;
  private RadioButton radioButton3;
  private RadioButton radioButton4;
  private TextView ratio;
  private TextView speedview;
  private TextView speedview2;
  private TextView speedview3;
  private TextView titlet1;

  public JoystickSetupDialog(Context paramContext, DataSaver[] paramArrayOfDataSaver, int paramInt1, int paramInt2)
  {
    super(paramContext);
    Object localObject = LayoutInflater.from(paramContext).inflate(R.layout.gpjoysticksetupdialog, null);
    this.mContext = paramContext;
    this.myScreenFlag = paramInt2;
    this.titlet1 = ((TextView)((View)localObject).findViewById(R.id.titlet1));
    this.btn_yes = ((Button)((View)localObject).findViewById(R.id.btn_yes));
    this.btn_no = ((Button)((View)localObject).findViewById(R.id.btn_no));
    this.btn_yes.setText(paramContext.getString(R.string.sure));
    this.btn_no.setText(paramContext.getString(R.string.cancle));
    this.radioButton2 = ((RadioButton)((View)localObject).findViewById(R.id.radioButton2));
    this.speedview = ((TextView)((View)localObject).findViewById(R.id.speedview));
    this.progress2 = ((SeekBar)((View)localObject).findViewById(R.id.progress2));
    this.ll_setup = ((LinearLayout)((View)localObject).findViewById(R.id.ll_setup));
    this.ll_setup2 = ((LinearLayout)((View)localObject).findViewById(R.id.ll_setup2));
    this.radioButton1 = ((RadioButton)((View)localObject).findViewById(R.id.radioButton1));
    this.ratio = ((TextView)((View)localObject).findViewById(R.id.ratio));
    this.progress1 = ((SeekBar)((View)localObject).findViewById(R.id.progress1));
    this.radioButton3 = ((RadioButton)((View)localObject).findViewById(R.id.radioButton3));
    this.speedview2 = ((TextView)((View)localObject).findViewById(R.id.speedview2));
    this.progress3 = ((SeekBar)((View)localObject).findViewById(R.id.progress3));
    this.radioButton4 = ((RadioButton)((View)localObject).findViewById(R.id.radioButton4));
    this.speedview3 = ((TextView)((View)localObject).findViewById(R.id.speedview3));
    this.progress4 = ((SeekBar)((View)localObject).findViewById(R.id.progress4));
    this.llviewjoystick = ((LinearLayout)((View)localObject).findViewById(R.id.llviewjoystick));
    setContentView((View)localObject);
    setWidth(-2);
    setHeight(-2);
    setFocusable(false);
    if (this.myScreenFlag == 1)
      this.llviewjoystick.setVisibility(8);
    else
      this.llviewjoystick.setVisibility(0);
    this.flaglr = paramInt1;
    if (this.flaglr == 22)
      this.titlet1.setText(paramContext.getString(R.string.op27));
    if (this.flaglr == 23)
      this.titlet1.setText(paramContext.getString(R.string.op28));
    if (this.flaglr == 24)
      this.titlet1.setText(paramContext.getString(R.string.op27));
    if (this.flaglr == 25)
      this.titlet1.setText(paramContext.getString(R.string.op28));
    if (this.flaglr == 26)
      this.titlet1.setText(paramContext.getString(R.string.op27));
    paramContext = new StringBuilder();
    paramContext.append("------ property = ");
    paramContext.append(paramArrayOfDataSaver[this.flaglr].property);
    MyLog.i("my_tag", paramContext.toString());
    if (paramArrayOfDataSaver[this.flaglr].property == 11)
    {
      this.radioButton2.setChecked(true);
      this.progress2.setEnabled(true);
      paramContext = this.speedview;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramArrayOfDataSaver[this.flaglr].radius);
      paramContext.setText(((StringBuilder)localObject).toString());
      this.progress2.setProgress(paramArrayOfDataSaver[this.flaglr].radius);
      this.ll_setup.setVisibility(8);
      this.radioButton1.setChecked(false);
      this.progress1.setEnabled(false);
      this.progress1.setProgress(0);
      this.ratio.setText("0");
    }
    else
    {
      this.radioButton2.setChecked(false);
      this.progress2.setEnabled(false);
      this.speedview.setText("0");
      this.progress2.setProgress(0);
      this.ll_setup.setVisibility(0);
      this.ll_setup2.setVisibility(8);
      this.radioButton1.setChecked(true);
      this.progress1.setEnabled(true);
      this.progress1.setProgress(paramArrayOfDataSaver[this.flaglr].radius);
      paramContext = this.ratio;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramArrayOfDataSaver[this.flaglr].radius);
      paramContext.setText(((StringBuilder)localObject).toString());
      this.progress3.setProgress(paramArrayOfDataSaver[this.flaglr].reverse & 0xFF);
      paramContext = this.speedview2;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramArrayOfDataSaver[this.flaglr].reverse & 0xFF);
      paramContext.setText(((StringBuilder)localObject).toString());
      paramContext = new StringBuilder();
      paramContext.append("----半径 = ");
      paramContext.append(paramArrayOfDataSaver[this.flaglr].radius);
      MyLog.i("my_tag", paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append("----速度 = ");
      paramContext.append(paramArrayOfDataSaver[this.flaglr].reverse & 0xFF);
      MyLog.i("my_tag", paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append("----属性prop = ");
      paramContext.append(paramArrayOfDataSaver[this.flaglr].block & 0xFF);
      MyLog.i("my_tag", paramContext.toString());
      if ((paramArrayOfDataSaver[this.flaglr].block & 0xFF) >= 128)
      {
        this.radioButton3.setChecked(true);
        this.radioButton4.setChecked(false);
        this.ll_setup2.setVisibility(0);
        paramContext = this.speedview3;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("");
        ((StringBuilder)localObject).append((paramArrayOfDataSaver[this.flaglr].block & 0xFF) - 128);
        paramContext.setText(((StringBuilder)localObject).toString());
        this.progress4.setProgress((paramArrayOfDataSaver[this.flaglr].block & 0xFF) - 128);
      }
      else
      {
        this.radioButton3.setChecked(false);
        this.radioButton4.setChecked(true);
        this.ll_setup2.setVisibility(8);
        this.speedview3.setText("0");
        this.progress4.setProgress(0);
      }
    }
    this.progress2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = JoystickSetupDialog.this.speedview;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.progress1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = JoystickSetupDialog.this.ratio;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.progress3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = JoystickSetupDialog.this.speedview2;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.progress4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = JoystickSetupDialog.this.speedview3;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          JoystickSetupDialog.this.radioButton1.setChecked(false);
          JoystickSetupDialog.this.progress1.setEnabled(false);
          JoystickSetupDialog.this.progress2.setEnabled(true);
          JoystickSetupDialog.this.ll_setup.setVisibility(8);
        }
      }
    });
    this.radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          JoystickSetupDialog.this.radioButton2.setChecked(false);
          JoystickSetupDialog.this.progress2.setEnabled(false);
          JoystickSetupDialog.this.progress1.setEnabled(true);
          JoystickSetupDialog.this.ll_setup.setVisibility(0);
        }
      }
    });
    this.radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          JoystickSetupDialog.this.radioButton4.setChecked(false);
          JoystickSetupDialog.this.progress4.setEnabled(true);
          JoystickSetupDialog.this.ll_setup2.setVisibility(0);
        }
      }
    });
    this.radioButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          JoystickSetupDialog.this.radioButton3.setChecked(false);
          JoystickSetupDialog.this.progress4.setEnabled(false);
          JoystickSetupDialog.this.ll_setup2.setVisibility(8);
        }
      }
    });
    this.btn_yes.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (JoystickSetupDialog.this.radioButton1.isChecked())
        {
          gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].property = 12;
          if (JoystickSetupDialog.this.progress1.getProgress() <= 0)
          {
            new ToastDialog(JoystickSetupDialog.this.mContext, JoystickSetupDialog.this.mContext.getResources().getString(R.string.gp_setup_dialog_tip8)).show();
          }
          else
          {
            gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].radius = JoystickSetupDialog.this.progress1.getProgress();
            if (JoystickSetupDialog.this.radioButton3.isChecked())
              gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].block = (JoystickSetupDialog.this.progress4.getProgress() + 128);
            else if (JoystickSetupDialog.this.radioButton4.isChecked())
              gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].block = 0;
            gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].reverse = JoystickSetupDialog.this.progress3.getProgress();
          }
        }
        if (JoystickSetupDialog.this.radioButton2.isChecked())
        {
          gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].property = 11;
          gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].radius = JoystickSetupDialog.this.progress2.getProgress();
          gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].block = 0;
          gpsetupdialog.mdatasaver[JoystickSetupDialog.this.flaglr].reverse = 0;
        }
        JoystickSetupDialog.this.dismiss();
      }
    });
    this.btn_no.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        JoystickSetupDialog.this.dismiss();
      }
    });
  }
}
