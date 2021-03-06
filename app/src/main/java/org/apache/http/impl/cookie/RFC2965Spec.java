package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class RFC2965Spec extends RFC2109Spec
{
  public RFC2965Spec()
  {
    this(null, false);
  }

  public RFC2965Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(paramArrayOfString, paramBoolean);
    registerAttribHandler("domain", new RFC2965DomainAttributeHandler());
    registerAttribHandler("port", new RFC2965PortAttributeHandler());
    registerAttribHandler("commenturl", new RFC2965CommentUrlAttributeHandler());
    registerAttribHandler("discard", new RFC2965DiscardAttributeHandler());
    registerAttribHandler("version", new RFC2965VersionAttributeHandler());
  }

  private static CookieOrigin adjustEffectiveHost(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getHost();
    int k = 0;
    int i = 0;
    while (i < str.length())
    {
      int m = str.charAt(i);
      j = k;
      if (m == 46)
        break label57;
      if (m == 58)
      {
        j = k;
        break label57;
      }
      i += 1;
    }
    int j = 1;
    label57: if (j != 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(".local");
      return new CookieOrigin(localStringBuilder.toString(), paramCookieOrigin.getPort(), paramCookieOrigin.getPath(), paramCookieOrigin.isSecure());
    }
    return paramCookieOrigin;
  }

  private List<Cookie> createCookies(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    int k = paramArrayOfHeaderElement.length;
    int i = 0;
    while (i < k)
    {
      Object localObject2 = paramArrayOfHeaderElement[i];
      Object localObject1 = ((HeaderElement)localObject2).getName();
      Object localObject3 = ((HeaderElement)localObject2).getValue();
      if ((localObject1 != null) && (((String)localObject1).length() != 0))
      {
        localObject1 = new BasicClientCookie2((String)localObject1, (String)localObject3);
        ((BasicClientCookie2)localObject1).setPath(getDefaultPath(paramCookieOrigin));
        ((BasicClientCookie2)localObject1).setDomain(getDefaultDomain(paramCookieOrigin));
        ((BasicClientCookie2)localObject1).setPorts(new int[] { paramCookieOrigin.getPort() });
        localObject2 = ((HeaderElement)localObject2).getParameters();
        localObject3 = new HashMap(localObject2.length);
        int j = localObject2.length - 1;
        Object localObject4;
        while (j >= 0)
        {
          localObject4 = localObject2[j];
          ((Map)localObject3).put(((NameValuePair)localObject4).getName().toLowerCase(Locale.ENGLISH), localObject4);
          j -= 1;
        }
        localObject2 = ((Map)localObject3).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (NameValuePair)((Map.Entry)((Iterator)localObject2).next()).getValue();
          localObject4 = ((NameValuePair)localObject3).getName().toLowerCase(Locale.ENGLISH);
          ((BasicClientCookie2)localObject1).setAttribute((String)localObject4, ((NameValuePair)localObject3).getValue());
          localObject4 = findAttribHandler((String)localObject4);
          if (localObject4 != null)
            ((CookieAttributeHandler)localObject4).parse((SetCookie)localObject1, ((NameValuePair)localObject3).getValue());
        }
        localArrayList.add(localObject1);
        i += 1;
      }
      else
      {
        throw new MalformedCookieException("Cookie name may not be empty");
      }
    }
    return localArrayList;
  }

  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    super.formatCookieAsVer(paramCharArrayBuffer, paramCookie, paramInt);
    if ((paramCookie instanceof ClientCookie))
    {
      String str = ((ClientCookie)paramCookie).getAttribute("port");
      if (str != null)
      {
        paramCharArrayBuffer.append("; $Port");
        paramCharArrayBuffer.append("=\"");
        if (str.trim().length() > 0)
        {
          paramCookie = paramCookie.getPorts();
          if (paramCookie != null)
          {
            paramInt = 0;
            int i = paramCookie.length;
            while (paramInt < i)
            {
              if (paramInt > 0)
                paramCharArrayBuffer.append(",");
              paramCharArrayBuffer.append(Integer.toString(paramCookie[paramInt]));
              paramInt += 1;
            }
          }
        }
        paramCharArrayBuffer.append("\"");
      }
    }
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
    localCharArrayBuffer.append("Cookie2");
    localCharArrayBuffer.append(": ");
    localCharArrayBuffer.append("$Version=");
    localCharArrayBuffer.append(Integer.toString(getVersion()));
    return new BufferedHeader(localCharArrayBuffer);
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    return super.match(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie2"))
    {
      paramCookieOrigin = new StringBuilder();
      paramCookieOrigin.append("Unrecognized cookie header '");
      paramCookieOrigin.append(paramHeader.toString());
      paramCookieOrigin.append("'");
      throw new MalformedCookieException(paramCookieOrigin.toString());
    }
    paramCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    return createCookies(paramHeader.getElements(), paramCookieOrigin);
  }

  protected List<Cookie> parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    return createCookies(paramArrayOfHeaderElement, adjustEffectiveHost(paramCookieOrigin));
  }

  public String toString()
  {
    return "rfc2965";
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    super.validate(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }
}