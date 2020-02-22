package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

@Deprecated
class SocketFactoryAdaptor
  implements SocketFactory
{
  private final SchemeSocketFactory factory;

  SocketFactoryAdaptor(SchemeSocketFactory paramSchemeSocketFactory)
  {
    this.factory = paramSchemeSocketFactory;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    if ((paramInetAddress == null) && (paramInt2 <= 0))
    {
      paramInetAddress = null;
    }
    else
    {
      int i = paramInt2;
      if (paramInt2 < 0)
        i = 0;
      paramInetAddress = new InetSocketAddress(paramInetAddress, i);
    }
    paramString = new InetSocketAddress(InetAddress.getByName(paramString), paramInt1);
    return this.factory.connectSocket(paramSocket, paramString, paramInetAddress, paramHttpParams);
  }

  public Socket createSocket()
    throws IOException
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    return this.factory.createSocket(localBasicHttpParams);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof SocketFactoryAdaptor))
      return this.factory.equals(((SocketFactoryAdaptor)paramObject).factory);
    return this.factory.equals(paramObject);
  }

  public SchemeSocketFactory getFactory()
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