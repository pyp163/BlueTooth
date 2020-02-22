package org.apache.http.impl.client;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.routing.HttpRoute;

@NotThreadSafe
public class RoutedRequest
{
  protected final RequestWrapper request;
  protected final HttpRoute route;

  public RoutedRequest(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
  {
    this.request = paramRequestWrapper;
    this.route = paramHttpRoute;
  }

  public final RequestWrapper getRequest()
  {
    return this.request;
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}