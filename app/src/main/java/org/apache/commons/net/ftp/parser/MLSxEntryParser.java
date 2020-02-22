package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

public class MLSxEntryParser extends FTPFileEntryParserImpl
{
  private static final MLSxEntryParser PARSER = new MLSxEntryParser();
  private static final HashMap<String, Integer> TYPE_TO_INT = new HashMap();
  private static int[] UNIX_GROUPS = { 0, 1, 2 };
  private static int[][] UNIX_PERMS = { new int[0], { 2 }, { 1 }, { 2, 1 }, { 0 }, { 0, 2 }, { 0, 1 }, { 0, 1, 2 } };

  static
  {
    TYPE_TO_INT.put("file", Integer.valueOf(0));
    TYPE_TO_INT.put("cdir", Integer.valueOf(1));
    TYPE_TO_INT.put("pdir", Integer.valueOf(1));
    TYPE_TO_INT.put("dir", Integer.valueOf(1));
  }

  private void doUnixPerms(FTPFile paramFTPFile, String paramString)
  {
    paramString = paramString.toCharArray();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      int k = paramString[i];
      if (k != 97)
        if (k != 112)
          if (k != 114)
            if (k == 119);
      switch (k)
      {
      default:
        switch (k)
        {
        default:
          break;
        case 109:
          paramFTPFile.setPermission(0, 1, true);
          break;
        case 108:
          paramFTPFile.setPermission(0, 2, true);
        }
        break;
      case 101:
        paramFTPFile.setPermission(0, 0, true);
        break;
      case 100:
        paramFTPFile.setPermission(0, 1, true);
        break;
      case 99:
        paramFTPFile.setPermission(0, 1, true);
        break;
        paramFTPFile.setPermission(0, 1, true);
        break;
        paramFTPFile.setPermission(0, 0, true);
        break;
        paramFTPFile.setPermission(0, 1, true);
        break;
        paramFTPFile.setPermission(0, 1, true);
      case 102:
      }
      i += 1;
    }
  }

  public static MLSxEntryParser getInstance()
  {
    return PARSER;
  }

  public static FTPFile parseEntry(String paramString)
  {
    return PARSER.parseFTPEntry(paramString);
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    Object localObject1 = paramString.split(" ", 2);
    if (localObject1.length != 2)
      return null;
    FTPFile localFTPFile = new FTPFile();
    localFTPFile.setRawListing(paramString);
    localFTPFile.setName(localObject1[1]);
    String[] arrayOfString = localObject1[0].split(";");
    boolean bool = localObject1[0].toLowerCase(Locale.ENGLISH).contains("unix.mode=");
    int m = arrayOfString.length;
    int i = 0;
    while (true)
    {
      label101: Object localObject2;
      if (i < m)
      {
        localObject1 = arrayOfString[i].split("=");
        if (localObject1.length == 2)
        {
          while (true)
          {
            paramString = localObject1[0].toLowerCase(Locale.ENGLISH);
            localObject1 = localObject1[1];
            localObject2 = ((String)localObject1).toLowerCase(Locale.ENGLISH);
            if ("size".equals(paramString))
            {
              localFTPFile.setSize(Long.parseLong((String)localObject1));
            }
            else
            {
              if (!"sizd".equals(paramString))
                break;
              localFTPFile.setSize(Long.parseLong((String)localObject1));
            }
          }
          if ("modify".equals(paramString))
          {
            if (((String)localObject1).contains("."))
              paramString = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            else
              paramString = new SimpleDateFormat("yyyyMMddHHmmss");
            localObject2 = TimeZone.getTimeZone("GMT");
            paramString.setTimeZone((TimeZone)localObject2);
            localObject2 = new GregorianCalendar((TimeZone)localObject2);
          }
        }
      }
      try
      {
        ((GregorianCalendar)localObject2).setTime(paramString.parse((String)localObject1));
        label252: localFTPFile.setTimestamp((Calendar)localObject2);
        break label101;
        if ("type".equals(paramString))
        {
          paramString = (Integer)TYPE_TO_INT.get(localObject2);
          if (paramString == null)
          {
            localFTPFile.setType(3);
            break label101;
          }
          localFTPFile.setType(paramString.intValue());
          break label101;
        }
        if (paramString.startsWith("unix."))
        {
          paramString = paramString.substring("unix.".length()).toLowerCase(Locale.ENGLISH);
          if ("group".equals(paramString))
          {
            localFTPFile.setGroup((String)localObject1);
            break label101;
          }
          if ("owner".equals(paramString))
          {
            localFTPFile.setUser((String)localObject1);
            break label101;
          }
          if (!"mode".equals(paramString))
            break label101;
          int n = ((String)localObject1).length();
          int j = 0;
          while (j < 3)
          {
            int k = ((String)localObject1).charAt(n - 3 + j) - '0';
            if ((k >= 0) && (k <= 7))
            {
              paramString = UNIX_PERMS[k];
              int i1 = paramString.length;
              k = 0;
              while (k < i1)
              {
                int i2 = paramString[k];
                localFTPFile.setPermission(UNIX_GROUPS[j], i2, true);
                k += 1;
              }
            }
            j += 1;
          }
        }
        if ((bool) || (!"perm".equals(paramString)))
          break label101;
        doUnixPerms(localFTPFile, (String)localObject2);
        i += 1;
        continue;
        return localFTPFile;
      }
      catch (ParseException paramString)
      {
        break label252;
      }
    }
  }
}