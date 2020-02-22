package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.download.DownLoadApk;
import com.qx.qgbox.download.NewDownLoadAPK;
import com.qx.qgbox.entitys.AppInfo;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.HttpUtil;
import com.qx.qgbox.views.CheckingDialog;
import com.qx.qgbox.views.MyLoadDialog;
import com.qx.qgbox.views.ToastDialog;
import java.io.File;
import java.util.Locale;
import org.json.JSONObject;

public class UpdateAppActivity extends Activity
{
  public static final int DOWN_LOAD_APK_FILES_SUCCESS = 0;
  public static final int MSG_ON_CHECKING_APP_ON_SERVER = 3;
  public static final int MSG_ON_REQUEST_APP_INFO_ERROR = 2;
  public static final int MSG_ON_REQUEST_APP_INFO_SUCCESS = 1;
  public static final int MSG_ON_REQUEST_EXTERNAL_STORAGE_PERMISSION = 4;
  private static String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };
  private static final int REQUEST_EXTERNAL_STORAGE = 102;
  public static UpdateAppActivity instance;
  private static MyLoadDialog loadDialog;
  private static AppInfo mAppInfo;
  private static CheckingDialog mCheckingDialog;
  private String downLoadPath = null;
  private ImageView iv_back;
  private LinearLayout ll_update;
  private String localPath = "/sdcard/ShootingPlush.apk";
  String locale = null;
  private Activity mActivity;
  private Context mContext;

  @SuppressLint({"HandlerLeak"})
  private Handler myHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 4:
        UpdateAppActivity.this.verifyStoragePermissions(UpdateAppActivity.this.mActivity);
        return;
      case 3:
        UpdateAppActivity.this.checkAPPVersionOnServer();
        return;
      case 2:
        UpdateAppActivity.this.tv_new.setText(UpdateAppActivity.this.mContext.getResources().getString(R.string.update_app_tip1));
        UpdateAppActivity.this.ll_update.setVisibility(View.GONE);
        UpdateAppActivity.this.tv_new.setVisibility(View.VISIBLE);
        if ((UpdateAppActivity.mCheckingDialog != null) && (UpdateAppActivity.mCheckingDialog.isShowing()))
        {
          UpdateAppActivity.mCheckingDialog.dismiss();
          return;
        }
        break;
      case 1:
      case 0:
      }
      try
      {
        paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
        if (paramAnonymousMessage.getInt("code") == 1000)
        {
          UpdateAppActivity.access$302(new AppInfo(new JSONObject(paramAnonymousMessage.getString("data"))));
          if (UpdateAppActivity.mAppInfo.getVersionCode() > CommonUtils.getLocalVersion(UpdateAppActivity.this.mContext))
          {
            UpdateAppActivity.access$402(UpdateAppActivity.this, UpdateAppActivity.mAppInfo.getUrl());
            StringBuilder localStringBuilder;
            if (UpdateAppActivity.this.locale.contains("zh_CN"))
            {
              paramAnonymousMessage = UpdateAppActivity.this.tv_update_info;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(UpdateAppActivity.mAppInfo.getContent());
              paramAnonymousMessage.setText(localStringBuilder.toString());
            }
            else
            {
              paramAnonymousMessage = UpdateAppActivity.this.tv_update_info;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(UpdateAppActivity.mAppInfo.getEnglish_content());
              paramAnonymousMessage.setText(localStringBuilder.toString());
            }
            UpdateAppActivity.this.ll_update.setVisibility(View.VISIBLE);
            UpdateAppActivity.this.tv_new.setVisibility(View.GONE);
          }
          else
          {
            UpdateAppActivity.this.ll_update.setVisibility(View.GONE);
            UpdateAppActivity.this.tv_new.setVisibility(View.VISIBLE);
          }
        }
        else
        {
          UpdateAppActivity.this.tv_new.setText(UpdateAppActivity.this.mContext.getResources().getString(R.string.update_app_tip1));
          UpdateAppActivity.this.ll_update.setVisibility(View.GONE);
          UpdateAppActivity.this.tv_new.setVisibility(View.VISIBLE);
          break label464;
          label416: UpdateAppActivity.this.tv_new.setText(UpdateAppActivity.this.mContext.getResources().getString(R.string.update_app_tip1));
          UpdateAppActivity.this.ll_update.setVisibility(View.GONE);
          UpdateAppActivity.this.tv_new.setVisibility(View.VISIBLE);
        }
        label464: if ((UpdateAppActivity.mCheckingDialog != null) && (UpdateAppActivity.mCheckingDialog.isShowing()))
        {
          UpdateAppActivity.mCheckingDialog.dismiss();
          return;
          if ((UpdateAppActivity.loadDialog != null) && (UpdateAppActivity.loadDialog.isShowing()))
            UpdateAppActivity.loadDialog.dismiss();
          DownLoadApk.install(UpdateAppActivity.this.mContext, new File(UpdateAppActivity.this.localPath));
          UpdateAppActivity.this.finish();
        }
        return;
      }
      catch (Exception paramAnonymousMessage)
      {
        break label416;
      }
    }
  };
  private TextView tv_new;
  private TextView tv_update_app;
  private TextView tv_update_info;

  private void checkAPPVersionOnServer()
  {
    mCheckingDialog.show();
    HttpUtil.HttpURLConnectionGet(this.myHandler, "http://shootingplus.com.cn/shootingplus/open/games/getAppVersionInfo?type=0", 1, 2);
  }

  public static UpdateAppActivity getInstance()
  {
    return instance;
  }

  private void initListener()
  {
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateAppActivity.this.finish();
      }
    });
    this.tv_update_app.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CommonUtils.isStringValid(UpdateAppActivity.this.downLoadPath))
        {
          if (UpdateAppActivity.loadDialog != null)
          {
            if (UpdateAppActivity.loadDialog.isShowing())
              UpdateAppActivity.loadDialog.dismiss();
            UpdateAppActivity.access$002(null);
          }
          UpdateAppActivity.access$002(new MyLoadDialog(UpdateAppActivity.this.mContext));
          UpdateAppActivity.loadDialog.setCancelable(false);
          UpdateAppActivity.loadDialog.setCanceledOnTouchOutside(false);
          UpdateAppActivity.loadDialog.show();
          new NewDownLoadAPK(UpdateAppActivity.this.downLoadPath, UpdateAppActivity.this.localPath).execute(new Void[0]);
          return;
        }
        new ToastDialog(UpdateAppActivity.this.mContext, UpdateAppActivity.this.mContext.getResources().getString(R.string.update_app_tip2)).show();
      }
    });
  }

  private void initView()
  {
    this.iv_back = ((ImageView)findViewById(R.id.iv_back));
    this.tv_update_app = ((TextView)findViewById(R.id.tv_update_app));
    this.tv_update_info = ((TextView)findViewById(R.id.tv_update_info));
    this.tv_new = ((TextView)findViewById(R.id.tv_new));
    this.ll_update = ((LinearLayout)findViewById(R.id.ll_update));
    this.tv_new.setVisibility(View.GONE);
    this.ll_update.setVisibility(View.GONE);
  }

  public Handler getMyHandler()
  {
    return this.myHandler;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_help);
    this.mContext = this;
    instance = this;
    this.mActivity = this;
    this.locale = Locale.getDefault().toString();
    mCheckingDialog = new CheckingDialog(this.mContext);
    mCheckingDialog.setCancelable(false);
    mCheckingDialog.setCanceledOnTouchOutside(false);
    initView();
    initListener();
    verifyStoragePermissions(this.mActivity);
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
    if (paramInt == 102)
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
        this.myHandler.sendEmptyMessageDelayed(3, 500L);
        return;
      }
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.maneactivity_tip1)).show();
      this.myHandler.sendEmptyMessageDelayed(4, 1500L);
    }
  }

  public void verifyStoragePermissions(Activity paramActivity)
  {
    if (ActivityCompat.checkSelfPermission(paramActivity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
    {
      ActivityCompat.requestPermissions(paramActivity, PERMISSIONS_STORAGE, 102);
      return;
    }
    checkAPPVersionOnServer();
  }
}
