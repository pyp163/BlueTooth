package org.apache.http.auth;

import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public class AuthScope
{
  public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  public static final String ANY_HOST;
  public static final int ANY_PORT = -1;
  public static final String ANY_REALM;
  public static final String ANY_SCHEME;
  private final String host;
  private final int port;
  private final String realm;
  private final String scheme;

  public AuthScope(String paramString, int paramInt)
  {
    this(paramString, paramInt, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2)
  {
    this(paramString1, paramInt, paramString2, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      paramString1 = ANY_HOST;
    else
      paramString1 = paramString1.toLowerCase(Locale.ENGLISH);
    this.host = paramString1;
    int i = paramInt;
    if (paramInt < 0)
      i = -1;
    this.port = i;
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = ANY_REALM;
    this.realm = paramString1;
    if (paramString3 == null)
      paramString1 = ANY_SCHEME;
    else
      paramString1 = paramString3.toUpperCase(Locale.ENGLISH);
    this.scheme = paramString1;
  }

  public AuthScope(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Scope may not be null");
    this.host = paramAuthScope.getHost();
    this.port = paramAuthScope.getPort();
    this.realm = paramAuthScope.getRealm();
    this.scheme = paramAuthScope.getScheme();
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (paramObject == null)
      return false;
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof AuthScope))
      return super.equals(paramObject);
    paramObject = (AuthScope)paramObject;
    boolean bool1 = bool2;
    if (LangUtils.equals(this.host, paramObject.host))
    {
      bool1 = bool2;
      if (this.port == paramObject.port)
      {
        bool1 = bool2;
        if (LangUtils.equals(this.realm, paramObject.realm))
        {
          bool1 = bool2;
          if (LangUtils.equals(this.scheme, paramObject.scheme))
            bool1 = true;
        }
      }
    }
    return bool1;
  }

  public String getHost()
  {
    return this.host;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getRealm()
  {
    return this.realm;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.port), this.realm), this.scheme);
  }

  public int match(AuthScope paramAuthScope)
  {
    int j;
    if (LangUtils.equals(this.scheme, paramAuthScope.scheme))
    {
      j = 1;
    }
    else
    {
      if ((this.scheme != ANY_SCHEME) && (paramAuthScope.scheme != ANY_SCHEME))
        return -1;
      j = 0;
    }
    int i;
    if (LangUtils.equals(this.realm, paramAuthScope.realm))
    {
      i = j + 2;
    }
    else
    {
      i = j;
      if (this.realm != ANY_REALM)
      {
        i = j;
        if (paramAuthScope.realm != ANY_REALM)
          return -1;
      }
    }
    if (this.port == paramAuthScope.port)
    {
      j = i + 4;
    }
    else
    {
      j = i;
      if (this.port != -1)
      {
        j = i;
        if (paramAuthScope.port != -1)
          return -1;
      }
    }
    if (LangUtils.equals(this.host, paramAuthScope.host))
      return j + 8;
    if ((this.host != ANY_HOST) && (paramAuthScope.host != ANY_HOST))
      return -1;
    return j;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.scheme != null)
    {
      localStringBuilder.append(this.scheme.toUpperCase(Locale.ENGLISH));
      localStringBuilder.append(' ');
    }
    if (this.realm != null)
    {
      localStringBuilder.append('\'');
      localStringBuilder.append(this.realm);
      localStringBuilder.append('\'');
    }
    else
    {
      localStringBuilder.append("<any realm>");
    }
    if (this.host != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(this.host);
      if (this.port >= 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(this.port);
      }
    }
    return localStringBuilder.toString();
  }
}