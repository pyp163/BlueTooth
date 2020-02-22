package com.qx.qgbox.gamemouse;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qx.qgbox.R;
import com.qx.qgbox.activity.MyApplication;
import com.qx.qgbox.entitys.NormalKey;
import com.qx.qgbox.service.FloatWindowService;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.AnalyseSpDataUtil;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.utils.NotchSizeUtil;
import com.qx.qgbox.utils.SpSetupImageSRCUtils;
import com.qx.qgbox.views.ToastDialog;
import java.util.ArrayList;
import java.util.Locale;

public class indicatordialog extends AlertDialog
{
  public static final int MSG_ON_DISPLAY_SETUP_AGAIN = 201860;
  public static final int MSG_ON_LOAD_MAP = 201859;
  public static final int MSG_ON_LOAD_MAP_ERROR = 201858;
  public static final int MSG_ON_REFRESH_MAP_SUCCESS = 201804;
  public static byte[] datapro;
  public static int deviceX = 0;
  public static int deviceY = 0;
  public static boolean isMapInfosSuccess = false;
  public static int landscape = 0;
  public static ArrayList<NormalKey> mNormalKeyList = new ArrayList();
  public static Handler myindicatorhandler;
  public static int portrait = 1;
  private ImageView[] imageView = new ImageView[this.length];
  int length = 351;
  String locale = null;
  RelativeLayout main = null;
  Context mcontext = null;
  DataSaverM[] mdatasaver = new DataSaverM[this.length];

  public indicatordialog(Context paramContext)
  {
    super(paramContext, R.style.MyDialog);
    this.mcontext = paramContext;
  }

  private int getpicturewh(int paramInt)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(this.mcontext.getResources(), R.drawable.bsmall);
    if (paramInt == 1)
      return localBitmap.getHeight();
    return localBitmap.getWidth();
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

  public int ScreenOriatation(Context paramContext)
  {
    if (paramContext.getResources().getConfiguration().orientation == 1)
      return portrait;
    return landscape;
  }

  void cleanallview()
  {
    if (this.main != null)
    {
      int i = 0;
      while (i < this.length)
      {
        if (this.imageView[i] != null)
          this.main.removeView(this.imageView[i]);
        i += 1;
      }
    }
  }

  void displaycircle()
  {
    cleanallview();
    int i = 0;
    while (i < this.length)
    {
      if (this.mdatasaver[i].x != -1)
        redisplaymap(i, 0);
      i += 1;
    }
  }

  void init()
  {
    this.imageView = MyApplication.getmSpSetupImageSRCUtils().initSPindicatorImageView();
  }

  void initMdatasaver()
  {
    int i = 0;
    while (i < this.length)
    {
      this.mdatasaver[i] = new DataSaverM();
      this.mdatasaver[i].property = -1;
      this.mdatasaver[i].name = "-1";
      this.mdatasaver[i].x = -1;
      this.mdatasaver[i].y = -1;
      i += 1;
    }
    if ((int)bluetoothdevmanager.screenhp >= (int)bluetoothdevmanager.screenwp)
    {
      float f = bluetoothdevmanager.screenwp;
      bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
      bluetoothdevmanager.screenhp = f;
    }
    if (datapro != null)
      datapro = null;
    if (datapro == null)
      datapro = MyApplication.getDatapro();
    if (datapro == null)
      return;
    this.mdatasaver = AnalyseSpDataUtil.initMdatasaverByByteArray(datapro, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp);
  }

  @SuppressLint({"HandlerLeak"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.indicator_main);
    getWindow().setLayout(-1, -1);
    getWindow().setFlags(8, 8);
    getWindow().setFlags(16, 16);
    if (NotchSizeUtil.hasNotchInScreen(this.mcontext))
      NotchSizeUtil.setFullScreenWindowLayoutInDisplayCutout(getWindow());
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
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramBundle = getWindow().getAttributes();
      paramBundle.layoutInDisplayCutoutMode = 1;
      getWindow().setAttributes(paramBundle);
    }
    int i = 0;
    setCancelable(false);
    hideBottomUIMenu();
    this.main = ((RelativeLayout)findViewById(R.id.i_Layout));
    this.locale = Locale.getDefault().toString();
    myindicatorhandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        int i = paramAnonymousMessage.what;
        if (i != 1)
        {
          if (i != 201804)
          {
            switch (i)
            {
            default:
              break;
            case 201860:
              indicatordialog.this.displaycircle();
              break;
            case 201859:
              indicatordialog.isMapInfosSuccess = false;
              BlueCmdManager.sendGetMapCmd((byte)0, (byte)0);
              MyLog.i("my_tag", "sendGetMapCmd0");
              new Thread()
              {
                public void run()
                {
                  int i = 0;
                  while ((i < 6) && (!indicatordialog.isMapInfosSuccess))
                  {
                    int j = i;
                    if (i > 0)
                      if ((bluetoothdevmanager.mNewIndicatordialog != null) && (bluetoothdevmanager.mNewIndicatordialog.isShowing()))
                      {
                        BlueCmdManager.sendGetMapCmd((byte)0, (byte)0);
                        j = i;
                      }
                      else
                      {
                        j = 8;
                      }
                    i = j + 1;
                    try
                    {
                      sleep(2000L);
                    }
                    catch (InterruptedException localInterruptedException)
                    {
                      localInterruptedException.printStackTrace();
                    }
                  }
                  if ((i >= 6) && (i != 9))
                    indicatordialog.myindicatorhandler.sendEmptyMessage(201858);
                }
              }
              .start();
              break;
            case 201858:
              if ((bluetoothdevmanager.mNewIndicatordialog == null) || (!bluetoothdevmanager.mNewIndicatordialog.isShowing()) || (!indicatordialog.this.mdatasaver[55].name.equalsIgnoreCase("-1")))
                break;
              new ToastDialog(indicatordialog.this.mcontext, indicatordialog.this.mcontext.getResources().getString(R.string.first_page_tip6)).show();
              break;
            }
          }
          else
          {
            indicatordialog.this.initMdatasaver();
            indicatordialog.this.init();
            indicatordialog.this.displaycircle();
            if (indicatordialog.this.locale.contains("ar"))
              indicatordialog.myindicatorhandler.sendEmptyMessageDelayed(201860, 50L);
            if (FloatWindowService.myHandle != null)
              FloatWindowService.myHandle.sendEmptyMessage(0);
            indicatordialog.isMapInfosSuccess = true;
          }
        }
        else
        {
          indicatordialog.this.init();
          indicatordialog.this.displaycircle();
        }
        super.handleMessage(paramAnonymousMessage);
      }
    };
    while (i < this.length)
    {
      this.mdatasaver[i] = new DataSaverM();
      this.mdatasaver[i].property = -1;
      this.mdatasaver[i].name = "-1";
      this.mdatasaver[i].x = -1;
      this.mdatasaver[i].y = -1;
      this.mdatasaver[i].joystick = "-1";
      this.mdatasaver[i].rumble = "-1";
      this.mdatasaver[i].whichmoto = "-1";
      this.mdatasaver[i].ms = "-1";
      i += 1;
    }
    myindicatorhandler.sendEmptyMessageDelayed(201859, 300L);
  }

  public void redisplaymap(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 204) && (paramInt1 != 209) && (paramInt1 != 210))
    {
      if (paramInt1 == 211)
        return;
      ViewGroup localViewGroup = (ViewGroup)this.imageView[paramInt1].getParent();
      if (localViewGroup != null)
        localViewGroup.removeAllViews();
      paramInt2 = getpicturewh(0) / 2;
      int i = getpicturewh(1) / 2;
      int k = this.imageView[paramInt1].getWidth() / 2;
      int j = this.imageView[paramInt1].getHeight() / 2;
      if (k != 0)
        paramInt2 = k;
      if (j != 0)
        i = j;
      this.imageView[paramInt1].setX(this.mdatasaver[paramInt1].x - paramInt2);
      this.imageView[paramInt1].setY(this.mdatasaver[paramInt1].y - i);
      this.main.addView(this.imageView[paramInt1]);
      setContentView(this.main);
      return;
    }
  }
}
