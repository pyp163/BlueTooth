package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;

public final class Handshake
{
  private final CipherSuite cipherSuite;
  private final List<Certificate> localCertificates;
  private final List<Certificate> peerCertificates;
  private final TlsVersion tlsVersion;

  private Handshake(TlsVersion paramTlsVersion, CipherSuite paramCipherSuite, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    this.tlsVersion = paramTlsVersion;
    this.cipherSuite = paramCipherSuite;
    this.peerCertificates = paramList1;
    this.localCertificates = paramList2;
  }

  public static Handshake get(SSLSession paramSSLSession)
    throws IOException
  {
    Object localObject = paramSSLSession.getCipherSuite();
    if (localObject == null)
      throw new IllegalStateException("cipherSuite == null");
    if ("SSL_NULL_WITH_NULL_NULL".equals(localObject))
      throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
    CipherSuite localCipherSuite = CipherSuite.forJavaName((String)localObject);
    localObject = paramSSLSession.getProtocol();
    if (localObject == null)
      throw new IllegalStateException("tlsVersion == null");
    if ("NONE".equals(localObject))
      throw new IOException("tlsVersion == NONE");
    TlsVersion localTlsVersion = TlsVersion.forJavaName((String)localObject);
    try
    {
      localObject = paramSSLSession.getPeerCertificates();
      break label102;
      label100: localObject = null;
      label102: if (localObject != null)
        localObject = Util.immutableList((Object[])localObject);
      else
        localObject = Collections.emptyList();
      paramSSLSession = paramSSLSession.getLocalCertificates();
      if (paramSSLSession != null)
        paramSSLSession = Util.immutableList(paramSSLSession);
      else
        paramSSLSession = Collections.emptyList();
      return new Handshake(localTlsVersion, localCipherSuite, (List)localObject, paramSSLSession);
    }
    catch (SSLPeerUnverifiedException localSSLPeerUnverifiedException)
    {
      break label100;
    }
  }

  public static Handshake get(TlsVersion paramTlsVersion, CipherSuite paramCipherSuite, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    if (paramTlsVersion == null)
      throw new NullPointerException("tlsVersion == null");
    if (paramCipherSuite == null)
      throw new NullPointerException("cipherSuite == null");
    return new Handshake(paramTlsVersion, paramCipherSuite, Util.immutableList(paramList1), Util.immutableList(paramList2));
  }

  private List<String> names(List<Certificate> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Certificate localCertificate = (Certificate)paramList.next();
      if ((localCertificate instanceof X509Certificate))
        localArrayList.add(String.valueOf(((X509Certificate)localCertificate).getSubjectDN()));
      else
        localArrayList.add(localCertificate.getType());
    }
    return localArrayList;
  }

  public CipherSuite cipherSuite()
  {
    return this.cipherSuite;
  }

  public boolean equals(@Nullable Object paramObject)
  {
    boolean bool1 = paramObject instanceof Handshake;
    boolean bool2 = false;
    if (!bool1)
      return false;
    paramObject = (Handshake)paramObject;
    bool1 = bool2;
    if (this.tlsVersion.equals(paramObject.tlsVersion))
    {
      bool1 = bool2;
      if (this.cipherSuite.equals(paramObject.cipherSuite))
      {
        bool1 = bool2;
        if (this.peerCertificates.equals(paramObject.peerCertificates))
        {
          bool1 = bool2;
          if (this.localCertificates.equals(paramObject.localCertificates))
            bool1 = true;
        }
      }
    }
    return bool1;
  }

  public int hashCode()
  {
    return (((527 + this.tlsVersion.hashCode()) * 31 + this.cipherSuite.hashCode()) * 31 + this.peerCertificates.hashCode()) * 31 + this.localCertificates.hashCode();
  }

  public List<Certificate> localCertificates()
  {
    return this.localCertificates;
  }

  @Nullable
  public Principal localPrincipal()
  {
    if (!this.localCertificates.isEmpty())
      return ((X509Certificate)this.localCertificates.get(0)).getSubjectX500Principal();
    return null;
  }

  public List<Certificate> peerCertificates()
  {
    return this.peerCertificates;
  }

  @Nullable
  public Principal peerPrincipal()
  {
    if (!this.peerCertificates.isEmpty())
      return ((X509Certificate)this.peerCertificates.get(0)).getSubjectX500Principal();
    return null;
  }

  public TlsVersion tlsVersion()
  {
    return this.tlsVersion;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Handshake{tlsVersion=");
    localStringBuilder.append(this.tlsVersion);
    localStringBuilder.append(" cipherSuite=");
    localStringBuilder.append(this.cipherSuite);
    localStringBuilder.append(" peerCertificates=");
    localStringBuilder.append(names(this.peerCertificates));
    localStringBuilder.append(" localCertificates=");
    localStringBuilder.append(names(this.localCertificates));
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}