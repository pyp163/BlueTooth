package org.apache.commons.net.telnet;

public class SimpleOptionHandler extends TelnetOptionHandler
{
  public SimpleOptionHandler(int paramInt)
  {
    super(paramInt, false, false, false, false);
  }

  public SimpleOptionHandler(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    super(paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
  }

  public int[] answerSubnegotiation(int[] paramArrayOfInt, int paramInt)
  {
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