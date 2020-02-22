package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import javax.annotation.Nullable;

public final class FileLocker
  implements Closeable
{

  @Nullable
  private final FileLock mLock;
  private final FileOutputStream mLockFileOutputStream;

  private FileLocker(File paramFile)
    throws IOException
  {
    this.mLockFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      paramFile = this.mLockFileOutputStream.getChannel().lock();
      if (paramFile == null)
        this.mLockFileOutputStream.close();
      this.mLock = paramFile;
      return;
    }
    finally
    {
      this.mLockFileOutputStream.close();
    }
    throw paramFile;
  }

  public static FileLocker lock(File paramFile)
    throws IOException
  {
    return new FileLocker(paramFile);
  }

  public void close()
    throws IOException
  {
    try
    {
      if (this.mLock != null)
        this.mLock.release();
      return;
    }
    finally
    {
      this.mLockFileOutputStream.close();
    }
  }
}