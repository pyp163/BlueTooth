package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class CustomTipDialog3 extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private Button dialog_btn_sure;

  public CustomTipDialog3(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_tip_dialog3, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    this.dialog_btn_sure = ((Button)findViewById(R.id.dialog_btn_sure));
    this.dialog_btn_sure.setOnClickListener(new clickListener());
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
      CustomTipDialog3.this.clickListenerInterface.doConfirm();
    }
  }
}
