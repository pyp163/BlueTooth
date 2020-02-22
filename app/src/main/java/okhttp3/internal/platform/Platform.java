package okhttp3.internal.platform;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;

public class Platform
{
  public static final int INFO = 4;
  private static final Platform PLATFORM = findPlatform();
  public static final int WARN = 5;
  private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

  public static List<String> alpnProtocolNames(List<Protocol> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol != Protocol.HTTP_1_0)
        localArrayList.add(localProtocol.toString());
      i += 1;
    }
    return localArrayList;
  }

  static byte[] concatLengthPrefixed(List<Protocol> paramList)
  {
    Buffer localBuffer = new Buffer();
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol != Protocol.HTTP_1_0)
      {
        localBuffer.writeByte(localProtocol.toString().length());
        localBuffer.writeUtf8(localProtocol.toString());
      }
      i += 1;
    }
    return localBuffer.readByteArray();
  }

  private static Platform findPlatform()
  {
    Object localObject = AndroidPlatform.buildIfSupported();
    if (localObject != null)
      return localObject;
    if (isConscryptPreferred())
    {
      localObject = ConscryptPlatform.buildIfSupported();
      if (localObject != null)
        return localObject;
    }
    localObject = Jdk9Platform.buildIfSupported();
    if (localObject != null)
      return localObject;
    localObject = Jdk8WithJettyBootPlatform.buildIfSupported();
    if (localObject != null)
      return localObject;
    return new Platform();
  }

  public static Platform get()
  {
    return PLATFORM;
  }

  public static boolean isConscryptPreferred()
  {
    if ("conscrypt".equals(Util.getSystemProperty("okhttp.platform", null)))
      return true;
    return "Conscrypt".equals(java.security.Security.getProviders()[0].getName());
  }

  @Nullable
  static <T> T readFieldOrNull(Object paramObject, Class<T> paramClass, String paramString)
  {
    Class localClass = paramObject.getClass();
    while (true)
    {
      if (localClass != Object.class);
      try
      {
        Object localObject = localClass.getDeclaredField(paramString);
        ((Field)localObject).setAccessible(true);
        localObject = ((Field)localObject).get(paramObject);
        if (!paramClass.isInstance(localObject))
          return null;
        localObject = paramClass.cast(localObject);
        return localObject;
        label54: throw new AssertionError();
        label62: localClass = localClass.getSuperclass();
        continue;
        if (!paramString.equals("delegate"))
        {
          paramObject = readFieldOrNull(paramObject, Object.class, "delegate");
          if (paramObject != null)
            return readFieldOrNull(paramObject, paramClass, paramString);
        }
        return null;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        break label62;
      }
      catch (IllegalAccessException paramObject)
      {
        break label54;
      }
    }
  }

  public void afterHandshake(SSLSocket paramSSLSocket)
  {
  }

  public CertificateChainCleaner buildCertificateChainCleaner(SSLSocketFactory paramSSLSocketFactory)
  {
    Object localObject = trustManager(paramSSLSocketFactory);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unable to extract the trust manager on ");
      ((StringBuilder)localObject).append(get());
      ((StringBuilder)localObject).append(", sslSocketFactory is ");
      ((StringBuilder)localObject).append(paramSSLSocketFactory.getClass());
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    return buildCertificateChainCleaner((X509TrustManager)localObject);
  }

  public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager paramX509TrustManager)
  {
    return new BasicCertificateChainCleaner(buildTrustRootIndex(paramX509TrustManager));
  }

  public TrustRootIndex buildTrustRootIndex(X509TrustManager paramX509TrustManager)
  {
    return new BasicTrustRootIndex(paramX509TrustManager.getAcceptedIssuers());
  }

  public void configureSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
  }

  public void configureTlsExtensions(SSLSocket paramSSLSocket, @Nullable String paramString, List<Protocol> paramList)
  {
  }

  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }

  public String getPrefix()
  {
    return "OkHttp";
  }

  public SSLContext getSSLContext()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      return localSSLContext;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new IllegalStateException("No TLS provider", localNoSuchAlgorithmException);
    }
  }

  @Nullable
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    return null;
  }

  @Nullable
  public Object getStackTraceForCloseable(String paramString)
  {
    if (logger.isLoggable(Level.FINE))
      return new Throwable(paramString);
    return null;
  }

  public boolean isCleartextTrafficPermitted(String paramString)
  {
    return true;
  }

  public void log(int paramInt, String paramString, @Nullable Throwable paramThrowable)
  {
    Level localLevel;
    if (paramInt == 5)
      localLevel = Level.WARNING;
    else
      localLevel = Level.INFO;
    logger.log(localLevel, paramString, paramThrowable);
  }

  public void logCloseableLeak(String paramString, Object paramObject)
  {
    Object localObject = paramString;
    if (paramObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
      localObject = ((StringBuilder)localObject).toString();
    }
    log(5, (String)localObject, (Throwable)paramObject);
  }

  public String toString()
  {
    return getClass().getSimpleName();
  }

  @Nullable
  protected X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    try
    {
      paramSSLSocketFactory = readFieldOrNull(paramSSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
      if (paramSSLSocketFactory == null)
        return null;
      paramSSLSocketFactory = (X509TrustManager)readFieldOrNull(paramSSLSocketFactory, X509TrustManager.class, "trustManager");
      return paramSSLSocketFactory;
    }
    catch (ClassNotFoundException paramSSLSocketFactory)
    {
    }
    return null;
  }
}