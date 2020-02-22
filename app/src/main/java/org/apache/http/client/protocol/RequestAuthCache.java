package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestAuthCache
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void doPreemptiveAuth(HttpHost paramHttpHost, AuthScheme paramAuthScheme, AuthState paramAuthState, CredentialsProvider paramCredentialsProvider)
  {
    String str = paramAuthScheme.getSchemeName();
    if (this.log.isDebugEnabled())
    {
      Log localLog = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Re-using cached '");
      localStringBuilder.append(str);
      localStringBuilder.append("' auth scheme for ");
      localStringBuilder.append(paramHttpHost);
      localLog.debug(localStringBuilder.toString());
    }
    paramHttpHost = paramCredentialsProvider.getCredentials(new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), AuthScope.ANY_REALM, str));
    if (paramHttpHost != null)
    {
      paramAuthState.setAuthScheme(paramAuthScheme);
      paramAuthState.setCredentials(paramHttpHost);
      return;
    }
    this.log.debug("No credentials for preemptive authentication");
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localObject == null)
    {
      this.log.debug("Auth cache not set in the context");
      return;
    }
    paramHttpRequest = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (paramHttpRequest == null)
    {
      this.log.debug("Credentials provider not set in the context");
      return;
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if ((localHttpHost != null) && (localAuthState != null) && (localAuthState.getAuthScheme() == null))
    {
      AuthScheme localAuthScheme = ((AuthCache)localObject).get(localHttpHost);
      if (localAuthScheme != null)
        doPreemptiveAuth(localHttpHost, localAuthScheme, localAuthState, paramHttpRequest);
    }
    localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.proxy_host");
    paramHttpContext = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
    if ((localHttpHost != null) && (paramHttpContext != null) && (paramHttpContext.getAuthScheme() == null))
    {
      localObject = ((AuthCache)localObject).get(localHttpHost);
      if (localObject != null)
        doPreemptiveAuth(localHttpHost, (AuthScheme)localObject, paramHttpContext, paramHttpRequest);
    }
  }
}