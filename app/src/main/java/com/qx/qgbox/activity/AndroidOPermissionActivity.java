package com.qx.qgbox.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.qx.qgbox.R;
import com.qx.qgbox.utils.AppDownloadManager;

public class AndroidOPermissionActivity extends Activity
{
  public static final int INSTALL_PACKAGES_REQUESTCODE = 1;
  public static AppDownloadManager.AndroidOInstallPermissionListener sListener;
  private AlertDialog mAlertDialog;
  private Context mContext;

  private void showDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.app_name);
    localBuilder.setMessage(this.mContext.getResources().getString(R.string.update_app_tip8));
    localBuilder.setPositiveButton(this.mContext.getResources().getString(R.string.update_app_tip7), new DialogInterface.OnClickListener()
    {

      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AndroidOPermissionActivity.this.startInstallPermissionSettingActivity();
        AndroidOPermissionActivity.this.mAlertDialog.dismiss();
      }
    });
    localBuilder.setNegativeButton(this.mContext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (AndroidOPermissionActivity.sListener != null)
          AndroidOPermissionActivity.sListener.permissionFail();
        AndroidOPermissionActivity.this.mAlertDialog.dismiss();
        AndroidOPermissionActivity.this.finish();
      }
    });
    this.mAlertDialog = localBuilder.create();
    this.mAlertDialog.setCanceledOnTouchOutside(false);
    this.mAlertDialog.setCancelable(false);
    this.mAlertDialog.show();
  }


  private void startInstallPermissionSettingActivity()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(getPackageName());
    startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse(localStringBuilder.toString())), 1);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == -1))
    {
      if (sListener != null)
        sListener.permissionSuccess();
    }
    else if (sListener != null)
      sListener.permissionFail();
    finish();
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = this;
    if (Build.VERSION.SDK_INT >= 26)
    {
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.REQUEST_INSTALL_PACKAGES" }, 1);
      return;
    }
    finish();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    sListener = null;
  }


  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if (paramInt != 1)
      return;
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0))
    {
      if (sListener != null)
      {
        sListener.permissionSuccess();
        finish();
      }
    }
    else
      showDialog();
  }
}
