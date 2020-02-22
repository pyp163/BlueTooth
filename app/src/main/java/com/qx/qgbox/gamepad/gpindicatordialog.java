package com.qx.qgbox.gamepad;

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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qx.qgbox.R;
import com.qx.qgbox.activity.MyApplication;
import com.qx.qgbox.entitys.ComKey;
import com.qx.qgbox.entitys.GPNormalKey;
import com.qx.qgbox.entitys.MacroKey;
import com.qx.qgbox.service.FloatWindowService;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.AnalyseDataUtil;
import com.qx.qgbox.utils.AnalyseDataUtilS1;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.GpSetupImageSRCUtils2;
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.utils.NotchSizeUtil;
import com.qx.qgbox.views.ToastDialog;

import java.util.ArrayList;
import java.util.Locale;

public class gpindicatordialog extends AlertDialog {
    public static final int MSG_ON_DISPLAY_SETUP_AGAIN = 201860;
    public static final int MSG_ON_LOAD_MAP = 201859;
    public static final int MSG_ON_LOAD_MAP_BY_MODE_KEY = 201861;
    public static final int MSG_ON_LOAD_MAP_ERROR = 201858;
    public static final int MSG_ON_REFRESH_MAP_SUCCESS = 201804;
    private static String comKeyFirst = "-1";
    public static byte[] datapro;
    public static int deviceX = 0;
    public static int deviceY = 0;
    public static boolean isMapInfosSuccess = false;
    public static int landscape = 0;
    public static ArrayList<GPNormalKey> mGPNormalKeyList = new ArrayList();
    public static ArrayList<MacroKey> mMacroKeyList = new ArrayList();
    public static Handler myindicatorhandler;
    public static int portrait = 1;
    long currentClickTime = 0L;
    private ImageView[] imageView = new ImageView[this.length];
    private ImageView[] imageViewLB = new ImageView[this.length];
    private ImageView[] imageViewLT = new ImageView[this.length];
    private ImageView[] imageViewRB = new ImageView[this.length];
    private ImageView[] imageViewRT = new ImageView[this.length];
    long lastClickTime = 0L;
    int length = 966;
    String locale = null;
    RelativeLayout main = null;
    Context mcontext = null;
    DataSaver[] mdatasaver = new DataSaver[this.length];

    public gpindicatordialog(Context paramContext) {
        super(paramContext, R.style.MyDialog);
        this.mcontext = paramContext;
    }

    private int getpicturewh(int paramInt) {
        Bitmap localBitmap = BitmapFactory.decodeResource(this.mcontext.getResources(), R.drawable.bsmall);
        if (paramInt == 1)
            return localBitmap.getHeight();
        return localBitmap.getWidth();
    }

    private void hideBottomUIMenu() {
        final View localView = getWindow().getDecorView();
        localView.setSystemUiVisibility(5894);
        localView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int paramAnonymousInt) {
                if ((paramAnonymousInt & 0x4) == 0)
                    localView.setSystemUiVisibility(5894);
            }
        });
    }

    private boolean isHasData() {
        int i = 0;
        while (i < 37) {
            if (!this.mdatasaver[i].name.equalsIgnoreCase("-1"))
                return true;
            i += 1;
        }
        return false;
    }

    public int ScreenOriatation(Context paramContext) {
        if (paramContext.getResources().getConfiguration().orientation == 1)
            return portrait;
        return landscape;
    }

    void cleanallview() {
        if (this.main != null) {
            int i = 0;
            while (i < this.length) {
                if (this.imageView[i] != null)
                    this.main.removeView(this.imageView[i]);
                i += 1;
            }
        }
    }

    void displaycircle() {
        cleanallview();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("--- comKeyFirst = ");
        localStringBuilder.append(comKeyFirst);
        MyLog.i("my_tag", localStringBuilder.toString());
        if (!comKeyFirst.equalsIgnoreCase("-1"))
            if (comKeyFirst.equalsIgnoreCase("r1"))
                this.imageView = this.imageViewRB;
            else if (comKeyFirst.equalsIgnoreCase("l1"))
                this.imageView = this.imageViewLB;
            else if (comKeyFirst.equalsIgnoreCase("r2"))
                this.imageView = this.imageViewRT;
            else if (comKeyFirst.equalsIgnoreCase("l2"))
                this.imageView = this.imageViewLT;
        int i = 0;
        while (i < this.length) {
            if (this.mdatasaver[i].x != -1)
                redisplaymap(i, 0);
            i += 1;
        }
    }

    void init() {
        this.imageView = MyApplication.getmGpSetupImageSRCUtils2().initGPIndicatorImageView(this.mcontext, 0);
        this.imageViewRB = MyApplication.getmGpSetupImageSRCUtils2().initGPIndicatorImageView(this.mcontext, 1);
        this.imageViewRT = MyApplication.getmGpSetupImageSRCUtils2().initGPIndicatorImageView(this.mcontext, 2);
        this.imageViewLB = MyApplication.getmGpSetupImageSRCUtils2().initGPIndicatorImageView(this.mcontext, 3);
        this.imageViewLT = MyApplication.getmGpSetupImageSRCUtils2().initGPIndicatorImageView(this.mcontext, 4);
    }

    void initMdatasaver() {
        int i = 0;
        while (i < this.length) {
            this.mdatasaver[i] = new DataSaver();
            this.mdatasaver[i].property = -1;
            this.mdatasaver[i].name = "-1";
            this.mdatasaver[i].x = -1;
            this.mdatasaver[i].y = -1;
            this.mdatasaver[i].radius = 0;
            this.mdatasaver[i].block = 0;
            this.mdatasaver[i].reverse = 0;
            i += 1;
        }
        byte[] arrayOfByte = MyApplication.getGpDatapro();
        if (arrayOfByte == null)
            return;
        float f;
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            f = bluetoothdevmanager.screenwp;
            bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
            bluetoothdevmanager.screenhp = f;
        }
        if ((arrayOfByte[4] != 1) && (arrayOfByte[4] != 3)) {
            AnalyseDataUtil.initMdatasaverByByteArray(arrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, 0);
            AnalyseDataUtilS1.initMdatasaverByByteArray(arrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, 1);
        } else {
            if ((int) bluetoothdevmanager.screenwp >= (int) bluetoothdevmanager.screenhp) {
                f = bluetoothdevmanager.screenhp;
                bluetoothdevmanager.screenhp = bluetoothdevmanager.screenwp;
                bluetoothdevmanager.screenwp = f;
            }
            AnalyseDataUtil.initMdatasaverByByteArrayPortraitScreen(arrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, 0);
            AnalyseDataUtilS1.initMdatasaverByByteArrayPortraitScreen(arrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, 1);
        }
        if (bluetoothdevmanager.deviceScenesMode == 0)
            this.mdatasaver = MyApplication.getMdatasaverScenes0();
        else if (bluetoothdevmanager.deviceScenesMode == 1)
            this.mdatasaver = MyApplication.getMdatasaverScenes1();
        else
            this.mdatasaver = MyApplication.getMdatasaverScenes0();
        new ComKey();
        ComKey localComKey;
        if (bluetoothdevmanager.mapVersion >= 5)
            localComKey = CommonUtils.checkComKey(arrayOfByte[9] & 0xFF);
        else
            localComKey = CommonUtils.checkComKey(arrayOfByte[23] & 0xFF);
        if (localComKey._id != -1) {
            this.mdatasaver[(localComKey._id + 294)].property = 7;
            this.mdatasaver[(localComKey._id + 294)].name = localComKey.name;
            comKeyFirst = localComKey.name;
            this.mdatasaver[(localComKey._id + 294)].x = ((int) (bluetoothdevmanager.screenwp * 2.0F / 3.0F));
            this.mdatasaver[(localComKey._id + 294)].y = ((int) (bluetoothdevmanager.screenhp * 2.0F / 3.0F));
            this.mdatasaver[(localComKey._id + 294)].radius = 0;
            this.mdatasaver[(localComKey._id + 294)].block = arrayOfByte[21];
        }
    }

    @SuppressLint({"HandlerLeak"})
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.gpindicator_main);
        getWindow().setLayout(-1, -1);
        getWindow().setFlags(8, 8);
        getWindow().setFlags(16, 16);
        if (NotchSizeUtil.hasNotchInScreen(this.mcontext))
            NotchSizeUtil.setFullScreenWindowLayoutInDisplayCutout(getWindow());
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(params);
        }
        if (Build.VERSION.SDK_INT < 23)
            getWindow().setType(2005);
        else if ((Build.VERSION.SDK_INT != 28) && (Build.VERSION.SDK_INT != 26) && (Build.VERSION.SDK_INT != 27)) {
            if (Build.VERSION.SDK_INT > 28)
                getWindow().setType(2038);
            else
                getWindow().setType(2003);
        } else
            getWindow().setType(2038);
        setCancelable(false);
        hideBottomUIMenu();
        this.main = ((RelativeLayout) findViewById(R.id.i_Layout));
        this.locale = Locale.getDefault().toString();
        myindicatorhandler = new Handler() {
            public void handleMessage(Message paramAnonymousMessage) {
                int i = paramAnonymousMessage.what;
                if (i != 1) {
                    if (i != 201804) {
                        switch (i) {
                            default:
                                break;
                            case 201861:
                                gpindicatordialog.this.initMdatasaver();
                                gpindicatordialog.this.init();
                                gpindicatordialog.myindicatorhandler.sendEmptyMessageDelayed(201860, 50L);
                                if (FloatWindowService.myHandle != null)
                                    FloatWindowService.myHandle.sendEmptyMessage(0);
                                gpindicatordialog.isMapInfosSuccess = true;
                                break;
                            case 201860:
                                gpindicatordialog.this.displaycircle();
                                break;
                            case 201859:
                                gpindicatordialog.isMapInfosSuccess = false;
                                BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                                new Thread() {
                                    public void run() {
                                        int i = 0;
                                        while ((i < 6) && (!gpindicatordialog.isMapInfosSuccess)) {
                                            int j = i;
                                            if (i > 0)
                                                if ((bluetoothdevmanager.mGpindicatordialog != null) && (bluetoothdevmanager.mGpindicatordialog.isShowing())) {
                                                    BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                                                    j = i;
                                                } else {
                                                    j = 8;
                                                }
                                            i = j + 1;
                                            try {
                                                sleep(3000L);
                                            } catch (InterruptedException localInterruptedException) {
                                                localInterruptedException.printStackTrace();
                                            }
                                        }
                                        if ((i >= 6) && (i != 9))
                                            gpindicatordialog.myindicatorhandler.sendEmptyMessage(201858);
                                    }
                                }
                                        .start();
                                break;
                            case 201858:
                                if ((bluetoothdevmanager.mGpindicatordialog == null) || (!bluetoothdevmanager.mGpindicatordialog.isShowing()) || (gpindicatordialog.this.isHasData()))
                                    break;
                                new ToastDialog(gpindicatordialog.this.mcontext, gpindicatordialog.this.mcontext.getResources().getString(R.string.first_page_tip6)).show();
                                break;
                        }
                    } else {
                        gpindicatordialog.this.initMdatasaver();
                        gpindicatordialog.this.init();
                        gpindicatordialog.this.displaycircle();
                        if (gpindicatordialog.this.locale.contains("ar"))
                            gpindicatordialog.myindicatorhandler.sendEmptyMessageDelayed(201860, 50L);
                        if (FloatWindowService.myHandle != null)
                            FloatWindowService.myHandle.sendEmptyMessage(0);
                        gpindicatordialog.isMapInfosSuccess = true;
                    }
                } else {
                    gpindicatordialog.this.init();
                    gpindicatordialog.this.displaycircle();
                }
                super.handleMessage(paramAnonymousMessage);
            }
        };
        int i = 0;
        while (i < this.length) {
            this.mdatasaver[i] = new DataSaver();
            this.mdatasaver[i].property = -1;
            this.mdatasaver[i].name = "-1";
            this.mdatasaver[i].x = -1;
            this.mdatasaver[i].y = -1;
            this.mdatasaver[i].radius = 0;
            this.mdatasaver[i].block = 0;
            this.mdatasaver[i].reverse = 0;
            i += 1;
        }
        myindicatorhandler.sendEmptyMessageDelayed(201859, 300L);
    }

    public void redisplaymap(int paramInt1, int paramInt2) {
        ViewGroup localViewGroup = (ViewGroup) this.imageView[paramInt1].getParent();
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
    }
}
