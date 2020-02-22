package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.Util;

public final class Address
{

  @Nullable
  final CertificatePinner certificatePinner;
  final List<ConnectionSpec> connectionSpecs;
  final Dns dns;

  @Nullable
  final HostnameVerifier hostnameVerifier;
  final List<Protocol> protocols;

  @Nullable
  final Proxy proxy;
  final Authenticator proxyAuthenticator;
  final ProxySelector proxySelector;
  final SocketFactory socketFactory;

  @Nullable
  final SSLSocketFactory sslSocketFactory;
  final HttpUrl url;

  public Address(String paramString, int paramInt, Dns paramDns, SocketFactory paramSocketFactory, @Nullable SSLSocketFactory paramSSLSocketFactory, @Nullable HostnameVerifier paramHostnameVerifier, @Nullable CertificatePinner paramCertificatePinner, Authenticator paramAuthenticator, @Nullable Proxy paramProxy, List<Protocol> paramList, List<ConnectionSpec> paramList1, ProxySelector paramProxySelector)
  {
    HttpUrl.Builder localBuilder = new HttpUrl.Builder();
    String str;
    if (paramSSLSocketFactory != null)
      str = "https";
    else
      str = "http";
    this.url = localBuilder.scheme(str).host(paramString).port(paramInt).build();
    if (paramDns == null)
      throw new NullPointerException("dns == null");
    this.dns = paramDns;
    if (paramSocketFactory == null)
      throw new NullPointerException("socketFactory == null");
    this.socketFactory = paramSocketFactory;
    if (paramAuthenticator == null)
      throw new NullPointerException("proxyAuthenticator == null");
    this.proxyAuthenticator = paramAuthenticator;
    if (paramList == null)
      throw new NullPointerException("protocols == null");
    this.protocols = Util.immutableList(paramList);
    if (paramList1 == null)
      throw new NullPointerException("connectionSpecs == null");
    this.connectionSpecs = Util.immutableList(paramList1);
    if (paramProxySelector == null)
      throw new NullPointerException("proxySelector == null");
    this.proxySelector = paramProxySelector;
    this.proxy = paramProxy;
    this.sslSocketFactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramHostnameVerifier;
    this.certificatePinner = paramCertificatePinner;
  }

  @Nullable
  public CertificatePinner certificatePinner()
  {
    return this.certificatePinner;
  }

  public List<ConnectionSpec> connectionSpecs()
  {
    return this.connectionSpecs;
  }

  public Dns dns()
  {
    return this.dns;
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Address))
    {
      HttpUrl localHttpUrl = this.url;
      paramObject = (Address)paramObject;
      if ((localHttpUrl.equals(paramObject.url)) && (equalsNonHost(paramObject)))
        return true;
    }
    return false;
  }

  boolean equalsNonHost(Address paramAddress)
  {
    return (this.dns.equals(paramAddress.dns)) && (this.proxyAuthenticator.equals(paramAddress.proxyAuthenticator)) && (this.protocols.equals(paramAddress.protocols)) && (this.connectionSpecs.equals(paramAddress.connectionSpecs)) && (this.proxySelector.equals(paramAddress.proxySelector)) && (Objects.equals(this.proxy, paramAddress.proxy)) && (Objects.equals(this.sslSocketFactory, paramAddress.sslSocketFactory)) && (Objects.equals(this.hostnameVerifier, paramAddress.hostnameVerifier)) && (Objects.equals(this.certificatePinner, paramAddress.certificatePinner)) && (url().port() == paramAddress.url().port());
  }

  public int hashCode()
  {
    return (((((((((527 + this.url.hashCode()) * 31 + this.dns.hashCode()) * 31 + this.proxyAuthenticator.hashCode()) * 31 + this.protocols.hashCode()) * 31 + this.connectionSpecs.hashCode()) * 31 + this.proxySelector.hashCode()) * 31 + Objects.hashCode(this.proxy)) * 31 + Objects.hashCode(this.sslSocketFactory)) * 31 + Objects.hashCode(this.hostnameVerifier)) * 31 + Objects.hashCode(this.certificatePinner);
  }

  @Nullable
  public HostnameVerifier hostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public List<Protocol> protocols()
  {
    return this.protocols;
  }

  @Nullable
  public Proxy proxy()
  {
    return this.proxy;
  }

  public Authenticator proxyAuthenticator()
  {
    return this.proxyAuthenticator;
  }

  public ProxySelector proxySelector()
  {
    return this.proxySelector;
  }

  public SocketFactory socketFactory()
  {
    return this.socketFactory;
  }

  @Nullable
  public SSLSocketFactory sslSocketFactory()
  {
    return this.sslSocketFactory;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Address{");
    localStringBuilder.append(this.url.host());
    localStringBuilder.append(":");
    localStringBuilder.append(this.url.port());
    if (this.proxy != null)
    {
      localStringBuilder.append(", proxy=");
      localStringBuilder.append(this.proxy);
    }
    else
    {
      localStringBuilder.append(", proxySelector=");
      localStringBuilder.append(this.proxySelector);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  public HttpUrl url()
  {
    return this.url;
  }
}