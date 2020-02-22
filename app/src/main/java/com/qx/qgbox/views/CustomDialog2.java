package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.qx.qgbox.R;

public class CustomDialog2 extends Dialog
{
  public static TasksCompletedView my_pb;

  public CustomDialog2(Context paramContext)
  {
    super(paramContext);
  }

  public CustomDialog2(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  public void setProgress(int paramInt)
  {
    my_pb.setProgress(paramInt);
  }

  public static class Builder
  {
    private Context context;

    public Builder(Context paramContext)
    {
      this.context = paramContext;
    }

    public CustomDialog2 create()
    {
      Object localObject = (LayoutInflater)this.context.getSystemService("layout_inflater");
      CustomDialog2 localCustomDialog2 = new CustomDialog2(this.context, R.style.custom_dialog2_style);
      localObject = ((LayoutInflater)localObject).inflate(R.layout.customdialog2, null);
      localCustomDialog2.addContentView((View)localObject, new ViewGroup.LayoutParams(-2, -2));
      CustomDialog2.my_pb = (TasksCompletedView)((View)localObject).findViewById(R.id.my_pb);
      localCustomDialog2.setContentView((View)localObject);
      return localCustomDialog2;
    }
  }
}
