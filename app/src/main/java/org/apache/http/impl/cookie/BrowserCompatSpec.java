package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BrowserCompatSpec extends CookieSpecBase
{

  @Deprecated
  protected static final String[] DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  private static final String[] DEFAULT_DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  private final String[] datepatterns;

  public BrowserCompatSpec()
  {
    this(null);
  }

  public BrowserCompatSpec(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
      this.datepatterns = ((String[])paramArrayOfString.clone());
    else
      this.datepatterns = DEFAULT_DATE_PATTERNS;
    registerAttribHandler("path", new BasicPathHandler());
    registerAttribHandler("domain", new BasicDomainHandler());
    registerAttribHandler("max-age", new BasicMaxAgeHandler());
    registerAttribHandler("secure", new BasicSecureHandler());
    registerAttribHandler("comment", new BasicCommentHandler());
    registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramList.size() * 20);
    localCharArrayBuffer.append("Cookie");
    localCharArrayBuffer.append(": ");
    int i = 0;
    while (i < paramList.size())
    {
      Object localObject = (Cookie)paramList.get(i);
      if (i > 0)
        localCharArrayBuffer.append("; ");
      localCharArrayBuffer.append(((Cookie)localObject).getName());
      localCharArrayBuffer.append("=");
      localObject = ((Cookie)localObject).getValue();
      if (localObject != null)
        localCharArrayBuffer.append((String)localObject);
      i += 1;
    }
    paramList = new ArrayList(1);
    paramList.add(new BufferedHeader(localCharArrayBuffer));
    return paramList;
  }

  public int getVersion()
  {
    return 0;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
    {
      paramCookieOrigin = new StringBuilder();
      paramCookieOrigin.append("Unrecognized cookie header '");
      paramCookieOrigin.append(paramHeader.toString());
      paramCookieOrigin.append("'");
      throw new MalformedCookieException(paramCookieOrigin.toString());
    }
    Object localObject = paramHeader.getElements();
    int m = localObject.length;
    int i = 0;
    int j = 0;
    int k = 0;
    HeaderElement[] arrayOfHeaderElement;
    while (i < m)
    {
      arrayOfHeaderElement = localObject[i];
      if (arrayOfHeaderElement.getParameterByName("version") != null)
        k = 1;
      if (arrayOfHeaderElement.getParameterByName("expires") != null)
        j = 1;
      i += 1;
    }
    if ((j == 0) && (k != 0))
    {
      paramHeader = (Header)localObject;
    }
    else
    {
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
      arrayOfHeaderElement = new HeaderElement[1];
      arrayOfHeaderElement[0] = localNetscapeDraftHeaderParser.parseHeader(paramHeader, (ParserCursor)localObject);
      paramHeader = arrayOfHeaderElement;
    }
    return parse(paramHeader, paramCookieOrigin);
  }

  public String toString()
  {
    return "compatibility";
  }
}