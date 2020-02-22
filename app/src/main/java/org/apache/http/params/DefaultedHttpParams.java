package org.apache.http.params;

public final class DefaultedHttpParams extends AbstractHttpParams
{
  private final HttpParams defaults;
  private final HttpParams local;

  public DefaultedHttpParams(HttpParams paramHttpParams1, HttpParams paramHttpParams2)
  {
    if (paramHttpParams1 == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.local = paramHttpParams1;
    this.defaults = paramHttpParams2;
  }

  public HttpParams copy()
  {
    return new DefaultedHttpParams(this.local.copy(), this.defaults);
  }

  public HttpParams getDefaults()
  {
    return this.defaults;
  }

  public Object getParameter(String paramString)
  {
    Object localObject2 = this.local.getParameter(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (this.defaults != null)
        localObject1 = this.defaults.getParameter(paramString);
    }
    return localObject1;
  }

  public boolean removeParameter(String paramString)
  {
    return this.local.removeParameter(paramString);
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    return this.local.setParameter(paramString, paramObject);
  }
}