package org.apache.http.impl.conn.tsccm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

@Deprecated
public class RefQueueWorker
  implements Runnable
{
  protected final RefQueueHandler refHandler;
  protected final ReferenceQueue<?> refQueue;
  protected volatile Thread workerThread;

  public RefQueueWorker(ReferenceQueue<?> paramReferenceQueue, RefQueueHandler paramRefQueueHandler)
  {
    if (paramReferenceQueue == null)
      throw new IllegalArgumentException("Queue must not be null.");
    if (paramRefQueueHandler == null)
      throw new IllegalArgumentException("Handler must not be null.");
    this.refQueue = paramReferenceQueue;
    this.refHandler = paramRefQueueHandler;
  }

  public void run()
  {
    if (this.workerThread == null)
      this.workerThread = Thread.currentThread();
    while (true)
    {
      if (this.workerThread == Thread.currentThread());
      try
      {
        Reference localReference = this.refQueue.remove();
        this.refHandler.handleReference(localReference);
        continue;
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
  }

  public void shutdown()
  {
    Thread localThread = this.workerThread;
    if (localThread != null)
    {
      this.workerThread = null;
      localThread.interrupt();
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RefQueueWorker::");
    localStringBuilder.append(this.workerThread);
    return localStringBuilder.toString();
  }
}