package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.Calendar;

public abstract interface FTPTimestampParser
{
  public static final String DEFAULT_RECENT_SDF = "MMM d HH:mm";
  public static final String DEFAULT_SDF = "MMM d yyyy";

  public abstract Calendar parseTimestamp(String paramString)
    throws ParseException;
}