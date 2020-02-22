package com.qx.qgbox.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import com.qx.qgbox.service.FloatWindowService;

public class FloatWindowBigView extends LinearLayout
{
  public static int viewHeight;
  public static int viewWidth;

  public FloatWindowBigView(final Context paramContext)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(R.layout.float_window_big, this);
    Object localObject = findViewById(R.id.big_window_layout);
    viewWidth = ((View)localObject).getLayoutParams().width;
    viewHeight = ((View)localObject).getLayoutParams().height;
    localObject = (Button)findViewById(R.id.close);
    Button localButton = (Button)findViewById(R.id.back);
    ((Button)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyWindowManager.removeBigWindow(paramContext);
        MyWindowManager.removeSmallWindow(paramContext);
        paramAnonymousView = new Intent(FloatWindowBigView.this.getContext(), FloatWindowService.class);
        paramContext.stopService(paramAnonymousView);
      }
    });
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyWindowManager.removeBigWindow(paramContext);
        MyWindowManager.createSmallWindow(paramContext);
      }
    });
  }
}
