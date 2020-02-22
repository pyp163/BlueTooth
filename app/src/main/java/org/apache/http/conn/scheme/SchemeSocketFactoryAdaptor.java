package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpParams;

@Deprecated
class SchemeSocketFactoryAdaptor
  implements SchemeSocketFactory
{
  private final SocketFactory factory;

  SchemeSocketFactoryAdaptor(SocketFactory paramSocketFactory)
  {
    this.factory = paramSocketFactory;
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    String str = paramInetSocketAddress1.getHostName();
    int j = paramInetSocketAddress1.getPort();
    int i;
    if (paramInetSocketAddress2 != null)
    {
      paramInetSocketAddress1 = paramInetSocketAddress2.getAddress();
      i = paramInetSocketAddress2.getPort();
    }
    else
    {
      paramInetSocketAddress1 = null;
      i = 0;
    }
    return this.factory.connectSocket(paramSocket, str, j, paramInetSocketAddress1, i, paramHttpParams);
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    return this.factory.createSocket();
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof SchemeSocketFactoryAdaptor))
      return this.factory.equals(((SchemeSocketFactoryAdaptor)paramObject).factory);
    return this.factory.equals(paramObject);
  }

  public SocketFactory getFactory()
  {
    return this.factory;
  }

  public int hashCode()
  {
    return this.factory.hashCode();
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    return this.factory.isSecure(paramSocket);
  }
}