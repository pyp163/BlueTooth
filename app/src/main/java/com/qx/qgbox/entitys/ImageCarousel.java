package com.qx.qgbox.entitys;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qx.qgbox.adapters.ImagesPagerAdapter;
import java.util.List;

public class ImageCarousel
{
  private ImagesPagerAdapter adapter;
  private AutoPlayThread autoPlayThread;
  private Context context;
  private List<View> dots;
  private LinearLayout dotsRoot;
  private volatile boolean isExit = true;
  private int previousPosition = 0;
  private List<SimpleDraweeView> simpleDraweeViewList;
  private int time;
  private String[] titles;
  private TextView tvTitle;
  private ViewPager viewPager;

  public ImageCarousel(Context paramContext, ViewPager paramViewPager, TextView paramTextView, List<View> paramList, int paramInt)
  {
    this.context = paramContext;
    this.viewPager = paramViewPager;
    this.tvTitle = paramTextView;
    this.dots = paramList;
    this.time = paramInt;
    Log.e("image", "构造方法");
  }

  private void setAutoPlay(boolean paramBoolean)
  {
  }

  private void setFirstLocation()
  {
    this.tvTitle.setText(this.titles[0]);
    ((View)this.dots.get(0)).setBackgroundResource(R.drawable.ic_dot_normal);
    this.viewPager.setCurrentItem(0);
  }

  public ImageCarousel init(List<SimpleDraweeView> paramList, String[] paramArrayOfString)
  {
    this.simpleDraweeViewList = paramList;
    this.titles = paramArrayOfString;
    Log.e("image", "init");
    this.autoPlayThread = new AutoPlayThread();
    return this;
  }

  public void reload(List<SimpleDraweeView> paramList, String[] paramArrayOfString)
  {
    init(paramList, paramArrayOfString);
    this.previousPosition = 0;
    start();
  }

  public void start()
  {
    if (this.adapter != null)
      this.adapter = null;
    this.adapter = new ImagesPagerAdapter(this.simpleDraweeViewList, this.viewPager, this.context);
    this.viewPager.setAdapter(this.adapter);
    this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
      }

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        paramAnonymousInt %= ImageCarousel.this.simpleDraweeViewList.size();
        ImageCarousel.this.tvTitle.setText(ImageCarousel.this.titles[paramAnonymousInt]);
        ((View)ImageCarousel.this.dots.get(ImageCarousel.this.previousPosition)).setBackgroundResource(R.drawable.ic_dot_focused);
        ((View)ImageCarousel.this.dots.get(paramAnonymousInt)).setBackgroundResource(R.drawable.ic_dot_normal);
        ImageCarousel.access$302(ImageCarousel.this, paramAnonymousInt);
      }
    });
    setFirstLocation();
  }

  public void startAutoPlay()
  {
    Log.e("thrad", "开始");
    this.isExit = false;
    this.autoPlayThread = null;
    this.autoPlayThread = new AutoPlayThread();
    this.autoPlayThread.start();
  }

  public void stopAutoPlay()
  {
    if (this.autoPlayThread != null)
    {
      Log.e("thrad", "暂停");
      this.isExit = true;
      this.autoPlayThread.interrupt();
      this.autoPlayThread = null;
    }
  }

  class AutoPlayThread extends Thread
  {
    AutoPlayThread()
    {
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 15	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread:this$0	Lcom/qx/qgbox/entitys/ImageCarousel;
      //   6: invokestatic 26	com/qx/qgbox/entitys/ImageCarousel:access$500	(Lcom/qx/qgbox/entitys/ImageCarousel;)Z
      //   9: istore_1
      //   10: iload_1
      //   11: ifne +60 -> 71
      //   14: aload_0
      //   15: getfield 15	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread:this$0	Lcom/qx/qgbox/entitys/ImageCarousel;
      //   18: invokestatic 30	com/qx/qgbox/entitys/ImageCarousel:access$600	(Lcom/qx/qgbox/entitys/ImageCarousel;)I
      //   21: i2l
      //   22: invokestatic 34	java/lang/Thread:sleep	(J)V
      //   25: aload_0
      //   26: getfield 15	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread:this$0	Lcom/qx/qgbox/entitys/ImageCarousel;
      //   29: invokestatic 38	com/qx/qgbox/entitys/ImageCarousel:access$800	(Lcom/qx/qgbox/entitys/ImageCarousel;)Landroid/content/Context;
      //   32: checkcast 40	android/app/Activity
      //   35: new 9	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread$1
      //   38: dup
      //   39: aload_0
      //   40: invokespecial 43	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread$1:<init>	(Lcom/qx/qgbox/entitys/ImageCarousel$AutoPlayThread;)V
      //   43: invokevirtual 47	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
      //   46: invokestatic 51	com/qx/qgbox/entitys/ImageCarousel$AutoPlayThread:interrupted	()Z
      //   49: ifeq -47 -> 2
      //   52: ldc 53
      //   54: ldc 55
      //   56: invokestatic 61	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   59: pop
      //   60: goto +11 -> 71
      //   63: ldc 53
      //   65: ldc 63
      //   67: invokestatic 61	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   70: pop
      //   71: aload_0
      //   72: monitorexit
      //   73: return
      //   74: astore_2
      //   75: aload_0
      //   76: monitorexit
      //   77: aload_2
      //   78: athrow
      //   79: astore_2
      //   80: goto -17 -> 63
      //
      // Exception table:
      //   from	to	target	type
      //   2	10	74	finally
      //   14	25	74	finally
      //   25	60	74	finally
      //   63	71	74	finally
      //   14	25	79	java/lang/InterruptedException
    }
  }
}
