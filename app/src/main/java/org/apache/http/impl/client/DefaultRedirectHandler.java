package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
@Immutable
public class DefaultRedirectHandler
  implements RedirectHandler
{
  private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
  private final Log log = LogFactory.getLog(getClass());

  public URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    Object localObject1 = paramHttpResponse.getFirstHeader("location");
    if (localObject1 == null)
    {
      paramHttpContext = new StringBuilder();
      paramHttpContext.append("Received redirect response ");
      paramHttpContext.append(paramHttpResponse.getStatusLine());
      paramHttpContext.append(" but no location header");
      throw new ProtocolException(paramHttpContext.toString());
    }
    Object localObject2 = ((Header)localObject1).getValue();
    Object localObject3;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Redirect requested to location '");
      ((StringBuilder)localObject3).append((String)localObject2);
      ((StringBuilder)localObject3).append("'");
      ((Log)localObject1).debug(((StringBuilder)localObject3).toString());
    }
    try
    {
      localObject1 = new URI((String)localObject2);
      localObject2 = paramHttpResponse.getParams();
      paramHttpResponse = (HttpResponse)localObject1;
      if (!((URI)localObject1).isAbsolute())
      {
        if (((HttpParams)localObject2).isParameterTrue("http.protocol.reject-relative-redirect"))
        {
          paramHttpResponse = new StringBuilder();
          paramHttpResponse.append("Relative redirect location '");
          paramHttpResponse.append(localObject1);
          paramHttpResponse.append("' not allowed");
          throw new ProtocolException(paramHttpResponse.toString());
        }
        paramHttpResponse = (HttpHost)paramHttpContext.getAttribute("http.target_host");
        if (paramHttpResponse == null)
          throw new IllegalStateException("Target host not available in the HTTP context");
        localObject3 = (HttpRequest)paramHttpContext.getAttribute("http.request");
        try
        {
          paramHttpResponse = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest)localObject3).getRequestLine().getUri()), paramHttpResponse, true), (URI)localObject1);
        }
        catch (URISyntaxException paramHttpResponse)
        {
          throw new ProtocolException(paramHttpResponse.getMessage(), paramHttpResponse);
        }
      }
      if (((HttpParams)localObject2).isParameterFalse("http.protocol.allow-circular-redirects"))
      {
        localObject2 = (RedirectLocations)paramHttpContext.getAttribute("http.protocol.redirect-locations");
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new RedirectLocations();
          paramHttpContext.setAttribute("http.protocol.redirect-locations", localObject1);
        }
        if (paramHttpResponse.getFragment() != null)
          try
          {
            paramHttpContext = URIUtils.rewriteURI(paramHttpResponse, new HttpHost(paramHttpResponse.getHost(), paramHttpResponse.getPort(), paramHttpResponse.getScheme()), true);
          }
          catch (URISyntaxException paramHttpResponse)
          {
            throw new ProtocolException(paramHttpResponse.getMessage(), paramHttpResponse);
          }
        else
          paramHttpContext = paramHttpResponse;
        if (((RedirectLocations)localObject1).contains(paramHttpContext))
        {
          paramHttpResponse = new StringBuilder();
          paramHttpResponse.append("Circular redirect to '");
          paramHttpResponse.append(paramHttpContext);
          paramHttpResponse.append("'");
          throw new CircularRedirectException(paramHttpResponse.toString());
        }
        ((RedirectLocations)localObject1).add(paramHttpContext);
      }
      return paramHttpResponse;
    }
    catch (URISyntaxException paramHttpResponse)
    {
      paramHttpContext = new StringBuilder();
      paramHttpContext.append("Invalid redirect URI: ");
      paramHttpContext.append((String)localObject2);
    }
    throw new ProtocolException(paramHttpContext.toString(), paramHttpResponse);
  }

  public boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    boolean bool = true;
    if (i != 307)
      switch (i)
      {
      default:
        return false;
      case 303:
        return true;
      case 301:
      case 302:
      }
    paramHttpResponse = ((HttpRequest)paramHttpContext.getAttribute("http.request")).getRequestLine().getMethod();
    if (!paramHttpResponse.equalsIgnoreCase("GET"))
    {
      if (paramHttpResponse.equalsIgnoreCase("HEAD"))
        return true;
      bool = false;
    }
    return bool;
  }
}