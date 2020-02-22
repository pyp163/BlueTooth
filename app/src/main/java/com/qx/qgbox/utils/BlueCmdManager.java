package com.qx.qgbox.utils;

import com.qx.qgbox.service.*;

public class BlueCmdManager
{
    public static final byte[] CMD_GET_DEVICE_CURRENT_WORK_MODE;
    public static final byte[] CMD_GET_DEVICE_IMAGE_INDEX;
    public static final byte[] CMD_GET_DEVICE_MAC_ADDRESS;
    public static final byte[] CMD_GET_DEVICE_MAP_MODE;
    public static final byte[] CMD_GET_DEVICE_PRODUCT_INFO;
    public static final byte[] CMD_GET_DEVICE_SN_CODE;
    public static final byte[] CMD_GET_GUN_PARM;
    public static final byte[] CMD_RESET_DEVICE;
    public static final byte[] CMD_RESET_GUN_PARM;
    public static final byte[] TOUCH_MODE_ABXY_MODE;
    public static final byte[] TOUCH_MODE_EYE_VIEW_RATIO_1;
    public static final byte[] TOUCH_MODE_EYE_VIEW_RATIO_2;
    public static final byte[] TOUCH_MODE_EYE_VIEW_RATIO_3;
    public static final byte[] TOUCH_MODE_EYE_VIEW_RATIO_4;
    
    static {
        TOUCH_MODE_EYE_VIEW_RATIO_1 = new byte[] { 32, 5, 3, 0, 1 };
        TOUCH_MODE_EYE_VIEW_RATIO_2 = new byte[] { 32, 5, 3, 0, 2 };
        TOUCH_MODE_EYE_VIEW_RATIO_3 = new byte[] { 32, 5, 3, 0, 3 };
        TOUCH_MODE_EYE_VIEW_RATIO_4 = new byte[] { 32, 5, 3, 0, 4 };
        TOUCH_MODE_ABXY_MODE = new byte[] { 32, 5, 3, 2, 0 };
        CMD_GET_DEVICE_SN_CODE = new byte[] { 16, 3, 1 };
        CMD_GET_DEVICE_IMAGE_INDEX = new byte[] { 16, 3, 35, 1 };
        CMD_GET_DEVICE_MAP_MODE = new byte[] { 16, 3, 2 };
        CMD_GET_DEVICE_PRODUCT_INFO = new byte[] { 16, 3, 3 };
        CMD_GET_DEVICE_CURRENT_WORK_MODE = new byte[] { 16, 3, 8 };
        CMD_GET_DEVICE_MAC_ADDRESS = new byte[] { 16, 3, 10 };
        CMD_RESET_DEVICE = new byte[] { 16, 3, 11 };
        CMD_GET_GUN_PARM = new byte[] { 22, 3, 33, 1 };
        CMD_RESET_GUN_PARM = new byte[] { 22, 3, 34, 1 };
    }
    
    public static void resetGunParm() {
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, BlueCmdManager.CMD_RESET_GUN_PARM);
        }
    }
    
    public static void saveGunparm(final int n, final int n2, final int n3, final int n4) {
        final byte b = (byte)n;
        final byte b2 = (byte)n2;
        final byte b3 = (byte)n3;
        final byte b4 = (byte)n4;
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, new byte[] { 22, 21, 13, 0, 0, 0, 0, 9, b, 9, b2, 9, b3, 9, b4, 0, 0, 0, 0, 0 });
        }
    }
    
    public static void saveGunparm2(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final byte b = (byte)n2;
        final byte b2 = (byte)n;
        final byte b3 = (byte)n4;
        final byte b4 = (byte)n3;
        final byte b5 = (byte)n6;
        final byte b6 = (byte)n5;
        final byte b7 = (byte)n8;
        final byte b8 = (byte)n7;
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, new byte[] { 22, 21, 13, 0, 0, 0, 0, b, b2, b3, b4, b5, b6, b7, b8, 0, 0, 0, 0, 0 });
        }
    }
    
    public static void saveMapDataToDevice(final byte b, final byte b2, final byte[] array, final int n) {
        final byte[] array2 = { 16, (byte)(n + 5), 4, b, b2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        System.arraycopy(array, 0, array2, 5, n);
        final StringBuilder sb = new StringBuilder();
        sb.append("------send header data: ");
        sb.append(CommonUtils.byteToString(array2));
        MyLog.i("CMD_SAVE_MAP", sb.toString());
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array2);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array2);
        }
    }
    
    public static void sendGetGunParm() {
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, BlueCmdManager.CMD_GET_GUN_PARM);
        }
    }
    
    public static void sendGetMapCmd(final byte b, final byte b2) {
        final byte[] array = { 16, 5, 9, b, b2 };
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array);
        }
    }
    
    public static void sendNormalCmd(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("sendNormalCmd:");
        sb.append(CommonUtils.byteToString(array));
        MyLog.i("sendNormalCmd", sb.toString());
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array);
        }
    }
    
    public static void sendRandomByteArray(final byte[] array) {
        final byte[] array2 = new byte[20];
        array2[0] = 16;
        array2[1] = 20;
        array2[2] = 39;
        final StringBuilder sb = new StringBuilder();
        sb.append("randomByteArray:");
        sb.append(CommonUtils.byteToString(array));
        MyLog.i("my_tag", sb.toString());
        System.arraycopy(array, 0, array2, 3, 17);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("sendRandomByteArray:");
        sb2.append(CommonUtils.byteToString(array2));
        MyLog.i("my_tag", sb2.toString());
        bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array2);
    }
    
    public static void sendResetCmd() {
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, BlueCmdManager.CMD_RESET_DEVICE);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, BlueCmdManager.CMD_RESET_DEVICE);
        }
    }
    
    public static void setDeviceBootloader() {
        final byte[] array2;
        final byte[] array = array2 = new byte[3];
        array2[0] = 16;
        array2[1] = 3;
        array2[2] = 31;
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array);
        }
    }
    
    public static void setDeviceCurrentMap(final byte b) {
        final byte[] array = { 16, 4, 5, b };
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array);
        }
    }
    
    public static void setDeviceWorkMode(final byte b) {
        final byte[] array = { 16, 4, 7, b };
        if (bluetoothdevmanager.devicemode == 0) {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_WRITE_UUID_STP, array);
        }
        else {
            bluetoothdevmanager.writeBle(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_WRITE_UUID_GPP, array);
        }
    }
}
