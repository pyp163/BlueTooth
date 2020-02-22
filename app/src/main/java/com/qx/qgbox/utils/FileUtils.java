package com.qx.qgbox.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.os.SystemClock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils
{
  public static void copyAssetDirToFiles(Context paramContext, String paramString)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext.getFilesDir());
    ((StringBuilder)localObject).append("/");
    ((StringBuilder)localObject).append(paramString);
    new File(((StringBuilder)localObject).toString()).mkdir();
    localObject = paramContext.getAssets();
    String[] arrayOfString = ((AssetManager)localObject).list(paramString);
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append('/');
      localStringBuilder.append(str);
      str = localStringBuilder.toString();
      if (((AssetManager)localObject).list(str).length == 0)
        try
        {
          copyAssetFileToFiles(paramContext, str);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      else
        copyAssetDirToFiles(paramContext, localInterruptedException);
      i += 1;
    }
  }

  public static void copyAssetFileToFiles(Context paramContext, String paramString)
    throws IOException, InterruptedException
  {
    Object localObject2 = paramContext.getAssets().open(paramString);
    Object localObject1 = new byte[((InputStream)localObject2).available()];
    ((InputStream)localObject2).read((byte[])localObject1);
    ((InputStream)localObject2).close();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramContext.getFilesDir());
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(paramString);
    localObject2 = new File(((StringBuilder)localObject2).toString());
    ((File)localObject2).createNewFile();
    localObject2 = new FileOutputStream((File)localObject2);
    ((FileOutputStream)localObject2).write((byte[])localObject1);
    ((FileOutputStream)localObject2).close();
    localObject1 = Runtime.getRuntime();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("chmod 777 ");
    ((StringBuilder)localObject2).append(paramContext.getFilesDir());
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(paramString);
    ((Runtime)localObject1).exec(((StringBuilder)localObject2).toString()).waitFor();
  }

  public static void copyAssetFileToshare(Context paramContext, String paramString)
    throws IOException, InterruptedException
  {
    Object localObject = paramContext.getAssets().open(paramString);
    byte[] arrayOfByte = new byte[((InputStream)localObject).available()];
    ((InputStream)localObject).read(arrayOfByte);
    ((InputStream)localObject).close();
    localObject = Runtime.getRuntime();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mkdir -p /data/data/");
    localStringBuilder.append(paramContext.getPackageName().toString());
    localStringBuilder.append("/shared_prefs ");
    ((Runtime)localObject).exec(localStringBuilder.toString());
    SystemClock.sleep(1000L);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("/data/data/");
    ((StringBuilder)localObject).append(paramContext.getPackageName().toString());
    ((StringBuilder)localObject).append("/shared_prefs");
    paramContext = new File(((StringBuilder)localObject).toString(), paramString);
    if (!paramContext.exists())
    {
      paramContext.createNewFile();
      paramContext = new FileOutputStream(paramContext);
      paramContext.write(arrayOfByte);
      paramContext.close();
    }
  }

  public static void copyFilesFromassets(Context paramContext, int paramInt)
    throws IOException
  {
    Object localObject1;
    if (paramInt == 1)
      localObject1 = paramContext.getAssets().list("profile");
    else
      localObject1 = null;
    if (paramInt == 2)
      localObject1 = paramContext.getAssets().list("profile1");
    if (paramInt == 3)
      localObject1 = paramContext.getAssets().list("profile2");
    int i = localObject1.length;
    int j = 0;
    File localFile;
    Object localObject3;
    if (i != 0)
    {
      i = 0;
      while (i < localObject1.length)
      {
        localFile = new File(paramContext.getFilesDir().getPath(), localObject1[i]);
        if (!localFile.exists())
        {
          if (paramInt == 1)
          {
            localObject2 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("profile/");
            ((StringBuilder)localObject3).append(localObject1[i]);
            localObject2 = ((AssetManager)localObject2).open(((StringBuilder)localObject3).toString());
          }
          else
          {
            localObject2 = null;
          }
          if (paramInt == 2)
          {
            localObject2 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("profile1/");
            ((StringBuilder)localObject3).append(localObject1[i]);
            localObject2 = ((AssetManager)localObject2).open(((StringBuilder)localObject3).toString());
          }
          if (paramInt == 3)
          {
            localObject2 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("profile2/");
            ((StringBuilder)localObject3).append(localObject1[i]);
            localObject2 = ((AssetManager)localObject2).open(((StringBuilder)localObject3).toString());
          }
          localObject3 = new byte[((InputStream)localObject2).available()];
          ((InputStream)localObject2).read((byte[])localObject3);
          ((InputStream)localObject2).close();
          localFile.createNewFile();
          localObject2 = new FileOutputStream(localFile);
          ((FileOutputStream)localObject2).write((byte[])localObject3);
          ((FileOutputStream)localObject2).close();
        }
        SystemClock.sleep(500L);
        i += 1;
      }
    }
    if (paramInt == 1)
      localObject1 = paramContext.getAssets().list("gpprofile");
    if (paramInt == 2)
      localObject1 = paramContext.getAssets().list("gpprofile1");
    Object localObject2 = localObject1;
    if (paramInt == 3)
      localObject2 = paramContext.getAssets().list("gpprofile2");
    if (localObject2.length != 0)
    {
      i = j;
      while (i < localObject2.length)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramContext.getApplicationInfo().dataDir);
        ((StringBuilder)localObject1).append("/files1");
        localFile = new File(((StringBuilder)localObject1).toString(), localObject2[i]);
        if (!localFile.exists())
        {
          if (paramInt == 1)
          {
            localObject1 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("gpprofile/");
            ((StringBuilder)localObject3).append(localObject2[i]);
            localObject1 = ((AssetManager)localObject1).open(((StringBuilder)localObject3).toString());
          }
          else
          {
            localObject1 = null;
          }
          if (paramInt == 2)
          {
            localObject1 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("gpprofile1/");
            ((StringBuilder)localObject3).append(localObject2[i]);
            localObject1 = ((AssetManager)localObject1).open(((StringBuilder)localObject3).toString());
          }
          if (paramInt == 3)
          {
            localObject1 = paramContext.getAssets();
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("gpprofile2/");
            ((StringBuilder)localObject3).append(localObject2[i]);
            localObject1 = ((AssetManager)localObject1).open(((StringBuilder)localObject3).toString());
          }
          localObject3 = new byte[((InputStream)localObject1).available()];
          ((InputStream)localObject1).read((byte[])localObject3);
          ((InputStream)localObject1).close();
          localFile.createNewFile();
          localObject1 = new FileOutputStream(localFile);
          ((FileOutputStream)localObject1).write((byte[])localObject3);
          ((FileOutputStream)localObject1).close();
        }
        SystemClock.sleep(500L);
        i += 1;
      }
    }
  }
}
