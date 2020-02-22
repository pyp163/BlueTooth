package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class UnpackingSoSource extends DirectorySoSource
{
  private static final String DEPS_FILE_NAME = "dso_deps";
  private static final String LOCK_FILE_NAME = "dso_lock";
  private static final String MANIFEST_FILE_NAME = "dso_manifest";
  private static final byte MANIFEST_VERSION = 1;
  private static final byte STATE_CLEAN = 1;
  private static final byte STATE_DIRTY = 0;
  private static final String STATE_FILE_NAME = "dso_state";
  private static final String TAG = "fb-UnpackingSoSource";

  @Nullable
  private String[] mAbis;
  protected final Context mContext;

  @Nullable
  protected String mCorruptedLib;
  private final Map<String, Object> mLibsBeingLoaded = new HashMap();

  protected UnpackingSoSource(Context paramContext, File paramFile)
  {
    super(paramFile, 1);
    this.mContext = paramContext;
  }

  protected UnpackingSoSource(Context paramContext, String paramString)
  {
    super(getSoStorePath(paramContext, paramString), 1);
    this.mContext = paramContext;
  }

  private void deleteUnmentionedFiles(Dso[] paramArrayOfDso)
    throws IOException
  {
    String[] arrayOfString = this.soDirectory.list();
    if (arrayOfString == null)
    {
      paramArrayOfDso = new StringBuilder();
      paramArrayOfDso.append("unable to list directory ");
      paramArrayOfDso.append(this.soDirectory);
      throw new IOException(paramArrayOfDso.toString());
    }
    int i = 0;
    while (i < arrayOfString.length)
    {
      Object localObject = arrayOfString[i];
      if ((!((String)localObject).equals("dso_state")) && (!((String)localObject).equals("dso_lock")) && (!((String)localObject).equals("dso_deps")) && (!((String)localObject).equals("dso_manifest")))
      {
        int k = 0;
        int j = 0;
        while ((k == 0) && (j < paramArrayOfDso.length))
        {
          if (paramArrayOfDso[j].name.equals(localObject))
            k = 1;
          j += 1;
        }
        if (k == 0)
        {
          localObject = new File(this.soDirectory, (String)localObject);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("deleting unaccounted-for file ");
          localStringBuilder.append(localObject);
          Log.v("fb-UnpackingSoSource", localStringBuilder.toString());
          SysUtil.dumbDeleteRecursive((File)localObject);
        }
      }
      i += 1;
    }
  }

  // ERROR //
  private void extractDso(InputDso paramInputDso, byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: new 94	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   7: astore 4
    //   9: aload 4
    //   11: ldc 141
    //   13: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: pop
    //   17: aload 4
    //   19: aload_1
    //   20: getfield 145	com/facebook/soloader/UnpackingSoSource$InputDso:dso	Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   23: getfield 120	com/facebook/soloader/UnpackingSoSource$Dso:name	Ljava/lang/String;
    //   26: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: ldc 43
    //   32: aload 4
    //   34: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokestatic 148	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: aload_0
    //   42: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   45: iconst_1
    //   46: iconst_1
    //   47: invokevirtual 152	java/io/File:setWritable	(ZZ)Z
    //   50: ifne +39 -> 89
    //   53: new 94	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   60: astore_1
    //   61: aload_1
    //   62: ldc 154
    //   64: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload_1
    //   69: aload_0
    //   70: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   73: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: new 76	java/io/IOException
    //   80: dup
    //   81: aload_1
    //   82: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokespecial 111	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   88: athrow
    //   89: new 88	java/io/File
    //   92: dup
    //   93: aload_0
    //   94: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   97: aload_1
    //   98: getfield 145	com/facebook/soloader/UnpackingSoSource$InputDso:dso	Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   101: getfield 120	com/facebook/soloader/UnpackingSoSource$Dso:name	Ljava/lang/String;
    //   104: invokespecial 123	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   107: astore 5
    //   109: new 156	java/io/RandomAccessFile
    //   112: dup
    //   113: aload 5
    //   115: ldc 158
    //   117: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   120: astore 4
    //   122: goto +69 -> 191
    //   125: astore 4
    //   127: new 94	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   134: astore 6
    //   136: aload 6
    //   138: ldc 161
    //   140: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload 6
    //   146: aload 5
    //   148: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 6
    //   154: ldc 163
    //   156: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: ldc 43
    //   162: aload 6
    //   164: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: aload 4
    //   169: invokestatic 167	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   172: pop
    //   173: aload 5
    //   175: invokestatic 137	com/facebook/soloader/SysUtil:dumbDeleteRecursive	(Ljava/io/File;)V
    //   178: new 156	java/io/RandomAccessFile
    //   181: dup
    //   182: aload 5
    //   184: ldc 158
    //   186: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   189: astore 4
    //   191: aload_1
    //   192: getfield 171	com/facebook/soloader/UnpackingSoSource$InputDso:content	Ljava/io/InputStream;
    //   195: invokevirtual 177	java/io/InputStream:available	()I
    //   198: istore_3
    //   199: iload_3
    //   200: iconst_1
    //   201: if_icmple +13 -> 214
    //   204: aload 4
    //   206: invokevirtual 181	java/io/RandomAccessFile:getFD	()Ljava/io/FileDescriptor;
    //   209: iload_3
    //   210: i2l
    //   211: invokestatic 185	com/facebook/soloader/SysUtil:fallocateIfSupported	(Ljava/io/FileDescriptor;J)V
    //   214: aload 4
    //   216: aload_1
    //   217: getfield 171	com/facebook/soloader/UnpackingSoSource$InputDso:content	Ljava/io/InputStream;
    //   220: ldc 186
    //   222: aload_2
    //   223: invokestatic 190	com/facebook/soloader/SysUtil:copyBytes	(Ljava/io/RandomAccessFile;Ljava/io/InputStream;I[B)I
    //   226: pop
    //   227: aload 4
    //   229: aload 4
    //   231: invokevirtual 194	java/io/RandomAccessFile:getFilePointer	()J
    //   234: invokevirtual 198	java/io/RandomAccessFile:setLength	(J)V
    //   237: aload 5
    //   239: iconst_1
    //   240: iconst_0
    //   241: invokevirtual 201	java/io/File:setExecutable	(ZZ)Z
    //   244: ifne +37 -> 281
    //   247: new 94	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   254: astore_1
    //   255: aload_1
    //   256: ldc 203
    //   258: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload_1
    //   263: aload 5
    //   265: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   268: pop
    //   269: new 76	java/io/IOException
    //   272: dup
    //   273: aload_1
    //   274: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: invokespecial 111	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   280: athrow
    //   281: aload 4
    //   283: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   286: return
    //   287: astore_1
    //   288: goto +11 -> 299
    //   291: astore_1
    //   292: aload 5
    //   294: invokestatic 137	com/facebook/soloader/SysUtil:dumbDeleteRecursive	(Ljava/io/File;)V
    //   297: aload_1
    //   298: athrow
    //   299: aload 4
    //   301: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   304: aload_1
    //   305: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   109	122	125	java/io/IOException
    //   191	199	287	finally
    //   204	214	287	finally
    //   214	281	287	finally
    //   292	299	287	finally
    //   191	199	291	java/io/IOException
    //   204	214	291	java/io/IOException
    //   214	281	291	java/io/IOException
  }

  private Object getLibraryLock(String paramString)
  {
    synchronized (this.mLibsBeingLoaded)
    {
      Object localObject2 = this.mLibsBeingLoaded.get(paramString);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new Object();
        this.mLibsBeingLoaded.put(paramString, localObject1);
      }
      return localObject1;
    }
  }

  public static File getSoStorePath(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getApplicationInfo().dataDir);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    return new File(localStringBuilder.toString());
  }

  // ERROR //
  private boolean refreshLocked(final FileLocker paramFileLocker, int paramInt, final byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: new 88	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   8: ldc 40
    //   10: invokespecial 123	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   13: astore 11
    //   15: new 156	java/io/RandomAccessFile
    //   18: dup
    //   19: aload 11
    //   21: ldc 158
    //   23: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   26: astore 9
    //   28: aconst_null
    //   29: astore 7
    //   31: aconst_null
    //   32: astore 8
    //   34: aload 8
    //   36: astore 6
    //   38: aload 9
    //   40: invokevirtual 245	java/io/RandomAccessFile:readByte	()B
    //   43: istore 5
    //   45: iload 5
    //   47: istore 4
    //   49: iload 5
    //   51: iconst_1
    //   52: if_icmpeq +120 -> 172
    //   55: aload 8
    //   57: astore 6
    //   59: new 94	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   66: astore 10
    //   68: aload 8
    //   70: astore 6
    //   72: aload 10
    //   74: ldc 247
    //   76: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload 8
    //   82: astore 6
    //   84: aload 10
    //   86: aload_0
    //   87: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   90: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload 8
    //   96: astore 6
    //   98: aload 10
    //   100: ldc 249
    //   102: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload 8
    //   108: astore 6
    //   110: ldc 43
    //   112: aload 10
    //   114: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: goto +48 -> 169
    //   124: astore_1
    //   125: goto +9 -> 134
    //   128: astore_1
    //   129: aload_1
    //   130: astore 6
    //   132: aload_1
    //   133: athrow
    //   134: aload 9
    //   136: ifnull +31 -> 167
    //   139: aload 6
    //   141: ifnull +21 -> 162
    //   144: aload 9
    //   146: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   149: goto +18 -> 167
    //   152: astore_3
    //   153: aload 6
    //   155: aload_3
    //   156: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   159: goto +8 -> 167
    //   162: aload 9
    //   164: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   167: aload_1
    //   168: athrow
    //   169: iconst_0
    //   170: istore 4
    //   172: aload 9
    //   174: ifnull +8 -> 182
    //   177: aload 9
    //   179: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   182: new 88	java/io/File
    //   185: dup
    //   186: aload_0
    //   187: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   190: ldc 25
    //   192: invokespecial 123	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   195: astore 12
    //   197: new 156	java/io/RandomAccessFile
    //   200: dup
    //   201: aload 12
    //   203: ldc 158
    //   205: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   208: astore 9
    //   210: aload 7
    //   212: astore 6
    //   214: aload 9
    //   216: invokevirtual 256	java/io/RandomAccessFile:length	()J
    //   219: l2i
    //   220: newarray byte
    //   222: astore 8
    //   224: aload 7
    //   226: astore 6
    //   228: aload 9
    //   230: aload 8
    //   232: invokevirtual 260	java/io/RandomAccessFile:read	([B)I
    //   235: aload 8
    //   237: arraylength
    //   238: if_icmpeq +19 -> 257
    //   241: aload 7
    //   243: astore 6
    //   245: ldc 43
    //   247: ldc_w 262
    //   250: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   253: pop
    //   254: iconst_0
    //   255: istore 4
    //   257: aload 7
    //   259: astore 6
    //   261: aload 8
    //   263: aload_3
    //   264: invokestatic 267	java/util/Arrays:equals	([B[B)Z
    //   267: ifne +360 -> 627
    //   270: aload 7
    //   272: astore 6
    //   274: ldc 43
    //   276: ldc_w 269
    //   279: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   282: pop
    //   283: iconst_0
    //   284: istore 4
    //   286: goto +341 -> 627
    //   289: aload 7
    //   291: astore 6
    //   293: ldc 43
    //   295: ldc_w 271
    //   298: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   301: pop
    //   302: aload 7
    //   304: astore 6
    //   306: aload 11
    //   308: iconst_0
    //   309: invokestatic 79	com/facebook/soloader/UnpackingSoSource:writeState	(Ljava/io/File;B)V
    //   312: aload 7
    //   314: astore 6
    //   316: aload_0
    //   317: invokevirtual 275	com/facebook/soloader/UnpackingSoSource:makeUnpacker	()Lcom/facebook/soloader/UnpackingSoSource$Unpacker;
    //   320: astore 10
    //   322: aload 10
    //   324: invokevirtual 279	com/facebook/soloader/UnpackingSoSource$Unpacker:getDsoManifest	()Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
    //   327: astore 8
    //   329: aload 10
    //   331: invokevirtual 283	com/facebook/soloader/UnpackingSoSource$Unpacker:openDsoIterator	()Lcom/facebook/soloader/UnpackingSoSource$InputDsoIterator;
    //   334: astore 6
    //   336: aload_0
    //   337: iload 4
    //   339: aload 8
    //   341: aload 6
    //   343: invokespecial 287	com/facebook/soloader/UnpackingSoSource:regenerate	(BLcom/facebook/soloader/UnpackingSoSource$DsoManifest;Lcom/facebook/soloader/UnpackingSoSource$InputDsoIterator;)V
    //   346: aload 6
    //   348: ifnull +8 -> 356
    //   351: aload 6
    //   353: invokevirtual 288	com/facebook/soloader/UnpackingSoSource$InputDsoIterator:close	()V
    //   356: aload 8
    //   358: astore 6
    //   360: aload 10
    //   362: ifnull +16 -> 378
    //   365: aload 7
    //   367: astore 6
    //   369: aload 10
    //   371: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   374: aload 8
    //   376: astore 6
    //   378: aload 9
    //   380: ifnull +8 -> 388
    //   383: aload 9
    //   385: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   388: aload 6
    //   390: ifnonnull +5 -> 395
    //   393: iconst_0
    //   394: ireturn
    //   395: new 6	com/facebook/soloader/UnpackingSoSource$1
    //   398: dup
    //   399: aload_0
    //   400: aload 12
    //   402: aload_3
    //   403: aload 6
    //   405: aload 11
    //   407: aload_1
    //   408: invokespecial 292	com/facebook/soloader/UnpackingSoSource$1:<init>	(Lcom/facebook/soloader/UnpackingSoSource;Ljava/io/File;[BLcom/facebook/soloader/UnpackingSoSource$DsoManifest;Ljava/io/File;Lcom/facebook/soloader/FileLocker;)V
    //   411: astore_1
    //   412: iload_2
    //   413: iconst_1
    //   414: iand
    //   415: ifeq +48 -> 463
    //   418: new 94	java/lang/StringBuilder
    //   421: dup
    //   422: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   425: astore_3
    //   426: aload_3
    //   427: ldc_w 294
    //   430: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: pop
    //   434: aload_3
    //   435: aload_0
    //   436: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   439: invokevirtual 297	java/io/File:getName	()Ljava/lang/String;
    //   442: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: new 299	java/lang/Thread
    //   449: dup
    //   450: aload_1
    //   451: aload_3
    //   452: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   455: invokespecial 302	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
    //   458: invokevirtual 305	java/lang/Thread:start	()V
    //   461: iconst_1
    //   462: ireturn
    //   463: aload_1
    //   464: invokeinterface 310 1 0
    //   469: iconst_1
    //   470: ireturn
    //   471: astore_1
    //   472: aconst_null
    //   473: astore_3
    //   474: goto +7 -> 481
    //   477: astore_3
    //   478: aload_3
    //   479: athrow
    //   480: astore_1
    //   481: aload 6
    //   483: ifnull +31 -> 514
    //   486: aload_3
    //   487: ifnull +22 -> 509
    //   490: aload 6
    //   492: invokevirtual 288	com/facebook/soloader/UnpackingSoSource$InputDsoIterator:close	()V
    //   495: goto +19 -> 514
    //   498: astore 6
    //   500: aload_3
    //   501: aload 6
    //   503: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   506: goto +8 -> 514
    //   509: aload 6
    //   511: invokevirtual 288	com/facebook/soloader/UnpackingSoSource$InputDsoIterator:close	()V
    //   514: aload_1
    //   515: athrow
    //   516: astore_1
    //   517: aconst_null
    //   518: astore_3
    //   519: goto +7 -> 526
    //   522: astore_3
    //   523: aload_3
    //   524: athrow
    //   525: astore_1
    //   526: aload 10
    //   528: ifnull +43 -> 571
    //   531: aload_3
    //   532: ifnull +30 -> 562
    //   535: aload 7
    //   537: astore 6
    //   539: aload 10
    //   541: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   544: goto +27 -> 571
    //   547: astore 8
    //   549: aload 7
    //   551: astore 6
    //   553: aload_3
    //   554: aload 8
    //   556: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   559: goto +12 -> 571
    //   562: aload 7
    //   564: astore 6
    //   566: aload 10
    //   568: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   571: aload 7
    //   573: astore 6
    //   575: aload_1
    //   576: athrow
    //   577: astore_1
    //   578: goto +9 -> 587
    //   581: astore_1
    //   582: aload_1
    //   583: astore 6
    //   585: aload_1
    //   586: athrow
    //   587: aload 9
    //   589: ifnull +31 -> 620
    //   592: aload 6
    //   594: ifnull +21 -> 615
    //   597: aload 9
    //   599: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   602: goto +18 -> 620
    //   605: astore_3
    //   606: aload 6
    //   608: aload_3
    //   609: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   612: goto +8 -> 620
    //   615: aload 9
    //   617: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   620: aload_1
    //   621: athrow
    //   622: astore 6
    //   624: goto -455 -> 169
    //   627: iload 4
    //   629: ifeq -340 -> 289
    //   632: iload_2
    //   633: iconst_2
    //   634: iand
    //   635: ifeq +6 -> 641
    //   638: goto -349 -> 289
    //   641: aconst_null
    //   642: astore 6
    //   644: goto -266 -> 378
    //
    // Exception table:
    //   from	to	target	type
    //   38	45	124	finally
    //   59	68	124	finally
    //   72	80	124	finally
    //   84	94	124	finally
    //   98	106	124	finally
    //   110	121	124	finally
    //   132	134	124	finally
    //   38	45	128	java/lang/Throwable
    //   59	68	128	java/lang/Throwable
    //   72	80	128	java/lang/Throwable
    //   84	94	128	java/lang/Throwable
    //   98	106	128	java/lang/Throwable
    //   110	121	128	java/lang/Throwable
    //   144	149	152	java/lang/Throwable
    //   336	346	471	finally
    //   336	346	477	java/lang/Throwable
    //   478	480	480	finally
    //   490	495	498	java/lang/Throwable
    //   322	336	516	finally
    //   351	356	516	finally
    //   490	495	516	finally
    //   500	506	516	finally
    //   509	514	516	finally
    //   514	516	516	finally
    //   322	336	522	java/lang/Throwable
    //   351	356	522	java/lang/Throwable
    //   500	506	522	java/lang/Throwable
    //   509	514	522	java/lang/Throwable
    //   514	516	522	java/lang/Throwable
    //   523	525	525	finally
    //   539	544	547	java/lang/Throwable
    //   214	224	577	finally
    //   228	241	577	finally
    //   245	254	577	finally
    //   261	270	577	finally
    //   274	283	577	finally
    //   293	302	577	finally
    //   306	312	577	finally
    //   316	322	577	finally
    //   369	374	577	finally
    //   539	544	577	finally
    //   553	559	577	finally
    //   566	571	577	finally
    //   575	577	577	finally
    //   585	587	577	finally
    //   214	224	581	java/lang/Throwable
    //   228	241	581	java/lang/Throwable
    //   245	254	581	java/lang/Throwable
    //   261	270	581	java/lang/Throwable
    //   274	283	581	java/lang/Throwable
    //   293	302	581	java/lang/Throwable
    //   306	312	581	java/lang/Throwable
    //   316	322	581	java/lang/Throwable
    //   369	374	581	java/lang/Throwable
    //   553	559	581	java/lang/Throwable
    //   566	571	581	java/lang/Throwable
    //   575	577	581	java/lang/Throwable
    //   597	602	605	java/lang/Throwable
    //   38	45	622	java/io/EOFException
    //   59	68	622	java/io/EOFException
    //   72	80	622	java/io/EOFException
    //   84	94	622	java/io/EOFException
    //   98	106	622	java/io/EOFException
    //   110	121	622	java/io/EOFException
  }

  // ERROR //
  private void regenerate(byte paramByte, DsoManifest paramDsoManifest, InputDsoIterator paramInputDsoIterator)
    throws IOException
  {
    // Byte code:
    //   0: new 94	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: ldc_w 314
    //   14: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: pop
    //   18: aload 6
    //   20: aload_0
    //   21: invokevirtual 318	java/lang/Object:getClass	()Ljava/lang/Class;
    //   24: invokevirtual 321	java/lang/Class:getName	()Ljava/lang/String;
    //   27: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: ldc 43
    //   33: aload 6
    //   35: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   38: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: new 156	java/io/RandomAccessFile
    //   45: dup
    //   46: new 88	java/io/File
    //   49: dup
    //   50: aload_0
    //   51: getfield 86	com/facebook/soloader/UnpackingSoSource:soDirectory	Ljava/io/File;
    //   54: ldc 31
    //   56: invokespecial 123	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   59: ldc 158
    //   61: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   64: astore 9
    //   66: aconst_null
    //   67: astore 8
    //   69: iload_1
    //   70: iconst_1
    //   71: if_icmpne +375 -> 446
    //   74: aload 8
    //   76: astore 6
    //   78: aload 9
    //   80: invokestatic 324	com/facebook/soloader/UnpackingSoSource$DsoManifest:read	(Ljava/io/DataInput;)Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
    //   83: astore 7
    //   85: aload 7
    //   87: astore 6
    //   89: goto +31 -> 120
    //   92: astore_2
    //   93: goto +318 -> 411
    //   96: astore_2
    //   97: goto +309 -> 406
    //   100: astore 7
    //   102: aload 8
    //   104: astore 6
    //   106: ldc 43
    //   108: ldc_w 326
    //   111: aload 7
    //   113: invokestatic 328	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   116: pop
    //   117: goto +329 -> 446
    //   120: aload 6
    //   122: astore 7
    //   124: aload 6
    //   126: ifnonnull +20 -> 146
    //   129: aload 8
    //   131: astore 6
    //   133: new 11	com/facebook/soloader/UnpackingSoSource$DsoManifest
    //   136: dup
    //   137: iconst_0
    //   138: anewarray 8	com/facebook/soloader/UnpackingSoSource$Dso
    //   141: invokespecial 330	com/facebook/soloader/UnpackingSoSource$DsoManifest:<init>	([Lcom/facebook/soloader/UnpackingSoSource$Dso;)V
    //   144: astore 7
    //   146: aload 8
    //   148: astore 6
    //   150: aload_0
    //   151: aload_2
    //   152: getfield 334	com/facebook/soloader/UnpackingSoSource$DsoManifest:dsos	[Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   155: invokespecial 336	com/facebook/soloader/UnpackingSoSource:deleteUnmentionedFiles	([Lcom/facebook/soloader/UnpackingSoSource$Dso;)V
    //   158: aload 8
    //   160: astore 6
    //   162: ldc_w 337
    //   165: newarray byte
    //   167: astore_2
    //   168: aload 8
    //   170: astore 6
    //   172: aload_3
    //   173: invokevirtual 341	com/facebook/soloader/UnpackingSoSource$InputDsoIterator:hasNext	()Z
    //   176: ifeq +181 -> 357
    //   179: aload 8
    //   181: astore 6
    //   183: aload_3
    //   184: invokevirtual 345	com/facebook/soloader/UnpackingSoSource$InputDsoIterator:next	()Lcom/facebook/soloader/UnpackingSoSource$InputDso;
    //   187: astore 10
    //   189: iconst_1
    //   190: istore 4
    //   192: iconst_0
    //   193: istore_1
    //   194: iload 4
    //   196: ifeq +75 -> 271
    //   199: iload_1
    //   200: aload 7
    //   202: getfield 334	com/facebook/soloader/UnpackingSoSource$DsoManifest:dsos	[Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   205: arraylength
    //   206: if_icmpge +65 -> 271
    //   209: iload 4
    //   211: istore 5
    //   213: aload 7
    //   215: getfield 334	com/facebook/soloader/UnpackingSoSource$DsoManifest:dsos	[Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   218: iload_1
    //   219: aaload
    //   220: getfield 120	com/facebook/soloader/UnpackingSoSource$Dso:name	Ljava/lang/String;
    //   223: aload 10
    //   225: getfield 145	com/facebook/soloader/UnpackingSoSource$InputDso:dso	Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   228: getfield 120	com/facebook/soloader/UnpackingSoSource$Dso:name	Ljava/lang/String;
    //   231: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   234: ifeq +218 -> 452
    //   237: iload 4
    //   239: istore 5
    //   241: aload 7
    //   243: getfield 334	com/facebook/soloader/UnpackingSoSource$DsoManifest:dsos	[Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   246: iload_1
    //   247: aaload
    //   248: getfield 348	com/facebook/soloader/UnpackingSoSource$Dso:hash	Ljava/lang/String;
    //   251: aload 10
    //   253: getfield 145	com/facebook/soloader/UnpackingSoSource$InputDso:dso	Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   256: getfield 348	com/facebook/soloader/UnpackingSoSource$Dso:hash	Ljava/lang/String;
    //   259: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   262: ifeq +190 -> 452
    //   265: iconst_0
    //   266: istore 5
    //   268: goto +184 -> 452
    //   271: iload 4
    //   273: ifeq +67 -> 340
    //   276: aload_0
    //   277: aload 10
    //   279: aload_2
    //   280: invokespecial 350	com/facebook/soloader/UnpackingSoSource:extractDso	(Lcom/facebook/soloader/UnpackingSoSource$InputDso;[B)V
    //   283: goto +57 -> 340
    //   286: aload_3
    //   287: athrow
    //   288: astore_2
    //   289: aload 10
    //   291: ifnull +43 -> 334
    //   294: aload_3
    //   295: ifnull +30 -> 325
    //   298: aload 8
    //   300: astore 6
    //   302: aload 10
    //   304: invokevirtual 351	com/facebook/soloader/UnpackingSoSource$InputDso:close	()V
    //   307: goto +27 -> 334
    //   310: astore 7
    //   312: aload 8
    //   314: astore 6
    //   316: aload_3
    //   317: aload 7
    //   319: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   322: goto +12 -> 334
    //   325: aload 8
    //   327: astore 6
    //   329: aload 10
    //   331: invokevirtual 351	com/facebook/soloader/UnpackingSoSource$InputDso:close	()V
    //   334: aload 8
    //   336: astore 6
    //   338: aload_2
    //   339: athrow
    //   340: aload 10
    //   342: ifnull -174 -> 168
    //   345: aload 8
    //   347: astore 6
    //   349: aload 10
    //   351: invokevirtual 351	com/facebook/soloader/UnpackingSoSource$InputDso:close	()V
    //   354: goto -186 -> 168
    //   357: aload 9
    //   359: ifnull +8 -> 367
    //   362: aload 9
    //   364: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   367: new 94	java/lang/StringBuilder
    //   370: dup
    //   371: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   374: astore_2
    //   375: aload_2
    //   376: ldc_w 353
    //   379: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: pop
    //   383: aload_2
    //   384: aload_0
    //   385: invokevirtual 318	java/lang/Object:getClass	()Ljava/lang/Class;
    //   388: invokevirtual 321	java/lang/Class:getName	()Ljava/lang/String;
    //   391: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: pop
    //   395: ldc 43
    //   397: aload_2
    //   398: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   401: invokestatic 131	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   404: pop
    //   405: return
    //   406: aload_2
    //   407: astore 6
    //   409: aload_2
    //   410: athrow
    //   411: aload 9
    //   413: ifnull +31 -> 444
    //   416: aload 6
    //   418: ifnull +21 -> 439
    //   421: aload 9
    //   423: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   426: goto +18 -> 444
    //   429: astore_3
    //   430: aload 6
    //   432: aload_3
    //   433: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   436: goto +8 -> 444
    //   439: aload 9
    //   441: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   444: aload_2
    //   445: athrow
    //   446: aconst_null
    //   447: astore 6
    //   449: goto -329 -> 120
    //   452: iload_1
    //   453: iconst_1
    //   454: iadd
    //   455: istore_1
    //   456: iload 5
    //   458: istore 4
    //   460: goto -266 -> 194
    //   463: astore_2
    //   464: aconst_null
    //   465: astore_3
    //   466: goto -177 -> 289
    //   469: astore_3
    //   470: goto -184 -> 286
    //
    // Exception table:
    //   from	to	target	type
    //   78	85	92	finally
    //   106	117	92	finally
    //   133	146	92	finally
    //   150	158	92	finally
    //   162	168	92	finally
    //   172	179	92	finally
    //   183	189	92	finally
    //   302	307	92	finally
    //   316	322	92	finally
    //   329	334	92	finally
    //   338	340	92	finally
    //   349	354	92	finally
    //   409	411	92	finally
    //   78	85	96	java/lang/Throwable
    //   106	117	96	java/lang/Throwable
    //   133	146	96	java/lang/Throwable
    //   150	158	96	java/lang/Throwable
    //   162	168	96	java/lang/Throwable
    //   172	179	96	java/lang/Throwable
    //   183	189	96	java/lang/Throwable
    //   316	322	96	java/lang/Throwable
    //   329	334	96	java/lang/Throwable
    //   338	340	96	java/lang/Throwable
    //   349	354	96	java/lang/Throwable
    //   78	85	100	java/lang/Exception
    //   286	288	288	finally
    //   302	307	310	java/lang/Throwable
    //   421	426	429	java/lang/Throwable
    //   199	209	463	finally
    //   213	237	463	finally
    //   241	265	463	finally
    //   276	283	463	finally
    //   199	209	469	java/lang/Throwable
    //   213	237	469	java/lang/Throwable
    //   241	265	469	java/lang/Throwable
    //   276	283	469	java/lang/Throwable
  }

  // ERROR //
  private static void writeState(File paramFile, byte paramByte)
    throws IOException
  {
    // Byte code:
    //   0: new 156	java/io/RandomAccessFile
    //   3: dup
    //   4: aload_0
    //   5: ldc 158
    //   7: invokespecial 159	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_2
    //   13: aload_2
    //   14: astore_0
    //   15: aload_3
    //   16: lconst_0
    //   17: invokevirtual 356	java/io/RandomAccessFile:seek	(J)V
    //   20: aload_2
    //   21: astore_0
    //   22: aload_3
    //   23: iload_1
    //   24: invokevirtual 360	java/io/RandomAccessFile:write	(I)V
    //   27: aload_2
    //   28: astore_0
    //   29: aload_3
    //   30: aload_3
    //   31: invokevirtual 194	java/io/RandomAccessFile:getFilePointer	()J
    //   34: invokevirtual 198	java/io/RandomAccessFile:setLength	(J)V
    //   37: aload_2
    //   38: astore_0
    //   39: aload_3
    //   40: invokevirtual 181	java/io/RandomAccessFile:getFD	()Ljava/io/FileDescriptor;
    //   43: invokevirtual 365	java/io/FileDescriptor:sync	()V
    //   46: aload_3
    //   47: ifnull +7 -> 54
    //   50: aload_3
    //   51: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   54: return
    //   55: astore_2
    //   56: goto +8 -> 64
    //   59: astore_2
    //   60: aload_2
    //   61: astore_0
    //   62: aload_2
    //   63: athrow
    //   64: aload_3
    //   65: ifnull +27 -> 92
    //   68: aload_0
    //   69: ifnull +19 -> 88
    //   72: aload_3
    //   73: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   76: goto +16 -> 92
    //   79: astore_3
    //   80: aload_0
    //   81: aload_3
    //   82: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   85: goto +7 -> 92
    //   88: aload_3
    //   89: invokevirtual 206	java/io/RandomAccessFile:close	()V
    //   92: aload_2
    //   93: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   15	20	55	finally
    //   22	27	55	finally
    //   29	37	55	finally
    //   39	46	55	finally
    //   62	64	55	finally
    //   15	20	59	java/lang/Throwable
    //   22	27	59	java/lang/Throwable
    //   29	37	59	java/lang/Throwable
    //   39	46	59	java/lang/Throwable
    //   72	76	79	java/lang/Throwable
  }

  // ERROR //
  protected byte[] getDepsBlock()
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 373	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore 5
    //   5: aload_0
    //   6: invokevirtual 275	com/facebook/soloader/UnpackingSoSource:makeUnpacker	()Lcom/facebook/soloader/UnpackingSoSource$Unpacker;
    //   9: astore 4
    //   11: aconst_null
    //   12: astore_3
    //   13: aload_3
    //   14: astore_2
    //   15: aload 4
    //   17: invokevirtual 279	com/facebook/soloader/UnpackingSoSource$Unpacker:getDsoManifest	()Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
    //   20: getfield 334	com/facebook/soloader/UnpackingSoSource$DsoManifest:dsos	[Lcom/facebook/soloader/UnpackingSoSource$Dso;
    //   23: astore 6
    //   25: aload_3
    //   26: astore_2
    //   27: aload 5
    //   29: iconst_1
    //   30: invokevirtual 377	android/os/Parcel:writeByte	(B)V
    //   33: aload_3
    //   34: astore_2
    //   35: aload 5
    //   37: aload 6
    //   39: arraylength
    //   40: invokevirtual 380	android/os/Parcel:writeInt	(I)V
    //   43: iconst_0
    //   44: istore_1
    //   45: aload_3
    //   46: astore_2
    //   47: iload_1
    //   48: aload 6
    //   50: arraylength
    //   51: if_icmpge +38 -> 89
    //   54: aload_3
    //   55: astore_2
    //   56: aload 5
    //   58: aload 6
    //   60: iload_1
    //   61: aaload
    //   62: getfield 120	com/facebook/soloader/UnpackingSoSource$Dso:name	Ljava/lang/String;
    //   65: invokevirtual 383	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   68: aload_3
    //   69: astore_2
    //   70: aload 5
    //   72: aload 6
    //   74: iload_1
    //   75: aaload
    //   76: getfield 348	com/facebook/soloader/UnpackingSoSource$Dso:hash	Ljava/lang/String;
    //   79: invokevirtual 383	android/os/Parcel:writeString	(Ljava/lang/String;)V
    //   82: iload_1
    //   83: iconst_1
    //   84: iadd
    //   85: istore_1
    //   86: goto -41 -> 45
    //   89: aload 4
    //   91: ifnull +8 -> 99
    //   94: aload 4
    //   96: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   99: aload 5
    //   101: invokevirtual 386	android/os/Parcel:marshall	()[B
    //   104: astore_2
    //   105: aload 5
    //   107: invokevirtual 389	android/os/Parcel:recycle	()V
    //   110: aload_2
    //   111: areturn
    //   112: astore_3
    //   113: goto +8 -> 121
    //   116: astore_3
    //   117: aload_3
    //   118: astore_2
    //   119: aload_3
    //   120: athrow
    //   121: aload 4
    //   123: ifnull +31 -> 154
    //   126: aload_2
    //   127: ifnull +22 -> 149
    //   130: aload 4
    //   132: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   135: goto +19 -> 154
    //   138: astore 4
    //   140: aload_2
    //   141: aload 4
    //   143: invokevirtual 253	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   146: goto +8 -> 154
    //   149: aload 4
    //   151: invokevirtual 289	com/facebook/soloader/UnpackingSoSource$Unpacker:close	()V
    //   154: aload_3
    //   155: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   15	25	112	finally
    //   27	33	112	finally
    //   35	43	112	finally
    //   47	54	112	finally
    //   56	68	112	finally
    //   70	82	112	finally
    //   119	121	112	finally
    //   15	25	116	java/lang/Throwable
    //   27	33	116	java/lang/Throwable
    //   35	43	116	java/lang/Throwable
    //   47	54	116	java/lang/Throwable
    //   56	68	116	java/lang/Throwable
    //   70	82	116	java/lang/Throwable
    //   130	135	138	java/lang/Throwable
  }

  public String[] getSoSourceAbis()
  {
    if (this.mAbis == null)
      return super.getSoSourceAbis();
    return this.mAbis;
  }

  public int loadLibrary(String paramString, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    synchronized (getLibraryLock(paramString))
    {
      paramInt = loadLibraryFrom(paramString, paramInt, this.soDirectory, paramThreadPolicy);
      return paramInt;
    }
  }

  protected abstract Unpacker makeUnpacker()
    throws IOException;

  protected void prepare(int paramInt)
    throws IOException
  {
    SysUtil.mkdirOrThrow(this.soDirectory);
    Object localObject1 = FileLocker.lock(new File(this.soDirectory, "dso_lock"));
    try
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("locked dso store ");
      localStringBuilder1.append(this.soDirectory);
      Log.v("fb-UnpackingSoSource", localStringBuilder1.toString());
      if (refreshLocked((FileLocker)localObject1, paramInt, getDepsBlock()))
      {
        localObject1 = null;
      }
      else
      {
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("dso store is up-to-date: ");
        localStringBuilder1.append(this.soDirectory);
        Log.i("fb-UnpackingSoSource", localStringBuilder1.toString());
      }
      if (localObject1 != null)
      {
        localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("releasing dso store lock for ");
        localStringBuilder1.append(this.soDirectory);
        Log.v("fb-UnpackingSoSource", localStringBuilder1.toString());
        ((FileLocker)localObject1).close();
        return;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("not releasing dso store lock for ");
      ((StringBuilder)localObject1).append(this.soDirectory);
      ((StringBuilder)localObject1).append(" (syncer thread started)");
      Log.v("fb-UnpackingSoSource", ((StringBuilder)localObject1).toString());
      return;
    }
    finally
    {
      if (localObject1 != null)
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("releasing dso store lock for ");
        localStringBuilder2.append(this.soDirectory);
        Log.v("fb-UnpackingSoSource", localStringBuilder2.toString());
        ((FileLocker)localObject1).close();
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("not releasing dso store lock for ");
        ((StringBuilder)localObject1).append(this.soDirectory);
        ((StringBuilder)localObject1).append(" (syncer thread started)");
        Log.v("fb-UnpackingSoSource", ((StringBuilder)localObject1).toString());
      }
    }
  }

  protected void prepare(String paramString)
    throws IOException
  {
    try
    {
      synchronized (getLibraryLock(paramString))
      {
        this.mCorruptedLib = paramString;
        prepare(2);
        return;
      }
    }
    finally
    {
    }
  }

  public void setSoSourceAbis(String[] paramArrayOfString)
  {
    this.mAbis = paramArrayOfString;
  }

  public static class Dso
  {
    public final String hash;
    public final String name;

    public Dso(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.hash = paramString2;
    }
  }

  public static final class DsoManifest
  {
    public final UnpackingSoSource.Dso[] dsos;

    public DsoManifest(UnpackingSoSource.Dso[] paramArrayOfDso)
    {
      this.dsos = paramArrayOfDso;
    }

    static final DsoManifest read(DataInput paramDataInput)
      throws IOException
    {
      if (paramDataInput.readByte() != 1)
        throw new RuntimeException("wrong dso manifest version");
      int j = paramDataInput.readInt();
      if (j < 0)
        throw new RuntimeException("illegal number of shared libraries");
      UnpackingSoSource.Dso[] arrayOfDso = new UnpackingSoSource.Dso[j];
      int i = 0;
      while (i < j)
      {
        arrayOfDso[i] = new UnpackingSoSource.Dso(paramDataInput.readUTF(), paramDataInput.readUTF());
        i += 1;
      }
      return new DsoManifest(arrayOfDso);
    }

    public final void write(DataOutput paramDataOutput)
      throws IOException
    {
      paramDataOutput.writeByte(1);
      paramDataOutput.writeInt(this.dsos.length);
      int i = 0;
      while (i < this.dsos.length)
      {
        paramDataOutput.writeUTF(this.dsos[i].name);
        paramDataOutput.writeUTF(this.dsos[i].hash);
        i += 1;
      }
    }
  }

  protected static final class InputDso
    implements Closeable
  {
    public final InputStream content;
    public final UnpackingSoSource.Dso dso;

    public InputDso(UnpackingSoSource.Dso paramDso, InputStream paramInputStream)
    {
      this.dso = paramDso;
      this.content = paramInputStream;
    }

    public void close()
      throws IOException
    {
      this.content.close();
    }
  }

  protected static abstract class InputDsoIterator
    implements Closeable
  {
    public void close()
      throws IOException
    {
    }

    public abstract boolean hasNext();

    public abstract UnpackingSoSource.InputDso next()
      throws IOException;
  }

  protected static abstract class Unpacker
    implements Closeable
  {
    public void close()
      throws IOException
    {
    }

    protected abstract UnpackingSoSource.DsoManifest getDsoManifest()
      throws IOException;

    protected abstract UnpackingSoSource.InputDsoIterator openDsoIterator()
      throws IOException;
  }
}