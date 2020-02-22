package com.facebook.common.util;

public class Hex
{
  private static final byte[] DIGITS;
  private static final char[] FIRST_CHAR;
  private static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static final char[] SECOND_CHAR;

  static
  {
    FIRST_CHAR = new char[256];
    SECOND_CHAR = new char[256];
    int k = 0;
    int j = 0;
    while (j < 256)
    {
      FIRST_CHAR[j] = HEX_DIGITS[(j >> 4 & 0xF)];
      SECOND_CHAR[j] = HEX_DIGITS[(j & 0xF)];
      j += 1;
    }
    DIGITS = new byte[103];
    j = 0;
    while (j <= 70)
    {
      DIGITS[j] = -1;
      j += 1;
    }
    for (int i = 0; ; i = (byte)(i + 1))
    {
      j = k;
      if (i >= 10)
        break;
      DIGITS[(i + 48)] = i;
    }
    while (j < 6)
    {
      byte[] arrayOfByte = DIGITS;
      i = (byte)(j + 10);
      arrayOfByte[(j + 65)] = i;
      DIGITS[(j + 97)] = i;
      j = (byte)(j + 1);
    }
  }

  public static String byte2Hex(int paramInt)
  {
    if ((paramInt <= 255) && (paramInt >= 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(String.valueOf(FIRST_CHAR[paramInt]));
      localStringBuilder.append(String.valueOf(SECOND_CHAR[paramInt]));
      return localStringBuilder.toString();
    }
    throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
  }

  public static byte[] decodeHex(String paramString)
  {
    int m = paramString.length();
    if ((m & 0x1) != 0)
      throw new IllegalArgumentException("Odd number of characters.");
    Object localObject = new byte[m >> 1];
    int j = 0;
    int i = 0;
    while (true)
    {
      int k = 1;
      if (j >= m)
        break;
      int n = j + 1;
      j = paramString.charAt(j);
      if (j > 102)
      {
        i = k;
        break label145;
      }
      j = DIGITS[j];
      if (j == -1)
      {
        i = k;
        break label145;
      }
      int i1 = paramString.charAt(n);
      if (i1 > 102)
      {
        i = k;
        break label145;
      }
      i1 = DIGITS[i1];
      if (i1 == -1)
      {
        i = k;
        break label145;
      }
      localObject[i] = ((byte)(j << 4 | i1));
      i += 1;
      j = n + 1;
    }
    i = 0;
    label145: if (i != 0)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid hexadecimal digit: ");
      ((StringBuilder)localObject).append(paramString);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    return localObject;
  }

  public static String encodeHex(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    int j = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      if ((k == 0) && (paramBoolean))
        break;
      int m = j + 1;
      arrayOfChar[j] = FIRST_CHAR[k];
      j = m + 1;
      arrayOfChar[m] = SECOND_CHAR[k];
      i += 1;
    }
    return new String(arrayOfChar, 0, j);
  }

  public static byte[] hexStringToByteArray(String paramString)
  {
    return decodeHex(paramString.replaceAll(" ", ""));
  }
}