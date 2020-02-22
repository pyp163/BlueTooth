package org.apache.http.impl.cookie;

import java.util.StringTokenizer;
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
public class RFC2965PortAttributeHandler
  implements CookieAttributeHandler
{
  private static int[] parsePortAttribute(String paramString)
    throws MalformedCookieException
  {
    paramString = new StringTokenizer(paramString, ",");
    Object localObject = new int[paramString.countTokens()];
    int i = 0;
    try
    {
      while (paramString.hasMoreTokens())
      {
        localObject[i] = Integer.parseInt(paramString.nextToken().trim());
        if (localObject[i] < 0)
          throw new MalformedCookieException("Invalid Port attribute.");
        i += 1;
      }
      return localObject;
    }
    catch (NumberFormatException paramString)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid Port attribute: ");
      ((StringBuilder)localObject).append(paramString.getMessage());
    }
    throw new MalformedCookieException(((StringBuilder)localObject).toString());
  }

  private static boolean portMatch(int paramInt, int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramInt == paramArrayOfInt[i])
        return true;
      i += 1;
    }
    return false;
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")))
    {
      if (paramCookie.getPorts() == null)
        return false;
      if (!portMatch(i, paramCookie.getPorts()))
        return false;
    }
    return true;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramSetCookie instanceof SetCookie2))
    {
      paramSetCookie = (SetCookie2)paramSetCookie;
      if ((paramString != null) && (paramString.trim().length() > 0))
        paramSetCookie.setPorts(parsePortAttribute(paramString));
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")) && (!portMatch(i, paramCookie.getPorts())))
      throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
  }
}