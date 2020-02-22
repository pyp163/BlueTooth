package com.qx.qgbox.gamemouse;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.qx.qgbox.R;
import com.qx.qgbox.utils.NotchSizeUtil;

public class greencursor extends AlertDialog
{
  public static ImageView bm;
  public static int landscape = 0;
  public static Handler mygreencursorhandler;
  public static int portrait = 1;
  Context mcontext = null;

  public greencursor(Context paramContext)
  {
    super(paramContext, R.style.MyDialog);
    this.mcontext = paramContext;
  }

  private int getpicturewh(int paramInt)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(this.mcontext.getResources(), R.drawable.b);
    if (paramInt == 1)
      return localBitmap.getHeight();
    return localBitmap.getWidth();
  }

  private int getscreenwh(int paramInt)
  {
    new DisplayMetrics();
    DisplayMetrics localDisplayMetrics = this.mcontext.getResources().getDisplayMetrics();
    if (paramInt == 0)
      return localDisplayMetrics.widthPixels;
    return localDisplayMetrics.heightPixels;
  }

  private void hideNavigationBar()
  {
    final View localView = getWindow().getDecorView();
    localView.setSystemUiVisibility(5894);
    localView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
    {
      public void onSystemUiVisibilityChange(int paramAnonymousInt)
      {
        if ((paramAnonymousInt & 0x4) == 0)
          localView.setSystemUiVisibility(5894);
      }
    });
  }

  public int ScreenOriatation(Context paramContext)
  {
    if (paramContext.getResources().getConfiguration().orientation == 1)
      return portrait;
    return landscape;
  }

  public void bmsetbig()
  {
    bm.setImageResource(R.drawable.mouse);
  }

  public void bmsetsmall()
  {
    bm.setImageResource(R.drawable.mouseiconsmall);
  }

  public int getbmheight()
  {
    return bm.getHeight();
  }

  public int getbmwidth()
  {
    return bm.getWidth();
  }

  public void getlocation(int[] paramArrayOfInt)
  {
    bm.getLocationOnScreen(paramArrayOfInt);
  }

  @SuppressLint({"HandlerLeak"})
  void init()
  {
    mygreencursorhandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        int i = paramAnonymousMessage.what;
        super.handleMessage(paramAnonymousMessage);
      }
    };
    hideNavigationBar();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bm = new ImageView(this.mcontext);
    bm.setImageResource(R.drawable.mouseicon1);
    setContentView(bm);
    getWindow().setLayout(-2, -2);
    getWindow().setFlags(8, 8);
    getWindow().setFlags(16, 16);
    if (NotchSizeUtil.hasNotchInScreen(this.mcontext))
      NotchSizeUtil.setFullScreenWindowLayoutInDisplayCutout(getWindow());
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramBundle = getWindow().getAttributes();
      paramBundle.layoutInDisplayCutoutMode = 1;
      getWindow().setAttributes(paramBundle);
    }
    if (Build.VERSION.SDK_INT < 23)
      getWindow().setType(2005);
    else if ((Build.VERSION.SDK_INT != 28) && (Build.VERSION.SDK_INT != 26) && (Build.VERSION.SDK_INT != 27))
    {
      if (Build.VERSION.SDK_INT > 28)
        getWindow().setType(2038);
      else
        getWindow().setType(2003);
    }
    else
      getWindow().setType(2038);
    setCancelable(false);
    init();
  }
}
