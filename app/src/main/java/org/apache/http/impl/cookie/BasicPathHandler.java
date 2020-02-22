package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class BasicPathHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str = paramCookieOrigin.getPath();
    paramCookieOrigin = paramCookie.getPath();
    paramCookie = paramCookieOrigin;
    if (paramCookieOrigin == null)
      paramCookie = "/";
    paramCookieOrigin = paramCookie;
    if (paramCookie.length() > 1)
    {
      paramCookieOrigin = paramCookie;
      if (paramCookie.endsWith("/"))
        paramCookieOrigin = paramCookie.substring(0, paramCookie.length() - 1);
    }
    boolean bool2 = str.startsWith(paramCookieOrigin);
    boolean bool1 = bool2;
    if (bool2)
    {
      bool1 = bool2;
      if (str.length() != paramCookieOrigin.length())
      {
        bool1 = bool2;
        if (!paramCookieOrigin.endsWith("/"))
        {
          if (str.charAt(paramCookieOrigin.length()) == '/')
            return true;
          bool1 = false;
        }
      }
    }
    return bool1;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.trim().length() != 0);
    }
    else
    {
      str = "/";
    }
    paramSetCookie.setPath(str);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (!match(paramCookie, paramCookieOrigin))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Illegal path attribute \"");
      localStringBuilder.append(paramCookie.getPath());
      localStringBuilder.append("\". Path of origin: \"");
      localStringBuilder.append(paramCookieOrigin.getPath());
      localStringBuilder.append("\"");
      throw new CookieRestrictionViolationException(localStringBuilder.toString());
    }
  }
}