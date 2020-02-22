package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class UnixFTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  static final String DEFAULT_DATE_FORMAT = "MMM d yyyy";
  static final String DEFAULT_RECENT_DATE_FORMAT = "MMM d HH:mm";
  public static final FTPClientConfig NUMERIC_DATE_CONFIG = new FTPClientConfig("UNIX", "yyyy-MM-dd HH:mm", null, null, null, null);
  static final String NUMERIC_DATE_FORMAT = "yyyy-MM-dd HH:mm";
  private static final String REGEX = "([bcdelfmpSs-])(((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-])))\\+?\\s*(\\d+)\\s+(?:(\\S+(?:\\s\\S+)*?)\\s+)?(?:(\\S+(?:\\s\\S+)*)\\s+)?(\\d+(?:,\\s*\\d+)?)\\s+((?:\\d+[-/]\\d+[-/]\\d+)|(?:\\S{3}\\s+\\d{1,2})|(?:\\d{1,2}\\s+\\S{3}))\\s+(\\d+(?::\\d+)?)\\s+(\\S*)(\\s*.*)";

  public UnixFTPEntryParser()
  {
    this(null);
  }

  public UnixFTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    super("([bcdelfmpSs-])(((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-])))\\+?\\s*(\\d+)\\s+(?:(\\S+(?:\\s\\S+)*?)\\s+)?(?:(\\S+(?:\\s\\S+)*)\\s+)?(\\d+(?:,\\s*\\d+)?)\\s+((?:\\d+[-/]\\d+[-/]\\d+)|(?:\\S{3}\\s+\\d{1,2})|(?:\\d{1,2}\\s+\\S{3}))\\s+(\\d+(?::\\d+)?)\\s+(\\S*)(\\s*.*)");
    configure(paramFTPClientConfig);
  }

  protected FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("UNIX", "MMM d yyyy", "MMM d HH:mm", null, null, null);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    FTPFile localFTPFile = new FTPFile();
    localFTPFile.setRawListing(paramString);
    String str5;
    String str4;
    Object localObject;
    String str2;
    String str3;
    String str6;
    String str1;
    if (matches(paramString))
    {
      str5 = group(1);
      str4 = group(15);
      localObject = group(16);
      str2 = group(17);
      str3 = group(18);
      paramString = new StringBuilder();
      paramString.append(group(19));
      paramString.append(" ");
      paramString.append(group(20));
      str6 = paramString.toString();
      paramString = group(21);
      str1 = group(22);
    }
    try
    {
      localFTPFile.setTimestamp(super.parseTimestamp(str6));
      i = str5.charAt(0);
      if (i != 45)
      {
        if (i != 108);
        switch (i)
        {
        default:
          i = 3;
        case 101:
        case 100:
          while (true)
          {
            j = 0;
            break;
            i = 2;
            continue;
            i = 1;
          }
        case 98:
        case 99:
          i = 1;
          break;
        case 102:
        }
      }
      else
      {
        i = 0;
      }
      int k = 0;
      int j = i;
      i = k;
      localFTPFile.setType(i);
      k = 4;
      int m = 0;
      while (m < 3)
      {
        localFTPFile.setPermission(m, 0, group(k).equals("-") ^ true);
        localFTPFile.setPermission(m, 1, group(k + 1).equals("-") ^ true);
        str5 = group(k + 2);
        if ((!str5.equals("-")) && (!Character.isUpperCase(str5.charAt(0))))
          localFTPFile.setPermission(m, 2, true);
        else
          localFTPFile.setPermission(m, 2, false);
        m += 1;
        k += 4;
      }
      if (j != 0);
    }
    catch (ParseException localNumberFormatException2)
    {
      try
      {
        localFTPFile.setHardLinkCount(Integer.parseInt(str4));
        localFTPFile.setUser((String)localObject);
        localFTPFile.setGroup(str2);
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        try
        {
          while (true)
          {
            int i;
            localFTPFile.setSize(Long.parseLong(str3));
            label384: if (str1 == null)
            {
              localFTPFile.setName(paramString);
              return localFTPFile;
            }
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(paramString);
            ((StringBuilder)localObject).append(str1);
            paramString = ((StringBuilder)localObject).toString();
            if (i == 2)
            {
              i = paramString.indexOf(" -> ");
              if (i == -1)
              {
                localFTPFile.setName(paramString);
                return localFTPFile;
              }
              localFTPFile.setName(paramString.substring(0, i));
              localFTPFile.setLink(paramString.substring(i + 4));
              return localFTPFile;
            }
            localFTPFile.setName(paramString);
            return localFTPFile;
            return null;
            localParseException = localParseException;
          }
          localNumberFormatException2 = localNumberFormatException2;
        }
        catch (NumberFormatException localNumberFormatException1)
        {
          break label384;
        }
      }
    }
  }

  public List<String> preParse(List<String> paramList)
  {
    ListIterator localListIterator = paramList.listIterator();
    while (localListIterator.hasNext())
      if (((String)localListIterator.next()).matches("^total \\d+$"))
        localListIterator.remove();
    return paramList;
  }
}