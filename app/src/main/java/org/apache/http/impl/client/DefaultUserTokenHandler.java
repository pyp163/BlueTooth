package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultUserTokenHandler
  implements UserTokenHandler
{
  private static Principal getAuthPrincipal(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isComplete()) && (localAuthScheme.isConnectionBased()))
    {
      paramAuthState = paramAuthState.getCredentials();
      if (paramAuthState != null)
        return paramAuthState.getUserPrincipal();
    }
    return null;
  }

  public Object getUserToken(HttpContext paramHttpContext)
  {
    Object localObject1 = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if (localObject1 != null)
    {
      localObject2 = getAuthPrincipal((AuthState)localObject1);
      localObject1 = localObject2;
      if (localObject2 == null)
        localObject1 = getAuthPrincipal((AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope"));
    }
    else
    {
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      paramHttpContext = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      localObject2 = localObject1;
      if (paramHttpContext.isOpen())
      {
        paramHttpContext = paramHttpContext.getSSLSession();
        localObject2 = localObject1;
        if (paramHttpContext != null)
          localObject2 = paramHttpContext.getLocalPrincipal();
      }
    }
    return localObject2;
  }
}