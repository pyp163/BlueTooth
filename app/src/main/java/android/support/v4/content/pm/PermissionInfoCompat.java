package android.support.v4.content.pm;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionInfoCompat
{
  @SuppressLint({"WrongConstant"})
  public static int getProtection(@NonNull PermissionInfo paramPermissionInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPermissionInfo.getProtection();
    return paramPermissionInfo.protectionLevel & 0xF;
  }

  @SuppressLint({"WrongConstant"})
  public static int getProtectionFlags(@NonNull PermissionInfo paramPermissionInfo)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramPermissionInfo.getProtectionFlags();
    return paramPermissionInfo.protectionLevel & 0xFFFFFFF0;
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface Protection
  {
  }

  @Retention(RetentionPolicy.SOURCE)
  @SuppressLint({"UniqueConstants"})
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface ProtectionFlags
  {
  }
}