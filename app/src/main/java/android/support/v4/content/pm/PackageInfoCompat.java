package android.support.v4.content.pm;

import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class PackageInfoCompat
{
  public static long getLongVersionCode(@NonNull PackageInfo paramPackageInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPackageInfo.getLongVersionCode();
    return paramPackageInfo.versionCode;
  }
}