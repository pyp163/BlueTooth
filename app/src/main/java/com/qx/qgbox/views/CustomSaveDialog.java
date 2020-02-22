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

public class CustomSaveDialog extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private Button dialog_btn_cancel;
  private Button dialog_btn_sure;

  public CustomSaveDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.savedialog1, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(17170445);
    this.dialog_btn_cancel = ((Button)findViewById(R.id.dialog_btn_cancel));
    this.dialog_btn_sure = ((Button)findViewById(R.id.dialog_btn_sure));
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
        CustomSaveDialog.this.clickListenerInterface.doConfirm();
        return;
      case R.id.dialog_btn_cancel:
      }
      CustomSaveDialog.this.clickListenerInterface.doCancel();
    }
  }
}
