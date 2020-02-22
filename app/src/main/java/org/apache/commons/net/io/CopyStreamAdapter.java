package org.apache.commons.net.io;

import java.util.Iterator;
import org.apache.commons.net.util.ListenerList;

public class CopyStreamAdapter
  implements CopyStreamListener
{
  private final ListenerList internalListeners = new ListenerList();

  public void addCopyStreamListener(CopyStreamListener paramCopyStreamListener)
  {
    this.internalListeners.addListener(paramCopyStreamListener);
  }

  public void bytesTransferred(long paramLong1, int paramInt, long paramLong2)
  {
    Iterator localIterator = this.internalListeners.iterator();
    while (localIterator.hasNext())
      ((CopyStreamListener)localIterator.next()).bytesTransferred(paramLong1, paramInt, paramLong2);
  }

  public void bytesTransferred(CopyStreamEvent paramCopyStreamEvent)
  {
    Iterator localIterator = this.internalListeners.iterator();
    while (localIterator.hasNext())
      ((CopyStreamListener)localIterator.next()).bytesTransferred(paramCopyStreamEvent);
  }

  public void removeCopyStreamListener(CopyStreamListener paramCopyStreamListener)
  {
    this.internalListeners.removeListener(paramCopyStreamListener);
  }
}