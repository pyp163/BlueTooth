package org.apache.commons.net.ntp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeStamp
  implements Serializable, Comparable<TimeStamp>
{
  public static final String NTP_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS";
  protected static final long msb0baseTime = 2085978496000L;
  protected static final long msb1baseTime = -2208988800000L;
  private static final long serialVersionUID = 8139806907588338737L;
  private final long ntpTime;
  private DateFormat simpleFormatter;
  private DateFormat utcFormatter;

  public TimeStamp(long paramLong)
  {
    this.ntpTime = paramLong;
  }

  public TimeStamp(String paramString)
    throws NumberFormatException
  {
    this.ntpTime = decodeNtpHexString(paramString);
  }

  public TimeStamp(Date paramDate)
  {
    long l;
    if (paramDate == null)
      l = 0L;
    else
      l = toNtpTime(paramDate.getTime());
    this.ntpTime = l;
  }

  private static void appendHexString(StringBuilder paramStringBuilder, long paramLong)
  {
    String str = Long.toHexString(paramLong);
    int i = str.length();
    while (i < 8)
    {
      paramStringBuilder.append('0');
      i += 1;
    }
    paramStringBuilder.append(str);
  }

  protected static long decodeNtpHexString(String paramString)
    throws NumberFormatException
  {
    if (paramString == null)
      throw new NumberFormatException("null");
    int i = paramString.indexOf('.');
    if (i == -1)
    {
      if (paramString.length() == 0)
        return 0L;
      return Long.parseLong(paramString, 16) << 32;
    }
    return Long.parseLong(paramString.substring(0, i), 16) << 32 | Long.parseLong(paramString.substring(i + 1), 16);
  }

  public static TimeStamp getCurrentTime()
  {
    return getNtpTime(System.currentTimeMillis());
  }

  public static TimeStamp getNtpTime(long paramLong)
  {
    return new TimeStamp(toNtpTime(paramLong));
  }

  public static long getTime(long paramLong)
  {
    long l = paramLong >>> 32 & 0xFFFFFFFF;
    paramLong = Math.round((paramLong & 0xFFFFFFFF) * 1000.0D / 4294967296.0D);
    if ((0x80000000 & l) == 0L)
      return l * 1000L + 2085978496000L + paramLong;
    return l * 1000L - 2208988800000L + paramLong;
  }

  public static TimeStamp parseNtpString(String paramString)
    throws NumberFormatException
  {
    return new TimeStamp(decodeNtpHexString(paramString));
  }

  protected static long toNtpTime(long paramLong)
  {
    int i;
    if (paramLong < 2085978496000L)
      i = 1;
    else
      i = 0;
    if (i != 0)
      paramLong += 2208988800000L;
    else
      paramLong -= 2085978496000L;
    long l1 = paramLong / 1000L;
    long l2 = paramLong % 1000L * 4294967296L / 1000L;
    paramLong = l1;
    if (i != 0)
      paramLong = l1 | 0x80000000;
    return l2 | paramLong << 32;
  }

  public static String toString(long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    appendHexString(localStringBuilder, paramLong >>> 32 & 0xFFFFFFFF);
    localStringBuilder.append('.');
    appendHexString(localStringBuilder, paramLong & 0xFFFFFFFF);
    return localStringBuilder.toString();
  }

  public int compareTo(TimeStamp paramTimeStamp)
  {
    boolean bool = this.ntpTime < paramTimeStamp.ntpTime;
    if (bool)
      return -1;
    if (!bool)
      return 0;
    return 1;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = paramObject instanceof TimeStamp;
    boolean bool1 = false;
    if (bool2)
    {
      if (this.ntpTime == ((TimeStamp)paramObject).ntpValue())
        bool1 = true;
      return bool1;
    }
    return false;
  }

  public Date getDate()
  {
    return new Date(getTime(this.ntpTime));
  }

  public long getFraction()
  {
    return this.ntpTime & 0xFFFFFFFF;
  }

  public long getSeconds()
  {
    return this.ntpTime >>> 32 & 0xFFFFFFFF;
  }

  public long getTime()
  {
    return getTime(this.ntpTime);
  }

  public int hashCode()
  {
    return (int)(this.ntpTime ^ this.ntpTime >>> 32);
  }

  public long ntpValue()
  {
    return this.ntpTime;
  }

  public String toDateString()
  {
    if (this.simpleFormatter == null)
    {
      this.simpleFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
      this.simpleFormatter.setTimeZone(TimeZone.getDefault());
    }
    Date localDate = getDate();
    return this.simpleFormatter.format(localDate);
  }

  public String toString()
  {
    return toString(this.ntpTime);
  }

  public String toUTCString()
  {
    if (this.utcFormatter == null)
    {
      this.utcFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
      this.utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    Date localDate = getDate();
    return this.utcFormatter.format(localDate);
  }
}