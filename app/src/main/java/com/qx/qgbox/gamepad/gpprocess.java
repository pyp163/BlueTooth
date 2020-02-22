package com.qx.qgbox.gamepad;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.qx.qgbox.service.bluetoothdevmanager;

import static com.tencent.bugly.crashreport.crash.c.k;

public class gpprocess
        implements IChange {
    public static String curpro;
    public static String[] keyinfo = {"A", "B", "X", "Y", "L1", "R1", "UP", "DOWN", "LEFT", "RIGHT", "START", "SELECT", "L3", "R3", "L2", "R2", "jl", "jr", "A+", "B+", "X+", "Y+", "F1", "F2", "F3", "F4", "F5", "F7", "F8", "F9", "F10", "TOUCH", "SENSOR"};
    static int oldl = 0;
    static int oldr = 0;
    public static int whichprofile = 1;
    int rmflag = 0;

    public int ScreenOriatation(Context paramContext) {
        if (paramContext.getResources().getConfiguration().orientation == 1)
            return 1;
        return 0;
    }

    public void change(int paramInt1, int paramInt2) {
        int i;
        int j;
        if (paramInt1 < 128)
            i = 128 - paramInt1;
        else
            i = 0;
        if (paramInt1 > 128)
            i = paramInt1 - 128;

        if (paramInt2 < 128)
            j = 128 - paramInt2;
        else
            j = 0;
        if (paramInt2 > 128)
            k = paramInt2 - 128;
        else
            k = j;
            j = k;
        if (120 < paramInt2) {
            j = k;
            if (paramInt2 < 136)
                j = 0;
        }
        int k = i;
        if (120 < paramInt1) {
            k = i;
            if (paramInt1 < 136)
                k = 0;
        }
        paramInt1 = j;
        paramInt2 = k;
        if (bluetoothdevmanager.bluetoothtype == 1) {
            byte[] arrayOfByte;
            if ((k == 0) && (j == 0) && (this.rmflag < 5)) {
                this.rmflag += 1;
                arrayOfByte = new byte[5];
                paramInt2 = (int) (k * 2 * 80 / 100.0D);
                paramInt1 = (int) (j * 2 * 80 / 100.0D);
                arrayOfByte[0] = 32;
                arrayOfByte[1] = 5;
                arrayOfByte[2] = 2;
                arrayOfByte[3] = ((byte) paramInt2);
                arrayOfByte[4] = ((byte) paramInt1);
                if ((bluetoothdevmanager.devicemode != 1) && (bluetoothdevmanager.devicemode != 2))
                    bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, arrayOfByte);
                else
                    bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, arrayOfByte);
            } else if (k == oldl) {
                paramInt1 = j;
                paramInt2 = k;
                if (j == oldr) ;
            } else {
                this.rmflag = 0;
                arrayOfByte = new byte[5];
                paramInt2 = (int) (k * 2 * 80 / 100.0D);
                paramInt1 = (int) (j * 2 * 80 / 100.0D);
                arrayOfByte[0] = 32;
                arrayOfByte[1] = 5;
                arrayOfByte[2] = 2;
                arrayOfByte[3] = ((byte) paramInt2);
                arrayOfByte[4] = ((byte) paramInt1);
                if ((bluetoothdevmanager.devicemode != 1) && (bluetoothdevmanager.devicemode != 2))
                    bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, arrayOfByte);
                else
                    bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, arrayOfByte);
            }
        }
        i = paramInt1;
        j = paramInt2;
        if (bluetoothdevmanager.bluetoothtype == 2)
            if ((paramInt2 == 0) && (paramInt1 == 0) && (this.rmflag < 5)) {
                this.rmflag += 1;
                j = (int) (paramInt2 * 80 / 100.0D);
                i = (int) (paramInt1 * 80 / 100.0D);
                bluetoothdevmanager.sppwrite(new byte[]{78, 71, 16, 7, 2, (byte) j, (byte) i});
            } else if (paramInt2 == oldl) {
                i = paramInt1;
                j = paramInt2;
                if (paramInt1 == oldr) ;
            } else {
                this.rmflag = 0;
                j = (int) (paramInt2 * 80 / 100.0D);
                i = (int) (paramInt1 * 80 / 100.0D);
                bluetoothdevmanager.sppwrite(new byte[]{78, 71, 16, 7, 2, (byte) j, (byte) i});
            }
        oldr = i;
        oldl = j;
    }
}
