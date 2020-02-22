package com.qx.qgbox.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BToast extends Toast
{
  private static final int TYPE_FALSE = 1;
  private static final int TYPE_HIDE = -1;
  private static final int TYPE_TRUE = 0;
  private static BToast toast;
  private static ImageView toast_img;

  public BToast(Context paramContext)
  {
    super(paramContext);
  }

  public static void cancelToast()
  {
    if (toast != null)
      toast.cancel();
  }

  private static void initToast(Context paramContext, CharSequence paramCharSequence)
  {
    try
    {
      cancelToast();
      toast = new BToast(paramContext);
      paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.toast_layout, null);
      ((TextView)paramContext.findViewById(R.id.toast_text)).setText(paramCharSequence);
      toast.setView(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void showText(Context paramContext, CharSequence paramCharSequence)
  {
    showToast(paramContext, paramCharSequence, 0, -1);
  }

  public static void showText(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    showToast(paramContext, paramCharSequence, paramInt, -1);
  }

  public static void showText(Context paramContext, CharSequence paramCharSequence, int paramInt, boolean paramBoolean)
  {
    showToast(paramContext, paramCharSequence, paramInt, paramBoolean ^ true);
  }

  public static void showText(Context paramContext, CharSequence paramCharSequence, boolean paramBoolean)
  {
    showToast(paramContext, paramCharSequence, 0, paramBoolean ^ true);
  }

  private static void showToast(Context paramContext, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    initToast(paramContext, paramCharSequence);
    if (paramInt1 == 1)
      toast.setDuration(1);
    else
      toast.setDuration(0);
    toast.show();
  }

  public void cancel()
  {
    try
    {
      super.cancel();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void show()
  {
    try
    {
      super.show();
      return;
    }
    catch (Exception localException)
    {
    }
  }
}
