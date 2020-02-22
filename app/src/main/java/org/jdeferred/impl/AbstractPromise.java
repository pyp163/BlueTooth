package org.jdeferred.impl;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jdeferred.AlwaysCallback;
import org.jdeferred.DoneCallback;
import org.jdeferred.DoneFilter;
import org.jdeferred.DonePipe;
import org.jdeferred.FailCallback;
import org.jdeferred.FailFilter;
import org.jdeferred.FailPipe;
import org.jdeferred.ProgressCallback;
import org.jdeferred.ProgressFilter;
import org.jdeferred.ProgressPipe;
import org.jdeferred.Promise;
import org.jdeferred.Promise.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPromise<D, F, P>
  implements Promise<D, F, P>
{
  protected final List<AlwaysCallback<D, F>> alwaysCallbacks = new CopyOnWriteArrayList();
  protected final List<DoneCallback<D>> doneCallbacks = new CopyOnWriteArrayList();
  protected final List<FailCallback<F>> failCallbacks = new CopyOnWriteArrayList();
  protected final Logger log = LoggerFactory.getLogger(AbstractPromise.class);
  protected final List<ProgressCallback<P>> progressCallbacks = new CopyOnWriteArrayList();
  protected F rejectResult;
  protected D resolveResult;
  protected volatile Promise.State state = Promise.State.PENDING;

  public Promise<D, F, P> always(AlwaysCallback<D, F> paramAlwaysCallback)
  {
    try
    {
      if (isPending())
        this.alwaysCallbacks.add(paramAlwaysCallback);
      else
        triggerAlways(paramAlwaysCallback, this.state, this.resolveResult, this.rejectResult);
      return this;
    }
    finally
    {
    }
    throw paramAlwaysCallback;
  }

  public Promise<D, F, P> done(DoneCallback<D> paramDoneCallback)
  {
    try
    {
      if (isResolved())
        triggerDone(paramDoneCallback, this.resolveResult);
      else
        this.doneCallbacks.add(paramDoneCallback);
      return this;
    }
    finally
    {
    }
    throw paramDoneCallback;
  }

  public Promise<D, F, P> fail(FailCallback<F> paramFailCallback)
  {
    try
    {
      if (isRejected())
        triggerFail(paramFailCallback, this.rejectResult);
      else
        this.failCallbacks.add(paramFailCallback);
      return this;
    }
    finally
    {
    }
    throw paramFailCallback;
  }

  public boolean isPending()
  {
    return this.state == Promise.State.PENDING;
  }

  public boolean isRejected()
  {
    return this.state == Promise.State.REJECTED;
  }

  public boolean isResolved()
  {
    return this.state == Promise.State.RESOLVED;
  }

  public Promise<D, F, P> progress(ProgressCallback<P> paramProgressCallback)
  {
    this.progressCallbacks.add(paramProgressCallback);
    return this;
  }

  public Promise.State state()
  {
    return this.state;
  }

  public Promise<D, F, P> then(DoneCallback<D> paramDoneCallback)
  {
    return done(paramDoneCallback);
  }

  public Promise<D, F, P> then(DoneCallback<D> paramDoneCallback, FailCallback<F> paramFailCallback)
  {
    done(paramDoneCallback);
    fail(paramFailCallback);
    return this;
  }

  public Promise<D, F, P> then(DoneCallback<D> paramDoneCallback, FailCallback<F> paramFailCallback, ProgressCallback<P> paramProgressCallback)
  {
    done(paramDoneCallback);
    fail(paramFailCallback);
    progress(paramProgressCallback);
    return this;
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter)
  {
    return new FilteredPromise(this, paramDoneFilter, null, null);
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter, FailFilter<F, F_OUT> paramFailFilter)
  {
    return new FilteredPromise(this, paramDoneFilter, paramFailFilter, null);
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter, FailFilter<F, F_OUT> paramFailFilter, ProgressFilter<P, P_OUT> paramProgressFilter)
  {
    return new FilteredPromise(this, paramDoneFilter, paramFailFilter, paramProgressFilter);
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe)
  {
    return new PipedPromise(this, paramDonePipe, null, null);
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> paramFailPipe)
  {
    return new PipedPromise(this, paramDonePipe, paramFailPipe, null);
  }

  public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> paramFailPipe, ProgressPipe<P, D_OUT, F_OUT, P_OUT> paramProgressPipe)
  {
    return new PipedPromise(this, paramDonePipe, paramFailPipe, paramProgressPipe);
  }

  protected void triggerAlways(AlwaysCallback<D, F> paramAlwaysCallback, Promise.State paramState, D paramD, F paramF)
  {
    paramAlwaysCallback.onAlways(paramState, paramD, paramF);
  }

  protected void triggerAlways(Promise.State paramState, D paramD, F paramF)
  {
    Iterator localIterator = this.alwaysCallbacks.iterator();
    while (localIterator.hasNext())
    {
      AlwaysCallback localAlwaysCallback = (AlwaysCallback)localIterator.next();
      try
      {
        triggerAlways(localAlwaysCallback, paramState, paramD, paramF);
      }
      catch (Exception localException)
      {
        this.log.error("an uncaught exception occured in a AlwaysCallback", localException);
      }
    }
    this.alwaysCallbacks.clear();
    try
    {
      notifyAll();
      return;
    }
    finally
    {
    }
    throw paramState;
  }

  protected void triggerDone(D paramD)
  {
    Iterator localIterator = this.doneCallbacks.iterator();
    while (localIterator.hasNext())
    {
      DoneCallback localDoneCallback = (DoneCallback)localIterator.next();
      try
      {
        triggerDone(localDoneCallback, paramD);
      }
      catch (Exception localException)
      {
        this.log.error("an uncaught exception occured in a DoneCallback", localException);
      }
    }
    this.doneCallbacks.clear();
  }

  protected void triggerDone(DoneCallback<D> paramDoneCallback, D paramD)
  {
    paramDoneCallback.onDone(paramD);
  }

  protected void triggerFail(F paramF)
  {
    Iterator localIterator = this.failCallbacks.iterator();
    while (localIterator.hasNext())
    {
      FailCallback localFailCallback = (FailCallback)localIterator.next();
      try
      {
        triggerFail(localFailCallback, paramF);
      }
      catch (Exception localException)
      {
        this.log.error("an uncaught exception occured in a FailCallback", localException);
      }
    }
    this.failCallbacks.clear();
  }

  protected void triggerFail(FailCallback<F> paramFailCallback, F paramF)
  {
    paramFailCallback.onFail(paramF);
  }

  protected void triggerProgress(P paramP)
  {
    Iterator localIterator = this.progressCallbacks.iterator();
    while (localIterator.hasNext())
    {
      ProgressCallback localProgressCallback = (ProgressCallback)localIterator.next();
      try
      {
        triggerProgress(localProgressCallback, paramP);
      }
      catch (Exception localException)
      {
        this.log.error("an uncaught exception occured in a ProgressCallback", localException);
      }
    }
  }

  protected void triggerProgress(ProgressCallback<P> paramProgressCallback, P paramP)
  {
    paramProgressCallback.onProgress(paramP);
  }

  public void waitSafely()
    throws InterruptedException
  {
    waitSafely(-1L);
  }

  // ERROR //
  public void waitSafely(long paramLong)
    throws InterruptedException
  {
    // Byte code:
    //   0: invokestatic 225	java/lang/System:currentTimeMillis	()J
    //   3: lstore 4
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: invokevirtual 62	org/jdeferred/impl/AbstractPromise:isPending	()Z
    //   11: istore 6
    //   13: iload 6
    //   15: ifeq +57 -> 72
    //   18: lload_1
    //   19: lconst_0
    //   20: lcmp
    //   21: istore_3
    //   22: iload_3
    //   23: ifgt +10 -> 33
    //   26: aload_0
    //   27: invokevirtual 228	java/lang/Object:wait	()V
    //   30: goto +15 -> 45
    //   33: aload_0
    //   34: lload_1
    //   35: invokestatic 225	java/lang/System:currentTimeMillis	()J
    //   38: lload 4
    //   40: lsub
    //   41: lsub
    //   42: invokevirtual 230	java/lang/Object:wait	(J)V
    //   45: iload_3
    //   46: ifle -39 -> 7
    //   49: invokestatic 225	java/lang/System:currentTimeMillis	()J
    //   52: lload 4
    //   54: lsub
    //   55: lload_1
    //   56: lcmp
    //   57: iflt -50 -> 7
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: invokestatic 236	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   66: invokevirtual 239	java/lang/Thread:interrupt	()V
    //   69: aload 7
    //   71: athrow
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    //   75: astore 7
    //   77: aload_0
    //   78: monitorexit
    //   79: aload 7
    //   81: athrow
    //   82: astore 7
    //   84: goto -21 -> 63
    //
    // Exception table:
    //   from	to	target	type
    //   7	13	75	finally
    //   26	30	75	finally
    //   33	45	75	finally
    //   49	62	75	finally
    //   63	72	75	finally
    //   72	74	75	finally
    //   77	79	75	finally
    //   26	30	82	java/lang/InterruptedException
    //   33	45	82	java/lang/InterruptedException
  }
}