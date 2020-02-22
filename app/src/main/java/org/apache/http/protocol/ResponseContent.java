package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public class ResponseContent
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpResponse.containsHeader("Transfer-Encoding"))
      throw new ProtocolException("Transfer-encoding header already present");
    if (paramHttpResponse.containsHeader("Content-Length"))
      throw new ProtocolException("Content-Length header already present");
    paramHttpContext = paramHttpResponse.getStatusLine().getProtocolVersion();
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity != null)
    {
      long l = localHttpEntity.getContentLength();
      if ((localHttpEntity.isChunked()) && (!paramHttpContext.lessEquals(HttpVersion.HTTP_1_0)))
        paramHttpResponse.addHeader("Transfer-Encoding", "chunked");
      else if (l >= 0L)
        paramHttpResponse.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
      if ((localHttpEntity.getContentType() != null) && (!paramHttpResponse.containsHeader("Content-Type")))
        paramHttpResponse.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() != null) && (!paramHttpResponse.containsHeader("Content-Encoding")))
        paramHttpResponse.addHeader(localHttpEntity.getContentEncoding());
    }
    else
    {
      int i = paramHttpResponse.getStatusLine().getStatusCode();
      if ((i != 204) && (i != 304) && (i != 205))
        paramHttpResponse.addHeader("Content-Length", "0");
    }
  }
}