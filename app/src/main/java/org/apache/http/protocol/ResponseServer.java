package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.params.HttpParams;

public class ResponseServer
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpResponse.containsHeader("Server"))
    {
      paramHttpContext = (String)paramHttpResponse.getParams().getParameter("http.origin-server");
      if (paramHttpContext != null)
        paramHttpResponse.addHeader("Server", paramHttpContext);
    }
  }
}