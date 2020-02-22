package org.apache.http.impl.cookie;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.http.annotation.Immutable;

@Immutable
public final class DateUtils
{
  private static final String[] DEFAULT_PATTERNS = { "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private static final Date DEFAULT_TWO_DIGIT_YEAR_START = localCalendar.getTime();
  public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  static
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(GMT);
    localCalendar.set(2000, 0, 1, 0, 0, 0);
    localCalendar.set(14, 0);
  }

  public static String formatDate(Date paramDate)
  {
    return formatDate(paramDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
  }

  public static String formatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("date is null");
    if (paramString == null)
      throw new IllegalArgumentException("pattern is null");
    return DateFormatHolder.formatFor(paramString).format(paramDate);
  }

  public static Date parseDate(String paramString)
    throws DateParseException
  {
    return parseDate(paramString, null, null);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString)
    throws DateParseException
  {
    return parseDate(paramString, paramArrayOfString, null);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString, Date paramDate)
    throws DateParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("dateValue is null");
    String[] arrayOfString = paramArrayOfString;
    if (paramArrayOfString == null)
      arrayOfString = DEFAULT_PATTERNS;
    paramArrayOfString = paramDate;
    if (paramDate == null)
      paramArrayOfString = DEFAULT_TWO_DIGIT_YEAR_START;
    paramDate = paramString;
    if (paramString.length() > 1)
    {
      paramDate = paramString;
      if (paramString.startsWith("'"))
      {
        paramDate = paramString;
        if (paramString.endsWith("'"))
          paramDate = paramString.substring(1, paramString.length() - 1);
      }
    }
    int j = arrayOfString.length;
    int i = 0;
    while (true)
    {
      if (i < j)
      {
        paramString = DateFormatHolder.formatFor(arrayOfString[i]);
        paramString.set2DigitYearStart(paramArrayOfString);
      }
      try
      {
        paramString = paramString.parse(paramDate);
        return paramString;
        label114: i += 1;
        continue;
        paramString = new StringBuilder();
        paramString.append("Unable to parse the date ");
        paramString.append(paramDate);
        throw new DateParseException(paramString.toString());
      }
      catch (ParseException paramString)
      {
        break label114;
      }
    }
  }

  static final class DateFormatHolder
  {
    private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal()
    {
      protected SoftReference<Map<String, SimpleDateFormat>> initialValue()
      {
        return new SoftReference(new HashMap());
      }
    };

    public static SimpleDateFormat formatFor(String paramString)
    {
      Object localObject2 = (Map)((SoftReference)THREADLOCAL_FORMATS.get()).get();
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new HashMap();
        THREADLOCAL_FORMATS.set(new SoftReference(localObject1));
      }
      SimpleDateFormat localSimpleDateFormat = (SimpleDateFormat)((Map)localObject1).get(paramString);
      localObject2 = localSimpleDateFormat;
      if (localSimpleDateFormat == null)
      {
        localObject2 = new SimpleDateFormat(paramString, Locale.US);
        ((SimpleDateFormat)localObject2).setTimeZone(TimeZone.getTimeZone("GMT"));
        ((Map)localObject1).put(paramString, localObject2);
      }
      return localObject2;
    }
  }
}