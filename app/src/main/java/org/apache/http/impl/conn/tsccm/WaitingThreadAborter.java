package org.apache.http.impl.conn.tsccm;

import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class WaitingThreadAborter
{
  private boolean aborted;
  private WaitingThread waitingThread;

  public void abort()
  {
    this.aborted = true;
    if (this.waitingThread != null)
      this.waitingThread.interrupt();
  }

  public void setWaitingThread(WaitingThread paramWaitingThread)
  {
    this.waitingThread = paramWaitingThread;
    if (this.aborted)
      paramWaitingThread.interrupt();
  }
}