package org.jdeferred.android;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import java.util.concurrent.ExecutorService;
import org.jdeferred.DeferredFutureTask;
import org.jdeferred.DeferredManager.StartPolicy;
import org.jdeferred.Promise;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.multiple.MasterProgress;
import org.jdeferred.multiple.MultipleResults;
import org.jdeferred.multiple.OneReject;

public class AndroidDeferredManager extends DefaultDeferredManager
{
  private static Void[] EMPTY_PARAMS = new Void[0];

  public AndroidDeferredManager()
  {
  }

  public AndroidDeferredManager(ExecutorService paramExecutorService)
  {
    super(paramExecutorService);
  }

  public <D, P> Promise<D, Throwable, P> when(DeferredFutureTask<D, P> paramDeferredFutureTask)
  {
    return new AndroidDeferredObject(super.when(paramDeferredFutureTask)).promise();
  }

  public <D, F, P> Promise<D, F, P> when(Promise<D, F, P> paramPromise)
  {
    if ((paramPromise instanceof AndroidDeferredObject))
      return paramPromise;
    return new AndroidDeferredObject(paramPromise).promise();
  }

  public <D, F, P> Promise<D, F, P> when(Promise<D, F, P> paramPromise, AndroidExecutionScope paramAndroidExecutionScope)
  {
    if ((paramPromise instanceof AndroidDeferredObject))
      return paramPromise;
    return new AndroidDeferredObject(paramPromise, paramAndroidExecutionScope).promise();
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(AndroidExecutionScope paramAndroidExecutionScope, Promise[] paramArrayOfPromise)
  {
    return new AndroidDeferredObject(super.when(paramArrayOfPromise), paramAndroidExecutionScope).promise();
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(AndroidExecutionScope paramAndroidExecutionScope, DeferredAsyncTask<Void, ?, ?>[] paramArrayOfDeferredAsyncTask)
  {
    assertNotEmpty(paramArrayOfDeferredAsyncTask);
    Promise[] arrayOfPromise = new Promise[paramArrayOfDeferredAsyncTask.length];
    int i = 0;
    while (i < paramArrayOfDeferredAsyncTask.length)
    {
      arrayOfPromise[i] = when(paramArrayOfDeferredAsyncTask[i]);
      i += 1;
    }
    return when(paramAndroidExecutionScope, arrayOfPromise);
  }

  @SuppressLint({"NewApi"})
  public <Progress, Result> Promise<Result, Throwable, Progress> when(DeferredAsyncTask<Void, Progress, Result> paramDeferredAsyncTask)
  {
    if ((paramDeferredAsyncTask.getStartPolicy() == DeferredManager.StartPolicy.AUTO) || ((paramDeferredAsyncTask.getStartPolicy() == DeferredManager.StartPolicy.DEFAULT) && (isAutoSubmit())))
      if (Build.VERSION.SDK_INT >= 11)
        paramDeferredAsyncTask.executeOnExecutor(getExecutorService(), EMPTY_PARAMS);
      else
        paramDeferredAsyncTask.execute(EMPTY_PARAMS);
    return paramDeferredAsyncTask.promise();
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(Promise[] paramArrayOfPromise)
  {
    return new AndroidDeferredObject(super.when(paramArrayOfPromise)).promise();
  }

  public Promise<MultipleResults, OneReject, MasterProgress> when(DeferredAsyncTask<Void, ?, ?>[] paramArrayOfDeferredAsyncTask)
  {
    assertNotEmpty(paramArrayOfDeferredAsyncTask);
    Promise[] arrayOfPromise = new Promise[paramArrayOfDeferredAsyncTask.length];
    int i = 0;
    while (i < paramArrayOfDeferredAsyncTask.length)
    {
      arrayOfPromise[i] = when(paramArrayOfDeferredAsyncTask[i]);
      i += 1;
    }
    return when(arrayOfPromise);
  }
}