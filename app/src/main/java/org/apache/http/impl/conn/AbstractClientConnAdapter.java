package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.protocol.HttpContext;

public abstract class AbstractClientConnAdapter
  implements ManagedClientConnection, HttpContext
{
  private volatile ClientConnectionManager connManager;
  private volatile long duration;
  private volatile boolean markedReusable;
  private volatile boolean released;
  private volatile OperatedClientConnection wrappedConnection;

  protected AbstractClientConnAdapter(ClientConnectionManager paramClientConnectionManager, OperatedClientConnection paramOperatedClientConnection)
  {
    this.connManager = paramClientConnectionManager;
    this.wrappedConnection = paramOperatedClientConnection;
    this.markedReusable = false;
    this.released = false;
    this.duration = 9223372036854775807L;
  }

  // ERROR //
  public void abortConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 30	org/apache/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 30	org/apache/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   19: aload_0
    //   20: invokevirtual 41	org/apache/http/impl/conn/AbstractClientConnAdapter:unmarkReusable	()V
    //   23: aload_0
    //   24: invokevirtual 44	org/apache/http/impl/conn/AbstractClientConnAdapter:shutdown	()V
    //   27: aload_0
    //   28: getfield 24	org/apache/http/impl/conn/AbstractClientConnAdapter:connManager	Lorg/apache/http/conn/ClientConnectionManager;
    //   31: ifnull +20 -> 51
    //   34: aload_0
    //   35: getfield 24	org/apache/http/impl/conn/AbstractClientConnAdapter:connManager	Lorg/apache/http/conn/ClientConnectionManager;
    //   38: aload_0
    //   39: aload_0
    //   40: getfield 34	org/apache/http/impl/conn/AbstractClientConnAdapter:duration	J
    //   43: getstatic 50	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   46: invokeinterface 56 5 0
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: astore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_2
    //   58: athrow
    //   59: astore_2
    //   60: goto -33 -> 27
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	54	finally
    //   14	23	54	finally
    //   23	27	54	finally
    //   27	51	54	finally
    //   23	27	59	java/io/IOException
  }

  @Deprecated
  protected final void assertNotAborted()
    throws InterruptedIOException
  {
    if (isReleased())
      throw new InterruptedIOException("Connection has been shut down");
  }

  protected final void assertValid(OperatedClientConnection paramOperatedClientConnection)
    throws ConnectionShutdownException
  {
    if ((!isReleased()) && (paramOperatedClientConnection != null))
      return;
    throw new ConnectionShutdownException();
  }

  protected void detach()
  {
    try
    {
      this.wrappedConnection = null;
      this.connManager = null;
      this.duration = 9223372036854775807L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void flush()
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.flush();
  }

  public Object getAttribute(String paramString)
  {
    try
    {
      OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
      assertValid(localOperatedClientConnection);
      if ((localOperatedClientConnection instanceof HttpContext))
      {
        paramString = ((HttpContext)localOperatedClientConnection).getAttribute(paramString);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public InetAddress getLocalAddress()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getLocalAddress();
  }

  public int getLocalPort()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getLocalPort();
  }

  protected ClientConnectionManager getManager()
  {
    return this.connManager;
  }

  public HttpConnectionMetrics getMetrics()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getMetrics();
  }

  public InetAddress getRemoteAddress()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemoteAddress();
  }

  public int getRemotePort()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemotePort();
  }

  public SSLSession getSSLSession()
  {
    Object localObject = getWrappedConnection();
    assertValid((OperatedClientConnection)localObject);
    boolean bool = isOpen();
    SSLSession localSSLSession = null;
    if (!bool)
      return null;
    localObject = ((OperatedClientConnection)localObject).getSocket();
    if ((localObject instanceof SSLSocket))
      localSSLSession = ((SSLSocket)localObject).getSession();
    return localSSLSession;
  }

  public int getSocketTimeout()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getSocketTimeout();
  }

  protected OperatedClientConnection getWrappedConnection()
  {
    return this.wrappedConnection;
  }

  public boolean isMarkedReusable()
  {
    return this.markedReusable;
  }

  public boolean isOpen()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection == null)
      return false;
    return localOperatedClientConnection.isOpen();
  }

  protected boolean isReleased()
  {
    return this.released;
  }

  public boolean isResponseAvailable(int paramInt)
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.isResponseAvailable(paramInt);
  }

  public boolean isSecure()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.isSecure();
  }

  public boolean isStale()
  {
    if (isReleased())
      return true;
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection == null)
      return true;
    return localOperatedClientConnection.isStale();
  }

  public void markReusable()
  {
    this.markedReusable = true;
  }

  public void receiveResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.receiveResponseEntity(paramHttpResponse);
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    return localOperatedClientConnection.receiveResponseHeader();
  }

  public void releaseConnection()
  {
    try
    {
      boolean bool = this.released;
      if (bool)
        return;
      this.released = true;
      if (this.connManager != null)
        this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
      return;
    }
    finally
    {
    }
  }

  public Object removeAttribute(String paramString)
  {
    try
    {
      OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
      assertValid(localOperatedClientConnection);
      if ((localOperatedClientConnection instanceof HttpContext))
      {
        paramString = ((HttpContext)localOperatedClientConnection).removeAttribute(paramString);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestEntity(paramHttpEntityEnclosingRequest);
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestHeader(paramHttpRequest);
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    try
    {
      OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
      assertValid(localOperatedClientConnection);
      if ((localOperatedClientConnection instanceof HttpContext))
        ((HttpContext)localOperatedClientConnection).setAttribute(paramString, paramObject);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void setIdleDuration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong > 0L)
    {
      this.duration = paramTimeUnit.toMillis(paramLong);
      return;
    }
    this.duration = -1L;
  }

  public void setSocketTimeout(int paramInt)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.setSocketTimeout(paramInt);
  }

  public void unmarkReusable()
  {
    this.markedReusable = false;
  }
}