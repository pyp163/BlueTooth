package com.qx.qgbox.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.KeyMap;
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.views.CustomTipDialog2;

import java.util.Date;

public class KeyTestActivity extends Activity {
    public static final int MSG_ON_Y1_UP = 10086;
    public static final int MSG_ON_Y2_UP = 10087;
    public static final int MSH_ON_HONG1 = 10089;
    public static final int MSH_ON_HONG11 = 10094;
    public static final int MSH_ON_HONG2 = 10090;
    public static final int MSH_ON_HONG22 = 10095;
    public static final int MSH_ON_HONG3 = 10091;
    public static final int MSH_ON_HONG33 = 10096;
    public static final int MSH_ON_HONG4 = 10092;
    public static final int MSH_ON_HONG44 = 10097;
    public static final int MSH_ON_HONG5 = 10093;
    public static final int MSH_ON_HONG55 = 10098;
    public static final int MSH_ON_TURBO1 = 10099;
    public static final int MSH_ON_TURBO2 = 10100;
    public static final int MS_CONN = 1;
    public static final int MS_DISCONN = 0;
    public static final int MS_REVDATA = 2;
    public static final int MS_VERSION = 3;
    private static int fx = 0;
    private static int fy = 0;
    public static int installing = 0;
    public static boolean isTest = true;
    private static ImageView iv_back;
    private static ImageView iv_device_icon;
    public static Handler mUiHandler;
    private static TextView textconnstate;
    private static int tx;
    private static int ty;
    private Animation animation = null;
    ImageView ball1 = null;
    ImageView ball2 = null;
    private ImageView ball3 = null;
    private ImageView ball4 = null;
    private ImageView ball5 = null;
    private ImageView ball6 = null;
    long currentClickTime = 0L;
    byte[] datapro2 = {32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, -128};
    byte[] datapro2gp = {32, 0, 0, 0, 0, 0, 0, -128, 0, -128, 0, -128, 0, -128, 0, 0, 0, 0, 0, 0};
    RelativeLayout gp = null;
    private RelativeLayout gpTest = null;
    private ImageView gp_red_ball5 = null;
    ImageView[] gpimageView = new ImageView[22];
    private int h1;
    private int h2;
    private int h3;
    private int h4;
    private int h5;
    private int h6;
    private int h7;
    private int h8;
    ImageView[] imageView = new ImageView[48];
    private ImageView[] imageViewGPTest = new ImageView[18];
    private ImageView[] imageViewSPTest = new ImageView[48];
    private boolean isFunctionTest = false;
    private boolean isHongNow = false;
    private boolean isTurboNow = false;
    private ImageView iv_ask1 = null;
    private ImageView iv_ask2 = null;
    private ImageView iv_ask3 = null;
    private ImageView iv_ask4 = null;
    private ImageView iv_ask5 = null;
    private ImageView iv_ask6 = null;
    private ImageView iv_ask7 = null;
    private ImageView iv_ask8 = null;
    private ImageView iv_ask_hong = null;
    private ImageView iv_ask_turbo = null;
    int[] keystate = new int[107];
    private int[] keystateTest = new int[18];
    private int[] keystateTestold = new int[18];
    int[] keystateold = new int[107];
    RelativeLayout km = null;
    private RelativeLayout kmTest = null;
    long lastClickTime = 0L;
    private Context mContext;
    private CustomTipDialog2 mCustomTipDialog = null;
    SharedPreferences mSharedPreferences = null;
    private TurboThread mTurboThread = null;
    ProgressBar pb1;
    ProgressBar pb2;
    private TextView textView2;
    private TextView textView4;
    private TextView textView6;
    private TextView textView8;
    int tstop = 0;
    private TextView tv_function_test;
    private TextView tv_key_test;
    private TextView tv_m1;
    private TextView tv_m2;
    private TextView tv_m3;
    private int w1 = 0;
    private int w2;
    private int w3;
    private int w4;
    private int w5;
    private int w6;
    private int w7;
    private int w8 = 0;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int x6;
    private int x7;
    private int x8;
    private int y1;
    private int y2;
    private int y3;
    private int y4;
    private int y5;
    private int y6;
    private int y7;
    private int y8;

    private boolean checkKey(byte[] paramArrayOfByte, int paramInt) {
        int i;
        if (bluetoothdevmanager.mapVersion >= 5)
            i = bluetoothdevmanager.maxJoystick * 2 + 3;
        else
            i = 7;
        while (i < paramArrayOfByte.length) {
            if ((paramArrayOfByte[i] & 0xFF) == paramInt)
                return true;
            i += 1;
        }
        return false;
    }

    public static void goHome(Context paramContext) {
        Intent localIntent = new Intent(paramContext, KeyTestActivity.class);
        localIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//131072
        localIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);//268435456
        paramContext.startActivity(localIntent);
    }

    private void gpdataprocess(byte[] paramArrayOfByte) {
        if (paramArrayOfByte[0] == 16)
            return;
        int i11 = this.x1;
        int i10 = this.y1;
        int i9 = this.x2;
        int i8 = this.y2;
        int m;
        int i2;
        int n;
        int j;
        int k;
        int i1;
        int i3;
        int i;
        int i7;
        int i6;
        int i5;
        int i4;
        Object label302;

        if (bluetoothdevmanager.mapVersion >= 5) {
            if (bluetoothdevmanager.maxJoystick > 0) {
                m = paramArrayOfByte[3] & 0xFF;
                i = paramArrayOfByte[4] & 0xFF;
            } else {
                m = 128;
                i = 128;
            }
            if (bluetoothdevmanager.maxJoystick > 1) {
                i2 = paramArrayOfByte[5] & 0xFF;
                n = paramArrayOfByte[6] & 0xFF;
            } else {
                i2 = 128;
                n = 128;
            }
            if (bluetoothdevmanager.maxJoystick > 2) {
                j = paramArrayOfByte[7] & 0xFF;
                k = paramArrayOfByte[8] & 0xFF;
            } else {
                k = 128;
                j = 128;
            }
            if (bluetoothdevmanager.maxJoystick > 3) {
                i1 = paramArrayOfByte[9] & 0xFF;
                i3 = paramArrayOfByte[10] & 0xFF;
            } else {
                i3 = 128;
                i1 = 128;
            }
            if (bluetoothdevmanager.maxJoystick > 4) {
                i7 = paramArrayOfByte[11] & 0xFF;
                i6 = paramArrayOfByte[12] & 0xFF;
                i4 = i;
                i5 = j;

                break;//label302
            }
        } else {
            m = paramArrayOfByte[3] & 0xFF;
            i = paramArrayOfByte[4] & 0xFF;
            i2 = paramArrayOfByte[5] & 0xFF;
            n = paramArrayOfByte[6] & 0xFF;
            i3 = 128;
            i1 = 128;
            k = 128;
            j = 128;
        }
        i7 = 128;
        i6 = 128;
        i5 = j;
        i4 = i;
        label302:
        i = i11;
        if ((!checkKey(paramArrayOfByte, 166)) && (!checkKey(paramArrayOfByte, 108)))
            this.keystate[0] = 0;
        else
            this.keystate[0] = 1;
        if ((!checkKey(paramArrayOfByte, 164)) && (!checkKey(paramArrayOfByte, 106)))
            this.keystate[1] = 0;
        else
            this.keystate[1] = 1;
        if ((!checkKey(paramArrayOfByte, 162)) && (!checkKey(paramArrayOfByte, 104)))
            this.keystate[2] = 0;
        else
            this.keystate[2] = 1;
        if ((!checkKey(paramArrayOfByte, 160)) && (!checkKey(paramArrayOfByte, 102)))
            this.keystate[3] = 0;
        else
            this.keystate[3] = 1;
        if ((!checkKey(paramArrayOfByte, 158)) && (!checkKey(paramArrayOfByte, 100)))
            this.keystate[4] = 0;
        else
            this.keystate[4] = 1;
        if ((!checkKey(paramArrayOfByte, 154)) && (!checkKey(paramArrayOfByte, 96)))
            this.keystate[5] = 0;
        else
            this.keystate[5] = 1;
        if ((!checkKey(paramArrayOfByte, 174)) && (!checkKey(paramArrayOfByte, 116)))
            this.keystate[6] = 0;
        else
            this.keystate[6] = 1;
        if ((!checkKey(paramArrayOfByte, 172)) && (!checkKey(paramArrayOfByte, 114)))
            this.keystate[7] = 0;
        else
            this.keystate[7] = 1;
        if ((!checkKey(paramArrayOfByte, 170)) && (!checkKey(paramArrayOfByte, 112)))
            this.keystate[8] = 0;
        else
            this.keystate[8] = 1;
        if ((!checkKey(paramArrayOfByte, 168)) && (!checkKey(paramArrayOfByte, 110)))
            this.keystate[9] = 0;
        else
            this.keystate[9] = 1;
        if ((!checkKey(paramArrayOfByte, 150)) && (!checkKey(paramArrayOfByte, 92)))
            this.keystate[12] = 0;
        else
            this.keystate[12] = 1;
        if ((!checkKey(paramArrayOfByte, 148)) && (!checkKey(paramArrayOfByte, 90)))
            this.keystate[13] = 0;
        else
            this.keystate[13] = 1;
        if ((!checkKey(paramArrayOfByte, 156)) && (!checkKey(paramArrayOfByte, 98)))
            this.keystate[14] = 0;
        else
            this.keystate[14] = 1;
        if ((!checkKey(paramArrayOfByte, 152)) && (!checkKey(paramArrayOfByte, 94)))
            this.keystate[15] = 0;
        else
            this.keystate[15] = 1;
        if ((!checkKey(paramArrayOfByte, 138)) && (!checkKey(paramArrayOfByte, 80)))
            this.keystate[10] = 0;
        else
            this.keystate[10] = 1;
        if ((!checkKey(paramArrayOfByte, 136)) && (!checkKey(paramArrayOfByte, 78)))
            this.keystate[11] = 0;
        else
            this.keystate[11] = 1;
        if ((!checkKey(paramArrayOfByte, 146)) && (!checkKey(paramArrayOfByte, 88)))
            this.keystate[18] = 0;
        else
            this.keystate[18] = 1;
        if ((!checkKey(paramArrayOfByte, 144)) && (!checkKey(paramArrayOfByte, 86)))
            this.keystate[19] = 0;
        else
            this.keystate[19] = 1;
        if ((!checkKey(paramArrayOfByte, 142)) && (!checkKey(paramArrayOfByte, 84)))
            this.keystate[20] = 0;
        else
            this.keystate[20] = 1;
        if ((!checkKey(paramArrayOfByte, 140)) && (!checkKey(paramArrayOfByte, 82)))
            this.keystate[21] = 0;
        else
            this.keystate[21] = 1;
        if ((m == 128) && (i4 == 128) && (i5 == 128) && (k == 128) && (i7 == 128) && (i6 == 128))
            this.keystate[16] = 0;
        else
            this.keystate[16] = 1;
        if ((i2 == 128) && (n == 128) && (i1 == 128) && (i3 == 128))
            this.keystate[17] = 0;
        else
            this.keystate[17] = 1;
        if ((m == 128) && (i4 == 128) && (i5 == 128) && (k == 128) && (i7 == 128) && (i6 == 128)) {
            this.ball1.setX(this.x1);
            this.ball1.setY(this.y1);
            this.textView2.setText("128");
            this.textView4.setText("128");
            this.keystate[16] = 0;
        }
        StringBuilder localStringBuilder;
        while (true) {
            break;
            if (m < 128)
                i = this.x1 - (128 - m);
            j = i10;
            if (m > 128)
                i = this.x1 + (m - 128);
            if (i4 < 128)
                j = this.y1 - (128 - i4);
            if (i4 > 128)
                j = this.y1 + (i4 - 128);
            if (i5 < 128)
                i = this.x1 - (128 - i5);
            if (i5 > 128)
                i = this.x1 + (i5 - 128);
            if (k < 128)
                j = this.y1 - (128 - k);
            if (k > 128)
                j = this.y1 + (k - 128);
            if (i7 < 128)
                i = this.x1 - (128 - i7);
            if (i7 > 128)
                i = this.x1 + (i7 - 128);
            if (i6 < 128)
                j = this.y1 - (128 - i6);
            if (i6 > 128)
                j = this.y1 + (i6 - 128);
            this.ball1.setX(i);
            this.ball1.setY(j);
            if ((m != 128) || (i4 != 128)) {
                paramArrayOfByte = this.textView2;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(m);
                paramArrayOfByte.setText(localStringBuilder.toString());
                paramArrayOfByte = this.textView4;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i4);
                paramArrayOfByte.setText(localStringBuilder.toString());
            }
            if ((i5 != 128) || (k != 128)) {
                paramArrayOfByte = this.textView2;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i5);
                paramArrayOfByte.setText(localStringBuilder.toString());
                paramArrayOfByte = this.textView4;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(k);
                paramArrayOfByte.setText(localStringBuilder.toString());
            }
            if ((i6 != 128) || (i6 != 128)) {
                paramArrayOfByte = this.textView2;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i6);
                paramArrayOfByte.setText(localStringBuilder.toString());
                paramArrayOfByte = this.textView4;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i6);
                paramArrayOfByte.setText(localStringBuilder.toString());
            }
            this.keystate[16] = 1;
        }
        if ((i2 == 128) && (n == 128) && (i1 == 128) && (i3 == 128)) {
            this.ball2.setX(this.x2);
            this.ball2.setY(this.y2);
            this.textView6.setText("128");
            this.textView8.setText("128");
            this.keystate[17] = 0;
        } else {
            i = i9;
            if (i2 < 128)
                i = this.x2 - (128 - i2);
            if (i2 > 128)
                i = this.x2 + (i2 - 128);
            j = i8;
            if (n < 128)
                j = this.y2 - (128 - n);
            if (n > 128)
                j = this.y2 + (n - 128);
            if (i1 < 128)
                i = this.x2 - (128 - i1);
            if (i1 > 128)
                i = this.x2 + (i1 - 128);
            if (i3 < 128)
                j = this.y2 - (128 - i3);
            if (i3 > 128)
                j = this.y2 + (i3 - 128);
            this.ball2.setX(i);
            this.ball2.setY(j);
            if ((i2 != 128) || (n != 128)) {
                paramArrayOfByte = this.textView6;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i2);
                paramArrayOfByte.setText(localStringBuilder.toString());
                paramArrayOfByte = this.textView8;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(n);
                paramArrayOfByte.setText(localStringBuilder.toString());
            }
            if ((i3 != 128) || (i3 != 128)) {
                paramArrayOfByte = this.textView6;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i3);
                paramArrayOfByte.setText(localStringBuilder.toString());
                paramArrayOfByte = this.textView8;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(i3);
                paramArrayOfByte.setText(localStringBuilder.toString());
            }
            this.keystate[17] = 1;
        }
        if ((this.keystate[0] == 1) && (this.keystateold[0] == 0))
            this.gpimageView[0].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[0] == 0) && (this.keystateold[0] == 1))
            this.gpimageView[0].setBackground(getResources().getDrawable(R.drawable.gpb1));
        if ((this.keystate[1] == 1) && (this.keystateold[1] == 0))
            this.gpimageView[1].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[1] == 0) && (this.keystateold[1] == 1))
            this.gpimageView[1].setBackground(getResources().getDrawable(R.drawable.gpb2));
        if ((this.keystate[2] == 1) && (this.keystateold[2] == 0))
            this.gpimageView[2].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[2] == 0) && (this.keystateold[2] == 1))
            this.gpimageView[2].setBackground(getResources().getDrawable(R.drawable.gpb3));
        if ((this.keystate[3] == 1) && (this.keystateold[3] == 0))
            this.gpimageView[3].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[3] == 0) && (this.keystateold[3] == 1))
            this.gpimageView[3].setBackground(getResources().getDrawable(R.drawable.gpb4));
        if ((this.keystate[4] == 1) && (this.keystateold[4] == 0))
            this.gpimageView[4].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[4] == 0) && (this.keystateold[4] == 1))
            if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
                this.gpimageView[4].setBackground(getResources().getDrawable(R.drawable.gpb5stk7));
            else
                this.gpimageView[4].setBackground(getResources().getDrawable(R.drawable.gpb5));
        if ((this.keystate[5] == 1) && (this.keystateold[5] == 0))
            this.gpimageView[5].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[5] == 0) && (this.keystateold[5] == 1))
            if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
                this.gpimageView[5].setBackground(getResources().getDrawable(R.drawable.gpb6stk7));
            else
                this.gpimageView[5].setBackground(getResources().getDrawable(R.drawable.gpb6));
        if ((this.keystate[6] == 1) && (this.keystateold[6] == 0))
            this.gpimageView[12].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[6] == 0) && (this.keystateold[6] == 1))
            this.gpimageView[12].setBackground(getResources().getDrawable(R.drawable.gpbup));
        if ((this.keystate[7] == 1) && (this.keystateold[7] == 0))
            this.gpimageView[13].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[7] == 0) && (this.keystateold[7] == 1))
            this.gpimageView[13].setBackground(getResources().getDrawable(R.drawable.gpbdown));
        if ((this.keystate[8] == 1) && (this.keystateold[8] == 0))
            this.gpimageView[14].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[8] == 0) && (this.keystateold[8] == 1))
            this.gpimageView[14].setBackground(getResources().getDrawable(R.drawable.gpbleft));
        if ((this.keystate[9] == 1) && (this.keystateold[9] == 0))
            this.gpimageView[15].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[9] == 0) && (this.keystateold[9] == 1))
            this.gpimageView[15].setBackground(getResources().getDrawable(R.drawable.gpbright));
        if ((this.keystate[10] == 1) && (this.keystateold[10] == 0))
            this.gpimageView[8].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[10] == 0) && (this.keystateold[10] == 1))
            this.gpimageView[8].setBackground(getResources().getDrawable(R.drawable.gpb10));
        if ((this.keystate[11] == 1) && (this.keystateold[11] == 0))
            this.gpimageView[9].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[11] == 0) && (this.keystateold[11] == 1))
            this.gpimageView[9].setBackground(getResources().getDrawable(R.drawable.gpb9));
        if ((this.keystate[12] == 1) && (this.keystateold[12] == 0))
            this.gpimageView[10].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[12] == 0) && (this.keystateold[12] == 1))
            this.gpimageView[10].setBackground(getResources().getDrawable(R.drawable.gpb11));
        if ((this.keystate[13] == 1) && (this.keystateold[13] == 0))
            this.gpimageView[11].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[13] == 0) && (this.keystateold[13] == 1))
            this.gpimageView[11].setBackground(getResources().getDrawable(R.drawable.gpb12));
        if ((this.keystate[14] == 1) && (this.keystateold[14] == 0))
            this.gpimageView[6].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[14] == 0) && (this.keystateold[14] == 1))
            if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
                this.gpimageView[6].setBackground(getResources().getDrawable(R.drawable.gpb7stk7));
            else
                this.gpimageView[6].setBackground(getResources().getDrawable(R.drawable.gpb7));
        if ((this.keystate[15] == 1) && (this.keystateold[15] == 0))
            this.gpimageView[7].setBackground(getResources().getDrawable(R.drawable.bp));
        if ((this.keystate[15] == 0) && (this.keystateold[15] == 1))
            if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
                this.gpimageView[7].setBackground(getResources().getDrawable(R.drawable.gpb8stk7));
            else
                this.gpimageView[7].setBackground(getResources().getDrawable(R.drawable.gpb8));
        if ((this.keystate[18] == 1) && (this.keystateold[18] == 0))
            this.gpimageView[18].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[18] == 0) && (this.keystateold[18] == 1))
            this.gpimageView[18].setBackground(getResources().getDrawable(R.drawable.gpbm1));
        if ((this.keystate[19] == 1) && (this.keystateold[19] == 0))
            this.gpimageView[19].setBackground(getResources().getDrawable(R.drawable.bp));
        if ((this.keystate[19] == 0) && (this.keystateold[19] == 1))
            this.gpimageView[19].setBackground(getResources().getDrawable(R.drawable.gpbm2));
        if ((this.keystate[20] == 1) && (this.keystateold[20] == 0))
            this.gpimageView[20].setBackground(getResources().getDrawable(R.drawable.gpbp));
        if ((this.keystate[20] == 0) && (this.keystateold[20] == 1))
            this.gpimageView[20].setBackground(getResources().getDrawable(R.drawable.gpbm3));
        if ((this.keystate[21] == 1) && (this.keystateold[21] == 0))
            this.gpimageView[21].setBackground(getResources().getDrawable(R.drawable.bp));
        if ((this.keystate[21] == 0) && (this.keystateold[21] == 1))
            this.gpimageView[21].setBackground(getResources().getDrawable(R.drawable.gpbm4));
        System.arraycopy(this.keystate, 0, this.keystateold, 0, 23);
    }

    private void gpdataprocessTest(byte[] paramArrayOfByte) {
        if (paramArrayOfByte[0] == 16)
            return;
        int i11 = this.x3;
        int i10 = this.y3;
        int i8 = this.x4;
        int i6 = this.y4;
        int j = this.x5;
        int i = this.y5;
        int i9 = this.x6;
        int i7 = this.y6;
        int k;
        int m;
        int i2;
        int i3;
        int i5;
        int i4;
        if (bluetoothdevmanager.mapVersion >= 5) {
            if (bluetoothdevmanager.maxJoystick > 0) {
                k = paramArrayOfByte[3] & 0xFF;
                m = paramArrayOfByte[4] & 0xFF;
            } else {
                k = 128;
                m = 128;
            }
            int n;
            int i1;
            if (bluetoothdevmanager.maxJoystick > 1) {
                n = paramArrayOfByte[5] & 0xFF;
                i1 = paramArrayOfByte[6] & 0xFF;
            } else {
                n = 128;
                i1 = 128;
            }
            if (bluetoothdevmanager.maxJoystick > 2) {
                i2 = paramArrayOfByte[7];
                i2 = paramArrayOfByte[8];
            }
            if (bluetoothdevmanager.maxJoystick > 3) {
                i2 = paramArrayOfByte[9];
                i2 = paramArrayOfByte[10];
            }
            i3 = n;
            i5 = k;
            i2 = i1;
            i4 = m;
            if (bluetoothdevmanager.maxJoystick > 4) {
                i2 = paramArrayOfByte[11];
                i2 = paramArrayOfByte[12];
                i3 = n;
                i5 = k;
                i2 = i1;
                i4 = m;
            }
        } else {
            i5 = paramArrayOfByte[3] & 0xFF;
            i4 = paramArrayOfByte[4] & 0xFF;
            i3 = paramArrayOfByte[5] & 0xFF;
            i2 = paramArrayOfByte[6] & 0xFF;
        }
        if (checkKey(paramArrayOfByte, 166))
            this.keystateTest[0] = 1;
        else
            this.keystateTest[0] = 0;
        if (checkKey(paramArrayOfByte, 164))
            this.keystateTest[1] = 1;
        else
            this.keystateTest[1] = 0;
        if (checkKey(paramArrayOfByte, 162))
            this.keystateTest[2] = 1;
        else
            this.keystateTest[2] = 0;
        if (checkKey(paramArrayOfByte, 160))
            this.keystateTest[3] = 1;
        else
            this.keystateTest[3] = 0;
        if (checkKey(paramArrayOfByte, 158))
            this.keystateTest[4] = 1;
        else
            this.keystateTest[4] = 0;
        if (checkKey(paramArrayOfByte, 154))
            this.keystateTest[5] = 1;
        else
            this.keystateTest[5] = 0;
        if (checkKey(paramArrayOfByte, 174))
            this.keystateTest[6] = 1;
        else
            this.keystateTest[6] = 0;
        if (checkKey(paramArrayOfByte, 156))
            this.keystateTest[7] = 1;
        else
            this.keystateTest[7] = 0;
        if (checkKey(paramArrayOfByte, 152))
            this.keystateTest[8] = 1;
        while (true) {
            break;
            this.keystateTest[8] = 0;
        }
        if ((i5 == 128) && (i4 == 128))
            this.keystateTest[9] = 0;
        else
            this.keystateTest[9] = 1;
        if ((i3 == 128) && (i2 == 128))
            this.keystateTest[10] = 0;
        else
            this.keystateTest[10] = 1;
        if (checkKey(paramArrayOfByte, 168))
            this.keystateTest[11] = 1;
        else
            this.keystateTest[11] = 0;
        if ((!checkKey(paramArrayOfByte, 170)) && (!checkKey(paramArrayOfByte, 112)))
            this.keystateTest[12] = 0;
        else
            this.keystateTest[12] = 1;
        if ((!checkKey(paramArrayOfByte, 172)) && (!checkKey(paramArrayOfByte, 114)))
            this.keystateTest[13] = 0;
        else
            this.keystateTest[13] = 1;
        if ((i5 == 128) && (i4 == 128)) {
            this.ball3.setX(this.x3);
            this.ball3.setY(this.y3);
            this.ball5.setX(this.x5);
            this.ball5.setY(this.y5);
            this.keystateTest[9] = 0;
        }
        while (true) {
            break;
            if (this.keystateTest[0] == 1) {
                k = i11;
                if (i5 < 128)
                    k = this.x3 - (128 - i5);
                m = k;
                if (i5 > 128)
                    m = this.x3 + (i5 - 128);
                k = i10;
                if (i4 < 128)
                    k = this.y3 - (128 - i4);
                if (i4 > 128)
                    k = this.y3 + (i4 - 128);
                this.ball3.setX(m);
                this.ball3.setY(k);
            } else if (this.keystateTest[4] == 1) {
                if (i5 < 128)
                    j = this.x5 - (128 - i5);
                if (i5 > 128)
                    j = this.x5 + (i5 - 128);
                if (i4 < 128)
                    i = this.y5 - (128 - i4);
                if (i4 > 128)
                    i = this.y5 + (i4 - 128);
                this.ball5.setX(j);
                this.ball5.setY(i);
            } else {
                this.ball3.setX(this.x3);
                this.ball3.setY(this.y3);
                this.ball5.setX(this.x5);
                this.ball5.setY(this.y5);
            }
            this.keystateTest[9] = 1;
        }
        if ((i3 == 128) && (i2 == 128)) {
            this.ball4.setX(this.x4);
            this.ball4.setY(this.y4);
            this.ball6.setX(this.x6);
            this.ball6.setY(this.y6);
            this.keystateTest[10] = 0;
        } else {
            if (this.keystateTest[7] == 1) {
                i = i8;
                if (i3 < 128)
                    i = this.x4 - (128 - i3);
                j = i;
                if (i3 > 128)
                    j = this.x4 + (i3 - 128);
                i = i6;
                if (i2 < 128)
                    i = this.y4 - (128 - i2);
                if (i2 > 128)
                    i = this.y4 + (i2 - 128);
                this.ball4.setX(j);
                this.ball4.setY(i);
            } else if (this.keystateTest[4] == 1) {
                if (i3 < 128)
                    j = this.x5 - (128 - i3);
                if (i3 > 128)
                    j = this.x5 + (i3 - 128);
                if (i2 < 128)
                    i = this.y5 - (128 - i2);
                if (i2 > 128)
                    i = this.y5 + (i2 - 128);
                this.ball5.setX(j);
                this.ball5.setY(i);
            } else if (this.keystateTest[8] == 1) {
                i = i9;
                if (i3 < 128)
                    i = this.x6 - (128 - i3);
                j = i;
                if (i3 > 128)
                    j = this.x6 + (i3 - 128);
                i = i7;
                if (i2 < 128)
                    i = this.y6 - (128 - i2);
                if (i2 > 128)
                    i = this.y6 + (i2 - 128);
                this.ball6.setX(j);
                this.ball6.setY(i);
            } else {
                this.ball4.setX(this.x4);
                this.ball4.setY(this.y4);
                this.ball6.setX(this.x6);
                this.ball6.setY(this.y6);
            }
            this.keystateTest[10] = 1;
        }
        if ((this.keystateTest[1] == 1) && (this.keystateTestold[1] == 0)) {
            this.gp_red_ball5.setVisibility(0);
            this.animation = new TranslateAnimation((float) (this.imageViewGPTest[1].getX() - this.imageViewGPTest[1].getWidth() * 1.2D), (float) (this.imageViewGPTest[2].getX() - this.imageViewGPTest[2].getWidth() * 1.2D), this.imageViewGPTest[1].getY(), this.imageViewGPTest[2].getY());
            this.animation.setDuration(210L);
            this.animation.setFillAfter(true);
            this.gp_red_ball5.startAnimation(this.animation);
        }
        if ((this.keystateTest[1] == 0) && (this.keystateTestold[1] == 1)) {
            if (this.animation != null)
                this.gp_red_ball5.clearAnimation();
            this.gp_red_ball5.setVisibility(8);
        }
        if ((this.keystateTest[2] == 1) && (this.keystateTestold[2] == 0)) {
            this.imageViewGPTest[3].setBackground(getResources().getDrawable(R.drawable.gpbp1));
            this.imageViewGPTest[4].setBackground(getResources().getDrawable(R.drawable.gpbp1));
        }
        if ((this.keystateTest[2] == 0) && (this.keystateTestold[2] == 1)) {
            this.imageViewGPTest[3].setBackground(getResources().getDrawable(R.drawable.gpxy1));
            this.imageViewGPTest[4].setBackground(getResources().getDrawable(R.drawable.gpxy2));
        }
        if ((this.keystateTest[3] == 1) && (this.keystateTestold[3] == 0)) {
            this.imageViewGPTest[5].setBackground(getResources().getDrawable(R.drawable.gpbp1));
            mUiHandler.sendEmptyMessageDelayed(10086, 50L);
        }
        if ((this.keystateTest[3] == 0) && (this.keystateTestold[3] == 1)) {
            this.imageViewGPTest[6].setBackground(getResources().getDrawable(R.drawable.gpbp1));
            mUiHandler.sendEmptyMessageDelayed(10087, 50L);
        }
        if ((this.keystateTest[6] == 1) && (this.keystateTestold[6] == 0))
            this.imageViewGPTest[0].setBackground(getResources().getDrawable(R.drawable.gpbp1));
        if ((this.keystateTest[6] == 0) && (this.keystateTestold[6] == 1))
            this.imageViewGPTest[0].setBackground(getResources().getDrawable(R.drawable.gpup));
        if (((this.keystateTest[5] == 1) && (this.keystateTest[11] == 1) && (this.keystateTestold[11] == 0)) || ((this.keystateTest[5] == 1) && (this.keystateTestold[5] == 0) && (this.keystateTest[11] == 1)))
            this.imageViewGPTest[7].setBackground(getResources().getDrawable(R.drawable.gpbp1));
        if (this.keystateTest[11] == 0)
            if (this.keystateTestold[11] == 1)
                break label2185;
        if ((this.keystateTest[5] == 0) && (this.keystateTestold[5] == 1))
            label2185:this.imageViewGPTest[7].setBackground(getResources().getDrawable(R.drawable.gprightcomrb));
        if ((this.keystateTest[12] == 1) && (this.keystateTestold[12] == 0) && (!this.isTurboNow)) {
            this.currentClickTime = new Date(System.currentTimeMillis()).getTime();
            if (this.currentClickTime - this.lastClickTime < 500L)
                return;
            this.lastClickTime = this.currentClickTime;
            this.isTurboNow = true;
            new TurboThread().start();
        }
        if ((this.keystateTest[12] == 0) && (this.keystateTestold[12] == 1)) {
            this.isTurboNow = false;
            this.imageViewGPTest[12].setBackground(getResources().getDrawable(R.drawable.gpleft));
        }
        if ((this.keystateTest[13] == 1) && (this.keystateTestold[13] == 0) && (!this.isHongNow)) {
            this.isHongNow = true;
            new HongThread().start();
        }
        if (this.keystateTest[13] == 0)
            i = this.keystateTestold[13];
        System.arraycopy(this.keystateTest, 0, this.keystateTestold, 0, 18);
    }

    private void initView() {
        this.tv_key_test = ((TextView) findViewById(R.id.tv_key_test));
        this.tv_function_test = ((TextView) findViewById(R.id.tv_function_test));
        this.tv_m1 = ((TextView) findViewById(R.id.tv_m1));
        this.tv_m2 = ((TextView) findViewById(R.id.tv_m2));
        this.tv_m3 = ((TextView) findViewById(R.id.tv_m3));
        this.kmTest = ((RelativeLayout) findViewById(R.id.emulayout_test));
        this.gpTest = ((RelativeLayout) findViewById(R.id.emulayout1_test));
        this.ball3 = ((ImageView) findViewById(R.id.gp_red_ball1));
        this.ball4 = ((ImageView) findViewById(R.id.gp_red_ball2));
        this.ball5 = ((ImageView) findViewById(R.id.gp_red_ball3));
        this.ball6 = ((ImageView) findViewById(R.id.gp_red_ball4));
        this.gp_red_ball5 = ((ImageView) findViewById(R.id.gp_red_ball5));
        this.imageViewGPTest[0] = ((ImageView) findViewById(R.id.iv_gpup));
        this.imageViewGPTest[1] = ((ImageView) findViewById(R.id.iv_gpbhp1));
        this.imageViewGPTest[2] = ((ImageView) findViewById(R.id.iv_gpbhp2));
        this.imageViewGPTest[3] = ((ImageView) findViewById(R.id.iv_gpxy1));
        this.imageViewGPTest[4] = ((ImageView) findViewById(R.id.iv_gpxy2));
        this.imageViewGPTest[5] = ((ImageView) findViewById(R.id.iv_gpy1));
        this.imageViewGPTest[6] = ((ImageView) findViewById(R.id.iv_gpy2));
        this.imageViewGPTest[7] = ((ImageView) findViewById(R.id.iv_gprightcomrb));
        this.imageViewGPTest[8] = ((ImageView) findViewById(R.id.iv_gpjs1));
        this.imageViewGPTest[9] = ((ImageView) findViewById(R.id.iv_gpjs2));
        this.imageViewGPTest[10] = ((ImageView) findViewById(R.id.iv_gpjs3));
        this.imageViewGPTest[11] = ((ImageView) findViewById(R.id.iv_gpjs4));
        this.imageViewGPTest[12] = ((ImageView) findViewById(R.id.iv_turbo));
        this.imageViewGPTest[13] = ((ImageView) findViewById(R.id.iv_hong1));
        this.imageViewGPTest[14] = ((ImageView) findViewById(R.id.iv_hong2));
        this.imageViewGPTest[15] = ((ImageView) findViewById(R.id.iv_hong3));
        this.imageViewGPTest[16] = ((ImageView) findViewById(R.id.iv_hong4));
        this.imageViewGPTest[17] = ((ImageView) findViewById(R.id.iv_hong5));
        this.tv_key_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                KeyTestActivity.this.tv_key_test.setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.bg_shape));
                KeyTestActivity.this.tv_function_test.setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.bg_shape2));
                KeyTestActivity.access$202(KeyTestActivity.this, false);
                KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                if (bluetoothdevmanager.devicemode == 0) {
                    KeyTestActivity.this.gp.setVisibility(View.GONE);
                    KeyTestActivity.this.km.setVisibility(View.VISIBLE);
                    return;
                }
                KeyTestActivity.this.gp.setVisibility(View.VISIBLE);
                KeyTestActivity.this.km.setVisibility(View.GONE);
            }
        });
        this.tv_function_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                KeyTestActivity.this.tv_key_test.setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.bg_shape2));
                KeyTestActivity.this.tv_function_test.setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.bg_shape));
                KeyTestActivity.access$202(KeyTestActivity.this, true);
                KeyTestActivity.this.gp.setVisibility(View.GONE);
                KeyTestActivity.this.km.setVisibility(View.GONE);
                if (bluetoothdevmanager.devicemode == 0) {
                    KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                    KeyTestActivity.this.kmTest.setVisibility(View.VISIBLE);
                    return;
                }
                KeyTestActivity.this.gpTest.setVisibility(View.VISIBLE);
                KeyTestActivity.this.kmTest.setVisibility(View.GONE);
            }
        });
        this.iv_ask1 = ((ImageView) findViewById(R.id.iv_ask1));
        this.iv_ask2 = ((ImageView) findViewById(R.id.iv_ask2));
        this.iv_ask3 = ((ImageView) findViewById(R.id.iv_ask3));
        this.iv_ask4 = ((ImageView) findViewById(R.id.iv_ask4));
        this.iv_ask5 = ((ImageView) findViewById(R.id.iv_ask5));
        this.iv_ask6 = ((ImageView) findViewById(R.id.iv_ask6));
        this.iv_ask7 = ((ImageView) findViewById(R.id.iv_ask7));
        this.iv_ask8 = ((ImageView) findViewById(R.id.iv_ask8));
        this.iv_ask_turbo = ((ImageView) findViewById(R.id.iv_ask_turbo));
        this.iv_ask_hong = ((ImageView) findViewById(R.id.iv_ask_hong));
        this.iv_ask1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op9)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.gp_setup_dialog_tip12)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op64)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op61)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op18)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op10)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op19)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.op55)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask_turbo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.turo_text2)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
        this.iv_ask_hong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (KeyTestActivity.this.mCustomTipDialog != null) {
                    if (KeyTestActivity.this.mCustomTipDialog.isShowing())
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    KeyTestActivity.access$502(KeyTestActivity.this, null);
                }
                KeyTestActivity.access$502(KeyTestActivity.this, new CustomTipDialog2(KeyTestActivity.this.mContext, KeyTestActivity.this.getResources().getString(R.string.op46), KeyTestActivity.this.getResources().getString(R.string.gp_setup_dialog_tip19)));
                KeyTestActivity.this.mCustomTipDialog.setClicklistener(new CustomTipDialog2.ClickListenerInterface() {
                    public void doConfirm() {
                        KeyTestActivity.this.mCustomTipDialog.dismiss();
                    }
                });
                KeyTestActivity.this.mCustomTipDialog.show();
            }
        });
    }

    private int postorid() {
        this.y1 = 0;
        this.y2 = 0;
        this.y3 = 0;
        this.y4 = 0;
        this.y5 = 0;
        this.y6 = 0;
        this.x1 = 0;
        this.x2 = 0;
        this.x3 = 0;
        this.x4 = 0;
        this.x5 = 0;
        this.x6 = 0;
        this.w1 = 0;
        this.h1 = 0;
        this.w2 = 0;
        this.h2 = 0;
        this.w3 = 0;
        this.h3 = 0;
        this.w4 = 0;
        this.h4 = 0;
        this.w5 = 0;
        this.h5 = 0;
        this.w6 = 0;
        this.h6 = 0;
        this.x7 = 0;
        this.y7 = 0;
        this.h7 = 0;
        this.w7 = 0;
        this.x8 = 0;
        this.y8 = 0;
        this.h8 = 7;
        this.w8 = 0;
        int[] arrayOfInt1 = new int[2];
        int[] arrayOfInt2 = new int[2];
        int[] arrayOfInt3 = new int[2];
        int[] arrayOfInt4 = new int[2];
        int[] arrayOfInt5 = new int[2];
        int[] arrayOfInt6 = new int[2];
        int[] arrayOfInt7 = new int[2];
        int[] arrayOfInt8 = new int[2];
        int[] arrayOfInt9 = new int[2];
        int[] arrayOfInt10 = new int[2];
        this.w1 = this.gpimageView[16].getWidth();
        this.h1 = this.gpimageView[16].getHeight();
        this.w2 = this.gpimageView[17].getWidth();
        this.h2 = this.gpimageView[17].getHeight();
        this.w3 = this.imageViewGPTest[8].getWidth();
        this.h3 = this.imageViewGPTest[8].getHeight();
        this.imageViewGPTest[8].getLocationInWindow(arrayOfInt4);
        this.w4 = this.imageViewGPTest[9].getWidth();
        this.h4 = this.imageViewGPTest[9].getHeight();
        this.imageViewGPTest[9].getLocationInWindow(arrayOfInt5);
        this.w5 = this.imageViewGPTest[10].getWidth();
        this.h5 = this.imageViewGPTest[10].getHeight();
        this.imageViewGPTest[10].getLocationInWindow(arrayOfInt6);
        this.w6 = this.imageViewGPTest[11].getWidth();
        this.h6 = this.imageViewGPTest[11].getHeight();
        this.imageViewGPTest[11].getLocationInWindow(arrayOfInt7);
        this.w7 = this.imageViewGPTest[1].getWidth();
        this.h7 = this.imageViewGPTest[1].getHeight();
        this.imageViewGPTest[1].getLocationInWindow(arrayOfInt9);
        this.w8 = this.imageViewGPTest[2].getWidth();
        this.h8 = this.imageViewGPTest[2].getHeight();
        this.imageViewGPTest[2].getLocationInWindow(arrayOfInt10);
        this.gpTest.getLocationInWindow(arrayOfInt8);
        this.gpimageView[16].getLocationInWindow(arrayOfInt1);
        this.gpimageView[17].getLocationInWindow(arrayOfInt2);
        this.gp.getLocationInWindow(arrayOfInt3);
        int k = this.ball1.getWidth();
        int m = this.ball1.getHeight();
        int n = this.ball2.getWidth();
        int i1 = this.ball2.getHeight();
        int i2 = this.ball3.getWidth();
        int i3 = this.ball3.getHeight();
        int i4 = this.ball4.getWidth();
        int i5 = this.ball4.getHeight();
        int i6 = this.ball5.getWidth();
        int i7 = this.ball5.getHeight();
        int i8 = this.ball6.getWidth();
        int i9 = this.ball6.getHeight();
        int j = this.gp_red_ball5.getWidth();
        int i = this.gp_red_ball5.getHeight();
        if (arrayOfInt1[0] == 0)
            return 0;
        this.x1 = (arrayOfInt1[0] + this.w1 / 2 - k / 2);
        this.y1 = (arrayOfInt1[1] + this.h1 / 2 - m / 2 - arrayOfInt3[1]);
        this.x2 = (arrayOfInt2[0] + this.w2 / 2 - n / 2);
        this.y2 = (arrayOfInt2[1] + this.h2 / 2 - i1 / 2 - arrayOfInt3[1]);
        this.x3 = (arrayOfInt4[0] + this.w3 / 2 - i2 / 2);
        this.y3 = (arrayOfInt4[1] + this.h3 / 2 - i3 / 2 - arrayOfInt8[1]);
        this.x4 = (arrayOfInt5[0] + this.w4 / 2 - i4 / 2);
        this.y4 = (arrayOfInt5[1] + this.h4 / 2 - i5 / 2 - arrayOfInt8[1]);
        this.x5 = (arrayOfInt6[0] + this.w5 / 2 - i6 / 2);
        this.y5 = (arrayOfInt6[1] + this.h5 / 2 - i7 / 2 - arrayOfInt8[1]);
        this.x6 = (arrayOfInt7[0] + this.w6 / 2 - i8 / 2);
        this.y6 = (arrayOfInt7[1] + this.h6 / 2 - i9 / 2 - arrayOfInt8[1]);
        k = arrayOfInt9[0];
        m = this.w7 / 2;
        j /= 2;
        this.x7 = (k + m - j);
        k = arrayOfInt9[1];
        m = this.h7 / 2;
        i /= 2;
        this.y7 = (k + m - i - arrayOfInt8[1]);
        this.x8 = (arrayOfInt10[0] + this.w8 / 2 - j);
        this.y8 = (arrayOfInt10[1] + this.h8 / 2 - i - arrayOfInt8[1]);
        this.ball1.setX(this.x1);
        this.ball1.setY(this.y1);
        this.ball2.setX(this.x2);
        this.ball2.setY(this.y2);
        this.ball3.setX(this.x3);
        this.ball3.setY(this.y3);
        this.ball4.setX(this.x4);
        this.ball4.setY(this.y4);
        this.ball5.setX(this.x5);
        this.ball5.setY(this.y5);
        this.ball6.setX(this.x6);
        this.ball6.setY(this.y6);
        this.gp_red_ball5.setX(this.x7);
        this.gp_red_ball5.setY(this.y7);
        fx = this.x7;
        fy = this.y7;
        tx = this.x8;
        ty = this.y8;
        this.gp_red_ball5.setVisibility(8);
        this.textView2.setText("128");
        this.textView4.setText("128");
        this.textView6.setText("128");
        this.textView8.setText("128");
        return 1;
    }

    public void dataprocess(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("test data = ");
        localStringBuilder.append(CommonUtils.byteToString(paramArrayOfByte));
        MyLog.i("my_tag", localStringBuilder.toString());
        int i = 0;
        while (i < 107) {
            if ((KeyMap.mkeymap[i].value != paramArrayOfByte[4]) && (KeyMap.mkeymap[i].value != paramArrayOfByte[5]) && (KeyMap.mkeymap[i].value != paramArrayOfByte[6]) && (KeyMap.mkeymap[i].value != paramArrayOfByte[7]) && (KeyMap.mkeymap[i].value != paramArrayOfByte[8]))
                this.keystate[i] = 0;
            else
                this.keystate[i] = 1;
            i += 1;
        }
        if ((paramArrayOfByte[3] & 0x1) == 1)
            this.keystate[96] = 1;
        else
            this.keystate[96] = 0;
        if ((paramArrayOfByte[3] & 0x2) == 2)
            this.keystate[97] = 1;
        else
            this.keystate[97] = 0;
        if ((paramArrayOfByte[3] & 0x4) == 4)
            this.keystate[98] = 1;
        else
            this.keystate[98] = 0;
        if ((paramArrayOfByte[9] & 0x1) == 1)
            this.keystate[104] = 1;
        else
            this.keystate[104] = 0;
        if ((paramArrayOfByte[9] & 0x4) == 4)
            this.keystate[105] = 1;
        else
            this.keystate[105] = 0;
        if ((paramArrayOfByte[9] & 0x2) == 2)
            this.keystate[106] = 1;
        else
            this.keystate[106] = 0;
        if ((this.keystate[53] == 1) && (this.keystateold[53] == 0))
            this.imageView[0].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[53] == 0) && (this.keystateold[53] == 1))
            this.imageView[0].setBackground(getResources().getDrawable(R.drawable.f1));
        if ((this.keystate[54] == 1) && (this.keystateold[54] == 0))
            this.imageView[1].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[54] == 0) && (this.keystateold[54] == 1))
            this.imageView[1].setBackground(getResources().getDrawable(R.drawable.f2));
        if ((this.keystate[55] == 1) && (this.keystateold[55] == 0))
            this.imageView[2].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[55] == 0) && (this.keystateold[55] == 1))
            this.imageView[2].setBackground(getResources().getDrawable(R.drawable.f3));
        if ((this.keystate[56] == 1) && (this.keystateold[56] == 0))
            this.imageView[3].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[56] == 0) && (this.keystateold[56] == 1))
            this.imageView[3].setBackground(getResources().getDrawable(R.drawable.f4));
        if ((this.keystate[57] == 1) && (this.keystateold[57] == 0))
            this.imageView[4].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[57] == 0) && (this.keystateold[57] == 1))
            this.imageView[4].setBackground(getResources().getDrawable(R.drawable.f5));
        if ((this.keystate[58] == 1) && (this.keystateold[58] == 0))
            this.imageView[5].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[58] == 0) && (this.keystateold[58] == 1))
            this.imageView[5].setBackground(getResources().getDrawable(R.drawable.f6));
        if ((this.keystate[0] == 1) && (this.keystateold[0] == 0))
            this.imageView[6].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[0] == 0) && (this.keystateold[0] == 1))
            this.imageView[6].setBackground(getResources().getDrawable(R.drawable.k1));
        if ((this.keystate[1] == 1) && (this.keystateold[1] == 0))
            this.imageView[7].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[1] == 0) && (this.keystateold[1] == 1))
            this.imageView[7].setBackground(getResources().getDrawable(R.drawable.k2));
        if ((this.keystate[2] == 1) && (this.keystateold[2] == 0))
            this.imageView[8].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[2] == 0) && (this.keystateold[2] == 1))
            this.imageView[8].setBackground(getResources().getDrawable(R.drawable.k3));
        if ((this.keystate[3] == 1) && (this.keystateold[3] == 0))
            this.imageView[9].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[3] == 0) && (this.keystateold[3] == 1))
            this.imageView[9].setBackground(getResources().getDrawable(R.drawable.k4));
        if ((this.keystate[4] == 1) && (this.keystateold[4] == 0))
            this.imageView[10].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[4] == 0) && (this.keystateold[4] == 1))
            this.imageView[10].setBackground(getResources().getDrawable(R.drawable.k5));
        if ((this.keystate[5] == 1) && (this.keystateold[5] == 0))
            this.imageView[11].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[5] == 0) && (this.keystateold[5] == 1))
            this.imageView[11].setBackground(getResources().getDrawable(R.drawable.k6));
        if ((this.keystate[6] == 1) && (this.keystateold[6] == 0))
            this.imageView[12].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[6] == 0) && (this.keystateold[6] == 1))
            this.imageView[12].setBackground(getResources().getDrawable(R.drawable.q));
        if ((this.keystate[7] == 1) && (this.keystateold[7] == 0))
            this.imageView[13].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[7] == 0) && (this.keystateold[7] == 1))
            this.imageView[13].setBackground(getResources().getDrawable(R.drawable.w));
        if ((this.keystate[8] == 1) && (this.keystateold[8] == 0))
            this.imageView[14].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[8] == 0) && (this.keystateold[8] == 1))
            this.imageView[14].setBackground(getResources().getDrawable(R.drawable.e));
        if ((this.keystate[9] == 1) && (this.keystateold[9] == 0))
            this.imageView[15].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[9] == 0) && (this.keystateold[9] == 1))
            this.imageView[15].setBackground(getResources().getDrawable(R.drawable.r));
        if ((this.keystate[10] == 1) && (this.keystateold[10] == 0))
            this.imageView[16].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[10] == 0) && (this.keystateold[10] == 1))
            this.imageView[16].setBackground(getResources().getDrawable(R.drawable.t));
        if ((this.keystate[11] == 1) && (this.keystateold[11] == 0))
            this.imageView[17].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[11] == 0) && (this.keystateold[11] == 1))
            this.imageView[17].setBackground(getResources().getDrawable(R.drawable.y));
        if ((this.keystate[12] == 1) && (this.keystateold[12] == 0))
            this.imageView[18].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[12] == 0) && (this.keystateold[12] == 1))
            this.imageView[18].setBackground(getResources().getDrawable(R.drawable.a));
        if ((this.keystate[13] == 1) && (this.keystateold[13] == 0))
            this.imageView[19].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[13] == 0) && (this.keystateold[13] == 1))
            this.imageView[19].setBackground(getResources().getDrawable(R.drawable.s));
        if ((this.keystate[14] == 1) && (this.keystateold[14] == 0))
            this.imageView[20].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[14] == 0) && (this.keystateold[14] == 1))
            this.imageView[20].setBackground(getResources().getDrawable(R.drawable.d));
        if ((this.keystate[15] == 1) && (this.keystateold[15] == 0))
            this.imageView[21].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[15] == 0) && (this.keystateold[15] == 1))
            this.imageView[21].setBackground(getResources().getDrawable(R.drawable.f));
        if ((this.keystate[16] == 1) && (this.keystateold[16] == 0))
            this.imageView[22].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[16] == 0) && (this.keystateold[16] == 1))
            this.imageView[22].setBackground(getResources().getDrawable(R.drawable.g));
        if ((this.keystate[17] == 1) && (this.keystateold[17] == 0))
            this.imageView[23].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[17] == 0) && (this.keystateold[17] == 1))
            this.imageView[23].setBackground(getResources().getDrawable(R.drawable.h));
        if ((this.keystate[18] == 1) && (this.keystateold[18] == 0))
            this.imageView[24].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[18] == 0) && (this.keystateold[18] == 1))
            this.imageView[24].setBackground(getResources().getDrawable(R.drawable.z));
        if ((this.keystate[19] == 1) && (this.keystateold[19] == 0))
            this.imageView[25].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[19] == 0) && (this.keystateold[19] == 1))
            this.imageView[25].setBackground(getResources().getDrawable(R.drawable.x));
        if ((this.keystate[20] == 1) && (this.keystateold[20] == 0))
            this.imageView[26].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[20] == 0) && (this.keystateold[20] == 1))
            this.imageView[26].setBackground(getResources().getDrawable(R.drawable.c));
        if ((this.keystate[21] == 1) && (this.keystateold[21] == 0))
            this.imageView[27].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[21] == 0) && (this.keystateold[21] == 1))
            this.imageView[27].setBackground(getResources().getDrawable(R.drawable.v));
        if ((this.keystate[22] == 1) && (this.keystateold[22] == 0))
            this.imageView[28].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[22] == 0) && (this.keystateold[22] == 1))
            this.imageView[28].setBackground(getResources().getDrawable(R.drawable.b));
        if ((this.keystate[23] == 1) && (this.keystateold[23] == 0))
            this.imageView[29].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[23] == 0) && (this.keystateold[23] == 1))
            this.imageView[29].setBackground(getResources().getDrawable(R.drawable.n));
        if ((this.keystate[24] == 1) && (this.keystateold[24] == 0))
            this.imageView[40].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[24] == 0) && (this.keystateold[24] == 1))
            this.imageView[40].setBackground(getResources().getDrawable(R.drawable.k));
        if ((this.keystate[25] == 1) && (this.keystateold[25] == 0))
            this.imageView[41].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[25] == 0) && (this.keystateold[25] == 1))
            this.imageView[41].setBackground(getResources().getDrawable(R.drawable.m));
        if ((this.keystate[26] == 1) && (this.keystateold[26] == 0))
            this.imageView[38].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[26] == 0) && (this.keystateold[26] == 1))
            this.imageView[38].setBackground(getResources().getDrawable(R.drawable.o));
        if ((this.keystate[27] == 1) && (this.keystateold[27] == 0))
            this.imageView[39].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[27] == 0) && (this.keystateold[27] == 1))
            this.imageView[39].setBackground(getResources().getDrawable(R.drawable.p));
        if ((this.keystate[42] == 1) && (this.keystateold[42] == 0))
            this.imageView[42].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[42] == 0) && (this.keystateold[42] == 1))
            this.imageView[42].setBackground(getResources().getDrawable(R.drawable.space));
        if ((this.keystate[29] == 1) && (this.keystateold[29] == 0))
            this.imageView[43].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[29] == 0) && (this.keystateold[29] == 1))
            this.imageView[43].setBackground(getResources().getDrawable(R.drawable.dot));
        if ((this.keystate[97] == 1) && (this.keystateold[97] == 0))
            this.imageView[44].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[97] == 0) && (this.keystateold[97] == 1))
            this.imageView[44].setBackground(getResources().getDrawable(R.drawable.leftshift));
        if ((this.keystate[104] == 1) && (this.keystate[105] == 0) && (this.keystate[106] == 0))
            this.imageView[30].setImageResource(R.drawable.mouseleft);
        else if ((this.keystate[104] == 0) && (this.keystate[105] == 1) && (this.keystate[106] == 0))
            this.imageView[30].setImageResource(R.drawable.mousemiddle);
        else if ((this.keystate[104] == 0) && (this.keystate[105] == 0) && (this.keystate[106] == 1))
            this.imageView[30].setImageResource(R.drawable.mouseright);
        else if ((this.keystate[104] == 1) && (this.keystate[105] == 1) && (this.keystate[106] == 0))
            this.imageView[30].setImageResource(R.drawable.mouselm);
        else if ((this.keystate[104] == 0) && (this.keystate[105] == 1) && (this.keystate[106] == 1))
            this.imageView[30].setImageResource(R.drawable.mousemr);
        else if ((this.keystate[104] == 1) && (this.keystate[105] == 0) && (this.keystate[106] == 1))
            this.imageView[30].setImageResource(R.drawable.mouselr);
        else if ((this.keystate[104] == 1) && (this.keystate[105] == 1) && (this.keystate[106] == 1))
            this.imageView[30].setImageResource(R.drawable.mouselmr);
        else
            this.imageView[30].setImageResource(R.drawable.mouse);
        if ((this.keystate[39] == 1) && (this.keystateold[39] == 0))
            this.imageView[33].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[39] == 0) && (this.keystateold[39] == 1))
            this.imageView[33].setBackground(getResources().getDrawable(R.drawable.esc));
        if ((this.keystate[41] == 1) && (this.keystateold[41] == 0))
            this.imageView[34].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[41] == 0) && (this.keystateold[41] == 1))
            this.imageView[34].setBackground(getResources().getDrawable(R.drawable.tab));
        if ((this.keystate[52] == 1) && (this.keystateold[52] == 0))
            this.imageView[35].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[52] == 0) && (this.keystateold[52] == 1))
            this.imageView[35].setBackground(getResources().getDrawable(R.drawable.capslock));
        if ((this.keystate[96] == 1) && (this.keystateold[96] == 0))
            this.imageView[36].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[96] == 0) && (this.keystateold[96] == 1))
            this.imageView[36].setBackground(getResources().getDrawable(R.drawable.leftctl));
        if ((this.keystate[98] == 1) && (this.keystateold[98] == 0))
            this.imageView[37].setBackground(getResources().getDrawable(R.drawable.bp1));
        if ((this.keystate[98] == 0) && (this.keystateold[98] == 1))
            this.imageView[37].setBackground(getResources().getDrawable(R.drawable.leftalt));
        System.arraycopy(this.keystate, 0, this.keystateold, 0, 107);
    }

    void initgamepad() {
        this.gpimageView[0] = ((ImageView) findViewById(R.id.gpimageView1));
        this.gpimageView[1] = ((ImageView) findViewById(R.id.gpimageView2));
        this.gpimageView[2] = ((ImageView) findViewById(R.id.gpimageView3));
        this.gpimageView[3] = ((ImageView) findViewById(R.id.gpimageView4));
        this.gpimageView[4] = ((ImageView) findViewById(R.id.gpimageView5));
        this.gpimageView[5] = ((ImageView) findViewById(R.id.gpimageView6));
        this.gpimageView[6] = ((ImageView) findViewById(R.id.gpimageView7));
        this.gpimageView[7] = ((ImageView) findViewById(R.id.gpimageView8));
        if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7")) {
            this.gpimageView[4].setImageDrawable(getResources().getDrawable(R.drawable.gpb5stk7));
            this.gpimageView[5].setImageDrawable(getResources().getDrawable(R.drawable.gpb6stk7));
            this.gpimageView[6].setImageDrawable(getResources().getDrawable(R.drawable.gpb7stk7));
            this.gpimageView[7].setImageDrawable(getResources().getDrawable(R.drawable.gpb8stk7));
        } else {
            this.gpimageView[4].setImageDrawable(getResources().getDrawable(R.drawable.gpb5));
            this.gpimageView[5].setImageDrawable(getResources().getDrawable(R.drawable.gpb6));
            this.gpimageView[6].setImageDrawable(getResources().getDrawable(R.drawable.gpb7));
            this.gpimageView[7].setImageDrawable(getResources().getDrawable(R.drawable.gpb8));
        }
        this.gpimageView[9] = ((ImageView) findViewById(R.id.gpimageView9));
        this.gpimageView[8] = ((ImageView) findViewById(R.id.gpimageView10));
        this.gpimageView[10] = ((ImageView) findViewById(R.id.gpimageView11));
        this.gpimageView[11] = ((ImageView) findViewById(R.id.gpimageView12));
        this.gpimageView[12] = ((ImageView) findViewById(R.id.gpimageView13));
        this.gpimageView[13] = ((ImageView) findViewById(R.id.gpimageView14));
        this.gpimageView[14] = ((ImageView) findViewById(R.id.gpimageView15));
        this.gpimageView[15] = ((ImageView) findViewById(R.id.gpimageView16));
        this.gpimageView[16] = ((ImageView) findViewById(R.id.gpimageView17));
        this.gpimageView[17] = ((ImageView) findViewById(R.id.gpimageView18));
        this.gpimageView[18] = ((ImageView) findViewById(R.id.gpimageView22));
        this.gpimageView[19] = ((ImageView) findViewById(R.id.gpimageView23));
        this.gpimageView[20] = ((ImageView) findViewById(R.id.gpimageView24));
        this.gpimageView[21] = ((ImageView) findViewById(R.id.gpimageView25));
        this.ball1 = ((ImageView) findViewById(R.id.gpimageView20));
        this.ball2 = ((ImageView) findViewById(R.id.gpimageView21));
        this.textView2 = ((TextView) findViewById(R.id.gptextView2));
        this.textView4 = ((TextView) findViewById(R.id.gptextView4));
        this.textView6 = ((TextView) findViewById(R.id.gptextView6));
        this.textView8 = ((TextView) findViewById(R.id.gptextView8));
        postorid();
    }

    void initkm() {
        this.imageView[0] = ((ImageView) findViewById(R.id.imageView1));
        this.imageView[1] = ((ImageView) findViewById(R.id.imageView2));
        this.imageView[2] = ((ImageView) findViewById(R.id.imageView3));
        this.imageView[3] = ((ImageView) findViewById(R.id.imageView4));
        this.imageView[4] = ((ImageView) findViewById(R.id.imageView5));
        this.imageView[5] = ((ImageView) findViewById(R.id.imageView6));
        this.imageView[6] = ((ImageView) findViewById(R.id.imageView7));
        this.imageView[7] = ((ImageView) findViewById(R.id.imageView8));
        this.imageView[8] = ((ImageView) findViewById(R.id.imageView9));
        this.imageView[9] = ((ImageView) findViewById(R.id.imageView10));
        this.imageView[10] = ((ImageView) findViewById(R.id.imageView11));
        this.imageView[11] = ((ImageView) findViewById(R.id.imageView12));
        this.imageView[12] = ((ImageView) findViewById(R.id.imageView13));
        this.imageView[13] = ((ImageView) findViewById(R.id.imageView14));
        this.imageView[14] = ((ImageView) findViewById(R.id.imageView15));
        this.imageView[15] = ((ImageView) findViewById(R.id.imageView16));
        this.imageView[16] = ((ImageView) findViewById(R.id.imageView17));
        this.imageView[17] = ((ImageView) findViewById(R.id.imageView18));
        this.imageView[18] = ((ImageView) findViewById(R.id.imageView19));
        this.imageView[19] = ((ImageView) findViewById(R.id.imageView20));
        this.imageView[20] = ((ImageView) findViewById(R.id.imageView21));
        this.imageView[21] = ((ImageView) findViewById(R.id.imageView22));
        this.imageView[22] = ((ImageView) findViewById(R.id.imageView23));
        this.imageView[23] = ((ImageView) findViewById(R.id.imageView24));
        this.imageView[24] = ((ImageView) findViewById(R.id.imageView25));
        this.imageView[25] = ((ImageView) findViewById(R.id.imageView26));
        this.imageView[26] = ((ImageView) findViewById(R.id.imageView27));
        this.imageView[27] = ((ImageView) findViewById(R.id.imageView28));
        this.imageView[28] = ((ImageView) findViewById(R.id.imageView29));
        this.imageView[29] = ((ImageView) findViewById(R.id.imageView30));
        this.imageView[30] = ((ImageView) findViewById(R.id.imageView31));
        this.imageView[33] = ((ImageView) findViewById(R.id.imageView50));
        this.imageView[34] = ((ImageView) findViewById(R.id.imageView51));
        this.imageView[35] = ((ImageView) findViewById(R.id.imageView52));
        this.imageView[36] = ((ImageView) findViewById(R.id.imageView53));
        this.imageView[37] = ((ImageView) findViewById(R.id.imageView54));
        this.imageView[38] = ((ImageView) findViewById(R.id.imageView42));
        this.imageView[39] = ((ImageView) findViewById(R.id.imageView43));
        this.imageView[40] = ((ImageView) findViewById(R.id.imageView44));
        this.imageView[41] = ((ImageView) findViewById(R.id.imageView45));
        this.imageView[42] = ((ImageView) findViewById(R.id.imageView46));
        this.imageView[43] = ((ImageView) findViewById(R.id.imageView47));
        this.imageView[44] = ((ImageView) findViewById(R.id.imageView48));
        bluetoothdevmanager.setCallbackt(new bluetoothdevmanager.Callback0() {
            public void onDataChange(byte[] paramAnonymousArrayOfByte) {
                Message localMessage = new Message();
                localMessage.what = 6;
                localMessage.obj = paramAnonymousArrayOfByte;
                if (KeyTestActivity.mUiHandler != null)
                    KeyTestActivity.mUiHandler.sendMessage(localMessage);
            }
        });
        mUiHandler = new Handler(getMainLooper()) {
            public void handleMessage(Message paramAnonymousMessage) {
                int i = paramAnonymousMessage.what;
                Object localObject;
                StringBuilder localStringBuilder;
                switch (i) {
                    default:
                        switch (i) {
                            default:
                                switch (i) {
                                    default:
                                        switch (i) {
                                            default:
                                                break;
                                            case 10100:
                                                KeyTestActivity.this.imageViewGPTest[12].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpleft));
                                                break;
                                            case 10099:
                                                KeyTestActivity.this.imageViewGPTest[12].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                                break;
                                            case 10098:
                                                KeyTestActivity.this.imageViewGPTest[17].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpdown));
                                                break;
                                            case 10097:
                                                KeyTestActivity.this.imageViewGPTest[16].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpdown));
                                                break;
                                            case 10096:
                                                KeyTestActivity.this.imageViewGPTest[15].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpdown));
                                                break;
                                            case 10095:
                                                KeyTestActivity.this.imageViewGPTest[14].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpdown));
                                                break;
                                            case 10094:
                                                KeyTestActivity.this.imageViewGPTest[13].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpdown));
                                                break;
                                            case 10093:
                                                KeyTestActivity.this.imageViewGPTest[17].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                                KeyTestActivity.access$802(KeyTestActivity.this, false);
                                                break;
                                            case 10092:
                                                KeyTestActivity.this.imageViewGPTest[16].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                                break;
                                            case 10091:
                                                KeyTestActivity.this.imageViewGPTest[15].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                                break;
                                            case 10090:
                                                KeyTestActivity.this.imageViewGPTest[14].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                                break;
                                            case 10089:
                                                KeyTestActivity.this.imageViewGPTest[13].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpbp1));
                                        }
                                        break;
                                    case 10087:
                                        KeyTestActivity.this.imageViewGPTest[6].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpy2));
                                        break;
                                    case 10086:
                                        KeyTestActivity.this.imageViewGPTest[5].setBackground(KeyTestActivity.this.getResources().getDrawable(R.drawable.gpy1));
                                }
                                break;
                            case 45:
                                KeyTestActivity.textconnstate.setTextColor(-16711936);
                                KeyTestActivity.textconnstate.setText(R.string.connok);
                                if (bluetoothdevmanager.devicemode == 0) {
                                    KeyTestActivity.this.tv_key_test.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_function_test.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m1.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m2.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m3.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                                    KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                    KeyTestActivity.this.gp.setVisibility(View.GONE);
                                    KeyTestActivity.this.km.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.km.invalidate();
                                } else {
                                    KeyTestActivity.this.tv_key_test.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_function_test.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m1.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m2.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m3.setVisibility(View.GONE);
                                    if (KeyTestActivity.this.isFunctionTest) {
                                        KeyTestActivity.this.gpTest.setVisibility(View.VISIBLE);
                                        KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                        KeyTestActivity.this.gp.setVisibility(View.GONE);
                                        KeyTestActivity.this.km.setVisibility(View.GONE);
                                    } else {
                                        KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                                        KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                        KeyTestActivity.this.km.setVisibility(View.GONE);
                                        KeyTestActivity.this.gp.setVisibility(View.VISIBLE);
                                        new Handler().postDelayed(new Runnable() {
                                                                      public void run() {
                                                                          while (KeyTestActivity.this.postorid() == 0)
                                                                              SystemClock.sleep(500L);
                                                                      }
                                                                  }
                                                , 0L);
                                    }
                                }
                                if (bluetoothdevmanager.devicemode == 0)
                                    KeyTestActivity.iv_device_icon.setImageResource(R.drawable.spic_launcher3);
                                else
                                    KeyTestActivity.iv_device_icon.setImageResource(R.drawable.gpic_launcher);
                                localObject = KeyTestActivity.textconnstate;
                                localStringBuilder = new StringBuilder();
                                localStringBuilder.append(bluetoothdevmanager.mBluetoothName);
                                localStringBuilder.append(" ");
                                localStringBuilder.append(KeyTestActivity.this.getResources().getString(R.string.connected));
                                ((TextView) localObject).setText(localStringBuilder.toString());
                                break;
                            case 44:
                                KeyTestActivity.textconnstate.setTextColor(-65536);
                                KeyTestActivity.textconnstate.setText(R.string.connerror);
                                if (bluetoothdevmanager.devicemode == 0) {
                                    KeyTestActivity.this.tv_key_test.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_function_test.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m1.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m2.setVisibility(View.GONE);
                                    KeyTestActivity.this.tv_m3.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                                    KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                    KeyTestActivity.this.gp.setVisibility(View.GONE);
                                    KeyTestActivity.this.km.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.km.invalidate();
                                } else {
                                    KeyTestActivity.this.tv_key_test.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_function_test.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m1.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m2.setVisibility(View.VISIBLE);
                                    KeyTestActivity.this.tv_m3.setVisibility(View.GONE);
                                    if (KeyTestActivity.this.isFunctionTest) {
                                        KeyTestActivity.this.gpTest.setVisibility(View.VISIBLE);
                                        KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                        KeyTestActivity.this.gp.setVisibility(View.GONE);
                                        KeyTestActivity.this.km.setVisibility(View.GONE);
                                    } else {
                                        KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                                        KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                        new Handler().postDelayed(new Runnable() {
                                                                      public void run() {
                                                                          while (KeyTestActivity.this.postorid() == 0)
                                                                              SystemClock.sleep(500L);
                                                                      }
                                                                  }
                                                , 0L);
                                        KeyTestActivity.this.km.setVisibility(View.GONE);
                                        KeyTestActivity.this.gp.setVisibility(View.VISIBLE);
                                    }
                                }
                                break;
                        }
                        break;
                    case 6:
                        localObject = new StringBuilder();
                        ((StringBuilder) localObject).append("------Test data: ");
                        ((StringBuilder) localObject).append(CommonUtils.byteToString((byte[]) paramAnonymousMessage.obj));
                        MyLog.i("test_data", ((StringBuilder) localObject).toString());
                        if (bluetoothdevmanager.devicemode == 0) {
                            if (!KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.dataprocess((byte[]) paramAnonymousMessage.obj);
                        } else if (bluetoothdevmanager.devicemode == 2)
                            if (KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.gpdataprocessTest((byte[]) paramAnonymousMessage.obj);
                            else
                                KeyTestActivity.this.gpdataprocess((byte[]) paramAnonymousMessage.obj);
                        break;
                    case 5:
                        if (bluetoothdevmanager.mConnectionState == 1) {
                            KeyTestActivity.textconnstate.setTextColor(-16711936);
                            KeyTestActivity.textconnstate.setText(R.string.connok);
                            if (bluetoothdevmanager.devicemode == 0)
                                KeyTestActivity.iv_device_icon.setImageResource(R.drawable.spic_launcher3);
                            else
                                KeyTestActivity.iv_device_icon.setImageResource(R.drawable.gpic_launcher);
                            localObject = KeyTestActivity.textconnstate;
                            localStringBuilder = new StringBuilder();
                            localStringBuilder.append(bluetoothdevmanager.mBluetoothName);
                            localStringBuilder.append(" ");
                            localStringBuilder.append(KeyTestActivity.this.getResources().getString(R.string.connected));
                            ((TextView) localObject).setText(localStringBuilder.toString());
                        } else {
                            KeyTestActivity.textconnstate.setTextColor(-65536);
                            KeyTestActivity.textconnstate.setText(R.string.connerror);
                            if (bluetoothdevmanager.devicemode == 0)
                                KeyTestActivity.iv_device_icon.setImageResource(R.drawable.spic_launcher3);
                            else
                                KeyTestActivity.iv_device_icon.setImageResource(R.drawable.gpic_launcher);
                        }
                        if (bluetoothdevmanager.devicemode == 0) {
                            if (!KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.dataprocess(KeyTestActivity.this.datapro2);
                        } else if (bluetoothdevmanager.devicemode == 2)
                            if (KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.gpdataprocessTest((byte[]) paramAnonymousMessage.obj);
                            else
                                KeyTestActivity.this.gpdataprocess(KeyTestActivity.this.datapro2gp);
                        break;
                    case 4:
                        if (bluetoothdevmanager.devicemode == 0) {
                            KeyTestActivity.this.tv_key_test.setVisibility(View.GONE);
                            KeyTestActivity.this.tv_function_test.setVisibility(View.GONE);
                            KeyTestActivity.this.tv_m1.setVisibility(View.GONE);
                            KeyTestActivity.this.tv_m2.setVisibility(View.GONE);
                            KeyTestActivity.this.tv_m3.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                            KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                            KeyTestActivity.this.gp.setVisibility(View.GONE);
                            KeyTestActivity.this.km.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.km.invalidate();
                        } else {
                            KeyTestActivity.this.tv_key_test.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.tv_function_test.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.tv_m1.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.tv_m2.setVisibility(View.VISIBLE);
                            KeyTestActivity.this.tv_m3.setVisibility(View.GONE);
                            if (KeyTestActivity.this.isFunctionTest) {
                                KeyTestActivity.this.gpTest.setVisibility(View.VISIBLE);
                                KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                KeyTestActivity.this.km.setVisibility(View.GONE);
                                KeyTestActivity.this.gp.setVisibility(View.GONE);
                            } else {
                                KeyTestActivity.this.gpTest.setVisibility(View.GONE);
                                KeyTestActivity.this.kmTest.setVisibility(View.GONE);
                                KeyTestActivity.this.km.setVisibility(View.GONE);
                                KeyTestActivity.this.gp.setVisibility(View.VISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                                              public void run() {
                                                                  while (KeyTestActivity.this.postorid() == 0)
                                                                      SystemClock.sleep(500L);
                                                              }
                                                          }
                                        , 0L);
                            }
                        }
                        break;
                    case 3:
                        KeyTestActivity.textconnstate.setTextColor(-16711936);
                        if (bluetoothdevmanager.devicemode == 0)
                            KeyTestActivity.iv_device_icon.setImageResource(R.drawable.spic_launcher3);
                        else
                            KeyTestActivity.iv_device_icon.setImageResource(R.drawable.gpic_launcher);
                        localObject = KeyTestActivity.textconnstate;
                        localStringBuilder = new StringBuilder();
                        localStringBuilder.append(bluetoothdevmanager.mBluetoothName);
                        localStringBuilder.append(" ");
                        localStringBuilder.append(KeyTestActivity.this.getResources().getString(R.string.connected));
                        ((TextView) localObject).setText(localStringBuilder.toString());
                        break;
                    case 0:
                        KeyTestActivity.textconnstate.setTextColor(-65536);
                        KeyTestActivity.textconnstate.setText(R.string.connerror);
                        if (bluetoothdevmanager.devicemode == 0) {
                            KeyTestActivity.iv_device_icon.setImageResource(R.drawable.spic_launcher3);
                        } else {
                            KeyTestActivity.iv_device_icon.setImageResource(R.drawable.gpic_launcher);
                            KeyTestActivity.this.postorid();
                        }
                        if (bluetoothdevmanager.devicemode == 0) {
                            if (!KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.dataprocess(KeyTestActivity.this.datapro2);
                        } else if (bluetoothdevmanager.devicemode == 2)
                            if (KeyTestActivity.this.isFunctionTest)
                                KeyTestActivity.this.gpdataprocessTest((byte[]) paramAnonymousMessage.obj);
                            else
                                KeyTestActivity.this.gpdataprocess(KeyTestActivity.this.datapro2gp);
                        break;
                    case 1:
                    case 2:
                }
                super.handleMessage(paramAnonymousMessage);
            }
        };
    }

    protected void onCreate(@Nullable Bundle paramBundle) {
        overridePendingTransition(0, 0);
        super.onCreate(paramBundle);
        this.mContext = this;
        FirstPageActivity.setIsTestKeyMode(true);
        isTest = true;
        setContentView(R.layout.activity_key_test);
        this.km = ((RelativeLayout) findViewById(R.id.emulayout));
        this.gp = ((RelativeLayout) findViewById(R.id.emulayout1));
        this.mContext = this;
        installing = 0;
        textconnstate = (TextView) findViewById(R.id.connstate);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_device_icon = (ImageView) findViewById(R.id.iv_device_icon);
        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                FirstPageActivity.setIsTestKeyMode(false);
                KeyTestActivity.isTest = false;
                KeyTestActivity.this.finish();
            }
        });
        initView();
        initkm();
        initgamepad();
    }

    protected void onDestroy() {
        super.onDestroy();
        FirstPageActivity.setIsTestKeyMode(false);
        isTest = false;
    }

    protected void onPause() {
        super.onPause();
        FirstPageActivity.setIsTestKeyMode(false);
        isTest = false;
    }

    protected void onResume() {
        super.onResume();
        FirstPageActivity.setIsTestKeyMode(true);
        isTest = true;
        Message localMessage = new Message();
        localMessage.what = 5;
        if (mUiHandler != null)
            mUiHandler.sendMessage(localMessage);
        new Message();
        new Thread() {
            public void run() {
                while (KeyTestActivity.isTest)
                    try {
                        BlueCmdManager.setDeviceWorkMode((byte) 1);
                        sleep(500L);
                    } catch (InterruptedException localInterruptedException) {
                        localInterruptedException.printStackTrace();
                    }
            }
        }
                .start();
    }

    protected void onStart() {
        super.onStart();
        FirstPageActivity.setIsTestKeyMode(true);
        isTest = true;
    }

    protected void onStop() {
        super.onStop();
        FirstPageActivity.setIsTestKeyMode(false);
        isTest = false;
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        if (paramBoolean) {
            this.tstop = 1;
            localObject = new Message();
            ((Message) localObject).what = 4;
            if (mUiHandler != null)
                mUiHandler.sendMessage((Message) localObject);
        } else {
            this.tstop = 0;
        }
        this.mSharedPreferences = getSharedPreferences("rembleflag", 0);
        Object localObject = this.mSharedPreferences.edit();
        ((SharedPreferences.Editor) localObject).putInt("tstopflag", this.tstop);
        ((SharedPreferences.Editor) localObject).commit();
        super.onWindowFocusChanged(paramBoolean);
    }

    class HongThread extends Thread {
        HongThread() {
        }

        public void run() {
            super.run();
            int i = 1;
            while ((KeyTestActivity.this.isHongNow) && (i < 6)) {
                if (i == 1)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10089);
                if (i == 2)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10090);
                if (i == 3)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10091);
                if (i == 4)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10092);
                if (i == 5)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10093);
                try {
                    sleep(100L);
                } catch (InterruptedException localInterruptedException1) {
                    localInterruptedException1.printStackTrace();
                }
                if (i == 1)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10094);
                if (i == 2)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10095);
                if (i == 3)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10096);
                if (i == 4)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10097);
                if (i == 5)
                    KeyTestActivity.mUiHandler.sendEmptyMessage(10098);
                i += 1;
                try {
                    sleep(230L);
                } catch (InterruptedException localInterruptedException2) {
                    localInterruptedException2.printStackTrace();
                }
            }
        }
    }

    class TurboThread extends Thread {
        TurboThread() {
        }

        public void run() {
            super.run();
            while (KeyTestActivity.this.isTurboNow) {
                KeyTestActivity.mUiHandler.sendEmptyMessage(10099);
                try {
                    sleep(100L);
                } catch (InterruptedException localInterruptedException1) {
                    localInterruptedException1.printStackTrace();
                }
                KeyTestActivity.mUiHandler.sendEmptyMessage(10100);
                try {
                    sleep(230L);
                } catch (InterruptedException localInterruptedException2) {
                    localInterruptedException2.printStackTrace();
                }
            }
        }
    }
}
