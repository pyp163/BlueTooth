package com.qx.qgbox.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.qx.qgbox.R;
import com.qx.qgbox.adapters.InstalledAppListAdapter;
import com.qx.qgbox.db.DBManager;
import com.qx.qgbox.entitys.InstalledApp;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.utils.AppInfoProvider;
import java.util.ArrayList;

public class InstalledAppListActivity extends Activity
  implements InstalledAppListAdapter.Callback
{
  public static final int GET_ALL_APP_FINISH = 0;
  private InstalledAppListAdapter.Callback callback;

  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what != 0)
        return;
      InstalledAppListActivity.this.mInstalledAppListAdapter.refresh(InstalledAppListActivity.this.mInstalledAppList, InstalledAppListActivity.this.callback);
    }
  };
  private ImageView iv_back;
  private ListView lv_installed_app;
  private Context mContext;
  private DBManager mDBManager;
  private ArrayList<InstalledApp> mInstalledAppList = new ArrayList();
  private InstalledAppListAdapter mInstalledAppListAdapter;
  private ArrayList<SunyesMaxGamePreset> mSunyesMaxGamePresetList = new ArrayList();
  private AppInfoProvider provider;

  private void initListener()
  {
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        InstalledAppListActivity.this.finish();
      }
    });
    this.lv_installed_app.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
      }
    });
  }

  private void initView()
  {
    this.iv_back = ((ImageView)findViewById(R.id.iv_back));
    this.lv_installed_app = ((ListView)findViewById(R.id.lv_installed_app));
    this.mInstalledAppListAdapter = new InstalledAppListAdapter(this.mContext, this.mInstalledAppList, this.callback);
    this.lv_installed_app.setAdapter(this.mInstalledAppListAdapter);
  }

  public void click(InstalledApp paramInstalledApp)
  {
    FirstPageActivity.getInstance().addInstalledApp(paramInstalledApp);
    finish();
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_installed_app_list);
    this.mContext = this;
    this.callback = this;
    this.mDBManager = new DBManager(this.mContext);
    initView();
    initListener();
    new Thread()
    {
      public void run()
      {
        if (bluetoothdevmanager.devicemode == 0)
          InstalledAppListActivity.access$302(InstalledAppListActivity.this, InstalledAppListActivity.this.mDBManager.queryAll(0));
        else
          InstalledAppListActivity.access$302(InstalledAppListActivity.this, InstalledAppListActivity.this.mDBManager.queryAll(1));
        InstalledAppListActivity.access$502(InstalledAppListActivity.this, new AppInfoProvider(InstalledAppListActivity.this.mContext, InstalledAppListActivity.this.mSunyesMaxGamePresetList));
        InstalledAppListActivity.access$002(InstalledAppListActivity.this, InstalledAppListActivity.this.provider.getAllApps());
        Message localMessage = new Message();
        localMessage.what = 0;
        InstalledAppListActivity.this.handler.sendMessage(localMessage);
      }
    }
    .start();
  }
}
