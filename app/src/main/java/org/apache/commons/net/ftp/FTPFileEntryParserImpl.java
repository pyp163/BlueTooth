package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public abstract class FTPFileEntryParserImpl
  implements FTPFileEntryParser
{
  public List<String> preParse(List<String> paramList)
  {
    return paramList;
  }

  public String readNextEntry(BufferedReader paramBufferedReader)
    throws IOException
  {
    return paramBufferedReader.readLine();
  }
}