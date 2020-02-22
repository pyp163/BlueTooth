package org.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.routing.HttpRoute;

@Immutable
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry>
{
  private final HttpRoute route;

  public BasicPoolEntryRef(BasicPoolEntry paramBasicPoolEntry, ReferenceQueue<Object> paramReferenceQueue)
  {
    super(paramBasicPoolEntry, paramReferenceQueue);
    if (paramBasicPoolEntry == null)
      throw new IllegalArgumentException("Pool entry must not be null.");
    this.route = paramBasicPoolEntry.getPlannedRoute();
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}