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
import com.qx.qgbox.utils.MyLog;

public class NewHelpActivity extends Activity
  implements View.OnClickListener
{
  private LinearLayout LL_gaishu;
  private ImageView iv_android_qs;
  private ImageView iv_back;
  private ImageView iv_change_key;
  private ImageView iv_connect;
  private ImageView iv_download_app;
  private ImageView iv_function_key_ins;
  private ImageView iv_gaishu;
  private ImageView iv_gp_key_ins;
  private ImageView iv_how_download;
  private ImageView iv_what_cloud_key;
  private ImageView iv_what_device;
  private ImageView iv_what_game;
  private ImageView iv_what_key;
  private LinearLayout ll_android_qs1;
  private LinearLayout ll_android_qs2;
  private LinearLayout ll_change_key1;
  private LinearLayout ll_change_key2;
  private LinearLayout ll_connect1;
  private LinearLayout ll_connect2;
  private LinearLayout ll_download_app1;
  private LinearLayout ll_download_app2;
  private LinearLayout ll_function_key_ins1;
  private LinearLayout ll_function_key_ins2;
  private LinearLayout ll_gp_key_ins1;
  private LinearLayout ll_gp_key_ins2;
  private LinearLayout ll_how_download1;
  private LinearLayout ll_how_download2;
  private LinearLayout ll_what_cloud_key;
  private LinearLayout ll_what_device;
  private LinearLayout ll_what_game1;
  private LinearLayout ll_what_game2;
  private LinearLayout ll_what_key1;
  private LinearLayout ll_what_key2;
  private Context mContext;

  @SuppressLint({"HandlerLeak"})
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
    }
  };
  private TextView tv_gaishu;
  private TextView tv_what_cloud_key;
  private TextView tv_what_device;

  private void initListener()
  {
    this.iv_back.setOnClickListener(this);
    this.LL_gaishu.setOnClickListener(this);
    this.ll_connect1.setOnClickListener(this);
    this.ll_what_device.setOnClickListener(this);
    this.ll_what_game1.setOnClickListener(this);
    this.ll_how_download1.setOnClickListener(this);
    this.ll_gp_key_ins1.setOnClickListener(this);
    this.ll_function_key_ins1.setOnClickListener(this);
    this.ll_download_app1.setOnClickListener(this);
    this.ll_what_key1.setOnClickListener(this);
    this.ll_what_cloud_key.setOnClickListener(this);
    this.ll_change_key1.setOnClickListener(this);
    this.ll_android_qs1.setOnClickListener(this);
  }

  private void initUI()
  {
    this.tv_gaishu.setVisibility(View.GONE);
    this.ll_connect2.setVisibility(View.GONE);
    this.tv_what_device.setVisibility(View.GONE);
    this.ll_what_game2.setVisibility(View.GONE);
    this.ll_how_download2.setVisibility(View.GONE);
    this.ll_gp_key_ins2.setVisibility(View.GONE);
    this.ll_function_key_ins2.setVisibility(View.GONE);
    this.ll_download_app2.setVisibility(View.GONE);
    this.ll_what_key2.setVisibility(View.GONE);
    this.tv_what_cloud_key.setVisibility(View.GONE);
    this.ll_change_key2.setVisibility(View.GONE);
    this.ll_android_qs2.setVisibility(View.GONE);
    this.iv_gaishu.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_connect.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_device.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_game.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_how_download.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_gp_key_ins.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_function_key_ins.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_download_app.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_cloud_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_change_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_android_qs.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
  }

  private void initView()
  {
    this.iv_back = ((ImageView)findViewById(R.id.iv_back));
    this.LL_gaishu = ((LinearLayout)findViewById(R.id.LL_gaishu));
    this.tv_gaishu = ((TextView)findViewById(R.id.tv_gaishu));
    this.ll_connect1 = ((LinearLayout)findViewById(R.id.ll_connect1));
    this.ll_connect2 = ((LinearLayout)findViewById(R.id.ll_connect2));
    this.ll_what_device = ((LinearLayout)findViewById(R.id.ll_what_device));
    this.tv_what_device = ((TextView)findViewById(R.id.tv_what_device));
    this.ll_what_game1 = ((LinearLayout)findViewById(R.id.ll_what_game1));
    this.ll_what_game2 = ((LinearLayout)findViewById(R.id.ll_what_game2));
    this.ll_how_download1 = ((LinearLayout)findViewById(R.id.ll_how_download1));
    this.ll_how_download2 = ((LinearLayout)findViewById(R.id.ll_how_download2));
    this.ll_gp_key_ins1 = ((LinearLayout)findViewById(R.id.ll_gp_key_ins1));
    this.ll_gp_key_ins2 = ((LinearLayout)findViewById(R.id.ll_gp_key_ins2));
    this.ll_function_key_ins1 = ((LinearLayout)findViewById(R.id.ll_function_key_ins1));
    this.ll_function_key_ins2 = ((LinearLayout)findViewById(R.id.ll_function_key_ins2));
    this.ll_download_app1 = ((LinearLayout)findViewById(R.id.ll_download_app1));
    this.ll_download_app2 = ((LinearLayout)findViewById(R.id.ll_download_app2));
    this.ll_what_key1 = ((LinearLayout)findViewById(R.id.ll_what_key1));
    this.ll_what_key2 = ((LinearLayout)findViewById(R.id.ll_what_key2));
    this.ll_what_cloud_key = ((LinearLayout)findViewById(R.id.ll_what_cloud_key));
    this.tv_what_cloud_key = ((TextView)findViewById(R.id.tv_what_cloud_key));
    this.ll_change_key1 = ((LinearLayout)findViewById(R.id.ll_change_key1));
    this.ll_change_key2 = ((LinearLayout)findViewById(R.id.ll_change_key2));
    this.ll_android_qs1 = ((LinearLayout)findViewById(R.id.ll_android_qs1));
    this.ll_android_qs2 = ((LinearLayout)findViewById(R.id.ll_android_qs2));
    this.iv_gaishu = ((ImageView)findViewById(R.id.iv_gaishu));
    this.iv_connect = ((ImageView)findViewById(R.id.iv_connect));
    this.iv_what_device = ((ImageView)findViewById(R.id.iv_what_device));
    this.iv_what_game = ((ImageView)findViewById(R.id.iv_what_game));
    this.iv_how_download = ((ImageView)findViewById(R.id.iv_how_download));
    this.iv_gp_key_ins = ((ImageView)findViewById(R.id.iv_gp_key_ins));
    this.iv_function_key_ins = ((ImageView)findViewById(R.id.iv_function_key_ins));
    this.iv_download_app = ((ImageView)findViewById(R.id.iv_download_app));
    this.iv_what_key = ((ImageView)findViewById(R.id.iv_what_key));
    this.iv_what_cloud_key = ((ImageView)findViewById(R.id.iv_what_cloud_key));
    this.iv_change_key = ((ImageView)findViewById(R.id.iv_change_key));
    this.iv_android_qs = ((ImageView)findViewById(R.id.iv_android_qs));
    initUI();
  }

  private void openCloseView(ImageView paramImageView, View paramView)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("----VISIBLE = ");
    localStringBuilder.append(paramView.getVisibility());
    MyLog.i("my_tag", localStringBuilder.toString());
    if (paramView.getVisibility() == View.VISIBLE)
    {
      paramView.setVisibility(View.GONE);
      paramImageView.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
      return;
    }
    this.tv_gaishu.setVisibility(View.GONE);
    this.ll_connect2.setVisibility(View.GONE);
    this.tv_what_device.setVisibility(View.GONE);
    this.ll_what_game2.setVisibility(View.GONE);
    this.ll_how_download2.setVisibility(View.GONE);
    this.ll_gp_key_ins2.setVisibility(View.GONE);
    this.ll_function_key_ins2.setVisibility(View.GONE);
    this.ll_download_app2.setVisibility(View.GONE);
    this.ll_what_key2.setVisibility(View.GONE);
    this.tv_what_cloud_key.setVisibility(View.GONE);
    this.ll_change_key2.setVisibility(View.GONE);
    this.ll_android_qs2.setVisibility(View.GONE);
    this.iv_gaishu.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_connect.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_device.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_game.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_how_download.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_gp_key_ins.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_function_key_ins.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_download_app.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_what_cloud_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_change_key.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    this.iv_android_qs.setImageDrawable(getResources().getDrawable(R.mipmap.down_open));
    paramImageView.setImageDrawable(getResources().getDrawable(R.mipmap.up_close));
    paramView.setVisibility(View.VISIBLE);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case R.id.ll_what_key1:
      openCloseView(this.iv_what_key, this.ll_what_key2);
      return;
    case R.id.ll_what_game1:
      openCloseView(this.iv_what_game, this.ll_what_game2);
      return;
    case R.id.ll_what_device:
      openCloseView(this.iv_what_device, this.tv_what_device);
      return;
    case R.id.ll_what_cloud_key:
      openCloseView(this.iv_what_cloud_key, this.tv_what_cloud_key);
      return;
    case R.id.ll_how_download1:
      openCloseView(this.iv_how_download, this.ll_how_download2);
      return;
    case R.id.ll_gp_key_ins1:
      openCloseView(this.iv_gp_key_ins, this.ll_gp_key_ins2);
      return;
    case R.id.ll_function_key_ins1:
      openCloseView(this.iv_function_key_ins, this.ll_function_key_ins2);
      return;
    case R.id.ll_download_app1:
      openCloseView(this.iv_download_app, this.ll_download_app2);
      return;
    case R.id.ll_connect1:
      openCloseView(this.iv_connect, this.ll_connect2);
      return;
    case R.id.ll_change_key1:
      openCloseView(this.iv_change_key, this.ll_change_key2);
      return;
    case R.id.ll_android_qs1:
      openCloseView(this.iv_android_qs, this.ll_android_qs2);
      return;
    case R.id.iv_back:
      finish();
      return;
    case R.id.LL_gaishu:
    }
    openCloseView(this.iv_gaishu, this.tv_gaishu);
  }

  protected void onCreate(Bundle paramBundle)
  {
    overridePendingTransition(0, 0);
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_new_help);
    this.mContext = this;
    initView();
    initListener();
  }
}
