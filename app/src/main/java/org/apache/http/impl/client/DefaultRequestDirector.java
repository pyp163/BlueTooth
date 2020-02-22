package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.client.RedirectException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.routing.BasicRouteDirector;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRouteDirector;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.EntityUtils;

@NotThreadSafe
public class DefaultRequestDirector
  implements RequestDirector
{
  protected final ClientConnectionManager connManager;
  private int execCount;
  protected final HttpProcessor httpProcessor;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  private final Log log;
  protected ManagedClientConnection managedConn;
  private int maxRedirects;
  protected final HttpParams params;
  protected final AuthenticationHandler proxyAuthHandler;
  protected final AuthState proxyAuthState;
  private int redirectCount;

  @Deprecated
  protected final RedirectHandler redirectHandler = null;
  protected final RedirectStrategy redirectStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpRequestRetryHandler retryHandler;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final HttpRoutePlanner routePlanner;
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthState targetAuthState;
  protected final UserTokenHandler userTokenHandler;
  private HttpHost virtualHost;

  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    if (paramLog == null)
      throw new IllegalArgumentException("Log may not be null.");
    if (paramHttpRequestExecutor == null)
      throw new IllegalArgumentException("Request executor may not be null.");
    if (paramClientConnectionManager == null)
      throw new IllegalArgumentException("Client connection manager may not be null.");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null.");
    if (paramConnectionKeepAliveStrategy == null)
      throw new IllegalArgumentException("Connection keep alive strategy may not be null.");
    if (paramHttpRoutePlanner == null)
      throw new IllegalArgumentException("Route planner may not be null.");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP protocol processor may not be null.");
    if (paramHttpRequestRetryHandler == null)
      throw new IllegalArgumentException("HTTP request retry handler may not be null.");
    if (paramRedirectStrategy == null)
      throw new IllegalArgumentException("Redirect strategy may not be null.");
    if (paramAuthenticationHandler1 == null)
      throw new IllegalArgumentException("Target authentication handler may not be null.");
    if (paramAuthenticationHandler2 == null)
      throw new IllegalArgumentException("Proxy authentication handler may not be null.");
    if (paramUserTokenHandler == null)
      throw new IllegalArgumentException("User token handler may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.log = paramLog;
    this.requestExec = paramHttpRequestExecutor;
    this.connManager = paramClientConnectionManager;
    this.reuseStrategy = paramConnectionReuseStrategy;
    this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
    this.routePlanner = paramHttpRoutePlanner;
    this.httpProcessor = paramHttpProcessor;
    this.retryHandler = paramHttpRequestRetryHandler;
    this.redirectStrategy = paramRedirectStrategy;
    this.targetAuthHandler = paramAuthenticationHandler1;
    this.proxyAuthHandler = paramAuthenticationHandler2;
    this.userTokenHandler = paramUserTokenHandler;
    this.params = paramHttpParams;
    this.managedConn = null;
    this.execCount = 0;
    this.redirectCount = 0;
    this.maxRedirects = this.params.getIntParameter("http.protocol.max-redirects", 100);
    this.targetAuthState = new AuthState();
    this.proxyAuthState = new AuthState();
  }

  @Deprecated
  public DefaultRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectHandler paramRedirectHandler, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    this(LogFactory.getLog(DefaultRequestDirector.class), paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, new DefaultRedirectStrategyAdaptor(paramRedirectHandler), paramAuthenticationHandler1, paramAuthenticationHandler2, paramUserTokenHandler, paramHttpParams);
  }

  private void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = this.managedConn;
    if (localManagedClientConnection != null)
    {
      this.managedConn = null;
      try
      {
        localManagedClientConnection.abortConnection();
      }
      catch (IOException localIOException2)
      {
        if (this.log.isDebugEnabled())
          this.log.debug(localIOException2.getMessage(), localIOException2);
      }
      try
      {
        localManagedClientConnection.releaseConnection();
        return;
      }
      catch (IOException localIOException1)
      {
        this.log.debug("Error releasing connection", localIOException1);
      }
    }
  }

  private void invalidateAuthIfSuccessful(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isConnectionBased()) && (localAuthScheme.isComplete()) && (paramAuthState.getCredentials() != null))
      paramAuthState.invalidate();
  }

  private void processChallenges(Map<String, Header> paramMap, AuthState paramAuthState, AuthenticationHandler paramAuthenticationHandler, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException, AuthenticationException
  {
    AuthScheme localAuthScheme2 = paramAuthState.getAuthScheme();
    AuthScheme localAuthScheme1 = localAuthScheme2;
    if (localAuthScheme2 == null)
    {
      localAuthScheme1 = paramAuthenticationHandler.selectScheme(paramMap, paramHttpResponse, paramHttpContext);
      paramAuthState.setAuthScheme(localAuthScheme1);
    }
    paramAuthState = localAuthScheme1.getSchemeName();
    paramMap = (Header)paramMap.get(paramAuthState.toLowerCase(Locale.ENGLISH));
    if (paramMap == null)
    {
      paramMap = new StringBuilder();
      paramMap.append(paramAuthState);
      paramMap.append(" authorization challenge expected, but not found");
      throw new AuthenticationException(paramMap.toString());
    }
    localAuthScheme1.processChallenge(paramMap);
    this.log.debug("Authorization challenge processed");
  }

  private void tryConnect(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramRoutedRequest = paramRoutedRequest.getRoute();
    int i = 0;
    while (true)
    {
      i += 1;
      try
      {
        if (!this.managedConn.isOpen())
          this.managedConn.open(paramRoutedRequest, paramHttpContext, this.params);
        else
          this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
        establishRoute(paramRoutedRequest, paramHttpContext);
        return;
      }
      catch (IOException localIOException1)
      {
      }
      try
      {
        this.managedConn.close();
        label75: if (this.retryHandler.retryRequest(localIOException1, i, paramHttpContext))
        {
          if (this.log.isInfoEnabled())
          {
            Log localLog = this.log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("I/O exception (");
            localStringBuilder.append(localIOException1.getClass().getName());
            localStringBuilder.append(") caught when connecting to the target host: ");
            localStringBuilder.append(localIOException1.getMessage());
            localLog.info(localStringBuilder.toString());
          }
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying connect");
          continue;
        }
        throw localIOException1;
      }
      catch (IOException localIOException2)
      {
        break label75;
      }
    }
  }

  private HttpResponse tryExecute(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    paramRoutedRequest = null;
    while (true)
    {
      this.execCount += 1;
      localRequestWrapper.incrementExecCount();
      if (!localRequestWrapper.isRepeatable())
      {
        this.log.debug("Cannot retry non-repeatable request");
        if (paramRoutedRequest != null)
          throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", paramRoutedRequest);
        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
      }
      Object localObject;
      try
      {
        if (!this.managedConn.isOpen())
          if (!localHttpRoute.isTunnelled())
          {
            this.log.debug("Reopening the direct connection.");
            this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
          }
          else
          {
            this.log.debug("Proxied connection. Need to start over.");
            return null;
          }
        if (this.log.isDebugEnabled())
        {
          paramRoutedRequest = this.log;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Attempt ");
          ((StringBuilder)localObject).append(this.execCount);
          ((StringBuilder)localObject).append(" to execute request");
          paramRoutedRequest.debug(((StringBuilder)localObject).toString());
        }
        paramRoutedRequest = this.requestExec.execute(localRequestWrapper, this.managedConn, paramHttpContext);
        return paramRoutedRequest;
      }
      catch (IOException paramRoutedRequest)
      {
        this.log.debug("Closing the connection.");
      }
      try
      {
        this.managedConn.close();
        label241: if (this.retryHandler.retryRequest(paramRoutedRequest, localRequestWrapper.getExecCount(), paramHttpContext))
        {
          if (this.log.isInfoEnabled())
          {
            localObject = this.log;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("I/O exception (");
            localStringBuilder.append(paramRoutedRequest.getClass().getName());
            localStringBuilder.append(") caught when processing request: ");
            localStringBuilder.append(paramRoutedRequest.getMessage());
            ((Log)localObject).info(localStringBuilder.toString());
          }
          if (this.log.isDebugEnabled())
            this.log.debug(paramRoutedRequest.getMessage(), paramRoutedRequest);
          this.log.info("Retrying request");
          continue;
        }
        throw paramRoutedRequest;
      }
      catch (IOException localIOException)
      {
        break label241;
      }
    }
  }

  private void updateAuthState(AuthState paramAuthState, HttpHost paramHttpHost, CredentialsProvider paramCredentialsProvider)
  {
    if (!paramAuthState.isValid())
      return;
    Object localObject = paramHttpHost.getHostName();
    int j = paramHttpHost.getPort();
    int i = j;
    if (j < 0)
      i = this.connManager.getSchemeRegistry().getScheme(paramHttpHost).getDefaultPort();
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    localObject = new AuthScope((String)localObject, i, localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
    if (this.log.isDebugEnabled())
    {
      paramHttpHost = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Authentication scope: ");
      localStringBuilder.append(localObject);
      paramHttpHost.debug(localStringBuilder.toString());
    }
    paramHttpHost = paramAuthState.getCredentials();
    if (paramHttpHost == null)
    {
      paramCredentialsProvider = paramCredentialsProvider.getCredentials((AuthScope)localObject);
      paramHttpHost = paramCredentialsProvider;
      if (this.log.isDebugEnabled())
        if (paramCredentialsProvider != null)
        {
          this.log.debug("Found credentials");
          paramHttpHost = paramCredentialsProvider;
        }
        else
        {
          this.log.debug("Credentials not found");
          paramHttpHost = paramCredentialsProvider;
        }
    }
    else if (localAuthScheme.isComplete())
    {
      this.log.debug("Authentication failed");
      paramHttpHost = null;
    }
    paramAuthState.setAuthScope((AuthScope)localObject);
    paramAuthState.setCredentials(paramHttpHost);
  }

  private RequestWrapper wrapRequest(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
      return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest);
    return new RequestWrapper(paramHttpRequest);
  }

  protected HttpRequest createConnectRequest(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    paramHttpContext = paramHttpRoute.getTargetHost();
    paramHttpRoute = paramHttpContext.getHostName();
    int j = paramHttpContext.getPort();
    int i = j;
    if (j < 0)
      i = this.connManager.getSchemeRegistry().getScheme(paramHttpContext.getSchemeName()).getDefaultPort();
    paramHttpContext = new StringBuilder(paramHttpRoute.length() + 6);
    paramHttpContext.append(paramHttpRoute);
    paramHttpContext.append(':');
    paramHttpContext.append(Integer.toString(i));
    return new BasicHttpRequest("CONNECT", paramHttpContext.toString(), HttpProtocolParams.getVersion(this.params));
  }

  protected boolean createTunnelToProxy(HttpRoute paramHttpRoute, int paramInt, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    throw new HttpException("Proxy chains are not supported.");
  }

  protected boolean createTunnelToTarget(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    HttpHost localHttpHost2 = paramHttpRoute.getTargetHost();
    Object localObject = null;
    int i = 0;
    while (i == 0)
    {
      if (!this.managedConn.isOpen())
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
      localObject = createConnectRequest(paramHttpRoute, paramHttpContext);
      ((HttpRequest)localObject).setParams(this.params);
      paramHttpContext.setAttribute("http.target_host", localHttpHost2);
      paramHttpContext.setAttribute("http.proxy_host", localHttpHost1);
      paramHttpContext.setAttribute("http.connection", this.managedConn);
      paramHttpContext.setAttribute("http.auth.target-scope", this.targetAuthState);
      paramHttpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
      paramHttpContext.setAttribute("http.request", localObject);
      this.requestExec.preProcess((HttpRequest)localObject, this.httpProcessor, paramHttpContext);
      localObject = this.requestExec.execute((HttpRequest)localObject, this.managedConn, paramHttpContext);
      ((HttpResponse)localObject).setParams(this.params);
      this.requestExec.postProcess((HttpResponse)localObject, this.httpProcessor, paramHttpContext);
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() < 200)
      {
        paramHttpRoute = new StringBuilder();
        paramHttpRoute.append("Unexpected response to CONNECT request: ");
        paramHttpRoute.append(((HttpResponse)localObject).getStatusLine());
        throw new HttpException(paramHttpRoute.toString());
      }
      CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
      if ((localCredentialsProvider != null) && (HttpClientParams.isAuthenticating(this.params)))
      {
        if (this.proxyAuthHandler.isAuthenticationRequested((HttpResponse)localObject, paramHttpContext))
        {
          this.log.debug("Proxy requested authentication");
          Map localMap = this.proxyAuthHandler.getChallenges((HttpResponse)localObject, paramHttpContext);
          try
          {
            AuthState localAuthState = this.proxyAuthState;
            AuthenticationHandler localAuthenticationHandler = this.proxyAuthHandler;
            try
            {
              processChallenges(localMap, localAuthState, localAuthenticationHandler, (HttpResponse)localObject, paramHttpContext);
            }
            catch (AuthenticationException localAuthenticationException1)
            {
            }
          }
          catch (AuthenticationException localAuthenticationException2)
          {
          }
          if (this.log.isWarnEnabled())
          {
            paramHttpRoute = this.log;
            paramHttpContext = new StringBuilder();
            paramHttpContext.append("Authentication error: ");
            paramHttpContext.append(localAuthenticationException2.getMessage());
            paramHttpRoute.warn(paramHttpContext.toString());
            break;
          }
          updateAuthState(this.proxyAuthState, localHttpHost1, localCredentialsProvider);
          if (this.proxyAuthState.getCredentials() != null)
          {
            if (this.reuseStrategy.keepAlive((HttpResponse)localObject, paramHttpContext))
            {
              this.log.debug("Connection kept alive");
              EntityUtils.consume(((HttpResponse)localObject).getEntity());
            }
            else
            {
              this.managedConn.close();
            }
            i = 0;
          }
          else
          {
            i = 1;
          }
        }
        else
        {
          this.proxyAuthState.setAuthScope(null);
        }
      }
      else
        i = 1;
    }
    if (((HttpResponse)localObject).getStatusLine().getStatusCode() > 299)
    {
      paramHttpRoute = ((HttpResponse)localObject).getEntity();
      if (paramHttpRoute != null)
        ((HttpResponse)localObject).setEntity(new BufferedHttpEntity(paramHttpRoute));
      this.managedConn.close();
      paramHttpRoute = new StringBuilder();
      paramHttpRoute.append("CONNECT refused by proxy: ");
      paramHttpRoute.append(((HttpResponse)localObject).getStatusLine());
      throw new TunnelRefusedException(paramHttpRoute.toString(), (HttpResponse)localObject);
    }
    this.managedConn.markReusable();
    return false;
  }

  protected HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    HttpHost localHttpHost = paramHttpHost;
    if (paramHttpHost == null)
      localHttpHost = (HttpHost)paramHttpRequest.getParams().getParameter("http.default-host");
    if (localHttpHost == null)
      throw new IllegalStateException("Target host must not be null, or set in parameters.");
    return this.routePlanner.determineRoute(localHttpHost, paramHttpRequest, paramHttpContext);
  }

  protected void establishRoute(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    BasicRouteDirector localBasicRouteDirector = new BasicRouteDirector();
    HttpRoute localHttpRoute;
    int i;
    do
    {
      localHttpRoute = this.managedConn.getRoute();
      i = localBasicRouteDirector.nextStep(paramHttpRoute, localHttpRoute);
      boolean bool;
      switch (i)
      {
      default:
        paramHttpRoute = new StringBuilder();
        paramHttpRoute.append("Unknown step indicator ");
        paramHttpRoute.append(i);
        paramHttpRoute.append(" from RouteDirector.");
        throw new IllegalStateException(paramHttpRoute.toString());
      case 5:
        this.managedConn.layerProtocol(paramHttpContext, this.params);
        break;
      case 4:
        int j = localHttpRoute.getHopCount() - 1;
        bool = createTunnelToProxy(paramHttpRoute, j, paramHttpContext);
        this.log.debug("Tunnel to proxy created.");
        this.managedConn.tunnelProxy(paramHttpRoute.getHopTarget(j), bool, this.params);
        break;
      case 3:
        bool = createTunnelToTarget(paramHttpRoute, paramHttpContext);
        this.log.debug("Tunnel to target created.");
        this.managedConn.tunnelTarget(bool, this.params);
        break;
      case 1:
      case 2:
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
      case 0:
      case -1:
      }
    }
    while (i > 0);
    return;
    paramHttpContext = new StringBuilder();
    paramHttpContext.append("Unable to establish route: planned = ");
    paramHttpContext.append(paramHttpRoute);
    paramHttpContext.append("; current = ");
    paramHttpContext.append(localHttpRoute);
    throw new HttpException(paramHttpContext.toString());
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    Object localObject1 = wrapRequest(paramHttpRequest);
    ((RequestWrapper)localObject1).setParams(this.params);
    Object localObject2 = determineRoute(paramHttpHost, (HttpRequest)localObject1, paramHttpContext);
    this.virtualHost = ((HttpHost)paramHttpRequest.getParams().getParameter("http.virtual-host"));
    if ((this.virtualHost != null) && (this.virtualHost.getPort() == -1))
    {
      i = paramHttpHost.getPort();
      if (i != -1)
        this.virtualHost = new HttpHost(this.virtualHost.getHostName(), i, this.virtualHost.getSchemeName());
    }
    localObject1 = new RoutedRequest((RequestWrapper)localObject1, (HttpRoute)localObject2);
    paramHttpHost = null;
    int i = 0;
    boolean bool1 = false;
    while (true)
    {
      if (i == 0);
      try
      {
        Object localObject3 = ((RoutedRequest)localObject1).getRequest();
        Object localObject5 = ((RoutedRequest)localObject1).getRoute();
        Object localObject4 = paramHttpContext.getAttribute("http.user-token");
        long l;
        if (this.managedConn == null)
        {
          paramHttpHost = this.connManager.requestConnection((HttpRoute)localObject5, localObject4);
          if ((paramHttpRequest instanceof AbortableHttpRequest))
            ((AbortableHttpRequest)paramHttpRequest).setConnectionRequest(paramHttpHost);
          l = ConnManagerParams.getTimeout(this.params);
          try
          {
            this.managedConn = paramHttpHost.getConnection(l, TimeUnit.MILLISECONDS);
            if ((HttpConnectionParams.isStaleCheckingEnabled(this.params)) && (this.managedConn.isOpen()))
            {
              this.log.debug("Stale connection check");
              if (this.managedConn.isStale())
              {
                this.log.debug("Stale connection detected");
                this.managedConn.close();
              }
            }
          }
          catch (InterruptedException paramHttpHost)
          {
            paramHttpRequest = new InterruptedIOException();
            paramHttpRequest.initCause(paramHttpHost);
            throw paramHttpRequest;
          }
        }
        if ((paramHttpRequest instanceof AbortableHttpRequest))
          ((AbortableHttpRequest)paramHttpRequest).setReleaseTrigger(this.managedConn);
        while (true)
        {
          try
          {
            tryConnect((RoutedRequest)localObject1, paramHttpContext);
            ((RequestWrapper)localObject3).resetHeaders();
            rewriteRequestURI((RequestWrapper)localObject3, (HttpRoute)localObject5);
            localObject2 = this.virtualHost;
            paramHttpHost = (HttpHost)localObject2;
            if (localObject2 == null)
              paramHttpHost = ((HttpRoute)localObject5).getTargetHost();
            localObject2 = ((HttpRoute)localObject5).getProxyHost();
            paramHttpContext.setAttribute("http.target_host", paramHttpHost);
            paramHttpContext.setAttribute("http.proxy_host", localObject2);
            paramHttpContext.setAttribute("http.connection", this.managedConn);
            paramHttpContext.setAttribute("http.auth.target-scope", this.targetAuthState);
            paramHttpContext.setAttribute("http.auth.proxy-scope", this.proxyAuthState);
            this.requestExec.preProcess((HttpRequest)localObject3, this.httpProcessor, paramHttpContext);
            localObject3 = tryExecute((RoutedRequest)localObject1, paramHttpContext);
            if (localObject3 == null)
            {
              paramHttpHost = (HttpHost)localObject3;
              break;
            }
            ((HttpResponse)localObject3).setParams(this.params);
            this.requestExec.postProcess((HttpResponse)localObject3, this.httpProcessor, paramHttpContext);
            boolean bool2 = this.reuseStrategy.keepAlive((HttpResponse)localObject3, paramHttpContext);
            if (bool2)
            {
              l = this.keepAliveStrategy.getKeepAliveDuration((HttpResponse)localObject3, paramHttpContext);
              if (this.log.isDebugEnabled())
              {
                if (l <= 0L)
                  break label1009;
                paramHttpHost = new StringBuilder();
                paramHttpHost.append("for ");
                paramHttpHost.append(l);
                paramHttpHost.append(" ");
                paramHttpHost.append(TimeUnit.MILLISECONDS);
                paramHttpHost = paramHttpHost.toString();
                localObject2 = this.log;
                localObject5 = new StringBuilder();
                ((StringBuilder)localObject5).append("Connection can be kept alive ");
                ((StringBuilder)localObject5).append(paramHttpHost);
                ((Log)localObject2).debug(((StringBuilder)localObject5).toString());
              }
              this.managedConn.setIdleDuration(l, TimeUnit.MILLISECONDS);
            }
            localObject2 = handleResponse((RoutedRequest)localObject1, (HttpResponse)localObject3, paramHttpContext);
            if (localObject2 == null)
            {
              j = 1;
              localObject2 = localObject1;
            }
            else
            {
              if (bool2)
              {
                EntityUtils.consume(((HttpResponse)localObject3).getEntity());
                this.managedConn.markReusable();
              }
              else
              {
                this.managedConn.close();
                invalidateAuthIfSuccessful(this.proxyAuthState);
                invalidateAuthIfSuccessful(this.targetAuthState);
              }
              if (((RoutedRequest)localObject2).getRoute().equals(((RoutedRequest)localObject1).getRoute()))
                break label1016;
              releaseConnection();
              break label1016;
            }
            paramHttpHost = (HttpHost)localObject3;
            i = j;
            bool1 = bool2;
            localObject1 = localObject2;
            if (this.managedConn == null)
              break;
            paramHttpHost = (HttpHost)localObject3;
            i = j;
            bool1 = bool2;
            localObject1 = localObject2;
            if (localObject4 != null)
              break;
            localObject4 = this.userTokenHandler.getUserToken(paramHttpContext);
            paramHttpContext.setAttribute("http.user-token", localObject4);
            paramHttpHost = (HttpHost)localObject3;
            i = j;
            bool1 = bool2;
            localObject1 = localObject2;
            if (localObject4 == null)
              break;
            this.managedConn.setState(localObject4);
            paramHttpHost = (HttpHost)localObject3;
            i = j;
            bool1 = bool2;
            localObject1 = localObject2;
          }
          catch (TunnelRefusedException paramHttpHost)
          {
            if (this.log.isDebugEnabled())
              this.log.debug(paramHttpHost.getMessage());
            paramHttpHost = paramHttpHost.getResponse();
          }
          if ((paramHttpHost != null) && (paramHttpHost.getEntity() != null) && (paramHttpHost.getEntity().isStreaming()))
          {
            paramHttpHost.setEntity(new BasicManagedEntity(paramHttpHost.getEntity(), this.managedConn, bool1));
            return paramHttpHost;
          }
          if (bool1)
            this.managedConn.markReusable();
          releaseConnection();
          return paramHttpHost;
          label972: abortConnection();
          throw paramHttpHost;
          label978: abortConnection();
          throw paramHttpHost;
          label984: abortConnection();
          throw paramHttpHost;
          label990: paramHttpHost = new InterruptedIOException("Connection has been shut down");
          paramHttpHost.initCause(paramHttpRequest);
          throw paramHttpHost;
          label1009: paramHttpHost = "indefinitely";
          continue;
          label1016: int j = i;
        }
      }
      catch (RuntimeException paramHttpHost)
      {
        break label972;
      }
      catch (IOException paramHttpHost)
      {
        break label978;
      }
      catch (HttpException paramHttpHost)
      {
        break label984;
      }
      catch (ConnectionShutdownException paramHttpRequest)
      {
        break label990;
      }
    }
  }

  protected RoutedRequest handleResponse(RoutedRequest paramRoutedRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    Object localObject2 = paramRoutedRequest.getRequest();
    Object localObject1 = ((RequestWrapper)localObject2).getParams();
    if ((HttpClientParams.isRedirecting((HttpParams)localObject1)) && (this.redirectStrategy.isRedirected((HttpRequest)localObject2, paramHttpResponse, paramHttpContext)))
    {
      if (this.redirectCount >= this.maxRedirects)
      {
        paramRoutedRequest = new StringBuilder();
        paramRoutedRequest.append("Maximum redirects (");
        paramRoutedRequest.append(this.maxRedirects);
        paramRoutedRequest.append(") exceeded");
        throw new RedirectException(paramRoutedRequest.toString());
      }
      this.redirectCount += 1;
      this.virtualHost = null;
      paramHttpResponse = this.redirectStrategy.getRedirect((HttpRequest)localObject2, paramHttpResponse, paramHttpContext);
      paramHttpResponse.setHeaders(((RequestWrapper)localObject2).getOriginal().getAllHeaders());
      paramRoutedRequest = paramHttpResponse.getURI();
      if (paramRoutedRequest.getHost() == null)
      {
        paramHttpResponse = new StringBuilder();
        paramHttpResponse.append("Redirect URI does not specify a valid host name: ");
        paramHttpResponse.append(paramRoutedRequest);
        throw new ProtocolException(paramHttpResponse.toString());
      }
      localObject2 = new HttpHost(paramRoutedRequest.getHost(), paramRoutedRequest.getPort(), paramRoutedRequest.getScheme());
      this.targetAuthState.setAuthScope(null);
      this.proxyAuthState.setAuthScope(null);
      if (!localHttpRoute.getTargetHost().equals(localObject2))
      {
        this.targetAuthState.invalidate();
        localObject3 = this.proxyAuthState.getAuthScheme();
        if ((localObject3 != null) && (((AuthScheme)localObject3).isConnectionBased()))
          this.proxyAuthState.invalidate();
      }
      paramHttpResponse = wrapRequest(paramHttpResponse);
      paramHttpResponse.setParams((HttpParams)localObject1);
      paramHttpContext = determineRoute((HttpHost)localObject2, paramHttpResponse, paramHttpContext);
      paramHttpResponse = new RoutedRequest(paramHttpResponse, paramHttpContext);
      if (this.log.isDebugEnabled())
      {
        localObject1 = this.log;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Redirecting to '");
        ((StringBuilder)localObject2).append(paramRoutedRequest);
        ((StringBuilder)localObject2).append("' via ");
        ((StringBuilder)localObject2).append(paramHttpContext);
        ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
      }
      return paramHttpResponse;
    }
    Object localObject3 = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if ((localObject3 != null) && (HttpClientParams.isAuthenticating((HttpParams)localObject1)))
    {
      if (this.targetAuthHandler.isAuthenticationRequested(paramHttpResponse, paramHttpContext))
      {
        localObject2 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
        localObject1 = localObject2;
        if (localObject2 == null)
          localObject1 = localHttpRoute.getTargetHost();
        this.log.debug("Target requested authentication");
        localObject2 = this.targetAuthHandler.getChallenges(paramHttpResponse, paramHttpContext);
        try
        {
          processChallenges((Map)localObject2, this.targetAuthState, this.targetAuthHandler, paramHttpResponse, paramHttpContext);
        }
        catch (AuthenticationException paramHttpResponse)
        {
          if (this.log.isWarnEnabled())
          {
            paramRoutedRequest = this.log;
            paramHttpContext = new StringBuilder();
            paramHttpContext.append("Authentication error: ");
            paramHttpContext.append(paramHttpResponse.getMessage());
            paramRoutedRequest.warn(paramHttpContext.toString());
            return null;
          }
        }
        updateAuthState(this.targetAuthState, (HttpHost)localObject1, (CredentialsProvider)localObject3);
        if (this.targetAuthState.getCredentials() != null)
          return paramRoutedRequest;
        return null;
      }
      this.targetAuthState.setAuthScope(null);
      if (this.proxyAuthHandler.isAuthenticationRequested(paramHttpResponse, paramHttpContext))
      {
        localObject1 = localHttpRoute.getProxyHost();
        this.log.debug("Proxy requested authentication");
        localObject2 = this.proxyAuthHandler.getChallenges(paramHttpResponse, paramHttpContext);
        try
        {
          processChallenges((Map)localObject2, this.proxyAuthState, this.proxyAuthHandler, paramHttpResponse, paramHttpContext);
        }
        catch (AuthenticationException paramHttpResponse)
        {
          if (this.log.isWarnEnabled())
          {
            paramRoutedRequest = this.log;
            paramHttpContext = new StringBuilder();
            paramHttpContext.append("Authentication error: ");
            paramHttpContext.append(paramHttpResponse.getMessage());
            paramRoutedRequest.warn(paramHttpContext.toString());
            return null;
          }
        }
        updateAuthState(this.proxyAuthState, (HttpHost)localObject1, (CredentialsProvider)localObject3);
        if (this.proxyAuthState.getCredentials() != null)
          return paramRoutedRequest;
        return null;
      }
      this.proxyAuthState.setAuthScope(null);
    }
    return null;
  }

  protected void releaseConnection()
  {
    try
    {
      this.managedConn.releaseConnection();
    }
    catch (IOException localIOException)
    {
      this.log.debug("IOException releasing connection", localIOException);
    }
    this.managedConn = null;
  }

  protected void rewriteRequestURI(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
    throws ProtocolException
  {
    Object localObject;
    try
    {
      localObject = paramRequestWrapper.getURI();
      if ((paramHttpRoute.getProxyHost() != null) && (!paramHttpRoute.isTunnelled()))
      {
        if (!((URI)localObject).isAbsolute())
          paramRequestWrapper.setURI(URIUtils.rewriteURI((URI)localObject, paramHttpRoute.getTargetHost()));
      }
      else if (((URI)localObject).isAbsolute())
        paramRequestWrapper.setURI(URIUtils.rewriteURI((URI)localObject, null));
      return;
    }
    catch (URISyntaxException paramHttpRoute)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid URI: ");
      ((StringBuilder)localObject).append(paramRequestWrapper.getRequestLine().getUri());
    }
    throw new ProtocolException(((StringBuilder)localObject).toString(), paramHttpRoute);
  }
}