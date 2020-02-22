package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    paramHttpContext = (Collection)paramHttpRequest.getParams().getParameter("http.default-headers");
    if (paramHttpContext != null)
    {
      paramHttpContext = paramHttpContext.iterator();
      while (paramHttpContext.hasNext())
        paramHttpRequest.addHeader((Header)paramHttpContext.next());
    }
  }
}