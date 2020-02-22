package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class LoadingAPPDialog extends Dialog
{
  public static TasksCompletedView my_pb;

  public LoadingAPPDialog(Context paramContext)
  {
    super(paramContext);
  }

  public LoadingAPPDialog(Context paramContext, int paramInt)
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

    public LoadingAPPDialog create()
    {
      Object localObject = (LayoutInflater)this.context.getSystemService("layout_inflater");
      LoadingAPPDialog localLoadingAPPDialog = new LoadingAPPDialog(this.context, R.style.custom_dialog2_style);
      localObject = ((LayoutInflater)localObject).inflate(R.layout.download_app_file_dialog, null);
      localLoadingAPPDialog.addContentView((View)localObject, new ViewGroup.LayoutParams(-2, -2));
      LoadingAPPDialog.my_pb = (TasksCompletedView)((View)localObject).findViewById(R.id.my_pb);
      localLoadingAPPDialog.setContentView((View)localObject);
      return localLoadingAPPDialog;
    }
  }
}
