package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class NTCredentials
  implements Credentials, Serializable
{
  private static final long serialVersionUID = -7385699315228907265L;
  private final String password;
  private final NTUserPrincipal principal;
  private final String workstation;

  public NTCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      String str = paramString.substring(0, i);
      this.password = paramString.substring(i + 1);
      paramString = str;
    }
    else
    {
      this.password = null;
    }
    i = paramString.indexOf('/');
    if (i >= 0)
      this.principal = new NTUserPrincipal(paramString.substring(0, i).toUpperCase(Locale.ENGLISH), paramString.substring(i + 1));
    else
      this.principal = new NTUserPrincipal(null, paramString.substring(i + 1));
    this.workstation = null;
  }

  public NTCredentials(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("User name may not be null");
    this.principal = new NTUserPrincipal(paramString4, paramString1);
    this.password = paramString2;
    if (paramString3 != null)
    {
      this.workstation = paramString3.toUpperCase(Locale.ENGLISH);
      return;
    }
    this.workstation = null;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NTCredentials))
    {
      paramObject = (NTCredentials)paramObject;
      if ((LangUtils.equals(this.principal, paramObject.principal)) && (LangUtils.equals(this.workstation, paramObject.workstation)))
        return true;
    }
    return false;
  }

  public String getDomain()
  {
    return this.principal.getDomain();
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserName()
  {
    return this.principal.getUsername();
  }

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public String getWorkstation()
  {
    return this.workstation;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.principal), this.workstation);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.principal);
    localStringBuilder.append("][workstation: ");
    localStringBuilder.append(this.workstation);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}