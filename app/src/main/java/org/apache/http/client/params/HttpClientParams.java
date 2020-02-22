package org.apache.http.client.params;

import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;

@Immutable
public class HttpClientParams
{
  public static String getCookiePolicy(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams = (String)paramHttpParams.getParameter("http.protocol.cookie-policy");
    if (paramHttpParams == null)
      return "best-match";
    return paramHttpParams;
  }

  public static boolean isAuthenticating(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-authentication", true);
  }

  public static boolean isRedirecting(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-redirects", true);
  }

  public static void setAuthenticating(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.handle-authentication", paramBoolean);
  }

  public static void setCookiePolicy(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.cookie-policy", paramString);
  }

  public static void setRedirecting(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.handle-redirects", paramBoolean);
  }
}