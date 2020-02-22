package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.NegotiateSchemeFactory;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.IgnoreSpecFactory;
import org.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.apache.http.impl.cookie.RFC2109SpecFactory;
import org.apache.http.impl.cookie.RFC2965SpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.util.EntityUtils;

@ThreadSafe
public abstract class AbstractHttpClient
  implements HttpClient
{

  @GuardedBy("this")
  private ClientConnectionManager connManager;

  @GuardedBy("this")
  private CookieStore cookieStore;

  @GuardedBy("this")
  private CredentialsProvider credsProvider;

  @GuardedBy("this")
  private HttpParams defaultParams;

  @GuardedBy("this")
  private ConnectionKeepAliveStrategy keepAliveStrategy;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  private BasicHttpProcessor mutableProcessor;

  @GuardedBy("this")
  private ImmutableHttpProcessor protocolProcessor;

  @GuardedBy("this")
  private AuthenticationHandler proxyAuthHandler;

  @GuardedBy("this")
  private RedirectStrategy redirectStrategy;

  @GuardedBy("this")
  private HttpRequestExecutor requestExec;

  @GuardedBy("this")
  private HttpRequestRetryHandler retryHandler;

  @GuardedBy("this")
  private ConnectionReuseStrategy reuseStrategy;

  @GuardedBy("this")
  private HttpRoutePlanner routePlanner;

  @GuardedBy("this")
  private AuthSchemeRegistry supportedAuthSchemes;

  @GuardedBy("this")
  private CookieSpecRegistry supportedCookieSpecs;

  @GuardedBy("this")
  private AuthenticationHandler targetAuthHandler;

  @GuardedBy("this")
  private UserTokenHandler userTokenHandler;

  protected AbstractHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.defaultParams = paramHttpParams;
    this.connManager = paramClientConnectionManager;
  }

  private static HttpHost determineTarget(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException
  {
    URI localURI = paramHttpUriRequest.getURI();
    if (localURI.isAbsolute())
    {
      HttpHost localHttpHost = URIUtils.extractHost(localURI);
      paramHttpUriRequest = localHttpHost;
      if (localHttpHost == null)
      {
        paramHttpUriRequest = new StringBuilder();
        paramHttpUriRequest.append("URI does not specify a valid host name: ");
        paramHttpUriRequest.append(localURI);
        throw new ClientProtocolException(paramHttpUriRequest.toString());
      }
    }
    else
    {
      paramHttpUriRequest = null;
    }
    return paramHttpUriRequest;
  }

  private final HttpProcessor getProtocolProcessor()
  {
    try
    {
      if (this.protocolProcessor == null)
      {
        localObject1 = getHttpProcessor();
        int k = ((BasicHttpProcessor)localObject1).getRequestInterceptorCount();
        HttpRequestInterceptor[] arrayOfHttpRequestInterceptor = new HttpRequestInterceptor[k];
        int j = 0;
        int i = 0;
        while (i < k)
        {
          arrayOfHttpRequestInterceptor[i] = ((BasicHttpProcessor)localObject1).getRequestInterceptor(i);
          i += 1;
        }
        k = ((BasicHttpProcessor)localObject1).getResponseInterceptorCount();
        HttpResponseInterceptor[] arrayOfHttpResponseInterceptor = new HttpResponseInterceptor[k];
        i = j;
        while (i < k)
        {
          arrayOfHttpResponseInterceptor[i] = ((BasicHttpProcessor)localObject1).getResponseInterceptor(i);
          i += 1;
        }
        this.protocolProcessor = new ImmutableHttpProcessor(arrayOfHttpRequestInterceptor, arrayOfHttpResponseInterceptor);
      }
      Object localObject1 = this.protocolProcessor;
      return localObject1;
    }
    finally
    {
    }
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramHttpRequestInterceptor = finally;
    }
    throw paramHttpRequestInterceptor;
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpRequestInterceptor, paramInt);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramHttpRequestInterceptor = finally;
    }
    throw paramHttpRequestInterceptor;
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpResponseInterceptor);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramHttpResponseInterceptor = finally;
    }
    throw paramHttpResponseInterceptor;
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    try
    {
      getHttpProcessor().addInterceptor(paramHttpResponseInterceptor, paramInt);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramHttpResponseInterceptor = finally;
    }
    throw paramHttpResponseInterceptor;
  }

  public void clearRequestInterceptors()
  {
    try
    {
      getHttpProcessor().clearRequestInterceptors();
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void clearResponseInterceptors()
  {
    try
    {
      getHttpProcessor().clearResponseInterceptors();
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected AuthSchemeRegistry createAuthSchemeRegistry()
  {
    AuthSchemeRegistry localAuthSchemeRegistry = new AuthSchemeRegistry();
    localAuthSchemeRegistry.register("Basic", new BasicSchemeFactory());
    localAuthSchemeRegistry.register("Digest", new DigestSchemeFactory());
    localAuthSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
    localAuthSchemeRegistry.register("negotiate", new NegotiateSchemeFactory());
    return localAuthSchemeRegistry;
  }

  protected ClientConnectionManager createClientConnectionManager()
  {
    SchemeRegistry localSchemeRegistry = SchemeRegistryFactory.createDefault();
    HttpParams localHttpParams = getParams();
    String str = (String)localHttpParams.getParameter("http.connection-manager.factory-class-name");
    if (str != null);
    try
    {
      try
      {
        ClientConnectionManagerFactory localClientConnectionManagerFactory = (ClientConnectionManagerFactory)Class.forName(str).newInstance();
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new InstantiationError(localInstantiationException.getMessage());
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalAccessError(localIllegalAccessException.getMessage());
      }
      label68: StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid class name: ");
      localStringBuilder.append(str);
      throw new IllegalStateException(localStringBuilder.toString());
      localStringBuilder = null;
      if (localStringBuilder != null)
        return localStringBuilder.newInstance(localHttpParams, localSchemeRegistry);
      return new SingleClientConnManager(localSchemeRegistry);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label68;
    }
  }

  @Deprecated
  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectHandler paramRedirectHandler, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectHandler, paramAuthenticationHandler1, paramAuthenticationHandler2, paramUserTokenHandler, paramHttpParams);
  }

  protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    return new DefaultRequestDirector(this.log, paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, paramAuthenticationHandler1, paramAuthenticationHandler2, paramUserTokenHandler, paramHttpParams);
  }

  protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy()
  {
    return new DefaultConnectionKeepAliveStrategy();
  }

  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    return new DefaultConnectionReuseStrategy();
  }

  protected CookieSpecRegistry createCookieSpecRegistry()
  {
    CookieSpecRegistry localCookieSpecRegistry = new CookieSpecRegistry();
    localCookieSpecRegistry.register("best-match", new BestMatchSpecFactory());
    localCookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
    localCookieSpecRegistry.register("netscape", new NetscapeDraftSpecFactory());
    localCookieSpecRegistry.register("rfc2109", new RFC2109SpecFactory());
    localCookieSpecRegistry.register("rfc2965", new RFC2965SpecFactory());
    localCookieSpecRegistry.register("ignoreCookies", new IgnoreSpecFactory());
    return localCookieSpecRegistry;
  }

  protected CookieStore createCookieStore()
  {
    return new BasicCookieStore();
  }

  protected CredentialsProvider createCredentialsProvider()
  {
    return new BasicCredentialsProvider();
  }

  protected HttpContext createHttpContext()
  {
    BasicHttpContext localBasicHttpContext = new BasicHttpContext();
    localBasicHttpContext.setAttribute("http.scheme-registry", getConnectionManager().getSchemeRegistry());
    localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
    localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
    localBasicHttpContext.setAttribute("http.cookie-store", getCookieStore());
    localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
    return localBasicHttpContext;
  }

  protected abstract HttpParams createHttpParams();

  protected abstract BasicHttpProcessor createHttpProcessor();

  protected HttpRequestRetryHandler createHttpRequestRetryHandler()
  {
    return new DefaultHttpRequestRetryHandler();
  }

  protected HttpRoutePlanner createHttpRoutePlanner()
  {
    return new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
  }

  protected AuthenticationHandler createProxyAuthenticationHandler()
  {
    return new DefaultProxyAuthenticationHandler();
  }

  @Deprecated
  protected RedirectHandler createRedirectHandler()
  {
    return new DefaultRedirectHandler();
  }

  protected HttpRequestExecutor createRequestExecutor()
  {
    return new HttpRequestExecutor();
  }

  protected AuthenticationHandler createTargetAuthenticationHandler()
  {
    return new DefaultTargetAuthenticationHandler();
  }

  protected UserTokenHandler createUserTokenHandler()
  {
    return new DefaultUserTokenHandler();
  }

  protected HttpParams determineParams(HttpRequest paramHttpRequest)
  {
    return new ClientParamsStack(null, getParams(), paramHttpRequest.getParams(), null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramResponseHandler == null)
      throw new IllegalArgumentException("Response handler must not be null.");
    paramHttpHost = execute(paramHttpHost, paramHttpRequest, paramHttpContext);
    try
    {
      paramHttpRequest = paramResponseHandler.handleResponse(paramHttpHost);
      EntityUtils.consume(paramHttpHost.getEntity());
      return paramHttpRequest;
    }
    catch (Throwable paramHttpRequest)
    {
      paramHttpHost = paramHttpHost.getEntity();
      try
      {
        EntityUtils.consume(paramHttpHost);
      }
      catch (Exception paramHttpHost)
      {
        this.log.warn("Error consuming content after an exception.", paramHttpHost);
      }
      if ((paramHttpRequest instanceof Error))
        throw ((Error)paramHttpRequest);
      if ((paramHttpRequest instanceof RuntimeException))
        throw ((RuntimeException)paramHttpRequest);
      if ((paramHttpRequest instanceof IOException))
        throw ((IOException)paramHttpRequest);
    }
    throw new UndeclaredThrowableException(paramHttpRequest);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpUriRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public final HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, (HttpContext)null);
  }

  public final HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("Request must not be null.");
    try
    {
      Object localObject = createHttpContext();
      if (paramHttpContext == null)
        paramHttpContext = (HttpContext)localObject;
      else
        paramHttpContext = new DefaultedHttpContext(paramHttpContext, (HttpContext)localObject);
      localObject = createClientRequestDirector(getRequestExecutor(), getConnectionManager(), getConnectionReuseStrategy(), getConnectionKeepAliveStrategy(), getRoutePlanner(), getProtocolProcessor(), getHttpRequestRetryHandler(), getRedirectStrategy(), getTargetAuthenticationHandler(), getProxyAuthenticationHandler(), getUserTokenHandler(), determineParams(paramHttpRequest));
      try
      {
        paramHttpHost = ((RequestDirector)localObject).execute(paramHttpHost, paramHttpRequest, paramHttpContext);
        return paramHttpHost;
      }
      catch (HttpException paramHttpHost)
      {
        throw new ClientProtocolException(paramHttpHost);
      }
    }
    finally
    {
    }
    throw paramHttpHost;
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpUriRequest, (HttpContext)null);
  }

  public final HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    if (paramHttpUriRequest == null)
      throw new IllegalArgumentException("Request must not be null.");
    return execute(determineTarget(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }

  public final AuthSchemeRegistry getAuthSchemes()
  {
    try
    {
      if (this.supportedAuthSchemes == null)
        this.supportedAuthSchemes = createAuthSchemeRegistry();
      AuthSchemeRegistry localAuthSchemeRegistry = this.supportedAuthSchemes;
      return localAuthSchemeRegistry;
    }
    finally
    {
    }
  }

  public final ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy()
  {
    try
    {
      if (this.keepAliveStrategy == null)
        this.keepAliveStrategy = createConnectionKeepAliveStrategy();
      ConnectionKeepAliveStrategy localConnectionKeepAliveStrategy = this.keepAliveStrategy;
      return localConnectionKeepAliveStrategy;
    }
    finally
    {
    }
  }

  public final ClientConnectionManager getConnectionManager()
  {
    try
    {
      if (this.connManager == null)
        this.connManager = createClientConnectionManager();
      ClientConnectionManager localClientConnectionManager = this.connManager;
      return localClientConnectionManager;
    }
    finally
    {
    }
  }

  public final ConnectionReuseStrategy getConnectionReuseStrategy()
  {
    try
    {
      if (this.reuseStrategy == null)
        this.reuseStrategy = createConnectionReuseStrategy();
      ConnectionReuseStrategy localConnectionReuseStrategy = this.reuseStrategy;
      return localConnectionReuseStrategy;
    }
    finally
    {
    }
  }

  public final CookieSpecRegistry getCookieSpecs()
  {
    try
    {
      if (this.supportedCookieSpecs == null)
        this.supportedCookieSpecs = createCookieSpecRegistry();
      CookieSpecRegistry localCookieSpecRegistry = this.supportedCookieSpecs;
      return localCookieSpecRegistry;
    }
    finally
    {
    }
  }

  public final CookieStore getCookieStore()
  {
    try
    {
      if (this.cookieStore == null)
        this.cookieStore = createCookieStore();
      CookieStore localCookieStore = this.cookieStore;
      return localCookieStore;
    }
    finally
    {
    }
  }

  public final CredentialsProvider getCredentialsProvider()
  {
    try
    {
      if (this.credsProvider == null)
        this.credsProvider = createCredentialsProvider();
      CredentialsProvider localCredentialsProvider = this.credsProvider;
      return localCredentialsProvider;
    }
    finally
    {
    }
  }

  protected final BasicHttpProcessor getHttpProcessor()
  {
    try
    {
      if (this.mutableProcessor == null)
        this.mutableProcessor = createHttpProcessor();
      BasicHttpProcessor localBasicHttpProcessor = this.mutableProcessor;
      return localBasicHttpProcessor;
    }
    finally
    {
    }
  }

  public final HttpRequestRetryHandler getHttpRequestRetryHandler()
  {
    try
    {
      if (this.retryHandler == null)
        this.retryHandler = createHttpRequestRetryHandler();
      HttpRequestRetryHandler localHttpRequestRetryHandler = this.retryHandler;
      return localHttpRequestRetryHandler;
    }
    finally
    {
    }
  }

  public final HttpParams getParams()
  {
    try
    {
      if (this.defaultParams == null)
        this.defaultParams = createHttpParams();
      HttpParams localHttpParams = this.defaultParams;
      return localHttpParams;
    }
    finally
    {
    }
  }

  public final AuthenticationHandler getProxyAuthenticationHandler()
  {
    try
    {
      if (this.proxyAuthHandler == null)
        this.proxyAuthHandler = createProxyAuthenticationHandler();
      AuthenticationHandler localAuthenticationHandler = this.proxyAuthHandler;
      return localAuthenticationHandler;
    }
    finally
    {
    }
  }

  @Deprecated
  public final RedirectHandler getRedirectHandler()
  {
    try
    {
      RedirectHandler localRedirectHandler = createRedirectHandler();
      return localRedirectHandler;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final RedirectStrategy getRedirectStrategy()
  {
    try
    {
      if (this.redirectStrategy == null)
        this.redirectStrategy = new DefaultRedirectStrategy();
      RedirectStrategy localRedirectStrategy = this.redirectStrategy;
      return localRedirectStrategy;
    }
    finally
    {
    }
  }

  public final HttpRequestExecutor getRequestExecutor()
  {
    try
    {
      if (this.requestExec == null)
        this.requestExec = createRequestExecutor();
      HttpRequestExecutor localHttpRequestExecutor = this.requestExec;
      return localHttpRequestExecutor;
    }
    finally
    {
    }
  }

  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    try
    {
      HttpRequestInterceptor localHttpRequestInterceptor = getHttpProcessor().getRequestInterceptor(paramInt);
      return localHttpRequestInterceptor;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getRequestInterceptorCount()
  {
    try
    {
      int i = getHttpProcessor().getRequestInterceptorCount();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    try
    {
      HttpResponseInterceptor localHttpResponseInterceptor = getHttpProcessor().getResponseInterceptor(paramInt);
      return localHttpResponseInterceptor;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getResponseInterceptorCount()
  {
    try
    {
      int i = getHttpProcessor().getResponseInterceptorCount();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final HttpRoutePlanner getRoutePlanner()
  {
    try
    {
      if (this.routePlanner == null)
        this.routePlanner = createHttpRoutePlanner();
      HttpRoutePlanner localHttpRoutePlanner = this.routePlanner;
      return localHttpRoutePlanner;
    }
    finally
    {
    }
  }

  public final AuthenticationHandler getTargetAuthenticationHandler()
  {
    try
    {
      if (this.targetAuthHandler == null)
        this.targetAuthHandler = createTargetAuthenticationHandler();
      AuthenticationHandler localAuthenticationHandler = this.targetAuthHandler;
      return localAuthenticationHandler;
    }
    finally
    {
    }
  }

  public final UserTokenHandler getUserTokenHandler()
  {
    try
    {
      if (this.userTokenHandler == null)
        this.userTokenHandler = createUserTokenHandler();
      UserTokenHandler localUserTokenHandler = this.userTokenHandler;
      return localUserTokenHandler;
    }
    finally
    {
    }
  }

  public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> paramClass)
  {
    try
    {
      getHttpProcessor().removeRequestInterceptorByClass(paramClass);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramClass = finally;
    }
    throw paramClass;
  }

  public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> paramClass)
  {
    try
    {
      getHttpProcessor().removeResponseInterceptorByClass(paramClass);
      this.protocolProcessor = null;
      return;
    }
    finally
    {
      paramClass = finally;
    }
    throw paramClass;
  }

  public void setAuthSchemes(AuthSchemeRegistry paramAuthSchemeRegistry)
  {
    try
    {
      this.supportedAuthSchemes = paramAuthSchemeRegistry;
      return;
    }
    finally
    {
      paramAuthSchemeRegistry = finally;
    }
    throw paramAuthSchemeRegistry;
  }

  public void setCookieSpecs(CookieSpecRegistry paramCookieSpecRegistry)
  {
    try
    {
      this.supportedCookieSpecs = paramCookieSpecRegistry;
      return;
    }
    finally
    {
      paramCookieSpecRegistry = finally;
    }
    throw paramCookieSpecRegistry;
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    try
    {
      this.cookieStore = paramCookieStore;
      return;
    }
    finally
    {
      paramCookieStore = finally;
    }
    throw paramCookieStore;
  }

  public void setCredentialsProvider(CredentialsProvider paramCredentialsProvider)
  {
    try
    {
      this.credsProvider = paramCredentialsProvider;
      return;
    }
    finally
    {
      paramCredentialsProvider = finally;
    }
    throw paramCredentialsProvider;
  }

  public void setHttpRequestRetryHandler(HttpRequestRetryHandler paramHttpRequestRetryHandler)
  {
    try
    {
      this.retryHandler = paramHttpRequestRetryHandler;
      return;
    }
    finally
    {
      paramHttpRequestRetryHandler = finally;
    }
    throw paramHttpRequestRetryHandler;
  }

  public void setKeepAliveStrategy(ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy)
  {
    try
    {
      this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
      return;
    }
    finally
    {
      paramConnectionKeepAliveStrategy = finally;
    }
    throw paramConnectionKeepAliveStrategy;
  }

  public void setParams(HttpParams paramHttpParams)
  {
    try
    {
      this.defaultParams = paramHttpParams;
      return;
    }
    finally
    {
      paramHttpParams = finally;
    }
    throw paramHttpParams;
  }

  public void setProxyAuthenticationHandler(AuthenticationHandler paramAuthenticationHandler)
  {
    try
    {
      this.proxyAuthHandler = paramAuthenticationHandler;
      return;
    }
    finally
    {
      paramAuthenticationHandler = finally;
    }
    throw paramAuthenticationHandler;
  }

  @Deprecated
  public void setRedirectHandler(RedirectHandler paramRedirectHandler)
  {
    try
    {
      this.redirectStrategy = new DefaultRedirectStrategyAdaptor(paramRedirectHandler);
      return;
    }
    finally
    {
      paramRedirectHandler = finally;
    }
    throw paramRedirectHandler;
  }

  public void setRedirectStrategy(RedirectStrategy paramRedirectStrategy)
  {
    try
    {
      this.redirectStrategy = paramRedirectStrategy;
      return;
    }
    finally
    {
      paramRedirectStrategy = finally;
    }
    throw paramRedirectStrategy;
  }

  public void setReuseStrategy(ConnectionReuseStrategy paramConnectionReuseStrategy)
  {
    try
    {
      this.reuseStrategy = paramConnectionReuseStrategy;
      return;
    }
    finally
    {
      paramConnectionReuseStrategy = finally;
    }
    throw paramConnectionReuseStrategy;
  }

  public void setRoutePlanner(HttpRoutePlanner paramHttpRoutePlanner)
  {
    try
    {
      this.routePlanner = paramHttpRoutePlanner;
      return;
    }
    finally
    {
      paramHttpRoutePlanner = finally;
    }
    throw paramHttpRoutePlanner;
  }

  public void setTargetAuthenticationHandler(AuthenticationHandler paramAuthenticationHandler)
  {
    try
    {
      this.targetAuthHandler = paramAuthenticationHandler;
      return;
    }
    finally
    {
      paramAuthenticationHandler = finally;
    }
    throw paramAuthenticationHandler;
  }

  public void setUserTokenHandler(UserTokenHandler paramUserTokenHandler)
  {
    try
    {
      this.userTokenHandler = paramUserTokenHandler;
      return;
    }
    finally
    {
      paramUserTokenHandler = finally;
    }
    throw paramUserTokenHandler;
  }
}