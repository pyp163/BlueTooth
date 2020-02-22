package org.apache.http.auth.params;

import org.apache.http.annotation.Immutable;
import org.apache.http.params.HttpParams;

@Immutable
public final class AuthParams
{
  public static String getCredentialCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.auth.credential-charset");
    paramHttpParams = str;
    if (str == null)
      paramHttpParams = "US-ASCII";
    return paramHttpParams;
  }

  public static void setCredentialCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.auth.credential-charset", paramString);
  }
}