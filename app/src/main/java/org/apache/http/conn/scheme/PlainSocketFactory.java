package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@Immutable
public class PlainSocketFactory
  implements SocketFactory, SchemeSocketFactory
{
  private final HostNameResolver nameResolver;

  public PlainSocketFactory()
  {
    this.nameResolver = null;
  }

  @Deprecated
  public PlainSocketFactory(HostNameResolver paramHostNameResolver)
  {
    this.nameResolver = paramHostNameResolver;
  }

  public static PlainSocketFactory getSocketFactory()
  {
    return new PlainSocketFactory();
  }

  @Deprecated
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
    if (this.nameResolver != null)
      paramString = this.nameResolver.resolve(paramString);
    else
      paramString = InetAddress.getByName(paramString);
    return connectSocket(paramSocket, new InetSocketAddress(paramString, paramInt1), paramInetAddress, paramHttpParams);
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, ConnectTimeoutException
  {
    if (paramInetSocketAddress1 == null)
      throw new IllegalArgumentException("Remote address may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Socket localSocket = paramSocket;
    if (paramSocket == null)
      localSocket = createSocket();
    if (paramInetSocketAddress2 != null)
    {
      localSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      localSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      localSocket.setSoTimeout(j);
      localSocket.connect(paramInetSocketAddress1, i);
      return localSocket;
      label94: paramSocket = new StringBuilder();
      paramSocket.append("Connect to ");
      paramSocket.append(paramInetSocketAddress1);
      paramSocket.append(" timed out");
      throw new ConnectTimeoutException(paramSocket.toString());
    }
    catch (SocketTimeoutException paramSocket)
    {
      break label94;
    }
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public Socket createSocket(HttpParams paramHttpParams)
  {
    return new Socket();
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}