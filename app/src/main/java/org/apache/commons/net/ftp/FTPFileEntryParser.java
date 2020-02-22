package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public abstract interface FTPFileEntryParser
{
  public abstract FTPFile parseFTPEntry(String paramString);

  public abstract List<String> preParse(List<String> paramList);

  public abstract String readNextEntry(BufferedReader paramBufferedReader)
    throws IOException;
}