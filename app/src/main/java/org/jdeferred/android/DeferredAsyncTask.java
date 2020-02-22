package org.jdeferred.android;

import android.os.AsyncTask;
import java.util.concurrent.CancellationException;
import org.jdeferred.DeferredManager.StartPolicy;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DeferredAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>
{
  private final DeferredObject<Result, Throwable, Progress> deferred = new DeferredObject();
  protected final Logger log = LoggerFactory.getLogger(DeferredAsyncTask.class);
  private final DeferredManager.StartPolicy startPolicy;
  private Throwable throwable;

  public DeferredAsyncTask()
  {
    this.startPolicy = DeferredManager.StartPolicy.DEFAULT;
  }

  public DeferredAsyncTask(DeferredManager.StartPolicy paramStartPolicy)
  {
    this.startPolicy = paramStartPolicy;
  }

  protected final Result doInBackground(Params[] paramArrayOfParams)
  {
    try
    {
      paramArrayOfParams = doInBackgroundSafe(paramArrayOfParams);
      return paramArrayOfParams;
    }
    catch (Throwable paramArrayOfParams)
    {
      this.throwable = paramArrayOfParams;
    }
    return null;
  }

  protected abstract Result doInBackgroundSafe(Params[] paramArrayOfParams)
    throws Exception;

  public DeferredManager.StartPolicy getStartPolicy()
  {
    return this.startPolicy;
  }

  protected final void notify(Progress paramProgress)
  {
    publishProgress(new Object[] { paramProgress });
  }

  protected final void onCancelled()
  {
    this.deferred.reject(new CancellationException());
  }

  protected final void onCancelled(Result paramResult)
  {
    this.deferred.reject(new CancellationException());
  }

  protected final void onPostExecute(Result paramResult)
  {
    if (this.throwable != null)
    {
      this.deferred.reject(this.throwable);
      return;
    }
    this.deferred.resolve(paramResult);
  }

  protected final void onProgressUpdate(Progress[] paramArrayOfProgress)
  {
    if ((paramArrayOfProgress != null) && (paramArrayOfProgress.length != 0))
    {
      if (paramArrayOfProgress.length > 0)
      {
        this.log.warn("There were multiple progress values.  Only the first one was used!");
        this.deferred.notify(paramArrayOfProgress[0]);
      }
    }
    else
      this.deferred.notify(null);
  }

  public Promise<Result, Throwable, Progress> promise()
  {
    return this.deferred.promise();
  }
}