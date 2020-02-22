package com.qx.qgbox.service;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SuperRunningPackage
{
  public static final int AID_APP = 10000;
  public static final int AID_USER = 100000;

  public static String getForegroundApp()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("VersionCode:");
    ((StringBuilder)localObject1).append(Build.VERSION.SDK_INT);
    Log.e("PKG", ((StringBuilder)localObject1).toString());
    File[] arrayOfFile = new File("/proc").listFiles();
    int n = arrayOfFile.length;
    Object localObject4 = null;
    int i = 0;
    int j = 2147483647;
    while (true)
    {
      int k;
      if (i < n)
      {
        localObject1 = arrayOfFile[i];
        if (!((File)localObject1).isDirectory())
        {
          localObject1 = localObject4;
          k = j;
        }
      }
      try
      {
        int m = Integer.parseInt(((File)localObject1).getName());
        Object localObject2;
        try
        {
          String[] arrayOfString = read(String.format("/proc/%d/cgroup", new Object[] { Integer.valueOf(m) })).split("/n");
          k = 0;
          while (k < arrayOfString.length)
          {
            Log.e("PKG", arrayOfString[k]);
            k += 1;
          }
          String str;
          if (arrayOfString.length == 2)
          {
            str = arrayOfString[0];
            localObject1 = arrayOfString[1];
          }
          else if (arrayOfString.length == 3)
          {
            str = arrayOfString[0];
            localObject1 = arrayOfString[2];
          }
          else
          {
            localObject1 = localObject4;
            k = j;
            if (arrayOfString.length != 5)
              break label465;
            str = arrayOfString[2];
            localObject1 = arrayOfString[4];
          }
          if (!((String)localObject1).endsWith(Integer.toString(m)))
          {
            localObject1 = localObject4;
            k = j;
          }
          else if (str.endsWith("bg_non_interactive"))
          {
            localObject1 = localObject4;
            k = j;
          }
          else
          {
            str = read(String.format("/proc/%d/cmdline", new Object[] { Integer.valueOf(m) }));
            if (str.contains("com.android.systemui"))
            {
              localObject1 = localObject4;
              k = j;
            }
            else
            {
              k = Integer.parseInt(localObject1.split(":")[2].split("/")[1].replace("uid_", ""));
              if ((k < 1000) || (k > 1038))
                break label492;
              localObject1 = localObject4;
              k = j;
              break label465;
              localObject1 = new File(String.format("/proc/%d/oom_score_adj", new Object[] { Integer.valueOf(m) }));
              if ((((File)localObject1).canRead()) && (Integer.parseInt(read(((File)localObject1).getAbsolutePath())) != 0))
              {
                localObject1 = localObject4;
                k = j;
              }
              else
              {
                m = Integer.parseInt(read(String.format("/proc/%d/oom_score", new Object[] { Integer.valueOf(m) })));
                localObject1 = localObject4;
                k = j;
                if (m < j)
                {
                  k = m;
                  localObject1 = str;
                }
              }
            }
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          k = j;
          localObject2 = localObject4;
        }
        label465: i += 1;
        localObject4 = localObject2;
        j = k;
        continue;
        return localObject4;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
        {
          Object localObject3 = localObject4;
          k = j;
          continue;
          label492: k -= 10000;
          while (k > 100000)
            k -= 100000;
          if (k < 0)
          {
            localObject3 = localObject4;
            k = j;
          }
        }
      }
    }
  }

  private static String read(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = new BufferedReader(new FileReader(paramString));
    localStringBuilder.append(paramString.readLine());
    while (true)
    {
      String str = paramString.readLine();
      if (str == null)
        break;
      localStringBuilder.append("/n");
      localStringBuilder.append(str);
    }
    paramString.close();
    return localStringBuilder.toString().trim();
  }
}
