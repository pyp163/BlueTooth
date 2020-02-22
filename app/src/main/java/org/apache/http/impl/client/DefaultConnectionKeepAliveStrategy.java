package org.apache.http.impl.client;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultConnectionKeepAliveStrategy
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    paramHttpResponse = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (true)
    {
      Object localObject;
      if (paramHttpResponse.hasNext())
      {
        localObject = paramHttpResponse.nextElement();
        paramHttpContext = ((HeaderElement)localObject).getName();
        localObject = ((HeaderElement)localObject).getValue();
        if ((localObject == null) || (!paramHttpContext.equalsIgnoreCase("timeout")));
      }
      else
      {
        try
        {
          long l = Long.parseLong((String)localObject);
          return l * 1000L;
          return -1L;
        }
        catch (NumberFormatException paramHttpContext)
        {
        }
      }
    }
  }
}