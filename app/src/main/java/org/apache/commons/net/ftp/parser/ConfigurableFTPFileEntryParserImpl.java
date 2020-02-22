package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.Calendar;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;

public abstract class ConfigurableFTPFileEntryParserImpl extends RegexFTPFileEntryParserImpl
  implements Configurable
{
  private final FTPTimestampParser timestampParser = new FTPTimestampParserImpl();

  public ConfigurableFTPFileEntryParserImpl(String paramString)
  {
    super(paramString);
  }

  public void configure(FTPClientConfig paramFTPClientConfig)
  {
    if ((this.timestampParser instanceof Configurable))
    {
      FTPClientConfig localFTPClientConfig = getDefaultConfiguration();
      if (paramFTPClientConfig != null)
      {
        if (paramFTPClientConfig.getDefaultDateFormatStr() == null)
          paramFTPClientConfig.setDefaultDateFormatStr(localFTPClientConfig.getDefaultDateFormatStr());
        if (paramFTPClientConfig.getRecentDateFormatStr() == null)
          paramFTPClientConfig.setRecentDateFormatStr(localFTPClientConfig.getRecentDateFormatStr());
        ((Configurable)this.timestampParser).configure(paramFTPClientConfig);
        return;
      }
      ((Configurable)this.timestampParser).configure(localFTPClientConfig);
    }
  }

  protected abstract FTPClientConfig getDefaultConfiguration();

  public Calendar parseTimestamp(String paramString)
    throws ParseException
  {
    return this.timestampParser.parseTimestamp(paramString);
  }
}