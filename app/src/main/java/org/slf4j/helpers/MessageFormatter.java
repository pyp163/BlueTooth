package org.slf4j.helpers;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public final class MessageFormatter
{
  static final char DELIM_START = '{';
  static final char DELIM_STOP = '}';
  static final String DELIM_STR = "{}";
  private static final char ESCAPE_CHAR = '\\';

  public static final FormattingTuple arrayFormat(String paramString, Object[] paramArrayOfObject)
  {
    Throwable localThrowable = getThrowableCandidate(paramArrayOfObject);
    if (paramString == null)
      return new FormattingTuple(null, paramArrayOfObject, localThrowable);
    if (paramArrayOfObject == null)
      return new FormattingTuple(paramString);
    StringBuffer localStringBuffer = new StringBuffer(paramString.length() + 50);
    int j = 0;
    int i = 0;
    while (j < paramArrayOfObject.length)
    {
      int k = paramString.indexOf("{}", i);
      if (k == -1)
      {
        if (i == 0)
          return new FormattingTuple(paramString, paramArrayOfObject, localThrowable);
        localStringBuffer.append(paramString.substring(i, paramString.length()));
        return new FormattingTuple(localStringBuffer.toString(), paramArrayOfObject, localThrowable);
      }
      if (isEscapedDelimeter(paramString, k))
      {
        if (!isDoubleEscaped(paramString, k))
        {
          j -= 1;
          localStringBuffer.append(paramString.substring(i, k - 1));
          localStringBuffer.append('{');
          i = k + 1;
        }
        else
        {
          localStringBuffer.append(paramString.substring(i, k - 1));
          deeplyAppendParameter(localStringBuffer, paramArrayOfObject[j], new HashMap());
          i = k + 2;
        }
      }
      else
      {
        localStringBuffer.append(paramString.substring(i, k));
        deeplyAppendParameter(localStringBuffer, paramArrayOfObject[j], new HashMap());
        i = k + 2;
      }
      j += 1;
    }
    localStringBuffer.append(paramString.substring(i, paramString.length()));
    if (j < paramArrayOfObject.length - 1)
      return new FormattingTuple(localStringBuffer.toString(), paramArrayOfObject, localThrowable);
    return new FormattingTuple(localStringBuffer.toString(), paramArrayOfObject, null);
  }

  private static void booleanArrayAppend(StringBuffer paramStringBuffer, boolean[] paramArrayOfBoolean)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfBoolean.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfBoolean[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  private static void byteArrayAppend(StringBuffer paramStringBuffer, byte[] paramArrayOfByte)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfByte[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  private static void charArrayAppend(StringBuffer paramStringBuffer, char[] paramArrayOfChar)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfChar.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfChar[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  private static void deeplyAppendParameter(StringBuffer paramStringBuffer, Object paramObject, Map paramMap)
  {
    if (paramObject == null)
    {
      paramStringBuffer.append("null");
      return;
    }
    if (!paramObject.getClass().isArray())
    {
      safeObjectAppend(paramStringBuffer, paramObject);
      return;
    }
    if ((paramObject instanceof boolean[]))
    {
      booleanArrayAppend(paramStringBuffer, (boolean[])paramObject);
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      byteArrayAppend(paramStringBuffer, (byte[])paramObject);
      return;
    }
    if ((paramObject instanceof char[]))
    {
      charArrayAppend(paramStringBuffer, (char[])paramObject);
      return;
    }
    if ((paramObject instanceof short[]))
    {
      shortArrayAppend(paramStringBuffer, (short[])paramObject);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      intArrayAppend(paramStringBuffer, (int[])paramObject);
      return;
    }
    if ((paramObject instanceof long[]))
    {
      longArrayAppend(paramStringBuffer, (long[])paramObject);
      return;
    }
    if ((paramObject instanceof float[]))
    {
      floatArrayAppend(paramStringBuffer, (float[])paramObject);
      return;
    }
    if ((paramObject instanceof double[]))
    {
      doubleArrayAppend(paramStringBuffer, (double[])paramObject);
      return;
    }
    objectArrayAppend(paramStringBuffer, (Object[])paramObject, paramMap);
  }

  private static void doubleArrayAppend(StringBuffer paramStringBuffer, double[] paramArrayOfDouble)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfDouble.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfDouble[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  private static void floatArrayAppend(StringBuffer paramStringBuffer, float[] paramArrayOfFloat)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfFloat.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfFloat[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  public static final FormattingTuple format(String paramString, Object paramObject)
  {
    return arrayFormat(paramString, new Object[] { paramObject });
  }

  public static final FormattingTuple format(String paramString, Object paramObject1, Object paramObject2)
  {
    return arrayFormat(paramString, new Object[] { paramObject1, paramObject2 });
  }

  static final Throwable getThrowableCandidate(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      if (paramArrayOfObject.length == 0)
        return null;
      paramArrayOfObject = paramArrayOfObject[(paramArrayOfObject.length - 1)];
      if ((paramArrayOfObject instanceof Throwable))
        return (Throwable)paramArrayOfObject;
      return null;
    }
    return null;
  }

  private static void intArrayAppend(StringBuffer paramStringBuffer, int[] paramArrayOfInt)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfInt[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  static final boolean isDoubleEscaped(String paramString, int paramInt)
  {
    return (paramInt >= 2) && (paramString.charAt(paramInt - 2) == '\\');
  }

  static final boolean isEscapedDelimeter(String paramString, int paramInt)
  {
    if (paramInt == 0)
      return false;
    return paramString.charAt(paramInt - 1) == '\\';
  }

  private static void longArrayAppend(StringBuffer paramStringBuffer, long[] paramArrayOfLong)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfLong.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfLong[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }

  private static void objectArrayAppend(StringBuffer paramStringBuffer, Object[] paramArrayOfObject, Map paramMap)
  {
    paramStringBuffer.append('[');
    if (!paramMap.containsKey(paramArrayOfObject))
    {
      paramMap.put(paramArrayOfObject, null);
      int j = paramArrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        deeplyAppendParameter(paramStringBuffer, paramArrayOfObject[i], paramMap);
        if (i != j - 1)
          paramStringBuffer.append(", ");
        i += 1;
      }
      paramMap.remove(paramArrayOfObject);
    }
    else
    {
      paramStringBuffer.append("...");
    }
    paramStringBuffer.append(']');
  }

  private static void safeObjectAppend(StringBuffer paramStringBuffer, Object paramObject)
  {
    try
    {
      paramStringBuffer.append(paramObject.toString());
      return;
    }
    catch (Throwable localThrowable)
    {
      PrintStream localPrintStream = System.err;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SLF4J: Failed toString() invocation on an object of type [");
      localStringBuilder.append(paramObject.getClass().getName());
      localStringBuilder.append("]");
      localPrintStream.println(localStringBuilder.toString());
      localThrowable.printStackTrace();
      paramStringBuffer.append("[FAILED toString()]");
    }
  }

  private static void shortArrayAppend(StringBuffer paramStringBuffer, short[] paramArrayOfShort)
  {
    paramStringBuffer.append('[');
    int j = paramArrayOfShort.length;
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(paramArrayOfShort[i]);
      if (i != j - 1)
        paramStringBuffer.append(", ");
      i += 1;
    }
    paramStringBuffer.append(']');
  }
}