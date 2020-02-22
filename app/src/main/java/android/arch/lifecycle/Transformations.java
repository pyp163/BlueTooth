package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Transformations
{
  @MainThread
  public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> paramLiveData, @NonNull final Function<X, Y> paramFunction)
  {
    MediatorLiveData localMediatorLiveData = new MediatorLiveData();
    localMediatorLiveData.addSource(paramLiveData, new Observer()
    {
      public void onChanged(@Nullable X paramAnonymousX)
      {
        this.val$result.setValue(paramFunction.apply(paramAnonymousX));
      }
    });
    return localMediatorLiveData;
  }

  @MainThread
  public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> paramLiveData, @NonNull Function<X, LiveData<Y>> paramFunction)
  {
    final MediatorLiveData localMediatorLiveData = new MediatorLiveData();
    localMediatorLiveData.addSource(paramLiveData, new Observer()
    {
      LiveData<Y> mSource;

      public void onChanged(@Nullable X paramAnonymousX)
      {
        paramAnonymousX = (LiveData)this.val$func.apply(paramAnonymousX);
        if (this.mSource == paramAnonymousX)
          return;
        if (this.mSource != null)
          localMediatorLiveData.removeSource(this.mSource);
        this.mSource = paramAnonymousX;
        if (this.mSource != null)
          localMediatorLiveData.addSource(this.mSource, new Observer()
          {
            public void onChanged(@Nullable Y paramAnonymous2Y)
            {
              Transformations.2.this.val$result.setValue(paramAnonymous2Y);
            }
          });
      }
    });
    return localMediatorLiveData;
  }
}