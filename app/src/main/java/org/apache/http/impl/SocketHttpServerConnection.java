package org.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.http.HttpInetConnection;
import org.apache.http.impl.io.SocketInputBuffer;
import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class SocketHttpServerConnection extends AbstractHttpServerConnection
  implements HttpInetConnection
{
  private volatile boolean open;
  private volatile Socket socket = null;

  protected void assertNotOpen()
  {
    if (this.open)
      throw new IllegalStateException("Connection is already open");
  }

  protected void assertOpen()
  {
    if (!this.open)
      throw new IllegalStateException("Connection is not open");
  }

  protected void bind(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.socket = paramSocket;
    int i = HttpConnectionParams.getSocketBufferSize(paramHttpParams);
    init(createHttpDataReceiver(paramSocket, i, paramHttpParams), createHttpDataTransmitter(paramSocket, i, paramHttpParams), paramHttpParams);
    this.open = true;
  }

  // ERROR //
  public void close()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	org/apache/http/impl/SocketHttpServerConnection:open	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: iconst_0
    //   10: putfield 20	org/apache/http/impl/SocketHttpServerConnection:open	Z
    //   13: aload_0
    //   14: iconst_0
    //   15: putfield 20	org/apache/http/impl/SocketHttpServerConnection:open	Z
    //   18: aload_0
    //   19: getfield 16	org/apache/http/impl/SocketHttpServerConnection:socket	Ljava/net/Socket;
    //   22: astore_1
    //   23: aload_0
    //   24: invokevirtual 66	org/apache/http/impl/SocketHttpServerConnection:doFlush	()V
    //   27: aload_1
    //   28: invokevirtual 71	java/net/Socket:shutdownOutput	()V
    //   31: aload_1
    //   32: invokevirtual 74	java/net/Socket:shutdownInput	()V
    //   35: aload_1
    //   36: invokevirtual 76	java/net/Socket:close	()V
    //   39: return
    //   40: astore_2
    //   41: aload_1
    //   42: invokevirtual 76	java/net/Socket:close	()V
    //   45: aload_2
    //   46: athrow
    //   47: astore_2
    //   48: goto -17 -> 31
    //   51: astore_2
    //   52: goto -17 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   23	27	40	finally
    //   27	31	40	finally
    //   31	35	40	finally
    //   27	31	47	java/io/IOException
    //   27	31	51	java/lang/UnsupportedOperationException
    //   31	35	51	java/io/IOException
    //   31	35	51	java/lang/UnsupportedOperationException
  }

  protected SessionInputBuffer createHttpDataReceiver(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return createSessionInputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected SessionOutputBuffer createHttpDataTransmitter(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return createSessionOutputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketInputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketOutputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  public InetAddress getLocalAddress()
  {
    if (this.socket != null)
      return this.socket.getLocalAddress();
    return null;
  }

  public int getLocalPort()
  {
    if (this.socket != null)
      return this.socket.getLocalPort();
    return -1;
  }

  public InetAddress getRemoteAddress()
  {
    if (this.socket != null)
      return this.socket.getInetAddress();
    return null;
  }

  public int getRemotePort()
  {
    if (this.socket != null)
      return this.socket.getPort();
    return -1;
  }

  protected Socket getSocket()
  {
    return this.socket;
  }

  public int getSocketTimeout()
  {
    if (this.socket != null);
    try
    {
      int i = this.socket.getSoTimeout();
      return i;
      return -1;
    }
    catch (SocketException localSocketException)
    {
    }
    return -1;
  }

  public boolean isOpen()
  {
    return this.open;
  }

  public void setSocketTimeout(int paramInt)
  {
    assertOpen();
    if (this.socket != null);
    try
    {
      this.socket.setSoTimeout(paramInt);
      return;
    }
    catch (SocketException localSocketException)
    {
    }
  }

  public void shutdown()
    throws IOException
  {
    this.open = false;
    Socket localSocket = this.socket;
    if (localSocket != null)
      localSocket.close();
  }
}