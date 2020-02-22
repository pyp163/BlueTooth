package com.facebook.soloader;

import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;

public class DirectorySoSource extends SoSource
{
  public static final int ON_LD_LIBRARY_PATH = 2;
  public static final int RESOLVE_DEPENDENCIES = 1;
  protected final int flags;
  protected final File soDirectory;

  public DirectorySoSource(File paramFile, int paramInt)
  {
    this.soDirectory = paramFile;
    this.flags = paramInt;
  }

  private static String[] getDependencies(File paramFile)
    throws IOException
  {
    if (SoLoader.SYSTRACE_LIBRARY_LOADING)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SoLoader.getElfDependencies[");
      localStringBuilder.append(paramFile.getName());
      localStringBuilder.append("]");
      Api18TraceUtils.beginTraceSection(localStringBuilder.toString());
    }
    try
    {
      paramFile = MinElf.extract_DT_NEEDED(paramFile);
      return paramFile;
    }
    finally
    {
      if (SoLoader.SYSTRACE_LIBRARY_LOADING)
        Api18TraceUtils.endSection();
    }
    throw paramFile;
  }

  private void loadDependencies(File paramFile, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    paramFile = getDependencies(paramFile);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Loading lib dependencies: ");
    localStringBuilder.append(Arrays.toString(paramFile));
    Log.d("SoLoader", localStringBuilder.toString());
    int i = 0;
    while (i < paramFile.length)
    {
      localStringBuilder = paramFile[i];
      if (!localStringBuilder.startsWith("/"))
        SoLoader.loadLibraryBySoName(localStringBuilder, paramInt | 0x1, paramThreadPolicy);
      i += 1;
    }
  }

  public void addToLdLibraryPath(Collection<String> paramCollection)
  {
    paramCollection.add(this.soDirectory.getAbsolutePath());
  }

  public int loadLibrary(String paramString, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    return loadLibraryFrom(paramString, paramInt, this.soDirectory, paramThreadPolicy);
  }

  protected int loadLibraryFrom(String paramString, int paramInt, File paramFile, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    File localFile = new File(paramFile, paramString);
    if (!localFile.exists())
    {
      paramThreadPolicy = new StringBuilder();
      paramThreadPolicy.append(paramString);
      paramThreadPolicy.append(" not found on ");
      paramThreadPolicy.append(paramFile.getCanonicalPath());
      Log.d("SoLoader", paramThreadPolicy.toString());
      return 0;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" found on ");
    localStringBuilder.append(paramFile.getCanonicalPath());
    Log.d("SoLoader", localStringBuilder.toString());
    if (((paramInt & 0x1) != 0) && ((this.flags & 0x2) != 0))
    {
      paramFile = new StringBuilder();
      paramFile.append(paramString);
      paramFile.append(" loaded implicitly");
      Log.d("SoLoader", paramFile.toString());
      return 2;
    }
    if ((this.flags & 0x1) != 0)
    {
      loadDependencies(localFile, paramInt, paramThreadPolicy);
    }
    else
    {
      paramFile = new StringBuilder();
      paramFile.append("Not resolving dependencies for ");
      paramFile.append(paramString);
      Log.d("SoLoader", paramFile.toString());
    }
    try
    {
      SoLoader.sSoFileLoader.load(localFile.getAbsolutePath(), paramInt);
      return 1;
    }
    catch (UnsatisfiedLinkError paramString)
    {
      if (paramString.getMessage().contains("bad ELF magic"))
      {
        Log.d("SoLoader", "Corrupted lib file detected");
        return 3;
      }
    }
    throw paramString;
  }

  public String toString()
  {
    try
    {
      String str = String.valueOf(this.soDirectory.getCanonicalPath());
      break label22;
      label14: str = this.soDirectory.getName();
      label22: StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(getClass().getName());
      localStringBuilder.append("[root = ");
      localStringBuilder.append(str);
      localStringBuilder.append(" flags = ");
      localStringBuilder.append(this.flags);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    catch (IOException localIOException)
    {
      break label14;
    }
  }

  @Nullable
  public File unpackLibrary(String paramString)
    throws IOException
  {
    paramString = new File(this.soDirectory, paramString);
    if (paramString.exists())
      return paramString;
    return null;
  }
}