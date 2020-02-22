package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpConnection;

@Deprecated
public class IdleConnectionHandler
{
  private final Map<HttpConnection, TimeValues> connectionToTimes = new HashMap();
  private final Log log = LogFactory.getLog(getClass());

  public void add(HttpConnection paramHttpConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    long l = System.currentTimeMillis();
    if (this.log.isDebugEnabled())
    {
      Log localLog = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding connection at: ");
      localStringBuilder.append(l);
      localLog.debug(localStringBuilder.toString());
    }
    this.connectionToTimes.put(paramHttpConnection, new TimeValues(l, paramLong, paramTimeUnit));
  }

  public void closeExpiredConnections()
  {
    long l = System.currentTimeMillis();
    Object localObject2;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Checking for expired connections, now: ");
      ((StringBuilder)localObject2).append(l);
      ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    Object localObject1 = this.connectionToTimes.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      localObject2 = (HttpConnection)((Map.Entry)localObject3).getKey();
      localObject3 = (TimeValues)((Map.Entry)localObject3).getValue();
      if (((TimeValues)localObject3).timeExpires <= l)
      {
        if (this.log.isDebugEnabled())
        {
          Log localLog = this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Closing connection, expired @: ");
          localStringBuilder.append(((TimeValues)localObject3).timeExpires);
          localLog.debug(localStringBuilder.toString());
        }
        try
        {
          ((HttpConnection)localObject2).close();
        }
        catch (IOException localIOException)
        {
          this.log.debug("I/O error closing connection", localIOException);
        }
      }
    }
  }

  public void closeIdleConnections(long paramLong)
  {
    paramLong = System.currentTimeMillis() - paramLong;
    Object localObject2;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Checking for connections, idle timeout: ");
      ((StringBuilder)localObject2).append(paramLong);
      ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    Object localObject1 = this.connectionToTimes.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      localObject2 = (HttpConnection)((Map.Entry)localObject3).getKey();
      long l = ((TimeValues)((Map.Entry)localObject3).getValue()).timeAdded;
      if (l <= paramLong)
      {
        if (this.log.isDebugEnabled())
        {
          localObject3 = this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Closing idle connection, connection time: ");
          localStringBuilder.append(l);
          ((Log)localObject3).debug(localStringBuilder.toString());
        }
        try
        {
          ((HttpConnection)localObject2).close();
        }
        catch (IOException localIOException)
        {
          this.log.debug("I/O error closing connection", localIOException);
        }
      }
    }
  }

  public boolean remove(HttpConnection paramHttpConnection)
  {
    paramHttpConnection = (TimeValues)this.connectionToTimes.remove(paramHttpConnection);
    if (paramHttpConnection == null)
    {
      this.log.warn("Removing a connection that never existed!");
      return true;
    }
    return System.currentTimeMillis() <= paramHttpConnection.timeExpires;
  }

  public void removeAll()
  {
    this.connectionToTimes.clear();
  }

  private static class TimeValues
  {
    private final long timeAdded;
    private final long timeExpires;

    TimeValues(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.timeAdded = paramLong1;
      if (paramLong2 > 0L)
      {
        this.timeExpires = (paramLong1 + paramTimeUnit.toMillis(paramLong2));
        return;
      }
      this.timeExpires = 9223372036854775807L;
    }
  }
}