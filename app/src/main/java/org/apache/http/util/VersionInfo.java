package org.apache.http.util;

import java.util.ArrayList;
import java.util.Map;

public class VersionInfo
{
  public static final String PROPERTY_MODULE = "info.module";
  public static final String PROPERTY_RELEASE = "info.release";
  public static final String PROPERTY_TIMESTAMP = "info.timestamp";
  public static final String UNAVAILABLE = "UNAVAILABLE";
  public static final String VERSION_PROPERTY_FILE = "version.properties";
  private final String infoClassloader;
  private final String infoModule;
  private final String infoPackage;
  private final String infoRelease;
  private final String infoTimestamp;

  protected VersionInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    this.infoPackage = paramString1;
    if (paramString2 == null)
      paramString2 = "UNAVAILABLE";
    this.infoModule = paramString2;
    if (paramString3 == null)
      paramString3 = "UNAVAILABLE";
    this.infoRelease = paramString3;
    if (paramString4 == null)
      paramString4 = "UNAVAILABLE";
    this.infoTimestamp = paramString4;
    if (paramString5 == null)
      paramString5 = "UNAVAILABLE";
    this.infoClassloader = paramString5;
  }

  protected static final VersionInfo fromMap(String paramString, Map paramMap, ClassLoader paramClassLoader)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Package identifier must not be null.");
    Object localObject4 = null;
    Object localObject2;
    Object localObject1;
    Object localObject3;
    if (paramMap != null)
    {
      localObject2 = (String)paramMap.get("info.module");
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((String)localObject2).length() < 1)
          localObject1 = null;
      }
      localObject3 = (String)paramMap.get("info.release");
      localObject2 = localObject3;
      if (localObject3 != null)
        if (((String)localObject3).length() >= 1)
        {
          localObject2 = localObject3;
          if (!((String)localObject3).equals("${pom.version}"));
        }
        else
        {
          localObject2 = null;
        }
      paramMap = (String)paramMap.get("info.timestamp");
      if ((paramMap != null) && ((paramMap.length() < 1) || (paramMap.equals("${mvn.timestamp}"))))
        paramMap = null;
      localObject3 = paramMap;
    }
    else
    {
      localObject1 = null;
      paramMap = localObject1;
      localObject3 = paramMap;
      localObject2 = paramMap;
    }
    paramMap = localObject4;
    if (paramClassLoader != null)
      paramMap = paramClassLoader.toString();
    return new VersionInfo(paramString, localObject1, (String)localObject2, (String)localObject3, paramMap);
  }

  // ERROR //
  public static final VersionInfo loadVersionInfo(String paramString, ClassLoader paramClassLoader)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +13 -> 14
    //   4: new 31	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 33
    //   10: invokespecial 36	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: astore_2
    //   16: aload_1
    //   17: ifnonnull +10 -> 27
    //   20: invokestatic 85	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   23: invokevirtual 89	java/lang/Thread:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   26: astore_2
    //   27: aconst_null
    //   28: astore_3
    //   29: new 91	java/lang/StringBuffer
    //   32: dup
    //   33: invokespecial 92	java/lang/StringBuffer:<init>	()V
    //   36: astore_1
    //   37: aload_1
    //   38: aload_0
    //   39: bipush 46
    //   41: bipush 47
    //   43: invokevirtual 96	java/lang/String:replace	(CC)Ljava/lang/String;
    //   46: invokevirtual 100	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   49: pop
    //   50: aload_1
    //   51: ldc 102
    //   53: invokevirtual 100	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   56: pop
    //   57: aload_1
    //   58: ldc 19
    //   60: invokevirtual 100	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   63: pop
    //   64: aload_2
    //   65: aload_1
    //   66: invokevirtual 103	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   69: invokevirtual 109	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   72: astore 4
    //   74: aload 4
    //   76: ifnull +33 -> 109
    //   79: new 111	java/util/Properties
    //   82: dup
    //   83: invokespecial 112	java/util/Properties:<init>	()V
    //   86: astore_1
    //   87: aload_1
    //   88: aload 4
    //   90: invokevirtual 116	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   93: aload 4
    //   95: invokevirtual 121	java/io/InputStream:close	()V
    //   98: goto +13 -> 111
    //   101: astore_1
    //   102: aload 4
    //   104: invokevirtual 121	java/io/InputStream:close	()V
    //   107: aload_1
    //   108: athrow
    //   109: aconst_null
    //   110: astore_1
    //   111: aload_1
    //   112: ifnull +10 -> 122
    //   115: aload_0
    //   116: aload_1
    //   117: aload_2
    //   118: invokestatic 123	org/apache/http/util/VersionInfo:fromMap	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/ClassLoader;)Lorg/apache/http/util/VersionInfo;
    //   121: astore_3
    //   122: aload_3
    //   123: areturn
    //   124: astore_1
    //   125: goto -16 -> 109
    //   128: astore 4
    //   130: goto -19 -> 111
    //
    // Exception table:
    //   from	to	target	type
    //   79	93	101	finally
    //   29	74	124	java/io/IOException
    //   102	109	124	java/io/IOException
    //   93	98	128	java/io/IOException
  }

  public static final VersionInfo[] loadVersionInfo(String[] paramArrayOfString, ClassLoader paramClassLoader)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("Package identifier list must not be null.");
    ArrayList localArrayList = new ArrayList(paramArrayOfString.length);
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      VersionInfo localVersionInfo = loadVersionInfo(paramArrayOfString[i], paramClassLoader);
      if (localVersionInfo != null)
        localArrayList.add(localVersionInfo);
      i += 1;
    }
    return (VersionInfo[])localArrayList.toArray(new VersionInfo[localArrayList.size()]);
  }

  public final String getClassloader()
  {
    return this.infoClassloader;
  }

  public final String getModule()
  {
    return this.infoModule;
  }

  public final String getPackage()
  {
    return this.infoPackage;
  }

  public final String getRelease()
  {
    return this.infoRelease;
  }

  public final String getTimestamp()
  {
    return this.infoTimestamp;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.infoPackage.length() + 20 + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
    localStringBuffer.append("VersionInfo(");
    localStringBuffer.append(this.infoPackage);
    localStringBuffer.append(':');
    localStringBuffer.append(this.infoModule);
    if (!"UNAVAILABLE".equals(this.infoRelease))
    {
      localStringBuffer.append(':');
      localStringBuffer.append(this.infoRelease);
    }
    if (!"UNAVAILABLE".equals(this.infoTimestamp))
    {
      localStringBuffer.append(':');
      localStringBuffer.append(this.infoTimestamp);
    }
    localStringBuffer.append(')');
    if (!"UNAVAILABLE".equals(this.infoClassloader))
    {
      localStringBuffer.append('@');
      localStringBuffer.append(this.infoClassloader);
    }
    return localStringBuffer.toString();
  }
}