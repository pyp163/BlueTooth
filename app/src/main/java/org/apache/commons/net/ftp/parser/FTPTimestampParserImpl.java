package org.apache.commons.net.ftp.parser;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;

public class FTPTimestampParserImpl
  implements FTPTimestampParser, Configurable
{
  private SimpleDateFormat defaultDateFormat;
  private boolean lenientFutureDates = false;
  private SimpleDateFormat recentDateFormat;

  public FTPTimestampParserImpl()
  {
    setDefaultDateFormat("MMM d yyyy");
    setRecentDateFormat("MMM d HH:mm");
  }

  private void setDefaultDateFormat(String paramString)
  {
    if (paramString != null)
    {
      this.defaultDateFormat = new SimpleDateFormat(paramString);
      this.defaultDateFormat.setLenient(false);
    }
  }

  private void setRecentDateFormat(String paramString)
  {
    if (paramString != null)
    {
      this.recentDateFormat = new SimpleDateFormat(paramString);
      this.recentDateFormat.setLenient(false);
    }
  }

  private void setServerTimeZone(String paramString)
  {
    TimeZone localTimeZone = TimeZone.getDefault();
    if (paramString != null)
      localTimeZone = TimeZone.getTimeZone(paramString);
    this.defaultDateFormat.setTimeZone(localTimeZone);
    if (this.recentDateFormat != null)
      this.recentDateFormat.setTimeZone(localTimeZone);
  }

  public void configure(FTPClientConfig paramFTPClientConfig)
  {
    Object localObject = paramFTPClientConfig.getServerLanguageCode();
    String str = paramFTPClientConfig.getShortMonthNames();
    if (str != null)
      localObject = FTPClientConfig.getDateFormatSymbols(str);
    else if (localObject != null)
      localObject = FTPClientConfig.lookupDateFormatSymbols((String)localObject);
    else
      localObject = FTPClientConfig.lookupDateFormatSymbols("en");
    str = paramFTPClientConfig.getRecentDateFormatStr();
    if (str == null)
    {
      this.recentDateFormat = null;
    }
    else
    {
      this.recentDateFormat = new SimpleDateFormat(str, (DateFormatSymbols)localObject);
      this.recentDateFormat.setLenient(false);
    }
    str = paramFTPClientConfig.getDefaultDateFormatStr();
    if (str == null)
      throw new IllegalArgumentException("defaultFormatString cannot be null");
    this.defaultDateFormat = new SimpleDateFormat(str, (DateFormatSymbols)localObject);
    this.defaultDateFormat.setLenient(false);
    setServerTimeZone(paramFTPClientConfig.getServerTimeZoneId());
    this.lenientFutureDates = paramFTPClientConfig.isLenientFutureDates();
  }

  public SimpleDateFormat getDefaultDateFormat()
  {
    return this.defaultDateFormat;
  }

  public String getDefaultDateFormatString()
  {
    return this.defaultDateFormat.toPattern();
  }

  public SimpleDateFormat getRecentDateFormat()
  {
    return this.recentDateFormat;
  }

  public String getRecentDateFormatString()
  {
    return this.recentDateFormat.toPattern();
  }

  public TimeZone getServerTimeZone()
  {
    return this.defaultDateFormat.getTimeZone();
  }

  public String[] getShortMonths()
  {
    return this.defaultDateFormat.getDateFormatSymbols().getShortMonths();
  }

  boolean isLenientFutureDates()
  {
    return this.lenientFutureDates;
  }

  public Calendar parseTimestamp(String paramString)
    throws ParseException
  {
    return parseTimestamp(paramString, Calendar.getInstance());
  }

  public Calendar parseTimestamp(String paramString, Calendar paramCalendar)
    throws ParseException
  {
    Object localObject2 = (Calendar)paramCalendar.clone();
    ((Calendar)localObject2).setTimeZone(getServerTimeZone());
    Calendar localCalendar = (Calendar)((Calendar)localObject2).clone();
    localCalendar.setTimeZone(getServerTimeZone());
    Object localObject1 = new ParsePosition(0);
    if (this.recentDateFormat != null)
    {
      if (this.lenientFutureDates)
        ((Calendar)localObject2).add(5, 1);
      paramCalendar = this.recentDateFormat.parse(paramString, (ParsePosition)localObject1);
    }
    else
    {
      paramCalendar = null;
    }
    if ((paramCalendar != null) && (((ParsePosition)localObject1).getIndex() == paramString.length()))
    {
      localCalendar.setTime(paramCalendar);
      localCalendar.set(1, ((Calendar)localObject2).get(1));
      if (localCalendar.after(localObject2))
      {
        localCalendar.add(1, -1);
        return localCalendar;
      }
    }
    else
    {
      if (this.recentDateFormat != null)
      {
        localObject1 = new ParsePosition(0);
        int i = ((Calendar)localObject2).get(1);
        paramCalendar = new StringBuilder();
        paramCalendar.append(paramString);
        paramCalendar.append(" ");
        paramCalendar.append(i);
        paramCalendar = paramCalendar.toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(this.recentDateFormat.toPattern());
        ((StringBuilder)localObject2).append(" yyyy");
        localObject2 = new SimpleDateFormat(((StringBuilder)localObject2).toString(), this.recentDateFormat.getDateFormatSymbols());
        ((SimpleDateFormat)localObject2).setLenient(false);
        ((SimpleDateFormat)localObject2).setTimeZone(this.recentDateFormat.getTimeZone());
        paramCalendar = ((SimpleDateFormat)localObject2).parse(paramCalendar, (ParsePosition)localObject1);
      }
      if ((paramCalendar != null) && (((ParsePosition)localObject1).getIndex() == paramString.length() + 5))
      {
        localCalendar.setTime(paramCalendar);
        return localCalendar;
      }
      paramCalendar = new ParsePosition(0);
      localObject1 = this.defaultDateFormat.parse(paramString, paramCalendar);
      if ((localObject1 == null) || (paramCalendar.getIndex() != paramString.length()))
        break label345;
      localCalendar.setTime((Date)localObject1);
    }
    return localCalendar;
    label345: throw new ParseException("Timestamp could not be parsed with older or recent DateFormat", paramCalendar.getErrorIndex());
  }

  void setLenientFutureDates(boolean paramBoolean)
  {
    this.lenientFutureDates = paramBoolean;
  }
}