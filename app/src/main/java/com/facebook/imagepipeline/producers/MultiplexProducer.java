package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.Priority;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MultiplexProducer<K, T extends Closeable>
  implements Producer<T>
{
  private final Producer<T> mInputProducer;

  @VisibleForTesting
  @GuardedBy("this")
  final Map<K, MultiplexProducer<K, T>.Multiplexer> mMultiplexers;

  protected MultiplexProducer(Producer<T> paramProducer)
  {
    this.mInputProducer = paramProducer;
    this.mMultiplexers = new HashMap();
  }

  private MultiplexProducer<K, T>.Multiplexer createAndPutNewMultiplexer(K paramK)
  {
    try
    {
      Multiplexer localMultiplexer = new Multiplexer(paramK);
      this.mMultiplexers.put(paramK, localMultiplexer);
      return localMultiplexer;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  private MultiplexProducer<K, T>.Multiplexer getExistingMultiplexer(K paramK)
  {
    try
    {
      paramK = (Multiplexer)this.mMultiplexers.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  private void removeMultiplexer(K paramK, MultiplexProducer<K, T>.Multiplexer paramMultiplexProducer)
  {
    try
    {
      if (this.mMultiplexers.get(paramK) == paramMultiplexProducer)
        this.mMultiplexers.remove(paramK);
      return;
    }
    finally
    {
      paramK = finally;
    }
    throw paramK;
  }

  protected abstract T cloneOrNull(T paramT);

  protected abstract K getKey(ProducerContext paramProducerContext);

  public void produceResults(Consumer<T> paramConsumer, ProducerContext paramProducerContext)
  {
    Object localObject = getKey(paramProducerContext);
    while (true)
    {
      int i = 0;
      try
      {
        Multiplexer localMultiplexer2 = getExistingMultiplexer(localObject);
        Multiplexer localMultiplexer1 = localMultiplexer2;
        if (localMultiplexer2 == null)
        {
          localMultiplexer1 = createAndPutNewMultiplexer(localObject);
          i = 1;
        }
        if (localMultiplexer1.addNewConsumer(paramConsumer, paramProducerContext))
        {
          if (i != 0)
            localMultiplexer1.startInputProducerIfHasAttachedConsumers();
          return;
        }
      }
      finally
      {
      }
    }
    throw paramConsumer;
  }

  @VisibleForTesting
  class Multiplexer
  {
    private final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> mConsumerContextPairs = Sets.newCopyOnWriteArraySet();

    @Nullable
    @GuardedBy("Multiplexer.this")
    private MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer mForwardingConsumer;
    private final K mKey;

    @Nullable
    @GuardedBy("Multiplexer.this")
    private T mLastIntermediateResult;

    @GuardedBy("Multiplexer.this")
    private float mLastProgress;

    @GuardedBy("Multiplexer.this")
    private int mLastStatus;

    @Nullable
    @GuardedBy("Multiplexer.this")
    private BaseProducerContext mMultiplexProducerContext;

    public Multiplexer()
    {
      Object localObject;
      this.mKey = localObject;
    }

    private void addCallbacks(final Pair<Consumer<T>, ProducerContext> paramPair, ProducerContext paramProducerContext)
    {
      paramProducerContext.addCallbacks(new BaseProducerContextCallbacks()
      {
        public void onCancellationRequested()
        {
          while (true)
          {
            synchronized (MultiplexProducer.Multiplexer.this)
            {
              boolean bool = MultiplexProducer.Multiplexer.this.mConsumerContextPairs.remove(paramPair);
              List localList1 = null;
              if (bool)
              {
                if (MultiplexProducer.Multiplexer.this.mConsumerContextPairs.isEmpty())
                {
                  localBaseProducerContext = MultiplexProducer.Multiplexer.this.mMultiplexProducerContext;
                  break label138;
                }
                localList1 = MultiplexProducer.Multiplexer.this.updateIsPrefetch();
                localList2 = MultiplexProducer.Multiplexer.this.updatePriority();
                localList3 = MultiplexProducer.Multiplexer.this.updateIsIntermediateResultExpected();
                BaseProducerContext localBaseProducerContext = null;
                BaseProducerContext.callOnIsPrefetchChanged(localList1);
                BaseProducerContext.callOnPriorityChanged(localList2);
                BaseProducerContext.callOnIsIntermediateResultExpectedChanged(localList3);
                if (localBaseProducerContext != null)
                  localBaseProducerContext.cancel();
                if (bool)
                  ((Consumer)paramPair.first).onCancellation();
                return;
              }
            }
            Object localObject2 = null;
            label138: List localList2 = null;
            List localList3 = null;
          }
        }

        public void onIsIntermediateResultExpectedChanged()
        {
          BaseProducerContext.callOnIsIntermediateResultExpectedChanged(MultiplexProducer.Multiplexer.this.updateIsIntermediateResultExpected());
        }

        public void onIsPrefetchChanged()
        {
          BaseProducerContext.callOnIsPrefetchChanged(MultiplexProducer.Multiplexer.this.updateIsPrefetch());
        }

        public void onPriorityChanged()
        {
          BaseProducerContext.callOnPriorityChanged(MultiplexProducer.Multiplexer.this.updatePriority());
        }
      });
    }

    private void closeSafely(Closeable paramCloseable)
    {
      if (paramCloseable != null)
        try
        {
          paramCloseable.close();
          return;
        }
        catch (IOException paramCloseable)
        {
          throw new RuntimeException(paramCloseable);
        }
    }

    private boolean computeIsIntermediateResultExpected()
    {
      try
      {
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        while (localIterator.hasNext())
        {
          boolean bool = ((ProducerContext)((Pair)localIterator.next()).second).isIntermediateResultExpected();
          if (bool)
            return true;
        }
        return false;
      }
      finally
      {
      }
    }

    private boolean computeIsPrefetch()
    {
      try
      {
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        while (localIterator.hasNext())
        {
          boolean bool = ((ProducerContext)((Pair)localIterator.next()).second).isPrefetch();
          if (!bool)
            return false;
        }
        return true;
      }
      finally
      {
      }
    }

    private Priority computePriority()
    {
      try
      {
        Priority localPriority = Priority.LOW;
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        while (localIterator.hasNext())
          localPriority = Priority.getHigherPriority(localPriority, ((ProducerContext)((Pair)localIterator.next()).second).getPriority());
        return localPriority;
      }
      finally
      {
      }
    }

    private void startInputProducerIfHasAttachedConsumers()
    {
      while (true)
      {
        try
        {
          Object localObject1 = this.mMultiplexProducerContext;
          boolean bool2 = false;
          if (localObject1 == null)
          {
            bool1 = true;
            Preconditions.checkArgument(bool1);
            bool1 = bool2;
            if (this.mForwardingConsumer == null)
              bool1 = true;
            Preconditions.checkArgument(bool1);
            if (this.mConsumerContextPairs.isEmpty())
            {
              MultiplexProducer.this.removeMultiplexer(this.mKey, this);
              return;
            }
            localObject1 = (ProducerContext)((Pair)this.mConsumerContextPairs.iterator().next()).second;
            this.mMultiplexProducerContext = new BaseProducerContext(((ProducerContext)localObject1).getImageRequest(), ((ProducerContext)localObject1).getId(), ((ProducerContext)localObject1).getListener(), ((ProducerContext)localObject1).getCallerContext(), ((ProducerContext)localObject1).getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority());
            this.mForwardingConsumer = new ForwardingConsumer(null);
            localObject1 = this.mMultiplexProducerContext;
            ForwardingConsumer localForwardingConsumer = this.mForwardingConsumer;
            MultiplexProducer.this.mInputProducer.produceResults(localForwardingConsumer, (ProducerContext)localObject1);
            return;
          }
        }
        finally
        {
        }
        boolean bool1 = false;
      }
    }

    @Nullable
    private List<ProducerContextCallbacks> updateIsIntermediateResultExpected()
    {
      try
      {
        Object localObject1 = this.mMultiplexProducerContext;
        if (localObject1 == null)
          return null;
        localObject1 = this.mMultiplexProducerContext.setIsIntermediateResultExpectedNoCallbacks(computeIsIntermediateResultExpected());
        return localObject1;
      }
      finally
      {
      }
    }

    @Nullable
    private List<ProducerContextCallbacks> updateIsPrefetch()
    {
      try
      {
        Object localObject1 = this.mMultiplexProducerContext;
        if (localObject1 == null)
          return null;
        localObject1 = this.mMultiplexProducerContext.setIsPrefetchNoCallbacks(computeIsPrefetch());
        return localObject1;
      }
      finally
      {
      }
    }

    @Nullable
    private List<ProducerContextCallbacks> updatePriority()
    {
      try
      {
        Object localObject1 = this.mMultiplexProducerContext;
        if (localObject1 == null)
          return null;
        localObject1 = this.mMultiplexProducerContext.setPriorityNoCallbacks(computePriority());
        return localObject1;
      }
      finally
      {
      }
    }

    public boolean addNewConsumer(Consumer<T> paramConsumer, ProducerContext paramProducerContext)
    {
      Pair localPair = Pair.create(paramConsumer, paramProducerContext);
      try
      {
        if (MultiplexProducer.this.getExistingMultiplexer(this.mKey) != this)
          return false;
        this.mConsumerContextPairs.add(localPair);
        Object localObject = updateIsPrefetch();
        List localList1 = updatePriority();
        List localList2 = updateIsIntermediateResultExpected();
        Closeable localCloseable = this.mLastIntermediateResult;
        float f = this.mLastProgress;
        int i = this.mLastStatus;
        BaseProducerContext.callOnIsPrefetchChanged((List)localObject);
        BaseProducerContext.callOnPriorityChanged(localList1);
        BaseProducerContext.callOnIsIntermediateResultExpectedChanged(localList2);
        try
        {
          try
          {
            if (localCloseable != this.mLastIntermediateResult)
            {
              localObject = null;
            }
            else
            {
              localObject = localCloseable;
              if (localCloseable != null)
                localObject = MultiplexProducer.this.cloneOrNull(localCloseable);
            }
            if (localObject != null)
            {
              if (f > 0.0F)
                paramConsumer.onProgressUpdate(f);
              paramConsumer.onNewResult(localObject, i);
              closeSafely((Closeable)localObject);
            }
            addCallbacks(localPair, paramProducerContext);
            return true;
          }
          finally
          {
          }
        }
        finally
        {
        }
      }
      finally
      {
      }
      throw paramConsumer;
    }

    public void onCancelled(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer paramMultiplexProducer)
    {
      try
      {
        if (this.mForwardingConsumer != paramMultiplexProducer)
          return;
        this.mForwardingConsumer = null;
        this.mMultiplexProducerContext = null;
        closeSafely(this.mLastIntermediateResult);
        this.mLastIntermediateResult = null;
        startInputProducerIfHasAttachedConsumers();
        return;
      }
      finally
      {
      }
      throw paramMultiplexProducer;
    }

    public void onFailure(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer arg1, Throwable paramThrowable)
    {
      try
      {
        if (this.mForwardingConsumer != ???)
          return;
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        this.mConsumerContextPairs.clear();
        MultiplexProducer.this.removeMultiplexer(this.mKey, this);
        closeSafely(this.mLastIntermediateResult);
        this.mLastIntermediateResult = null;
        while (localIterator.hasNext())
          synchronized ((Pair)localIterator.next())
          {
            ((Consumer)???.first).onFailure(paramThrowable);
          }
        return;
      }
      finally
      {
      }
      throw ???;
    }

    public void onNextResult(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer arg1, T paramT, int paramInt)
    {
      try
      {
        if (this.mForwardingConsumer != ???)
          return;
        closeSafely(this.mLastIntermediateResult);
        this.mLastIntermediateResult = null;
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        if (BaseConsumer.isNotLast(paramInt))
        {
          this.mLastIntermediateResult = MultiplexProducer.this.cloneOrNull(paramT);
          this.mLastStatus = paramInt;
        }
        else
        {
          this.mConsumerContextPairs.clear();
          MultiplexProducer.this.removeMultiplexer(this.mKey, this);
        }
        while (localIterator.hasNext())
          synchronized ((Pair)localIterator.next())
          {
            ((Consumer)???.first).onNewResult(paramT, paramInt);
          }
        return;
      }
      finally
      {
      }
      throw ???;
    }

    public void onProgressUpdate(MultiplexProducer<K, T>.Multiplexer.ForwardingConsumer arg1, float paramFloat)
    {
      try
      {
        if (this.mForwardingConsumer != ???)
          return;
        this.mLastProgress = paramFloat;
        Iterator localIterator = this.mConsumerContextPairs.iterator();
        while (localIterator.hasNext())
          synchronized ((Pair)localIterator.next())
          {
            ((Consumer)???.first).onProgressUpdate(paramFloat);
          }
        return;
      }
      finally
      {
      }
      throw ???;
    }

    private class ForwardingConsumer extends BaseConsumer<T>
    {
      private ForwardingConsumer()
      {
      }

      protected void onCancellationImpl()
      {
        MultiplexProducer.Multiplexer.this.onCancelled(this);
      }

      protected void onFailureImpl(Throwable paramThrowable)
      {
        MultiplexProducer.Multiplexer.this.onFailure(this, paramThrowable);
      }

      protected void onNewResultImpl(T paramT, int paramInt)
      {
        MultiplexProducer.Multiplexer.this.onNextResult(this, paramT, paramInt);
      }

      protected void onProgressUpdateImpl(float paramFloat)
      {
        MultiplexProducer.Multiplexer.this.onProgressUpdate(this, paramFloat);
      }
    }
  }
}