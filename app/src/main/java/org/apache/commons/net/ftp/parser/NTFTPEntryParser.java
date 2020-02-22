package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class NTFTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  private static final String DEFAULT_DATE_FORMAT = "MM-dd-yy hh:mma";
  private static final String DEFAULT_DATE_FORMAT2 = "MM-dd-yy kk:mm";
  private static final String REGEX = "(\\S+)\\s+(\\S+)\\s+(?:(<DIR>)|([0-9]+))\\s+(\\S.*)";
  private FTPTimestampParser timestampParser;

  public NTFTPEntryParser()
  {
    this(null);
  }

  public NTFTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    super("(\\S+)\\s+(\\S+)\\s+(?:(<DIR>)|([0-9]+))\\s+(\\S.*)");
    configure(paramFTPClientConfig);
    paramFTPClientConfig = new FTPClientConfig("WINDOWS", "MM-dd-yy kk:mm", null, null, null, null);
    paramFTPClientConfig.setDefaultDateFormatStr("MM-dd-yy kk:mm");
    this.timestampParser = new FTPTimestampParserImpl();
    ((Configurable)this.timestampParser).configure(paramFTPClientConfig);
  }

  public FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("WINDOWS", "MM-dd-yy hh:mma", null, null, null, null);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    FTPFile localFTPFile = new FTPFile();
    localFTPFile.setRawListing(paramString);
    String str3;
    String str1;
    String str2;
    if (matches(paramString))
    {
      paramString = new StringBuilder();
      paramString.append(group(1));
      paramString.append(" ");
      paramString.append(group(2));
      str3 = paramString.toString();
      paramString = group(3);
      str1 = group(4);
      str2 = group(5);
    }
    try
    {
      localFTPFile.setTimestamp(super.parseTimestamp(str3));
    }
    catch (ParseException localParseException2)
    {
      try
      {
        while (true)
        {
          localFTPFile.setTimestamp(this.timestampParser.parseTimestamp(str3));
          label109: if ((str2 != null) && (!str2.equals(".")))
          {
            if (str2.equals(".."))
              return null;
            localFTPFile.setName(str2);
            if ("<DIR>".equals(paramString))
            {
              localFTPFile.setType(1);
              localFTPFile.setSize(0L);
              return localFTPFile;
            }
            localFTPFile.setType(0);
            if (str1 != null)
              localFTPFile.setSize(Long.parseLong(str1));
            return localFTPFile;
          }
          return null;
          return null;
          localParseException2 = localParseException2;
        }
      }
      catch (ParseException localParseException1)
      {
        break label109;
      }
    }
  }
}