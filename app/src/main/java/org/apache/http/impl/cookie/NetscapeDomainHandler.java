package org.apache.http.impl.cookie;

import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;

@Immutable
public class NetscapeDomainHandler extends BasicDomainHandler
{
  private static boolean isSpecialDomain(String paramString)
  {
    paramString = paramString.toUpperCase(Locale.ENGLISH);
    return (paramString.endsWith(".COM")) || (paramString.endsWith(".EDU")) || (paramString.endsWith(".NET")) || (paramString.endsWith(".GOV")) || (paramString.endsWith(".MIL")) || (paramString.endsWith(".ORG")) || (paramString.endsWith(".INT"));
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookie == null)
      return false;
    return paramCookieOrigin.endsWith(paramCookie);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    super.validate(paramCookie, paramCookieOrigin);
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookieOrigin.contains("."))
    {
      int i = new StringTokenizer(paramCookie, ".").countTokens();
      if (isSpecialDomain(paramCookie))
      {
        if (i < 2)
        {
          paramCookieOrigin = new StringBuilder();
          paramCookieOrigin.append("Domain attribute \"");
          paramCookieOrigin.append(paramCookie);
          paramCookieOrigin.append("\" violates the Netscape cookie specification for ");
          paramCookieOrigin.append("special domains");
          throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
        }
      }
      else if (i < 3)
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie);
        paramCookieOrigin.append("\" violates the Netscape cookie specification");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
    }
  }
}