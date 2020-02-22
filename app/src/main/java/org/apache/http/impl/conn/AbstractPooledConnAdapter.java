package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter
{
  protected volatile AbstractPoolEntry poolEntry;

  protected AbstractPooledConnAdapter(ClientConnectionManager paramClientConnectionManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super(paramClientConnectionManager, paramAbstractPoolEntry.connection);
    this.poolEntry = paramAbstractPoolEntry;
  }

  @Deprecated
  protected final void assertAttached()
  {
    if (this.poolEntry == null)
      throw new ConnectionShutdownException();
  }

  protected void assertValid(AbstractPoolEntry paramAbstractPoolEntry)
  {
    if ((!isReleased()) && (paramAbstractPoolEntry != null))
      return;
    throw new ConnectionShutdownException();
  }

  public void close()
    throws IOException
  {
    Object localObject = getPoolEntry();
    if (localObject != null)
      ((AbstractPoolEntry)localObject).shutdownEntry();
    localObject = getWrappedConnection();
    if (localObject != null)
      ((OperatedClientConnection)localObject).close();
  }

  protected void detach()
  {
    try
    {
      this.poolEntry = null;
      super.detach();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected AbstractPoolEntry getPoolEntry()
  {
    return this.poolEntry;
  }

  public HttpRoute getRoute()
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    if (localAbstractPoolEntry.tracker == null)
      return null;
    return localAbstractPoolEntry.tracker.toRoute();
  }

  public Object getState()
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    return localAbstractPoolEntry.getState();
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.layerProtocol(paramHttpContext, paramHttpParams);
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.open(paramHttpRoute, paramHttpContext, paramHttpParams);
  }

  public void setState(Object paramObject)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.setState(paramObject);
  }

  public void shutdown()
    throws IOException
  {
    Object localObject = getPoolEntry();
    if (localObject != null)
      ((AbstractPoolEntry)localObject).shutdownEntry();
    localObject = getWrappedConnection();
    if (localObject != null)
      ((OperatedClientConnection)localObject).shutdown();
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.tunnelProxy(paramHttpHost, paramBoolean, paramHttpParams);
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.tunnelTarget(paramBoolean, paramHttpParams);
  }
}