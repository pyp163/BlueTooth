package com.qx.qgbox.utils;

import java.util.Formatter;

public class Conversion
{
  public static String BytetohexString(byte[] paramArrayOfByte, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 3);
    Formatter localFormatter = new Formatter(localStringBuilder);
    int i = 0;
    while (i < paramInt)
    {
      if (i < paramInt - 1)
        localFormatter.format("%02X:", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      else
        localFormatter.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      i += 1;
    }
    localFormatter.close();
    return localStringBuilder.toString();
  }

  static String BytetohexString(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 3);
    Formatter localFormatter = new Formatter(localStringBuilder);
    if (!paramBoolean)
    {
      i = 0;
      while (i < paramArrayOfByte.length)
      {
        if (i < paramArrayOfByte.length - 1)
          localFormatter.format("%02X:", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
        else
          localFormatter.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
        i += 1;
      }
    }
    int i = paramArrayOfByte.length - 1;
    while (i >= 0)
    {
      if (i > 0)
        localFormatter.format("%02X:", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      else
        localFormatter.format("%02X", new Object[] { Byte.valueOf(paramArrayOfByte[i]) });
      i -= 1;
    }
    localFormatter.close();
    return localStringBuilder.toString();
  }

  public static long buildUint16(byte paramByte1, byte paramByte2)
  {
    if ((paramByte1 & 0x80) == 0)
    {
      l = paramByte1;
      return paramByte2 & 0xFF | l << 8;
    }
    long l = paramByte1;
    return paramByte2 & 0xFF | (l & 0x7F) << 8 | 0x8000;
  }

  public static int hexStringtoByte(String paramString, byte[] paramArrayOfByte)
  {
    int i = 0;
    int k = 0;
    if (paramString != null)
    {
      i = 0;
      int m;
      for (int j = 0; k < paramString.length(); j = m)
      {
        int n;
        if (((paramString.charAt(k) < '0') || (paramString.charAt(k) > '9')) && ((paramString.charAt(k) < 'a') || (paramString.charAt(k) > 'f')))
        {
          n = i;
          m = j;
          if (paramString.charAt(k) >= 'A')
          {
            n = i;
            m = j;
            if (paramString.charAt(k) > 'F');
          }
        }
        else
        {
          if (j != 0)
          {
            paramArrayOfByte[i] = ((byte)(paramArrayOfByte[i] + (byte)Character.digit(paramString.charAt(k), 16)));
            i += 1;
          }
          else
          {
            paramArrayOfByte[i] = ((byte)(Character.digit(paramString.charAt(k), 16) << 4));
          }
          m = j ^ 0x1;
          n = i;
        }
        k += 1;
        i = n;
      }
    }
    return i;
  }

  public static byte hiUint16(long paramLong)
  {
    return (byte)(int)(paramLong >> 8);
  }

  private static boolean isAsciiPrintable(char paramChar)
  {
    return (paramChar >= ' ') && (paramChar < '');
  }

  public static boolean isAsciiPrintable(String paramString)
  {
    if (paramString == null)
      return false;
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!isAsciiPrintable(paramString.charAt(i)))
        return false;
      i += 1;
    }
    return true;
  }

  public static byte loUint16(long paramLong)
  {
    return (byte)(int)(paramLong & 0xFF);
  }
}
