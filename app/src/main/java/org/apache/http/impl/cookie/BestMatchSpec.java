package org.apache.http.impl.cookie;

import java.util.Iterator;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BestMatchSpec
  implements CookieSpec
{
  private BrowserCompatSpec compat;
  private final String[] datepatterns;
  private RFC2109Spec obsoleteStrict;
  private final boolean oneHeader;
  private RFC2965Spec strict;

  public BestMatchSpec()
  {
    this(null, false);
  }

  public BestMatchSpec(String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString == null)
      paramArrayOfString = null;
    else
      paramArrayOfString = (String[])paramArrayOfString.clone();
    this.datepatterns = paramArrayOfString;
    this.oneHeader = paramBoolean;
  }

  private BrowserCompatSpec getCompat()
  {
    if (this.compat == null)
      this.compat = new BrowserCompatSpec(this.datepatterns);
    return this.compat;
  }

  private RFC2109Spec getObsoleteStrict()
  {
    if (this.obsoleteStrict == null)
      this.obsoleteStrict = new RFC2109Spec(this.datepatterns, this.oneHeader);
    return this.obsoleteStrict;
  }

  private RFC2965Spec getStrict()
  {
    if (this.strict == null)
      this.strict = new RFC2965Spec(this.datepatterns, this.oneHeader);
    return this.strict;
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    int i = 2147483647;
    int j = 1;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      int k = j;
      if (!(localCookie instanceof SetCookie2))
        k = 0;
      j = k;
      if (localCookie.getVersion() < i)
      {
        i = localCookie.getVersion();
        j = k;
      }
    }
    if (i > 0)
    {
      if (j != 0)
        return getStrict().formatCookies(paramList);
      return getObsoleteStrict().formatCookies(paramList);
    }
    return getCompat().formatCookies(paramList);
  }

  public int getVersion()
  {
    return getStrict().getVersion();
  }

  public Header getVersionHeader()
  {
    return getStrict().getVersionHeader();
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
        return getStrict().match(paramCookie, paramCookieOrigin);
      return getObsoleteStrict().match(paramCookie, paramCookieOrigin);
    }
    return getCompat().match(paramCookie, paramCookieOrigin);
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    Object localObject = paramHeader.getElements();
    int m = localObject.length;
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < m)
    {
      localNetscapeDraftHeaderParser = localObject[i];
      if (localNetscapeDraftHeaderParser.getParameterByName("version") != null)
        k = 1;
      if (localNetscapeDraftHeaderParser.getParameterByName("expires") != null)
        j = 1;
      i += 1;
    }
    if ((j == 0) && (k != 0))
    {
      if ("Set-Cookie2".equals(paramHeader.getName()))
        return getStrict().parse((HeaderElement[])localObject, paramCookieOrigin);
      return getObsoleteStrict().parse((HeaderElement[])localObject, paramCookieOrigin);
    }
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
    if ((paramHeader instanceof FormattedHeader))
    {
      localObject = (FormattedHeader)paramHeader;
      paramHeader = ((FormattedHeader)localObject).getBuffer();
      localObject = new ParserCursor(((FormattedHeader)localObject).getValuePos(), paramHeader.length());
    }
    else
    {
      localObject = paramHeader.getValue();
      if (localObject == null)
        throw new MalformedCookieException("Header value is null");
      paramHeader = new CharArrayBuffer(((String)localObject).length());
      paramHeader.append((String)localObject);
      localObject = new ParserCursor(0, paramHeader.length());
    }
    paramHeader = localNetscapeDraftHeaderParser.parseHeader(paramHeader, (ParserCursor)localObject);
    return getCompat().parse(new HeaderElement[] { paramHeader }, paramCookieOrigin);
  }

  public String toString()
  {
    return "best-match";
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
      {
        getStrict().validate(paramCookie, paramCookieOrigin);
        return;
      }
      getObsoleteStrict().validate(paramCookie, paramCookieOrigin);
      return;
    }
    getCompat().validate(paramCookie, paramCookieOrigin);
  }
}