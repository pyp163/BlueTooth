package org.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ProxySelectorRoutePlanner
  implements HttpRoutePlanner
{
  protected ProxySelector proxySelector;
  protected final SchemeRegistry schemeRegistry;

  public ProxySelectorRoutePlanner(SchemeRegistry paramSchemeRegistry, ProxySelector paramProxySelector)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.proxySelector = paramProxySelector;
  }

  protected Proxy chooseProxy(List<Proxy> paramList, HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramHttpHost = null;
      int i = 0;
      while ((paramHttpHost == null) && (i < paramList.size()))
      {
        paramHttpRequest = (Proxy)paramList.get(i);
        switch (1.$SwitchMap$java$net$Proxy$Type[paramHttpRequest.type().ordinal()])
        {
        default:
          break;
        case 1:
        case 2:
          paramHttpHost = paramHttpRequest;
        }
        i += 1;
      }
      paramList = paramHttpHost;
      if (paramHttpHost == null)
        paramList = Proxy.NO_PROXY;
      return paramList;
    }
    throw new IllegalArgumentException("Proxy list must not be empty.");
  }

  protected HttpHost determineProxy(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    ProxySelector localProxySelector2 = this.proxySelector;
    ProxySelector localProxySelector1 = localProxySelector2;
    if (localProxySelector2 == null)
      localProxySelector1 = ProxySelector.getDefault();
    localProxySelector2 = null;
    if (localProxySelector1 == null)
      return null;
    try
    {
      URI localURI = new URI(paramHttpHost.toURI());
      paramHttpRequest = chooseProxy(localProxySelector1.select(localURI), paramHttpHost, paramHttpRequest, paramHttpContext);
      paramHttpHost = localProxySelector2;
      if (paramHttpRequest.type() == Proxy.Type.HTTP)
      {
        if (!(paramHttpRequest.address() instanceof InetSocketAddress))
        {
          paramHttpHost = new StringBuilder();
          paramHttpHost.append("Unable to handle non-Inet proxy address: ");
          paramHttpHost.append(paramHttpRequest.address());
          throw new HttpException(paramHttpHost.toString());
        }
        paramHttpHost = (InetSocketAddress)paramHttpRequest.address();
        paramHttpHost = new HttpHost(getHost(paramHttpHost), paramHttpHost.getPort());
      }
      return paramHttpHost;
    }
    catch (URISyntaxException paramHttpRequest)
    {
      paramHttpContext = new StringBuilder();
      paramHttpContext.append("Cannot convert host to URI: ");
      paramHttpContext.append(paramHttpHost);
    }
    throw new HttpException(paramHttpContext.toString(), paramHttpRequest);
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalStateException("Request must not be null.");
    Object localObject = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (localObject != null)
      return localObject;
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null.");
    localObject = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    paramHttpRequest = determineProxy(paramHttpHost, paramHttpRequest, paramHttpContext);
    boolean bool = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName()).isLayered();
    if (paramHttpRequest == null)
      return new HttpRoute(paramHttpHost, (InetAddress)localObject, bool);
    return new HttpRoute(paramHttpHost, (InetAddress)localObject, paramHttpRequest, bool);
  }

  protected String getHost(InetSocketAddress paramInetSocketAddress)
  {
    if (paramInetSocketAddress.isUnresolved())
      return paramInetSocketAddress.getHostName();
    return paramInetSocketAddress.getAddress().getHostAddress();
  }

  public ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }

  public void setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
  }
}