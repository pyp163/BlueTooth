package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Parcel;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class ApkSoSource extends ExtractFromZipSoSource
{
  private static final byte APK_SO_SOURCE_SIGNATURE_VERSION = 2;
  private static final byte LIBS_DIR_DOESNT_EXIST = 1;
  private static final byte LIBS_DIR_DONT_CARE = 0;
  private static final byte LIBS_DIR_SNAPSHOT = 2;
  public static final int PREFER_ANDROID_LIBS_DIRECTORY = 1;
  private static final String TAG = "ApkSoSource";
  private final int mFlags;

  public ApkSoSource(Context paramContext, String paramString, int paramInt)
  {
    super(paramContext, paramString, new File(paramContext.getApplicationInfo().sourceDir), "^lib/([^/]+)/([^/]+\\.so)$");
    this.mFlags = paramInt;
  }

  protected byte[] getDepsBlock()
    throws IOException
  {
    Object localObject1 = this.mZipFileName.getCanonicalFile();
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeByte((byte)2);
      localParcel.writeString(((File)localObject1).getPath());
      localParcel.writeLong(((File)localObject1).lastModified());
      localParcel.writeInt(SysUtil.getAppVersionCode(this.mContext));
      if ((this.mFlags & 0x1) == 0)
      {
        localParcel.writeByte((byte)0);
        localObject1 = localParcel.marshall();
        return localObject1;
      }
      localObject1 = this.mContext.getApplicationInfo().nativeLibraryDir;
      if (localObject1 == null)
      {
        localParcel.writeByte((byte)1);
        localObject1 = localParcel.marshall();
        return localObject1;
      }
      localObject1 = new File((String)localObject1).getCanonicalFile();
      if (!((File)localObject1).exists())
      {
        localParcel.writeByte((byte)1);
        localObject1 = localParcel.marshall();
        return localObject1;
      }
      localParcel.writeByte((byte)2);
      localParcel.writeString(((File)localObject1).getPath());
      localParcel.writeLong(((File)localObject1).lastModified());
      localObject1 = localParcel.marshall();
      return localObject1;
    }
    finally
    {
      localParcel.recycle();
    }
  }

  protected UnpackingSoSource.Unpacker makeUnpacker()
    throws IOException
  {
    return new ApkUnpacker(this);
  }

  protected class ApkUnpacker extends ExtractFromZipSoSource.ZipUnpacker
  {
    private final int mFlags = ApkSoSource.this.mFlags;
    private File mLibDir = new File(ApkSoSource.this.mContext.getApplicationInfo().nativeLibraryDir);

    ApkUnpacker(ExtractFromZipSoSource arg2)
      throws IOException
    {
      super(localUnpackingSoSource);
    }

    protected boolean shouldExtract(ZipEntry paramZipEntry, String paramString)
    {
      String str = paramZipEntry.getName();
      boolean bool2 = paramString.equals(ApkSoSource.this.mCorruptedLib);
      boolean bool1 = true;
      if (bool2)
      {
        ApkSoSource.this.mCorruptedLib = null;
        paramZipEntry = String.format("allowing consideration of corrupted lib %s", new Object[] { paramString });
      }
      else if ((this.mFlags & 0x1) == 0)
      {
        paramZipEntry = new StringBuilder();
        paramZipEntry.append("allowing consideration of ");
        paramZipEntry.append(str);
        paramZipEntry.append(": self-extraction preferred");
        paramZipEntry = paramZipEntry.toString();
      }
      else
      {
        File localFile = new File(this.mLibDir, paramString);
        if (!localFile.isFile())
        {
          paramZipEntry = String.format("allowing considering of %s: %s not in system lib dir", new Object[] { str, paramString });
        }
        else
        {
          long l1 = localFile.length();
          long l2 = paramZipEntry.getSize();
          if (l1 != l2)
          {
            paramZipEntry = String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", new Object[] { localFile, Long.valueOf(l1), Long.valueOf(l2) });
          }
          else
          {
            paramZipEntry = new StringBuilder();
            paramZipEntry.append("not allowing consideration of ");
            paramZipEntry.append(str);
            paramZipEntry.append(": deferring to libdir");
            paramZipEntry = paramZipEntry.toString();
            bool1 = false;
          }
        }
      }
      Log.d("ApkSoSource", paramZipEntry);
      return bool1;
    }
  }
}