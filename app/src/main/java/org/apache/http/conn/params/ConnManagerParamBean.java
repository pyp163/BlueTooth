package org.apache.http.conn.params;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@Deprecated
@NotThreadSafe
public class ConnManagerParamBean extends HttpAbstractParamBean
{
  public ConnManagerParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  @Deprecated
  public void setConnectionsPerRoute(ConnPerRouteBean paramConnPerRouteBean)
  {
    this.params.setParameter("http.conn-manager.max-per-route", paramConnPerRouteBean);
  }

  @Deprecated
  public void setMaxTotalConnections(int paramInt)
  {
    this.params.setIntParameter("http.conn-manager.max-total", paramInt);
  }

  public void setTimeout(long paramLong)
  {
    this.params.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}