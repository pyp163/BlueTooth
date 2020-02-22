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

public class setuplr extends PopupWindow
{
  Button btn_no;
  Button btn_yes;
  int l2deadflag = 0;
  int languageflag = 0;
  String locale = null;
  int lrflag = 0;
  SharedPreferences m = null;
  SharedPreferences mSharedPreferences = null;
  Context mcontext;
  int r2deadflag = 0;
  private SeekBar spin1;
  private TextView tView;
  private TextView v1;

  public setuplr(Context paramContext, int paramInt)
  {
    super(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.gplrdialog, null);
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
      this.tView.setText(paramContext.getString(R.string.op49));
    else
      this.tView.setText(paramContext.getString(R.string.op50));
    this.m = paramContext.getSharedPreferences("lrdeadflag", 0);
    if (this.lrflag == 0)
      this.l2deadflag = this.m.getInt("l2deadflag", -1);
    else
      this.r2deadflag = this.m.getInt("r2deadflag", -1);
    Object localObject;
    StringBuilder localStringBuilder;
    if (this.lrflag == 0)
    {
      if (this.l2deadflag == -1)
      {
        localObject = this.m.edit();
        ((SharedPreferences.Editor)localObject).putInt("l2deadflag", this.spin1.getProgress());
        ((SharedPreferences.Editor)localObject).commit();
        this.spin1.setProgress(0);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op51));
        localStringBuilder.append("0");
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
      if (this.l2deadflag != -1)
      {
        this.spin1.setProgress(this.l2deadflag);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op51));
        localStringBuilder.append(this.l2deadflag);
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
    }
    else
    {
      if (this.r2deadflag == -1)
      {
        localObject = this.m.edit();
        ((SharedPreferences.Editor)localObject).putInt("rdeadflag", this.spin1.getProgress());
        ((SharedPreferences.Editor)localObject).commit();
        this.spin1.setProgress(0);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op51));
        localStringBuilder.append("0");
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
      if (this.r2deadflag != -1)
      {
        this.spin1.setProgress(this.r2deadflag);
        localObject = this.v1;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext.getString(R.string.op51));
        localStringBuilder.append(this.r2deadflag);
        ((TextView)localObject).setText(localStringBuilder.toString());
      }
    }
    this.spin1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = setuplr.this.v1;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(setuplr.this.mcontext.getString(R.string.op51));
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
        if (setuplr.this.lrflag == 0)
        {
          paramAnonymousView = setuplr.this.m.edit();
          paramAnonymousView.putInt("l2deadflag", setuplr.this.spin1.getProgress());
          paramAnonymousView.commit();
        }
        else
        {
          paramAnonymousView = setuplr.this.m.edit();
          paramAnonymousView.putInt("r2deadflag", setuplr.this.spin1.getProgress());
          paramAnonymousView.commit();
        }
        setuplr.this.dismiss();
      }
    });
    this.btn_no.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        setuplr.this.dismiss();
      }
    });
  }
}
