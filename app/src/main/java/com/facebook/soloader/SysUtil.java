package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil
{
  private static final byte APK_SIGNATURE_VERSION = 1;

  static int copyBytes(RandomAccessFile paramRandomAccessFile, InputStream paramInputStream, int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(paramArrayOfByte, 0, Math.min(paramArrayOfByte.length, paramInt - i));
      if (j == -1)
        break;
      paramRandomAccessFile.write(paramArrayOfByte, 0, j);
      i += j;
    }
    return i;
  }

  public static void deleteOrThrow(File paramFile)
    throws IOException
  {
    if (!paramFile.delete())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("could not delete file ");
      localStringBuilder.append(paramFile);
      throw new IOException(localStringBuilder.toString());
    }
  }

  public static void dumbDeleteRecursive(File paramFile)
    throws IOException
  {
    Object localObject;
    if (paramFile.isDirectory())
    {
      localObject = paramFile.listFiles();
      if (localObject == null)
        return;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        dumbDeleteRecursive(localObject[i]);
        i += 1;
      }
    }
    if ((!paramFile.delete()) && (paramFile.exists()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("could not delete: ");
      ((StringBuilder)localObject).append(paramFile);
      throw new IOException(((StringBuilder)localObject).toString());
    }
  }

  public static void fallocateIfSupported(FileDescriptor paramFileDescriptor, long paramLong)
    throws IOException
  {
    if (Build.VERSION.SDK_INT >= 21)
      LollipopSysdeps.fallocateIfSupported(paramFileDescriptor, paramLong);
  }

  public static int findAbiScore(String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      if ((paramArrayOfString[i] != null) && (paramString.equals(paramArrayOfString[i])))
        return i;
      i += 1;
    }
    return -1;
  }

  // ERROR //
  static void fsyncRecursive(File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 69	java/io/File:isDirectory	()Z
    //   4: ifeq +66 -> 70
    //   7: aload_0
    //   8: invokevirtual 73	java/io/File:listFiles	()[Ljava/io/File;
    //   11: astore_2
    //   12: aload_2
    //   13: ifnonnull +36 -> 49
    //   16: new 48	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   23: astore_2
    //   24: aload_2
    //   25: ldc 103
    //   27: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_2
    //   32: aload_0
    //   33: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: new 19	java/io/IOException
    //   40: dup
    //   41: aload_2
    //   42: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokespecial 65	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   48: athrow
    //   49: iconst_0
    //   50: istore_1
    //   51: iload_1
    //   52: aload_2
    //   53: arraylength
    //   54: if_icmpge +57 -> 111
    //   57: aload_2
    //   58: iload_1
    //   59: aaload
    //   60: invokestatic 105	com/facebook/soloader/SysUtil:fsyncRecursive	(Ljava/io/File;)V
    //   63: iload_1
    //   64: iconst_1
    //   65: iadd
    //   66: istore_1
    //   67: goto -16 -> 51
    //   70: aload_0
    //   71: invokevirtual 108	java/io/File:getPath	()Ljava/lang/String;
    //   74: ldc 110
    //   76: invokevirtual 114	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   79: ifeq +4 -> 83
    //   82: return
    //   83: new 33	java/io/RandomAccessFile
    //   86: dup
    //   87: aload_0
    //   88: ldc 116
    //   90: invokespecial 119	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   93: astore_3
    //   94: aconst_null
    //   95: astore_0
    //   96: aload_3
    //   97: invokevirtual 123	java/io/RandomAccessFile:getFD	()Ljava/io/FileDescriptor;
    //   100: invokevirtual 128	java/io/FileDescriptor:sync	()V
    //   103: aload_3
    //   104: ifnull +7 -> 111
    //   107: aload_3
    //   108: invokevirtual 131	java/io/RandomAccessFile:close	()V
    //   111: return
    //   112: astore_2
    //   113: goto +8 -> 121
    //   116: astore_2
    //   117: aload_2
    //   118: astore_0
    //   119: aload_2
    //   120: athrow
    //   121: aload_3
    //   122: ifnull +27 -> 149
    //   125: aload_0
    //   126: ifnull +19 -> 145
    //   129: aload_3
    //   130: invokevirtual 131	java/io/RandomAccessFile:close	()V
    //   133: goto +16 -> 149
    //   136: astore_3
    //   137: aload_0
    //   138: aload_3
    //   139: invokevirtual 135	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   142: goto +7 -> 149
    //   145: aload_3
    //   146: invokevirtual 131	java/io/RandomAccessFile:close	()V
    //   149: aload_2
    //   150: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   96	103	112	finally
    //   119	121	112	finally
    //   96	103	116	java/lang/Throwable
    //   129	133	136	java/lang/Throwable
  }

  public static int getAppVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager != null);
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
      return 0;
    }
    catch (PackageManager.NameNotFoundException|RuntimeException paramContext)
    {
    }
    return 0;
  }

  public static String[] getSupportedAbis()
  {
    if (Build.VERSION.SDK_INT < 21)
      return new String[] { Build.CPU_ABI, Build.CPU_ABI2 };
    return LollipopSysdeps.getSupportedAbis();
  }

  public static byte[] makeApkDepBlock(File paramFile, Context paramContext)
    throws IOException
  {
    File localFile = paramFile.getCanonicalFile();
    paramFile = Parcel.obtain();
    try
    {
      paramFile.writeByte((byte)1);
      paramFile.writeString(localFile.getPath());
      paramFile.writeLong(localFile.lastModified());
      paramFile.writeInt(getAppVersionCode(paramContext));
      paramContext = paramFile.marshall();
      return paramContext;
    }
    finally
    {
      paramFile.recycle();
    }
    throw paramContext;
  }

  static void mkdirOrThrow(File paramFile)
    throws IOException
  {
    if ((!paramFile.mkdirs()) && (!paramFile.isDirectory()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("cannot mkdir: ");
      localStringBuilder.append(paramFile);
      throw new IOException(localStringBuilder.toString());
    }
  }

  private static final class LollipopSysdeps
  {
    @DoNotOptimize
    public static void fallocateIfSupported(FileDescriptor paramFileDescriptor, long paramLong)
      throws IOException
    {
      try
      {
        Os.posix_fallocate(paramFileDescriptor, 0L, paramLong);
        return;
      }
      catch (ErrnoException paramFileDescriptor)
      {
        if ((paramFileDescriptor.errno != OsConstants.EOPNOTSUPP) && (paramFileDescriptor.errno != OsConstants.ENOSYS) && (paramFileDescriptor.errno != OsConstants.EINVAL))
          throw new IOException(paramFileDescriptor.toString(), paramFileDescriptor);
      }
    }

    @DoNotOptimize
    public static String[] getSupportedAbis()
    {
      return Build.SUPPORTED_ABIS;
    }
  }
}