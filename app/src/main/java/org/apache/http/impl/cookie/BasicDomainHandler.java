package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class BasicDomainHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str = paramCookieOrigin.getHost();
    paramCookieOrigin = paramCookie.getDomain();
    boolean bool = false;
    if (paramCookieOrigin == null)
      return false;
    if (str.equals(paramCookieOrigin))
      return true;
    paramCookie = paramCookieOrigin;
    if (!paramCookieOrigin.startsWith("."))
    {
      paramCookie = new StringBuilder();
      paramCookie.append('.');
      paramCookie.append(paramCookieOrigin);
      paramCookie = paramCookie.toString();
    }
    if ((str.endsWith(paramCookie)) || (str.equals(paramCookie.substring(1))))
      bool = true;
    return bool;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for domain attribute");
    if (paramString.trim().length() == 0)
      throw new MalformedCookieException("Blank value for domain attribute");
    paramSetCookie.setDomain(paramString);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str = paramCookieOrigin.getHost();
    paramCookieOrigin = paramCookie.getDomain();
    if (paramCookieOrigin == null)
      throw new CookieRestrictionViolationException("Cookie domain may not be null");
    if (str.contains("."))
    {
      if (!str.endsWith(paramCookieOrigin))
      {
        paramCookie = paramCookieOrigin;
        if (paramCookieOrigin.startsWith("."))
          paramCookie = paramCookieOrigin.substring(1, paramCookieOrigin.length());
        if (!str.equals(paramCookie))
        {
          paramCookieOrigin = new StringBuilder();
          paramCookieOrigin.append("Illegal domain attribute \"");
          paramCookieOrigin.append(paramCookie);
          paramCookieOrigin.append("\". Domain of origin: \"");
          paramCookieOrigin.append(str);
          paramCookieOrigin.append("\"");
          throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
        }
      }
    }
    else if (!str.equals(paramCookieOrigin))
    {
      paramCookie = new StringBuilder();
      paramCookie.append("Illegal domain attribute \"");
      paramCookie.append(paramCookieOrigin);
      paramCookie.append("\". Domain of origin: \"");
      paramCookie.append(str);
      paramCookie.append("\"");
      throw new CookieRestrictionViolationException(paramCookie.toString());
    }
  }
}