package com.qx.qgbox.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChangeDataUtil
{
  // ERROR //
  public static void createFileWithByte(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 17	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 20	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_2
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_0
    //   13: aload_2
    //   14: invokevirtual 24	java/io/File:exists	()Z
    //   17: ifeq +8 -> 25
    //   20: aload_2
    //   21: invokevirtual 27	java/io/File:delete	()Z
    //   24: pop
    //   25: aload_2
    //   26: invokevirtual 30	java/io/File:createNewFile	()Z
    //   29: pop
    //   30: new 32	java/io/FileOutputStream
    //   33: dup
    //   34: aload_2
    //   35: invokespecial 35	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   38: astore_2
    //   39: new 37	java/io/BufferedOutputStream
    //   42: dup
    //   43: aload_2
    //   44: invokespecial 40	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   47: astore_0
    //   48: aload_0
    //   49: aload_1
    //   50: invokevirtual 44	java/io/BufferedOutputStream:write	([B)V
    //   53: aload_0
    //   54: invokevirtual 47	java/io/BufferedOutputStream:flush	()V
    //   57: aload_2
    //   58: ifnull +15 -> 73
    //   61: aload_2
    //   62: invokevirtual 50	java/io/FileOutputStream:close	()V
    //   65: goto +8 -> 73
    //   68: astore_1
    //   69: aload_1
    //   70: invokevirtual 53	java/io/IOException:printStackTrace	()V
    //   73: aload_0
    //   74: ifnull +77 -> 151
    //   77: aload_0
    //   78: invokevirtual 54	java/io/BufferedOutputStream:close	()V
    //   81: return
    //   82: astore_1
    //   83: goto +10 -> 93
    //   86: astore_1
    //   87: goto +12 -> 99
    //   90: astore_1
    //   91: aconst_null
    //   92: astore_0
    //   93: goto +60 -> 153
    //   96: astore_1
    //   97: aconst_null
    //   98: astore_0
    //   99: goto +18 -> 117
    //   102: astore_1
    //   103: aconst_null
    //   104: astore_0
    //   105: aload_3
    //   106: astore_2
    //   107: goto +46 -> 153
    //   110: astore_1
    //   111: aconst_null
    //   112: astore_3
    //   113: aload_0
    //   114: astore_2
    //   115: aload_3
    //   116: astore_0
    //   117: aload_1
    //   118: invokevirtual 55	java/lang/Exception:printStackTrace	()V
    //   121: aload_2
    //   122: ifnull +15 -> 137
    //   125: aload_2
    //   126: invokevirtual 50	java/io/FileOutputStream:close	()V
    //   129: goto +8 -> 137
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual 53	java/io/IOException:printStackTrace	()V
    //   137: aload_0
    //   138: ifnull +13 -> 151
    //   141: aload_0
    //   142: invokevirtual 54	java/io/BufferedOutputStream:close	()V
    //   145: return
    //   146: astore_0
    //   147: aload_0
    //   148: invokevirtual 55	java/lang/Exception:printStackTrace	()V
    //   151: return
    //   152: astore_1
    //   153: aload_2
    //   154: ifnull +15 -> 169
    //   157: aload_2
    //   158: invokevirtual 50	java/io/FileOutputStream:close	()V
    //   161: goto +8 -> 169
    //   164: astore_2
    //   165: aload_2
    //   166: invokevirtual 53	java/io/IOException:printStackTrace	()V
    //   169: aload_0
    //   170: ifnull +15 -> 185
    //   173: aload_0
    //   174: invokevirtual 54	java/io/BufferedOutputStream:close	()V
    //   177: goto +8 -> 185
    //   180: astore_0
    //   181: aload_0
    //   182: invokevirtual 55	java/lang/Exception:printStackTrace	()V
    //   185: aload_1
    //   186: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   61	65	68	java/io/IOException
    //   48	57	82	finally
    //   48	57	86	java/lang/Exception
    //   39	48	90	finally
    //   39	48	96	java/lang/Exception
    //   13	25	102	finally
    //   25	39	102	finally
    //   13	25	110	java/lang/Exception
    //   25	39	110	java/lang/Exception
    //   125	129	132	java/io/IOException
    //   77	81	146	java/lang/Exception
    //   141	145	146	java/lang/Exception
    //   117	121	152	finally
    //   157	161	164	java/io/IOException
    //   173	177	180	java/lang/Exception
  }

  public static int get16BitByValue(int paramInt1, int paramInt2)
  {
    return paramInt2 / (paramInt1 / 256 + 1);
  }

  public static byte[] getBytes(String paramString)
  {
    try
    {
      paramString = new FileInputStream(new File(paramString));
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1000);
      byte[] arrayOfByte = new byte[1000];
      while (true)
      {
        int i = paramString.read(arrayOfByte);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      paramString.close();
      localByteArrayOutputStream.close();
      paramString = localByteArrayOutputStream.toByteArray();
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static int getFireSpeedByteTurbo(int paramInt)
  {
    if (paramInt > 10)
      return 4;
    if (paramInt == 10)
      return 5;
    if (paramInt != 8)
    {
      if (paramInt == 9)
        return 6;
      if (paramInt == 7)
        return 7;
      if (paramInt == 6)
        return 8;
      if (paramInt == 5)
        return 9;
      if (paramInt <= 4)
        return 10;
      return 5;
    }
    return 6;
  }

  public static int getFireTurboByteSpeed(int paramInt)
  {
    if (paramInt == 4)
      return 12;
    if (paramInt == 5)
      return 10;
    if (paramInt == 6)
      return 8;
    if (paramInt == 7)
      return 7;
    if (paramInt == 8)
      return 6;
    if (paramInt == 9)
      return 5;
    if (paramInt == 10)
      return 4;
    return 10;
  }

  public static int getIntegerByBit(byte paramByte1, byte paramByte2)
  {
    return paramByte1 & 0xFF | paramByte2 << 8;
  }

  public static int getValueBy16Bit(int paramInt1, int paramInt2)
  {
    return paramInt2 * (paramInt1 / 256 + 1);
  }

  public static byte[] intToByteArray(int paramInt)
  {
    return new byte[] { (byte)(paramInt >> 8 & 0xFF), (byte)(paramInt & 0xFF) };
  }
}
