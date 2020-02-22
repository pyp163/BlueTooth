package com.qx.qgbox.gamemouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.qx.qgbox.R;

public class MySeekBarDialog extends PopupWindow
{
  private Button btn_no;
  private Button btn_yes;
  private int radius = -1;
  private SeekBar spin1;
  private TextView tv_radius;

  public MySeekBarDialog(Context paramContext, int paramInt)
  {
    super(paramContext);
    View view = LayoutInflater.from(paramContext).inflate(R.layout.seekdialog, null);
    this.spin1 = ((SeekBar)view.findViewById(R.id.progress1));
    this.tv_radius = ((TextView)view.findViewById(R.id.tv_radius));
    setContentView(view);
    setWidth(-2);
    setHeight(-2);
    setFocusable(false);
    this.spin1.setProgress(paramInt);
    TextView localTextView = this.tv_radius;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(paramInt);
    localTextView.setText(localStringBuilder.toString());
    this.spin1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        TextView seekBarTextView = MySeekBarDialog.this.tv_radius;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        seekBarTextView.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.btn_yes = ((Button)view.findViewById(R.id.btn_yes));
    this.btn_no = ((Button)view.findViewById(R.id.btn_no));
    this.btn_yes.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        com.qx.qgbox.service.bluetoothdevmanager.radius = MySeekBarDialog.this.spin1.getProgress();
        newSetupdialog.setSPRadius(MySeekBarDialog.this.spin1.getProgress());
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
