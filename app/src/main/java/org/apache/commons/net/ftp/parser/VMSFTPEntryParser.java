package org.apache.commons.net.ftp.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.StringTokenizer;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;

public class VMSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  private static final String DEFAULT_DATE_FORMAT = "d-MMM-yyyy HH:mm:ss";
  private static final String REGEX = "(.*;[0-9]+)\\s*(\\d+)/\\d+\\s*(\\S+)\\s+(\\S+)\\s+\\[(([0-9$A-Za-z_]+)|([0-9$A-Za-z_]+),([0-9$a-zA-Z_]+))\\]?\\s*\\([a-zA-Z]*,([a-zA-Z]*),([a-zA-Z]*),([a-zA-Z]*)\\)";

  public VMSFTPEntryParser()
  {
    this(null);
  }

  public VMSFTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    super("(.*;[0-9]+)\\s*(\\d+)/\\d+\\s*(\\S+)\\s+(\\S+)\\s+\\[(([0-9$A-Za-z_]+)|([0-9$A-Za-z_]+),([0-9$a-zA-Z_]+))\\]?\\s*\\([a-zA-Z]*,([a-zA-Z]*),([a-zA-Z]*),([a-zA-Z]*)\\)");
    configure(paramFTPClientConfig);
  }

  protected FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("VMS", "d-MMM-yyyy HH:mm:ss", null, null, null, null);
  }

  protected boolean isVersioning()
  {
    return false;
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    boolean bool = matches(paramString);
    String str1 = null;
    FTPFile localFTPFile;
    String str2;
    String str3;
    String str7;
    String str4;
    String str5;
    String str6;
    if (bool)
    {
      localFTPFile = new FTPFile();
      localFTPFile.setRawListing(paramString);
      str2 = group(1);
      str3 = group(2);
      paramString = new StringBuilder();
      paramString.append(group(3));
      paramString.append(" ");
      paramString.append(group(4));
      str7 = paramString.toString();
      paramString = group(5);
      str4 = group(9);
      str5 = group(10);
      str6 = group(11);
    }
    try
    {
      localFTPFile.setTimestamp(super.parseTimestamp(str7));
      label124: paramString = new StringTokenizer(paramString, ",");
      switch (paramString.countTokens())
      {
      default:
        paramString = null;
        break;
      case 2:
        str1 = paramString.nextToken();
        paramString = paramString.nextToken();
        break;
      case 1:
        paramString = paramString.nextToken();
      }
      if (str2.lastIndexOf(".DIR") != -1)
        localFTPFile.setType(1);
      else
        localFTPFile.setType(0);
      if (isVersioning())
        localFTPFile.setName(str2);
      else
        localFTPFile.setName(str2.substring(0, str2.lastIndexOf(";")));
      localFTPFile.setSize(Long.parseLong(str3) * 512L);
      localFTPFile.setGroup(str1);
      localFTPFile.setUser(paramString);
      int i = 0;
      while (i < 3)
      {
        paramString = new String[] { str4, str5, str6 }[i];
        if (paramString.indexOf('R') >= 0)
          bool = true;
        else
          bool = false;
        localFTPFile.setPermission(i, 0, bool);
        if (paramString.indexOf('W') >= 0)
          bool = true;
        else
          bool = false;
        localFTPFile.setPermission(i, 1, bool);
        if (paramString.indexOf('E') >= 0)
          bool = true;
        else
          bool = false;
        localFTPFile.setPermission(i, 2, bool);
        i += 1;
      }
      return localFTPFile;
      return null;
    }
    catch (ParseException localParseException)
    {
      break label124;
    }
  }

  @Deprecated
  public FTPFile[] parseFileList(InputStream paramInputStream)
    throws IOException
  {
    FTPListParseEngine localFTPListParseEngine = new FTPListParseEngine(this);
    localFTPListParseEngine.readServerList(paramInputStream, null);
    return localFTPListParseEngine.getFiles();
  }

  public String readNextEntry(BufferedReader paramBufferedReader)
    throws IOException
  {
    String str = paramBufferedReader.readLine();
    StringBuilder localStringBuilder = new StringBuilder();
    while (str != null)
      if ((!str.startsWith("Directory")) && (!str.startsWith("Total")))
      {
        localStringBuilder.append(str);
        if (str.trim().endsWith(")"))
          break;
        str = paramBufferedReader.readLine();
      }
      else
      {
        str = paramBufferedReader.readLine();
      }
    if (localStringBuilder.length() == 0)
      return null;
    return localStringBuilder.toString();
  }
}