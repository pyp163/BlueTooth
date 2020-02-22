package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UriPatternMatcher
{
  private final Map map = new HashMap();

  public Object lookup(String paramString)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("Request URI may not be null");
      int i = paramString.indexOf("?");
      String str1 = paramString;
      if (i != -1)
        str1 = paramString.substring(0, i);
      paramString = this.map.get(str1);
      String str3 = paramString;
      if (paramString == null)
      {
        String str2 = null;
        Iterator localIterator = this.map.keySet().iterator();
        while (true)
        {
          str3 = paramString;
          if (!localIterator.hasNext())
            break;
          str3 = (String)localIterator.next();
          if ((matchUriRequestPattern(str3, str1)) && ((str2 == null) || (str2.length() < str3.length()) || ((str2.length() == str3.length()) && (str3.endsWith("*")))))
          {
            paramString = this.map.get(str3);
            str2 = str3;
          }
        }
      }
      return str3;
      label174: throw paramString;
    }
    finally
    {
      break label174;
    }
  }

  protected boolean matchUriRequestPattern(String paramString1, String paramString2)
  {
    boolean bool2 = paramString1.equals("*");
    boolean bool1 = true;
    if (bool2)
      return true;
    if ((!paramString1.endsWith("*")) || (!paramString2.startsWith(paramString1.substring(0, paramString1.length() - 1))))
    {
      if ((paramString1.startsWith("*")) && (paramString2.endsWith(paramString1.substring(1, paramString1.length()))))
        return true;
      bool1 = false;
    }
    return bool1;
  }

  public void register(String paramString, Object paramObject)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("URI request pattern may not be null");
      this.map.put(paramString, paramObject);
      return;
      label31: throw paramString;
    }
    finally
    {
      break label31;
    }
  }

  public void setHandlers(Map paramMap)
  {
    if (paramMap == null);
    try
    {
      throw new IllegalArgumentException("Map of handlers may not be null");
      this.map.clear();
      this.map.putAll(paramMap);
      return;
      label38: throw paramMap;
    }
    finally
    {
      break label38;
    }
  }

  public void setObjects(Map paramMap)
  {
    if (paramMap == null);
    try
    {
      throw new IllegalArgumentException("Map of handlers may not be null");
      this.map.clear();
      this.map.putAll(paramMap);
      return;
      label38: throw paramMap;
    }
    finally
    {
      break label38;
    }
  }

  public void unregister(String paramString)
  {
    if (paramString == null)
      return;
    try
    {
      this.map.remove(paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }
}