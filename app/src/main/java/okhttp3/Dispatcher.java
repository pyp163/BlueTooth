package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class Dispatcher
{

  @Nullable
  private ExecutorService executorService;

  @Nullable
  private Runnable idleCallback;
  private int maxRequests = 64;
  private int maxRequestsPerHost = 5;
  private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
  private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque();
  private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

  public Dispatcher()
  {
  }

  public Dispatcher(ExecutorService paramExecutorService)
  {
    this.executorService = paramExecutorService;
  }

  @Nullable
  private RealCall.AsyncCall findExistingCallWithHost(String paramString)
  {
    Iterator localIterator = this.runningAsyncCalls.iterator();
    RealCall.AsyncCall localAsyncCall;
    while (localIterator.hasNext())
    {
      localAsyncCall = (RealCall.AsyncCall)localIterator.next();
      if (localAsyncCall.host().equals(paramString))
        return localAsyncCall;
    }
    localIterator = this.readyAsyncCalls.iterator();
    while (localIterator.hasNext())
    {
      localAsyncCall = (RealCall.AsyncCall)localIterator.next();
      if (localAsyncCall.host().equals(paramString))
        return localAsyncCall;
    }
    return null;
  }

  private <T> void finished(Deque<T> paramDeque, T paramT)
  {
    try
    {
      if (!paramDeque.remove(paramT))
        throw new AssertionError("Call wasn't in-flight!");
      paramDeque = this.idleCallback;
      if ((!promoteAndExecute()) && (paramDeque != null))
        paramDeque.run();
      return;
    }
    finally
    {
    }
    throw paramDeque;
  }

  private boolean promoteAndExecute()
  {
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      try
      {
        Iterator localIterator = this.readyAsyncCalls.iterator();
        if (localIterator.hasNext())
        {
          RealCall.AsyncCall localAsyncCall = (RealCall.AsyncCall)localIterator.next();
          if (this.runningAsyncCalls.size() < this.maxRequests)
          {
            if (localAsyncCall.callsPerHost().get() >= this.maxRequestsPerHost)
              continue;
            localIterator.remove();
            localAsyncCall.callsPerHost().incrementAndGet();
            localArrayList.add(localAsyncCall);
            this.runningAsyncCalls.add(localAsyncCall);
            continue;
          }
        }
        int j = runningCallsCount();
        int i = 0;
        if (j > 0)
        {
          bool = true;
          j = localArrayList.size();
          if (i < j)
          {
            ((RealCall.AsyncCall)localArrayList.get(i)).executeOn(executorService());
            i += 1;
            continue;
          }
          return bool;
        }
      }
      finally
      {
      }
      boolean bool = false;
    }
  }

  public void cancelAll()
  {
    try
    {
      Iterator localIterator = this.readyAsyncCalls.iterator();
      while (localIterator.hasNext())
        ((RealCall.AsyncCall)localIterator.next()).get().cancel();
      localIterator = this.runningAsyncCalls.iterator();
      while (localIterator.hasNext())
        ((RealCall.AsyncCall)localIterator.next()).get().cancel();
      localIterator = this.runningSyncCalls.iterator();
      while (localIterator.hasNext())
        ((RealCall)localIterator.next()).cancel();
      return;
    }
    finally
    {
    }
  }

  void enqueue(RealCall.AsyncCall paramAsyncCall)
  {
    try
    {
      this.readyAsyncCalls.add(paramAsyncCall);
      if (!paramAsyncCall.get().forWebSocket)
      {
        RealCall.AsyncCall localAsyncCall = findExistingCallWithHost(paramAsyncCall.host());
        if (localAsyncCall != null)
          paramAsyncCall.reuseCallsPerHostFrom(localAsyncCall);
      }
      promoteAndExecute();
      return;
    }
    finally
    {
    }
    throw paramAsyncCall;
  }

  void executed(RealCall paramRealCall)
  {
    try
    {
      this.runningSyncCalls.add(paramRealCall);
      return;
    }
    finally
    {
      paramRealCall = finally;
    }
    throw paramRealCall;
  }

  public ExecutorService executorService()
  {
    try
    {
      if (this.executorService == null)
        this.executorService = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
      ExecutorService localExecutorService = this.executorService;
      return localExecutorService;
    }
    finally
    {
    }
  }

  void finished(RealCall.AsyncCall paramAsyncCall)
  {
    paramAsyncCall.callsPerHost().decrementAndGet();
    finished(this.runningAsyncCalls, paramAsyncCall);
  }

  void finished(RealCall paramRealCall)
  {
    finished(this.runningSyncCalls, paramRealCall);
  }

  public int getMaxRequests()
  {
    try
    {
      int i = this.maxRequests;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getMaxRequestsPerHost()
  {
    try
    {
      int i = this.maxRequestsPerHost;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<Call> queuedCalls()
  {
    try
    {
      Object localObject1 = new ArrayList();
      Iterator localIterator = this.readyAsyncCalls.iterator();
      while (localIterator.hasNext())
        ((List)localObject1).add(((RealCall.AsyncCall)localIterator.next()).get());
      localObject1 = Collections.unmodifiableList((List)localObject1);
      return localObject1;
    }
    finally
    {
    }
  }

  public int queuedCallsCount()
  {
    try
    {
      int i = this.readyAsyncCalls.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<Call> runningCalls()
  {
    try
    {
      Object localObject1 = new ArrayList();
      ((List)localObject1).addAll(this.runningSyncCalls);
      Iterator localIterator = this.runningAsyncCalls.iterator();
      while (localIterator.hasNext())
        ((List)localObject1).add(((RealCall.AsyncCall)localIterator.next()).get());
      localObject1 = Collections.unmodifiableList((List)localObject1);
      return localObject1;
    }
    finally
    {
    }
  }

  public int runningCallsCount()
  {
    try
    {
      int i = this.runningAsyncCalls.size();
      int j = this.runningSyncCalls.size();
      return i + j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setIdleCallback(@Nullable Runnable paramRunnable)
  {
    try
    {
      this.idleCallback = paramRunnable;
      return;
    }
    finally
    {
      paramRunnable = finally;
    }
    throw paramRunnable;
  }

  public void setMaxRequests(int paramInt)
  {
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("max < 1: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    try
    {
      this.maxRequests = paramInt;
      promoteAndExecute();
      return;
    }
    finally
    {
    }
  }

  public void setMaxRequestsPerHost(int paramInt)
  {
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("max < 1: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    try
    {
      this.maxRequestsPerHost = paramInt;
      promoteAndExecute();
      return;
    }
    finally
    {
    }
  }
}