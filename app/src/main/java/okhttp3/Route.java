package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import javax.annotation.Nullable;

public final class Route
{
  final Address address;
  final InetSocketAddress inetSocketAddress;
  final Proxy proxy;

  public Route(Address paramAddress, Proxy paramProxy, InetSocketAddress paramInetSocketAddress)
  {
    if (paramAddress == null)
      throw new NullPointerException("address == null");
    if (paramProxy == null)
      throw new NullPointerException("proxy == null");
    if (paramInetSocketAddress == null)
      throw new NullPointerException("inetSocketAddress == null");
    this.address = paramAddress;
    this.proxy = paramProxy;
    this.inetSocketAddress = paramInetSocketAddress;
  }

  public Address address()
  {
    return this.address;
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Route))
    {
      paramObject = (Route)paramObject;
      if ((paramObject.address.equals(this.address)) && (paramObject.proxy.equals(this.proxy)) && (paramObject.inetSocketAddress.equals(this.inetSocketAddress)))
        return true;
    }
    return false;
  }

  public int hashCode()
  {
    return ((527 + this.address.hashCode()) * 31 + this.proxy.hashCode()) * 31 + this.inetSocketAddress.hashCode();
  }

  public Proxy proxy()
  {
    return this.proxy;
  }

  public boolean requiresTunnel()
  {
    return (this.address.sslSocketFactory != null) && (this.proxy.type() == Proxy.Type.HTTP);
  }

  public InetSocketAddress socketAddress()
  {
    return this.inetSocketAddress;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Route{");
    localStringBuilder.append(this.inetSocketAddress);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}