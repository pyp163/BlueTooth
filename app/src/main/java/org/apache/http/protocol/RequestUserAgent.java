package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.params.HttpProtocolParams;

public class RequestUserAgent
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpRequest.containsHeader("User-Agent"))
    {
      paramHttpContext = HttpProtocolParams.getUserAgent(paramHttpRequest.getParams());
      if (paramHttpContext != null)
        paramHttpRequest.addHeader("User-Agent", paramHttpContext);
    }
  }
}