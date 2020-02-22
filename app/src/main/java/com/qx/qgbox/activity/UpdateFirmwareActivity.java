package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qx.qgbox.R;
import com.qx.qgbox.adapters.FwListItemsAdapter;
import com.qx.qgbox.entitys.FWInfo;
import com.qx.qgbox.service.DfuService;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.DownLoadAndDecZip;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateFirmwareActivity extends Activity
{
  private static final int BAIDU_READ_PHONE_STATE = 101;
  public static final int DOWN_LOAD_ZIP_FILES_ERROR = 8;
  public static final int DOWN_LOAD_ZIP_FILES_SUCCESS = 4;
  private static String[] LOCATION_GPS = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE" };
  public static final int MSG_ON_CHECKING_FW_ON_SERVER = 914;
  public static final int MSG_ON_REQUEST_ACCESS_FINE_LOCATION = 913;
  public static final int MSG_ON_REQUEST_EXTERNAL_STORAGE_PERMISSION = 911;
  public static final int MSG_ON_REQUEST_FW_INFO_ERROR = 6;
  public static final int MSG_ON_REQUEST_FW_INFO_SUCCESS = 5;
  public static final int MSG_ON_REQUEST_GPS_PERMISSION = 912;
  public static final int MSG_ON_SCANNING_DEVICE = 915;
  private static String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private static final int PRIVATE_CODE = 100;
  public static final int REQUEST_ENABLE_BT = 1;
  private static final int REQUEST_EXTERNAL_STORAGE = 102;
  public static UpdateFirmwareActivity instance;
  private static CheckingDialog mCheckingDialog;
  private Button btn_ignore_version;
  long currentClickTime = 0L;
  private CustomDialog1 customDialog1;
  private CustomDialog2 customDialog2;
  private final DfuProgressListener dfuProgressListener = new DfuProgressListener()
  {
    public void onDeviceConnected(String paramAnonymousString)
    {
    }

    public void onDeviceConnecting(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onDeviceConnecting");
    }

    public void onDeviceDisconnected(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onDeviceDisconnected");
    }

    public void onDeviceDisconnecting(String paramAnonymousString)
    {
      paramAnonymousString = new StringBuilder();
      paramAnonymousString.append("onDeviceDisconnecting");
      paramAnonymousString.append(Build.BRAND);
      MyLog.i("my_tag", paramAnonymousString.toString());
      if ("Xiaomi".equalsIgnoreCase(Build.BRAND))
      {
        if ((UpdateFirmwareActivity.this.pdLoading != null) && (UpdateFirmwareActivity.this.pdLoading.isShowing()))
          UpdateFirmwareActivity.this.pdLoading.dismiss();
        if ((UpdateFirmwareActivity.this.customDialog2 != null) && (UpdateFirmwareActivity.this.customDialog2.isShowing()))
          UpdateFirmwareActivity.this.customDialog2.dismiss();
        if ((UpdateFirmwareActivity.this.customDialog1 != null) && (UpdateFirmwareActivity.this.customDialog1.isShowing()))
          UpdateFirmwareActivity.this.customDialog1.dismiss();
        FirstPageActivity.setIsUpdateFW(true);
        UpdateFirmwareActivity.access$202(UpdateFirmwareActivity.this, new CustomDialog1.Builder(UpdateFirmwareActivity.this.mContext).setMessage(R.string.upgrade_success).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            UpdateFirmwareActivity.this.customDialog1.dismiss();
            if (FirstPageActivity.mUiHandler != null)
              FirstPageActivity.mUiHandler.sendEmptyMessage(22);
            UpdateFirmwareActivity.this.finish();
          }
        }).create());
        UpdateFirmwareActivity.this.customDialog1.setCanceledOnTouchOutside(false);
        UpdateFirmwareActivity.this.customDialog1.show();
      }
    }

    public void onDfuAborted(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onDfuAborted");
      if ((UpdateFirmwareActivity.this.pdLoading != null) && (UpdateFirmwareActivity.this.pdLoading.isShowing()))
        UpdateFirmwareActivity.this.pdLoading.dismiss();
      if ((UpdateFirmwareActivity.this.customDialog1 != null) && (UpdateFirmwareActivity.this.customDialog1.isShowing()))
        UpdateFirmwareActivity.this.customDialog1.dismiss();
      UpdateFirmwareActivity.access$202(UpdateFirmwareActivity.this, new CustomDialog1.Builder(UpdateFirmwareActivity.this.mContext).setTitle(R.string.upgrade_fail).setMessage("onDfuAborted").setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
          UpdateFirmwareActivity.this.customDialog1.dismiss();
        }
      }).create());
      UpdateFirmwareActivity.this.customDialog1.setCanceledOnTouchOutside(false);
      UpdateFirmwareActivity.this.customDialog1.show();
    }

    public void onDfuCompleted(String paramAnonymousString)
    {
      if ((UpdateFirmwareActivity.this.pdLoading != null) && (UpdateFirmwareActivity.this.pdLoading.isShowing()))
        UpdateFirmwareActivity.this.pdLoading.dismiss();
      if ((UpdateFirmwareActivity.this.customDialog2 != null) && (UpdateFirmwareActivity.this.customDialog2.isShowing()))
        UpdateFirmwareActivity.this.customDialog2.dismiss();
      if ((UpdateFirmwareActivity.this.customDialog1 != null) && (UpdateFirmwareActivity.this.customDialog1.isShowing()))
        UpdateFirmwareActivity.this.customDialog1.dismiss();
      FirstPageActivity.setIsUpdateFW(true);
      UpdateFirmwareActivity.access$202(UpdateFirmwareActivity.this, new CustomDialog1.Builder(UpdateFirmwareActivity.this.mContext).setMessage(R.string.upgrade_success).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
          UpdateFirmwareActivity.this.customDialog1.dismiss();
          if (FirstPageActivity.mUiHandler != null)
            FirstPageActivity.mUiHandler.sendEmptyMessage(22);
          UpdateFirmwareActivity.this.finish();
        }
      }).create());
      UpdateFirmwareActivity.this.customDialog1.setCanceledOnTouchOutside(false);
      UpdateFirmwareActivity.this.customDialog1.show();
    }

    public void onDfuProcessStarted(String paramAnonymousString)
    {
    }

    public void onDfuProcessStarting(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onDfuProcessStarted");
    }

    public void onEnablingDfuMode(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onEnablingDfuMode");
    }

    public void onError(String paramAnonymousString1, int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString2)
    {
      if ((UpdateFirmwareActivity.this.pdLoading != null) && (UpdateFirmwareActivity.this.pdLoading.isShowing()))
        UpdateFirmwareActivity.this.pdLoading.dismiss();
      if ((UpdateFirmwareActivity.this.customDialog1 != null) && (UpdateFirmwareActivity.this.customDialog1.isShowing()))
        UpdateFirmwareActivity.this.customDialog1.dismiss();
      UpdateFirmwareActivity.access$202(UpdateFirmwareActivity.this, new CustomDialog1.Builder(UpdateFirmwareActivity.this.mContext).setTitle(R.string.upgrade_fail).setMessage(paramAnonymousString2).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
          UpdateFirmwareActivity.this.customDialog1.dismiss();
        }
      }).create());
      UpdateFirmwareActivity.this.customDialog1.setCanceledOnTouchOutside(false);
      UpdateFirmwareActivity.this.customDialog1.show();
    }

    public void onFirmwareValidating(String paramAnonymousString)
    {
      MyLog.i("my_tag", "onFirmwareValidating");
    }

    public void onProgressChanged(String paramAnonymousString, int paramAnonymousInt1, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if ((UpdateFirmwareActivity.this.pdLoading != null) && (UpdateFirmwareActivity.this.pdLoading.isShowing()))
        UpdateFirmwareActivity.this.pdLoading.dismiss();
      UpdateFirmwareActivity.this.customDialog2.setProgress(paramAnonymousInt1);
      if ((UpdateFirmwareActivity.this.customDialog2 != null) && (!UpdateFirmwareActivity.this.customDialog2.isShowing()))
        UpdateFirmwareActivity.this.customDialog2.show();
    }
  };
  private String downLoadZipPath = "";
  private ListView fw_list;
  private String ignoreVersion = "1.01";
  private boolean isIgnoreVersion = false;
  private ImageView iv_back;
  private MMKV kv;
  long lastClickTime = 0L;
  private LinearLayout ll_gp;
  private LinearLayout ll_update_info;
  private LinearLayout ll_update_info_sp;
  private LocationManager lm;
  String locale = null;
  private Activity mActivity;
  private BluetoothAdapter mBluetoothAdapter;
  private Context mContext;
  private FWInfo mFWInfo;
  private ArrayList<FWInfo> mFWInfoList = new ArrayList();
  private FwListItemsAdapter mFwListItemsAdapter;

  @SuppressLint({"HandlerLeak"})
  private Handler myHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      if (i != 8)
        switch (i)
        {
        default:
          switch (i)
          {
          default:
            return;
          case 914:
            UpdateFirmwareActivity.this.checkFWVersionOnServer();
            return;
          case 913:
            UpdateFirmwareActivity.this.verifyLocationPermissions(UpdateFirmwareActivity.this.mActivity);
            return;
          case 912:
            paramAnonymousMessage = new Intent();
            paramAnonymousMessage.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
            UpdateFirmwareActivity.this.startActivityForResult(paramAnonymousMessage, 100);
            return;
          case 911:
          }
          UpdateFirmwareActivity.this.verifyStoragePermissions(UpdateFirmwareActivity.this.mActivity);
          return;
        case 6:
          if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity.mCheckingDialog.dismiss();
          UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.GONE);
          UpdateFirmwareActivity.this.tv_new.setVisibility(View.VISIBLE);
          return;
        case 5:
        case 4:
        }
      while (true)
      {
        try
        {
          paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
          if (paramAnonymousMessage.getInt("code") == 1000)
          {
            if (bluetoothdevmanager.devicemode == 0)
            {
              paramAnonymousMessage = paramAnonymousMessage.getJSONArray("data");
              if (paramAnonymousMessage.length() <= 0)
                break label1491;
              i = 0;
              if (i >= paramAnonymousMessage.length())
                break label1491;
              UpdateFirmwareActivity.this.mFWInfoList.add(new FWInfo((JSONObject)paramAnonymousMessage.get(i)));
              i += 1;
              continue;
              if (i >= UpdateFirmwareActivity.this.mFWInfoList.size())
                break label1503;
              if (i == 0)
              {
                UpdateFirmwareActivity.access$802(UpdateFirmwareActivity.this, (FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i));
                break label1496;
              }
              if (!CommonUtils.checkFWVersion(((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i)).getVersionCode(), UpdateFirmwareActivity.this.mFWInfo.getVersionCode()))
                break label1496;
              UpdateFirmwareActivity.access$802(UpdateFirmwareActivity.this, (FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i));
              break label1496;
              if (i < UpdateFirmwareActivity.this.mFWInfoList.size())
              {
                if (UpdateFirmwareActivity.this.mFWInfo.getVersionCode().equalsIgnoreCase(((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i)).getVersionCode()))
                {
                  UpdateFirmwareActivity.this.stateChecked.add(Boolean.valueOf(true));
                  break label1508;
                }
                UpdateFirmwareActivity.this.stateChecked.add(Boolean.valueOf(false));
                break label1508;
              }
              if ((UpdateFirmwareActivity.this.ignoreVersion != null) && (!CommonUtils.checkFWVersion(UpdateFirmwareActivity.this.mFWInfo.getVersionCode(), UpdateFirmwareActivity.this.ignoreVersion)))
              {
                UpdateFirmwareActivity.access$1102(UpdateFirmwareActivity.this, true);
                UpdateFirmwareActivity.this.btn_ignore_version.setText(UpdateFirmwareActivity.this.getString(R.string.fw_list_ignore_version));
              }
              else
              {
                UpdateFirmwareActivity.access$1102(UpdateFirmwareActivity.this, false);
                UpdateFirmwareActivity.this.btn_ignore_version.setText(UpdateFirmwareActivity.this.getResources().getString(R.string.fw_list_ignore_version_open));
              }
              UpdateFirmwareActivity.this.mFwListItemsAdapter.refresh(UpdateFirmwareActivity.this.mFWInfoList, UpdateFirmwareActivity.this.stateChecked);
              if (UpdateFirmwareActivity.this.mFWInfoList.size() > 0)
              {
                paramAnonymousMessage = new Date(System.currentTimeMillis());
                UpdateFirmwareActivity.this.currentClickTime = paramAnonymousMessage.getTime();
                if (UpdateFirmwareActivity.this.currentClickTime - UpdateFirmwareActivity.this.lastClickTime < 3000L)
                  return;
                UpdateFirmwareActivity.this.lastClickTime = UpdateFirmwareActivity.this.currentClickTime;
                UpdateFirmwareActivity.this.tv_new.setVisibility(View.GONE);
                UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.VISIBLE);
                UpdateFirmwareActivity.this.ll_update_info_sp.setVisibility(View.VISIBLE);
                UpdateFirmwareActivity.this.ll_gp.setVisibility(View.GONE);
                UpdateFirmwareActivity.access$1802(UpdateFirmwareActivity.this, UpdateFirmwareActivity.this.mFWInfo.getUrl());
                new DownLoadAndDecZip(UpdateFirmwareActivity.this.downLoadZipPath, UpdateFirmwareActivity.this.saveZipPath).execute(new Void[0]);
                return;
              }
              if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
                UpdateFirmwareActivity.mCheckingDialog.dismiss();
              UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.GONE);
              UpdateFirmwareActivity.this.tv_new.setVisibility(View.VISIBLE);
              return;
            }
            paramAnonymousMessage = new JSONObject(paramAnonymousMessage.getString("data"));
            UpdateFirmwareActivity.access$802(UpdateFirmwareActivity.this, new FWInfo(paramAnonymousMessage));
            if ((CommonUtils.isStringValid(UpdateFirmwareActivity.this.mFWInfo.getVersionCode())) && (CommonUtils.isStringValid(UpdateFirmwareActivity.this.mFWInfo.getUrl())))
            {
              paramAnonymousMessage = new Date(System.currentTimeMillis());
              UpdateFirmwareActivity.this.currentClickTime = paramAnonymousMessage.getTime();
              if (UpdateFirmwareActivity.this.currentClickTime - UpdateFirmwareActivity.this.lastClickTime < 3000L)
                return;
              UpdateFirmwareActivity.this.lastClickTime = UpdateFirmwareActivity.this.currentClickTime;
              UpdateFirmwareActivity.this.tv_new.setVisibility(View.GONE);
              UpdateFirmwareActivity.this.ll_update_info_sp.setVisibility(View.GONE);
              UpdateFirmwareActivity.this.ll_gp.setVisibility(View.VISIBLE);
              paramAnonymousMessage = UpdateFirmwareActivity.this.tv_version_tip;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip3));
              localStringBuilder.append(":");
              localStringBuilder.append(UpdateFirmwareActivity.this.mFWInfo.getVersionCode());
              paramAnonymousMessage.setText(localStringBuilder.toString());
              if ((!UpdateFirmwareActivity.this.locale.contains("zh_CN")) && (!UpdateFirmwareActivity.this.locale.contains("zh_HK")) && (!UpdateFirmwareActivity.this.locale.contains("zh_TW")))
              {
                paramAnonymousMessage = UpdateFirmwareActivity.this.tv_content;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip4));
                localStringBuilder.append(":\n");
                localStringBuilder.append(UpdateFirmwareActivity.this.mFWInfo.getEnglish_content());
                paramAnonymousMessage.setText(localStringBuilder.toString());
              }
              else
              {
                paramAnonymousMessage = UpdateFirmwareActivity.this.tv_content;
                localStringBuilder = new StringBuilder();
                localStringBuilder.append(UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip4));
                localStringBuilder.append(":\n");
                localStringBuilder.append(UpdateFirmwareActivity.this.mFWInfo.getContent());
                paramAnonymousMessage.setText(localStringBuilder.toString());
              }
              UpdateFirmwareActivity.access$1802(UpdateFirmwareActivity.this, UpdateFirmwareActivity.this.mFWInfo.getUrl());
              new DownLoadAndDecZip(UpdateFirmwareActivity.this.downLoadZipPath, UpdateFirmwareActivity.this.saveZipPath).execute(new Void[0]);
              return;
            }
            if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
              UpdateFirmwareActivity.mCheckingDialog.dismiss();
            UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.GONE);
            UpdateFirmwareActivity.this.tv_new.setVisibility(View.VISIBLE);
            return;
          }
          if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity.mCheckingDialog.dismiss();
          UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.GONE);
          UpdateFirmwareActivity.this.tv_new.setVisibility(View.VISIBLE);
          return;
        }
        catch (Exception paramAnonymousMessage)
        {
          paramAnonymousMessage.printStackTrace();
          if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity.mCheckingDialog.dismiss();
          UpdateFirmwareActivity.this.ll_update_info.setVisibility(View.GONE);
          UpdateFirmwareActivity.this.tv_new.setVisibility(View.VISIBLE);
          return;
        }
        new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip2)).show();
        if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
        {
          UpdateFirmwareActivity.mCheckingDialog.dismiss();
          return;
          new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.location_list12)).show();
          if ((UpdateFirmwareActivity.mCheckingDialog != null) && (UpdateFirmwareActivity.mCheckingDialog.isShowing()))
            UpdateFirmwareActivity.mCheckingDialog.dismiss();
        }
        return;
        label1491: i = 0;
        continue;
        label1496: i += 1;
        continue;
        label1503: i = 0;
        continue;
        label1508: i += 1;
      }
    }
  };
  private ProgressDialog pdLoading;
  private String saveZipPath = "/sdcard/";
  private DfuServiceInitiator starter;
  private ArrayList<Boolean> stateChecked = new ArrayList();
  private TextView tv_content;
  private TextView tv_new;
  private Button tv_update_fw;
  private TextView tv_version_tip;
  private String updateZipPath = "/sdcard/dfu.zip";

  private void checkFWVersionOnServer()
  {
    mCheckingDialog.show();
    if ((bluetoothdevmanager.mConnectionState != 0) && (bluetoothdevmanager.devicemode == 0) && (CommonUtils.isStringValid(FirstPageActivity.projectName)))
    {
      OkHttpUtil.get(this.myHandler, HttpUrlConfig.getUrlByPname("0", FirstPageActivity.projectName, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, 1, ProjectFilterConfig.device759Param), 5, 6);
      return;
    }
    if ((bluetoothdevmanager.mConnectionState != 0) && (CommonUtils.isStringValid(FirstPageActivity.projectName)))
    {
      OkHttpUtil.get(this.myHandler, HttpUrlConfig.getUrlByPname("1", FirstPageActivity.projectName, bluetoothdevmanager.devicePID, bluetoothdevmanager.deviceVID, ProjectFilterConfig.device759Param), 5, 6);
      return;
    }
    this.myHandler.sendEmptyMessageDelayed(6, 1500L);
  }

  public static UpdateFirmwareActivity getInstance()
  {
    return instance;
  }

  private void initDialog()
  {
    this.customDialog2 = new CustomDialog2.Builder(this.mContext).create();
    this.customDialog2.setCanceledOnTouchOutside(false);
    this.pdLoading = new ProgressDialog(this.mContext);
    this.pdLoading.setMessage(getResources().getString(R.string.loading));
    this.pdLoading.setCanceledOnTouchOutside(false);
  }

  private void initListener()
  {
    this.btn_ignore_version.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = UpdateFirmwareActivity.this.isIgnoreVersion;
        int i = 0;
        if (bool)
        {
          UpdateFirmwareActivity.access$1102(UpdateFirmwareActivity.this, false);
          UpdateFirmwareActivity.access$1002(UpdateFirmwareActivity.this, "1.01");
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append("---ignoreVersion = ");
          paramAnonymousView.append(UpdateFirmwareActivity.this.ignoreVersion);
          MyLog.i("my_tag", paramAnonymousView.toString());
          UpdateFirmwareActivity.this.kv.encode("ProjectName", FirstPageActivity.projectName);
          UpdateFirmwareActivity.this.kv.encode(FirstPageActivity.projectName, FirstPageActivity.macAddress);
          UpdateFirmwareActivity.this.kv.encode(FirstPageActivity.macAddress, true);
          UpdateFirmwareActivity.this.kv.encode("ignore_version", UpdateFirmwareActivity.this.ignoreVersion);
          FirstPageActivity.setIsUpdateFW(false);
        }
        else
        {
          UpdateFirmwareActivity.access$1102(UpdateFirmwareActivity.this, true);
          while (i < UpdateFirmwareActivity.this.mFWInfoList.size())
          {
            if (i == 0)
              UpdateFirmwareActivity.access$1002(UpdateFirmwareActivity.this, ((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i)).getVersionCode());
            else if (CommonUtils.checkFWVersion(((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i)).getVersionCode(), UpdateFirmwareActivity.this.ignoreVersion))
              UpdateFirmwareActivity.access$1002(UpdateFirmwareActivity.this, ((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(i)).getVersionCode());
            i += 1;
          }
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append("---ignoreVersion = ");
          paramAnonymousView.append(UpdateFirmwareActivity.this.ignoreVersion);
          MyLog.i("my_tag", paramAnonymousView.toString());
          UpdateFirmwareActivity.this.kv.encode("ProjectName", FirstPageActivity.projectName);
          UpdateFirmwareActivity.this.kv.encode(FirstPageActivity.projectName, FirstPageActivity.macAddress);
          UpdateFirmwareActivity.this.kv.encode(FirstPageActivity.macAddress, true);
          UpdateFirmwareActivity.this.kv.encode("ignore_version", UpdateFirmwareActivity.this.ignoreVersion);
          FirstPageActivity.setIsUpdateFW(true);
        }
        if (UpdateFirmwareActivity.this.isIgnoreVersion)
        {
          UpdateFirmwareActivity.this.btn_ignore_version.setText(UpdateFirmwareActivity.this.getString(R.string.fw_list_ignore_version));
          return;
        }
        UpdateFirmwareActivity.this.btn_ignore_version.setText(UpdateFirmwareActivity.this.getResources().getString(R.string.fw_list_ignore_version_open));
      }
    });
    this.tv_update_fw.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new StringBuilder();
        paramAnonymousView.append("----mDeviceAddress = ");
        paramAnonymousView.append(FirstPageActivity.macAddress);
        MyLog.i("my_tag", paramAnonymousView.toString());
        if (!CommonUtils.isStringValid(FirstPageActivity.macAddress))
        {
          new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip7)).show();
          return;
        }
        if (bluetoothdevmanager.mConnectionState == 0)
        {
          new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip8)).show();
          return;
        }
        if (CommonUtils.isStringValid(UpdateFirmwareActivity.this.updateZipPath))
        {
          if (CommonUtils.isFilesExists(UpdateFirmwareActivity.this.updateZipPath))
          {
            UpdateFirmwareActivity.access$202(UpdateFirmwareActivity.this, new CustomDialog1.Builder(UpdateFirmwareActivity.this.mContext).setMessage(R.string.update_firmware_now).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                if ((UpdateFirmwareActivity.this.pdLoading != null) && (!UpdateFirmwareActivity.this.pdLoading.isShowing()))
                  UpdateFirmwareActivity.this.pdLoading.show();
                UpdateFirmwareActivity.access$2402(UpdateFirmwareActivity.this, new DfuServiceInitiator(FirstPageActivity.macAddress).setDeviceName("Smart Lock").setKeepBond(false).setPacketsReceiptNotificationsEnabled(true).setPacketsReceiptNotificationsValue(10));
                UpdateFirmwareActivity.this.starter.setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
                UpdateFirmwareActivity.this.starter.setZip(UpdateFirmwareActivity.this.updateZipPath);
                if (Build.VERSION.SDK_INT >= 26)
                  DfuServiceInitiator.createDfuNotificationChannel(UpdateFirmwareActivity.this.mContext);
                UpdateFirmwareActivity.this.starter.start(UpdateFirmwareActivity.this.mContext, DfuService.class);
                UpdateFirmwareActivity.this.customDialog1.dismiss();
              }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                UpdateFirmwareActivity.this.customDialog1.dismiss();
              }
            }).create());
            UpdateFirmwareActivity.this.customDialog1.setCanceledOnTouchOutside(false);
            UpdateFirmwareActivity.this.customDialog1.show();
            return;
          }
          new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip5)).show();
          return;
        }
        new ToastDialog(UpdateFirmwareActivity.this.mContext, UpdateFirmwareActivity.this.mContext.getResources().getString(R.string.update_fw_tip6)).show();
      }
    });
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateFirmwareActivity.this.finish();
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
    this.mFwListItemsAdapter = new FwListItemsAdapter(this.mContext, this.mFWInfoList, this.stateChecked);
    this.fw_list.setAdapter(this.mFwListItemsAdapter);
    this.fw_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        UpdateFirmwareActivity.this.mFwListItemsAdapter.setItemsChecked(paramAnonymousInt);
        if (!UpdateFirmwareActivity.this.mFWInfo.getVersionCode().equalsIgnoreCase(((FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(paramAnonymousInt)).getVersionCode()))
        {
          UpdateFirmwareActivity.access$802(UpdateFirmwareActivity.this, (FWInfo)UpdateFirmwareActivity.this.mFWInfoList.get(paramAnonymousInt));
          UpdateFirmwareActivity.access$1802(UpdateFirmwareActivity.this, UpdateFirmwareActivity.this.mFWInfo.getUrl());
          paramAnonymousAdapterView = new StringBuilder();
          paramAnonymousAdapterView.append("---downLoadZipPath = ");
          paramAnonymousAdapterView.append(UpdateFirmwareActivity.this.downLoadZipPath);
          MyLog.i("my_tag", paramAnonymousAdapterView.toString());
          new DownLoadAndDecZip(UpdateFirmwareActivity.this.downLoadZipPath, UpdateFirmwareActivity.this.saveZipPath).execute(new Void[0]);
        }
      }
    });
  }

  public Handler getMyHandler()
  {
    return this.myHandler;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 100)
    {
      if (this.lm.isProviderEnabled("gps"))
      {
        if (ContextCompat.checkSelfPermission(this.mActivity, "android.permission.ACCESS_FINE_LOCATION") != 0)
        {
          ActivityCompat.requestPermissions(this.mActivity, LOCATION_GPS, 101);
          return;
        }
        this.myHandler.sendEmptyMessageDelayed(915, 500L);
        return;
      }
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip2)).show();
      this.myHandler.sendEmptyMessageDelayed(912, 1500L);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_update_firmware);
    this.mContext = this;
    instance = this;
    this.mActivity = this;
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
    initListener();
    verifyStoragePermissions(this.mActivity);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
    DfuServiceListenerHelper.unregisterProgressListener(this, this.dfuProgressListener);
  }

  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    int k = 1;
    int i;
    if (paramInt == 102)
    {
      i = 0;
      int j = 1;
      while (i < paramArrayOfString.length)
      {
        if (paramArrayOfInt[i] == -1)
          j = 0;
        i += 1;
      }
      if (j != 0)
      {
        this.myHandler.sendEmptyMessageDelayed(914, 500L);
      }
      else
      {
        new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip1)).show();
        this.myHandler.sendEmptyMessageDelayed(911, 1500L);
      }
    }
    if (paramInt == 101)
    {
      paramInt = 0;
      i = k;
      while (paramInt < paramArrayOfString.length)
      {
        if (paramArrayOfInt[paramInt] == -1)
          i = 0;
        paramInt += 1;
      }
      if (i != 0)
      {
        this.myHandler.sendEmptyMessageDelayed(915, 500L);
        return;
      }
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip3)).show();
      this.myHandler.sendEmptyMessageDelayed(913, 1500L);
    }
  }

  protected void onResume()
  {
    super.onResume();
    if ((!this.mBluetoothAdapter.isEnabled()) && (!this.mBluetoothAdapter.isEnabled()))
      startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
    DfuServiceListenerHelper.registerProgressListener(this, this.dfuProgressListener);
  }

  public void verifyLocationPermissions(Activity paramActivity)
  {
    Activity localActivity = this.mActivity;
    this.lm = ((LocationManager)paramActivity.getSystemService("location"));
    if (this.lm.isProviderEnabled("gps"))
    {
      if (ContextCompat.checkSelfPermission(paramActivity, "android.permission.ACCESS_FINE_LOCATION") != 0)
      {
        ActivityCompat.requestPermissions(paramActivity, LOCATION_GPS, 101);
        return;
      }
      this.myHandler.sendEmptyMessageDelayed(915, 500L);
      return;
    }
    new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip2)).show();
    paramActivity = new Intent();
    paramActivity.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
    startActivityForResult(paramActivity, 100);
  }

  public void verifyStoragePermissions(Activity paramActivity)
  {
    if (ActivityCompat.checkSelfPermission(paramActivity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
    {
      ActivityCompat.requestPermissions(paramActivity, PERMISSIONS_STORAGE, 102);
      return;
    }
    checkFWVersionOnServer();
  }
}
