package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public final class HttpRoute
  implements RouteInfo, Cloneable
{
  private static final HttpHost[] EMPTY_HTTP_HOST_ARRAY = new HttpHost[0];
  private final RouteInfo.LayerType layered;
  private final InetAddress localAddress;
  private final HttpHost[] proxyChain;
  private final boolean secure;
  private final HttpHost targetHost;
  private final RouteInfo.TunnelType tunnelled;

  private HttpRoute(InetAddress paramInetAddress, HttpHost paramHttpHost, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramArrayOfHttpHost == null)
      throw new IllegalArgumentException("Proxies may not be null.");
    if ((paramTunnelType == RouteInfo.TunnelType.TUNNELLED) && (paramArrayOfHttpHost.length == 0))
      throw new IllegalArgumentException("Proxy required if tunnelled.");
    RouteInfo.TunnelType localTunnelType = paramTunnelType;
    if (paramTunnelType == null)
      localTunnelType = RouteInfo.TunnelType.PLAIN;
    paramTunnelType = paramLayerType;
    if (paramLayerType == null)
      paramTunnelType = RouteInfo.LayerType.PLAIN;
    this.targetHost = paramHttpHost;
    this.localAddress = paramInetAddress;
    this.proxyChain = paramArrayOfHttpHost;
    this.secure = paramBoolean;
    this.tunnelled = localTunnelType;
    this.layered = paramTunnelType;
  }

  public HttpRoute(HttpHost paramHttpHost)
  {
    this(null, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean)
  {
    this(paramInetAddress, paramHttpHost1, arrayOfHttpHost, paramBoolean, localTunnelType, localLayerType);
    if (paramHttpHost2 == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
  }

  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramInetAddress, paramHttpHost1, toChain(paramHttpHost2), paramBoolean, paramTunnelType, paramLayerType);
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, boolean paramBoolean)
  {
    this(paramInetAddress, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, paramBoolean, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramInetAddress, paramHttpHost, toChain(paramArrayOfHttpHost), paramBoolean, paramTunnelType, paramLayerType);
  }

  private static HttpHost[] toChain(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      return EMPTY_HTTP_HOST_ARRAY;
    return new HttpHost[] { paramHttpHost };
  }

  private static HttpHost[] toChain(HttpHost[] paramArrayOfHttpHost)
  {
    if ((paramArrayOfHttpHost != null) && (paramArrayOfHttpHost.length >= 1))
    {
      int j = paramArrayOfHttpHost.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfHttpHost[i] == null)
          throw new IllegalArgumentException("Proxy chain may not contain null elements.");
        i += 1;
      }
      HttpHost[] arrayOfHttpHost = new HttpHost[paramArrayOfHttpHost.length];
      System.arraycopy(paramArrayOfHttpHost, 0, arrayOfHttpHost, 0, paramArrayOfHttpHost.length);
      return arrayOfHttpHost;
    }
    return EMPTY_HTTP_HOST_ARRAY;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof HttpRoute))
    {
      paramObject = (HttpRoute)paramObject;
      return (this.secure == paramObject.secure) && (this.tunnelled == paramObject.tunnelled) && (this.layered == paramObject.layered) && (LangUtils.equals(this.targetHost, paramObject.targetHost)) && (LangUtils.equals(this.localAddress, paramObject.localAddress)) && (LangUtils.equals(this.proxyChain, paramObject.proxyChain));
    }
    return false;
  }

  public final int getHopCount()
  {
    return this.proxyChain.length + 1;
  }

  public final HttpHost getHopTarget(int paramInt)
  {
    StringBuilder localStringBuilder;
    if (paramInt < 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Hop index must not be negative: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    int i = getHopCount();
    if (paramInt >= i)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Hop index ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" exceeds route length ");
      localStringBuilder.append(i);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (paramInt < i - 1)
      return this.proxyChain[paramInt];
    return this.targetHost;
  }

  public final RouteInfo.LayerType getLayerType()
  {
    return this.layered;
  }

  public final InetAddress getLocalAddress()
  {
    return this.localAddress;
  }

  public final HttpHost getProxyHost()
  {
    if (this.proxyChain.length == 0)
      return null;
    return this.proxyChain[0];
  }

  public final HttpHost getTargetHost()
  {
    return this.targetHost;
  }

  public final RouteInfo.TunnelType getTunnelType()
  {
    return this.tunnelled;
  }

  public final int hashCode()
  {
    int j = LangUtils.hashCode(LangUtils.hashCode(17, this.targetHost), this.localAddress);
    int i = 0;
    while (i < this.proxyChain.length)
    {
      j = LangUtils.hashCode(j, this.proxyChain[i]);
      i += 1;
    }
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(j, this.secure), this.tunnelled), this.layered);
  }

  public final boolean isLayered()
  {
    return this.layered == RouteInfo.LayerType.LAYERED;
  }

  public final boolean isSecure()
  {
    return this.secure;
  }

  public final boolean isTunnelled()
  {
    return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getHopCount() * 30 + 50);
    localStringBuilder.append("HttpRoute[");
    if (this.localAddress != null)
    {
      localStringBuilder.append(this.localAddress);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED)
      localStringBuilder.append('t');
    if (this.layered == RouteInfo.LayerType.LAYERED)
      localStringBuilder.append('l');
    if (this.secure)
      localStringBuilder.append('s');
    localStringBuilder.append("}->");
    HttpHost[] arrayOfHttpHost = this.proxyChain;
    int j = arrayOfHttpHost.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(arrayOfHttpHost[i]);
      localStringBuilder.append("->");
      i += 1;
    }
    localStringBuilder.append(this.targetHost);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}