package org.apache.commons.net;

import java.util.EventListener;

public abstract interface ProtocolCommandListener extends EventListener
{
  public abstract void protocolCommandSent(ProtocolCommandEvent paramProtocolCommandEvent);

  public abstract void protocolReplyReceived(ProtocolCommandEvent paramProtocolCommandEvent);
}