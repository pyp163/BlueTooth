package org.apache.http.impl.cookie;

import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class RFC2109DomainHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    boolean bool2 = false;
    if (paramCookie == null)
      return false;
    boolean bool1;
    if (!paramCookieOrigin.equals(paramCookie))
    {
      bool1 = bool2;
      if (paramCookie.startsWith("."))
      {
        bool1 = bool2;
        if (!paramCookieOrigin.endsWith(paramCookie));
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
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
    paramCookieOrigin = paramCookieOrigin.getHost();
    paramCookie = paramCookie.getDomain();
    if (paramCookie == null)
      throw new CookieRestrictionViolationException("Cookie domain may not be null");
    if (!paramCookie.equals(paramCookieOrigin))
    {
      StringBuilder localStringBuilder;
      if (paramCookie.indexOf('.') == -1)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Domain attribute \"");
        localStringBuilder.append(paramCookie);
        localStringBuilder.append("\" does not match the host \"");
        localStringBuilder.append(paramCookieOrigin);
        localStringBuilder.append("\"");
        throw new CookieRestrictionViolationException(localStringBuilder.toString());
      }
      if (!paramCookie.startsWith("."))
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie);
        paramCookieOrigin.append("\" violates RFC 2109: domain must start with a dot");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      int i = paramCookie.indexOf('.', 1);
      if ((i >= 0) && (i != paramCookie.length() - 1))
      {
        paramCookieOrigin = paramCookieOrigin.toLowerCase(Locale.ENGLISH);
        if (!paramCookieOrigin.endsWith(paramCookie))
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Illegal domain attribute \"");
          localStringBuilder.append(paramCookie);
          localStringBuilder.append("\". Domain of origin: \"");
          localStringBuilder.append(paramCookieOrigin);
          localStringBuilder.append("\"");
          throw new CookieRestrictionViolationException(localStringBuilder.toString());
        }
        if (paramCookieOrigin.substring(0, paramCookieOrigin.length() - paramCookie.length()).indexOf('.') != -1)
        {
          paramCookieOrigin = new StringBuilder();
          paramCookieOrigin.append("Domain attribute \"");
          paramCookieOrigin.append(paramCookie);
          paramCookieOrigin.append("\" violates RFC 2109: host minus domain may not contain any dots");
          throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
        }
      }
      else
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie);
        paramCookieOrigin.append("\" violates RFC 2109: domain must contain an embedded dot");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
    }
  }
}