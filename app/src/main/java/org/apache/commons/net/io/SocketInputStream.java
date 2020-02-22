package org.apache.commons.net.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketInputStream extends FilterInputStream
{
  private final Socket __socket;

  public SocketInputStream(Socket paramSocket, InputStream paramInputStream)
  {
    super(paramInputStream);
    this.__socket = paramSocket;
  }

  public void close()
    throws IOException
  {
    super.close();
    this.__socket.close();
  }
}