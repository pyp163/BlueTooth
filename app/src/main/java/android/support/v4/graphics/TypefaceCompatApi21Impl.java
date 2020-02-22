package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.File;

@RequiresApi(21)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl
{
  private static final String TAG = "TypefaceCompatApi21Impl";

  private File getFile(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("/proc/self/fd/");
      localStringBuilder.append(paramParcelFileDescriptor.getFd());
      paramParcelFileDescriptor = Os.readlink(localStringBuilder.toString());
      if (OsConstants.S_ISREG(Os.stat(paramParcelFileDescriptor).st_mode))
      {
        paramParcelFileDescriptor = new File(paramParcelFileDescriptor);
        return paramParcelFileDescriptor;
      }
      return null;
    }
    catch (ErrnoException paramParcelFileDescriptor)
    {
    }
    return null;
  }

  // ERROR //
  public android.graphics.Typeface createFromFontInfo(android.content.Context paramContext, android.os.CancellationSignal paramCancellationSignal, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: aload_3
    //   10: iload 4
    //   12: invokevirtual 83	android/support/v4/graphics/TypefaceCompatApi21Impl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   15: astore_3
    //   16: aload_1
    //   17: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 5
    //   22: aload 5
    //   24: aload_3
    //   25: invokevirtual 95	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   28: ldc 97
    //   30: aload_2
    //   31: invokevirtual 103	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   34: astore_3
    //   35: aload_0
    //   36: aload_3
    //   37: invokespecial 105	android/support/v4/graphics/TypefaceCompatApi21Impl:getFile	(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +28 -> 70
    //   45: aload_2
    //   46: invokevirtual 109	java/io/File:canRead	()Z
    //   49: ifne +6 -> 55
    //   52: goto +18 -> 70
    //   55: aload_2
    //   56: invokestatic 115	android/graphics/Typeface:createFromFile	(Ljava/io/File;)Landroid/graphics/Typeface;
    //   59: astore_1
    //   60: aload_3
    //   61: ifnull +7 -> 68
    //   64: aload_3
    //   65: invokevirtual 118	android/os/ParcelFileDescriptor:close	()V
    //   68: aload_1
    //   69: areturn
    //   70: new 120	java/io/FileInputStream
    //   73: dup
    //   74: aload_3
    //   75: invokevirtual 124	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   78: invokespecial 127	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   81: astore 5
    //   83: aload_0
    //   84: aload_1
    //   85: aload 5
    //   87: invokespecial 131	android/support/v4/graphics/TypefaceCompatBaseImpl:createFromInputStream	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   90: astore_1
    //   91: aload 5
    //   93: ifnull +8 -> 101
    //   96: aload 5
    //   98: invokevirtual 132	java/io/FileInputStream:close	()V
    //   101: aload_3
    //   102: ifnull +7 -> 109
    //   105: aload_3
    //   106: invokevirtual 118	android/os/ParcelFileDescriptor:close	()V
    //   109: aload_1
    //   110: areturn
    //   111: astore_1
    //   112: aconst_null
    //   113: astore_2
    //   114: goto +7 -> 121
    //   117: astore_2
    //   118: aload_2
    //   119: athrow
    //   120: astore_1
    //   121: aload 5
    //   123: ifnull +31 -> 154
    //   126: aload_2
    //   127: ifnull +22 -> 149
    //   130: aload 5
    //   132: invokevirtual 132	java/io/FileInputStream:close	()V
    //   135: goto +19 -> 154
    //   138: astore 5
    //   140: aload_2
    //   141: aload 5
    //   143: invokevirtual 136	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   146: goto +8 -> 154
    //   149: aload 5
    //   151: invokevirtual 132	java/io/FileInputStream:close	()V
    //   154: aload_1
    //   155: athrow
    //   156: astore_1
    //   157: aconst_null
    //   158: astore_2
    //   159: goto +7 -> 166
    //   162: astore_2
    //   163: aload_2
    //   164: athrow
    //   165: astore_1
    //   166: aload_3
    //   167: ifnull +27 -> 194
    //   170: aload_2
    //   171: ifnull +19 -> 190
    //   174: aload_3
    //   175: invokevirtual 118	android/os/ParcelFileDescriptor:close	()V
    //   178: goto +16 -> 194
    //   181: astore_3
    //   182: aload_2
    //   183: aload_3
    //   184: invokevirtual 136	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   187: goto +7 -> 194
    //   190: aload_3
    //   191: invokevirtual 118	android/os/ParcelFileDescriptor:close	()V
    //   194: aload_1
    //   195: athrow
    //   196: astore_1
    //   197: aconst_null
    //   198: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   83	91	111	finally
    //   83	91	117	java/lang/Throwable
    //   118	120	120	finally
    //   130	135	138	java/lang/Throwable
    //   35	41	156	finally
    //   45	52	156	finally
    //   55	60	156	finally
    //   70	83	156	finally
    //   96	101	156	finally
    //   130	135	156	finally
    //   140	146	156	finally
    //   149	154	156	finally
    //   154	156	156	finally
    //   35	41	162	java/lang/Throwable
    //   45	52	162	java/lang/Throwable
    //   55	60	162	java/lang/Throwable
    //   70	83	162	java/lang/Throwable
    //   96	101	162	java/lang/Throwable
    //   140	146	162	java/lang/Throwable
    //   149	154	162	java/lang/Throwable
    //   154	156	162	java/lang/Throwable
    //   163	165	165	finally
    //   174	178	181	java/lang/Throwable
    //   22	35	196	java/io/IOException
    //   64	68	196	java/io/IOException
    //   105	109	196	java/io/IOException
    //   174	178	196	java/io/IOException
    //   182	187	196	java/io/IOException
    //   190	194	196	java/io/IOException
    //   194	196	196	java/io/IOException
  }
}