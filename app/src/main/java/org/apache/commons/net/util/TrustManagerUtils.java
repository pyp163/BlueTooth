package org.apache.commons.net.util;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class TrustManagerUtils
{
  private static final X509TrustManager ACCEPT_ALL = new TrustManager(false);
  private static final X509TrustManager CHECK_SERVER_VALIDITY = new TrustManager(true);
  private static final X509Certificate[] EMPTY_X509CERTIFICATE_ARRAY = new X509Certificate[0];

  public static X509TrustManager getAcceptAllTrustManager()
  {
    return ACCEPT_ALL;
  }

  public static X509TrustManager getDefaultTrustManager(KeyStore paramKeyStore)
    throws GeneralSecurityException
  {
    TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    localTrustManagerFactory.init(paramKeyStore);
    return (X509TrustManager)localTrustManagerFactory.getTrustManagers()[0];
  }

  public static X509TrustManager getValidateServerCertificateTrustManager()
  {
    return CHECK_SERVER_VALIDITY;
  }

  private static class TrustManager
    implements X509TrustManager
  {
    private final boolean checkServerValidity;

    TrustManager(boolean paramBoolean)
    {
      this.checkServerValidity = paramBoolean;
    }

    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
    }

    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
      throws CertificateException
    {
      if (this.checkServerValidity)
      {
        int i = 0;
        while (i < paramArrayOfX509Certificate.length)
        {
          paramArrayOfX509Certificate[i].checkValidity();
          i += 1;
        }
      }
    }

    public X509Certificate[] getAcceptedIssuers()
    {
      return TrustManagerUtils.EMPTY_X509CERTIFICATE_ARRAY;
    }
  }
}