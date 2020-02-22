package org.apache.http.cookie.params;

import java.util.Collection;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class CookieSpecParamBean extends HttpAbstractParamBean
{
  public CookieSpecParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setDatePatterns(Collection<String> paramCollection)
  {
    this.params.setParameter("http.protocol.cookie-datepatterns", paramCollection);
  }

  public void setSingleHeader(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.single-cookie-header", paramBoolean);
  }
}