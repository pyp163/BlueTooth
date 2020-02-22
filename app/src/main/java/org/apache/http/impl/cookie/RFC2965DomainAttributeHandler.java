package org.apache.http.impl.cookie;

import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class RFC2965DomainAttributeHandler
  implements CookieAttributeHandler
{
  public boolean domainMatch(String paramString1, String paramString2)
  {
    return (paramString1.equals(paramString2)) || ((paramString2.startsWith(".")) && (paramString1.endsWith(paramString2)));
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    paramCookieOrigin = paramCookieOrigin.getHost().toLowerCase(Locale.ENGLISH);
    paramCookie = paramCookie.getDomain();
    boolean bool2 = domainMatch(paramCookieOrigin, paramCookie);
    boolean bool1 = false;
    if (!bool2)
      return false;
    if (paramCookieOrigin.substring(0, paramCookieOrigin.length() - paramCookie.length()).indexOf('.') == -1)
      bool1 = true;
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
    String str = paramString.toLowerCase(Locale.ENGLISH);
    paramString = str;
    if (!str.startsWith("."))
    {
      paramString = new StringBuilder();
      paramString.append('.');
      paramString.append(str);
      paramString = paramString.toString();
    }
    paramSetCookie.setDomain(paramString);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    paramCookieOrigin = paramCookieOrigin.getHost().toLowerCase(Locale.ENGLISH);
    if (paramCookie.getDomain() == null)
      throw new CookieRestrictionViolationException("Invalid cookie state: domain not specified");
    Object localObject = paramCookie.getDomain().toLowerCase(Locale.ENGLISH);
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain")))
    {
      if (!((String)localObject).startsWith("."))
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie.getDomain());
        paramCookieOrigin.append("\" violates RFC 2109: domain must start with a dot");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      int i = ((String)localObject).indexOf('.', 1);
      if (((i < 0) || (i == ((String)localObject).length() - 1)) && (!((String)localObject).equals(".local")))
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie.getDomain());
        paramCookieOrigin.append("\" violates RFC 2965: the value contains no embedded dots ");
        paramCookieOrigin.append("and the value is not .local");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      if (!domainMatch(paramCookieOrigin, (String)localObject))
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie.getDomain());
        paramCookieOrigin.append("\" violates RFC 2965: effective host name does not ");
        paramCookieOrigin.append("domain-match domain attribute.");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
      if (paramCookieOrigin.substring(0, paramCookieOrigin.length() - ((String)localObject).length()).indexOf('.') != -1)
      {
        paramCookieOrigin = new StringBuilder();
        paramCookieOrigin.append("Domain attribute \"");
        paramCookieOrigin.append(paramCookie.getDomain());
        paramCookieOrigin.append("\" violates RFC 2965: ");
        paramCookieOrigin.append("effective host minus domain may not contain any dots");
        throw new CookieRestrictionViolationException(paramCookieOrigin.toString());
      }
    }
    else if (!paramCookie.getDomain().equals(paramCookieOrigin))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Illegal domain attribute: \"");
      ((StringBuilder)localObject).append(paramCookie.getDomain());
      ((StringBuilder)localObject).append("\".");
      ((StringBuilder)localObject).append("Domain of origin: \"");
      ((StringBuilder)localObject).append(paramCookieOrigin);
      ((StringBuilder)localObject).append("\"");
      throw new CookieRestrictionViolationException(((StringBuilder)localObject).toString());
    }
  }
}