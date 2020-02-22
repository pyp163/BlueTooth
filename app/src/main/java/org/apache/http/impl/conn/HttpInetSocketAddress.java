package org.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.apache.http.HttpHost;

class HttpInetSocketAddress extends InetSocketAddress
{
  private static final long serialVersionUID = -6650701828361907957L;
  private final HttpHost host;

  public HttpInetSocketAddress(HttpHost paramHttpHost, InetAddress paramInetAddress, int paramInt)
  {
    super(paramInetAddress, paramInt);
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.host = paramHttpHost;
  }

  public HttpHost getHost()
  {
    return this.host;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.host.getHostName());
    localStringBuilder.append(":");
    localStringBuilder.append(getPort());
    return localStringBuilder.toString();
  }
}