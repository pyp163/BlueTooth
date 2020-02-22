package org.apache.http.auth.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

public class AuthParamBean extends HttpAbstractParamBean
{
  public AuthParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setCredentialCharset(String paramString)
  {
    AuthParams.setCredentialCharset(this.params, paramString);
  }
}