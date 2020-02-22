package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PublicSuffixDatabase
{
  private static final String[] EMPTY_RULE = new String[0];
  private static final byte EXCEPTION_MARKER = 33;
  private static final String[] PREVAILING_RULE = { "*" };
  public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
  private static final byte[] WILDCARD_LABEL = { 42 };
  private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
  private final AtomicBoolean listRead = new AtomicBoolean(false);
  private byte[] publicSuffixExceptionListBytes;
  private byte[] publicSuffixListBytes;
  private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

  private static String binarySearchBytes(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, int paramInt)
  {
    int n = paramArrayOfByte.length;
    int i1 = 0;
    if (i1 < n)
    {
      int i = (i1 + n) / 2;
      while ((i > -1) && (paramArrayOfByte[i] != 10))
        i -= 1;
      int i4 = i + 1;
      i = 1;
      int i5;
      while (true)
      {
        i5 = i4 + i;
        if (paramArrayOfByte[i5] == 10)
          break;
        i += 1;
      }
      int i6 = i5 - i4;
      int j = paramInt;
      int m = 0;
      i = 0;
      int k = 0;
      while (true)
      {
        int i2;
        if (m != 0)
        {
          m = 0;
          i2 = 46;
        }
        else
        {
          i2 = paramArrayOfByte1[j][i] & 0xFF;
        }
        int i7 = i2 - (paramArrayOfByte[(i4 + k)] & 0xFF);
        if (i7 == 0)
        {
          i2 = k + 1;
          int i3 = i + 1;
          if (i2 == i6)
          {
            i = i3;
            k = i2;
          }
          else
          {
            i = i3;
            k = i2;
            if (paramArrayOfByte1[j].length != i3)
              continue;
            if (j != paramArrayOfByte1.length - 1)
              break label316;
            k = i2;
            i = i3;
          }
        }
        else
        {
          if (i7 < 0)
          {
            i = i4 - 1;
            label211: n = i;
            break;
          }
          if (i7 > 0);
          for (i = i5 + 1; ; i = i5 + 1)
          {
            i1 = i;
            break;
            k = i6 - k;
            i = paramArrayOfByte1[j].length - i;
            while (true)
            {
              j += 1;
              if (j >= paramArrayOfByte1.length)
                break;
              i += paramArrayOfByte1[j].length;
            }
            if (i < k)
            {
              i = i4 - 1;
              break label211;
            }
            if (i <= k)
              break label300;
          }
          label300: return new String(paramArrayOfByte, i4, i6, StandardCharsets.UTF_8);
          label316: j += 1;
          m = 1;
          i = -1;
          k = i2;
        }
      }
    }
    return null;
  }

  private String[] findMatchingRule(String[] paramArrayOfString)
  {
    boolean bool = this.listRead.get();
    int j = 0;
    if ((!bool) && (this.listRead.compareAndSet(false, true)))
      readTheListUninterruptibly();
    try
    {
      this.readCompleteLatch.await();
      break label51;
      label45: Thread.currentThread().interrupt();
      try
      {
        label51: if (this.publicSuffixListBytes == null)
          throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        byte[][] arrayOfByte = new byte[paramArrayOfString.length][];
        int i = 0;
        while (i < paramArrayOfString.length)
        {
          arrayOfByte[i] = paramArrayOfString[i].getBytes(StandardCharsets.UTF_8);
          i += 1;
        }
        i = 0;
        while (i < arrayOfByte.length)
        {
          paramArrayOfString = binarySearchBytes(this.publicSuffixListBytes, arrayOfByte, i);
          if (paramArrayOfString != null)
            break label143;
          i += 1;
        }
        paramArrayOfString = null;
        label143: if (arrayOfByte.length > 1)
        {
          localObject2 = (byte[][])arrayOfByte.clone();
          i = 0;
          while (i < localObject2.length - 1)
          {
            localObject2[i] = WILDCARD_LABEL;
            localObject1 = binarySearchBytes(this.publicSuffixListBytes, (byte[][])localObject2, i);
            if (localObject1 != null)
              break label208;
            i += 1;
          }
        }
        Object localObject1 = null;
        label208: if (localObject1 != null)
        {
          i = j;
          while (i < arrayOfByte.length - 1)
          {
            localObject2 = binarySearchBytes(this.publicSuffixExceptionListBytes, arrayOfByte, i);
            if (localObject2 != null)
              break label254;
            i += 1;
          }
        }
        Object localObject2 = null;
        label254: if (localObject2 != null)
        {
          paramArrayOfString = new StringBuilder();
          paramArrayOfString.append("!");
          paramArrayOfString.append((String)localObject2);
          return paramArrayOfString.toString().split("\\.");
        }
        if ((paramArrayOfString == null) && (localObject1 == null))
          return PREVAILING_RULE;
        if (paramArrayOfString != null)
          paramArrayOfString = paramArrayOfString.split("\\.");
        else
          paramArrayOfString = EMPTY_RULE;
        if (localObject1 != null)
          localObject1 = ((String)localObject1).split("\\.");
        else
          localObject1 = EMPTY_RULE;
        if (paramArrayOfString.length > localObject1.length)
          return paramArrayOfString;
        return localObject1;
      }
      finally
      {
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      break label45;
    }
  }

  public static PublicSuffixDatabase get()
  {
    return instance;
  }

  // ERROR //
  private void readTheList()
    throws java.io.IOException
  {
    // Byte code:
    //   0: ldc 2
    //   2: ldc 14
    //   4: invokevirtual 150	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   7: astore_1
    //   8: aload_1
    //   9: ifnonnull +4 -> 13
    //   12: return
    //   13: new 152	okio/GzipSource
    //   16: dup
    //   17: aload_1
    //   18: invokestatic 158	okio/Okio:source	(Ljava/io/InputStream;)Lokio/Source;
    //   21: invokespecial 161	okio/GzipSource:<init>	(Lokio/Source;)V
    //   24: invokestatic 165	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   27: astore_3
    //   28: aconst_null
    //   29: astore_2
    //   30: aload_2
    //   31: astore_1
    //   32: aload_3
    //   33: invokeinterface 171 1 0
    //   38: newarray byte
    //   40: astore 4
    //   42: aload_2
    //   43: astore_1
    //   44: aload_3
    //   45: aload 4
    //   47: invokeinterface 175 2 0
    //   52: aload_2
    //   53: astore_1
    //   54: aload_3
    //   55: invokeinterface 171 1 0
    //   60: newarray byte
    //   62: astore 5
    //   64: aload_2
    //   65: astore_1
    //   66: aload_3
    //   67: aload 5
    //   69: invokeinterface 175 2 0
    //   74: aload_3
    //   75: ifnull +9 -> 84
    //   78: aload_3
    //   79: invokeinterface 178 1 0
    //   84: aload_0
    //   85: monitorenter
    //   86: aload_0
    //   87: aload 4
    //   89: putfield 97	okhttp3/internal/publicsuffix/PublicSuffixDatabase:publicSuffixListBytes	[B
    //   92: aload_0
    //   93: aload 5
    //   95: putfield 119	okhttp3/internal/publicsuffix/PublicSuffixDatabase:publicSuffixExceptionListBytes	[B
    //   98: aload_0
    //   99: monitorexit
    //   100: aload_0
    //   101: getfield 57	okhttp3/internal/publicsuffix/PublicSuffixDatabase:readCompleteLatch	Ljava/util/concurrent/CountDownLatch;
    //   104: invokevirtual 181	java/util/concurrent/CountDownLatch:countDown	()V
    //   107: return
    //   108: astore_1
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_1
    //   112: athrow
    //   113: astore_2
    //   114: goto +8 -> 122
    //   117: astore_2
    //   118: aload_2
    //   119: astore_1
    //   120: aload_2
    //   121: athrow
    //   122: aload_3
    //   123: ifnull +31 -> 154
    //   126: aload_1
    //   127: ifnull +21 -> 148
    //   130: aload_3
    //   131: invokeinterface 178 1 0
    //   136: goto +18 -> 154
    //   139: astore_3
    //   140: aload_1
    //   141: aload_3
    //   142: invokevirtual 185	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   145: goto +9 -> 154
    //   148: aload_3
    //   149: invokeinterface 178 1 0
    //   154: aload_2
    //   155: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   86	100	108	finally
    //   109	111	108	finally
    //   32	42	113	finally
    //   44	52	113	finally
    //   54	64	113	finally
    //   66	74	113	finally
    //   120	122	113	finally
    //   32	42	117	java/lang/Throwable
    //   44	52	117	java/lang/Throwable
    //   54	64	117	java/lang/Throwable
    //   66	74	117	java/lang/Throwable
    //   130	136	139	java/lang/Throwable
  }

  // ERROR //
  private void readTheListUninterruptibly()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: invokespecial 190	okhttp3/internal/publicsuffix/PublicSuffixDatabase:readTheList	()V
    //   6: iload_1
    //   7: ifeq +9 -> 16
    //   10: invokestatic 92	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   13: invokevirtual 95	java/lang/Thread:interrupt	()V
    //   16: return
    //   17: astore_2
    //   18: goto +34 -> 52
    //   21: astore_2
    //   22: invokestatic 195	okhttp3/internal/platform/Platform:get	()Lokhttp3/internal/platform/Platform;
    //   25: iconst_5
    //   26: ldc 197
    //   28: aload_2
    //   29: invokevirtual 201	okhttp3/internal/platform/Platform:log	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   32: iload_1
    //   33: ifeq +9 -> 42
    //   36: invokestatic 92	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   39: invokevirtual 95	java/lang/Thread:interrupt	()V
    //   42: return
    //   43: invokestatic 204	java/lang/Thread:interrupted	()Z
    //   46: pop
    //   47: iconst_1
    //   48: istore_1
    //   49: goto -47 -> 2
    //   52: iload_1
    //   53: ifeq +9 -> 62
    //   56: invokestatic 92	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   59: invokevirtual 95	java/lang/Thread:interrupt	()V
    //   62: aload_2
    //   63: athrow
    //   64: astore_2
    //   65: goto -22 -> 43
    //
    // Exception table:
    //   from	to	target	type
    //   2	6	17	finally
    //   22	32	17	finally
    //   43	47	17	finally
    //   2	6	21	java/io/IOException
    //   2	6	64	java/io/InterruptedIOException
  }

  public String getEffectiveTldPlusOne(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("domain == null");
    Object localObject = IDN.toUnicode(paramString).split("\\.");
    String[] arrayOfString = findMatchingRule((String[])localObject);
    if ((localObject.length == arrayOfString.length) && (arrayOfString[0].charAt(0) != '!'))
      return null;
    int i;
    if (arrayOfString[0].charAt(0) == '!')
      i = localObject.length - arrayOfString.length;
    else
      i = localObject.length - (arrayOfString.length + 1);
    localObject = new StringBuilder();
    paramString = paramString.split("\\.");
    while (i < paramString.length)
    {
      ((StringBuilder)localObject).append(paramString[i]);
      ((StringBuilder)localObject).append('.');
      i += 1;
    }
    ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
    return ((StringBuilder)localObject).toString();
  }

  void setListBytes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this.publicSuffixListBytes = paramArrayOfByte1;
    this.publicSuffixExceptionListBytes = paramArrayOfByte2;
    this.listRead.set(true);
    this.readCompleteLatch.countDown();
  }
}