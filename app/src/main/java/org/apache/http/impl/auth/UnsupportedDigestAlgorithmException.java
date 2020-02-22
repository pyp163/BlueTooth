package org.apache.http.impl.auth;

import org.apache.http.annotation.Immutable;

@Immutable
public class UnsupportedDigestAlgorithmException extends RuntimeException
{
  private static final long serialVersionUID = 319558534317118022L;

  public UnsupportedDigestAlgorithmException()
  {
  }

  public UnsupportedDigestAlgorithmException(String paramString)
  {
    super(paramString);
  }

  public UnsupportedDigestAlgorithmException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}