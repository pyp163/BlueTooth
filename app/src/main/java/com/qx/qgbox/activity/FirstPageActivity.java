package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qx.qgbox.R;
import com.qx.qgbox.adapters.OfficialGameItemsAdapter;
import com.qx.qgbox.adapters.SunyesMaxGameItemsAdapter;
import com.qx.qgbox.db.DBManager;
import com.qx.qgbox.entitys.AppInfo;
import com.qx.qgbox.entitys.ComKey;
import com.qx.qgbox.entitys.FWInfo;
import com.qx.qgbox.entitys.FactoryInfo;
import com.qx.qgbox.entitys.InstalledApp;
import com.qx.qgbox.entitys.OfficialGame;
import com.qx.qgbox.entitys.OfficialGamePreset;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import com.qx.qgbox.gamemouse.DataSaverM;
import com.qx.qgbox.gamemouse.IniFile;
import com.qx.qgbox.gamepad.DataSaver;
import com.qx.qgbox.pulltorefreshgridview.BaseRefreshLayout;
import com.qx.qgbox.pulltorefreshgridview.DaisyRefreshLayout;
import com.qx.qgbox.service.FloatWindowService;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.AnalyseDataUtil;
import com.qx.qgbox.utils.AnalyseDataUtilS1;
import com.qx.qgbox.utils.AnalyseSpDataUtil;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.utils.ChangeDataUtil;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.DeviceUtils;
import com.qx.qgbox.utils.GpSetupImageSRCUtils1;
import com.qx.qgbox.utils.GpSetupImageSRCUtils2;
import com.qx.qgbox.utils.HttpUrlConfig;
import com.qx.qgbox.utils.HttpUtil;
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.utils.OkHttpUtil;
import com.qx.qgbox.utils.ProjectFilterConfig;
import com.qx.qgbox.views.CustomBigImageDialog;
import com.qx.qgbox.views.CustomDialog;
import com.qx.qgbox.views.CustomTipDialog;
import com.qx.qgbox.views.CustomTipDialog3;
import com.qx.qgbox.views.CustomWarningDialog;
import com.qx.qgbox.views.DownLoadDialog;
import com.qx.qgbox.views.MyLoadDialog;
import com.qx.qgbox.views.MyWindowManager;
import com.qx.qgbox.views.PagerDialog;
import com.qx.qgbox.views.PopMenu;
import com.qx.qgbox.views.PopMenuDle;
import com.qx.qgbox.views.PopMenuSGP;
import com.qx.qgbox.views.ToastDialog;
import com.tencent.mmkv.MMKV;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

public class FirstPageActivity extends Activity {
    public static final int IMAGE_REQUEST_CODE = 201869;
    public static final int MSG_ON_ANALYSE_BLUE_DATA = 6;
    public static final int MSG_ON_ANALYSE_CRC_DATA = 201901;
    public static final int MSG_ON_CHANGE_MAP0 = 201811;
    public static final int MSG_ON_CHANGE_MAP1 = 201812;
    public static final int MSG_ON_CHANGE_MAP2 = 201813;
    public static final int MSG_ON_CHANGE_MAP3 = 201814;
    public static final int MSG_ON_CHANGE_MAP4 = 201815;
    public static final int MSG_ON_CHECK_PRESET_PIC = 201868;
    public static final int MSG_ON_DEVICE_NOT_SUPPORT_PORTRAIT_SCREEN = 201907;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_FAIL = 201831;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_FAIL_GP = 201853;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_SUCCESS = 201830;
    public static final int MSG_ON_DOWNLOAD_F1_FILE_SUCCESS_GP = 201852;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_FAIL = 201835;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_FAIL_GP = 201857;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_SUCCESS = 201834;
    public static final int MSG_ON_DOWNLOAD_F2_FILE_SUCCESS_GP = 201856;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_FAIL = 201839;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_FAIL_GP = 201861;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_SUCCESS = 201838;
    public static final int MSG_ON_DOWNLOAD_F3_FILE_SUCCESS_GP = 201860;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_FAIL = 201843;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_FAIL_GP = 201865;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_SUCCESS = 201842;
    public static final int MSG_ON_DOWNLOAD_F4_FILE_SUCCESS_GP = 201864;
    public static final int MSG_ON_DOWNLOAD_F5_FILE_FAIL = 201847;
    public static final int MSG_ON_DOWNLOAD_F5_FILE_SUCCESS = 201846;
    public static final int MSG_ON_DOWNLOAD_OFFICIAL_GAME_PRESET_FAIL = 201877;
    public static final int MSG_ON_DOWNLOAD_OFFICIAL_GAME_PRESET_SUCCESS = 201876;
    public static final int MSG_ON_FIRST_LOAD_MAP_FAIL = 17;
    public static final int MSG_ON_FIRST_LOAD_MAP_SUCCESS = 16;
    public static final int MSG_ON_GET_CONTENT_FAIL = 201873;
    public static final int MSG_ON_GET_CONTENT_SUCCESS = 201872;
    public static final int MSG_ON_GET_DEVICE_ALL_INFOS_SUCCESS = 7;
    public static final int MSG_ON_GET_DEVICE_ALL_INFOS_TIME_OUT = 9;
    public static final int MSG_ON_GET_ENCRPY_BYTE_ARRAY_FAIL = 201906;
    public static final int MSG_ON_GET_ENCRPY_BYTE_ARRAY_SUCCESS = 201905;
    public static final int MSG_ON_GET_EPST_MODE_CODE_FAIL = 25;
    public static final int MSG_ON_GET_EPST_MODE_CODE_SUCCESS = 24;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_FAIL = 201829;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_FAIL_GP = 201851;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_SUCCESS = 201828;
    public static final int MSG_ON_GET_F1_FILE_DOWNLOAD_URL_SUCCESS_GP = 201850;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_FAIL = 201833;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_FAIL_GP = 201855;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_SUCCESS = 201832;
    public static final int MSG_ON_GET_F2_FILE_DOWNLOAD_URL_SUCCESS_GP = 201854;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_FAIL = 201837;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_FAIL_GP = 201859;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_SUCCESS = 201836;
    public static final int MSG_ON_GET_F3_FILE_DOWNLOAD_URL_SUCCESS_GP = 201858;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_FAIL = 201841;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_FAIL_GP = 201863;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_SUCCESS = 201840;
    public static final int MSG_ON_GET_F4_FILE_DOWNLOAD_URL_SUCCESS_GP = 201862;
    public static final int MSG_ON_GET_F5_FILE_DOWNLOAD_URL_FAIL = 201845;
    public static final int MSG_ON_GET_F5_FILE_DOWNLOAD_URL_SUCCESS = 201844;
    public static final int MSG_ON_GET_OFFICIAL_GAME_FAIL = 201871;
    public static final int MSG_ON_GET_OFFICIAL_GAME_PRESET_FAIL = 201875;
    public static final int MSG_ON_GET_OFFICIAL_GAME_PRESET_SUCCESS = 201874;
    public static final int MSG_ON_GET_OFFICIAL_GAME_SUCCESS = 201870;
    public static final int MSG_ON_INIT_DEVICE_INFOS = 8;
    public static final int MSG_ON_PULL_DOWN_REFRESH_TIME_OUT = 201904;
    public static final int MSG_ON_REGET_DEVICE_INFO = 23;
    public static final int MSG_ON_REQUEST_APP_INFO_ERROR = 21;
    public static final int MSG_ON_REQUEST_APP_INFO_SUCCESS = 20;
    public static final int MSG_ON_REQUEST_FW_INFO_ERROR = 19;
    public static final int MSG_ON_REQUEST_FW_INFO_SUCCESS = 18;
    public static final int MSG_ON_SAVE_LOCALTION_GAME_PRESET_TO_DEVICE_FAIL = 201903;
    public static final int MSG_ON_SAVE_LOCALTION_GAME_PRESET_TO_DEVICE_SUCCESS = 201902;
    public static final int MSG_ON_SAVE_OFFICIAL_GAME_PRESET_TO_DEVICE = 201878;
    public static final int MSG_ON_SAVE_OFFICIAL_GAME_PRESET_TO_DEVICE_FAIL = 201880;
    public static final int MSG_ON_SAVE_OFFICIAL_GAME_PRESET_TO_DEVICE_SUCCESS = 201879;
    public static final int MSG_ON_SEND_SYNC_DATA_TO_DEVICE = 201849;
    public static final int MSG_ON_SEND_SYNC_DATA_TO_DEVICE_GP = 201867;
    public static final int MSG_ON_START_LOAD_MAP = 201805;
    public static final int MSG_ON_SYNC_NEXT_LOCATION_DATA = 201848;
    public static final int MSG_ON_SYNC_NEXT_LOCATION_DATA_GP = 201866;
    public static final int MSG_ON_UPDATE_FW_SUCCESS = 22;
    public static final int MS_CONN = 1;
    public static final int MS_ON_BLE_CONNECTED = 3;
    public static final int MS_ON_BLE_DISCONNECT = 0;
    public static final int MS_REVDATA = 2;
    private static final String TAG = "FirstPageActivity";
    public static String appName = "";
    public static String deviceSnCode;
    public static int deviceType = 0;
    private static DownLoadDialog downLoadDialog;
    public static String fwVerCode;
    public static String hwVersion;
    public static int installing = 0;
    public static FirstPageActivity instance;
    public static final boolean isApplicationMarketVersion = false;
    public static boolean isDown = false;
    public static boolean isF1 = false;
    public static boolean isF2 = false;
    public static boolean isF3 = false;
    public static boolean isF4 = false;
    public static boolean isF5 = false;
    public static final boolean isGooglePlayVersion = false;
    private static boolean isHideAutoSyncPresetTip = false;
    public static boolean isLeft = false;
    private static boolean isMapInfosSuccess = true;
    private static boolean isNewDevice = false;
    private static boolean isReConnect = false;
    public static boolean isResetDevice = false;
    public static boolean isRight = false;
    public static boolean isSaveOfficialGamePresetNow = false;
    public static boolean isShowFBtn = true;
    public static boolean isSyncPresetByGameIdNow = false;
    public static boolean isSyncPresetNow = false;
    public static boolean isTestKeyMode = false;
    public static boolean isUp = false;
    public static boolean isUpdateAPP = true;
    public static boolean isUpdateFW = true;
    public static boolean issaveLocaltionPresetFileToDevice = false;
    public static ImageView iv_menu;
    public static int length = 351;
    static int lengthGp = 966;
    private static String locale;
    public static String location = "";
    public static String locationPath = "";
    private static AppInfo mAppInfo;
    private static Context mContext;
    private static CustomDialog mCustomDialog;
    private static CustomTipDialog mCustomTipDialog;
    private static CustomTipDialog3 mCustomTipDialog3;
    private static FWInfo mFWInfo;
    private static MyLoadDialog mMyLoadDialog;
    private static syncdingDialog mSyncdingDialog;
    public static Handler mUiHandler;
    public static String macAddress;
    public static DataSaverM[] mdatasaver = new DataSaverM[length];
    public static DataSaver[] mdatasaverGp = new DataSaver[lengthGp];
    public static String model = "";
    private static presetCloudSyncDialog mpresetCloudSyncDialog;
    private static presetCloudSyncDialog2 mpresetCloudSyncDialog2;
    public static String officialGamePresetDownlocaPath = "";
    public static int platform = 1;
    public static String projectName;
    private static int reSendNumgp = 0;
    private static int reSendNumsp = 0;
    public static String resolution = "";
    private static int saveNum = 0;
    private static boolean showAutoSyncPresetTip = false;
    public static String syncCurrentLocation = "";
    public static String syncToastStr = "";
    private static TextView textconnstate;
    private byte[] EncrpyArray = null;
    private byte[] MapDataByteArray = null;
    private byte[] RandomByteArray = null;
    private CheckBox cb_fbtn;
    private String currentDownLoadGameName = null;
    private SimpleDraweeView current_device_icon;
    private CustomWarningDialog customWarningDialog;
    private byte[] deviceEncrpyArray = null;
    private String epseCode = "";
    private int getGameListNum = 0;
    private int getGamePresetNum = 0;
    private GridView gv_official_game;
    private GridView gv_official_game_sunyes_max;
    private String ignoreVersion = "1.01";
    private ArrayList<String> imageUrlList = new ArrayList();
    private boolean isCloseHelp = false;
    private boolean isEnterCode = false;
    private boolean isOpenGame = false;
    private boolean isPullDownRefresh = false;
    private int isss = 0;
    private ImageView iv_close_help;
    private ImageView iv_cloud_ask;
    private ImageView iv_didnot_connect_device;/**/
    private ImageView iv_game_list_ask;
    private ImageView iv_help_ask;
    private MMKV kv;
    private LinearLayout ll_cloud;
    private LinearLayout ll_connstate;
    private LinearLayout ll_factory_manager;
    private LinearLayout ll_game_list;
    private LinearLayout ll_help;
    private LinearLayout ll_help_in;
    private CustomBigImageDialog mCustomBigImageDialog = null;
    private DBManager mDBManager;
    private editPresetDialog mEditPresetDialog = null;
    private ArrayList<FWInfo> mFWInfoList = new ArrayList();
    private FactoryInfo mFactoryInfo;
    private OfficialGameItemsAdapter mOfficialGameItemsAdapter;
    private ArrayList<OfficialGame> mOfficialGameList = new ArrayList();
    private PopMenuDle mPopMenuDle;
    private DaisyRefreshLayout mRefreshLayout;
    private SunyesMaxGameItemsAdapter mSunyesMaxGameItemsAdapter;
    private ArrayList<SunyesMaxGamePreset> mSunyesMaxGamePresetList = new ArrayList();
    private LinearLayout main;
    private PagerDialog pagerDialog = null;
    private PopMenu popMenu = null;
    private PopMenuSGP popMenuSGP = null;
    private AdapterView.OnItemClickListener popmenuItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("---------position = ");
            stringBuilder.append(paramAnonymousInt);
            MyLog.i("my_tag", stringBuilder.toString());
            switch (paramAnonymousInt) {
                default:
                    return;
                case MSG_ON_GET_DEVICE_ALL_INFOS_TIME_OUT:
                    moveTaskToBack(true);
                    popMenu.dismiss();
                    return;
                case MSG_ON_INIT_DEVICE_INFOS:
                    if ((CommonUtils.isStringValid(projectName)) && ((projectName.equals("Q789")) || (projectName.equalsIgnoreCase("789P"))) && (bluetoothdevmanager.mConnectionState != 0))
                        startActivity(new Intent(FirstPageActivity.this, NewHelpActivity.class));
                    else
                        moveTaskToBack(true);
                    popMenu.dismiss();
                    return;
                case 7:
                    if ((CommonUtils.isStringValid(projectName)) && ((projectName.equals("Q789")) || (projectName.equalsIgnoreCase("789P"))) && (bluetoothdevmanager.mConnectionState != 0))
                        startActivity(new Intent(FirstPageActivity.this, UpdateFirmwareActivity.class));
                    else
                        startActivity(new Intent(FirstPageActivity.this, NewHelpActivity.class));
                    popMenu.dismiss();
                    return;
                case 6:
                    if (bluetoothdevmanager.mNewSetupdialog != null) {
                        if (bluetoothdevmanager.mNewSetupdialog.isShowing())
                            bluetoothdevmanager.mNewSetupdialog.dismiss();
                        bluetoothdevmanager.mNewSetupdialog.cancel();
                        bluetoothdevmanager.mNewSetupdialog = null;
                    }
                    customWarningDialog = new CustomWarningDialog.Builder(mContext)
                            .setTitle(mContext.getResources().getString(R.string.warning))
                            .setMessage(mContext.getResources().getString(R.string.enter_reset_device2))
                            .setPositiveButton(mContext.getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                                    BlueCmdManager.sendResetCmd();
                                    if (bluetoothdevmanager.devicemode == 0)
                                        MyApplication.setDatapro(null);
                                    else
                                        MyApplication.setGpDatapro(null);
                                    isResetDevice = true;
                                    mUiHandler.sendEmptyMessageDelayed(8, 1500L);
                                    customWarningDialog.dismiss();
                                }
                            }).setNegativeButton(mContext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {
                                    customWarningDialog.dismiss();
                                }
                            }).create();
                    customWarningDialog.setCanceledOnTouchOutside(false);
                    customWarningDialog.show();
                    popMenu.dismiss();
                    return;
                case 5:
                    if ((bluetoothdevmanager.mConnectionState != 0) && ((!isUpdateFW) || (bluetoothdevmanager.devicemode == 0))) {
                        if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device759)))
                            startActivity(new Intent(FirstPageActivity.this, UpdateFirmwareActivity.class));
                        else if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763)))
                            startActivity(new Intent(FirstPageActivity.this, UpdateFirmwareActivity763.class));
                        else
                            startActivity(new Intent(FirstPageActivity.this, UpdateFirmwareActivity.class));
                    } else
                        new ToastDialog(mContext, mContext.getResources().getString(R.string.first_page_tip2)).show();
                    popMenu.dismiss();
                    return;
                case 4:
                    if (!isUpdateAPP)
                        startActivity(new Intent(FirstPageActivity.this, UpdateAppActivity1.class));
                    else
                        new ToastDialog(mContext, mContext.getResources().getString(R.string.first_page_tip1)).show();
                    popMenu.dismiss();
                    return;
                case 3:
                    if ((hwVersion == null) || (hwVersion == ""))
                        hwVersion = "HW:UNKNOWN";
                    if (mCustomDialog != null) {
                        if (mCustomDialog.isShowing())
                            mCustomDialog.dismiss();
                        mCustomDialog = null;
                    }
                    mCustomDialog = new CustomDialog(mContext, getResources().getString(R.string.my_hwversion), hwVersion, getResources().getDrawable(R.mipmap.ic_launcher), false, false);
                    mCustomDialog.setClicklistener(new CustomDialog.ClickListenerInterface() {
                        public void doCancel() {
                            mCustomDialog.dismiss();
                        }

                        public void doConfirm() {
                            mCustomDialog.dismiss();
                        }
                    });
                    mCustomDialog.show();
                    popMenu.dismiss();
                    return;
                case 2:
                    showMyDialogForAbout();
                    popMenu.dismiss();
                    return;
                case 1:
                    isTestKeyMode = true;
                    KeyTestActivity.goHome(FirstPageActivity.mContext);
                    popMenu.dismiss();
                    return;
                case 0:
            }
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(mContext))
                    askForPermission();
                else
                    new ToastDialog(FirstPageActivity.mContext, mContext.getResources().getString(R.string.indicator5)).show();
            } else
                new ToastDialog(mContext, mContext.getResources().getString(R.string.indicator5)).show();
            popMenu.dismiss();
        }
    };
    private TextView tv_device_content;
    private TextView tv_factory_contact;
    private TextView tv_factory_name;
    private TextView tv_factory_name1;
    private PowerManager.WakeLock wakeLock;

    private void analyseBlueData(byte[] paramArrayOfByte) {
        int i;
        if (paramArrayOfByte[2] == 1) {
            i = 3;
            while (i < paramArrayOfByte.length - 1) {
                StringBuilder localObject1 = new StringBuilder();
                localObject1.append(deviceSnCode);
                localObject1.append(paramArrayOfByte[i]);
                deviceSnCode = localObject1.toString();
                i += 1;
            }
            BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_MAP_MODE);
        }
        if (paramArrayOfByte[2] == 35) {
            if (bluetoothdevmanager.mDeviceAddress == null)
                BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_CURRENT_WORK_MODE);
            bluetoothdevmanager.defaultGameId.clear();
            if (bluetoothdevmanager.mapMaxNum == 1) {
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6])));
            } else if (bluetoothdevmanager.mapMaxNum == 2) {
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8])));
            } else if (bluetoothdevmanager.mapMaxNum == 3) {
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[9], paramArrayOfByte[10])));
            } else if (bluetoothdevmanager.mapMaxNum == 4) {
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[9], paramArrayOfByte[10])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[11], paramArrayOfByte[12])));
            } else if ((paramArrayOfByte[1] & 0xFF) == 15) {
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[9], paramArrayOfByte[10])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[11], paramArrayOfByte[12])));
                bluetoothdevmanager.defaultGameId.add(Integer.valueOf(getIntegerByBit(paramArrayOfByte[13], paramArrayOfByte[14])));
            }
            if ((isNewDevice) && (bluetoothdevmanager.defaultGameId.size() > 0) && (bluetoothdevmanager.mapMaxNum == 1) && (!isHideAutoSyncPresetTip) && (!isResetDevice)) {
                showAutoSyncPresetTip = true;
                isNewDevice = false;
                mUiHandler.sendEmptyMessage(MSG_ON_ANALYSE_CRC_DATA);
            }
        }
        if (paramArrayOfByte[2] == 2) {
            BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_CURRENT_WORK_MODE);
            bluetoothdevmanager.mapVersion = getIntegerByBit(paramArrayOfByte[4], paramArrayOfByte[5]);
            StringBuilder localObject1 = new StringBuilder();
            localObject1.append("------mapVersion = ");
            localObject1.append(bluetoothdevmanager.mapVersion);
            MyLog.i("my_tag", localObject1.toString());
            bluetoothdevmanager.mapMaxNum = paramArrayOfByte[6];
            localObject1 = new StringBuilder();
            localObject1.append("--- 最大存储映射的个数 =  ");
            localObject1.append(bluetoothdevmanager.mapMaxNum);
            MyLog.i("my_tag", localObject1.toString());
            if ((paramArrayOfByte[1] & 0xFF) >= 9) {
                if (paramArrayOfByte[8] == 0)
                    isNewDevice = true;
                else
                    isNewDevice = false;
                BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_IMAGE_INDEX);
            }
            if ((paramArrayOfByte[1] & 0xFF) >= 11) {
                bluetoothdevmanager.maxJoystick = paramArrayOfByte[9];
                localObject1 = new StringBuilder();
                localObject1.append("------maxJoystick = ");
                localObject1.append(bluetoothdevmanager.maxJoystick);
                MyLog.i("my_tag", localObject1.toString());
                bluetoothdevmanager.maxKey = paramArrayOfByte[10];
                localObject1 = new StringBuilder();
                localObject1.append("------maxKey = ");
                localObject1.append(bluetoothdevmanager.maxKey);
                MyLog.i("my_tag", localObject1.toString());
            } else {
                bluetoothdevmanager.maxKey = 24;
            }
            if ((paramArrayOfByte[1] & 0xFF) >= 13) {
                bluetoothdevmanager.maxMacro = paramArrayOfByte[11];
                localObject1 = new StringBuilder();
                localObject1.append("------maxMacro = ");
                localObject1.append(bluetoothdevmanager.maxMacro);
                MyLog.i("my_tag", localObject1.toString());
                bluetoothdevmanager.maxMacroPoint = paramArrayOfByte[12];
                localObject1 = new StringBuilder();
                localObject1.append("------maxMacroPoint = ");
                localObject1.append(bluetoothdevmanager.maxMacroPoint);
                MyLog.i("my_tag", localObject1.toString());
            } else {
                bluetoothdevmanager.maxMacro = 0;
                bluetoothdevmanager.maxMacroPoint = 0;
            }
        }
        if (paramArrayOfByte[2] == 3) {
            bluetoothdevmanager.deviceVID = String.valueOf(paramArrayOfByte[4] << 8 | paramArrayOfByte[3] & 0xFF);
            bluetoothdevmanager.devicePID = String.valueOf(paramArrayOfByte[6] << 8 | paramArrayOfByte[5] & 0xFF);
            if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7")) {
                MyApplication.setmGpSetupImageSRCUtils1(new GpSetupImageSRCUtils1(mContext));
                MyApplication.setmGpSetupImageSRCUtils2(new GpSetupImageSRCUtils2(mContext));
            }
            String localObject2 = null;
            if (paramArrayOfByte[7] != 0)
                localObject2 = String.valueOf(paramArrayOfByte[7]);
            String localObject1 = localObject2;
            if (paramArrayOfByte[8] != 0)
                if (localObject2 != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(localObject2);
                    stringBuilder.append(",");
                    stringBuilder.append(paramArrayOfByte[8]);
                    localObject1 = stringBuilder.toString();
                } else {
                    localObject1 = String.valueOf(paramArrayOfByte[8]);
                }
            localObject2 = localObject1;
            if (paramArrayOfByte[9] != 0)
                if (localObject1 != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(localObject1);
                    stringBuilder.append(",");
                    stringBuilder.append(String.valueOf(paramArrayOfByte[9]));
                    localObject2 = stringBuilder.toString();
                } else {
                    localObject2 = String.valueOf(paramArrayOfByte[9]);
                }
            localObject1 = localObject2;
            if (paramArrayOfByte[10] != 0)
                if (localObject2 != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(localObject2);
                    stringBuilder.append(",");
                    stringBuilder.append(paramArrayOfByte[10]);
                    localObject1 = stringBuilder.toString();
                } else {
                    localObject1 = String.valueOf(paramArrayOfByte[10]);
                }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[11])));
            stringBuilder.append(".");
            stringBuilder.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[12])));
            stringBuilder.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[13])));
            fwVerCode = stringBuilder.toString();
            projectName = CommonUtils.asciiToString(localObject1);
            if (!projectName.equalsIgnoreCase(CommonUtils.asciiToString(localObject1)))
                isUpdateFW = true;
            StringBuilder stringBuilderDev = new StringBuilder();
            stringBuilderDev.append("------ deviceVID = ");
            stringBuilderDev.append(bluetoothdevmanager.deviceVID);
            MyLog.i("my_tag", stringBuilderDev.toString());
            if ((paramArrayOfByte[1] & 0xFF) >= 15) {
                bluetoothdevmanager.gpWorkModeCode = paramArrayOfByte[14];
                stringBuilderDev = new StringBuilder();
                stringBuilderDev.append("------ gpWorkModeCode = ");
                stringBuilderDev.append(bluetoothdevmanager.gpWorkModeCode);
                MyLog.i("my_tag", stringBuilderDev.toString());
            }
            if (((paramArrayOfByte[1] & 0xFF) >= 16) && (paramArrayOfByte[15] != 0))
                i = paramArrayOfByte[15];
            hwVersion = "";
            if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter))) {
                stringBuilderDev = new StringBuilder();
                stringBuilderDev.append("Project:");
                stringBuilderDev.append(ProjectFilterConfig.prijectFilter);
                stringBuilderDev.append("\nHardware:");
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[11])));
                stringBuilderDev.append(".");
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[12])));
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[13])));
                stringBuilderDev.append("\nPID:");
                stringBuilderDev.append(bluetoothdevmanager.devicePID);
                stringBuilderDev.append("\nVID:");
                stringBuilderDev.append(bluetoothdevmanager.deviceVID);
                hwVersion = stringBuilderDev.toString();
            } else {
                stringBuilderDev = new StringBuilder();
                stringBuilderDev.append("Project:");
                stringBuilderDev.append(CommonUtils.asciiToString((String) localObject1));
                stringBuilderDev.append("\nHardware:");
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[11])));
                stringBuilderDev.append(".");
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[12])));
                stringBuilderDev.append(CommonUtils.asciiToString(String.valueOf(paramArrayOfByte[13])));
                stringBuilderDev.append("\nPID:");
                stringBuilderDev.append(bluetoothdevmanager.devicePID);
                stringBuilderDev.append("\nVID:");
                stringBuilderDev.append(bluetoothdevmanager.deviceVID);
                hwVersion = stringBuilderDev.toString();
            }
            checkFWOnServer(projectName);
            BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_SN_CODE);
        }
        if (paramArrayOfByte[2] == 8)
            BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_MAC_ADDRESS);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("receiveNormalCmd:");
        stringBuilder.append(CommonUtils.byteToString(paramArrayOfByte));
        MyLog.i("sendNormalCmd", stringBuilder.toString());
        if (paramArrayOfByte[2] == 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[3] & 0xFF)));
            stringBuilder.append(":");
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[4] & 0xFF)));
            stringBuilder.append(":");
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[5] & 0xFF)));
            stringBuilder.append(":");
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[6] & 0xFF)));
            stringBuilder.append(":");
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[7] & 0xFF)));
            stringBuilder.append(":");
            stringBuilder.append(strUtil(Integer.toHexString(paramArrayOfByte[8] & 0xFF)).trim());
            bluetoothdevmanager.mDeviceAddress = stringBuilder.toString();
            macAddress = bluetoothdevmanager.mDeviceAddress.toUpperCase();
            mUiHandler.sendEmptyMessage(7);
            StringBuilder paramStrBuilder = new StringBuilder();
            paramStrBuilder.append("------macAddress = ");
            paramStrBuilder.append(macAddress);
            MyLog.i("my_tag", paramStrBuilder.toString());
        }
    }

    private void changeData() {
        byte[] arrayOfByte = new byte[264];
        int j;
        int i;
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            i = (int) bluetoothdevmanager.screenwp;
            j = (int) bluetoothdevmanager.screenhp;
        } else {
            i = (int) bluetoothdevmanager.screenhp;
            j = (int) bluetoothdevmanager.screenwp;
        }
        arrayOfByte = AnalyseSpDataUtil.changeDataToByteArray(mdatasaver, i, j, bluetoothdevmanager.mapVersion, 2);
        byte[] localObject = new byte[262];
        System.arraycopy(arrayOfByte, 2, localObject, 0, 262);
        int ij = CommonUtils.CRC_GetModbus16(localObject, 262);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------save checkCode = ");
        stringBuilder.append(i);
        MyLog.i("crc_tag", stringBuilder.toString());
        localObject = ChangeDataUtil.intToByteArray(ij);
        arrayOfByte[0] = localObject[1];
        arrayOfByte[1] = localObject[0];
        if (bluetoothdevmanager.servicehandle != null) {
            Message message = new Message();
            message.what = 20013;
            message.obj = arrayOfByte;
            bluetoothdevmanager.servicehandle.sendMessage(message);
            MyApplication.setSaveData(arrayOfByte);
        }
    }

    private void changeDataGpV5() {
        int i = 0;
        int j = 0;
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            i = (int) bluetoothdevmanager.screenwp;
        } else {
            j = (int) bluetoothdevmanager.screenhp;
        }
//        for (int j = (int) bluetoothdevmanager.screenhp; ; j = (int) bluetoothdevmanager.screenwp) {
//            break;
//            i = (int) bluetoothdevmanager.screenhp;
//        }

        //TODO 待改动
        byte[] arrayOfByte = AnalyseDataUtil.changeDataToByteArray(mdatasaverGp, i, j, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---dataproSave = ");
        stringBuilder.append(arrayOfByte.length);
        MyLog.i("my_tag", stringBuilder.toString());
        new StringBuilder(arrayOfByte.length);
        if (bluetoothdevmanager.servicehandle != null) {
            Message message = new Message();
            message.what = 20013;
            message.obj = arrayOfByte;
            bluetoothdevmanager.servicehandle.sendMessage(message);
            MyApplication.setSaveData(arrayOfByte);
        }
    }

    private void checkFWOnServer(String paramString) {
        if ((CommonUtils.isStringValid(paramString)) && (!fwVerCode.contains("2."))) {
            if (bluetoothdevmanager.devicemode == 0) {
                if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763)))
                    OkHttpUtil.get(mUiHandler, HttpUrlConfig.getUrlByPname("0", paramString, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, 1, ProjectFilterConfig.device763Param), 18, 19);
                else
                    OkHttpUtil.get(mUiHandler, HttpUrlConfig.getUrlByPname("0", paramString, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, 1, ProjectFilterConfig.device759Param), 18, 19);
            } else if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763)))
                OkHttpUtil.get(mUiHandler, HttpUrlConfig.getUrlByPname("1", paramString, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, ProjectFilterConfig.device763Param), 18, 19);
            else
                OkHttpUtil.get(mUiHandler, HttpUrlConfig.getUrlByPname("1", paramString, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, ProjectFilterConfig.device759Param), 18, 19);
        } else
            isUpdateFW = true;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("----mBluetoothName = ");
        localStringBuilder.append(bluetoothdevmanager.mBluetoothName);
        MyLog.i("my_tag", localStringBuilder.toString());
        if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter))) {
            OkHttpUtil.get(mUiHandler, HttpUrlConfig.getConteneUrl(ProjectFilterConfig.prijectFilter), 201872, 201873);
            return;
        }
        OkHttpUtil.get(mUiHandler, HttpUrlConfig.getConteneUrl(paramString), 201872, 201873);
    }

    private void downloadOfficialFile(JSONObject paramJSONObject, int paramInt1, int paramInt2, int paramInt3, String paramString) {
        try {
            if ((paramJSONObject.getInt("code") == 1000) && (paramJSONObject.getString("message").equalsIgnoreCase("success")) && (CommonUtils.isStringValid(paramJSONObject.getString("url")))) {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("----gameId = ");
                localStringBuilder.append(paramJSONObject.getString("gameId"));
                MyLog.i("my_tag", localStringBuilder.toString());
                locationPath = null;
                if (CommonUtils.isStringValid(paramJSONObject.getString("eurl"))) {
                    String stringJs = paramJSONObject.getString("eurl");
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
                    localStringBuilder.append("/");
                    localStringBuilder.append(projectName);
                    localStringBuilder.append(stringJs);
                    localStringBuilder.append(".txt");
                    locationPath = localStringBuilder.toString();
                } else {
                    String stringJs = paramJSONObject.getString("url");
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
                    localStringBuilder.append("/");
                    localStringBuilder.append(projectName);
                    localStringBuilder.append(stringJs);
                    localStringBuilder.append(".ini");
                    locationPath = localStringBuilder.toString();
                }
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("---下载文件的保存路径 = ");
                localStringBuilder.append(locationPath);
                MyLog.i("my_tag", localStringBuilder.toString());
                File file = new File(locationPath);
                if (file.exists())
                    file.delete();
                HttpUtil.downloadFile(mUiHandler, paramInt2, paramInt3, file.getAbsolutePath(), locationPath);
                return;
            }
            mUiHandler.sendEmptyMessage(paramInt1);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            mUiHandler.sendEmptyMessage(paramInt1);
        }
    }

    private void downloadPresetTo(OfficialGamePreset paramOfficialGamePreset, String paramString) {
        officialGamePresetDownlocaPath = null;
        if (!CommonUtils.isStringValid(paramOfficialGamePreset.getPresetTxtFileUrl())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
            stringBuilder.append("/");
            stringBuilder.append(projectName);
            stringBuilder.append(syncCurrentLocation);
            stringBuilder.append(".ini");
            officialGamePresetDownlocaPath = stringBuilder.toString();
        } else {
            paramString = paramOfficialGamePreset.getPresetTxtFileUrl();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
            stringBuilder.append("/");
            stringBuilder.append(projectName);
            stringBuilder.append(syncCurrentLocation);
            stringBuilder.append(".txt");
            officialGamePresetDownlocaPath = stringBuilder.toString();
        }
        File file = new File(officialGamePresetDownlocaPath);
        if (file.exists())
            file.delete();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---downloadUrl = ");
        stringBuilder.append(paramString);
        MyLog.i("my_tag", stringBuilder.toString());
        HttpUtil.downloadFile(mUiHandler, 201876, 201877, paramString, officialGamePresetDownlocaPath);
    }

    public static FirstPageActivity getInstance() {
        return instance;
    }

    private int getIntegerByBit(byte paramByte1, byte paramByte2) {
        return paramByte1 & 0xFF | paramByte2 << 8;
    }

    public static void goHome(Context paramContext) {
        Intent localIntent = new Intent(paramContext, FirstPageActivity.class);
        localIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//131072
        localIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);//268435456
        paramContext.startActivity(localIntent);
    }

    private void initHelpView() {
        this.ll_help = ((LinearLayout) findViewById(R.id.ll_help));
        this.ll_help_in = ((LinearLayout) findViewById(R.id.ll_help_in));
        this.iv_close_help = ((ImageView) findViewById(R.id.iv_close_help));
        this.iv_help_ask = ((ImageView) findViewById(R.id.iv_help_ask));
        this.kv.decodeBool("is_close_help_flag", false);
        if (this.isCloseHelp)
            this.ll_help.setVisibility(View.GONE);
        else
            this.ll_help.setVisibility(View.VISIBLE);
        this.ll_help_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(FirstPageActivity.this, NewHelpActivity.class));
            }
        });
        this.iv_close_help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mCustomDialog != null) {
                    if (mCustomDialog.isShowing())
                        mCustomDialog.dismiss();
                    mCustomDialog = null;
                }
                mCustomDialog = new CustomDialog(mContext, getResources().getString(R.string.op46), getResources().getString(R.string.first_page_tip33), getResources().getDrawable(R.mipmap.ic_launcher), true, false);
                mCustomDialog.setClicklistener(new CustomDialog.ClickListenerInterface() {
                    public void doCancel() {
                        mCustomDialog.dismiss();
                    }

                    public void doConfirm() {
                        //Todo 待改动
                        //FirstPageActivity.access$1702(FirstPageActivity.this, true);
                        ll_help.setVisibility(View.GONE);
                        kv.encode("is_close_help_flag", isCloseHelp);
                        mCustomDialog.dismiss();
                    }
                });
                mCustomDialog.show();
            }
        });
        this.iv_help_ask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mCustomTipDialog != null) {
                    if (mCustomTipDialog.isShowing())
                        mCustomTipDialog.dismiss();
                    mCustomTipDialog = null;
                }
                mCustomTipDialog = new CustomTipDialog(FirstPageActivity.mContext, getResources().getString(R.string.op46), getResources().getString(R.string.first_page_tip32), getResources().getDrawable(R.mipmap.ic_launcher));
                mCustomTipDialog.setClicklistener(new CustomTipDialog.ClickListenerInterface() {
                    public void doConfirm() {
                        mCustomTipDialog.dismiss();
                    }
                });
                mCustomTipDialog.show();
            }
        });
    }

    private void initUpdateUI() {
        if ((!isUpdateFW) && (!isUpdateAPP)) {
            iv_menu.setImageDrawable(getResources().getDrawable(R.mipmap.menu_2));
            return;
        }
        if (((!isUpdateFW) && (isUpdateAPP)) || ((isUpdateFW) && (!isUpdateAPP))) {
            iv_menu.setImageDrawable(getResources().getDrawable(R.mipmap.menu_1));
            return;
        }
        iv_menu.setImageDrawable(getResources().getDrawable(R.mipmap.menu));
    }

    private void initViewFactoryManager() {
        this.ll_factory_manager = ((LinearLayout) findViewById(R.id.ll_factory_manager));
        this.current_device_icon = ((SimpleDraweeView) findViewById(R.id.current_device_icon));
        this.tv_device_content = ((TextView) findViewById(R.id.tv_device_content));
        this.tv_factory_name = ((TextView) findViewById(R.id.tv_factory_name));
        this.tv_factory_name1 = ((TextView) findViewById(R.id.tv_factory_name1));
        this.tv_factory_contact = ((TextView) findViewById(R.id.tv_factory_contact));
        this.ll_factory_manager.setVisibility(View.GONE);
    }

    private void initViewOfSunyesMaxGame() {
        this.gv_official_game_sunyes_max = ((GridView) findViewById(R.id.gv_official_game_sunyes_max));
        this.mSunyesMaxGameItemsAdapter = new SunyesMaxGameItemsAdapter(mContext, this.mSunyesMaxGamePresetList);
        this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(mContext));
        this.gv_official_game_sunyes_max.setAdapter(this.mSunyesMaxGameItemsAdapter);
        this.gv_official_game_sunyes_max.setVisibility(View.VISIBLE);
        this.gv_official_game_sunyes_max.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong) {
                if (paramAnonymousInt == mSunyesMaxGamePresetList.size() - 1) {
                    startActivity(new Intent(FirstPageActivity.this, InstalledAppListActivity.class));
                    return;
                }
                popMenuSGP = new PopMenuSGP(FirstPageActivity.mContext, 0);
                popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list2), R.mipmap.pointb1));
                popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list3), R.mipmap.pointb1));
                popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list4), R.mipmap.pointb1));
                popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list5), R.mipmap.pointb1));
                popMenuSGP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long) {
                        int j = 0;
                        int i = 0;
                        switch (paramAnonymous2Int) {
                            default:
                                break;
                            case 3:
                                mDBManager.delete(mSunyesMaxGamePresetList.get(paramAnonymousInt).getSmgpId());
                                CommonUtils.delDrawable(mContext, mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName());
                                if (bluetoothdevmanager.devicemode == 0)
                                    mDBManager.queryAll(0);
                                else
                                    mDBManager.queryAll(1);
                                mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(mContext));
                                mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                                break;
                            case 2:
                                if (!CommonUtils.isAvilible(mContext, mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppPackageName())) {
                                    new ToastDialog(mContext, FirstPageActivity.mContext.getResources().getString(R.string.app_not_install)).show();
                                    return;
                                }
                                if (mEditPresetDialog != null)
                                    if (mEditPresetDialog.isShowing())
                                        mEditPresetDialog.dismiss();
                                mEditPresetDialog = null;
                                mEditPresetDialog = new editPresetDialog(mContext, new OfficialGamePreset(mSunyesMaxGamePresetList.get(paramAnonymousInt), 1));
                                mEditPresetDialog.show();
                                break;
                            case 1:
                                if (!CommonUtils.isAvilible(mContext, mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppPackageName())) {
                                    new ToastDialog(mContext, FirstPageActivity.mContext.getResources().getString(R.string.app_not_install)).show();
                                    return;
                                }
                                CommonUtils.startInstallApp(mContext, mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppPackageName());
                                if (bluetoothdevmanager.devicemode == 0)
                                    paramAnonymous2Int = i;
                                else
                                    paramAnonymous2Int = 1;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Preset Path = ");
                                stringBuilder.append(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath());
                                MyLog.i("my_tag", stringBuilder.toString());
                                if (CommonUtils.isStringValid(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath())) {
                                    if ((bluetoothdevmanager.servicehandle != null) && (!isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1)) {
                                        //todo 未知逻辑 待改。。。
                                        //FirstPageActivity.access$1402(FirstPageActivity.this, true);

                                        saveLocaltionPresetFileToDevice(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath(), mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName());
                                    }
                                } else {
                                    Message message = new Message();
                                    message.what = 20037;
                                    message.obj = mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName();
                                    bluetoothdevmanager.servicehandle.sendMessageDelayed(message, 800L);
                                }
                                break;
                            case 0:
                                if (bluetoothdevmanager.devicemode == 0)
                                    paramAnonymous2Int = j;
                                else
                                    paramAnonymous2Int = 1;
                                StringBuilder stringBuilder1 = new StringBuilder();
                                stringBuilder1.append("Preset Path = ");
                                stringBuilder1.append(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath());
                                MyLog.i("my_tag", paramAnonymous2AdapterView.toString());
                                if (CommonUtils.isStringValid(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath())) {
                                    if ((bluetoothdevmanager.servicehandle != null) && (!isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1))
                                        saveLocaltionPresetFileToDevice(mDBManager.queryByGame(mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath(), mSunyesMaxGamePresetList.get(paramAnonymousInt).getAppName());
                                } else
                                    new ToastDialog(mContext, mContext.getResources().getString(R.string.location_list9)).show();
                                break;
                        }
                        popMenuSGP.dismiss();
                    }
                });
                popMenuSGP.showAsDropDown(paramAnonymousView, 0, 0);
            }
        });
    }

    private void initViewOfficialGame() {
        this.mRefreshLayout = ((DaisyRefreshLayout) findViewById(R.id.refresh_layout));
        this.mRefreshLayout.setEnableLoadMore(false);
        this.mRefreshLayout.setOnRefreshListener(new BaseRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                bluetoothdevmanager.mOfficialGamePresetList.clear();
                //todo 未知逻辑猜的
                //FirstPageActivity.access$2402(FirstPageActivity.this, true);
                //FirstPageActivity.access$2502(FirstPageActivity.this, 0);
                mRefreshLayout.setLoadMore(true);
                mRefreshLayout.setVisibility(View.VISIBLE);
                OkHttpUtil.get(mUiHandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201870, 201871);
                new Handler().postDelayed(new Runnable() {
                                              public void run() {
                                                  mRefreshLayout.setRefreshing(false);
                                                  mUiHandler.sendEmptyMessage(201904);
                                              }
                                          }
                        , 3500L);
            }
        });
        this.gv_official_game = ((GridView) findViewById(R.id.gv_official_game));
        this.iv_game_list_ask = ((ImageView) findViewById(R.id.iv_game_list_ask));
        this.ll_game_list = ((LinearLayout) findViewById(R.id.ll_game_list));
        this.ll_game_list.setVisibility(View.GONE);
        this.gv_official_game.setVisibility(View.GONE);
        this.mOfficialGameItemsAdapter = new OfficialGameItemsAdapter(mContext, bluetoothdevmanager.mOfficialGamePresetList);
        this.gv_official_game.setAdapter(this.mOfficialGameItemsAdapter);
        this.iv_game_list_ask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mCustomTipDialog != null) {
                    if (mCustomTipDialog.isShowing())
                        mCustomTipDialog.dismiss();
                    mCustomTipDialog = null;
                }
                if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                    mCustomTipDialog = new CustomTipDialog(mContext, FirstPageActivity.this.getResources().getString(R.string.op46), FirstPageActivity.this.getResources().getString(R.string.location_list6), getResources().getDrawable(R.drawable.gpic_launcher));
                else
                mCustomTipDialog = new CustomTipDialog(mContext, FirstPageActivity.this.getResources().getString(R.string.op46), FirstPageActivity.this.getResources().getString(R.string.location_list7), getResources().getDrawable(R.drawable.gpic_launcher));
                mCustomTipDialog.setClicklistener(new CustomTipDialog.ClickListenerInterface() {
                    public void doConfirm() {
                        mCustomTipDialog.dismiss();
                    }
                });
                mCustomTipDialog.show();
            }
        });
        this.gv_official_game.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong) {
                if (popMenuSGP != null)
                    //FirstPageActivity.access$1102(FirstPageActivity.this, null);
                    popMenuSGP = null;
                if (paramAnonymousInt == bluetoothdevmanager.mOfficialGamePresetList.size() - 1) {
                    startActivity(new Intent(FirstPageActivity.this, InstalledAppListActivity.class));
                    return;
                }
                if (bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getDataOrg() == 0) {
                    if (bluetoothdevmanager.devicemode == 0) {
                        popMenuSGP = new PopMenuSGP(FirstPageActivity.mContext, 0);
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip17), R.mipmap.pointb1));
                        if (!Locale.getDefault().toString().contains("zh_CN"))
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.analyse_crc_text3), R.mipmap.pointb1));
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip18), R.mipmap.pointb1));
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip19), R.mipmap.pointb1));
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip20), R.mipmap.pointb1));
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip21), R.mipmap.pointb1));
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip22), R.mipmap.pointb1));
                        popMenuSGP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long) {
                                //FirstPageActivity.access$2702(FirstPageActivity.this, ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getGameName());
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("------------GameName = ");
                                stringBuilder.append(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getGameName());
                                MyLog.i("my_tag", stringBuilder.toString());
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("----count = ");
                                stringBuilder.append(popMenuSGP.getCount());
                                MyLog.i("my_tag", stringBuilder.toString());
                                switch (paramAnonymous2Int) {
                                    default:
                                        break;
                                    case 6:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "f5";
                                            downloadPresetTo(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 5:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "f4";
                                            downloadPresetTo(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "f5";
                                            downloadPresetTo(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 4:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "f3";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "f4";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 3:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "f2";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "f3";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 2:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "f1";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "f2";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 1:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            StringBuilder stringBuilder1 = new StringBuilder();
                                            stringBuilder1.append("------------android_url = ");
                                            stringBuilder1.append(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url());
                                            MyLog.i("my_tag", stringBuilder1.toString());
                                            if (!CommonUtils.isStringValid(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url()))
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.analyse_crc_text4)).show();
                                        }
                                        break;
                                    case 0:
                                }
                                try {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url()));
                                    intent.setPackage("com.android.vending");
                                    if (intent.resolveActivity(FirstPageActivity.mContext.getPackageManager()) != null) {
                                        mContext.startActivity(intent);
                                    } else {
                                        intent = new Intent("android.intent.action.VIEW");
                                        intent.setData(Uri.parse(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url()));
                                        if (intent.resolveActivity(FirstPageActivity.mContext.getPackageManager()) != null) {
                                            mContext.startActivity(intent);
//                                            break label1638;
//                                            label1421:
                                            MyLog.i("my_tag", "GoogleMarket Intent not found");
//                                            break label1638;
                                            syncCurrentLocation = "f1";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(FirstPageActivity.mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
//                                            break label1638;
                                            if (CommonUtils.isPicUrl(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getThronePicUrl())) {
                                                intent = new Intent(FirstPageActivity.this, PreviewPresetActivity.class);
                                                intent.putExtra("effect_preview_url", ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getThronePicUrl());
                                                startActivity(intent);
                                            } else {
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip29)).show();
                                            }
                                        }
                                    }
                                    //label1638:
                                    popMenuSGP.dismiss();
                                    return;
                                } catch (ActivityNotFoundException e) {

                                }
                            }
                        });
                    } else {
                        popMenuSGP = new PopMenuSGP(FirstPageActivity.mContext, 1);
                        popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip17), R.mipmap.pointb1));
                        if (!Locale.getDefault().toString().contains("zh_CN"))
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.analyse_crc_text3), R.mipmap.pointb1));
                        if (bluetoothdevmanager.mapMaxNum == 1) {
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip23_1), R.mipmap.pointb1));
                        } else if (bluetoothdevmanager.mapMaxNum >= 4) {
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip23), R.mipmap.pointb1));
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip24), R.mipmap.pointb1));
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip25), R.mipmap.pointb1));
                            popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip26), R.mipmap.pointb1));
                        }
                        popMenuSGP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("----name = ");
                                stringBuilder.append(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getGameName());
                                stringBuilder.append(";   gameId = ");
                                stringBuilder.append(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getGameId());
                                MyLog.i("my_tag", stringBuilder.toString());
                                switch (paramAnonymous2Int) {
                                    default:
                                        break;
                                    case 5:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "left";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 4:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "down";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "left";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 3:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            syncCurrentLocation = "right";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            syncCurrentLocation = "down";
                                            downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 2:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            FirstPageActivity.syncCurrentLocation = "up";
                                            FirstPageActivity.this.downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        } else {
                                            FirstPageActivity.syncCurrentLocation = "right";
                                            FirstPageActivity.this.downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
                                        }
                                        break;
                                    case 1:
                                        if (!Locale.getDefault().toString().contains("zh_CN")) {
                                            StringBuilder stringBuilder1 = new StringBuilder();
                                            stringBuilder1.append("------------android_url = ");
                                            stringBuilder1.append(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getAndroid_url());
                                            MyLog.i("my_tag", stringBuilder1.toString());
                                            if (!CommonUtils.isStringValid(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getAndroid_url()))
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.analyse_crc_text4)).show();
                                        }
                                        break;
                                    case 0:
                                }
                                try {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url()));
                                    intent.setPackage("com.android.vending");
                                    if (intent.resolveActivity(FirstPageActivity.mContext.getPackageManager()) != null) {
                                        FirstPageActivity.mContext.startActivity(intent);
                                    } else {
                                        intent = new Intent("android.intent.action.VIEW");
                                        intent.setData(Uri.parse(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAndroid_url()));
                                        if (intent.resolveActivity(FirstPageActivity.mContext.getPackageManager()) != null) {
                                            FirstPageActivity.mContext.startActivity(intent);
//                                            break label1366;
//                                            label1149:
                                            MyLog.i("my_tag", "GoogleMarket Intent not found");
//                                            break label1366;
                                            FirstPageActivity.syncCurrentLocation = "up";
                                            FirstPageActivity.this.downloadPresetTo((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getPresetUrl());
                                            if (downLoadDialog != null) {
                                                if (downLoadDialog.isShowing())
                                                    downLoadDialog.dismiss();
                                                downLoadDialog = null;
                                            }
                                            downLoadDialog = new DownLoadDialog(mContext);
                                            downLoadDialog.setCancelable(false);
                                            downLoadDialog.setCanceledOnTouchOutside(false);
                                            downLoadDialog.show();
//                                            break label1366;
                                            if (CommonUtils.isPicUrl(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getHandlePicUrl())) {
                                                intent = new Intent(FirstPageActivity.this, PreviewPresetActivity.class);
                                                intent.putExtra("effect_preview_url", ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getHandlePicUrl());
                                                startActivity(intent);
                                            } else {
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip29)).show();
                                            }
                                        }
                                    }
//                                    label1366:
                                    popMenuSGP.dismiss();
                                    return;
                                } catch (ActivityNotFoundException e) {
//                                    break label1149;
                                }
                            }
                        });
                    }
                    popMenuSGP.showAsDropDown(paramAnonymousView, 0, 0);
                    return;
                }
                if (bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getDataOrg() == 1) {
                    popMenuSGP = new PopMenuSGP(mContext, 0);
                    FirstPageActivity.this.popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list2), R.mipmap.pointb1));
                    FirstPageActivity.this.popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list3), R.mipmap.pointb1));
                    FirstPageActivity.this.popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list4), R.mipmap.pointb1));
                    FirstPageActivity.this.popMenuSGP.addItem(new PopMenuSGP.Item(FirstPageActivity.mContext.getString(R.string.location_list5), R.mipmap.pointb1));
                    FirstPageActivity.this.popMenuSGP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> paramAnonymous2AdapterView, View paramAnonymous2View, int paramAnonymous2Int, long paramAnonymous2Long) {
                            int j = 0;
                            int i = 0;
                            switch (paramAnonymous2Int) {
                                default:
                                    break;
                                case 3:
                                    FirstPageActivity.this.mDBManager.delete(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getSmgpId());
                                    CommonUtils.delDrawable(FirstPageActivity.mContext, ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName());
                                    bluetoothdevmanager.mOfficialGamePresetList.remove(paramAnonymousInt);
                                    FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                                    break;
                                case 2:
                                    if (!CommonUtils.isAvilible(FirstPageActivity.mContext, ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppPackageName())) {
                                        new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.app_not_install)).show();
                                        return;
                                    }
                                    if (mEditPresetDialog != null)
                                        //FirstPageActivity.access$1502(FirstPageActivity.this, null);
                                        mEditPresetDialog = null;
                                    mEditPresetDialog = new editPresetDialog(mContext, bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt));
                                    mEditPresetDialog.show();
                                    break;
                                case 1:
                                    if (!CommonUtils.isAvilible(mContext, ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppPackageName())) {
                                        new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.app_not_install)).show();
                                        return;
                                    }
                                    CommonUtils.startInstallApp(FirstPageActivity.mContext, ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppPackageName());
                                    if (bluetoothdevmanager.devicemode == 0)
                                        paramAnonymous2Int = i;
                                    else
                                        paramAnonymous2Int = 1;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Preset Path = ");
                                    stringBuilder.append(FirstPageActivity.this.mDBManager.queryByGame(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName(), paramAnonymous2Int).getPresetPath());
                                    MyLog.i("my_tag", stringBuilder.toString());
                                    if (CommonUtils.isStringValid(mDBManager.queryByGame(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName(), paramAnonymous2Int).getPresetPath())) {
                                        //todo 需要修改
                                        //FirstPageActivity.access$1402(FirstPageActivity.this, true);

                                        if ((bluetoothdevmanager.servicehandle != null) && (!FirstPageActivity.isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1))
                                            saveLocaltionPresetFileToDevice(mDBManager.queryByGame(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName(), paramAnonymous2Int).getPresetPath(), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName());
                                    } else {
                                        Message message = new Message();
                                        message.what = 20037;
                                        message.obj = ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName();
                                        bluetoothdevmanager.servicehandle.sendMessageDelayed(message, 800L);
                                    }
                                    break;
                                case 0:
                                    if (bluetoothdevmanager.devicemode == 0)
                                        paramAnonymous2Int = j;
                                    else
                                        paramAnonymous2Int = 1;
                                    StringBuilder stringBuilder1 = new StringBuilder();
                                    stringBuilder1.append("Preset Path = ");
                                    stringBuilder1.append(mDBManager.queryByGame(bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt).getAppName(), paramAnonymous2Int).getPresetPath());
                                    MyLog.i("my_tag", stringBuilder1.toString());
                                    if (CommonUtils.isStringValid(mDBManager.queryByGame(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName(), paramAnonymous2Int).getPresetPath())) {
                                        if ((bluetoothdevmanager.servicehandle != null) && (!FirstPageActivity.isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1))
                                            saveLocaltionPresetFileToDevice(mDBManager.queryByGame(((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName(), paramAnonymous2Int).getPresetPath(), ((OfficialGamePreset) bluetoothdevmanager.mOfficialGamePresetList.get(paramAnonymousInt)).getAppName());
                                    } else
                                        new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.location_list9)).show();
                                    break;
                            }
                            popMenuSGP.dismiss();
                        }
                    });
                    popMenuSGP.showAsDropDown(paramAnonymousView, 0, 0);
                }
            }
        });
    }

    private void loadMap() {
        if (!isMapInfosSuccess)
            return;
        isMapInfosSuccess = false;
        if (mMyLoadDialog != null) {
            if (mMyLoadDialog.isShowing())
                mMyLoadDialog.dismiss();
            mMyLoadDialog = null;
        }
        mMyLoadDialog = new MyLoadDialog(mContext);
        mMyLoadDialog.setCancelable(false);
        mMyLoadDialog.setCanceledOnTouchOutside(false);
        mMyLoadDialog.show();
        BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
        MyLog.i("my_tag", "sendGetMapCmd0");
        new Thread() {
            public void run() {
                int i = 0;
                while ((i < 6) && (!FirstPageActivity.isMapInfosSuccess)) {
                    if (i > 0)
                        BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                    i += 1;
                    try {
                        sleep(3000L);
                    } catch (InterruptedException localInterruptedException) {
                        localInterruptedException.printStackTrace();
                    }
                }
                if ((i >= 6) && (i != 9))
                    FirstPageActivity.mUiHandler.sendEmptyMessage(17);
            }
        }
                .start();
    }

    private byte[] rearrangeData(byte[] paramArrayOfByte) {
        int m = getIntegerByBit(paramArrayOfByte[3], (byte) 0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------mVer = ");
        stringBuilder.append(m);
        MyLog.i("my_tag", stringBuilder.toString());
        float f;
        int k;
        int j;
        int i;
        if (bluetoothdevmanager.devicemode == 0) {
            if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                f = bluetoothdevmanager.screenwp;
                bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
                bluetoothdevmanager.screenhp = f;
            }
            k = paramArrayOfByte[13];
            mdatasaver = AnalyseSpDataUtil.initMdatasaverByByteArray(paramArrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp);
            if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                i = (int) bluetoothdevmanager.screenwp;
                j = (int) bluetoothdevmanager.screenhp;
            } else {
                i = (int) bluetoothdevmanager.screenhp;
                j = (int) bluetoothdevmanager.screenwp;
            }
            paramArrayOfByte = AnalyseSpDataUtil.changeDataToByteArray(mdatasaver, i, j, bluetoothdevmanager.mapVersion, k);
            byte[] bytes = new byte[262];
            System.arraycopy(paramArrayOfByte, 2, bytes, 0, 262);
            i = CommonUtils.CRC_GetModbus16(bytes, 262);
            stringBuilder = new StringBuilder();
            stringBuilder.append("------save checkCode = ");
            stringBuilder.append(i);
            MyLog.i("crc_tag", stringBuilder.toString());
            bytes = ChangeDataUtil.intToByteArray(i);
            paramArrayOfByte[0] = bytes[1];
            paramArrayOfByte[1] = bytes[0];
            return paramArrayOfByte;
        }
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            f = bluetoothdevmanager.screenwp;
            bluetoothdevmanager.screenwp = bluetoothdevmanager.screenhp;
            bluetoothdevmanager.screenhp = f;
        }
        if ((paramArrayOfByte[4] != 1) && ((paramArrayOfByte[4] != 3) || (m < 7))) {
            AnalyseDataUtil.initMdatasaverByByteArray(paramArrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, m, bluetoothdevmanager.maxKey, 0);
            AnalyseDataUtilS1.initMdatasaverByByteArray(paramArrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, m, bluetoothdevmanager.maxKey, 1);
        } else {
            if ((int) bluetoothdevmanager.screenwp >= (int) bluetoothdevmanager.screenhp) {
                f = bluetoothdevmanager.screenhp;
                bluetoothdevmanager.screenhp = bluetoothdevmanager.screenwp;
                bluetoothdevmanager.screenwp = f;
            }
            AnalyseDataUtil.initMdatasaverByByteArrayPortraitScreen(paramArrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, m, bluetoothdevmanager.maxKey, 0);
            AnalyseDataUtilS1.initMdatasaverByByteArrayPortraitScreen(paramArrayOfByte, bluetoothdevmanager.screenwp, bluetoothdevmanager.screenhp, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint, m, bluetoothdevmanager.maxKey, 1);
        }
        mdatasaverGp = MyApplication.getMdatasaverScenes0();
        ComKey comKey;
        if (m >= 5) {
            comKey = CommonUtils.checkComKey(paramArrayOfByte[9] & 0xFF);
        }else {
            comKey = CommonUtils.checkComKey(paramArrayOfByte[23] & 0xFF);
        }
        if (comKey._id != -1) {
            mdatasaverGp[(comKey._id + 294)].property = 7;
            mdatasaverGp[(comKey._id + 294)].name = comKey.name;
            mdatasaverGp[(comKey._id + 294)].x = ((int) (bluetoothdevmanager.screenwp * 2.0F / 3.0F));
            mdatasaverGp[(comKey._id + 294)].y = ((int) (bluetoothdevmanager.screenhp * 2.0F / 3.0F));
            mdatasaverGp[(comKey._id + 294)].radius = 0;
            mdatasaverGp[(comKey._id + 294)].block = paramArrayOfByte[21];
        }
        if ((m < 5) && (bluetoothdevmanager.mapVersion >= 5) && ((mdatasaverGp[22].x == -1) || (!mdatasaverGp[22].name.equals("JOYSTICK1")) || (mdatasaverGp[23].x == -1) || (!mdatasaverGp[23].name.equals("JOYSTICK2")) || (mdatasaverGp[24].x == -1) || (!mdatasaverGp[24].name.equals("JOYSTICK3")) || (mdatasaverGp[25].x == -1) || (!mdatasaverGp[25].name.equals("JOYSTICK4")) || (mdatasaverGp[26].x == -1) || (!mdatasaverGp[26].name.equals("JOYSTICK5")))) {
            if ((mdatasaverGp[16].x != -1) && (mdatasaverGp[16].name.equals("jl"))) {
                if (mdatasaverGp[16].radius != 0) {
                    mdatasaverGp[22].property = 12;
                    mdatasaverGp[22].name = "JOYSTICK1";
                    mdatasaverGp[22].x = mdatasaverGp[16].x;
                    mdatasaverGp[22].y = mdatasaverGp[16].y;
                    mdatasaverGp[22].radius = mdatasaverGp[16].radius;
                    mdatasaverGp[22].block = 0;
                    mdatasaverGp[22].reverse = 70;
                } else {
                    mdatasaverGp[22].property = 11;
                    mdatasaverGp[22].name = "JOYSTICK1";
                    mdatasaverGp[22].x = mdatasaverGp[16].x;
                    mdatasaverGp[22].y = mdatasaverGp[16].y;
                    mdatasaverGp[22].radius = mdatasaverGp[16].block;
                    mdatasaverGp[22].block = 0;
                    mdatasaverGp[22].reverse = 0;
                }
                mdatasaverGp[16].property = -1;
                mdatasaverGp[16].name = "-1";
                mdatasaverGp[16].x = -1;
                mdatasaverGp[16].y = -1;
                mdatasaverGp[16].radius = 0;
                mdatasaverGp[16].block = 0;
                mdatasaverGp[16].reverse = 0;
            }
            if ((mdatasaverGp[17].x != -1) && (mdatasaverGp[17].name.equals("jr"))) {
                if (mdatasaverGp[17].radius != 0) {
                    mdatasaverGp[23].property = 12;
                    mdatasaverGp[23].name = "JOYSTICK2";
                    mdatasaverGp[23].x = mdatasaverGp[17].x;
                    mdatasaverGp[23].y = mdatasaverGp[17].y;
                    mdatasaverGp[23].radius = mdatasaverGp[17].radius;
                    mdatasaverGp[23].block = 0;
                    mdatasaverGp[23].reverse = 70;
                } else {
                    mdatasaverGp[23].property = 11;
                    mdatasaverGp[23].name = "JOYSTICK2";
                    mdatasaverGp[23].x = mdatasaverGp[17].x;
                    mdatasaverGp[23].y = mdatasaverGp[17].y;
                    mdatasaverGp[23].radius = mdatasaverGp[17].block;
                    mdatasaverGp[23].block = 0;
                    mdatasaverGp[23].reverse = 0;
                }
                mdatasaverGp[17].property = -1;
                mdatasaverGp[17].name = "-1";
                mdatasaverGp[17].x = -1;
                mdatasaverGp[17].y = -1;
                mdatasaverGp[17].radius = 0;
                mdatasaverGp[17].block = 0;
                mdatasaverGp[17].reverse = 0;
            }
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("--mVer = ");
        stringBuilder1.append(m);
        stringBuilder1.append(";   mapVersion = ");
        stringBuilder1.append(bluetoothdevmanager.mapVersion);
        MyLog.i("my_tag", stringBuilder1.toString());
        if ((m >= 5) && (bluetoothdevmanager.mapVersion < 5)) {
            if ((mdatasaverGp[22].x != -1) && (mdatasaverGp[22].name.equals("JOYSTICK1"))) {
                if ((mdatasaverGp[22].radius != 0) && (mdatasaverGp[22].property == 12)) {
                    mdatasaverGp[16].property = 0;
                    mdatasaverGp[16].name = "jl";
                    mdatasaverGp[16].x = mdatasaverGp[22].x;
                    mdatasaverGp[16].y = mdatasaverGp[22].y;
                    mdatasaverGp[16].radius = mdatasaverGp[22].radius;
                    mdatasaverGp[16].block = 0;
                    mdatasaverGp[16].reverse = 70;
                } else {
                    mdatasaverGp[16].property = 0;
                    mdatasaverGp[16].name = "jl";
                    mdatasaverGp[16].x = mdatasaverGp[22].x;
                    mdatasaverGp[16].y = mdatasaverGp[22].y;
                    mdatasaverGp[16].radius = 0;
                    mdatasaverGp[16].block = mdatasaverGp[22].radius;
                    mdatasaverGp[16].reverse = 0;
                }
                mdatasaverGp[22].property = -1;
                mdatasaverGp[22].name = "-1";
                mdatasaverGp[22].x = -1;
                mdatasaverGp[22].y = -1;
                mdatasaverGp[22].radius = 0;
                mdatasaverGp[22].block = 0;
                mdatasaverGp[22].reverse = 0;
            }
            if ((mdatasaverGp[23].x != -1) && (mdatasaverGp[23].name.equals("JOYSTICK2"))) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("-------radius = ");
                stringBuilder.append(mdatasaverGp[17].radius);
                stringBuilder.append(";   block = ");
                stringBuilder.append(mdatasaverGp[23].block);
                MyLog.i("my_tag", stringBuilder.toString());
                if ((mdatasaverGp[23].radius != 0) && (mdatasaverGp[23].property == 12)) {
                    mdatasaverGp[17].property = 0;
                    mdatasaverGp[17].name = "jr";
                    mdatasaverGp[17].x = mdatasaverGp[23].x;
                    mdatasaverGp[17].y = mdatasaverGp[23].y;
                    mdatasaverGp[17].radius = mdatasaverGp[23].radius;
                    mdatasaverGp[17].block = 0;
                    mdatasaverGp[17].reverse = 70;
                } else {
                    mdatasaverGp[17].property = 0;
                    mdatasaverGp[17].name = "jr";
                    mdatasaverGp[17].x = mdatasaverGp[23].x;
                    mdatasaverGp[17].y = mdatasaverGp[23].y;
                    mdatasaverGp[17].radius = 0;
                    mdatasaverGp[17].block = mdatasaverGp[23].radius;
                    mdatasaverGp[17].reverse = 0;
                }
                mdatasaverGp[23].property = -1;
                mdatasaverGp[23].name = "-1";
                mdatasaverGp[23].x = -1;
                mdatasaverGp[23].y = -1;
                mdatasaverGp[23].radius = 0;
                mdatasaverGp[23].block = 0;
                mdatasaverGp[23].reverse = 0;
            }
        }
        MyApplication.setMdatasaverScenes0(mdatasaverGp);
        if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
            j = (int) bluetoothdevmanager.screenwp;
            i = (int)bluetoothdevmanager.screenhp;
        }else {
            j = (int) bluetoothdevmanager.screenhp;
            i = (int) bluetoothdevmanager.screenwp;

        }
//        for (int i = (int) bluetoothdevmanager.screenhp; ; i = (int) bluetoothdevmanager.screenwp) {
//            k = i;
//            break;
//            j = (int) bluetoothdevmanager.screenhp;
//        }
        if (((paramArrayOfByte[4] == 1) || (paramArrayOfByte[4] == 3)) && (m >= 7)) {
            MyLog.i("my_tag", "---竖屏---");
            i = j;
            j = i;
            if (bluetoothdevmanager.isScreenOriatationPortrait(mContext))
                if ((int) bluetoothdevmanager.screenhp >= (int) bluetoothdevmanager.screenwp) {
                    i = (int) bluetoothdevmanager.screenhp;
                    j = (int) bluetoothdevmanager.screenwp;
                } else {
                    i = (int) bluetoothdevmanager.screenwp;
                    j = (int) bluetoothdevmanager.screenhp;
                }
            return AnalyseDataUtil.changeDataToByteArrayPortraitScreen(i, j, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint);
        }
        return AnalyseDataUtil.changeDataToByteArray(j, k, bluetoothdevmanager.mapVersion, bluetoothdevmanager.maxKey, bluetoothdevmanager.maxMacro, bluetoothdevmanager.maxMacroPoint);
    }

    private void saveLocaltionPresetFileToDevice(String paramString1, String paramString2) {
        mUiHandler.sendEmptyMessageDelayed(201903, 3000L);
        issaveLocaltionPresetFileToDevice = true;
        appName = paramString2;
        byte[] bytes = ChangeDataUtil.getBytes(paramString1);
        if (bytes != null) {
            if (((bytes[4] == 1) || (bytes[4] == 3)) && (bluetoothdevmanager.mapVersion < 7)) {
                mUiHandler.removeMessages(201903);
                mUiHandler.sendEmptyMessage(201907);
                return;
            }
            if (bluetoothdevmanager.servicehandle != null) {
                Message message = new Message();
                message.what = 20013;
                message.obj = rearrangeData(bytes);
                bluetoothdevmanager.servicehandle.sendMessage(message);
                MyApplication.setSaveData(rearrangeData(bytes));
            }
        } else {
            mUiHandler.sendEmptyMessage(201903);
        }
    }

    private void sendOfficialPresetToDevice(String paramString) {
        isSaveOfficialGamePresetNow = true;
        locationPath = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".txt");
        locationPath = stringBuilder.toString();
        if (new File(locationPath).exists()) {
            byte[] bytes = ChangeDataUtil.getBytes(locationPath);
            if (bytes != null) {
                if (((bytes[4] == 1) || (bytes[4] == 3)) && (bluetoothdevmanager.mapVersion < 7)) {
                    mUiHandler.removeMessages(201880);
                    mUiHandler.sendEmptyMessage(201907);
                } else if (bluetoothdevmanager.servicehandle != null) {
                    Message message = new Message();
                    message.what = 20013;
                    message.obj = rearrangeData(bytes);
                    bluetoothdevmanager.servicehandle.sendMessage(message);
                    MyApplication.setSaveData(rearrangeData(bytes));
                }
                new File(locationPath).delete();
                return;
            }
            mUiHandler.sendEmptyMessage(201880);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".ini");
        locationPath = stringBuilder.toString();
        IniFile localIniFile = new IniFile(new File(locationPath));
        int i;
        String localObject2;
        String str2;
        String str1;
        String str3;
        String str4;
        String str9;
        if (bluetoothdevmanager.devicemode == 0) {
            i = 0;
            while (i < length) {
                paramString = (String) localIniFile.get("setup", Integer.toString(i));
                if (paramString != null) {
                    String[] arrayOfString = paramString.split(" ");
                    localObject2 = arrayOfString[0];
                    String str5 = arrayOfString[1];
                    String str6 = arrayOfString[2];
                    String str7 = arrayOfString[3];
                    String str8 = arrayOfString[4];
                    paramString = "-1";
                    str9 = "-1";
                    str2 = "-1";
                    str1 = str2;
                    if (arrayOfString.length > 5) {
                        str3 = arrayOfString[5];
                        str4 = arrayOfString[6];
                        paramString = str3;
                        str9 = str4;
                        str1 = str2;
                        if (arrayOfString.length == 8) {
                            str1 = arrayOfString[7];
                            str9 = str4;
                            paramString = str3;
                        }
                    }
                    if ((Integer.parseInt((String) localObject2) != 5) && (Integer.parseInt((String) localObject2) != 6))
                        mdatasaver[i].property = Integer.parseInt((String) localObject2);
                    else if (bluetoothdevmanager.mapVersion == 2)
                        mdatasaver[i].property = Integer.parseInt((String) localObject2);
                    else if (bluetoothdevmanager.mapVersion == 1)
                        mdatasaver[i].property = 0;
                    mdatasaver[i].name = str5;
                    mdatasaver[i].x = Integer.parseInt(str6);
                    mdatasaver[i].y = Integer.parseInt(str7);
                    mdatasaver[i].joystick = str8;
                    mdatasaver[i].rumble = paramString;
                    mdatasaver[i].whichmoto = ((String) str9);
                    mdatasaver[i].ms = str1;
                } else {
                    mdatasaver[i].name = "-1";
                    mdatasaver[i].x = -1;
                    mdatasaver[i].y = -1;
                    mdatasaver[i].joystick = "-1";
                    mdatasaver[i].rumble = "-1";
                    mdatasaver[i].whichmoto = "-1";
                    mdatasaver[i].ms = "-1";
                }
                i += 1;
            }
            if ((this.currentDownLoadGameName.equalsIgnoreCase("和平精英")) && (bluetoothdevmanager.mapVersion == 2) && (mdatasaver[106].name.equalsIgnoreCase("mouseright")) && (mdatasaver[106].x > 0) && (mdatasaver[106].y > 0)) {
                MyLog.i("my_tag", "---Game Name = 和平精英,mapVersion = 2,Key Name = mouseright");
                mdatasaver[320].property = 5;
                mdatasaver[320].name = mdatasaver[106].name;
                mdatasaver[320].x = mdatasaver[106].x;
                mdatasaver[320].y = mdatasaver[106].y;
                mdatasaver[320].joystick = mdatasaver[106].joystick;
                mdatasaver[320].rumble = mdatasaver[106].rumble;
                mdatasaver[320].whichmoto = mdatasaver[106].whichmoto;
                mdatasaver[320].ms = mdatasaver[106].ms;
                mdatasaver[106].property = -1;
                mdatasaver[106].name = "-1";
                mdatasaver[106].x = -1;
                mdatasaver[106].y = -1;
                mdatasaver[106].joystick = "-1";
                mdatasaver[106].rumble = "-1";
                mdatasaver[106].whichmoto = "-1";
                mdatasaver[106].ms = "-1";
            }
            changeData();
        } else {
            i = 0;
            while (i < lengthGp) {
                paramString = (String) localIniFile.get("setup", Integer.toString(i));
                if (paramString != null) {
                    String[] strings = paramString.split(" ");
                    paramString = strings[0];
                    str9 = strings[1];
                    str1 = strings[2];
                    str2 = strings[3];
                    str3 = strings[4];
                    str4 = strings[5];
                    localObject2 = strings[6];
                    mdatasaverGp[i].property = Integer.parseInt(paramString);
                    mdatasaverGp[i].name = str9;
                    mdatasaverGp[i].x = Integer.parseInt(str1);
                    mdatasaverGp[i].y = Integer.parseInt(str2);
                    mdatasaverGp[i].radius = Integer.parseInt(str3);
                    mdatasaverGp[i].block = Integer.parseInt(str4);
                    mdatasaverGp[i].reverse = Integer.parseInt(localObject2);
                    if ((!mdatasaverGp[i].name.equals("-1")) && (mdatasaverGp[i].x != -1) && (i < 38) && (CommonUtils.getGPKeyCodeByName(mdatasaverGp[i].name) != -1) && (i != 16) && (i != 17)) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("---- name = ");
                        stringBuilder.append(mdatasaverGp[i].name);
                        stringBuilder.append("    KeyCode = ");
                        stringBuilder.append(CommonUtils.getGPKeyCodeByName(mdatasaverGp[i].name));
                        stringBuilder.append("   y = ");
                        stringBuilder.append(mdatasaverGp[i].y);
                        stringBuilder.append("   x = ");
                        stringBuilder.append(mdatasaverGp[i].x);
                        MyLog.i("my_tag", stringBuilder.toString());
                    }
                } else {
                    mdatasaverGp[i].property = 1;
                    mdatasaverGp[i].name = "-1";
                    mdatasaverGp[i].x = -1;
                    mdatasaverGp[i].y = -1;
                    mdatasaverGp[i].radius = -1;
                    mdatasaverGp[i].block = -1;
                    mdatasaverGp[i].reverse = -1;
                }
                i += 1;
            }
            if ((bluetoothdevmanager.mapVersion >= 5) && ((mdatasaverGp[22].x == -1) || (!mdatasaverGp[22].name.equals("JOYSTICK1")) || (mdatasaverGp[23].x == -1) || (!mdatasaverGp[23].name.equals("JOYSTICK2")) || (mdatasaverGp[24].x == -1) || (!mdatasaverGp[24].name.equals("JOYSTICK3")) || (mdatasaverGp[25].x == -1) || (!mdatasaverGp[25].name.equals("JOYSTICK4")) || (mdatasaverGp[26].x == -1) || (!mdatasaverGp[26].name.equals("JOYSTICK5")))) {
                if ((mdatasaverGp[16].x != -1) && (mdatasaverGp[16].name.equals("jl"))) {
                    if (mdatasaverGp[16].radius != 0) {
                        mdatasaverGp[22].property = 12;
                        mdatasaverGp[22].name = "JOYSTICK1";
                        mdatasaverGp[22].x = mdatasaverGp[16].x;
                        mdatasaverGp[22].y = mdatasaverGp[16].y;
                        mdatasaverGp[22].radius = mdatasaverGp[16].radius;
                        mdatasaverGp[22].block = 0;
                        mdatasaverGp[22].reverse = 70;
                    } else {
                        mdatasaverGp[22].property = 11;
                        mdatasaverGp[22].name = "JOYSTICK1";
                        mdatasaverGp[22].x = mdatasaverGp[16].x;
                        mdatasaverGp[22].y = mdatasaverGp[16].y;
                        mdatasaverGp[22].radius = mdatasaverGp[16].block;
                        mdatasaverGp[22].block = 0;
                        mdatasaverGp[22].reverse = 0;
                    }
                    mdatasaverGp[16].property = -1;
                    mdatasaverGp[16].name = "-1";
                    mdatasaverGp[16].x = -1;
                    mdatasaverGp[16].y = -1;
                    mdatasaverGp[16].radius = 0;
                    mdatasaverGp[16].block = 0;
                    mdatasaverGp[16].reverse = 0;
                }
                if ((mdatasaverGp[17].x != -1) && (mdatasaverGp[17].name.equals("jr"))) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("-------radius = ");
                    stringBuilder.append(mdatasaverGp[17].radius);
                    stringBuilder.append(";   block = ");
                    stringBuilder.append(mdatasaverGp[23].block);
                    MyLog.i("my_tag", stringBuilder.toString());
                    if (mdatasaverGp[17].radius != 0) {
                        mdatasaverGp[23].property = 12;
                        mdatasaverGp[23].name = "JOYSTICK2";
                        mdatasaverGp[23].x = mdatasaverGp[17].x;
                        mdatasaverGp[23].y = mdatasaverGp[17].y;
                        mdatasaverGp[23].radius = mdatasaverGp[17].radius;
                        mdatasaverGp[23].block = 0;
                        mdatasaverGp[23].reverse = 70;
                    } else {
                        mdatasaverGp[23].property = 11;
                        mdatasaverGp[23].name = "JOYSTICK2";
                        mdatasaverGp[23].x = mdatasaverGp[17].x;
                        mdatasaverGp[23].y = mdatasaverGp[17].y;
                        mdatasaverGp[23].radius = mdatasaverGp[17].block;
                        mdatasaverGp[23].block = 0;
                        mdatasaverGp[23].reverse = 0;
                    }
                    mdatasaverGp[17].property = -1;
                    mdatasaverGp[17].name = "-1";
                    mdatasaverGp[17].x = -1;
                    mdatasaverGp[17].y = -1;
                    mdatasaverGp[17].radius = 0;
                    mdatasaverGp[17].block = 0;
                    mdatasaverGp[17].reverse = 0;
                }
            }
            changeDataGpV5();
        }
        new File(locationPath).delete();
    }

    private void sendSyncDataToDevice(String paramString) {
        isSyncPresetNow = true;
        locationPath = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".txt");
        locationPath = stringBuilder.toString();
        if (new File(locationPath).exists()) {
            byte[] bytes = ChangeDataUtil.getBytes(locationPath);
            if (bytes != null) {
                if (bluetoothdevmanager.servicehandle != null) {
                    Message message = new Message();
                    message.what = 20013;
                    message.obj = rearrangeData(bytes);
                    bluetoothdevmanager.servicehandle.sendMessage((Message) message);
                    MyApplication.setSaveData(rearrangeData(bytes));
                }
                new File(locationPath).delete();
                return;
            }
            mUiHandler.sendEmptyMessage(201849);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".ini");
        locationPath = stringBuilder.toString();
        IniFile localIniFile = new IniFile(new File(locationPath));
        int j = 0;
        int i = 0;
        String str;
        while (i < length) {
            paramString = (String) localIniFile.get("setup", Integer.toString(i));
            if (paramString != null) {
                String[] arrayOfString = paramString.split(" ");
                String str5 = arrayOfString[j];
                String str6 = arrayOfString[1];
                String str7 = arrayOfString[2];
                String str8 = arrayOfString[3];
                String str9 = arrayOfString[4];
                paramString = "-1";
                str = "-1";
                String str2 = "-1";
                String str1 = str2;
                if (arrayOfString.length > 5) {
                    String str3 = arrayOfString[5];
                    String str4 = arrayOfString[6];
                    paramString = str3;
                    str = str4;
                    str1 = str2;
                    if (arrayOfString.length == 8) {
                        str1 = arrayOfString[7];
                        str = str4;
                        paramString = str3;
                    }
                }
                if ((Integer.parseInt(str5) != 5) && (Integer.parseInt(str5) != 6))
                    mdatasaver[i].property = Integer.parseInt(str5);
                //todo 未知
                while (true) {
                    //break;
                    if (bluetoothdevmanager.mapVersion == 2) {
                        mdatasaver[i].property = Integer.parseInt(str5);
                    }else if (bluetoothdevmanager.mapVersion == 1) {
                        mdatasaver[i].property = 0;
                    }
                    break;
                }
                j = 0;
                mdatasaver[i].name = str6;
                mdatasaver[i].x = Integer.parseInt(str7);
                mdatasaver[i].y = Integer.parseInt(str8);
                mdatasaver[i].joystick = str9;
                mdatasaver[i].rumble = paramString;
                mdatasaver[i].whichmoto = ((String) str);
                mdatasaver[i].ms = str1;
            } else {
                mdatasaver[i].property = 1;
                mdatasaver[i].name = "-1";
                mdatasaver[i].x = -1;
                mdatasaver[i].y = -1;
                mdatasaver[i].joystick = "-1";
                mdatasaver[i].rumble = "-1";
                mdatasaver[i].whichmoto = "-1";
                mdatasaver[i].ms = "-1";
            }
            i += 1;
        }
        changeData();
        new File(locationPath).delete();
    }

    private void sendSyncDataToDeviceGp(String paramString) {
        isSyncPresetNow = true;
        locationPath = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".txt");
        locationPath = stringBuilder.toString();
        if (new File(locationPath).exists()) {
            byte[] bytes = ChangeDataUtil.getBytes(locationPath);
            if (bytes != null) {
                if (((bytes[4] == 1) || (bytes[4] == 3)) && (bluetoothdevmanager.mapVersion < 7)) {
                    mUiHandler.removeMessages(201867);
                    mUiHandler.sendEmptyMessage(201907);
                } else if (bluetoothdevmanager.servicehandle != null) {
                    Message message = new Message();
                    message.what = 20013;
                    message.obj = rearrangeData(bytes);
                    bluetoothdevmanager.servicehandle.sendMessage(message);
                    MyApplication.setSaveData(rearrangeData(bytes));
                }
                new File(locationPath).delete();
                return;
            }
            mUiHandler.sendEmptyMessage(201867);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getApplicationContext().getFilesDir().getPath());
        stringBuilder.append("/");
        stringBuilder.append(projectName);
        stringBuilder.append(paramString);
        stringBuilder.append(".ini");
        locationPath = stringBuilder.toString();
        if (new File(locationPath).exists()) {
            IniFile iniFile = new IniFile(new File(locationPath));
            int i = 0;
            while (i < lengthGp) {
                String setup = (String) iniFile.get("setup", Integer.toString(i));
                if (setup != null) {
                    String[] strings = setup.split(" ");
                    String strl = strings[0];
                    String localObject2 = strings[1];
                    String str1 = strings[2];
                    String str2 = strings[3];
                    String str3 = strings[4];
                    String str4 = strings[5];
                    String str6 = strings[6];
                    mdatasaverGp[i].property = Integer.parseInt((String) strl);
                    mdatasaverGp[i].name = localObject2;
                    mdatasaverGp[i].x = Integer.parseInt(str1);
                    mdatasaverGp[i].y = Integer.parseInt(str2);
                    mdatasaverGp[i].radius = Integer.parseInt(str3);
                    mdatasaverGp[i].block = Integer.parseInt(str4);
                    mdatasaverGp[i].reverse = Integer.parseInt((String) str6);
                } else {
                    mdatasaverGp[i].property = 1;
                    mdatasaverGp[i].name = "-1";
                    mdatasaverGp[i].x = -1;
                    mdatasaverGp[i].y = -1;
                    mdatasaverGp[i].radius = -1;
                    mdatasaverGp[i].block = -1;
                    mdatasaverGp[i].reverse = -1;
                }
                i += 1;
            }
            if ((bluetoothdevmanager.mapVersion >= 5) && ((mdatasaverGp[22].x == -1) || (!mdatasaverGp[22].name.equals("JOYSTICK1")) || (mdatasaverGp[23].x == -1) || (!mdatasaverGp[23].name.equals("JOYSTICK2")) || (mdatasaverGp[24].x == -1) || (!mdatasaverGp[24].name.equals("JOYSTICK3")) || (mdatasaverGp[25].x == -1) || (!mdatasaverGp[25].name.equals("JOYSTICK4")) || (mdatasaverGp[26].x == -1) || (!mdatasaverGp[26].name.equals("JOYSTICK5")))) {
                if ((mdatasaverGp[16].x != -1) && (mdatasaverGp[16].name.equals("jl"))) {
                    if (mdatasaverGp[16].radius != 0) {
                        mdatasaverGp[22].property = 12;
                        mdatasaverGp[22].name = "JOYSTICK1";
                        mdatasaverGp[22].x = mdatasaverGp[16].x;
                        mdatasaverGp[22].y = mdatasaverGp[16].y;
                        mdatasaverGp[22].radius = mdatasaverGp[16].radius;
                        mdatasaverGp[22].block = 0;
                        mdatasaverGp[22].reverse = 70;
                    } else {
                        mdatasaverGp[22].property = 11;
                        mdatasaverGp[22].name = "JOYSTICK1";
                        mdatasaverGp[22].x = mdatasaverGp[16].x;
                        mdatasaverGp[22].y = mdatasaverGp[16].y;
                        mdatasaverGp[22].radius = mdatasaverGp[16].block;
                        mdatasaverGp[22].block = 0;
                        mdatasaverGp[22].reverse = 0;
                    }
                    mdatasaverGp[16].property = -1;
                    mdatasaverGp[16].name = "-1";
                    mdatasaverGp[16].x = -1;
                    mdatasaverGp[16].y = -1;
                    mdatasaverGp[16].radius = 0;
                    mdatasaverGp[16].block = 0;
                    mdatasaverGp[16].reverse = 0;
                }
                if ((mdatasaverGp[17].x != -1) && (mdatasaverGp[17].name.equals("jr"))) {
                    if (mdatasaverGp[17].radius != 0) {
                        mdatasaverGp[23].property = 12;
                        mdatasaverGp[23].name = "JOYSTICK2";
                        mdatasaverGp[23].x = mdatasaverGp[17].x;
                        mdatasaverGp[23].y = mdatasaverGp[17].y;
                        mdatasaverGp[23].radius = mdatasaverGp[17].radius;
                        mdatasaverGp[23].block = 0;
                        mdatasaverGp[23].reverse = 70;
                    } else {
                        mdatasaverGp[23].property = 11;
                        mdatasaverGp[23].name = "JOYSTICK2";
                        mdatasaverGp[23].x = mdatasaverGp[17].x;
                        mdatasaverGp[23].y = mdatasaverGp[17].y;
                        mdatasaverGp[23].radius = mdatasaverGp[17].block;
                        mdatasaverGp[23].block = 0;
                        mdatasaverGp[23].reverse = 0;
                    }
                    mdatasaverGp[17].property = -1;
                    mdatasaverGp[17].name = "-1";
                    mdatasaverGp[17].x = -1;
                    mdatasaverGp[17].y = -1;
                    mdatasaverGp[17].radius = 0;
                    mdatasaverGp[17].block = 0;
                    mdatasaverGp[17].reverse = 0;
                }
            }
            changeDataGpV5();
            new File(locationPath).delete();
            return;
        }
        mUiHandler.sendEmptyMessage(201867);
    }

    public static void setIsTestKeyMode(boolean paramBoolean) {
        isTestKeyMode = paramBoolean;
    }

    public static void setIsUpdateFW(boolean paramBoolean) {
        isUpdateFW = paramBoolean;
        if ((!isUpdateFW) && (!isUpdateAPP)) {
            iv_menu.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.menu_2));
            return;
        }
        if (((!isUpdateFW) && (isUpdateAPP)) || ((isUpdateFW) && (!isUpdateAPP))) {
            iv_menu.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.menu_1));
            return;
        }
        iv_menu.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.menu));
    }

    private void showFactoryContent(FactoryInfo paramFactoryInfo) {
        this.current_device_icon.setImageURI(Uri.parse(this.mFactoryInfo.getSmallPic()));
        this.tv_factory_name.setText(this.mFactoryInfo.getName());
        this.tv_factory_contact.setText(this.mFactoryInfo.getPhone());
        this.tv_factory_name1.setText(this.mFactoryInfo.getContent());
        this.tv_device_content.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getDetail())) {
                    Intent intent = new Intent(FirstPageActivity.this, DeviceContentActivity.class);
                    intent.putExtra("device_content", FirstPageActivity.this.mFactoryInfo.getDetail());
                    startActivity(intent);
                    return;
                }
                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip30)).show();
            }
        });
        this.current_device_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                    return;
                if (mCustomBigImageDialog != null) {
                    if (mCustomBigImageDialog.isShowing())
                        mCustomBigImageDialog.dismiss();
                    mCustomBigImageDialog = null;
                }
                imageUrlList.clear();
                if ((CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getPic())) && (CommonUtils.isPicUrl(FirstPageActivity.this.mFactoryInfo.getPic())))
                    FirstPageActivity.this.imageUrlList.add(FirstPageActivity.this.mFactoryInfo.getPic());
                if ((CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getPic_second())) && (CommonUtils.isPicUrl(FirstPageActivity.this.mFactoryInfo.getPic_second())))
                    FirstPageActivity.this.imageUrlList.add(FirstPageActivity.this.mFactoryInfo.getPic_second());
                if ((CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getPic_third())) && (CommonUtils.isPicUrl(FirstPageActivity.this.mFactoryInfo.getPic_third())))
                    FirstPageActivity.this.imageUrlList.add(FirstPageActivity.this.mFactoryInfo.getPic_third());
                if ((CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getPic_fourth())) && (CommonUtils.isPicUrl(FirstPageActivity.this.mFactoryInfo.getPic_fourth())))
                    FirstPageActivity.this.imageUrlList.add(FirstPageActivity.this.mFactoryInfo.getPic_fourth());
                if ((CommonUtils.isStringValid(FirstPageActivity.this.mFactoryInfo.getPic_fifth())) && (CommonUtils.isPicUrl(FirstPageActivity.this.mFactoryInfo.getPic_fifth())))
                    FirstPageActivity.this.imageUrlList.add(FirstPageActivity.this.mFactoryInfo.getPic_fifth());
                if (FirstPageActivity.this.imageUrlList.size() == 0) {
                    new ToastDialog(FirstPageActivity.mContext, "大图不存在！").show();
                    return;
                }
                int i = 0;
                while (i < imageUrlList.size())
                    i += 1;
                pagerDialog = new PagerDialog(mContext, imageUrlList);
                pagerDialog.show();
            }
        });
    }

    private void showIOSModeDialog() {
        if (mCustomTipDialog3 == null) {
            mCustomTipDialog3 = new CustomTipDialog3(mContext);
            mCustomTipDialog3.setCanceledOnTouchOutside(false);
            mCustomTipDialog3.setClicklistener(new CustomTipDialog3.ClickListenerInterface() {
                public void doConfirm() {
                    bluetoothdevmanager.disconnect();
                    FirstPageActivity.mCustomTipDialog3.dismiss();
                    FirstPageActivity.this.finish();
                }
            });
            mCustomTipDialog3.show();
            return;
        }
        if (!mCustomTipDialog3.isShowing()) {
            mCustomTipDialog3.setCanceledOnTouchOutside(false);
            mCustomTipDialog3.show();
        }
    }

    private void showMyDialogForAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View localView = LayoutInflater.from(mContext).inflate(R.layout.my_dialog, null);
        Button localButton = (Button) localView.findViewById(R.id.dialog_btn_sure);
        LinearLayout localLinearLayout1 = (LinearLayout) localView.findViewById(R.id.ll_title);
        final LinearLayout localLinearLayout2 = (LinearLayout) localView.findViewById(R.id.ll_pwd);
        final EditText localEditText = (EditText) localView.findViewById(R.id.et_pwd_code);
        final TextView localTextView1 = (TextView) localView.findViewById(R.id.dialog_message);
        TextView localTextView2 = (TextView) localView.findViewById(R.id.tv_delete);
        TextView localTextView3 = (TextView) localView.findViewById(R.id.tv0);
        TextView localTextView4 = (TextView) localView.findViewById(R.id.tv1);
        TextView localTextView5 = (TextView) localView.findViewById(R.id.tv2);
        TextView localTextView6 = (TextView) localView.findViewById(R.id.tv3);
        TextView localTextView7 = (TextView) localView.findViewById(R.id.tv4);
        TextView localTextView8 = (TextView) localView.findViewById(R.id.tv5);
        TextView localTextView9 = (TextView) localView.findViewById(R.id.tv6);
        TextView localTextView10 = (TextView) localView.findViewById(R.id.tv7);
        TextView localTextView11 = (TextView) localView.findViewById(R.id.tv8);
        TextView localTextView12 = (TextView) localView.findViewById(R.id.tv9);
        localLinearLayout2.setVisibility(View.GONE);
        localTextView1.setVisibility(View.VISIBLE);
        final AlertDialog dialog = builder.create();
        dialog.show();
        this.isss = 0;
        this.isEnterCode = false;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setContentView(localView);
        localLinearLayout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //todo 这里要改
                //FirstPageActivity.access$7108(FirstPageActivity.this);
                if (FirstPageActivity.this.isss >= 7) {
                    //FirstPageActivity.access$7202(FirstPageActivity.this, true);
                    localLinearLayout2.setVisibility(View.VISIBLE);
                    localTextView1.setVisibility(View.GONE);
                }
            }
        });
        localButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (isEnterCode) {
                    epseCode = localEditText.getText().toString().trim();
                    if (CommonUtils.isStringValid(epseCode)) {
                        OkHttpUtil.get(FirstPageActivity.mUiHandler, "http://shootingplus.com.cn/shootingplus/open/games/getEpstpPwd?model=SWT", 24, 25);
                        dialog.dismiss();
                        return;
                    }
                    new ToastDialog(FirstPageActivity.mContext, "密码不能为空").show();
                    return;
                }
                dialog.dismiss();
            }
        });
        localTextView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //FirstPageActivity.access$5102(FirstPageActivity.this, "");
                localEditText.setText("");
            }
        });
        localTextView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (epseCode.length() < 6) {
                    //paramAnonymousView = FirstPageActivity.this;
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("0");
                    localTextView1.setText(localStringBuilder.toString());
                    //FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                    //paramAnonymousView = localEditText;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append("");
                    localStringBuilder.append(epseCode);
                    localEditText.setText(localStringBuilder.toString());
                }
            }
        });
        localTextView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("1");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("1");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("2");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("2");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("3");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("3");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("4");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("4");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("5");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("5");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("6");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("6");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("7");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("7");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("8");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("8");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
        localTextView12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("9");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                } else if ((CommonUtils.isStringValid(FirstPageActivity.this.epseCode)) && (FirstPageActivity.this.epseCode.length() < 6)) {
                    paramAnonymousView = FirstPageActivity.this;
                    localStringBuilder = new StringBuilder();
                    localStringBuilder.append(FirstPageActivity.this.epseCode);
                    localStringBuilder.append("9");
                    FirstPageActivity.access$5102(paramAnonymousView, localStringBuilder.toString());
                }
                paramAnonymousView = localEditText;
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("");
                localStringBuilder.append(FirstPageActivity.this.epseCode);
                paramAnonymousView.setText(localStringBuilder.toString());
            }
        });
    }

    private void showUi() {
        if (bluetoothdevmanager.devicemode == 0)
            this.iv_didnot_connect_device.setImageDrawable(getResources().getDrawable(R.drawable.spic_launcher3));
        else
            this.iv_didnot_connect_device.setImageDrawable(getResources().getDrawable(R.drawable.gpic_launcher));
        this.ll_cloud.setVisibility(View.VISIBLE);
        textconnstate.setTextColor(-16711936);//-16711936
        Object localObject1 = textconnstate;
        Object localObject2 = new StringBuilder();
        ((StringBuilder) localObject2).append(bluetoothdevmanager.mBluetoothName);
        ((StringBuilder) localObject2).append(" ");
        ((StringBuilder) localObject2).append(getResources().getString(R.string.connected));
        ((TextView) localObject1).setText(((StringBuilder) localObject2).toString());
        if (!this.kv.decodeBool("is_post_phone_info", false)) {
            if (bluetoothdevmanager.screenwp > bluetoothdevmanager.screenhp) {
                localObject1 = new StringBuilder();
                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                ((StringBuilder) localObject1).append("x");
                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                localObject1 = ((StringBuilder) localObject1).toString();
            } else {
                localObject1 = new StringBuilder();
                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                ((StringBuilder) localObject1).append("x");
                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                localObject1 = ((StringBuilder) localObject1).toString();
            }
            localObject2 = new StringBuilder();
            ((StringBuilder) localObject2).append("-------手机唯一码:");
            ((StringBuilder) localObject2).append(DeviceUtils.getUniqueId(mContext));
            ((StringBuilder) localObject2).append(";\n手机厂商:");
            ((StringBuilder) localObject2).append(DeviceUtils.getDeviceBrand());
            ((StringBuilder) localObject2).append(";\n手机型号:");
            ((StringBuilder) localObject2).append(DeviceUtils.getSystemModel());
            ((StringBuilder) localObject2).append(";\n分辨率:");
            ((StringBuilder) localObject2).append((String) localObject1);
            MyLog.i("my_tag", ((StringBuilder) localObject2).toString());
            localObject2 = new HashMap();
            ((HashMap) localObject2).put("platform", "1");
            ((HashMap) localObject2).put("brand", DeviceUtils.getDeviceBrand());
            ((HashMap) localObject2).put("model", DeviceUtils.getSystemModel());
            ((HashMap) localObject2).put("resolution", localObject1);
            ((HashMap) localObject2).put("imei", DeviceUtils.getUniqueId(mContext));
            OkHttpUtil.postDataWithParame(HttpUrlConfig.POST_PHONE_INFO_URL, (HashMap) localObject2);
        }
        this.ll_game_list.setVisibility(View.VISIBLE);
        if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter))) {
            this.gv_official_game.setVisibility(View.GONE);
            this.gv_official_game_sunyes_max.setVisibility(View.VISIBLE);
            if (bluetoothdevmanager.devicemode == 0)
                this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(0);
            else
                this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(1);
            this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(mContext));
            this.mSunyesMaxGameItemsAdapter.refresh(this.mSunyesMaxGamePresetList);
            return;
        }
        this.gv_official_game.setVisibility(View.VISIBLE);
        this.gv_official_game_sunyes_max.setVisibility(View.GONE);
        if (bluetoothdevmanager.devicemode == 0)
            this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(0);
        else
            this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(1);
        this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(mContext));
        this.mSunyesMaxGameItemsAdapter.refresh(this.mSunyesMaxGamePresetList);
    }

    private void startmyservice() {
        Object localObject = ((ActivityManager) getSystemService("activity")).getRunningServices(2147483647).iterator();//2147483647
        while (((Iterator) localObject).hasNext())
            if ("com.qx.qgbox.service.bluetoothdevmanager".equals(((ActivityManager.RunningServiceInfo) ((Iterator) localObject).next()).service.getClassName())) {
                i = 1;
                break label62;
            }
        int i = 0;
        label62:
        if (i == 0) {
            localObject = new Intent(this, bluetoothdevmanager.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService((Intent) localObject);
                return;
            }
            startService((Intent) localObject);
        }
    }

    private String strUtil(String paramString) {
        Object localObject = paramString;
        if (paramString.length() != 2) {
            localObject = new StringBuilder();
            ((StringBuilder) localObject).append("0");
            ((StringBuilder) localObject).append(paramString);
            localObject = ((StringBuilder) localObject).toString();
        }
        return localObject;
    }

    private void syncCloudFinish(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
        reSendNumsp = 0;
        if ((!paramBoolean1) && (!paramBoolean2) && (!paramBoolean3) && (!paramBoolean4) && (!paramBoolean5)) {
            new ToastDialog(mContext, mContext.getResources().getString(R.string.new_setup_dialog_tip38)).show();
            if ((mSyncdingDialog != null) && (mSyncdingDialog.isShowing()))
                mSyncdingDialog.dismiss();
        } else {
            if ((paramBoolean1) && (paramBoolean2) && (paramBoolean3) && (paramBoolean4) && (paramBoolean5)) {
                mUiHandler.sendEmptyMessage(201811);
                syncCurrentLocation = "f1";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                return;
            }
            if (paramBoolean1) {
                mUiHandler.sendEmptyMessage(201811);
                syncCurrentLocation = "f1";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                return;
            }
            if (paramBoolean2) {
                mUiHandler.sendEmptyMessage(201812);
                syncCurrentLocation = "f2";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                return;
            }
            if (paramBoolean3) {
                mUiHandler.sendEmptyMessage(201813);
                syncCurrentLocation = "f3";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                return;
            }
            if (paramBoolean4) {
                mUiHandler.sendEmptyMessage(201814);
                syncCurrentLocation = "f4";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                return;
            }
            if (paramBoolean5) {
                mUiHandler.sendEmptyMessage(201815);
                syncCurrentLocation = "f5";
                mUiHandler.sendEmptyMessageDelayed(201849, 200L);
            }
        }
    }

    private void syncCloudFinishGp(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
        reSendNumgp = 0;
        if ((!paramBoolean1) && (!paramBoolean2) && (!paramBoolean3) && (!paramBoolean4)) {
            new ToastDialog(mContext, mContext.getResources().getString(R.string.new_setup_dialog_tip38)).show();
            if ((mSyncdingDialog != null) && (mSyncdingDialog.isShowing()))
                mSyncdingDialog.dismiss();
        } else {
            if ((paramBoolean1) && (paramBoolean2) && (paramBoolean3) && (paramBoolean4)) {
                mUiHandler.sendEmptyMessage(201811);
                syncCurrentLocation = "up";
                mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                return;
            }
            if (paramBoolean1) {
                mUiHandler.sendEmptyMessage(201811);
                syncCurrentLocation = "up";
                mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                return;
            }
            if (paramBoolean2) {
                mUiHandler.sendEmptyMessage(201812);
                syncCurrentLocation = "right";
                mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                return;
            }
            if (paramBoolean3) {
                mUiHandler.sendEmptyMessage(201813);
                syncCurrentLocation = "down";
                mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                return;
            }
            if (paramBoolean4) {
                mUiHandler.sendEmptyMessage(201814);
                syncCurrentLocation = "left";
                mUiHandler.sendEmptyMessageDelayed(201867, 200L);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void addInstalledApp(InstalledApp paramInstalledApp) {
        SunyesMaxGamePreset localSunyesMaxGamePreset = new SunyesMaxGamePreset();
        localSunyesMaxGamePreset.setAppName(paramInstalledApp.getAppName());
        localSunyesMaxGamePreset.setIcon(paramInstalledApp.getIcon());
        localSunyesMaxGamePreset.setAppPackageName(paramInstalledApp.getPackageName());
        localSunyesMaxGamePreset.setAddTime(CommonUtils.getCurrentDateTime());
        localSunyesMaxGamePreset.setIconPath(CommonUtils.saveDrawable(mContext, paramInstalledApp.getIcon(), paramInstalledApp.getAppName()));
        if (bluetoothdevmanager.devicemode == 0)
            localSunyesMaxGamePreset.setDeviceType(0);
        else
            localSunyesMaxGamePreset.setDeviceType(1);
        this.mDBManager.addSunyesMaxGamePreset(localSunyesMaxGamePreset);
        this.mSunyesMaxGamePresetList.clear();
        if (bluetoothdevmanager.devicemode == 0)
            this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(0);
        else
            this.mSunyesMaxGamePresetList = this.mDBManager.queryAll(1);
        this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(mContext));
        this.mSunyesMaxGameItemsAdapter.refresh(this.mSunyesMaxGamePresetList);
    }

    @TargetApi(23)
    public void askForPermission() {
        if (!Settings.canDrawOverlays(this)) {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("package:");
            localStringBuilder.append(getPackageName());
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(localStringBuilder.toString())), 1234);
            return;
        }
        Toast.makeText(this, getString(R.string.indicator5), Toast.LENGTH_SHORT).show();
    }

    void initView() {
        int i = 0;
        while (i < length) {
            mdatasaver[i] = new DataSaverM();
            mdatasaver[i].property = -1;
            mdatasaver[i].name = "-1";
            mdatasaver[i].x = -1;
            mdatasaver[i].y = -1;
            mdatasaver[i].joystick = "-1";
            mdatasaver[i].rumble = "-1";
            mdatasaver[i].whichmoto = "-1";
            mdatasaver[i].ms = "-1";
            i += 1;
        }
        i = 0;
        while (i < lengthGp) {
            mdatasaverGp[i] = new DataSaver();
            mdatasaverGp[i].property = -1;
            mdatasaverGp[i].name = "-1";
            mdatasaverGp[i].x = -1;
            mdatasaverGp[i].y = -1;
            mdatasaverGp[i].radius = 0;
            mdatasaverGp[i].block = 0;
            mdatasaverGp[i].reverse = 0;
            i += 1;
        }
        textconnstate = (TextView) findViewById(R.id.connstate);
        this.ll_connstate = ((LinearLayout) findViewById(R.id.ll_connstate));
        this.iv_cloud_ask = ((ImageView) findViewById(R.id.iv_cloud_ask));
        this.ll_cloud = ((LinearLayout) findViewById(R.id.ll_cloud));
        this.iv_didnot_connect_device = ((ImageView) findViewById(R.id.iv_didnot_connect_device));
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        this.cb_fbtn = ((CheckBox) findViewById(R.id.cb_fbtn));
        this.ll_cloud.setVisibility(View.GONE);
        this.main = ((LinearLayout) findViewById(R.id.main_Layout1));
        this.ll_cloud.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (bluetoothdevmanager.mConnectionState != 0) {
                    if (FirstPageActivity.mpresetCloudSyncDialog == null)
                        FirstPageActivity.access$3102(new FirstPageActivity.presetCloudSyncDialog(FirstPageActivity.this, FirstPageActivity.mContext));
                    FirstPageActivity.mpresetCloudSyncDialog.show();
                    return;
                }
                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.update_fw_tip8)).show();
            }
        });
        this.iv_cloud_ask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (FirstPageActivity.mCustomTipDialog != null) {
                    if (FirstPageActivity.mCustomTipDialog.isShowing())
                        FirstPageActivity.mCustomTipDialog.dismiss();
                    FirstPageActivity.access$1902(null);
                }
                FirstPageActivity.access$1902(new CustomTipDialog(FirstPageActivity.mContext, FirstPageActivity.this.getResources().getString(R.string.op46), FirstPageActivity.this.getResources().getString(R.string.new_setup_dialog_tip21), FirstPageActivity.this.getResources().getDrawable(R.mipmap.ic_launcher)));
                FirstPageActivity.mCustomTipDialog.setClicklistener(new CustomTipDialog.ClickListenerInterface() {
                    public void doConfirm() {
                        FirstPageActivity.mCustomTipDialog.dismiss();
                    }
                });
                FirstPageActivity.mCustomTipDialog.show();
            }
        });
        this.cb_fbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                FirstPageActivity.isShowFBtn = paramAnonymousBoolean;
                if (FirstPageActivity.isShowFBtn)
                    MyWindowManager.createSmallWindow(FirstPageActivity.mContext);
                else if (MyWindowManager.smallWindow != null)
                    MyWindowManager.removeSmallWindow(FirstPageActivity.mContext);
                FirstPageActivity.this.kv.encode("float_btn_setup_key", FirstPageActivity.isShowFBtn);
            }
        });
        this.iv_didnot_connect_device.setImageDrawable(getResources().getDrawable(R.mipmap.didnot_connect_device));
        iv_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (FirstPageActivity.this.popMenu != null)
                    FirstPageActivity.access$102(FirstPageActivity.this, null);
                FirstPageActivity.access$102(FirstPageActivity.this, new PopMenu(FirstPageActivity.mContext));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.my_suspension_window), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.key_test), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.my_about), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.my_hwversion), R.mipmap.pointb));
                if (!FirstPageActivity.isUpdateAPP)
                    FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.down_updata_app), R.mipmap.pointb_red));
                else
                    FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.down_updata_app), R.mipmap.pointb));
                if (!FirstPageActivity.isUpdateFW)
                    FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.update_fw), R.mipmap.pointb_red));
                else
                    FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.update_fw), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.reset_device), R.mipmap.pointb));
                if ((FirstPageActivity.projectName != null) && ((FirstPageActivity.projectName.equals("Q789")) || (FirstPageActivity.projectName.equalsIgnoreCase("789P"))) && (bluetoothdevmanager.mConnectionState != 0))
                    FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.first_page_tip31), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.my_help), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.addItem(new PopMenu.Item(FirstPageActivity.mContext.getString(R.string.my_exit), R.mipmap.pointb));
                FirstPageActivity.this.popMenu.setOnItemClickListener(FirstPageActivity.this.popmenuItemClickListener);
                FirstPageActivity.this.popMenu.showAsDropDown(FirstPageActivity.iv_menu, 110, 0);
            }
        });
        this.ll_connstate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                paramAnonymousView = new Intent("android.settings.BLUETOOTH_SETTINGS");
                FirstPageActivity.this.startActivity(paramAnonymousView);
            }
        });
        mUiHandler = new Handler(getMainLooper()) {
            public void handleMessage(Message paramAnonymousMessage) {
                int i = paramAnonymousMessage.what;
                Object localObject1;
                Object localObject4;
                if (i != 201805)
                    switch (i) {
                        default:
                            switch (i) {
                                default:
                                    switch (i) {
                                        default:
                                            switch (i) {
                                                default:
                                                    switch (i) {
                                                        default:
                                                            switch (i) {
                                                                default:
                                                                    switch (i) {
                                                                        default:
                                                                            break;
                                                                        case 201907:
                                                                            if ((FirstPageActivity.downLoadDialog != null) && (FirstPageActivity.downLoadDialog.isShowing()))
                                                                                FirstPageActivity.downLoadDialog.dismiss();
                                                                            FirstPageActivity.access$2902(null);
                                                                            if ((FirstPageActivity.mSyncdingDialog != null) && (FirstPageActivity.mSyncdingDialog.isShowing()))
                                                                                FirstPageActivity.mSyncdingDialog.dismiss();
                                                                            FirstPageActivity.isSaveOfficialGamePresetNow = false;
                                                                            FirstPageActivity.isSyncPresetNow = false;
                                                                            FirstPageActivity.issaveLocaltionPresetFileToDevice = false;
                                                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.support_ps)).show();
                                                                            break;
                                                                        case 201906:
                                                                            bluetoothdevmanager.disconnect();
                                                                            FirstPageActivity.access$6302(true);
                                                                            if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                                                FirstPageActivity.mMyLoadDialog.dismiss();
                                                                            break;
                                                                        case 201905:
                                                                            FirstPageActivity.mUiHandler.removeMessages(201906);
                                                                            FirstPageActivity.access$6502(FirstPageActivity.this, CommonUtils.getDeviceEncrpyArray((byte[]) paramAnonymousMessage.obj));
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append("RandomByteArray:");
                                                                            ((StringBuilder) localObject1).append(CommonUtils.byteToString(FirstPageActivity.this.RandomByteArray));
                                                                            MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append("EncrpyArray:");
                                                                            ((StringBuilder) localObject1).append(CommonUtils.byteToString(FirstPageActivity.this.EncrpyArray));
                                                                            MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append("MapDataByteArray:");
                                                                            ((StringBuilder) localObject1).append(CommonUtils.byteToString(FirstPageActivity.this.MapDataByteArray));
                                                                            MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append("deviceEncrpyArray:");
                                                                            ((StringBuilder) localObject1).append(CommonUtils.byteToString(FirstPageActivity.this.deviceEncrpyArray));
                                                                            MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                            if (CommonUtils.checkDevice(FirstPageActivity.this.RandomByteArray, FirstPageActivity.this.EncrpyArray, FirstPageActivity.this.MapDataByteArray, FirstPageActivity.this.deviceEncrpyArray)) {
                                                                                FirstPageActivity.access$6302(true);
                                                                                if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                                                    FirstPageActivity.mMyLoadDialog.dismiss();
                                                                                FirstPageActivity.this.showUi();
                                                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip5)).show();
                                                                            } else {
                                                                                FirstPageActivity.access$6302(true);
                                                                                if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                                                    FirstPageActivity.mMyLoadDialog.dismiss();
                                                                                bluetoothdevmanager.disconnect();
                                                                                MyLog.i("my_tag", "763手柄，预设加载成功，密钥校检失败禁用！");
                                                                            }
                                                                            break;
                                                                        case 201904:
                                                                            if (FirstPageActivity.this.isPullDownRefresh) {
                                                                                FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                                                                                FirstPageActivity.access$2402(FirstPageActivity.this, false);
                                                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.pulltorefreshgridview3)).show();
                                                                            }
                                                                            break;
                                                                        case 201903:
                                                                            FirstPageActivity.issaveLocaltionPresetFileToDevice = false;
                                                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip10)).show();
                                                                            break;
                                                                        case 201902:
                                                                            FirstPageActivity.issaveLocaltionPresetFileToDevice = false;
                                                                            FirstPageActivity.mUiHandler.removeMessages(201903);
                                                                            if (FirstPageActivity.this.isOpenGame) {
                                                                                FirstPageActivity.access$1402(FirstPageActivity.this, false);
                                                                                localObject1 = new Message();
                                                                                ((Message) localObject1).what = 20037;
                                                                                ((Message) localObject1).obj = FirstPageActivity.appName;
                                                                                bluetoothdevmanager.servicehandle.sendMessageDelayed((Message) localObject1, 300L);
                                                                            }
                                                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip5)).show();
                                                                            break;
                                                                        case MSG_ON_ANALYSE_CRC_DATA:
                                                                            MyLog.i("my_tag", "------------提示同步");
                                                                            if ((bluetoothdevmanager.mOfficialGamePresetList.size() > 0) && (CommonUtils.isContain(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue())) && (FirstPageActivity.showAutoSyncPresetTip)) {
                                                                                if (FirstPageActivity.mpresetCloudSyncDialog2 == null)
                                                                                    FirstPageActivity.access$3402(new FirstPageActivity.presetCloudSyncDialog2(FirstPageActivity.this, FirstPageActivity.mContext));
                                                                                FirstPageActivity.mpresetCloudSyncDialog2.show();
                                                                                FirstPageActivity.access$3302(false);
                                                                            }
                                                                            break;
                                                                    }
                                                                    break;
                                                                case 201880:
                                                                    FirstPageActivity.access$3808();
                                                                    if (FirstPageActivity.saveNum <= 2) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201878);
                                                                    } else {
                                                                        if ((FirstPageActivity.downLoadDialog != null) && (FirstPageActivity.isSaveOfficialGamePresetNow)) {
                                                                            if (FirstPageActivity.downLoadDialog.isShowing())
                                                                                FirstPageActivity.downLoadDialog.dismiss();
                                                                            FirstPageActivity.access$2902(null);
                                                                            if ((FirstPageActivity.mSyncdingDialog != null) && (FirstPageActivity.mSyncdingDialog.isShowing()))
                                                                                FirstPageActivity.mSyncdingDialog.dismiss();
                                                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip28)).show();
                                                                        }
                                                                        FirstPageActivity.isSyncPresetByGameIdNow = false;
                                                                    }
                                                                    break;
                                                                case 201879:
                                                                    FirstPageActivity.access$3802(0);
                                                                    FirstPageActivity.mUiHandler.removeMessages(201880);
                                                                    if (FirstPageActivity.isSaveOfficialGamePresetNow) {
                                                                        if (bluetoothdevmanager.devicemode == 0) {
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append(FirstPageActivity.this.getResources().getString(R.string.first_page_tip27));
                                                                            ((StringBuilder) localObject1).append("Ctrl + ");
                                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncCurrentLocation);
                                                                            localObject1 = ((StringBuilder) localObject1).toString();
                                                                        } else {
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append(FirstPageActivity.this.getResources().getString(R.string.first_page_tip27));
                                                                            ((StringBuilder) localObject1).append("Home + ");
                                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncCurrentLocation);
                                                                            localObject1 = ((StringBuilder) localObject1).toString();
                                                                        }
                                                                        new ToastDialog(FirstPageActivity.mContext, (String) localObject1).show();
                                                                        localObject1 = new StringBuilder();
                                                                        ((StringBuilder) localObject1).append("bluetoothdevmanager.defaultGameId.size() = ");
                                                                        ((StringBuilder) localObject1).append(bluetoothdevmanager.defaultGameId.size());
                                                                        ((StringBuilder) localObject1).append(";   syncCurrentLocation = ");
                                                                        ((StringBuilder) localObject1).append(FirstPageActivity.syncCurrentLocation);
                                                                        MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                        if ((bluetoothdevmanager.defaultGameId.size() > 1) && (!FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("left")) && (FirstPageActivity.isSyncPresetByGameIdNow)) {
                                                                            if ((bluetoothdevmanager.defaultGameId.size() == 2) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("up"))) {
                                                                                FirstPageActivity.syncCurrentLocation = "right";
                                                                                FirstPageActivity.this.downloadPresetTo(CommonUtils.getGameDownloadOfficialGamePreset(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(1)).intValue()), CommonUtils.getGameDownloadUrl(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(1)).intValue()));
                                                                            }
                                                                            if ((bluetoothdevmanager.defaultGameId.size() == 3) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("right"))) {
                                                                                FirstPageActivity.syncCurrentLocation = "down";
                                                                                FirstPageActivity.this.downloadPresetTo(CommonUtils.getGameDownloadOfficialGamePreset(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(2)).intValue()), CommonUtils.getGameDownloadUrl(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(2)).intValue()));
                                                                            }
                                                                            if ((bluetoothdevmanager.defaultGameId.size() == 4) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("down"))) {
                                                                                FirstPageActivity.syncCurrentLocation = "left";
                                                                                FirstPageActivity.this.downloadPresetTo(CommonUtils.getGameDownloadOfficialGamePreset(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(3)).intValue()), CommonUtils.getGameDownloadUrl(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(3)).intValue()));
                                                                            }
                                                                        } else {
                                                                            MyLog.i("my_tag", "MSG_ON_START_LOAD_MAP");
                                                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201805, 1000L);
                                                                            if (FirstPageActivity.downLoadDialog != null) {
                                                                                if (FirstPageActivity.downLoadDialog.isShowing())
                                                                                    FirstPageActivity.downLoadDialog.dismiss();
                                                                                FirstPageActivity.access$2902(null);
                                                                            }
                                                                            if ((FirstPageActivity.mSyncdingDialog != null) && (FirstPageActivity.mSyncdingDialog.isShowing()))
                                                                                FirstPageActivity.mSyncdingDialog.dismiss();
                                                                        }
                                                                    }
                                                                    FirstPageActivity.isSaveOfficialGamePresetNow = false;
                                                                    break;
                                                                case 201878:
                                                                    FirstPageActivity.this.sendOfficialPresetToDevice(FirstPageActivity.syncCurrentLocation);
                                                                    FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201880, 3000L);
                                                                    break;
                                                                case 201877:
                                                                    FirstPageActivity.isSyncPresetByGameIdNow = false;
                                                                    if (FirstPageActivity.downLoadDialog != null) {
                                                                        if (FirstPageActivity.downLoadDialog.isShowing())
                                                                            FirstPageActivity.downLoadDialog.dismiss();
                                                                        FirstPageActivity.access$2902(null);
                                                                    }
                                                                    if ((FirstPageActivity.mSyncdingDialog != null) && (FirstPageActivity.mSyncdingDialog.isShowing()))
                                                                        FirstPageActivity.mSyncdingDialog.dismiss();
                                                                    new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip28)).show();
                                                                    break;
                                                                case 201876:
                                                                    FirstPageActivity.access$3802(0);
                                                                    if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("up"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201811);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("right"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201812);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("down"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201813);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("left"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201814);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f1"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201811);
                                                                        FirstPageActivity.syncCurrentLocation = "f1";
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f2"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201812);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f3"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201813);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f4"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201814);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f5"))) {
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201815);
                                                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201878, 1000L);
                                                                    }
                                                                    break;
                                                                case 201875:
                                                                    bluetoothdevmanager.mOfficialGamePresetList.clear();
                                                                    FirstPageActivity.this.ll_game_list.setVisibility(8);
                                                                    FirstPageActivity.access$4308(FirstPageActivity.this);
                                                                    if (FirstPageActivity.this.getGamePresetNum < 8) {
                                                                        i = -1;
                                                                        if (bluetoothdevmanager.devicemode == 0)
                                                                            i = 0;
                                                                        else if (bluetoothdevmanager.devicemode == 2)
                                                                            i = 1;
                                                                        localObject4 = "";
                                                                        if ((bluetoothdevmanager.screenhp > 0.0F) && (bluetoothdevmanager.screenwp > 0.0F) && (bluetoothdevmanager.screenhp > bluetoothdevmanager.screenwp)) {
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                                                                            ((StringBuilder) localObject1).append("x");
                                                                            ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                                                                            localObject1 = ((StringBuilder) localObject1).toString();
                                                                        } else {
                                                                            localObject1 = localObject4;
                                                                            if (bluetoothdevmanager.screenhp > 0.0F) {
                                                                                localObject1 = localObject4;
                                                                                if (bluetoothdevmanager.screenwp > 0.0F) {
                                                                                    localObject1 = localObject4;
                                                                                    if (bluetoothdevmanager.screenhp < bluetoothdevmanager.screenwp) {
                                                                                        localObject1 = new StringBuilder();
                                                                                        ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                                                                                        ((StringBuilder) localObject1).append("x");
                                                                                        ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                                                                                        localObject1 = ((StringBuilder) localObject1).toString();
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        if ((i != -1) && (CommonUtils.isStringValid((String) localObject1)))
                                                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialGamePresetList(i, (String) localObject1), 201874, 201875);
                                                                    } else {
                                                                        localObject1 = new StringBuilder();
                                                                        ((StringBuilder) localObject1).append("-----获取官方预设列表失败 = ");
                                                                        ((StringBuilder) localObject1).append(FirstPageActivity.this.getGamePresetNum);
                                                                        MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                                                        bluetoothdevmanager.mOfficialGamePresetList.clear();
                                                                        FirstPageActivity.this.mSunyesMaxGamePresetList.clear();
                                                                        if (bluetoothdevmanager.devicemode == 0)
                                                                            FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(0));
                                                                        else
                                                                            FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(1));
                                                                        FirstPageActivity.this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(FirstPageActivity.mContext));
                                                                        i = 0;
                                                                        while (i < FirstPageActivity.this.mSunyesMaxGamePresetList.size()) {
                                                                            bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset((SunyesMaxGamePreset) FirstPageActivity.this.mSunyesMaxGamePresetList.get(i), 1));
                                                                            i += 1;
                                                                        }
                                                                        if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                                                                            FirstPageActivity.this.mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                                                                        else
                                                                            FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                                                                        if (FirstPageActivity.this.isPullDownRefresh) {
                                                                            FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                                                                            FirstPageActivity.access$2402(FirstPageActivity.this, false);
                                                                        }
                                                                    }
                                                                    break;
                                                                case 201874:
                                                                case 201873:
                                                                case 201872:
                                                                case 201871:
                                                                case 201870:
                                                            }
                                                            break;
                                                        case 201868:
                                                        case 201867:
                                                        case 201866:
                                                        case 201865:
                                                        case 201864:
                                                        case 201863:
                                                        case 201862:
                                                        case 201861:
                                                        case 201860:
                                                        case 201859:
                                                        case 201858:
                                                        case 201857:
                                                        case 201856:
                                                        case 201855:
                                                        case 201854:
                                                        case 201853:
                                                        case 201852:
                                                        case 201851:
                                                        case 201850:
                                                        case 201849:
                                                        case 201848:
                                                        case 201847:
                                                        case 201846:
                                                        case 201845:
                                                        case 201844:
                                                        case 201843:
                                                        case 201842:
                                                        case 201841:
                                                        case 201840:
                                                        case 201839:
                                                        case 201838:
                                                        case 201837:
                                                        case 201836:
                                                        case 201835:
                                                        case 201834:
                                                        case 201833:
                                                        case 201832:
                                                        case 201831:
                                                        case 201830:
                                                        case 201829:
                                                        case MSG_ON_GET_F1_FILE_DOWNLOAD_URL_SUCCESS:
                                                    }
                                                    break;
                                                case 201815:
                                                case 201814:
                                                case 201813:
                                                case 201812:
                                                case 201811:
                                            }
                                            break;
                                        case 25:
                                        case 24:
                                        case 23:
                                        case 22:
                                        case 21:
                                        case 20:
                                        case 19:
                                        case 18:
                                        case 17:
                                        case 16:
                                    }
                                    break;
                                case 8:
                                case 7:
                                case 6:
                                case 5:
                            }
                            break;
                        case 3:
                        case 0:
                        case 1:
                        case 2:
                    }
                try {
                    localObject1 = (JSONObject) paramAnonymousMessage.obj;
                    if (((JSONObject) localObject1).getInt("code") == 1000) {
                        localObject1 = ((JSONObject) localObject1).getJSONArray("data");
                        localObject4 = new StringBuilder();
                        ((StringBuilder) localObject4).append("-----Game Preset length = ");
                        ((StringBuilder) localObject4).append(((JSONArray) localObject1).length());
                        MyLog.i("my_tag", ((StringBuilder) localObject4).toString());
                        if (((JSONArray) localObject1).length() > 0) {
                            bluetoothdevmanager.mOfficialGamePresetList.clear();
                            i = 0;
                            if (i < ((JSONArray) localObject1).length()) {
                                j = 0;
                                if (j >= FirstPageActivity.this.mOfficialGameList.size())
                                    break label10944;
                                if (((JSONArray) localObject1).getJSONObject(i).getInt("gameId") != ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getGameId())
                                    break label10937;
                                if (FirstPageActivity.locale.contains("zh_CN")) {
                                    if (!CommonUtils.isContainChinese(((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getName()))
                                        break label10937;
                                    bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset(((JSONArray) localObject1).getJSONObject(i).getString("eurl"), ((JSONArray) localObject1).getJSONObject(i).getString("url"), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getLogo(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getGameId(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getHandlePic(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getThronePic(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getName(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getAndroid_url(), 0));
                                    break label10937;
                                }
                                if (CommonUtils.isContainChinese(((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getName()))
                                    break label10937;
                                bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset(((JSONArray) localObject1).getJSONObject(i).getString("eurl"), ((JSONArray) localObject1).getJSONObject(i).getString("url"), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getLogo(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getGameId(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getHandlePic(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getThronePic(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getName(), ((OfficialGame) FirstPageActivity.this.mOfficialGameList.get(j)).getAndroid_url(), 0));
                                break label10937;
                            }
                            FirstPageActivity.this.mSunyesMaxGamePresetList.clear();
                            if (bluetoothdevmanager.devicemode == 0)
                                FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(0));
                            else
                                FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(1));
                            FirstPageActivity.this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(FirstPageActivity.mContext));
                            i = 0;
                            while (i < FirstPageActivity.this.mSunyesMaxGamePresetList.size()) {
                                bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset((SunyesMaxGamePreset) FirstPageActivity.this.mSunyesMaxGamePresetList.get(i), 1));
                                i += 1;
                            }
                            if (bluetoothdevmanager.mOfficialGamePresetList.size() > 0) {
                                if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                                    FirstPageActivity.this.mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                                else
                                    FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                                if (FirstPageActivity.this.isPullDownRefresh) {
                                    FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                                    FirstPageActivity.access$2402(FirstPageActivity.this, false);
                                }
                                if ((bluetoothdevmanager.mOfficialGamePresetList.size() > 0) && (bluetoothdevmanager.defaultGameId.size() > 0) && (CommonUtils.isContain(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue())) && (FirstPageActivity.showAutoSyncPresetTip)) {
                                    if (FirstPageActivity.mpresetCloudSyncDialog2 == null)
                                        FirstPageActivity.access$3402(new FirstPageActivity.presetCloudSyncDialog2(FirstPageActivity.this, FirstPageActivity.mContext));
                                    FirstPageActivity.mpresetCloudSyncDialog2.show();
                                    FirstPageActivity.access$3302(false);
                                }
                            } else {
                                FirstPageActivity.mUiHandler.sendEmptyMessage(201875);
                            }
                        } else {
                            FirstPageActivity.mUiHandler.sendEmptyMessage(201875);
                        }
                    } else if (((JSONObject) localObject1).getInt("code") == 1001) {
                        bluetoothdevmanager.mOfficialGamePresetList.clear();
                        FirstPageActivity.this.mSunyesMaxGamePresetList.clear();
                        if (bluetoothdevmanager.devicemode == 0)
                            FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(0));
                        else
                            FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(1));
                        FirstPageActivity.this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(FirstPageActivity.mContext));
                        i = 0;
                        while (i < FirstPageActivity.this.mSunyesMaxGamePresetList.size()) {
                            bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset((SunyesMaxGamePreset) FirstPageActivity.this.mSunyesMaxGamePresetList.get(i), 1));
                            i += 1;
                        }
                        if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                            FirstPageActivity.this.mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                        else
                            FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                        if (FirstPageActivity.this.isPullDownRefresh) {
                            FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                            FirstPageActivity.access$2402(FirstPageActivity.this, false);
                        }
                    } else {
                        FirstPageActivity.mUiHandler.sendEmptyMessage(201875);
                        break label10911;
                        FirstPageActivity.mUiHandler.sendEmptyMessage(201875);
                        break label10911;
                        FirstPageActivity.this.ll_factory_manager.setVisibility(8);
                    }
                } catch (Exception localException5) {
                    while (true) {
                        int j;
                        try {
                            localObject1 = (JSONObject) paramAnonymousMessage.obj;
                            if (((JSONObject) localObject1).getInt("code") == 1000) {
                                localObject1 = ((JSONObject) localObject1).getJSONArray("data");
                                if (((JSONArray) localObject1).length() > 0) {
                                    FirstPageActivity.access$2002(FirstPageActivity.this, new FactoryInfo(((JSONArray) localObject1).getJSONObject(0)));
                                    FirstPageActivity.this.showFactoryContent(FirstPageActivity.this.mFactoryInfo);
                                    FirstPageActivity.this.ll_factory_manager.setVisibility(0);
                                } else {
                                    FirstPageActivity.mUiHandler.sendEmptyMessage(201873);
                                }
                            } else {
                                FirstPageActivity.mUiHandler.sendEmptyMessage(201873);
                                continue;
                                FirstPageActivity.mUiHandler.sendEmptyMessage(201873);
                                continue;
                                FirstPageActivity.this.mOfficialGameList.clear();
                                FirstPageActivity.this.ll_game_list.setVisibility(8);
                                FirstPageActivity.access$2508(FirstPageActivity.this);
                                if (FirstPageActivity.this.getGameListNum < 8) {
                                    OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201870, 201871);
                                } else {
                                    localObject1 = new StringBuilder();
                                    ((StringBuilder) localObject1).append("1002-----获取官方游戏列表失败 = ");
                                    ((StringBuilder) localObject1).append(FirstPageActivity.this.getGamePresetNum);
                                    MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                    bluetoothdevmanager.mOfficialGamePresetList.clear();
                                    FirstPageActivity.this.mSunyesMaxGamePresetList.clear();
                                    if (bluetoothdevmanager.devicemode == 0)
                                        FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(0));
                                    else
                                        FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(1));
                                    FirstPageActivity.this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(FirstPageActivity.mContext));
                                    i = 0;
                                    if (i < FirstPageActivity.this.mSunyesMaxGamePresetList.size()) {
                                        bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset((SunyesMaxGamePreset) FirstPageActivity.this.mSunyesMaxGamePresetList.get(i), 1));
                                        i += 1;
                                        continue;
                                    }
                                    if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                                        FirstPageActivity.this.mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                                    else
                                        FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                                    if (FirstPageActivity.this.isPullDownRefresh) {
                                        FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                                        FirstPageActivity.access$2402(FirstPageActivity.this, false);
                                    }
                                }
                            }
                        } catch (Exception localException5) {
                            try {
                                localObject1 = (JSONObject) paramAnonymousMessage.obj;
                                localObject4 = new StringBuilder();
                                ((StringBuilder) localObject4).append("----code = ");
                                ((StringBuilder) localObject4).append(((JSONObject) localObject1).getInt("code"));
                                MyLog.i("my_tag", ((StringBuilder) localObject4).toString());
                                if (((JSONObject) localObject1).getInt("code") == 1000) {
                                    localObject1 = ((JSONObject) localObject1).getJSONArray("data");
                                    if (((JSONArray) localObject1).length() > 0) {
                                        FirstPageActivity.this.mOfficialGameList.clear();
                                        i = 0;
                                        if (i < ((JSONArray) localObject1).length()) {
                                            FirstPageActivity.this.mOfficialGameList.add(new OfficialGame(((JSONArray) localObject1).getJSONObject(i)));
                                            i += 1;
                                            continue;
                                        }
                                        if (FirstPageActivity.this.mOfficialGameList.size() > 0) {
                                            i = -1;
                                            if (bluetoothdevmanager.devicemode == 0)
                                                i = 0;
                                            else if (bluetoothdevmanager.devicemode == 2)
                                                i = 1;
                                            localObject4 = "";
                                            if ((bluetoothdevmanager.screenhp > 0.0F) && (bluetoothdevmanager.screenwp > 0.0F) && (bluetoothdevmanager.screenhp > bluetoothdevmanager.screenwp)) {
                                                localObject1 = new StringBuilder();
                                                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                                                ((StringBuilder) localObject1).append("x");
                                                ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                                                localObject1 = ((StringBuilder) localObject1).toString();
                                            } else {
                                                localObject1 = localObject4;
                                                if (bluetoothdevmanager.screenhp > 0.0F) {
                                                    localObject1 = localObject4;
                                                    if (bluetoothdevmanager.screenwp > 0.0F) {
                                                        localObject1 = localObject4;
                                                        if (bluetoothdevmanager.screenhp < bluetoothdevmanager.screenwp) {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenwp);
                                                            ((StringBuilder) localObject1).append("x");
                                                            ((StringBuilder) localObject1).append((int) bluetoothdevmanager.screenhp);
                                                            localObject1 = ((StringBuilder) localObject1).toString();
                                                        }
                                                    }
                                                }
                                            }
                                            if ((i != -1) && (CommonUtils.isStringValid((String) localObject1))) {
                                                FirstPageActivity.access$4302(FirstPageActivity.this, 0);
                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialGamePresetList(i, (String) localObject1), 201874, 201875);
                                            }
                                        } else {
                                            FirstPageActivity.mUiHandler.sendEmptyMessage(201871);
                                        }
                                    } else {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201871);
                                    }
                                } else if (((JSONObject) localObject1).getInt("code") == 1001) {
                                    localObject1 = new StringBuilder();
                                    ((StringBuilder) localObject1).append("1001-----获取官方游戏列表失败 = ");
                                    ((StringBuilder) localObject1).append(FirstPageActivity.this.getGamePresetNum);
                                    MyLog.i("my_tag", ((StringBuilder) localObject1).toString());
                                    bluetoothdevmanager.mOfficialGamePresetList.clear();
                                    FirstPageActivity.this.mSunyesMaxGamePresetList.clear();
                                    if (bluetoothdevmanager.devicemode == 0)
                                        FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(0));
                                    else
                                        FirstPageActivity.access$1002(FirstPageActivity.this, FirstPageActivity.this.mDBManager.queryAll(1));
                                    FirstPageActivity.this.mSunyesMaxGamePresetList.add(CommonUtils.getAddAppSunyesMaxGamePreset(FirstPageActivity.mContext));
                                    i = 0;
                                    if (i < FirstPageActivity.this.mSunyesMaxGamePresetList.size()) {
                                        bluetoothdevmanager.mOfficialGamePresetList.add(new OfficialGamePreset((SunyesMaxGamePreset) FirstPageActivity.this.mSunyesMaxGamePresetList.get(i), 1));
                                        i += 1;
                                        continue;
                                    }
                                    if ((CommonUtils.isStringValid(bluetoothdevmanager.mBluetoothName)) && (bluetoothdevmanager.mBluetoothName.equalsIgnoreCase(ProjectFilterConfig.prijectFilter)))
                                        FirstPageActivity.this.mSunyesMaxGameItemsAdapter.refresh(FirstPageActivity.this.mSunyesMaxGamePresetList);
                                    else
                                        FirstPageActivity.this.mOfficialGameItemsAdapter.refresh(bluetoothdevmanager.mOfficialGamePresetList);
                                    if (FirstPageActivity.this.isPullDownRefresh) {
                                        FirstPageActivity.this.mRefreshLayout.setRefreshing(false);
                                        FirstPageActivity.access$2402(FirstPageActivity.this, false);
                                    }
                                } else {
                                    FirstPageActivity.mUiHandler.sendEmptyMessage(201871);
                                    continue;
                                    FirstPageActivity.mUiHandler.sendEmptyMessage(201871);
                                    continue;
                                    localObject1 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    FirstPageActivity.this.startActivityForResult((Intent) localObject1, IMAGE_REQUEST_CODE);
                                    continue;
                                    if (FirstPageActivity.reSendNumgp <= 2) {
                                        localObject1 = new StringBuilder();
                                        ((StringBuilder) localObject1).append("----开始计数 reSendNum = ");
                                        ((StringBuilder) localObject1).append(FirstPageActivity.reSendNumgp);
                                        MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                        FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201867, 3500L);
                                        FirstPageActivity.access$4408();
                                        FirstPageActivity.this.sendSyncDataToDeviceGp(FirstPageActivity.syncCurrentLocation);
                                    } else {
                                        localObject1 = new StringBuilder();
                                        ((StringBuilder) localObject1).append("----发送");
                                        ((StringBuilder) localObject1).append(FirstPageActivity.syncCurrentLocation);
                                        ((StringBuilder) localObject1).append("预设到设备超时失败！");
                                        MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                        if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("up")))
                                            FirstPageActivity.isUp = false;
                                        if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("right")))
                                            FirstPageActivity.isRight = false;
                                        if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("down")))
                                            FirstPageActivity.isDown = false;
                                        if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("left")))
                                            FirstPageActivity.isLeft = false;
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(201848);
                                        continue;
                                        if ((FirstPageActivity.reSendNumgp <= 2) && (FirstPageActivity.reSendNumgp != 0)) {
                                            localObject1 = new StringBuilder();
                                            ((StringBuilder) localObject1).append("----停止计数 reSendNum = ");
                                            ((StringBuilder) localObject1).append(FirstPageActivity.reSendNumgp);
                                            MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                            FirstPageActivity.mUiHandler.removeMessages(201867);
                                        }
                                        FirstPageActivity.access$4402(0);
                                        if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("up")) && (FirstPageActivity.isRight)) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessage(201812);
                                            FirstPageActivity.syncCurrentLocation = "right";
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                                        } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("right")) && (FirstPageActivity.isDown)) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessage(201813);
                                            FirstPageActivity.syncCurrentLocation = "down";
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                                        } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("down")) && (FirstPageActivity.isLeft)) {
                                            FirstPageActivity.mUiHandler.sendEmptyMessage(201814);
                                            FirstPageActivity.syncCurrentLocation = "left";
                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201867, 200L);
                                        } else {
                                            if (FirstPageActivity.isSyncPresetNow) {
                                                if ((FirstPageActivity.isUp) && (FirstPageActivity.isRight) && (FirstPageActivity.isDown) && (FirstPageActivity.isLeft)) {
                                                    FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip39);
                                                } else if ((!FirstPageActivity.isUp) && (!FirstPageActivity.isRight) && (!FirstPageActivity.isDown) && (!FirstPageActivity.isLeft)) {
                                                    FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip38);
                                                } else {
                                                    FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip40);
                                                    if (FirstPageActivity.isUp) {
                                                        localObject1 = new StringBuilder();
                                                        ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                        ((StringBuilder) localObject1).append("home+up");
                                                        FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                    }
                                                    if (FirstPageActivity.isRight)
                                                        if (FirstPageActivity.isUp) {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("、home+right");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        } else {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("home+right");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        }
                                                    if (FirstPageActivity.isDown)
                                                        if ((!FirstPageActivity.isUp) && (!FirstPageActivity.isRight)) {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("home+down");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        } else {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("、home+down");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        }
                                                    if (FirstPageActivity.isLeft)
                                                        if ((!FirstPageActivity.isUp) && (!FirstPageActivity.isRight) && (!FirstPageActivity.isDown)) {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("home+left");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        } else {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                            ((StringBuilder) localObject1).append("、home+left");
                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                        }
                                                }
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.syncToastStr).show();
                                                FirstPageActivity.mUiHandler.sendEmptyMessage(201811);
                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201805, 1000L);
                                            }
                                            FirstPageActivity.isSyncPresetNow = false;
                                            continue;
                                            FirstPageActivity.isLeft = false;
                                            FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                            continue;
                                            FirstPageActivity.isLeft = true;
                                            FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                            continue;
                                            FirstPageActivity.isLeft = false;
                                            FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                            continue;
                                            FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201863, 201864, 201865, "left");
                                            continue;
                                            FirstPageActivity.isDown = false;
                                            FirstPageActivity.location = "left";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201862, 201863);
                                            continue;
                                            FirstPageActivity.isDown = true;
                                            FirstPageActivity.location = "left";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201862, 201863);
                                            continue;
                                            FirstPageActivity.isDown = false;
                                            FirstPageActivity.location = "left";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201862, 201863);
                                            continue;
                                            FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201859, 201860, 201861, "down");
                                            continue;
                                            FirstPageActivity.isRight = false;
                                            FirstPageActivity.location = "down";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201858, 201859);
                                            continue;
                                            FirstPageActivity.isRight = true;
                                            FirstPageActivity.location = "down";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201858, 201859);
                                            continue;
                                            FirstPageActivity.isRight = false;
                                            FirstPageActivity.location = "down";
                                            OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201858, 201859);
                                            continue;
                                            FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201855, 201856, 201857, "right");
                                            continue;
                                            FirstPageActivity.isUp = false;
                                            if (bluetoothdevmanager.mapMaxNum == 1) {
                                                FirstPageActivity.isRight = false;
                                                FirstPageActivity.isDown = false;
                                                FirstPageActivity.isLeft = false;
                                                FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                            } else {
                                                FirstPageActivity.location = "right";
                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201854, 201855);
                                                continue;
                                                FirstPageActivity.isUp = true;
                                                if (bluetoothdevmanager.mapMaxNum == 1) {
                                                    FirstPageActivity.isRight = false;
                                                    FirstPageActivity.isDown = false;
                                                    FirstPageActivity.isLeft = false;
                                                    FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                                } else {
                                                    FirstPageActivity.location = "right";
                                                    OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201854, 201855);
                                                    continue;
                                                    FirstPageActivity.isUp = false;
                                                    if (bluetoothdevmanager.mapMaxNum == 1) {
                                                        FirstPageActivity.isRight = false;
                                                        FirstPageActivity.isDown = false;
                                                        FirstPageActivity.isLeft = false;
                                                        FirstPageActivity.this.syncCloudFinishGp(FirstPageActivity.mContext, FirstPageActivity.isUp, FirstPageActivity.isRight, FirstPageActivity.isDown, FirstPageActivity.isLeft);
                                                    } else {
                                                        FirstPageActivity.location = "right";
                                                        OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201854, 201855);
                                                        continue;
                                                        FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201851, 201852, 201853, "up");
                                                        continue;
                                                        if (FirstPageActivity.reSendNumsp <= 2) {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append("----开始计数 reSendNum = ");
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.reSendNumsp);
                                                            MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201849, 2500L);
                                                            FirstPageActivity.access$4808();
                                                            FirstPageActivity.this.sendSyncDataToDevice(FirstPageActivity.syncCurrentLocation);
                                                        } else {
                                                            localObject1 = new StringBuilder();
                                                            ((StringBuilder) localObject1).append("----发送");
                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncCurrentLocation);
                                                            ((StringBuilder) localObject1).append("预设到设备超时失败！");
                                                            MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                                            FirstPageActivity.access$4802(0);
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f1")))
                                                                FirstPageActivity.isF1 = false;
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f2")))
                                                                FirstPageActivity.isF2 = false;
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f3")))
                                                                FirstPageActivity.isF3 = false;
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f4")))
                                                                FirstPageActivity.isF4 = false;
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f5")))
                                                                FirstPageActivity.isF5 = false;
                                                            FirstPageActivity.mUiHandler.sendEmptyMessage(201848);
                                                            continue;
                                                            if ((FirstPageActivity.reSendNumsp <= 2) && (FirstPageActivity.reSendNumsp != 0)) {
                                                                localObject1 = new StringBuilder();
                                                                ((StringBuilder) localObject1).append("----停止计数 reSendNum = ");
                                                                ((StringBuilder) localObject1).append(FirstPageActivity.reSendNumsp);
                                                                MyLog.i("sync_sync", ((StringBuilder) localObject1).toString());
                                                                FirstPageActivity.mUiHandler.removeMessages(201849);
                                                            }
                                                            FirstPageActivity.access$4802(0);
                                                            if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f1")) && (FirstPageActivity.isF2)) {
                                                                FirstPageActivity.mUiHandler.sendEmptyMessage(201812);
                                                                FirstPageActivity.syncCurrentLocation = "f2";
                                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                                                            } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f2")) && (FirstPageActivity.isF3)) {
                                                                FirstPageActivity.mUiHandler.sendEmptyMessage(201813);
                                                                FirstPageActivity.syncCurrentLocation = "f3";
                                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                                                            } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f3")) && (FirstPageActivity.isF4)) {
                                                                FirstPageActivity.mUiHandler.sendEmptyMessage(201814);
                                                                FirstPageActivity.syncCurrentLocation = "f4";
                                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                                                            } else if ((CommonUtils.isStringValid(FirstPageActivity.syncCurrentLocation)) && (FirstPageActivity.syncCurrentLocation.equalsIgnoreCase("f4")) && (FirstPageActivity.isF5)) {
                                                                FirstPageActivity.mUiHandler.sendEmptyMessage(201815);
                                                                FirstPageActivity.syncCurrentLocation = "f5";
                                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201849, 200L);
                                                            } else {
                                                                if (FirstPageActivity.isSyncPresetNow) {
                                                                    if ((FirstPageActivity.isF1) && (FirstPageActivity.isF2) && (FirstPageActivity.isF3) && (FirstPageActivity.isF4) && (FirstPageActivity.isF5)) {
                                                                        FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip39);
                                                                    } else if ((!FirstPageActivity.isF1) && (!FirstPageActivity.isF2) && (!FirstPageActivity.isF3) && (!FirstPageActivity.isF4) && (!FirstPageActivity.isF5)) {
                                                                        FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip38);
                                                                    } else {
                                                                        FirstPageActivity.syncToastStr = FirstPageActivity.mContext.getResources().getString(R.string.new_setup_dialog_tip40);
                                                                        if (FirstPageActivity.isF1) {
                                                                            localObject1 = new StringBuilder();
                                                                            ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                            ((StringBuilder) localObject1).append("Ctrl+F1");
                                                                            FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                        }
                                                                        if (FirstPageActivity.isF2)
                                                                            if (FirstPageActivity.isF1) {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("、Ctrl+F2");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            } else {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("Ctrl+F2");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            }
                                                                        if (FirstPageActivity.isF3)
                                                                            if ((!FirstPageActivity.isF1) && (!FirstPageActivity.isF2)) {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("Ctrl+F3");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            } else {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("、Ctrl+F3");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            }
                                                                        if (FirstPageActivity.isF4)
                                                                            if ((!FirstPageActivity.isF1) && (!FirstPageActivity.isF2) && (!FirstPageActivity.isF3)) {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("Ctrl+F4");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            } else {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("、Ctrl+F4");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            }
                                                                        if (FirstPageActivity.isF5)
                                                                            if ((!FirstPageActivity.isF1) && (!FirstPageActivity.isF2) && (!FirstPageActivity.isF3) && (!FirstPageActivity.isF4)) {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("Ctrl+F5");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            } else {
                                                                                localObject1 = new StringBuilder();
                                                                                ((StringBuilder) localObject1).append(FirstPageActivity.syncToastStr);
                                                                                ((StringBuilder) localObject1).append("、Ctrl+F5");
                                                                                FirstPageActivity.syncToastStr = ((StringBuilder) localObject1).toString();
                                                                            }
                                                                    }
                                                                    new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.syncToastStr).show();
                                                                    FirstPageActivity.mUiHandler.sendEmptyMessage(201811);
                                                                    FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201805, 1000L);
                                                                }
                                                                FirstPageActivity.isSyncPresetNow = false;
                                                                continue;
                                                                FirstPageActivity.isF5 = false;
                                                                FirstPageActivity.this.syncCloudFinish(FirstPageActivity.mContext, FirstPageActivity.isF1, FirstPageActivity.isF2, FirstPageActivity.isF3, FirstPageActivity.isF4, FirstPageActivity.isF5);
                                                                continue;
                                                                FirstPageActivity.isF5 = true;
                                                                FirstPageActivity.this.syncCloudFinish(FirstPageActivity.mContext, FirstPageActivity.isF1, FirstPageActivity.isF2, FirstPageActivity.isF3, FirstPageActivity.isF4, FirstPageActivity.isF5);
                                                                continue;
                                                                FirstPageActivity.isF5 = false;
                                                                FirstPageActivity.this.syncCloudFinish(FirstPageActivity.mContext, FirstPageActivity.isF1, FirstPageActivity.isF2, FirstPageActivity.isF3, FirstPageActivity.isF4, FirstPageActivity.isF5);
                                                                continue;
                                                                FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201845, 201846, 201847, "f5");
                                                                continue;
                                                                FirstPageActivity.isF4 = false;
                                                                FirstPageActivity.location = "F5";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201844, 201845);
                                                                continue;
                                                                FirstPageActivity.isF4 = true;
                                                                FirstPageActivity.location = "F5";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201844, 201845);
                                                                continue;
                                                                FirstPageActivity.isF5 = false;
                                                                FirstPageActivity.location = "F5";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201844, 201845);
                                                                continue;
                                                                FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201841, 201842, 201843, "f4");
                                                                continue;
                                                                FirstPageActivity.isF3 = false;
                                                                FirstPageActivity.location = "F4";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201840, 201841);
                                                                continue;
                                                                FirstPageActivity.isF3 = true;
                                                                FirstPageActivity.location = "F4";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201840, 201841);
                                                                continue;
                                                                FirstPageActivity.isF3 = false;
                                                                FirstPageActivity.location = "F4";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201840, 201841);
                                                                continue;
                                                                FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201837, 201838, 201839, "f3");
                                                                continue;
                                                                FirstPageActivity.isF2 = false;
                                                                FirstPageActivity.location = "F3";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201836, 201837);
                                                                continue;
                                                                FirstPageActivity.isF2 = true;
                                                                FirstPageActivity.location = "F3";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201836, 201837);
                                                                continue;
                                                                FirstPageActivity.isF2 = false;
                                                                FirstPageActivity.location = "F3";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201836, 201837);
                                                                continue;
                                                                FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201833, 201834, 201835, "f2");
                                                                continue;
                                                                FirstPageActivity.isF1 = false;
                                                                FirstPageActivity.location = "F2";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201832, 201833);
                                                                continue;
                                                                FirstPageActivity.isF1 = true;
                                                                FirstPageActivity.location = "F2";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201832, 201833);
                                                                continue;
                                                                FirstPageActivity.isF1 = false;
                                                                FirstPageActivity.location = "F2";
                                                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201832, 201833);
                                                                continue;
                                                                FirstPageActivity.this.downloadOfficialFile((JSONObject) paramAnonymousMessage.obj, 201829, 201830, 201831, "f1");
                                                                continue;
                                                                BlueCmdManager.setDeviceCurrentMap((byte) 4);
                                                                continue;
                                                                BlueCmdManager.setDeviceCurrentMap((byte) 3);
                                                                continue;
                                                                BlueCmdManager.setDeviceCurrentMap((byte) 2);
                                                                continue;
                                                                BlueCmdManager.setDeviceCurrentMap((byte) 1);
                                                                continue;
                                                                BlueCmdManager.setDeviceCurrentMap((byte) 0);
                                                                continue;
                                                                bluetoothdevmanager.isEpstMode = false;
                                                                FirstPageActivity.access$5102(FirstPageActivity.this, "");
                                                                new ToastDialog(FirstPageActivity.mContext, "打开工程模式失败").show();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (Exception localException5) {
                                try {
                                    localObject1 = (JSONObject) paramAnonymousMessage.obj;
                                    if ((((JSONObject) localObject1).getInt("code") == 1000) && (((JSONObject) localObject1).getString("message").equalsIgnoreCase("success")) && (CommonUtils.isStringValid(((JSONObject) localObject1).getString("pwd"))) && (((JSONObject) localObject1).getString("pwd").equalsIgnoreCase(FirstPageActivity.this.epseCode))) {
                                        bluetoothdevmanager.isEpstMode = true;
                                        new ToastDialog(FirstPageActivity.mContext, "已处于工程模式！").show();
                                        FirstPageActivity.access$5102(FirstPageActivity.this, "");
                                    } else {
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(25);
                                        continue;
                                        FirstPageActivity.mUiHandler.sendEmptyMessage(25);
                                        continue;
                                        FirstPageActivity.access$5602(false);
                                        BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_PRODUCT_INFO);
                                        continue;
                                        FirstPageActivity.isUpdateFW = true;
                                        FirstPageActivity.this.initUpdateUI();
                                        continue;
                                        FirstPageActivity.isUpdateAPP = true;
                                        FirstPageActivity.this.initUpdateUI();
                                        continue;
                                        try {
                                            localObject1 = (JSONObject) paramAnonymousMessage.obj;
                                            if (((JSONObject) localObject1).getInt("code") == 1000) {
                                                FirstPageActivity.access$5202(new AppInfo(new JSONObject(((JSONObject) localObject1).getString("data"))));
                                                if (FirstPageActivity.mAppInfo.getVersionCode() > CommonUtils.getLocalVersion(FirstPageActivity.mContext))
                                                    FirstPageActivity.isUpdateAPP = false;
                                                else
                                                    FirstPageActivity.isUpdateAPP = true;
                                            } else {
                                                FirstPageActivity.isUpdateAPP = true;
                                            }
                                        } catch (Exception localException1) {
                                            localException1.printStackTrace();
                                            FirstPageActivity.isUpdateAPP = true;
                                        }
                                        FirstPageActivity.this.initUpdateUI();
                                        continue;
                                        FirstPageActivity.isUpdateFW = true;
                                        FirstPageActivity.this.initUpdateUI();
                                        continue;
                                        try {
                                            Object localObject2 = (JSONObject) paramAnonymousMessage.obj;
                                            if (((JSONObject) localObject2).getInt("code") == 1000) {
                                                if (bluetoothdevmanager.devicemode == 0) {
                                                    localObject2 = ((JSONObject) localObject2).getJSONArray("data");
                                                    if (((JSONArray) localObject2).length() <= 0)
                                                        break label10951;
                                                    i = 0;
                                                    if (i >= ((JSONArray) localObject2).length())
                                                        break label10951;
                                                    FirstPageActivity.this.mFWInfoList.add(new FWInfo((JSONObject) ((JSONArray) localObject2).get(i)));
                                                    i += 1;
                                                    continue;
                                                    if (i < FirstPageActivity.this.mFWInfoList.size()) {
                                                        if (i == 0) {
                                                            FirstPageActivity.access$5502((FWInfo) FirstPageActivity.this.mFWInfoList.get(i));
                                                            break label10956;
                                                        }
                                                        if (!CommonUtils.checkFWVersion(((FWInfo) FirstPageActivity.this.mFWInfoList.get(i)).getVersionCode(), FirstPageActivity.mFWInfo.getVersionCode()))
                                                            break label10956;
                                                        FirstPageActivity.access$5502((FWInfo) FirstPageActivity.this.mFWInfoList.get(i));
                                                        break label10956;
                                                    }
                                                } else {
                                                    FirstPageActivity.access$5502(new FWInfo(new JSONObject(((JSONObject) localObject2).getString("data"))));
                                                }
                                                localObject2 = new StringBuilder();
                                                ((StringBuilder) localObject2).append("-----获取服务器上分位信息成功----mFWInfo.getVersionCode() = ");
                                                ((StringBuilder) localObject2).append(FirstPageActivity.mFWInfo.getVersionCode());
                                                ((StringBuilder) localObject2).append(";    hwVersion = ");
                                                ((StringBuilder) localObject2).append(FirstPageActivity.fwVerCode);
                                                MyLog.i("my_tag", ((StringBuilder) localObject2).toString());
                                                if ((FirstPageActivity.mFWInfo != null) && (CommonUtils.isStringValid(FirstPageActivity.fwVerCode))) {
                                                    localObject2 = new StringBuilder();
                                                    ((StringBuilder) localObject2).append("---ignoreVersion = ");
                                                    ((StringBuilder) localObject2).append(FirstPageActivity.this.ignoreVersion);
                                                    MyLog.i("my_tag", ((StringBuilder) localObject2).toString());
                                                    if ((CommonUtils.checkFWVersion(FirstPageActivity.mFWInfo.getVersionCode(), FirstPageActivity.fwVerCode)) && ((FirstPageActivity.this.ignoreVersion == null) || ((FirstPageActivity.this.ignoreVersion != null) && (CommonUtils.checkFWVersion(FirstPageActivity.mFWInfo.getVersionCode(), FirstPageActivity.this.ignoreVersion))) || (bluetoothdevmanager.devicemode != 0))) {
                                                        localObject2 = new StringBuilder();
                                                        ((StringBuilder) localObject2).append("---isUpdateFW = ");
                                                        ((StringBuilder) localObject2).append(FirstPageActivity.isUpdateFW);
                                                        MyLog.i("my_tag", ((StringBuilder) localObject2).toString());
                                                        FirstPageActivity.isUpdateFW = false;
                                                    } else {
                                                        FirstPageActivity.isUpdateFW = true;
                                                    }
                                                    if (CommonUtils.isStringValid(FirstPageActivity.projectName)) {
                                                        FirstPageActivity.this.kv.encode("ProjectName", FirstPageActivity.projectName);
                                                        FirstPageActivity.this.kv.encode(FirstPageActivity.projectName, FirstPageActivity.macAddress);
                                                        FirstPageActivity.this.kv.encode(FirstPageActivity.macAddress, FirstPageActivity.isUpdateFW);
                                                        FirstPageActivity.this.kv.encode(FirstPageActivity.macAddress, bluetoothdevmanager.devicemode);
                                                    }
                                                    FirstPageActivity.this.initUpdateUI();
                                                }
                                            } else {
                                                FirstPageActivity.isUpdateFW = true;
                                                FirstPageActivity.this.initUpdateUI();
                                                Log.i("my_tag", "-----获取服务器上分位信息返回失败----");
                                            }
                                        } catch (Exception localException2) {
                                            localException2.printStackTrace();
                                            FirstPageActivity.isUpdateFW = true;
                                            FirstPageActivity.this.initUpdateUI();
                                        }
                                        if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763))) {
                                            FirstPageActivity.access$6302(true);
                                            if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                FirstPageActivity.mMyLoadDialog.dismiss();
                                            MyLog.i("my_tag", "763手柄，预设加载失败，禁用！");
                                            bluetoothdevmanager.disconnect();
                                        } else if ((bluetoothdevmanager.devicemode != 0) && (bluetoothdevmanager.mapVersion >= 7)) {
                                            FirstPageActivity.access$6302(true);
                                            if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                FirstPageActivity.mMyLoadDialog.dismiss();
                                            bluetoothdevmanager.disconnect();
                                        } else {
                                            FirstPageActivity.access$6302(true);
                                            if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                FirstPageActivity.mMyLoadDialog.dismiss();
                                            FirstPageActivity.this.showUi();
                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip6)).show();
                                            continue;
                                            FirstPageActivity.access$6302(true);
                                            if ((CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763))) {
                                                FirstPageActivity.access$6602(FirstPageActivity.this, CommonUtils.getByteArray());
                                                FirstPageActivity.access$6702(FirstPageActivity.this, CommonUtils.getEncrpyArray(FirstPageActivity.this.RandomByteArray[4] & 0xFF));
                                                FirstPageActivity.access$6802(FirstPageActivity.this, CommonUtils.getMapDataByteArray(MyApplication.getGpDatapro()));
                                                BlueCmdManager.sendRandomByteArray(FirstPageActivity.this.RandomByteArray);
                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201906, 500L);
                                            } else if ((bluetoothdevmanager.devicemode != 0) && (bluetoothdevmanager.mapVersion >= 7)) {
                                                FirstPageActivity.access$6602(FirstPageActivity.this, CommonUtils.getByteArray());
                                                FirstPageActivity.access$6702(FirstPageActivity.this, CommonUtils.getEncrpyArray(FirstPageActivity.this.RandomByteArray[4] & 0xFF));
                                                FirstPageActivity.access$6802(FirstPageActivity.this, CommonUtils.getMapDataByteArray(MyApplication.getGpDatapro()));
                                                BlueCmdManager.sendRandomByteArray(FirstPageActivity.this.RandomByteArray);
                                                FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(201906, 500L);
                                            } else {
                                                if ((FirstPageActivity.mMyLoadDialog != null) && (FirstPageActivity.mMyLoadDialog.isShowing()))
                                                    FirstPageActivity.mMyLoadDialog.dismiss();
                                                FirstPageActivity.this.showUi();
                                                new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip5)).show();
                                                continue;
                                                if (bluetoothdevmanager.mConnectionState != 0) {
                                                    if (FirstPageActivity.isResetDevice) {
                                                        FirstPageActivity.isResetDevice = false;
                                                        new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.reset_device_success)).show();
                                                    }
                                                    BlueCmdManager.sendNormalCmd(BlueCmdManager.CMD_GET_DEVICE_PRODUCT_INFO);
                                                } else {
                                                    new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip7)).show();
                                                    continue;
                                                    if ((CommonUtils.isStringValid(bluetoothdevmanager.mDeviceAddress)) && (CommonUtils.isStringValid(bluetoothdevmanager.factoryName)) && (bluetoothdevmanager.factoryName.equalsIgnoreCase(ProjectFilterConfig.device763)) && (!CommonUtils.isAndroidV3Mode(bluetoothdevmanager.mDeviceAddress))) {
                                                        FirstPageActivity.this.showIOSModeDialog();
                                                    } else {
                                                        FirstPageActivity.this.loadMap();
                                                        if (FirstPageActivity.isShowFBtn) {
                                                            Object localObject3 = new Intent(FirstPageActivity.this, FloatWindowService.class);
                                                            if (Build.VERSION.SDK_INT >= 26) {
                                                                FirstPageActivity.this.startForegroundService((Intent) localObject3);
                                                            } else {
                                                                FirstPageActivity.this.startService((Intent) localObject3);
                                                                continue;
                                                                FirstPageActivity.this.analyseBlueData((byte[]) paramAnonymousMessage.obj);
                                                                continue;
                                                                if (bluetoothdevmanager.mConnectionState == 1) {
                                                                    FirstPageActivity.access$2502(FirstPageActivity.this, 0);
                                                                    OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201870, 201871);
                                                                } else {
                                                                    FirstPageActivity.hwVersion = "";
                                                                    FirstPageActivity.textconnstate.setTextColor(-65536);
                                                                    FirstPageActivity.textconnstate.setText(R.string.connerror);
                                                                    FirstPageActivity.this.iv_didnot_connect_device.setImageDrawable(FirstPageActivity.this.getResources().getDrawable(R.mipmap.didnot_connect_device));
                                                                    FirstPageActivity.this.ll_cloud.setVisibility(View.GONE);
                                                                    FirstPageActivity.this.ll_game_list.setVisibility(View.GONE);
                                                                    FirstPageActivity.this.ll_factory_manager.setVisibility(View.GONE);
                                                                    continue;
                                                                    FirstPageActivity.access$5602(false);
                                                                    if ((!CommonUtils.isStringValid(FirstPageActivity.hwVersion)) || (FirstPageActivity.hwVersion.equalsIgnoreCase("HW:UNKNOWN")))
                                                                        if (!FirstPageActivity.isReConnect) {
                                                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(8, 2000L);
                                                                        } else {
                                                                            new ToastDialog(FirstPageActivity.mContext, FirstPageActivity.mContext.getResources().getString(R.string.first_page_tip3)).show();
                                                                            FirstPageActivity.mUiHandler.sendEmptyMessageDelayed(23, 2000L);
                                                                        }
                                                                    if (!FirstPageActivity.this.kv.decodeBool("is_post_phone_info", false)) {
                                                                        if (bluetoothdevmanager.screenwp > bluetoothdevmanager.screenhp) {
                                                                            localObject3 = new StringBuilder();
                                                                            ((StringBuilder) localObject3).append((int) bluetoothdevmanager.screenwp);
                                                                            ((StringBuilder) localObject3).append("x");
                                                                            ((StringBuilder) localObject3).append((int) bluetoothdevmanager.screenhp);
                                                                            localObject3 = ((StringBuilder) localObject3).toString();
                                                                        } else {
                                                                            localObject3 = new StringBuilder();
                                                                            ((StringBuilder) localObject3).append((int) bluetoothdevmanager.screenhp);
                                                                            ((StringBuilder) localObject3).append("x");
                                                                            ((StringBuilder) localObject3).append((int) bluetoothdevmanager.screenwp);
                                                                            localObject3 = ((StringBuilder) localObject3).toString();
                                                                        }
                                                                        localObject4 = new StringBuilder();
                                                                        ((StringBuilder) localObject4).append("-------手机唯一码:");
                                                                        ((StringBuilder) localObject4).append(DeviceUtils.getUniqueId(FirstPageActivity.mContext));
                                                                        ((StringBuilder) localObject4).append(";\n手机厂商:");
                                                                        ((StringBuilder) localObject4).append(DeviceUtils.getDeviceBrand());
                                                                        ((StringBuilder) localObject4).append(";\n手机型号:");
                                                                        ((StringBuilder) localObject4).append(DeviceUtils.getSystemModel());
                                                                        ((StringBuilder) localObject4).append(";\n分辨率:");
                                                                        ((StringBuilder) localObject4).append((String) localObject3);
                                                                        MyLog.i("my_tag", ((StringBuilder) localObject4).toString());
                                                                        localObject4 = new HashMap();
                                                                        ((HashMap) localObject4).put("platform", "1");
                                                                        ((HashMap) localObject4).put("brand", DeviceUtils.getDeviceBrand());
                                                                        ((HashMap) localObject4).put("model", DeviceUtils.getSystemModel());
                                                                        ((HashMap) localObject4).put("resolution", localObject3);
                                                                        ((HashMap) localObject4).put("imei", DeviceUtils.getUniqueId(FirstPageActivity.mContext));
                                                                        OkHttpUtil.postDataWithParame(HttpUrlConfig.POST_PHONE_INFO_URL, (HashMap) localObject4);
                                                                    }
                                                                    FirstPageActivity.access$2502(FirstPageActivity.this, 0);
                                                                    OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.GET_OFFICIAL_GAME_LIST, 201870, 201871);
                                                                    continue;
                                                                    if (bluetoothdevmanager.mConnectionState != 0) {
                                                                        bluetoothdevmanager.mConnectionState = 0;
                                                                        FirstPageActivity.access$5602(true);
                                                                    }
                                                                    FirstPageActivity.hwVersion = "";
                                                                    FirstPageActivity.textconnstate.setTextColor(-65536);
                                                                    FirstPageActivity.textconnstate.setText(R.string.connerror);
                                                                    FirstPageActivity.this.iv_didnot_connect_device.setImageDrawable(FirstPageActivity.this.getResources().getDrawable(R.mipmap.didnot_connect_device));
                                                                    FirstPageActivity.this.ll_cloud.setVisibility(View.GONE);
                                                                    FirstPageActivity.this.ll_game_list.setVisibility(View.GONE);
                                                                    FirstPageActivity.this.ll_factory_manager.setVisibility(View.GONE);
                                                                    continue;
                                                                    if ((FirstPageActivity.mSyncdingDialog != null) && (FirstPageActivity.mSyncdingDialog.isShowing()))
                                                                        FirstPageActivity.mSyncdingDialog.dismiss();
                                                                    BlueCmdManager.sendGetMapCmd((byte) 0, (byte) 0);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    label10911:
                                    super.handleMessage(paramAnonymousMessage);
                                    return;
                                    localException3 = localException3;
                                    continue;
                                    localException4 = localException4;
                                    continue;
                                    localException5 = localException5;
                                } catch (Exception localException6) {
                                    continue;
                                }
                            }
                        }
                        label10937:
                        j += 1;
                        continue;
                        label10944:
                        i += 1;
                        continue;
                        label10951:
                        i = 0;
                        continue;
                        label10956:
                        i += 1;
                    }
                }
            }
        };
        startmyservice();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt1 == 1234)
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this))
                    new ToastDialog(mContext, mContext.getResources().getString(R.string.open_pp_fail)).show();
                else
                    new ToastDialog(mContext, mContext.getResources().getString(R.string.indicator5)).show();
            } else {
                new ToastDialog(mContext, mContext.getResources().getString(R.string.indicator5)).show();
            }
        if ((paramInt1 == IMAGE_REQUEST_CODE) && (paramInt2 == Activity.RESULT_OK) && (paramIntent != null)) {
            Uri uri = paramIntent.getData();
            assert uri != null;
            Cursor cursor = getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            assert cursor != null;
            if (cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndex("_data"));
            }
            cursor.close();
            // TODO 有更改，未知用处
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    protected void onCreate(@Nullable Bundle paramBundle) {
        overridePendingTransition(0, 0);
        super.onCreate(paramBundle);
        MyLog.i("my_tag", "------onCreate------");
        setContentView(R.layout.activity_first_page);//"power"
        this.wakeLock = ((PowerManager) getSystemService(Context.POWER_SERVICE)).newWakeLock(26, "My Tag");
        try {
            this.mDBManager = new DBManager(this);
            //label61:
            installing = 0;
            mContext = this;
            instance = this;
            locale = Locale.getDefault().toString();
            this.kv = MMKV.mmkvWithID("MyID", 2);
            initView();
            bluetoothdevmanager.setCallbaccmd(new bluetoothdevmanager.Callback3() {
                public void onDataChange(byte[] paramAnonymousArrayOfByte) {
                    Message localMessage = new Message();
                    localMessage.what = 6;
                    localMessage.obj = paramAnonymousArrayOfByte;
                    if (mUiHandler != null)
                        mUiHandler.sendMessage(localMessage);
                }
            });
            new Thread(new Runnable() {
                public void run() {
                    projectName = kv.decodeString("ProjectName");
                    if (CommonUtils.isStringValid(projectName)) {
                        macAddress = kv.decodeString(projectName);
                        isUpdateFW = kv.decodeBool(macAddress, true);
                        ignoreVersion = kv.decodeString("ignore_version");
                        if (ignoreVersion == null)
                            ignoreVersion = "1.01";
                    } else {
                        isUpdateFW = true;
                    }
                    isShowFBtn = kv.decodeBool("float_btn_setup_key", true);
                    cb_fbtn.setChecked(isShowFBtn);
                    //FirstPageActivity.access$902(kv.decodeBool("auto_sync_preset_tip_key", false));
                    isShowFBtn = kv.decodeBool("auto_sync_preset_tip_key", false);
                    OkHttpUtil.get(mUiHandler, "http://shootingplus.com.cn/shootingplus/open/games/getAppVersionInfo?type=0", 20, 21);
                }
            }).start();
            initViewOfficialGame();
            initViewFactoryManager();
            initHelpView();
            initViewOfSunyesMaxGame();
            return;
        } catch (Exception e) {
            //break label61;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        MyLog.i("my_tag", "---------onDestroy-------");
        if (this.wakeLock != null) {
            this.wakeLock.release();
            this.wakeLock = null;
        }
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
            return true;
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            public void run() {
                MyLog.i("my_tag", "------onResume------");
                if ((FirstPageActivity.this.wakeLock != null) && (!FirstPageActivity.this.wakeLock.isHeld()))
                    FirstPageActivity.this.wakeLock.acquire();
                Message localMessage = new Message();
                localMessage.what = 5;
                if (FirstPageActivity.mUiHandler != null)
                    FirstPageActivity.mUiHandler.sendMessageDelayed(localMessage, 1500L);
            }
        }).start();
    }

    protected void onStart() {
        super.onStart();
        MyLog.i("my_tag", "------onStart------");
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
    }

    public class editPresetDialog extends AlertDialog {
        private Context mContext;
        private OfficialGamePreset mOfficialGamePreset;
        private TextView tv_cancel;
        private TextView tv_sure;

        public editPresetDialog(Context paramOfficialGamePreset, OfficialGamePreset arg3) {
            super(paramOfficialGamePreset);
            this.mContext = paramOfficialGamePreset;
            //Object localObject;
            this.mOfficialGamePreset = arg3;
        }

        void init() {
            this.tv_cancel = ((TextView) findViewById(R.id.tv_cancel));
            this.tv_sure = ((TextView) findViewById(R.id.tv_sure));
            this.tv_sure.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    int i;
                    if (bluetoothdevmanager.devicemode == 0)
                        i = 0;
                    else
                        i = 1;
                    CommonUtils.startInstallApp(FirstPageActivity.editPresetDialog.this.mContext, FirstPageActivity.editPresetDialog.this.mOfficialGamePreset.getAppPackageName());
                    StringBuilder editBuilder = new StringBuilder();
                    editBuilder.append("Preset Path = ");
                    editBuilder.append(FirstPageActivity.this.mDBManager.queryByGame(FirstPageActivity.editPresetDialog.this.mOfficialGamePreset.getAppName(), i).getPresetPath());
                    MyLog.i("my_tag", editBuilder.toString());
                    if ((bluetoothdevmanager.servicehandle != null) && (!FirstPageActivity.isTestKeyMode) && (bluetoothdevmanager.mConnectionState == 1)) {
                        Message message = new Message();
                        message.what = 20022;
                        message.arg1 = 666;
                        message.obj = FirstPageActivity.editPresetDialog.this.mOfficialGamePreset;
                        bluetoothdevmanager.servicehandle.sendMessageDelayed(message, 300L);
                    }
                    FirstPageActivity.editPresetDialog.this.dismiss();
                }
            });
            this.tv_cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    FirstPageActivity.editPresetDialog.this.dismiss();
                }
            });
        }

        protected void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            setContentView(R.layout.edit_preset_dialog);
            getWindow().setLayout(-1, -1);
            getWindow().setFlags(8, 8);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);//17170445
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
            setCanceledOnTouchOutside(false);
            init();
        }
    }

    public class presetCloudSyncDialog extends AlertDialog {
        private Context mContext;
        private TextView tv_cancel;
        private TextView tv_sure;

        public presetCloudSyncDialog(Context arg2) {
            super(arg2);
            this.mContext = arg2;
        }

        void init() {
            this.tv_cancel = ((TextView) findViewById(R.id.tv_cancel));
            this.tv_sure = ((TextView) findViewById(R.id.tv_sure));
            this.tv_sure.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    if (mSyncdingDialog == null)//access$3902
                        mSyncdingDialog = new syncdingDialog(mContext);
                    if (!mSyncdingDialog.isShowing())
                        mSyncdingDialog.show();
                    if (bluetoothdevmanager.mConnectionState == 1)
                        if (bluetoothdevmanager.devicemode == 0) {
                            platform = 1;
                            deviceType = 0;
                            model = projectName;
                            if ((int) bluetoothdevmanager.screenhp > (int) bluetoothdevmanager.screenwp) {
                                StringBuilder str = new StringBuilder();
                                str.append((int) bluetoothdevmanager.screenhp);
                                str.append("x");
                                str.append((int) bluetoothdevmanager.screenwp);
                                resolution = str.toString();
                            } else {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append((int) bluetoothdevmanager.screenwp);
                                stringBuilder.append("x");
                                stringBuilder.append((int) bluetoothdevmanager.screenhp);
                                resolution = stringBuilder.toString();
                            }
                            location = "F1";
                            OkHttpUtil.get(mUiHandler, HttpUrlConfig
                                    .getOfficialFileDownloadRul(platform, deviceType, model, location, resolution), MSG_ON_GET_F1_FILE_DOWNLOAD_URL_SUCCESS, 201829);
                        } else if (bluetoothdevmanager.devicemode == 2) {
                            if ((bluetoothdevmanager.defaultGameId.size() > 0) && (bluetoothdevmanager.mapMaxNum == 1)) {
                                FirstPageActivity.isSyncPresetByGameIdNow = true;
                                FirstPageActivity.syncCurrentLocation = "up";
                                FirstPageActivity.this.downloadPresetTo(
                                        CommonUtils.getGameDownloadOfficialGamePreset(
                                                bluetoothdevmanager.mOfficialGamePresetList, (
                                                        (Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue()),
                                        CommonUtils.getGameDownloadUrl(bluetoothdevmanager.mOfficialGamePresetList, (
                                                (Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue()));
                                if (downLoadDialog != null) {
                                    if (downLoadDialog.isShowing())
                                        downLoadDialog.dismiss();
                                    downLoadDialog = null;
                                }
                                downLoadDialog = new DownLoadDialog(mContext);
                                downLoadDialog.setCancelable(false);
                                downLoadDialog.setCanceledOnTouchOutside(false);
                                FirstPageActivity.downLoadDialog.show();
                            } else {
                                FirstPageActivity.platform = 1;
                                FirstPageActivity.deviceType = 1;
                                FirstPageActivity.model = FirstPageActivity.projectName;
                                if ((int) bluetoothdevmanager.screenhp > (int) bluetoothdevmanager.screenwp) {
                                    StringBuilder stringBuffer = new StringBuilder();
                                    stringBuffer.append((int) bluetoothdevmanager.screenhp);
                                    stringBuffer.append("x");
                                    stringBuffer.append((int) bluetoothdevmanager.screenwp);
                                    resolution = stringBuffer.toString();
                                } else {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append((int) bluetoothdevmanager.screenwp);
                                    stringBuilder.append("x");
                                    stringBuilder.append((int) bluetoothdevmanager.screenhp);
                                    resolution = stringBuilder.toString();
                                }
                                FirstPageActivity.location = "up";
                                OkHttpUtil.get(FirstPageActivity.mUiHandler, HttpUrlConfig.getOfficialFileDownloadRul(FirstPageActivity.platform, FirstPageActivity.deviceType, FirstPageActivity.model, FirstPageActivity.location, FirstPageActivity.resolution), 201850, 201851);
                            }
                        } else {
                            new ToastDialog(FirstPageActivity.presetCloudSyncDialog.this.mContext, "未知设备，无法同步！").show();
                        }
                    FirstPageActivity.presetCloudSyncDialog.this.dismiss();
                }
            });
            this.tv_cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    FirstPageActivity.presetCloudSyncDialog.this.dismiss();
                }
            });
        }

        protected void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            setContentView(R.layout.savedialog12);
            getWindow().setLayout(-1, -1);
            getWindow().setFlags(8, 8);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
            setCanceledOnTouchOutside(false);
            init();
        }
    }

    public class presetCloudSyncDialog2 extends AlertDialog {
        private CheckBox cb_cb;
        private Context mContext;
        private TextView tv_cancel;
        private TextView tv_sure;

        public presetCloudSyncDialog2(Context arg2) {
            super(arg2);
            this.mContext = arg2;
        }

        void init() {
            this.tv_cancel = ((TextView) findViewById(R.id.tv_cancel));
            this.tv_sure = ((TextView) findViewById(R.id.tv_sure));
            this.cb_cb = ((CheckBox) findViewById(R.id.cb_cb));
            this.tv_sure.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    if (bluetoothdevmanager.mConnectionState == 1) {
                        FirstPageActivity.isSyncPresetByGameIdNow = true;
                        FirstPageActivity.syncCurrentLocation = "up";
                        FirstPageActivity.this.downloadPresetTo(CommonUtils.getGameDownloadOfficialGamePreset(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue()), CommonUtils.getGameDownloadUrl(bluetoothdevmanager.mOfficialGamePresetList, ((Integer) bluetoothdevmanager.defaultGameId.get(0)).intValue()));
                        if (downLoadDialog != null) {
                            if (downLoadDialog.isShowing())
                                downLoadDialog.dismiss();
                            downLoadDialog = null;
                        }
                        downLoadDialog = new DownLoadDialog(mContext);
                        downLoadDialog.setCancelable(false);
                        downLoadDialog.setCanceledOnTouchOutside(false);
                        downLoadDialog.show();
                    } else {
                        new ToastDialog(mContext, "未知设备，无法同步！").show();
                    }
                    //FirstPageActivity.access$902(FirstPageActivity.presetCloudSyncDialog2.this.cb_cb.isChecked());
                    if (cb_cb.isChecked())
                        kv.encode("auto_sync_preset_tip_key", isHideAutoSyncPresetTip);
                    dismiss();
                }
            });
            this.tv_cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    //FirstPageActivity.access$902(FirstPageActivity.presetCloudSyncDialog2.this.cb_cb.isChecked());
                    if (cb_cb.isChecked())
                        kv.encode("auto_sync_preset_tip_key", FirstPageActivity.isHideAutoSyncPresetTip);
                    dismiss();
                }
            });
        }

        protected void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            setContentView(R.layout.savedialog13);
            getWindow().setLayout(-1, -1);
            getWindow().setFlags(8, 8);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
            setCanceledOnTouchOutside(false);
            init();
        }
    }

    public class syncdingDialog extends AlertDialog {
        private Context mContext;

        public syncdingDialog(Context arg2) {
            super(arg2);
            this.mContext = arg2;
        }

        protected void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            setContentView(R.layout.my_progress_dialog3);
            getWindow().setLayout(-1, -1);
            getWindow().setFlags(8, 8);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
            setCanceledOnTouchOutside(false);
        }
    }
}
