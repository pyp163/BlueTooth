package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil
{
  private static final String CACHE_FILE_PREFIX = ".font";
  private static final String TAG = "TypefaceCompatUtil";

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
    }
  }

  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt)
  {
    paramContext = getTempFile(paramContext);
    if (paramContext == null)
      return null;
    try
    {
      boolean bool = copyToFile(paramContext, paramResources, paramInt);
      if (!bool)
        return null;
      paramResources = mmap(paramContext);
      return paramResources;
    }
    finally
    {
      paramContext.delete();
    }
    throw paramResources;
  }

  // ERROR //
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: iload_2
    //   2: invokevirtual 59	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   5: astore_1
    //   6: aload_0
    //   7: aload_1
    //   8: invokestatic 62	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Ljava/io/InputStream;)Z
    //   11: istore_3
    //   12: aload_1
    //   13: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   16: iload_3
    //   17: ireturn
    //   18: astore_0
    //   19: goto +6 -> 25
    //   22: astore_0
    //   23: aconst_null
    //   24: astore_1
    //   25: aload_1
    //   26: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   29: aload_0
    //   30: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   6	12	18	finally
    //   0	6	22	finally
  }

  // ERROR //
  public static boolean copyToFile(File paramFile, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: invokestatic 70	android/os/StrictMode:allowThreadDiskWrites	()Landroid/os/StrictMode$ThreadPolicy;
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: aconst_null
    //   9: astore_3
    //   10: new 72	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: iconst_0
    //   16: invokespecial 75	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   19: astore_0
    //   20: sipush 1024
    //   23: newarray byte
    //   25: astore_3
    //   26: aload_1
    //   27: aload_3
    //   28: invokevirtual 81	java/io/InputStream:read	([B)I
    //   31: istore_2
    //   32: iload_2
    //   33: iconst_m1
    //   34: if_icmpeq +13 -> 47
    //   37: aload_0
    //   38: aload_3
    //   39: iconst_0
    //   40: iload_2
    //   41: invokevirtual 85	java/io/FileOutputStream:write	([BII)V
    //   44: goto -18 -> 26
    //   47: aload_0
    //   48: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   51: aload 5
    //   53: invokestatic 89	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   56: iconst_1
    //   57: ireturn
    //   58: astore_1
    //   59: aload_0
    //   60: astore_3
    //   61: aload_1
    //   62: astore_0
    //   63: goto +72 -> 135
    //   66: astore_1
    //   67: goto +11 -> 78
    //   70: astore_0
    //   71: goto +64 -> 135
    //   74: astore_1
    //   75: aload 4
    //   77: astore_0
    //   78: aload_0
    //   79: astore_3
    //   80: new 91	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   87: astore 4
    //   89: aload_0
    //   90: astore_3
    //   91: aload 4
    //   93: ldc 94
    //   95: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload_0
    //   100: astore_3
    //   101: aload 4
    //   103: aload_1
    //   104: invokevirtual 102	java/io/IOException:getMessage	()Ljava/lang/String;
    //   107: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload_0
    //   112: astore_3
    //   113: ldc 15
    //   115: aload 4
    //   117: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokestatic 111	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: aload_0
    //   125: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   128: aload 5
    //   130: invokestatic 89	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   133: iconst_0
    //   134: ireturn
    //   135: aload_3
    //   136: invokestatic 64	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   139: aload 5
    //   141: invokestatic 89	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   144: aload_0
    //   145: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   20	26	58	finally
    //   26	32	58	finally
    //   37	44	58	finally
    //   20	26	66	java/io/IOException
    //   26	32	66	java/io/IOException
    //   37	44	66	java/io/IOException
    //   10	20	70	finally
    //   80	89	70	finally
    //   91	99	70	finally
    //   101	111	70	finally
    //   113	124	70	finally
    //   10	20	74	java/io/IOException
  }

  @Nullable
  public static File getTempFile(Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(".font");
    ((StringBuilder)localObject).append(Process.myPid());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(Process.myTid());
    ((StringBuilder)localObject).append("-");
    localObject = ((StringBuilder)localObject).toString();
    int i = 0;
    while (true)
    {
      File localFile;
      if (i < 100)
      {
        localFile = paramContext.getCacheDir();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append(i);
        localFile = new File(localFile, localStringBuilder.toString());
      }
      try
      {
        boolean bool = localFile.createNewFile();
        if (bool)
          return localFile;
        label116: i += 1;
        continue;
        return null;
      }
      catch (IOException localIOException)
      {
        break label116;
      }
    }
  }

  // ERROR //
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 144	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc 146
    //   9: aload_1
    //   10: invokevirtual 152	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnonnull +13 -> 28
    //   18: aload_2
    //   19: ifnull +7 -> 26
    //   22: aload_2
    //   23: invokevirtual 155	android/os/ParcelFileDescriptor:close	()V
    //   26: aconst_null
    //   27: areturn
    //   28: new 157	java/io/FileInputStream
    //   31: dup
    //   32: aload_2
    //   33: invokevirtual 161	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   36: invokespecial 164	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   39: astore 5
    //   41: aload 5
    //   43: invokevirtual 168	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   46: astore_0
    //   47: aload_0
    //   48: invokevirtual 174	java/nio/channels/FileChannel:size	()J
    //   51: lstore_3
    //   52: aload_0
    //   53: getstatic 180	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   56: lconst_0
    //   57: lload_3
    //   58: invokevirtual 184	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   61: astore_0
    //   62: aload 5
    //   64: ifnull +8 -> 72
    //   67: aload 5
    //   69: invokevirtual 185	java/io/FileInputStream:close	()V
    //   72: aload_2
    //   73: ifnull +7 -> 80
    //   76: aload_2
    //   77: invokevirtual 155	android/os/ParcelFileDescriptor:close	()V
    //   80: aload_0
    //   81: areturn
    //   82: astore_1
    //   83: aconst_null
    //   84: astore_0
    //   85: goto +7 -> 92
    //   88: astore_0
    //   89: aload_0
    //   90: athrow
    //   91: astore_1
    //   92: aload 5
    //   94: ifnull +31 -> 125
    //   97: aload_0
    //   98: ifnull +22 -> 120
    //   101: aload 5
    //   103: invokevirtual 185	java/io/FileInputStream:close	()V
    //   106: goto +19 -> 125
    //   109: astore 5
    //   111: aload_0
    //   112: aload 5
    //   114: invokevirtual 189	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   117: goto +8 -> 125
    //   120: aload 5
    //   122: invokevirtual 185	java/io/FileInputStream:close	()V
    //   125: aload_1
    //   126: athrow
    //   127: astore_0
    //   128: aconst_null
    //   129: astore_1
    //   130: goto +7 -> 137
    //   133: astore_1
    //   134: aload_1
    //   135: athrow
    //   136: astore_0
    //   137: aload_2
    //   138: ifnull +27 -> 165
    //   141: aload_1
    //   142: ifnull +19 -> 161
    //   145: aload_2
    //   146: invokevirtual 155	android/os/ParcelFileDescriptor:close	()V
    //   149: goto +16 -> 165
    //   152: astore_2
    //   153: aload_1
    //   154: aload_2
    //   155: invokevirtual 189	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   158: goto +7 -> 165
    //   161: aload_2
    //   162: invokevirtual 155	android/os/ParcelFileDescriptor:close	()V
    //   165: aload_0
    //   166: athrow
    //   167: astore_0
    //   168: aconst_null
    //   169: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   41	62	82	finally
    //   41	62	88	java/lang/Throwable
    //   89	91	91	finally
    //   101	106	109	java/lang/Throwable
    //   28	41	127	finally
    //   67	72	127	finally
    //   101	106	127	finally
    //   111	117	127	finally
    //   120	125	127	finally
    //   125	127	127	finally
    //   28	41	133	java/lang/Throwable
    //   67	72	133	java/lang/Throwable
    //   111	117	133	java/lang/Throwable
    //   120	125	133	java/lang/Throwable
    //   125	127	133	java/lang/Throwable
    //   134	136	136	finally
    //   145	149	152	java/lang/Throwable
    //   5	14	167	java/io/IOException
    //   22	26	167	java/io/IOException
    //   76	80	167	java/io/IOException
    //   145	149	167	java/io/IOException
    //   153	158	167	java/io/IOException
    //   161	165	167	java/io/IOException
    //   165	167	167	java/io/IOException
  }

  // ERROR //
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile)
  {
    // Byte code:
    //   0: new 157	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 192	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore 4
    //   10: aload 4
    //   12: invokevirtual 168	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   15: astore_0
    //   16: aload_0
    //   17: invokevirtual 174	java/nio/channels/FileChannel:size	()J
    //   20: lstore_1
    //   21: aload_0
    //   22: getstatic 180	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   25: lconst_0
    //   26: lload_1
    //   27: invokevirtual 184	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   30: astore_0
    //   31: aload 4
    //   33: ifnull +8 -> 41
    //   36: aload 4
    //   38: invokevirtual 185	java/io/FileInputStream:close	()V
    //   41: aload_0
    //   42: areturn
    //   43: astore_3
    //   44: aconst_null
    //   45: astore_0
    //   46: goto +7 -> 53
    //   49: astore_0
    //   50: aload_0
    //   51: athrow
    //   52: astore_3
    //   53: aload 4
    //   55: ifnull +31 -> 86
    //   58: aload_0
    //   59: ifnull +22 -> 81
    //   62: aload 4
    //   64: invokevirtual 185	java/io/FileInputStream:close	()V
    //   67: goto +19 -> 86
    //   70: astore 4
    //   72: aload_0
    //   73: aload 4
    //   75: invokevirtual 189	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   78: goto +8 -> 86
    //   81: aload 4
    //   83: invokevirtual 185	java/io/FileInputStream:close	()V
    //   86: aload_3
    //   87: athrow
    //   88: astore_0
    //   89: aconst_null
    //   90: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   10	31	43	finally
    //   10	31	49	java/lang/Throwable
    //   50	52	52	finally
    //   62	67	70	java/lang/Throwable
    //   0	10	88	java/io/IOException
    //   36	41	88	java/io/IOException
    //   62	67	88	java/io/IOException
    //   72	78	88	java/io/IOException
    //   81	86	88	java/io/IOException
    //   86	88	88	java/io/IOException
  }
}