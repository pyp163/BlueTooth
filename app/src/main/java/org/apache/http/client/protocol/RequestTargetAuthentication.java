package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestTargetAuthentication
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      return;
    if (paramHttpRequest.containsHeader("Authorization"))
      return;
    Object localObject = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if (localObject == null)
    {
      this.log.debug("Target auth state not set in the context");
      return;
    }
    AuthScheme localAuthScheme = ((AuthState)localObject).getAuthScheme();
    if (localAuthScheme == null)
      return;
    Credentials localCredentials = ((AuthState)localObject).getCredentials();
    if (localCredentials == null)
    {
      this.log.debug("User credentials not available");
      return;
    }
    if ((((AuthState)localObject).getAuthScope() != null) || (!localAuthScheme.isConnectionBased()))
      try
      {
        if ((localAuthScheme instanceof ContextAwareAuthScheme))
          paramHttpContext = ((ContextAwareAuthScheme)localAuthScheme).authenticate(localCredentials, paramHttpRequest, paramHttpContext);
        else
          paramHttpContext = localAuthScheme.authenticate(localCredentials, paramHttpRequest);
        paramHttpRequest.addHeader(paramHttpContext);
        return;
      }
      catch (AuthenticationException paramHttpRequest)
      {
        if (this.log.isErrorEnabled())
        {
          paramHttpContext = this.log;
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Authentication error: ");
          ((StringBuilder)localObject).append(paramHttpRequest.getMessage());
          paramHttpContext.error(((StringBuilder)localObject).toString());
        }
      }
  }
}