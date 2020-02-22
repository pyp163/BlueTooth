package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParser;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

public class CompositeFileEntryParser extends FTPFileEntryParserImpl
{
  private FTPFileEntryParser cachedFtpFileEntryParser = null;
  private final FTPFileEntryParser[] ftpFileEntryParsers;

  public CompositeFileEntryParser(FTPFileEntryParser[] paramArrayOfFTPFileEntryParser)
  {
    this.ftpFileEntryParsers = paramArrayOfFTPFileEntryParser;
  }

  public FTPFile parseFTPEntry(String paramString)
  {
    if (this.cachedFtpFileEntryParser != null)
    {
      paramString = this.cachedFtpFileEntryParser.parseFTPEntry(paramString);
      if (paramString != null)
        return paramString;
    }
    else
    {
      int i = 0;
      while (i < this.ftpFileEntryParsers.length)
      {
        FTPFileEntryParser localFTPFileEntryParser = this.ftpFileEntryParsers[i];
        FTPFile localFTPFile = localFTPFileEntryParser.parseFTPEntry(paramString);
        if (localFTPFile != null)
        {
          this.cachedFtpFileEntryParser = localFTPFileEntryParser;
          return localFTPFile;
        }
        i += 1;
      }
    }
    return null;
  }
}