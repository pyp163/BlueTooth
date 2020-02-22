package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;

@NotThreadSafe
public class BasicPoolEntry extends AbstractPoolEntry
{
  private final long created;
  private long expiry;
  private long updated;
  private long validUntil;

  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute)
  {
    this(paramClientConnectionOperator, paramHttpRoute, -1L, TimeUnit.MILLISECONDS);
  }

  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramClientConnectionOperator, paramHttpRoute);
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    this.created = System.currentTimeMillis();
    if (paramLong > 0L)
      this.validUntil = (this.created + paramTimeUnit.toMillis(paramLong));
    else
      this.validUntil = 9223372036854775807L;
    this.expiry = this.validUntil;
  }

  @Deprecated
  public BasicPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute, ReferenceQueue<Object> paramReferenceQueue)
  {
    super(paramClientConnectionOperator, paramHttpRoute);
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    this.created = System.currentTimeMillis();
    this.validUntil = 9223372036854775807L;
    this.expiry = this.validUntil;
  }

  protected final OperatedClientConnection getConnection()
  {
    return this.connection;
  }

  public long getCreated()
  {
    return this.created;
  }

  public long getExpiry()
  {
    return this.expiry;
  }

  protected final HttpRoute getPlannedRoute()
  {
    return this.route;
  }

  public long getUpdated()
  {
    return this.updated;
  }

  public long getValidUntil()
  {
    return this.validUntil;
  }

  @Deprecated
  protected final BasicPoolEntryRef getWeakRef()
  {
    return null;
  }

  public boolean isExpired(long paramLong)
  {
    return paramLong >= this.expiry;
  }

  protected void shutdownEntry()
  {
    super.shutdownEntry();
  }

  public void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    this.updated = System.currentTimeMillis();
    if (paramLong > 0L)
      paramLong = this.updated + paramTimeUnit.toMillis(paramLong);
    else
      paramLong = 9223372036854775807L;
    this.expiry = Math.min(this.validUntil, paramLong);
  }
}