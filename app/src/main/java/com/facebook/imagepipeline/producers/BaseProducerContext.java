package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class BaseProducerContext
  implements ProducerContext
{

  @GuardedBy("this")
  private final List<ProducerContextCallbacks> mCallbacks;
  private final Object mCallerContext;
  private final String mId;
  private final ImageRequest mImageRequest;

  @GuardedBy("this")
  private boolean mIsCancelled;

  @GuardedBy("this")
  private boolean mIsIntermediateResultExpected;

  @GuardedBy("this")
  private boolean mIsPrefetch;
  private final ImageRequest.RequestLevel mLowestPermittedRequestLevel;

  @GuardedBy("this")
  private Priority mPriority;
  private final ProducerListener mProducerListener;

  public BaseProducerContext(ImageRequest paramImageRequest, String paramString, ProducerListener paramProducerListener, Object paramObject, ImageRequest.RequestLevel paramRequestLevel, boolean paramBoolean1, boolean paramBoolean2, Priority paramPriority)
  {
    this.mImageRequest = paramImageRequest;
    this.mId = paramString;
    this.mProducerListener = paramProducerListener;
    this.mCallerContext = paramObject;
    this.mLowestPermittedRequestLevel = paramRequestLevel;
    this.mIsPrefetch = paramBoolean1;
    this.mPriority = paramPriority;
    this.mIsIntermediateResultExpected = paramBoolean2;
    this.mIsCancelled = false;
    this.mCallbacks = new ArrayList();
  }

  public static void callOnCancellationRequested(@Nullable List<ProducerContextCallbacks> paramList)
  {
    if (paramList == null)
      return;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      ((ProducerContextCallbacks)paramList.next()).onCancellationRequested();
  }

  public static void callOnIsIntermediateResultExpectedChanged(@Nullable List<ProducerContextCallbacks> paramList)
  {
    if (paramList == null)
      return;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      ((ProducerContextCallbacks)paramList.next()).onIsIntermediateResultExpectedChanged();
  }

  public static void callOnIsPrefetchChanged(@Nullable List<ProducerContextCallbacks> paramList)
  {
    if (paramList == null)
      return;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      ((ProducerContextCallbacks)paramList.next()).onIsPrefetchChanged();
  }

  public static void callOnPriorityChanged(@Nullable List<ProducerContextCallbacks> paramList)
  {
    if (paramList == null)
      return;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      ((ProducerContextCallbacks)paramList.next()).onPriorityChanged();
  }

  public void addCallbacks(ProducerContextCallbacks paramProducerContextCallbacks)
  {
    try
    {
      this.mCallbacks.add(paramProducerContextCallbacks);
      boolean bool = this.mIsCancelled;
      if (bool)
        paramProducerContextCallbacks.onCancellationRequested();
      return;
    }
    finally
    {
    }
    throw paramProducerContextCallbacks;
  }

  public void cancel()
  {
    callOnCancellationRequested(cancelNoCallbacks());
  }

  @Nullable
  public List<ProducerContextCallbacks> cancelNoCallbacks()
  {
    try
    {
      boolean bool = this.mIsCancelled;
      if (bool)
        return null;
      this.mIsCancelled = true;
      ArrayList localArrayList = new ArrayList(this.mCallbacks);
      return localArrayList;
    }
    finally
    {
    }
  }

  public Object getCallerContext()
  {
    return this.mCallerContext;
  }

  public String getId()
  {
    return this.mId;
  }

  public ImageRequest getImageRequest()
  {
    return this.mImageRequest;
  }

  public ProducerListener getListener()
  {
    return this.mProducerListener;
  }

  public ImageRequest.RequestLevel getLowestPermittedRequestLevel()
  {
    return this.mLowestPermittedRequestLevel;
  }

  public Priority getPriority()
  {
    try
    {
      Priority localPriority = this.mPriority;
      return localPriority;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isCancelled()
  {
    try
    {
      boolean bool = this.mIsCancelled;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isIntermediateResultExpected()
  {
    try
    {
      boolean bool = this.mIsIntermediateResultExpected;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isPrefetch()
  {
    try
    {
      boolean bool = this.mIsPrefetch;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  @Nullable
  public List<ProducerContextCallbacks> setIsIntermediateResultExpectedNoCallbacks(boolean paramBoolean)
  {
    try
    {
      boolean bool = this.mIsIntermediateResultExpected;
      if (paramBoolean == bool)
        return null;
      this.mIsIntermediateResultExpected = paramBoolean;
      ArrayList localArrayList = new ArrayList(this.mCallbacks);
      return localArrayList;
    }
    finally
    {
    }
  }

  @Nullable
  public List<ProducerContextCallbacks> setIsPrefetchNoCallbacks(boolean paramBoolean)
  {
    try
    {
      boolean bool = this.mIsPrefetch;
      if (paramBoolean == bool)
        return null;
      this.mIsPrefetch = paramBoolean;
      ArrayList localArrayList = new ArrayList(this.mCallbacks);
      return localArrayList;
    }
    finally
    {
    }
  }

  @Nullable
  public List<ProducerContextCallbacks> setPriorityNoCallbacks(Priority paramPriority)
  {
    try
    {
      Priority localPriority = this.mPriority;
      if (paramPriority == localPriority)
        return null;
      this.mPriority = paramPriority;
      paramPriority = new ArrayList(this.mCallbacks);
      return paramPriority;
    }
    finally
    {
    }
    throw paramPriority;
  }
}