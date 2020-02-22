package org.apache.http.protocol;

import java.io.IOException;
import java.net.InetAddress;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;

public class RequestTargetHost
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
    if ((paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) && (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
      return;
    if (!paramHttpRequest.containsHeader("Host"))
    {
      HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      Object localObject = localHttpHost;
      if (localHttpHost == null)
      {
        localObject = (HttpConnection)paramHttpContext.getAttribute("http.connection");
        paramHttpContext = localHttpHost;
        if ((localObject instanceof HttpInetConnection))
        {
          paramHttpContext = (HttpInetConnection)localObject;
          localObject = paramHttpContext.getRemoteAddress();
          int i = paramHttpContext.getRemotePort();
          paramHttpContext = localHttpHost;
          if (localObject != null)
            paramHttpContext = new HttpHost(((InetAddress)localObject).getHostName(), i);
        }
        localObject = paramHttpContext;
        if (paramHttpContext == null)
        {
          if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
            return;
          throw new ProtocolException("Target host missing");
        }
      }
      paramHttpRequest.addHeader("Host", ((HttpHost)localObject).toHostString());
    }
  }
}