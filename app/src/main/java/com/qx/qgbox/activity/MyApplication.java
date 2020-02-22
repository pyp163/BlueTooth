package com.qx.qgbox.activity;

import android.app.Application;
import android.util.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.qx.qgbox.entitys.AppInfo;
import com.qx.qgbox.gamepad.DataSaver;
import com.qx.qgbox.utils.GpSetupImageSRCUtils1;
import com.qx.qgbox.utils.GpSetupImageSRCUtils2;
import com.qx.qgbox.utils.SpSetupImageSRCUtils;
import com.qx.qgbox.utils.SystemParams;
import com.tencent.mmkv.MMKV;
import java.io.File;

public class MyApplication extends Application
{
  private static final String JP_TAG = "JIGUANG-Example";
  private static final String TAG = "MyApplication";
  private static byte[] datapro;
  private static byte[] gpDatapro;
  private static AppInfo mAppInfo;
  private static GpSetupImageSRCUtils1 mGpSetupImageSRCUtils1;
  private static GpSetupImageSRCUtils2 mGpSetupImageSRCUtils2;
  private static MyApplication mMyApplication;
  private static SpSetupImageSRCUtils mSpSetupImageSRCUtils;
  public static DataSaver[] mdatasaverScenes0;
  public static DataSaver[] mdatasaverScenes1;
  private static byte[] saveData;

  public static MyApplication getApplication()
  {
    return mMyApplication;
  }

  public static byte[] getDatapro()
  {
    return datapro;
  }

  public static byte[] getGpDatapro()
  {
    return gpDatapro;
  }

  public static DataSaver[] getMdatasaverScenes0()
  {
    return mdatasaverScenes0;
  }

  public static DataSaver[] getMdatasaverScenes1()
  {
    return mdatasaverScenes1;
  }

  public static byte[] getSaveData()
  {
    return saveData;
  }

  public static AppInfo getmAppInfo()
  {
    return mAppInfo;
  }

  public static GpSetupImageSRCUtils1 getmGpSetupImageSRCUtils1()
  {
    return mGpSetupImageSRCUtils1;
  }

  public static GpSetupImageSRCUtils2 getmGpSetupImageSRCUtils2()
  {
    return mGpSetupImageSRCUtils2;
  }

  public static SpSetupImageSRCUtils getmSpSetupImageSRCUtils()
  {
    return mSpSetupImageSRCUtils;
  }

  public static void setDatapro(byte[] paramArrayOfByte)
  {
    datapro = paramArrayOfByte;
  }

  public static void setGpDatapro(byte[] paramArrayOfByte)
  {
    gpDatapro = paramArrayOfByte;
  }

  public static void setMdatasaverScenes0(DataSaver[] paramArrayOfDataSaver)
  {
    mdatasaverScenes0 = paramArrayOfDataSaver;
  }

  public static void setMdatasaverScenes1(DataSaver[] paramArrayOfDataSaver)
  {
    mdatasaverScenes1 = paramArrayOfDataSaver;
  }

  public static void setSaveData(byte[] paramArrayOfByte)
  {
    saveData = paramArrayOfByte;
  }

  public static void setmAppInfo(AppInfo paramAppInfo)
  {
    mAppInfo = paramAppInfo;
  }

  public static void setmGpSetupImageSRCUtils1(GpSetupImageSRCUtils1 paramGpSetupImageSRCUtils1)
  {
    mGpSetupImageSRCUtils1 = paramGpSetupImageSRCUtils1;
  }

  public static void setmGpSetupImageSRCUtils2(GpSetupImageSRCUtils2 paramGpSetupImageSRCUtils2)
  {
    mGpSetupImageSRCUtils2 = paramGpSetupImageSRCUtils2;
  }

  public static void setmSpSetupImageSRCUtils(SpSetupImageSRCUtils paramSpSetupImageSRCUtils)
  {
    mSpSetupImageSRCUtils = paramSpSetupImageSRCUtils;
  }

  public void onCreate()
  {
    mMyApplication = this;
    Log.i("MyApplication", "[ExampleApplication] onCreate");
    super.onCreate();
    Fresco.initialize(this);
    SystemParams.init(this);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(getFilesDir().getAbsolutePath());
    ((StringBuilder)localObject).append("/mmkv_2");
    localObject = MMKV.initialize(((StringBuilder)localObject).toString());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mmkv root: ");
    localStringBuilder.append((String)localObject);
    Log.i("MMKV", localStringBuilder.toString());
    mGpSetupImageSRCUtils1 = new GpSetupImageSRCUtils1(this);
    mGpSetupImageSRCUtils2 = new GpSetupImageSRCUtils2(this);
    mSpSetupImageSRCUtils = new SpSetupImageSRCUtils(this);
  }
}
