package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestClientConnControl
  implements HttpRequestInterceptor
{
  private static final String PROXY_CONN_DIRECTIVE = "Proxy-Connection";
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
    {
      paramHttpRequest.setHeader("Proxy-Connection", "Keep-Alive");
      return;
    }
    paramHttpContext = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (paramHttpContext == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    paramHttpContext = paramHttpContext.getRoute();
    if (((paramHttpContext.getHopCount() == 1) || (paramHttpContext.isTunnelled())) && (!paramHttpRequest.containsHeader("Connection")))
      paramHttpRequest.addHeader("Connection", "Keep-Alive");
    if ((paramHttpContext.getHopCount() == 2) && (!paramHttpContext.isTunnelled()) && (!paramHttpRequest.containsHeader("Proxy-Connection")))
      paramHttpRequest.addHeader("Proxy-Connection", "Keep-Alive");
  }
}