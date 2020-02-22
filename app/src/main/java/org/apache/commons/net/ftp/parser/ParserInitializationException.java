package org.apache.commons.net.ftp.parser;

public class ParserInitializationException extends RuntimeException
{
  private static final long serialVersionUID = 5563335279583210658L;

  public ParserInitializationException(String paramString)
  {
    super(paramString);
  }

  public ParserInitializationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  @Deprecated
  public Throwable getRootCause()
  {
    return super.getCause();
  }
}