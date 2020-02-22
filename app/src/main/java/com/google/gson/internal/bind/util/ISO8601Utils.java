package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
  private static final String UTC_ID = "UTC";

  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar);
  }

  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_UTC);
  }

  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_UTC);
  }

  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int j = "yyyy-MM-ddThh:mm:ss".length();
    if (paramBoolean)
      i = ".sss".length();
    else
      i = 0;
    if (paramTimeZone.getRawOffset() == 0);
    int k;
    for (paramDate = "Z"; ; paramDate = "+hh:mm")
    {
      k = paramDate.length();
      break;
    }
    paramDate = new StringBuilder(j + i + k);
    padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
    char c = '-';
    paramDate.append('-');
    padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
    paramDate.append('-');
    padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
    paramDate.append('T');
    padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
    paramDate.append(':');
    padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
    paramDate.append(':');
    padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
    if (paramBoolean)
    {
      paramDate.append('.');
      padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
    }
    int i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
    if (i != 0)
    {
      k = i / 60000;
      j = Math.abs(k / 60);
      k = Math.abs(k % 60);
      if (i >= 0)
        c = '+';
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    else
    {
      paramDate.append('Z');
    }
    return paramDate.toString();
  }

  private static int indexOfNonDigit(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if (i >= 48)
      {
        if (i > 57)
          return paramInt;
        paramInt += 1;
      }
      else
      {
        return paramInt;
      }
    }
    return paramString.length();
  }

  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }

  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    label1044: label1059: 
    while (true)
    {
      char c;
      Object localObject2;
      try
      {
        i = paramParsePosition.getIndex();
        j = i + 4;
        int i3 = parseInt(paramString, i, j);
        i = j;
        if (checkOffset(paramString, j, '-'))
          i = j + 1;
        j = i + 2;
        int i4 = parseInt(paramString, i, j);
        i = j;
        if (checkOffset(paramString, j, '-'))
          i = j + 1;
        j = i + 2;
        int i5 = parseInt(paramString, i, j);
        boolean bool = checkOffset(paramString, j, 'T');
        Object localObject1;
        if ((!bool) && (paramString.length() <= j))
        {
          localObject1 = new GregorianCalendar(i3, i4 - 1, i5);
          paramParsePosition.setIndex(j);
          return ((Calendar)localObject1).getTime();
        }
        if (!bool)
          break label1012;
        i = j + 1;
        j = i + 2;
        k = parseInt(paramString, i, j);
        i = j;
        if (checkOffset(paramString, j, ':'))
          i = j + 1;
        j = i + 2;
        m = parseInt(paramString, i, j);
        i = j;
        if (checkOffset(paramString, j, ':'))
          i = j + 1;
        if (paramString.length() <= i)
          break label1003;
        j = paramString.charAt(i);
        if ((j == 90) || (j == 43) || (j == 45))
          break label1003;
        i1 = i + 2;
        i = parseInt(paramString, i, i1);
        j = i;
        if (i > 59)
        {
          j = i;
          if (i < 63)
            j = 59;
        }
        if (!checkOffset(paramString, i1, '.'))
          break label990;
        n = i1 + 1;
        i1 = indexOfNonDigit(paramString, n + 1);
        i2 = Math.min(i1, n + 3);
        i = parseInt(paramString, n, i2);
        switch (i2 - n)
        {
        default:
          if (paramString.length() <= k)
            throw new IllegalArgumentException("No time zone indicator");
          c = paramString.charAt(k);
          if (c != 'Z')
            break label1044;
          localObject1 = TIMEZONE_UTC;
          k += 1;
          continue;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Invalid time zone indicator '");
          ((StringBuilder)localObject1).append(c);
          ((StringBuilder)localObject1).append("'");
          throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
          localObject1 = paramString.substring(k);
          if (((String)localObject1).length() < 5)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append("00");
            localObject1 = ((StringBuilder)localObject2).toString();
          }
          k += ((String)localObject1).length();
          if ((!"+0000".equals(localObject1)) && (!"+00:00".equals(localObject1)))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("GMT");
            ((StringBuilder)localObject2).append((String)localObject1);
            localObject2 = ((StringBuilder)localObject2).toString();
            localObject1 = TimeZone.getTimeZone((String)localObject2);
            localObject3 = ((TimeZone)localObject1).getID();
            if ((((String)localObject3).equals(localObject2)) || (((String)localObject3).replace(":", "").equals(localObject2)))
              break label1059;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("Mismatching time zone indicator: ");
            ((StringBuilder)localObject3).append((String)localObject2);
            ((StringBuilder)localObject3).append(" given, resolves to ");
            ((StringBuilder)localObject3).append(((TimeZone)localObject1).getID());
            throw new IndexOutOfBoundsException(((StringBuilder)localObject3).toString());
          }
          localObject1 = TIMEZONE_UTC;
          localObject1 = new GregorianCalendar((TimeZone)localObject1);
          ((Calendar)localObject1).setLenient(false);
          ((Calendar)localObject1).set(1, i3);
          ((Calendar)localObject1).set(2, i4 - 1);
          ((Calendar)localObject1).set(5, i5);
          ((Calendar)localObject1).set(11, n);
          ((Calendar)localObject1).set(12, m);
          ((Calendar)localObject1).set(13, j);
          ((Calendar)localObject1).set(14, i);
          paramParsePosition.setIndex(k);
          localObject1 = ((Calendar)localObject1).getTime();
          return localObject1;
        case 2:
        case 1:
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
      }
      if (paramString == null)
      {
        paramString = null;
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append('"');
        ((StringBuilder)localObject2).append(paramString);
        ((StringBuilder)localObject2).append('"');
        paramString = ((StringBuilder)localObject2).toString();
      }
      Object localObject3 = localIndexOutOfBoundsException.getMessage();
      if (localObject3 != null)
      {
        localObject2 = localObject3;
        if (!((String)localObject3).isEmpty());
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("(");
        ((StringBuilder)localObject2).append(localIndexOutOfBoundsException.getClass().getName());
        ((StringBuilder)localObject2).append(")");
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Failed to parse date [");
      ((StringBuilder)localObject3).append(paramString);
      ((StringBuilder)localObject3).append("]: ");
      ((StringBuilder)localObject3).append((String)localObject2);
      paramString = new ParseException(((StringBuilder)localObject3).toString(), paramParsePosition.getIndex());
      paramString.initCause(localIndexOutOfBoundsException);
      throw paramString;
      while (true)
      {
        break;
        i *= 10;
        continue;
        i *= 100;
      }
      int n = k;
      int k = i1;
      continue;
      label990: int i = 0;
      n = k;
      k = i1;
      continue;
      label1003: int j = i;
      i = m;
      break label1017;
      label1012: k = 0;
      i = 0;
      label1017: int i2 = 0;
      int i1 = 0;
      n = k;
      int m = i;
      i = i2;
      k = j;
      j = i1;
      continue;
      if (c != '+')
        if (c == '-');
    }
  }

  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 >= 0) && (paramInt2 <= paramString.length()) && (paramInt1 <= paramInt2))
    {
      int i;
      int j;
      StringBuilder localStringBuilder;
      if (paramInt1 < paramInt2)
      {
        i = paramInt1 + 1;
        j = Character.digit(paramString.charAt(paramInt1), 10);
        if (j < 0)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid number: ");
          localStringBuilder.append(paramString.substring(paramInt1, paramInt2));
          throw new NumberFormatException(localStringBuilder.toString());
        }
        j = -j;
      }
      else
      {
        i = paramInt1;
        j = 0;
      }
      while (i < paramInt2)
      {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k < 0)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid number: ");
          localStringBuilder.append(paramString.substring(paramInt1, paramInt2));
          throw new NumberFormatException(localStringBuilder.toString());
        }
        j = j * 10 - k;
        i += 1;
      }
      return -j;
    }
    throw new NumberFormatException(paramString);
  }
}