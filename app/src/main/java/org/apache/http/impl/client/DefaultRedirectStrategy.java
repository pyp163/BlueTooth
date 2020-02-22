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
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultRedirectStrategy
  implements RedirectStrategy
{
  public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
  private final Log log = LogFactory.getLog(getClass());

  protected URI createLocationURI(String paramString)
    throws ProtocolException
  {
    try
    {
      URI localURI = new URI(paramString);
      return localURI;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid redirect URI: ");
      localStringBuilder.append(paramString);
      throw new ProtocolException(localStringBuilder.toString(), localURISyntaxException);
    }
  }

  public URI getLocationURI(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    Object localObject1 = paramHttpResponse.getFirstHeader("location");
    if (localObject1 == null)
    {
      paramHttpRequest = new StringBuilder();
      paramHttpRequest.append("Received redirect response ");
      paramHttpRequest.append(paramHttpResponse.getStatusLine());
      paramHttpRequest.append(" but no location header");
      throw new ProtocolException(paramHttpRequest.toString());
    }
    localObject1 = ((Header)localObject1).getValue();
    if (this.log.isDebugEnabled())
    {
      localObject2 = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Redirect requested to location '");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("'");
      ((Log)localObject2).debug(localStringBuilder.toString());
    }
    localObject1 = createLocationURI((String)localObject1);
    Object localObject2 = paramHttpResponse.getParams();
    paramHttpResponse = (HttpResponse)localObject1;
    if (!((URI)localObject1).isAbsolute())
    {
      if (((HttpParams)localObject2).isParameterTrue("http.protocol.reject-relative-redirect"))
      {
        paramHttpRequest = new StringBuilder();
        paramHttpRequest.append("Relative redirect location '");
        paramHttpRequest.append(localObject1);
        paramHttpRequest.append("' not allowed");
        throw new ProtocolException(paramHttpRequest.toString());
      }
      paramHttpResponse = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (paramHttpResponse == null)
        throw new IllegalStateException("Target host not available in the HTTP context");
      try
      {
        paramHttpResponse = URIUtils.resolve(URIUtils.rewriteURI(new URI(paramHttpRequest.getRequestLine().getUri()), paramHttpResponse, true), (URI)localObject1);
      }
      catch (URISyntaxException paramHttpRequest)
      {
        throw new ProtocolException(paramHttpRequest.getMessage(), paramHttpRequest);
      }
    }
    if (((HttpParams)localObject2).isParameterFalse("http.protocol.allow-circular-redirects"))
    {
      localObject1 = (RedirectLocations)paramHttpContext.getAttribute("http.protocol.redirect-locations");
      paramHttpRequest = (HttpRequest)localObject1;
      if (localObject1 == null)
      {
        paramHttpRequest = new RedirectLocations();
        paramHttpContext.setAttribute("http.protocol.redirect-locations", paramHttpRequest);
      }
      if (paramHttpResponse.getFragment() != null)
        try
        {
          paramHttpContext = URIUtils.rewriteURI(paramHttpResponse, new HttpHost(paramHttpResponse.getHost(), paramHttpResponse.getPort(), paramHttpResponse.getScheme()), true);
        }
        catch (URISyntaxException paramHttpRequest)
        {
          throw new ProtocolException(paramHttpRequest.getMessage(), paramHttpRequest);
        }
      else
        paramHttpContext = paramHttpResponse;
      if (paramHttpRequest.contains(paramHttpContext))
      {
        paramHttpRequest = new StringBuilder();
        paramHttpRequest.append("Circular redirect to '");
        paramHttpRequest.append(paramHttpContext);
        paramHttpRequest.append("'");
        throw new CircularRedirectException(paramHttpRequest.toString());
      }
      paramHttpRequest.add(paramHttpContext);
    }
    return paramHttpResponse;
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    paramHttpResponse = getLocationURI(paramHttpRequest, paramHttpResponse, paramHttpContext);
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("HEAD"))
      return new HttpHead(paramHttpResponse);
    return new HttpGet(paramHttpResponse);
  }

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    paramHttpRequest = paramHttpRequest.getRequestLine().getMethod();
    paramHttpResponse = paramHttpResponse.getFirstHeader("location");
    boolean bool = true;
    if (i != 307)
      switch (i)
      {
      default:
        return false;
      case 303:
        return true;
      case 302:
        return ((paramHttpRequest.equalsIgnoreCase("GET")) || (paramHttpRequest.equalsIgnoreCase("HEAD"))) && (paramHttpResponse != null);
      case 301:
      }
    if (!paramHttpRequest.equalsIgnoreCase("GET"))
    {
      if (paramHttpRequest.equalsIgnoreCase("HEAD"))
        return true;
      bool = false;
    }
    return bool;
  }
}