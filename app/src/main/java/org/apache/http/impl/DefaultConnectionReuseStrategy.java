package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.TokenIterator;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;

public class DefaultConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  protected TokenIterator createTokenIterator(HeaderIterator paramHeaderIterator)
  {
    return new BasicTokenIterator(paramHeaderIterator);
  }

  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null.");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null.");
    paramHttpContext = (HttpConnection)paramHttpContext.getAttribute("http.connection");
    if ((paramHttpContext != null) && (!paramHttpContext.isOpen()))
      return false;
    paramHttpContext = paramHttpResponse.getEntity();
    ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
    if ((paramHttpContext != null) && (paramHttpContext.getContentLength() < 0L) && ((!paramHttpContext.isChunked()) || (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))))
      return false;
    HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Connection");
    paramHttpContext = localHeaderIterator;
    if (!localHeaderIterator.hasNext())
      paramHttpContext = paramHttpResponse.headerIterator("Proxy-Connection");
    if (paramHttpContext.hasNext());
    try
    {
      paramHttpResponse = createTokenIterator(paramHttpContext);
      int i = 0;
      while (paramHttpResponse.hasNext())
      {
        paramHttpContext = paramHttpResponse.nextToken();
        if ("Close".equalsIgnoreCase(paramHttpContext))
          return false;
        boolean bool = "Keep-Alive".equalsIgnoreCase(paramHttpContext);
        if (bool)
          i = 1;
      }
      if (i != 0)
        return true;
      return localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0) ^ true;
    }
    catch (ParseException paramHttpResponse)
    {
    }
    return false;
  }
}