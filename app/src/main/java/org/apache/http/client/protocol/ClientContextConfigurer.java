package org.apache.http.client.protocol;

import java.util.List;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ClientContextConfigurer
  implements ClientContext
{
  private final HttpContext context;

  public ClientContextConfigurer(HttpContext paramHttpContext)
  {
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    this.context = paramHttpContext;
  }

  @Deprecated
  public void setAuthSchemePref(List<String> paramList)
  {
    this.context.setAttribute("http.auth.scheme-pref", paramList);
  }

  public void setAuthSchemeRegistry(AuthSchemeRegistry paramAuthSchemeRegistry)
  {
    this.context.setAttribute("http.authscheme-registry", paramAuthSchemeRegistry);
  }

  public void setCookieSpecRegistry(CookieSpecRegistry paramCookieSpecRegistry)
  {
    this.context.setAttribute("http.cookiespec-registry", paramCookieSpecRegistry);
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    this.context.setAttribute("http.cookie-store", paramCookieStore);
  }

  public void setCredentialsProvider(CredentialsProvider paramCredentialsProvider)
  {
    this.context.setAttribute("http.auth.credentials-provider", paramCredentialsProvider);
  }
}