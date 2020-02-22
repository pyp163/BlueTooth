package org.apache.http.conn.params;

import org.apache.http.annotation.Immutable;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

@Deprecated
@Immutable
public final class ConnManagerParams
  implements ConnManagerPNames
{
  private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute()
  {
    public int getMaxForRoute(HttpRoute paramAnonymousHttpRoute)
    {
      return 2;
    }
  };
  public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

  @Deprecated
  public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    ConnPerRoute localConnPerRoute = (ConnPerRoute)paramHttpParams.getParameter("http.conn-manager.max-per-route");
    paramHttpParams = localConnPerRoute;
    if (localConnPerRoute == null)
      paramHttpParams = DEFAULT_CONN_PER_ROUTE;
    return paramHttpParams;
  }

  @Deprecated
  public static int getMaxTotalConnections(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    return paramHttpParams.getIntParameter("http.conn-manager.max-total", 20);
  }

  @Deprecated
  public static long getTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Long localLong = (Long)paramHttpParams.getParameter("http.conn-manager.timeout");
    if (localLong != null)
      return localLong.longValue();
    return paramHttpParams.getIntParameter("http.connection.timeout", 0);
  }

  @Deprecated
  public static void setMaxConnectionsPerRoute(HttpParams paramHttpParams, ConnPerRoute paramConnPerRoute)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    paramHttpParams.setParameter("http.conn-manager.max-per-route", paramConnPerRoute);
  }

  @Deprecated
  public static void setMaxTotalConnections(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    paramHttpParams.setIntParameter("http.conn-manager.max-total", paramInt);
  }

  @Deprecated
  public static void setTimeout(HttpParams paramHttpParams, long paramLong)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}