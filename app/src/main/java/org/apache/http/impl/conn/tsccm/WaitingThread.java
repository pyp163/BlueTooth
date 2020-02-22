package org.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class WaitingThread
{
  private boolean aborted;
  private final Condition cond;
  private final RouteSpecificPool pool;
  private Thread waiter;

  public WaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    if (paramCondition == null)
      throw new IllegalArgumentException("Condition must not be null.");
    this.cond = paramCondition;
    this.pool = paramRouteSpecificPool;
  }

  public boolean await(Date paramDate)
    throws InterruptedException
  {
    if (this.waiter != null)
    {
      paramDate = new StringBuilder();
      paramDate.append("A thread is already waiting on this object.\ncaller: ");
      paramDate.append(Thread.currentThread());
      paramDate.append("\nwaiter: ");
      paramDate.append(this.waiter);
      throw new IllegalStateException(paramDate.toString());
    }
    if (this.aborted)
      throw new InterruptedException("Operation interrupted");
    this.waiter = Thread.currentThread();
    if (paramDate != null);
    try
    {
      boolean bool = this.cond.awaitUntil(paramDate);
      break label111;
      this.cond.await();
      bool = true;
      label111: if (this.aborted)
        throw new InterruptedException("Operation interrupted");
      this.waiter = null;
      return bool;
      label135: this.waiter = null;
      throw paramDate;
    }
    finally
    {
      break label135;
    }
  }

  public final Condition getCondition()
  {
    return this.cond;
  }

  public final RouteSpecificPool getPool()
  {
    return this.pool;
  }

  public final Thread getThread()
  {
    return this.waiter;
  }

  public void interrupt()
  {
    this.aborted = true;
    this.cond.signalAll();
  }

  public void wakeup()
  {
    if (this.waiter == null)
      throw new IllegalStateException("Nobody waiting on this object.");
    this.cond.signalAll();
  }
}