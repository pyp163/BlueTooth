package org.apache.commons.net.telnet;

public abstract class TelnetOptionHandler
{
  private boolean acceptLocal = false;
  private boolean acceptRemote = false;
  private boolean doFlag = false;
  private boolean initialLocal = false;
  private boolean initialRemote = false;
  private int optionCode = -1;
  private boolean willFlag = false;

  public TelnetOptionHandler(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.optionCode = paramInt;
    this.initialLocal = paramBoolean1;
    this.initialRemote = paramBoolean2;
    this.acceptLocal = paramBoolean3;
    this.acceptRemote = paramBoolean4;
  }

  public abstract int[] answerSubnegotiation(int[] paramArrayOfInt, int paramInt);

  public boolean getAcceptLocal()
  {
    return this.acceptLocal;
  }

  public boolean getAcceptRemote()
  {
    return this.acceptRemote;
  }

  boolean getDo()
  {
    return this.doFlag;
  }

  public boolean getInitLocal()
  {
    return this.initialLocal;
  }

  public boolean getInitRemote()
  {
    return this.initialRemote;
  }

  public int getOptionCode()
  {
    return this.optionCode;
  }

  boolean getWill()
  {
    return this.willFlag;
  }

  public void setAcceptLocal(boolean paramBoolean)
  {
    this.acceptLocal = paramBoolean;
  }

  public void setAcceptRemote(boolean paramBoolean)
  {
    this.acceptRemote = paramBoolean;
  }

  void setDo(boolean paramBoolean)
  {
    this.doFlag = paramBoolean;
  }

  public void setInitLocal(boolean paramBoolean)
  {
    this.initialLocal = paramBoolean;
  }

  public void setInitRemote(boolean paramBoolean)
  {
    this.initialRemote = paramBoolean;
  }

  void setWill(boolean paramBoolean)
  {
    this.willFlag = paramBoolean;
  }

  public abstract int[] startSubnegotiationLocal();

  public abstract int[] startSubnegotiationRemote();
}