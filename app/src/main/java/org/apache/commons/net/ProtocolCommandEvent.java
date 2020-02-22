package org.apache.commons.net;

import java.util.EventObject;

public class ProtocolCommandEvent extends EventObject
{
  private static final long serialVersionUID = 403743538418947240L;
  private final String __command;
  private final boolean __isCommand;
  private final String __message;
  private final int __replyCode;

  public ProtocolCommandEvent(Object paramObject, int paramInt, String paramString)
  {
    super(paramObject);
    this.__replyCode = paramInt;
    this.__message = paramString;
    this.__isCommand = false;
    this.__command = null;
  }

  public ProtocolCommandEvent(Object paramObject, String paramString1, String paramString2)
  {
    super(paramObject);
    this.__replyCode = 0;
    this.__message = paramString2;
    this.__isCommand = true;
    this.__command = paramString1;
  }

  public String getCommand()
  {
    return this.__command;
  }

  public String getMessage()
  {
    return this.__message;
  }

  public int getReplyCode()
  {
    return this.__replyCode;
  }

  public boolean isCommand()
  {
    return this.__isCommand;
  }

  public boolean isReply()
  {
    return isCommand() ^ true;
  }
}