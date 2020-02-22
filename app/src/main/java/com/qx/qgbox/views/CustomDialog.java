package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialog extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private Button dialog_btn_cancel;
  private Button dialog_btn_sure;
  private ImageView dialog_icon;
  private TextView dialog_message;
  private TextView dialog_title;
  private Drawable icon;
  private boolean isBtnCancelVisible;
  private boolean isDangerGameDialoge;
  private String message;
  private String title;

  public CustomDialog(Context paramContext, String paramString1, String paramString2, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext);
    this.context = paramContext;
    this.title = paramString1;
    this.message = paramString2;
    this.icon = paramDrawable;
    this.isBtnCancelVisible = paramBoolean1;
    this.isDangerGameDialoge = paramBoolean2;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_dialog, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    this.dialog_icon = ((ImageView)findViewById(R.id.dialog_icon));
    this.dialog_title = ((TextView)findViewById(R.id.dialog_title));
    this.dialog_message = ((TextView)findViewById(R.id.dialog_message));
    this.dialog_btn_cancel = ((Button)findViewById(R.id.dialog_btn_cancel));
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
    if (!this.isBtnCancelVisible)
      this.dialog_btn_cancel.setVisibility(View.GONE);
    if (this.isDangerGameDialoge)
    {
      this.dialog_btn_cancel.setText(this.context.getResources().getString(R.string.no_continue));
      this.dialog_btn_sure.setText(this.context.getResources().getString(R.string.yes_continue));
      this.dialog_btn_sure.setTextColor(this.context.getResources().getColor(R.color.holo_red_dark));
      this.dialog_message.setTextColor(this.context.getResources().getColor(R.color.holo_yellow_dark));
    }
    this.dialog_btn_cancel.setOnClickListener(new clickListener(null));
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
    public abstract void doCancel();

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
      switch (paramView.getId())
      {
      default:
        return;
      case R.id.dialog_btn_sure:
        CustomDialog.this.clickListenerInterface.doConfirm();
        return;
      case R.id.dialog_btn_cancel:
      }
      CustomDialog.this.clickListenerInterface.doCancel();
    }
  }
}
