package org.apache.http.cookie;

import java.util.Locale;
import org.apache.http.annotation.Immutable;

@Immutable
public final class CookieOrigin
{
  private final String host;
  private final String path;
  private final int port;
  private final boolean secure;

  public CookieOrigin(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().length() == 0)
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
    {
      paramString1 = new StringBuilder();
      paramString1.append("Invalid port: ");
      paramString1.append(paramInt);
      throw new IllegalArgumentException(paramString1.toString());
    }
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    this.host = paramString1.toLowerCase(Locale.ENGLISH);
    this.port = paramInt;
    if (paramString2.trim().length() != 0)
      this.path = paramString2;
    else
      this.path = "/";
    this.secure = paramBoolean;
  }

  public String getHost()
  {
    return this.host;
  }

  public String getPath()
  {
    return this.path;
  }

  public int getPort()
  {
    return this.port;
  }

  public boolean isSecure()
  {
    return this.secure;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    if (this.secure)
      localStringBuilder.append("(secure)");
    localStringBuilder.append(this.host);
    localStringBuilder.append(':');
    localStringBuilder.append(Integer.toString(this.port));
    localStringBuilder.append(this.path);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}