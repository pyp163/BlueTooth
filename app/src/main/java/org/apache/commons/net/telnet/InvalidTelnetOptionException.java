package org.apache.commons.net.telnet;

public class InvalidTelnetOptionException extends Exception
{
  private static final long serialVersionUID = -2516777155928793597L;
  private final String msg;
  private final int optionCode;

  public InvalidTelnetOptionException(String paramString, int paramInt)
  {
    this.optionCode = paramInt;
    this.msg = paramString;
  }

  public String getMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.msg);
    localStringBuilder.append(": ");
    localStringBuilder.append(this.optionCode);
    return localStringBuilder.toString();
  }
}