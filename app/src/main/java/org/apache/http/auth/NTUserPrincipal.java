package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class NTUserPrincipal
  implements Principal, Serializable
{
  private static final long serialVersionUID = -6870169797924406894L;
  private final String domain;
  private final String ntname;
  private final String username;

  public NTUserPrincipal(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      throw new IllegalArgumentException("User name may not be null");
    this.username = paramString2;
    if (paramString1 != null)
      this.domain = paramString1.toUpperCase(Locale.ENGLISH);
    else
      this.domain = null;
    if ((this.domain != null) && (this.domain.length() > 0))
    {
      paramString1 = new StringBuilder();
      paramString1.append(this.domain);
      paramString1.append('/');
      paramString1.append(this.username);
      this.ntname = paramString1.toString();
      return;
    }
    this.ntname = this.username;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof NTUserPrincipal))
    {
      paramObject = (NTUserPrincipal)paramObject;
      if ((LangUtils.equals(this.username, paramObject.username)) && (LangUtils.equals(this.domain, paramObject.domain)))
        return true;
    }
    return false;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public String getName()
  {
    return this.ntname;
  }

  public String getUsername()
  {
    return this.username;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.username), this.domain);
  }

  public String toString()
  {
    return this.ntname;
  }
}