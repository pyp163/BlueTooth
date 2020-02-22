package org.apache.commons.net.util;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509ExtendedKeyManager;

public final class KeyManagerUtils
{
  private static final String DEFAULT_STORE_TYPE = KeyStore.getDefaultType();

  public static KeyManager createClientKeyManager(File paramFile, String paramString)
    throws IOException, GeneralSecurityException
  {
    return createClientKeyManager(DEFAULT_STORE_TYPE, paramFile, paramString, null, paramString);
  }

  public static KeyManager createClientKeyManager(File paramFile, String paramString1, String paramString2)
    throws IOException, GeneralSecurityException
  {
    return createClientKeyManager(DEFAULT_STORE_TYPE, paramFile, paramString1, paramString2, paramString1);
  }

  public static KeyManager createClientKeyManager(String paramString1, File paramFile, String paramString2, String paramString3, String paramString4)
    throws IOException, GeneralSecurityException
  {
    return createClientKeyManager(loadStore(paramString1, paramFile, paramString2), paramString3, paramString4);
  }

  public static KeyManager createClientKeyManager(KeyStore paramKeyStore, String paramString1, String paramString2)
    throws GeneralSecurityException
  {
    if (paramString1 == null)
      paramString1 = findAlias(paramKeyStore);
    return new X509KeyManager(new ClientKeyStore(paramKeyStore, paramString1, paramString2));
  }

  private static String findAlias(KeyStore paramKeyStore)
    throws KeyStoreException
  {
    Enumeration localEnumeration = paramKeyStore.aliases();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      if (paramKeyStore.isKeyEntry(str))
        return str;
    }
    throw new KeyStoreException("Cannot find a private key entry");
  }

  // ERROR //
  private static KeyStore loadStore(String paramString1, File paramFile, String paramString2)
    throws KeyStoreException, IOException, GeneralSecurityException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 85	java/security/KeyStore:getInstance	(Ljava/lang/String;)Ljava/security/KeyStore;
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_0
    //   7: new 87	java/io/FileInputStream
    //   10: dup
    //   11: aload_1
    //   12: invokespecial 90	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: astore_1
    //   16: aload_3
    //   17: aload_1
    //   18: aload_2
    //   19: invokevirtual 94	java/lang/String:toCharArray	()[C
    //   22: invokevirtual 98	java/security/KeyStore:load	(Ljava/io/InputStream;[C)V
    //   25: aload_1
    //   26: invokestatic 104	org/apache/commons/net/io/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   29: aload_3
    //   30: areturn
    //   31: astore_2
    //   32: aload_1
    //   33: astore_0
    //   34: aload_2
    //   35: astore_1
    //   36: goto +4 -> 40
    //   39: astore_1
    //   40: aload_0
    //   41: invokestatic 104	org/apache/commons/net/io/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   44: aload_1
    //   45: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   16	25	31	finally
    //   7	16	39	finally
  }

  private static class ClientKeyStore
  {
    private final X509Certificate[] certChain;
    private final PrivateKey key;
    private final String keyAlias;

    ClientKeyStore(KeyStore paramKeyStore, String paramString1, String paramString2)
      throws GeneralSecurityException
    {
      this.keyAlias = paramString1;
      this.key = ((PrivateKey)paramKeyStore.getKey(this.keyAlias, paramString2.toCharArray()));
      paramKeyStore = paramKeyStore.getCertificateChain(this.keyAlias);
      paramString1 = new X509Certificate[paramKeyStore.length];
      int i = 0;
      while (i < paramKeyStore.length)
      {
        paramString1[i] = ((X509Certificate)paramKeyStore[i]);
        i += 1;
      }
      this.certChain = paramString1;
    }

    final String getAlias()
    {
      return this.keyAlias;
    }

    final X509Certificate[] getCertificateChain()
    {
      return this.certChain;
    }

    final PrivateKey getPrivateKey()
    {
      return this.key;
    }
  }

  private static class X509KeyManager extends X509ExtendedKeyManager
  {
    private final KeyManagerUtils.ClientKeyStore keyStore;

    X509KeyManager(KeyManagerUtils.ClientKeyStore paramClientKeyStore)
    {
      this.keyStore = paramClientKeyStore;
    }

    public String chooseClientAlias(String[] paramArrayOfString, Principal[] paramArrayOfPrincipal, Socket paramSocket)
    {
      return this.keyStore.getAlias();
    }

    public String chooseServerAlias(String paramString, Principal[] paramArrayOfPrincipal, Socket paramSocket)
    {
      return null;
    }

    public X509Certificate[] getCertificateChain(String paramString)
    {
      return this.keyStore.getCertificateChain();
    }

    public String[] getClientAliases(String paramString, Principal[] paramArrayOfPrincipal)
    {
      return new String[] { this.keyStore.getAlias() };
    }

    public PrivateKey getPrivateKey(String paramString)
    {
      return this.keyStore.getPrivateKey();
    }

    public String[] getServerAliases(String paramString, Principal[] paramArrayOfPrincipal)
    {
      return null;
    }
  }
}