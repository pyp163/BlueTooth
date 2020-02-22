package com.qx.qgbox.download;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;

public class DownLoadApk
{
  public static final String TAG = "DownLoadApk";

  public static void download(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
  }

  private static PackageInfo getApkInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
    if (paramContext != null)
      return paramContext;
    return null;
  }

  public static void install(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
}
