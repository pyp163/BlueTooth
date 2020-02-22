package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.util.LangUtils;

@NotThreadSafe
public final class RouteTracker
  implements RouteInfo, Cloneable
{
  private boolean connected;
  private RouteInfo.LayerType layered;
  private final InetAddress localAddress;
  private HttpHost[] proxyChain;
  private boolean secure;
  private final HttpHost targetHost;
  private RouteInfo.TunnelType tunnelled;

  public RouteTracker(HttpHost paramHttpHost, InetAddress paramInetAddress)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null.");
    this.targetHost = paramHttpHost;
    this.localAddress = paramInetAddress;
    this.tunnelled = RouteInfo.TunnelType.PLAIN;
    this.layered = RouteInfo.LayerType.PLAIN;
  }

  public RouteTracker(HttpRoute paramHttpRoute)
  {
    this(paramHttpRoute.getTargetHost(), paramHttpRoute.getLocalAddress());
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public final void connectProxy(HttpHost paramHttpHost, boolean paramBoolean)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
    if (this.connected)
      throw new IllegalStateException("Already connected.");
    this.connected = true;
    this.proxyChain = new HttpHost[] { paramHttpHost };
    this.secure = paramBoolean;
  }

  public final void connectTarget(boolean paramBoolean)
  {
    if (this.connected)
      throw new IllegalStateException("Already connected.");
    this.connected = true;
    this.secure = paramBoolean;
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof RouteTracker))
      return false;
    paramObject = (RouteTracker)paramObject;
    return (this.connected == paramObject.connected) && (this.secure == paramObject.secure) && (this.tunnelled == paramObject.tunnelled) && (this.layered == paramObject.layered) && (LangUtils.equals(this.targetHost, paramObject.targetHost)) && (LangUtils.equals(this.localAddress, paramObject.localAddress)) && (LangUtils.equals(this.proxyChain, paramObject.proxyChain));
  }

  public final int getHopCount()
  {
    if (this.connected)
    {
      if (this.proxyChain == null)
        return 1;
      return 1 + this.proxyChain.length;
    }
    return 0;
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
      localStringBuilder.append(" exceeds tracked route length ");
      localStringBuilder.append(i);
      localStringBuilder.append(".");
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
    if (this.proxyChain == null)
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
    int i = LangUtils.hashCode(LangUtils.hashCode(17, this.targetHost), this.localAddress);
    int k = i;
    if (this.proxyChain != null)
    {
      int j = 0;
      while (true)
      {
        k = i;
        if (j >= this.proxyChain.length)
          break;
        i = LangUtils.hashCode(i, this.proxyChain[j]);
        j += 1;
      }
    }
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(k, this.connected), this.secure), this.tunnelled), this.layered);
  }

  public final boolean isConnected()
  {
    return this.connected;
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

  public final void layerProtocol(boolean paramBoolean)
  {
    if (!this.connected)
      throw new IllegalStateException("No layered protocol unless connected.");
    this.layered = RouteInfo.LayerType.LAYERED;
    this.secure = paramBoolean;
  }

  public final HttpRoute toRoute()
  {
    if (!this.connected)
      return null;
    return new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(getHopCount() * 30 + 50);
    localStringBuilder.append("RouteTracker[");
    if (this.localAddress != null)
    {
      localStringBuilder.append(this.localAddress);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (this.connected)
      localStringBuilder.append('c');
    if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED)
      localStringBuilder.append('t');
    if (this.layered == RouteInfo.LayerType.LAYERED)
      localStringBuilder.append('l');
    if (this.secure)
      localStringBuilder.append('s');
    localStringBuilder.append("}->");
    if (this.proxyChain != null)
    {
      int i = 0;
      while (i < this.proxyChain.length)
      {
        localStringBuilder.append(this.proxyChain[i]);
        localStringBuilder.append("->");
        i += 1;
      }
    }
    localStringBuilder.append(this.targetHost);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }

  public final void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Proxy host may not be null.");
    if (!this.connected)
      throw new IllegalStateException("No tunnel unless connected.");
    if (this.proxyChain == null)
      throw new IllegalStateException("No proxy tunnel without proxy.");
    HttpHost[] arrayOfHttpHost = new HttpHost[this.proxyChain.length + 1];
    System.arraycopy(this.proxyChain, 0, arrayOfHttpHost, 0, this.proxyChain.length);
    arrayOfHttpHost[(arrayOfHttpHost.length - 1)] = paramHttpHost;
    this.proxyChain = arrayOfHttpHost;
    this.secure = paramBoolean;
  }

  public final void tunnelTarget(boolean paramBoolean)
  {
    if (!this.connected)
      throw new IllegalStateException("No tunnel unless connected.");
    if (this.proxyChain == null)
      throw new IllegalStateException("No tunnel without proxy.");
    this.tunnelled = RouteInfo.TunnelType.TUNNELLED;
    this.secure = paramBoolean;
  }
}