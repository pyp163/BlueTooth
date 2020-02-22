package org.jdeferred.impl;

import org.jdeferred.DoneCallback;
import org.jdeferred.DoneFilter;
import org.jdeferred.FailCallback;
import org.jdeferred.FailFilter;
import org.jdeferred.ProgressCallback;
import org.jdeferred.ProgressFilter;
import org.jdeferred.Promise;

public class FilteredPromise<D, F, P, D_OUT, F_OUT, P_OUT> extends DeferredObject<D_OUT, F_OUT, P_OUT>
  implements Promise<D_OUT, F_OUT, P_OUT>
{
  protected static final NoOpDoneFilter NO_OP_DONE_FILTER = new NoOpDoneFilter();
  protected static final NoOpFailFilter NO_OP_FAIL_FILTER = new NoOpFailFilter();
  protected static final NoOpProgressFilter NO_OP_PROGRESS_FILTER = new NoOpProgressFilter();
  private final DoneFilter<D, D_OUT> doneFilter;
  private final FailFilter<F, F_OUT> failFilter;
  private final ProgressFilter<P, P_OUT> progressFilter;

  public FilteredPromise(Promise<D, F, P> paramPromise, DoneFilter<D, D_OUT> paramDoneFilter, FailFilter<F, F_OUT> paramFailFilter, ProgressFilter<P, P_OUT> paramProgressFilter)
  {
    Object localObject = paramDoneFilter;
    if (paramDoneFilter == null)
      localObject = NO_OP_DONE_FILTER;
    this.doneFilter = ((DoneFilter)localObject);
    paramDoneFilter = paramFailFilter;
    if (paramFailFilter == null)
      paramDoneFilter = NO_OP_FAIL_FILTER;
    this.failFilter = paramDoneFilter;
    paramDoneFilter = paramProgressFilter;
    if (paramProgressFilter == null)
      paramDoneFilter = NO_OP_PROGRESS_FILTER;
    this.progressFilter = paramDoneFilter;
    paramPromise.done(new DoneCallback()
    {
      public void onDone(D paramAnonymousD)
      {
        FilteredPromise.this.resolve(FilteredPromise.this.doneFilter.filterDone(paramAnonymousD));
      }
    }).fail(new FailCallback()
    {
      public void onFail(F paramAnonymousF)
      {
        FilteredPromise.this.reject(FilteredPromise.this.failFilter.filterFail(paramAnonymousF));
      }
    }).progress(new ProgressCallback()
    {
      public void onProgress(P paramAnonymousP)
      {
        FilteredPromise.this.notify(FilteredPromise.this.progressFilter.filterProgress(paramAnonymousP));
      }
    });
  }

  public static final class NoOpDoneFilter<D>
    implements DoneFilter<D, D>
  {
    public D filterDone(D paramD)
    {
      return paramD;
    }
  }

  public static final class NoOpFailFilter<F>
    implements FailFilter<F, F>
  {
    public F filterFail(F paramF)
    {
      return paramF;
    }
  }

  public static final class NoOpProgressFilter<P>
    implements ProgressFilter<P, P>
  {
    public P filterProgress(P paramP)
    {
      return paramP;
    }
  }
}