package org.apache.http.protocol;

import java.util.Map;

public class HttpRequestHandlerRegistry
  implements HttpRequestHandlerResolver
{
  private final UriPatternMatcher matcher = new UriPatternMatcher();

  public HttpRequestHandler lookup(String paramString)
  {
    return (HttpRequestHandler)this.matcher.lookup(paramString);
  }

  protected boolean matchUriRequestPattern(String paramString1, String paramString2)
  {
    return this.matcher.matchUriRequestPattern(paramString1, paramString2);
  }

  public void register(String paramString, HttpRequestHandler paramHttpRequestHandler)
  {
    if (paramString == null)
      throw new IllegalArgumentException("URI request pattern may not be null");
    if (paramHttpRequestHandler == null)
      throw new IllegalArgumentException("Request handler may not be null");
    this.matcher.register(paramString, paramHttpRequestHandler);
  }

  public void setHandlers(Map paramMap)
  {
    this.matcher.setObjects(paramMap);
  }

  public void unregister(String paramString)
  {
    this.matcher.unregister(paramString);
  }
}