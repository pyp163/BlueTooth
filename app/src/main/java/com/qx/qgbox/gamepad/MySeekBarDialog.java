package com.qx.qgbox.gamepad;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.qx.qgbox.views.ToastDialog;

public class MySeekBarDialog extends PopupWindow
{
  Button btn_no;
  Button btn_yes;
  int flaglr = 0;
  private Context mContext;
  int property = -1;
  RadioButton rbtn1;
  RadioButton rbtn2;
  private TextView s1;
  private TextView s2;
  private SeekBar spin1;
  private SeekBar spin2;
  private TextView tView;
  private TextView v1;
  private TextView v2;

  public MySeekBarDialog(Context paramContext, DataSaver[] paramArrayOfDataSaver, int paramInt)
  {
    super(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.gpseekdialog, null);
    this.mContext = paramContext;
    this.spin1 = ((SeekBar)localView.findViewById(R.id.progress1));
    this.spin2 = ((SeekBar)localView.findViewById(R.id.progress2));
    this.tView = ((TextView)localView.findViewById(R.id.titlet1));
    this.v1 = ((TextView)localView.findViewById(R.id.ratio));
    this.v2 = ((TextView)localView.findViewById(R.id.speedview));
    this.rbtn1 = ((RadioButton)localView.findViewById(R.id.radioButton1));
    this.rbtn2 = ((RadioButton)localView.findViewById(R.id.radioButton2));
    this.s1 = ((TextView)localView.findViewById(R.id.textView1));
    this.s2 = ((TextView)localView.findViewById(R.id.textView2));
    setContentView(localView);
    setWidth(-2);
    setHeight(-2);
    setFocusable(false);
    this.flaglr = paramInt;
    this.rbtn1.setText(paramContext.getString(R.string.op34));
    this.rbtn2.setText(paramContext.getString(R.string.op35));
    this.s1.setText(paramContext.getString(R.string.op36));
    this.s2.setText(paramContext.getString(R.string.op37));
    TextView localTextView;
    StringBuilder localStringBuilder;
    if (this.flaglr == 0)
    {
      this.tView.setText(paramContext.getString(R.string.op27));
      if (paramArrayOfDataSaver[16].radius != 0)
      {
        this.rbtn1.setChecked(true);
        this.spin1.setEnabled(true);
        localTextView = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[16].radius);
        localTextView.setText(localStringBuilder.toString());
        this.spin1.setProgress(paramArrayOfDataSaver[16].radius);
        this.rbtn2.setChecked(false);
        this.spin2.setEnabled(false);
        this.spin2.setProgress(paramArrayOfDataSaver[16].block);
        localTextView = this.v2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[16].block);
        localTextView.setText(localStringBuilder.toString());
      }
      else
      {
        this.rbtn1.setChecked(false);
        this.spin1.setEnabled(false);
        localTextView = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[16].radius);
        localTextView.setText(localStringBuilder.toString());
        this.spin1.setProgress(paramArrayOfDataSaver[16].radius);
        this.rbtn2.setChecked(true);
        this.spin2.setEnabled(true);
        this.spin2.setProgress(paramArrayOfDataSaver[16].block);
        localTextView = this.v2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[16].block);
        localTextView.setText(localStringBuilder.toString());
      }
    }
    if (this.flaglr == 1)
    {
      this.tView.setText(paramContext.getString(R.string.op28));
      if (paramArrayOfDataSaver[17].radius != 0)
      {
        this.rbtn1.setChecked(true);
        this.spin1.setEnabled(true);
        localTextView = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[17].radius);
        localTextView.setText(localStringBuilder.toString());
        this.spin1.setProgress(paramArrayOfDataSaver[17].radius);
        this.rbtn2.setChecked(false);
        this.spin2.setEnabled(false);
        this.spin2.setProgress(paramArrayOfDataSaver[17].block);
        localTextView = this.v2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[17].block);
        localTextView.setText(localStringBuilder.toString());
      }
      else
      {
        this.rbtn1.setChecked(false);
        this.spin1.setEnabled(false);
        localTextView = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[17].radius);
        localTextView.setText(localStringBuilder.toString());
        this.spin1.setProgress(paramArrayOfDataSaver[17].radius);
        this.rbtn2.setChecked(true);
        this.spin2.setEnabled(true);
        this.spin2.setProgress(paramArrayOfDataSaver[17].block);
        localTextView = this.v2;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramArrayOfDataSaver[17].block);
        localTextView.setText(localStringBuilder.toString());
      }
    }
    this.spin1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = MySeekBarDialog.this.v1;
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
    this.spin2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = MySeekBarDialog.this.v2;
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
    this.rbtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MySeekBarDialog.this.rbtn2.setChecked(false);
          MySeekBarDialog.this.spin2.setEnabled(false);
          MySeekBarDialog.this.spin1.setEnabled(true);
        }
      }
    });
    this.rbtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MySeekBarDialog.this.rbtn1.setChecked(false);
          MySeekBarDialog.this.spin1.setEnabled(false);
          MySeekBarDialog.this.spin2.setEnabled(true);
        }
      }
    });
    this.btn_yes = ((Button)localView.findViewById(R.id.btn_yes));
    this.btn_no = ((Button)localView.findViewById(R.id.btn_no));
    this.btn_yes.setText(paramContext.getString(R.string.sure));
    this.btn_no.setText(paramContext.getString(R.string.cancle));
    this.btn_yes.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MySeekBarDialog.this.rbtn1.isChecked())
          if (MySeekBarDialog.this.spin1.getProgress() <= 0)
          {
            new ToastDialog(MySeekBarDialog.this.mContext, MySeekBarDialog.this.mContext.getResources().getString(R.string.gp_setup_dialog_tip8)).show();
          }
          else if (MySeekBarDialog.this.flaglr == 0)
          {
            gpsetupdialog.mdatasaver[16].radius = MySeekBarDialog.this.spin1.getProgress();
            gpsetupdialog.mdatasaver[16].block = 0;
            gpsetupdialog.mdatasaver[16].reverse = 0;
          }
          else
          {
            gpsetupdialog.mdatasaver[17].radius = MySeekBarDialog.this.spin1.getProgress();
            gpsetupdialog.mdatasaver[17].block = 0;
            gpsetupdialog.mdatasaver[17].reverse = 0;
          }
        if (MySeekBarDialog.this.rbtn2.isChecked())
          if (MySeekBarDialog.this.flaglr == 0)
          {
            gpsetupdialog.mdatasaver[16].radius = 0;
            gpsetupdialog.mdatasaver[16].block = MySeekBarDialog.this.spin2.getProgress();
            gpsetupdialog.mdatasaver[16].reverse = 0;
          }
          else
          {
            gpsetupdialog.mdatasaver[17].radius = 0;
            gpsetupdialog.mdatasaver[17].block = MySeekBarDialog.this.spin2.getProgress();
            gpsetupdialog.mdatasaver[17].reverse = 0;
          }
        MySeekBarDialog.this.dismiss();
      }
    });
    this.btn_no.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MySeekBarDialog.this.dismiss();
      }
    });
  }
}
