package org.apache.commons.net.telnet;

public class EchoOptionHandler extends TelnetOptionHandler
{
  public EchoOptionHandler()
  {
    super(1, false, false, false, false);
  }

  public EchoOptionHandler(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    super(1, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
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