package com.facebook.soloader;

import android.util.Log;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public abstract class NativeLibrary
{
  private static final String TAG = "com.facebook.soloader.NativeLibrary";
  private boolean mLibrariesLoaded = false;

  @Nullable
  private List<String> mLibraryNames;

  @Nullable
  private volatile UnsatisfiedLinkError mLinkError = null;
  private Boolean mLoadLibraries = Boolean.valueOf(true);
  private final Object mLock = new Object();

  protected NativeLibrary(List<String> paramList)
  {
    this.mLibraryNames = paramList;
  }

  public void ensureLoaded()
    throws UnsatisfiedLinkError
  {
    if (!loadLibraries())
      throw this.mLinkError;
  }

  @Nullable
  public UnsatisfiedLinkError getError()
  {
    return this.mLinkError;
  }

  protected void initialNativeCheck()
    throws UnsatisfiedLinkError
  {
  }

  @Nullable
  public boolean loadLibraries()
  {
    synchronized (this.mLock)
    {
      if (!this.mLoadLibraries.booleanValue())
      {
        bool = this.mLibrariesLoaded;
        return bool;
      }
      try
      {
        if (this.mLibraryNames != null)
        {
          Iterator localIterator = this.mLibraryNames.iterator();
          while (localIterator.hasNext())
            SoLoader.loadLibrary((String)localIterator.next());
        }
        initialNativeCheck();
        this.mLibrariesLoaded = true;
        this.mLibraryNames = null;
      }
      catch (Throwable localThrowable)
      {
        Log.e(TAG, "Failed to load native lib (other error): ", localThrowable);
        this.mLinkError = new UnsatisfiedLinkError("Failed loading libraries");
        this.mLinkError.initCause(localThrowable);
        this.mLibrariesLoaded = false;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Log.e(TAG, "Failed to load native lib (initial check): ", localUnsatisfiedLinkError);
        this.mLinkError = localUnsatisfiedLinkError;
        this.mLibrariesLoaded = false;
      }
      this.mLoadLibraries = Boolean.valueOf(false);
      boolean bool = this.mLibrariesLoaded;
      return bool;
    }
  }
}