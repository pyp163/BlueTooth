package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

public class ApplicationSoSource extends SoSource
{
  private Context applicationContext;
  private int flags;
  private DirectorySoSource soSource;

  public ApplicationSoSource(Context paramContext, int paramInt)
  {
    this.applicationContext = paramContext.getApplicationContext();
    if (this.applicationContext == null)
    {
      Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
      this.applicationContext = paramContext;
    }
    this.flags = paramInt;
    this.soSource = new DirectorySoSource(new File(this.applicationContext.getApplicationInfo().nativeLibraryDir), paramInt);
  }

  public void addToLdLibraryPath(Collection<String> paramCollection)
  {
    this.soSource.addToLdLibraryPath(paramCollection);
  }

  public boolean checkAndMaybeUpdate()
    throws IOException
  {
    try
    {
      File localFile1 = this.soSource.soDirectory;
      Context localContext = this.applicationContext.createPackageContext(this.applicationContext.getPackageName(), 0);
      File localFile2 = new File(localContext.getApplicationInfo().nativeLibraryDir);
      if (!localFile1.equals(localFile2))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Native library directory updated from ");
        localStringBuilder.append(localFile1);
        localStringBuilder.append(" to ");
        localStringBuilder.append(localFile2);
        Log.d("SoLoader", localStringBuilder.toString());
        this.flags |= 1;
        this.soSource = new DirectorySoSource(localFile2, this.flags);
        this.soSource.prepare(this.flags);
        this.applicationContext = localContext;
        return true;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException(localNameNotFoundException);
    }
  }

  public int loadLibrary(String paramString, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    return this.soSource.loadLibrary(paramString, paramInt, paramThreadPolicy);
  }

  protected void prepare(int paramInt)
    throws IOException
  {
    this.soSource.prepare(paramInt);
  }

  public String toString()
  {
    return this.soSource.toString();
  }

  @Nullable
  public File unpackLibrary(String paramString)
    throws IOException
  {
    return this.soSource.unpackLibrary(paramString);
  }
}