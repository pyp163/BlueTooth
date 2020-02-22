package org.apache.http;

import java.io.Serializable;
import java.util.Locale;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;

public final class HttpHost
  implements Cloneable, Serializable
{
  public static final String DEFAULT_SCHEME_NAME = "http";
  private static final long serialVersionUID = -7529410654042457626L;
  protected final String hostname;
  protected final String lcHostname;
  protected final int port;
  protected final String schemeName;

  public HttpHost(String paramString)
  {
    this(paramString, -1, null);
  }

  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, null);
  }

  public HttpHost(String paramString1, int paramInt, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Host name may not be null");
    this.hostname = paramString1;
    this.lcHostname = paramString1.toLowerCase(Locale.ENGLISH);
    if (paramString2 != null)
      this.schemeName = paramString2.toLowerCase(Locale.ENGLISH);
    else
      this.schemeName = "http";
    this.port = paramInt;
  }

  public HttpHost(HttpHost paramHttpHost)
  {
    this(paramHttpHost.hostname, paramHttpHost.port, paramHttpHost.schemeName);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof HttpHost))
    {
      paramObject = (HttpHost)paramObject;
      return (this.lcHostname.equals(paramObject.lcHostname)) && (this.port == paramObject.port) && (this.schemeName.equals(paramObject.schemeName));
    }
    return false;
  }

  public String getHostName()
  {
    return this.hostname;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getSchemeName()
  {
    return this.schemeName;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.lcHostname), this.port), this.schemeName);
  }

  public String toHostString()
  {
    if (this.port != -1)
    {
      CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(this.hostname.length() + 6);
      localCharArrayBuffer.append(this.hostname);
      localCharArrayBuffer.append(":");
      localCharArrayBuffer.append(Integer.toString(this.port));
      return localCharArrayBuffer.toString();
    }
    return this.hostname;
  }

  public String toString()
  {
    return toURI();
  }

  public String toURI()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(32);
    localCharArrayBuffer.append(this.schemeName);
    localCharArrayBuffer.append("://");
    localCharArrayBuffer.append(this.hostname);
    if (this.port != -1)
    {
      localCharArrayBuffer.append(':');
      localCharArrayBuffer.append(Integer.toString(this.port));
    }
    return localCharArrayBuffer.toString();
  }
}