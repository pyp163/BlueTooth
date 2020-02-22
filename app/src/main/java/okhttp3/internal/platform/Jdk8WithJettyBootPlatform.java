package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;

class Jdk8WithJettyBootPlatform extends Platform
{
  private final Class<?> clientProviderClass;
  private final Method getMethod;
  private final Method putMethod;
  private final Method removeMethod;
  private final Class<?> serverProviderClass;

  Jdk8WithJettyBootPlatform(Method paramMethod1, Method paramMethod2, Method paramMethod3, Class<?> paramClass1, Class<?> paramClass2)
  {
    this.putMethod = paramMethod1;
    this.getMethod = paramMethod2;
    this.removeMethod = paramMethod3;
    this.clientProviderClass = paramClass1;
    this.serverProviderClass = paramClass2;
  }

  public static Platform buildIfSupported()
  {
    try
    {
      Object localObject1 = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject2).append("$Provider");
      localObject2 = Class.forName(((StringBuilder)localObject2).toString(), true, null);
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject3).append("$ClientProvider");
      localObject3 = Class.forName(((StringBuilder)localObject3).toString(), true, null);
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject4).append("$ServerProvider");
      localObject4 = Class.forName(((StringBuilder)localObject4).toString(), true, null);
      localObject1 = new Jdk8WithJettyBootPlatform(((Class)localObject1).getMethod("put", new Class[] { SSLSocket.class, localObject2 }), ((Class)localObject1).getMethod("get", new Class[] { SSLSocket.class }), ((Class)localObject1).getMethod("remove", new Class[] { SSLSocket.class }), (Class)localObject3, (Class)localObject4);
      return localObject1;
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
    }
    return null;
  }

  public void afterHandshake(SSLSocket paramSSLSocket)
  {
    try
    {
      this.removeMethod.invoke(null, new Object[] { paramSSLSocket });
      return;
    }
    catch (IllegalAccessException|InvocationTargetException paramSSLSocket)
    {
    }
    throw new AssertionError("failed to remove ALPN", paramSSLSocket);
  }

  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
    Object localObject = alpnProtocolNames(paramList);
    try
    {
      paramString = Platform.class.getClassLoader();
      paramList = this.clientProviderClass;
      Class localClass = this.serverProviderClass;
      localObject = new AlpnProvider((List)localObject);
      paramString = Proxy.newProxyInstance(paramString, new Class[] { paramList, localClass }, (InvocationHandler)localObject);
      this.putMethod.invoke(null, new Object[] { paramSSLSocket, paramString });
      return;
    }
    catch (InvocationTargetException|IllegalAccessException paramSSLSocket)
    {
    }
    throw new AssertionError("failed to set ALPN", paramSSLSocket);
  }

  @Nullable
  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    try
    {
      paramSSLSocket = (AlpnProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[] { paramSSLSocket }));
      if ((!paramSSLSocket.unsupported) && (paramSSLSocket.selected == null))
      {
        Platform.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
        return null;
      }
      if (paramSSLSocket.unsupported)
        return null;
      paramSSLSocket = paramSSLSocket.selected;
      return paramSSLSocket;
    }
    catch (InvocationTargetException|IllegalAccessException paramSSLSocket)
    {
    }
    throw new AssertionError("failed to get ALPN selected protocol", paramSSLSocket);
  }

  private static class AlpnProvider
    implements InvocationHandler
  {
    private final List<String> protocols;
    String selected;
    boolean unsupported;

    AlpnProvider(List<String> paramList)
    {
      this.protocols = paramList;
    }

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      String str = paramMethod.getName();
      Class localClass = paramMethod.getReturnType();
      paramObject = paramArrayOfObject;
      if (paramArrayOfObject == null)
        paramObject = Util.EMPTY_STRING_ARRAY;
      if ((str.equals("supports")) && (Boolean.TYPE == localClass))
        return Boolean.valueOf(true);
      if ((str.equals("unsupported")) && (Void.TYPE == localClass))
      {
        this.unsupported = true;
        return null;
      }
      if ((str.equals("protocols")) && (paramObject.length == 0))
        return this.protocols;
      if (((str.equals("selectProtocol")) || (str.equals("select"))) && (String.class == localClass) && (paramObject.length == 1) && ((paramObject[0] instanceof List)))
      {
        paramObject = (List)paramObject[0];
        int j = paramObject.size();
        int i = 0;
        while (i < j)
        {
          paramMethod = (String)paramObject.get(i);
          if (this.protocols.contains(paramMethod))
          {
            this.selected = paramMethod;
            return paramMethod;
          }
          i += 1;
        }
        paramObject = (String)this.protocols.get(0);
        this.selected = paramObject;
        return paramObject;
      }
      if (((str.equals("protocolSelected")) || (str.equals("selected"))) && (paramObject.length == 1))
      {
        this.selected = ((String)paramObject[0]);
        return null;
      }
      return paramMethod.invoke(this, paramObject);
    }
  }
}