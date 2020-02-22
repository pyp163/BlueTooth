package com.qx.qgbox.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.qx.qgbox.activity.AndroidOPermissionActivity;
import com.qx.qgbox.views.BToast;
import java.io.File;
import java.lang.ref.WeakReference;

public class AppDownloadManager
{
  private DownloadChangeObserver mDownLoadChangeObserver;
  private DownloadManager mDownloadManager;
  private DownloadReceiver mDownloadReceiver;
  private long mReqId;
  private OnUpdateListener mUpdateListener;
  private WeakReference<Activity> weakReference;

  public AppDownloadManager(Activity paramActivity)
  {
    this.weakReference = new WeakReference(paramActivity);
    this.mDownloadManager = ((DownloadManager)((Activity)this.weakReference.get()).getSystemService("download"));
    this.mDownLoadChangeObserver = new DownloadChangeObserver(new Handler());
    this.mDownloadReceiver = new DownloadReceiver();
  }

  private void installApk(Context paramContext, Intent paramIntent)
  {
    long l = paramIntent.getLongExtra("extra_download_id", -1L);
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    if (l == this.mReqId)
    {
      if (Build.VERSION.SDK_INT < 23)
      {
        paramIntent = this.mDownloadManager.getUriForDownloadedFile(l);
      }
      else if (Build.VERSION.SDK_INT < 24)
      {
        paramIntent = Uri.fromFile(queryDownloadedApk(paramContext, l));
      }
      else
      {
        paramIntent = FileProvider.getUriForFile(paramContext, "com.qx.qgbox.fileProvider", new File(paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "app_name.apk"));
        localIntent.addFlags(3);
      }
      localIntent.setDataAndType(paramIntent, "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent);
    }
  }

  public static File queryDownloadedApk(Context paramContext, long paramLong)
  {
    Object localObject2 = (DownloadManager)paramContext.getSystemService("download");
    String str = null;
    Object localObject1 = null;
    paramContext = str;
    if (paramLong != -1L)
    {
      paramContext = new DownloadManager.Query();
      paramContext.setFilterById(new long[] { paramLong });
      paramContext.setFilterByStatus(8);
      localObject2 = ((DownloadManager)localObject2).query(paramContext);
      paramContext = str;
      if (localObject2 != null)
      {
        paramContext = localObject1;
        if (((Cursor)localObject2).moveToFirst())
        {
          str = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("local_uri"));
          paramContext = localObject1;
          if (!TextUtils.isEmpty(str))
            paramContext = new File(Uri.parse(str).getPath());
        }
        ((Cursor)localObject2).close();
      }
    }
    return paramContext;
  }

  // ERROR //
  private void updateView()
  {
    // Byte code:
    //   0: iconst_3
    //   1: newarray int
    //   3: astore_1
    //   4: aload_1
    //   5: dup
    //   6: iconst_0
    //   7: iconst_0
    //   8: iastore
    //   9: dup
    //   10: iconst_1
    //   11: iconst_0
    //   12: iastore
    //   13: dup
    //   14: iconst_2
    //   15: iconst_0
    //   16: iastore
    //   17: pop
    //   18: new 167	android/app/DownloadManager$Query
    //   21: dup
    //   22: invokespecial 168	android/app/DownloadManager$Query:<init>	()V
    //   25: iconst_1
    //   26: newarray long
    //   28: dup
    //   29: iconst_0
    //   30: aload_0
    //   31: getfield 107	com/qx/qgbox/utils/AppDownloadManager:mReqId	J
    //   34: lastore
    //   35: invokevirtual 172	android/app/DownloadManager$Query:setFilterById	([J)Landroid/app/DownloadManager$Query;
    //   38: astore_2
    //   39: aload_0
    //   40: getfield 59	com/qx/qgbox/utils/AppDownloadManager:mDownloadManager	Landroid/app/DownloadManager;
    //   43: aload_2
    //   44: invokevirtual 180	android/app/DownloadManager:query	(Landroid/app/DownloadManager$Query;)Landroid/database/Cursor;
    //   47: astore_2
    //   48: aload_2
    //   49: ifnull +70 -> 119
    //   52: aload_2
    //   53: invokeinterface 186 1 0
    //   58: ifeq +61 -> 119
    //   61: aload_1
    //   62: iconst_0
    //   63: aload_2
    //   64: aload_2
    //   65: ldc 218
    //   67: invokeinterface 221 2 0
    //   72: invokeinterface 225 2 0
    //   77: iastore
    //   78: aload_1
    //   79: iconst_1
    //   80: aload_2
    //   81: aload_2
    //   82: ldc 227
    //   84: invokeinterface 221 2 0
    //   89: invokeinterface 225 2 0
    //   94: iastore
    //   95: aload_1
    //   96: iconst_2
    //   97: aload_2
    //   98: aload_2
    //   99: ldc 229
    //   101: invokeinterface 192 2 0
    //   106: invokeinterface 225 2 0
    //   111: iastore
    //   112: goto +7 -> 119
    //   115: astore_1
    //   116: goto +39 -> 155
    //   119: aload_2
    //   120: ifnull +9 -> 129
    //   123: aload_2
    //   124: invokeinterface 216 1 0
    //   129: aload_0
    //   130: getfield 231	com/qx/qgbox/utils/AppDownloadManager:mUpdateListener	Lcom/qx/qgbox/utils/AppDownloadManager$OnUpdateListener;
    //   133: ifnull +18 -> 151
    //   136: aload_0
    //   137: getfield 231	com/qx/qgbox/utils/AppDownloadManager:mUpdateListener	Lcom/qx/qgbox/utils/AppDownloadManager$OnUpdateListener;
    //   140: aload_1
    //   141: iconst_0
    //   142: iaload
    //   143: aload_1
    //   144: iconst_1
    //   145: iaload
    //   146: invokeinterface 235 3 0
    //   151: return
    //   152: astore_1
    //   153: aconst_null
    //   154: astore_2
    //   155: aload_2
    //   156: ifnull +9 -> 165
    //   159: aload_2
    //   160: invokeinterface 216 1 0
    //   165: aload_1
    //   166: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   52	112	115	finally
    //   39	48	152	finally
  }

  public void cancel()
  {
    this.mDownloadManager.remove(new long[] { this.mReqId });
  }

  public void downloadApk(String paramString1, String paramString2, String paramString3)
  {
    File localFile = new File(((Activity)this.weakReference.get()).getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "app_name.apk");
    if ((localFile != null) && (localFile.exists()))
      localFile.delete();
    paramString1 = new DownloadManager.Request(Uri.parse(paramString1));
    paramString1.setTitle(paramString2);
    paramString1.setDescription(paramString3);
    paramString1.setNotificationVisibility(1);
    paramString1.setDestinationInExternalFilesDir((Context)this.weakReference.get(), Environment.DIRECTORY_DOWNLOADS, "app_name.apk");
    paramString1.setMimeType("application/vnd.android.package-archive");
    this.mReqId = this.mDownloadManager.enqueue(paramString1);
  }

  public void install(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }

  public void onPause()
  {
    ((Activity)this.weakReference.get()).getContentResolver().unregisterContentObserver(this.mDownLoadChangeObserver);
    ((Activity)this.weakReference.get()).unregisterReceiver(this.mDownloadReceiver);
  }

  public void resume()
  {
    ((Activity)this.weakReference.get()).getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true, this.mDownLoadChangeObserver);
    ((Activity)this.weakReference.get()).registerReceiver(this.mDownloadReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
  }

  public void setUpdateListener(OnUpdateListener paramOnUpdateListener)
  {
    this.mUpdateListener = paramOnUpdateListener;
  }

  public static abstract interface AndroidOInstallPermissionListener
  {
    public abstract void permissionFail();

    public abstract void permissionSuccess();
  }

  class DownloadChangeObserver extends ContentObserver
  {
    public DownloadChangeObserver(Handler arg2)
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      super.onChange(paramBoolean);
      AppDownloadManager.this.updateView();
    }
  }

  class DownloadReceiver extends BroadcastReceiver
  {
    DownloadReceiver()
    {
    }

    public void onReceive(final Context paramContext, final Intent paramIntent)
    {
      if (Build.VERSION.SDK_INT >= 26)
      {
        if (!paramContext.getPackageManager().canRequestPackageInstalls())
        {
          AndroidOPermissionActivity.sListener = new AppDownloadManager.AndroidOInstallPermissionListener()
          {
            public void permissionFail()
            {
              BToast.showText(paramContext, "授权失败，无法安装应用");
            }

            public void permissionSuccess()
            {
              AppDownloadManager.this.installApk(paramContext, paramIntent);
            }
          };
          paramContext.startActivity(new Intent(paramContext, AndroidOPermissionActivity.class));
          return;
        }
        AppDownloadManager.this.installApk(paramContext, paramIntent);
        return;
      }
      AppDownloadManager.this.installApk(paramContext, paramIntent);
    }
  }

  public static abstract interface OnUpdateListener
  {
    public abstract void update(int paramInt1, int paramInt2);
  }
}
