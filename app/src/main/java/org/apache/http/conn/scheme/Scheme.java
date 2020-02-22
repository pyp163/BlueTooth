package org.apache.http.conn.scheme;

import java.util.Locale;
import org.apache.http.annotation.Immutable;
import org.apache.http.util.LangUtils;

@Immutable
public final class Scheme
{
  private final int defaultPort;
  private final boolean layered;
  private final String name;
  private final SchemeSocketFactory socketFactory;
  private String stringRep;

  public Scheme(String paramString, int paramInt, SchemeSocketFactory paramSchemeSocketFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Scheme name may not be null");
    if ((paramInt > 0) && (paramInt <= 65535))
    {
      if (paramSchemeSocketFactory == null)
        throw new IllegalArgumentException("Socket factory may not be null");
      this.name = paramString.toLowerCase(Locale.ENGLISH);
      this.socketFactory = paramSchemeSocketFactory;
      this.defaultPort = paramInt;
      this.layered = (paramSchemeSocketFactory instanceof LayeredSchemeSocketFactory);
      return;
    }
    paramString = new StringBuilder();
    paramString.append("Port is invalid: ");
    paramString.append(paramInt);
    throw new IllegalArgumentException(paramString.toString());
  }

  @Deprecated
  public Scheme(String paramString, SocketFactory paramSocketFactory, int paramInt)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Scheme name may not be null");
    if (paramSocketFactory == null)
      throw new IllegalArgumentException("Socket factory may not be null");
    if ((paramInt > 0) && (paramInt <= 65535))
    {
      this.name = paramString.toLowerCase(Locale.ENGLISH);
      if ((paramSocketFactory instanceof LayeredSocketFactory))
      {
        this.socketFactory = new LayeredSchemeSocketFactoryAdaptor((LayeredSocketFactory)paramSocketFactory);
        this.layered = true;
      }
      else
      {
        this.socketFactory = new SchemeSocketFactoryAdaptor(paramSocketFactory);
        this.layered = false;
      }
      this.defaultPort = paramInt;
      return;
    }
    paramString = new StringBuilder();
    paramString.append("Port is invalid: ");
    paramString.append(paramInt);
    throw new IllegalArgumentException(paramString.toString());
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof Scheme))
    {
      paramObject = (Scheme)paramObject;
      return (this.name.equals(paramObject.name)) && (this.defaultPort == paramObject.defaultPort) && (this.layered == paramObject.layered);
    }
    return false;
  }

  public final int getDefaultPort()
  {
    return this.defaultPort;
  }

  public final String getName()
  {
    return this.name;
  }

  public final SchemeSocketFactory getSchemeSocketFactory()
  {
    return this.socketFactory;
  }

  @Deprecated
  public final SocketFactory getSocketFactory()
  {
    if ((this.socketFactory instanceof SchemeSocketFactoryAdaptor))
      return ((SchemeSocketFactoryAdaptor)this.socketFactory).getFactory();
    if (this.layered)
      return new LayeredSocketFactoryAdaptor((LayeredSchemeSocketFactory)this.socketFactory);
    return new SocketFactoryAdaptor(this.socketFactory);
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), this.name), this.layered);
  }

  public final boolean isLayered()
  {
    return this.layered;
  }

  public final int resolvePort(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0)
      i = this.defaultPort;
    return i;
  }

  public final String toString()
  {
    if (this.stringRep == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.name);
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(this.defaultPort));
      this.stringRep = localStringBuilder.toString();
    }
    return this.stringRep;
  }
}