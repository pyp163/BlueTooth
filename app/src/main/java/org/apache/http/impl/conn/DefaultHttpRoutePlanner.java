package org.apache.http.impl.conn;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

@ThreadSafe
public class DefaultHttpRoutePlanner
  implements HttpRoutePlanner
{
  protected final SchemeRegistry schemeRegistry;

  public DefaultHttpRoutePlanner(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalStateException("Request must not be null.");
    paramHttpContext = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (paramHttpContext != null)
      return paramHttpContext;
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null.");
    paramHttpContext = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    paramHttpRequest = ConnRouteParams.getDefaultProxy(paramHttpRequest.getParams());
    try
    {
      Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
      boolean bool = localScheme.isLayered();
      if (paramHttpRequest == null)
        return new HttpRoute(paramHttpHost, paramHttpContext, bool);
      return new HttpRoute(paramHttpHost, paramHttpContext, paramHttpRequest, bool);
    }
    catch (IllegalStateException paramHttpHost)
    {
    }
    throw new HttpException(paramHttpHost.getMessage());
  }
}