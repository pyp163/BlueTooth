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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.service.bluetoothdevmanager.Callback5;
import com.qx.qgbox.utils.BlueCmdManager;
import com.qx.qgbox.views.ToastDialog;
import java.util.ArrayList;
import java.util.List;

public class Setupdialog extends AlertDialog
{
  public static final int ANALYSE_DATA = 2;
  public static final int MSG_ON_LOADING_GUN_PARM = 1;
  public static int delayedTime = 100;
  public static Handler mHandler;
  private TextView btn_finish_setup;
  private TextView btn_reset;
  private TextView btn_save;
  private TextView gun_setup;
  private TextView gun_setup_tip;
  private LinearLayout ll_setup;
  private Context mContext;
  private TextView mirrors_four_range;
  private SeekBar mirrors_four_range_seek_bar;
  private TextView mirrors_three_range;
  private SeekBar mirrors_three_range_seek_bar;
  private TextView mirrors_two_range;
  private SeekBar mirrors_two_range_seek_bar;
  private TextView red_range;
  private SeekBar red_range_seek_bar;
  private List<Setupitem> setupTypeList = new ArrayList();
  private ListAdapter setupTypeListAdapter;
  private ListView setup_type;
  private ScrollView sv_tip;

  public Setupdialog(Context paramContext)
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
      this.red_range_seek_bar.setProgress(paramArrayOfByte[8] & 0xFF);
      this.mirrors_two_range_seek_bar.setProgress(paramArrayOfByte[10] & 0xFF);
      this.mirrors_three_range_seek_bar.setProgress(paramArrayOfByte[12] & 0xFF);
      this.mirrors_four_range_seek_bar.setProgress(paramArrayOfByte[14] & 0xFF);
    }
    if ((paramArrayOfByte[2] & 0xFF) == 13)
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.gun_setup_tip2)).show();
    if ((paramArrayOfByte[2] & 0xFF) == 34)
    {
      mHandler.sendEmptyMessageDelayed(1, delayedTime);
      new ToastDialog(this.mContext, this.mContext.getResources().getString(R.string.gun_setup_tip3)).show();
    }
  }

  private void initAdapter()
  {
    Setupitem localSetupitem = new Setupitem(this.mContext.getResources().getString(R.string.setting_gun));
    localSetupitem.setSelected(true);
    this.setupTypeList.add(localSetupitem);
    this.setupTypeListAdapter = new ListAdapter(this.mContext);
    this.setupTypeListAdapter.setList(this.setupTypeList);
    this.setup_type.setAdapter(this.setupTypeListAdapter);
  }

  private void initListener()
  {
    this.gun_setup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setupdialog.this.ll_setup.setVisibility(View.VISIBLE);
        Setupdialog.this.sv_tip.setVisibility(View.GONE);
        Setupdialog.this.gun_setup.setBackgroundColor(Setupdialog.this.mContext.getResources().getColor(R.color.shape_color));
        Setupdialog.this.gun_setup_tip.setBackgroundColor(Setupdialog.this.mContext.getResources().getColor(R.color.black));
      }
    });
    this.gun_setup_tip.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setupdialog.this.ll_setup.setVisibility(View.GONE);
        Setupdialog.this.sv_tip.setVisibility(View.VISIBLE);
        Setupdialog.this.gun_setup.setBackgroundColor(Setupdialog.this.mContext.getResources().getColor(R.color.black));
        Setupdialog.this.gun_setup_tip.setBackgroundColor(Setupdialog.this.mContext.getResources().getColor(R.color.shape_color));
      }
    });
    this.setup_type.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
      }
    });
    this.red_range_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        TextView seekBar = Setupdialog.this.red_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        seekBar.setText(localStringBuilder.toString());
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
        TextView seekBar = Setupdialog.this.mirrors_two_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        seekBar.setText(localStringBuilder.toString());
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
        TextView seekBar = Setupdialog.this.mirrors_three_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        seekBar.setText(localStringBuilder.toString());
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
        TextView seekBar = Setupdialog.this.mirrors_four_range;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousInt);
        seekBar.setText(localStringBuilder.toString());
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
        BlueCmdManager.saveGunparm(Setupdialog.this.red_range_seek_bar.getProgress(), Setupdialog.this.mirrors_two_range_seek_bar.getProgress(), Setupdialog.this.mirrors_three_range_seek_bar.getProgress(), Setupdialog.this.mirrors_four_range_seek_bar.getProgress());
      }
    });
    this.btn_finish_setup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setupdialog.this.dismiss();
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
    this.gun_setup = ((TextView)findViewById(R.id.gun_setup));
    this.gun_setup_tip = ((TextView)findViewById(R.id.gun_setup_tip));
    this.ll_setup = ((LinearLayout)findViewById(R.id.ll_setup));
    this.sv_tip = ((ScrollView)findViewById(R.id.sv_tip));
    this.ll_setup.setVisibility(View.VISIBLE);
    this.sv_tip.setVisibility(View.GONE);
    this.gun_setup.setBackgroundColor(this.mContext.getResources().getColor(R.color.shape_color));
    this.gun_setup_tip.setBackgroundColor(this.mContext.getResources().getColor(R.color.black));
    this.setup_type = ((ListView)findViewById(R.id.setup_type));
    this.btn_reset = ((TextView)findViewById(R.id.btn_reset));
    this.btn_save = ((TextView)findViewById(R.id.btn_save));
    this.btn_finish_setup = ((TextView)findViewById(R.id.btn_finish_setup));
  }

  @SuppressLint({"HandlerLeak"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.mouseleftdialog);
    getWindow().setLayout(-1, -1);
    getWindow().setFlags(8, 8);
    if (Build.VERSION.SDK_INT < 23)
      getWindow().setType(2005);
    else
      getWindow().setType(2003);
    setCancelable(false);
    mHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          break;
        case 2:
          Setupdialog.this.dataprocess((byte[])paramAnonymousMessage.obj);
          break;
        case 1:
          BlueCmdManager.sendGetGunParm();
        }
        super.handleMessage(paramAnonymousMessage);
      }
    };
    initView();
    initAdapter();
    initListener();
    bluetoothdevmanager.setCallbackGun(new bluetoothdevmanager.Callback5()
    {
      public void onDataChange(byte[] paramAnonymousArrayOfByte)
      {
        Message localMessage = new Message();
        localMessage.what = 2;
        localMessage.obj = paramAnonymousArrayOfByte;
        Setupdialog.mHandler.sendMessage(localMessage);
      }
    });
    if (mHandler != null)
      mHandler.sendEmptyMessageDelayed(1, delayedTime);
  }
}
