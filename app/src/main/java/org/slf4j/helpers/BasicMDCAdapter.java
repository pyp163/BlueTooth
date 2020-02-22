package org.slf4j.helpers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter
  implements MDCAdapter
{
  static boolean IS_JDK14 = isJDK14();
  private InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

  static boolean isJDK14()
  {
    try
    {
      boolean bool = System.getProperty("java.version").startsWith("1.4");
      return bool;
      label13: return false;
    }
    catch (SecurityException localSecurityException)
    {
      break label13;
    }
  }

  public void clear()
  {
    Map localMap = (Map)this.inheritableThreadLocal.get();
    if (localMap != null)
    {
      localMap.clear();
      if (isJDK14())
      {
        this.inheritableThreadLocal.set(null);
        return;
      }
      this.inheritableThreadLocal.remove();
    }
  }

  public String get(String paramString)
  {
    Map localMap = (Map)this.inheritableThreadLocal.get();
    if ((localMap != null) && (paramString != null))
      return (String)localMap.get(paramString);
    return null;
  }

  public Map getCopyOfContextMap()
  {
    Map localMap1 = (Map)this.inheritableThreadLocal.get();
    if (localMap1 != null)
    {
      Map localMap2 = Collections.synchronizedMap(new HashMap());
      try
      {
        localMap2.putAll(localMap1);
        return localMap2;
      }
      finally
      {
      }
    }
    return null;
  }

  public Set getKeys()
  {
    Map localMap = (Map)this.inheritableThreadLocal.get();
    if (localMap != null)
      return localMap.keySet();
    return null;
  }

  public void put(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("key cannot be null");
    Map localMap2 = (Map)this.inheritableThreadLocal.get();
    Map localMap1 = localMap2;
    if (localMap2 == null)
    {
      localMap1 = Collections.synchronizedMap(new HashMap());
      this.inheritableThreadLocal.set(localMap1);
    }
    localMap1.put(paramString1, paramString2);
  }

  public void remove(String paramString)
  {
    Map localMap = (Map)this.inheritableThreadLocal.get();
    if (localMap != null)
      localMap.remove(paramString);
  }

  public void setContextMap(Map paramMap)
  {
    paramMap = Collections.synchronizedMap(new HashMap(paramMap));
    this.inheritableThreadLocal.set(paramMap);
  }
}