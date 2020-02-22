package com.qx.qgbox.gamemouse;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.qx.qgbox.R;

public class FolatButtonDialog extends AlertDialog
{
  public static int landscape = 0;
  public static Handler myhandler;
  public static int portrait = 1;
  private int dragstart = 0;
  private ImageView immenu;
  boolean isLongClickModule = false;
  boolean isLongClicking = false;
  private int lastX = 0;
  private int lastY = 0;
  Context mcontext = null;

  public FolatButtonDialog(Context paramContext)
  {
    super(paramContext, R.style.MyDialog);
    this.mcontext = paramContext;
  }

  private int getscreenwh(int paramInt)
  {
    new DisplayMetrics();
    DisplayMetrics localDisplayMetrics = this.mcontext.getResources().getDisplayMetrics();
    if (paramInt == 0)
      return localDisplayMetrics.widthPixels;
    return localDisplayMetrics.heightPixels;
  }

  private void hideBottomUIMenu()
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

  private boolean isLongPressed(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2, long paramLong3)
  {
    paramFloat1 = Math.abs(paramFloat3 - paramFloat1);
    paramFloat2 = Math.abs(paramFloat4 - paramFloat2);
    return (paramFloat1 >= 3.0F) || (paramFloat2 >= 3.0F) || (paramLong2 - paramLong1 >= paramLong3);
  }

  @SuppressLint({"HandlerLeak"})
  void initHandler()
  {
    myhandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
      }
    };
  }

  @SuppressLint({"ClickableViewAccessibility"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.folat_btn_layout);
    getWindow().setLayout(-1, -1);
    getWindow().setFlags(8, 8);
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
    hideBottomUIMenu();
    initHandler();
    this.immenu = ((ImageView)findViewById(R.id.imageView1));
    this.immenu.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default:
          return false;
        case 2:
          if (!FolatButtonDialog.this.isLongClickModule)
            FolatButtonDialog.this.isLongClickModule = FolatButtonDialog.this.isLongPressed(FolatButtonDialog.this.lastX, FolatButtonDialog.this.lastY, paramAnonymousMotionEvent.getRawX(), paramAnonymousMotionEvent.getRawY(), paramAnonymousMotionEvent.getDownTime(), paramAnonymousMotionEvent.getEventTime(), 150L);
          if ((FolatButtonDialog.this.isLongClickModule) && (!FolatButtonDialog.this.isLongClicking))
            FolatButtonDialog.this.isLongClicking = true;
          if (FolatButtonDialog.this.isLongClicking)
          {
            int i = (int)paramAnonymousMotionEvent.getRawX() - FolatButtonDialog.this.lastX;
            int k = (int)paramAnonymousMotionEvent.getRawY() - FolatButtonDialog.this.lastY;
            int m = paramAnonymousView.getLeft() + i;
            int j = paramAnonymousView.getBottom() + k;
            i = paramAnonymousView.getRight() + i;
            int n = paramAnonymousView.getTop() + k;
            k = m;
            if (m < 0)
            {
              i = paramAnonymousView.getWidth() + 0;
              k = 0;
            }
            m = n;
            if (n < 0)
            {
              j = paramAnonymousView.getHeight() + 0;
              m = 0;
            }
            n = k;
            k = i;
            if (i > FolatButtonDialog.this.getscreenwh(0))
            {
              k = FolatButtonDialog.this.getscreenwh(0);
              n = k - paramAnonymousView.getWidth();
            }
            i = j;
            if (j > FolatButtonDialog.this.getscreenwh(1))
            {
              i = FolatButtonDialog.this.getscreenwh(1);
              m = i - paramAnonymousView.getHeight();
            }
            paramAnonymousView.layout(n, m, k, i);
            FolatButtonDialog.access$002(FolatButtonDialog.this, (int)paramAnonymousMotionEvent.getX());
            FolatButtonDialog.access$102(FolatButtonDialog.this, (int)paramAnonymousMotionEvent.getY());
            return false;
          }
          break;
        case 1:
          paramAnonymousMotionEvent.getRawX();
          paramAnonymousMotionEvent.getRawY();
          FolatButtonDialog.access$402(FolatButtonDialog.this, 0);
          if (!FolatButtonDialog.this.isLongClicking)
          {
            Log.i("FBTN_TAG", "-----点击了悬浮按钮---------");
            return false;
          }
          if (FolatButtonDialog.this.isLongClickModule)
          {
            FolatButtonDialog.this.isLongClickModule = false;
            FolatButtonDialog.this.isLongClicking = false;
          }
          paramAnonymousMotionEvent = new RelativeLayout.LayoutParams(-2, -2);
          paramAnonymousMotionEvent.leftMargin = paramAnonymousView.getLeft();
          paramAnonymousMotionEvent.topMargin = paramAnonymousView.getTop();
          paramAnonymousMotionEvent.width = paramAnonymousView.getWidth();
          paramAnonymousMotionEvent.height = paramAnonymousView.getHeight();
          paramAnonymousMotionEvent.setMargins(paramAnonymousView.getLeft(), paramAnonymousView.getTop(), 0, 0);
          paramAnonymousView.setLayoutParams(paramAnonymousMotionEvent);
          return false;
        case 0:
          FolatButtonDialog.access$002(FolatButtonDialog.this, (int)paramAnonymousMotionEvent.getRawX());
          FolatButtonDialog.access$102(FolatButtonDialog.this, (int)paramAnonymousMotionEvent.getRawY());
        }
        return false;
      }
    });
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      return false;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}
