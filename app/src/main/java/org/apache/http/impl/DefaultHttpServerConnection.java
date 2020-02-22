package org.apache.http.impl;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class DefaultHttpServerConnection extends SocketHttpServerConnection
{
  public void bind(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    assertNotOpen();
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
    {
      boolean bool;
      if (i > 0)
        bool = true;
      else
        bool = false;
      paramSocket.setSoLinger(bool, i);
    }
    super.bind(paramSocket, paramHttpParams);
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    if (isOpen())
      localStringBuffer.append(getRemotePort());
    else
      localStringBuffer.append("closed");
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
}