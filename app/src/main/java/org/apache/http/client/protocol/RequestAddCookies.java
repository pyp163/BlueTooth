package org.apache.http.client.protocol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestAddCookies
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    Object localObject3 = (CookieStore)paramHttpContext.getAttribute("http.cookie-store");
    if (localObject3 == null)
    {
      this.log.debug("Cookie store not specified in HTTP context");
      return;
    }
    Object localObject2 = (CookieSpecRegistry)paramHttpContext.getAttribute("http.cookiespec-registry");
    if (localObject2 == null)
    {
      this.log.debug("CookieSpec registry not specified in HTTP context");
      return;
    }
    Object localObject6 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localObject6 == null)
    {
      this.log.debug("Target host not set in the context");
      return;
    }
    Object localObject5 = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
    if (localObject5 == null)
    {
      this.log.debug("HTTP connection not set in the context");
      return;
    }
    Object localObject4 = HttpClientParams.getCookiePolicy(paramHttpRequest.getParams());
    Object localObject1;
    Object localObject7;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject7 = new StringBuilder();
      ((StringBuilder)localObject7).append("CookieSpec selected: ");
      ((StringBuilder)localObject7).append((String)localObject4);
      ((Log)localObject1).debug(((StringBuilder)localObject7).toString());
    }
    if ((paramHttpRequest instanceof HttpUriRequest))
      localObject1 = ((HttpUriRequest)paramHttpRequest).getURI();
    try
    {
      localObject1 = new URI(paramHttpRequest.getRequestLine().getUri());
      localObject7 = ((HttpHost)localObject6).getHostName();
      int k = ((HttpHost)localObject6).getPort();
      int j = 0;
      int i = k;
      if (k < 0)
        if (((HttpRoutedConnection)localObject5).getRoute().getHopCount() == 1)
        {
          i = ((HttpRoutedConnection)localObject5).getRemotePort();
        }
        else
        {
          localObject6 = ((HttpHost)localObject6).getSchemeName();
          if (((String)localObject6).equalsIgnoreCase("http"))
            i = 80;
          else if (((String)localObject6).equalsIgnoreCase("https"))
            i = 443;
          else
            i = 0;
        }
      localObject1 = new CookieOrigin((String)localObject7, i, ((URI)localObject1).getPath(), ((HttpRoutedConnection)localObject5).isSecure());
      localObject2 = ((CookieSpecRegistry)localObject2).getCookieSpec((String)localObject4, paramHttpRequest.getParams());
      localObject5 = new ArrayList(((CookieStore)localObject3).getCookies());
      localObject3 = new ArrayList();
      localObject4 = new Date();
      localObject5 = ((List)localObject5).iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject6 = (Cookie)((Iterator)localObject5).next();
        StringBuilder localStringBuilder;
        if (!((Cookie)localObject6).isExpired((Date)localObject4))
        {
          if (((CookieSpec)localObject2).match((Cookie)localObject6, (CookieOrigin)localObject1))
          {
            if (this.log.isDebugEnabled())
            {
              localObject7 = this.log;
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("Cookie ");
              localStringBuilder.append(localObject6);
              localStringBuilder.append(" match ");
              localStringBuilder.append(localObject1);
              ((Log)localObject7).debug(localStringBuilder.toString());
            }
            ((List)localObject3).add(localObject6);
          }
        }
        else if (this.log.isDebugEnabled())
        {
          localObject7 = this.log;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Cookie ");
          localStringBuilder.append(localObject6);
          localStringBuilder.append(" expired");
          ((Log)localObject7).debug(localStringBuilder.toString());
        }
      }
      if (!((List)localObject3).isEmpty())
      {
        localObject4 = ((CookieSpec)localObject2).formatCookies((List)localObject3).iterator();
        while (((Iterator)localObject4).hasNext())
          paramHttpRequest.addHeader((Header)((Iterator)localObject4).next());
      }
      k = ((CookieSpec)localObject2).getVersion();
      if (k > 0)
      {
        localObject3 = ((List)localObject3).iterator();
        for (i = j; ((Iterator)localObject3).hasNext(); i = 1)
        {
          label728: localObject4 = (Cookie)((Iterator)localObject3).next();
          if ((k == ((Cookie)localObject4).getVersion()) && ((localObject4 instanceof SetCookie2)))
            break label728;
        }
        if (i != 0)
        {
          localObject3 = ((CookieSpec)localObject2).getVersionHeader();
          if (localObject3 != null)
            paramHttpRequest.addHeader((Header)localObject3);
        }
      }
      paramHttpContext.setAttribute("http.cookie-spec", localObject2);
      paramHttpContext.setAttribute("http.cookie-origin", localObject1);
      return;
    }
    catch (URISyntaxException paramHttpContext)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Invalid request URI: ");
      ((StringBuilder)localObject1).append(paramHttpRequest.getRequestLine().getUri());
    }
    throw new ProtocolException(((StringBuilder)localObject1).toString(), paramHttpContext);
  }
}