package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration
  implements Closeable
{
  private Runnable action;
  private boolean closed;
  private final Object lock = new Object();
  private CancellationTokenSource tokenSource;

  CancellationTokenRegistration(CancellationTokenSource paramCancellationTokenSource, Runnable paramRunnable)
  {
    this.tokenSource = paramCancellationTokenSource;
    this.action = paramRunnable;
  }

  private void throwIfClosed()
  {
    if (this.closed)
      throw new IllegalStateException("Object already closed");
  }

  public void close()
  {
    synchronized (this.lock)
    {
      if (this.closed)
        return;
      this.closed = true;
      this.tokenSource.unregister(this);
      this.tokenSource = null;
      this.action = null;
      return;
    }
  }

  void runAction()
  {
    synchronized (this.lock)
    {
      throwIfClosed();
      this.action.run();
      close();
      return;
    }
  }
}