package org.apache.http.client.methods;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public abstract class HttpRequestBase extends AbstractHttpMessage
  implements HttpUriRequest, AbortableHttpRequest, Cloneable
{
  private Lock abortLock = new ReentrantLock();
  private boolean aborted;
  private ClientConnectionRequest connRequest;
  private ConnectionReleaseTrigger releaseTrigger;
  private URI uri;

  // ERROR //
  public void abort()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 30	org/apache/http/client/methods/HttpRequestBase:abortLock	Ljava/util/concurrent/locks/Lock;
    //   4: invokeinterface 39 1 0
    //   9: aload_0
    //   10: getfield 41	org/apache/http/client/methods/HttpRequestBase:aborted	Z
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq +13 -> 28
    //   18: aload_0
    //   19: getfield 30	org/apache/http/client/methods/HttpRequestBase:abortLock	Ljava/util/concurrent/locks/Lock;
    //   22: invokeinterface 44 1 0
    //   27: return
    //   28: aload_0
    //   29: iconst_1
    //   30: putfield 41	org/apache/http/client/methods/HttpRequestBase:aborted	Z
    //   33: aload_0
    //   34: getfield 46	org/apache/http/client/methods/HttpRequestBase:connRequest	Lorg/apache/http/conn/ClientConnectionRequest;
    //   37: astore_2
    //   38: aload_0
    //   39: getfield 48	org/apache/http/client/methods/HttpRequestBase:releaseTrigger	Lorg/apache/http/conn/ConnectionReleaseTrigger;
    //   42: astore_3
    //   43: aload_0
    //   44: getfield 30	org/apache/http/client/methods/HttpRequestBase:abortLock	Ljava/util/concurrent/locks/Lock;
    //   47: invokeinterface 44 1 0
    //   52: aload_2
    //   53: ifnull +9 -> 62
    //   56: aload_2
    //   57: invokeinterface 53 1 0
    //   62: aload_3
    //   63: ifnull +9 -> 72
    //   66: aload_3
    //   67: invokeinterface 58 1 0
    //   72: return
    //   73: astore_2
    //   74: aload_0
    //   75: getfield 30	org/apache/http/client/methods/HttpRequestBase:abortLock	Ljava/util/concurrent/locks/Lock;
    //   78: invokeinterface 44 1 0
    //   83: aload_2
    //   84: athrow
    //   85: astore_2
    //   86: return
    //
    // Exception table:
    //   from	to	target	type
    //   9	14	73	finally
    //   28	43	73	finally
    //   66	72	85	java/io/IOException
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    HttpRequestBase localHttpRequestBase = (HttpRequestBase)super.clone();
    localHttpRequestBase.abortLock = new ReentrantLock();
    localHttpRequestBase.aborted = false;
    localHttpRequestBase.releaseTrigger = null;
    localHttpRequestBase.connRequest = null;
    localHttpRequestBase.headergroup = ((HeaderGroup)CloneUtils.clone(this.headergroup));
    localHttpRequestBase.params = ((HttpParams)CloneUtils.clone(this.params));
    return localHttpRequestBase;
  }

  public abstract String getMethod();

  public ProtocolVersion getProtocolVersion()
  {
    return HttpProtocolParams.getVersion(getParams());
  }

  public RequestLine getRequestLine()
  {
    String str = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    Object localObject1 = getURI();
    if (localObject1 != null)
      localObject1 = ((URI)localObject1).toASCIIString();
    else
      localObject1 = null;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (((String)localObject1).length() != 0);
    }
    else
    {
      localObject2 = "/";
    }
    return new BasicRequestLine(str, (String)localObject2, localProtocolVersion);
  }

  public URI getURI()
  {
    return this.uri;
  }

  public boolean isAborted()
  {
    return this.aborted;
  }

  public void setConnectionRequest(ClientConnectionRequest paramClientConnectionRequest)
    throws IOException
  {
    this.abortLock.lock();
    try
    {
      if (this.aborted)
        throw new IOException("Request already aborted");
      this.releaseTrigger = null;
      this.connRequest = paramClientConnectionRequest;
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw paramClientConnectionRequest;
  }

  public void setReleaseTrigger(ConnectionReleaseTrigger paramConnectionReleaseTrigger)
    throws IOException
  {
    this.abortLock.lock();
    try
    {
      if (this.aborted)
        throw new IOException("Request already aborted");
      this.connRequest = null;
      this.releaseTrigger = paramConnectionReleaseTrigger;
      return;
    }
    finally
    {
      this.abortLock.unlock();
    }
    throw paramConnectionReleaseTrigger;
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }
}