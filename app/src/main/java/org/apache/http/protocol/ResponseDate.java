package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.StatusLine;

public class ResponseDate
  implements HttpResponseInterceptor
{
  private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null.");
    if ((paramHttpResponse.getStatusLine().getStatusCode() >= 200) && (!paramHttpResponse.containsHeader("Date")))
      paramHttpResponse.setHeader("Date", DATE_GENERATOR.getCurrentDate());
  }
}