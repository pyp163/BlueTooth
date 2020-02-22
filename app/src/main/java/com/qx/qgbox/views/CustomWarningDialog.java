package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.qgbox.R;

public class CustomWarningDialog extends Dialog
{
  public CustomWarningDialog(Context paramContext)
  {
    super(paramContext);
  }

  public CustomWarningDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  public static class Builder
  {
    private DialogInterface.OnClickListener cancel_btnClickListener;
    private String cancel_btnText;
    private DialogInterface.OnClickListener confirm_btnClickListener;
    private String confirm_btnText;
    private View contentView;
    private Context context;
    private String message;
    private DialogInterface.OnClickListener neutral_btnClickListener;
    private String neutral_btnText;
    private String title;

    public Builder(Context paramContext)
    {
      this.context = paramContext;
    }

    public CustomWarningDialog create()
    {
      Object localObject = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      final CustomWarningDialog localCustomWarningDialog = new CustomWarningDialog(this.context, R.style.mystyle);
      localObject = ((LayoutInflater)localObject).inflate(R.layout.customwarningdialog, null);
      localCustomWarningDialog.addContentView((View)localObject, new ViewGroup.LayoutParams(-1, -2));
      ((TextView)((View)localObject).findViewById(R.id.title)).setText(this.title);
      ((TextView)((View)localObject).findViewById(R.id.title)).getPaint().setFakeBoldText(true);
      if ((this.title == null) || (this.title.trim().length() == 0))
        ((TextView)((View)localObject).findViewById(R.id.my_message)).setGravity(17);
      if ((this.neutral_btnText != null) && (this.confirm_btnText != null) && (this.cancel_btnText != null))
      {
        ((Button)((View)localObject).findViewById(R.id.confirm_btn)).setText(this.confirm_btnText);
        if (this.neutral_btnClickListener != null)
          ((Button)((View)localObject).findViewById(R.id.neutral_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              CustomWarningDialog.Builder.this.neutral_btnClickListener.onClick(localCustomWarningDialog, -3);
            }
          });
        else
          ((Button)((View)localObject).findViewById(R.id.neutral_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              localCustomWarningDialog.dismiss();
            }
          });
      }
      else
      {
        ((View)localObject).findViewById(R.id.neutral_btn).setVisibility(View.GONE);
        ((View)localObject).findViewById(R.id.single_line).setVisibility(View.GONE);
      }
      if (this.confirm_btnText != null)
      {
        ((Button)((View)localObject).findViewById(R.id.confirm_btn)).setText(this.confirm_btnText);
        if (this.confirm_btnClickListener != null)
          ((Button)((View)localObject).findViewById(R.id.confirm_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              CustomWarningDialog.Builder.this.confirm_btnClickListener.onClick(localCustomWarningDialog, -1);
            }
          });
        else
          ((Button)((View)localObject).findViewById(R.id.confirm_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              localCustomWarningDialog.dismiss();
            }
          });
      }
      else
      {
        ((View)localObject).findViewById(R.id.confirm_btn).setVisibility(View.GONE);
        ((View)localObject).findViewById(R.id.second_line).setVisibility(View.GONE);
        ((View)localObject).findViewById(R.id.cancel_btn).setBackgroundResource(R.drawable.single_btn_select);
      }
      if (this.cancel_btnText != null)
      {
        ((Button)((View)localObject).findViewById(R.id.cancel_btn)).setText(this.cancel_btnText);
        if (this.cancel_btnClickListener != null)
          ((Button)((View)localObject).findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              CustomWarningDialog.Builder.this.cancel_btnClickListener.onClick(localCustomWarningDialog, -2);
            }
          });
        else
          ((Button)((View)localObject).findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              localCustomWarningDialog.dismiss();
            }
          });
      }
      else
      {
        ((View)localObject).findViewById(R.id.cancel_btn).setVisibility(View.GONE);
        ((View)localObject).findViewById(R.id.second_line).setVisibility(View.GONE);
        ((View)localObject).findViewById(R.id.confirm_btn).setBackgroundResource(R.drawable.single_btn_select);
      }
      if (this.message != null)
      {
        ((TextView)((View)localObject).findViewById(R.id.my_message)).setText(this.message);
      }
      else if (this.contentView != null)
      {
        ((LinearLayout)((View)localObject).findViewById(R.id.my_message)).removeAllViews();
        ((LinearLayout)((View)localObject).findViewById(R.id.my_message)).addView(this.contentView, new ViewGroup.LayoutParams(-2, -2));
      }
      localCustomWarningDialog.setContentView((View)localObject);
      return localCustomWarningDialog;
    }

    public Builder setContentView(View paramView)
    {
      this.contentView = paramView;
      return this;
    }

    public Builder setMessage(int paramInt)
    {
      this.message = ((String)this.context.getText(paramInt));
      return this;
    }

    public Builder setMessage(String paramString)
    {
      this.message = paramString;
      return this;
    }

    public Builder setMessageById(int paramInt)
    {
      this.message = ((String)this.context.getText(paramInt));
      return this;
    }

    public Builder setNegativeButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.cancel_btnText = ((String)this.context.getText(paramInt));
      this.cancel_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setNegativeButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.cancel_btnText = paramString;
      this.cancel_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setNeutralButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.neutral_btnText = ((String)this.context.getText(paramInt));
      this.neutral_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setNeutralButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.neutral_btnText = paramString;
      this.neutral_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setPositiveButton(int paramInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.confirm_btnText = ((String)context.getText(paramInt));
      this.confirm_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setPositiveButton(String paramString, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.confirm_btnText = paramString;
      this.confirm_btnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setTitle(int paramInt)
    {
      this.title = ((String)this.context.getText(paramInt));
      return this;
    }

    public Builder setTitle(String paramString)
    {
      this.title = paramString;
      return this;
    }
  }
}
