package org.jdeferred.multiple;

import java.util.concurrent.atomic.AtomicInteger;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.ProgressCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

public class MasterDeferredObject extends DeferredObject<MultipleResults, OneReject, MasterProgress>
  implements Promise<MultipleResults, OneReject, MasterProgress>
{
  private final AtomicInteger doneCount = new AtomicInteger();
  private final AtomicInteger failCount = new AtomicInteger();
  private final int numberOfPromises;
  private final MultipleResults results;

  public MasterDeferredObject(Promise[] paramArrayOfPromise)
  {
    if ((paramArrayOfPromise != null) && (paramArrayOfPromise.length != 0))
    {
      this.numberOfPromises = paramArrayOfPromise.length;
      this.results = new MultipleResults(this.numberOfPromises);
      int k = paramArrayOfPromise.length;
      int j = 0;
      final int i = 0;
      while (j < k)
      {
        final Promise localPromise = paramArrayOfPromise[j];
        localPromise.fail(new FailCallback()
        {
          public void onFail(Object paramAnonymousObject)
          {
            synchronized (MasterDeferredObject.this)
            {
              if (!MasterDeferredObject.this.isPending())
                return;
              int i = MasterDeferredObject.this.failCount.incrementAndGet();
              MasterDeferredObject.this.notify(new MasterProgress(MasterDeferredObject.this.doneCount.get(), i, MasterDeferredObject.this.numberOfPromises));
              MasterDeferredObject.this.reject(new OneReject(i, localPromise, paramAnonymousObject));
              return;
            }
          }
        }).progress(new ProgressCallback()
        {
          public void onProgress(Object paramAnonymousObject)
          {
            synchronized (MasterDeferredObject.this)
            {
              if (!MasterDeferredObject.this.isPending())
                return;
              MasterDeferredObject.this.notify(new OneProgress(MasterDeferredObject.this.doneCount.get(), MasterDeferredObject.this.failCount.get(), MasterDeferredObject.this.numberOfPromises, i, localPromise, paramAnonymousObject));
              return;
            }
          }
        }).done(new DoneCallback()
        {
          public void onDone(Object paramAnonymousObject)
          {
            synchronized (MasterDeferredObject.this)
            {
              if (!MasterDeferredObject.this.isPending())
                return;
              MasterDeferredObject.this.results.set(i, new OneResult(i, localPromise, paramAnonymousObject));
              int i = MasterDeferredObject.this.doneCount.incrementAndGet();
              MasterDeferredObject.this.notify(new MasterProgress(i, MasterDeferredObject.this.failCount.get(), MasterDeferredObject.this.numberOfPromises));
              if (i == MasterDeferredObject.this.numberOfPromises)
                MasterDeferredObject.this.resolve(MasterDeferredObject.this.results);
              return;
            }
          }
        });
        j += 1;
        i += 1;
      }
      return;
    }
    throw new IllegalArgumentException("Promises is null or empty");
  }
}