package org.apache.http.impl.io;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.params.HttpParams;

public class SocketOutputBuffer extends AbstractSessionOutputBuffer
{
  public SocketOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    int i = paramInt;
    if (paramInt < 0)
      i = paramSocket.getSendBufferSize();
    paramInt = i;
    if (i < 1024)
      paramInt = 1024;
    init(paramSocket.getOutputStream(), paramInt, paramHttpParams);
  }
}