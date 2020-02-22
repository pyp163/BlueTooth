package com.qx.qgbox.service;

import com.qx.qgbox.entitys.*;
import com.qx.qgbox.gamemouse.*;
import android.util.*;
import android.content.*;
import com.qx.qgbox.activity.*;
import android.net.wifi.*;
import java.io.*;
import android.app.*;
import java.util.*;
import android.bluetooth.*;

import com.qx.qgbox.gamepad.greencursor;
import com.qx.qgbox.gamepad.*;
import android.view.*;
import android.graphics.*;
import android.annotation.*;
import android.os.*;
import android.support.annotation.*;
import android.content.res.*;
import com.qx.qgbox.views.*;
import com.qx.qgbox.utils.*;

public class bluetoothdevmanager extends Service
{
    public static final String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public static final String ACTION_DATA_WRITE_FAIL = "com.example.bluetooth.le.ACTION_DATA_WRITE_FAIL";
    public static final String ACTION_DATA_WRITE_SUCCESS = "com.example.bluetooth.le.ACTION_DATA_WRITE_SUCCESS";
    public static final UUID CHARACTER_763_FIRMWARE_UUID;
    public static final UUID CHARACTER_DEVICEINFO_UUID;
    public static final UUID CHARACTER_FIRMWARE_UUID;
    public static final UUID CHARACTER_READ1_UUID;
    public static final UUID CHARACTER_READ_UUID_GPP;
    public static final UUID CHARACTER_READ_UUID_STP;
    public static final UUID CHARACTER_WRITE_UUID_GPP;
    public static final UUID CHARACTER_WRITE_UUID_STP;
    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 0;
    public static final String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
    public static final String EXTRA_DATA_BYTE = "com.example.bluetooth.le.EXTRA_DATA_BYTE";
    public static final String EXTRA_UUID = "com.example.bluetooth.le.EXTRA_UUID";
    private static final int FAST_CLICK_DELAY_TIME = 2000;
    public static final int MSG_ON_HIDE_KEY_TIP = 20036;
    public static final int MSG_ON_HID_MOUSE = 20019;
    public static final int MSG_ON_LOAD_MAP_CALLBACK = 20015;
    public static final int MSG_ON_RESET_FLAG1 = 20008;
    public static final int MSG_ON_RESET_FLAG10 = 20034;
    public static final int MSG_ON_RESET_FLAG2 = 20011;
    public static final int MSG_ON_RESET_FLAG3 = 20020;
    public static final int MSG_ON_RESET_FLAG4 = 20021;
    public static final int MSG_ON_RESET_FLAG5 = 20024;
    public static final int MSG_ON_RESET_FLAG6 = 20025;
    public static final int MSG_ON_RESET_FLAG7 = 20026;
    public static final int MSG_ON_RESET_FLAG8 = 20027;
    public static final int MSG_ON_RESET_FLAG9 = 20028;
    public static final int MSG_ON_SAVE_MAP = 20013;
    public static final int MSG_ON_SAVE_MAP_CALLBACK = 20014;
    public static final int MSG_ON_SHOW_KEY_TIP = 20035;
    public static final int MSG_ON_SHOW_MOUSE_FLAG = 20017;
    public static final int MSG_ON_SHOW_MOUSE_FLAG2 = 20018;
    public static final int MSG_ON_SHOW_SETUP_BY_FLOAT_WINDOW = 20022;
    public static final int MSG_ON_SHOW_SETUP_BY_GAME_LIST = 20037;
    public static final int MSG_ON_SHOW_SETUP_ESC = 20033;
    public static final int MSG_ON_SHOW_SETUP_F1 = 20012;
    public static final int MSG_ON_SHOW_SETUP_F3 = 20023;
    public static final int MSG_ON_SHOW_SETUP_F4 = 20029;
    public static final int MSG_ON_SHOW_SETUP_F5 = 20030;
    public static final int MSG_ON_SHOW_SETUP_F6 = 20031;
    public static final int MSG_ON_SHOW_SETUP_F7 = 20032;
    public static final int MSG_ON_SHOW_TIP_F2 = 20016;
    public static final UUID SERVIE_READ_UUID_GPP;
    public static final UUID SERVIE_READ_UUID_STP;
    public static final String TAG = "bluetoothdevmanager";
    public static final UUID UUID_BLOCK;
    public static final UUID UUID_HEART_RATE_MEASUREMENT;
    public static final UUID UUID_IDENTFY;
    public static final UUID UUID_OTA_SERVICE;
    public static byte[] blueData;
    public static int bluetoothtype = 0;
    private static Callback3 callbaccmd;
    private static Callback1 callbackMouse;
    public static byte[] callbackdDatapro;
    private static Callback4 callbackgp;
    private static Callback5 callbackgun;
    private static Callback2 callbackm;
    private static Callback0 callbackt;
    public static boolean checkconnflag = false;
    public static int counterEsc = 0;
    public static int counterSelect = 0;
    public static int counterStart = 0;
    public static int counterf1 = 0;
    public static int counterf2 = 0;
    public static int counterf3 = 0;
    public static int counterf4 = 0;
    public static int counterf5 = 0;
    public static int counterf6 = 0;
    public static int counterf7 = 0;
    public static String currentGameName = "";
    public static CustomWarningDialog customWarningDialog;
    public static byte[] datapro;
    public static ArrayList<Integer> defaultGameId;
    public static float density = 3.0f;
    public static String devicePID;
    public static int deviceScenesMode = 0;
    public static String deviceVID = "14";
    public static int devicemode = 0;
    public static int dpipx = 1;
    public static String factoryName;
    public static int gpWorkModeCode = 0;
    public static bluetoothdevmanager instance;
    public static boolean isEpstMode = false;
    public static boolean isFormPresetList = false;
    public static int islandor = 1;
    public static boolean isspp = false;
    public static byte[] loaddatapro;
    public static byte[] loadgpdatapro;
    private static BluetoothAdapter mBluetoothAdapter;
    public static BluetoothGatt mBluetoothGatt;
    public static String mBluetoothName;
    public static int mConnectionState = 0;
    public static String mDeviceAddress;
    public static gpindicatordialog mGpindicatordialog;
    public static gpsetupdialog mGpsetupdialog;
    public static GunSetupdialog mGunSetupdialog;
    public static indicatordialog mNewIndicatordialog;
    public static newSetupdialog mNewSetupdialog;
    public static OfficialGamePreset mOfficialGamePreset;
    public static ArrayList<OfficialGamePreset> mOfficialGamePresetList;
    private static int macroBitNum = 0;
    public static int mapMaxNum = 4;
    public static int mapVersion = 0;
    public static int maxJoystick = 0;
    public static int maxKey = 24;
    public static int maxMacro = 0;
    public static int maxMacroPoint = 0;
    public static BluetoothDevice mdevice;
    public static int mflag = 0;
    public static greencursor mg;
    public static InputStream minputstream;
    public static byte[] mousedatapro;
    public static byte[] mousedatapro2;
    public static int mouseflag = 0;
    public static OutputStream moutputstream;
    public static Handler myHandler;
    public static int oldscreen = -2;
    public static WindowManager$LayoutParams params;
    public static int piaNum = 0;
    public static int radius = 280;
    public static float resolutionX = 1080.0f;
    public static float resolutionY = 1920.0f;
    public static byte[] saveDataPro;
    public static float scalex = 1.0f;
    public static float scaley = 1.0f;
    public static int screenHeight = 0;
    public static int screenWidth = 0;
    public static float screenhp = 1920.0f;
    public static float screenwp = 1080.0f;
    public static Handler servicehandle;
    public static int setupuiactivity = 0;
    public static BluetoothSocket socket;
    public static Handler threadhandle;
    public static Handler threadhandleMouse;
    public static int whichprofile = 1;
    int blstate;
    int blstateold;
    byte byte4;
    private Thread checkconnt;
    int checkcount;
    byte[] cmdon;
    byte[] datapro1;
    byte[] datapro1gp;
    byte[] datapro2;
    byte[] datapro2gp;
    byte[] dataprold;
    int dnum;
    int dnumold;
    Thread enumthread;
    Thread enumthreadMouse;
    private long laseTime;
    private long lastClickTime;
    private BluetoothGattCharacteristic mBluetoothGattCharateristic;
    private final BluetoothGattCallback mGattCallback;
    private Thread msppThread;
    String notificationId;
    NotificationManager notificationManager;
    String notificationName;
    
    static {
        CHARACTER_READ1_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        CHARACTER_WRITE_UUID_STP = UUID.fromString("93990002-1111-6666-8888-0123456789AB");
        SERVIE_READ_UUID_STP = UUID.fromString("93990001-1111-6666-8888-0123456789AB");
        CHARACTER_READ_UUID_STP = UUID.fromString("93990003-1111-6666-8888-0123456789AB");
        CHARACTER_WRITE_UUID_GPP = UUID.fromString("94990002-1111-6666-8888-0123456789AB");
        SERVIE_READ_UUID_GPP = UUID.fromString("94990001-1111-6666-8888-0123456789AB");
        CHARACTER_READ_UUID_GPP = UUID.fromString("94990003-1111-6666-8888-0123456789AB");
        CHARACTER_FIRMWARE_UUID = UUID.fromString("00002a26-0000-1000-8000-00805f9b34fb");
        CHARACTER_763_FIRMWARE_UUID = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");
        CHARACTER_DEVICEINFO_UUID = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
        UUID_HEART_RATE_MEASUREMENT = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
        UUID_OTA_SERVICE = UUID.fromString("f000ffc0-0451-4000-b000-000000000000");
        UUID_IDENTFY = UUID.fromString("f000ffc1-0451-4000-b000-000000000000");
        UUID_BLOCK = UUID.fromString("f000ffc2-0451-4000-b000-000000000000");
        bluetoothdevmanager.defaultGameId = new ArrayList<Integer>();
        bluetoothdevmanager.mOfficialGamePresetList = new ArrayList<OfficialGamePreset>();
        bluetoothdevmanager.datapro = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bluetoothdevmanager.blueData = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bluetoothdevmanager.mousedatapro = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bluetoothdevmanager.mousedatapro2 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        bluetoothdevmanager.callbackdDatapro = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    public bluetoothdevmanager() {
        this.enumthread = null;
        this.cmdon = new byte[] { 78, 71, 16, 6, 8, 1 };
        this.datapro2 = new byte[] { 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, -128 };
        this.dataprold = new byte[] { 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, -128 };
        this.datapro2gp = new byte[] { 32, 0, 0, 0, 0, 0, 0, -128, 0, -128, 0, -128, 0, -128, 0, 0, 0, 0, 0, 0 };
        this.datapro1gp = new byte[] { 32, 0, 0, 0, 0, 0, 0, -128, 0, -128, 0, -128, 0, -128, 0, 0, 0, 0, 0, 0 };
        this.byte4 = 0;
        this.datapro1 = new byte[] { 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, -128 };
        this.blstate = 0;
        this.blstateold = 0;
        this.dnum = 0;
        this.dnumold = 0;
        this.checkcount = 0;
        this.enumthreadMouse = null;
        this.notificationId = "channelId";
        this.notificationName = "channelName";
        this.laseTime = 0L;
        this.msppThread = null;
        this.checkconnt = null;
        this.mGattCallback = new BluetoothGattCallback() {
            public void onCharacteristicChanged(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                bluetoothdevmanager.this.broadcastUpdate("com.example.bluetooth.le.ACTION_DATA_AVAILABLE", bluetoothGattCharacteristic);
                if (bluetoothGattCharacteristic.getUuid().equals(bluetoothdevmanager.CHARACTER_READ_UUID_STP) || bluetoothGattCharacteristic.getUuid().equals(bluetoothdevmanager.CHARACTER_READ_UUID_GPP)) {
                    final byte[] value = bluetoothGattCharacteristic.getValue();
                    bluetoothdevmanager.mConnectionState = 1;
                    if (value != null && value.length == 20) {
                        System.arraycopy(value, 0, bluetoothdevmanager.blueData, 0, 20);
                        if (bluetoothdevmanager.threadhandle != null) {
                            final Bundle data = new Bundle();
                            data.putByteArray("send", bluetoothdevmanager.blueData);
                            final Message message = new Message();
                            message.setData(data);
                            bluetoothdevmanager.threadhandle.sendMessage(message);
                        }
                        System.arraycopy(value, 0, bluetoothdevmanager.mousedatapro2, 0, 20);
                        if (bluetoothdevmanager.threadhandleMouse != null) {
                            final Bundle data2 = new Bundle();
                            data2.putByteArray("send", bluetoothdevmanager.mousedatapro2);
                            final Message message2 = new Message();
                            message2.setData(data2);
                            bluetoothdevmanager.threadhandleMouse.sendMessage(message2);
                        }
                    }
                }
            }
            
            public void onCharacteristicRead(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int n) {
                if (n == 0) {
                    if (!bluetoothdevmanager.CHARACTER_FIRMWARE_UUID.equals(bluetoothGattCharacteristic.getUuid())) {
                        if (bluetoothdevmanager.CHARACTER_763_FIRMWARE_UUID.equals(bluetoothGattCharacteristic.getUuid())) {
                            bluetoothdevmanager.factoryName = bluetoothGattCharacteristic.getStringValue(0);
                            final StringBuilder sb = new StringBuilder();
                            sb.append("CHARACTER_763_FIRMWARE_UUID factoryName = ");
                            sb.append(bluetoothdevmanager.factoryName);
                            MyLog.i("my_tag", sb.toString());
                        }
                        else {
                            bluetoothdevmanager.this.broadcastUpdate("com.example.bluetooth.le.ACTION_DATA_AVAILABLE", bluetoothGattCharacteristic);
                        }
                    }
                }
            }
            
            public void onCharacteristicWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int n) {
                super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, n);
                if (n == 0) {
                    bluetoothdevmanager.this.broadcastUpdate("com.example.bluetooth.le.ACTION_DATA_WRITE_SUCCESS");
                }
            }
            
            public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, final int n, final int n2) {
                if (n2 == 2) {
                    final Message message = new Message();
                    message.arg1 = 6;
                    if (bluetoothdevmanager.servicehandle != null) {
                        bluetoothdevmanager.servicehandle.sendMessage(message);
                    }
                }
                else if (n2 == 0) {
                    final Message message2 = new Message();
                    message2.what = 0;
                    if (FirstPageActivity.mUiHandler != null) {
                        FirstPageActivity.mUiHandler.sendMessage(message2);
                    }
                    if (KeyTestActivity.mUiHandler != null) {
                        KeyTestActivity.mUiHandler.sendEmptyMessage(44);
                    }
                    bluetoothdevmanager.this.close();
                }
            }
            
            public void onReliableWriteCompleted(final BluetoothGatt bluetoothGatt, final int n) {
                super.onReliableWriteCompleted(bluetoothGatt, n);
                bluetoothdevmanager.this.broadcastUpdate("com.example.bluetooth.le.ACTION_DATA_WRITE_SUCCESS");
            }
            
            public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, final int n) {
                Label_0209: {
                    if (n == 0) {
                        final Iterator<BluetoothGattService> iterator = (Iterator<BluetoothGattService>)bluetoothGatt.getServices().iterator();
                        while (true) {
                            String string;
                            do {
                                final boolean hasNext = iterator.hasNext();
                                boolean b = false;
                                if (hasNext) {
                                    final BluetoothGattService bluetoothGattService = iterator.next();
                                    new HashMap();
                                    string = bluetoothGattService.getUuid().toString();
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("-------uuid = ");
                                    sb.append(string);
                                    MyLog.i("my_tag", sb.toString());
                                    if (!string.equals("93990001-1111-6666-8888-0123456789AB".toLowerCase())) {
                                        continue;
                                    }
                                    bluetoothdevmanager.devicemode = 0;
                                    b = true;
                                }
                                if (b) {
                                    bluetoothdevmanager.mConnectionState = 1;
                                    if (KeyTestActivity.mUiHandler != null) {
                                        KeyTestActivity.mUiHandler.sendEmptyMessage(45);
                                    }
                                    final Message message = new Message();
                                    message.what = 3;
                                    if (FirstPageActivity.mUiHandler != null) {
                                        FirstPageActivity.mUiHandler.sendMessage(message);
                                    }
                                    new Thread(new pollThread()).start();
                                }
                                break Label_0209;
                            } while (!string.equals("94990001-1111-6666-8888-0123456789AB".toLowerCase()));
                            bluetoothdevmanager.devicemode = 2;
                            continue;
                        }
                    }
                }
                super.onServicesDiscovered(bluetoothGatt, n);
            }
        };
        this.lastClickTime = 0L;
    }
    
    private void analyseMapData(final byte[] array) {
        if (array[4] != bluetoothdevmanager.piaNum) {
            if (array[4] == 0) {
                bluetoothdevmanager.loaddatapro = new byte[bluetoothdevmanager.macroBitNum + 264];
                bluetoothdevmanager.loadgpdatapro = new byte[bluetoothdevmanager.macroBitNum + 264];
            }
            if (bluetoothdevmanager.devicemode == 0) {
                System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, 15);
            }
            else {
                System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, 15);
            }
        }
        else {
            final int n = (bluetoothdevmanager.macroBitNum + 264) % 15;
            if (n == 0) {
                if (bluetoothdevmanager.devicemode == 0) {
                    System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, 15);
                }
                else {
                    System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, 15);
                }
            }
            else if (bluetoothdevmanager.devicemode == 0) {
                System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, n);
            }
            else {
                System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, n);
            }
        }
    }
    
    private void analyseMapDataV5(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("------analyseMapDataV5 data: ");
        sb.append(CommonUtils.byteToString(array));
        MyLog.i("CMD_SAVE_MAP", sb.toString());
        if (array[2] == 9 && array[4] <= bluetoothdevmanager.piaNum) {
            if (array[4] != bluetoothdevmanager.piaNum) {
                if (array[4] == 0) {
                    bluetoothdevmanager.loaddatapro = new byte[bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum];
                    bluetoothdevmanager.loadgpdatapro = new byte[bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum];
                }
                if (bluetoothdevmanager.devicemode == 0) {
                    System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, 15);
                }
                else {
                    System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, 15);
                }
            }
            else {
                final int n = (bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum) % 15;
                if (n == 0) {
                    if (bluetoothdevmanager.devicemode == 0) {
                        System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, 15);
                    }
                    else {
                        System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, 15);
                    }
                }
                else if (bluetoothdevmanager.devicemode == 0) {
                    System.arraycopy(array, 5, bluetoothdevmanager.loaddatapro, array[4] * 15, n);
                }
                else {
                    System.arraycopy(array, 5, bluetoothdevmanager.loadgpdatapro, array[4] * 15, n);
                }
            }
        }
    }
    
    private void broadcastUpdate(final String s) {
        this.sendBroadcast(new Intent(s));
    }
    
    private void broadcastUpdate(final String s, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        final Intent intent = new Intent(s);
        if (bluetoothdevmanager.UUID_HEART_RATE_MEASUREMENT.equals(bluetoothGattCharacteristic.getUuid())) {
            int n;
            if ((bluetoothGattCharacteristic.getProperties() & 0x1) != 0x0) {
                n = 18;
                Log.i("bluetoothdevmanager", "Heart rate format UINT16.");
            }
            else {
                n = 17;
                Log.i("bluetoothdevmanager", "Heart rate format UINT8.");
            }
            final int intValue = bluetoothGattCharacteristic.getIntValue(n, 1);
            Log.i("bluetoothdevmanager", String.format("Received heart rate: %d", intValue));
            intent.putExtra("com.example.bluetooth.le.EXTRA_DATA", String.valueOf(intValue));
        }
        else {
            final byte[] value = bluetoothGattCharacteristic.getValue();
            if (value != null && value.length > 0) {
                final StringBuilder sb = new StringBuilder(value.length);
                for (int length = value.length, i = 0; i < length; ++i) {
                    sb.append(String.format("%02X", value[i]));
                }
                intent.putExtra("com.example.bluetooth.le.EXTRA_DATA", sb.toString());
                intent.putExtra("com.example.bluetooth.le.EXTRA_DATA_BYTE", bluetoothGattCharacteristic.getValue());
                intent.putExtra("com.example.bluetooth.le.EXTRA_UUID", bluetoothGattCharacteristic.getUuid().toString());
            }
        }
        this.sendBroadcast(intent);
    }
    
    private boolean checkKey(final byte[] array, final int n) {
        for (int i = 2; i < array.length; ++i) {
            if ((array[i] & 0xFF) == n) {
                return true;
            }
        }
        return false;
    }
    
    public static int dip2px(final Context context, final float n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    public static void disconnect() {
        if (bluetoothdevmanager.mBluetoothAdapter != null && bluetoothdevmanager.mBluetoothGatt != null) {
            bluetoothdevmanager.mBluetoothGatt.disconnect();
        }
    }
    
    private int getIntegerByBit(final byte b, final byte b2) {
        return (b & 0xFF) | b2 << 8;
    }
    
    private Notification getNotification() {
        final Notification$Builder setSmallIcon = new Notification$Builder((Context)this).setSmallIcon(R.mipmap.ic_launcher);
        if (Build$VERSION.SDK_INT >= 26) {
            setSmallIcon.setChannelId(this.notificationId);
        }
        return setSmallIcon.build();
    }
    
    public static bluetoothdevmanager getService() {
        return bluetoothdevmanager.instance;
    }
    
    private void initMouse() {
        bluetoothdevmanager.mg = new greencursor((Context)this);
        bluetoothdevmanager.mg.getWindow().setGravity(Gravity.CENTER);//8388659
        bluetoothdevmanager.params = bluetoothdevmanager.mg.getWindow().getAttributes();
        final WindowManager$LayoutParams params = bluetoothdevmanager.params;
        params.flags |= 0x7D0;
        bluetoothdevmanager.params.x = (int)bluetoothdevmanager.resolutionX / 2;
        bluetoothdevmanager.params.y = (int)bluetoothdevmanager.resolutionY / 2;
        bluetoothdevmanager.mg.getWindow().setAttributes(bluetoothdevmanager.params);
        bluetoothdevmanager.dpipx = dip2px((Context)this, 1.0f);
        bluetoothdevmanager.myHandler = new Handler(this.getMainLooper()) {
            public void handleMessage(final Message message) {
                if (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                    bluetoothdevmanager.islandor = 1;
                }
                else {
                    bluetoothdevmanager.islandor = 0;
                }
                if (bluetoothdevmanager.screenhp > bluetoothdevmanager.screenwp) {
                    final int n = (int)bluetoothdevmanager.screenwp;
                    bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                    bluetoothdevmanager.screenhp = (float)n;
                }
                switch (message.what) {
                    case 20019: {
                        if (bluetoothdevmanager.mg == null) {
                            break;
                        }
                        bluetoothdevmanager.mg.hide();
                        bluetoothdevmanager.mg = null;
                        bluetoothdevmanager.mflag = 0;
                        break;
                    }
                    case 20018: {
                        bluetoothdevmanager.myHandler.removeMessages(20019);
                        if (bluetoothdevmanager.mg == null) {
                            bluetoothdevmanager.mg = new greencursor((Context)bluetoothdevmanager.this);
                        }
                        if (System.currentTimeMillis() - bluetoothdevmanager.this.laseTime >= 60L) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("--\u9f20\u6807\u6570\u636e\u95f4\u9694\u65f6\u95f4 = ");
                            sb.append(System.currentTimeMillis() - bluetoothdevmanager.this.laseTime);
                            MyLog.i("my_tag", sb.toString());
                        }
                        bluetoothdevmanager.this.laseTime = System.currentTimeMillis();
                        bluetoothdevmanager.params.x = (int)(bluetoothdevmanager.screenwp * message.arg1 / bluetoothdevmanager.resolutionY);
                        bluetoothdevmanager.params.y = (int)(bluetoothdevmanager.screenhp * message.arg2 / bluetoothdevmanager.resolutionX);
                        bluetoothdevmanager.mg.getWindow().setAttributes(bluetoothdevmanager.params);
                        bluetoothdevmanager.myHandler.sendEmptyMessageDelayed(20019, 500L);
                        break;
                    }
                    case 20017: {
                        if (bluetoothdevmanager.mg == null) {
                            bluetoothdevmanager.mg = new greencursor((Context)bluetoothdevmanager.this);
                        }
                        bluetoothdevmanager.params.x = message.arg1;
                        bluetoothdevmanager.params.y = message.arg2;
                        bluetoothdevmanager.mg.getWindow().setAttributes(bluetoothdevmanager.params);
                        bluetoothdevmanager.mg.show();
                        break;
                    }
                }
            }
        };
    }
    
    private boolean isDataInited(final byte[] array) {
        if (bluetoothdevmanager.devicemode == 0 && MyApplication.getDatapro() == null) {
            return false;
        }
        if (bluetoothdevmanager.devicemode == 0 && MyApplication.getDatapro() != null) {
            final byte[] datapro = MyApplication.getDatapro();
            final StringBuilder sb = new StringBuilder();
            sb.append("data[0]---->");
            sb.append(datapro[0]);
            sb.append("----?=");
            sb.append(array[5]);
            sb.append("----data[1]---->");
            sb.append(datapro[1]);
            sb.append("----?=");
            sb.append(array[6]);
            MyLog.i("my_tag", sb.toString());
            return datapro[0] == array[5] && datapro[1] == array[6];
        }
        if (bluetoothdevmanager.devicemode != 0 && MyApplication.getGpDatapro() == null) {
            return false;
        }
        if (bluetoothdevmanager.devicemode != 0 && MyApplication.getGpDatapro() != null) {
            final byte[] gpDatapro = MyApplication.getGpDatapro();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("data[0]---->");
            sb2.append(gpDatapro[0]);
            sb2.append("----?=");
            sb2.append(array[5]);
            sb2.append("----data[1]---->");
            sb2.append(gpDatapro[1]);
            sb2.append("----?=");
            sb2.append(array[6]);
            MyLog.i("my_tag", sb2.toString());
            return gpDatapro[0] == array[5] && gpDatapro[1] == array[6];
        }
        return true;
    }
    
    public static boolean isScreenOriatationPortrait(final Context context) {
        final int orientation = context.getResources().getConfiguration().orientation;
        boolean b = true;
        if (orientation != 1) {
            b = false;
        }
        return b;
    }
    
    private static void keepAwake(final Context context) {
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        powerManager.newWakeLock(1, context.getResources().getString(R.string.app_name)).acquire();
        powerManager.newWakeLock(805306374, "Screen Deam or screen stays on for a little longer").acquire();
        powerManager.newWakeLock(26, "").acquire();
        ((WifiManager)context.getSystemService("wifi")).createWifiLock(1, "LockTag").acquire();
    }
    
    public static boolean read763Characteristic() {
        if (bluetoothdevmanager.mBluetoothAdapter == null || bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(bluetoothdevmanager.CHARACTER_DEVICEINFO_UUID);
        if (service == null) {
            return false;
        }
        final BluetoothGattCharacteristic characteristic = service.getCharacteristic(bluetoothdevmanager.CHARACTER_763_FIRMWARE_UUID);
        return characteristic != null && bluetoothdevmanager.mBluetoothGatt.readCharacteristic(characteristic);
    }
    
    public static boolean readCharacteristic() {
        if (bluetoothdevmanager.mBluetoothAdapter == null || bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(bluetoothdevmanager.CHARACTER_DEVICEINFO_UUID);
        if (service == null) {
            return false;
        }
        final BluetoothGattCharacteristic characteristic = service.getCharacteristic(bluetoothdevmanager.CHARACTER_FIRMWARE_UUID);
        return characteristic != null && bluetoothdevmanager.mBluetoothGatt.readCharacteristic(characteristic);
    }
    
    public static void setCallbaccmd(final Callback3 callbaccmd) {
        bluetoothdevmanager.callbaccmd = callbaccmd;
    }
    
    public static void setCallbackGun(final Callback5 callbackgun) {
        bluetoothdevmanager.callbackgun = callbackgun;
    }
    
    public static void setCallbackMouse(final Callback1 callbackMouse) {
        bluetoothdevmanager.callbackMouse = callbackMouse;
    }
    
    public static void setCallbackgp(final Callback4 callbackgp) {
        bluetoothdevmanager.callbackgp = callbackgp;
    }
    
    public static void setCallbackm(final Callback2 callbackm) {
        bluetoothdevmanager.callbackm = callbackm;
    }
    
    public static void setCallbackt(final Callback0 callbackt) {
        bluetoothdevmanager.callbackt = callbackt;
    }
    
    public static boolean setCharacteristicNotification(final UUID uuid, final UUID uuid2, final boolean b) {
        final BluetoothAdapter mBluetoothAdapter = bluetoothdevmanager.mBluetoothAdapter;
        final boolean b2 = false;
        if (mBluetoothAdapter == null || bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(uuid);
        if (service == null) {
            return false;
        }
        final BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        if (characteristic == null) {
            return false;
        }
        bluetoothdevmanager.mBluetoothGatt.setCharacteristicNotification(characteristic, b);
        final BluetoothGattDescriptor descriptor = characteristic.getDescriptor(bluetoothdevmanager.CHARACTER_READ1_UUID);
        boolean writeDescriptor = b2;
        if (descriptor != null) {
            final boolean setValue = descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            if (!setValue) {
                return setValue;
            }
            writeDescriptor = bluetoothdevmanager.mBluetoothGatt.writeDescriptor(descriptor);
        }
        return writeDescriptor;
    }
    
    public static int sppwrite(final byte[] array) {
        if (bluetoothdevmanager.moutputstream != null) {
            try {
                bluetoothdevmanager.moutputstream.write(array);
                bluetoothdevmanager.moutputstream.flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
        return 0;
    }
    
    public static void startFetch(final BluetoothDevice p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: ldc_w           "android.bluetooth.BluetoothDevice"
        //     5: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     8: astore_1       
        //     9: goto            15
        //    12: astore_1       
        //    13: aconst_null    
        //    14: astore_1       
        //    15: aload_1        
        //    16: ifnull          45
        //    19: aload_1        
        //    20: ldc_w           "fetchUuidsWithSdp"
        //    23: iconst_0       
        //    24: anewarray       Ljava/lang/Class;
        //    27: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    30: astore_1       
        //    31: aload_1        
        //    32: ifnull          45
        //    35: aload_1        
        //    36: aload_0        
        //    37: iconst_0       
        //    38: anewarray       Ljava/lang/Object;
        //    41: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    44: pop            
        //    45: return         
        //    46: astore_1       
        //    47: aload_2        
        //    48: astore_1       
        //    49: goto            31
        //    52: astore_0       
        //    53: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  2      9      12     15     Ljava/lang/ClassNotFoundException;
        //  19     31     46     52     Ljava/lang/NoSuchMethodException;
        //  35     45     52     56     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void startForegroundService() {
        this.notificationManager = (NotificationManager)this.getSystemService("notification");
        if (Build$VERSION.SDK_INT >= 26) {
            this.notificationManager.createNotificationChannel(new NotificationChannel(this.notificationId, (CharSequence)this.notificationName, 1));
        }
        this.startForeground(1, this.getNotification());
    }
    
    private void starttestA(final byte[] array) {
        if (array != null && array[0] == 21) {
            if (bluetoothdevmanager.devicemode == 0) {
                if (array[4] == 58 && !isScreenOriatationPortrait((Context)this)) {
                    bluetoothdevmanager.servicehandle.removeMessages(20008);
                    ++bluetoothdevmanager.counterf1;
                    if (bluetoothdevmanager.counterf1 == 1) {
                        final Message message = new Message();
                        message.what = 20012;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20008, 60L);
                }
                if (array[4] == 59 && !isScreenOriatationPortrait((Context)this)) {
                    ++bluetoothdevmanager.counterf2;
                    bluetoothdevmanager.servicehandle.removeMessages(20011);
                    if (bluetoothdevmanager.counterf2 == 1) {
                        final Message message2 = new Message();
                        message2.what = 20016;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message2);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20011, 60L);
                }
                if (array[4] == 60) {
                    ++bluetoothdevmanager.counterf3;
                    bluetoothdevmanager.servicehandle.removeMessages(20024);
                    if (bluetoothdevmanager.counterf3 == 1) {
                        final Message message3 = new Message();
                        message3.what = 20023;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message3);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20024, 60L);
                }
                if (array[4] == 61) {
                    ++bluetoothdevmanager.counterf4;
                    bluetoothdevmanager.servicehandle.removeMessages(20025);
                    if (bluetoothdevmanager.counterf4 == 1) {
                        final Message message4 = new Message();
                        message4.what = 20029;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message4);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20025, 60L);
                }
                if (array[4] == 62) {
                    ++bluetoothdevmanager.counterf5;
                    bluetoothdevmanager.servicehandle.removeMessages(20026);
                    if (bluetoothdevmanager.counterf5 == 1) {
                        final Message message5 = new Message();
                        message5.what = 20030;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message5);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20026, 60L);
                }
                if (array[4] == 63) {
                    ++bluetoothdevmanager.counterf6;
                    bluetoothdevmanager.servicehandle.removeMessages(20027);
                    if (bluetoothdevmanager.counterf6 == 1) {
                        final Message message6 = new Message();
                        message6.what = 20031;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message6);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20027, 60L);
                }
                if (array[4] == 64) {
                    ++bluetoothdevmanager.counterf7;
                    bluetoothdevmanager.servicehandle.removeMessages(20028);
                    if (bluetoothdevmanager.counterf7 == 1) {
                        final Message message7 = new Message();
                        message7.what = 20032;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message7);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20028, 60L);
                }
                if (array[4] == 41) {
                    ++bluetoothdevmanager.counterEsc;
                    bluetoothdevmanager.servicehandle.removeMessages(20034);
                    if (bluetoothdevmanager.counterEsc == 1) {
                        final Message message8 = new Message();
                        message8.what = 20033;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message8);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20034, 60L);
                }
            }
            else {
                int n = 7;
                if (bluetoothdevmanager.mapVersion >= 5) {
                    n = bluetoothdevmanager.maxJoystick * 2 + 3;
                }
                if ((array[n] & 0xFF) == 0x8A || (array[n] & 0xFF) == 0x50) {
                    bluetoothdevmanager.servicehandle.removeMessages(20020);
                    ++bluetoothdevmanager.counterStart;
                    if ((array[n] & 0xFF) == 0x8A) {
                        bluetoothdevmanager.deviceScenesMode = 0;
                    }
                    else if ((array[n] & 0xFF) == 0x50) {
                        bluetoothdevmanager.deviceScenesMode = 1;
                    }
                    if (bluetoothdevmanager.counterStart == 1) {
                        final Message message9 = new Message();
                        message9.what = 20012;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message9);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20020, 60L);
                }
                if ((array[n] & 0xFF) == 0x88 | (array[n] & 0xFF) == 0x4E) {
                    bluetoothdevmanager.servicehandle.removeMessages(20021);
                    ++bluetoothdevmanager.counterSelect;
                    if ((array[n] & 0xFF) == 0x88) {
                        bluetoothdevmanager.deviceScenesMode = 0;
                    }
                    else if ((array[n] & 0xFF) == 0x4E) {
                        bluetoothdevmanager.deviceScenesMode = 1;
                    }
                    if (bluetoothdevmanager.counterSelect == 1) {
                        final Message message10 = new Message();
                        message10.what = 20016;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message10);
                        }
                    }
                    bluetoothdevmanager.servicehandle.sendEmptyMessageDelayed(20021, 60L);
                }
            }
        }
    }
    
    public static boolean writeBle(final UUID uuid, final UUID uuid2, final byte[] value) {
        if (bluetoothdevmanager.mBluetoothAdapter == null || bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(uuid);
        if (service == null) {
            return false;
        }
        final BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        if (characteristic == null) {
            return false;
        }
        characteristic.setValue(value);
        characteristic.setWriteType(1);
        return bluetoothdevmanager.mBluetoothGatt.writeCharacteristic(characteristic);
    }
    
    public void CheckconnThread() {
        bluetoothdevmanager.checkconnflag = true;
        if (this.checkconnt != null && this.checkconnt.isAlive()) {
            return;
        }
        (this.checkconnt = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bluetoothdevmanager.checkconnflag) {
                    SystemClock.sleep(500L);
                    final bluetoothdevmanager this$0 = bluetoothdevmanager.this;
                    ++this$0.checkcount;
                    if (bluetoothdevmanager.this.checkcount == 4) {
                        bluetoothdevmanager.this.getconndev();
                        bluetoothdevmanager.this.checkcount = 0;
                    }
                }
            }
        })).start();
    }
    
    public int ScreenOriatation() {
        if (this.getResources().getConfiguration().orientation == 1) {
            return 1;
        }
        return 0;
    }
    
    public void analyseMousedata(final byte[] array) {
        // monitorenter(this)
        if (array[0] != 18) {
            // monitorexit(this)
            return;
        }
        final int n = array[7] << 8 | (array[6] & 0xFF);
        final byte b = array[5];
        final byte b2 = array[4];
        try {
            final int n2 = (int)bluetoothdevmanager.resolutionX - ((b2 & 0xFF) | b << 8);
            if (bluetoothdevmanager.mflag == 0) {
                final Message message = new Message();
                message.what = 20017;
                message.arg1 = n;
                message.arg2 = n2;
                bluetoothdevmanager.myHandler.sendMessage(message);
            }
            ++bluetoothdevmanager.mflag;
            final Message message2 = new Message();
            message2.what = 20018;
            message2.arg1 = n;
            message2.arg2 = n2;
            bluetoothdevmanager.myHandler.sendMessage(message2);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public void bleclose() {
        if (bluetoothdevmanager.mBluetoothGatt == null) {
            return;
        }
        disconnect();
        bluetoothdevmanager.mBluetoothGatt.close();
        bluetoothdevmanager.mBluetoothGatt = null;
        this.CheckconnThread();
    }
    
    public boolean bleconnect(final BluetoothDevice bluetoothDevice) {
        if (bluetoothdevmanager.mBluetoothAdapter == null) {
            return false;
        }
        if (bluetoothDevice == null) {
            return false;
        }
        bluetoothdevmanager.mBluetoothGatt = bluetoothDevice.connectGatt((Context)this, false, this.mGattCallback);
        bluetoothdevmanager.mBluetoothName = bluetoothDevice.getName();
        return bluetoothdevmanager.mBluetoothGatt != null;
    }
    
    int btoi(final byte b) {
        return (b & 0x80) + (b & 0x7F);
    }
    
    public void close() {
        this.sppclose();
        this.bleclose();
        if (bluetoothdevmanager.mNewSetupdialog != null) {
            if (bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                bluetoothdevmanager.mNewSetupdialog.dismiss();
            }
            final newSetupdialog mNewSetupdialog = bluetoothdevmanager.mNewSetupdialog;
            newSetupdialog.isMapInfosSuccess = true;
            bluetoothdevmanager.mNewSetupdialog.cancel();
            bluetoothdevmanager.mNewSetupdialog = null;
        }
        if (bluetoothdevmanager.mNewIndicatordialog != null) {
            if (bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                bluetoothdevmanager.mNewIndicatordialog.dismiss();
            }
            final indicatordialog mNewIndicatordialog = bluetoothdevmanager.mNewIndicatordialog;
            indicatordialog.isMapInfosSuccess = true;
            bluetoothdevmanager.mNewIndicatordialog.cancel();
            bluetoothdevmanager.mNewIndicatordialog = null;
        }
        if (bluetoothdevmanager.mGpsetupdialog != null) {
            if (bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                bluetoothdevmanager.mGpsetupdialog.dismiss();
            }
            final gpsetupdialog mGpsetupdialog = bluetoothdevmanager.mGpsetupdialog;
            gpsetupdialog.isMapInfosSuccess = true;
            bluetoothdevmanager.mGpsetupdialog.cancel();
            bluetoothdevmanager.mGpsetupdialog = null;
        }
        if (bluetoothdevmanager.mGpindicatordialog != null) {
            if (bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                bluetoothdevmanager.mGpindicatordialog.dismiss();
            }
            final gpindicatordialog mGpindicatordialog = bluetoothdevmanager.mGpindicatordialog;
            gpindicatordialog.isMapInfosSuccess = true;
            bluetoothdevmanager.mGpindicatordialog.cancel();
            bluetoothdevmanager.mGpindicatordialog = null;
        }
    }
    
    public boolean connectspp(final BluetoothDevice bluetoothDevice) {
        try {
            bluetoothdevmanager.socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            try {
                bluetoothdevmanager.socket.connect();
                if (bluetoothdevmanager.socket != null) {
                    try {
                        bluetoothdevmanager.minputstream = bluetoothdevmanager.socket.getInputStream();
                        bluetoothdevmanager.moutputstream = bluetoothdevmanager.socket.getOutputStream();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
                try {
                    bluetoothdevmanager.moutputstream.write(this.cmdon);
                    bluetoothdevmanager.moutputstream.flush();
                    bluetoothdevmanager.mConnectionState = 1;
                    this.sppstart();
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
                return true;
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
                return false;
            }
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
            return false;
        }
    }
    
    public List<BluetoothGattService> getSupportedGattServices() {
        if (bluetoothdevmanager.mBluetoothGatt == null) {
            return null;
        }
        return (List<BluetoothGattService>)bluetoothdevmanager.mBluetoothGatt.getServices();
    }
    
    void getconndev() {
        bluetoothdevmanager.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothdevmanager.mBluetoothAdapter == null) {
            final Message message = new Message();
            message.arg1 = 13;
            if (bluetoothdevmanager.servicehandle != null) {
                bluetoothdevmanager.servicehandle.sendMessage(message);
            }
            return;
        }
        if (!bluetoothdevmanager.mBluetoothAdapter.isEnabled()) {
            final Message message2 = new Message();
            message2.arg1 = 13;
            if (bluetoothdevmanager.servicehandle != null) {
                bluetoothdevmanager.servicehandle.sendMessage(message2);
            }
            return;
        }
        bluetoothdevmanager.mBluetoothAdapter.getProfileProxy((Context)this, (BluetoothProfile$ServiceListener)new BluetoothProfile$ServiceListener() {
            public void onServiceConnected(int i, final BluetoothProfile bluetoothProfile) {
                final List connectedDevices = bluetoothProfile.getConnectedDevices();
                bluetoothdevmanager.this.dnum = connectedDevices.size();
                if (bluetoothdevmanager.this.dnum != bluetoothdevmanager.this.dnumold) {
                    if (bluetoothdevmanager.this.dnum > 0) {
                        bluetoothdevmanager.this.blstate = 0;
                        for (final BluetoothDevice bluetoothDevice : connectedDevices) {
                            if (bluetoothdevmanager.mConnectionState != 1) {
                                if (bluetoothDevice.getAddress().contains(":63:25")) {
                                    if (Build$VERSION.SDK_INT < 23) {
                                        bluetoothdevmanager.mdevice = bluetoothDevice;
                                        bluetoothdevmanager.bluetoothtype = 2;
                                        gpprocess.curpro = null;
                                        bluetoothdevmanager.islandor = -1;
                                        bluetoothdevmanager.oldscreen = -2;
                                        if (bluetoothdevmanager.this.connectspp(bluetoothdevmanager.mdevice)) {
                                            bluetoothdevmanager.devicemode = 1;
                                            final Message message = new Message();
                                            message.what = 3;
                                            if (FirstPageActivity.mUiHandler != null) {
                                                FirstPageActivity.mUiHandler.sendMessage(message);
                                            }
                                            final Message message2 = new Message();
                                            message2.what = 4;
                                            if (FirstPageActivity.mUiHandler != null) {
                                                FirstPageActivity.mUiHandler.sendMessage(message2);
                                            }
                                        }
                                        bluetoothdevmanager.this.blstate = 1;
                                        break;
                                    }
                                    bluetoothdevmanager.startFetch(bluetoothDevice);
                                    SystemClock.sleep(1000L);
                                    final ParcelUuid[] uuids = bluetoothDevice.getUuids();
                                    if (uuids == null) {
                                        continue;
                                    }
                                    Message message3;
                                    Message message4;
                                    for (i = 0; i < uuids.length; ++i) {
                                        if (uuids[i].toString().equals("00001101-0000-1000-8000-00805F9B34FB".toLowerCase()) && bluetoothDevice.getAddress().contains(":63:25")) {
                                            bluetoothdevmanager.mdevice = bluetoothDevice;
                                            bluetoothdevmanager.bluetoothtype = 2;
                                            gpprocess.curpro = null;
                                            bluetoothdevmanager.islandor = -1;
                                            bluetoothdevmanager.oldscreen = -2;
                                            if (bluetoothdevmanager.this.connectspp(bluetoothdevmanager.mdevice)) {
                                                bluetoothdevmanager.devicemode = 1;
                                                message3 = new Message();
                                                message3.what = 3;
                                                if (FirstPageActivity.mUiHandler != null) {
                                                    FirstPageActivity.mUiHandler.sendMessage(message3);
                                                }
                                                message4 = new Message();
                                                message4.what = 4;
                                                if (FirstPageActivity.mUiHandler != null) {
                                                    FirstPageActivity.mUiHandler.sendMessage(message4);
                                                }
                                            }
                                            bluetoothdevmanager.this.blstate = 1;
                                            break;
                                        }
                                    }
                                }
                                else {
                                    bluetoothdevmanager.this.bleconnect(bluetoothDevice);
                                }
                            }
                        }
                    }
                    else {
                        bluetoothdevmanager.this.blstate = 0;
                    }
                }
                if (bluetoothdevmanager.this.blstate != bluetoothdevmanager.this.blstateold) {
                    if (bluetoothdevmanager.this.blstate == 1 && bluetoothdevmanager.this.blstateold == 0 && bluetoothdevmanager.mConnectionState != 1) {
                        final Message message5 = new Message();
                        message5.arg1 = 12;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message5);
                        }
                    }
                    if (bluetoothdevmanager.this.blstate == 0 && bluetoothdevmanager.this.blstateold == 1) {
                        final Message message6 = new Message();
                        message6.arg1 = 13;
                        if (bluetoothdevmanager.servicehandle != null) {
                            bluetoothdevmanager.servicehandle.sendMessage(message6);
                        }
                    }
                }
                bluetoothdevmanager.this.blstateold = bluetoothdevmanager.this.blstate;
                bluetoothdevmanager.this.dnumold = bluetoothdevmanager.this.dnum;
            }
            
            public void onServiceDisconnected(final int n) {
            }
        }, 4);
    }
    
    void getrealwh() {
        final WindowManager windowManager = (WindowManager)this.getSystemService("window");
        final Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        if (isScreenOriatationPortrait((Context)this)) {
            bluetoothdevmanager.islandor = 1;
            bluetoothdevmanager.screenwp = (float)point.x;
            bluetoothdevmanager.screenhp = (float)point.y;
        }
        else {
            bluetoothdevmanager.islandor = 0;
            bluetoothdevmanager.screenwp = (float)point.x;
            bluetoothdevmanager.screenhp = (float)point.y;
        }
        if (bluetoothdevmanager.screenhp > bluetoothdevmanager.screenwp) {
            bluetoothdevmanager.resolutionX = bluetoothdevmanager.screenwp;
            bluetoothdevmanager.resolutionY = bluetoothdevmanager.screenhp;
        }
        else {
            bluetoothdevmanager.resolutionX = bluetoothdevmanager.screenhp;
            bluetoothdevmanager.resolutionY = bluetoothdevmanager.screenwp;
        }
    }
    
    public void mainthreadstart() {
        if (this.enumthread != null && this.enumthread.isAlive()) {
            return;
        }
        (this.enumthread = new Thread(new Runnable() {
            @SuppressLint({ "HandlerLeak" })
            @Override
            public void run() {
                Looper.prepare();
                bluetoothdevmanager.threadhandle = new Handler() {
                    public void handleMessage(final Message message) {
                        bluetoothdevmanager.datapro = message.getData().getByteArray("send");
                        if (bluetoothdevmanager.mConnectionState == 1) {
                            if (bluetoothdevmanager.callbackt != null && FirstPageActivity.isTestKeyMode) {
                                bluetoothdevmanager.callbackt.onDataChange(bluetoothdevmanager.datapro);
                            }
                            else {
                                if (bluetoothdevmanager.datapro[0] == 21) {
                                    bluetoothdevmanager.this.starttestA(bluetoothdevmanager.datapro);
                                }
                                if (bluetoothdevmanager.callbackgun != null && bluetoothdevmanager.mGunSetupdialog != null && bluetoothdevmanager.mGunSetupdialog.isShowing() && bluetoothdevmanager.datapro[0] == 22) {
                                    bluetoothdevmanager.callbackgun.onDataChange(bluetoothdevmanager.datapro);
                                }
                                if (bluetoothdevmanager.callbackm != null && bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing() && bluetoothdevmanager.datapro[0] == 17) {
                                    bluetoothdevmanager.callbackm.onDataChange(bluetoothdevmanager.datapro);
                                }
                                if (bluetoothdevmanager.callbackgp != null && bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing() && bluetoothdevmanager.datapro[0] == 17) {
                                    bluetoothdevmanager.callbackgp.onDataChange(bluetoothdevmanager.datapro);
                                }
                                if (bluetoothdevmanager.datapro[0] == 17 && (bluetoothdevmanager.this.checkKey(bluetoothdevmanager.datapro, 134) || bluetoothdevmanager.this.checkKey(bluetoothdevmanager.datapro, 76)) && bluetoothdevmanager.mGpindicatordialog != null && bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("2--------\u6309\u4e0bMODE\u952e------");
                                    sb.append(CommonUtils.byteToString(bluetoothdevmanager.datapro));
                                    MyLog.i("my_tag", sb.toString());
                                    if (bluetoothdevmanager.this.checkKey(bluetoothdevmanager.datapro, 134)) {
                                        bluetoothdevmanager.deviceScenesMode = 0;
                                    }
                                    if (bluetoothdevmanager.this.checkKey(bluetoothdevmanager.datapro, 76)) {
                                        bluetoothdevmanager.deviceScenesMode = 1;
                                    }
                                    bluetoothdevmanager.mGpindicatordialog.dismiss();
                                    bluetoothdevmanager.mGpindicatordialog.cancel();
                                    bluetoothdevmanager.mGpindicatordialog = null;
                                    (bluetoothdevmanager.mGpindicatordialog = new gpindicatordialog((Context)bluetoothdevmanager.this)).show();
                                }
                                if (bluetoothdevmanager.callbaccmd != null && bluetoothdevmanager.datapro[0] == 16) {
                                    bluetoothdevmanager.callbaccmd.onDataChange(bluetoothdevmanager.datapro);
                                }
                                if (bluetoothdevmanager.datapro[2] == 4 && bluetoothdevmanager.servicehandle != null) {
                                    final Message message2 = new Message();
                                    message2.what = 20014;
                                    message2.obj = bluetoothdevmanager.datapro;
                                    bluetoothdevmanager.servicehandle.sendMessage(message2);
                                }
                                if (bluetoothdevmanager.datapro[2] == 9 && bluetoothdevmanager.servicehandle != null) {
                                    final Message message3 = new Message();
                                    message3.what = 20015;
                                    message3.obj = bluetoothdevmanager.datapro;
                                    bluetoothdevmanager.servicehandle.sendMessage(message3);
                                }
                                if (bluetoothdevmanager.datapro[2] == 39 && FirstPageActivity.mUiHandler != null) {
                                    final Message message4 = new Message();
                                    message4.what = 201905;
                                    message4.obj = bluetoothdevmanager.datapro;
                                    FirstPageActivity.mUiHandler.sendMessage(message4);
                                }
                            }
                        }
                    }
                };
                Looper.loop();
            }
        })).start();
    }
    
    public void mainthreadstart2() {
        if (this.enumthreadMouse != null && this.enumthreadMouse.isAlive()) {
            return;
        }
        (this.enumthreadMouse = new Thread(new Runnable() {
            @SuppressLint({ "HandlerLeak" })
            @Override
            public void run() {
                Looper.prepare();
                bluetoothdevmanager.threadhandleMouse = new Handler() {
                    public void handleMessage(final Message message) {
                        bluetoothdevmanager.mousedatapro = message.getData().getByteArray("send");
                        bluetoothdevmanager.this.analyseMousedata(bluetoothdevmanager.mousedatapro);
                    }
                };
                Looper.loop();
            }
        })).start();
    }
    
    @Nullable
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.getrealwh();
        final Message message = new Message();
        message.arg1 = 16;
        if (bluetoothdevmanager.servicehandle != null) {
            bluetoothdevmanager.servicehandle.sendMessage(message);
        }
        super.onConfigurationChanged(configuration);
    }
    
    public void onCreate() {
        if (bluetoothdevmanager.servicehandle == null) {
            bluetoothdevmanager.servicehandle = new Handler(this.getMainLooper()) {
                public void handleMessage(final Message message) {
                    if (message.what == 1000) {
                        new ToastDialog((Context)bluetoothdevmanager.this, (String)message.obj).show();
                    }
                    if (message.what == 20013) {
                        bluetoothdevmanager.saveDataPro = (byte[])message.obj;
                        if (bluetoothdevmanager.saveDataPro == null) {
                            bluetoothdevmanager.saveDataPro = MyApplication.getSaveData();
                        }
                        final byte[] array2;
                        final byte[] array = array2 = new byte[15];
                        array2[0] = 0;
                        array2[2] = (array2[1] = 0);
                        array2[4] = (array2[3] = 0);
                        array2[6] = (array2[5] = 0);
                        array2[8] = (array2[7] = 0);
                        array2[10] = (array2[9] = 0);
                        array2[12] = (array2[11] = 0);
                        array2[14] = (array2[13] = 0);
                        System.arraycopy(bluetoothdevmanager.saveDataPro, 0, array, 0, 15);
                        BlueCmdManager.saveMapDataToDevice((byte)0, (byte)0, array, array.length);
                    }
                    if (message.what == 20014) {
                        bluetoothdevmanager.callbackdDatapro = (byte[])message.obj;
                        bluetoothdevmanager.macroBitNum = bluetoothdevmanager.maxMacro * (bluetoothdevmanager.maxMacroPoint * 4 + 2);
                        if (bluetoothdevmanager.mapVersion >= 5) {
                            bluetoothdevmanager.piaNum = (bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum) / 15;
                            --bluetoothdevmanager.piaNum;
                            final int n = (bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum) % 15;
                            if (n > 0) {
                                ++bluetoothdevmanager.piaNum;
                            }
                            if (bluetoothdevmanager.callbackdDatapro[2] == 4 && bluetoothdevmanager.callbackdDatapro[4] < bluetoothdevmanager.piaNum) {
                                if (bluetoothdevmanager.callbackdDatapro[4] + 1 <= bluetoothdevmanager.piaNum - 1) {
                                    final byte[] array4;
                                    final byte[] array3 = array4 = new byte[15];
                                    array4[0] = 0;
                                    array4[2] = (array4[1] = 0);
                                    array4[4] = (array4[3] = 0);
                                    array4[6] = (array4[5] = 0);
                                    array4[8] = (array4[7] = 0);
                                    array4[10] = (array4[9] = 0);
                                    array4[12] = (array4[11] = 0);
                                    array4[14] = (array4[13] = 0);
                                    System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array3, 0, 15);
                                    BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array3, array3.length);
                                }
                                else {
                                    final byte[] array6;
                                    final byte[] array5 = array6 = new byte[15];
                                    array6[0] = 0;
                                    array6[2] = (array6[1] = 0);
                                    array6[4] = (array6[3] = 0);
                                    array6[6] = (array6[5] = 0);
                                    array6[8] = (array6[7] = 0);
                                    array6[10] = (array6[9] = 0);
                                    array6[12] = (array6[11] = 0);
                                    array6[14] = (array6[13] = 0);
                                    if (n > 0) {
                                        System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array5, 0, n);
                                        BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array5, n);
                                    }
                                    else {
                                        System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array5, 0, 15);
                                        BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array5, array5.length);
                                    }
                                    if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                        final newSetupdialog mNewSetupdialog = bluetoothdevmanager.mNewSetupdialog;
                                        if (newSetupdialog.myhandler != null) {
                                            final newSetupdialog mNewSetupdialog2 = bluetoothdevmanager.mNewSetupdialog;
                                            if (newSetupdialog.isSyncPresetNow) {
                                                final newSetupdialog mNewSetupdialog3 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog4 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler.sendEmptyMessageDelayed(201848, 1000L);
                                            }
                                            else {
                                                final newSetupdialog mNewSetupdialog5 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler2 = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog6 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler2.sendEmptyMessageDelayed(201801, 100L);
                                            }
                                        }
                                    }
                                    if (bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                        final gpsetupdialog mGpsetupdialog = bluetoothdevmanager.mGpsetupdialog;
                                        if (gpsetupdialog.myhandler != null) {
                                            final gpsetupdialog mGpsetupdialog2 = bluetoothdevmanager.mGpsetupdialog;
                                            if (gpsetupdialog.isSyncPresetNow) {
                                                final gpsetupdialog mGpsetupdialog3 = bluetoothdevmanager.mGpsetupdialog;
                                                final Handler myhandler3 = gpsetupdialog.myhandler;
                                                final gpsetupdialog mGpsetupdialog4 = bluetoothdevmanager.mGpsetupdialog;
                                                myhandler3.sendEmptyMessageDelayed(201844, 1000L);
                                            }
                                            else {
                                                final gpsetupdialog mGpsetupdialog5 = bluetoothdevmanager.mGpsetupdialog;
                                                if (gpsetupdialog.isSaveOfficialGamePresetNow) {
                                                    final gpsetupdialog mGpsetupdialog6 = bluetoothdevmanager.mGpsetupdialog;
                                                    final Handler myhandler4 = gpsetupdialog.myhandler;
                                                    final gpsetupdialog mGpsetupdialog7 = bluetoothdevmanager.mGpsetupdialog;
                                                    myhandler4.sendEmptyMessage(201904);
                                                }
                                                else {
                                                    final gpsetupdialog mGpsetupdialog8 = bluetoothdevmanager.mGpsetupdialog;
                                                    final Handler myhandler5 = gpsetupdialog.myhandler;
                                                    final gpsetupdialog mGpsetupdialog9 = bluetoothdevmanager.mGpsetupdialog;
                                                    myhandler5.sendEmptyMessageDelayed(201801, 100L);
                                                }
                                            }
                                        }
                                    }
                                    if (FirstPageActivity.isSyncPresetNow) {
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201848, 1000L);
                                        }
                                        else if (bluetoothdevmanager.devicemode == 2) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201866, 1000L);
                                        }
                                    }
                                    if (FirstPageActivity.isSaveOfficialGamePresetNow) {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201879);
                                    }
                                    if (FirstPageActivity.issaveLocaltionPresetFileToDevice) {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201902);
                                    }
                                    if (bluetoothdevmanager.devicemode == 0) {
                                        MyApplication.setDatapro(bluetoothdevmanager.saveDataPro);
                                    }
                                    else {
                                        MyApplication.setGpDatapro(bluetoothdevmanager.saveDataPro);
                                    }
                                    bluetoothdevmanager.resolutionX = bluetoothdevmanager.screenhp;
                                    bluetoothdevmanager.resolutionY = bluetoothdevmanager.screenwp;
                                }
                            }
                        }
                        else {
                            bluetoothdevmanager.piaNum = (bluetoothdevmanager.macroBitNum + 264) / 15;
                            --bluetoothdevmanager.piaNum;
                            final int n2 = (bluetoothdevmanager.macroBitNum + 264) % 15;
                            if (n2 > 0) {
                                ++bluetoothdevmanager.piaNum;
                            }
                            if (bluetoothdevmanager.callbackdDatapro[2] == 4 && bluetoothdevmanager.callbackdDatapro[4] < bluetoothdevmanager.piaNum) {
                                if (bluetoothdevmanager.callbackdDatapro[4] + 1 <= bluetoothdevmanager.piaNum - 1) {
                                    final byte[] array8;
                                    final byte[] array7 = array8 = new byte[15];
                                    array8[0] = 0;
                                    array8[2] = (array8[1] = 0);
                                    array8[4] = (array8[3] = 0);
                                    array8[6] = (array8[5] = 0);
                                    array8[8] = (array8[7] = 0);
                                    array8[10] = (array8[9] = 0);
                                    array8[12] = (array8[11] = 0);
                                    array8[14] = (array8[13] = 0);
                                    System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array7, 0, 15);
                                    BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array7, array7.length);
                                }
                                else {
                                    final byte[] array10;
                                    final byte[] array9 = array10 = new byte[15];
                                    array10[0] = 0;
                                    array10[2] = (array10[1] = 0);
                                    array10[4] = (array10[3] = 0);
                                    array10[6] = (array10[5] = 0);
                                    array10[8] = (array10[7] = 0);
                                    array10[10] = (array10[9] = 0);
                                    array10[12] = (array10[11] = 0);
                                    array10[14] = (array10[13] = 0);
                                    if (n2 > 0) {
                                        System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array9, 0, n2);
                                        BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array9, n2);
                                    }
                                    else {
                                        System.arraycopy(bluetoothdevmanager.saveDataPro, (bluetoothdevmanager.callbackdDatapro[4] + 1) * 15, array9, 0, 15);
                                        BlueCmdManager.saveMapDataToDevice((byte)0, (byte)(bluetoothdevmanager.callbackdDatapro[4] + 1), array9, array9.length);
                                    }
                                    if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                        final newSetupdialog mNewSetupdialog7 = bluetoothdevmanager.mNewSetupdialog;
                                        if (newSetupdialog.myhandler != null) {
                                            final newSetupdialog mNewSetupdialog8 = bluetoothdevmanager.mNewSetupdialog;
                                            if (newSetupdialog.isSyncPresetNow) {
                                                final newSetupdialog mNewSetupdialog9 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler6 = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog10 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler6.sendEmptyMessageDelayed(201848, 1000L);
                                            }
                                            else {
                                                final newSetupdialog mNewSetupdialog11 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler7 = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog12 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler7.sendEmptyMessageDelayed(201801, 100L);
                                            }
                                        }
                                    }
                                    if (bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                        final gpsetupdialog mGpsetupdialog10 = bluetoothdevmanager.mGpsetupdialog;
                                        if (gpsetupdialog.myhandler != null) {
                                            final gpsetupdialog mGpsetupdialog11 = bluetoothdevmanager.mGpsetupdialog;
                                            if (gpsetupdialog.isSyncPresetNow) {
                                                final gpsetupdialog mGpsetupdialog12 = bluetoothdevmanager.mGpsetupdialog;
                                                final Handler myhandler8 = gpsetupdialog.myhandler;
                                                final gpsetupdialog mGpsetupdialog13 = bluetoothdevmanager.mGpsetupdialog;
                                                myhandler8.sendEmptyMessageDelayed(201844, 1000L);
                                            }
                                            else {
                                                final gpsetupdialog mGpsetupdialog14 = bluetoothdevmanager.mGpsetupdialog;
                                                if (gpsetupdialog.isSaveOfficialGamePresetNow) {
                                                    final gpsetupdialog mGpsetupdialog15 = bluetoothdevmanager.mGpsetupdialog;
                                                    final Handler myhandler9 = gpsetupdialog.myhandler;
                                                    final gpsetupdialog mGpsetupdialog16 = bluetoothdevmanager.mGpsetupdialog;
                                                    myhandler9.sendEmptyMessage(201904);
                                                }
                                                else {
                                                    final gpsetupdialog mGpsetupdialog17 = bluetoothdevmanager.mGpsetupdialog;
                                                    final Handler myhandler10 = gpsetupdialog.myhandler;
                                                    final gpsetupdialog mGpsetupdialog18 = bluetoothdevmanager.mGpsetupdialog;
                                                    myhandler10.sendEmptyMessageDelayed(201801, 100L);
                                                }
                                            }
                                        }
                                    }
                                    if (FirstPageActivity.isSyncPresetNow) {
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201848, 1000L);
                                        }
                                        else if (bluetoothdevmanager.devicemode == 2) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201866, 1000L);
                                        }
                                    }
                                    if (FirstPageActivity.isSaveOfficialGamePresetNow) {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201879);
                                    }
                                    if (FirstPageActivity.issaveLocaltionPresetFileToDevice) {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201902);
                                    }
                                    if (bluetoothdevmanager.devicemode == 0) {
                                        MyApplication.setDatapro(bluetoothdevmanager.saveDataPro);
                                    }
                                    else {
                                        MyApplication.setGpDatapro(bluetoothdevmanager.saveDataPro);
                                    }
                                    bluetoothdevmanager.resolutionX = bluetoothdevmanager.screenhp;
                                    bluetoothdevmanager.resolutionY = bluetoothdevmanager.screenwp;
                                }
                            }
                        }
                    }
                    if (message.what == 20015) {
                        final byte[] array11 = (byte[])message.obj;
                        if (array11[2] == 9) {
                            if (array11[4] == 0 && bluetoothdevmanager.this.isDataInited(array11)) {
                                if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                    final newSetupdialog mNewSetupdialog13 = bluetoothdevmanager.mNewSetupdialog;
                                    final Handler myhandler11 = newSetupdialog.myhandler;
                                    final newSetupdialog mNewSetupdialog14 = bluetoothdevmanager.mNewSetupdialog;
                                    myhandler11.sendEmptyMessage(201804);
                                }
                                else if (bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                    final gpsetupdialog mGpsetupdialog19 = bluetoothdevmanager.mGpsetupdialog;
                                    final Handler myhandler12 = gpsetupdialog.myhandler;
                                    final gpsetupdialog mGpsetupdialog20 = bluetoothdevmanager.mGpsetupdialog;
                                    myhandler12.sendEmptyMessage(201804);
                                }
                                else if (bluetoothdevmanager.mGpindicatordialog != null && bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                    final gpindicatordialog mGpindicatordialog = bluetoothdevmanager.mGpindicatordialog;
                                    final Handler myindicatorhandler = gpindicatordialog.myindicatorhandler;
                                    final gpindicatordialog mGpindicatordialog2 = bluetoothdevmanager.mGpindicatordialog;
                                    myindicatorhandler.sendEmptyMessage(201804);
                                }
                                else if (bluetoothdevmanager.mNewIndicatordialog != null && bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                    final indicatordialog mNewIndicatordialog = bluetoothdevmanager.mNewIndicatordialog;
                                    final Handler myindicatorhandler2 = indicatordialog.myindicatorhandler;
                                    final indicatordialog mNewIndicatordialog2 = bluetoothdevmanager.mNewIndicatordialog;
                                    myindicatorhandler2.sendEmptyMessage(201804);
                                }
                                else if (FirstPageActivity.mUiHandler != null) {
                                    FirstPageActivity.mUiHandler.sendEmptyMessage(16);
                                }
                            }
                            else {
                                bluetoothdevmanager.macroBitNum = bluetoothdevmanager.maxMacro * (bluetoothdevmanager.maxMacroPoint * 4 + 2);
                                if (bluetoothdevmanager.mapVersion >= 5) {
                                    bluetoothdevmanager.piaNum = (bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum) / 15;
                                    --bluetoothdevmanager.piaNum;
                                    if ((bluetoothdevmanager.maxKey * 10 + 10 + bluetoothdevmanager.macroBitNum) % 15 > 0) {
                                        ++bluetoothdevmanager.piaNum;
                                    }
                                    if (array11[4] < bluetoothdevmanager.piaNum) {
                                        bluetoothdevmanager.this.analyseMapDataV5(array11);
                                        BlueCmdManager.sendGetMapCmd(array11[3], (byte)(array11[4] + 1));
                                    }
                                    else {
                                        bluetoothdevmanager.this.analyseMapDataV5(array11);
                                        final byte[] array12 = new byte[2];
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            array12[0] = bluetoothdevmanager.loaddatapro[1];
                                            array12[1] = bluetoothdevmanager.loaddatapro[0];
                                        }
                                        else {
                                            array12[0] = bluetoothdevmanager.loadgpdatapro[1];
                                            array12[1] = bluetoothdevmanager.loadgpdatapro[0];
                                        }
                                        byte[] array13;
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            array13 = new byte[bluetoothdevmanager.loaddatapro.length - 2];
                                        }
                                        else {
                                            array13 = new byte[bluetoothdevmanager.loadgpdatapro.length - 2];
                                        }
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            System.arraycopy(bluetoothdevmanager.loaddatapro, 2, array13, 0, bluetoothdevmanager.loaddatapro.length - 2);
                                        }
                                        else {
                                            System.arraycopy(bluetoothdevmanager.loadgpdatapro, 2, array13, 0, bluetoothdevmanager.loadgpdatapro.length - 2);
                                        }
                                        int n3;
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            n3 = CommonUtils.CRC_GetModbus16(array13, bluetoothdevmanager.loaddatapro.length - 2);
                                        }
                                        else {
                                            n3 = CommonUtils.CRC_GetModbus16(array13, bluetoothdevmanager.loadgpdatapro.length - 2);
                                        }
                                        final int n4 = (array12[1] & 0xFF) | (array12[0] & 0xFF) << 8;
                                        final StringBuilder sb = new StringBuilder();
                                        sb.append("---get checkCode = ");
                                        sb.append(n3);
                                        sb.append(";    standardCode = ");
                                        sb.append(n4);
                                        MyLog.i("crc_tag", sb.toString());
                                        if (n3 == n4) {
                                            if (bluetoothdevmanager.devicemode == 0) {
                                                bluetoothdevmanager.resolutionX = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loaddatapro[5], bluetoothdevmanager.loaddatapro[6]);
                                                bluetoothdevmanager.resolutionY = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loaddatapro[7], bluetoothdevmanager.loaddatapro[8]);
                                            }
                                            else {
                                                bluetoothdevmanager.resolutionX = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loadgpdatapro[5], bluetoothdevmanager.loadgpdatapro[6]);
                                                bluetoothdevmanager.resolutionY = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loadgpdatapro[7], bluetoothdevmanager.loadgpdatapro[8]);
                                            }
                                            if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                                final newSetupdialog mNewSetupdialog15 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler13 = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog16 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler13.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                                final gpsetupdialog mGpsetupdialog21 = bluetoothdevmanager.mGpsetupdialog;
                                                final Handler myhandler14 = gpsetupdialog.myhandler;
                                                final gpsetupdialog mGpsetupdialog22 = bluetoothdevmanager.mGpsetupdialog;
                                                myhandler14.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mGpindicatordialog != null && bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                                final gpindicatordialog mGpindicatordialog3 = bluetoothdevmanager.mGpindicatordialog;
                                                final Handler myindicatorhandler3 = gpindicatordialog.myindicatorhandler;
                                                final gpindicatordialog mGpindicatordialog4 = bluetoothdevmanager.mGpindicatordialog;
                                                myindicatorhandler3.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mNewIndicatordialog != null && bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                                final indicatordialog mNewIndicatordialog3 = bluetoothdevmanager.mNewIndicatordialog;
                                                final Handler myindicatorhandler4 = indicatordialog.myindicatorhandler;
                                                final indicatordialog mNewIndicatordialog4 = bluetoothdevmanager.mNewIndicatordialog;
                                                myindicatorhandler4.sendEmptyMessage(201804);
                                            }
                                            else if (FirstPageActivity.mUiHandler != null) {
                                                FirstPageActivity.mUiHandler.sendEmptyMessage(16);
                                            }
                                            if (bluetoothdevmanager.devicemode == 0) {
                                                MyApplication.setDatapro(bluetoothdevmanager.loaddatapro);
                                            }
                                            else {
                                                MyApplication.setGpDatapro(bluetoothdevmanager.loadgpdatapro);
                                            }
                                        }
                                    }
                                }
                                else {
                                    final StringBuilder sb2 = new StringBuilder();
                                    sb2.append("---\u52a0\u8f7d\u9884\u8bbe data[1] =  ");
                                    sb2.append(array11[1]);
                                    sb2.append(";    data[4] = ");
                                    sb2.append(array11[4]);
                                    MyLog.i("sync_tag", sb2.toString());
                                    bluetoothdevmanager.piaNum = (bluetoothdevmanager.macroBitNum + 264) / 15;
                                    --bluetoothdevmanager.piaNum;
                                    if ((bluetoothdevmanager.macroBitNum + 264) % 15 > 0) {
                                        ++bluetoothdevmanager.piaNum;
                                    }
                                    final StringBuilder sb3 = new StringBuilder();
                                    sb3.append("---\u52a0\u8f7d\u9884\u8bbe piaNum =  ");
                                    sb3.append(bluetoothdevmanager.piaNum);
                                    sb3.append(";    macroBitNum = ");
                                    sb3.append(bluetoothdevmanager.macroBitNum);
                                    MyLog.i("sync_tag", sb3.toString());
                                    if (array11[4] < bluetoothdevmanager.piaNum) {
                                        bluetoothdevmanager.this.analyseMapData(array11);
                                        BlueCmdManager.sendGetMapCmd(array11[3], (byte)(array11[4] + 1));
                                    }
                                    else {
                                        bluetoothdevmanager.this.analyseMapData(array11);
                                        final byte[] array14 = new byte[2];
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            array14[0] = bluetoothdevmanager.loaddatapro[1];
                                            array14[1] = bluetoothdevmanager.loaddatapro[0];
                                        }
                                        else {
                                            array14[0] = bluetoothdevmanager.loadgpdatapro[1];
                                            array14[1] = bluetoothdevmanager.loadgpdatapro[0];
                                        }
                                        byte[] array15;
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            array15 = new byte[bluetoothdevmanager.loaddatapro.length - 2];
                                        }
                                        else {
                                            array15 = new byte[bluetoothdevmanager.loadgpdatapro.length - 2];
                                        }
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            System.arraycopy(bluetoothdevmanager.loaddatapro, 2, array15, 0, bluetoothdevmanager.loaddatapro.length - 2);
                                        }
                                        else {
                                            System.arraycopy(bluetoothdevmanager.loadgpdatapro, 2, array15, 0, bluetoothdevmanager.loadgpdatapro.length - 2);
                                        }
                                        int n5;
                                        if (bluetoothdevmanager.devicemode == 0) {
                                            n5 = CommonUtils.CRC_GetModbus16(array15, bluetoothdevmanager.loaddatapro.length - 2);
                                        }
                                        else {
                                            n5 = CommonUtils.CRC_GetModbus16(array15, bluetoothdevmanager.loadgpdatapro.length - 2);
                                        }
                                        final int n6 = (array14[1] & 0xFF) | (array14[0] & 0xFF) << 8;
                                        final StringBuilder sb4 = new StringBuilder();
                                        sb4.append("---get checkCode = ");
                                        sb4.append(n5);
                                        sb4.append(";    standardCode = ");
                                        sb4.append(n6);
                                        MyLog.i("crc_tag", sb4.toString());
                                        if (n5 == n6) {
                                            if (bluetoothdevmanager.devicemode == 0) {
                                                bluetoothdevmanager.resolutionX = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loaddatapro[5], bluetoothdevmanager.loaddatapro[6]);
                                                bluetoothdevmanager.resolutionY = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loaddatapro[7], bluetoothdevmanager.loaddatapro[8]);
                                            }
                                            else {
                                                bluetoothdevmanager.resolutionX = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loadgpdatapro[5], bluetoothdevmanager.loadgpdatapro[6]);
                                                bluetoothdevmanager.resolutionY = (float)bluetoothdevmanager.this.getIntegerByBit(bluetoothdevmanager.loadgpdatapro[7], bluetoothdevmanager.loadgpdatapro[8]);
                                            }
                                            if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                                final newSetupdialog mNewSetupdialog17 = bluetoothdevmanager.mNewSetupdialog;
                                                final Handler myhandler15 = newSetupdialog.myhandler;
                                                final newSetupdialog mNewSetupdialog18 = bluetoothdevmanager.mNewSetupdialog;
                                                myhandler15.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mGpsetupdialog != null && bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                                final gpsetupdialog mGpsetupdialog23 = bluetoothdevmanager.mGpsetupdialog;
                                                final Handler myhandler16 = gpsetupdialog.myhandler;
                                                final gpsetupdialog mGpsetupdialog24 = bluetoothdevmanager.mGpsetupdialog;
                                                myhandler16.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mGpindicatordialog != null && bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                                final gpindicatordialog mGpindicatordialog5 = bluetoothdevmanager.mGpindicatordialog;
                                                final Handler myindicatorhandler5 = gpindicatordialog.myindicatorhandler;
                                                final gpindicatordialog mGpindicatordialog6 = bluetoothdevmanager.mGpindicatordialog;
                                                myindicatorhandler5.sendEmptyMessage(201804);
                                            }
                                            else if (bluetoothdevmanager.mNewIndicatordialog != null && bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                                final indicatordialog mNewIndicatordialog5 = bluetoothdevmanager.mNewIndicatordialog;
                                                final Handler myindicatorhandler6 = indicatordialog.myindicatorhandler;
                                                final indicatordialog mNewIndicatordialog6 = bluetoothdevmanager.mNewIndicatordialog;
                                                myindicatorhandler6.sendEmptyMessage(201804);
                                            }
                                            else if (FirstPageActivity.mUiHandler != null) {
                                                FirstPageActivity.mUiHandler.sendEmptyMessage(16);
                                            }
                                            if (bluetoothdevmanager.devicemode == 0) {
                                                MyApplication.setDatapro(bluetoothdevmanager.loaddatapro);
                                            }
                                            else {
                                                MyApplication.setGpDatapro(bluetoothdevmanager.loadgpdatapro);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (message.what == 20037) {
                        if (bluetoothdevmanager.mConnectionState != 1) {
                            return;
                        }
                        bluetoothdevmanager.isFormPresetList = false;
                        bluetoothdevmanager.currentGameName = String.valueOf(message.obj);
                        if (bluetoothdevmanager.devicemode == 0) {
                            if (bluetoothdevmanager.mNewIndicatordialog != null) {
                                final indicatordialog mNewIndicatordialog7 = bluetoothdevmanager.mNewIndicatordialog;
                                if (indicatordialog.isMapInfosSuccess) {
                                    if (bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                        bluetoothdevmanager.mNewIndicatordialog.dismiss();
                                    }
                                    bluetoothdevmanager.mNewIndicatordialog.cancel();
                                    bluetoothdevmanager.mNewIndicatordialog = null;
                                    bluetoothdevmanager.this.lastClickTime = 0L;
                                }
                            }
                            if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                                (bluetoothdevmanager.mNewIndicatordialog = new indicatordialog((Context)bluetoothdevmanager.this)).show();
                            }
                        }
                        else {
                            if (bluetoothdevmanager.mGpindicatordialog != null) {
                                final gpindicatordialog mGpindicatordialog7 = bluetoothdevmanager.mGpindicatordialog;
                                if (gpindicatordialog.isMapInfosSuccess) {
                                    if (bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                        bluetoothdevmanager.mGpindicatordialog.dismiss();
                                    }
                                    bluetoothdevmanager.mGpindicatordialog.cancel();
                                    bluetoothdevmanager.mGpindicatordialog = null;
                                    bluetoothdevmanager.this.lastClickTime = 0L;
                                }
                            }
                            if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) || (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) && bluetoothdevmanager.mapVersion >= 7)) {
                                (bluetoothdevmanager.mGpindicatordialog = new gpindicatordialog((Context)bluetoothdevmanager.this)).show();
                            }
                        }
                    }
                    Label_3701: {
                        if (message.what == 20022) {
                            if (bluetoothdevmanager.mConnectionState != 1) {
                                return;
                            }
                            if (message.arg1 == 666) {
                                bluetoothdevmanager.isFormPresetList = true;
                                bluetoothdevmanager.mOfficialGamePreset = (OfficialGamePreset)message.obj;
                                bluetoothdevmanager.currentGameName = bluetoothdevmanager.mOfficialGamePreset.getAppName();
                            }
                            else {
                                bluetoothdevmanager.isFormPresetList = false;
                            }
                            if (bluetoothdevmanager.devicemode == 0) {
                                if (bluetoothdevmanager.mNewSetupdialog != null) {
                                    final newSetupdialog mNewSetupdialog19 = bluetoothdevmanager.mNewSetupdialog;
                                    if (newSetupdialog.isMapInfosSuccess) {
                                        final newSetupdialog mNewSetupdialog20 = bluetoothdevmanager.mNewSetupdialog;
                                        newSetupdialog.isTest = false;
                                        BlueCmdManager.setDeviceWorkMode((byte)0);
                                        if (bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                            bluetoothdevmanager.mNewSetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewSetupdialog.cancel();
                                        bluetoothdevmanager.mNewSetupdialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_3701;
                                    }
                                }
                                if (bluetoothdevmanager.mNewSetupdialog == null) {
                                    if (bluetoothdevmanager.mNewIndicatordialog != null) {
                                        if (bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                            bluetoothdevmanager.mNewIndicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewIndicatordialog.cancel();
                                        bluetoothdevmanager.mNewIndicatordialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                                        final newSetupdialog mNewSetupdialog21 = bluetoothdevmanager.mNewSetupdialog;
                                        newSetupdialog.isTest = true;
                                        (bluetoothdevmanager.mNewSetupdialog = new newSetupdialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                            else {
                                if (bluetoothdevmanager.mGpsetupdialog != null) {
                                    final gpsetupdialog mGpsetupdialog25 = bluetoothdevmanager.mGpsetupdialog;
                                    if (gpsetupdialog.isMapInfosSuccess) {
                                        final gpsetupdialog mGpsetupdialog26 = bluetoothdevmanager.mGpsetupdialog;
                                        gpsetupdialog.isTest = false;
                                        BlueCmdManager.setDeviceWorkMode((byte)0);
                                        if (bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                            bluetoothdevmanager.mGpsetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpsetupdialog.cancel();
                                        bluetoothdevmanager.mGpsetupdialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_3701;
                                    }
                                }
                                if (bluetoothdevmanager.mGpsetupdialog == null) {
                                    if (bluetoothdevmanager.mGpindicatordialog != null) {
                                        if (bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                            bluetoothdevmanager.mGpindicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpindicatordialog.cancel();
                                        bluetoothdevmanager.mGpindicatordialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) || (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) && bluetoothdevmanager.mapVersion >= 7)) {
                                        final gpsetupdialog mGpsetupdialog27 = bluetoothdevmanager.mGpsetupdialog;
                                        gpsetupdialog.isTest = true;
                                        (bluetoothdevmanager.mGpsetupdialog = new gpsetupdialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                        }
                    }
                    Label_4056: {
                        if (message.what == 20012) {
                            bluetoothdevmanager.isFormPresetList = false;
                            final StringBuilder sb5 = new StringBuilder();
                            sb5.append("--- devicemode = ");
                            sb5.append(bluetoothdevmanager.devicemode);
                            MyLog.i("my_tag", sb5.toString());
                            if (bluetoothdevmanager.devicemode == 0) {
                                if (bluetoothdevmanager.mNewSetupdialog != null) {
                                    final newSetupdialog mNewSetupdialog22 = bluetoothdevmanager.mNewSetupdialog;
                                    if (newSetupdialog.isMapInfosSuccess) {
                                        final newSetupdialog mNewSetupdialog23 = bluetoothdevmanager.mNewSetupdialog;
                                        newSetupdialog.isTest = false;
                                        BlueCmdManager.setDeviceWorkMode((byte)0);
                                        if (bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                            bluetoothdevmanager.mNewSetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewSetupdialog.cancel();
                                        bluetoothdevmanager.mNewSetupdialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_4056;
                                    }
                                }
                                if (bluetoothdevmanager.mNewSetupdialog == null) {
                                    if (bluetoothdevmanager.mNewIndicatordialog != null) {
                                        if (bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                            bluetoothdevmanager.mNewIndicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewIndicatordialog.cancel();
                                        bluetoothdevmanager.mNewIndicatordialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                                        final newSetupdialog mNewSetupdialog24 = bluetoothdevmanager.mNewSetupdialog;
                                        newSetupdialog.isTest = true;
                                        (bluetoothdevmanager.mNewSetupdialog = new newSetupdialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                            else {
                                if (bluetoothdevmanager.mGpsetupdialog != null) {
                                    final gpsetupdialog mGpsetupdialog28 = bluetoothdevmanager.mGpsetupdialog;
                                    if (gpsetupdialog.isMapInfosSuccess) {
                                        final gpsetupdialog mGpsetupdialog29 = bluetoothdevmanager.mGpsetupdialog;
                                        gpsetupdialog.isTest = false;
                                        BlueCmdManager.setDeviceWorkMode((byte)0);
                                        if (bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                            bluetoothdevmanager.mGpsetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpsetupdialog.cancel();
                                        bluetoothdevmanager.mGpsetupdialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_4056;
                                    }
                                }
                                if (bluetoothdevmanager.mGpsetupdialog == null) {
                                    if (bluetoothdevmanager.mGpindicatordialog != null) {
                                        if (bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                            bluetoothdevmanager.mGpindicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpindicatordialog.cancel();
                                        bluetoothdevmanager.mGpindicatordialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) || (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) && bluetoothdevmanager.mapVersion >= 7)) {
                                        final gpsetupdialog mGpsetupdialog30 = bluetoothdevmanager.mGpsetupdialog;
                                        gpsetupdialog.isTest = true;
                                        (bluetoothdevmanager.mGpsetupdialog = new gpsetupdialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                        }
                    }
                    Label_4361: {
                        if (message.what == 20016) {
                            bluetoothdevmanager.isFormPresetList = false;
                            if (bluetoothdevmanager.devicemode == 0) {
                                if (bluetoothdevmanager.mNewIndicatordialog != null) {
                                    final indicatordialog mNewIndicatordialog8 = bluetoothdevmanager.mNewIndicatordialog;
                                    if (indicatordialog.isMapInfosSuccess) {
                                        if (bluetoothdevmanager.mNewIndicatordialog.isShowing()) {
                                            bluetoothdevmanager.mNewIndicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewIndicatordialog.cancel();
                                        bluetoothdevmanager.mNewIndicatordialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_4361;
                                    }
                                }
                                if (bluetoothdevmanager.mNewIndicatordialog == null) {
                                    if (bluetoothdevmanager.mNewSetupdialog != null) {
                                        if (bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                            final newSetupdialog mNewSetupdialog25 = bluetoothdevmanager.mNewSetupdialog;
                                            newSetupdialog.isTest = false;
                                            BlueCmdManager.setDeviceWorkMode((byte)0);
                                            bluetoothdevmanager.mNewSetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mNewSetupdialog.cancel();
                                        bluetoothdevmanager.mNewSetupdialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                                        (bluetoothdevmanager.mNewIndicatordialog = new indicatordialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                            else {
                                if (bluetoothdevmanager.mGpindicatordialog != null) {
                                    final gpindicatordialog mGpindicatordialog8 = bluetoothdevmanager.mGpindicatordialog;
                                    if (gpindicatordialog.isMapInfosSuccess) {
                                        if (bluetoothdevmanager.mGpindicatordialog.isShowing()) {
                                            bluetoothdevmanager.mGpindicatordialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpindicatordialog.cancel();
                                        bluetoothdevmanager.mGpindicatordialog = null;
                                        bluetoothdevmanager.this.lastClickTime = 0L;
                                        break Label_4361;
                                    }
                                }
                                if (bluetoothdevmanager.mGpindicatordialog == null) {
                                    if (bluetoothdevmanager.mGpsetupdialog != null) {
                                        if (bluetoothdevmanager.mGpsetupdialog.isShowing()) {
                                            final gpsetupdialog mGpsetupdialog31 = bluetoothdevmanager.mGpsetupdialog;
                                            gpsetupdialog.isTest = false;
                                            BlueCmdManager.setDeviceWorkMode((byte)0);
                                            bluetoothdevmanager.mGpsetupdialog.dismiss();
                                        }
                                        bluetoothdevmanager.mGpsetupdialog.cancel();
                                        bluetoothdevmanager.mGpsetupdialog = null;
                                    }
                                    if (!bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) || (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this) && bluetoothdevmanager.mapVersion >= 7)) {
                                        (bluetoothdevmanager.mGpindicatordialog = new gpindicatordialog((Context)bluetoothdevmanager.this)).show();
                                    }
                                }
                            }
                        }
                    }
                    if (message.what == 20023) {
                        if (bluetoothdevmanager.mGunSetupdialog != null) {
                            if (bluetoothdevmanager.mGunSetupdialog.isShowing()) {
                                bluetoothdevmanager.mGunSetupdialog.dismiss();
                                bluetoothdevmanager.mGunSetupdialog.cancel();
                                bluetoothdevmanager.mGunSetupdialog = null;
                            }
                            else {
                                bluetoothdevmanager.mGunSetupdialog.show();
                            }
                        }
                        else {
                            (bluetoothdevmanager.mGunSetupdialog = new GunSetupdialog((Context)bluetoothdevmanager.this)).show();
                        }
                    }
                    if (message.what == 20029) {
                        new ToastDialog((Context)bluetoothdevmanager.this, bluetoothdevmanager.this.getResources().getString(R.string.bluetoothdevmanager_tip1)).show();
                    }
                    if (message.what == 20030) {
                        new ToastDialog((Context)bluetoothdevmanager.this, bluetoothdevmanager.this.getResources().getString(R.string.bluetoothdevmanager_tip2)).show();
                    }
                    if (message.what == 20031) {
                        new ToastDialog((Context)bluetoothdevmanager.this, bluetoothdevmanager.this.getResources().getString(R.string.bluetoothdevmanager_tip3)).show();
                    }
                    if (message.what == 20032) {
                        new ToastDialog((Context)bluetoothdevmanager.this, bluetoothdevmanager.this.getResources().getString(R.string.bluetoothdevmanager_tip4)).show();
                    }
                    if (message.what == 20033) {
                        new ToastDialog((Context)bluetoothdevmanager.this, bluetoothdevmanager.this.getResources().getString(R.string.bluetoothdevmanager_tip5)).show();
                    }
                    if (bluetoothdevmanager.isScreenOriatationPortrait((Context)bluetoothdevmanager.this)) {
                        bluetoothdevmanager.islandor = 1;
                    }
                    else {
                        bluetoothdevmanager.islandor = 0;
                    }
                    if (message.arg1 == 6 && bluetoothdevmanager.mBluetoothGatt != null) {
                        bluetoothdevmanager.mBluetoothGatt.discoverServices();
                    }
                    if (message.arg1 == 12) {
                        bluetoothdevmanager.this.mainthreadstart();
                        bluetoothdevmanager.this.CheckconnThread();
                        final Message message2 = new Message();
                        message2.what = 3;
                        if (FirstPageActivity.mUiHandler != null) {
                            FirstPageActivity.mUiHandler.sendMessage(message2);
                        }
                    }
                    if (message.arg1 == 13) {
                        bluetoothdevmanager.mConnectionState = 0;
                        if (bluetoothdevmanager.devicemode == 0) {
                            System.arraycopy(bluetoothdevmanager.this.dataprold, 0, bluetoothdevmanager.datapro, 0, 17);
                            System.arraycopy(bluetoothdevmanager.this.dataprold, 0, bluetoothdevmanager.this.datapro1, 0, 17);
                        }
                        else {
                            System.arraycopy(bluetoothdevmanager.this.datapro1gp, 0, bluetoothdevmanager.datapro, 0, 17);
                            System.arraycopy(bluetoothdevmanager.this.datapro2gp, 0, bluetoothdevmanager.this.datapro1, 0, 17);
                        }
                        final Message message3 = new Message();
                        message3.what = 0;
                        if (FirstPageActivity.mUiHandler != null) {
                            FirstPageActivity.mUiHandler.sendMessage(message3);
                        }
                    }
                    if (message.what == 20008) {
                        bluetoothdevmanager.counterf1 = 0;
                    }
                    if (message.what == 20011) {
                        bluetoothdevmanager.counterf2 = 0;
                    }
                    if (message.what == 20024) {
                        bluetoothdevmanager.counterf3 = 0;
                    }
                    if (message.what == 20025) {
                        bluetoothdevmanager.counterf4 = 0;
                    }
                    if (message.what == 20026) {
                        bluetoothdevmanager.counterf5 = 0;
                    }
                    if (message.what == 20027) {
                        bluetoothdevmanager.counterf6 = 0;
                    }
                    if (message.what == 20028) {
                        bluetoothdevmanager.counterf7 = 0;
                    }
                    if (message.what == 20034) {
                        bluetoothdevmanager.counterEsc = 0;
                    }
                    if (message.what == 20020) {
                        bluetoothdevmanager.counterStart = 0;
                    }
                    if (message.what == 20021) {
                        bluetoothdevmanager.counterSelect = 0;
                    }
                    super.handleMessage(message);
                }
            };
        }
        this.mainthreadstart();
        this.mainthreadstart2();
        this.initMouse();
        if (bluetoothdevmanager.mConnectionState != 1) {
            this.CheckconnThread();
        }
        this.getrealwh();
        this.startForegroundService();
        (bluetoothdevmanager.instance = this).onCreate();
    }
    
    public void onDestroy() {
        this.stopForeground(true);
        this.sendBroadcast(new Intent("com.qx.qgbox.service.bluetoothdevmanager.destroy"));
        super.onDestroy();
    }
    
    @SuppressLint({ "WrongConstant" })
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        super.onStartCommand(intent, n, n2);
        return 1;
    }
    
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }
    
    public byte[] readBle(final UUID uuid, final UUID uuid2) {
        if (bluetoothdevmanager.mBluetoothAdapter == null || bluetoothdevmanager.mBluetoothGatt == null) {
            return null;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(uuid);
        if (service == null) {
            return null;
        }
        final BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        if (characteristic == null) {
            return null;
        }
        bluetoothdevmanager.mBluetoothGatt.readCharacteristic(characteristic);
        final byte[] value = characteristic.getValue();
        if (value != null) {
            return value;
        }
        return null;
    }
    
    public boolean setCharacteristicNotification763(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final boolean b) {
        if (bluetoothdevmanager.mBluetoothAdapter != null && bluetoothdevmanager.mBluetoothGatt != null) {
            Log.e("bluetoothdevmanager", bluetoothGattCharacteristic.getUuid().toString());
            bluetoothdevmanager.mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, b);
            final BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
            boolean b2;
            if (b) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                b2 = bluetoothdevmanager.mBluetoothGatt.writeDescriptor(descriptor);
            }
            else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                b2 = bluetoothdevmanager.mBluetoothGatt.writeDescriptor(descriptor);
            }
            return b2;
        }
        Log.w("bluetoothdevmanager", "BluetoothAdapter not initialized");
        return false;
    }
    
    public void sppclose() {
        if (bluetoothdevmanager.moutputstream != null) {
            try {
                bluetoothdevmanager.moutputstream.close();
                bluetoothdevmanager.moutputstream = null;
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (bluetoothdevmanager.minputstream != null) {
            try {
                bluetoothdevmanager.minputstream.close();
                bluetoothdevmanager.minputstream = null;
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        if (bluetoothdevmanager.socket != null) {
            try {
                bluetoothdevmanager.socket.close();
                bluetoothdevmanager.socket = null;
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    public int sppread(final byte[] array) {
        if (bluetoothdevmanager.minputstream != null) {
            try {
                return bluetoothdevmanager.minputstream.read(array);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
    
    public void sppstart() {
        bluetoothdevmanager.isspp = true;
        if (this.msppThread != null && this.msppThread.isAlive()) {
            return;
        }
        (this.msppThread = new Thread(new Runnable() {
            int count = -1;
            
            @Override
            public void run() {
                while (bluetoothdevmanager.isspp) {
                    SystemClock.sleep(16L);
                    bluetoothdevmanager.this.byte4 = 0;
                    if (bluetoothdevmanager.mConnectionState == 1 && bluetoothdevmanager.minputstream != null) {
                        try {
                            this.count = bluetoothdevmanager.minputstream.available();
                            if (this.count >= 23) {
                                final byte[] array = new byte[this.count];
                                bluetoothdevmanager.minputstream.read(array, 0, this.count);
                                if (array[7] != 0) {
                                    final bluetoothdevmanager this$0 = bluetoothdevmanager.this;
                                    this$0.byte4 |= 0x4;
                                }
                                if (array[8] != 0) {
                                    final bluetoothdevmanager this$2 = bluetoothdevmanager.this;
                                    this$2.byte4 |= 0x8;
                                }
                                if (array[9] != 0) {
                                    final bluetoothdevmanager this$3 = bluetoothdevmanager.this;
                                    this$3.byte4 |= 0x10;
                                }
                                if (array[10] != 0) {
                                    final bluetoothdevmanager this$4 = bluetoothdevmanager.this;
                                    this$4.byte4 |= 0x20;
                                }
                                if (array[11] != 0) {
                                    final bluetoothdevmanager this$5 = bluetoothdevmanager.this;
                                    this$5.byte4 |= 0x40;
                                }
                                if (array[12] != 0) {
                                    final bluetoothdevmanager this$6 = bluetoothdevmanager.this;
                                    this$6.byte4 |= (byte)128;
                                }
                                bluetoothdevmanager.this.datapro1[2] = array[6];
                                bluetoothdevmanager.this.datapro1[3] = array[5];
                                bluetoothdevmanager.this.datapro1[4] = bluetoothdevmanager.this.byte4;
                                bluetoothdevmanager.this.datapro1[5] = array[13];
                                bluetoothdevmanager.this.datapro1[6] = array[14];
                                bluetoothdevmanager.this.datapro1[7] = array[15];
                                bluetoothdevmanager.this.datapro1[9] = array[17];
                                bluetoothdevmanager.this.datapro1[11] = array[19];
                                bluetoothdevmanager.this.datapro1[13] = array[21];
                            }
                            System.arraycopy(bluetoothdevmanager.this.datapro1, 0, bluetoothdevmanager.this.datapro2, 0, 15);
                            if (bluetoothdevmanager.threadhandle == null) {
                                continue;
                            }
                            final Bundle data = new Bundle();
                            data.putByteArray("send", bluetoothdevmanager.this.datapro2);
                            final Message message = new Message();
                            message.setData(data);
                            bluetoothdevmanager.threadhandle.sendMessage(message);
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                            System.arraycopy(bluetoothdevmanager.this.dataprold, 0, bluetoothdevmanager.this.datapro2, 0, 15);
                            System.arraycopy(bluetoothdevmanager.this.dataprold, 0, bluetoothdevmanager.this.datapro1, 0, 15);
                        }
                    }
                }
            }
        })).start();
    }
    
    public boolean writeOTABlock(final byte[] value) {
        if (bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(bluetoothdevmanager.UUID_OTA_SERVICE);
        if (service == null) {
            MyLog.i("bluetoothdevmanager", "get service fail");
            return false;
        }
        this.mBluetoothGattCharateristic = service.getCharacteristic(bluetoothdevmanager.UUID_BLOCK);
        if (this.mBluetoothGattCharateristic == null) {
            MyLog.i("bluetoothdevmanager", "mBluetoothGattCharateristic is null");
            return false;
        }
        this.mBluetoothGattCharateristic.setWriteType(1);
        this.mBluetoothGattCharateristic.setValue(value);
        return bluetoothdevmanager.mBluetoothGatt.writeCharacteristic(this.mBluetoothGattCharateristic);
    }
    
    public boolean writeOTAIdentfy(final byte[] value) {
        if (bluetoothdevmanager.mBluetoothGatt == null) {
            return false;
        }
        final BluetoothGattService service = bluetoothdevmanager.mBluetoothGatt.getService(bluetoothdevmanager.UUID_OTA_SERVICE);
        if (service == null) {
            Log.e("bluetoothdevmanager", "get service fail");
            return false;
        }
        this.mBluetoothGattCharateristic = service.getCharacteristic(bluetoothdevmanager.UUID_IDENTFY);
        if (this.mBluetoothGattCharateristic == null) {
            Log.e("bluetoothdevmanager", "mBluetoothGattCharateristic is null");
            return false;
        }
        this.mBluetoothGattCharateristic.setWriteType(1);
        this.mBluetoothGattCharateristic.setValue(value);
        if (!bluetoothdevmanager.mBluetoothGatt.writeCharacteristic(this.mBluetoothGattCharateristic)) {
            Log.e("bluetoothdevmanager", "false write");
            return false;
        }
        return true;
    }
    
    public interface Callback0
    {
        void onDataChange(final byte[] p0);
    }
    
    public interface Callback1
    {
        void onDataChange(final byte[] p0);
    }
    
    public interface Callback2
    {
        void onDataChange(final byte[] p0);
    }
    
    public interface Callback3
    {
        void onDataChange(final byte[] p0);
    }
    
    public interface Callback4
    {
        void onDataChange(final byte[] p0);
    }
    
    public interface Callback5
    {
        void onDataChange(final byte[] p0);
    }
    
    public class DataSaver
    {
        public String flag;
        public String joystick;
        public String ms;
        public String name;
        public int property;
        public String rumble;
        public String whichmoto;
        public int x;
        public int y;
    }
    
    public class pollThread implements Runnable
    {
        int res1;
        
        public pollThread() {
            this.res1 = 0;
        }
        
        @Override
        public void run() {
            SystemClock.sleep(200L);
            bluetoothdevmanager.readCharacteristic();
            SystemClock.sleep(500L);
            bluetoothdevmanager.read763Characteristic();
            SystemClock.sleep(500L);
            if (bluetoothdevmanager.devicemode != 1 && bluetoothdevmanager.devicemode != 2) {
                bluetoothdevmanager.setCharacteristicNotification(bluetoothdevmanager.SERVIE_READ_UUID_STP, bluetoothdevmanager.CHARACTER_READ_UUID_STP, true);
            }
            else {
                bluetoothdevmanager.setCharacteristicNotification(bluetoothdevmanager.SERVIE_READ_UUID_GPP, bluetoothdevmanager.CHARACTER_READ_UUID_GPP, true);
            }
            SystemClock.sleep(200L);
        }
    }
}
