package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.List<Ljava.security.cert.Certificate;>;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner
{
  public static final CertificatePinner DEFAULT = new Builder().build();

  @Nullable
  private final CertificateChainCleaner certificateChainCleaner;
  private final Set<Pin> pins;

  CertificatePinner(Set<Pin> paramSet, @Nullable CertificateChainCleaner paramCertificateChainCleaner)
  {
    this.pins = paramSet;
    this.certificateChainCleaner = paramCertificateChainCleaner;
  }

  public static String pin(Certificate paramCertificate)
  {
    if (!(paramCertificate instanceof X509Certificate))
      throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("sha256/");
    localStringBuilder.append(sha256((X509Certificate)paramCertificate).base64());
    return localStringBuilder.toString();
  }

  static ByteString sha1(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).sha1();
  }

  static ByteString sha256(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).sha256();
  }

  public void check(String paramString, List<Certificate> paramList)
    throws SSLPeerUnverifiedException
  {
    List localList = findMatchingPins(paramString);
    if (localList.isEmpty())
      return;
    Object localObject3 = paramList;
    if (this.certificateChainCleaner != null)
      localObject3 = this.certificateChainCleaner.clean(paramList, paramString);
    int m = ((List)localObject3).size();
    int k = 0;
    int i = 0;
    Object localObject1;
    while (i < m)
    {
      X509Certificate localX509Certificate = (X509Certificate)((List)localObject3).get(i);
      int n = localList.size();
      localObject1 = null;
      paramList = (List<Certificate>)localObject1;
      j = 0;
      while (j < n)
      {
        Pin localPin = (Pin)localList.get(j);
        Object localObject2;
        if (localPin.hashAlgorithm.equals("sha256/"))
        {
          localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = sha256(localX509Certificate);
          localObject1 = localObject2;
          if (!localPin.hash.equals(localObject2));
        }
        else
        {
          if (!localPin.hashAlgorithm.equals("sha1/"))
            break label211;
          localObject2 = paramList;
          if (paramList == null)
            localObject2 = sha1(localX509Certificate);
          paramList = (List<Certificate>)localObject2;
          if (localPin.hash.equals(localObject2))
            return;
        }
        j += 1;
        continue;
        label211: paramString = new StringBuilder();
        paramString.append("unsupported hashAlgorithm: ");
        paramString.append(localPin.hashAlgorithm);
        throw new AssertionError(paramString.toString());
      }
      i += 1;
    }
    paramList = new StringBuilder();
    paramList.append("Certificate pinning failure!");
    paramList.append("\n  Peer certificate chain:");
    int j = ((List)localObject3).size();
    i = 0;
    while (i < j)
    {
      localObject1 = (X509Certificate)((List)localObject3).get(i);
      paramList.append("\n    ");
      paramList.append(pin((Certificate)localObject1));
      paramList.append(": ");
      paramList.append(((X509Certificate)localObject1).getSubjectDN().getName());
      i += 1;
    }
    paramList.append("\n  Pinned certificates for ");
    paramList.append(paramString);
    paramList.append(":");
    j = localList.size();
    i = k;
    while (i < j)
    {
      paramString = (Pin)localList.get(i);
      paramList.append("\n    ");
      paramList.append(paramString);
      i += 1;
    }
    throw new SSLPeerUnverifiedException(paramList.toString());
  }

  public void check(String paramString, Certificate[] paramArrayOfCertificate)
    throws SSLPeerUnverifiedException
  {
    check(paramString, Arrays.asList(paramArrayOfCertificate));
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof CertificatePinner))
    {
      CertificateChainCleaner localCertificateChainCleaner = this.certificateChainCleaner;
      paramObject = (CertificatePinner)paramObject;
      if ((Objects.equals(localCertificateChainCleaner, paramObject.certificateChainCleaner)) && (this.pins.equals(paramObject.pins)))
        return true;
    }
    return false;
  }

  List<Pin> findMatchingPins(String paramString)
  {
    Object localObject1 = Collections.emptyList();
    Iterator localIterator = this.pins.iterator();
    while (localIterator.hasNext())
    {
      Pin localPin = (Pin)localIterator.next();
      if (localPin.matches(paramString))
      {
        Object localObject2 = localObject1;
        if (((List)localObject1).isEmpty())
          localObject2 = new ArrayList();
        ((List)localObject2).add(localPin);
        localObject1 = localObject2;
      }
    }
    return localObject1;
  }

  public int hashCode()
  {
    return Objects.hashCode(this.certificateChainCleaner) * 31 + this.pins.hashCode();
  }

  CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner paramCertificateChainCleaner)
  {
    if (Objects.equals(this.certificateChainCleaner, paramCertificateChainCleaner))
      return this;
    return new CertificatePinner(this.pins, paramCertificateChainCleaner);
  }

  public static final class Builder
  {
    private final List<CertificatePinner.Pin> pins = new ArrayList();

    public Builder add(String paramString, String[] paramArrayOfString)
    {
      if (paramString == null)
        throw new NullPointerException("pattern == null");
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramArrayOfString[i];
        this.pins.add(new CertificatePinner.Pin(paramString, str));
        i += 1;
      }
      return this;
    }

    public CertificatePinner build()
    {
      return new CertificatePinner(new LinkedHashSet(this.pins), null);
    }
  }

  static final class Pin
  {
    private static final String WILDCARD = "*.";
    final String canonicalHostname;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;

    Pin(String paramString1, String paramString2)
    {
      this.pattern = paramString1;
      StringBuilder localStringBuilder;
      if (paramString1.startsWith("*."))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://");
        localStringBuilder.append(paramString1.substring("*.".length()));
        paramString1 = HttpUrl.get(localStringBuilder.toString()).host();
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://");
        localStringBuilder.append(paramString1);
        paramString1 = HttpUrl.get(localStringBuilder.toString()).host();
      }
      this.canonicalHostname = paramString1;
      if (paramString2.startsWith("sha1/"))
      {
        this.hashAlgorithm = "sha1/";
        this.hash = ByteString.decodeBase64(paramString2.substring("sha1/".length()));
      }
      else
      {
        if (!paramString2.startsWith("sha256/"))
          break label204;
        this.hashAlgorithm = "sha256/";
        this.hash = ByteString.decodeBase64(paramString2.substring("sha256/".length()));
      }
      if (this.hash == null)
      {
        paramString1 = new StringBuilder();
        paramString1.append("pins must be base64: ");
        paramString1.append(paramString2);
        throw new IllegalArgumentException(paramString1.toString());
      }
      return;
      label204: paramString1 = new StringBuilder();
      paramString1.append("pins must start with 'sha256/' or 'sha1/': ");
      paramString1.append(paramString2);
      throw new IllegalArgumentException(paramString1.toString());
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Pin))
      {
        String str = this.pattern;
        paramObject = (Pin)paramObject;
        if ((str.equals(paramObject.pattern)) && (this.hashAlgorithm.equals(paramObject.hashAlgorithm)) && (this.hash.equals(paramObject.hash)))
          return true;
      }
      return false;
    }

    public int hashCode()
    {
      return ((527 + this.pattern.hashCode()) * 31 + this.hashAlgorithm.hashCode()) * 31 + this.hash.hashCode();
    }

    boolean matches(String paramString)
    {
      if (this.pattern.startsWith("*."))
      {
        int i = paramString.indexOf('.');
        return (paramString.length() - i - 1 == this.canonicalHostname.length()) && (paramString.regionMatches(false, i + 1, this.canonicalHostname, 0, this.canonicalHostname.length()));
      }
      return paramString.equals(this.canonicalHostname);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.hashAlgorithm);
      localStringBuilder.append(this.hash.base64());
      return localStringBuilder.toString();
    }
  }
}