package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T>
{
  private AtomicBoolean mComputing = new AtomicBoolean(false);
  private final Executor mExecutor;
  private AtomicBoolean mInvalid = new AtomicBoolean(true);

  @VisibleForTesting
  final Runnable mInvalidationRunnable = new Runnable()
  {
    @MainThread
    public void run()
    {
      boolean bool = ComputableLiveData.this.mLiveData.hasActiveObservers();
      if ((ComputableLiveData.this.mInvalid.compareAndSet(false, true)) && (bool))
        ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
    }
  };
  private final LiveData<T> mLiveData;

  @VisibleForTesting
  final Runnable mRefreshRunnable = new Runnable()
  {
    @WorkerThread
    public void run()
    {
      int i;
      do
      {
        if (ComputableLiveData.this.mComputing.compareAndSet(false, true))
        {
          Object localObject1 = null;
          i = 0;
          try
          {
            while (ComputableLiveData.this.mInvalid.compareAndSet(true, false))
            {
              localObject1 = ComputableLiveData.this.compute();
              i = 1;
            }
            if (i != 0)
              ComputableLiveData.this.mLiveData.postValue(localObject1);
          }
          finally
          {
            ComputableLiveData.this.mComputing.set(false);
          }
        }
        i = 0;
      }
      while ((i != 0) && (ComputableLiveData.this.mInvalid.get()));
    }
  };

  public ComputableLiveData()
  {
    this(ArchTaskExecutor.getIOThreadExecutor());
  }

  public ComputableLiveData(@NonNull Executor paramExecutor)
  {
    this.mExecutor = paramExecutor;
    this.mLiveData = new LiveData()
    {
      protected void onActive()
      {
        ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
      }
    };
  }

  @WorkerThread
  protected abstract T compute();

  @NonNull
  public LiveData<T> getLiveData()
  {
    return this.mLiveData;
  }

  public void invalidate()
  {
    ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
  }
}