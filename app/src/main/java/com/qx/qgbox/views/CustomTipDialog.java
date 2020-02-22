package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomTipDialog extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private Button dialog_btn_sure;
  private ImageView dialog_icon;
  private TextView dialog_message;
  private TextView dialog_title;
  private Drawable icon;
  private String message;
  private String title;

  public CustomTipDialog(Context paramContext, String paramString1, String paramString2, Drawable paramDrawable)
  {
    super(paramContext);
    this.context = paramContext;
    this.title = paramString1;
    this.message = paramString2;
    this.icon = paramDrawable;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_tip_dialog, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    this.dialog_icon = ((ImageView)findViewById(R.id.dialog_icon));
    this.dialog_title = ((TextView)findViewById(R.id.dialog_title));
    this.dialog_message = ((TextView)findViewById(R.id.dialog_message));
    this.dialog_btn_sure = ((Button)findViewById(R.id.dialog_btn_sure));
    if (this.title != null)
    {
      this.dialog_icon.setImageDrawable(this.icon);
      this.dialog_title.setText(this.title);
    }
    else
    {
      this.dialog_icon.setVisibility(View.GONE);
      this.dialog_title.setVisibility(View.GONE);
    }
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
      if (paramView.getId() != R.id.dialog_btn_sure)//dialog_btn_sure
        return;
      CustomTipDialog.this.clickListenerInterface.doConfirm();
    }
  }
}
