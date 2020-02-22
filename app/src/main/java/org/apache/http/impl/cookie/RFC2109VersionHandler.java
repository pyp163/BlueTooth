package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class RFC2109VersionHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    if (paramString.trim().length() == 0)
      throw new MalformedCookieException("Blank value for version attribute");
    try
    {
      paramSetCookie.setVersion(Integer.parseInt(paramString));
      return;
    }
    catch (NumberFormatException paramSetCookie)
    {
      paramString = new StringBuilder();
      paramString.append("Invalid version: ");
      paramString.append(paramSetCookie.getMessage());
    }
    throw new MalformedCookieException(paramString.toString());
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookie.getVersion() < 0)
      throw new CookieRestrictionViolationException("Cookie version may not be negative");
  }
}