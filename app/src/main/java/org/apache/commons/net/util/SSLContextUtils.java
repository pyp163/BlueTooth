package org.apache.commons.net.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class SSLContextUtils
{
  public static SSLContext createSSLContext(String paramString, KeyManager paramKeyManager, TrustManager paramTrustManager)
    throws IOException
  {
    TrustManager[] arrayOfTrustManager = null;
    if (paramKeyManager == null)
    {
      paramKeyManager = null;
    }
    else
    {
      KeyManager[] arrayOfKeyManager = new KeyManager[1];
      arrayOfKeyManager[0] = paramKeyManager;
      paramKeyManager = arrayOfKeyManager;
    }
    if (paramTrustManager == null)
    {
      paramTrustManager = arrayOfTrustManager;
    }
    else
    {
      arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = paramTrustManager;
      paramTrustManager = arrayOfTrustManager;
    }
    return createSSLContext(paramString, paramKeyManager, paramTrustManager);
  }

  public static SSLContext createSSLContext(String paramString, KeyManager[] paramArrayOfKeyManager, TrustManager[] paramArrayOfTrustManager)
    throws IOException
  {
    try
    {
      paramString = SSLContext.getInstance(paramString);
      paramString.init(paramArrayOfKeyManager, paramArrayOfTrustManager, null);
      return paramString;
    }
    catch (GeneralSecurityException paramString)
    {
      paramArrayOfKeyManager = new IOException("Could not initialize SSL context");
      paramArrayOfKeyManager.initCause(paramString);
    }
    throw paramArrayOfKeyManager;
  }
}