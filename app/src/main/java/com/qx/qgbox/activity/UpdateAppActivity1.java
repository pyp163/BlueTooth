package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.AppInfo;
import com.qx.qgbox.utils.AppDownloadManager;
import com.qx.qgbox.utils.AppDownloadManager.OnUpdateListener;
import com.qx.qgbox.utils.CommonUtils;
import com.qx.qgbox.utils.HttpUtil;
import com.qx.qgbox.views.CheckingDialog;
import com.qx.qgbox.views.LoadingAPPDialog;
import com.qx.qgbox.views.LoadingAPPDialog.Builder;
import com.qx.qgbox.views.ToastDialog;
import java.util.Locale;
import org.json.JSONObject;

public class UpdateAppActivity1 extends Activity
{
  public static final int MSG_ON_REQUEST_APP_INFO_ERROR = 2;
  public static final int MSG_ON_REQUEST_APP_INFO_SUCCESS = 1;
  public static UpdateAppActivity1 instance;
  private static AppInfo mAppInfo;
  private static CheckingDialog mCheckingDialog;
  private String downLoadPath = null;
  private ImageView iv_back;
  private LinearLayout ll_update;
  private String localPath = "/sdcard/ShootingPlush.apk";
  String locale = null;
  private Activity mActivity;
  private AppDownloadManager mAppDownloadManager = null;
  private Context mContext;
  private LoadingAPPDialog mLoadingAPPDialog;

  @SuppressLint({"HandlerLeak"})
  private Handler myHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 2:
        UpdateAppActivity1.this.tv_new.setText(UpdateAppActivity1.this.mContext.getResources().getString(R.string.update_app_tip1));
        UpdateAppActivity1.this.ll_update.setVisibility(View.GONE);
        UpdateAppActivity1.this.tv_new.setVisibility(View.VISIBLE);
        if ((UpdateAppActivity1.mCheckingDialog != null) && (UpdateAppActivity1.mCheckingDialog.isShowing()))
        {
          UpdateAppActivity1.mCheckingDialog.dismiss();
          return;
        }
        break;
      case 1:
      }
      try
      {
        paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
        if (paramAnonymousMessage.getInt("code") == 1000)
        {
          UpdateAppActivity1.access$002(new AppInfo(new JSONObject(paramAnonymousMessage.getString("data"))));
          if (UpdateAppActivity1.mAppInfo.getVersionCode() > CommonUtils.getLocalVersion(UpdateAppActivity1.this.mContext))
          {
            UpdateAppActivity1.access$202(UpdateAppActivity1.this, UpdateAppActivity1.mAppInfo.getUrl());
            StringBuilder localStringBuilder;
            if (UpdateAppActivity1.this.locale.contains("zh_CN"))
            {
              paramAnonymousMessage = UpdateAppActivity1.this.tv_update_info;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(UpdateAppActivity1.mAppInfo.getContent());
              paramAnonymousMessage.setText(localStringBuilder.toString());
            }
            else
            {
              paramAnonymousMessage = UpdateAppActivity1.this.tv_update_info;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("");
              localStringBuilder.append(UpdateAppActivity1.mAppInfo.getEnglish_content());
              paramAnonymousMessage.setText(localStringBuilder.toString());
            }
            UpdateAppActivity1.this.ll_update.setVisibility(View.VISIBLE);
            UpdateAppActivity1.this.tv_new.setVisibility(View.GONE);
          }
          else
          {
            UpdateAppActivity1.this.ll_update.setVisibility(View.GONE);
            UpdateAppActivity1.this.tv_new.setVisibility(View.VISIBLE);
          }
        }
        else
        {
          UpdateAppActivity1.this.tv_new.setText(UpdateAppActivity1.this.mContext.getResources().getString(R.string.update_app_tip1));
          UpdateAppActivity1.this.ll_update.setVisibility(View.GONE);
          UpdateAppActivity1.this.tv_new.setVisibility(View.VISIBLE);
          break label429;
          label381: UpdateAppActivity1.this.tv_new.setText(UpdateAppActivity1.this.mContext.getResources().getString(R.string.update_app_tip1));
          UpdateAppActivity1.this.ll_update.setVisibility(View.GONE);
          UpdateAppActivity1.this.tv_new.setVisibility(View.VISIBLE);
        }
        label429: if ((UpdateAppActivity1.mCheckingDialog != null) && (UpdateAppActivity1.mCheckingDialog.isShowing()))
          UpdateAppActivity1.mCheckingDialog.dismiss();
        return;
      }
      catch (Exception paramAnonymousMessage)
      {
        break label381;
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

  public static UpdateAppActivity1 getInstance()
  {
    return instance;
  }

  private void initListener()
  {
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpdateAppActivity1.this.finish();
      }
    });
    this.tv_update_app.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CommonUtils.isStringValid(UpdateAppActivity1.this.downLoadPath))
        {
          UpdateAppActivity1.this.mAppDownloadManager.setUpdateListener(new AppDownloadManager.OnUpdateListener()
          {
            public void update(int paramAnonymous2Int1, int paramAnonymous2Int2)
            {
              if (paramAnonymous2Int2 != 0)
                UpdateAppActivity1.this.mLoadingAPPDialog.setProgress(paramAnonymous2Int1 * 100 / paramAnonymous2Int2);
              if ((UpdateAppActivity1.this.mLoadingAPPDialog != null) && (!UpdateAppActivity1.this.mLoadingAPPDialog.isShowing()))
                UpdateAppActivity1.this.mLoadingAPPDialog.show();
              if ((paramAnonymous2Int1 == paramAnonymous2Int2) && (paramAnonymous2Int2 != 0))
                UpdateAppActivity1.this.mLoadingAPPDialog.dismiss();
            }
          });
          UpdateAppActivity1.this.mAppDownloadManager.downloadApk(UpdateAppActivity1.this.downLoadPath, UpdateAppActivity1.this.mContext.getResources().getString(R.string.app_name), UpdateAppActivity1.this.mContext.getResources().getString(R.string.update_app_tip5));
          return;
        }
        new ToastDialog(UpdateAppActivity1.this.mContext, UpdateAppActivity1.this.mContext.getResources().getString(R.string.update_app_tip2)).show();
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
    this.mLoadingAPPDialog = new LoadingAPPDialog.Builder(this.mContext).create();
    this.mLoadingAPPDialog.setCanceledOnTouchOutside(false);
    this.mLoadingAPPDialog.setCancelable(false);
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
    this.mAppDownloadManager = new AppDownloadManager(this.mActivity);
    checkAPPVersionOnServer();
  }

  protected void onPause()
  {
    super.onPause();
    if (this.mAppDownloadManager != null)
      this.mAppDownloadManager.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mAppDownloadManager != null)
      this.mAppDownloadManager.resume();
  }
}
