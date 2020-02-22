package com.qx.qgbox.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.util.Locale;

public class setuprange extends PopupWindow
{
  Button btn_no;
  Button btn_yes;
  int languageflag = 0;
  int ldeadflag = 0;
  String locale = null;
  int lrflag = 0;
  SharedPreferences m = null;
  SharedPreferences mSharedPreferences = null;
  Context mcontext;
  int rdeadflag = 0;
  private SeekBar spin1;
  private TextView tView;
  private TextView v1;

  public setuprange(Context paramContext, int paramInt)
  {
    super(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.gprangedialog, null);
    this.mcontext = paramContext;
    setContentView(localView);
    setWidth(-2);
    setHeight(-2);
    this.spin1 = ((SeekBar)localView.findViewById(R.id.progress1));
    this.tView = ((TextView)localView.findViewById(R.id.titlet1));
    this.v1 = ((TextView)localView.findViewById(R.id.textView2));
    this.locale = Locale.getDefault().toString();
    this.lrflag = paramInt;
    this.mSharedPreferences = paramContext.getSharedPreferences("mlanguage", 0);
    this.languageflag = this.mSharedPreferences.getInt("mlanguage", -1);
    if (this.lrflag == 0)
      this.tView.setText(paramContext.getString(R.string.op31));
    else
      this.tView.setText(paramContext.getString(R.string.op32));
    this.m = paramContext.getSharedPreferences("deadflag", 0);
    if (this.lrflag == 0)
      this.ldeadflag = this.m.getInt("ldeadflag", -1);
    else
      this.rdeadflag = this.m.getInt("rdeadflag", -1);
    Object localObject;
    StringBuilder localStringBuilder;
    if (this.lrflag == 0)
    {
      if (this.ldeadflag == -1)
      {
        localObject = this.m.edit();
        ((SharedPreferences.Editor)localObject).putInt("ldeadflag", this.spin1.getProgress());
        ((SharedPreferences.Editor)localObject).commit();
        this.spin1.setProgress(0);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op33));
        localStringBuilder.append("0");
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
      if (this.ldeadflag != -1)
      {
        this.spin1.setProgress(this.ldeadflag);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op33));
        localStringBuilder.append(this.ldeadflag);
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
    }
    else
    {
      if (this.rdeadflag == -1)
      {
        localObject = this.m.edit();
        ((SharedPreferences.Editor)localObject).putInt("rdeadflag", this.spin1.getProgress());
        ((SharedPreferences.Editor)localObject).commit();
        this.spin1.setProgress(0);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op33));
        localStringBuilder.append("0");
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
      if (this.rdeadflag != -1)
      {
        this.spin1.setProgress(this.rdeadflag);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op33));
        localStringBuilder.append(this.rdeadflag);
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
    }
    this.spin1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = setuprange.this.v1;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(setuprange.this.mcontext.getString(R.string.op33));
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
    this.btn_yes = ((Button)localView.findViewById(R.id.btn_yes));
    this.btn_no = ((Button)localView.findViewById(R.id.btn_no));
    this.btn_yes.setText(this.mcontext.getString(R.string.sure));
    this.btn_no.setText(this.mcontext.getString(R.string.cancle));
    this.btn_yes.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (setuprange.this.lrflag == 0)
        {
          paramAnonymousView = setuprange.this.m.edit();
          paramAnonymousView.putInt("ldeadflag", setuprange.this.spin1.getProgress());
          paramAnonymousView.commit();
        }
        else
        {
          paramAnonymousView = setuprange.this.m.edit();
          paramAnonymousView.putInt("rdeadflag", setuprange.this.spin1.getProgress());
          paramAnonymousView.commit();
        }
        setuprange.this.dismiss();
      }
    });
    this.btn_no.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        setuprange.this.dismiss();
      }
    });
  }
}
