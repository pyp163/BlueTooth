package org.apache.http.impl.client;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

@Deprecated
@Immutable
class DefaultRedirectStrategyAdaptor
  implements RedirectStrategy
{
  private final RedirectHandler handler;

  @Deprecated
  public DefaultRedirectStrategyAdaptor(RedirectHandler paramRedirectHandler)
  {
    this.handler = paramRedirectHandler;
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    paramHttpResponse = this.handler.getLocationURI(paramHttpResponse, paramHttpContext);
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("HEAD"))
      return new HttpHead(paramHttpResponse);
    return new HttpGet(paramHttpResponse);
  }

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    return this.handler.isRedirectRequested(paramHttpResponse, paramHttpContext);
  }
}