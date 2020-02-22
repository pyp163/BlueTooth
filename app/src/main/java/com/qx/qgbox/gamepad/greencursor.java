package com.qx.qgbox.gamepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.widget.ImageView;

public class greencursor extends AlertDialog
{
  public static int landscape = 0;
  public static Handler mygreencursorhandler;
  public static int portrait = 1;
  ImageView bm;
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
    this.bm.setImageResource(R.drawable.gpmouse);
  }

  public void bmsetsmall()
  {
    this.bm.setImageResource(R.drawable.gpmousesmall);
  }

  public int getbmheight()
  {
    return this.bm.getHeight();
  }

  public int getbmwidth()
  {
    return this.bm.getWidth();
  }

  public void getlocation(int[] paramArrayOfInt)
  {
    this.bm.getLocationOnScreen(paramArrayOfInt);
  }

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
    this.bm = new ImageView(this.mcontext);
    this.bm.setImageResource(R.drawable.gpmouse);
    setContentView(this.bm);
    getWindow().setLayout(-2, -2);
    getWindow().setFlags(8, 8);
    getWindow().setFlags(16, 16);
    if (Build.VERSION.SDK_INT < 23)
      getWindow().setType(2005);
    else
      getWindow().setType(2003);
    setCancelable(false);
    init();
  }
}
