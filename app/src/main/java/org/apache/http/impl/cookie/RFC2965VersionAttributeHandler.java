package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965VersionAttributeHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    try
    {
      int i = Integer.parseInt(paramString);
      break label38;
      label36: i = -1;
      label38: if (i < 0)
        throw new MalformedCookieException("Invalid cookie version.");
      paramSetCookie.setVersion(i);
      return;
    }
    catch (NumberFormatException paramString)
    {
      break label36;
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (((paramCookie instanceof SetCookie2)) && ((paramCookie instanceof ClientCookie)) && (!((ClientCookie)paramCookie).containsAttribute("version")))
      throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
  }
}