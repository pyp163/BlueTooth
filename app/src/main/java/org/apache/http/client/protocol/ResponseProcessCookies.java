package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.protocol.HttpContext;

@Immutable
public class ResponseProcessCookies
  implements HttpResponseInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void processCookies(HeaderIterator paramHeaderIterator, CookieSpec paramCookieSpec, CookieOrigin paramCookieOrigin, CookieStore paramCookieStore)
  {
    while (paramHeaderIterator.hasNext())
    {
      Header localHeader = paramHeaderIterator.nextHeader();
      Object localObject1;
      try
      {
        Iterator localIterator = paramCookieSpec.parse(localHeader, paramCookieOrigin).iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (Cookie)localIterator.next();
          Object localObject2;
          try
          {
            paramCookieSpec.validate((Cookie)localObject1, paramCookieOrigin);
            paramCookieStore.addCookie((Cookie)localObject1);
            if (!this.log.isDebugEnabled())
              continue;
            Log localLog = this.log;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Cookie accepted: \"");
            ((StringBuilder)localObject2).append(localObject1);
            ((StringBuilder)localObject2).append("\". ");
            localLog.debug(((StringBuilder)localObject2).toString());
          }
          catch (MalformedCookieException localMalformedCookieException2)
          {
          }
          if (this.log.isWarnEnabled())
          {
            localObject2 = this.log;
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("Cookie rejected: \"");
            localStringBuilder2.append(localObject1);
            localStringBuilder2.append("\". ");
            localStringBuilder2.append(localMalformedCookieException2.getMessage());
            ((Log)localObject2).warn(localStringBuilder2.toString());
          }
        }
      }
      catch (MalformedCookieException localMalformedCookieException1)
      {
      }
      if (this.log.isWarnEnabled())
      {
        localObject1 = this.log;
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("Invalid cookie header: \"");
        localStringBuilder1.append(localHeader);
        localStringBuilder1.append("\". ");
        localStringBuilder1.append(localMalformedCookieException1.getMessage());
        ((Log)localObject1).warn(localStringBuilder1.toString());
      }
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    CookieSpec localCookieSpec = (CookieSpec)paramHttpContext.getAttribute("http.cookie-spec");
    if (localCookieSpec == null)
    {
      this.log.debug("Cookie spec not specified in HTTP context");
      return;
    }
    CookieStore localCookieStore = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localCookieStore == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    paramHttpContext = (CookieOrigin)paramHttpContext.getAttribute("http.cookie-origin");
    if (paramHttpContext == null)
    {
      this.log.debug("Cookie origin not specified in HTTP context");
      return;
    }
    processCookies(paramHttpResponse.headerIterator("Set-Cookie"), localCookieSpec, paramHttpContext, localCookieStore);
    if (localCookieSpec.getVersion() > 0)
      processCookies(paramHttpResponse.headerIterator("Set-Cookie2"), localCookieSpec, paramHttpContext, localCookieStore);
  }
}