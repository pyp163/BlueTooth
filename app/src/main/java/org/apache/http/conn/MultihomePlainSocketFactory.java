package org.apache.http.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@Deprecated
@Immutable
public final class MultihomePlainSocketFactory
  implements SocketFactory
{
  private static final MultihomePlainSocketFactory DEFAULT_FACTORY = new MultihomePlainSocketFactory();

  public static MultihomePlainSocketFactory getSocketFactory()
  {
    return DEFAULT_FACTORY;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null.");
    Socket localSocket = paramSocket;
    if (paramSocket == null)
      localSocket = createSocket();
    if ((paramInetAddress != null) || (paramInt2 > 0))
    {
      int i = paramInt2;
      if (paramInt2 < 0)
        i = 0;
      localSocket.bind(new InetSocketAddress(paramInetAddress, i));
    }
    paramInt2 = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    paramSocket = InetAddress.getAllByName(paramString);
    paramString = new ArrayList(paramSocket.length);
    paramString.addAll(Arrays.asList(paramSocket));
    Collections.shuffle(paramString);
    paramSocket = null;
    paramString = paramString.iterator();
    while (true)
    {
      if (paramString.hasNext())
        paramInetAddress = (InetAddress)paramString.next();
      try
      {
        try
        {
          localSocket.connect(new InetSocketAddress(paramInetAddress, paramInt1), paramInt2);
        }
        catch (IOException paramSocket)
        {
          localSocket = new Socket();
        }
        continue;
        label179: paramSocket = new StringBuilder();
        paramSocket.append("Connect to ");
        paramSocket.append(paramInetAddress);
        paramSocket.append(" timed out");
        throw new ConnectTimeoutException(paramSocket.toString());
        if (paramSocket != null)
          throw paramSocket;
        return localSocket;
      }
      catch (SocketTimeoutException paramSocket)
      {
        break label179;
      }
    }
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.getClass() != Socket.class)
      throw new IllegalArgumentException("Socket not created by this factory.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}