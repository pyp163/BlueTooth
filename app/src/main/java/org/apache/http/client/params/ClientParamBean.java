package org.apache.http.client.params;

import java.util.Collection;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class ClientParamBean extends HttpAbstractParamBean
{
  public ClientParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setAllowCircularRedirects(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.allow-circular-redirects", paramBoolean);
  }

  @Deprecated
  public void setConnectionManagerFactory(ClientConnectionManagerFactory paramClientConnectionManagerFactory)
  {
    this.params.setParameter("http.connection-manager.factory-object", paramClientConnectionManagerFactory);
  }

  public void setConnectionManagerFactoryClassName(String paramString)
  {
    this.params.setParameter("http.connection-manager.factory-class-name", paramString);
  }

  public void setCookiePolicy(String paramString)
  {
    this.params.setParameter("http.protocol.cookie-policy", paramString);
  }

  public void setDefaultHeaders(Collection<Header> paramCollection)
  {
    this.params.setParameter("http.default-headers", paramCollection);
  }

  public void setDefaultHost(HttpHost paramHttpHost)
  {
    this.params.setParameter("http.default-host", paramHttpHost);
  }

  public void setHandleAuthentication(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.handle-authentication", paramBoolean);
  }

  public void setHandleRedirects(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.handle-redirects", paramBoolean);
  }

  public void setMaxRedirects(int paramInt)
  {
    this.params.setIntParameter("http.protocol.max-redirects", paramInt);
  }

  public void setRejectRelativeRedirect(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.reject-relative-redirect", paramBoolean);
  }

  public void setVirtualHost(HttpHost paramHttpHost)
  {
    this.params.setParameter("http.virtual-host", paramHttpHost);
  }
}