package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile
{
  private final File mBackupName;
  private final File mBaseName;

  public AtomicFile(@NonNull File paramFile)
  {
    this.mBaseName = paramFile;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramFile.getPath());
    localStringBuilder.append(".bak");
    this.mBackupName = new File(localStringBuilder.toString());
  }

  private static boolean sync(@NonNull FileOutputStream paramFileOutputStream)
  {
    try
    {
      paramFileOutputStream.getFD().sync();
      return true;
      label9: return false;
    }
    catch (IOException paramFileOutputStream)
    {
      break label9;
    }
  }

  public void delete()
  {
    this.mBaseName.delete();
    this.mBackupName.delete();
  }

  public void failWrite(@Nullable FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        this.mBaseName.delete();
        this.mBackupName.renameTo(this.mBaseName);
        return;
      }
      catch (IOException paramFileOutputStream)
      {
        Log.w("AtomicFile", "failWrite: Got exception:", paramFileOutputStream);
      }
    }
  }

  public void finishWrite(@Nullable FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        this.mBackupName.delete();
        return;
      }
      catch (IOException paramFileOutputStream)
      {
        Log.w("AtomicFile", "finishWrite: Got exception:", paramFileOutputStream);
      }
    }
  }

  @NonNull
  public File getBaseFile()
  {
    return this.mBaseName;
  }

  @NonNull
  public FileInputStream openRead()
    throws FileNotFoundException
  {
    if (this.mBackupName.exists())
    {
      this.mBaseName.delete();
      this.mBackupName.renameTo(this.mBaseName);
    }
    return new FileInputStream(this.mBaseName);
  }

  @NonNull
  public byte[] readFully()
    throws IOException
  {
    FileInputStream localFileInputStream = openRead();
    try
    {
      Object localObject1 = new byte[localFileInputStream.available()];
      int i = 0;
      while (true)
      {
        int j = localFileInputStream.read((byte[])localObject1, i, localObject1.length - i);
        if (j <= 0)
          return localObject1;
        j = i + j;
        int k = localFileInputStream.available();
        i = j;
        if (k > localObject1.length - j)
        {
          byte[] arrayOfByte = new byte[k + j];
          System.arraycopy(localObject1, 0, arrayOfByte, 0, j);
          localObject1 = arrayOfByte;
          i = j;
        }
      }
    }
    finally
    {
      localFileInputStream.close();
    }
  }

  @NonNull
  public FileOutputStream startWrite()
    throws IOException
  {
    Object localObject;
    if (this.mBaseName.exists())
      if (!this.mBackupName.exists())
      {
        if (!this.mBaseName.renameTo(this.mBackupName))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Couldn't rename file ");
          ((StringBuilder)localObject).append(this.mBaseName);
          ((StringBuilder)localObject).append(" to backup file ");
          ((StringBuilder)localObject).append(this.mBackupName);
          Log.w("AtomicFile", ((StringBuilder)localObject).toString());
        }
      }
      else
        this.mBaseName.delete();
    try
    {
      localObject = new FileOutputStream(this.mBaseName);
      return localObject;
      if (!this.mBaseName.getParentFile().mkdirs())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Couldn't create directory ");
        ((StringBuilder)localObject).append(this.mBaseName);
        throw new IOException(((StringBuilder)localObject).toString());
      }
    }
    catch (FileNotFoundException localFileNotFoundException1)
    {
      try
      {
        localObject = new FileOutputStream(this.mBaseName);
        return localObject;
        label172: localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Couldn't create ");
        ((StringBuilder)localObject).append(this.mBaseName);
        throw new IOException(((StringBuilder)localObject).toString());
        localFileNotFoundException1 = localFileNotFoundException1;
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
        break label172;
      }
    }
  }
}