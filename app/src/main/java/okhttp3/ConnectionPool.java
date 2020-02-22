package okhttp3;

import java.util.concurrent.TimeUnit;
import okhttp3.internal.connection.RealConnectionPool;

public final class ConnectionPool
{
  final RealConnectionPool delegate;

  public ConnectionPool()
  {
    this(5, 5L, TimeUnit.MINUTES);
  }

  public ConnectionPool(int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    this.delegate = new RealConnectionPool(paramInt, paramLong, paramTimeUnit);
  }

  public int connectionCount()
  {
    return this.delegate.connectionCount();
  }

  public void evictAll()
  {
    this.delegate.evictAll();
  }

  public int idleConnectionCount()
  {
    return this.delegate.idleConnectionCount();
  }
}