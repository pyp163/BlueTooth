package org.apache.commons.net.ftp;

import java.text.DateFormatSymbols;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FTPClientConfig
{
  private static final Map<String, Object> LANGUAGE_CODE_MAP = new TreeMap();
  public static final String SYST_AS400 = "AS/400";
  public static final String SYST_L8 = "TYPE: L8";
  public static final String SYST_MVS = "MVS";
  public static final String SYST_NETWARE = "NETWARE";
  public static final String SYST_NT = "WINDOWS";
  public static final String SYST_OS2 = "OS/2";
  public static final String SYST_OS400 = "OS/400";
  public static final String SYST_UNIX = "UNIX";
  public static final String SYST_VMS = "VMS";
  private String defaultDateFormatStr = null;
  private boolean lenientFutureDates = true;
  private String recentDateFormatStr = null;
  private String serverLanguageCode = null;
  private final String serverSystemKey;
  private String serverTimeZoneId = null;
  private String shortMonthNames = null;

  static
  {
    LANGUAGE_CODE_MAP.put("en", Locale.ENGLISH);
    LANGUAGE_CODE_MAP.put("de", Locale.GERMAN);
    LANGUAGE_CODE_MAP.put("it", Locale.ITALIAN);
    LANGUAGE_CODE_MAP.put("es", new Locale("es", "", ""));
    LANGUAGE_CODE_MAP.put("pt", new Locale("pt", "", ""));
    LANGUAGE_CODE_MAP.put("da", new Locale("da", "", ""));
    LANGUAGE_CODE_MAP.put("sv", new Locale("sv", "", ""));
    LANGUAGE_CODE_MAP.put("no", new Locale("no", "", ""));
    LANGUAGE_CODE_MAP.put("nl", new Locale("nl", "", ""));
    LANGUAGE_CODE_MAP.put("ro", new Locale("ro", "", ""));
    LANGUAGE_CODE_MAP.put("sq", new Locale("sq", "", ""));
    LANGUAGE_CODE_MAP.put("sh", new Locale("sh", "", ""));
    LANGUAGE_CODE_MAP.put("sk", new Locale("sk", "", ""));
    LANGUAGE_CODE_MAP.put("sl", new Locale("sl", "", ""));
    LANGUAGE_CODE_MAP.put("fr", "jan|fév|mar|avr|mai|jun|jui|aoû|sep|oct|nov|déc");
  }

  public FTPClientConfig()
  {
    this("UNIX");
  }

  public FTPClientConfig(String paramString)
  {
    this.serverSystemKey = paramString;
  }

  public FTPClientConfig(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this(paramString1);
    this.defaultDateFormatStr = paramString2;
    this.recentDateFormatStr = paramString3;
    this.serverLanguageCode = paramString4;
    this.shortMonthNames = paramString5;
    this.serverTimeZoneId = paramString6;
  }

  public static DateFormatSymbols getDateFormatSymbols(String paramString)
  {
    paramString = splitShortMonthString(paramString);
    DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols(Locale.US);
    localDateFormatSymbols.setShortMonths(paramString);
    return localDateFormatSymbols;
  }

  public static Collection<String> getSupportedLanguageCodes()
  {
    return LANGUAGE_CODE_MAP.keySet();
  }

  public static DateFormatSymbols lookupDateFormatSymbols(String paramString)
  {
    paramString = LANGUAGE_CODE_MAP.get(paramString);
    if (paramString != null)
    {
      if ((paramString instanceof Locale))
        return new DateFormatSymbols((Locale)paramString);
      if ((paramString instanceof String))
        return getDateFormatSymbols((String)paramString);
    }
    return new DateFormatSymbols(Locale.US);
  }

  private static String[] splitShortMonthString(String paramString)
  {
    paramString = new StringTokenizer(paramString, "|");
    if (12 != paramString.countTokens())
      throw new IllegalArgumentException("expecting a pipe-delimited string containing 12 tokens");
    String[] arrayOfString = new String[13];
    int i = 0;
    while (paramString.hasMoreTokens())
    {
      arrayOfString[i] = paramString.nextToken();
      i += 1;
    }
    arrayOfString[i] = "";
    return arrayOfString;
  }

  public String getDefaultDateFormatStr()
  {
    return this.defaultDateFormatStr;
  }

  public String getRecentDateFormatStr()
  {
    return this.recentDateFormatStr;
  }

  public String getServerLanguageCode()
  {
    return this.serverLanguageCode;
  }

  public String getServerSystemKey()
  {
    return this.serverSystemKey;
  }

  public String getServerTimeZoneId()
  {
    return this.serverTimeZoneId;
  }

  public String getShortMonthNames()
  {
    return this.shortMonthNames;
  }

  public boolean isLenientFutureDates()
  {
    return this.lenientFutureDates;
  }

  public void setDefaultDateFormatStr(String paramString)
  {
    this.defaultDateFormatStr = paramString;
  }

  public void setLenientFutureDates(boolean paramBoolean)
  {
    this.lenientFutureDates = paramBoolean;
  }

  public void setRecentDateFormatStr(String paramString)
  {
    this.recentDateFormatStr = paramString;
  }

  public void setServerLanguageCode(String paramString)
  {
    this.serverLanguageCode = paramString;
  }

  public void setServerTimeZoneId(String paramString)
  {
    this.serverTimeZoneId = paramString;
  }

  public void setShortMonthNames(String paramString)
  {
    this.shortMonthNames = paramString;
  }
}