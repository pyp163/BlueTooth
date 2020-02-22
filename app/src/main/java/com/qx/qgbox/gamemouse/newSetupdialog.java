package com.qx.qgbox.gamemouse;

import android.app.*;

import com.qx.qgbox.R;
import com.qx.qgbox.db.*;
import com.qx.qgbox.entitys.*;

import android.content.*;

import com.qx.qgbox.activity.*;

import java.io.*;

import android.content.res.Resources;
import android.graphics.*;

import com.qx.qgbox.views.*;

import android.os.*;

import java.util.*;

import com.qx.qgbox.service.*;
import com.qx.qgbox.utils.*;

import android.annotation.*;
import android.util.*;
import android.view.*;
import android.graphics.drawable.*;

import org.json.*;

import android.text.*;
import android.support.annotation.*;

import com.qx.qgbox.adapters.*;

import android.widget.*;

public class newSetupdialog extends AlertDialog implements View.OnTouchListener, AbstractSpinerAdapter.IOnItemSelectListener {
    public static final int MSG_ON_ADD_OFFICIAL_FILE_URL_FAIL = 201827;
    public static final int MSG_ON_ADD_OFFICIAL_FILE_URL_SUCCESS = 201826;
    public static final int MSG_ON_CHANGE_MAP0 = 201811;
    public static final int MSG_ON_CHANGE_MAP1 = 201812;
    public static final int MSG_ON_CHANGE_MAP2 = 201813;
    public static final int MSG_ON_CHANGE_MAP3 = 201814;
    public static final int MSG_ON_CHANGE_MAP4 = 201815;
    public static final int MSG_ON_DISPLAY_SETUP_AGAIN = 201860;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_FAIL = 201831;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_SUCCESS = 201830;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_FAIL = 201835;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_SUCCESS = 201834;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_FAIL = 201839;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_SUCCESS = 201838;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_FAIL = 201843;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_SUCCESS = 201842;
    public static final int MSG_ON_DOWNLOAD_F5_FILE_FAIL = 201847;
    public static final int MSG_ON_DOWNLOAD_F5_FILE_SUCCESS = 201846;
    public static final int MSG_ON_DOWNLOAD_SHARE_FILE_FAIL = 201821;
    public static final int MSG_ON_DOWNLOAD_SHARE_FILE_SUCCESS = 201820;
    public static final int MSG_ON_GET_DEVICE_MODEL_FAIL = 201851;
    public static final int MSG_ON_GET_DEVICE_MODEL_SUCCESS = 201850;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_FAIL = 201829;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_SUCCESS = 201828;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_FAIL = 201833;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_SUCCESS = 201832;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_FAIL = 201837;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_SUCCESS = 201836;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_FAIL = 201841;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_SUCCESS = 201840;
    public static final int MSG_ON_GET_F5_FILE_DOWNLOAD_URL_FAIL = 201845;
    public static final int MSG_ON_GET_F5_FILE_DOWNLOAD_URL_SUCCESS = 201844;
    public static final int MSG_ON_GET_OFFICIAL_GAME_FAIL = 201853;
    public static final int MSG_ON_GET_OFFICIAL_GAME_SUCCESS = 201852;
    public static final int MSG_ON_GET_SHARE_FILE_DOWNLOAD_URL_FAIL = 201823;
    public static final int MSG_ON_GET_SHARE_FILE_DOWNLOAD_URL_SUCCESS = 201822;
    public static final int MSG_ON_GET_UNSUPPORTED_RESOLUTION_LIST_FAIL = 201902;
    public static final int MSG_ON_GET_UNSUPPORTED_RESOLUTION_LIST_SUCCESS = 201901;
    public static final int MSG_ON_LOAD_MAP = 201859;
    public static final int MSG_ON_LOAD_MAP_ERROR = 201858;
    public static final int MSG_ON_POST_OFFICIAL_GAME_PRESET_URL_SUCCESS = 201856;
    public static final int MSG_ON_POST_OFFICIAL_GAME_PRESET_URL__FAIL = 201857;
    public static final int MSG_ON_POS_SHARE_FILE_URL_FAIL = 201819;
    public static final int MSG_ON_POS_SHARE_FILE_URL_SUCCESS = 201818;
    public static final int MSG_ON_REFRESH_MAP_FAIL = 201803;
    public static final int MSG_ON_REFRESH_MAP_SUCCESS = 201804;
    public static final int MSG_ON_SAVE_MAP_TO_DEVICE = 201801;
    public static final int MSG_ON_SAVE_MAP_TO_DEVICE_FAIL = 201802;
    public static final int MSG_ON_SEND_SYNC_DATA_TO_DEVICE = 201849;
    public static final int MSG_ON_START_LOAD_MAP = 201805;
    public static final int MSG_ON_SYNC_NEXT_LOCATION_DATA = 201848;
    public static final int MSG_ON_UOLOAD_OFFICIAL_GAME_PRESET_FAIL = 201855;
    public static final int MSG_ON_UOLOAD_OFFICIAL_GAME_PRESET_SUCCESS = 201854;
    public static final int MSG_ON_UPLOAD_FILE_FAIL = 201817;
    public static final int MSG_ON_UPLOAD_FILE_SUCCESS = 201816;
    public static final int MSG_ON_UPLOAD_OFFICIAL_FILE_FAIL = 201825;
    public static final int MSG_ON_UPLOAD_OFFICIAL_FILE_SUCCESS = 201824;
    public static final String TAG = "newSetupdialog";
    public static byte[] dataproSave;
    private static ArrayList<Device> deviceList;
    public static int deviceType = 0;
    public static int deviceX = 0;
    public static int deviceY = 0;
    public static String downloadOfficialFileUrl = "";
    public static String downloadShareFilePath;
    public static int gameId = 0;
    public static boolean isF1 = false;
    public static boolean isF2 = false;
    public static boolean isF3 = false;
    public static boolean isF4 = false;
    public static boolean isF5 = false;
    public static boolean isMapInfosSuccess = false;
    public static boolean isSyncPresetNow = false;
    public static boolean isTest = true;
    public static int landscape = 0;
    public static int length = 351;
    public static String location = "";
    public static String locationPath = "";
    private static loadingDialog mLoadingDialog;
    public static ArrayList<NormalKey> mNormalKeyList;
    private static syncdingDialog mSyncdingDialog;
    public static DataSaverM[] mdatasaver;
    public static String model = "";
    public static ArrayList<String> modelList;
    public static int modelNum = 0;
    public static int mousespeed = 2;
    public static Handler myhandler;
    public static int platform = 1;
    public static int portrait = 1;
    private static int reSendNum = 0;
    public static String resolution = "";
    private static int saveNum = 0;
    public static String setdatal = "-1";
    public static String setdatar = "-1";
    public static String shareCode;
    public static String syncCurrentLocation = "";
    public static String syncToastStr = "";
    public static String uploadFilePath;
    public static String uploadOfficialFilePath = "";
    public static String uploadOfficialGameFileName = "temp.ini";
    public static String uploadOfficialGameFilePath = "";
    private int currentposition;
    private int dragstart;
    private int getUnsupportedResolutionListNum;
    private ImageView[] imageView;
    private ImageView immenu;
    boolean isLongClickModule;
    boolean isLongClicking;
    int[] keystate;
    int[] keystateold;
    private long lastClickTime;
    private int lastX;
    private int lastY;
    String locale;
    private DBManager mDBManager;
    private errorloggwindow mErrorloggwindow;
    private greenDialog mGreenDialog;
    private MySeekBarDialog mMySeekBarDialog;
    private newSaveDialog mNewSaveDialog;
    private ArrayList<OfficialGame> mOfficialGameList;
    private postToOffDialog mPostToOffDialog;
    private saveMapErroeloggwindow mSaveMapErroeloggwindow;
    private setupdialogwindow mSetupdialogwindow;
    private setupdialogwindow2 mSetupdialogwindow2;
    private SpinerPopWindow mSpinerPopWindow;
    private ArrayList<SunyesMaxGamePreset> mSunyesMaxGamePresetList;
    private ArrayList<String> mUnsupportedResolutionList;
    private viewSetupDialogwindow mViewSetupDialogwindow;
    RelativeLayout main;
    Context mcontext;
    private TextView mname;
    private newSaveDialog1 mnewSaveDialog1;
    private List<String> nameList;
    long time;

    static {
        newSetupdialog.mNormalKeyList = new ArrayList<NormalKey>();
        newSetupdialog.modelList = new ArrayList<String>();
        newSetupdialog.dataproSave = new byte[264];
        newSetupdialog.mdatasaver = new DataSaverM[newSetupdialog.length];
        newSetupdialog.deviceList = new ArrayList<Device>();
    }

    public newSetupdialog(final Context mcontext) {
        super(mcontext, R.style.MyDialog);
        this.time = 0L;
        this.main = null;
        this.locale = null;
        this.mcontext = null;
        this.isLongClickModule = false;
        this.isLongClicking = false;
        this.keystate = new int[newSetupdialog.length];
        this.keystateold = new int[newSetupdialog.length];
        this.mOfficialGameList = new ArrayList<OfficialGame>();
        this.mPostToOffDialog = null;
        this.mNewSaveDialog = null;
        this.mnewSaveDialog1 = null;
        this.imageView = new ImageView[newSetupdialog.length];
        this.mname = null;
        this.lastX = 0;
        this.lastY = 0;
        this.dragstart = 0;
        this.currentposition = -1;
        this.nameList = new ArrayList<String>();
        this.mMySeekBarDialog = null;
        this.mGreenDialog = null;
        this.mViewSetupDialogwindow = null;
        this.mErrorloggwindow = null;
        this.mSaveMapErroeloggwindow = null;
        this.lastClickTime = 0L;
        this.mUnsupportedResolutionList = new ArrayList<String>();
        this.getUnsupportedResolutionListNum = 0;
        this.mSunyesMaxGamePresetList = new ArrayList<SunyesMaxGamePreset>();
        this.mcontext = mcontext;
    }

    private void changeData() {
        int n;
        int n2;
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            n = (int) bluetoothdevmanager.screenwp;
            n2 = (int) bluetoothdevmanager.screenhp;
        } else {
            n = (int) bluetoothdevmanager.screenhp;
            n2 = (int) bluetoothdevmanager.screenwp;
        }
        newSetupdialog.dataproSave = AnalyseSpDataUtil.changeDataToByteArray(newSetupdialog.mdatasaver, n, n2, bluetoothdevmanager.mapVersion, newSetupdialog.mousespeed);
        final byte[] array = new byte[262];
        System.arraycopy(newSetupdialog.dataproSave, 2, array, 0, 262);
        final int crc_GetModbus16 = CommonUtils.CRC_GetModbus16(array, 262);
        final StringBuilder sb = new StringBuilder();
        sb.append("------save checkCode = ");
        sb.append(crc_GetModbus16);
        MyLog.i("crc_tag", sb.toString());
        final byte[] intToByteArray = ChangeDataUtil.intToByteArray(crc_GetModbus16);
        newSetupdialog.dataproSave[0] = intToByteArray[1];
        newSetupdialog.dataproSave[1] = intToByteArray[0];
        if (bluetoothdevmanager.servicehandle != null) {
            final Message message = new Message();
            message.what = 20013;
            message.obj = newSetupdialog.dataproSave;
            bluetoothdevmanager.servicehandle.sendMessage(message);
            MyApplication.setSaveData(newSetupdialog.dataproSave);
        }
    }

    private void createLocationTxtFile(final String s) {
        int n;
        int n2;
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            n = (int) bluetoothdevmanager.screenwp;
            n2 = (int) bluetoothdevmanager.screenhp;
        } else {
            n = (int) bluetoothdevmanager.screenhp;
            n2 = (int) bluetoothdevmanager.screenwp;
        }
        final byte[] changeDataToByteArray = AnalyseSpDataUtil.changeDataToByteArray(newSetupdialog.mdatasaver, n, n2, bluetoothdevmanager.mapVersion, newSetupdialog.mousespeed);
        final byte[] array = new byte[262];
        System.arraycopy(newSetupdialog.dataproSave, 2, array, 0, 262);
        final byte[] intToByteArray = ChangeDataUtil.intToByteArray(CommonUtils.CRC_GetModbus16(array, 262));
        changeDataToByteArray[0] = intToByteArray[1];
        changeDataToByteArray[1] = intToByteArray[0];
        ChangeDataUtil.createFileWithByte(s, changeDataToByteArray);
    }

    private void dataprocess1(final byte[] array) {
        if (array[0] != 17) {
            return;
        }
        if (!newSetupdialog.isMapInfosSuccess) {
            return;
        }
        for (int i = 0; i < 107; ++i) {
            if (KeyMap.mkeymap[i].value != array[4] && KeyMap.mkeymap[i].value != array[5] && KeyMap.mkeymap[i].value != array[6] && KeyMap.mkeymap[i].value != array[7] && KeyMap.mkeymap[i].value != array[8]) {
                this.keystate[i] = 0;
            } else {
                this.keystate[i] = 1;
            }
        }
        if ((array[3] & 0x1) == 0x1) {
            this.keystate[96] = 1;
        } else {
            this.keystate[96] = 0;
        }
        if ((array[3] & 0x2) == 0x2) {
            this.keystate[97] = 1;
        } else {
            this.keystate[97] = 0;
        }
        if ((array[3] & 0x4) == 0x4) {
            this.keystate[98] = 1;
        } else {
            this.keystate[98] = 0;
        }
        if ((array[3] & 0x8) == 0x8) {
            this.keystate[99] = 1;
        } else {
            this.keystate[99] = 0;
        }
        if ((array[3] & 0x10) == 0x10) {
            this.keystate[100] = 1;
        } else {
            this.keystate[100] = 0;
        }
        if ((array[3] & 0x20) == 0x20) {
            this.keystate[101] = 1;
        } else {
            this.keystate[101] = 0;
        }
        if ((array[3] & 0x40) == 0x40) {
            this.keystate[102] = 1;
        } else {
            this.keystate[102] = 0;
        }
        if ((array[3] & 0x80) == 0x80) {
            this.keystate[103] = 1;
        } else {
            this.keystate[103] = 0;
        }
        if ((array[9] & 0x1) == 0x1) {
            this.keystate[104] = 1;
        } else {
            this.keystate[104] = 0;
        }
        if ((array[9] & 0x4) == 0x4) {
            this.keystate[105] = 1;
        } else {
            this.keystate[105] = 0;
        }
        if ((array[9] & 0x2) == 0x2) {
            this.keystate[106] = 1;
        } else {
            this.keystate[106] = 0;
        }
        System.arraycopy(this.keystate, 0, this.keystate, 107, 24);
        System.arraycopy(this.keystate, 0, this.keystate, 131, 24);
        System.arraycopy(this.keystate, 0, this.keystate, 155, 24);
        System.arraycopy(this.keystate, 0, this.keystate, 179, 24);
        this.keystate[203] = this.keystate[74];
        this.keystate[204] = this.keystate[75];
        this.keystate[205] = this.keystate[76];
        this.keystate[206] = this.keystate[77];
        for (int j = 0; j < 107; ++j) {
            if (this.keystate[j] == 1 && j != 53 && j != 54 && j != 56 && j != 105 && j != 57) {
                if (j == 41) {
                    if (newSetupdialog.mdatasaver[130].property != 1 && newSetupdialog.mdatasaver[160].property != 1) {
                        if (newSetupdialog.mdatasaver[41].name.equalsIgnoreCase("tab")) {
                            this.gonedone(KeyMap.mkeymap[j].name, j);
                        } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                            if (this.mErrorloggwindow == null) {
                                this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                            }
                            if (!this.mErrorloggwindow.isShowing()) {
                                this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                                newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                            }
                        } else {
                            this.gonedone(KeyMap.mkeymap[j].name, j);
                        }
                    } else {
                        this.gonedone(KeyMap.mkeymap[j].name, 130);
                        this.gonedone(KeyMap.mkeymap[j].name, 160);
                    }
                } else if (j == 23) {
                    if (newSetupdialog.mdatasaver[41].name.equalsIgnoreCase("N")) {
                        this.gonedone(KeyMap.mkeymap[j].name, j);
                    } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                        if (this.mErrorloggwindow == null) {
                            this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                        }
                        if (!this.mErrorloggwindow.isShowing()) {
                            this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                            newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                        }
                    } else {
                        this.gonedone(KeyMap.mkeymap[j].name, j);
                    }
                } else if (j < 30 && j != 12 && j != 13 && j != 7 && j != 14) {
                    final DataSaverM[] mdatasaver = newSetupdialog.mdatasaver;
                    final int n = j + 107;
                    if (mdatasaver[n].property != 1 && newSetupdialog.mdatasaver[j + 137].property != 1) {
                        final DataSaverM[] mdatasaver2 = newSetupdialog.mdatasaver;
                        final int n2 = j + 231;
                        if (mdatasaver2[n2].property != 4 && newSetupdialog.mdatasaver[j + 261].property != 4) {
                            final DataSaverM[] mdatasaver3 = newSetupdialog.mdatasaver;
                            final int n3 = j + 167;
                            if (mdatasaver3[n3].property == 2) {
                                this.gonedone(KeyMap.mkeymap[j].name, n3);
                            } else {
                                final DataSaverM[] mdatasaver4 = newSetupdialog.mdatasaver;
                                final int n4 = j + 197;
                                if (mdatasaver4[n4].property == 3) {
                                    this.gonedone(KeyMap.mkeymap[j].name, n4);
                                } else if (!newSetupdialog.mdatasaver[j].name.equalsIgnoreCase("-1")) {
                                    this.gonedone(KeyMap.mkeymap[j].name, j);
                                } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                                    if (this.mErrorloggwindow == null) {
                                        this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                                    }
                                    if (!this.mErrorloggwindow.isShowing()) {
                                        this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                                        newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                                    }
                                } else {
                                    this.gonedone(KeyMap.mkeymap[j].name, j);
                                }
                            }
                        } else {
                            this.gonedone(KeyMap.mkeymap[j].name, n2);
                            this.gonedone(KeyMap.mkeymap[j].name, j + 261);
                        }
                    } else {
                        this.gonedone(KeyMap.mkeymap[j].name, n);
                        this.gonedone(KeyMap.mkeymap[j].name, j + 137);
                    }
                } else if (j != 12 && j != 13 && j != 7 && j != 14) {
                    if (j == 42) {
                        if (!newSetupdialog.mdatasaver[42].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 42);
                        } else if (!newSetupdialog.mdatasaver[121].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 121);
                        } else if (!newSetupdialog.mdatasaver[151].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 151);
                        } else if (!newSetupdialog.mdatasaver[245].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 245);
                        } else if (!newSetupdialog.mdatasaver[275].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 275);
                        } else if (!newSetupdialog.mdatasaver[305].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 305);
                        } else if (!newSetupdialog.mdatasaver[335].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[42].name, 335);
                        } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                            if (this.mErrorloggwindow == null) {
                                this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                            }
                            if (!this.mErrorloggwindow.isShowing()) {
                                this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                                newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                            }
                        } else {
                            this.gonedone(KeyMap.mkeymap[j].name, j);
                        }
                    } else if (j == 96) {
                        if (!newSetupdialog.mdatasaver[96].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 96);
                        } else if (!newSetupdialog.mdatasaver[119].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 119);
                        } else if (!newSetupdialog.mdatasaver[149].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 149);
                        } else if (!newSetupdialog.mdatasaver[243].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 243);
                        } else if (!newSetupdialog.mdatasaver[273].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 273);
                        } else if (!newSetupdialog.mdatasaver[303].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 303);
                        } else if (!newSetupdialog.mdatasaver[333].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[96].name, 333);
                        } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                            if (this.mErrorloggwindow == null) {
                                this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                            }
                            if (!this.mErrorloggwindow.isShowing()) {
                                this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                                newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                            }
                        } else {
                            this.gonedone(KeyMap.mkeymap[j].name, j);
                        }
                    } else if (j == 97) {
                        if (!newSetupdialog.mdatasaver[97].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 97);
                        } else if (!newSetupdialog.mdatasaver[120].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 120);
                        } else if (!newSetupdialog.mdatasaver[150].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 150);
                        } else if (!newSetupdialog.mdatasaver[244].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 244);
                        } else if (!newSetupdialog.mdatasaver[274].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 274);
                        } else if (!newSetupdialog.mdatasaver[304].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 304);
                        } else if (!newSetupdialog.mdatasaver[334].name.equalsIgnoreCase("-1")) {
                            this.gonedone(KeyMap.mkeymap[97].name, 334);
                        } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                            if (this.mErrorloggwindow == null) {
                                this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                            }
                            if (!this.mErrorloggwindow.isShowing()) {
                                this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                                newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                            }
                        } else {
                            this.gonedone(KeyMap.mkeymap[j].name, j);
                        }
                    } else if (!newSetupdialog.mdatasaver[j].name.equalsIgnoreCase("-1")) {
                        this.gonedone(KeyMap.mkeymap[j].name, j);
                    } else if (this.isMostKey() && !this.isNullBlueData(array)) {
                        if (this.mErrorloggwindow == null) {
                            this.mErrorloggwindow = new errorloggwindow(this.mcontext);
                        }
                        if (!this.mErrorloggwindow.isShowing()) {
                            this.mErrorloggwindow.showAtLocation((View) this.main, 17, 0, 0);
                            newSetupdialog.myhandler.sendEmptyMessageDelayed(6, 800L);
                        }
                    } else {
                        this.gonedone(KeyMap.mkeymap[j].name, j);
                    }
                }
            }
        }
        System.arraycopy(this.keystate, 0, this.keystateold, 0, newSetupdialog.length);
    }

    private void downloadOfficialFile(final JSONObject jsonObject, final int n, final int n2, final int n3, final String s) {
        try {
            if (jsonObject.getInt("code") == 1000 && jsonObject.getString("message").equalsIgnoreCase("success") && CommonUtils.isStringValid(jsonObject.getString("url"))) {
                newSetupdialog.locationPath = null;
                final StringBuilder sb = new StringBuilder();
                sb.append(this.mcontext.getApplicationContext().getFilesDir().getPath());
                sb.append("/");
                sb.append(FirstPageActivity.projectName);
                sb.append(s);
                sb.append(".ini");
                newSetupdialog.locationPath = sb.toString();
                final File file = new File(newSetupdialog.locationPath);
                if (file.exists()) {
                    file.delete();
                }
                HttpUtil.downloadFile(newSetupdialog.myhandler, n2, n3, jsonObject.getString("url"), newSetupdialog.locationPath);
            } else {
                newSetupdialog.myhandler.sendEmptyMessage(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            newSetupdialog.myhandler.sendEmptyMessage(n);
        }
    }

    private void getaixSetupDialog(final View view) {
        view.getId();
        if (this.mMySeekBarDialog != null) {
            if (this.mMySeekBarDialog.isShowing()) {
                this.mMySeekBarDialog.dismiss();
            }
            this.mMySeekBarDialog = null;
        }
        (this.mMySeekBarDialog = new MySeekBarDialog(this.mcontext, bluetoothdevmanager.radius)).showAtLocation((View) this.main, 17, 0, 0);
    }

    private int getpicturewh(final int n) {
        final Bitmap decodeResource = BitmapFactory.decodeResource(this.mcontext.getResources(), R.drawable.b);
        if (n == 1) {
            return decodeResource.getHeight();
        }
        return decodeResource.getWidth();
    }

    private int getscreenwh(final int n) {
        new DisplayMetrics();
        final DisplayMetrics displayMetrics = this.mcontext.getResources().getDisplayMetrics();
        if (n == 0) {
            return displayMetrics.widthPixels;
        }
        return displayMetrics.heightPixels;
    }

    private void hideBottomUIMenu() {
        final View decorView = this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(5894);
        decorView.setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener) new View.OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(final int n) {
                if ((n & 0x4) == 0x0) {
                    decorView.setSystemUiVisibility(5894);
                }
            }
        });
    }

    private boolean isLongPressed(float abs, float abs2, final float n, final float n2, final long n3, final long n4, final long n5) {
        abs = Math.abs(n - abs);
        abs2 = Math.abs(n2 - abs2);
        return abs >= 25.0f || abs2 >= 25.0f;
    }

    private boolean isMostKey() {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < newSetupdialog.length) {
            int n3 = n;
            if (newSetupdialog.mdatasaver[i].x != -1) {
                n3 = n + 1;
            }
            int n4 = 0;
            Label_0066:
            {
                if (newSetupdialog.mdatasaver[i].property != 4) {
                    n4 = n2;
                    if (newSetupdialog.mdatasaver[i].property != 1) {
                        break Label_0066;
                    }
                }
                n4 = n2 + 1;
            }
            ++i;
            n = n3;
            n2 = n4;
        }
        return n - 6 - n2 / 2 >= 24;
    }

    private boolean isNullBlueData(final byte[] array) {
        for (int i = 3; i < 10; ++i) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void listprofile() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mcontext.getApplicationContext().getFilesDir().getPath());
        sb.append("/qx.ini");
        final IniFile iniFile = new IniFile(new File(sb.toString()));
        final String s = (String) iniFile.get("setup", Integer.toString(56));
        if (!CommonUtils.isStringValid(s)) {
            newSetupdialog.myhandler.sendEmptyMessage(201821);
            return;
        }
        final String[] split = s.split(" ");
        if (split.length > 0) {
            int n = 1;
            if (split[1].equalsIgnoreCase("mouse")) {
                int n2;
                for (int i = 0; i < newSetupdialog.length; ++i, n = n2) {
                    final String s2 = (String) iniFile.get("setup", Integer.toString(i));
                    n2 = n;
                    if (s2 != null) {
                        final String[] split2 = s2.split(" ");
                        final String s3 = split2[0];
                        final String name = split2[n];
                        final String s4 = split2[2];
                        final String s5 = split2[3];
                        final String joystick = split2[4];
                        String rumble = "-1";
                        String whichmoto = "-1";
                        String ms = "-1";
                        if (split2.length > 5) {
                            final String s6 = split2[5];
                            final String s7 = split2[6];
                            rumble = s6;
                            whichmoto = s7;
                            ms = ms;
                            if (split2.length == 8) {
                                ms = split2[7];
                                whichmoto = s7;
                                rumble = s6;
                            }
                        }
                        if (Integer.parseInt(s3) != 5 && Integer.parseInt(s3) != 6) {
                            newSetupdialog.mdatasaver[i].property = Integer.parseInt(s3);
                        } else if (bluetoothdevmanager.mapVersion == 2) {
                            newSetupdialog.mdatasaver[i].property = Integer.parseInt(s3);
                        } else if (bluetoothdevmanager.mapVersion == 1) {
                            newSetupdialog.mdatasaver[i].property = 0;
                        }
                        n2 = 1;
                        newSetupdialog.mdatasaver[i].name = name;
                        newSetupdialog.mdatasaver[i].x = Integer.parseInt(s4);
                        newSetupdialog.mdatasaver[i].y = Integer.parseInt(s5);
                        newSetupdialog.mdatasaver[i].joystick = joystick;
                        newSetupdialog.mdatasaver[i].rumble = rumble;
                        newSetupdialog.mdatasaver[i].whichmoto = whichmoto;
                        newSetupdialog.mdatasaver[i].ms = ms;
                    }
                }
                this.cleanallview();
                this.displaycircle();
                new ToastDialog(this.mcontext, this.mcontext.getResources().getString(R.string.new_setup_dialog_tip23)).show();
                new saveNowDialoggwindow(this.mcontext).showAtLocation((View) this.main, 17, 0, 0);
                return;
            }
        }
        newSetupdialog.myhandler.sendEmptyMessage(201821);
    }

    private void presetupnormal() {
        newSetupdialog.mdatasaver[56].property = 0;
        newSetupdialog.mdatasaver[56].name = "mouse";
        newSetupdialog.mdatasaver[56].x = 1500;
        newSetupdialog.mdatasaver[56].y = 355;
        newSetupdialog.mdatasaver[56].joystick = "-1";
        newSetupdialog.mdatasaver[56].rumble = "-1";
        newSetupdialog.mdatasaver[56].whichmoto = "-1";
        newSetupdialog.mdatasaver[56].ms = "-1";
        newSetupdialog.mdatasaver[55].property = 0;
        newSetupdialog.mdatasaver[55].name = "center";
        newSetupdialog.mdatasaver[55].x = 370;
        newSetupdialog.mdatasaver[55].y = 830;
        newSetupdialog.mdatasaver[55].joystick = "-1";
        newSetupdialog.mdatasaver[55].rumble = "-1";
        newSetupdialog.mdatasaver[55].whichmoto = "-1";
        newSetupdialog.mdatasaver[55].ms = "-1";
        newSetupdialog.mdatasaver[104].property = 0;
        newSetupdialog.mdatasaver[104].name = "mouseleft";
        newSetupdialog.mdatasaver[104].x = 370;
        newSetupdialog.mdatasaver[104].y = 490;
        newSetupdialog.mdatasaver[104].joystick = "-1";
        newSetupdialog.mdatasaver[104].rumble = "-1";
        newSetupdialog.mdatasaver[104].whichmoto = "-1";
        newSetupdialog.mdatasaver[104].ms = "-1";
        newSetupdialog.mdatasaver[106].property = 0;
        newSetupdialog.mdatasaver[106].name = "mouseright";
        newSetupdialog.mdatasaver[106].x = 1750;
        newSetupdialog.mdatasaver[106].y = 490;
        newSetupdialog.mdatasaver[106].joystick = "-1";
        newSetupdialog.mdatasaver[106].rumble = "-1";
        newSetupdialog.mdatasaver[106].whichmoto = "-1";
        newSetupdialog.mdatasaver[106].ms = "-1";
    }

    private void profilesave(final String s) {
        final IniFile iniFile = new IniFile(new File(s));
        if (iniFile != null) {
            if (newSetupdialog.mdatasaver[55].x != -1) {
                newSetupdialog.mdatasaver[55].joystick = newSetupdialog.setdatal;
            }
            for (int i = 0; i < newSetupdialog.length; ++i) {
                final String string = Integer.toString(i);
                final StringBuilder sb = new StringBuilder();
                sb.append(newSetupdialog.mdatasaver[i].property);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].name);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].x);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].y);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].joystick);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].rumble);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].whichmoto);
                sb.append(" ");
                sb.append(newSetupdialog.mdatasaver[i].ms);
                iniFile.set("setup", string, sb.toString());
            }
            iniFile.save();
        }
    }

    private void sendSyncDataToDevice(String rumble) {
        newSetupdialog.isSyncPresetNow = true;
        newSetupdialog.locationPath = null;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mcontext.getApplicationContext().getFilesDir().getPath());
        sb.append("/");
        sb.append(FirstPageActivity.projectName);
        sb.append(rumble);
        sb.append(".ini");
        newSetupdialog.locationPath = sb.toString();
        final IniFile iniFile = new IniFile(new File(newSetupdialog.locationPath));
        int n = 0;
        for (int i = 0; i < newSetupdialog.length; ++i) {
            rumble = (String) iniFile.get("setup", Integer.toString(i));
            if (rumble != null) {
                final String[] split = rumble.split(" ");
                final String s = split[n];
                final String name = split[1];
                final String s2 = split[2];
                final String s3 = split[3];
                final String joystick = split[4];
                rumble = "-1";
                String whichmoto = "-1";
                String ms = "-1";
                if (split.length > 5) {
                    final String s4 = split[5];
                    final String s5 = split[6];
                    rumble = s4;
                    whichmoto = s5;
                    ms = ms;
                    if (split.length == 8) {
                        ms = split[7];
                        whichmoto = s5;
                        rumble = s4;
                    }
                }
                if (Integer.parseInt(s) != 5 && Integer.parseInt(s) != 6) {
                    newSetupdialog.mdatasaver[i].property = Integer.parseInt(s);
                } else if (bluetoothdevmanager.mapVersion == 2) {
                    newSetupdialog.mdatasaver[i].property = Integer.parseInt(s);
                } else if (bluetoothdevmanager.mapVersion == 1) {
                    newSetupdialog.mdatasaver[i].property = 0;
                }
                n = 0;
                newSetupdialog.mdatasaver[i].name = name;
                newSetupdialog.mdatasaver[i].x = Integer.parseInt(s2);
                newSetupdialog.mdatasaver[i].y = Integer.parseInt(s3);
                newSetupdialog.mdatasaver[i].joystick = joystick;
                newSetupdialog.mdatasaver[i].rumble = rumble;
                newSetupdialog.mdatasaver[i].whichmoto = whichmoto;
                newSetupdialog.mdatasaver[i].ms = ms;
            } else {
                newSetupdialog.mdatasaver[i].property = 1;
                newSetupdialog.mdatasaver[i].name = "-1";
                newSetupdialog.mdatasaver[i].x = -1;
                newSetupdialog.mdatasaver[i].y = -1;
                newSetupdialog.mdatasaver[i].joystick = "-1";
                newSetupdialog.mdatasaver[i].rumble = "-1";
                newSetupdialog.mdatasaver[i].whichmoto = "-1";
                newSetupdialog.mdatasaver[i].ms = "-1";
            }
        }
        this.changeData();
    }

    public static void setSPRadius(final int n) {
        final DataSaverM dataSaverM = newSetupdialog.mdatasaver[55];
        final StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(n);
        dataSaverM.ms = sb.toString();
    }

    private void setupViews() {
        this.mSpinerPopWindow = new SpinerPopWindow(this.mcontext);
        this.nameList.clear();
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip18));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip25));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip2));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip3));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip4));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip5));
        this.nameList.add(this.mcontext.getResources().getString(R.string.new_setup_dialog_tip6));
        this.mSpinerPopWindow.refreshData(this.nameList, 0);
        this.mSpinerPopWindow.setItemListener(this);
    }

    private void showSpinWindow() {
        final int width = this.immenu.getWidth();
        if (!this.nameList.isEmpty()) {
            final SpinerPopWindow mSpinerPopWindow = this.mSpinerPopWindow;
            final int width2 = this.immenu.getWidth();
            final int n = width / 10;
            mSpinerPopWindow.setWidth(width2 - n);
            this.mSpinerPopWindow.showAsDropDown((View) this.immenu, n, 0);
        }
    }

    private void syncCloudFinish(final Context context, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        newSetupdialog.reSendNum = 0;
        if (!b && !b2 && !b3 && !b4 && !b5) {
            new ToastDialog(this.mcontext, this.mcontext.getResources().getString(R.string.new_setup_dialog_tip38)).show();
            if (newSetupdialog.mSyncdingDialog != null && newSetupdialog.mSyncdingDialog.isShowing()) {
                newSetupdialog.mSyncdingDialog.dismiss();
            }
        } else if (b && b2 && b3 && b4 && b5) {
            newSetupdialog.myhandler.sendEmptyMessage(201811);
            newSetupdialog.syncCurrentLocation = "f1";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        } else if (b) {
            newSetupdialog.myhandler.sendEmptyMessage(201811);
            newSetupdialog.syncCurrentLocation = "f1";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        } else if (b2) {
            newSetupdialog.myhandler.sendEmptyMessage(201812);
            newSetupdialog.syncCurrentLocation = "f2";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        } else if (b3) {
            newSetupdialog.myhandler.sendEmptyMessage(201813);
            newSetupdialog.syncCurrentLocation = "f3";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        } else if (b4) {
            newSetupdialog.myhandler.sendEmptyMessage(201814);
            newSetupdialog.syncCurrentLocation = "f4";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        } else if (b5) {
            newSetupdialog.myhandler.sendEmptyMessage(201815);
            newSetupdialog.syncCurrentLocation = "f5";
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
        }
    }

    public int ScreenOriatation(final Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return newSetupdialog.portrait;
        }
        return newSetupdialog.landscape;
    }

    public void ShowDialog(final String text) {
        final showdialogwindow showdialogwindow = new showdialogwindow(this.mcontext, text);
        showdialogwindow.tx.setText((CharSequence) text);
        showdialogwindow.showAtLocation((View) this.main, 17, 0, 0);
    }

    void cleanallview() {
        if (this.main != null) {
            for (int i = 0; i < newSetupdialog.length; ++i) {
                if (this.imageView[i] != null) {
                    this.main.removeView((View) this.imageView[i]);
                    this.imageView[i].setId(-1);
                }
            }
        }
    }

    void clearkeyinfor() {
        this.cleanallview();
        for (int i = 0; i < newSetupdialog.length; ++i) {
            newSetupdialog.mdatasaver[i].property = -1;
            newSetupdialog.mdatasaver[i].name = "-1";
            newSetupdialog.mdatasaver[i].x = -1;
            newSetupdialog.mdatasaver[i].y = -1;
            newSetupdialog.mdatasaver[i].joystick = "-1";
            newSetupdialog.mdatasaver[i].rumble = "-1";
            newSetupdialog.mdatasaver[i].whichmoto = "-1";
            newSetupdialog.mdatasaver[i].ms = "-1";
        }
    }

    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (this.dragstart == 1) {
            return this.onTouch((View) this.imageView[this.currentposition], motionEvent);
        }
        if (this.dragstart == 2) {
            return this.onTouch((View) this.immenu, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    void displaycircle() {
        for (int i = 0; i < newSetupdialog.length; ++i) {
            if (newSetupdialog.mdatasaver[i].x != -1) {
                this.redisplaymap(i, 0);
            }
        }
    }

    void displaysetup() {
        this.cleanallview();
        this.displaycircle();
    }

    void gonedone(final String name, final int id) {
        if (id != 7 && id != 12 && id != 13 && id != 14) {
            final ViewGroup viewGroup = (ViewGroup) this.imageView[id].getParent();
            if (viewGroup != null) {
                viewGroup.removeView((View) this.imageView[id]);
            }
            final int width = this.imageView[id].getWidth();
            final int height = this.imageView[id].getHeight();
            this.main.removeView((View) this.imageView[id]);
            this.imageView[id].setX((float) (this.getscreenwh(0) / 2 - width / 2));
            this.imageView[id].setY((float) (this.getscreenwh(1) / 2 - height / 2));
            this.imageView[id].setId(id);
            newSetupdialog.mdatasaver[id].x = this.getscreenwh(0) / 2;
            newSetupdialog.mdatasaver[id].y = this.getscreenwh(1) / 2;
            newSetupdialog.mdatasaver[id].name = name;
            if (id == 98 && name.equalsIgnoreCase("leftalt")) {
                newSetupdialog.mdatasaver[id].property = 2;
            }
            this.main.addView((View) this.imageView[id]);
            this.setContentView((View) this.main);
        }
    }

    void initMdatasaver() {
        for (int i = 0; i < newSetupdialog.length; ++i) {
            newSetupdialog.mdatasaver[i] = new DataSaverM();
            newSetupdialog.mdatasaver[i].property = -1;
            newSetupdialog.mdatasaver[i].name = "-1";
            newSetupdialog.mdatasaver[i].x = -1;
            newSetupdialog.mdatasaver[i].y = -1;
            newSetupdialog.mdatasaver[i].joystick = "-1";
            newSetupdialog.mdatasaver[i].rumble = "-1";
            newSetupdialog.mdatasaver[i].whichmoto = "-1";
            newSetupdialog.mdatasaver[i].ms = "-1";
        }
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            final float screenwp = bluetoothdevmanager.screenwp;
            bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
            bluetoothdevmanager.screenhp = screenwp;
        }
        final byte[] datapro = MyApplication.getDatapro();
        if (datapro == null) {
            return;
        }
        newSetupdialog.mousespeed = datapro[13];
        newSetupdialog.mdatasaver = AnalyseSpDataUtil.initMdatasaverByByteArray(datapro, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp);
    }

    int inrectbtn(final float n, final float n2) {
        final int[] array = new int[2];
        final int width = this.immenu.getWidth();
        final int height = this.immenu.getHeight();
        this.immenu.getLocationOnScreen(array);
        final int n3 = array[0];
        final int n4 = height + array[1];
        final int n5 = this.getpicturewh(0) / 2 + 15;
        if (n > array[0] && n < array[0] + n5 && n2 > array[1] && n2 < n4) {
            return 0;
        }
        final int n6 = width - n5;
        array[0] += n5;
        if (n > array[0] && n < array[0] + n6 / 5 && n2 > array[1] && n2 < n4) {
            return 1;
        }
        if (n > array[0] + n6 / 5 && n < array[0] + n6 * 2 / 5 && n2 > array[1] && n2 < n4) {
            return 2;
        }
        if (n > array[0] + n6 * 2 / 5 && n < array[0] + n6 * 3 / 5 && n2 > array[1] && n2 < n4) {
            return 3;
        }
        if (n > array[0] + n6 * 3 / 5 && n < array[0] + n6 * 4 / 5 && n2 > array[1] && n2 < n4) {
            return 4;
        }
        if (n > array[0] + n6 * 4 / 5 && n < array[0] + n6 && n2 > array[1] && n2 < n4) {
            return 5;
        }
        return -1;
    }

    int ishaveview() {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < newSetupdialog.length) {
            int n3 = n2;
            if (-1 != this.imageView[i].getId()) {
                n3 = n2 + 1;
            }
            int n4 = n;
            if (newSetupdialog.mdatasaver[i].x != -1) {
                n4 = n + 1;
            }
            ++i;
            n = n4;
            n2 = n3;
        }
        if (n == 0 && n2 == 0) {
            return 0;
        }
        return 1;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.setupk_main);
        this.getWindow().setLayout(-1, -1);
        this.getWindow().setFlags(8, 8);
        if (NotchSizeUtil.hasNotchInScreen(this.mcontext)) {
            NotchSizeUtil.setFullScreenWindowLayoutInDisplayCutout(this.getWindow());
        }
        while (true) {
            try {
                this.mDBManager = new DBManager(this.mcontext);
                if (Build.VERSION.SDK_INT < 23) {
                    this.getWindow().setType(2005);
                } else if (Build.VERSION.SDK_INT != 28 && Build.VERSION.SDK_INT != 26 && Build.VERSION.SDK_INT != 27) {
                    if (Build.VERSION.SDK_INT > 28) {
                        this.getWindow().setType(2038);
                    } else {
                        this.getWindow().setType(2003);
                    }
                } else {
                    this.getWindow().setType(2038);
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("----SDK_INT = ");
                sb.append(Build.VERSION.SDK_INT);
                MyLog.i("my_tag", sb.toString());
                if (Build.VERSION.SDK_INT >= 28) {
                    final WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
                    attributes.layoutInDisplayCutoutMode = 1;
                    this.getWindow().setAttributes(attributes);
                }
                this.setCancelable(false);
                this.hideBottomUIMenu();
                (this.main = (RelativeLayout) this.findViewById(R.id.main_Layout)).setOnTouchListener((View.OnTouchListener) this);
                this.locale = Locale.getDefault().toString();
                this.immenu = (ImageView) this.findViewById(R.id.imageView1);
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("---------locale = ");
                sb2.append(this.locale);
                MyLog.i("my_tag", sb2.toString());
                if (this.locale.contains("zh_CN")) {
                    this.immenu.setImageResource(R.drawable.menu_sp_cn);
                } else if (!this.locale.contains("zh_HK") && !this.locale.contains("zh_TW")) {
                    if (this.locale.contains("en")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_en);
                    } else if (this.locale.contains("ar_EG")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_reg);
                    } else if (this.locale.contains("ja_JP")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rjp);
                    } else if (this.locale.contains("ko_KR")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rkr);
                    } else if (this.locale.contains("ms_MY")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rmy);
                    } else if (this.locale.contains("pt_PT")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rpt);
                    } else if (this.locale.contains("th_TH")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rth);
                    } else if (this.locale.contains("fil_PH")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_rph);
                    } else if (this.locale.contains("ru")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_ru);
                    } else if (this.locale.contains("de")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_de);
                    } else if (this.locale.contains("fr")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_fr);
                    } else if (this.locale.contains("es")) {
                        this.immenu.setImageResource(R.drawable.menu_sp_es);
                    } else {
                        this.immenu.setImageResource(R.drawable.menu_sp_en);
                    }
                } else {
                    this.immenu.setImageResource(R.drawable.menu_sp_hk);
                }
                this.immenu.setVisibility(View.GONE);
                this.mname = (TextView) this.findViewById(R.id.textview1);
                if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                    final float screenwp = bluetoothdevmanager.screenwp;
                    bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                    bluetoothdevmanager.screenhp = screenwp;
                }
                final TextView mname = this.mname;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("");
                sb3.append(bluetoothdevmanager.currentGameName);
                mname.setText((CharSequence) sb3.toString());
                this.imageView = MyApplication.getmSpSetupImageSRCUtils().initSPImageView();
                for (int i = 0; i < newSetupdialog.length; ++i) {
                    this.imageView[i].setOnTouchListener((View.OnTouchListener) new View.OnTouchListener() {
                        public boolean onTouch(final View view, final MotionEvent motionEvent) {
                            if (motionEvent.getAction() == 0) {
                                newSetupdialog.this.startDrag(view);
                            }
                            return false;
                        }
                    });
                }
                this.immenu.setOnTouchListener((View.OnTouchListener) new View.OnTouchListener() {
                    public boolean onTouch(final View view, final MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            newSetupdialog.this.startDrag(view);
                        }
                        return false;
                    }
                });
                this.setupViews();
                this.timerinit();
                bluetoothdevmanager.setCallbackm((bluetoothdevmanager.Callback2) new bluetoothdevmanager.Callback2() {
                    @Override
                    public void onDataChange(final byte[] obj) {
                        final Message message = new Message();
                        message.what = 2;
                        message.obj = obj;
                        newSetupdialog.myhandler.sendMessage(message);
                    }
                });
                for (int j = 0; j < newSetupdialog.length; ++j) {
                    newSetupdialog.mdatasaver[j] = new DataSaverM();
                    newSetupdialog.mdatasaver[j].property = -1;
                    newSetupdialog.mdatasaver[j].name = "-1";
                    newSetupdialog.mdatasaver[j].x = -1;
                    newSetupdialog.mdatasaver[j].y = -1;
                    newSetupdialog.mdatasaver[j].joystick = "-1";
                    newSetupdialog.mdatasaver[j].rumble = "-1";
                    newSetupdialog.mdatasaver[j].whichmoto = "-1";
                    newSetupdialog.mdatasaver[j].ms = "-1";
                    this.keystate[j] = 0;
                    this.keystateold[j] = 0;
                }
                new Thread() {
                    @Override
                    public void run() {
                        while (newSetupdialog.isTest) {
                            try {
                                BlueCmdManager.setDeviceWorkMode((byte) 1);
                                Thread.sleep(500L);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }.start();
                final TextView mname2 = this.mname;
                final StringBuilder sb4 = new StringBuilder();
                sb4.append("");
                sb4.append(bluetoothdevmanager.currentGameName);
                mname2.setText((CharSequence) sb4.toString());
                if (bluetoothdevmanager.isFormPresetList) {
                    this.immenu.setVisibility(View.VISIBLE);
                    if (CommonUtils.isStringValid(this.mDBManager.queryByGame(bluetoothdevmanager.mOfficialGamePreset.getAppName(), newSetupdialog.deviceType).getPresetPath())) {
                        for (int k = 0; k < newSetupdialog.length; ++k) {
                            newSetupdialog.mdatasaver[k] = new DataSaverM();
                            newSetupdialog.mdatasaver[k].property = -1;
                            newSetupdialog.mdatasaver[k].name = "-1";
                            newSetupdialog.mdatasaver[k].x = -1;
                            newSetupdialog.mdatasaver[k].y = -1;
                            newSetupdialog.mdatasaver[k].joystick = "-1";
                            newSetupdialog.mdatasaver[k].rumble = "-1";
                            newSetupdialog.mdatasaver[k].whichmoto = "-1";
                            newSetupdialog.mdatasaver[k].ms = "-1";
                        }
                        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                            final float screenwp2 = bluetoothdevmanager.screenwp;
                            bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                            bluetoothdevmanager.screenhp = screenwp2;
                        }
                        final byte[] bytes = ChangeDataUtil.getBytes(this.mDBManager.queryByGame(bluetoothdevmanager.mOfficialGamePreset.getAppName(), newSetupdialog.deviceType).getPresetPath());
                        if (bytes == null) {
                            return;
                        }
                        newSetupdialog.mousespeed = bytes[13];
                        newSetupdialog.mdatasaver = AnalyseSpDataUtil.initMdatasaverByByteArray(bytes, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp);
                        this.displaysetup();
                        if (FloatWindowService.myHandle != null) {
                            FloatWindowService.myHandle.sendEmptyMessage(0);
                        }
                        newSetupdialog.isMapInfosSuccess = true;
                    } else {
                        this.clearkeyinfor();
                        final TextView mname3 = this.mname;
                        final StringBuilder sb5 = new StringBuilder();
                        sb5.append("");
                        sb5.append(bluetoothdevmanager.currentGameName);
                        mname3.setText((CharSequence) sb5.toString());
                        this.presetupnormal();
                        this.displaycircle();
                        if (FloatWindowService.myHandle != null) {
                            FloatWindowService.myHandle.sendEmptyMessage(0);
                        }
                        newSetupdialog.isMapInfosSuccess = true;
                    }
                } else {
                    newSetupdialog.myhandler.sendEmptyMessageDelayed(201859, 300L);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtil.get(newSetupdialog.myhandler, HttpUrlConfig.getGetDeviceModelUrl(0), 201850, 201851);
                        newSetupdialog.this.getUnsupportedResolutionListNum = 0;
                        OkHttpUtil.get(newSetupdialog.myhandler, "http://shootingplus.com.cn/shootingplus/open/games/getNoResolutionList?platform=1", 201901, 201902);
                    }
                }).start();
            } catch (Exception ex) {
                continue;
            }
            break;
        }
    }

    public void onItemClick(final int n) {
        if (n == 0) {
            if (bluetoothdevmanager.mConnectionState != 0) {
                new presetCloudSyncDialog(this.mcontext).showAtLocation((View) this.main, 17, 0, 0);
            } else {
                new ToastDialog(this.mcontext, this.mcontext.getResources().getString(R.string.update_fw_tip8)).show();
            }
        }
        if (n == 1) {
            new getSharePresetDialog(this.mcontext).showAtLocation((View) this.main, 17, 0, 0);
        }
        if (n == 2) {
            newSetupdialog.myhandler.sendEmptyMessage(201811);
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 500L);
        }
        if (n == 3) {
            newSetupdialog.myhandler.sendEmptyMessage(201812);
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 500L);
        }
        if (n == 4) {
            newSetupdialog.myhandler.sendEmptyMessage(201813);
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 500L);
        }
        if (n == 5) {
            newSetupdialog.myhandler.sendEmptyMessage(201814);
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 500L);
        }
        if (n == 6) {
            newSetupdialog.myhandler.sendEmptyMessage(201815);
            newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 500L);
        }
    }

    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            bluetoothdevmanager.mNewSetupdialog.cancel();
            bluetoothdevmanager.mNewSetupdialog = null;
            return false;
        }
        return super.onKeyDown(n, keyEvent);
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.dragstart == 1 || this.dragstart == 2) {
            switch (motionEvent.getAction()) {
                case 2: {
                    if (!this.isLongClickModule) {
                        if (System.currentTimeMillis() - this.lastClickTime < 300L && !this.isLongPressed((float) this.lastX, (float) this.lastY, motionEvent.getRawX(), motionEvent.getRawY(), motionEvent.getDownTime(), motionEvent.getEventTime(), 150L)) {
                            this.isLongClickModule = false;
                        } else {
                            this.isLongClickModule = true;
                        }
                    }
                    if (this.isLongClickModule && !this.isLongClicking) {
                        this.isLongClicking = true;
                    }
                    if (!this.isLongClicking) {
                        break;
                    }
                    if (!view.equals(this.immenu)) {
                        this.setupzoompic(this.currentposition);
                        final int n = (int) motionEvent.getRawX() - this.lastX;
                        final int n2 = (int) motionEvent.getRawY() - this.lastY;
                        view.layout(view.getLeft() + n, view.getTop() + n2, view.getRight() + n, view.getBottom() + n2);
                        this.lastX = (int) motionEvent.getRawX();
                        this.lastY = (int) motionEvent.getRawY();
                        break;
                    }
                    final int n3 = (int) motionEvent.getRawX() - this.lastX;
                    final int n4 = (int) motionEvent.getRawY() - this.lastY;
                    final int n5 = view.getLeft() + n3;
                    int n6 = view.getBottom() + n4;
                    int n7 = view.getRight() + n3;
                    final int n8 = view.getTop() + n4;
                    int n9;
                    if ((n9 = n5) < 0) {
                        n7 = view.getWidth() + 0;
                        n9 = 0;
                    }
                    int n10;
                    if ((n10 = n8) < 0) {
                        n6 = view.getHeight() + 0;
                        n10 = 0;
                    }
                    int n11 = n9;
                    int getscreenwh;
                    if ((getscreenwh = n7) > this.getscreenwh(0)) {
                        getscreenwh = this.getscreenwh(0);
                        n11 = getscreenwh - view.getWidth();
                    }
                    int getscreenwh2;
                    if ((getscreenwh2 = n6) > this.getscreenwh(1)) {
                        getscreenwh2 = this.getscreenwh(1);
                        n10 = getscreenwh2 - view.getHeight();
                    }
                    view.layout(n11, n10, getscreenwh, getscreenwh2);
                    this.lastX = (int) motionEvent.getX();
                    this.lastY = (int) motionEvent.getY();
                    break;
                }
                case 1: {
                    final int n12 = (int) motionEvent.getRawX();
                    final int n13 = (int) motionEvent.getRawY();
                    this.dragstart = 0;
                    if (view.equals(this.immenu)) {
                        if (!this.isLongClicking) {
                            final float n14 = (float) n12;
                            final float n15 = (float) n13;
                            this.inrectbtn(n14, n15);
                            if (1 == this.inrectbtn(n14, n15)) {
                                this.showSpinWindow();
                            }
                            if (2 == this.inrectbtn(n14, n15)) {
                                this.clearkeyinfor();
                                final TextView mname = this.mname;
                                final StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(bluetoothdevmanager.currentGameName);
                                mname.setText((CharSequence) sb.toString());
                                this.presetupnormal();
                                this.displaycircle();
                            }
                            if (3 == this.inrectbtn(n14, n15)) {
                                if (bluetoothdevmanager.isEpstMode) {
                                    if (this.mnewSaveDialog1 == null) {
                                        (this.mnewSaveDialog1 = new newSaveDialog1(this.mcontext)).show();
                                    } else if (!this.mnewSaveDialog1.isShowing()) {
                                        this.mnewSaveDialog1.show();
                                    }
                                } else {
                                    new sunyesMaxSavedialoggwindow(this.mcontext).showAtLocation((View) this.main, 17, 0, 0);
                                }
                            }
                            if (4 == this.inrectbtn(n14, n15)) {
                                newSetupdialog.isTest = false;
                                BlueCmdManager.setDeviceWorkMode((byte) 0);
                                bluetoothdevmanager.mNewSetupdialog.cancel();
                                bluetoothdevmanager.mNewSetupdialog = null;
                            }
                            this.inrectbtn(n14, n15);
                            break;
                        }
                        if (this.isLongClickModule) {
                            this.isLongClickModule = false;
                            this.isLongClicking = false;
                        }
                        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                        layoutParams.leftMargin = view.getLeft();
                        layoutParams.topMargin = view.getTop();
                        layoutParams.width = view.getWidth();
                        layoutParams.height = view.getHeight();
                        layoutParams.setMargins(view.getLeft(), view.getTop(), 0, 0);
                        view.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
                        break;
                    } else if (!this.isLongClicking) {
                        final int id = view.getId();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("-------id = ");
                        sb2.append(id);
                        Log.i("newSetupdialog", sb2.toString());
                        if (id == 55) {
                            this.getaixSetupDialog(view);
                        }
                        if (id == 56) {
                            if (this.mViewSetupDialogwindow != null) {
                                if (this.mViewSetupDialogwindow.isShowing()) {
                                    this.mViewSetupDialogwindow.dismiss();
                                }
                                this.mViewSetupDialogwindow = null;
                            }
                            (this.mViewSetupDialogwindow = new viewSetupDialogwindow(this.mcontext)).showAtLocation((View) this.main, 17, 0, 0);
                        }
                        if (id == 55 || id == 56 || id == 204 || id == 209 || id == 210 || id == 211 || id == 98) {
                            break;
                        }
                        if (bluetoothdevmanager.mapVersion == 2) {
                            if (this.mSetupdialogwindow != null) {
                                if (this.mSetupdialogwindow.isShowing()) {
                                    this.mSetupdialogwindow.dismiss();
                                }
                                this.mSetupdialogwindow = null;
                            }
                            (this.mSetupdialogwindow = new setupdialogwindow(this.mcontext, view)).showAtLocation((View) this.main, 17, 0, 0);
                            break;
                        }
                        if (bluetoothdevmanager.mapVersion == 1) {
                            if (this.mSetupdialogwindow2 != null) {
                                if (this.mSetupdialogwindow2.isShowing()) {
                                    this.mSetupdialogwindow2.dismiss();
                                }
                                this.mSetupdialogwindow2 = null;
                            }
                            (this.mSetupdialogwindow2 = new setupdialogwindow2(this.mcontext, view)).showAtLocation((View) this.main, 17, 0, 0);
                            break;
                        }
                        break;
                    } else {
                        if (this.isLongClickModule) {
                            this.isLongClickModule = false;
                            this.isLongClicking = false;
                        }
                        final int[] array = new int[2];
                        view.getLocationOnScreen(array);
                        if (5 == this.inrectbtn((float) (array[0] + view.getWidth() / 2), (float) (array[1] + view.getHeight() / 2)) && this.currentposition != 55 && this.currentposition != 56 && this.currentposition != 104 && this.currentposition != 106 && this.currentposition != 204 && this.currentposition != 209 && this.currentposition != 210 && this.currentposition != 211) {
                            this.reversezoompic(this.currentposition);
                            final RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                            layoutParams2.leftMargin = view.getLeft();
                            layoutParams2.topMargin = view.getTop();
                            layoutParams2.width = view.getWidth();
                            layoutParams2.height = view.getHeight();
                            layoutParams2.setMargins(view.getLeft(), view.getTop(), 0, 0);
                            view.setLayoutParams((ViewGroup.LayoutParams) layoutParams2);
                            this.main.removeView(view);
                            if (newSetupdialog.mdatasaver[this.currentposition].property == 1) {
                                if (this.currentposition < 137) {
                                    newSetupdialog.mdatasaver[this.currentposition + 30].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].ms = "-1";
                                    this.imageView[this.currentposition + 30].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition + 30]);
                                } else {
                                    newSetupdialog.mdatasaver[this.currentposition - 30].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].ms = "-1";
                                    this.imageView[this.currentposition - 30].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition - 30]);
                                }
                            }
                            if (newSetupdialog.mdatasaver[this.currentposition].property == 4) {
                                if (this.currentposition > 230 && this.currentposition < 261) {
                                    newSetupdialog.mdatasaver[this.currentposition + 30].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].ms = "-1";
                                    this.imageView[this.currentposition + 30].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition + 30]);
                                    newSetupdialog.mdatasaver[this.currentposition + 60].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 60].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 60].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 60].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 60].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 60].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 60].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 60].ms = "-1";
                                    this.imageView[this.currentposition + 60].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition + 60]);
                                }
                                if (this.currentposition > 260 && this.currentposition < 291) {
                                    newSetupdialog.mdatasaver[this.currentposition - 30].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition - 30].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition - 30].ms = "-1";
                                    this.imageView[this.currentposition - 30].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition - 30]);
                                    newSetupdialog.mdatasaver[this.currentposition + 30].property = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].name = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].x = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].y = -1;
                                    newSetupdialog.mdatasaver[this.currentposition + 30].rumble = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].whichmoto = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].joystick = "-1";
                                    newSetupdialog.mdatasaver[this.currentposition + 30].ms = "-1";
                                    this.imageView[this.currentposition + 30].setId(-1);
                                    this.main.removeView((View) this.imageView[this.currentposition + 30]);
                                }
                            }
                            newSetupdialog.mdatasaver[this.currentposition].property = -1;
                            newSetupdialog.mdatasaver[this.currentposition].name = "-1";
                            newSetupdialog.mdatasaver[this.currentposition].x = -1;
                            newSetupdialog.mdatasaver[this.currentposition].y = -1;
                            newSetupdialog.mdatasaver[this.currentposition].rumble = "-1";
                            newSetupdialog.mdatasaver[this.currentposition].whichmoto = "-1";
                            newSetupdialog.mdatasaver[this.currentposition].joystick = "-1";
                            newSetupdialog.mdatasaver[this.currentposition].ms = "-1";
                            this.imageView[this.currentposition].setId(-1);
                            this.setContentView((View) this.main);
                            break;
                        }
                        this.reversezoompic(this.currentposition);
                        final RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
                        layoutParams3.leftMargin = view.getLeft();
                        layoutParams3.topMargin = view.getTop();
                        if (view.getWidth() != view.getHeight()) {
                            if (view.getHeight() > view.getWidth()) {
                                layoutParams3.width = view.getHeight();
                                layoutParams3.height = view.getHeight();
                            } else {
                                layoutParams3.width = view.getWidth();
                                layoutParams3.height = view.getWidth();
                            }
                        } else {
                            layoutParams3.width = view.getWidth();
                            layoutParams3.height = view.getHeight();
                        }
                        layoutParams3.setMargins(view.getLeft(), view.getTop(), 0, 0);
                        view.setLayoutParams((ViewGroup.LayoutParams) layoutParams3);
                        final int[] array2 = new int[2];
                        final int n16 = view.getWidth() / 2;
                        final int n17 = view.getHeight() / 2;
                        view.getLocationOnScreen(array2);
                        newSetupdialog.mdatasaver[this.currentposition].x = array2[0] + n16;
                        newSetupdialog.mdatasaver[this.currentposition].y = array2[1] + n17;
                        break;
                    }
                }
                case 0: {
                    this.lastX = (int) motionEvent.getRawX();
                    this.lastY = (int) motionEvent.getRawY();
                    this.lastClickTime = System.currentTimeMillis();
                    break;
                }
            }
        }
        return false;
    }

    public void onWindowFocusChanged(final boolean b) {
        if (b) {
            this.hideBottomUIMenu();
        }
        super.onWindowFocusChanged(b);
    }

    public void redisplaymap(final int n, int n2) {
        if (n != 204 && n != 209 && n != 210 && n != 211) {
            final ViewGroup viewGroup = (ViewGroup) this.imageView[n].getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            n2 = this.getpicturewh(0) / 2;
            final int n3 = this.getpicturewh(1) / 2;
            final int n4 = this.imageView[n].getWidth() / 2;
            int n5 = this.imageView[n].getHeight() / 2;
            if (n4 != 0) {
                n2 = n4;
            }
            if (n5 == 0) {
                n5 = n3;
            }
            this.imageView[n].setX((float) (newSetupdialog.mdatasaver[n].x - n2));
            this.imageView[n].setY((float) (newSetupdialog.mdatasaver[n].y - n5));
            this.imageView[n].setId(n);
            this.main.addView((View) this.imageView[n]);
            this.currentposition = n;
            this.setContentView((View) this.main);
        }
    }

    void reversezoompic(final int n) {
        this.imageView[n] = MyApplication.getmSpSetupImageSRCUtils().updateImagePic(this.imageView[n], n);
    }

    void setupzoompic(final int n) {
        this.imageView[n] = MyApplication.getmSpSetupImageSRCUtils().setupzoompic(this.imageView[n], n);
    }

    public PopupWindow showTipPopupWindow(final View view, final Context context, final ArrayList<String> list, final AdapterView.OnItemClickListener adapterView$OnItemClickListener, final View.OnClickListener view$OnClickListener) {
        final View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.popuw_content_top_arrow_layout, (ViewGroup) null);
        inflate.measure(0, 0);
        final ListView listView = (ListView) inflate.findViewById(R.id.tip_lv);
        listView.setAdapter(new PopuwListViewAdapter(context, list));
        final PopupWindow popupWindow = new PopupWindow(inflate, inflate.getMeasuredWidth(), inflate.getMeasuredHeight(), false);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                popupWindow.dismiss();
                adapterView$OnItemClickListener.onItemClick((AdapterView) adapterView, view, n, n2);
            }
        });
        inflate.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public void onClick(final View view) {
                view$OnClickListener.onClick(view);
            }
        });
        inflate.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) this);
            }
        });
        popupWindow.setBackgroundDrawable((Drawable) new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setTouchInterceptor((View.OnTouchListener) new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
        return popupWindow;
    }

    public PopupWindow showTipPopupWindow2(final View view, final Context context, final ArrayList<OfficialGame> list, final AdapterView.OnItemClickListener adapterView$OnItemClickListener, final View.OnClickListener view$OnClickListener) {
        final View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.popuw_content_top_arrow_layout, (ViewGroup) null);
        inflate.measure(0, 0);
        final ListView listView = (ListView) inflate.findViewById(R.id.tip_lv);
        listView.setAdapter(new PopuwListViewAdapter2(context, list));
        final PopupWindow popupWindow = new PopupWindow(inflate, inflate.getMeasuredWidth(), inflate.getMeasuredHeight(), false);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                popupWindow.dismiss();
                adapterView$OnItemClickListener.onItemClick((AdapterView) adapterView, view, n, n2);
            }
        });
        inflate.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public void onClick(final View view) {
                view$OnClickListener.onClick(view);
            }
        });
        inflate.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) this);
            }
        });
        popupWindow.setBackgroundDrawable((Drawable) new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setTouchInterceptor((View.OnTouchListener) new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
        return popupWindow;
    }

    public PopupWindow showTipPopupWindow3(final View view, final Context context, final ArrayList<SunyesMaxGamePreset> list, final AdapterView.OnItemClickListener adapterView$OnItemClickListener, final View.OnClickListener view$OnClickListener) {
        final View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.popuw_content_top_arrow_layout, (ViewGroup) null);
        inflate.measure(0, 0);
        final ListView listView = (ListView) inflate.findViewById(R.id.tip_lv);
        listView.setAdapter(new PopuwListViewAdapter3(context, list));
        final PopupWindow popupWindow = new PopupWindow(inflate, inflate.getMeasuredWidth(), inflate.getMeasuredHeight(), false);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                popupWindow.dismiss();
                adapterView$OnItemClickListener.onItemClick((AdapterView) adapterView, view, n, n2);
            }
        });
        inflate.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public void onClick(final View view) {
                view$OnClickListener.onClick(view);
            }
        });
        inflate.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) this);
            }
        });
        popupWindow.setBackgroundDrawable((Drawable) new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setTouchInterceptor((View.OnTouchListener) new View.OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                return false;
            }
        });
        popupWindow.showAsDropDown(view);
        return popupWindow;
    }

    void startDrag(final View view) {
        if (!view.equals(this.immenu)) {
            this.currentposition = view.getId();
            this.dragstart = 1;
        }
        if (view.equals(this.immenu)) {
            this.dragstart = 2;
        }
    }

    @SuppressLint({"HandlerLeak"})
    void timerinit() {
        newSetupdialog.myhandler = new Handler() {
            public void handleMessage(final Message message) {
                if (message.what == 201856) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip47)).show();
                }
                if (message.what == 201857) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip48)).show();
                }
                if (message.what == 201854) {
                    if (CommonUtils.isStringValid(newSetupdialog.uploadOfficialGameFilePath)) {
                        final File file = new File(newSetupdialog.uploadOfficialGameFilePath);
                        if (file != null) {
                            file.delete();
                        }
                    }
                    try {
                        final String string = ((JSONObject) message.obj).getString("url");
                        if (CommonUtils.isStringValid(string)) {
                            HttpUtil.addOfficialGamePresetPost(newSetupdialog.myhandler, 201856, 201857, "http://shootingplus.com.cn/shootingplus/open/games/addGamePreset", newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.gameId, newSetupdialog.resolution, string);
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201855);
                        }
                    } catch (JSONException ex2) {
                        newSetupdialog.myhandler.sendEmptyMessage(201855);
                    }
                }
                if (message.what == 201855) {
                    if (CommonUtils.isStringValid(newSetupdialog.uploadOfficialGameFilePath)) {
                        final File file2 = new File(newSetupdialog.uploadOfficialGameFilePath);
                        if (file2 != null) {
                            file2.delete();
                        }
                    }
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip48)).show();
                }
                if (message.what == 201852) {
                    try {
                        final JSONObject jsonObject = (JSONObject) message.obj;
                        if (jsonObject.getInt("code") == 1000) {
                            final JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (jsonArray.length() > 0) {
                                newSetupdialog.this.mOfficialGameList.clear();
                                for (int i = 0; i < jsonArray.length(); ++i) {
                                    newSetupdialog.this.mOfficialGameList.add(new OfficialGame(jsonArray.getJSONObject(i)));
                                }
                            } else {
                                newSetupdialog.myhandler.sendEmptyMessage(201853);
                            }
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201853);
                        }
                    } catch (Exception ex3) {
                        newSetupdialog.myhandler.sendEmptyMessage(201853);
                    }
                }
                if (message.what == 201853) {
                    newSetupdialog.this.mOfficialGameList.clear();
                }
                if (message.what == 201902) {
                    newSetupdialog.this.mUnsupportedResolutionList.clear();
                    if (newSetupdialog.this.getUnsupportedResolutionListNum < 6) {
                        OkHttpUtil.get(newSetupdialog.myhandler, "http://shootingplus.com.cn/shootingplus/open/games/getNoResolutionList?platform=1", 201901, 201902);
                    }
                    newSetupdialog.this.getUnsupportedResolutionListNum++;
                }
                if (message.what == 201901) {
                    int n;
                    if (bluetoothdevmanager.screenhp > bluetoothdevmanager.screenwp) {
                        n = (int) bluetoothdevmanager.screenhp;
                    } else {
                        n = (int) bluetoothdevmanager.screenwp;
                    }
                    try {
                        final JSONObject jsonObject2 = (JSONObject) message.obj;
                        if (jsonObject2.getInt("code") == 1000) {
                            final JSONArray jsonArray2 = jsonObject2.getJSONArray("data");
                            if (jsonArray2.length() > 0) {
                                newSetupdialog.this.mUnsupportedResolutionList.clear();
                                for (int j = 0; j < jsonArray2.length(); ++j) {
                                    if (CommonUtils.isInterval(jsonArray2.getJSONObject(j).getString("resolution"), n - 40, n + 40) && !CommonUtils.isStringInList(jsonArray2.getJSONObject(j).getString("resolution"), newSetupdialog.this.mUnsupportedResolutionList)) {
                                        newSetupdialog.this.mUnsupportedResolutionList.add(jsonArray2.getJSONObject(j).getString("resolution"));
                                    }
                                }
                            } else {
                                newSetupdialog.myhandler.sendEmptyMessage(201902);
                            }
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201902);
                        }
                    } catch (Exception ex4) {
                        newSetupdialog.myhandler.sendEmptyMessage(201902);
                    }
                }
                if (message.what == 201850) {
                    try {
                        final JSONObject jsonObject3 = (JSONObject) message.obj;
                        if (jsonObject3.getInt("code") == 1000) {
                            final JSONArray jsonArray3 = jsonObject3.getJSONArray("data");
                            if (jsonArray3.length() > 0) {
                                newSetupdialog.deviceList.clear();
                                for (int k = 0; k < jsonArray3.length(); ++k) {
                                    newSetupdialog.deviceList.add(new Device(jsonArray3.getJSONObject(k).getString("model"), false));
                                }
                                HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201852, 201853);
                            } else {
                                newSetupdialog.myhandler.sendEmptyMessage(201851);
                            }
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201851);
                        }
                    } catch (Exception ex5) {
                        newSetupdialog.myhandler.sendEmptyMessage(201851);
                    }
                }
                if (message.what == 201851) {
                    newSetupdialog.deviceList.clear();
                    newSetupdialog.deviceList.add(new Device(FirstPageActivity.projectName, false));
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201852, 201853);
                }
                if (message.what == 201849) {
                    if (newSetupdialog.reSendNum <= 2) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("----\u5f00\u59cb\u8ba1\u6570 reSendNum = ");
                        sb.append(newSetupdialog.reSendNum);
                        MyLog.i("sync_sync", sb.toString());
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 2500L);
                        newSetupdialog.reSendNum = newSetupdialog.reSendNum;
                        newSetupdialog.this.sendSyncDataToDevice(newSetupdialog.syncCurrentLocation);
                    } else {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("----\u53d1\u9001");
                        sb2.append(newSetupdialog.syncCurrentLocation);
                        sb2.append("\u9884\u8bbe\u5230\u8bbe\u5907\u8d85\u65f6\u5931\u8d25\uff01");
                        MyLog.i("sync_sync", sb2.toString());
                        newSetupdialog.reSendNum = 0;
                        if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f1")) {
                            newSetupdialog.isF1 = false;
                        }
                        if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f2")) {
                            newSetupdialog.isF2 = false;
                        }
                        if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f3")) {
                            newSetupdialog.isF3 = false;
                        }
                        if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f4")) {
                            newSetupdialog.isF4 = false;
                        }
                        if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f5")) {
                            newSetupdialog.isF5 = false;
                        }
                        newSetupdialog.myhandler.sendEmptyMessage(201848);
                    }
                }
                if (message.what == 201848) {
                    if (newSetupdialog.reSendNum <= 2 && newSetupdialog.reSendNum != 0) {
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append("----\u505c\u6b62\u8ba1\u6570 reSendNum = ");
                        sb3.append(newSetupdialog.reSendNum);
                        MyLog.i("sync_sync", sb3.toString());
                        newSetupdialog.myhandler.removeMessages(201849);
                    }
                    newSetupdialog.reSendNum = 0;
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("----syncCurrentLocation = ");
                    sb4.append(newSetupdialog.syncCurrentLocation);
                    MyLog.i("sync_sync", sb4.toString());
                    if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f1") && newSetupdialog.isF2) {
                        newSetupdialog.myhandler.sendEmptyMessage(201812);
                        newSetupdialog.syncCurrentLocation = "f2";
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
                    } else if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f2") && newSetupdialog.isF3) {
                        newSetupdialog.myhandler.sendEmptyMessage(201813);
                        newSetupdialog.syncCurrentLocation = "f3";
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
                    } else if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f3") && newSetupdialog.isF4) {
                        newSetupdialog.myhandler.sendEmptyMessage(201814);
                        newSetupdialog.syncCurrentLocation = "f4";
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
                    } else if (CommonUtils.isStringValid(newSetupdialog.syncCurrentLocation) && newSetupdialog.syncCurrentLocation.equalsIgnoreCase("f4") && newSetupdialog.isF5) {
                        newSetupdialog.myhandler.sendEmptyMessage(201815);
                        newSetupdialog.syncCurrentLocation = "f5";
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201849, 200L);
                    } else {
                        newSetupdialog.isSyncPresetNow = false;
                        if (newSetupdialog.isF1 && newSetupdialog.isF2 && newSetupdialog.isF3 && newSetupdialog.isF4 && newSetupdialog.isF5) {
                            newSetupdialog.syncToastStr = newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip39);
                        } else if (!newSetupdialog.isF1 && !newSetupdialog.isF2 && !newSetupdialog.isF3 && !newSetupdialog.isF4 && !newSetupdialog.isF5) {
                            newSetupdialog.syncToastStr = newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip38);
                        } else {
                            newSetupdialog.syncToastStr = newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip40);
                            if (newSetupdialog.isF1) {
                                final StringBuilder sb5 = new StringBuilder();
                                sb5.append(newSetupdialog.syncToastStr);
                                sb5.append("Ctrl+F1");
                                newSetupdialog.syncToastStr = sb5.toString();
                            }
                            if (newSetupdialog.isF2) {
                                if (newSetupdialog.isF1) {
                                    final StringBuilder sb6 = new StringBuilder();
                                    sb6.append(newSetupdialog.syncToastStr);
                                    sb6.append("\u3001Ctrl+F2");
                                    newSetupdialog.syncToastStr = sb6.toString();
                                } else {
                                    final StringBuilder sb7 = new StringBuilder();
                                    sb7.append(newSetupdialog.syncToastStr);
                                    sb7.append("Ctrl+F2");
                                    newSetupdialog.syncToastStr = sb7.toString();
                                }
                            }
                            if (newSetupdialog.isF3) {
                                if (!newSetupdialog.isF1 && !newSetupdialog.isF2) {
                                    final StringBuilder sb8 = new StringBuilder();
                                    sb8.append(newSetupdialog.syncToastStr);
                                    sb8.append("Ctrl+F3");
                                    newSetupdialog.syncToastStr = sb8.toString();
                                } else {
                                    final StringBuilder sb9 = new StringBuilder();
                                    sb9.append(newSetupdialog.syncToastStr);
                                    sb9.append("\u3001Ctrl+F3");
                                    newSetupdialog.syncToastStr = sb9.toString();
                                }
                            }
                            if (newSetupdialog.isF4) {
                                if (!newSetupdialog.isF1 && !newSetupdialog.isF2 && !newSetupdialog.isF3) {
                                    final StringBuilder sb10 = new StringBuilder();
                                    sb10.append(newSetupdialog.syncToastStr);
                                    sb10.append("Ctrl+F4");
                                    newSetupdialog.syncToastStr = sb10.toString();
                                } else {
                                    final StringBuilder sb11 = new StringBuilder();
                                    sb11.append(newSetupdialog.syncToastStr);
                                    sb11.append("\u3001Ctrl+F4");
                                    newSetupdialog.syncToastStr = sb11.toString();
                                }
                            }
                            if (newSetupdialog.isF5) {
                                if (!newSetupdialog.isF1 && !newSetupdialog.isF2 && !newSetupdialog.isF3 && !newSetupdialog.isF4) {
                                    final StringBuilder sb12 = new StringBuilder();
                                    sb12.append(newSetupdialog.syncToastStr);
                                    sb12.append("Ctrl+F5");
                                    newSetupdialog.syncToastStr = sb12.toString();
                                } else {
                                    final StringBuilder sb13 = new StringBuilder();
                                    sb13.append(newSetupdialog.syncToastStr);
                                    sb13.append("\u3001Ctrl+F5");
                                    newSetupdialog.syncToastStr = sb13.toString();
                                }
                            }
                        }
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.syncToastStr).show();
                        newSetupdialog.myhandler.sendEmptyMessage(201811);
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201805, 1000L);
                    }
                }
                if (message.what == 201844) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F5\u9884\u8bbeURL\u6210\u529f\uff0c\u5f00\u59cb\u4e0b\u8f7dF5\u9884\u8bbe");
                    newSetupdialog.this.downloadOfficialFile((JSONObject) message.obj, 201845, 201846, 201847, "f5");
                }
                if (message.what == 201846) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF5\u9884\u8bbe\u6210\u529f\uff0c\u5f00\u59cb\u53d1\u9001\u9884\u8bbe\u5230\u8bbe\u5907");
                    newSetupdialog.isF5 = true;
                    newSetupdialog.this.syncCloudFinish(newSetupdialog.this.mcontext, newSetupdialog.isF1, newSetupdialog.isF2, newSetupdialog.isF3, newSetupdialog.isF4, newSetupdialog.isF5);
                }
                if (message.what == 201847) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF5\u9884\u8bbe\u5931\u8d25\uff0c\u5f00\u59cb\u53d1\u9001\u9884\u8bbe\u5230\u8bbe\u5907");
                    newSetupdialog.isF5 = false;
                    newSetupdialog.this.syncCloudFinish(newSetupdialog.this.mcontext, newSetupdialog.isF1, newSetupdialog.isF2, newSetupdialog.isF3, newSetupdialog.isF4, newSetupdialog.isF5);
                }
                if (message.what == 201845) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F5\u9884\u8bbeURL\u5931\u8d25\uff0c\u5f00\u59cb\u53d1\u9001\u9884\u8bbe\u5230\u8bbe\u5907");
                    newSetupdialog.isF5 = false;
                    newSetupdialog.this.syncCloudFinish(newSetupdialog.this.mcontext, newSetupdialog.isF1, newSetupdialog.isF2, newSetupdialog.isF3, newSetupdialog.isF4, newSetupdialog.isF5);
                }
                if (message.what == 201840) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F4\u9884\u8bbeURL\u6210\u529f\uff0c\u5f00\u59cb\u4e0b\u8f7dF4\u9884\u8bbe");
                    newSetupdialog.this.downloadOfficialFile((JSONObject) message.obj, 201841, 201842, 201843, "f4");
                }
                if (message.what == 201842) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF4\u9884\u8bbe\u6210\u529f\uff0c\u5f00\u59cb\u83b7\u53d6F5\u9884\u8bbeURL");
                    newSetupdialog.isF4 = true;
                    newSetupdialog.location = "F5";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201844, 201845);
                }
                if (message.what == 201843) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF4\u9884\u8bbe\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F5\u9884\u8bbeURL");
                    newSetupdialog.isF4 = false;
                    newSetupdialog.location = "F5";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201844, 201845);
                }
                if (message.what == 201841) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F4\u9884\u8bbeURL\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F5\u9884\u8bbeURL");
                    newSetupdialog.isF5 = false;
                    newSetupdialog.location = "F5";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201844, 201845);
                }
                if (message.what == 201836) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F3\u9884\u8bbeURL\u6210\u529f\uff0c\u5f00\u59cb\u4e0b\u8f7dF3\u9884\u8bbe");
                    newSetupdialog.this.downloadOfficialFile((JSONObject) message.obj, 201837, 201838, 201839, "f3");
                }
                if (message.what == 201838) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF3\u9884\u8bbe\u6210\u529f\uff0c\u5f00\u59cb\u83b7\u53d6F4\u9884\u8bbeURL");
                    newSetupdialog.isF3 = true;
                    newSetupdialog.location = "F4";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201840, 201841);
                }
                if (message.what == 201839) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF3\u9884\u8bbe\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F4\u9884\u8bbeURL");
                    newSetupdialog.isF3 = false;
                    newSetupdialog.location = "F4";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201840, 201841);
                }
                if (message.what == 201837) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F3\u9884\u8bbeURL\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F4\u9884\u8bbeURL");
                    newSetupdialog.isF3 = false;
                    newSetupdialog.location = "F4";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201840, 201841);
                }
                if (message.what == 201832) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F2\u9884\u8bbeURL\u6210\u529f\uff0c\u5f00\u59cb\u4e0b\u8f7dF2\u9884\u8bbe");
                    newSetupdialog.this.downloadOfficialFile((JSONObject) message.obj, 201833, 201834, 201835, "f2");
                }
                if (message.what == 201834) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF2\u9884\u8bbe\u6210\u529f\uff0c\u5f00\u59cb\u83b7\u53d6F3\u9884\u8bbeURL");
                    newSetupdialog.isF2 = true;
                    newSetupdialog.location = "F3";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201836, 201837);
                }
                if (message.what == 201835) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF2\u9884\u8bbe\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F3\u9884\u8bbeURL");
                    newSetupdialog.isF2 = false;
                    newSetupdialog.location = "F3";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201836, 201837);
                }
                if (message.what == 201833) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F2\u9884\u8bbeURL\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F3\u9884\u8bbeURL");
                    newSetupdialog.isF2 = false;
                    newSetupdialog.location = "F3";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201836, 201837);
                }
                if (message.what == 201828) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F1\u9884\u8bbeURL\u6210\u529f\uff0c\u5f00\u59cb\u4e0b\u8f7dF1\u9884\u8bbe");
                    newSetupdialog.this.downloadOfficialFile((JSONObject) message.obj, 201829, 201830, 201831, "f1");
                }
                if (message.what == 201830) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF1\u9884\u8bbe\u6210\u529f\uff0c\u5f00\u59cb\u83b7\u53d6F2\u9884\u8bbeURL");
                    newSetupdialog.isF1 = true;
                    newSetupdialog.location = "F2";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201832, 201833);
                }
                if (message.what == 201831) {
                    MyLog.i("sync_tag", "----------\u4e0b\u8f7dF1\u9884\u8bbe\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F2\u9884\u8bbeURL");
                    newSetupdialog.isF1 = false;
                    newSetupdialog.location = "F2";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201832, 201833);
                }
                if (message.what == 201829) {
                    MyLog.i("sync_tag", "----------\u83b7\u53d6F1\u9884\u8bbeURL\u5931\u8d25\uff0c\u5f00\u59cb\u83b7\u53d6F2\u9884\u8bbeURL");
                    newSetupdialog.isF1 = false;
                    newSetupdialog.location = "F2";
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201832, 201833);
                }
                if (message.what == 201826) {
                    if (newSetupdialog.modelNum == newSetupdialog.modelList.size() - 1) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip33)).show();
                    } else {
                        newSetupdialog.uploadOfficialFilePath = null;
                        final StringBuilder sb14 = new StringBuilder();
                        sb14.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb14.append("/");
                        sb14.append(newSetupdialog.location);
                        sb14.append(".ini");
                        newSetupdialog.uploadOfficialFilePath = sb14.toString();
                        final StringBuilder sb15 = new StringBuilder();
                        sb15.append(newSetupdialog.location);
                        sb15.append(".ini");
                        final String string2 = sb15.toString();
                        ++newSetupdialog.modelNum;
                        newSetupdialog.model = newSetupdialog.modelList.get(newSetupdialog.modelNum);
                        newSetupdialog.this.profilesave(newSetupdialog.uploadOfficialFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201824, 201825, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadOfficialFilePath, string2);
                    }
                }
                if (message.what == 201827) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip34)).show();
                }
                if (message.what == 201824) {
                    if (CommonUtils.isStringValid(newSetupdialog.uploadOfficialFilePath)) {
                        final File file3 = new File(newSetupdialog.uploadOfficialFilePath);
                        if (file3 != null) {
                            file3.delete();
                        }
                    }
                    try {
                        newSetupdialog.downloadOfficialFileUrl = ((JSONObject) message.obj).getString("url");
                        final StringBuilder sb16 = new StringBuilder();
                        sb16.append("OfficialFileUrl--->");
                        sb16.append(newSetupdialog.downloadOfficialFileUrl);
                        Log.i("upload_file_tag", sb16.toString());
                        if (CommonUtils.isStringValid(newSetupdialog.downloadOfficialFileUrl)) {
                            HttpUtil.addOfficialPresetPost(newSetupdialog.myhandler, 201826, 201827, "http://shootingplus.com.cn/shootingplus/open/games/addOfficialPreset", newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution, newSetupdialog.gameId, newSetupdialog.downloadOfficialFileUrl);
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201825);
                        }
                    } catch (JSONException ex6) {
                        newSetupdialog.myhandler.sendEmptyMessage(201825);
                    }
                }
                if (message.what == 201825) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip34)).show();
                }
                if (message.what == 201822) {
                    try {
                        final JSONObject jsonObject4 = (JSONObject) message.obj;
                        if (jsonObject4.getInt("code") == 1000 && jsonObject4.getString("message").equalsIgnoreCase("success") && CommonUtils.isStringValid(jsonObject4.getString("url"))) {
                            newSetupdialog.downloadShareFilePath = null;
                            final StringBuilder sb17 = new StringBuilder();
                            sb17.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                            sb17.append("/qx.ini");
                            newSetupdialog.downloadShareFilePath = sb17.toString();
                            final File file4 = new File(newSetupdialog.downloadShareFilePath);
                            if (file4.exists()) {
                                file4.delete();
                            }
                            HttpUtil.downloadFile(newSetupdialog.myhandler, 201820, 201821, jsonObject4.getString("url"), newSetupdialog.downloadShareFilePath);
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201823);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        newSetupdialog.myhandler.sendEmptyMessage(201823);
                    }
                }
                if (message.what == 201823) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip24)).show();
                }
                if (message.what == 201820) {
                    try {
                        if (new File(newSetupdialog.downloadShareFilePath).exists()) {
                            newSetupdialog.this.listprofile();
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201821);
                        }
                    } catch (Resources.NotFoundException ex7) {
                        newSetupdialog.myhandler.sendEmptyMessage(201821);
                    }
                }
                if (message.what == 201821) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip24)).show();
                }
                if (message.what == 201818) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip16)).show();
                }
                if (message.what == 201819) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip17)).show();
                }
                if (message.what == 201816) {
                    if (CommonUtils.isStringValid(newSetupdialog.uploadFilePath)) {
                        final File file5 = new File(newSetupdialog.uploadFilePath);
                        if (file5 != null) {
                            file5.delete();
                        }
                    }
                    try {
                        final String string3 = ((JSONObject) message.obj).getString("url");
                        if (CommonUtils.isStringValid(string3) && CommonUtils.isStringValid(newSetupdialog.shareCode)) {
                            HttpUtil.HttpURLConnectionPost(newSetupdialog.myhandler, 201818, 201819, "http://shootingplus.com.cn/shootingplus/open/games/addSharePreset", newSetupdialog.shareCode, string3);
                        } else {
                            newSetupdialog.myhandler.sendEmptyMessage(201817);
                        }
                    } catch (JSONException ex8) {
                        newSetupdialog.myhandler.sendEmptyMessage(201817);
                    }
                }
                if (message.what == 201817) {
                    if (CommonUtils.isStringValid(newSetupdialog.uploadFilePath)) {
                        final File file6 = new File(newSetupdialog.uploadFilePath);
                        if (file6 != null) {
                            file6.delete();
                        }
                    }
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip17)).show();
                }
                if (message.what == 201805) {
                    if (newSetupdialog.mSyncdingDialog != null && newSetupdialog.mSyncdingDialog.isShowing()) {
                        newSetupdialog.mSyncdingDialog.dismiss();
                    }
                    BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                }
                if (message.what == 201811) {
                    BlueCmdManager.setDeviceCurrentMap((byte) 0);
                }
                if (message.what == 201812) {
                    BlueCmdManager.setDeviceCurrentMap((byte) 1);
                }
                if (message.what == 201813) {
                    BlueCmdManager.setDeviceCurrentMap((byte) 2);
                }
                if (message.what == 201814) {
                    BlueCmdManager.setDeviceCurrentMap((byte) 3);
                }
                if (message.what == 201815) {
                    BlueCmdManager.setDeviceCurrentMap((byte) 4);
                }
                if (message.what == 201860) {
                    newSetupdialog.this.displaysetup();
                }
                if (message.what == 201804) {
                    newSetupdialog.this.immenu.setVisibility(View.VISIBLE);
                    newSetupdialog.this.initMdatasaver();
                    newSetupdialog.this.displaysetup();
                    if (newSetupdialog.this.locale.contains("ar")) {
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201860, 50L);
                    }
                    if (FloatWindowService.myHandle != null) {
                        FloatWindowService.myHandle.sendEmptyMessage(0);
                    }
                    newSetupdialog.isMapInfosSuccess = true;
                }
                if (message.what == 201859) {
                    newSetupdialog.isMapInfosSuccess = false;
                    BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                    MyLog.i("my_tag", "sendGetMapCmd0");
                    new Thread() {
                        @Override
                        public void run() {
                            int n = 0;
                            while (n < 6 && !newSetupdialog.isMapInfosSuccess) {
                                int n2;
                                if ((n2 = n) > 0) {
                                    if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                                        BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                                        n2 = n;
                                    } else {
                                        n2 = 8;
                                    }
                                }
                                n = n2 + 1;
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (n >= 6 && n != 9) {
                                newSetupdialog.myhandler.sendEmptyMessage(201858);
                            }
                        }
                    }.start();
                }
                if (message.what == 201858 && bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing() && newSetupdialog.mdatasaver[55].name.equalsIgnoreCase("-1")) {
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.first_page_tip6)).show();
                    newSetupdialog.this.immenu.setVisibility(View.VISIBLE);
                }
                if (message.what == 201801) {
                    if (newSetupdialog.mLoadingDialog != null && newSetupdialog.mLoadingDialog.isShowing()) {
                        newSetupdialog.mLoadingDialog.dismiss();
                    }
                    newSetupdialog.isTest = false;
                    MyApplication.setDatapro(newSetupdialog.dataproSave);
                    newSetupdialog.this.initMdatasaver();
                    newSetupdialog.saveNum = 0;
                    new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip9)).show();
                    newSetupdialog.myhandler.removeMessages(201802);
                    if (bluetoothdevmanager.mNewSetupdialog != null && bluetoothdevmanager.mNewSetupdialog.isShowing()) {
                        bluetoothdevmanager.mNewSetupdialog.dismiss();
                        bluetoothdevmanager.mNewSetupdialog.cancel();
                        bluetoothdevmanager.mNewSetupdialog = null;
                    }
                }
                if (message.what == 201802) {
                    newSetupdialog.saveNum = newSetupdialog.saveNum;
                    if (newSetupdialog.saveNum == 1) {
                        newSetupdialog.this.changeData();
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                    } else {
                        newSetupdialog.saveNum = 0;
                        if (newSetupdialog.mLoadingDialog != null && newSetupdialog.mLoadingDialog.isShowing()) {
                            newSetupdialog.mLoadingDialog.dismiss();
                            new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip10)).show();
                        }
                    }
                }
                final int what = message.what;
                if (message.what == 2) {
                    newSetupdialog.this.dataprocess1((byte[]) message.obj);
                }
                if (message.what == 3 && bluetoothdevmanager.setupuiactivity == 1) {
                    newSetupdialog.this.cleanallview();
                    newSetupdialog.this.displaycircle();
                }
                final int what2 = message.what;
                if (message.what == 5) {
                    newSetupdialog.this.hideBottomUIMenu();
                }
                if (message.what == 6) {
                    if (newSetupdialog.this.mErrorloggwindow != null && newSetupdialog.this.mErrorloggwindow.isShowing()) {
                        newSetupdialog.this.mErrorloggwindow.dismiss();
                    }
                    if (newSetupdialog.this.mSaveMapErroeloggwindow != null && newSetupdialog.this.mSaveMapErroeloggwindow.isShowing()) {
                        newSetupdialog.this.mSaveMapErroeloggwindow.dismiss();
                    }
                }
                super.handleMessage(message);
            }
        };
    }

    public class errorloggwindow extends PopupWindow {
        private Context mContext;

        public errorloggwindow(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            this.setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.savedialog2, (ViewGroup) null));
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
        }
    }

    public class getSharePresetDialog extends PopupWindow {
        private EditText et_pwd_code;
        private Context mContext;
        private TextView tv_cancel;
        private TextView tv_sure;

        public getSharePresetDialog(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.savedialog6, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
            this.tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
            this.tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
            (this.et_pwd_code = (EditText) inflate.findViewById(R.id.et_pwd_code)).addTextChangedListener((TextWatcher) new TextWatcher() {
                public void afterTextChanged(final Editable editable) {
                    if (getSharePresetDialog.this.et_pwd_code.getText().toString().trim().length() == 8) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip27)).show();
                    }
                }

                public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }

                public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }
            });
            this.tv_sure.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (!CommonUtils.isStringValid(getSharePresetDialog.this.et_pwd_code.getText().toString().trim())) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip15)).show();
                        return;
                    }
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getShareFileDownloadUrl(getSharePresetDialog.this.et_pwd_code.getText().toString().trim()), 201822, 201823);
                    getSharePresetDialog.this.dismiss();
                }
            });
            this.tv_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    getSharePresetDialog.this.dismiss();
                }
            });
        }
    }

    public class loadingDialog extends PopupWindow {
        private Context mContext;

        public loadingDialog(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            this.setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.my_progress_dialog1, (ViewGroup) null));
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
        }
    }

    public class newSaveDialog extends AlertDialog {
        private TextView dialog_message;
        private EditText et_pwd_code;
        private ArrayList<String> list1;
        private ArrayList<String> list2;
        private ArrayList<String> list3;
        private LinearLayout ll_post_official;
        private LinearLayout ll_share;
        private Context mContext;
        private int saveMode;
        private TextView tv_cancel;
        private TextView tv_device_info;
        private TextView tv_device_model;
        private TextView tv_device_type;
        private TextView tv_phone_info;
        private TextView tv_post_official;
        private TextView tv_post_official_line;
        private TextView tv_preset_location;
        private TextView tv_resolution;
        private TextView tv_save;
        private TextView tv_share;
        private TextView tv_sure;

        public newSaveDialog(final Context mContext) {
            super(mContext, R.style.custom_window_dialog);
            this.saveMode = 0;
            this.mContext = mContext;
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            this.tv_save = (TextView) this.findViewById(R.id.tv_save);
            this.tv_share = (TextView) this.findViewById(R.id.tv_share);
            this.tv_post_official = (TextView) this.findViewById(R.id.tv_post_official);
            this.tv_post_official_line = (TextView) this.findViewById(R.id.tv_post_official_line);
            this.et_pwd_code = (EditText) this.findViewById(R.id.et_pwd_code);
            this.ll_share = (LinearLayout) this.findViewById(R.id.ll_share);
            this.dialog_message = (TextView) this.findViewById(R.id.dialog_message);
            this.tv_device_type = (TextView) this.findViewById(R.id.tv_device_type);
            this.tv_device_model = (TextView) this.findViewById(R.id.tv_device_model);
            this.tv_preset_location = (TextView) this.findViewById(R.id.tv_preset_location);
            this.tv_resolution = (TextView) this.findViewById(R.id.tv_resolution);
            this.tv_cancel = (TextView) this.findViewById(R.id.dialog_btn_cancel);
            this.tv_sure = (TextView) this.findViewById(R.id.dialog_btn_sure);
            this.tv_device_info = (TextView) this.findViewById(R.id.tv_device_info);
            this.tv_phone_info = (TextView) this.findViewById(R.id.tv_phone_info);
            this.ll_post_official = (LinearLayout) this.findViewById(R.id.ll_post_official);
            if (bluetoothdevmanager.isEpstMode) {
                this.saveMode = 2;
                this.tv_post_official_line.setVisibility(View.VISIBLE);
                this.tv_post_official.setVisibility(View.VISIBLE);
            } else {
                this.saveMode = 0;
                this.tv_post_official_line.setVisibility(View.GONE);
                this.tv_post_official.setVisibility(View.GONE);
            }
            if (this.saveMode == 0) {
                this.dialog_message.setVisibility(View.VISIBLE);
                this.ll_share.setVisibility(View.GONE);
                this.ll_post_official.setVisibility(View.GONE);
                this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
            } else if (this.saveMode == 2) {
                this.dialog_message.setVisibility(View.GONE);
                this.ll_share.setVisibility(View.GONE);
                this.ll_post_official.setVisibility(View.VISIBLE);
                this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
            }
            this.et_pwd_code.addTextChangedListener((TextWatcher) new TextWatcher() {
                public void afterTextChanged(final Editable editable) {
                    if (newSaveDialog.this.et_pwd_code.getText().toString().trim().length() == 8) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip27)).show();
                    }
                }

                public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }

                public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }
            });
            this.tv_save.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog.this.saveMode != 0) {
                        newSaveDialog.this.dialog_message.setVisibility(View.VISIBLE);
                        newSaveDialog.this.ll_share.setVisibility(View.GONE);
                        newSaveDialog.this.ll_post_official.setVisibility(View.GONE);
                        newSaveDialog.this.saveMode = 0;
                        newSaveDialog.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSaveDialog.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_share.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog.this.saveMode != 1) {
                        newSaveDialog.this.dialog_message.setVisibility(View.GONE);
                        newSaveDialog.this.ll_share.setVisibility(View.VISIBLE);
                        newSaveDialog.this.ll_post_official.setVisibility(View.GONE);
                        newSaveDialog.this.saveMode = 1;
                        newSaveDialog.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSaveDialog.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_post_official.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog.this.saveMode != 2) {
                        newSaveDialog.this.dialog_message.setVisibility(View.GONE);
                        newSaveDialog.this.ll_share.setVisibility(View.GONE);
                        newSaveDialog.this.ll_post_official.setVisibility(View.VISIBLE);
                        newSaveDialog.this.saveMode = 2;
                        newSaveDialog.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                    }
                }
            });
            if (bluetoothdevmanager.devicemode == 0) {
                final TextView tv_device_info = this.tv_device_info;
                final StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(this.mContext.getResources().getString(R.string.app_name1));
                sb.append("\t\t");
                sb.append(FirstPageActivity.projectName);
                tv_device_info.setText((CharSequence) sb.toString());
            } else {
                final TextView tv_device_info2 = this.tv_device_info;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(this.mContext.getResources().getString(R.string.app_name2));
                sb2.append("\t\t");
                sb2.append(FirstPageActivity.projectName);
                tv_device_info2.setText((CharSequence) sb2.toString());
            }
            if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                final float screenwp = bluetoothdevmanager.screenwp;
                bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                bluetoothdevmanager.screenhp = screenwp;
            }
            final TextView tv_phone_info = this.tv_phone_info;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append((int) bluetoothdevmanager.screenwp);
            sb3.append("x");
            sb3.append((int) bluetoothdevmanager.screenhp);
            tv_phone_info.setText((CharSequence) sb3.toString());
            (this.list1 = new ArrayList<String>()).add("ShootingPlus");
            this.list1.add("GamepadPlus");
            if (bluetoothdevmanager.devicemode == 0) {
                newSetupdialog.deviceType = 0;
                this.tv_device_type.setText((CharSequence) "ShootingPlus");
            } else {
                newSetupdialog.deviceType = 1;
                this.tv_device_type.setText((CharSequence) "GamepadPlus");
            }
            (this.list2 = new ArrayList<String>()).add("Q789");
            this.list2.add("P916");
            this.list2.add("GB");
            this.list2.add("BSP1");
            this.list2.add("789P");
            this.list2.add("P918");
            this.list2.add("V1");
            this.list2.add("STM3");
            this.list2.add("L2");
            for (int i = 0; i < this.list2.size(); ++i) {
                if (FirstPageActivity.projectName.equalsIgnoreCase(this.list2.get(i))) {
                    final TextView tv_device_model = this.tv_device_model;
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("");
                    sb4.append(this.list2.get(i));
                    tv_device_model.setText((CharSequence) sb4.toString());
                }
            }
            (this.list3 = new ArrayList<String>()).add("F1");
            this.list3.add("F2");
            this.list3.add("F3");
            this.list3.add("F4");
            this.list3.add("F5");
            final TextView tv_preset_location = this.tv_preset_location;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("");
            sb5.append(this.list3.get(0));
            tv_preset_location.setText((CharSequence) sb5.toString());
            final TextView tv_resolution = this.tv_resolution;
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("");
            sb6.append((int) bluetoothdevmanager.screenwp);
            sb6.append("x");
            sb6.append((int) bluetoothdevmanager.screenhp);
            tv_resolution.setText((CharSequence) sb6.toString());
            this.tv_device_type.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) newSaveDialog.this.tv_device_type, newSaveDialog.this.mContext, newSaveDialog.this.list1, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$4700 = newSaveDialog.this.tv_device_type;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSaveDialog.this.list1.get(n));
                            access$4700.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_device_model.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) newSaveDialog.this.tv_device_type, newSaveDialog.this.mContext, newSaveDialog.this.list2, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$5100 = newSaveDialog.this.tv_device_model;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSaveDialog.this.list2.get(n));
                            access$5100.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_preset_location.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) newSaveDialog.this.tv_device_type, newSaveDialog.this.mContext, newSaveDialog.this.list3, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$5300 = newSaveDialog.this.tv_preset_location;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSaveDialog.this.list3.get(n));
                            access$5300.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_sure.setOnClickListener((View.OnClickListener) new View.OnClickListener() {

                public void onClick(final View view) {
                    if (newSaveDialog.this.saveMode == 0) {
                        newSetupdialog.this.changeData();
                        newSaveDialog.this.dismiss();
                        if (newSetupdialog.mLoadingDialog == null) {
                            newSetupdialog.mLoadingDialog = new loadingDialog(newSetupdialog.this.mcontext);
                        }
                        newSetupdialog.mLoadingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                    } else if (newSaveDialog.this.saveMode == 1) {
                        if (!CommonUtils.isStringValid(newSaveDialog.this.et_pwd_code.getText().toString().trim())) {
                            new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip15)).show();
                            return;
                        }
                        newSetupdialog.uploadFilePath = null;
                        newSetupdialog.shareCode = null;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb.append("/");
                        sb.append(newSaveDialog.this.et_pwd_code.getText().toString().trim());
                        sb.append(".ini");
                        newSetupdialog.uploadFilePath = sb.toString();
                        newSetupdialog.shareCode = newSaveDialog.this.et_pwd_code.getText().toString().trim();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(newSaveDialog.this.et_pwd_code.getText().toString().trim());
                        sb2.append(".ini");
                        final String string = sb2.toString();
                        newSetupdialog.this.profilesave(newSetupdialog.uploadFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201816, 201817, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadFilePath, string);
                        newSaveDialog.this.dismiss();
                    } else if (newSaveDialog.this.saveMode == 2) {
                        if (newSaveDialog.this.tv_device_type.getText().toString().trim().equalsIgnoreCase("ShootingPlus")) {
                            newSetupdialog.deviceType = 0;
                        } else if (newSaveDialog.this.tv_device_type.getText().toString().trim().equalsIgnoreCase("GamepadPlus")) {
                            newSetupdialog.deviceType = 1;
                        }
                        newSetupdialog.model = newSaveDialog.this.tv_device_model.getText().toString().trim();
                        newSetupdialog.location = newSaveDialog.this.tv_preset_location.getText().toString().trim();
                        newSetupdialog.resolution = newSaveDialog.this.tv_resolution.getText().toString().trim();
                        if (CommonUtils.isStringValid(newSetupdialog.model) && CommonUtils.isStringValid(newSetupdialog.location) && CommonUtils.isStringValid(newSetupdialog.resolution)) {
                            newSetupdialog.uploadOfficialFilePath = null;
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                            sb3.append("/");
                            sb3.append(newSetupdialog.location);
                            sb3.append(".ini");
                            newSetupdialog.uploadOfficialFilePath = sb3.toString();
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append(newSetupdialog.location);
                            sb4.append(".ini");
                            final String string2 = sb4.toString();
                            newSetupdialog.this.profilesave(newSetupdialog.uploadOfficialFilePath);
                            HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201824, 201825, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadOfficialFilePath, string2);
                            newSaveDialog.this.dismiss();
                        }
                    }
                }
            });
            this.tv_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSaveDialog.this.dismiss();
                }
            });
        }

        protected void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            this.setContentView(R.layout.savedialog10);
            this.getWindow().setLayout(-2, -2);
            this.getWindow().setFlags(8, 8);
            if (Build.VERSION.SDK_INT < 23) {
                this.getWindow().setType(2005);
            } else if (Build.VERSION.SDK_INT != 28 && Build.VERSION.SDK_INT != 26 && Build.VERSION.SDK_INT != 27) {
                if (Build.VERSION.SDK_INT > 28) {
                    this.getWindow().setType(2038);
                } else {
                    this.getWindow().setType(2003);
                }
            } else {
                this.getWindow().setType(2038);
            }
            this.setCancelable(false);
            this.setCanceledOnTouchOutside(false);
            this.init();
        }
    }

    public class newSaveDialog1 extends AlertDialog {
        private CheckBox cb_all_device;
        private TextView dialog_message;
        private EditText et_pwd_code;
        private GridView gv_device;
        private ArrayList<String> list3;
        private LinearLayout ll_post_game_preset;
        private LinearLayout ll_post_official;
        private LinearLayout ll_share;
        private Context mContext;
        private DeviceItemsAdapter mDeviceItemsAdapter;
        private int saveMode;
        private TextView tv_cancel;
        private TextView tv_device_info;
        private TextView tv_device_info_pg;
        private TextView tv_game_name;
        private TextView tv_phone_info;
        private TextView tv_phone_info_pg;
        private TextView tv_post_game_preset;
        private TextView tv_post_official;
        private TextView tv_post_official_line;
        private TextView tv_preset_game_id;
        private TextView tv_preset_location;
        private TextView tv_save;
        private TextView tv_share;
        private TextView tv_sure;

        public newSaveDialog1(final Context mContext) {
            super(mContext, R.style.custom_window_dialog);
            this.saveMode = 0;
            this.mContext = mContext;
        }

        void init() {
            this.tv_save = (TextView) this.findViewById(R.id.tv_save);
            this.tv_share = (TextView) this.findViewById(R.id.tv_share);
            this.tv_post_official = (TextView) this.findViewById(R.id.tv_post_official);
            this.tv_post_official_line = (TextView) this.findViewById(R.id.tv_post_official_line);
            this.et_pwd_code = (EditText) this.findViewById(R.id.et_pwd_code);
            this.ll_share = (LinearLayout) this.findViewById(R.id.ll_share);
            this.dialog_message = (TextView) this.findViewById(R.id.dialog_message);
            this.tv_preset_location = (TextView) this.findViewById(R.id.tv_preset_location);
            this.tv_preset_game_id = (TextView) this.findViewById(R.id.tv_preset_game_id);
            this.tv_cancel = (TextView) this.findViewById(R.id.dialog_btn_cancel);
            this.tv_sure = (TextView) this.findViewById(R.id.dialog_btn_sure);
            this.tv_device_info = (TextView) this.findViewById(R.id.tv_device_info);
            this.tv_phone_info = (TextView) this.findViewById(R.id.tv_phone_info);
            this.ll_post_official = (LinearLayout) this.findViewById(R.id.ll_post_official);
            this.gv_device = (GridView) this.findViewById(R.id.gv_device);
            this.cb_all_device = (CheckBox) this.findViewById(R.id.cb_all_device);
            this.ll_post_game_preset = (LinearLayout) this.findViewById(R.id.ll_post_game_preset);
            this.tv_post_game_preset = (TextView) this.findViewById(R.id.tv_post_game_preset);
            this.tv_device_info_pg = (TextView) this.findViewById(R.id.tv_device_info_pg);
            this.tv_phone_info_pg = (TextView) this.findViewById(R.id.tv_phone_info_pg);
            this.tv_game_name = (TextView) this.findViewById(R.id.tv_game_name);
            this.cb_all_device.setChecked(false);
            if (bluetoothdevmanager.isEpstMode) {
                this.saveMode = 2;
                this.tv_post_official_line.setVisibility(View.VISIBLE);
                this.tv_post_official.setVisibility(View.VISIBLE);
            } else {
                this.saveMode = 0;
                this.tv_post_official_line.setVisibility(View.GONE);
                this.tv_post_official.setVisibility(View.GONE);
            }
            if (this.saveMode == 0) {
                this.dialog_message.setVisibility(View.VISIBLE);
                this.ll_share.setVisibility(View.GONE);
                this.ll_post_official.setVisibility(View.GONE);
                this.ll_post_game_preset.setVisibility(View.GONE);
                this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
            } else if (this.saveMode == 2) {
                this.dialog_message.setVisibility(View.GONE);
                this.ll_share.setVisibility(View.GONE);
                this.ll_post_official.setVisibility(View.VISIBLE);
                this.ll_post_game_preset.setVisibility(View.GONE);
                this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
            }
            this.et_pwd_code.addTextChangedListener((TextWatcher) new TextWatcher() {
                public void afterTextChanged(final Editable editable) {
                    if (newSaveDialog1.this.et_pwd_code.getText().toString().trim().length() == 8) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip27)).show();
                    }
                }

                public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }

                public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }
            });
            this.tv_save.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog1.this.saveMode != 0) {
                        newSaveDialog1.this.dialog_message.setVisibility(View.VISIBLE);
                        newSaveDialog1.this.ll_share.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_official.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_game_preset.setVisibility(View.GONE);
                        newSaveDialog1.this.saveMode = 0;
                        newSaveDialog1.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSaveDialog1.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_game_preset.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_share.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog1.this.saveMode != 1) {
                        newSaveDialog1.this.dialog_message.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_share.setVisibility(View.VISIBLE);
                        newSaveDialog1.this.ll_post_official.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_game_preset.setVisibility(View.GONE);
                        newSaveDialog1.this.saveMode = 1;
                        newSaveDialog1.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSaveDialog1.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_game_preset.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_post_official.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog1.this.saveMode != 2) {
                        newSaveDialog1.this.dialog_message.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_share.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_official.setVisibility(View.VISIBLE);
                        newSaveDialog1.this.ll_post_game_preset.setVisibility(View.GONE);
                        newSaveDialog1.this.saveMode = 2;
                        newSaveDialog1.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSaveDialog1.this.tv_post_game_preset.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_post_game_preset.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSaveDialog1.this.saveMode != 3) {
                        newSaveDialog1.this.dialog_message.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_share.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_official.setVisibility(View.GONE);
                        newSaveDialog1.this.ll_post_game_preset.setVisibility(View.VISIBLE);
                        newSaveDialog1.this.saveMode = 3;
                        newSaveDialog1.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_official.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSaveDialog1.this.tv_post_game_preset.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                    }
                }
            });
            final TextView tv_phone_info = this.tv_phone_info;
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append((int) bluetoothdevmanager.screenwp);
            sb.append("x");
            sb.append((int) bluetoothdevmanager.screenhp);
            tv_phone_info.setText((CharSequence) sb.toString());
            if (bluetoothdevmanager.devicemode == 0) {
                newSetupdialog.deviceType = 0;
                this.tv_device_info.setText((CharSequence) "ShootingPlus");
                this.tv_device_info_pg.setText((CharSequence) "ShootingPlus");
            } else {
                newSetupdialog.deviceType = 1;
                this.tv_device_info.setText((CharSequence) "GamepadPlus");
                this.tv_device_info_pg.setText((CharSequence) "GamepadPlus");
            }
            this.mDeviceItemsAdapter = new DeviceItemsAdapter(this.mContext, newSetupdialog.deviceList);
            this.gv_device.setAdapter(mDeviceItemsAdapter);
            this.cb_all_device.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                    int i = 0;
                    if (b) {
                        while (i < newSetupdialog.deviceList.size()) {
                            ((Device) newSetupdialog.deviceList.get(i)).setChecked(true);
                            ++i;
                        }
                    } else {
                        for (int j = 0; j < newSetupdialog.deviceList.size(); ++j) {
                            ((Device) newSetupdialog.deviceList.get(j)).setChecked(false);
                        }
                    }
                    newSaveDialog1.this.mDeviceItemsAdapter.refresh(newSetupdialog.deviceList);
                }
            });
            (this.list3 = new ArrayList<String>()).add("F1");
            this.list3.add("F2");
            this.list3.add("F3");
            this.list3.add("F4");
            this.list3.add("F5");
            final TextView tv_preset_location = this.tv_preset_location;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(this.list3.get(0));
            tv_preset_location.setText((CharSequence) sb2.toString());
            final TextView tv_phone_info2 = this.tv_phone_info;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append((int) bluetoothdevmanager.screenwp);
            sb3.append("x");
            sb3.append((int) bluetoothdevmanager.screenhp);
            tv_phone_info2.setText((CharSequence) sb3.toString());
            final TextView tv_phone_info_pg = this.tv_phone_info_pg;
            final StringBuilder sb4 = new StringBuilder();
            sb4.append("");
            sb4.append((int) bluetoothdevmanager.screenwp);
            sb4.append("x");
            sb4.append((int) bluetoothdevmanager.screenhp);
            tv_phone_info_pg.setText((CharSequence) sb4.toString());
            this.tv_preset_location.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) newSaveDialog1.this.tv_preset_location, newSaveDialog1.this.mContext, newSaveDialog1.this.list3, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$6600 = newSaveDialog1.this.tv_preset_location;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSaveDialog1.this.list3.get(n));
                            access$6600.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_preset_game_id.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow2((View) newSaveDialog1.this.tv_preset_game_id, newSaveDialog1.this.mContext, newSetupdialog.this.mOfficialGameList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$6900 = newSaveDialog1.this.tv_preset_game_id;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSetupdialog.this.mOfficialGameList.get(n).getName());
                            access$6900.setText((CharSequence) sb.toString());
                            newSetupdialog.gameId = newSetupdialog.this.mOfficialGameList.get(n).getGameId();
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_phone_info.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSetupdialog.this.mUnsupportedResolutionList.size() > 0) {
                        newSetupdialog.this.showTipPopupWindow((View) newSaveDialog1.this.tv_phone_info, newSaveDialog1.this.mContext, newSetupdialog.this.mUnsupportedResolutionList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                                final TextView access$7000 = newSaveDialog1.this.tv_phone_info;
                                final StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(newSetupdialog.this.mUnsupportedResolutionList.get(n));
                                access$7000.setText((CharSequence) sb.toString());
                            }
                        }, (View.OnClickListener) new View.OnClickListener() {
                            public void onClick(final View view) {
                            }
                        });
                    }
                }
            });
            this.tv_phone_info_pg.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSetupdialog.this.mUnsupportedResolutionList.size() > 0) {
                        newSetupdialog.this.showTipPopupWindow((View) newSaveDialog1.this.tv_phone_info_pg, newSaveDialog1.this.mContext, newSetupdialog.this.mUnsupportedResolutionList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                            public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                                final TextView access$7100 = newSaveDialog1.this.tv_phone_info_pg;
                                final StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(newSetupdialog.this.mUnsupportedResolutionList.get(n));
                                access$7100.setText((CharSequence) sb.toString());
                            }
                        }, (View.OnClickListener) new View.OnClickListener() {
                            public void onClick(final View view) {
                            }
                        });
                    }
                }
            });
            this.tv_game_name.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow2((View) newSaveDialog1.this.tv_device_info_pg, newSaveDialog1.this.mContext, newSetupdialog.this.mOfficialGameList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$7300 = newSaveDialog1.this.tv_game_name;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSetupdialog.this.mOfficialGameList.get(n).getName());
                            access$7300.setText((CharSequence) sb.toString());
                            newSetupdialog.gameId = newSetupdialog.this.mOfficialGameList.get(n).getGameId();
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_sure.setOnClickListener((View.OnClickListener) new View.OnClickListener() {

                public void onClick(final View view) {
                    if (newSaveDialog1.this.saveMode == 0) {
                        newSetupdialog.this.changeData();
                        newSaveDialog1.this.dismiss();
                        if (newSetupdialog.mLoadingDialog == null) {
                            newSetupdialog.mLoadingDialog = new loadingDialog(newSetupdialog.this.mcontext);
                        }
                        newSetupdialog.mLoadingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                    } else if (newSaveDialog1.this.saveMode == 1) {
                        if (!CommonUtils.isStringValid(newSaveDialog1.this.et_pwd_code.getText().toString().trim())) {
                            new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip15)).show();
                            return;
                        }
                        newSetupdialog.uploadFilePath = null;
                        newSetupdialog.shareCode = null;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb.append("/");
                        sb.append(newSaveDialog1.this.et_pwd_code.getText().toString().trim());
                        sb.append(".ini");
                        newSetupdialog.uploadFilePath = sb.toString();
                        newSetupdialog.shareCode = newSaveDialog1.this.et_pwd_code.getText().toString().trim();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(newSaveDialog1.this.et_pwd_code.getText().toString().trim());
                        sb2.append(".ini");
                        final String string = sb2.toString();
                        newSetupdialog.this.profilesave(newSetupdialog.uploadFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201816, 201817, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadFilePath, string);
                        newSaveDialog1.this.dismiss();
                    } else if (newSaveDialog1.this.saveMode == 2) {
                        if (newSaveDialog1.this.tv_device_info.getText().toString().trim().equalsIgnoreCase("ShootingPlus")) {
                            newSetupdialog.deviceType = 0;
                        } else if (newSaveDialog1.this.tv_device_info.getText().toString().trim().equalsIgnoreCase("GamepadPlus")) {
                            newSetupdialog.deviceType = 1;
                        }
                        newSetupdialog.modelList.clear();
                        for (int i = 0; i < newSetupdialog.deviceList.size(); ++i) {
                            if (((Device) newSetupdialog.deviceList.get(i)).isChecked()) {
                                newSetupdialog.modelList.add(((Device) newSetupdialog.deviceList.get(i)).getDeviceName());
                            }
                        }
                        newSetupdialog.location = newSaveDialog1.this.tv_preset_location.getText().toString().trim();
                        newSetupdialog.resolution = newSaveDialog1.this.tv_phone_info.getText().toString().trim();
                        if (newSetupdialog.modelList.size() > 0 && CommonUtils.isStringValid(newSetupdialog.modelList.get(0)) && CommonUtils.isStringValid(newSetupdialog.location) && CommonUtils.isStringValid(newSetupdialog.resolution) && newSetupdialog.gameId != 0) {
                            newSetupdialog.uploadOfficialFilePath = null;
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                            sb3.append("/");
                            sb3.append(newSetupdialog.location);
                            sb3.append(".ini");
                            newSetupdialog.uploadOfficialFilePath = sb3.toString();
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append(newSetupdialog.location);
                            sb4.append(".ini");
                            final String string2 = sb4.toString();
                            newSetupdialog.modelNum = 0;
                            newSetupdialog.model = newSetupdialog.modelList.get(newSetupdialog.modelNum);
                            final StringBuilder sb5 = new StringBuilder();
                            sb5.append("--------resolution = ");
                            sb5.append(newSetupdialog.resolution);
                            sb5.append(";   deviceType = ");
                            sb5.append(newSetupdialog.deviceType);
                            sb5.append(";   location = ");
                            sb5.append(newSetupdialog.location);
                            sb5.append(";   gameId = ");
                            sb5.append(newSetupdialog.gameId);
                            sb5.append(";   model = ");
                            sb5.append(newSetupdialog.model);
                            Log.i("upload_file_tag", sb5.toString());
                            newSetupdialog.this.profilesave(newSetupdialog.uploadOfficialFilePath);
                            HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201824, 201825, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadOfficialFilePath, string2);
                            newSaveDialog1.this.dismiss();
                        }
                    } else if (newSaveDialog1.this.saveMode == 3) {
                        newSetupdialog.platform = 1;
                        newSetupdialog.resolution = newSaveDialog1.this.tv_phone_info_pg.getText().toString().trim();
                        if (newSaveDialog1.this.tv_device_info_pg.getText().toString().trim().equalsIgnoreCase("ShootingPlus")) {
                            newSetupdialog.deviceType = 0;
                        } else if (newSaveDialog1.this.tv_device_info_pg.getText().toString().trim().equalsIgnoreCase("GamepadPlus")) {
                            newSetupdialog.deviceType = 1;
                        }
                        if (CommonUtils.isStringValid(newSetupdialog.resolution) && newSetupdialog.gameId != 0) {
                            final StringBuilder sb6 = new StringBuilder();
                            sb6.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                            sb6.append("/temp.ini");
                            newSetupdialog.uploadOfficialGameFilePath = sb6.toString();
                            newSetupdialog.this.profilesave(newSetupdialog.uploadOfficialGameFilePath);
                            newSetupdialog.uploadOfficialGameFileName = "temp.ini";
                            HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201854, 201855, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadOfficialGameFilePath, newSetupdialog.uploadOfficialGameFileName);
                        }
                        newSaveDialog1.this.dismiss();
                    }
                }
            });
            this.tv_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSaveDialog1.this.dismiss();
                }
            });
        }

        protected void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            this.setContentView(R.layout.savedialog11);
            this.getWindow().setLayout(-2, -2);
            this.getWindow().setFlags(8, 8);
            this.getWindow().clearFlags(131080);
            this.getWindow().setSoftInputMode(18);
            if (Build.VERSION.SDK_INT < 23) {
                this.getWindow().setType(2005);
            } else if (Build.VERSION.SDK_INT != 28 && Build.VERSION.SDK_INT != 26 && Build.VERSION.SDK_INT != 27) {
                if (Build.VERSION.SDK_INT > 28) {
                    this.getWindow().setType(2038);
                } else {
                    this.getWindow().setType(2003);
                }
            } else {
                this.getWindow().setType(2038);
            }
            this.setCancelable(false);
            this.setCanceledOnTouchOutside(false);
            this.init();
        }
    }

    public class newSavedialoggwindow extends PopupWindow {
        private TextView dialog_btn_cancel;
        private TextView dialog_btn_sure;
        private TextView dialog_message;
        private EditText et_pwd_code;
        private boolean isSave;
        private LinearLayout ll_share;
        private Context mContext;
        private TextView tv_save;
        private TextView tv_share;

        public newSavedialoggwindow(final Context mContext) {
            super(mContext);
            this.isSave = true;
            this.mContext = mContext;
            this.init();
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.savedialog4, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
            this.dialog_btn_cancel = (TextView) inflate.findViewById(R.id.dialog_btn_cancel);
            this.dialog_btn_sure = (TextView) inflate.findViewById(R.id.dialog_btn_sure);
            this.tv_save = (TextView) inflate.findViewById(R.id.tv_save);
            this.tv_share = (TextView) inflate.findViewById(R.id.tv_share);
            this.et_pwd_code = (EditText) inflate.findViewById(R.id.et_pwd_code);
            this.ll_share = (LinearLayout) inflate.findViewById(R.id.ll_share);
            (this.dialog_message = (TextView) inflate.findViewById(R.id.dialog_message)).setVisibility(View.VISIBLE);
            this.ll_share.setVisibility(View.GONE);
            this.isSave = true;
            this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
            this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
            this.et_pwd_code.addTextChangedListener((TextWatcher) new TextWatcher() {
                public void afterTextChanged(final Editable editable) {
                    if (newSavedialoggwindow.this.et_pwd_code.getText().toString().trim().length() == 8) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip27)).show();
                    }
                }

                public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }

                public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }
            });
            this.tv_save.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if (!newSavedialoggwindow.this.isSave) {
                        newSavedialoggwindow.this.dialog_message.setVisibility(View.VISIBLE);
                        newSavedialoggwindow.this.ll_share.setVisibility(View.GONE);
                        newSavedialoggwindow.this.isSave = true;
                        newSavedialoggwindow.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        newSavedialoggwindow.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_share.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSavedialoggwindow.this.isSave) {
                        newSavedialoggwindow.this.dialog_message.setVisibility(View.GONE);
                        newSavedialoggwindow.this.ll_share.setVisibility(View.VISIBLE);
                        newSavedialoggwindow.this.isSave = false;
                        newSavedialoggwindow.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        newSavedialoggwindow.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                    }
                }
            });
            this.dialog_btn_sure.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {
                    if (newSavedialoggwindow.this.isSave) {
                        newSetupdialog.this.changeData();
                        newSavedialoggwindow.this.dismiss();
                        if (newSetupdialog.mLoadingDialog == null) {
                            newSetupdialog.mLoadingDialog = new loadingDialog(newSetupdialog.this.mcontext);
                        }
                        newSetupdialog.mLoadingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                    } else {
                        if (!CommonUtils.isStringValid(newSavedialoggwindow.this.et_pwd_code.getText().toString().trim())) {
                            new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip15)).show();
                            return;
                        }
                        newSetupdialog.uploadFilePath = null;
                        newSetupdialog.shareCode = null;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb.append("/");
                        sb.append(newSavedialoggwindow.this.et_pwd_code.getText().toString().trim());
                        sb.append(".ini");
                        newSetupdialog.uploadFilePath = sb.toString();
                        newSetupdialog.shareCode = newSavedialoggwindow.this.et_pwd_code.getText().toString().trim();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(newSavedialoggwindow.this.et_pwd_code.getText().toString().trim());
                        sb2.append(".ini");
                        final String string = sb2.toString();
                        newSetupdialog.this.profilesave(newSetupdialog.uploadFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201816, 201817, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadFilePath, string);
                        newSavedialoggwindow.this.dismiss();
                    }
                }
            });
            this.dialog_btn_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSavedialoggwindow.this.dismiss();
                }
            });
        }
    }

    public class postToOffDialog extends AlertDialog {
        private ArrayList<String> list1;
        private ArrayList<String> list2;
        private ArrayList<String> list3;
        private Context mContext;
        private TextView tv_cancel;
        private TextView tv_device_info;
        private TextView tv_device_model;
        private TextView tv_device_type;
        private TextView tv_phone_info;
        private TextView tv_preset_location;
        private TextView tv_resolution;
        private TextView tv_sure;

        public postToOffDialog(final Context mContext) {
            super(mContext, R.style.custom_window_dialog);
            this.mContext = mContext;
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            this.tv_device_type = (TextView) this.findViewById(R.id.tv_device_type);
            this.tv_device_model = (TextView) this.findViewById(R.id.tv_device_model);
            this.tv_preset_location = (TextView) this.findViewById(R.id.tv_preset_location);
            this.tv_resolution = (TextView) this.findViewById(R.id.tv_resolution);
            this.tv_cancel = (TextView) this.findViewById(R.id.tv_cancel);
            this.tv_sure = (TextView) this.findViewById(R.id.tv_sure);
            this.tv_device_info = (TextView) this.findViewById(R.id.tv_device_info);
            this.tv_phone_info = (TextView) this.findViewById(R.id.tv_phone_info);
            if (bluetoothdevmanager.devicemode == 0) {
                final TextView tv_device_info = this.tv_device_info;
                final StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(this.mContext.getResources().getString(R.string.app_name1));
                sb.append("\t\t");
                sb.append(FirstPageActivity.projectName);
                tv_device_info.setText((CharSequence) sb.toString());
            } else {
                final TextView tv_device_info2 = this.tv_device_info;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(this.mContext.getResources().getString(R.string.app_name2));
                sb2.append("\t\t");
                sb2.append(FirstPageActivity.projectName);
                tv_device_info2.setText((CharSequence) sb2.toString());
            }
            if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                final float screenwp = bluetoothdevmanager.screenwp;
                bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                bluetoothdevmanager.screenhp = screenwp;
            }
            final TextView tv_phone_info = this.tv_phone_info;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append((int) bluetoothdevmanager.screenwp);
            sb3.append("x");
            sb3.append((int) bluetoothdevmanager.screenhp);
            tv_phone_info.setText((CharSequence) sb3.toString());
            (this.list1 = new ArrayList<String>()).add("ShootingPlus");
            this.list1.add("GamepadPlus");
            if (bluetoothdevmanager.devicemode == 0) {
                newSetupdialog.deviceType = 0;
                this.tv_device_type.setText((CharSequence) "ShootingPlus");
            } else {
                newSetupdialog.deviceType = 1;
                this.tv_device_type.setText((CharSequence) "GamepadPlus");
            }
            (this.list2 = new ArrayList<String>()).add("Q789");
            this.list2.add("P916");
            this.list2.add("GB");
            this.list2.add("BSP1");
            this.list2.add("789P");
            this.list2.add("P918");
            this.list2.add("V1");
            this.list2.add("STM3");
            this.list2.add("L2");
            for (int i = 0; i < this.list2.size(); ++i) {
                if (FirstPageActivity.projectName.equalsIgnoreCase(this.list2.get(i))) {
                    final TextView tv_device_model = this.tv_device_model;
                    final StringBuilder sb4 = new StringBuilder();
                    sb4.append("");
                    sb4.append(this.list2.get(i));
                    tv_device_model.setText((CharSequence) sb4.toString());
                }
            }
            (this.list3 = new ArrayList<String>()).add("F1");
            this.list3.add("F2");
            this.list3.add("F3");
            this.list3.add("F4");
            this.list3.add("F5");
            final TextView tv_preset_location = this.tv_preset_location;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("");
            sb5.append(this.list3.get(0));
            tv_preset_location.setText((CharSequence) sb5.toString());
            final TextView tv_resolution = this.tv_resolution;
            final StringBuilder sb6 = new StringBuilder();
            sb6.append("");
            sb6.append((int) bluetoothdevmanager.screenwp);
            sb6.append("x");
            sb6.append((int) bluetoothdevmanager.screenhp);
            tv_resolution.setText((CharSequence) sb6.toString());
            this.tv_device_type.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow(postToOffDialog.this.tv_device_type, postToOffDialog.this.mContext, postToOffDialog.this.list1, new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$7500 = postToOffDialog.this.tv_device_type;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(postToOffDialog.this.list1.get(n));
                            access$7500.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_device_model.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) postToOffDialog.this.tv_device_type, postToOffDialog.this.mContext, postToOffDialog.this.list2, new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$7900 = postToOffDialog.this.tv_device_model;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(postToOffDialog.this.list2.get(n));
                            access$7900.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_preset_location.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow((View) postToOffDialog.this.tv_device_type, postToOffDialog.this.mContext, postToOffDialog.this.list3, new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$8100 = postToOffDialog.this.tv_preset_location;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(postToOffDialog.this.list3.get(n));
                            access$8100.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.tv_sure.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {
                    if (postToOffDialog.this.tv_device_type.getText().toString().trim().equalsIgnoreCase("ShootingPlus")) {
                        newSetupdialog.deviceType = 0;
                    } else if (postToOffDialog.this.tv_device_type.getText().toString().trim().equalsIgnoreCase("GamepadPlus")) {
                        newSetupdialog.deviceType = 1;
                    }
                    newSetupdialog.model = postToOffDialog.this.tv_device_model.getText().toString().trim();
                    newSetupdialog.location = postToOffDialog.this.tv_preset_location.getText().toString().trim();
                    newSetupdialog.resolution = postToOffDialog.this.tv_resolution.getText().toString().trim();
                    if (CommonUtils.isStringValid(newSetupdialog.model) && CommonUtils.isStringValid(newSetupdialog.location) && CommonUtils.isStringValid(newSetupdialog.resolution)) {
                        newSetupdialog.uploadOfficialFilePath = null;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb.append("/");
                        sb.append(newSetupdialog.location);
                        sb.append(".ini");
                        newSetupdialog.uploadOfficialFilePath = sb.toString();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(newSetupdialog.location);
                        sb2.append(".ini");
                        final String string = sb2.toString();
                        newSetupdialog.this.profilesave(newSetupdialog.uploadOfficialFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201824, 201825, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadOfficialFilePath, string);
                        postToOffDialog.this.dismiss();
                    }
                }
            });
            this.tv_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    postToOffDialog.this.dismiss();
                }
            });
        }

        protected void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            this.setContentView(R.layout.savedialog9);
            this.getWindow().setLayout(-2, -2);
            this.getWindow().setFlags(8, 8);
            if (Build.VERSION.SDK_INT < 23) {
                this.getWindow().setType(2005);
            } else if (Build.VERSION.SDK_INT != 28 && Build.VERSION.SDK_INT != 26 && Build.VERSION.SDK_INT != 27) {
                if (Build.VERSION.SDK_INT > 28) {
                    this.getWindow().setType(2038);
                } else {
                    this.getWindow().setType(2003);
                }
            } else {
                this.getWindow().setType(2038);
            }
            this.setCancelable(false);
            this.setCanceledOnTouchOutside(false);
            this.init();
        }
    }

    public class presetCloudSyncDialog extends PopupWindow {
        private Context mContext;
        private TextView tv_cancel;
        private TextView tv_sure;

        public presetCloudSyncDialog(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.savedialog5, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
            this.tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
            (this.tv_sure = (TextView) inflate.findViewById(R.id.tv_sure)).setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if (newSetupdialog.mSyncdingDialog == null) {
                        newSetupdialog.mSyncdingDialog = new syncdingDialog(presetCloudSyncDialog.this.mContext);
                    }
                    if (!newSetupdialog.mSyncdingDialog.isShowing()) {
                        newSetupdialog.mSyncdingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                    }
                    final StringBuilder sb = new StringBuilder();
                    sb.append("----------\u5f00\u59cb\u9884\u8bbe\u4e91\u540c\u6b65");
                    sb.append(newSetupdialog.mSyncdingDialog.isShowing());
                    MyLog.i("sync_tag", sb.toString());
                    newSetupdialog.platform = 1;
                    newSetupdialog.deviceType = 0;
                    newSetupdialog.model = FirstPageActivity.projectName;
                    if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                        final float screenwp = bluetoothdevmanager.screenwp;
                        bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                        bluetoothdevmanager.screenhp = screenwp;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append((int) bluetoothdevmanager.screenwp);
                    sb2.append("x");
                    sb2.append((int) bluetoothdevmanager.screenhp);
                    newSetupdialog.resolution = sb2.toString();
                    newSetupdialog.location = "F1";
                    MyLog.i("sync_tag", "----------\u5f00\u59cb\u9884\u8bbe\u4e91\u540c\u6b65");
                    HttpUtil.HttpURLConnectionGet(newSetupdialog.myhandler, HttpUrlConfig.getOfficialFileDownloadRul(newSetupdialog.platform, newSetupdialog.deviceType, newSetupdialog.model, newSetupdialog.location, newSetupdialog.resolution), 201828, 201829);
                    presetCloudSyncDialog.this.dismiss();
                }
            });
            this.tv_cancel.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    presetCloudSyncDialog.this.dismiss();
                }
            });
        }
    }

    public class saveMapErroeloggwindow extends PopupWindow {
        private Context mContext;

        public saveMapErroeloggwindow(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            this.setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.savedialog3, (ViewGroup) null));
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
        }
    }

    public class saveNowDialoggwindow extends PopupWindow {
        private Context mContext;
        private TextView tv_on;
        private TextView tv_sure;

        public saveNowDialoggwindow(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.savedialog7, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
            this.tv_on = (TextView) inflate.findViewById(R.id.tv_on);
            (this.tv_sure = (TextView) inflate.findViewById(R.id.tv_sure)).setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {
                    newSetupdialog.this.changeData();
                    saveNowDialoggwindow.this.dismiss();
                    if (newSetupdialog.mLoadingDialog == null) {
                        newSetupdialog.mLoadingDialog = new loadingDialog(newSetupdialog.this.mcontext);
                    }
                    newSetupdialog.mLoadingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                    newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                }
            });
            this.tv_on.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    saveNowDialoggwindow.this.dismiss();
                }
            });
        }
    }

    public class setupdialogwindow extends PopupWindow {
        ListAdapter listAdapter;
        ListView listView;
        private Context mContext;
        private ListView mListView;
        CheckBox mbox;
        CheckBox mbox1;
        CheckBox mrumble;
        TextView mtextview;
        final View mv;
        int pos;
        TextView reverse;
        TextView title;
        TextView trumble;

        public setupdialogwindow(final Context mContext, final View mv) {
            super(mContext);
            this.pos = -1;
            this.mv = mv;
            this.mContext = mContext;
            this.init();
        }

        private void init() {
            this.pos = this.mv.getId();
            Setupitem[] array;
            if (this.pos != 41 && this.pos != 130 && this.pos != 160) {
                if (this.pos != 104 && this.pos != 106 && this.pos != 135 && this.pos != 136 && this.pos != 165 && this.pos != 166 && this.pos != 259 && this.pos != 260 && this.pos != 289 && this.pos != 290 && this.pos != 319 && this.pos != 320 && this.pos != 349 && this.pos != 350) {
                    if ((this.pos >= 30 || this.pos == 23) && (this.pos <= 106 || this.pos >= 167) && (this.pos <= 230 || this.pos >= 291) && this.pos != 96 && this.pos != 97 && this.pos != 42 && (this.pos <= 290 || this.pos >= 321) && (this.pos <= 320 || this.pos >= 351)) {
                        return;
                    }
                    array = new Setupitem[]{new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_36)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_37)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_38)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_42)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_43))};
                } else {
                    array = new Setupitem[]{new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_36)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_38)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_42)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_43))};
                }
            } else {
                array = new Setupitem[]{new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_36)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_37))};
            }
            final ArrayList<Setupitem> list = new ArrayList<Setupitem>();
            for (int length = array.length, i = 0; i < length; ++i) {
                list.add(array[i]);
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.popup_setup, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(false);
            this.listView = (ListView) inflate.findViewById(R.id.lv_setup);
            final Button button = (Button) inflate.findViewById(R.id.btn_yes);
            final Button button2 = (Button) inflate.findViewById(R.id.btn_no);
            this.mtextview = (TextView) inflate.findViewById(R.id.textView1);
            this.title = (TextView) inflate.findViewById(R.id.titlet);
            (this.reverse = (TextView) inflate.findViewById(R.id.restext)).setText((CharSequence) "");
            final TextView title = this.title;
            final StringBuilder sb = new StringBuilder();
            sb.append(newSetupdialog.mdatasaver[this.mv.getId()].name.toUpperCase());
            sb.append(" ");
            sb.append(newSetupdialog.this.mcontext.getString(R.string.op8));
            title.setText((CharSequence) sb.toString());
            button.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.sure));
            button2.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.cancle));
            (this.listAdapter = new ListAdapter(this.mContext)).setList(list);
            this.listView.setAdapter((ListAdapter) this.listAdapter);
            this.listView.setOnItemClickListener((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                int cont1 = setupdialogwindow.this.listAdapter.getCount();

                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    setupdialogwindow.this.listAdapter.select(n);
                    if (this.cont1 == 2) {
                        if (n == 0) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                            setupdialogwindow.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 1) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        }
                    } else if (this.cont1 == 4) {
                        if (n == 0) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                            setupdialogwindow.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 1) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                            setupdialogwindow.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 2) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_44));
                        }
                        if (n == 3) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_45));
                        }
                    } else {
                        if (n == 0) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                            setupdialogwindow.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 1) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        }
                        if (n == 2) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                            setupdialogwindow.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 3) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_44));
                        }
                        if (n == 4) {
                            setupdialogwindow.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_45));
                        }
                    }
                }
            });
            final int count = this.listAdapter.getCount();
            final int id = this.mv.getId();
            if (count == 2) {
                if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                    final Setupitem setupitem = (Setupitem) this.listAdapter.getItem(0);
                    if (setupitem != null) {
                        setupitem.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 1) {
                    final Setupitem setupitem2 = (Setupitem) this.listAdapter.getItem(1);
                    if (setupitem2 != null) {
                        setupitem2.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        this.reverse.setText((CharSequence) "");
                    }
                }
            } else if (count == 4) {
                if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                    final Setupitem setupitem3 = (Setupitem) this.listAdapter.getItem(0);
                    if (setupitem3 != null) {
                        setupitem3.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 4) {
                    final Setupitem setupitem4 = (Setupitem) this.listAdapter.getItem(1);
                    if (setupitem4 != null) {
                        setupitem4.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 5) {
                    final Setupitem setupitem5 = (Setupitem) this.listAdapter.getItem(2);
                    if (setupitem5 != null) {
                        setupitem5.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_44));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 6) {
                    final Setupitem setupitem6 = (Setupitem) this.listAdapter.getItem(3);
                    if (setupitem6 != null) {
                        setupitem6.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_45));
                        this.reverse.setText((CharSequence) "");
                    }
                }
            } else {
                if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                    final Setupitem setupitem7 = (Setupitem) this.listAdapter.getItem(0);
                    if (setupitem7 != null) {
                        setupitem7.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 1) {
                    final Setupitem setupitem8 = (Setupitem) this.listAdapter.getItem(1);
                    if (setupitem8 != null) {
                        setupitem8.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 4) {
                    final Setupitem setupitem9 = (Setupitem) this.listAdapter.getItem(2);
                    if (setupitem9 != null) {
                        setupitem9.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 5) {
                    final Setupitem setupitem10 = (Setupitem) this.listAdapter.getItem(3);
                    if (setupitem10 != null) {
                        setupitem10.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_44));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 6) {
                    final Setupitem setupitem11 = (Setupitem) this.listAdapter.getItem(4);
                    if (setupitem11 != null) {
                        setupitem11.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_45));
                        this.reverse.setText((CharSequence) "");
                    }
                }
            }
            button.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    final int id = setupdialogwindow.this.mv.getId();
                    final int count = setupdialogwindow.this.listAdapter.getCount();
                    final boolean selected = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(0)).isSelected();
                    final boolean selected2 = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(1)).isSelected();
                    boolean b;
                    boolean b2;
                    if (count == 4) {
                        b = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(2)).isSelected();
                        b2 = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(3)).isSelected();
                    } else {
                        b = false;
                        b2 = false;
                    }
                    boolean selected3;
                    if (count == 5) {
                        b = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(2)).isSelected();
                        b2 = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(3)).isSelected();
                        selected3 = ((Setupitem) setupdialogwindow.this.listAdapter.getItem(4)).isSelected();
                    } else {
                        selected3 = false;
                    }
                    if (selected) {
                        if (newSetupdialog.mdatasaver[id].property == 1 && id != 119 && id != 120 && id != 121 && id != 149 && id != 150 && id != 151) {
                            if (id > 136 && id < 167 && id != 160) {
                                if (id == 165) {
                                    newSetupdialog.mdatasaver[104].property = 0;
                                    newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 166) {
                                    newSetupdialog.mdatasaver[106].property = 0;
                                    newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver = newSetupdialog.mdatasaver;
                                    final int n = id - 137;
                                    mdatasaver[n].property = 0;
                                    newSetupdialog.mdatasaver[n].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver2 = newSetupdialog.mdatasaver;
                                final int n2 = id - 30;
                                mdatasaver2[n2].property = -1;
                                newSetupdialog.mdatasaver[n2].x = -1;
                                newSetupdialog.mdatasaver[n2].y = -1;
                                newSetupdialog.mdatasaver[n2].joystick = "-1";
                            }
                            if (id > 106 && id < 137 && id != 130) {
                                if (id == 135) {
                                    newSetupdialog.mdatasaver[104].property = 0;
                                    newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 136) {
                                    newSetupdialog.mdatasaver[106].property = 0;
                                    newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver3 = newSetupdialog.mdatasaver;
                                    final int n3 = id - 107;
                                    mdatasaver3[n3].property = 0;
                                    newSetupdialog.mdatasaver[n3].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n3].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n3].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver4 = newSetupdialog.mdatasaver;
                                final int n4 = id + 30;
                                mdatasaver4[n4].property = -1;
                                newSetupdialog.mdatasaver[n4].x = -1;
                                newSetupdialog.mdatasaver[n4].y = -1;
                                newSetupdialog.mdatasaver[n4].joystick = "-1";
                            }
                            if (id == 130 || id == 160) {
                                newSetupdialog.mdatasaver[41].property = 0;
                                newSetupdialog.mdatasaver[41].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[41].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[41].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[130].property = -1;
                                newSetupdialog.mdatasaver[130].x = -1;
                                newSetupdialog.mdatasaver[130].y = -1;
                                newSetupdialog.mdatasaver[130].joystick = "-1";
                                newSetupdialog.mdatasaver[160].property = -1;
                                newSetupdialog.mdatasaver[160].x = -1;
                                newSetupdialog.mdatasaver[160].y = -1;
                                newSetupdialog.mdatasaver[160].joystick = "-1";
                            }
                        } else if (newSetupdialog.mdatasaver[id].property == 4 && id != 243 && id != 244 && id != 245 && id != 273 && id != 274 && id != 275) {
                            if (id > 230 && id < 261) {
                                if (id == 259) {
                                    newSetupdialog.mdatasaver[104].property = 0;
                                    newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 260) {
                                    newSetupdialog.mdatasaver[106].property = 0;
                                    newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver5 = newSetupdialog.mdatasaver;
                                    final int n5 = id - 231;
                                    mdatasaver5[n5].property = 0;
                                    newSetupdialog.mdatasaver[n5].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n5].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n5].name = newSetupdialog.mdatasaver[id].name;
                                }
                                final DataSaverM[] mdatasaver6 = newSetupdialog.mdatasaver;
                                final int n6 = id + 30;
                                mdatasaver6[n6].property = -1;
                                newSetupdialog.mdatasaver[n6].x = -1;
                                newSetupdialog.mdatasaver[n6].y = -1;
                                newSetupdialog.mdatasaver[n6].joystick = "-1";
                            }
                            if (id > 260 && id < 291) {
                                if (id == 289) {
                                    newSetupdialog.mdatasaver[104].property = 0;
                                    newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 290) {
                                    newSetupdialog.mdatasaver[106].property = 0;
                                    newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver7 = newSetupdialog.mdatasaver;
                                    final int n7 = id - 261;
                                    mdatasaver7[n7].property = 0;
                                    newSetupdialog.mdatasaver[n7].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n7].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n7].name = newSetupdialog.mdatasaver[id].name;
                                }
                                final DataSaverM[] mdatasaver8 = newSetupdialog.mdatasaver;
                                final int n8 = id - 30;
                                mdatasaver8[n8].property = -1;
                                newSetupdialog.mdatasaver[n8].x = -1;
                                newSetupdialog.mdatasaver[n8].y = -1;
                                newSetupdialog.mdatasaver[n8].joystick = "-1";
                            }
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        } else if (newSetupdialog.mdatasaver[id].property == 5 && id != 303 && id != 304 && id != 305) {
                            if (id == 319) {
                                newSetupdialog.mdatasaver[104].property = 0;
                                newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                            } else if (id == 320) {
                                newSetupdialog.mdatasaver[106].property = 0;
                                newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                            } else {
                                final DataSaverM[] mdatasaver9 = newSetupdialog.mdatasaver;
                                final int n9 = id - 291;
                                mdatasaver9[n9].property = 0;
                                newSetupdialog.mdatasaver[n9].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n9].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n9].name = newSetupdialog.mdatasaver[id].name;
                            }
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        } else if (newSetupdialog.mdatasaver[id].property == 6 && id != 333 && id != 334 && id != 335) {
                            if (id == 349) {
                                newSetupdialog.mdatasaver[104].property = 0;
                                newSetupdialog.mdatasaver[104].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[104].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[104].name = newSetupdialog.mdatasaver[id].name;
                            } else if (id == 350) {
                                newSetupdialog.mdatasaver[106].property = 0;
                                newSetupdialog.mdatasaver[106].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[106].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[106].name = newSetupdialog.mdatasaver[id].name;
                            } else {
                                final DataSaverM[] mdatasaver10 = newSetupdialog.mdatasaver;
                                final int n10 = id - 321;
                                mdatasaver10[n10].property = 0;
                                newSetupdialog.mdatasaver[n10].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n10].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n10].name = newSetupdialog.mdatasaver[id].name;
                            }
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        } else {
                            newSetupdialog.mdatasaver[id].property = 0;
                        }
                        if (id == 41) {
                            newSetupdialog.mdatasaver[id].property = 0;
                            newSetupdialog.mdatasaver[id].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[id].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[id].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[130].property = -1;
                            newSetupdialog.mdatasaver[130].x = -1;
                            newSetupdialog.mdatasaver[130].y = -1;
                            newSetupdialog.mdatasaver[130].joystick = "-1";
                            newSetupdialog.mdatasaver[160].property = -1;
                            newSetupdialog.mdatasaver[160].x = -1;
                            newSetupdialog.mdatasaver[160].y = -1;
                            newSetupdialog.mdatasaver[160].joystick = "-1";
                        }
                        if (id == 96 || id == 119 || id == 149 || id == 243 || id == 273 || id == 303 || id == 333) {
                            newSetupdialog.mdatasaver[96].property = 0;
                            newSetupdialog.mdatasaver[96].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[96].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[96].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[119].property = -1;
                            newSetupdialog.mdatasaver[119].x = -1;
                            newSetupdialog.mdatasaver[119].y = -1;
                            newSetupdialog.mdatasaver[119].joystick = "-1";
                            newSetupdialog.mdatasaver[149].property = -1;
                            newSetupdialog.mdatasaver[149].x = -1;
                            newSetupdialog.mdatasaver[149].y = -1;
                            newSetupdialog.mdatasaver[149].joystick = "-1";
                            newSetupdialog.mdatasaver[243].property = -1;
                            newSetupdialog.mdatasaver[243].x = -1;
                            newSetupdialog.mdatasaver[243].y = -1;
                            newSetupdialog.mdatasaver[243].joystick = "-1";
                            newSetupdialog.mdatasaver[273].property = -1;
                            newSetupdialog.mdatasaver[273].x = -1;
                            newSetupdialog.mdatasaver[273].y = -1;
                            newSetupdialog.mdatasaver[273].joystick = "-1";
                            newSetupdialog.mdatasaver[303].property = -1;
                            newSetupdialog.mdatasaver[303].x = -1;
                            newSetupdialog.mdatasaver[303].y = -1;
                            newSetupdialog.mdatasaver[303].joystick = "-1";
                            newSetupdialog.mdatasaver[333].property = -1;
                            newSetupdialog.mdatasaver[333].x = -1;
                            newSetupdialog.mdatasaver[333].y = -1;
                            newSetupdialog.mdatasaver[333].joystick = "-1";
                        }
                        if (id == 97 || id == 120 || id == 150 || id == 244 || id == 274 || id == 304 || id == 334) {
                            newSetupdialog.mdatasaver[97].property = 0;
                            newSetupdialog.mdatasaver[97].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[97].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[97].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[120].property = -1;
                            newSetupdialog.mdatasaver[120].x = -1;
                            newSetupdialog.mdatasaver[120].y = -1;
                            newSetupdialog.mdatasaver[120].joystick = "-1";
                            newSetupdialog.mdatasaver[150].property = -1;
                            newSetupdialog.mdatasaver[150].x = -1;
                            newSetupdialog.mdatasaver[150].y = -1;
                            newSetupdialog.mdatasaver[150].joystick = "-1";
                            newSetupdialog.mdatasaver[244].property = -1;
                            newSetupdialog.mdatasaver[244].x = -1;
                            newSetupdialog.mdatasaver[244].y = -1;
                            newSetupdialog.mdatasaver[244].joystick = "-1";
                            newSetupdialog.mdatasaver[274].property = -1;
                            newSetupdialog.mdatasaver[274].x = -1;
                            newSetupdialog.mdatasaver[274].y = -1;
                            newSetupdialog.mdatasaver[274].joystick = "-1";
                            newSetupdialog.mdatasaver[304].property = -1;
                            newSetupdialog.mdatasaver[304].x = -1;
                            newSetupdialog.mdatasaver[304].y = -1;
                            newSetupdialog.mdatasaver[304].joystick = "-1";
                            newSetupdialog.mdatasaver[334].property = -1;
                            newSetupdialog.mdatasaver[334].x = -1;
                            newSetupdialog.mdatasaver[334].y = -1;
                            newSetupdialog.mdatasaver[334].joystick = "-1";
                        }
                        if (id == 42 || id == 121 || id == 151 || id == 245 || id == 275 || id == 305 || id == 335) {
                            newSetupdialog.mdatasaver[42].property = 0;
                            newSetupdialog.mdatasaver[42].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[42].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[42].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[121].property = -1;
                            newSetupdialog.mdatasaver[121].x = -1;
                            newSetupdialog.mdatasaver[121].y = -1;
                            newSetupdialog.mdatasaver[121].joystick = "-1";
                            newSetupdialog.mdatasaver[151].property = -1;
                            newSetupdialog.mdatasaver[151].x = -1;
                            newSetupdialog.mdatasaver[151].y = -1;
                            newSetupdialog.mdatasaver[151].joystick = "-1";
                            newSetupdialog.mdatasaver[245].property = -1;
                            newSetupdialog.mdatasaver[245].x = -1;
                            newSetupdialog.mdatasaver[245].y = -1;
                            newSetupdialog.mdatasaver[245].joystick = "-1";
                            newSetupdialog.mdatasaver[275].property = -1;
                            newSetupdialog.mdatasaver[275].x = -1;
                            newSetupdialog.mdatasaver[275].y = -1;
                            newSetupdialog.mdatasaver[275].joystick = "-1";
                            newSetupdialog.mdatasaver[305].property = -1;
                            newSetupdialog.mdatasaver[305].x = -1;
                            newSetupdialog.mdatasaver[305].y = -1;
                            newSetupdialog.mdatasaver[305].joystick = "-1";
                            newSetupdialog.mdatasaver[335].property = -1;
                            newSetupdialog.mdatasaver[335].x = -1;
                            newSetupdialog.mdatasaver[335].y = -1;
                            newSetupdialog.mdatasaver[335].joystick = "-1";
                        }
                    }
                    if (selected2) {
                        if (count == 4) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id == 104) {
                                    newSetupdialog.mdatasaver[259].property = 4;
                                    newSetupdialog.mdatasaver[259].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[259].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[259].joystick = "2";
                                    newSetupdialog.mdatasaver[259].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[289].property = 4;
                                    newSetupdialog.mdatasaver[289].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[289].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[289].joystick = "-1";
                                    newSetupdialog.mdatasaver[289].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 106) {
                                    newSetupdialog.mdatasaver[260].property = 4;
                                    newSetupdialog.mdatasaver[260].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[260].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[260].joystick = "2";
                                    newSetupdialog.mdatasaver[260].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[290].property = 4;
                                    newSetupdialog.mdatasaver[290].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[290].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[290].joystick = "-1";
                                    newSetupdialog.mdatasaver[290].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 5) {
                                final DataSaverM[] mdatasaver11 = newSetupdialog.mdatasaver;
                                final int n11 = id - 60;
                                mdatasaver11[n11].property = 4;
                                newSetupdialog.mdatasaver[n11].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n11].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n11].joystick = "-1";
                                newSetupdialog.mdatasaver[n11].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver12 = newSetupdialog.mdatasaver;
                                final int n12 = id - 30;
                                mdatasaver12[n12].property = 4;
                                newSetupdialog.mdatasaver[n12].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n12].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n12].joystick = "-1";
                                newSetupdialog.mdatasaver[n12].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 6) {
                                final DataSaverM[] mdatasaver13 = newSetupdialog.mdatasaver;
                                final int n13 = id - 60;
                                mdatasaver13[n13].property = 4;
                                newSetupdialog.mdatasaver[n13].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n13].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n13].joystick = "-1";
                                newSetupdialog.mdatasaver[n13].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver14 = newSetupdialog.mdatasaver;
                                final int n14 = id - 90;
                                mdatasaver14[n14].property = 4;
                                newSetupdialog.mdatasaver[n14].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n14].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n14].joystick = "-1";
                                newSetupdialog.mdatasaver[n14].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        } else if (count == 5) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id != 96 && id != 97 && id != 42) {
                                    if (id == 104) {
                                        newSetupdialog.mdatasaver[135].property = 1;
                                        newSetupdialog.mdatasaver[135].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[135].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[135].joystick = "2";
                                        newSetupdialog.mdatasaver[135].name = newSetupdialog.mdatasaver[id].name;
                                        newSetupdialog.mdatasaver[165].property = 1;
                                        newSetupdialog.mdatasaver[165].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[165].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[165].joystick = "-1";
                                        newSetupdialog.mdatasaver[165].name = newSetupdialog.mdatasaver[id].name;
                                    } else if (id == 106) {
                                        newSetupdialog.mdatasaver[136].property = 1;
                                        newSetupdialog.mdatasaver[136].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[136].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[136].joystick = "2";
                                        newSetupdialog.mdatasaver[136].name = newSetupdialog.mdatasaver[id].name;
                                        newSetupdialog.mdatasaver[166].property = 1;
                                        newSetupdialog.mdatasaver[166].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[166].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[166].joystick = "-1";
                                        newSetupdialog.mdatasaver[166].name = newSetupdialog.mdatasaver[id].name;
                                    } else {
                                        final DataSaverM[] mdatasaver15 = newSetupdialog.mdatasaver;
                                        final int n15 = id + 107;
                                        mdatasaver15[n15].property = 1;
                                        newSetupdialog.mdatasaver[n15].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[n15].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[n15].joystick = "2";
                                        newSetupdialog.mdatasaver[n15].name = newSetupdialog.mdatasaver[id].name;
                                        final DataSaverM[] mdatasaver16 = newSetupdialog.mdatasaver;
                                        final int n16 = id + 137;
                                        mdatasaver16[n16].property = 1;
                                        newSetupdialog.mdatasaver[n16].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[n16].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[n16].joystick = "-1";
                                        newSetupdialog.mdatasaver[n16].name = newSetupdialog.mdatasaver[id].name;
                                    }
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 96) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[243].property = -1;
                                    newSetupdialog.mdatasaver[243].x = -1;
                                    newSetupdialog.mdatasaver[243].y = -1;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].property = -1;
                                    newSetupdialog.mdatasaver[273].x = -1;
                                    newSetupdialog.mdatasaver[273].y = -1;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 97) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[244].property = -1;
                                    newSetupdialog.mdatasaver[244].x = -1;
                                    newSetupdialog.mdatasaver[244].y = -1;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].property = -1;
                                    newSetupdialog.mdatasaver[274].x = -1;
                                    newSetupdialog.mdatasaver[274].y = -1;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 42) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[245].property = -1;
                                    newSetupdialog.mdatasaver[245].x = -1;
                                    newSetupdialog.mdatasaver[245].y = -1;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].property = -1;
                                    newSetupdialog.mdatasaver[275].x = -1;
                                    newSetupdialog.mdatasaver[275].y = -1;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 4) {
                                if (id > 230 && id < 261 && id != 243 && id != 244 && id != 245) {
                                    final DataSaverM[] mdatasaver17 = newSetupdialog.mdatasaver;
                                    final int n17 = id - 124;
                                    mdatasaver17[n17].property = 1;
                                    newSetupdialog.mdatasaver[n17].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n17].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n17].joystick = "2";
                                    newSetupdialog.mdatasaver[n17].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver18 = newSetupdialog.mdatasaver;
                                    final int n18 = id - 94;
                                    mdatasaver18[n18].property = 1;
                                    newSetupdialog.mdatasaver[n18].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n18].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n18].joystick = "-1";
                                    newSetupdialog.mdatasaver[n18].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver19 = newSetupdialog.mdatasaver;
                                    final int n19 = id + 30;
                                    mdatasaver19[n19].property = -1;
                                    newSetupdialog.mdatasaver[n19].x = -1;
                                    newSetupdialog.mdatasaver[n19].y = -1;
                                    newSetupdialog.mdatasaver[n19].joystick = "-1";
                                }
                                if (id > 260 && id < 291 && id != 273 && id != 274 && id != 275) {
                                    final DataSaverM[] mdatasaver20 = newSetupdialog.mdatasaver;
                                    final int n20 = id - 154;
                                    mdatasaver20[n20].property = 1;
                                    newSetupdialog.mdatasaver[n20].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n20].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n20].joystick = "2";
                                    newSetupdialog.mdatasaver[n20].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver21 = newSetupdialog.mdatasaver;
                                    final int n21 = id - 124;
                                    mdatasaver21[n21].property = 1;
                                    newSetupdialog.mdatasaver[n21].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n21].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n21].joystick = "-1";
                                    newSetupdialog.mdatasaver[n21].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver22 = newSetupdialog.mdatasaver;
                                    final int n22 = id - 30;
                                    mdatasaver22[n22].property = -1;
                                    newSetupdialog.mdatasaver[n22].x = -1;
                                    newSetupdialog.mdatasaver[n22].y = -1;
                                    newSetupdialog.mdatasaver[n22].joystick = "-1";
                                }
                                if (id == 243 || id == 273) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[243].property = -1;
                                    newSetupdialog.mdatasaver[243].x = -1;
                                    newSetupdialog.mdatasaver[243].y = -1;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].property = -1;
                                    newSetupdialog.mdatasaver[273].x = -1;
                                    newSetupdialog.mdatasaver[273].y = -1;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[96].property = -1;
                                    newSetupdialog.mdatasaver[96].x = -1;
                                    newSetupdialog.mdatasaver[96].y = -1;
                                    newSetupdialog.mdatasaver[96].joystick = "-1";
                                }
                                if (id == 244 || id == 274) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[244].property = -1;
                                    newSetupdialog.mdatasaver[244].x = -1;
                                    newSetupdialog.mdatasaver[244].y = -1;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].property = -1;
                                    newSetupdialog.mdatasaver[274].x = -1;
                                    newSetupdialog.mdatasaver[274].y = -1;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 245 || id == 275) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[245].property = -1;
                                    newSetupdialog.mdatasaver[245].x = -1;
                                    newSetupdialog.mdatasaver[245].y = -1;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].property = -1;
                                    newSetupdialog.mdatasaver[275].x = -1;
                                    newSetupdialog.mdatasaver[275].y = -1;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 5) {
                                if (id > 290 && id < 321 && id != 303 && id != 304 && id != 305) {
                                    final DataSaverM[] mdatasaver23 = newSetupdialog.mdatasaver;
                                    final int n23 = id - 184;
                                    mdatasaver23[n23].property = 1;
                                    newSetupdialog.mdatasaver[n23].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n23].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n23].joystick = "2";
                                    newSetupdialog.mdatasaver[n23].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver24 = newSetupdialog.mdatasaver;
                                    final int n24 = id - 154;
                                    mdatasaver24[n24].property = 1;
                                    newSetupdialog.mdatasaver[n24].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n24].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n24].joystick = "-1";
                                    newSetupdialog.mdatasaver[n24].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 303) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 304) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 305) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 6) {
                                if (id > 320 && id < 351 && id != 333 && id != 334 && id != 335) {
                                    final DataSaverM[] mdatasaver25 = newSetupdialog.mdatasaver;
                                    final int n25 = id - 214;
                                    mdatasaver25[n25].property = 1;
                                    newSetupdialog.mdatasaver[n25].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n25].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n25].joystick = "2";
                                    newSetupdialog.mdatasaver[n25].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver26 = newSetupdialog.mdatasaver;
                                    final int n26 = id - 184;
                                    mdatasaver26[n26].property = 1;
                                    newSetupdialog.mdatasaver[n26].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n26].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n26].joystick = "-1";
                                    newSetupdialog.mdatasaver[n26].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 333) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 334) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                }
                                if (id == 335) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        } else if (count == 2 && id == 41) {
                            newSetupdialog.mdatasaver[130].property = 1;
                            newSetupdialog.mdatasaver[130].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[130].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[130].joystick = "2";
                            newSetupdialog.mdatasaver[130].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[160].property = 1;
                            newSetupdialog.mdatasaver[160].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[160].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[160].joystick = "-1";
                            newSetupdialog.mdatasaver[160].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        }
                    }
                    if (b) {
                        if (count == 4) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id == 104) {
                                    newSetupdialog.mdatasaver[319].property = 5;
                                    newSetupdialog.mdatasaver[319].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[319].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[319].joystick = "-1";
                                    newSetupdialog.mdatasaver[319].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 106) {
                                    newSetupdialog.mdatasaver[320].property = 5;
                                    newSetupdialog.mdatasaver[320].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[320].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[320].joystick = "-1";
                                    newSetupdialog.mdatasaver[320].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver27 = newSetupdialog.mdatasaver;
                                    final int n27 = id + 291;
                                    mdatasaver27[n27].property = 5;
                                    newSetupdialog.mdatasaver[n27].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n27].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n27].joystick = "-1";
                                    newSetupdialog.mdatasaver[n27].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 4) {
                                if (id > 230 && id < 261) {
                                    final DataSaverM[] mdatasaver28 = newSetupdialog.mdatasaver;
                                    final int n28 = id + 60;
                                    mdatasaver28[n28].property = 5;
                                    newSetupdialog.mdatasaver[n28].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n28].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n28].joystick = "-1";
                                    newSetupdialog.mdatasaver[n28].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver29 = newSetupdialog.mdatasaver;
                                    final int n29 = id + 30;
                                    mdatasaver29[n29].property = -1;
                                    newSetupdialog.mdatasaver[n29].x = -1;
                                    newSetupdialog.mdatasaver[n29].y = -1;
                                    newSetupdialog.mdatasaver[n29].joystick = "-1";
                                    newSetupdialog.mdatasaver[n29].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id > 260 && id < 291) {
                                    final DataSaverM[] mdatasaver30 = newSetupdialog.mdatasaver;
                                    final int n30 = id + 30;
                                    mdatasaver30[n30].property = 5;
                                    newSetupdialog.mdatasaver[n30].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n30].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n30].joystick = "-1";
                                    newSetupdialog.mdatasaver[n30].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver31 = newSetupdialog.mdatasaver;
                                    final int n31 = id - 30;
                                    mdatasaver31[n31].property = -1;
                                    newSetupdialog.mdatasaver[n31].x = -1;
                                    newSetupdialog.mdatasaver[n31].y = -1;
                                    newSetupdialog.mdatasaver[n31].joystick = "-1";
                                    newSetupdialog.mdatasaver[n31].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 6) {
                                final DataSaverM[] mdatasaver32 = newSetupdialog.mdatasaver;
                                final int n32 = id - 30;
                                mdatasaver32[n32].property = 5;
                                newSetupdialog.mdatasaver[n32].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n32].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n32].joystick = "-1";
                                newSetupdialog.mdatasaver[n32].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].name = "-1";
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        } else if (count == 5) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id != 96 && id != 97 && id != 42) {
                                    if (id == 104) {
                                        newSetupdialog.mdatasaver[259].property = 4;
                                        newSetupdialog.mdatasaver[259].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[259].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[259].joystick = "-1";
                                        newSetupdialog.mdatasaver[259].name = newSetupdialog.mdatasaver[id].name;
                                        newSetupdialog.mdatasaver[289].property = 4;
                                        newSetupdialog.mdatasaver[289].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[289].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[289].joystick = "-1";
                                        newSetupdialog.mdatasaver[289].name = newSetupdialog.mdatasaver[id].name;
                                    } else if (id == 106) {
                                        newSetupdialog.mdatasaver[260].property = 4;
                                        newSetupdialog.mdatasaver[260].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[260].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[260].joystick = "-1";
                                        newSetupdialog.mdatasaver[260].name = newSetupdialog.mdatasaver[id].name;
                                        newSetupdialog.mdatasaver[290].property = 4;
                                        newSetupdialog.mdatasaver[290].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[290].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[290].joystick = "-1";
                                        newSetupdialog.mdatasaver[290].name = newSetupdialog.mdatasaver[id].name;
                                    } else {
                                        final DataSaverM[] mdatasaver33 = newSetupdialog.mdatasaver;
                                        final int n33 = id + 231;
                                        mdatasaver33[n33].property = 4;
                                        newSetupdialog.mdatasaver[n33].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[n33].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[n33].joystick = "-1";
                                        newSetupdialog.mdatasaver[n33].name = newSetupdialog.mdatasaver[id].name;
                                        final DataSaverM[] mdatasaver34 = newSetupdialog.mdatasaver;
                                        final int n34 = id + 261;
                                        mdatasaver34[n34].property = 4;
                                        newSetupdialog.mdatasaver[n34].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[n34].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[n34].joystick = "-1";
                                        newSetupdialog.mdatasaver[n34].name = newSetupdialog.mdatasaver[id].name;
                                    }
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 96) {
                                    newSetupdialog.mdatasaver[119].property = -1;
                                    newSetupdialog.mdatasaver[119].x = -1;
                                    newSetupdialog.mdatasaver[119].y = -1;
                                    newSetupdialog.mdatasaver[119].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].property = -1;
                                    newSetupdialog.mdatasaver[149].x = -1;
                                    newSetupdialog.mdatasaver[149].y = -1;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[243].property = 4;
                                    newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[273].property = 4;
                                    newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[96].property = -1;
                                    newSetupdialog.mdatasaver[96].x = -1;
                                    newSetupdialog.mdatasaver[96].y = -1;
                                    newSetupdialog.mdatasaver[96].joystick = "-1";
                                }
                                if (id == 97) {
                                    newSetupdialog.mdatasaver[120].property = -1;
                                    newSetupdialog.mdatasaver[120].x = -1;
                                    newSetupdialog.mdatasaver[120].y = -1;
                                    newSetupdialog.mdatasaver[120].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].property = -1;
                                    newSetupdialog.mdatasaver[150].x = -1;
                                    newSetupdialog.mdatasaver[150].y = -1;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[244].property = 4;
                                    newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[274].property = 4;
                                    newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 42) {
                                    newSetupdialog.mdatasaver[121].property = -1;
                                    newSetupdialog.mdatasaver[121].x = -1;
                                    newSetupdialog.mdatasaver[121].y = -1;
                                    newSetupdialog.mdatasaver[121].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].property = -1;
                                    newSetupdialog.mdatasaver[151].x = -1;
                                    newSetupdialog.mdatasaver[151].y = -1;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[245].property = 4;
                                    newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[275].property = 4;
                                    newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 1) {
                                if (id > 136 && id < 167 && id != 149 && id != 150 && id != 151) {
                                    final DataSaverM[] mdatasaver35 = newSetupdialog.mdatasaver;
                                    final int n35 = id + 94;
                                    mdatasaver35[n35].property = 4;
                                    newSetupdialog.mdatasaver[n35].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n35].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n35].joystick = "-1";
                                    newSetupdialog.mdatasaver[n35].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver36 = newSetupdialog.mdatasaver;
                                    final int n36 = id + 124;
                                    mdatasaver36[n36].property = 4;
                                    newSetupdialog.mdatasaver[n36].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n36].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n36].joystick = "-1";
                                    newSetupdialog.mdatasaver[n36].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                    final DataSaverM[] mdatasaver37 = newSetupdialog.mdatasaver;
                                    final int n37 = id - 30;
                                    mdatasaver37[n37].property = -1;
                                    newSetupdialog.mdatasaver[n37].x = -1;
                                    newSetupdialog.mdatasaver[n37].y = -1;
                                    newSetupdialog.mdatasaver[n37].joystick = "-1";
                                }
                                if (id > 106 && id < 137 && id != 119 && id != 120 && id != 121) {
                                    final DataSaverM[] mdatasaver38 = newSetupdialog.mdatasaver;
                                    final int n38 = id + 124;
                                    mdatasaver38[n38].property = 4;
                                    newSetupdialog.mdatasaver[n38].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n38].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n38].joystick = "-1";
                                    newSetupdialog.mdatasaver[n38].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver39 = newSetupdialog.mdatasaver;
                                    final int n39 = id + 154;
                                    mdatasaver39[n39].property = 4;
                                    newSetupdialog.mdatasaver[n39].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n39].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n39].joystick = "-1";
                                    newSetupdialog.mdatasaver[n39].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                    final DataSaverM[] mdatasaver40 = newSetupdialog.mdatasaver;
                                    final int n40 = id + 30;
                                    mdatasaver40[n40].property = -1;
                                    newSetupdialog.mdatasaver[n40].x = -1;
                                    newSetupdialog.mdatasaver[n40].y = -1;
                                    newSetupdialog.mdatasaver[n40].joystick = "-1";
                                }
                                if (id == 119 || id == 149) {
                                    newSetupdialog.mdatasaver[243].property = 4;
                                    newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[273].property = 4;
                                    newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[119].property = -1;
                                    newSetupdialog.mdatasaver[119].x = -1;
                                    newSetupdialog.mdatasaver[119].y = -1;
                                    newSetupdialog.mdatasaver[119].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].property = -1;
                                    newSetupdialog.mdatasaver[149].x = -1;
                                    newSetupdialog.mdatasaver[149].y = -1;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[96].property = -1;
                                    newSetupdialog.mdatasaver[96].x = -1;
                                    newSetupdialog.mdatasaver[96].y = -1;
                                    newSetupdialog.mdatasaver[96].joystick = "-1";
                                }
                                if (id == 120 || id == 150) {
                                    newSetupdialog.mdatasaver[244].property = 4;
                                    newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[274].property = 4;
                                    newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[120].property = -1;
                                    newSetupdialog.mdatasaver[120].x = -1;
                                    newSetupdialog.mdatasaver[120].y = -1;
                                    newSetupdialog.mdatasaver[120].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].property = -1;
                                    newSetupdialog.mdatasaver[150].x = -1;
                                    newSetupdialog.mdatasaver[150].y = -1;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 121 || id == 151) {
                                    newSetupdialog.mdatasaver[245].property = 4;
                                    newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[275].property = 4;
                                    newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[121].property = -1;
                                    newSetupdialog.mdatasaver[121].x = -1;
                                    newSetupdialog.mdatasaver[121].y = -1;
                                    newSetupdialog.mdatasaver[121].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].property = -1;
                                    newSetupdialog.mdatasaver[151].x = -1;
                                    newSetupdialog.mdatasaver[151].y = -1;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 5) {
                                if (id > 290 && id < 321 && id != 303 && id != 304 && id != 305) {
                                    final DataSaverM[] mdatasaver41 = newSetupdialog.mdatasaver;
                                    final int n41 = id - 60;
                                    mdatasaver41[n41].property = 4;
                                    newSetupdialog.mdatasaver[n41].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n41].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n41].joystick = "-1";
                                    newSetupdialog.mdatasaver[n41].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver42 = newSetupdialog.mdatasaver;
                                    final int n42 = id - 30;
                                    mdatasaver42[n42].property = 4;
                                    newSetupdialog.mdatasaver[n42].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n42].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n42].joystick = "-1";
                                    newSetupdialog.mdatasaver[n42].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 303) {
                                    newSetupdialog.mdatasaver[243].property = 4;
                                    newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[273].property = 4;
                                    newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[303].property = -1;
                                    newSetupdialog.mdatasaver[303].x = -1;
                                    newSetupdialog.mdatasaver[303].y = -1;
                                    newSetupdialog.mdatasaver[303].joystick = "-1";
                                }
                                if (id == 304) {
                                    newSetupdialog.mdatasaver[244].property = 4;
                                    newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[274].property = 4;
                                    newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[304].property = -1;
                                    newSetupdialog.mdatasaver[304].x = -1;
                                    newSetupdialog.mdatasaver[304].y = -1;
                                    newSetupdialog.mdatasaver[304].joystick = "-1";
                                }
                                if (id == 305) {
                                    newSetupdialog.mdatasaver[245].property = 4;
                                    newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[275].property = 4;
                                    newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[305].property = -1;
                                    newSetupdialog.mdatasaver[305].x = -1;
                                    newSetupdialog.mdatasaver[305].y = -1;
                                    newSetupdialog.mdatasaver[305].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 6) {
                                if (id > 320 && id < 351 && id != 333 && id != 334 && id != 335) {
                                    final DataSaverM[] mdatasaver43 = newSetupdialog.mdatasaver;
                                    final int n43 = id - 60;
                                    mdatasaver43[n43].property = 4;
                                    newSetupdialog.mdatasaver[n43].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n43].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n43].joystick = "-1";
                                    newSetupdialog.mdatasaver[n43].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver44 = newSetupdialog.mdatasaver;
                                    final int n44 = id - 90;
                                    mdatasaver44[n44].property = 4;
                                    newSetupdialog.mdatasaver[n44].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n44].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n44].joystick = "-1";
                                    newSetupdialog.mdatasaver[n44].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 333) {
                                    newSetupdialog.mdatasaver[243].property = 4;
                                    newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[273].property = 4;
                                    newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[333].property = -1;
                                    newSetupdialog.mdatasaver[333].x = -1;
                                    newSetupdialog.mdatasaver[333].y = -1;
                                    newSetupdialog.mdatasaver[333].joystick = "-1";
                                }
                                if (id == 334) {
                                    newSetupdialog.mdatasaver[244].property = 4;
                                    newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[274].property = 4;
                                    newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[334].property = -1;
                                    newSetupdialog.mdatasaver[334].x = -1;
                                    newSetupdialog.mdatasaver[334].y = -1;
                                    newSetupdialog.mdatasaver[334].joystick = "-1";
                                }
                                if (id == 335) {
                                    newSetupdialog.mdatasaver[245].property = 4;
                                    newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[275].property = 4;
                                    newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[335].property = -1;
                                    newSetupdialog.mdatasaver[335].x = -1;
                                    newSetupdialog.mdatasaver[335].y = -1;
                                    newSetupdialog.mdatasaver[335].joystick = "-1";
                                }
                            }
                        }
                    }
                    if (b2) {
                        if (count == 4) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id == 104) {
                                    newSetupdialog.mdatasaver[349].property = 6;
                                    newSetupdialog.mdatasaver[349].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[349].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[349].joystick = "-1";
                                    newSetupdialog.mdatasaver[349].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 106) {
                                    newSetupdialog.mdatasaver[350].property = 6;
                                    newSetupdialog.mdatasaver[350].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[350].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[350].joystick = "-1";
                                    newSetupdialog.mdatasaver[350].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver45 = newSetupdialog.mdatasaver;
                                    final int n45 = id + 321;
                                    mdatasaver45[n45].property = 6;
                                    newSetupdialog.mdatasaver[n45].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n45].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n45].joystick = "-1";
                                    newSetupdialog.mdatasaver[n45].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (newSetupdialog.mdatasaver[id].property == 4) {
                                if (id > 230 && id < 261) {
                                    final DataSaverM[] mdatasaver46 = newSetupdialog.mdatasaver;
                                    final int n46 = id + 90;
                                    mdatasaver46[n46].property = 6;
                                    newSetupdialog.mdatasaver[n46].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n46].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n46].joystick = "-1";
                                    newSetupdialog.mdatasaver[n46].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver47 = newSetupdialog.mdatasaver;
                                    final int n47 = id + 30;
                                    mdatasaver47[n47].property = -1;
                                    newSetupdialog.mdatasaver[n47].x = -1;
                                    newSetupdialog.mdatasaver[n47].y = -1;
                                    newSetupdialog.mdatasaver[n47].joystick = "-1";
                                    newSetupdialog.mdatasaver[n47].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id > 260 && id < 291) {
                                    final DataSaverM[] mdatasaver48 = newSetupdialog.mdatasaver;
                                    final int n48 = id + 60;
                                    mdatasaver48[n48].property = 6;
                                    newSetupdialog.mdatasaver[n48].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n48].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n48].joystick = "-1";
                                    newSetupdialog.mdatasaver[n48].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver49 = newSetupdialog.mdatasaver;
                                    final int n49 = id - 30;
                                    mdatasaver49[n49].property = -1;
                                    newSetupdialog.mdatasaver[n49].x = -1;
                                    newSetupdialog.mdatasaver[n49].y = -1;
                                    newSetupdialog.mdatasaver[n49].joystick = "-1";
                                    newSetupdialog.mdatasaver[n49].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 5 && id > 290 && id < 321) {
                                final DataSaverM[] mdatasaver50 = newSetupdialog.mdatasaver;
                                final int n50 = id + 30;
                                mdatasaver50[n50].property = 6;
                                newSetupdialog.mdatasaver[n50].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n50].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n50].joystick = "-1";
                                newSetupdialog.mdatasaver[n50].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        } else if (count == 5) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id != 96 && id != 97 && id != 42) {
                                    if (id == 104) {
                                        newSetupdialog.mdatasaver[319].property = 5;
                                        newSetupdialog.mdatasaver[319].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[319].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[319].joystick = "-1";
                                        newSetupdialog.mdatasaver[319].name = newSetupdialog.mdatasaver[id].name;
                                    } else if (id == 106) {
                                        newSetupdialog.mdatasaver[320].property = 5;
                                        newSetupdialog.mdatasaver[320].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[320].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[320].joystick = "-1";
                                        newSetupdialog.mdatasaver[320].name = newSetupdialog.mdatasaver[id].name;
                                    } else {
                                        final DataSaverM[] mdatasaver51 = newSetupdialog.mdatasaver;
                                        final int n51 = id + 291;
                                        mdatasaver51[n51].property = 5;
                                        newSetupdialog.mdatasaver[n51].x = newSetupdialog.mdatasaver[id].x;
                                        newSetupdialog.mdatasaver[n51].y = newSetupdialog.mdatasaver[id].y;
                                        newSetupdialog.mdatasaver[n51].joystick = "-1";
                                        newSetupdialog.mdatasaver[n51].name = newSetupdialog.mdatasaver[id].name;
                                    }
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 96) {
                                    newSetupdialog.mdatasaver[303].property = 5;
                                    newSetupdialog.mdatasaver[303].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[303].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[303].joystick = "-1";
                                    newSetupdialog.mdatasaver[303].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[96].property = -1;
                                    newSetupdialog.mdatasaver[96].x = -1;
                                    newSetupdialog.mdatasaver[96].y = -1;
                                    newSetupdialog.mdatasaver[96].joystick = "-1";
                                }
                                if (id == 97) {
                                    newSetupdialog.mdatasaver[304].property = 5;
                                    newSetupdialog.mdatasaver[304].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[304].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[304].joystick = "-1";
                                    newSetupdialog.mdatasaver[304].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 42) {
                                    newSetupdialog.mdatasaver[305].property = 5;
                                    newSetupdialog.mdatasaver[305].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[305].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[305].joystick = "-1";
                                    newSetupdialog.mdatasaver[305].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 1) {
                                if (id > 136 && id < 167 && id != 149 && id != 150 && id != 151) {
                                    final DataSaverM[] mdatasaver52 = newSetupdialog.mdatasaver;
                                    final int n52 = id + 154;
                                    mdatasaver52[n52].property = 5;
                                    newSetupdialog.mdatasaver[n52].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n52].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n52].joystick = "-1";
                                    newSetupdialog.mdatasaver[n52].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                    final DataSaverM[] mdatasaver53 = newSetupdialog.mdatasaver;
                                    final int n53 = id - 30;
                                    mdatasaver53[n53].property = -1;
                                    newSetupdialog.mdatasaver[n53].x = -1;
                                    newSetupdialog.mdatasaver[n53].y = -1;
                                    newSetupdialog.mdatasaver[n53].joystick = "-1";
                                }
                                if (id > 106 && id < 137 && id != 119 && id != 120 && id != 121) {
                                    final DataSaverM[] mdatasaver54 = newSetupdialog.mdatasaver;
                                    final int n54 = id + 184;
                                    mdatasaver54[n54].property = 5;
                                    newSetupdialog.mdatasaver[n54].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n54].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n54].joystick = "-1";
                                    newSetupdialog.mdatasaver[n54].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                    final DataSaverM[] mdatasaver55 = newSetupdialog.mdatasaver;
                                    final int n55 = id + 30;
                                    mdatasaver55[n55].property = -1;
                                    newSetupdialog.mdatasaver[n55].x = -1;
                                    newSetupdialog.mdatasaver[n55].y = -1;
                                    newSetupdialog.mdatasaver[n55].joystick = "-1";
                                }
                                if (id == 119 || id == 149) {
                                    newSetupdialog.mdatasaver[303].property = 5;
                                    newSetupdialog.mdatasaver[303].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[303].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[303].joystick = "-1";
                                    newSetupdialog.mdatasaver[303].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[119].property = -1;
                                    newSetupdialog.mdatasaver[119].x = -1;
                                    newSetupdialog.mdatasaver[119].y = -1;
                                    newSetupdialog.mdatasaver[119].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].property = -1;
                                    newSetupdialog.mdatasaver[149].x = -1;
                                    newSetupdialog.mdatasaver[149].y = -1;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                }
                                if (id == 120 || id == 150) {
                                    newSetupdialog.mdatasaver[304].property = 5;
                                    newSetupdialog.mdatasaver[304].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[304].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[304].joystick = "-1";
                                    newSetupdialog.mdatasaver[304].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[120].property = -1;
                                    newSetupdialog.mdatasaver[120].x = -1;
                                    newSetupdialog.mdatasaver[120].y = -1;
                                    newSetupdialog.mdatasaver[120].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].property = -1;
                                    newSetupdialog.mdatasaver[150].x = -1;
                                    newSetupdialog.mdatasaver[150].y = -1;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 121 || id == 151) {
                                    newSetupdialog.mdatasaver[305].property = 5;
                                    newSetupdialog.mdatasaver[305].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[305].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[305].joystick = "-1";
                                    newSetupdialog.mdatasaver[305].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[121].property = -1;
                                    newSetupdialog.mdatasaver[121].x = -1;
                                    newSetupdialog.mdatasaver[121].y = -1;
                                    newSetupdialog.mdatasaver[121].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].property = -1;
                                    newSetupdialog.mdatasaver[151].x = -1;
                                    newSetupdialog.mdatasaver[151].y = -1;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 4) {
                                if (id > 230 && id < 261 && id != 243 && id != 244 && id != 245) {
                                    final DataSaverM[] mdatasaver56 = newSetupdialog.mdatasaver;
                                    final int n56 = id + 60;
                                    mdatasaver56[n56].property = 5;
                                    newSetupdialog.mdatasaver[n56].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n56].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n56].joystick = "-1";
                                    newSetupdialog.mdatasaver[n56].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver57 = newSetupdialog.mdatasaver;
                                    final int n57 = id + 30;
                                    mdatasaver57[n57].property = -1;
                                    newSetupdialog.mdatasaver[n57].x = -1;
                                    newSetupdialog.mdatasaver[n57].y = -1;
                                    newSetupdialog.mdatasaver[n57].joystick = "-1";
                                    newSetupdialog.mdatasaver[n57].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id > 260 && id < 291 && id != 273 && id != 274 && id != 275) {
                                    final DataSaverM[] mdatasaver58 = newSetupdialog.mdatasaver;
                                    final int n58 = id + 30;
                                    mdatasaver58[n58].property = 5;
                                    newSetupdialog.mdatasaver[n58].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n58].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n58].joystick = "-1";
                                    newSetupdialog.mdatasaver[n58].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver59 = newSetupdialog.mdatasaver;
                                    final int n59 = id - 30;
                                    mdatasaver59[n59].property = -1;
                                    newSetupdialog.mdatasaver[n59].x = -1;
                                    newSetupdialog.mdatasaver[n59].y = -1;
                                    newSetupdialog.mdatasaver[n59].joystick = "-1";
                                    newSetupdialog.mdatasaver[n59].name = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 243 || id == 273) {
                                    newSetupdialog.mdatasaver[303].property = 5;
                                    newSetupdialog.mdatasaver[303].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[303].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[303].joystick = "-1";
                                    newSetupdialog.mdatasaver[303].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[243].property = -1;
                                    newSetupdialog.mdatasaver[243].x = -1;
                                    newSetupdialog.mdatasaver[243].y = -1;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].property = -1;
                                    newSetupdialog.mdatasaver[273].x = -1;
                                    newSetupdialog.mdatasaver[273].y = -1;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                }
                                if (id == 244 || id == 274) {
                                    newSetupdialog.mdatasaver[304].property = 5;
                                    newSetupdialog.mdatasaver[304].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[304].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[304].joystick = "-1";
                                    newSetupdialog.mdatasaver[304].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[244].property = -1;
                                    newSetupdialog.mdatasaver[244].x = -1;
                                    newSetupdialog.mdatasaver[244].y = -1;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].property = -1;
                                    newSetupdialog.mdatasaver[274].x = -1;
                                    newSetupdialog.mdatasaver[274].y = -1;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                }
                                if (id == 245 || id == 275) {
                                    newSetupdialog.mdatasaver[305].property = 5;
                                    newSetupdialog.mdatasaver[305].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[305].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[305].joystick = "-1";
                                    newSetupdialog.mdatasaver[305].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[245].property = -1;
                                    newSetupdialog.mdatasaver[245].x = -1;
                                    newSetupdialog.mdatasaver[245].y = -1;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].property = -1;
                                    newSetupdialog.mdatasaver[275].x = -1;
                                    newSetupdialog.mdatasaver[275].y = -1;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 6 && id > 320 && id < 351) {
                                final DataSaverM[] mdatasaver60 = newSetupdialog.mdatasaver;
                                final int n60 = id - 30;
                                mdatasaver60[n60].property = 5;
                                newSetupdialog.mdatasaver[n60].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n60].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n60].joystick = "-1";
                                newSetupdialog.mdatasaver[n60].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        }
                    }
                    if (selected3) {
                        if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                            if (id != 96 && id != 97 && id != 42) {
                                if (id == 104) {
                                    newSetupdialog.mdatasaver[349].property = 6;
                                    newSetupdialog.mdatasaver[349].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[349].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[349].joystick = "-1";
                                    newSetupdialog.mdatasaver[349].name = newSetupdialog.mdatasaver[id].name;
                                } else if (id == 106) {
                                    newSetupdialog.mdatasaver[350].property = 6;
                                    newSetupdialog.mdatasaver[350].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[350].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[350].joystick = "-1";
                                    newSetupdialog.mdatasaver[350].name = newSetupdialog.mdatasaver[id].name;
                                } else {
                                    final DataSaverM[] mdatasaver61 = newSetupdialog.mdatasaver;
                                    final int n61 = id + 321;
                                    mdatasaver61[n61].property = 6;
                                    newSetupdialog.mdatasaver[n61].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n61].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n61].joystick = "-1";
                                    newSetupdialog.mdatasaver[n61].name = newSetupdialog.mdatasaver[id].name;
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (id == 96) {
                                newSetupdialog.mdatasaver[333].property = 6;
                                newSetupdialog.mdatasaver[333].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[333].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[333].joystick = "-1";
                                newSetupdialog.mdatasaver[333].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[96].property = -1;
                                newSetupdialog.mdatasaver[96].x = -1;
                                newSetupdialog.mdatasaver[96].y = -1;
                                newSetupdialog.mdatasaver[96].joystick = "-1";
                            }
                            if (id == 97) {
                                newSetupdialog.mdatasaver[334].property = 6;
                                newSetupdialog.mdatasaver[334].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[334].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[334].joystick = "-1";
                                newSetupdialog.mdatasaver[334].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[97].property = -1;
                                newSetupdialog.mdatasaver[97].x = -1;
                                newSetupdialog.mdatasaver[97].y = -1;
                                newSetupdialog.mdatasaver[97].joystick = "-1";
                            }
                            if (id == 42) {
                                newSetupdialog.mdatasaver[335].property = 6;
                                newSetupdialog.mdatasaver[335].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[335].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[335].joystick = "-1";
                                newSetupdialog.mdatasaver[335].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[42].property = -1;
                                newSetupdialog.mdatasaver[42].x = -1;
                                newSetupdialog.mdatasaver[42].y = -1;
                                newSetupdialog.mdatasaver[42].joystick = "-1";
                            }
                        }
                        if (newSetupdialog.mdatasaver[id].property == 1) {
                            if (id > 136 && id < 167 && id != 149 && id != 150 && id != 151) {
                                final DataSaverM[] mdatasaver62 = newSetupdialog.mdatasaver;
                                final int n62 = id + 184;
                                mdatasaver62[n62].property = 6;
                                newSetupdialog.mdatasaver[n62].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n62].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n62].joystick = "-1";
                                newSetupdialog.mdatasaver[n62].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver63 = newSetupdialog.mdatasaver;
                                final int n63 = id - 30;
                                mdatasaver63[n63].property = -1;
                                newSetupdialog.mdatasaver[n63].x = -1;
                                newSetupdialog.mdatasaver[n63].y = -1;
                                newSetupdialog.mdatasaver[n63].joystick = "-1";
                            }
                            if (id > 106 && id < 137 && id != 119 && id != 120 && id != 121) {
                                final DataSaverM[] mdatasaver64 = newSetupdialog.mdatasaver;
                                final int n64 = id + 214;
                                mdatasaver64[n64].property = 6;
                                newSetupdialog.mdatasaver[n64].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n64].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n64].joystick = "-1";
                                newSetupdialog.mdatasaver[n64].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver65 = newSetupdialog.mdatasaver;
                                final int n65 = id + 30;
                                mdatasaver65[n65].property = -1;
                                newSetupdialog.mdatasaver[n65].x = -1;
                                newSetupdialog.mdatasaver[n65].y = -1;
                                newSetupdialog.mdatasaver[n65].joystick = "-1";
                            }
                            if (id == 119 || id == 149) {
                                newSetupdialog.mdatasaver[333].property = 6;
                                newSetupdialog.mdatasaver[333].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[333].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[333].joystick = "-1";
                                newSetupdialog.mdatasaver[333].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[119].property = -1;
                                newSetupdialog.mdatasaver[119].x = -1;
                                newSetupdialog.mdatasaver[119].y = -1;
                                newSetupdialog.mdatasaver[119].joystick = "-1";
                                newSetupdialog.mdatasaver[149].property = -1;
                                newSetupdialog.mdatasaver[149].x = -1;
                                newSetupdialog.mdatasaver[149].y = -1;
                                newSetupdialog.mdatasaver[149].joystick = "-1";
                            }
                            if (id == 120 || id == 150) {
                                newSetupdialog.mdatasaver[334].property = 6;
                                newSetupdialog.mdatasaver[334].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[334].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[334].joystick = "-1";
                                newSetupdialog.mdatasaver[334].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[120].property = -1;
                                newSetupdialog.mdatasaver[120].x = -1;
                                newSetupdialog.mdatasaver[120].y = -1;
                                newSetupdialog.mdatasaver[120].joystick = "-1";
                                newSetupdialog.mdatasaver[150].property = -1;
                                newSetupdialog.mdatasaver[150].x = -1;
                                newSetupdialog.mdatasaver[150].y = -1;
                                newSetupdialog.mdatasaver[150].joystick = "-1";
                            }
                            if (id == 121 || id == 151) {
                                newSetupdialog.mdatasaver[335].property = 6;
                                newSetupdialog.mdatasaver[335].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[335].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[335].joystick = "-1";
                                newSetupdialog.mdatasaver[335].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[121].property = -1;
                                newSetupdialog.mdatasaver[121].x = -1;
                                newSetupdialog.mdatasaver[121].y = -1;
                                newSetupdialog.mdatasaver[121].joystick = "-1";
                                newSetupdialog.mdatasaver[151].property = -1;
                                newSetupdialog.mdatasaver[151].x = -1;
                                newSetupdialog.mdatasaver[151].y = -1;
                                newSetupdialog.mdatasaver[151].joystick = "-1";
                            }
                        }
                        if (newSetupdialog.mdatasaver[id].property == 4) {
                            if (id > 230 && id < 261 && id != 243 && id != 244 && id != 245) {
                                final DataSaverM[] mdatasaver66 = newSetupdialog.mdatasaver;
                                final int n66 = id + 90;
                                mdatasaver66[n66].property = 6;
                                newSetupdialog.mdatasaver[n66].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n66].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n66].joystick = "-1";
                                newSetupdialog.mdatasaver[n66].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver67 = newSetupdialog.mdatasaver;
                                final int n67 = id + 30;
                                mdatasaver67[n67].property = -1;
                                newSetupdialog.mdatasaver[n67].x = -1;
                                newSetupdialog.mdatasaver[n67].y = -1;
                                newSetupdialog.mdatasaver[n67].joystick = "-1";
                                newSetupdialog.mdatasaver[n67].name = "-1";
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (id > 260 && id < 291 && id != 273 && id != 274 && id != 275) {
                                final DataSaverM[] mdatasaver68 = newSetupdialog.mdatasaver;
                                final int n68 = id + 60;
                                mdatasaver68[n68].property = 6;
                                newSetupdialog.mdatasaver[n68].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n68].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n68].joystick = "-1";
                                newSetupdialog.mdatasaver[n68].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver69 = newSetupdialog.mdatasaver;
                                final int n69 = id - 30;
                                mdatasaver69[n69].property = -1;
                                newSetupdialog.mdatasaver[n69].x = -1;
                                newSetupdialog.mdatasaver[n69].y = -1;
                                newSetupdialog.mdatasaver[n69].joystick = "-1";
                                newSetupdialog.mdatasaver[n69].name = "-1";
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (id == 243 || id == 273) {
                                newSetupdialog.mdatasaver[333].property = 6;
                                newSetupdialog.mdatasaver[333].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[333].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[333].joystick = "-1";
                                newSetupdialog.mdatasaver[333].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[243].property = -1;
                                newSetupdialog.mdatasaver[243].x = -1;
                                newSetupdialog.mdatasaver[243].y = -1;
                                newSetupdialog.mdatasaver[243].joystick = "-1";
                                newSetupdialog.mdatasaver[273].property = -1;
                                newSetupdialog.mdatasaver[273].x = -1;
                                newSetupdialog.mdatasaver[273].y = -1;
                                newSetupdialog.mdatasaver[273].joystick = "-1";
                            }
                            if (id == 244 || id == 274) {
                                newSetupdialog.mdatasaver[334].property = 6;
                                newSetupdialog.mdatasaver[334].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[334].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[334].joystick = "-1";
                                newSetupdialog.mdatasaver[334].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[244].property = -1;
                                newSetupdialog.mdatasaver[244].x = -1;
                                newSetupdialog.mdatasaver[244].y = -1;
                                newSetupdialog.mdatasaver[244].joystick = "-1";
                                newSetupdialog.mdatasaver[274].property = -1;
                                newSetupdialog.mdatasaver[274].x = -1;
                                newSetupdialog.mdatasaver[274].y = -1;
                                newSetupdialog.mdatasaver[274].joystick = "-1";
                            }
                            if (id == 245 || id == 275) {
                                newSetupdialog.mdatasaver[335].property = 6;
                                newSetupdialog.mdatasaver[335].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[335].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[335].joystick = "-1";
                                newSetupdialog.mdatasaver[335].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[245].property = -1;
                                newSetupdialog.mdatasaver[245].x = -1;
                                newSetupdialog.mdatasaver[245].y = -1;
                                newSetupdialog.mdatasaver[245].joystick = "-1";
                                newSetupdialog.mdatasaver[275].property = -1;
                                newSetupdialog.mdatasaver[275].x = -1;
                                newSetupdialog.mdatasaver[275].y = -1;
                                newSetupdialog.mdatasaver[275].joystick = "-1";
                            }
                        }
                        if (newSetupdialog.mdatasaver[id].property == 5 && id > 290 && id < 321) {
                            final DataSaverM[] mdatasaver70 = newSetupdialog.mdatasaver;
                            final int n70 = id + 30;
                            mdatasaver70[n70].property = 6;
                            newSetupdialog.mdatasaver[n70].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[n70].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[n70].joystick = "-1";
                            newSetupdialog.mdatasaver[n70].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        }
                    }
                    newSetupdialog.this.cleanallview();
                    newSetupdialog.this.displaycircle();
                    setupdialogwindow.this.dismiss();
                }
            });
            button2.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    setupdialogwindow.this.dismiss();
                }
            });
        }
    }

    public class setupdialogwindow2 extends PopupWindow {
        ListAdapter listAdapter;
        ListView listView;
        private Context mContext;
        private ListView mListView;
        CheckBox mbox;
        CheckBox mbox1;
        CheckBox mrumble;
        TextView mtextview;
        final View mv;
        int pos;
        TextView reverse;
        TextView title;
        TextView trumble;

        public setupdialogwindow2(final Context mContext, final View mv) {
            super(mContext);
            this.pos = -1;
            this.mv = mv;
            this.mContext = mContext;
            this.init();
        }

        private void init() {
            this.pos = this.mv.getId();
            Setupitem[] array;
            if (this.pos != 41 && this.pos != 130 && this.pos != 160) {
                if ((this.pos >= 30 || this.pos == 23) && (this.pos <= 106 || this.pos >= 167) && (this.pos <= 230 || this.pos >= 291) && this.pos != 96 && this.pos != 97 && this.pos != 42) {
                    return;
                }
                array = new Setupitem[]{new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_36)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_37)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_38))};
            } else {
                array = new Setupitem[]{new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_36)), new Setupitem(newSetupdialog.this.mcontext.getString(R.string.new_37))};
            }
            final ArrayList<Setupitem> list = new ArrayList<Setupitem>();
            for (int length = array.length, i = 0; i < length; ++i) {
                list.add(array[i]);
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.popup_setup, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(false);
            this.listView = (ListView) inflate.findViewById(R.id.lv_setup);
            final Button button = (Button) inflate.findViewById(R.id.btn_yes);
            final Button button2 = (Button) inflate.findViewById(R.id.btn_no);
            this.mtextview = (TextView) inflate.findViewById(R.id.textView1);
            this.title = (TextView) inflate.findViewById(R.id.titlet);
            (this.reverse = (TextView) inflate.findViewById(R.id.restext)).setText((CharSequence) "");
            final TextView title = this.title;
            final StringBuilder sb = new StringBuilder();
            sb.append(newSetupdialog.mdatasaver[this.mv.getId()].name.toUpperCase());
            sb.append(" ");
            sb.append(newSetupdialog.this.mcontext.getString(R.string.op8));
            title.setText((CharSequence) sb.toString());
            button.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.sure));
            button2.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.cancle));
            (this.listAdapter = new ListAdapter(this.mContext)).setList(list);
            this.listView.setAdapter((ListAdapter) this.listAdapter);
            this.listView.setOnItemClickListener((AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                int cont1 = setupdialogwindow2.this.listAdapter.getCount();

                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    setupdialogwindow2.this.listAdapter.select(n);
                    if (this.cont1 == 2) {
                        if (n == 0) {
                            setupdialogwindow2.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                            setupdialogwindow2.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 1) {
                            setupdialogwindow2.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        }
                    } else {
                        if (n == 0) {
                            setupdialogwindow2.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                            setupdialogwindow2.this.reverse.setText((CharSequence) "");
                        }
                        if (n == 1) {
                            setupdialogwindow2.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        }
                        if (n == 2) {
                            setupdialogwindow2.this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                            setupdialogwindow2.this.reverse.setText((CharSequence) "");
                        }
                    }
                }
            });
            final int count = this.listAdapter.getCount();
            final int id = this.mv.getId();
            if (count == 2) {
                if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                    final Setupitem setupitem = (Setupitem) this.listAdapter.getItem(0);
                    if (setupitem != null) {
                        setupitem.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 1) {
                    final Setupitem setupitem2 = (Setupitem) this.listAdapter.getItem(1);
                    if (setupitem2 != null) {
                        setupitem2.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        this.reverse.setText((CharSequence) "");
                    }
                }
            } else {
                if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                    final Setupitem setupitem3 = (Setupitem) this.listAdapter.getItem(0);
                    if (setupitem3 != null) {
                        setupitem3.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_39));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 1) {
                    final Setupitem setupitem4 = (Setupitem) this.listAdapter.getItem(1);
                    if (setupitem4 != null) {
                        setupitem4.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_40));
                        this.reverse.setText((CharSequence) "");
                    }
                }
                if (newSetupdialog.mdatasaver[id].property == 4) {
                    final Setupitem setupitem5 = (Setupitem) this.listAdapter.getItem(2);
                    if (setupitem5 != null) {
                        setupitem5.setSelected(true);
                        this.mtextview.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.new_41));
                        this.reverse.setText((CharSequence) "");
                    }
                }
            }
            button.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    final int id = setupdialogwindow2.this.mv.getId();
                    final int count = setupdialogwindow2.this.listAdapter.getCount();
                    final boolean selected = ((Setupitem) setupdialogwindow2.this.listAdapter.getItem(0)).isSelected();
                    final boolean selected2 = ((Setupitem) setupdialogwindow2.this.listAdapter.getItem(1)).isSelected();
                    final boolean b = count == 3 && ((Setupitem) setupdialogwindow2.this.listAdapter.getItem(2)).isSelected();
                    if (selected) {
                        if (newSetupdialog.mdatasaver[id].property == 1 && id != 119 && id != 120 && id != 121 && id != 149 && id != 150 && id != 151) {
                            if (id > 136 && id < 167 && id != 160) {
                                final DataSaverM[] mdatasaver = newSetupdialog.mdatasaver;
                                final int n = id - 137;
                                mdatasaver[n].property = 0;
                                newSetupdialog.mdatasaver[n].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver2 = newSetupdialog.mdatasaver;
                                final int n2 = id - 30;
                                mdatasaver2[n2].property = -1;
                                newSetupdialog.mdatasaver[n2].x = -1;
                                newSetupdialog.mdatasaver[n2].y = -1;
                                newSetupdialog.mdatasaver[n2].joystick = "-1";
                            }
                            if (id > 106 && id < 137 && id != 130) {
                                final DataSaverM[] mdatasaver3 = newSetupdialog.mdatasaver;
                                final int n3 = id - 107;
                                mdatasaver3[n3].property = 0;
                                newSetupdialog.mdatasaver[n3].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n3].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n3].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver4 = newSetupdialog.mdatasaver;
                                final int n4 = id + 30;
                                mdatasaver4[n4].property = -1;
                                newSetupdialog.mdatasaver[n4].x = -1;
                                newSetupdialog.mdatasaver[n4].y = -1;
                                newSetupdialog.mdatasaver[n4].joystick = "-1";
                            }
                            if (id == 130 || id == 160) {
                                newSetupdialog.mdatasaver[41].property = 0;
                                newSetupdialog.mdatasaver[41].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[41].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[41].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[130].property = -1;
                                newSetupdialog.mdatasaver[130].x = -1;
                                newSetupdialog.mdatasaver[130].y = -1;
                                newSetupdialog.mdatasaver[130].joystick = "-1";
                                newSetupdialog.mdatasaver[160].property = -1;
                                newSetupdialog.mdatasaver[160].x = -1;
                                newSetupdialog.mdatasaver[160].y = -1;
                                newSetupdialog.mdatasaver[160].joystick = "-1";
                            }
                        } else if (newSetupdialog.mdatasaver[id].property == 4 && id != 243 && id != 244 && id != 245 && id != 273 && id != 274 && id != 275) {
                            if (id > 230 && id < 261) {
                                final DataSaverM[] mdatasaver5 = newSetupdialog.mdatasaver;
                                final int n5 = id - 231;
                                mdatasaver5[n5].property = 0;
                                newSetupdialog.mdatasaver[n5].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n5].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n5].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver6 = newSetupdialog.mdatasaver;
                                final int n6 = id + 30;
                                mdatasaver6[n6].property = -1;
                                newSetupdialog.mdatasaver[n6].x = -1;
                                newSetupdialog.mdatasaver[n6].y = -1;
                                newSetupdialog.mdatasaver[n6].joystick = "-1";
                                final DataSaverM[] mdatasaver7 = newSetupdialog.mdatasaver;
                                final int n7 = id + 60;
                                mdatasaver7[n7].property = -1;
                                newSetupdialog.mdatasaver[n7].x = -1;
                                newSetupdialog.mdatasaver[n7].y = -1;
                                newSetupdialog.mdatasaver[n7].joystick = "-1";
                            }
                            if (id > 260 && id < 291) {
                                final DataSaverM[] mdatasaver8 = newSetupdialog.mdatasaver;
                                final int n8 = id - 261;
                                mdatasaver8[n8].property = 0;
                                newSetupdialog.mdatasaver[n8].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n8].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n8].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver9 = newSetupdialog.mdatasaver;
                                final int n9 = id + 30;
                                mdatasaver9[n9].property = -1;
                                newSetupdialog.mdatasaver[n9].x = -1;
                                newSetupdialog.mdatasaver[n9].y = -1;
                                newSetupdialog.mdatasaver[n9].joystick = "-1";
                                final DataSaverM[] mdatasaver10 = newSetupdialog.mdatasaver;
                                final int n10 = id - 30;
                                mdatasaver10[n10].property = -1;
                                newSetupdialog.mdatasaver[n10].x = -1;
                                newSetupdialog.mdatasaver[n10].y = -1;
                                newSetupdialog.mdatasaver[n10].joystick = "-1";
                            }
                            if (id > 290 && id < 321) {
                                final DataSaverM[] mdatasaver11 = newSetupdialog.mdatasaver;
                                final int n11 = id - 291;
                                mdatasaver11[n11].property = 0;
                                newSetupdialog.mdatasaver[n11].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n11].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n11].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver12 = newSetupdialog.mdatasaver;
                                final int n12 = id - 60;
                                mdatasaver12[n12].property = -1;
                                newSetupdialog.mdatasaver[n12].x = -1;
                                newSetupdialog.mdatasaver[n12].y = -1;
                                newSetupdialog.mdatasaver[n12].joystick = "-1";
                                final DataSaverM[] mdatasaver13 = newSetupdialog.mdatasaver;
                                final int n13 = id - 30;
                                mdatasaver13[n13].property = -1;
                                newSetupdialog.mdatasaver[n13].x = -1;
                                newSetupdialog.mdatasaver[n13].y = -1;
                                newSetupdialog.mdatasaver[n13].joystick = "-1";
                            }
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        } else {
                            newSetupdialog.mdatasaver[id].property = 0;
                        }
                        if (id == 41) {
                            newSetupdialog.mdatasaver[id].property = 0;
                            newSetupdialog.mdatasaver[id].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[id].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[id].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[130].property = -1;
                            newSetupdialog.mdatasaver[130].x = -1;
                            newSetupdialog.mdatasaver[130].y = -1;
                            newSetupdialog.mdatasaver[130].joystick = "-1";
                            newSetupdialog.mdatasaver[160].property = -1;
                            newSetupdialog.mdatasaver[160].x = -1;
                            newSetupdialog.mdatasaver[160].y = -1;
                            newSetupdialog.mdatasaver[160].joystick = "-1";
                        }
                        if (id == 96 || id == 119 || id == 149 || id == 243 || id == 273) {
                            newSetupdialog.mdatasaver[96].property = 0;
                            newSetupdialog.mdatasaver[96].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[96].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[96].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[119].property = -1;
                            newSetupdialog.mdatasaver[119].x = -1;
                            newSetupdialog.mdatasaver[119].y = -1;
                            newSetupdialog.mdatasaver[119].joystick = "-1";
                            newSetupdialog.mdatasaver[149].property = -1;
                            newSetupdialog.mdatasaver[149].x = -1;
                            newSetupdialog.mdatasaver[149].y = -1;
                            newSetupdialog.mdatasaver[149].joystick = "-1";
                            newSetupdialog.mdatasaver[243].property = -1;
                            newSetupdialog.mdatasaver[243].x = -1;
                            newSetupdialog.mdatasaver[243].y = -1;
                            newSetupdialog.mdatasaver[243].joystick = "-1";
                            newSetupdialog.mdatasaver[273].property = -1;
                            newSetupdialog.mdatasaver[273].x = -1;
                            newSetupdialog.mdatasaver[273].y = -1;
                            newSetupdialog.mdatasaver[273].joystick = "-1";
                        }
                        if (id == 97 || id == 120 || id == 150 || id == 244 || id == 274) {
                            newSetupdialog.mdatasaver[97].property = 0;
                            newSetupdialog.mdatasaver[97].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[97].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[97].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[120].property = -1;
                            newSetupdialog.mdatasaver[120].x = -1;
                            newSetupdialog.mdatasaver[120].y = -1;
                            newSetupdialog.mdatasaver[120].joystick = "-1";
                            newSetupdialog.mdatasaver[150].property = -1;
                            newSetupdialog.mdatasaver[150].x = -1;
                            newSetupdialog.mdatasaver[150].y = -1;
                            newSetupdialog.mdatasaver[150].joystick = "-1";
                            newSetupdialog.mdatasaver[244].property = -1;
                            newSetupdialog.mdatasaver[244].x = -1;
                            newSetupdialog.mdatasaver[244].y = -1;
                            newSetupdialog.mdatasaver[244].joystick = "-1";
                            newSetupdialog.mdatasaver[274].property = -1;
                            newSetupdialog.mdatasaver[274].x = -1;
                            newSetupdialog.mdatasaver[274].y = -1;
                            newSetupdialog.mdatasaver[274].joystick = "-1";
                        }
                        if (id == 42 || id == 121 || id == 151 || id == 245 || id == 275) {
                            newSetupdialog.mdatasaver[42].property = 0;
                            newSetupdialog.mdatasaver[42].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[42].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[42].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[121].property = -1;
                            newSetupdialog.mdatasaver[121].x = -1;
                            newSetupdialog.mdatasaver[121].y = -1;
                            newSetupdialog.mdatasaver[121].joystick = "-1";
                            newSetupdialog.mdatasaver[151].property = -1;
                            newSetupdialog.mdatasaver[151].x = -1;
                            newSetupdialog.mdatasaver[151].y = -1;
                            newSetupdialog.mdatasaver[151].joystick = "-1";
                            newSetupdialog.mdatasaver[245].property = -1;
                            newSetupdialog.mdatasaver[245].x = -1;
                            newSetupdialog.mdatasaver[245].y = -1;
                            newSetupdialog.mdatasaver[245].joystick = "-1";
                            newSetupdialog.mdatasaver[275].property = -1;
                            newSetupdialog.mdatasaver[275].x = -1;
                            newSetupdialog.mdatasaver[275].y = -1;
                            newSetupdialog.mdatasaver[275].joystick = "-1";
                        }
                    }
                    if (selected2) {
                        if (count == 3) {
                            if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                                if (id != 96 && id != 97 && id != 42) {
                                    final DataSaverM[] mdatasaver14 = newSetupdialog.mdatasaver;
                                    final int n14 = id + 107;
                                    mdatasaver14[n14].property = 1;
                                    newSetupdialog.mdatasaver[n14].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n14].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n14].joystick = "2";
                                    newSetupdialog.mdatasaver[n14].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver15 = newSetupdialog.mdatasaver;
                                    final int n15 = id + 137;
                                    mdatasaver15[n15].property = 1;
                                    newSetupdialog.mdatasaver[n15].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n15].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n15].joystick = "-1";
                                    newSetupdialog.mdatasaver[n15].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 96) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[243].property = -1;
                                    newSetupdialog.mdatasaver[243].x = -1;
                                    newSetupdialog.mdatasaver[243].y = -1;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].property = -1;
                                    newSetupdialog.mdatasaver[273].x = -1;
                                    newSetupdialog.mdatasaver[273].y = -1;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 97) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[244].property = -1;
                                    newSetupdialog.mdatasaver[244].x = -1;
                                    newSetupdialog.mdatasaver[244].y = -1;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].property = -1;
                                    newSetupdialog.mdatasaver[274].x = -1;
                                    newSetupdialog.mdatasaver[274].y = -1;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                                if (id == 42) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[245].property = -1;
                                    newSetupdialog.mdatasaver[245].x = -1;
                                    newSetupdialog.mdatasaver[245].y = -1;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].property = -1;
                                    newSetupdialog.mdatasaver[275].x = -1;
                                    newSetupdialog.mdatasaver[275].y = -1;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[id].property = -1;
                                    newSetupdialog.mdatasaver[id].x = -1;
                                    newSetupdialog.mdatasaver[id].y = -1;
                                    newSetupdialog.mdatasaver[id].joystick = "-1";
                                }
                            }
                            if (newSetupdialog.mdatasaver[id].property == 4) {
                                if (id > 230 && id < 261 && id != 243 && id != 244 && id != 245) {
                                    final DataSaverM[] mdatasaver16 = newSetupdialog.mdatasaver;
                                    final int n16 = id - 124;
                                    mdatasaver16[n16].property = 1;
                                    newSetupdialog.mdatasaver[n16].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n16].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n16].joystick = "2";
                                    newSetupdialog.mdatasaver[n16].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver17 = newSetupdialog.mdatasaver;
                                    final int n17 = id - 94;
                                    mdatasaver17[n17].property = 1;
                                    newSetupdialog.mdatasaver[n17].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n17].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n17].joystick = "-1";
                                    newSetupdialog.mdatasaver[n17].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver18 = newSetupdialog.mdatasaver;
                                    final int n18 = id + 30;
                                    mdatasaver18[n18].property = -1;
                                    newSetupdialog.mdatasaver[n18].x = -1;
                                    newSetupdialog.mdatasaver[n18].y = -1;
                                    newSetupdialog.mdatasaver[n18].joystick = "-1";
                                    final DataSaverM[] mdatasaver19 = newSetupdialog.mdatasaver;
                                    final int n19 = id + 60;
                                    mdatasaver19[n19].property = -1;
                                    newSetupdialog.mdatasaver[n19].x = -1;
                                    newSetupdialog.mdatasaver[n19].y = -1;
                                    newSetupdialog.mdatasaver[n19].joystick = "-1";
                                }
                                if (id > 260 && id < 291 && id != 273 && id != 274 && id != 275) {
                                    final DataSaverM[] mdatasaver20 = newSetupdialog.mdatasaver;
                                    final int n20 = id - 154;
                                    mdatasaver20[n20].property = 1;
                                    newSetupdialog.mdatasaver[n20].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n20].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n20].joystick = "2";
                                    newSetupdialog.mdatasaver[n20].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver21 = newSetupdialog.mdatasaver;
                                    final int n21 = id - 124;
                                    mdatasaver21[n21].property = 1;
                                    newSetupdialog.mdatasaver[n21].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n21].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n21].joystick = "-1";
                                    newSetupdialog.mdatasaver[n21].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver22 = newSetupdialog.mdatasaver;
                                    final int n22 = id + 30;
                                    mdatasaver22[n22].property = -1;
                                    newSetupdialog.mdatasaver[n22].x = -1;
                                    newSetupdialog.mdatasaver[n22].y = -1;
                                    newSetupdialog.mdatasaver[n22].joystick = "-1";
                                    final DataSaverM[] mdatasaver23 = newSetupdialog.mdatasaver;
                                    final int n23 = id - 30;
                                    mdatasaver23[n23].property = -1;
                                    newSetupdialog.mdatasaver[n23].x = -1;
                                    newSetupdialog.mdatasaver[n23].y = -1;
                                    newSetupdialog.mdatasaver[n23].joystick = "-1";
                                }
                                if (id > 290 && id < 321 && id != 303 && id != 304 && id != 305) {
                                    final DataSaverM[] mdatasaver24 = newSetupdialog.mdatasaver;
                                    final int n24 = id - 184;
                                    mdatasaver24[n24].property = 1;
                                    newSetupdialog.mdatasaver[n24].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n24].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n24].joystick = "2";
                                    newSetupdialog.mdatasaver[n24].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver25 = newSetupdialog.mdatasaver;
                                    final int n25 = id - 154;
                                    mdatasaver25[n25].property = 1;
                                    newSetupdialog.mdatasaver[n25].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[n25].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[n25].joystick = "-1";
                                    newSetupdialog.mdatasaver[n25].name = newSetupdialog.mdatasaver[id].name;
                                    final DataSaverM[] mdatasaver26 = newSetupdialog.mdatasaver;
                                    final int n26 = id - 60;
                                    mdatasaver26[n26].property = -1;
                                    newSetupdialog.mdatasaver[n26].x = -1;
                                    newSetupdialog.mdatasaver[n26].y = -1;
                                    newSetupdialog.mdatasaver[n26].joystick = "-1";
                                    final DataSaverM[] mdatasaver27 = newSetupdialog.mdatasaver;
                                    final int n27 = id - 30;
                                    mdatasaver27[n27].property = -1;
                                    newSetupdialog.mdatasaver[n27].x = -1;
                                    newSetupdialog.mdatasaver[n27].y = -1;
                                    newSetupdialog.mdatasaver[n27].joystick = "-1";
                                }
                                if (id == 243 || id == 273) {
                                    newSetupdialog.mdatasaver[119].property = 1;
                                    newSetupdialog.mdatasaver[119].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[119].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[119].joystick = "2";
                                    newSetupdialog.mdatasaver[119].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[149].property = 1;
                                    newSetupdialog.mdatasaver[149].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[149].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[149].joystick = "-1";
                                    newSetupdialog.mdatasaver[149].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[243].property = -1;
                                    newSetupdialog.mdatasaver[243].x = -1;
                                    newSetupdialog.mdatasaver[243].y = -1;
                                    newSetupdialog.mdatasaver[243].joystick = "-1";
                                    newSetupdialog.mdatasaver[273].property = -1;
                                    newSetupdialog.mdatasaver[273].x = -1;
                                    newSetupdialog.mdatasaver[273].y = -1;
                                    newSetupdialog.mdatasaver[273].joystick = "-1";
                                    newSetupdialog.mdatasaver[96].property = -1;
                                    newSetupdialog.mdatasaver[96].x = -1;
                                    newSetupdialog.mdatasaver[96].y = -1;
                                    newSetupdialog.mdatasaver[96].joystick = "-1";
                                }
                                if (id == 244 || id == 274) {
                                    newSetupdialog.mdatasaver[120].property = 1;
                                    newSetupdialog.mdatasaver[120].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[120].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[120].joystick = "2";
                                    newSetupdialog.mdatasaver[120].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[150].property = 1;
                                    newSetupdialog.mdatasaver[150].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[150].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[150].joystick = "-1";
                                    newSetupdialog.mdatasaver[150].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[244].property = -1;
                                    newSetupdialog.mdatasaver[244].x = -1;
                                    newSetupdialog.mdatasaver[244].y = -1;
                                    newSetupdialog.mdatasaver[244].joystick = "-1";
                                    newSetupdialog.mdatasaver[274].property = -1;
                                    newSetupdialog.mdatasaver[274].x = -1;
                                    newSetupdialog.mdatasaver[274].y = -1;
                                    newSetupdialog.mdatasaver[274].joystick = "-1";
                                    newSetupdialog.mdatasaver[97].property = -1;
                                    newSetupdialog.mdatasaver[97].x = -1;
                                    newSetupdialog.mdatasaver[97].y = -1;
                                    newSetupdialog.mdatasaver[97].joystick = "-1";
                                }
                                if (id == 245 || id == 275) {
                                    newSetupdialog.mdatasaver[121].property = 1;
                                    newSetupdialog.mdatasaver[121].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[121].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[121].joystick = "2";
                                    newSetupdialog.mdatasaver[121].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[151].property = 1;
                                    newSetupdialog.mdatasaver[151].x = newSetupdialog.mdatasaver[id].x;
                                    newSetupdialog.mdatasaver[151].y = newSetupdialog.mdatasaver[id].y;
                                    newSetupdialog.mdatasaver[151].joystick = "-1";
                                    newSetupdialog.mdatasaver[151].name = newSetupdialog.mdatasaver[id].name;
                                    newSetupdialog.mdatasaver[245].property = -1;
                                    newSetupdialog.mdatasaver[245].x = -1;
                                    newSetupdialog.mdatasaver[245].y = -1;
                                    newSetupdialog.mdatasaver[245].joystick = "-1";
                                    newSetupdialog.mdatasaver[275].property = -1;
                                    newSetupdialog.mdatasaver[275].x = -1;
                                    newSetupdialog.mdatasaver[275].y = -1;
                                    newSetupdialog.mdatasaver[275].joystick = "-1";
                                    newSetupdialog.mdatasaver[42].property = -1;
                                    newSetupdialog.mdatasaver[42].x = -1;
                                    newSetupdialog.mdatasaver[42].y = -1;
                                    newSetupdialog.mdatasaver[42].joystick = "-1";
                                }
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                        } else if (count == 2 && id == 41) {
                            newSetupdialog.mdatasaver[130].property = 1;
                            newSetupdialog.mdatasaver[130].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[130].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[130].joystick = "2";
                            newSetupdialog.mdatasaver[130].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[160].property = 1;
                            newSetupdialog.mdatasaver[160].x = newSetupdialog.mdatasaver[id].x;
                            newSetupdialog.mdatasaver[160].y = newSetupdialog.mdatasaver[id].y;
                            newSetupdialog.mdatasaver[160].joystick = "-1";
                            newSetupdialog.mdatasaver[160].name = newSetupdialog.mdatasaver[id].name;
                            newSetupdialog.mdatasaver[id].property = -1;
                            newSetupdialog.mdatasaver[id].x = -1;
                            newSetupdialog.mdatasaver[id].y = -1;
                            newSetupdialog.mdatasaver[id].joystick = "-1";
                        }
                    }
                    if (b) {
                        if (newSetupdialog.mdatasaver[id].property == 0 || newSetupdialog.mdatasaver[id].property == -1) {
                            if (id != 96 && id != 97 && id != 42) {
                                final DataSaverM[] mdatasaver28 = newSetupdialog.mdatasaver;
                                final int n28 = id + 231;
                                mdatasaver28[n28].property = 4;
                                newSetupdialog.mdatasaver[n28].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n28].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n28].joystick = "-1";
                                newSetupdialog.mdatasaver[n28].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver29 = newSetupdialog.mdatasaver;
                                final int n29 = id + 261;
                                mdatasaver29[n29].property = 4;
                                newSetupdialog.mdatasaver[n29].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n29].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n29].joystick = "-1";
                                newSetupdialog.mdatasaver[n29].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                            }
                            if (id == 96) {
                                newSetupdialog.mdatasaver[119].property = -1;
                                newSetupdialog.mdatasaver[119].x = -1;
                                newSetupdialog.mdatasaver[119].y = -1;
                                newSetupdialog.mdatasaver[119].joystick = "-1";
                                newSetupdialog.mdatasaver[149].property = -1;
                                newSetupdialog.mdatasaver[149].x = -1;
                                newSetupdialog.mdatasaver[149].y = -1;
                                newSetupdialog.mdatasaver[149].joystick = "-1";
                                newSetupdialog.mdatasaver[243].property = 4;
                                newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[243].joystick = "-1";
                                newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[273].property = 4;
                                newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[273].joystick = "-1";
                                newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[96].property = -1;
                                newSetupdialog.mdatasaver[96].x = -1;
                                newSetupdialog.mdatasaver[96].y = -1;
                                newSetupdialog.mdatasaver[96].joystick = "-1";
                            }
                            if (id == 97) {
                                newSetupdialog.mdatasaver[120].property = -1;
                                newSetupdialog.mdatasaver[120].x = -1;
                                newSetupdialog.mdatasaver[120].y = -1;
                                newSetupdialog.mdatasaver[120].joystick = "-1";
                                newSetupdialog.mdatasaver[150].property = -1;
                                newSetupdialog.mdatasaver[150].x = -1;
                                newSetupdialog.mdatasaver[150].y = -1;
                                newSetupdialog.mdatasaver[150].joystick = "-1";
                                newSetupdialog.mdatasaver[244].property = 4;
                                newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[244].joystick = "-1";
                                newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[274].property = 4;
                                newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[274].joystick = "-1";
                                newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[97].property = -1;
                                newSetupdialog.mdatasaver[97].x = -1;
                                newSetupdialog.mdatasaver[97].y = -1;
                                newSetupdialog.mdatasaver[97].joystick = "-1";
                            }
                            if (id == 42) {
                                newSetupdialog.mdatasaver[121].property = -1;
                                newSetupdialog.mdatasaver[121].x = -1;
                                newSetupdialog.mdatasaver[121].y = -1;
                                newSetupdialog.mdatasaver[121].joystick = "-1";
                                newSetupdialog.mdatasaver[151].property = -1;
                                newSetupdialog.mdatasaver[151].x = -1;
                                newSetupdialog.mdatasaver[151].y = -1;
                                newSetupdialog.mdatasaver[151].joystick = "-1";
                                newSetupdialog.mdatasaver[245].property = 4;
                                newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[245].joystick = "-1";
                                newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[275].property = 4;
                                newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[275].joystick = "-1";
                                newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[42].property = -1;
                                newSetupdialog.mdatasaver[42].x = -1;
                                newSetupdialog.mdatasaver[42].y = -1;
                                newSetupdialog.mdatasaver[42].joystick = "-1";
                            }
                        }
                        if (newSetupdialog.mdatasaver[id].property == 1) {
                            if (id > 136 && id != 149 && id != 150 && id != 151) {
                                final DataSaverM[] mdatasaver30 = newSetupdialog.mdatasaver;
                                final int n30 = id + 94;
                                mdatasaver30[n30].property = 4;
                                newSetupdialog.mdatasaver[n30].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n30].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n30].joystick = "-1";
                                newSetupdialog.mdatasaver[n30].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver31 = newSetupdialog.mdatasaver;
                                final int n31 = id + 124;
                                mdatasaver31[n31].property = 4;
                                newSetupdialog.mdatasaver[n31].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n31].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n31].joystick = "-1";
                                newSetupdialog.mdatasaver[n31].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver32 = newSetupdialog.mdatasaver;
                                final int n32 = id - 30;
                                mdatasaver32[n32].property = -1;
                                newSetupdialog.mdatasaver[n32].x = -1;
                                newSetupdialog.mdatasaver[n32].y = -1;
                                newSetupdialog.mdatasaver[n32].joystick = "-1";
                            }
                            if (id > 106 && id < 137 && id != 119 && id != 120 && id != 121) {
                                final DataSaverM[] mdatasaver33 = newSetupdialog.mdatasaver;
                                final int n33 = id + 124;
                                mdatasaver33[n33].property = 4;
                                newSetupdialog.mdatasaver[n33].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n33].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n33].joystick = "-1";
                                newSetupdialog.mdatasaver[n33].name = newSetupdialog.mdatasaver[id].name;
                                final DataSaverM[] mdatasaver34 = newSetupdialog.mdatasaver;
                                final int n34 = id + 154;
                                mdatasaver34[n34].property = 4;
                                newSetupdialog.mdatasaver[n34].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[n34].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[n34].joystick = "-1";
                                newSetupdialog.mdatasaver[n34].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[id].property = -1;
                                newSetupdialog.mdatasaver[id].x = -1;
                                newSetupdialog.mdatasaver[id].y = -1;
                                newSetupdialog.mdatasaver[id].joystick = "-1";
                                final DataSaverM[] mdatasaver35 = newSetupdialog.mdatasaver;
                                final int n35 = id + 30;
                                mdatasaver35[n35].property = -1;
                                newSetupdialog.mdatasaver[n35].x = -1;
                                newSetupdialog.mdatasaver[n35].y = -1;
                                newSetupdialog.mdatasaver[n35].joystick = "-1";
                            }
                            if (id == 119 || id == 149) {
                                newSetupdialog.mdatasaver[243].property = 4;
                                newSetupdialog.mdatasaver[243].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[243].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[243].joystick = "-1";
                                newSetupdialog.mdatasaver[243].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[273].property = 4;
                                newSetupdialog.mdatasaver[273].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[273].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[273].joystick = "-1";
                                newSetupdialog.mdatasaver[273].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[119].property = -1;
                                newSetupdialog.mdatasaver[119].x = -1;
                                newSetupdialog.mdatasaver[119].y = -1;
                                newSetupdialog.mdatasaver[119].joystick = "-1";
                                newSetupdialog.mdatasaver[149].property = -1;
                                newSetupdialog.mdatasaver[149].x = -1;
                                newSetupdialog.mdatasaver[149].y = -1;
                                newSetupdialog.mdatasaver[149].joystick = "-1";
                                newSetupdialog.mdatasaver[96].property = -1;
                                newSetupdialog.mdatasaver[96].x = -1;
                                newSetupdialog.mdatasaver[96].y = -1;
                                newSetupdialog.mdatasaver[96].joystick = "-1";
                            }
                            if (id == 120 || id == 150) {
                                newSetupdialog.mdatasaver[244].property = 4;
                                newSetupdialog.mdatasaver[244].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[244].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[244].joystick = "-1";
                                newSetupdialog.mdatasaver[244].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[274].property = 4;
                                newSetupdialog.mdatasaver[274].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[274].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[274].joystick = "-1";
                                newSetupdialog.mdatasaver[274].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[120].property = -1;
                                newSetupdialog.mdatasaver[120].x = -1;
                                newSetupdialog.mdatasaver[120].y = -1;
                                newSetupdialog.mdatasaver[120].joystick = "-1";
                                newSetupdialog.mdatasaver[150].property = -1;
                                newSetupdialog.mdatasaver[150].x = -1;
                                newSetupdialog.mdatasaver[150].y = -1;
                                newSetupdialog.mdatasaver[150].joystick = "-1";
                                newSetupdialog.mdatasaver[97].property = -1;
                                newSetupdialog.mdatasaver[97].x = -1;
                                newSetupdialog.mdatasaver[97].y = -1;
                                newSetupdialog.mdatasaver[97].joystick = "-1";
                            }
                            if (id == 121 || id == 151) {
                                newSetupdialog.mdatasaver[245].property = 4;
                                newSetupdialog.mdatasaver[245].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[245].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[245].joystick = "-1";
                                newSetupdialog.mdatasaver[245].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[275].property = 4;
                                newSetupdialog.mdatasaver[275].x = newSetupdialog.mdatasaver[id].x;
                                newSetupdialog.mdatasaver[275].y = newSetupdialog.mdatasaver[id].y;
                                newSetupdialog.mdatasaver[275].joystick = "-1";
                                newSetupdialog.mdatasaver[275].name = newSetupdialog.mdatasaver[id].name;
                                newSetupdialog.mdatasaver[121].property = -1;
                                newSetupdialog.mdatasaver[121].x = -1;
                                newSetupdialog.mdatasaver[121].y = -1;
                                newSetupdialog.mdatasaver[121].joystick = "-1";
                                newSetupdialog.mdatasaver[151].property = -1;
                                newSetupdialog.mdatasaver[151].x = -1;
                                newSetupdialog.mdatasaver[151].y = -1;
                                newSetupdialog.mdatasaver[151].joystick = "-1";
                                newSetupdialog.mdatasaver[42].property = -1;
                                newSetupdialog.mdatasaver[42].x = -1;
                                newSetupdialog.mdatasaver[42].y = -1;
                                newSetupdialog.mdatasaver[42].joystick = "-1";
                            }
                        }
                    }
                    newSetupdialog.this.cleanallview();
                    newSetupdialog.this.displaycircle();
                    setupdialogwindow2.this.dismiss();
                }
            });
            button2.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    setupdialogwindow2.this.dismiss();
                }
            });
        }
    }

    public class showdialogwindow extends PopupWindow {
        Button btn_yes;
        private Context mContext;
        String str;
        TextView tx;
        TextView vtitle;

        public showdialogwindow(final Context mContext, final String str) {
            super(mContext);
            this.str = null;
            this.tx = null;
            this.mContext = mContext;
            this.str = str;
            this.init();
        }

        void init() {
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.showdialog, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.tx = (TextView) inflate.findViewById(R.id.textView1);
            this.vtitle = (TextView) inflate.findViewById(R.id.titlet1);
            this.btn_yes = (Button) inflate.findViewById(R.id.btn_yes);
            this.setFocusable(false);
            this.vtitle.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.op46));
            this.btn_yes.setText((CharSequence) newSetupdialog.this.mcontext.getString(R.string.sure));
            this.btn_yes.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    showdialogwindow.this.dismiss();
                }
            });
        }
    }

    public class sunyesMaxSavedialoggwindow extends PopupWindow {
        private CheckBox cb_bunding_game;
        private TextView dialog_btn_cancel;
        private TextView dialog_btn_sure;
        private TextView dialog_message;
        private EditText et_pwd_code;
        private boolean isSave;
        private LinearLayout ll_save;
        private LinearLayout ll_share;
        private Context mContext;
        private TextView tv_bunding_game_name;
        private TextView tv_save;
        private TextView tv_share;

        public sunyesMaxSavedialoggwindow(final Context mContext) {
            super(mContext);
            this.isSave = true;
            this.mContext = mContext;
            this.init();
        }

        void init() {
            if (newSetupdialog.this.ishaveview() == 0) {
                newSetupdialog.this.ShowDialog(newSetupdialog.this.mcontext.getString(R.string.op21));
                return;
            }
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.savedialog14, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
            this.dialog_btn_cancel = (TextView) inflate.findViewById(R.id.dialog_btn_cancel);
            this.dialog_btn_sure = (TextView) inflate.findViewById(R.id.dialog_btn_sure);
            this.tv_save = (TextView) inflate.findViewById(R.id.tv_save);
            this.tv_share = (TextView) inflate.findViewById(R.id.tv_share);
            this.et_pwd_code = (EditText) inflate.findViewById(R.id.et_pwd_code);
            this.ll_share = (LinearLayout) inflate.findViewById(R.id.ll_share);
            this.ll_save = (LinearLayout) inflate.findViewById(R.id.ll_save);
            this.dialog_message = (TextView) inflate.findViewById(R.id.dialog_message);
            this.cb_bunding_game = (CheckBox) inflate.findViewById(R.id.cb_bunding_game);
            this.tv_bunding_game_name = (TextView) inflate.findViewById(R.id.tv_bunding_game_name);
            this.ll_save.setVisibility(View.VISIBLE);
            this.ll_share.setVisibility(View.GONE);
            this.isSave = true;
            this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
            this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
            newSetupdialog.this.mSunyesMaxGamePresetList = newSetupdialog.this.mDBManager.queryAll(0);
            if (CommonUtils.isStringValid(bluetoothdevmanager.currentGameName)) {
                this.cb_bunding_game.setChecked(true);
            }
            final TextView tv_bunding_game_name = this.tv_bunding_game_name;
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(bluetoothdevmanager.currentGameName);
            tv_bunding_game_name.setText((CharSequence) sb.toString());
            this.tv_bunding_game_name.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.this.showTipPopupWindow3((View) sunyesMaxSavedialoggwindow.this.tv_bunding_game_name, sunyesMaxSavedialoggwindow.this.mContext, newSetupdialog.this.mSunyesMaxGamePresetList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                            final TextView access$2900 = sunyesMaxSavedialoggwindow.this.tv_bunding_game_name;
                            final StringBuilder sb = new StringBuilder();
                            sb.append("");
                            sb.append(newSetupdialog.this.mSunyesMaxGamePresetList.get(n).getAppName());
                            access$2900.setText((CharSequence) sb.toString());
                        }
                    }, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(final View view) {
                        }
                    });
                }
            });
            this.et_pwd_code.addTextChangedListener((TextWatcher) new TextWatcher() {
                public void afterTextChanged(final Editable editable) {
                    if (sunyesMaxSavedialoggwindow.this.et_pwd_code.getText().toString().trim().length() == 8) {
                        new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip27)).show();
                    }
                }

                public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }

                public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                }
            });
            this.tv_save.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if (!sunyesMaxSavedialoggwindow.this.isSave) {
                        sunyesMaxSavedialoggwindow.this.ll_save.setVisibility(View.VISIBLE);
                        sunyesMaxSavedialoggwindow.this.ll_share.setVisibility(View.GONE);
                        sunyesMaxSavedialoggwindow.this.isSave = true;
                        sunyesMaxSavedialoggwindow.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                        sunyesMaxSavedialoggwindow.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                    }
                }
            });
            this.tv_share.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if (sunyesMaxSavedialoggwindow.this.isSave) {
                        sunyesMaxSavedialoggwindow.this.ll_save.setVisibility(View.GONE);
                        sunyesMaxSavedialoggwindow.this.ll_share.setVisibility(View.VISIBLE);
                        sunyesMaxSavedialoggwindow.this.isSave = false;
                        sunyesMaxSavedialoggwindow.this.tv_save.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.black));
                        sunyesMaxSavedialoggwindow.this.tv_share.setBackgroundColor(newSetupdialog.this.mcontext.getResources().getColor(R.color.shape_color));
                    }
                }
            });
            this.dialog_btn_sure.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View view) {
                    if (sunyesMaxSavedialoggwindow.this.isSave) {
                        if (sunyesMaxSavedialoggwindow.this.cb_bunding_game.isChecked() && CommonUtils.isBundingGameNameOk(newSetupdialog.this.mSunyesMaxGamePresetList, sunyesMaxSavedialoggwindow.this.tv_bunding_game_name.getText().toString().trim())) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(newSetupdialog.this.mcontext.getApplicationContext().getDir("spFiles", 0).getPath());
                            sb.append("/");
                            sb.append(sunyesMaxSavedialoggwindow.this.tv_bunding_game_name.getText().toString().trim());
                            sb.append(".ini");
                            final String string = sb.toString();
                            newSetupdialog.this.createLocationTxtFile(string);
                            newSetupdialog.this.mDBManager.updateByGameName(sunyesMaxSavedialoggwindow.this.tv_bunding_game_name.getText().toString().trim(), string, 0);
                        }
                        newSetupdialog.this.changeData();
                        sunyesMaxSavedialoggwindow.this.dismiss();
                        if (newSetupdialog.mLoadingDialog == null) {
                            newSetupdialog.mLoadingDialog = new loadingDialog(newSetupdialog.this.mcontext);
                        }
                        newSetupdialog.mLoadingDialog.showAtLocation((View) newSetupdialog.this.main, 17, 0, 0);
                        newSetupdialog.myhandler.sendEmptyMessageDelayed(201802, 2500L);
                    } else {
                        if (!CommonUtils.isStringValid(sunyesMaxSavedialoggwindow.this.et_pwd_code.getText().toString().trim())) {
                            new ToastDialog(newSetupdialog.this.mcontext, newSetupdialog.this.mcontext.getResources().getString(R.string.new_setup_dialog_tip15)).show();
                            return;
                        }
                        newSetupdialog.uploadFilePath = null;
                        newSetupdialog.shareCode = null;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(newSetupdialog.this.mcontext.getApplicationContext().getFilesDir().getPath());
                        sb2.append("/");
                        sb2.append(sunyesMaxSavedialoggwindow.this.et_pwd_code.getText().toString().trim());
                        sb2.append(".ini");
                        newSetupdialog.uploadFilePath = sb2.toString();
                        newSetupdialog.shareCode = sunyesMaxSavedialoggwindow.this.et_pwd_code.getText().toString().trim();
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(sunyesMaxSavedialoggwindow.this.et_pwd_code.getText().toString().trim());
                        sb3.append(".ini");
                        final String string2 = sb3.toString();
                        newSetupdialog.this.profilesave(newSetupdialog.uploadFilePath);
                        HttpUtil.uploadLogFile(newSetupdialog.myhandler, 201816, 201817, "http://shootingplus.com.cn/shootingplus/open/upload/upload", newSetupdialog.uploadFilePath, string2);
                        sunyesMaxSavedialoggwindow.this.dismiss();
                    }
                }
            });
            this.dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    sunyesMaxSavedialoggwindow.this.dismiss();
                }
            });
        }
    }

    public class syncdingDialog extends PopupWindow {
        private Context mContext;

        public syncdingDialog(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        void init() {
            this.setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.my_progress_dialog2, (ViewGroup) null));
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(true);
            newSetupdialog.this.setCanceledOnTouchOutside(false);
        }
    }

    public class viewSetupDialogwindow extends PopupWindow {
        private Context mContext;
        private SeekBar sb_speed;
        private TextView tv_cancel;
        private TextView tv_speed;
        private TextView tv_sure;

        public viewSetupDialogwindow(final Context mContext) {
            super(mContext);
            this.mContext = mContext;
            this.init();
        }

        private void init() {
            final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.greendialog2, (ViewGroup) null);
            this.setContentView(inflate);
            this.setWidth(-2);
            this.setHeight(-2);
            this.setFocusable(false);
            this.sb_speed = (SeekBar) inflate.findViewById(R.id.sb_speed);
            this.tv_speed = (TextView) inflate.findViewById(R.id.tv_speed);
            this.tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
            this.tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
            if (newSetupdialog.mousespeed > 0) {
                this.sb_speed.setProgress(newSetupdialog.mousespeed - 1);
            } else {
                this.sb_speed.setProgress(newSetupdialog.mousespeed);
            }
            final TextView tv_speed = this.tv_speed;
            final StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(newSetupdialog.mousespeed);
            tv_speed.setText(sb.toString());
            this.sb_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
                    final TextView access$8300 = viewSetupDialogwindow.this.tv_speed;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(n + 1);
                    access$8300.setText(sb.toString());
                }

                public void onStartTrackingTouch(final SeekBar seekBar) {
                }

                public void onStopTrackingTouch(final SeekBar seekBar) {
                }
            });
            this.tv_sure.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    newSetupdialog.mousespeed = viewSetupDialogwindow.this.sb_speed.getProgress() + 1;
                    viewSetupDialogwindow.this.dismiss();
                }
            });
            this.tv_cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    viewSetupDialogwindow.this.dismiss();
                }
            });
        }
    }
}
