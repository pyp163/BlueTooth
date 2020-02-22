package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
class LayeredSchemeSocketFactoryAdaptor extends SchemeSocketFactoryAdaptor
  implements LayeredSchemeSocketFactory
{
  private final LayeredSocketFactory factory;

  LayeredSchemeSocketFactoryAdaptor(LayeredSocketFactory paramLayeredSocketFactory)
  {
    super(paramLayeredSocketFactory);
    this.factory = paramLayeredSocketFactory;
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return this.factory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}