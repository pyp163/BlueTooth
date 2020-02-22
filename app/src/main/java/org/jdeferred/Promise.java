package org.jdeferred;

public abstract interface Promise<D, F, P>
{
  public abstract Promise<D, F, P> always(AlwaysCallback<D, F> paramAlwaysCallback);

  public abstract Promise<D, F, P> done(DoneCallback<D> paramDoneCallback);

  public abstract Promise<D, F, P> fail(FailCallback<F> paramFailCallback);

  public abstract boolean isPending();

  public abstract boolean isRejected();

  public abstract boolean isResolved();

  public abstract Promise<D, F, P> progress(ProgressCallback<P> paramProgressCallback);

  public abstract State state();

  public abstract Promise<D, F, P> then(DoneCallback<D> paramDoneCallback);

  public abstract Promise<D, F, P> then(DoneCallback<D> paramDoneCallback, FailCallback<F> paramFailCallback);

  public abstract Promise<D, F, P> then(DoneCallback<D> paramDoneCallback, FailCallback<F> paramFailCallback, ProgressCallback<P> paramProgressCallback);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter, FailFilter<F, F_OUT> paramFailFilter);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> paramDoneFilter, FailFilter<F, F_OUT> paramFailFilter, ProgressFilter<P, P_OUT> paramProgressFilter);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> paramFailPipe);

  public abstract <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> paramFailPipe, ProgressPipe<P, D_OUT, F_OUT, P_OUT> paramProgressPipe);

  public abstract void waitSafely()
    throws InterruptedException;

  public abstract void waitSafely(long paramLong)
    throws InterruptedException;

  public static enum State
  {
    PENDING, REJECTED, RESOLVED;
  }
}