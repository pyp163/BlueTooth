package org.apache.commons.net;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.commons.net.util.ListenerList;

public class ProtocolCommandSupport
  implements Serializable
{
  private static final long serialVersionUID = -8017692739988399978L;
  private final ListenerList __listeners = new ListenerList();
  private final Object __source;

  public ProtocolCommandSupport(Object paramObject)
  {
    this.__source = paramObject;
  }

  public void addProtocolCommandListener(ProtocolCommandListener paramProtocolCommandListener)
  {
    this.__listeners.addListener(paramProtocolCommandListener);
  }

  public void fireCommandSent(String paramString1, String paramString2)
  {
    paramString1 = new ProtocolCommandEvent(this.__source, paramString1, paramString2);
    paramString2 = this.__listeners.iterator();
    while (paramString2.hasNext())
      ((ProtocolCommandListener)paramString2.next()).protocolCommandSent(paramString1);
  }

  public void fireReplyReceived(int paramInt, String paramString)
  {
    paramString = new ProtocolCommandEvent(this.__source, paramInt, paramString);
    Iterator localIterator = this.__listeners.iterator();
    while (localIterator.hasNext())
      ((ProtocolCommandListener)localIterator.next()).protocolReplyReceived(paramString);
  }

  public int getListenerCount()
  {
    return this.__listeners.getListenerCount();
  }

  public void removeProtocolCommandListener(ProtocolCommandListener paramProtocolCommandListener)
  {
    this.__listeners.removeListener(paramProtocolCommandListener);
  }
}