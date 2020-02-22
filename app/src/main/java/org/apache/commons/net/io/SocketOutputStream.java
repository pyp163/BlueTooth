package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketOutputStream extends FilterOutputStream
{
  private final Socket __socket;

  public SocketOutputStream(Socket paramSocket, OutputStream paramOutputStream)
  {
    super(paramOutputStream);
    this.__socket = paramSocket;
  }

  public void close()
    throws IOException
  {
    super.close();
    this.__socket.close();
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}