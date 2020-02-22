package org.apache.commons.net.telnet;

public class TerminalTypeOptionHandler extends TelnetOptionHandler
{
  protected static final int TERMINAL_TYPE = 24;
  protected static final int TERMINAL_TYPE_IS = 0;
  protected static final int TERMINAL_TYPE_SEND = 1;
  private final String termType;

  public TerminalTypeOptionHandler(String paramString)
  {
    super(24, false, false, false, false);
    this.termType = paramString;
  }

  public TerminalTypeOptionHandler(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    super(24, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    this.termType = paramString;
  }

  public int[] answerSubnegotiation(int[] paramArrayOfInt, int paramInt)
  {
    if ((paramArrayOfInt != null) && (paramInt > 1) && (this.termType != null))
    {
      paramInt = 0;
      if ((paramArrayOfInt[0] == 24) && (paramArrayOfInt[1] == 1))
      {
        paramArrayOfInt = new int[this.termType.length() + 2];
        paramArrayOfInt[0] = 24;
        paramArrayOfInt[1] = 0;
        while (paramInt < this.termType.length())
        {
          paramArrayOfInt[(paramInt + 2)] = this.termType.charAt(paramInt);
          paramInt += 1;
        }
        return paramArrayOfInt;
      }
    }
    return null;
  }

  public int[] startSubnegotiationLocal()
  {
    return null;
  }

  public int[] startSubnegotiationRemote()
  {
    return null;
  }
}