package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public final class BasicUserPrincipal
  implements Principal, Serializable
{
  private static final long serialVersionUID = -2266305184969850467L;
  private final String username;

  public BasicUserPrincipal(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("User name may not be null");
    this.username = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof BasicUserPrincipal))
    {
      paramObject = (BasicUserPrincipal)paramObject;
      if (LangUtils.equals(this.username, paramObject.username))
        return true;
    }
    return false;
  }

  public String getName()
  {
    return this.username;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(17, this.username);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.username);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}