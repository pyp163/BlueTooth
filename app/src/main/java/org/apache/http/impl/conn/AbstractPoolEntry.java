package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@NotThreadSafe
public abstract class AbstractPoolEntry
{
  protected final ClientConnectionOperator connOperator;
  protected final OperatedClientConnection connection;
  protected volatile HttpRoute route;
  protected volatile Object state;
  protected volatile RouteTracker tracker;

  protected AbstractPoolEntry(ClientConnectionOperator paramClientConnectionOperator, HttpRoute paramHttpRoute)
  {
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    this.connOperator = paramClientConnectionOperator;
    this.connection = paramClientConnectionOperator.createConnection();
    this.route = paramHttpRoute;
    this.tracker = null;
  }

  public Object getState()
  {
    return this.state;
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker != null) && (this.tracker.isConnected()))
    {
      if (!this.tracker.isTunnelled())
        throw new IllegalStateException("Protocol layering without a tunnel not supported.");
      if (this.tracker.isLayered())
        throw new IllegalStateException("Multiple protocol layering not supported.");
      HttpHost localHttpHost = this.tracker.getTargetHost();
      this.connOperator.updateSecureConnection(this.connection, localHttpHost, paramHttpContext, paramHttpParams);
      this.tracker.layerProtocol(this.connection.isSecure());
      return;
    }
    throw new IllegalStateException("Connection not open.");
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker != null) && (this.tracker.isConnected()))
      throw new IllegalStateException("Connection already open.");
    this.tracker = new RouteTracker(paramHttpRoute);
    HttpHost localHttpHost2 = paramHttpRoute.getProxyHost();
    ClientConnectionOperator localClientConnectionOperator = this.connOperator;
    OperatedClientConnection localOperatedClientConnection = this.connection;
    HttpHost localHttpHost1;
    if (localHttpHost2 != null)
      localHttpHost1 = localHttpHost2;
    else
      localHttpHost1 = paramHttpRoute.getTargetHost();
    localClientConnectionOperator.openConnection(localOperatedClientConnection, localHttpHost1, paramHttpRoute.getLocalAddress(), paramHttpContext, paramHttpParams);
    paramHttpRoute = this.tracker;
    if (paramHttpRoute == null)
      throw new IOException("Request aborted");
    if (localHttpHost2 == null)
    {
      paramHttpRoute.connectTarget(this.connection.isSecure());
      return;
    }
    paramHttpRoute.connectProxy(localHttpHost2, this.connection.isSecure());
  }

  public void setState(Object paramObject)
  {
    this.state = paramObject;
  }

  protected void shutdownEntry()
  {
    this.tracker = null;
    this.state = null;
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Next proxy must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker != null) && (this.tracker.isConnected()))
    {
      this.connection.update(null, paramHttpHost, paramBoolean, paramHttpParams);
      this.tracker.tunnelProxy(paramHttpHost, paramBoolean);
      return;
    }
    throw new IllegalStateException("Connection not open.");
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if ((this.tracker != null) && (this.tracker.isConnected()))
    {
      if (this.tracker.isTunnelled())
        throw new IllegalStateException("Connection is already tunnelled.");
      this.connection.update(null, this.tracker.getTargetHost(), paramBoolean, paramHttpParams);
      this.tracker.tunnelTarget(paramBoolean);
      return;
    }
    throw new IllegalStateException("Connection not open.");
  }
}