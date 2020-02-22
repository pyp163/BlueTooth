package org.apache.http.protocol;

public final class DefaultedHttpContext
  implements HttpContext
{
  private final HttpContext defaults;
  private final HttpContext local;

  public DefaultedHttpContext(HttpContext paramHttpContext1, HttpContext paramHttpContext2)
  {
    if (paramHttpContext1 == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    this.local = paramHttpContext1;
    this.defaults = paramHttpContext2;
  }

  public Object getAttribute(String paramString)
  {
    Object localObject = this.local.getAttribute(paramString);
    if (localObject == null)
      return this.defaults.getAttribute(paramString);
    return localObject;
  }

  public HttpContext getDefaults()
  {
    return this.defaults;
  }

  public Object removeAttribute(String paramString)
  {
    return this.local.removeAttribute(paramString);
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.local.setAttribute(paramString, paramObject);
  }
}