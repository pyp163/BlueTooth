package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFileEntryParser;

public abstract interface FTPFileEntryParserFactory
{
  public abstract FTPFileEntryParser createFileEntryParser(String paramString)
    throws ParserInitializationException;

  public abstract FTPFileEntryParser createFileEntryParser(FTPClientConfig paramFTPClientConfig)
    throws ParserInitializationException;
}