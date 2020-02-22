package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class CustomTipDialog2 extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private Button dialog_btn_sure;
  private TextView dialog_message;
  private TextView dialog_title;
  private String message;
  private String title;

  public CustomTipDialog2(Context paramContext, String paramString1, String paramString2)
  {
    super(paramContext);
    this.context = paramContext;
    this.title = paramString1;
    this.message = paramString2;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_tip_dialog2, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(17170445);
    this.dialog_title = ((TextView)findViewById(R.id.dialog_title));
    this.dialog_message = ((TextView)findViewById(R.id.dialog_message));
    this.dialog_btn_sure = ((Button)findViewById(R.id.dialog_btn_sure));
    if (this.title != null)
      this.dialog_title.setText(this.title);
    else
      this.dialog_title.setVisibility(8);
    paramBundle = this.dialog_message;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(this.message);
    paramBundle.setText(localStringBuilder.toString());
    this.dialog_btn_sure.setOnClickListener(new clickListener(null));
  }

  public void setClicklistener(ClickListenerInterface paramClickListenerInterface)
  {
    this.clickListenerInterface = paramClickListenerInterface;
  }

  public void show()
  {
    super.show();
  }

  public static abstract interface ClickListenerInterface
  {
    public abstract void doConfirm();
  }

  private class clickListener
    implements View.OnClickListener
  {
    private clickListener()
    {
    }

    public void onClick(View paramView)
    {
      if (paramView.getId() != R.id.dialog_btn_sure)
        return;
      CustomTipDialog2.this.clickListenerInterface.doConfirm();
    }
  }
}
