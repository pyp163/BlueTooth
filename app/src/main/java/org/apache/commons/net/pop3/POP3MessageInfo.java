package org.apache.commons.net.pop3;

public final class POP3MessageInfo
{
  public String identifier;
  public int number;
  public int size;

  public POP3MessageInfo()
  {
    this(0, null, 0);
  }

  public POP3MessageInfo(int paramInt1, int paramInt2)
  {
    this(paramInt1, null, paramInt2);
  }

  public POP3MessageInfo(int paramInt, String paramString)
  {
    this(paramInt, paramString, -1);
  }

  private POP3MessageInfo(int paramInt1, String paramString, int paramInt2)
  {
    this.number = paramInt1;
    this.size = paramInt2;
    this.identifier = paramString;
  }
}