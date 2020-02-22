package okio;

import java.util.concurrent.TimeUnit;

final class PushableTimeout extends Timeout
{
  private long originalDeadlineNanoTime;
  private boolean originalHasDeadline;
  private long originalTimeoutNanos;
  private Timeout pushed;

  void pop()
  {
    this.pushed.timeout(this.originalTimeoutNanos, TimeUnit.NANOSECONDS);
    if (this.originalHasDeadline)
    {
      this.pushed.deadlineNanoTime(this.originalDeadlineNanoTime);
      return;
    }
    this.pushed.clearDeadline();
  }

  void push(Timeout paramTimeout)
  {
    this.pushed = paramTimeout;
    this.originalHasDeadline = paramTimeout.hasDeadline();
    long l;
    if (this.originalHasDeadline)
      l = paramTimeout.deadlineNanoTime();
    else
      l = -1L;
    this.originalDeadlineNanoTime = l;
    this.originalTimeoutNanos = paramTimeout.timeoutNanos();
    paramTimeout.timeout(minTimeout(this.originalTimeoutNanos, timeoutNanos()), TimeUnit.NANOSECONDS);
    if ((this.originalHasDeadline) && (hasDeadline()))
    {
      paramTimeout.deadlineNanoTime(Math.min(deadlineNanoTime(), this.originalDeadlineNanoTime));
      return;
    }
    if (hasDeadline())
      paramTimeout.deadlineNanoTime(deadlineNanoTime());
  }
}