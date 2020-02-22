package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
class LayeredSocketFactoryAdaptor extends SocketFactoryAdaptor
  implements LayeredSocketFactory
{
  private final LayeredSchemeSocketFactory factory;

  LayeredSocketFactoryAdaptor(LayeredSchemeSocketFactory paramLayeredSchemeSocketFactory)
  {
    super(paramLayeredSchemeSocketFactory);
    this.factory = paramLayeredSchemeSocketFactory;
  }

  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return this.factory.createLayeredSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}