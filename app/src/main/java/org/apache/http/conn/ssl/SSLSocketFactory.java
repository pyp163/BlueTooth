package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@ThreadSafe
public class SSLSocketFactory
  implements LayeredSchemeSocketFactory, LayeredSocketFactory
{
  public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
  public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
  public static final String SSL = "SSL";
  public static final String SSLV2 = "SSLv2";
  public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
  public static final String TLS = "TLS";
  private volatile X509HostnameVerifier hostnameVerifier;
  private final HostNameResolver nameResolver;
  private final javax.net.ssl.SSLSocketFactory socketfactory;

  private SSLSocketFactory()
  {
    this(createDefaultSSLContext());
  }

  @Deprecated
  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, HostNameResolver paramHostNameResolver)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramHostNameResolver);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, paramTrustStrategy), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(KeyStore paramKeyStore)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, paramKeyStore, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore, String paramString)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore, paramString, null, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore1, String paramString, KeyStore paramKeyStore2)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore1, paramString, paramKeyStore2, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(SSLContext paramSSLContext)
  {
    this(paramSSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  @Deprecated
  public SSLSocketFactory(SSLContext paramSSLContext, HostNameResolver paramHostNameResolver)
  {
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    this.nameResolver = paramHostNameResolver;
  }

  public SSLSocketFactory(SSLContext paramSSLContext, X509HostnameVerifier paramX509HostnameVerifier)
  {
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, paramX509HostnameVerifier);
  }

  private static SSLContext createDefaultSSLContext()
  {
    try
    {
      SSLContext localSSLContext = createSSLContext("TLS", null, null, null, null, null);
      return localSSLContext;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException("Failure initializing default SSL context", localException);
    }
  }

  private static SSLContext createSSLContext(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException
  {
    String str = paramString1;
    if (paramString1 == null)
      str = "TLS";
    KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    if (paramString2 != null)
      paramString1 = paramString2.toCharArray();
    else
      paramString1 = null;
    localKeyManagerFactory.init(paramKeyStore1, paramString1);
    paramString1 = localKeyManagerFactory.getKeyManagers();
    paramKeyStore1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    paramKeyStore1.init(paramKeyStore2);
    paramKeyStore1 = paramKeyStore1.getTrustManagers();
    if ((paramKeyStore1 != null) && (paramTrustStrategy != null))
    {
      int i = 0;
      while (i < paramKeyStore1.length)
      {
        paramString2 = paramKeyStore1[i];
        if ((paramString2 instanceof X509TrustManager))
          paramKeyStore1[i] = new TrustManagerDecorator((X509TrustManager)paramString2, paramTrustStrategy);
        i += 1;
      }
    }
    paramString2 = SSLContext.getInstance(str);
    paramString2.init(paramString1, paramKeyStore1, paramSecureRandom);
    return paramString2;
  }

  public static SSLSocketFactory getSocketFactory()
  {
    return new SSLSocketFactory();
  }

  @Deprecated
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    if ((paramInetAddress == null) && (paramInt2 <= 0))
    {
      paramInetAddress = null;
    }
    else
    {
      int i = paramInt2;
      if (paramInt2 < 0)
        i = 0;
      paramInetAddress = new InetSocketAddress(paramInetAddress, i);
    }
    if (this.nameResolver != null)
      paramString = this.nameResolver.resolve(paramString);
    else
      paramString = InetAddress.getByName(paramString);
    return connectSocket(paramSocket, new InetSocketAddress(paramString, paramInt1), paramInetAddress, paramHttpParams);
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    if (paramInetSocketAddress1 == null)
      throw new IllegalArgumentException("Remote address may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    if (paramSocket == null)
      paramSocket = new Socket();
    if (paramInetSocketAddress2 != null)
    {
      paramSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      paramSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      paramSocket.setSoTimeout(j);
      paramSocket.connect(paramInetSocketAddress1, i);
      paramInetSocketAddress2 = paramInetSocketAddress1.toString();
      i = paramInetSocketAddress1.getPort();
      paramInetSocketAddress1 = new StringBuilder();
      paramInetSocketAddress1.append(":");
      paramInetSocketAddress1.append(i);
      paramHttpParams = paramInetSocketAddress1.toString();
      paramInetSocketAddress1 = paramInetSocketAddress2;
      if (paramInetSocketAddress2.endsWith(paramHttpParams))
        paramInetSocketAddress1 = paramInetSocketAddress2.substring(0, paramInetSocketAddress2.length() - paramHttpParams.length());
      if ((paramSocket instanceof SSLSocket))
        paramSocket = (SSLSocket)paramSocket;
      else
        paramSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramInetSocketAddress1, i, true);
      if (this.hostnameVerifier != null)
        try
        {
          this.hostnameVerifier.verify(paramInetSocketAddress1, paramSocket);
          return paramSocket;
        }
        catch (IOException paramInetSocketAddress1)
        {
        }
    }
    catch (SocketTimeoutException paramSocket)
    {
      try
      {
        paramSocket.close();
        label211: throw paramInetSocketAddress1;
        return paramSocket;
        while (true)
        {
          paramSocket = new StringBuilder();
          paramSocket.append("Connect to ");
          paramSocket.append(paramInetSocketAddress1);
          paramSocket.append(" timed out");
          throw new ConnectTimeoutException(paramSocket.toString());
          paramSocket = paramSocket;
        }
      }
      catch (Exception paramSocket)
      {
        break label211;
      }
    }
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    paramSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, paramSocket);
    return paramSocket;
  }

  @Deprecated
  public Socket createSocket()
    throws IOException
  {
    return this.socketfactory.createSocket();
  }

  @Deprecated
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, paramBoolean);
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    return this.socketfactory.createSocket();
  }

  public X509HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (!(paramSocket instanceof SSLSocket))
      throw new IllegalArgumentException("Socket not created by this factory");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed");
    return true;
  }

  @Deprecated
  public void setHostnameVerifier(X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramX509HostnameVerifier == null)
      throw new IllegalArgumentException("Hostname verifier may not be null");
    this.hostnameVerifier = paramX509HostnameVerifier;
  }
}