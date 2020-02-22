package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SoLoader
{
  static final boolean DEBUG = false;
  public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
  public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
  public static final int SOLOADER_LOOK_IN_ZIP = 4;
  private static final String SO_STORE_NAME_MAIN = "lib-main";
  static final boolean SYSTRACE_LIBRARY_LOADING;
  static final String TAG = "SoLoader";

  @Nullable
  @GuardedBy("sSoSourcesLock")
  private static ApplicationSoSource sApplicationSoSource;

  @Nullable
  @GuardedBy("sSoSourcesLock")
  private static UnpackingSoSource sBackupSoSource;

  @GuardedBy("sSoSourcesLock")
  private static int sFlags;
  private static final Set<String> sLoadedAndMergedLibraries;

  @GuardedBy("SoLoader.class")
  private static final HashSet<String> sLoadedLibraries;

  @GuardedBy("SoLoader.class")
  private static final Map<String, Object> sLoadingLibraries;

  @Nullable
  static SoFileLoader sSoFileLoader;

  @Nullable
  @GuardedBy("sSoSourcesLock")
  private static SoSource[] sSoSources;
  private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
  private static int sSoSourcesVersion;

  @Nullable
  private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper;

  static
  {
    sLoadedLibraries = new HashSet();
    sLoadingLibraries = new HashMap();
    sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    boolean bool = false;
    try
    {
      int i = Build.VERSION.SDK_INT;
      if (i >= 18)
        bool = true;
      label57: SYSTRACE_LIBRARY_LOADING = bool;
      return;
    }
    catch (NoClassDefFoundError|UnsatisfiedLinkError localNoClassDefFoundError)
    {
      break label57;
    }
  }

  public static boolean areSoSourcesAbisSupported()
  {
    sSoSourcesLock.readLock().lock();
    try
    {
      Object localObject1 = sSoSources;
      if (localObject1 == null)
        return false;
      localObject1 = SysUtil.getSupportedAbis();
      int i = 0;
      while (i < sSoSources.length)
      {
        String[] arrayOfString = sSoSources[i].getSoSourceAbis();
        int j = 0;
        while (j < arrayOfString.length)
        {
          int k = 0;
          boolean bool = false;
          while ((k < localObject1.length) && (!bool))
          {
            bool = arrayOfString[j].equals(localObject1[k]);
            k += 1;
          }
          if (!bool)
            return false;
          j += 1;
        }
        i += 1;
      }
      return true;
    }
    finally
    {
      sSoSourcesLock.readLock().unlock();
    }
  }

  private static void assertInitialized()
  {
    sSoSourcesLock.readLock().lock();
    try
    {
      if (sSoSources == null)
        throw new RuntimeException("SoLoader.init() not yet called");
      return;
    }
    finally
    {
      sSoSourcesLock.readLock().unlock();
    }
  }

  public static void deinitForTest()
  {
    setSoSources(null);
  }

  // ERROR //
  private static void doLoadLibraryBySoName(String paramString, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   3: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   6: invokevirtual 112	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   9: getstatic 114	com/facebook/soloader/SoLoader:sSoSources	[Lcom/facebook/soloader/SoSource;
    //   12: ifnonnull +74 -> 86
    //   15: new 155	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   22: astore_2
    //   23: aload_2
    //   24: ldc 158
    //   26: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload_2
    //   31: aload_0
    //   32: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: pop
    //   36: aload_2
    //   37: ldc 164
    //   39: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: ldc 31
    //   45: aload_2
    //   46: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokestatic 174	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   52: pop
    //   53: new 155	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   60: astore_2
    //   61: aload_2
    //   62: ldc 176
    //   64: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload_2
    //   69: aload_0
    //   70: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: new 64	java/lang/UnsatisfiedLinkError
    //   77: dup
    //   78: aload_2
    //   79: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokespecial 177	java/lang/UnsatisfiedLinkError:<init>	(Ljava/lang/String;)V
    //   85: athrow
    //   86: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   89: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   92: invokevirtual 117	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   95: aload_2
    //   96: ifnonnull +13 -> 109
    //   99: invokestatic 183	android/os/StrictMode:allowThreadDiskReads	()Landroid/os/StrictMode$ThreadPolicy;
    //   102: astore_2
    //   103: iconst_1
    //   104: istore 7
    //   106: goto +6 -> 112
    //   109: iconst_0
    //   110: istore 7
    //   112: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   115: ifeq +43 -> 158
    //   118: new 155	java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   125: astore 9
    //   127: aload 9
    //   129: ldc 185
    //   131: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 9
    //   137: aload_0
    //   138: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload 9
    //   144: ldc 187
    //   146: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload 9
    //   152: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokestatic 192	com/facebook/soloader/Api18TraceUtils:beginTraceSection	(Ljava/lang/String;)V
    //   158: iconst_0
    //   159: istore 4
    //   161: iload 4
    //   163: istore 5
    //   165: iload 4
    //   167: istore 6
    //   169: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   172: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   175: invokevirtual 112	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:lock	()V
    //   178: iload 4
    //   180: istore 5
    //   182: iload 4
    //   184: istore 6
    //   186: getstatic 194	com/facebook/soloader/SoLoader:sSoSourcesVersion	I
    //   189: istore 8
    //   191: iconst_0
    //   192: istore 5
    //   194: iload 4
    //   196: istore_3
    //   197: iload 4
    //   199: ifne +148 -> 347
    //   202: iload 4
    //   204: istore_3
    //   205: iload 5
    //   207: getstatic 114	com/facebook/soloader/SoLoader:sSoSources	[Lcom/facebook/soloader/SoSource;
    //   210: arraylength
    //   211: if_icmpge +136 -> 347
    //   214: getstatic 114	com/facebook/soloader/SoLoader:sSoSources	[Lcom/facebook/soloader/SoSource;
    //   217: iload 5
    //   219: aaload
    //   220: aload_0
    //   221: iload_1
    //   222: aload_2
    //   223: invokevirtual 198	com/facebook/soloader/SoSource:loadLibrary	(Ljava/lang/String;ILandroid/os/StrictMode$ThreadPolicy;)I
    //   226: istore_3
    //   227: iload_3
    //   228: iconst_3
    //   229: if_icmpne +76 -> 305
    //   232: getstatic 200	com/facebook/soloader/SoLoader:sBackupSoSource	Lcom/facebook/soloader/UnpackingSoSource;
    //   235: ifnull +70 -> 305
    //   238: new 155	java/lang/StringBuilder
    //   241: dup
    //   242: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   245: astore 9
    //   247: aload 9
    //   249: ldc 202
    //   251: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: aload 9
    //   257: aload_0
    //   258: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: ldc 31
    //   264: aload 9
    //   266: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   269: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   272: pop
    //   273: getstatic 200	com/facebook/soloader/SoLoader:sBackupSoSource	Lcom/facebook/soloader/UnpackingSoSource;
    //   276: aload_0
    //   277: invokevirtual 210	com/facebook/soloader/UnpackingSoSource:prepare	(Ljava/lang/String;)V
    //   280: getstatic 200	com/facebook/soloader/SoLoader:sBackupSoSource	Lcom/facebook/soloader/UnpackingSoSource;
    //   283: aload_0
    //   284: iload_1
    //   285: aload_2
    //   286: invokevirtual 211	com/facebook/soloader/UnpackingSoSource:loadLibrary	(Ljava/lang/String;ILandroid/os/StrictMode$ThreadPolicy;)I
    //   289: istore 4
    //   291: iload 4
    //   293: istore_3
    //   294: goto +53 -> 347
    //   297: astore 9
    //   299: iload_3
    //   300: istore 4
    //   302: goto +17 -> 319
    //   305: iload 5
    //   307: iconst_1
    //   308: iadd
    //   309: istore 5
    //   311: iload_3
    //   312: istore 4
    //   314: goto -120 -> 194
    //   317: astore 9
    //   319: iload 4
    //   321: istore 5
    //   323: iload 4
    //   325: istore 6
    //   327: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   330: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   333: invokevirtual 117	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   336: iload 4
    //   338: istore 5
    //   340: iload 4
    //   342: istore 6
    //   344: aload 9
    //   346: athrow
    //   347: iload_3
    //   348: istore 5
    //   350: iload_3
    //   351: istore 6
    //   353: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   356: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   359: invokevirtual 117	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   362: iload_3
    //   363: ifne +110 -> 473
    //   366: iload_3
    //   367: istore 5
    //   369: iload_3
    //   370: istore 6
    //   372: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   375: invokevirtual 215	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   378: invokevirtual 218	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:lock	()V
    //   381: getstatic 220	com/facebook/soloader/SoLoader:sApplicationSoSource	Lcom/facebook/soloader/ApplicationSoSource;
    //   384: ifnull +20 -> 404
    //   387: getstatic 220	com/facebook/soloader/SoLoader:sApplicationSoSource	Lcom/facebook/soloader/ApplicationSoSource;
    //   390: invokevirtual 225	com/facebook/soloader/ApplicationSoSource:checkAndMaybeUpdate	()Z
    //   393: ifeq +11 -> 404
    //   396: getstatic 194	com/facebook/soloader/SoLoader:sSoSourcesVersion	I
    //   399: iconst_1
    //   400: iadd
    //   401: putstatic 194	com/facebook/soloader/SoLoader:sSoSourcesVersion	I
    //   404: getstatic 194	com/facebook/soloader/SoLoader:sSoSourcesVersion	I
    //   407: istore 4
    //   409: iload 4
    //   411: iload 8
    //   413: if_icmpeq +9 -> 422
    //   416: iconst_1
    //   417: istore 4
    //   419: goto +6 -> 425
    //   422: iconst_0
    //   423: istore 4
    //   425: iload_3
    //   426: istore 5
    //   428: iload_3
    //   429: istore 6
    //   431: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   434: invokevirtual 215	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   437: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   440: iload 4
    //   442: istore 5
    //   444: goto +32 -> 476
    //   447: astore 9
    //   449: iload_3
    //   450: istore 5
    //   452: iload_3
    //   453: istore 6
    //   455: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   458: invokevirtual 215	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   461: invokevirtual 226	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   464: iload_3
    //   465: istore 5
    //   467: iload_3
    //   468: istore 6
    //   470: aload 9
    //   472: athrow
    //   473: iconst_0
    //   474: istore 5
    //   476: iload_3
    //   477: istore 4
    //   479: iload 5
    //   481: ifne -320 -> 161
    //   484: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   487: ifeq +6 -> 493
    //   490: invokestatic 229	com/facebook/soloader/Api18TraceUtils:endSection	()V
    //   493: iload 7
    //   495: ifeq +7 -> 502
    //   498: aload_2
    //   499: invokestatic 233	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   502: iload_3
    //   503: ifeq +8 -> 511
    //   506: iload_3
    //   507: iconst_3
    //   508: if_icmpne +158 -> 666
    //   511: new 155	java/lang/StringBuilder
    //   514: dup
    //   515: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   518: astore_2
    //   519: aload_2
    //   520: ldc 176
    //   522: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: pop
    //   526: aload_2
    //   527: aload_0
    //   528: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: pop
    //   532: aload_2
    //   533: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   536: astore_0
    //   537: ldc 31
    //   539: aload_0
    //   540: invokestatic 174	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   543: pop
    //   544: new 64	java/lang/UnsatisfiedLinkError
    //   547: dup
    //   548: aload_0
    //   549: invokespecial 177	java/lang/UnsatisfiedLinkError:<init>	(Ljava/lang/String;)V
    //   552: athrow
    //   553: astore 9
    //   555: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   558: ifeq +6 -> 564
    //   561: invokestatic 229	com/facebook/soloader/Api18TraceUtils:endSection	()V
    //   564: iload 7
    //   566: ifeq +7 -> 573
    //   569: aload_2
    //   570: invokestatic 233	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   573: iload 5
    //   575: ifeq +15 -> 590
    //   578: iload 5
    //   580: iconst_3
    //   581: if_icmpne +6 -> 587
    //   584: goto +6 -> 590
    //   587: aload 9
    //   589: athrow
    //   590: new 155	java/lang/StringBuilder
    //   593: dup
    //   594: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   597: astore_2
    //   598: aload_2
    //   599: ldc 176
    //   601: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   604: pop
    //   605: aload_2
    //   606: aload_0
    //   607: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: pop
    //   611: aload_2
    //   612: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   615: astore_0
    //   616: ldc 31
    //   618: aload_0
    //   619: invokestatic 174	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   622: pop
    //   623: new 64	java/lang/UnsatisfiedLinkError
    //   626: dup
    //   627: aload_0
    //   628: invokespecial 177	java/lang/UnsatisfiedLinkError:<init>	(Ljava/lang/String;)V
    //   631: athrow
    //   632: astore 10
    //   634: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   637: ifeq +6 -> 643
    //   640: invokestatic 229	com/facebook/soloader/Api18TraceUtils:endSection	()V
    //   643: iload 7
    //   645: ifeq +7 -> 652
    //   648: aload_2
    //   649: invokestatic 233	android/os/StrictMode:setThreadPolicy	(Landroid/os/StrictMode$ThreadPolicy;)V
    //   652: iload 6
    //   654: ifeq +13 -> 667
    //   657: iload 6
    //   659: iconst_3
    //   660: if_icmpne +6 -> 666
    //   663: goto +4 -> 667
    //   666: return
    //   667: new 155	java/lang/StringBuilder
    //   670: dup
    //   671: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   674: astore_2
    //   675: aload_2
    //   676: ldc 176
    //   678: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   681: pop
    //   682: aload_2
    //   683: aload_0
    //   684: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: pop
    //   688: aload_2
    //   689: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   692: astore_2
    //   693: aload_2
    //   694: astore_0
    //   695: aload 10
    //   697: ifnull +61 -> 758
    //   700: aload 10
    //   702: invokevirtual 236	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   705: astore 9
    //   707: aload 9
    //   709: astore_0
    //   710: aload 9
    //   712: ifnonnull +9 -> 721
    //   715: aload 10
    //   717: invokevirtual 237	java/lang/Throwable:toString	()Ljava/lang/String;
    //   720: astore_0
    //   721: new 155	java/lang/StringBuilder
    //   724: dup
    //   725: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   728: astore 9
    //   730: aload 9
    //   732: aload_2
    //   733: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: pop
    //   737: aload 9
    //   739: ldc 239
    //   741: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: pop
    //   745: aload 9
    //   747: aload_0
    //   748: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: pop
    //   752: aload 9
    //   754: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   757: astore_0
    //   758: ldc 31
    //   760: aload_0
    //   761: invokestatic 174	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   764: pop
    //   765: new 64	java/lang/UnsatisfiedLinkError
    //   768: dup
    //   769: aload_0
    //   770: invokespecial 177	java/lang/UnsatisfiedLinkError:<init>	(Ljava/lang/String;)V
    //   773: athrow
    //   774: astore_0
    //   775: getstatic 71	com/facebook/soloader/SoLoader:sSoSourcesLock	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   778: invokevirtual 107	java/util/concurrent/locks/ReentrantReadWriteLock:readLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
    //   781: invokevirtual 117	java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock:unlock	()V
    //   784: aload_0
    //   785: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   232	291	297	finally
    //   205	227	317	finally
    //   381	404	447	finally
    //   404	409	447	finally
    //   169	178	553	finally
    //   186	191	553	finally
    //   327	336	553	finally
    //   344	347	553	finally
    //   353	362	553	finally
    //   372	381	553	finally
    //   431	440	553	finally
    //   455	464	553	finally
    //   470	473	553	finally
    //   169	178	632	java/lang/Throwable
    //   186	191	632	java/lang/Throwable
    //   327	336	632	java/lang/Throwable
    //   344	347	632	java/lang/Throwable
    //   353	362	632	java/lang/Throwable
    //   372	381	632	java/lang/Throwable
    //   431	440	632	java/lang/Throwable
    //   455	464	632	java/lang/Throwable
    //   470	473	632	java/lang/Throwable
    //   9	86	774	finally
  }

  public static Set<String> getLoadedLibraries()
  {
    try
    {
      HashSet localHashSet = (HashSet)sLoadedLibraries.clone();
      return localHashSet;
    }
    finally
    {
    }
  }

  @Nullable
  private static Method getNativeLoadRuntimeMethod()
  {
    if (Build.VERSION.SDK_INT < 23)
      return null;
    try
    {
      Method localMethod;
      if (Build.VERSION.SDK_INT <= 27)
        localMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[] { String.class, ClassLoader.class, String.class });
      else
        localMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[] { String.class, ClassLoader.class });
      localMethod.setAccessible(true);
      return localMethod;
    }
    catch (NoSuchMethodException|SecurityException localNoSuchMethodException)
    {
      Log.w("SoLoader", "Cannot get nativeLoad method", localNoSuchMethodException);
    }
    return null;
  }

  public static void init(Context paramContext, int paramInt)
    throws IOException
  {
    init(paramContext, paramInt, null);
  }

  private static void init(Context paramContext, int paramInt, @Nullable SoFileLoader paramSoFileLoader)
    throws IOException
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.allowThreadDiskWrites();
    try
    {
      initSoLoader(paramSoFileLoader);
      initSoSources(paramContext, paramInt, paramSoFileLoader);
      return;
    }
    finally
    {
      StrictMode.setThreadPolicy(localThreadPolicy);
    }
    throw paramContext;
  }

  public static void init(Context paramContext, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }

  private static void initSoLoader(@Nullable final SoFileLoader paramSoFileLoader)
  {
    if (paramSoFileLoader != null)
      try
      {
        sSoFileLoader = paramSoFileLoader;
        return;
      }
      finally
      {
        break label69;
      }
    final Runtime localRuntime = Runtime.getRuntime();
    final Method localMethod = getNativeLoadRuntimeMethod();
    boolean bool;
    if (localMethod != null)
    {
      bool = true;
      if (!bool)
        break label79;
      paramSoFileLoader = Api14Utils.getClassLoaderLdLoadLibrary();
    }
    while (true)
    {
      sSoFileLoader = new SoFileLoader()
      {
        // ERROR //
        private String getLibHash(String paramAnonymousString)
        {
          // Byte code:
          //   0: new 46	java/io/File
          //   3: dup
          //   4: aload_1
          //   5: invokespecial 49	java/io/File:<init>	(Ljava/lang/String;)V
          //   8: astore_1
          //   9: ldc 51
          //   11: invokestatic 57	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
          //   14: astore 5
          //   16: new 59	java/io/FileInputStream
          //   19: dup
          //   20: aload_1
          //   21: invokespecial 62	java/io/FileInputStream:<init>	(Ljava/io/File;)V
          //   24: astore 4
          //   26: aconst_null
          //   27: astore_3
          //   28: aload_3
          //   29: astore_1
          //   30: sipush 4096
          //   33: newarray byte
          //   35: astore 6
          //   37: aload_3
          //   38: astore_1
          //   39: aload 4
          //   41: aload 6
          //   43: invokevirtual 68	java/io/InputStream:read	([B)I
          //   46: istore_2
          //   47: iload_2
          //   48: ifle +17 -> 65
          //   51: aload_3
          //   52: astore_1
          //   53: aload 5
          //   55: aload 6
          //   57: iconst_0
          //   58: iload_2
          //   59: invokevirtual 72	java/security/MessageDigest:update	([BII)V
          //   62: goto -25 -> 37
          //   65: aload_3
          //   66: astore_1
          //   67: ldc 74
          //   69: iconst_1
          //   70: anewarray 4	java/lang/Object
          //   73: dup
          //   74: iconst_0
          //   75: new 76	java/math/BigInteger
          //   78: dup
          //   79: iconst_1
          //   80: aload 5
          //   82: invokevirtual 80	java/security/MessageDigest:digest	()[B
          //   85: invokespecial 83	java/math/BigInteger:<init>	(I[B)V
          //   88: aastore
          //   89: invokestatic 89	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
          //   92: astore_3
          //   93: aload_3
          //   94: astore_1
          //   95: aload 4
          //   97: ifnull +66 -> 163
          //   100: aload 4
          //   102: invokevirtual 92	java/io/InputStream:close	()V
          //   105: aload_3
          //   106: areturn
          //   107: astore_3
          //   108: goto +8 -> 116
          //   111: astore_3
          //   112: aload_3
          //   113: astore_1
          //   114: aload_3
          //   115: athrow
          //   116: aload 4
          //   118: ifnull +31 -> 149
          //   121: aload_1
          //   122: ifnull +22 -> 144
          //   125: aload 4
          //   127: invokevirtual 92	java/io/InputStream:close	()V
          //   130: goto +19 -> 149
          //   133: astore 4
          //   135: aload_1
          //   136: aload 4
          //   138: invokevirtual 96	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
          //   141: goto +8 -> 149
          //   144: aload 4
          //   146: invokevirtual 92	java/io/InputStream:close	()V
          //   149: aload_3
          //   150: athrow
          //   151: astore_1
          //   152: aload_1
          //   153: invokevirtual 100	java/security/NoSuchAlgorithmException:toString	()Ljava/lang/String;
          //   156: areturn
          //   157: astore_1
          //   158: aload_1
          //   159: invokevirtual 101	java/io/IOException:toString	()Ljava/lang/String;
          //   162: astore_1
          //   163: aload_1
          //   164: areturn
          //
          // Exception table:
          //   from	to	target	type
          //   30	37	107	finally
          //   39	47	107	finally
          //   53	62	107	finally
          //   67	93	107	finally
          //   114	116	107	finally
          //   30	37	111	java/lang/Throwable
          //   39	47	111	java/lang/Throwable
          //   53	62	111	java/lang/Throwable
          //   67	93	111	java/lang/Throwable
          //   125	130	133	java/lang/Throwable
          //   0	26	151	java/security/NoSuchAlgorithmException
          //   100	105	151	java/security/NoSuchAlgorithmException
          //   125	130	151	java/security/NoSuchAlgorithmException
          //   135	141	151	java/security/NoSuchAlgorithmException
          //   144	149	151	java/security/NoSuchAlgorithmException
          //   149	151	151	java/security/NoSuchAlgorithmException
          //   0	26	157	java/io/IOException
          //   100	105	157	java/io/IOException
          //   125	130	157	java/io/IOException
          //   135	141	157	java/io/IOException
          //   144	149	157	java/io/IOException
          //   149	151	157	java/io/IOException
        }

        // ERROR //
        public void load(String paramAnonymousString, int paramAnonymousInt)
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 24	com/facebook/soloader/SoLoader$1:val$hasNativeLoadMethod	Z
          //   4: ifeq +483 -> 487
          //   7: iload_2
          //   8: iconst_4
          //   9: iand
          //   10: iconst_4
          //   11: if_icmpne +8 -> 19
          //   14: iconst_1
          //   15: istore_2
          //   16: goto +5 -> 21
          //   19: iconst_0
          //   20: istore_2
          //   21: iload_2
          //   22: ifeq +11 -> 33
          //   25: aload_0
          //   26: getfield 26	com/facebook/soloader/SoLoader$1:val$localLdLibraryPath	Ljava/lang/String;
          //   29: astore_3
          //   30: goto +8 -> 38
          //   33: aload_0
          //   34: getfield 28	com/facebook/soloader/SoLoader$1:val$localLdLibraryPathNoZips	Ljava/lang/String;
          //   37: astore_3
          //   38: aconst_null
          //   39: astore 9
          //   41: aconst_null
          //   42: astore 10
          //   44: aconst_null
          //   45: astore 8
          //   47: aload 9
          //   49: astore 5
          //   51: aload_3
          //   52: astore 4
          //   54: aload 10
          //   56: astore 7
          //   58: aload_3
          //   59: astore 6
          //   61: aload_0
          //   62: getfield 30	com/facebook/soloader/SoLoader$1:val$runtime	Ljava/lang/Runtime;
          //   65: astore 11
          //   67: aload 9
          //   69: astore 5
          //   71: aload_3
          //   72: astore 4
          //   74: aload 10
          //   76: astore 7
          //   78: aload_3
          //   79: astore 6
          //   81: aload 11
          //   83: monitorenter
          //   84: aload 8
          //   86: astore 4
          //   88: aload_3
          //   89: astore 5
          //   91: getstatic 115	android/os/Build$VERSION:SDK_INT	I
          //   94: bipush 27
          //   96: if_icmpgt +53 -> 149
          //   99: aload 8
          //   101: astore 4
          //   103: aload_3
          //   104: astore 5
          //   106: aload_0
          //   107: getfield 32	com/facebook/soloader/SoLoader$1:val$nativeLoadRuntimeMethod	Ljava/lang/reflect/Method;
          //   110: aload_0
          //   111: getfield 30	com/facebook/soloader/SoLoader$1:val$runtime	Ljava/lang/Runtime;
          //   114: iconst_3
          //   115: anewarray 4	java/lang/Object
          //   118: dup
          //   119: iconst_0
          //   120: aload_1
          //   121: aastore
          //   122: dup
          //   123: iconst_1
          //   124: ldc 8
          //   126: invokevirtual 121	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
          //   129: aastore
          //   130: dup
          //   131: iconst_2
          //   132: aload_3
          //   133: aastore
          //   134: invokevirtual 127	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   137: checkcast 85	java/lang/String
          //   140: astore 6
          //   142: aload 6
          //   144: astore 4
          //   146: goto +46 -> 192
          //   149: aload 8
          //   151: astore 4
          //   153: aload_3
          //   154: astore 5
          //   156: aload_0
          //   157: getfield 32	com/facebook/soloader/SoLoader$1:val$nativeLoadRuntimeMethod	Ljava/lang/reflect/Method;
          //   160: aload_0
          //   161: getfield 30	com/facebook/soloader/SoLoader$1:val$runtime	Ljava/lang/Runtime;
          //   164: iconst_2
          //   165: anewarray 4	java/lang/Object
          //   168: dup
          //   169: iconst_0
          //   170: aload_1
          //   171: aastore
          //   172: dup
          //   173: iconst_1
          //   174: ldc 8
          //   176: invokevirtual 121	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
          //   179: aastore
          //   180: invokevirtual 127	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
          //   183: checkcast 85	java/lang/String
          //   186: astore 6
          //   188: aload 6
          //   190: astore 4
          //   192: aload 4
          //   194: ifnull +13 -> 207
          //   197: new 129	java/lang/UnsatisfiedLinkError
          //   200: dup
          //   201: aload 4
          //   203: invokespecial 130	java/lang/UnsatisfiedLinkError:<init>	(Ljava/lang/String;)V
          //   206: athrow
          //   207: aload 11
          //   209: monitorexit
          //   210: aload 4
          //   212: ifnull +279 -> 491
          //   215: new 132	java/lang/StringBuilder
          //   218: dup
          //   219: invokespecial 133	java/lang/StringBuilder:<init>	()V
          //   222: astore 5
          //   224: aload 5
          //   226: ldc 135
          //   228: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   231: pop
          //   232: aload 5
          //   234: aload 4
          //   236: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   239: pop
          //   240: aload 5
          //   242: ldc 141
          //   244: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   247: pop
          //   248: aload 5
          //   250: aload_0
          //   251: aload_1
          //   252: invokespecial 143	com/facebook/soloader/SoLoader$1:getLibHash	(Ljava/lang/String;)Ljava/lang/String;
          //   255: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   258: pop
          //   259: aload 5
          //   261: ldc 145
          //   263: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   266: pop
          //   267: aload 5
          //   269: aload_3
          //   270: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   273: pop
          //   274: ldc 147
          //   276: aload 5
          //   278: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   281: invokestatic 154	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   284: pop
          //   285: return
          //   286: astore 8
          //   288: aload 5
          //   290: astore_3
          //   291: aload 4
          //   293: astore 6
          //   295: aload 6
          //   297: astore 4
          //   299: aload_3
          //   300: astore 5
          //   302: aload 11
          //   304: monitorexit
          //   305: aload 6
          //   307: astore 5
          //   309: aload_3
          //   310: astore 4
          //   312: aload 6
          //   314: astore 7
          //   316: aload_3
          //   317: astore 6
          //   319: aload 8
          //   321: athrow
          //   322: astore_3
          //   323: goto +86 -> 409
          //   326: astore 8
          //   328: aload 7
          //   330: astore 5
          //   332: aload 6
          //   334: astore 4
          //   336: new 132	java/lang/StringBuilder
          //   339: dup
          //   340: invokespecial 133	java/lang/StringBuilder:<init>	()V
          //   343: astore_3
          //   344: aload 7
          //   346: astore 5
          //   348: aload 6
          //   350: astore 4
          //   352: aload_3
          //   353: ldc 156
          //   355: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   358: pop
          //   359: aload 7
          //   361: astore 5
          //   363: aload 6
          //   365: astore 4
          //   367: aload_3
          //   368: aload_1
          //   369: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   372: pop
          //   373: aload 7
          //   375: astore 5
          //   377: aload 6
          //   379: astore 4
          //   381: aload_3
          //   382: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   385: astore_3
          //   386: new 158	java/lang/RuntimeException
          //   389: dup
          //   390: aload_3
          //   391: aload 8
          //   393: invokespecial 161	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
          //   396: athrow
          //   397: astore 7
          //   399: aload_3
          //   400: astore 5
          //   402: aload 6
          //   404: astore 4
          //   406: aload 7
          //   408: astore_3
          //   409: aload 5
          //   411: ifnull +74 -> 485
          //   414: new 132	java/lang/StringBuilder
          //   417: dup
          //   418: invokespecial 133	java/lang/StringBuilder:<init>	()V
          //   421: astore 6
          //   423: aload 6
          //   425: ldc 135
          //   427: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   430: pop
          //   431: aload 6
          //   433: aload 5
          //   435: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   438: pop
          //   439: aload 6
          //   441: ldc 141
          //   443: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   446: pop
          //   447: aload 6
          //   449: aload_0
          //   450: aload_1
          //   451: invokespecial 143	com/facebook/soloader/SoLoader$1:getLibHash	(Ljava/lang/String;)Ljava/lang/String;
          //   454: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   457: pop
          //   458: aload 6
          //   460: ldc 145
          //   462: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   465: pop
          //   466: aload 6
          //   468: aload 4
          //   470: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   473: pop
          //   474: ldc 147
          //   476: aload 6
          //   478: invokevirtual 148	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   481: invokestatic 154	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
          //   484: pop
          //   485: aload_3
          //   486: athrow
          //   487: aload_1
          //   488: invokestatic 165	java/lang/System:load	(Ljava/lang/String;)V
          //   491: return
          //   492: astore 8
          //   494: aload 4
          //   496: astore 6
          //   498: goto -203 -> 295
          //
          // Exception table:
          //   from	to	target	type
          //   91	99	286	finally
          //   106	142	286	finally
          //   156	188	286	finally
          //   302	305	286	finally
          //   61	67	322	finally
          //   81	84	322	finally
          //   319	322	322	finally
          //   336	344	322	finally
          //   352	359	322	finally
          //   367	373	322	finally
          //   381	386	322	finally
          //   61	67	326	java/lang/IllegalAccessException
          //   61	67	326	java/lang/IllegalArgumentException
          //   61	67	326	java/lang/reflect/InvocationTargetException
          //   81	84	326	java/lang/IllegalAccessException
          //   81	84	326	java/lang/IllegalArgumentException
          //   81	84	326	java/lang/reflect/InvocationTargetException
          //   319	322	326	java/lang/IllegalAccessException
          //   319	322	326	java/lang/IllegalArgumentException
          //   319	322	326	java/lang/reflect/InvocationTargetException
          //   386	397	397	finally
          //   197	207	492	finally
          //   207	210	492	finally
        }
      };
      return;
      label69: throw paramSoFileLoader;
      bool = false;
      break;
      label79: paramSoFileLoader = null;
    }
  }

  private static void initSoSources(Context paramContext, int paramInt, @Nullable SoFileLoader paramSoFileLoader)
    throws IOException
  {
    sSoSourcesLock.writeLock().lock();
    while (true)
    {
      try
      {
        if (sSoSources == null)
        {
          Log.d("SoLoader", "init start");
          sFlags = paramInt;
          ArrayList localArrayList = new ArrayList();
          Object localObject = System.getenv("LD_LIBRARY_PATH");
          paramSoFileLoader = (SoFileLoader)localObject;
          if (localObject == null)
            paramSoFileLoader = "/vendor/lib:/system/lib";
          paramSoFileLoader = paramSoFileLoader.split(":");
          int i = 0;
          if (i < paramSoFileLoader.length)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("adding system library source: ");
            ((StringBuilder)localObject).append(paramSoFileLoader[i]);
            Log.d("SoLoader", ((StringBuilder)localObject).toString());
            localArrayList.add(new DirectorySoSource(new File(paramSoFileLoader[i]), 2));
            i += 1;
            continue;
          }
          if (paramContext != null)
            if ((paramInt & 0x1) != 0)
            {
              sBackupSoSource = null;
              Log.d("SoLoader", "adding exo package source: lib-main");
              localArrayList.add(0, new ExoSoSource(paramContext, "lib-main"));
            }
            else
            {
              paramSoFileLoader = paramContext.getApplicationInfo();
              if (((paramSoFileLoader.flags & 0x1) == 0) || ((paramSoFileLoader.flags & 0x80) != 0))
                break label522;
              paramInt = 1;
              break label524;
              if (Build.VERSION.SDK_INT > 17)
                break label533;
              paramInt = 1;
              sApplicationSoSource = new ApplicationSoSource(paramContext, paramInt);
              paramSoFileLoader = new StringBuilder();
              paramSoFileLoader.append("adding application source: ");
              paramSoFileLoader.append(sApplicationSoSource.toString());
              Log.d("SoLoader", paramSoFileLoader.toString());
              localArrayList.add(0, sApplicationSoSource);
              paramInt = 1;
              sBackupSoSource = new ApkSoSource(paramContext, "lib-main", paramInt);
              paramContext = new StringBuilder();
              paramContext.append("adding backup  source: ");
              paramContext.append(sBackupSoSource.toString());
              Log.d("SoLoader", paramContext.toString());
              localArrayList.add(0, sBackupSoSource);
            }
          paramContext = (SoSource[])localArrayList.toArray(new SoSource[localArrayList.size()]);
          int j = makePrepareFlags();
          paramInt = paramContext.length;
          i = paramInt - 1;
          if (paramInt > 0)
          {
            paramSoFileLoader = new StringBuilder();
            paramSoFileLoader.append("Preparing SO source: ");
            paramSoFileLoader.append(paramContext[i]);
            Log.d("SoLoader", paramSoFileLoader.toString());
            paramContext[i].prepare(j);
            paramInt = i;
            continue;
          }
          sSoSources = paramContext;
          sSoSourcesVersion += 1;
          paramContext = new StringBuilder();
          paramContext.append("init finish: ");
          paramContext.append(sSoSources.length);
          paramContext.append(" SO sources prepared");
          Log.d("SoLoader", paramContext.toString());
        }
        else
        {
          return;
        }
      }
      finally
      {
        Log.d("SoLoader", "init exiting");
        sSoSourcesLock.writeLock().unlock();
      }
      label522: paramInt = 0;
      label524: if (paramInt != 0)
      {
        paramInt = 0;
        continue;
        label533: paramInt = 0;
      }
    }
  }

  public static boolean loadLibrary(String paramString)
  {
    return loadLibrary(paramString, 0);
  }

  public static boolean loadLibrary(String paramString, int paramInt)
    throws UnsatisfiedLinkError
  {
    sSoSourcesLock.readLock().lock();
    try
    {
      if (sSoSources == null)
        if ("http://www.android.com/".equals(System.getProperty("java.vendor.url")))
          assertInitialized();
        else
          try
          {
            boolean bool = sLoadedLibraries.contains(paramString) ^ true;
            if (bool)
              if (sSystemLoadLibraryWrapper != null)
                sSystemLoadLibraryWrapper.loadLibrary(paramString);
              else
                System.loadLibrary(paramString);
            return bool;
          }
          finally
          {
          }
      sSoSourcesLock.readLock().unlock();
      String str2 = MergedSoMapping.mapLibName(paramString);
      String str1;
      if (str2 != null)
        str1 = str2;
      else
        str1 = paramString;
      return loadLibraryBySoName(System.mapLibraryName(str1), paramString, str2, paramInt, null);
    }
    finally
    {
      sSoSourcesLock.readLock().unlock();
    }
    throw paramString;
  }

  static void loadLibraryBySoName(String paramString, int paramInt, StrictMode.ThreadPolicy paramThreadPolicy)
  {
    loadLibraryBySoName(paramString, null, null, paramInt, paramThreadPolicy);
  }

  // ERROR //
  private static boolean loadLibraryBySoName(String paramString1, @Nullable String paramString2, @Nullable String paramString3, int paramInt, @Nullable StrictMode.ThreadPolicy paramThreadPolicy)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 456	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: istore 8
    //   6: iconst_0
    //   7: istore 7
    //   9: iload 8
    //   11: ifne +17 -> 28
    //   14: getstatic 92	com/facebook/soloader/SoLoader:sLoadedAndMergedLibraries	Ljava/util/Set;
    //   17: aload_1
    //   18: invokeinterface 459 2 0
    //   23: ifeq +5 -> 28
    //   26: iconst_0
    //   27: ireturn
    //   28: ldc 2
    //   30: monitorenter
    //   31: getstatic 76	com/facebook/soloader/SoLoader:sLoadedLibraries	Ljava/util/HashSet;
    //   34: aload_0
    //   35: invokevirtual 431	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   38: ifeq +453 -> 491
    //   41: aload_2
    //   42: ifnonnull +443 -> 485
    //   45: ldc 2
    //   47: monitorexit
    //   48: iconst_0
    //   49: ireturn
    //   50: getstatic 81	com/facebook/soloader/SoLoader:sLoadingLibraries	Ljava/util/Map;
    //   53: aload_0
    //   54: invokeinterface 464 2 0
    //   59: ifeq +17 -> 76
    //   62: getstatic 81	com/facebook/soloader/SoLoader:sLoadingLibraries	Ljava/util/Map;
    //   65: aload_0
    //   66: invokeinterface 468 2 0
    //   71: astore 9
    //   73: goto +24 -> 97
    //   76: new 4	java/lang/Object
    //   79: dup
    //   80: invokespecial 101	java/lang/Object:<init>	()V
    //   83: astore 9
    //   85: getstatic 81	com/facebook/soloader/SoLoader:sLoadingLibraries	Ljava/util/Map;
    //   88: aload_0
    //   89: aload 9
    //   91: invokeinterface 472 3 0
    //   96: pop
    //   97: ldc 2
    //   99: monitorexit
    //   100: aload 9
    //   102: monitorenter
    //   103: iload 5
    //   105: istore 6
    //   107: iload 5
    //   109: ifne +196 -> 305
    //   112: ldc 2
    //   114: monitorenter
    //   115: getstatic 76	com/facebook/soloader/SoLoader:sLoadedLibraries	Ljava/util/HashSet;
    //   118: aload_0
    //   119: invokevirtual 431	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   122: ifeq +18 -> 140
    //   125: aload_2
    //   126: ifnonnull +11 -> 137
    //   129: ldc 2
    //   131: monitorexit
    //   132: aload 9
    //   134: monitorexit
    //   135: iconst_0
    //   136: ireturn
    //   137: iconst_1
    //   138: istore 5
    //   140: ldc 2
    //   142: monitorexit
    //   143: iload 5
    //   145: istore 6
    //   147: iload 5
    //   149: ifne +156 -> 305
    //   152: new 155	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   159: astore 10
    //   161: aload 10
    //   163: ldc_w 474
    //   166: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload 10
    //   172: aload_0
    //   173: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: pop
    //   177: ldc 31
    //   179: aload 10
    //   181: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   187: pop
    //   188: aload_0
    //   189: iload_3
    //   190: aload 4
    //   192: invokestatic 476	com/facebook/soloader/SoLoader:doLoadLibraryBySoName	(Ljava/lang/String;ILandroid/os/StrictMode$ThreadPolicy;)V
    //   195: ldc 2
    //   197: monitorenter
    //   198: new 155	java/lang/StringBuilder
    //   201: dup
    //   202: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   205: astore 4
    //   207: aload 4
    //   209: ldc_w 478
    //   212: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload 4
    //   218: aload_0
    //   219: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: ldc 31
    //   225: aload 4
    //   227: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   233: pop
    //   234: getstatic 76	com/facebook/soloader/SoLoader:sLoadedLibraries	Ljava/util/HashSet;
    //   237: aload_0
    //   238: invokevirtual 479	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   241: pop
    //   242: ldc 2
    //   244: monitorexit
    //   245: iload 5
    //   247: istore 6
    //   249: goto +56 -> 305
    //   252: astore_0
    //   253: ldc 2
    //   255: monitorexit
    //   256: aload_0
    //   257: athrow
    //   258: astore_0
    //   259: aload_0
    //   260: invokevirtual 480	java/lang/UnsatisfiedLinkError:getMessage	()Ljava/lang/String;
    //   263: astore_1
    //   264: aload_1
    //   265: ifnull +22 -> 287
    //   268: aload_1
    //   269: ldc_w 482
    //   272: invokevirtual 484	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   275: ifeq +12 -> 287
    //   278: new 11	com/facebook/soloader/SoLoader$WrongAbiError
    //   281: dup
    //   282: aload_0
    //   283: invokespecial 487	com/facebook/soloader/SoLoader$WrongAbiError:<init>	(Ljava/lang/Throwable;)V
    //   286: athrow
    //   287: aload_0
    //   288: athrow
    //   289: astore_0
    //   290: new 137	java/lang/RuntimeException
    //   293: dup
    //   294: aload_0
    //   295: invokespecial 488	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   298: athrow
    //   299: astore_0
    //   300: ldc 2
    //   302: monitorexit
    //   303: aload_0
    //   304: athrow
    //   305: iload 7
    //   307: istore_3
    //   308: aload_1
    //   309: invokestatic 456	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   312: ifne +20 -> 332
    //   315: iload 7
    //   317: istore_3
    //   318: getstatic 92	com/facebook/soloader/SoLoader:sLoadedAndMergedLibraries	Ljava/util/Set;
    //   321: aload_1
    //   322: invokeinterface 459 2 0
    //   327: ifeq +5 -> 332
    //   330: iconst_1
    //   331: istore_3
    //   332: aload_2
    //   333: ifnull +133 -> 466
    //   336: iload_3
    //   337: ifne +129 -> 466
    //   340: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   343: ifeq +39 -> 382
    //   346: new 155	java/lang/StringBuilder
    //   349: dup
    //   350: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   353: astore_2
    //   354: aload_2
    //   355: ldc_w 490
    //   358: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: pop
    //   362: aload_2
    //   363: aload_1
    //   364: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: pop
    //   368: aload_2
    //   369: ldc 187
    //   371: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: pop
    //   375: aload_2
    //   376: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   379: invokestatic 192	com/facebook/soloader/Api18TraceUtils:beginTraceSection	(Ljava/lang/String;)V
    //   382: new 155	java/lang/StringBuilder
    //   385: dup
    //   386: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   389: astore_2
    //   390: aload_2
    //   391: ldc_w 492
    //   394: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: pop
    //   398: aload_2
    //   399: aload_1
    //   400: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: pop
    //   404: aload_2
    //   405: ldc_w 494
    //   408: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: pop
    //   412: aload_2
    //   413: aload_0
    //   414: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: ldc 31
    //   420: aload_2
    //   421: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   427: pop
    //   428: aload_1
    //   429: invokestatic 497	com/facebook/soloader/MergedSoMapping:invokeJniOnload	(Ljava/lang/String;)V
    //   432: getstatic 92	com/facebook/soloader/SoLoader:sLoadedAndMergedLibraries	Ljava/util/Set;
    //   435: aload_1
    //   436: invokeinterface 498 2 0
    //   441: pop
    //   442: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   445: ifeq +21 -> 466
    //   448: invokestatic 229	com/facebook/soloader/Api18TraceUtils:endSection	()V
    //   451: goto +15 -> 466
    //   454: astore_0
    //   455: getstatic 99	com/facebook/soloader/SoLoader:SYSTRACE_LIBRARY_LOADING	Z
    //   458: ifeq +6 -> 464
    //   461: invokestatic 229	com/facebook/soloader/Api18TraceUtils:endSection	()V
    //   464: aload_0
    //   465: athrow
    //   466: aload 9
    //   468: monitorexit
    //   469: iload 6
    //   471: iconst_1
    //   472: ixor
    //   473: ireturn
    //   474: aload 9
    //   476: monitorexit
    //   477: aload_0
    //   478: athrow
    //   479: astore_0
    //   480: ldc 2
    //   482: monitorexit
    //   483: aload_0
    //   484: athrow
    //   485: iconst_1
    //   486: istore 5
    //   488: goto -438 -> 50
    //   491: iconst_0
    //   492: istore 5
    //   494: goto -444 -> 50
    //   497: astore_0
    //   498: goto -24 -> 474
    //
    // Exception table:
    //   from	to	target	type
    //   198	245	252	finally
    //   253	256	252	finally
    //   152	195	258	java/lang/UnsatisfiedLinkError
    //   152	195	289	java/io/IOException
    //   115	125	299	finally
    //   129	132	299	finally
    //   140	143	299	finally
    //   300	303	299	finally
    //   382	442	454	finally
    //   31	41	479	finally
    //   45	48	479	finally
    //   50	73	479	finally
    //   76	97	479	finally
    //   97	100	479	finally
    //   480	483	479	finally
    //   112	115	497	finally
    //   132	135	497	finally
    //   152	195	497	finally
    //   195	198	497	finally
    //   256	258	497	finally
    //   259	264	497	finally
    //   268	287	497	finally
    //   287	289	497	finally
    //   290	299	497	finally
    //   303	305	497	finally
    //   308	315	497	finally
    //   318	330	497	finally
    //   340	382	497	finally
    //   442	451	497	finally
    //   455	464	497	finally
    //   464	466	497	finally
    //   466	469	497	finally
    //   474	477	497	finally
  }

  public static String makeLdLibraryPath()
  {
    sSoSourcesLock.readLock().lock();
    try
    {
      assertInitialized();
      Log.d("SoLoader", "makeLdLibraryPath");
      Object localObject1 = new ArrayList();
      Object localObject3 = sSoSources;
      int i = 0;
      while (i < localObject3.length)
      {
        localObject3[i].addToLdLibraryPath((Collection)localObject1);
        i += 1;
      }
      localObject1 = TextUtils.join(":", (Iterable)localObject1);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("makeLdLibraryPath final path: ");
      ((StringBuilder)localObject3).append((String)localObject1);
      Log.d("SoLoader", ((StringBuilder)localObject3).toString());
      return localObject1;
    }
    finally
    {
      sSoSourcesLock.readLock().unlock();
    }
  }

  @Nullable
  public static String makeNonZipPath(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = paramString.split(":");
    ArrayList localArrayList = new ArrayList(paramString.length);
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      if (!localObject.contains("!"))
        localArrayList.add(localObject);
      i += 1;
    }
    return TextUtils.join(":", localArrayList);
  }

  private static int makePrepareFlags()
  {
    sSoSourcesLock.writeLock().lock();
    try
    {
      int i = sFlags;
      if ((i & 0x2) != 0)
        i = 1;
      else
        i = 0;
      return i;
    }
    finally
    {
      sSoSourcesLock.writeLock().unlock();
    }
  }

  public static void prependSoSource(SoSource paramSoSource)
    throws IOException
  {
    sSoSourcesLock.writeLock().lock();
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Prepending to SO sources: ");
      ((StringBuilder)localObject).append(paramSoSource);
      Log.d("SoLoader", ((StringBuilder)localObject).toString());
      assertInitialized();
      paramSoSource.prepare(makePrepareFlags());
      localObject = new SoSource[sSoSources.length + 1];
      localObject[0] = paramSoSource;
      System.arraycopy(sSoSources, 0, localObject, 1, sSoSources.length);
      sSoSources = (SoSource[])localObject;
      sSoSourcesVersion += 1;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Prepended to SO sources: ");
      ((StringBuilder)localObject).append(paramSoSource);
      Log.d("SoLoader", ((StringBuilder)localObject).toString());
      return;
    }
    finally
    {
      sSoSourcesLock.writeLock().unlock();
    }
    throw paramSoSource;
  }

  static void resetStatus()
  {
    try
    {
      sLoadedLibraries.clear();
      sLoadingLibraries.clear();
      sSoFileLoader = null;
      setSoSources(null);
      return;
    }
    finally
    {
    }
  }

  public static void setInTestMode()
  {
    setSoSources(new SoSource[] { new NoopSoSource() });
  }

  static void setSoFileLoader(SoFileLoader paramSoFileLoader)
  {
    sSoFileLoader = paramSoFileLoader;
  }

  static void setSoSources(SoSource[] paramArrayOfSoSource)
  {
    sSoSourcesLock.writeLock().lock();
    try
    {
      sSoSources = paramArrayOfSoSource;
      sSoSourcesVersion += 1;
      return;
    }
    finally
    {
      sSoSourcesLock.writeLock().unlock();
    }
    throw paramArrayOfSoSource;
  }

  public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper paramSystemLoadLibraryWrapper)
  {
    sSystemLoadLibraryWrapper = paramSystemLoadLibraryWrapper;
  }

  public static File unpackLibraryAndDependencies(String paramString)
    throws UnsatisfiedLinkError
  {
    assertInitialized();
    try
    {
      paramString = unpackLibraryBySoName(System.mapLibraryName(paramString));
      return paramString;
    }
    catch (IOException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  static File unpackLibraryBySoName(String paramString)
    throws IOException
  {
    sSoSourcesLock.readLock().lock();
    int i = 0;
    try
    {
      while (i < sSoSources.length)
      {
        File localFile = sSoSources[i].unpackLibrary(paramString);
        if (localFile != null)
        {
          sSoSourcesLock.readLock().unlock();
          return localFile;
        }
        i += 1;
      }
      sSoSourcesLock.readLock().unlock();
      throw new FileNotFoundException(paramString);
    }
    finally
    {
    }
    throw paramString;
  }

  @TargetApi(14)
  @DoNotOptimize
  private static class Api14Utils
  {
    public static String getClassLoaderLdLoadLibrary()
    {
      Object localObject = SoLoader.class.getClassLoader();
      if (!(localObject instanceof BaseDexClassLoader))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ClassLoader ");
        localStringBuilder.append(localObject.getClass().getName());
        localStringBuilder.append(" should be of type BaseDexClassLoader");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      try
      {
        localObject = (BaseDexClassLoader)localObject;
        localObject = (String)BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke(localObject, new Object[0]);
        return localObject;
      }
      catch (Exception localException)
      {
        throw new RuntimeException("Cannot call getLdLibraryPath", localException);
      }
    }
  }

  public static final class WrongAbiError extends UnsatisfiedLinkError
  {
    WrongAbiError(Throwable paramThrowable)
    {
      super();
      initCause(paramThrowable);
    }
  }
}