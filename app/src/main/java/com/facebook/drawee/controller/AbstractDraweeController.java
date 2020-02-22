package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Objects.ToStringHelper;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DeferredReleaser.Releasable;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.gestures.GestureDetector.ClickListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AbstractDraweeController<T, INFO>
  implements DraweeController, DeferredReleaser.Releasable, GestureDetector.ClickListener
{
  private static final Class<?> TAG = AbstractDraweeController.class;
  private Object mCallerContext;

  @Nullable
  private String mContentDescription;

  @Nullable
  private ControllerListener<INFO> mControllerListener;

  @Nullable
  private Drawable mControllerOverlay;

  @Nullable
  private ControllerViewportVisibilityListener mControllerViewportVisibilityListener;

  @Nullable
  private DataSource<T> mDataSource;
  private final DeferredReleaser mDeferredReleaser;

  @Nullable
  private Drawable mDrawable;
  private final DraweeEventTracker mEventTracker = DraweeEventTracker.newInstance();

  @Nullable
  private T mFetchedImage;

  @Nullable
  private GestureDetector mGestureDetector;
  private boolean mHasFetchFailed;
  private String mId;
  private boolean mIsAttached;
  private boolean mIsRequestSubmitted;
  private boolean mIsVisibleInViewportHint;
  private boolean mJustConstructed = true;
  private boolean mRetainImageOnFailure;

  @Nullable
  private RetryManager mRetryManager;

  @Nullable
  private SettableDraweeHierarchy mSettableDraweeHierarchy;
  private final Executor mUiThreadImmediateExecutor;

  public AbstractDraweeController(DeferredReleaser paramDeferredReleaser, Executor paramExecutor, String paramString, Object paramObject)
  {
    this.mDeferredReleaser = paramDeferredReleaser;
    this.mUiThreadImmediateExecutor = paramExecutor;
    init(paramString, paramObject);
  }

  private void init(String paramString, Object paramObject)
  {
    try
    {
      this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_INIT_CONTROLLER);
      if ((!this.mJustConstructed) && (this.mDeferredReleaser != null))
        this.mDeferredReleaser.cancelDeferredRelease(this);
      this.mIsAttached = false;
      this.mIsVisibleInViewportHint = false;
      releaseFetch();
      this.mRetainImageOnFailure = false;
      if (this.mRetryManager != null)
        this.mRetryManager.init();
      if (this.mGestureDetector != null)
      {
        this.mGestureDetector.init();
        this.mGestureDetector.setClickListener(this);
      }
      if ((this.mControllerListener instanceof InternalForwardingListener))
        ((InternalForwardingListener)this.mControllerListener).clearListeners();
      else
        this.mControllerListener = null;
      this.mControllerViewportVisibilityListener = null;
      if (this.mSettableDraweeHierarchy != null)
      {
        this.mSettableDraweeHierarchy.reset();
        this.mSettableDraweeHierarchy.setControllerOverlay(null);
        this.mSettableDraweeHierarchy = null;
      }
      this.mControllerOverlay = null;
      if (FLog.isLoggable(2))
        FLog.v(TAG, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, paramString);
      this.mId = paramString;
      this.mCallerContext = paramObject;
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  private boolean isExpectedDataSource(String paramString, DataSource<T> paramDataSource)
  {
    if ((paramDataSource == null) && (this.mDataSource == null))
      return true;
    return (paramString.equals(this.mId)) && (paramDataSource == this.mDataSource) && (this.mIsRequestSubmitted);
  }

  private void logMessageAndFailure(String paramString, Throwable paramThrowable)
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, paramString, paramThrowable);
  }

  private void logMessageAndImage(String paramString, T paramT)
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: %s: image: %s %x", new Object[] { Integer.valueOf(System.identityHashCode(this)), this.mId, paramString, getImageClass(paramT), Integer.valueOf(getImageHash(paramT)) });
  }

  private void onFailureInternal(String paramString, DataSource<T> paramDataSource, Throwable paramThrowable, boolean paramBoolean)
  {
    if (!isExpectedDataSource(paramString, paramDataSource))
    {
      logMessageAndFailure("ignore_old_datasource @ onFailure", paramThrowable);
      paramDataSource.close();
      return;
    }
    paramDataSource = this.mEventTracker;
    if (paramBoolean)
      paramString = DraweeEventTracker.Event.ON_DATASOURCE_FAILURE;
    else
      paramString = DraweeEventTracker.Event.ON_DATASOURCE_FAILURE_INT;
    paramDataSource.recordEvent(paramString);
    if (paramBoolean)
    {
      logMessageAndFailure("final_failed @ onFailure", paramThrowable);
      this.mDataSource = null;
      this.mHasFetchFailed = true;
      if ((this.mRetainImageOnFailure) && (this.mDrawable != null))
        this.mSettableDraweeHierarchy.setImage(this.mDrawable, 1.0F, true);
      else if (shouldRetryOnTap())
        this.mSettableDraweeHierarchy.setRetry(paramThrowable);
      else
        this.mSettableDraweeHierarchy.setFailure(paramThrowable);
      getControllerListener().onFailure(this.mId, paramThrowable);
      return;
    }
    logMessageAndFailure("intermediate_failed @ onFailure", paramThrowable);
    getControllerListener().onIntermediateImageFailed(this.mId, paramThrowable);
  }

  // ERROR //
  private void onNewResultInternal(String paramString, DataSource<T> paramDataSource, @Nullable T paramT, float paramFloat, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial 228	com/facebook/drawee/controller/AbstractDraweeController:isExpectedDataSource	(Ljava/lang/String;Lcom/facebook/datasource/DataSource;)Z
    //   6: ifne +24 -> 30
    //   9: aload_0
    //   10: ldc_w 283
    //   13: aload_3
    //   14: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   17: aload_0
    //   18: aload_3
    //   19: invokevirtual 289	com/facebook/drawee/controller/AbstractDraweeController:releaseImage	(Ljava/lang/Object;)V
    //   22: aload_2
    //   23: invokeinterface 238 1 0
    //   28: pop
    //   29: return
    //   30: aload_0
    //   31: getfield 75	com/facebook/drawee/controller/AbstractDraweeController:mEventTracker	Lcom/facebook/drawee/components/DraweeEventTracker;
    //   34: astore 8
    //   36: iload 5
    //   38: ifeq +11 -> 49
    //   41: getstatic 292	com/facebook/drawee/components/DraweeEventTracker$Event:ON_DATASOURCE_RESULT	Lcom/facebook/drawee/components/DraweeEventTracker$Event;
    //   44: astore 7
    //   46: goto +8 -> 54
    //   49: getstatic 295	com/facebook/drawee/components/DraweeEventTracker$Event:ON_DATASOURCE_RESULT_INT	Lcom/facebook/drawee/components/DraweeEventTracker$Event;
    //   52: astore 7
    //   54: aload 8
    //   56: aload 7
    //   58: invokevirtual 113	com/facebook/drawee/components/DraweeEventTracker:recordEvent	(Lcom/facebook/drawee/components/DraweeEventTracker$Event;)V
    //   61: aload_0
    //   62: aload_3
    //   63: invokevirtual 299	com/facebook/drawee/controller/AbstractDraweeController:createDrawable	(Ljava/lang/Object;)Landroid/graphics/drawable/Drawable;
    //   66: astore 7
    //   68: aload_0
    //   69: getfield 301	com/facebook/drawee/controller/AbstractDraweeController:mFetchedImage	Ljava/lang/Object;
    //   72: astore_2
    //   73: aload_0
    //   74: getfield 250	com/facebook/drawee/controller/AbstractDraweeController:mDrawable	Landroid/graphics/drawable/Drawable;
    //   77: astore 8
    //   79: aload_0
    //   80: aload_3
    //   81: putfield 301	com/facebook/drawee/controller/AbstractDraweeController:mFetchedImage	Ljava/lang/Object;
    //   84: aload_0
    //   85: aload 7
    //   87: putfield 250	com/facebook/drawee/controller/AbstractDraweeController:mDrawable	Landroid/graphics/drawable/Drawable;
    //   90: iload 5
    //   92: ifeq +52 -> 144
    //   95: aload_0
    //   96: ldc_w 303
    //   99: aload_3
    //   100: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   103: aload_0
    //   104: aconst_null
    //   105: putfield 195	com/facebook/drawee/controller/AbstractDraweeController:mDataSource	Lcom/facebook/datasource/DataSource;
    //   108: aload_0
    //   109: getfield 152	com/facebook/drawee/controller/AbstractDraweeController:mSettableDraweeHierarchy	Lcom/facebook/drawee/interfaces/SettableDraweeHierarchy;
    //   112: aload 7
    //   114: fconst_1
    //   115: iload 6
    //   117: invokeinterface 254 4 0
    //   122: aload_0
    //   123: invokevirtual 268	com/facebook/drawee/controller/AbstractDraweeController:getControllerListener	()Lcom/facebook/drawee/controller/ControllerListener;
    //   126: aload_1
    //   127: aload_0
    //   128: aload_3
    //   129: invokevirtual 307	com/facebook/drawee/controller/AbstractDraweeController:getImageInfo	(Ljava/lang/Object;)Ljava/lang/Object;
    //   132: aload_0
    //   133: invokevirtual 311	com/facebook/drawee/controller/AbstractDraweeController:getAnimatable	()Landroid/graphics/drawable/Animatable;
    //   136: invokeinterface 315 4 0
    //   141: goto +41 -> 182
    //   144: aload_0
    //   145: ldc_w 317
    //   148: aload_3
    //   149: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   152: aload_0
    //   153: getfield 152	com/facebook/drawee/controller/AbstractDraweeController:mSettableDraweeHierarchy	Lcom/facebook/drawee/interfaces/SettableDraweeHierarchy;
    //   156: aload 7
    //   158: fload 4
    //   160: iload 6
    //   162: invokeinterface 254 4 0
    //   167: aload_0
    //   168: invokevirtual 268	com/facebook/drawee/controller/AbstractDraweeController:getControllerListener	()Lcom/facebook/drawee/controller/ControllerListener;
    //   171: aload_1
    //   172: aload_0
    //   173: aload_3
    //   174: invokevirtual 307	com/facebook/drawee/controller/AbstractDraweeController:getImageInfo	(Ljava/lang/Object;)Ljava/lang/Object;
    //   177: invokeinterface 320 3 0
    //   182: aload 8
    //   184: ifnull +16 -> 200
    //   187: aload 8
    //   189: aload 7
    //   191: if_acmpeq +9 -> 200
    //   194: aload_0
    //   195: aload 8
    //   197: invokevirtual 323	com/facebook/drawee/controller/AbstractDraweeController:releaseDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   200: aload_2
    //   201: ifnull +21 -> 222
    //   204: aload_2
    //   205: aload_3
    //   206: if_acmpeq +16 -> 222
    //   209: aload_0
    //   210: ldc_w 325
    //   213: aload_2
    //   214: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   217: aload_0
    //   218: aload_2
    //   219: invokevirtual 289	com/facebook/drawee/controller/AbstractDraweeController:releaseImage	(Ljava/lang/Object;)V
    //   222: return
    //   223: aload 8
    //   225: ifnull +16 -> 241
    //   228: aload 8
    //   230: aload 7
    //   232: if_acmpeq +9 -> 241
    //   235: aload_0
    //   236: aload 8
    //   238: invokevirtual 323	com/facebook/drawee/controller/AbstractDraweeController:releaseDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   241: aload_2
    //   242: ifnull +21 -> 263
    //   245: aload_2
    //   246: aload_3
    //   247: if_acmpeq +16 -> 263
    //   250: aload_0
    //   251: ldc_w 325
    //   254: aload_2
    //   255: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   258: aload_0
    //   259: aload_2
    //   260: invokevirtual 289	com/facebook/drawee/controller/AbstractDraweeController:releaseImage	(Ljava/lang/Object;)V
    //   263: aload_1
    //   264: athrow
    //   265: astore 7
    //   267: aload_0
    //   268: ldc_w 327
    //   271: aload_3
    //   272: invokespecial 285	com/facebook/drawee/controller/AbstractDraweeController:logMessageAndImage	(Ljava/lang/String;Ljava/lang/Object;)V
    //   275: aload_0
    //   276: aload_3
    //   277: invokevirtual 289	com/facebook/drawee/controller/AbstractDraweeController:releaseImage	(Ljava/lang/Object;)V
    //   280: aload_0
    //   281: aload_1
    //   282: aload_2
    //   283: aload 7
    //   285: iload 5
    //   287: invokespecial 97	com/facebook/drawee/controller/AbstractDraweeController:onFailureInternal	(Ljava/lang/String;Lcom/facebook/datasource/DataSource;Ljava/lang/Throwable;Z)V
    //   290: return
    //   291: astore_1
    //   292: goto -69 -> 223
    //
    // Exception table:
    //   from	to	target	type
    //   61	68	265	java/lang/Exception
    //   95	141	291	finally
    //   144	182	291	finally
  }

  private void onProgressUpdateInternal(String paramString, DataSource<T> paramDataSource, float paramFloat, boolean paramBoolean)
  {
    if (!isExpectedDataSource(paramString, paramDataSource))
    {
      logMessageAndFailure("ignore_old_datasource @ onProgress", null);
      paramDataSource.close();
      return;
    }
    if (!paramBoolean)
      this.mSettableDraweeHierarchy.setProgress(paramFloat, false);
  }

  private void releaseFetch()
  {
    boolean bool = this.mIsRequestSubmitted;
    this.mIsRequestSubmitted = false;
    this.mHasFetchFailed = false;
    if (this.mDataSource != null)
    {
      this.mDataSource.close();
      this.mDataSource = null;
    }
    if (this.mDrawable != null)
      releaseDrawable(this.mDrawable);
    if (this.mContentDescription != null)
      this.mContentDescription = null;
    this.mDrawable = null;
    if (this.mFetchedImage != null)
    {
      logMessageAndImage("release", this.mFetchedImage);
      releaseImage(this.mFetchedImage);
      this.mFetchedImage = null;
    }
    if (bool)
      getControllerListener().onRelease(this.mId);
  }

  private boolean shouldRetryOnTap()
  {
    return (this.mHasFetchFailed) && (this.mRetryManager != null) && (this.mRetryManager.shouldRetryOnTap());
  }

  public void addControllerListener(ControllerListener<? super INFO> paramControllerListener)
  {
    Preconditions.checkNotNull(paramControllerListener);
    if ((this.mControllerListener instanceof InternalForwardingListener))
    {
      ((InternalForwardingListener)this.mControllerListener).addListener(paramControllerListener);
      return;
    }
    if (this.mControllerListener != null)
    {
      this.mControllerListener = InternalForwardingListener.createInternal(this.mControllerListener, paramControllerListener);
      return;
    }
    this.mControllerListener = paramControllerListener;
  }

  protected abstract Drawable createDrawable(T paramT);

  @Nullable
  public Animatable getAnimatable()
  {
    if ((this.mDrawable instanceof Animatable))
      return (Animatable)this.mDrawable;
    return null;
  }

  protected T getCachedImage()
  {
    return null;
  }

  public Object getCallerContext()
  {
    return this.mCallerContext;
  }

  @Nullable
  public String getContentDescription()
  {
    return this.mContentDescription;
  }

  protected ControllerListener<INFO> getControllerListener()
  {
    if (this.mControllerListener == null)
      return BaseControllerListener.getNoOpListener();
    return this.mControllerListener;
  }

  @Nullable
  protected Drawable getControllerOverlay()
  {
    return this.mControllerOverlay;
  }

  protected abstract DataSource<T> getDataSource();

  @Nullable
  protected GestureDetector getGestureDetector()
  {
    return this.mGestureDetector;
  }

  @Nullable
  public DraweeHierarchy getHierarchy()
  {
    return this.mSettableDraweeHierarchy;
  }

  public String getId()
  {
    return this.mId;
  }

  protected String getImageClass(@Nullable T paramT)
  {
    if (paramT != null)
      return paramT.getClass().getSimpleName();
    return "<null>";
  }

  protected int getImageHash(@Nullable T paramT)
  {
    return System.identityHashCode(paramT);
  }

  @Nullable
  protected abstract INFO getImageInfo(T paramT);

  @ReturnsOwnership
  protected RetryManager getRetryManager()
  {
    if (this.mRetryManager == null)
      this.mRetryManager = new RetryManager();
    return this.mRetryManager;
  }

  protected void initialize(String paramString, Object paramObject)
  {
    init(paramString, paramObject);
    this.mJustConstructed = false;
  }

  public void onAttach()
  {
    if (FLog.isLoggable(2))
    {
      Class localClass = TAG;
      int i = System.identityHashCode(this);
      String str2 = this.mId;
      String str1;
      if (this.mIsRequestSubmitted)
        str1 = "request already submitted";
      else
        str1 = "request needs submit";
      FLog.v(localClass, "controller %x %s: onAttach: %s", Integer.valueOf(i), str2, str1);
    }
    this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_ATTACH_CONTROLLER);
    Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
    this.mDeferredReleaser.cancelDeferredRelease(this);
    this.mIsAttached = true;
    if (!this.mIsRequestSubmitted)
      submitRequest();
  }

  public boolean onClick()
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.mId);
    if (shouldRetryOnTap())
    {
      this.mRetryManager.notifyTapToRetry();
      this.mSettableDraweeHierarchy.reset();
      submitRequest();
      return true;
    }
    return false;
  }

  public void onDetach()
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.mId);
    this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DETACH_CONTROLLER);
    this.mIsAttached = false;
    this.mDeferredReleaser.scheduleDeferredRelease(this);
  }

  protected void onImageLoadedFromCacheImmediately(String paramString, T paramT)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.mId, paramMotionEvent);
    if (this.mGestureDetector == null)
      return false;
    if ((!this.mGestureDetector.isCapturingGesture()) && (!shouldHandleGesture()))
      return false;
    this.mGestureDetector.onTouchEvent(paramMotionEvent);
    return true;
  }

  public void onViewportVisibilityHint(boolean paramBoolean)
  {
    ControllerViewportVisibilityListener localControllerViewportVisibilityListener = this.mControllerViewportVisibilityListener;
    if (localControllerViewportVisibilityListener != null)
      if ((paramBoolean) && (!this.mIsVisibleInViewportHint))
        localControllerViewportVisibilityListener.onDraweeViewportEntry(this.mId);
      else if ((!paramBoolean) && (this.mIsVisibleInViewportHint))
        localControllerViewportVisibilityListener.onDraweeViewportExit(this.mId);
    this.mIsVisibleInViewportHint = paramBoolean;
  }

  public void release()
  {
    this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_RELEASE_CONTROLLER);
    if (this.mRetryManager != null)
      this.mRetryManager.reset();
    if (this.mGestureDetector != null)
      this.mGestureDetector.reset();
    if (this.mSettableDraweeHierarchy != null)
      this.mSettableDraweeHierarchy.reset();
    releaseFetch();
  }

  protected abstract void releaseDrawable(@Nullable Drawable paramDrawable);

  protected abstract void releaseImage(@Nullable T paramT);

  public void removeControllerListener(ControllerListener<? super INFO> paramControllerListener)
  {
    Preconditions.checkNotNull(paramControllerListener);
    if ((this.mControllerListener instanceof InternalForwardingListener))
    {
      ((InternalForwardingListener)this.mControllerListener).removeListener(paramControllerListener);
      return;
    }
    if (this.mControllerListener == paramControllerListener)
      this.mControllerListener = null;
  }

  public void setContentDescription(@Nullable String paramString)
  {
    this.mContentDescription = paramString;
  }

  protected void setControllerOverlay(@Nullable Drawable paramDrawable)
  {
    this.mControllerOverlay = paramDrawable;
    if (this.mSettableDraweeHierarchy != null)
      this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
  }

  public void setControllerViewportVisibilityListener(@Nullable ControllerViewportVisibilityListener paramControllerViewportVisibilityListener)
  {
    this.mControllerViewportVisibilityListener = paramControllerViewportVisibilityListener;
  }

  protected void setGestureDetector(@Nullable GestureDetector paramGestureDetector)
  {
    this.mGestureDetector = paramGestureDetector;
    if (this.mGestureDetector != null)
      this.mGestureDetector.setClickListener(this);
  }

  public void setHierarchy(@Nullable DraweeHierarchy paramDraweeHierarchy)
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, paramDraweeHierarchy);
    DraweeEventTracker localDraweeEventTracker = this.mEventTracker;
    DraweeEventTracker.Event localEvent;
    if (paramDraweeHierarchy != null)
      localEvent = DraweeEventTracker.Event.ON_SET_HIERARCHY;
    else
      localEvent = DraweeEventTracker.Event.ON_CLEAR_HIERARCHY;
    localDraweeEventTracker.recordEvent(localEvent);
    if (this.mIsRequestSubmitted)
    {
      this.mDeferredReleaser.cancelDeferredRelease(this);
      release();
    }
    if (this.mSettableDraweeHierarchy != null)
    {
      this.mSettableDraweeHierarchy.setControllerOverlay(null);
      this.mSettableDraweeHierarchy = null;
    }
    if (paramDraweeHierarchy != null)
    {
      Preconditions.checkArgument(paramDraweeHierarchy instanceof SettableDraweeHierarchy);
      this.mSettableDraweeHierarchy = ((SettableDraweeHierarchy)paramDraweeHierarchy);
      this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
    }
  }

  protected void setRetainImageOnFailure(boolean paramBoolean)
  {
    this.mRetainImageOnFailure = paramBoolean;
  }

  protected boolean shouldHandleGesture()
  {
    return shouldRetryOnTap();
  }

  protected void submitRequest()
  {
    Object localObject = getCachedImage();
    if (localObject != null)
    {
      this.mDataSource = null;
      this.mIsRequestSubmitted = true;
      this.mHasFetchFailed = false;
      this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SUBMIT_CACHE_HIT);
      getControllerListener().onSubmit(this.mId, this.mCallerContext);
      onImageLoadedFromCacheImmediately(this.mId, localObject);
      onNewResultInternal(this.mId, this.mDataSource, localObject, 1.0F, true, true);
      return;
    }
    this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DATASOURCE_SUBMIT);
    getControllerListener().onSubmit(this.mId, this.mCallerContext);
    this.mSettableDraweeHierarchy.setProgress(0.0F, true);
    this.mIsRequestSubmitted = true;
    this.mHasFetchFailed = false;
    this.mDataSource = getDataSource();
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
    localObject = new BaseDataSubscriber()
    {
      public void onFailureImpl(DataSource<T> paramAnonymousDataSource)
      {
        AbstractDraweeController.this.onFailureInternal(this.val$id, paramAnonymousDataSource, paramAnonymousDataSource.getFailureCause(), true);
      }

      public void onNewResultImpl(DataSource<T> paramAnonymousDataSource)
      {
        boolean bool = paramAnonymousDataSource.isFinished();
        float f = paramAnonymousDataSource.getProgress();
        Object localObject = paramAnonymousDataSource.getResult();
        if (localObject != null)
        {
          AbstractDraweeController.this.onNewResultInternal(this.val$id, paramAnonymousDataSource, localObject, f, bool, this.val$wasImmediate);
          return;
        }
        if (bool)
          AbstractDraweeController.this.onFailureInternal(this.val$id, paramAnonymousDataSource, new NullPointerException(), true);
      }

      public void onProgressUpdate(DataSource<T> paramAnonymousDataSource)
      {
        boolean bool = paramAnonymousDataSource.isFinished();
        float f = paramAnonymousDataSource.getProgress();
        AbstractDraweeController.this.onProgressUpdateInternal(this.val$id, paramAnonymousDataSource, f, bool);
      }
    };
    this.mDataSource.subscribe((DataSubscriber)localObject, this.mUiThreadImmediateExecutor);
  }

  public String toString()
  {
    return Objects.toStringHelper(this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", this.mEventTracker.toString()).toString();
  }

  private static class InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO>
  {
    public static <INFO> InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> paramControllerListener1, ControllerListener<? super INFO> paramControllerListener2)
    {
      InternalForwardingListener localInternalForwardingListener = new InternalForwardingListener();
      localInternalForwardingListener.addListener(paramControllerListener1);
      localInternalForwardingListener.addListener(paramControllerListener2);
      return localInternalForwardingListener;
    }
  }
}