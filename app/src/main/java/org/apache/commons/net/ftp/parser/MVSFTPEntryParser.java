package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.List;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

public class MVSFTPEntryParser extends ConfigurableFTPFileEntryParserImpl
{
  static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm";
  static final String FILE_LIST_REGEX = "\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+[FV]\\S*\\s+\\S+\\s+\\S+\\s+(PS|PO|PO-E)\\s+(\\S+)\\s*";
  static final int FILE_LIST_TYPE = 0;
  static final String JES_LEVEL_1_LIST_REGEX = "(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*";
  static final int JES_LEVEL_1_LIST_TYPE = 3;
  static final String JES_LEVEL_2_LIST_REGEX = "(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+).*";
  static final int JES_LEVEL_2_LIST_TYPE = 4;
  static final String MEMBER_LIST_REGEX = "(\\S+)\\s+\\S+\\s+\\S+\\s+(\\S+)\\s+(\\S+)\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s*";
  static final int MEMBER_LIST_TYPE = 1;
  static final int UNIX_LIST_TYPE = 2;
  static final int UNKNOWN_LIST_TYPE = -1;
  private int isType = -1;
  private UnixFTPEntryParser unixFTPEntryParser;

  public MVSFTPEntryParser()
  {
    super("");
    super.configure(null);
  }

  private boolean parseFileList(FTPFile paramFTPFile, String paramString)
  {
    if (matches(paramString))
    {
      paramFTPFile.setRawListing(paramString);
      paramString = group(2);
      String str = group(1);
      paramFTPFile.setName(paramString);
      if ("PS".equals(str))
      {
        paramFTPFile.setType(0);
        return true;
      }
      if ((!"PO".equals(str)) && (!"PO-E".equals(str)))
        return false;
      paramFTPFile.setType(1);
      return true;
    }
    return false;
  }

  private boolean parseJeslevel1List(FTPFile paramFTPFile, String paramString)
  {
    if ((matches(paramString)) && (group(3).equalsIgnoreCase("OUTPUT")))
    {
      paramFTPFile.setRawListing(paramString);
      paramFTPFile.setName(group(2));
      paramFTPFile.setType(0);
      return true;
    }
    return false;
  }

  private boolean parseJeslevel2List(FTPFile paramFTPFile, String paramString)
  {
    if ((matches(paramString)) && (group(4).equalsIgnoreCase("OUTPUT")))
    {
      paramFTPFile.setRawListing(paramString);
      paramFTPFile.setName(group(2));
      paramFTPFile.setType(0);
      return true;
    }
    return false;
  }

  private boolean parseMemberList(FTPFile paramFTPFile, String paramString)
  {
    if (matches(paramString))
    {
      paramFTPFile.setRawListing(paramString);
      paramString = group(1);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(group(2));
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(group(3));
      localObject = ((StringBuilder)localObject).toString();
      paramFTPFile.setName(paramString);
      paramFTPFile.setType(0);
      try
      {
        paramFTPFile.setTimestamp(super.parseTimestamp((String)localObject));
        return true;
      }
      catch (ParseException paramFTPFile)
      {
        paramFTPFile.printStackTrace();
        return false;
      }
    }
    return false;
  }

  private boolean parseSimpleEntry(FTPFile paramFTPFile, String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      paramFTPFile.setRawListing(paramString);
      paramFTPFile.setName(paramString.split(" ")[0]);
      paramFTPFile.setType(0);
      return true;
    }
    return false;
  }

  private boolean parseUnixList(FTPFile paramFTPFile, String paramString)
  {
    return this.unixFTPEntryParser.parseFTPEntry(paramString) != null;
  }

  protected FTPClientConfig getDefaultConfiguration()
  {
    return new FTPClientConfig("MVS", "yyyy/MM/dd HH:mm", null, null, null, null);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    FTPFile localFTPFile = new FTPFile();
    boolean bool;
    if (this.isType == 0)
    {
      bool = parseFileList(localFTPFile, paramString);
    }
    else if (this.isType == 1)
    {
      bool = parseMemberList(localFTPFile, paramString);
      if (!bool)
        bool = parseSimpleEntry(localFTPFile, paramString);
    }
    else if (this.isType == 2)
    {
      bool = parseUnixList(localFTPFile, paramString);
    }
    else if (this.isType == 3)
    {
      bool = parseJeslevel1List(localFTPFile, paramString);
    }
    else if (this.isType == 4)
    {
      bool = parseJeslevel2List(localFTPFile, paramString);
    }
    else
    {
      bool = false;
    }
    paramString = localFTPFile;
    if (!bool)
      paramString = null;
    return paramString;
  }

  public List<String> preParse(List<String> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      String str = (String)paramList.get(0);
      if ((str.indexOf("Volume") >= 0) && (str.indexOf("Dsname") >= 0))
      {
        setType(0);
        super.setRegex("\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+[FV]\\S*\\s+\\S+\\s+\\S+\\s+(PS|PO|PO-E)\\s+(\\S+)\\s*");
      }
      else if ((str.indexOf("Name") >= 0) && (str.indexOf("Id") >= 0))
      {
        setType(1);
        super.setRegex("(\\S+)\\s+\\S+\\s+\\S+\\s+(\\S+)\\s+(\\S+)\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s*");
      }
      else if (str.indexOf("total") == 0)
      {
        setType(2);
        this.unixFTPEntryParser = new UnixFTPEntryParser();
      }
      else if (str.indexOf("Spool Files") >= 30)
      {
        setType(3);
        super.setRegex("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*");
      }
      else if ((str.indexOf("JOBNAME") == 0) && (str.indexOf("JOBID") > 8))
      {
        setType(4);
        super.setRegex("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+).*");
      }
      else
      {
        setType(-1);
      }
      if (this.isType != 3)
        paramList.remove(0);
    }
    return paramList;
  }

  void setType(int paramInt)
  {
    this.isType = paramInt;
  }
}