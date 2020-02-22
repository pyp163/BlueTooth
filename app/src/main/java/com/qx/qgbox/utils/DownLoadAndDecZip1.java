package com.qx.qgbox.utils;

import android.os.AsyncTask;
import android.os.Handler;
import com.qx.qgbox.activity.UpdateFirmwareActivity;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DownLoadAndDecZip1 extends AsyncTask<Void, Void, Boolean>
{
  private String decZipToFile;
  private String downUrl;

  public DownLoadAndDecZip1(String paramString1, String paramString2)
  {
    this.downUrl = paramString1;
    this.decZipToFile = paramString2;
  }

  public void deleteDir(File paramFile)
  {
    if (!paramFile.exists())
      return;
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if ((arrayOfFile == null) || (arrayOfFile.length == 0))
        paramFile.delete();
      int i = 0;
      while (i < arrayOfFile.length)
      {
        deleteDir(arrayOfFile[i]);
        i += 1;
      }
    }
    paramFile.delete();
  }

  protected Boolean doInBackground(Void[] paramArrayOfVoid)
  {
    new File(this.decZipToFile);
    while (true)
    {
      if (this.downUrl != null);
      try
      {
        paramArrayOfVoid = (HttpURLConnection)new URL(this.downUrl).openConnection();
        paramArrayOfVoid.setConnectTimeout(5000);
        paramArrayOfVoid.setReadTimeout(5000);
        paramArrayOfVoid.connect();
        InputStream localInputStream = paramArrayOfVoid.getInputStream();
        int i = paramArrayOfVoid.getContentLength();
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(this.decZipToFile);
        ((StringBuilder)localObject).append("dfu.zip");
        localObject = new File(((StringBuilder)localObject).toString());
        if (((File)localObject).exists())
          deleteDir((File)localObject);
        ((File)localObject).setReadable(true);
        ((File)localObject).setWritable(true);
        ((File)localObject).setExecutable(true);
        FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject);
        byte[] arrayOfByte = new byte[256];
        while (true)
        {
          int j = localInputStream.read(arrayOfByte);
          if (j == -1)
            break;
          localFileOutputStream.write(arrayOfByte, 0, j);
        }
        localInputStream.close();
        localFileOutputStream.flush();
        localFileOutputStream.close();
        paramArrayOfVoid.disconnect();
        if (i == ((File)localObject).length())
        {
          UpdateFirmwareActivity.getInstance().getMyHandler().sendEmptyMessage(4);
          this.downUrl = null;
          return null;
        }
        ((File)localObject).delete();
        continue;
        label242: this.downUrl = null;
        UpdateFirmwareActivity.getInstance().getMyHandler().sendEmptyMessageDelayed(8, 300L);
        continue;
        label265: this.downUrl = null;
        UpdateFirmwareActivity.getInstance().getMyHandler().sendEmptyMessageDelayed(8, 300L);
        continue;
        label288: this.downUrl = null;
        UpdateFirmwareActivity.getInstance().getMyHandler().sendEmptyMessageDelayed(8, 300L);
        continue;
        label311: this.downUrl = null;
        UpdateFirmwareActivity.getInstance().getMyHandler().sendEmptyMessageDelayed(8, 300L);
        continue;
        return null;
      }
      catch (MalformedURLException paramArrayOfVoid)
      {
        break label311;
      }
      catch (IllegalArgumentException paramArrayOfVoid)
      {
        break label288;
      }
      catch (IOException paramArrayOfVoid)
      {
        break label265;
      }
      catch (Exception paramArrayOfVoid)
      {
        break label242;
      }
    }
  }

  public File getRealFileName(String paramString1, String paramString2)
  {
    String[] arrayOfString = paramString2.split("/");
    paramString1 = new File(paramString1);
    if (arrayOfString.length > 1)
    {
      int i = 0;
      while (i < arrayOfString.length - 1)
      {
        paramString2 = arrayOfString[i];
        try
        {
          String str1 = new String(paramString2.getBytes("8859_1"), "GB2312");
          paramString2 = str1;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
          localUnsupportedEncodingException1.printStackTrace();
        }
        paramString1 = new File(paramString1, paramString2);
        i += 1;
      }
      if (!paramString1.exists())
        paramString1.mkdirs();
      paramString2 = arrayOfString[(arrayOfString.length - 1)];
      try
      {
        String str2 = new String(paramString2.getBytes("8859_1"), "GB2312");
        paramString2 = str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException2)
      {
        localUnsupportedEncodingException2.printStackTrace();
      }
      return new File(paramString1, paramString2);
    }
    return paramString1;
  }

  protected void onPostExecute(Boolean paramBoolean)
  {
    super.onPostExecute(paramBoolean);
  }

  public void upZipFile(File paramFile, String paramString)
    throws IOException
  {
    Object localObject1 = new File(paramString);
    if (!((File)localObject1).exists())
      ((File)localObject1).mkdir();
    paramFile = new ZipFile(paramFile);
    localObject1 = paramFile.entries();
    byte[] arrayOfByte = new byte[1024];
    while (((Enumeration)localObject1).hasMoreElements())
    {
      Object localObject2 = (ZipEntry)((Enumeration)localObject1).nextElement();
      Object localObject3;
      if (((ZipEntry)localObject2).isDirectory())
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(paramString);
        ((StringBuilder)localObject3).append(((ZipEntry)localObject2).getName());
        new File(new String(((StringBuilder)localObject3).toString().getBytes("8859_1"), "GB2312"));
      }
      else if (!getRealFileName(paramString, ((ZipEntry)localObject2).getName()).exists())
      {
        localObject3 = new BufferedOutputStream(new FileOutputStream(getRealFileName(paramString, ((ZipEntry)localObject2).getName())));
        localObject2 = new BufferedInputStream(paramFile.getInputStream((ZipEntry)localObject2));
        while (true)
        {
          int i = ((InputStream)localObject2).read(arrayOfByte, 0, 1024);
          if (i == -1)
            break;
          ((OutputStream)localObject3).write(arrayOfByte, 0, i);
        }
        ((InputStream)localObject2).close();
        ((OutputStream)localObject3).close();
      }
    }
    paramFile.close();
  }
}
