package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@ThreadSafe
public class DefaultClientConnectionOperator
  implements ClientConnectionOperator
{
  private final Log log = LogFactory.getLog(getClass());
  protected final SchemeRegistry schemeRegistry;

  public DefaultClientConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry amy not be null");
    this.schemeRegistry = paramSchemeRegistry;
  }

  public OperatedClientConnection createConnection()
  {
    return new DefaultClientConnection();
  }

  public void openConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, InetAddress paramInetAddress, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must not be open");
    Object localObject1 = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    SchemeSocketFactory localSchemeSocketFactory = ((Scheme)localObject1).getSchemeSocketFactory();
    InetAddress[] arrayOfInetAddress = resolveHostname(paramHttpHost.getHostName());
    int k = ((Scheme)localObject1).resolvePort(paramHttpHost.getPort());
    int i = 0;
    while (true)
    {
      Object localObject3 = paramInetAddress;
      if (i >= arrayOfInetAddress.length)
        break;
      localObject1 = arrayOfInetAddress[i];
      int m = arrayOfInetAddress.length;
      int j = 1;
      if (i != m - 1)
        j = 0;
      Object localObject2 = localSchemeSocketFactory.createSocket(paramHttpParams);
      paramOperatedClientConnection.opening((Socket)localObject2, paramHttpHost);
      HttpInetSocketAddress localHttpInetSocketAddress = new HttpInetSocketAddress(paramHttpHost, (InetAddress)localObject1, k);
      localObject1 = null;
      if (localObject3 != null)
        localObject1 = new InetSocketAddress((InetAddress)localObject3, 0);
      if (this.log.isDebugEnabled())
      {
        localObject3 = this.log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Connecting to ");
        localStringBuilder.append(localHttpInetSocketAddress);
        ((Log)localObject3).debug(localStringBuilder.toString());
      }
      try
      {
        localObject3 = localSchemeSocketFactory.connectSocket((Socket)localObject2, localHttpInetSocketAddress, (InetSocketAddress)localObject1, paramHttpParams);
        localObject1 = localObject2;
        if (localObject2 != localObject3)
        {
          paramOperatedClientConnection.opening((Socket)localObject3, paramHttpHost);
          localObject1 = localObject3;
        }
        try
        {
          prepareSocket((Socket)localObject1, paramHttpContext, paramHttpParams);
          paramOperatedClientConnection.openCompleted(localSchemeSocketFactory.isSecure((Socket)localObject1), paramHttpParams);
          return;
        }
        catch (ConnectTimeoutException localConnectTimeoutException1)
        {
        }
        catch (ConnectException localConnectException1)
        {
        }
      }
      catch (ConnectTimeoutException localConnectTimeoutException2)
      {
        if (j == 0)
          break label363;
        throw localConnectTimeoutException2;
      }
      catch (ConnectException localConnectException2)
      {
      }
      if (j != 0)
        throw new HttpHostConnectException(paramHttpHost, localConnectException2);
      label363: if (this.log.isDebugEnabled())
      {
        Log localLog = this.log;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Connect to ");
        ((StringBuilder)localObject2).append(localHttpInetSocketAddress);
        ((StringBuilder)localObject2).append(" timed out. ");
        ((StringBuilder)localObject2).append("Connection will be retried using another IP address");
        localLog.debug(((StringBuilder)localObject2).toString());
      }
      i += 1;
    }
  }

  protected void prepareSocket(Socket paramSocket, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
    {
      boolean bool;
      if (i > 0)
        bool = true;
      else
        bool = false;
      paramSocket.setSoLinger(bool, i);
    }
  }

  protected InetAddress[] resolveHostname(String paramString)
    throws UnknownHostException
  {
    return InetAddress.getAllByName(paramString);
  }

  public void updateSecureConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (!paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must be open");
    Object localObject = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    if (!(((Scheme)localObject).getSchemeSocketFactory() instanceof LayeredSchemeSocketFactory))
    {
      paramOperatedClientConnection = new StringBuilder();
      paramOperatedClientConnection.append("Target scheme (");
      paramOperatedClientConnection.append(((Scheme)localObject).getName());
      paramOperatedClientConnection.append(") must have layered socket factory.");
      throw new IllegalArgumentException(paramOperatedClientConnection.toString());
    }
    localObject = (LayeredSchemeSocketFactory)((Scheme)localObject).getSchemeSocketFactory();
    try
    {
      Socket localSocket = ((LayeredSchemeSocketFactory)localObject).createLayeredSocket(paramOperatedClientConnection.getSocket(), paramHttpHost.getHostName(), paramHttpHost.getPort(), true);
      prepareSocket(localSocket, paramHttpContext, paramHttpParams);
      paramOperatedClientConnection.update(localSocket, paramHttpHost, ((LayeredSchemeSocketFactory)localObject).isSecure(localSocket), paramHttpParams);
      return;
    }
    catch (ConnectException paramOperatedClientConnection)
    {
    }
    throw new HttpHostConnectException(paramHttpHost, paramOperatedClientConnection);
  }
}