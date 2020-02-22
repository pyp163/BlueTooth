package org.apache.http;

import org.apache.http.util.ExceptionUtils;

public class HttpException extends Exception
{
  private static final long serialVersionUID = -5437299376222011036L;

  public HttpException()
  {
  }

  public HttpException(String paramString)
  {
    super(paramString);
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    ExceptionUtils.initCause(this, paramThrowable);
  }
}