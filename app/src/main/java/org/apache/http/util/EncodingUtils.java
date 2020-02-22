package org.apache.http.util;

import java.io.UnsupportedEncodingException;

public final class EncodingUtils
{
  public static byte[] getAsciiBytes(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      paramString = paramString.getBytes("US-ASCII");
      return paramString;
      label23: throw new Error("HttpClient requires ASCII support");
    }
    catch (UnsupportedEncodingException paramString)
    {
      break label23;
    }
  }

  public static String getAsciiString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    return getAsciiString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static String getAsciiString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramInt1, paramInt2, "US-ASCII");
      return paramArrayOfByte;
      label29: throw new Error("HttpClient requires ASCII support");
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      break label29;
    }
  }

  public static byte[] getBytes(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("data may not be null");
    if ((paramString2 != null) && (paramString2.length() != 0));
    try
    {
      paramString2 = paramString1.getBytes(paramString2);
      return paramString2;
      label36: return paramString1.getBytes();
      throw new IllegalArgumentException("charset may not be null or empty");
    }
    catch (UnsupportedEncodingException paramString2)
    {
      break label36;
    }
  }

  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    if ((paramString != null) && (paramString.length() != 0));
    try
    {
      paramString = new String(paramArrayOfByte, paramInt1, paramInt2, paramString);
      return paramString;
      label42: return new String(paramArrayOfByte, paramInt1, paramInt2);
      throw new IllegalArgumentException("charset may not be null or empty");
    }
    catch (UnsupportedEncodingException paramString)
    {
      break label42;
    }
  }

  public static String getString(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }
}