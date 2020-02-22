package org.apache.http.auth;

import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class AuthState
{
  private AuthScheme authScheme;
  private AuthScope authScope;
  private Credentials credentials;

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public AuthScope getAuthScope()
  {
    return this.authScope;
  }

  public Credentials getCredentials()
  {
    return this.credentials;
  }

  public void invalidate()
  {
    this.authScheme = null;
    this.authScope = null;
    this.credentials = null;
  }

  public boolean isValid()
  {
    return this.authScheme != null;
  }

  public void setAuthScheme(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme == null)
    {
      invalidate();
      return;
    }
    this.authScheme = paramAuthScheme;
  }

  public void setAuthScope(AuthScope paramAuthScope)
  {
    this.authScope = paramAuthScope;
  }

  public void setCredentials(Credentials paramCredentials)
  {
    this.credentials = paramCredentials;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("auth scope [");
    localStringBuilder.append(this.authScope);
    localStringBuilder.append("]; credentials set [");
    String str;
    if (this.credentials != null)
      str = "true";
    else
      str = "false";
    localStringBuilder.append(str);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}