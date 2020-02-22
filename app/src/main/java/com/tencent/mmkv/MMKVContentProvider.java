package com.tencent.mmkv;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class MMKVContentProvider extends ContentProvider
{
  protected static final String FUNCTION_NAME = "mmkvFromAshmemID";
  protected static final String KEY = "KEY";
  protected static final String KEY_CRYPT = "KEY_CRYPT";
  protected static final String KEY_MODE = "KEY_MODE";
  protected static final String KEY_SIZE = "KEY_SIZE";
  private static Uri gUri;

  @Nullable
  protected static Uri contentUri(Context paramContext)
  {
    if (gUri != null)
      return gUri;
    if (paramContext == null)
      return null;
    paramContext = queryAuthority(paramContext);
    if (paramContext == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("content://");
    localStringBuilder.append(paramContext);
    gUri = Uri.parse(localStringBuilder.toString());
    return gUri;
  }

  protected static String getProcessNameByPID(Context paramContext, int paramInt)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = paramContext.getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == paramInt)
          return localRunningAppProcessInfo.processName;
      }
    }
    return "";
  }

  private Bundle mmkvFromAshmemID(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    MMKV localMMKV = MMKV.mmkvWithAshmemID(getContext(), paramString1, paramInt1, paramInt2, paramString2);
    if (localMMKV != null)
    {
      paramString2 = new ParcelableMMKV(localMMKV);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append(" fd = ");
      localStringBuilder.append(localMMKV.ashmemFD());
      localStringBuilder.append(", meta fd = ");
      localStringBuilder.append(localMMKV.ashmemMetaFD());
      Log.i("MMKV", localStringBuilder.toString());
      paramString1 = new Bundle();
      paramString1.putParcelable("KEY", paramString2);
      return paramString1;
    }
    return null;
  }

  private static String queryAuthority(Context paramContext)
  {
    try
    {
      ComponentName localComponentName = new ComponentName(paramContext, MMKVContentProvider.class.getName());
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        paramContext = paramContext.getProviderInfo(localComponentName, 0);
        if (paramContext != null)
        {
          paramContext = paramContext.authority;
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  @Nullable
  public Bundle call(@NonNull String paramString1, @Nullable String paramString2, @Nullable Bundle paramBundle)
  {
    if ((paramString1.equals("mmkvFromAshmemID")) && (paramBundle != null))
      return mmkvFromAshmemID(paramString2, paramBundle.getInt("KEY_SIZE"), paramBundle.getInt("KEY_MODE"), paramBundle.getString("KEY_CRYPT"));
    return null;
  }

  public int delete(@NonNull Uri paramUri, @Nullable String paramString, @Nullable String[] paramArrayOfString)
  {
    throw new UnsupportedOperationException("Not implement in MMKV");
  }

  @Nullable
  public String getType(@NonNull Uri paramUri)
  {
    return null;
  }

  @Nullable
  public Uri insert(@NonNull Uri paramUri, @Nullable ContentValues paramContentValues)
  {
    throw new UnsupportedOperationException("Not implement in MMKV");
  }

  public boolean onCreate()
  {
    Object localObject = getContext();
    if (localObject == null)
      return false;
    localObject = queryAuthority((Context)localObject);
    if (localObject == null)
      return false;
    if (gUri == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("content://");
      localStringBuilder.append((String)localObject);
      gUri = Uri.parse(localStringBuilder.toString());
    }
    return true;
  }

  @Nullable
  public Cursor query(@NonNull Uri paramUri, @Nullable String[] paramArrayOfString1, @Nullable String paramString1, @Nullable String[] paramArrayOfString2, @Nullable String paramString2)
  {
    throw new UnsupportedOperationException("Not implement in MMKV");
  }

  public int update(@NonNull Uri paramUri, @Nullable ContentValues paramContentValues, @Nullable String paramString, @Nullable String[] paramArrayOfString)
  {
    throw new UnsupportedOperationException("Not implement in MMKV");
  }
}