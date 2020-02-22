package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public class ResponseConnControl
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if ((i != 400) && (i != 408) && (i != 411) && (i != 413) && (i != 414) && (i != 503) && (i != 501))
    {
      HttpEntity localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity != null)
      {
        ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
        if ((localHttpEntity.getContentLength() < 0L) && ((!localHttpEntity.isChunked()) || (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))))
        {
          paramHttpResponse.setHeader("Connection", "Close");
          return;
        }
      }
      paramHttpContext = (HttpRequest)paramHttpContext.getAttribute("http.request");
      if (paramHttpContext != null)
      {
        paramHttpContext = paramHttpContext.getFirstHeader("Connection");
        if (paramHttpContext != null)
          paramHttpResponse.setHeader("Connection", paramHttpContext.getValue());
      }
      return;
    }
    paramHttpResponse.setHeader("Connection", "Close");
  }
}