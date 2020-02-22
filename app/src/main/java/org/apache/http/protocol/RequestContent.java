package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;

public class RequestContent
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      if (paramHttpRequest.containsHeader("Transfer-Encoding"))
        throw new ProtocolException("Transfer-encoding header already present");
      if (paramHttpRequest.containsHeader("Content-Length"))
        throw new ProtocolException("Content-Length header already present");
      paramHttpContext = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localHttpEntity == null)
      {
        paramHttpRequest.addHeader("Content-Length", "0");
        return;
      }
      if ((!localHttpEntity.isChunked()) && (localHttpEntity.getContentLength() >= 0L))
      {
        paramHttpRequest.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
      }
      else
      {
        if (paramHttpContext.lessEquals(HttpVersion.HTTP_1_0))
        {
          paramHttpRequest = new StringBuffer();
          paramHttpRequest.append("Chunked transfer encoding not allowed for ");
          paramHttpRequest.append(paramHttpContext);
          throw new ProtocolException(paramHttpRequest.toString());
        }
        paramHttpRequest.addHeader("Transfer-Encoding", "chunked");
      }
      if ((localHttpEntity.getContentType() != null) && (!paramHttpRequest.containsHeader("Content-Type")))
        paramHttpRequest.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() != null) && (!paramHttpRequest.containsHeader("Content-Encoding")))
        paramHttpRequest.addHeader(localHttpEntity.getContentEncoding());
    }
  }
}