package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class UsernamePasswordCredentials
  implements Credentials, Serializable
{
  private static final long serialVersionUID = 243343858802739403L;
  private final String password;
  private final BasicUserPrincipal principal;

  public UsernamePasswordCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      this.principal = new BasicUserPrincipal(paramString.substring(0, i));
      this.password = paramString.substring(i + 1);
      return;
    }
    this.principal = new BasicUserPrincipal(paramString);
    this.password = null;
  }

  public UsernamePasswordCredentials(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Username may not be null");
    this.principal = new BasicUserPrincipal(paramString1);
    this.password = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof UsernamePasswordCredentials))
    {
      paramObject = (UsernamePasswordCredentials)paramObject;
      if (LangUtils.equals(this.principal, paramObject.principal))
        return true;
    }
    return false;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserName()
  {
    return this.principal.getName();
  }

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public int hashCode()
  {
    return this.principal.hashCode();
  }

  public String toString()
  {
    return this.principal.toString();
  }
}