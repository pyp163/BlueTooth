package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Immutable
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for max-age attribute");
    try
    {
      int i = Integer.parseInt(paramString);
      if (i < 0)
      {
        paramSetCookie = new StringBuilder();
        paramSetCookie.append("Negative max-age attribute: ");
        paramSetCookie.append(paramString);
        throw new MalformedCookieException(paramSetCookie.toString());
      }
      paramSetCookie.setExpiryDate(new Date(System.currentTimeMillis() + i * 1000L));
      return;
      label94: paramSetCookie = new StringBuilder();
      paramSetCookie.append("Invalid max-age attribute: ");
      paramSetCookie.append(paramString);
      throw new MalformedCookieException(paramSetCookie.toString());
    }
    catch (NumberFormatException paramSetCookie)
    {
      break label94;
    }
  }
}