package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;

public class RequestConnControl
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    if (!paramHttpRequest.containsHeader("Connection"))
      paramHttpRequest.addHeader("Connection", "Keep-Alive");
  }
}