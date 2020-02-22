package org.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@ThreadSafe
public class ConnPoolByRoute extends AbstractConnPool
{
  protected final ConnPerRoute connPerRoute;
  private final long connTTL;
  private final TimeUnit connTTLTimeUnit;
  protected final Queue<BasicPoolEntry> freeConnections;
  protected final Set<BasicPoolEntry> leasedConnections;
  private final Log log = LogFactory.getLog(getClass());
  protected volatile int maxTotalConnections;
  protected volatile int numConnections;
  protected final ClientConnectionOperator operator;
  private final Lock poolLock;
  protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
  protected volatile boolean shutdown;
  protected final Queue<WaitingThread> waitingThreads;

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt)
  {
    this(paramClientConnectionOperator, paramConnPerRoute, paramInt, -1L, TimeUnit.MILLISECONDS);
  }

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    if (paramConnPerRoute == null)
      throw new IllegalArgumentException("Connections per route may not be null");
    this.poolLock = this.poolLock;
    this.leasedConnections = this.leasedConnections;
    this.operator = paramClientConnectionOperator;
    this.connPerRoute = paramConnPerRoute;
    this.maxTotalConnections = paramInt;
    this.freeConnections = createFreeConnQueue();
    this.waitingThreads = createWaitingThreadQueue();
    this.routeToPool = createRouteToPoolMap();
    this.connTTL = paramLong;
    this.connTTLTimeUnit = paramTimeUnit;
  }

  @Deprecated
  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams)
  {
    this(paramClientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(paramHttpParams), ConnManagerParams.getMaxTotalConnections(paramHttpParams));
  }

  private void closeConnection(BasicPoolEntry paramBasicPoolEntry)
  {
    paramBasicPoolEntry = paramBasicPoolEntry.getConnection();
    if (paramBasicPoolEntry != null)
      try
      {
        paramBasicPoolEntry.close();
        return;
      }
      catch (IOException paramBasicPoolEntry)
      {
        this.log.debug("I/O error closing connection", paramBasicPoolEntry);
      }
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    long l = System.currentTimeMillis();
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (localBasicPoolEntry.isExpired(l))
        {
          if (this.log.isDebugEnabled())
          {
            Log localLog = this.log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Closing connection expired @ ");
            localStringBuilder.append(new Date(localBasicPoolEntry.getExpiry()));
            localLog.debug(localStringBuilder.toString());
          }
          localIterator.remove();
          deleteEntry(localBasicPoolEntry);
        }
      }
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    long l = paramLong;
    if (paramLong < 0L)
      l = 0L;
    Object localObject1;
    Object localObject2;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Closing connections idle longer than ");
      ((StringBuilder)localObject2).append(l);
      ((StringBuilder)localObject2).append(" ");
      ((StringBuilder)localObject2).append(paramTimeUnit);
      ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    paramLong = System.currentTimeMillis();
    l = paramTimeUnit.toMillis(l);
    this.poolLock.lock();
    try
    {
      paramTimeUnit = this.freeConnections.iterator();
      while (paramTimeUnit.hasNext())
      {
        localObject1 = (BasicPoolEntry)paramTimeUnit.next();
        if (((BasicPoolEntry)localObject1).getUpdated() <= paramLong - l)
        {
          if (this.log.isDebugEnabled())
          {
            localObject2 = this.log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Closing connection last used @ ");
            localStringBuilder.append(new Date(((BasicPoolEntry)localObject1).getUpdated()));
            ((Log)localObject2).debug(localStringBuilder.toString());
          }
          paramTimeUnit.remove();
          deleteEntry((BasicPoolEntry)localObject1);
        }
      }
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramTimeUnit;
  }

  protected BasicPoolEntry createEntry(RouteSpecificPool paramRouteSpecificPool, ClientConnectionOperator paramClientConnectionOperator)
  {
    if (this.log.isDebugEnabled())
    {
      Log localLog = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Creating new connection [");
      localStringBuilder.append(paramRouteSpecificPool.getRoute());
      localStringBuilder.append("]");
      localLog.debug(localStringBuilder.toString());
    }
    paramClientConnectionOperator = new BasicPoolEntry(paramClientConnectionOperator, paramRouteSpecificPool.getRoute(), this.connTTL, this.connTTLTimeUnit);
    this.poolLock.lock();
    try
    {
      paramRouteSpecificPool.createdEntry(paramClientConnectionOperator);
      this.numConnections += 1;
      this.leasedConnections.add(paramClientConnectionOperator);
      return paramClientConnectionOperator;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramRouteSpecificPool;
  }

  protected Queue<BasicPoolEntry> createFreeConnQueue()
  {
    return new LinkedList();
  }

  protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap()
  {
    return new HashMap();
  }

  protected Queue<WaitingThread> createWaitingThreadQueue()
  {
    return new LinkedList();
  }

  public void deleteClosedConnections()
  {
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (!localBasicPoolEntry.getConnection().isOpen())
        {
          localIterator.remove();
          deleteEntry(localBasicPoolEntry);
        }
      }
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected void deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    Object localObject;
    if (this.log.isDebugEnabled())
    {
      localObject = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Deleting connection [");
      localStringBuilder.append(localHttpRoute);
      localStringBuilder.append("][");
      localStringBuilder.append(paramBasicPoolEntry.getState());
      localStringBuilder.append("]");
      ((Log)localObject).debug(localStringBuilder.toString());
    }
    this.poolLock.lock();
    try
    {
      closeConnection(paramBasicPoolEntry);
      localObject = getRoutePool(localHttpRoute, true);
      ((RouteSpecificPool)localObject).deleteEntry(paramBasicPoolEntry);
      this.numConnections -= 1;
      if (((RouteSpecificPool)localObject).isUnused())
        this.routeToPool.remove(localHttpRoute);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramBasicPoolEntry;
  }

  protected void deleteLeastUsedEntry()
  {
    this.poolLock.lock();
    try
    {
      BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)this.freeConnections.remove();
      if (localBasicPoolEntry != null)
        deleteEntry(localBasicPoolEntry);
      else if (this.log.isDebugEnabled())
        this.log.debug("No free connection to delete");
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void freeEntry(BasicPoolEntry paramBasicPoolEntry, boolean paramBoolean, long paramLong, TimeUnit paramTimeUnit)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    Object localObject1;
    Object localObject2;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Releasing connection [");
      ((StringBuilder)localObject2).append(localHttpRoute);
      ((StringBuilder)localObject2).append("][");
      ((StringBuilder)localObject2).append(paramBasicPoolEntry.getState());
      ((StringBuilder)localObject2).append("]");
      ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    this.poolLock.lock();
    while (true)
    {
      try
      {
        if (this.shutdown)
        {
          closeConnection(paramBasicPoolEntry);
          return;
        }
        this.leasedConnections.remove(paramBasicPoolEntry);
        localObject2 = getRoutePool(localHttpRoute, true);
        if (paramBoolean)
        {
          if (this.log.isDebugEnabled())
          {
            if (paramLong <= 0L)
              break label363;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("for ");
            ((StringBuilder)localObject1).append(paramLong);
            ((StringBuilder)localObject1).append(" ");
            ((StringBuilder)localObject1).append(paramTimeUnit);
            localObject1 = ((StringBuilder)localObject1).toString();
            Log localLog = this.log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Pooling connection [");
            localStringBuilder.append(localHttpRoute);
            localStringBuilder.append("][");
            localStringBuilder.append(paramBasicPoolEntry.getState());
            localStringBuilder.append("]; keep alive ");
            localStringBuilder.append((String)localObject1);
            localLog.debug(localStringBuilder.toString());
          }
          ((RouteSpecificPool)localObject2).freeEntry(paramBasicPoolEntry);
          paramBasicPoolEntry.updateExpiry(paramLong, paramTimeUnit);
          this.freeConnections.add(paramBasicPoolEntry);
        }
        else
        {
          ((RouteSpecificPool)localObject2).dropEntry();
          this.numConnections -= 1;
        }
        notifyWaitingThread((RouteSpecificPool)localObject2);
        return;
      }
      finally
      {
        this.poolLock.unlock();
      }
      label363: localObject1 = "indefinitely";
    }
  }

  public int getConnectionsInPool()
  {
    this.poolLock.lock();
    try
    {
      int i = this.numConnections;
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    int i = 0;
    try
    {
      paramHttpRoute = getRoutePool(paramHttpRoute, false);
      if (paramHttpRoute != null)
        i = paramHttpRoute.getEntryCount();
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramHttpRoute;
  }

  protected BasicPoolEntry getEntryBlocking(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit, WaitingThreadAborter paramWaitingThreadAborter)
    throws ConnectionPoolTimeoutException, InterruptedException
  {
    Object localObject2 = null;
    Date localDate;
    if (paramLong > 0L)
      localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
    else
      localDate = null;
    this.poolLock.lock();
    while (true)
    {
      try
      {
        RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
        Object localObject1 = null;
        paramTimeUnit = (TimeUnit)localObject2;
        localObject2 = paramTimeUnit;
        if (paramTimeUnit == null)
        {
          if (this.shutdown)
            throw new IllegalStateException("Connection pool shut down");
          if (this.log.isDebugEnabled())
          {
            paramTimeUnit = this.log;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("[");
            ((StringBuilder)localObject2).append(paramHttpRoute);
            ((StringBuilder)localObject2).append("] total kept alive: ");
            ((StringBuilder)localObject2).append(this.freeConnections.size());
            ((StringBuilder)localObject2).append(", total issued: ");
            ((StringBuilder)localObject2).append(this.leasedConnections.size());
            ((StringBuilder)localObject2).append(", total allocated: ");
            ((StringBuilder)localObject2).append(this.numConnections);
            ((StringBuilder)localObject2).append(" out of ");
            ((StringBuilder)localObject2).append(this.maxTotalConnections);
            paramTimeUnit.debug(((StringBuilder)localObject2).toString());
          }
          localObject2 = getFreeEntry(localRouteSpecificPool, paramObject);
          if (localObject2 == null)
          {
            if (localRouteSpecificPool.getCapacity() <= 0)
              break label713;
            i = 1;
            if (this.log.isDebugEnabled())
            {
              paramTimeUnit = this.log;
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Available capacity: ");
              ((StringBuilder)localObject3).append(localRouteSpecificPool.getCapacity());
              ((StringBuilder)localObject3).append(" out of ");
              ((StringBuilder)localObject3).append(localRouteSpecificPool.getMaxEntries());
              ((StringBuilder)localObject3).append(" [");
              ((StringBuilder)localObject3).append(paramHttpRoute);
              ((StringBuilder)localObject3).append("][");
              ((StringBuilder)localObject3).append(paramObject);
              ((StringBuilder)localObject3).append("]");
              paramTimeUnit.debug(((StringBuilder)localObject3).toString());
            }
            if ((i != 0) && (this.numConnections < this.maxTotalConnections))
            {
              paramTimeUnit = createEntry(localRouteSpecificPool, this.operator);
              continue;
            }
            if ((i != 0) && (!this.freeConnections.isEmpty()))
            {
              deleteLeastUsedEntry();
              localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
              paramTimeUnit = createEntry(localRouteSpecificPool, this.operator);
              continue;
            }
            if (this.log.isDebugEnabled())
            {
              paramTimeUnit = this.log;
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Need to wait for connection [");
              ((StringBuilder)localObject3).append(paramHttpRoute);
              ((StringBuilder)localObject3).append("][");
              ((StringBuilder)localObject3).append(paramObject);
              ((StringBuilder)localObject3).append("]");
              paramTimeUnit.debug(((StringBuilder)localObject3).toString());
            }
            Object localObject3 = localObject1;
            if (localObject1 == null)
            {
              localObject3 = newWaitingThread(this.poolLock.newCondition(), localRouteSpecificPool);
              paramWaitingThreadAborter.setWaitingThread((WaitingThread)localObject3);
            }
            try
            {
              localRouteSpecificPool.queueThread((WaitingThread)localObject3);
              this.waitingThreads.add(localObject3);
              boolean bool = ((WaitingThread)localObject3).await(localDate);
              localRouteSpecificPool.removeThread((WaitingThread)localObject3);
              this.waitingThreads.remove(localObject3);
              paramTimeUnit = (TimeUnit)localObject2;
              localObject1 = localObject3;
              if (bool)
                continue;
              paramTimeUnit = (TimeUnit)localObject2;
              localObject1 = localObject3;
              if (localDate == null)
                continue;
              paramTimeUnit = (TimeUnit)localObject2;
              localObject1 = localObject3;
              if (localDate.getTime() > System.currentTimeMillis())
                continue;
              throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
            }
            finally
            {
              localRouteSpecificPool.removeThread((WaitingThread)localObject3);
              this.waitingThreads.remove(localObject3);
            }
          }
        }
        return localObject2;
      }
      finally
      {
        this.poolLock.unlock();
      }
      label713: int i = 0;
    }
  }

  protected BasicPoolEntry getFreeEntry(RouteSpecificPool paramRouteSpecificPool, Object paramObject)
  {
    this.poolLock.lock();
    int i = 0;
    BasicPoolEntry localBasicPoolEntry = null;
    while (i == 0)
      try
      {
        localBasicPoolEntry = paramRouteSpecificPool.allocEntry(paramObject);
        Log localLog;
        StringBuilder localStringBuilder;
        if (localBasicPoolEntry != null)
        {
          if (this.log.isDebugEnabled())
          {
            localLog = this.log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Getting free connection [");
            localStringBuilder.append(paramRouteSpecificPool.getRoute());
            localStringBuilder.append("][");
            localStringBuilder.append(paramObject);
            localStringBuilder.append("]");
            localLog.debug(localStringBuilder.toString());
          }
          this.freeConnections.remove(localBasicPoolEntry);
          if (localBasicPoolEntry.isExpired(System.currentTimeMillis()))
          {
            if (this.log.isDebugEnabled())
            {
              localLog = this.log;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Closing expired free connection [");
              localStringBuilder.append(paramRouteSpecificPool.getRoute());
              localStringBuilder.append("][");
              localStringBuilder.append(paramObject);
              localStringBuilder.append("]");
              localLog.debug(localStringBuilder.toString());
            }
            closeConnection(localBasicPoolEntry);
            paramRouteSpecificPool.dropEntry();
            this.numConnections -= 1;
          }
          else
          {
            this.leasedConnections.add(localBasicPoolEntry);
          }
        }
        else
        {
          if (this.log.isDebugEnabled())
          {
            localLog = this.log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("No free connections [");
            localStringBuilder.append(paramRouteSpecificPool.getRoute());
            localStringBuilder.append("][");
            localStringBuilder.append(paramObject);
            localStringBuilder.append("]");
            localLog.debug(localStringBuilder.toString());
          }
          i = 1;
        }
      }
      finally
      {
        this.poolLock.unlock();
      }
    this.poolLock.unlock();
    return localBasicPoolEntry;
  }

  protected Lock getLock()
  {
    return this.poolLock;
  }

  public int getMaxTotalConnections()
  {
    return this.maxTotalConnections;
  }

  protected RouteSpecificPool getRoutePool(HttpRoute paramHttpRoute, boolean paramBoolean)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool2 = (RouteSpecificPool)this.routeToPool.get(paramHttpRoute);
      RouteSpecificPool localRouteSpecificPool1 = localRouteSpecificPool2;
      if (localRouteSpecificPool2 == null)
      {
        localRouteSpecificPool1 = localRouteSpecificPool2;
        if (paramBoolean)
        {
          localRouteSpecificPool1 = newRouteSpecificPool(paramHttpRoute);
          this.routeToPool.put(paramHttpRoute, localRouteSpecificPool1);
        }
      }
      return localRouteSpecificPool1;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramHttpRoute;
  }

  protected void handleLostEntry(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
      localRouteSpecificPool.dropEntry();
      if (localRouteSpecificPool.isUnused())
        this.routeToPool.remove(paramHttpRoute);
      this.numConnections -= 1;
      notifyWaitingThread(localRouteSpecificPool);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
    throw paramHttpRoute;
  }

  protected RouteSpecificPool newRouteSpecificPool(HttpRoute paramHttpRoute)
  {
    return new RouteSpecificPool(paramHttpRoute, this.connPerRoute);
  }

  protected WaitingThread newWaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    return new WaitingThread(paramCondition, paramRouteSpecificPool);
  }

  protected void notifyWaitingThread(RouteSpecificPool paramRouteSpecificPool)
  {
    this.poolLock.lock();
    if (paramRouteSpecificPool != null);
    try
    {
      if (paramRouteSpecificPool.hasThread())
      {
        if (this.log.isDebugEnabled())
        {
          Log localLog = this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Notifying thread waiting on pool [");
          localStringBuilder.append(paramRouteSpecificPool.getRoute());
          localStringBuilder.append("]");
          localLog.debug(localStringBuilder.toString());
        }
        paramRouteSpecificPool = paramRouteSpecificPool.nextThread();
      }
      else if (!this.waitingThreads.isEmpty())
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Notifying thread waiting on any pool");
        paramRouteSpecificPool = (WaitingThread)this.waitingThreads.remove();
      }
      else
      {
        if (!this.log.isDebugEnabled())
          break label199;
        this.log.debug("Notifying no-one, there are no waiting threads");
        break label199;
      }
      if (paramRouteSpecificPool != null)
        paramRouteSpecificPool.wakeup();
      this.poolLock.unlock();
      return;
      this.poolLock.unlock();
      throw paramRouteSpecificPool;
    }
    finally
    {
      while (true)
      {
        continue;
        label199: paramRouteSpecificPool = null;
      }
    }
  }

  public PoolEntryRequest requestPoolEntry(final HttpRoute paramHttpRoute, final Object paramObject)
  {
    return new PoolEntryRequest()
    {
      public void abortRequest()
      {
        ConnPoolByRoute.this.poolLock.lock();
        try
        {
          this.val$aborter.abort();
          return;
        }
        finally
        {
          ConnPoolByRoute.this.poolLock.unlock();
        }
      }

      public BasicPoolEntry getPoolEntry(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        return ConnPoolByRoute.this.getEntryBlocking(paramHttpRoute, paramObject, paramAnonymousLong, paramAnonymousTimeUnit, this.val$aborter);
      }
    };
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.poolLock.lock();
    try
    {
      this.maxTotalConnections = paramInt;
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void shutdown()
  {
    this.poolLock.lock();
    try
    {
      boolean bool = this.shutdown;
      if (bool)
        return;
      this.shutdown = true;
      Iterator localIterator = this.leasedConnections.iterator();
      Object localObject2;
      while (localIterator.hasNext())
      {
        localObject2 = (BasicPoolEntry)localIterator.next();
        localIterator.remove();
        closeConnection((BasicPoolEntry)localObject2);
      }
      localIterator = this.freeConnections.iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (BasicPoolEntry)localIterator.next();
        localIterator.remove();
        if (this.log.isDebugEnabled())
        {
          Log localLog = this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Closing connection [");
          localStringBuilder.append(((BasicPoolEntry)localObject2).getPlannedRoute());
          localStringBuilder.append("][");
          localStringBuilder.append(((BasicPoolEntry)localObject2).getState());
          localStringBuilder.append("]");
          localLog.debug(localStringBuilder.toString());
        }
        closeConnection((BasicPoolEntry)localObject2);
      }
      localIterator = this.waitingThreads.iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (WaitingThread)localIterator.next();
        localIterator.remove();
        ((WaitingThread)localObject2).wakeup();
      }
      this.routeToPool.clear();
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }
}