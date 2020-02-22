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

public class SocketHttpClientConnection extends AbstractHttpClientConnection
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
    init(createSessionInputBuffer(paramSocket, i, paramHttpParams), createSessionOutputBuffer(paramSocket, i, paramHttpParams), paramHttpParams);
    this.open = true;
  }

  // ERROR //
  public void close()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	org/apache/http/impl/SocketHttpClientConnection:open	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: iconst_0
    //   10: putfield 20	org/apache/http/impl/SocketHttpClientConnection:open	Z
    //   13: aload_0
    //   14: getfield 16	org/apache/http/impl/SocketHttpClientConnection:socket	Ljava/net/Socket;
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 66	org/apache/http/impl/SocketHttpClientConnection:doFlush	()V
    //   22: aload_1
    //   23: invokevirtual 71	java/net/Socket:shutdownOutput	()V
    //   26: aload_1
    //   27: invokevirtual 74	java/net/Socket:shutdownInput	()V
    //   30: aload_1
    //   31: invokevirtual 76	java/net/Socket:close	()V
    //   34: return
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 76	java/net/Socket:close	()V
    //   40: aload_2
    //   41: athrow
    //   42: astore_2
    //   43: goto -17 -> 26
    //   46: astore_2
    //   47: goto -17 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   18	22	35	finally
    //   22	26	35	finally
    //   26	30	35	finally
    //   22	26	42	java/io/IOException
    //   22	26	46	java/lang/UnsupportedOperationException
    //   26	30	46	java/io/IOException
    //   26	30	46	java/lang/UnsupportedOperationException
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