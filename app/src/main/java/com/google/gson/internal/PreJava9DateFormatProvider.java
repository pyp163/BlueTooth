package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PreJava9DateFormatProvider
{
  private static String getDateFormatPattern(int paramInt)
  {
    switch (paramInt)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown DateFormat style: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 3:
      return "M/d/yy";
    case 2:
      return "MMM d, y";
    case 1:
      return "MMMM d, y";
    case 0:
    }
    return "EEEE, MMMM d, y";
  }

  private static String getDatePartOfDateTimePattern(int paramInt)
  {
    switch (paramInt)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown DateFormat style: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 3:
      return "M/d/yy";
    case 2:
      return "MMM d, yyyy";
    case 1:
      return "MMMM d, yyyy";
    case 0:
    }
    return "EEEE, MMMM d, yyyy";
  }

  private static String getTimePartOfDateTimePattern(int paramInt)
  {
    switch (paramInt)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown DateFormat style: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 3:
      return "h:mm a";
    case 2:
      return "h:mm:ss a";
    case 0:
    case 1:
    }
    return "h:mm:ss a z";
  }

  public static DateFormat getUSDateFormat(int paramInt)
  {
    return new SimpleDateFormat(getDateFormatPattern(paramInt), Locale.US);
  }

  public static DateFormat getUSDateTimeFormat(int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getDatePartOfDateTimePattern(paramInt1));
    localStringBuilder.append(" ");
    localStringBuilder.append(getTimePartOfDateTimePattern(paramInt2));
    return new SimpleDateFormat(localStringBuilder.toString(), Locale.US);
  }
}