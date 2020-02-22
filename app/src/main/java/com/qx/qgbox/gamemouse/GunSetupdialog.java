package com.qx.qgbox.gamemouse;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.service.bluetoothdevmanager.Callback5;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.views.ToastDialog;

public class GunSetupdialog extends AlertDialog
{
  public static final int ANALYSE_DATA = 2;
  public static final int MSG_ON_LOADING_GUN_PARM = 1;
  public static final int MSG_ON_SAVE_GUN_PARM_SUCCESS = 3;
  public static int delayedTime = 100;
  public static Handler mHandler;
  private TextView btn_finish_setup;
  private TextView btn_reset;
  private TextView btn_save;
  private Context mContext;
  private TextView mirrors_four_range;
  private SeekBar mirrors_four_range_seek_bar;
  private TextView mirrors_three_range;
  private SeekBar mirrors_three_range_seek_bar;
  private TextView mirrors_two_range;
  private SeekBar mirrors_two_range_seek_bar;
  private TextView red_range;
  private SeekBar red_range_seek_bar;

  public GunSetupdialog(Context paramContext)
  {
    super(paramContext, R.style.custom_window_dialog);
    this.mContext = paramContext;
  }

  private void dataprocess(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte[0] != 22)
      return;
    if ((paramArrayOfByte[2] & 0xFF) == 33)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("----data[2] = ");
      localStringBuilder.append(paramArrayOfByte[2] & 0xFF);
      localStringBuilder.append(";  data[8] = ");
      localStringBuilder.append(paramArrayOfByte[8] & 0xFF);
      localStringBuilder.append(";  data[7] = ");
      localStringBuilder.append(paramArrayOfByte[7] & 0xFF);
      localStringBuilder.append(";  data[10] = ");
      localStringBuilder.append(paramArrayOfByte[10] & 0xFF);
      localStringBuilder.append(";  data[9] = ");
      localStringBuilder.append(paramArrayOfByte[9] & 0xFF);
      localStringBuilder.append(";  data[12] = ");
      localStringBuilder.append(paramArrayOfByte[12] & 0xFF);
      localStringBuilder.append(";  data[11] = ");
      localStringBuilder.append(paramArrayOfByte[11] & 0xFF);
      localStringBuilder.append(";  data[14] = ");
      localStringBuilder.append(paramArrayOfByte[14] & 0xFF);
      localStringBuilder.append(";  data[13] = ");
      localStringBuilder.append(paramArrayOfByte[13] & 0xFF);
      Log.i("gun_tag", localStringBuilder.toString());
      this.red_range_seek_bar.setProgress(paramArrayOfByte[8] & 0xFF);
      this.mirrors_two_range_seek_bar.setProgress(paramArrayOfByte[10] & 0xFF);
      this.mirrors_three_range_seek_bar.setProgress(paramArrayOfByte[12] & 0xFF);
      this.mirrors_four_range_seek_bar.setProgress(paramArrayOfByte[14] & 0xFF);
    }
    if ((paramArrayOfByte[2] & 0xFF) == 13)
    {
      dismiss();
      mHandler.sendEmptyMessageDelayed(3, 200L);
    }
    if ((paramArrayOfByte[2] & 0xFF) == 34)
    {
      mHandler.sendEmptyMessageDelayed(1, delayedTime);
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.gun_setup_tip3)).show();
    }
  }

  private void initListener()
  {
    this.red_range_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = GunSetupdialog.this.red_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.mirrors_two_range_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = GunSetupdialog.this.mirrors_two_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.mirrors_three_range_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = GunSetupdialog.this.mirrors_three_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.mirrors_four_range_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousSeekBar = GunSetupdialog.this.mirrors_four_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        paramAnonymousSeekBar.setText(localStringBuilder.toString());
      }

      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
      {
      }
    });
    this.btn_reset.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BlueCmdManager.resetGunParm();
      }
    });
    this.btn_save.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BlueCmdManager.saveGunparm(GunSetupdialog.this.red_range_seek_bar.getProgress(), GunSetupdialog.this.mirrors_two_range_seek_bar.getProgress(), GunSetupdialog.this.mirrors_three_range_seek_bar.getProgress(), GunSetupdialog.this.mirrors_four_range_seek_bar.getProgress());
      }
    });
    this.btn_finish_setup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GunSetupdialog.this.dismiss();
      }
    });
  }

  private void initView()
  {
    this.red_range_seek_bar = ((SeekBar)findViewById(R.id.red_range_seek_bar));
    this.mirrors_two_range_seek_bar = ((SeekBar)findViewById(R.id.mirrors_two_range_seek_bar));
    this.mirrors_three_range_seek_bar = ((SeekBar)findViewById(R.id.mirrors_three_range_seek_bar));
    this.mirrors_four_range_seek_bar = ((SeekBar)findViewById(R.id.mirrors_four_range_seek_bar));
    this.red_range = ((TextView)findViewById(R.id.red_range));
    this.mirrors_two_range = ((TextView)findViewById(R.id.mirrors_two_range));
    this.mirrors_three_range = ((TextView)findViewById(R.id.mirrors_three_range));
    this.mirrors_four_range = ((TextView)findViewById(R.id.mirrors_four_range));
    this.btn_reset = ((TextView)findViewById(R.id.btn_reset));
    this.btn_save = ((TextView)findViewById(R.id.btn_save));
    this.btn_finish_setup = ((TextView)findViewById(R.id.btn_finish_setup));
  }

  @SuppressLint({"HandlerLeak"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.gun_setup_dialog1);
    getWindow().setLayout(-2, -2);
    getWindow().setFlags(8, 8);
    if (Build.VERSION.SDK_INT < 23)
      getWindow().setType(2005);
    else if ((Build.VERSION.SDK_INT != 28) && (Build.VERSION.SDK_INT != 26) && (Build.VERSION.SDK_INT != 27))
    {
      if (Build.VERSION.SDK_INT > 28)
        getWindow().setType(2038);
      else
        getWindow().setType(2003);
    }
    else
      getWindow().setType(2038);
    setCancelable(false);
    mHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          break;
        case 3:
          new ToastDialog(GunSetupdialog.this.mContext, GunSetupdialog.this.mContext.getResources().getString(R.string.gun_setup_tip2)).show();
          break;
        case 2:
          GunSetupdialog.this.dataprocess((byte[])paramAnonymousMessage.obj);
          break;
        case 1:
          BlueCmdManager.sendGetGunParm();
        }
        super.handleMessage(paramAnonymousMessage);
      }
    };
    initView();
    initListener();
    bluetoothdevmanager.setCallbackGun(new bluetoothdevmanager.Callback5()
    {
      public void onDataChange(byte[] paramAnonymousArrayOfByte)
      {
        Message localMessage = new Message();
        localMessage.what = 2;
        localMessage.obj = paramAnonymousArrayOfByte;
        GunSetupdialog.mHandler.sendMessage(localMessage);
      }
    });
    if (mHandler != null)
      mHandler.sendEmptyMessageDelayed(1, delayedTime);
  }
}
