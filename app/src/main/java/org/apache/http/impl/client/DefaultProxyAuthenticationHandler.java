package org.apache.http.impl.client;

import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultProxyAuthenticationHandler extends AbstractAuthenticationHandler
{
  protected List<String> getAuthPreferences(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    List localList = (List)paramHttpResponse.getParams().getParameter("http.auth.proxy-scheme-pref");
    if (localList != null)
      return localList;
    return super.getAuthPreferences(paramHttpResponse, paramHttpContext);
  }

  public Map<String, Header> getChallenges(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    return parseChallenges(paramHttpResponse.getHeaders("Proxy-Authenticate"));
  }

  public boolean isAuthenticationRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    return paramHttpResponse.getStatusLine().getStatusCode() == 407;
  }
}