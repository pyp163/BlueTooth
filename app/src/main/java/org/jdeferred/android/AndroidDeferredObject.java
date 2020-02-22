package org.jdeferred.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.jdeferred.AlwaysCallback;
import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.ProgressCallback;
import org.jdeferred.Promise;
import org.jdeferred.Promise.State;
import org.jdeferred.impl.DeferredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidDeferredObject<D, F, P> extends DeferredObject<D, F, P>
{
  private static final int MESSAGE_POST_ALWAYS = 4;
  private static final int MESSAGE_POST_DONE = 1;
  private static final int MESSAGE_POST_FAIL = 3;
  private static final int MESSAGE_POST_PROGRESS = 2;
  private static final InternalHandler sHandler = new InternalHandler();
  private final AndroidExecutionScope defaultAndroidExecutionScope;
  protected final Logger log = LoggerFactory.getLogger(AndroidDeferredObject.class);

  public AndroidDeferredObject(Promise<D, F, P> paramPromise)
  {
    this(paramPromise, AndroidExecutionScope.UI);
  }

  public AndroidDeferredObject(Promise<D, F, P> paramPromise, AndroidExecutionScope paramAndroidExecutionScope)
  {
    this.defaultAndroidExecutionScope = paramAndroidExecutionScope;
    paramPromise.done(new DoneCallback()
    {
      public void onDone(D paramAnonymousD)
      {
        AndroidDeferredObject.this.resolve(paramAnonymousD);
      }
    }).progress(new ProgressCallback()
    {
      public void onProgress(P paramAnonymousP)
      {
        AndroidDeferredObject.this.notify(paramAnonymousP);
      }
    }).fail(new FailCallback()
    {
      public void onFail(F paramAnonymousF)
      {
        AndroidDeferredObject.this.reject(paramAnonymousF);
      }
    });
  }

  protected AndroidExecutionScope determineAndroidExecutionScope(Object paramObject)
  {
    if ((paramObject instanceof AndroidExecutionScopeable))
      paramObject = ((AndroidExecutionScopeable)paramObject).getExecutionScope();
    else
      paramObject = null;
    Object localObject = paramObject;
    if (paramObject == null)
      localObject = this.defaultAndroidExecutionScope;
    return localObject;
  }

  protected <Callback> void executeInUiThread(int paramInt, Callback paramCallback, Promise.State paramState, D paramD, F paramF, P paramP)
  {
    sHandler.obtainMessage(paramInt, new CallbackMessage(this, paramCallback, paramState, paramD, paramF, paramP)).sendToTarget();
  }

  protected void triggerAlways(AlwaysCallback<D, F> paramAlwaysCallback, Promise.State paramState, D paramD, F paramF)
  {
    if (determineAndroidExecutionScope(paramAlwaysCallback) == AndroidExecutionScope.UI)
    {
      executeInUiThread(4, paramAlwaysCallback, paramState, paramD, paramF, null);
      return;
    }
    super.triggerAlways(paramAlwaysCallback, paramState, paramD, paramF);
  }

  protected void triggerDone(DoneCallback<D> paramDoneCallback, D paramD)
  {
    if (determineAndroidExecutionScope(paramDoneCallback) == AndroidExecutionScope.UI)
    {
      executeInUiThread(1, paramDoneCallback, Promise.State.RESOLVED, paramD, null, null);
      return;
    }
    super.triggerDone(paramDoneCallback, paramD);
  }

  protected void triggerFail(FailCallback<F> paramFailCallback, F paramF)
  {
    if (determineAndroidExecutionScope(paramFailCallback) == AndroidExecutionScope.UI)
    {
      executeInUiThread(3, paramFailCallback, Promise.State.REJECTED, null, paramF, null);
      return;
    }
    super.triggerFail(paramFailCallback, paramF);
  }

  protected void triggerProgress(ProgressCallback<P> paramProgressCallback, P paramP)
  {
    if (determineAndroidExecutionScope(paramProgressCallback) == AndroidExecutionScope.UI)
    {
      executeInUiThread(2, paramProgressCallback, Promise.State.PENDING, null, null, paramP);
      return;
    }
    super.triggerProgress(paramProgressCallback, paramP);
  }

  private static class CallbackMessage<Callback, D, F, P>
  {
    final Callback callback;
    final Deferred deferred;
    final P progress;
    final F rejected;
    final D resolved;
    final Promise.State state;

    CallbackMessage(Deferred paramDeferred, Callback paramCallback, Promise.State paramState, D paramD, F paramF, P paramP)
    {
      this.deferred = paramDeferred;
      this.callback = paramCallback;
      this.state = paramState;
      this.resolved = paramD;
      this.rejected = paramF;
      this.progress = paramP;
    }
  }

  private static class InternalHandler extends Handler
  {
    public InternalHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      AndroidDeferredObject.CallbackMessage localCallbackMessage = (AndroidDeferredObject.CallbackMessage)paramMessage.obj;
      switch (paramMessage.what)
      {
      default:
        return;
      case 4:
        ((AlwaysCallback)localCallbackMessage.callback).onAlways(localCallbackMessage.state, localCallbackMessage.resolved, localCallbackMessage.rejected);
        return;
      case 3:
        ((FailCallback)localCallbackMessage.callback).onFail(localCallbackMessage.rejected);
        return;
      case 2:
        ((ProgressCallback)localCallbackMessage.callback).onProgress(localCallbackMessage.progress);
        return;
      case 1:
      }
      ((DoneCallback)localCallbackMessage.callback).onDone(localCallbackMessage.resolved);
    }
  }
}