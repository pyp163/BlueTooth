package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.impl.SocketHttpClientConnection;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

@NotThreadSafe
public class DefaultClientConnection extends SocketHttpClientConnection
  implements OperatedClientConnection, HttpContext
{
  private final Map<String, Object> attributes = new HashMap();
  private boolean connSecure;
  private final Log headerLog = LogFactory.getLog("org.apache.http.headers");
  private final Log log = LogFactory.getLog(getClass());
  private volatile boolean shutdown;
  private volatile Socket socket;
  private HttpHost targetHost;
  private final Log wireLog = LogFactory.getLog("org.apache.http.wire");

  public void close()
    throws IOException
  {
    try
    {
      super.close();
      this.log.debug("Connection closed");
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  protected HttpMessageParser createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    return new DefaultResponseParser(paramSessionInputBuffer, null, paramHttpResponseFactory, paramHttpParams);
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    int i = paramInt;
    if (paramInt == -1)
      i = 8192;
    SessionInputBuffer localSessionInputBuffer = super.createSessionInputBuffer(paramSocket, i, paramHttpParams);
    paramSocket = localSessionInputBuffer;
    if (this.wireLog.isDebugEnabled())
      paramSocket = new LoggingSessionInputBuffer(localSessionInputBuffer, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return paramSocket;
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    int i = paramInt;
    if (paramInt == -1)
      i = 8192;
    SessionOutputBuffer localSessionOutputBuffer = super.createSessionOutputBuffer(paramSocket, i, paramHttpParams);
    paramSocket = localSessionOutputBuffer;
    if (this.wireLog.isDebugEnabled())
      paramSocket = new LoggingSessionOutputBuffer(localSessionOutputBuffer, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return paramSocket;
  }

  public Object getAttribute(String paramString)
  {
    return this.attributes.get(paramString);
  }

  public final Socket getSocket()
  {
    return this.socket;
  }

  public final HttpHost getTargetHost()
  {
    return this.targetHost;
  }

  public final boolean isSecure()
  {
    return this.connSecure;
  }

  public void openCompleted(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertNotOpen();
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    this.connSecure = paramBoolean;
    bind(this.socket, paramHttpParams);
  }

  public void opening(Socket paramSocket, HttpHost paramHttpHost)
    throws IOException
  {
    assertNotOpen();
    this.socket = paramSocket;
    this.targetHost = paramHttpHost;
    if (this.shutdown)
    {
      paramSocket.close();
      throw new IOException("Connection already shutdown");
    }
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    HttpResponse localHttpResponse = super.receiveResponseHeader();
    Object localObject;
    StringBuilder localStringBuilder1;
    if (this.log.isDebugEnabled())
    {
      localObject = this.log;
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Receiving response: ");
      localStringBuilder1.append(localHttpResponse.getStatusLine());
      ((Log)localObject).debug(localStringBuilder1.toString());
    }
    if (this.headerLog.isDebugEnabled())
    {
      localObject = this.headerLog;
      localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("<< ");
      localStringBuilder1.append(localHttpResponse.getStatusLine().toString());
      ((Log)localObject).debug(localStringBuilder1.toString());
      localObject = localHttpResponse.getAllHeaders();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder1 = localObject[i];
        Log localLog = this.headerLog;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("<< ");
        localStringBuilder2.append(localStringBuilder1.toString());
        localLog.debug(localStringBuilder2.toString());
        i += 1;
      }
    }
    return localHttpResponse;
  }

  public Object removeAttribute(String paramString)
  {
    return this.attributes.remove(paramString);
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    Log localLog;
    Object localObject;
    if (this.log.isDebugEnabled())
    {
      localLog = this.log;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Sending request: ");
      ((StringBuilder)localObject).append(paramHttpRequest.getRequestLine());
      localLog.debug(((StringBuilder)localObject).toString());
    }
    super.sendRequestHeader(paramHttpRequest);
    if (this.headerLog.isDebugEnabled())
    {
      localLog = this.headerLog;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(">> ");
      ((StringBuilder)localObject).append(paramHttpRequest.getRequestLine().toString());
      localLog.debug(((StringBuilder)localObject).toString());
      paramHttpRequest = paramHttpRequest.getAllHeaders();
      int j = paramHttpRequest.length;
      int i = 0;
      while (i < j)
      {
        localLog = paramHttpRequest[i];
        localObject = this.headerLog;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(">> ");
        localStringBuilder.append(localLog.toString());
        ((Log)localObject).debug(localStringBuilder.toString());
        i += 1;
      }
    }
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.attributes.put(paramString, paramObject);
  }

  public void shutdown()
    throws IOException
  {
    this.shutdown = true;
    try
    {
      super.shutdown();
      this.log.debug("Connection shut down");
      Socket localSocket = this.socket;
      if (localSocket != null)
      {
        localSocket.close();
        return;
      }
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error shutting down connection", localIOException);
    }
  }

  public void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertOpen();
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if (paramSocket != null)
    {
      this.socket = paramSocket;
      bind(paramSocket, paramHttpParams);
    }
    this.targetHost = paramHttpHost;
    this.connSecure = paramBoolean;
  }
}