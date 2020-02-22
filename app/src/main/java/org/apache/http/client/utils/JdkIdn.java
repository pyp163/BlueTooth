package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.annotation.Immutable;

@Immutable
public class JdkIdn
  implements Idn
{
  private final Method toUnicode;

  public JdkIdn()
    throws ClassNotFoundException
  {
    Class localClass = Class.forName("java.net.IDN");
    try
    {
      this.toUnicode = localClass.getMethod("toUnicode", new Class[] { String.class });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new IllegalStateException(localNoSuchMethodException.getMessage(), localNoSuchMethodException);
    }
    catch (SecurityException localSecurityException)
    {
      throw new IllegalStateException(localSecurityException.getMessage(), localSecurityException);
    }
  }

  public String toUnicode(String paramString)
  {
    try
    {
      paramString = (String)this.toUnicode.invoke(null, new Object[] { paramString });
      return paramString;
    }
    catch (InvocationTargetException paramString)
    {
      paramString = paramString.getCause();
      throw new RuntimeException(paramString.getMessage(), paramString);
    }
    catch (IllegalAccessException paramString)
    {
    }
    throw new IllegalStateException(paramString.getMessage(), paramString);
  }
}