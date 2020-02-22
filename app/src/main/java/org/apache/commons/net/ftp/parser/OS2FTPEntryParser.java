package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class OS2FTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  private static final String DEFAULT_DATE_FORMAT = "MM-dd-yy HH:mm";
  private static final String REGEX = "\\s*([0-9]+)\\s*(\\s+|[A-Z]+)\\s*(DIR|\\s+)\\s*(\\S+)\\s+(\\S+)\\s+(\\S.*)";

  public OS2FTPEntryParser()
  {
    this(null);
  }

  public OS2FTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    super("\\s*([0-9]+)\\s*(\\s+|[A-Z]+)\\s*(DIR|\\s+)\\s*(\\S+)\\s+(\\S+)\\s+(\\S.*)");
    configure(paramFTPClientConfig);
  }

  protected FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("OS/2", "MM-dd-yy HH:mm", null, null, null, null);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    FTPFile localFTPFile = new FTPFile();
    String str1;
    String str2;
    Object localObject;
    String str3;
    if (matches(paramString))
    {
      paramString = group(1);
      str1 = group(2);
      str2 = group(3);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(group(4));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(group(5));
      str3 = ((StringBuilder)localObject).toString();
      localObject = group(6);
    }
    try
    {
      localFTPFile.setTimestamp(super.parseTimestamp(str3));
      label99: if ((!str2.trim().equals("DIR")) && (!str1.trim().equals("DIR")))
        localFTPFile.setType(0);
      else
        localFTPFile.setType(1);
      localFTPFile.setName(((String)localObject).trim());
      localFTPFile.setSize(Long.parseLong(paramString.trim()));
      return localFTPFile;
      return null;
    }
    catch (ParseException localParseException)
    {
      break label99;
    }
  }
}