package com.qx.qgbox.download;

import android.os.AsyncTask;
import android.os.Handler;
import com.qx.qgbox.activity.UpdateAppActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewDownLoadAPK extends AsyncTask<Void, Void, Boolean>
{
  private String downLoadUrl;
  private String localPath;

  public NewDownLoadAPK(String paramString1, String paramString2)
  {
    this.downLoadUrl = paramString1;
    this.localPath = paramString2;
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
    while (this.downLoadUrl != null)
      try
      {
        paramArrayOfVoid = (HttpURLConnection)new URL(this.downLoadUrl).openConnection();
        paramArrayOfVoid.setConnectTimeout(5000);
        paramArrayOfVoid.setReadTimeout(5000);
        paramArrayOfVoid.connect();
        InputStream localInputStream = paramArrayOfVoid.getInputStream();
        int i = paramArrayOfVoid.getContentLength();
        File localFile = new File(this.localPath);
        if (localFile.exists())
          deleteDir(localFile);
        localFile.setReadable(true);
        localFile.setWritable(true);
        localFile.setExecutable(true);
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
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
        if (i == localFile.length())
        {
          UpdateAppActivity.getInstance().getMyHandler().sendEmptyMessage(0);
          this.downLoadUrl = null;
          return null;
        }
        localFile.delete();
      }
      catch (Exception paramArrayOfVoid)
      {
        paramArrayOfVoid.printStackTrace();
      }
      catch (IOException paramArrayOfVoid)
      {
        paramArrayOfVoid.printStackTrace();
      }
      catch (IllegalArgumentException paramArrayOfVoid)
      {
        paramArrayOfVoid.printStackTrace();
      }
      catch (MalformedURLException paramArrayOfVoid)
      {
        paramArrayOfVoid.printStackTrace();
      }
    return null;
  }

  protected void onPostExecute(Boolean paramBoolean)
  {
    super.onPostExecute(paramBoolean);
  }
}
