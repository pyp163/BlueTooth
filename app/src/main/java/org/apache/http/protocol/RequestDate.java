package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;

public class RequestDate
  implements HttpRequestInterceptor
{
  private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null.");
    if (((paramHttpRequest instanceof HttpEntityEnclosingRequest)) && (!paramHttpRequest.containsHeader("Date")))
      paramHttpRequest.setHeader("Date", DATE_GENERATOR.getCurrentDate());
  }
}