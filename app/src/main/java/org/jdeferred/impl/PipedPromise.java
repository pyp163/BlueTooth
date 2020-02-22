package org.jdeferred.impl;

import org.jdeferred.DoneCallback;
import org.jdeferred.DonePipe;
import org.jdeferred.FailCallback;
import org.jdeferred.FailPipe;
import org.jdeferred.ProgressCallback;
import org.jdeferred.ProgressPipe;
import org.jdeferred.Promise;

public class PipedPromise<D, F, P, D_OUT, F_OUT, P_OUT> extends DeferredObject<D_OUT, F_OUT, P_OUT>
  implements Promise<D_OUT, F_OUT, P_OUT>
{
  public PipedPromise(Promise<D, F, P> paramPromise, final DonePipe<D, D_OUT, F_OUT, P_OUT> paramDonePipe, final FailPipe<F, D_OUT, F_OUT, P_OUT> paramFailPipe, final ProgressPipe<P, D_OUT, F_OUT, P_OUT> paramProgressPipe)
  {
    paramPromise.done(new DoneCallback()
    {
      public void onDone(D paramAnonymousD)
      {
        if (paramDonePipe != null)
        {
          PipedPromise.this.pipe(paramDonePipe.pipeDone(paramAnonymousD));
          return;
        }
        PipedPromise.this.resolve(paramAnonymousD);
      }
    }).fail(new FailCallback()
    {
      public void onFail(F paramAnonymousF)
      {
        if (paramFailPipe != null)
        {
          PipedPromise.this.pipe(paramFailPipe.pipeFail(paramAnonymousF));
          return;
        }
        PipedPromise.this.reject(paramAnonymousF);
      }
    }).progress(new ProgressCallback()
    {
      public void onProgress(P paramAnonymousP)
      {
        if (paramProgressPipe != null)
        {
          PipedPromise.this.pipe(paramProgressPipe.pipeProgress(paramAnonymousP));
          return;
        }
        PipedPromise.this.notify(paramAnonymousP);
      }
    });
  }

  protected Promise<D_OUT, F_OUT, P_OUT> pipe(Promise<D_OUT, F_OUT, P_OUT> paramPromise)
  {
    paramPromise.done(new DoneCallback()
    {
      public void onDone(D_OUT paramAnonymousD_OUT)
      {
        PipedPromise.this.resolve(paramAnonymousD_OUT);
      }
    }).fail(new FailCallback()
    {
      public void onFail(F_OUT paramAnonymousF_OUT)
      {
        PipedPromise.this.reject(paramAnonymousF_OUT);
      }
    }).progress(new ProgressCallback()
    {
      public void onProgress(P_OUT paramAnonymousP_OUT)
      {
        PipedPromise.this.notify(paramAnonymousP_OUT);
      }
    });
    return paramPromise;
  }
}