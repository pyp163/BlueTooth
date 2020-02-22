package org.apache.http.params;

public abstract class HttpAbstractParamBean
{
  protected final HttpParams params;

  public HttpAbstractParamBean(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.params = paramHttpParams;
  }
}