package okhttp3.internal.platform;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;

class AndroidPlatform extends Platform
{
  private static final int MAX_LOG_LENGTH = 4000;
  private final CloseGuard closeGuard = CloseGuard.get();
  private final Method getAlpnSelectedProtocol;
  private final Method setAlpnProtocols;
  private final Method setHostname;
  private final Method setUseSessionTickets;
  private final Class<?> sslParametersClass;
  private final Class<?> sslSocketClass;

  AndroidPlatform(Class<?> paramClass1, Class<?> paramClass2, Method paramMethod1, Method paramMethod2, Method paramMethod3, Method paramMethod4)
  {
    this.sslParametersClass = paramClass1;
    this.sslSocketClass = paramClass2;
    this.setUseSessionTickets = paramMethod1;
    this.setHostname = paramMethod2;
    this.getAlpnSelectedProtocol = paramMethod3;
    this.setAlpnProtocols = paramMethod4;
  }

  private boolean api23IsCleartextTrafficPermitted(String paramString, Class<?> paramClass, Object paramObject)
    throws InvocationTargetException, IllegalAccessException
  {
    try
    {
      boolean bool = ((Boolean)paramClass.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(paramObject, new Object[0])).booleanValue();
      return bool;
      label29: return super.isCleartextTrafficPermitted(paramString);
    }
    catch (NoSuchMethodException paramClass)
    {
      break label29;
    }
  }

  private boolean api24IsCleartextTrafficPermitted(String paramString, Class<?> paramClass, Object paramObject)
    throws InvocationTargetException, IllegalAccessException
  {
    try
    {
      boolean bool = ((Boolean)paramClass.getMethod("isCleartextTrafficPermitted", new Class[] { String.class }).invoke(paramObject, new Object[] { paramString })).booleanValue();
      return bool;
      label38: return api23IsCleartextTrafficPermitted(paramString, paramClass, paramObject);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label38;
    }
  }

  @Nullable
  public static Platform buildIfSupported()
  {
    try
    {
      localObject = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
      localClass = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
      if (Build.VERSION.SDK_INT < 21);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      try
      {
        Class localClass;
        Object localObject = new AndroidPlatform((Class)localObject, localClass, localClass.getDeclaredMethod("setUseSessionTickets", new Class[] { Boolean.TYPE }), localClass.getMethod("setHostname", new Class[] { String.class }), localClass.getMethod("getAlpnSelectedProtocol", new Class[0]), localClass.getMethod("setAlpnProtocols", new Class[] { [B.class }));
        return localObject;
        label88: localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Expected Android API level 21+ but was ");
        ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
        throw new IllegalStateException(((StringBuilder)localObject).toString());
        while (true)
        {
          return null;
          localClassNotFoundException = localClassNotFoundException;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        break label88;
      }
    }
  }

  public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = Class.forName("android.net.http.X509TrustManagerExtensions");
      localObject = new AndroidCertificateChainCleaner(((Class)localObject).getConstructor(new Class[] { X509TrustManager.class }).newInstance(new Object[] { paramX509TrustManager }), ((Class)localObject).getMethod("checkServerTrusted", new Class[] { [Ljava.security.cert.X509Certificate.class, String.class, String.class }));
      return localObject;
      label65: return super.buildCertificateChainCleaner(paramX509TrustManager);
    }
    catch (Exception localException)
    {
      break label65;
    }
  }

  public TrustRootIndex buildTrustRootIndex(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = paramX509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[] { X509Certificate.class });
      ((Method)localObject).setAccessible(true);
      localObject = new CustomTrustRootIndex(paramX509TrustManager, (Method)localObject);
      return localObject;
      label36: return super.buildTrustRootIndex(paramX509TrustManager);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label36;
    }
  }

  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    if (!this.sslSocketClass.isInstance(paramSSLSocket))
      return;
    if (paramString != null);
    try
    {
      this.setUseSessionTickets.invoke(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
      this.setHostname.invoke(paramSSLSocket, new Object[] { paramString });
      this.setAlpnProtocols.invoke(paramSSLSocket, new Object[] { concatLengthPrefixed(paramList) });
      return;
      label77: throw new AssertionError(paramSSLSocket);
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
      break label77;
    }
  }

  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    try
    {
      paramSocket.connect(paramInetSocketAddress, paramInt);
      return;
    }
    catch (ClassCastException paramSocket)
    {
      if (Build.VERSION.SDK_INT == 26)
        throw new IOException("Exception in connect", paramSocket);
      throw paramSocket;
    }
    catch (AssertionError paramSocket)
    {
      if (Util.isAndroidGetsocknameError(paramSocket))
        throw new IOException(paramSocket);
    }
    throw paramSocket;
  }

  public SSLContext getSSLContext()
  {
    int i = 1;
    try
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        int j = Build.VERSION.SDK_INT;
        if (j < 22);
      }
      else
      {
        i = 0;
      }
      if (i == 0);
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      try
      {
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1.2");
        return localSSLContext;
        try
        {
          label37: localSSLContext = SSLContext.getInstance("TLS");
          return localSSLContext;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException1)
        {
          throw new IllegalStateException("No TLS provider", localNoSuchAlgorithmException1);
        }
        localNoClassDefFoundError = localNoClassDefFoundError;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException2)
      {
        break label37;
      }
    }
  }

  @Nullable
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    boolean bool = this.sslSocketClass.isInstance(paramSSLSocket);
    Object localObject = null;
    if (!bool)
      return null;
    try
    {
      byte[] arrayOfByte = (byte[])this.getAlpnSelectedProtocol.invoke(paramSSLSocket, new Object[0]);
      paramSSLSocket = localObject;
      if (arrayOfByte != null)
        paramSSLSocket = new String(arrayOfByte, StandardCharsets.UTF_8);
      return paramSSLSocket;
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
    }
    throw new AssertionError(paramSSLSocket);
  }

  @Nullable
  public Object getStackTraceForCloseable(String paramString)
  {
    return this.closeGuard.createAndOpen(paramString);
  }

  public boolean isCleartextTrafficPermitted(String paramString)
  {
    try
    {
      Class localClass = Class.forName("android.security.NetworkSecurityPolicy");
      boolean bool = api24IsCleartextTrafficPermitted(paramString, localClass, localClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
      return bool;
    }
    catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException paramString)
    {
      throw new AssertionError("unable to determine cleartext support", paramString);
      return super.isCleartextTrafficPermitted(paramString);
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      label48: break label48;
    }
  }

  public void log(int paramInt, String paramString, @Nullable Throwable paramThrowable)
  {
    int i = 5;
    if (paramInt != 5)
      i = 3;
    Object localObject = paramString;
    if (paramThrowable != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append('\n');
      ((StringBuilder)localObject).append(Log.getStackTraceString(paramThrowable));
      localObject = ((StringBuilder)localObject).toString();
    }
    paramInt = 0;
    int k = ((String)localObject).length();
    if (paramInt < k)
    {
      int j = ((String)localObject).indexOf('\n', paramInt);
      if (j == -1)
        j = k;
      while (true)
      {
        int m = Math.min(j, paramInt + 4000);
        Log.println(i, "OkHttp", ((String)localObject).substring(paramInt, m));
        if (m >= j)
        {
          paramInt = m + 1;
          break;
        }
        paramInt = m;
      }
    }
  }

  public void logCloseableLeak(String paramString, Object paramObject)
  {
    if (!this.closeGuard.warnIfOpen(paramObject))
      log(5, paramString, null);
  }

  @Nullable
  protected X509TrustManager trustManager(SSLSocketFactory paramSSLSocketFactory)
  {
    Object localObject2 = readFieldOrNull(paramSSLSocketFactory, this.sslParametersClass, "sslParameters");
    Object localObject1 = localObject2;
    if (localObject2 == null);
    try
    {
      localObject1 = readFieldOrNull(paramSSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, paramSSLSocketFactory.getClass().getClassLoader()), "sslParameters");
      break label49;
      label43: return super.trustManager(paramSSLSocketFactory);
      label49: paramSSLSocketFactory = (X509TrustManager)readFieldOrNull(localObject1, X509TrustManager.class, "x509TrustManager");
      if (paramSSLSocketFactory != null)
        return paramSSLSocketFactory;
      return (X509TrustManager)readFieldOrNull(localObject1, X509TrustManager.class, "trustManager");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label43;
    }
  }

  static final class AndroidCertificateChainCleaner extends CertificateChainCleaner
  {
    private final Method checkServerTrusted;
    private final Object x509TrustManagerExtensions;

    AndroidCertificateChainCleaner(Object paramObject, Method paramMethod)
    {
      this.x509TrustManagerExtensions = paramObject;
      this.checkServerTrusted = paramMethod;
    }

    public List<Certificate> clean(List<Certificate> paramList, String paramString)
      throws SSLPeerUnverifiedException
    {
      try
      {
        paramList = (X509Certificate[])paramList.toArray(new X509Certificate[paramList.size()]);
        paramList = (List)this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, new Object[] { paramList, "RSA", paramString });
        return paramList;
      }
      catch (IllegalAccessException paramList)
      {
        throw new AssertionError(paramList);
      }
      catch (InvocationTargetException paramList)
      {
        paramString = new SSLPeerUnverifiedException(paramList.getMessage());
        paramString.initCause(paramList);
      }
      throw paramString;
    }

    public boolean equals(Object paramObject)
    {
      return paramObject instanceof AndroidCertificateChainCleaner;
    }

    public int hashCode()
    {
      return 0;
    }
  }

  static final class CloseGuard
  {
    private final Method getMethod;
    private final Method openMethod;
    private final Method warnIfOpenMethod;

    CloseGuard(Method paramMethod1, Method paramMethod2, Method paramMethod3)
    {
      this.getMethod = paramMethod1;
      this.openMethod = paramMethod2;
      this.warnIfOpenMethod = paramMethod3;
    }

    static CloseGuard get()
    {
      Object localObject1 = null;
      try
      {
        Object localObject2 = Class.forName("dalvik.system.CloseGuard");
        Method localMethod = ((Class)localObject2).getMethod("get", new Class[0]);
        Object localObject3 = ((Class)localObject2).getMethod("open", new Class[] { String.class });
        localObject2 = ((Class)localObject2).getMethod("warnIfOpen", new Class[0]);
        localObject1 = localMethod;
        break label55;
        label51: localObject2 = null;
        localObject3 = localObject2;
        label55: return new CloseGuard(localObject1, (Method)localObject3, (Method)localObject2);
      }
      catch (Exception localException)
      {
        break label51;
      }
    }

    Object createAndOpen(String paramString)
    {
      if (this.getMethod != null);
      try
      {
        Object localObject = this.getMethod.invoke(null, new Object[0]);
        this.openMethod.invoke(localObject, new Object[] { paramString });
        return localObject;
        return null;
      }
      catch (Exception paramString)
      {
      }
      return null;
    }

    boolean warnIfOpen(Object paramObject)
    {
      boolean bool = false;
      if (paramObject != null);
      try
      {
        this.warnIfOpenMethod.invoke(paramObject, new Object[0]);
        bool = true;
        return bool;
      }
      catch (Exception paramObject)
      {
      }
      return false;
    }
  }

  static final class CustomTrustRootIndex
    implements TrustRootIndex
  {
    private final Method findByIssuerAndSignatureMethod;
    private final X509TrustManager trustManager;

    CustomTrustRootIndex(X509TrustManager paramX509TrustManager, Method paramMethod)
    {
      this.findByIssuerAndSignatureMethod = paramMethod;
      this.trustManager = paramX509TrustManager;
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this)
        return true;
      if (!(paramObject instanceof CustomTrustRootIndex))
        return false;
      paramObject = (CustomTrustRootIndex)paramObject;
      return (this.trustManager.equals(paramObject.trustManager)) && (this.findByIssuerAndSignatureMethod.equals(paramObject.findByIssuerAndSignatureMethod));
    }

    public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
    {
      try
      {
        paramX509Certificate = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[] { paramX509Certificate });
        if (paramX509Certificate != null)
        {
          paramX509Certificate = paramX509Certificate.getTrustedCert();
          return paramX509Certificate;
        }
        return null;
      }
      catch (IllegalAccessException paramX509Certificate)
      {
        throw new AssertionError("unable to get issues and signature", paramX509Certificate);
      }
      catch (InvocationTargetException paramX509Certificate)
      {
      }
      return null;
    }

    public int hashCode()
    {
      return this.trustManager.hashCode() + this.findByIssuerAndSignatureMethod.hashCode() * 31;
    }
  }
}