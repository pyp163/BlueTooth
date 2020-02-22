package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.client.AuthCache;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.HttpContext;

@Immutable
public class ResponseAuthCache
  implements HttpResponseInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void cache(AuthCache paramAuthCache, HttpHost paramHttpHost, AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if (paramAuthState.getAuthScope() != null)
    {
      if (paramAuthState.getCredentials() != null)
      {
        if (this.log.isDebugEnabled())
        {
          paramAuthState = this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Caching '");
          localStringBuilder.append(localAuthScheme.getSchemeName());
          localStringBuilder.append("' auth scheme for ");
          localStringBuilder.append(paramHttpHost);
          paramAuthState.debug(localStringBuilder.toString());
        }
        paramAuthCache.put(paramHttpHost, localAuthScheme);
        return;
      }
      paramAuthCache.remove(paramHttpHost);
    }
  }

  private boolean isCachable(AuthState paramAuthState)
  {
    paramAuthState = paramAuthState.getAuthScheme();
    boolean bool = false;
    if (paramAuthState != null)
    {
      if (!paramAuthState.isComplete())
        return false;
      paramAuthState = paramAuthState.getSchemeName();
      if ((paramAuthState.equalsIgnoreCase("Basic")) || (paramAuthState.equalsIgnoreCase("Digest")))
        bool = true;
      return bool;
    }
    return false;
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    paramHttpResponse = (HttpResponse)localObject;
    if (localHttpHost != null)
    {
      paramHttpResponse = (HttpResponse)localObject;
      if (localAuthState != null)
      {
        paramHttpResponse = (HttpResponse)localObject;
        if (isCachable(localAuthState))
        {
          paramHttpResponse = (HttpResponse)localObject;
          if (localObject == null)
          {
            paramHttpResponse = new BasicAuthCache();
            paramHttpContext.setAttribute("http.auth.auth-cache", paramHttpResponse);
          }
          cache(paramHttpResponse, localHttpHost, localAuthState);
        }
      }
    }
    localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.proxy_host");
    localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
    if ((localHttpHost != null) && (localAuthState != null) && (isCachable(localAuthState)))
    {
      localObject = paramHttpResponse;
      if (paramHttpResponse == null)
      {
        localObject = new BasicAuthCache();
        paramHttpContext.setAttribute("http.auth.auth-cache", localObject);
      }
      cache((AuthCache)localObject, localHttpHost, localAuthState);
    }
  }
}