package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.qgbox.R;
import com.qx.qgbox.adapters.Fw763ListItemsAdapter;
import com.qx.qgbox.entitys.FWInfo763;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.Conversion;
import com.qx.qgbox.utils.HttpUrlConfig;
import com.qx.qgbox.utils.MyLog;
import com.qx.qgbox.utils.OkHttpUtil;
import com.qx.qgbox.utils.ProjectFilterConfig;
import com.qx.qgbox.views.CheckingDialog;
import com.qx.qgbox.views.CustomDialog1;
import com.qx.qgbox.views.CustomDialog1.Builder;
import com.qx.qgbox.views.CustomDialog2;
import com.qx.qgbox.views.CustomDialog2.Builder;
import com.qx.qgbox.views.ToastDialog;
import com.tencent.mmkv.MMKV;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateFirmwareActivity763 extends Activity
{
  public static final int DOWN_LOAD_ZIP_FILES_ERROR = 908;
  public static final int DOWN_LOAD_ZIP_FILES_SUCCESS = 907;
  private static final int FILE_BUFFER_SIZE = 262144;
  private static final int HAL_FLASH_WORD_SIZE = 4;
  public static final int MSG_ON_CHECKING_FW_ON_SERVER = 912;
  public static final int MSG_ON_REQUEST_EXTERNAL_STORAGE_PERMISSION = 911;
  public static final int MSG_ON_REQUEST_FW_FAIL = 910;
  public static final int MSG_ON_REQUEST_FW_SUCCESS = 909;
  private static final int MSG_ON_UPDATE = 914;
  public static final int MSG_ON_UPDATE_SUCCESS = 906;
  private static final int OAD_BLOCK_SIZE = 16;
  private static final int OAD_BUFFER_SIZE = 18;
  private static String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private static final int REQUEST_EXTERNAL_STORAGE = 913;
  private static final int SEND_INTERVAL = 10;
  public static final String TAG = "UpdateFirmwareActivity763";
  private static final long TIMER_INTERVAL = 1000L;
  private static UpdateFirmwareActivity763 instance;
  private static CheckingDialog mCheckingDialog;
  private int DelayTimer = 2;
  private int OTAType = 0;
  private Button btn_ignore_version;
  private boolean canGo = false;
  long currentClickTime = 0L;
  private CustomDialog1 customDialog1;
  private CustomDialog2 customDialog2;
  private TextView device_fw_rom_version;
  private TextView device_fw_version;
  private String downLoadUpdateFileUrl = "";
  private TextView downloaded_fw_rom_version;
  private TextView downloaded_fw_version;
  private boolean flagTag = false;
  private ListView fw_list;
  private boolean ifBlockSend = false;
  private String ignoreVersion = "1.01";
  private boolean isIgnoreVersion = false;
  private boolean isOTADone = false;
  private ImageView iv_back;
  private MMKV kv;
  private long lastBlockReq = 0L;
  long lastClickTime = 0L;
  private LinearLayout ll_gp;
  private LinearLayout ll_update_info;
  private LinearLayout ll_update_info_sp;
  private String locale = null;
  private Activity mActivity;
  private long mAlreadyReadCount = 0L;
  private BluetoothAdapter mBluetoothAdapter;
  private bluetoothdevmanager mBluetoothLeService;
  private Context mContext;
  private FWInfo763 mFWInfo763;
  private ArrayList<FWInfo763> mFWInfo763List = new ArrayList();
  private final byte[] mFileBuffer = new byte[262144];
  private ImgHdr mFileImgHdr = new ImgHdr(null);
  private byte[] mFileIndexBuffer;
  private Fw763ListItemsAdapter mFw763ListItemsAdapter;
  private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      BluetoothManager localBluetoothManager = (BluetoothManager)UpdateFirmwareActivity763.this.getSystemService("bluetooth");
      UpdateFirmwareActivity763.access$202(UpdateFirmwareActivity763.this, localBluetoothManager.getAdapter());
      if ("com.example.bluetooth.le.ACTION_DATA_AVAILABLE".equals(paramAnonymousContext))
      {
        paramAnonymousContext = paramAnonymousIntent.getByteArrayExtra("com.example.bluetooth.le.EXTRA_DATA_BYTE");
        paramAnonymousIntent = paramAnonymousIntent.getStringExtra("com.example.bluetooth.le.EXTRA_UUID");
        if (paramAnonymousIntent.equals(bluetoothdevmanager.UUID_BLOCK.toString()))
        {
          UpdateFirmwareActivity763.this.GETOTANotifyData(paramAnonymousContext);
          return;
        }
        if (paramAnonymousIntent.equals(bluetoothdevmanager.UUID_IDENTFY.toString()))
        {
          Toast.makeText(UpdateFirmwareActivity763.this.mContext, "Get Device Version Success", 0).show();
          UpdateFirmwareActivity763.this.GETVersionData(paramAnonymousContext);
        }
      }
      else
      {
        if ("com.example.bluetooth.le.ACTION_DATA_WRITE_FAIL".equals(paramAnonymousContext))
        {
          UpdateFirmwareActivity763.this.setBlockIndex(2);
          return;
        }
        if ("com.example.bluetooth.le.ACTION_DATA_WRITE_SUCCESS".equals(paramAnonymousContext))
          UpdateFirmwareActivity763.this.setBlockIndex(1);
      }
    }
  };
  private final Lock mLock = new ReentrantLock();
  private BluetoothGattCharacteristic mOTAUUID1 = null;
  private BluetoothGattCharacteristic mOTAUUID2 = null;
  private byte[] mOadBuffer = new byte[18];
  private ProgInfo mProgInfo = new ProgInfo(null);
  private boolean mProgramming = false;
  private int mReadyToUpdate = 0;
  private Timer mTimer = null;
  private TimerTask mTimerTask = null;

  @SuppressLint({"HandlerLeak"})
  private Handler myHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 913:
      default:
        return;
      case 914:
        if (CommonUtils.isStringValid(UpdateFirmwareActivity763.this.updateFilePath))
        {
          if (CommonUtils.isFilesExists(UpdateFirmwareActivity763.this.updateFilePath))
          {
            UpdateFirmwareActivity763.this.loadFile(UpdateFirmwareActivity763.this.updateFilePath);
            UpdateFirmwareActivity763.this.customDialog2.setProgress(0);
            UpdateFirmwareActivity763.this.tv_update_fw.setEnabled(false);
            if ((UpdateFirmwareActivity763.this.customDialog2 != null) && (!UpdateFirmwareActivity763.this.customDialog2.isShowing()))
              UpdateFirmwareActivity763.this.customDialog2.show();
            UpdateFirmwareActivity763.this.startProgramming();
            return;
          }
          new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip5)).show();
          return;
        }
        new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip6)).show();
        return;
      case 912:
        UpdateFirmwareActivity763.this.checkFWVersionOnServer();
        return;
      case 911:
        UpdateFirmwareActivity763.this.verifyStoragePermissions(UpdateFirmwareActivity763.this.mActivity);
        return;
      case 910:
        MyLog.i("my_tag", "加载服务器分位列表失败！");
        if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
          UpdateFirmwareActivity763.mCheckingDialog.dismiss();
        UpdateFirmwareActivity763.this.ll_update_info.setVisibility(8);
        UpdateFirmwareActivity763.this.tv_new.setVisibility(0);
        return;
      case 909:
      case 908:
      case 907:
      case 906:
      }
      while (true)
      {
        int i;
        try
        {
          paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.getInt("code") == 1000)
          {
            if (bluetoothdevmanager.devicemode == 0)
            {
              paramAnonymousMessage = paramAnonymousMessage.getJSONArray("data");
              if (paramAnonymousMessage.length() <= 0)
                break label1793;
              i = 0;
              if (i >= paramAnonymousMessage.length())
                break label1793;
              UpdateFirmwareActivity763.this.mFWInfo763List.add(new FWInfo763((JSONObject)paramAnonymousMessage.get(i)));
              i += 1;
              continue;
              if (i >= UpdateFirmwareActivity763.this.mFWInfo763List.size())
                break label1805;
              if (i == 0)
              {
                UpdateFirmwareActivity763.access$1202(UpdateFirmwareActivity763.this, (FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i));
                break label1798;
              }
              if (!CommonUtils.checkFWVersion(((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i)).getVersionCode(), UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode()))
                break label1798;
              UpdateFirmwareActivity763.access$1202(UpdateFirmwareActivity763.this, (FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i));
              break label1798;
              if (i < UpdateFirmwareActivity763.this.mFWInfo763List.size())
              {
                if (UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode().equalsIgnoreCase(((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i)).getVersionCode()))
                {
                  UpdateFirmwareActivity763.this.stateChecked.add(Boolean.valueOf(true));
                  break label1810;
                }
                UpdateFirmwareActivity763.this.stateChecked.add(Boolean.valueOf(false));
                break label1810;
              }
              if ((UpdateFirmwareActivity763.this.ignoreVersion != null) && (!CommonUtils.checkFWVersion(UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode(), UpdateFirmwareActivity763.this.ignoreVersion)))
              {
                UpdateFirmwareActivity763.access$1502(UpdateFirmwareActivity763.this, true);
                UpdateFirmwareActivity763.this.btn_ignore_version.setText(UpdateFirmwareActivity763.this.getString(R.string.fw_list_ignore_version));
              }
              else
              {
                UpdateFirmwareActivity763.access$1502(UpdateFirmwareActivity763.this, false);
                UpdateFirmwareActivity763.this.btn_ignore_version.setText(UpdateFirmwareActivity763.this.getResources().getString(R.string.fw_list_ignore_version_open));
              }
              UpdateFirmwareActivity763.this.mFw763ListItemsAdapter.refresh(UpdateFirmwareActivity763.this.mFWInfo763List, UpdateFirmwareActivity763.this.stateChecked);
              if (UpdateFirmwareActivity763.this.mFWInfo763List.size() > 0)
              {
                paramAnonymousMessage = new Date(System.currentTimeMillis());
                UpdateFirmwareActivity763.this.currentClickTime = paramAnonymousMessage.getTime();
                if (UpdateFirmwareActivity763.this.currentClickTime - UpdateFirmwareActivity763.this.lastClickTime < 3000L)
                  return;
                UpdateFirmwareActivity763.this.lastClickTime = UpdateFirmwareActivity763.this.currentClickTime;
                UpdateFirmwareActivity763.this.tv_new.setVisibility(8);
                UpdateFirmwareActivity763.this.ll_update_info.setVisibility(0);
                UpdateFirmwareActivity763.this.ll_update_info_sp.setVisibility(0);
                UpdateFirmwareActivity763.this.ll_gp.setVisibility(8);
                UpdateFirmwareActivity763.access$2202(UpdateFirmwareActivity763.this, UpdateFirmwareActivity763.this.mFWInfo763.getUrl());
                UpdateFirmwareActivity763.this.delUpdateFile(UpdateFirmwareActivity763.this.updateFilePath);
                OkHttpUtil.downLoadFile(UpdateFirmwareActivity763.this.myHandler, 907, 908, UpdateFirmwareActivity763.this.downLoadUpdateFileUrl, UpdateFirmwareActivity763.this.updateFilePath);
                return;
              }
              if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
                UpdateFirmwareActivity763.mCheckingDialog.dismiss();
              UpdateFirmwareActivity763.this.ll_update_info.setVisibility(8);
              UpdateFirmwareActivity763.this.tv_new.setVisibility(0);
              return;
            }
            paramAnonymousMessage = new JSONObject(paramAnonymousMessage.getString("data"));
            UpdateFirmwareActivity763.access$1202(UpdateFirmwareActivity763.this, new FWInfo763(paramAnonymousMessage));
            if ((CommonUtils.isStringValid(UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode())) && (CommonUtils.isStringValid(UpdateFirmwareActivity763.this.mFWInfo763.getUrl())))
            {
              paramAnonymousMessage = new Date(System.currentTimeMillis());
              UpdateFirmwareActivity763.this.currentClickTime = paramAnonymousMessage.getTime();
              if (UpdateFirmwareActivity763.this.currentClickTime - UpdateFirmwareActivity763.this.lastClickTime < 3000L)
                return;
              UpdateFirmwareActivity763.this.lastClickTime = UpdateFirmwareActivity763.this.currentClickTime;
              UpdateFirmwareActivity763.this.tv_new.setVisibility(8);
              UpdateFirmwareActivity763.this.ll_update_info_sp.setVisibility(8);
              UpdateFirmwareActivity763.this.ll_gp.setVisibility(0);
              paramAnonymousMessage = UpdateFirmwareActivity763.this.tv_version_tip;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip3));
              localStringBuilder.append(":");
              localStringBuilder.append(UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode());
              paramAnonymousMessage.setText(localStringBuilder.toString());
              if ((!UpdateFirmwareActivity763.this.locale.contains("zh_CN")) && (!UpdateFirmwareActivity763.this.locale.contains("zh_HK")) && (!UpdateFirmwareActivity763.this.locale.contains("zh_TW")))
              {
                paramAnonymousMessage = UpdateFirmwareActivity763.this.tv_content;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip4));
                localStringBuilder.append(":\n");
                localStringBuilder.append(UpdateFirmwareActivity763.this.mFWInfo763.getEnglish_content());
                paramAnonymousMessage.setText(localStringBuilder.toString());
              }
              else
              {
                paramAnonymousMessage = UpdateFirmwareActivity763.this.tv_content;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip4));
                localStringBuilder.append(":\n");
                localStringBuilder.append(UpdateFirmwareActivity763.this.mFWInfo763.getContent());
                paramAnonymousMessage.setText(localStringBuilder.toString());
              }
              UpdateFirmwareActivity763.access$2202(UpdateFirmwareActivity763.this, UpdateFirmwareActivity763.this.mFWInfo763.getUrl());
              UpdateFirmwareActivity763.this.delUpdateFile(UpdateFirmwareActivity763.this.updateFilePath);
              OkHttpUtil.downLoadFile(UpdateFirmwareActivity763.this.myHandler, 907, 908, UpdateFirmwareActivity763.this.downLoadUpdateFileUrl, UpdateFirmwareActivity763.this.updateFilePath);
              return;
            }
            if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
              UpdateFirmwareActivity763.mCheckingDialog.dismiss();
            UpdateFirmwareActivity763.this.ll_update_info.setVisibility(8);
            UpdateFirmwareActivity763.this.tv_new.setVisibility(0);
            return;
          }
          if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity763.mCheckingDialog.dismiss();
          UpdateFirmwareActivity763.this.ll_update_info.setVisibility(8);
          UpdateFirmwareActivity763.this.tv_new.setVisibility(0);
          return;
        }
        catch (Exception paramAnonymousMessage)
        {
          paramAnonymousMessage.printStackTrace();
          if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity763.mCheckingDialog.dismiss();
          UpdateFirmwareActivity763.this.ll_update_info.setVisibility(8);
          UpdateFirmwareActivity763.this.tv_new.setVisibility(0);
          return;
        }
        new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.location_list12)).show();
        if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
        {
          UpdateFirmwareActivity763.mCheckingDialog.dismiss();
          return;
          if (CommonUtils.isBinFilePidVidEqualDevice(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.updateFilePath, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID))
          {
            new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip2)).show();
            if ((UpdateFirmwareActivity763.mCheckingDialog != null) && (UpdateFirmwareActivity763.mCheckingDialog.isShowing()))
              UpdateFirmwareActivity763.mCheckingDialog.dismiss();
            UpdateFirmwareActivity763.this.initDeviceInfo();
            return;
          }
          UpdateFirmwareActivity763.this.myHandler.sendEmptyMessage(910);
          return;
          if ((UpdateFirmwareActivity763.this.customDialog2 != null) && (UpdateFirmwareActivity763.this.customDialog2.isShowing()))
            UpdateFirmwareActivity763.this.customDialog2.dismiss();
          FirstPageActivity.setIsUpdateFW(true);
          UpdateFirmwareActivity763.access$2902(UpdateFirmwareActivity763.this, new CustomDialog1.Builder(UpdateFirmwareActivity763.this.mContext).setMessage(R.string.upgrade_success).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              UpdateFirmwareActivity763.this.customDialog1.dismiss();
              if (FirstPageActivity.mUiHandler != null)
                FirstPageActivity.mUiHandler.sendEmptyMessage(22);
              UpdateFirmwareActivity763.this.finish();
            }
          }).create());
          UpdateFirmwareActivity763.this.customDialog1.setCanceledOnTouchOutside(false);
          UpdateFirmwareActivity763.this.customDialog1.show();
        }
        return;
        label1793: i = 0;
        continue;
        label1798: i += 1;
        continue;
        label1805: i = 0;
        continue;
        label1810: i += 1;
      }
    }
  };
  private ArrayList<Boolean> stateChecked = new ArrayList();
  private TextView tv_content;
  private TextView tv_new;
  private Button tv_update_fw;
  private TextView tv_version_tip;
  private String updateFilePath = "";
  private UUID uuid_ota1 = UUID.fromString("f000ffc1-0451-4000-b000-000000000000");
  private UUID uuid_ota2 = UUID.fromString("f000ffc2-0451-4000-b000-000000000000");
  private UUID uuida = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

  private void checkFWVersionOnServer()
  {
    mCheckingDialog.show();
    if ((bluetoothdevmanager.mConnectionState != 0) && (bluetoothdevmanager.devicemode == 0) && (CommonUtils.isStringValid(FirstPageActivity.projectName)))
    {
      OkHttpUtil.get(this.myHandler, HttpUrlConfig.getUrlByPname("0", FirstPageActivity.projectName, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, 1, ProjectFilterConfig.device763Param), 909, 910);
      return;
    }
    if ((bluetoothdevmanager.mConnectionState != 0) && (CommonUtils.isStringValid(FirstPageActivity.projectName)))
    {
      OkHttpUtil.get(this.myHandler, HttpUrlConfig.getUrlByPname("1", FirstPageActivity.projectName, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, ProjectFilterConfig.device763Param), 909, 910);
      return;
    }
    this.myHandler.sendEmptyMessageDelayed(910, 1500L);
  }

  private void displayStats()
  {
    int i = this.mProgInfo.iTimeElapsed / 1000;
    if (i > 0)
    {
      int j = (int)(this.mProgInfo.iBytes / i);
      String str = String.format("Time: %d / %d sec", new Object[] { Integer.valueOf(i), Integer.valueOf((int)((float)(this.mFileImgHdr.len * 4L) / (float)this.mProgInfo.iBytes * i)) });
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(String.format("    Bytes: %d (%d/sec)", new Object[] { Long.valueOf(this.mProgInfo.iBytes), Integer.valueOf(j) }));
      localStringBuilder.toString();
      return;
    }
  }

  public static UpdateFirmwareActivity763 getInstance()
  {
    return instance;
  }

  private void initDeviceInfo()
  {
    this.mBluetoothLeService.writeOTAIdentfy(new byte[] { 0 });
    Iterator localIterator1 = this.mBluetoothLeService.getSupportedGattServices().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((BluetoothGattService)localIterator1.next()).getCharacteristics().iterator();
      while (localIterator2.hasNext())
      {
        BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic)localIterator2.next();
        String str = localBluetoothGattCharacteristic.getUuid().toString();
        if (str.equals(this.uuid_ota1.toString()))
        {
          this.mOTAUUID1 = localBluetoothGattCharacteristic;
          MyLog.i("UpdateFirmwareActivity763", "find one");
          this.mBluetoothLeService.setCharacteristicNotification763(this.mOTAUUID1, true);
        }
        else if (str.equals(this.uuid_ota2.toString()))
        {
          this.mOTAUUID2 = localBluetoothGattCharacteristic;
          MyLog.i("UpdateFirmwareActivity763", "find two");
          this.mBluetoothLeService.setCharacteristicNotification763(this.mOTAUUID2, true);
        }
      }
    }
    if ((loadFileVersion(this.updateFilePath)) && (!this.tv_update_fw.isEnabled()))
      this.tv_update_fw.setEnabled(true);
  }

  private void initDialog()
  {
    this.customDialog2 = new CustomDialog2.Builder(this.mContext).create();
    this.customDialog2.setCanceledOnTouchOutside(false);
  }

  private void initListener()
  {
    this.btn_ignore_version.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = UpdateFirmwareActivity763.this.isIgnoreVersion;
        int i = 0;
        if (bool)
        {
          UpdateFirmwareActivity763.access$1502(UpdateFirmwareActivity763.this, false);
          UpdateFirmwareActivity763.access$1402(UpdateFirmwareActivity763.this, "1.01");
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append("---ignoreVersion = ");
          paramAnonymousView.append(UpdateFirmwareActivity763.this.ignoreVersion);
          MyLog.i("my_tag", paramAnonymousView.toString());
          UpdateFirmwareActivity763.this.kv.encode("ProjectName", FirstPageActivity.projectName);
          UpdateFirmwareActivity763.this.kv.encode(FirstPageActivity.projectName, FirstPageActivity.macAddress);
          UpdateFirmwareActivity763.this.kv.encode(FirstPageActivity.macAddress, true);
          UpdateFirmwareActivity763.this.kv.encode("ignore_version", UpdateFirmwareActivity763.this.ignoreVersion);
          FirstPageActivity.setIsUpdateFW(false);
        }
        else
        {
          UpdateFirmwareActivity763.access$1502(UpdateFirmwareActivity763.this, true);
          while (i < UpdateFirmwareActivity763.this.mFWInfo763List.size())
          {
            if (i == 0)
              UpdateFirmwareActivity763.access$1402(UpdateFirmwareActivity763.this, ((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i)).getVersionCode());
            else if (CommonUtils.checkFWVersion(((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i)).getVersionCode(), UpdateFirmwareActivity763.this.ignoreVersion))
              UpdateFirmwareActivity763.access$1402(UpdateFirmwareActivity763.this, ((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(i)).getVersionCode());
            i += 1;
          }
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append("---ignoreVersion = ");
          paramAnonymousView.append(UpdateFirmwareActivity763.this.ignoreVersion);
          MyLog.i("my_tag", paramAnonymousView.toString());
          UpdateFirmwareActivity763.this.kv.encode("ProjectName", FirstPageActivity.projectName);
          UpdateFirmwareActivity763.this.kv.encode(FirstPageActivity.projectName, FirstPageActivity.macAddress);
          UpdateFirmwareActivity763.this.kv.encode(FirstPageActivity.macAddress, true);
          UpdateFirmwareActivity763.this.kv.encode("ignore_version", UpdateFirmwareActivity763.this.ignoreVersion);
          FirstPageActivity.setIsUpdateFW(true);
        }
        if (UpdateFirmwareActivity763.this.isIgnoreVersion)
        {
          UpdateFirmwareActivity763.this.btn_ignore_version.setText(UpdateFirmwareActivity763.this.getString(R.string.fw_list_ignore_version));
          return;
        }
        UpdateFirmwareActivity763.this.btn_ignore_version.setText(UpdateFirmwareActivity763.this.getResources().getString(R.string.fw_list_ignore_version_open));
      }
    });
    this.tv_update_fw.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new StringBuilder();
        paramAnonymousView.append("----mConnectionState = ");
        paramAnonymousView.append(bluetoothdevmanager.mConnectionState);
        MyLog.i("my_tag", paramAnonymousView.toString());
        if (bluetoothdevmanager.mConnectionState == 0)
        {
          new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip8)).show();
          return;
        }
        if (CommonUtils.isStringValid(UpdateFirmwareActivity763.this.updateFilePath))
        {
          if (CommonUtils.isFilesExists(UpdateFirmwareActivity763.this.updateFilePath))
          {
            UpdateFirmwareActivity763.this.loadFile(UpdateFirmwareActivity763.this.updateFilePath);
            UpdateFirmwareActivity763.this.customDialog2.setProgress(0);
            UpdateFirmwareActivity763.this.tv_update_fw.setEnabled(false);
            if ((UpdateFirmwareActivity763.this.customDialog2 != null) && (!UpdateFirmwareActivity763.this.customDialog2.isShowing()))
              UpdateFirmwareActivity763.this.customDialog2.show();
            UpdateFirmwareActivity763.this.startProgramming();
            return;
          }
          new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip5)).show();
          return;
        }
        new ToastDialog(UpdateFirmwareActivity763.this.mContext, UpdateFirmwareActivity763.this.mContext.getResources().getString(R.string.update_fw_tip6)).show();
      }
    });
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateFirmwareActivity763.this.finish();
      }
    });
  }

  private void initView()
  {
    initDialog();
    this.tv_version_tip = ((TextView)findViewById(R.id.tv_version_tip));
    this.tv_content = ((TextView)findViewById(R.id.tv_content));
    this.tv_update_fw = ((Button)findViewById(R.id.tv_update_fw));
    this.tv_new = ((TextView)findViewById(R.id.tv_new));
    this.iv_back = ((ImageView)findViewById(R.id.iv_back));
    this.ll_update_info_sp = ((LinearLayout)findViewById(R.id.ll_update_info_sp));
    this.btn_ignore_version = ((Button)findViewById(R.id.btn_ignore_version));
    this.ll_update_info = ((LinearLayout)findViewById(R.id.ll_update_info));
    this.ll_gp = ((LinearLayout)findViewById(R.id.ll_gp));
    this.fw_list = ((ListView)findViewById(R.id.fw_list));
    this.mFw763ListItemsAdapter = new Fw763ListItemsAdapter(this.mContext, this.mFWInfo763List, this.stateChecked);
    this.fw_list.setAdapter(this.mFw763ListItemsAdapter);
    this.fw_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        UpdateFirmwareActivity763.this.mFw763ListItemsAdapter.setItemsChecked(paramAnonymousInt);
        if (!UpdateFirmwareActivity763.this.mFWInfo763.getVersionCode().equalsIgnoreCase(((FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(paramAnonymousInt)).getVersionCode()))
        {
          UpdateFirmwareActivity763.access$1202(UpdateFirmwareActivity763.this, (FWInfo763)UpdateFirmwareActivity763.this.mFWInfo763List.get(paramAnonymousInt));
          UpdateFirmwareActivity763.access$2202(UpdateFirmwareActivity763.this, UpdateFirmwareActivity763.this.mFWInfo763.getUrl());
          paramAnonymousAdapterView = new StringBuilder();
          paramAnonymousAdapterView.append("---downLoadUpdateFileUrl = ");
          paramAnonymousAdapterView.append(UpdateFirmwareActivity763.this.downLoadUpdateFileUrl);
          MyLog.i("my_tag", paramAnonymousAdapterView.toString());
          UpdateFirmwareActivity763.this.delUpdateFile(UpdateFirmwareActivity763.this.updateFilePath);
          OkHttpUtil.downLoadFile(UpdateFirmwareActivity763.this.myHandler, 907, 908, UpdateFirmwareActivity763.this.downLoadUpdateFileUrl, UpdateFirmwareActivity763.this.updateFilePath);
        }
      }
    });
    this.device_fw_version = ((TextView)findViewById(R.id.device_fw_version));
    this.device_fw_rom_version = ((TextView)findViewById(R.id.device_fw_rom_version));
    this.downloaded_fw_version = ((TextView)findViewById(R.id.downloaded_fw_version));
    this.downloaded_fw_rom_version = ((TextView)findViewById(R.id.downloaded_fw_rom_version));
  }

  private boolean loadFile(String paramString)
  {
    try
    {
      paramString = this.mContext.getContentResolver().openInputStream(Uri.fromFile(new File(paramString)));
      paramString.read(this.mFileBuffer, 0, this.mFileBuffer.length);
      paramString.close();
      this.mFileImgHdr.ver = Conversion.buildUint16(this.mFileBuffer[5], this.mFileBuffer[4]);
      this.mFileImgHdr.len = Conversion.buildUint16(this.mFileBuffer[7], this.mFileBuffer[6]);
      this.mFileImgHdr.rom_ver = Conversion.buildUint16(this.mFileBuffer[15], this.mFileBuffer[14]);
      System.arraycopy(this.mFileBuffer, 8, this.mFileImgHdr.uid, 0, 4);
      displayStats();
      return false;
    }
    catch (IOException paramString)
    {
    }
    return false;
  }

  private boolean loadFileVersion(String paramString)
  {
    this.mFileIndexBuffer = new byte[16];
    try
    {
      paramString = new FileInputStream(new File(paramString));
      if (paramString.available() > 16)
      {
        paramString.read(this.mFileIndexBuffer, 0, 16);
        paramString.close();
        if (checkBinCorrect())
        {
          paramString = new StringBuilder(5);
          paramString.append(String.format("%02X", new Object[] { Byte.valueOf(this.mFileIndexBuffer[5]) }));
          paramString.append(String.format("%02X", new Object[] { Byte.valueOf(this.mFileIndexBuffer[4]) }));
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("getFileVerion  ");
          localStringBuilder.append(paramString.toString());
          MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
          this.downloaded_fw_version.setText(paramString.toString());
          paramString = new StringBuilder(5);
          paramString.append(String.format("%02X", new Object[] { Byte.valueOf(this.mFileIndexBuffer[15]) }));
          paramString.append(String.format("%02X", new Object[] { Byte.valueOf(this.mFileIndexBuffer[14]) }));
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("getFileVerion222  ");
          localStringBuilder.append(paramString.toString());
          MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
          this.downloaded_fw_rom_version.setText(paramString.toString());
          return true;
        }
        Toast.makeText(this.mContext, "not a correct bin", Toast.LENGTH_SHORT).show();
        return false;
      }
      paramString.close();
      return false;
    }
    catch (IOException paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("IOException ");
      localStringBuilder.append(paramString.toString());
      MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
    }
    return false;
  }

  private static IntentFilter makeGattUpdateIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
    localIntentFilter.addAction("com.example.bluetooth.le.ACTION_DATA_AVAILABLE");
    localIntentFilter.addAction("com.example.bluetooth.le.ACTION_DATA_WRITE_FAIL");
    localIntentFilter.addAction("com.example.bluetooth.le.ACTION_DATA_WRITE_SUCCESS");
    return localIntentFilter;
  }

  private void programBlock()
  {
    if (!this.mProgramming)
      return;
    this.ifBlockSend = true;
    StringBuilder localStringBuilder;
    if (this.mProgInfo.iBlocks < this.mProgInfo.nBlocks)
    {
      this.mOadBuffer = new byte[18];
      this.mOadBuffer[0] = Conversion.loUint16(this.mProgInfo.iBlocks);
      this.mOadBuffer[1] = Conversion.hiUint16(this.mProgInfo.iBlocks);
      System.arraycopy(this.mFileBuffer, (int)this.mProgInfo.iBytes, this.mOadBuffer, 2, 16);
      try
      {
        if ((this.mBluetoothLeService == null) || (!this.canGo) || (!this.mBluetoothLeService.writeOTABlock(this.mOadBuffer)))
          break label196;
        this.canGo = false;
      }
      catch (NullPointerException localNullPointerException1)
      {
        this.mProgramming = false;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("NullPointerException");
        localStringBuilder.append(localNullPointerException1.toString());
        MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
      }
    }
    else
    {
      this.isOTADone = true;
      this.mProgramming = false;
      this.myHandler.sendEmptyMessageDelayed(906, 300L);
    }
    label196: this.ifBlockSend = false;
    if (!this.mProgramming)
      try
      {
        this.mActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            UpdateFirmwareActivity763.this.displayStats();
            UpdateFirmwareActivity763.this.stopProgramming();
          }
        });
        return;
      }
      catch (NullPointerException localNullPointerException2)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("NullPointerException222");
        localStringBuilder.append(localNullPointerException2.toString());
        MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
      }
  }

  private void startProgramming()
  {
    this.mProgramming = true;
    this.DelayTimer = 2;
    byte[] arrayOfByte = new byte[16];
    System.arraycopy(this.mFileBuffer, 0, arrayOfByte, 0, 16);
    StringBuilder localStringBuilder = new StringBuilder(arrayOfByte.length);
    int j = arrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(String.format("%02x ", new Object[] { Byte.valueOf(arrayOfByte[i]) }));
      i += 1;
    }
    this.mBluetoothLeService.writeOTAIdentfy(arrayOfByte);
    this.mProgInfo.reset();
    this.mReadyToUpdate = 1;
    this.canGo = true;
    new Thread(new OadTask(null)).start();
    this.mTimer = new Timer();
    this.mTimerTask = new ProgTimerTask(null);
    this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0L, 1000L);
  }

  private void stopProgramming()
  {
    if (this.mTimer != null)
    {
      this.mTimer.cancel();
      this.mTimer.purge();
    }
    if (this.mTimerTask != null)
      this.mTimerTask.cancel();
    this.mTimerTask = null;
    this.mProgramming = false;
  }

  public void GETOTANotifyData(byte[] paramArrayOfByte)
  {
    long l = Conversion.buildUint16(paramArrayOfByte[1], paramArrayOfByte[0]);
    if ((this.mProgInfo.nBlocks == l) && (this.mProgramming))
    {
      stopProgramming();
      return;
    }
    if ((l == 0L) && (!this.flagTag))
    {
      this.flagTag = true;
      this.mLock.lock();
      this.lastBlockReq = l;
      this.mAlreadyReadCount = l;
      this.mProgInfo.iBlocks = l;
      this.mProgInfo.iBytes = (l * 16L);
      this.mLock.unlock();
      return;
    }
    this.mLock.lock();
    this.lastBlockReq = l;
    this.mAlreadyReadCount = l;
    this.mProgInfo.iBlocks = l;
    this.mProgInfo.iBytes = (l * 16L);
    if (this.DelayTimer < 50)
      this.DelayTimer = 50;
    this.mLock.unlock();
  }

  public void GETVersionData(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(paramArrayOfByte.length);
    localStringBuilder1.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[1]) }));
    localStringBuilder1.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[0]) }));
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("GETVersionData ");
    localStringBuilder2.append(localStringBuilder1.toString());
    MyLog.i("UpdateFirmwareActivity763", localStringBuilder2.toString());
    this.device_fw_version.setText(localStringBuilder1.toString());
    localStringBuilder1 = new StringBuilder(paramArrayOfByte.length);
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("GETVersionData222 ");
    localStringBuilder2.append(String.valueOf(paramArrayOfByte.length));
    MyLog.i("UpdateFirmwareActivity763", localStringBuilder2.toString());
    if (paramArrayOfByte.length == 10)
    {
      localStringBuilder1.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[9]) }));
      localStringBuilder1.append(String.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[8]) }));
    }
    else
    {
      localStringBuilder1.append("FF");
      localStringBuilder1.append("FF");
    }
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("GETVersionData333 ");
    paramArrayOfByte.append(localStringBuilder1.toString());
    MyLog.i("UpdateFirmwareActivity763", paramArrayOfByte.toString());
    this.device_fw_rom_version.setText(localStringBuilder1.toString());
  }

  public boolean checkBinCorrect()
  {
    byte[] arrayOfByte = new byte[4];
    System.arraycopy(this.mFileIndexBuffer, 8, arrayOfByte, 0, 4);
    StringBuilder localStringBuilder = new StringBuilder(5);
    localStringBuilder.append(String.format("%02X ", new Object[] { Byte.valueOf(arrayOfByte[0]) }));
    localStringBuilder.append(String.format("%02X ", new Object[] { Byte.valueOf(arrayOfByte[1]) }));
    localStringBuilder.append(String.format("%02X ", new Object[] { Byte.valueOf(arrayOfByte[2]) }));
    localStringBuilder.append(String.format("%02X", new Object[] { Byte.valueOf(arrayOfByte[3]) }));
    MyLog.i("UpdateFirmwareActivity763", localStringBuilder.toString());
    if ((arrayOfByte[0] == 66) && (arrayOfByte[1] == 66) && (arrayOfByte[2] == 66) && (arrayOfByte[3] == 66))
    {
      this.OTAType = 1;
      return true;
    }
    if ((arrayOfByte[0] == 83) && (arrayOfByte[1] == 83) && (arrayOfByte[2] == 83) && (arrayOfByte[3] == 83))
    {
      this.OTAType = 2;
      return true;
    }
    this.OTAType = 0;
    return false;
  }

  public void delUpdateFile(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists())
      paramString.delete();
  }

  public Handler getMyHandler()
  {
    return this.myHandler;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setFlags(128, 128);
    setContentView(R.layout.activity_update_firmware_763);
    this.mContext = this;
    instance = this;
    this.mActivity = this;
    paramBundle = new StringBuilder();
    paramBundle.append(this.mContext.getApplicationContext().getFilesDir());
    paramBundle.append(File.separator);
    paramBundle.append("ota763.bin");
    this.updateFilePath = paramBundle.toString();
    this.locale = Locale.getDefault().toString();
    this.kv = MMKV.mmkvWithID("MyID", 2);
    this.ignoreVersion = this.kv.decodeString("ignore_version");
    if (this.ignoreVersion == null)
      this.ignoreVersion = "1.01";
    mCheckingDialog = new CheckingDialog(this.mContext);
    mCheckingDialog.setCancelable(false);
    mCheckingDialog.setCanceledOnTouchOutside(false);
    initView();
    if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))
    {
      Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
      finish();
    }
    this.mBluetoothAdapter = ((BluetoothManager)getSystemService("bluetooth")).getAdapter();
    if (this.mBluetoothAdapter == null)
    {
      Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
      finish();
      return;
    }
    this.mBluetoothLeService = bluetoothdevmanager.getService();
    initListener();
    verifyStoragePermissions(this.mActivity);
    registerReceiver(this.mGattUpdateReceiver, makeGattUpdateIntentFilter());
  }

  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.mGattUpdateReceiver);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if (paramInt == 913)
    {
      paramInt = 0;
      int i = 1;
      while (paramInt < paramArrayOfString.length)
      {
        if (paramArrayOfInt[paramInt] == -1)
          i = 0;
        paramInt += 1;
      }
      if (i != 0)
      {
        this.myHandler.sendEmptyMessageDelayed(912, 500L);
        return;
      }
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip1)).show();
      this.myHandler.sendEmptyMessageDelayed(911, 1500L);
    }
  }

  public void setBlockIndex(int paramInt)
  {
    if ((!this.mProgramming) || (paramInt == 1));
    try
    {
      this.mLock.lock();
      this.canGo = true;
      this.mAlreadyReadCount = (this.mProgInfo.iBlocks + 1L);
      ProgInfo localProgInfo = this.mProgInfo;
      localProgInfo.iBlocks += 1L;
      this.mProgInfo.iBytes += 16L;
      this.mLock.unlock();
      this.customDialog2.setProgress((short)(int)(this.mProgInfo.iBlocks * 100L / this.mProgInfo.nBlocks));
      return;
      this.mLock.lock();
      this.canGo = true;
      this.mLock.unlock();
      return;
      label134: MyLog.i("UpdateFirmwareActivity763", localProgInfo.toString());
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      break label134;
    }
  }

  public void verifyStoragePermissions(Activity paramActivity)
  {
    if (ActivityCompat.checkSelfPermission(paramActivity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
    {
      ActivityCompat.requestPermissions(paramActivity, PERMISSIONS_STORAGE, 913);
      return;
    }
    checkFWVersionOnServer();
  }

  private class ImgHdr
  {
    long len = -1L;
    long rom_ver = -1L;
    byte[] uid = new byte[4];
    long ver = -1L;

    private ImgHdr()
    {
    }
  }

  private class OadTask
    implements Runnable
  {
    int x = 0;

    private OadTask()
    {
    }

    public void run()
    {
      while (true)
      {
        if (UpdateFirmwareActivity763.this.mProgramming)
        {
          try
          {
            this.x += 10;
            Thread.sleep(10L);
          }
          catch (InterruptedException localInterruptedException1)
          {
            localInterruptedException1.printStackTrace();
          }
          int i = 0;
          while (true)
          {
            if (i < 4)
              j = 1;
            else
              j = 0;
            if (((j & UpdateFirmwareActivity763.this.mProgramming) == 0) || (!UpdateFirmwareActivity763.this.flagTag))
              break;
            int j = i;
            if (UpdateFirmwareActivity763.this.mReadyToUpdate > 0)
            {
              UpdateFirmwareActivity763.this.mLock.lock();
              UpdateFirmwareActivity763.this.programBlock();
              UpdateFirmwareActivity763.this.mLock.unlock();
              i += 1;
              j = i;
              if (UpdateFirmwareActivity763.this.DelayTimer > 2)
              {
                UpdateFirmwareActivity763.access$4110(UpdateFirmwareActivity763.this);
                j = i;
              }
            }
            try
            {
              this.x += UpdateFirmwareActivity763.this.DelayTimer;
              Thread.sleep(UpdateFirmwareActivity763.this.DelayTimer);
              i = j;
            }
            catch (InterruptedException localInterruptedException2)
            {
              localInterruptedException2.printStackTrace();
              i = j;
            }
          }
          if (this.x < 1000)
            continue;
          this.x %= 1000;
        }
        try
        {
          UpdateFirmwareActivity763.this.mActivity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              UpdateFirmwareActivity763.this.displayStats();
            }
          });
          continue;
          label226: MyLog.i("UpdateFirmwareActivity763", "something wrong 2");
          continue;
          return;
        }
        catch (NullPointerException localNullPointerException)
        {
          break label226;
        }
      }
    }
  }

  private class ProgInfo
  {
    long iBlocks = 0L;
    long iBytes = 0L;
    int iTimeElapsed = 0;
    long nBlocks = 0L;

    private ProgInfo()
    {
    }

    void reset()
    {
      this.iBytes = 0L;
      this.iBlocks = 0L;
      this.iTimeElapsed = 0;
      this.nBlocks = ((short)(int)(UpdateFirmwareActivity763.this.mFileImgHdr.len / 4L));
    }
  }

  private class ProgTimerTask extends TimerTask
  {
    private ProgTimerTask()
    {
    }

    public void run()
    {
      UpdateFirmwareActivity763.ProgInfo localProgInfo = UpdateFirmwareActivity763.this.mProgInfo;
      localProgInfo.iTimeElapsed = ((int)(localProgInfo.iTimeElapsed + 1000L));
    }
  }
}
