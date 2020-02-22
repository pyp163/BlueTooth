package com.facebook.common.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract;
import android.provider.MediaStore.Images.Media;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nullable;

public class UriUtil
{
  public static final String DATA_SCHEME = "data";
  public static final String HTTPS_SCHEME = "https";
  public static final String HTTP_SCHEME = "http";
  public static final String LOCAL_ASSET_SCHEME = "asset";
  private static final Uri LOCAL_CONTACT_IMAGE_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");
  public static final String LOCAL_CONTENT_SCHEME = "content";
  public static final String LOCAL_FILE_SCHEME = "file";
  public static final String LOCAL_RESOURCE_SCHEME = "res";
  public static final String QUALIFIED_RESOURCE_SCHEME = "android.resource";

  @Nullable
  public static String getRealPathFromUri(ContentResolver paramContentResolver, Uri paramUri)
  {
    boolean bool = isLocalContentUri(paramUri);
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject3;
    if (bool)
    {
      try
      {
        paramUri = paramContentResolver.query(paramUri, null, null, null, null);
        paramContentResolver = localObject1;
        if (paramUri != null)
        {
          paramContentResolver = localObject1;
          try
          {
            if (paramUri.moveToFirst())
            {
              int i = paramUri.getColumnIndex("_data");
              paramContentResolver = localObject1;
              if (i != -1)
                paramContentResolver = paramUri.getString(i);
            }
          }
          finally
          {
            paramContentResolver = paramUri;
            paramUri = localObject2;
            break label100;
          }
        }
        localObject3 = paramContentResolver;
        if (paramUri == null)
          break label129;
        paramUri.close();
        return paramContentResolver;
      }
      finally
      {
        paramContentResolver = null;
        label100: if (paramContentResolver != null)
          paramContentResolver.close();
      }
    }
    else
    {
      localObject3 = localObject4;
      if (isLocalFileUri(paramUri))
        localObject3 = paramUri.getPath();
    }
    label129: return localObject3;
  }

  @Nullable
  public static String getSchemeOrNull(@Nullable Uri paramUri)
  {
    if (paramUri == null)
      return null;
    return paramUri.getScheme();
  }

  public static Uri getUriForFile(File paramFile)
  {
    return Uri.fromFile(paramFile);
  }

  public static Uri getUriForQualifiedResource(String paramString, int paramInt)
  {
    return new Uri.Builder().scheme("android.resource").authority(paramString).path(String.valueOf(paramInt)).build();
  }

  public static Uri getUriForResourceId(int paramInt)
  {
    return new Uri.Builder().scheme("res").path(String.valueOf(paramInt)).build();
  }

  public static boolean isDataUri(@Nullable Uri paramUri)
  {
    return "data".equals(getSchemeOrNull(paramUri));
  }

  public static boolean isLocalAssetUri(@Nullable Uri paramUri)
  {
    return "asset".equals(getSchemeOrNull(paramUri));
  }

  public static boolean isLocalCameraUri(Uri paramUri)
  {
    paramUri = paramUri.toString();
    return (paramUri.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) || (paramUri.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString()));
  }

  public static boolean isLocalContactUri(Uri paramUri)
  {
    return (isLocalContentUri(paramUri)) && ("com.android.contacts".equals(paramUri.getAuthority())) && (!paramUri.getPath().startsWith(LOCAL_CONTACT_IMAGE_URI.getPath()));
  }

  public static boolean isLocalContentUri(@Nullable Uri paramUri)
  {
    return "content".equals(getSchemeOrNull(paramUri));
  }

  public static boolean isLocalFileUri(@Nullable Uri paramUri)
  {
    return "file".equals(getSchemeOrNull(paramUri));
  }

  public static boolean isLocalResourceUri(@Nullable Uri paramUri)
  {
    return "res".equals(getSchemeOrNull(paramUri));
  }

  public static boolean isNetworkUri(@Nullable Uri paramUri)
  {
    paramUri = getSchemeOrNull(paramUri);
    return ("https".equals(paramUri)) || ("http".equals(paramUri));
  }

  public static boolean isQualifiedResourceUri(@Nullable Uri paramUri)
  {
    return "android.resource".equals(getSchemeOrNull(paramUri));
  }

  public static Uri parseUriOrNull(@Nullable String paramString)
  {
    if (paramString != null)
      return Uri.parse(paramString);
    return null;
  }

  @Nullable
  public static URL uriToUrl(@Nullable Uri paramUri)
  {
    if (paramUri == null)
      return null;
    try
    {
      paramUri = new URL(paramUri.toString());
      return paramUri;
    }
    catch (MalformedURLException paramUri)
    {
    }
    throw new RuntimeException(paramUri);
  }
}