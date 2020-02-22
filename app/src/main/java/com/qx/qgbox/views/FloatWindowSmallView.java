package com.qx.qgbox.views;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qx.qgbox.activity.FirstPageActivity;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.MyLog;
import java.lang.reflect.Field;

public class FloatWindowSmallView extends LinearLayout
{
  private static int statusBarHeight;
  public static int viewHeight;
  public static int viewWidth;
  private ImageView imageView1;
  boolean isLongClickModule = false;
  boolean isLongClicking = false;
  private long lastClickTime = 0L;
  private int lastX = 0;
  private int lastY = 0;
  private Context mContext;
  private WindowManager.LayoutParams mParams;
  int n = 0;
  private WindowManager windowManager;
  private float xDownInScreen;
  private float xInScreen;
  private float xInView;
  private float yDownInScreen;
  private float yInScreen;
  private float yInView;

  public FloatWindowSmallView(Context paramContext)
  {
    super(paramContext);
    this.windowManager = ((WindowManager)paramContext.getSystemService("window"));
    LayoutInflater.from(paramContext).inflate(R.layout.float_window_small, this);
    this.mContext = paramContext;
    paramContext = findViewById(R.id.small_window_layout);
    this.imageView1 = ((ImageView)paramContext.findViewById(R.id.imageView1));
    viewWidth = paramContext.getLayoutParams().width;
    viewHeight = paramContext.getLayoutParams().height;
  }

  private int getStatusBarHeight()
  {
    if (statusBarHeight == 0)
      try
      {
        Class localClass = Class.forName("com.android.internal.R$dimen");
        Object localObject = localClass.newInstance();
        int i = ((Integer)localClass.getField("status_bar_height").get(localObject)).intValue();
        statusBarHeight = getResources().getDimensionPixelSize(i);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    return statusBarHeight;
  }

  private boolean isLongPressed(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2, long paramLong3)
  {
    paramFloat1 = Math.abs(paramFloat3 - paramFloat1);
    paramFloat2 = Math.abs(paramFloat4 - paramFloat2);
    return (paramFloat1 >= 25.0F) || (paramFloat2 >= 25.0F);
  }

  private void openBigWindow()
  {
    if ((bluetoothdevmanager.servicehandle != null) && (!FirstPageActivity.isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1))
      bluetoothdevmanager.servicehandle.sendEmptyMessage(20022);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
      return true;
    case 2:
      if (!this.isLongClickModule)
        if ((System.currentTimeMillis() - this.lastClickTime < 300L) && (!isLongPressed(this.xDownInScreen, this.yDownInScreen, paramMotionEvent.getRawX(), paramMotionEvent.getRawY(), paramMotionEvent.getDownTime(), paramMotionEvent.getEventTime(), 150L)))
          this.isLongClickModule = false;
        else
          this.isLongClickModule = true;
      if ((this.isLongClickModule) && (!this.isLongClicking))
        this.isLongClicking = true;
      if (this.isLongClicking)
      {
        this.n += 1;
        this.imageView1.setImageDrawable(this.mContext.getResources().getDrawable(R.mipmap.float_btn));
        this.xInScreen = paramMotionEvent.getRawX();
        this.yInScreen = paramMotionEvent.getRawY();
        this.mParams.x = ((int)this.xInScreen - this.imageView1.getWidth());
        this.mParams.y = ((int)this.yInScreen - this.imageView1.getHeight() * 2 / 3);
        this.windowManager.updateViewLayout(this, this.mParams);
        return true;
      }
      break;
    case 1:
      if (this.isLongClicking)
        this.imageView1.setImageDrawable(this.mContext.getResources().getDrawable(R.mipmap.float_btn));
      this.isLongClicking = false;
      this.isLongClickModule = false;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.n);
      localStringBuilder.append("--X = ");
      localStringBuilder.append((int)paramMotionEvent.getRawX());
      localStringBuilder.append(";  Y = ");
      localStringBuilder.append((int)paramMotionEvent.getRawY());
      MyLog.i("my_tag", localStringBuilder.toString());
      if (System.currentTimeMillis() - this.lastClickTime < 300L)
      {
        openBigWindow();
        return true;
      }
      break;
    case 0:
      this.xDownInScreen = ((int)paramMotionEvent.getRawX());
      this.yDownInScreen = ((int)paramMotionEvent.getRawY());
      this.n = 0;
      this.isLongClicking = false;
      this.isLongClickModule = false;
      this.lastClickTime = System.currentTimeMillis();
    }
    return true;
  }

  public void setParams(WindowManager.LayoutParams paramLayoutParams)
  {
    this.mParams = paramLayoutParams;
  }
}
