package com.qx.qgbox.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;

public class FilePathResolver
{
  public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
        try
        {
          if (paramContext.moveToFirst())
          {
            paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
            if (paramContext != null)
              paramContext.close();
            return paramUri;
          }
        }
        finally
        {
          break label80;
        }
      if (paramContext != null)
        paramContext.close();
      return null;
    }
    finally
    {
      paramContext = null;
      label80: if (paramContext != null)
        paramContext.close();
    }
    throw paramUri;
  }

  @SuppressLint({"NewApi"})
  public static String getPath(Context paramContext, Uri paramUri)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 19)
      i = 1;
    else
      i = 0;
    Object localObject = null;
    if ((i != 0) && (DocumentsContract.isDocumentUri(paramContext, paramUri)))
    {
      if (isExternalStorageDocument(paramUri))
      {
        paramContext = DocumentsContract.getDocumentId(paramUri).split(":");
        if ("primary".equalsIgnoreCase(paramContext[0]))
        {
          paramUri = new StringBuilder();
          paramUri.append(Environment.getExternalStorageDirectory());
          paramUri.append("/");
          paramUri.append(paramContext[1]);
          return paramUri.toString();
        }
      }
      else
      {
        if (isDownloadsDocument(paramUri))
        {
          paramUri = DocumentsContract.getDocumentId(paramUri);
          return getDataColumn(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
        }
        if (isMediaDocument(paramUri))
        {
          String[] arrayOfString = DocumentsContract.getDocumentId(paramUri).split(":");
          String str = arrayOfString[0];
          if ("image".equals(str))
          {
            paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
          }
          else if ("video".equals(str))
          {
            paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          }
          else
          {
            paramUri = localObject;
            if ("audio".equals(str))
              paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          }
          return getDataColumn(paramContext, paramUri, "_id=?", new String[] { arrayOfString[1] });
        }
      }
    }
    else
    {
      if ("content".equalsIgnoreCase(paramUri.getScheme()))
      {
        if (isGooglePhotosUri(paramUri))
          return paramUri.getLastPathSegment();
        return getDataColumn(paramContext, paramUri, null, null);
      }
      if ("file".equalsIgnoreCase(paramUri.getScheme()))
        return paramUri.getPath();
    }
    return null;
  }

  public static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }

  public static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }

  public static boolean isGooglePhotosUri(Uri paramUri)
  {
    return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
  }

  public static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
}
