package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

final class Jdk9Platform extends Platform
{
  final Method getProtocolMethod;
  final Method setProtocolMethod;

  Jdk9Platform(Method paramMethod1, Method paramMethod2)
  {
    this.setProtocolMethod = paramMethod1;
    this.getProtocolMethod = paramMethod2;
  }

  public static Jdk9Platform buildIfSupported()
  {
    try
    {
      Jdk9Platform localJdk9Platform = new Jdk9Platform(SSLParameters.class.getMethod("setApplicationProtocols", new Class[] { [Ljava.lang.String.class }), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
      return localJdk9Platform;
      label37: return null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label37;
    }
  }

  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    try
    {
      paramString = paramSSLSocket.getSSLParameters();
      paramList = alpnProtocolNames(paramList);
      this.setProtocolMethod.invoke(paramString, new Object[] { paramList.toArray(new String[paramList.size()]) });
      paramSSLSocket.setSSLParameters(paramString);
      return;
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
    }
    throw new AssertionError("failed to set SSL parameters", paramSSLSocket);
  }

  @Nullable
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    try
    {
      paramSSLSocket = (String)this.getProtocolMethod.invoke(paramSSLSocket, new Object[0]);
      if (paramSSLSocket != null)
      {
        boolean bool = paramSSLSocket.equals("");
        if (!bool)
          return paramSSLSocket;
      }
      return null;
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
    }
    throw new AssertionError("failed to get ALPN selected protocol", paramSSLSocket);
  }

  public X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
  }
}