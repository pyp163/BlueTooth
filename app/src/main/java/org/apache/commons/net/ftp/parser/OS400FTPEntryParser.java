package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class OS400FTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  private static final String DEFAULT_DATE_FORMAT = "yy/MM/dd HH:mm:ss";
  private static final String REGEX = "(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\*\\S+)\\s+(\\S+/?)\\s*";

  public OS400FTPEntryParser()
  {
    this(null);
  }

  public OS400FTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    super("(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\S+)\\s+(\\*\\S+)\\s+(\\S+/?)\\s*");
    configure(paramFTPClientConfig);
  }

  protected FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("OS/400", "yy/MM/dd HH:mm:ss", null, null, null, null);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    FTPFile localFTPFile = new FTPFile();
    localFTPFile.setRawListing(paramString);
    String str1;
    Object localObject;
    int i;
    String str3;
    String str2;
    if (matches(paramString))
    {
      paramString = group(1);
      str1 = group(2);
      localObject = new StringBuilder();
      i = 3;
      ((StringBuilder)localObject).append(group(3));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(group(4));
      str3 = ((StringBuilder)localObject).toString();
      str2 = group(5);
      localObject = group(6);
    }
    try
    {
      localFTPFile.setTimestamp(super.parseTimestamp(str3));
      if (str2.equalsIgnoreCase("*STMF"))
        i = 0;
      else if (str2.equalsIgnoreCase("*DIR"))
        i = 1;
      localFTPFile.setType(i);
      localFTPFile.setUser(paramString);
    }
    catch (ParseException localParseException)
    {
      try
      {
        localFTPFile.setSize(Long.parseLong(str1));
        label153: paramString = (String)localObject;
        if (((String)localObject).endsWith("/"))
          paramString = ((String)localObject).substring(0, ((String)localObject).length() - 1);
        i = paramString.lastIndexOf('/');
        localObject = paramString;
        if (i > -1)
          localObject = paramString.substring(i + 1);
        localFTPFile.setName((String)localObject);
        return localFTPFile;
        return null;
        localParseException = localParseException;
      }
      catch (NumberFormatException paramString)
      {
        break label153;
      }
    }
  }
}