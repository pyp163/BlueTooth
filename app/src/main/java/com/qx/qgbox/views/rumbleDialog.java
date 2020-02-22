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

public class rumbleDialog extends PopupWindow
{
  Button btn_no;
  Button btn_yes;
  int languageflag = 0;
  String locale = null;
  SharedPreferences m = null;
  SharedPreferences mSharedPreferences = null;
  Context mcontext;
  int rmflag = 0;
  private SeekBar spin1;
  private TextView tView;
  private TextView v1;

  public rumbleDialog(Context paramContext)
  {
    super(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.gprumbledialog, null);
    this.mcontext = paramContext;
    setContentView(localView);
    setWidth(-2);
    setHeight(-2);
    this.spin1 = ((SeekBar)localView.findViewById(R.id.progress1));
    this.tView = ((TextView)localView.findViewById(R.id.titlet1));
    this.v1 = ((TextView)localView.findViewById(R.id.textView2));
    this.locale = Locale.getDefault().toString();
    this.mSharedPreferences = paramContext.getSharedPreferences("mlanguage", 0);
    this.languageflag = this.mSharedPreferences.getInt("mlanguage", -1);
    this.tView.setText(paramContext.getString(R.string.op29));
    this.m = paramContext.getSharedPreferences("rmflag", 0);
    this.rmflag = this.m.getInt("rmflag", -1);
    Object localObject;
    StringBuilder localStringBuilder;
    if (this.rmflag == -1)
    {
      localObject = this.m.edit();
      ((SharedPreferences.Editor)localObject).putInt("rmflag", this.spin1.getProgress());
      ((SharedPreferences.Editor)localObject).commit();
      this.spin1.setProgress(80);
      localObject = this.v1;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getString(R.string.op30));
      localStringBuilder.append("80%");
      ((TextView)localObject).setText(localStringBuilder.toString());
    }
    if (this.rmflag != -1)
    {
      this.spin1.setProgress(this.rmflag);
      localObject = this.v1;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getString(R.string.op30));
      localStringBuilder.append(this.rmflag);
      localStringBuilder.append("%");
      ((TextView)localObject).setText(localStringBuilder.toString());
    }
    this.spin1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = rumbleDialog.this.v1;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(rumbleDialog.this.mcontext.getString(R.string.op30));
        localStringBuilder.append(paramAnonymousInt);
        localStringBuilder.append("%");
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
        paramAnonymousView = rumbleDialog.this.m.edit();
        paramAnonymousView.putInt("rmflag", rumbleDialog.this.spin1.getProgress());
        paramAnonymousView.commit();
        rumbleDialog.this.dismiss();
      }
    });
    this.btn_no.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        rumbleDialog.this.dismiss();
      }
    });
  }
}
