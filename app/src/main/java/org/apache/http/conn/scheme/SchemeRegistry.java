package org.apache.http.conn.scheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.annotation.ThreadSafe;

@ThreadSafe
public final class SchemeRegistry
{
  private final ConcurrentHashMap<String, Scheme> registeredSchemes = new ConcurrentHashMap();

  public final Scheme get(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null.");
    return (Scheme)this.registeredSchemes.get(paramString);
  }

  public final Scheme getScheme(String paramString)
  {
    Object localObject = get(paramString);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Scheme '");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("' not registered.");
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    return localObject;
  }

  public final Scheme getScheme(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host must not be null.");
    return getScheme(paramHttpHost.getSchemeName());
  }

  public final List<String> getSchemeNames()
  {
    return new ArrayList(this.registeredSchemes.keySet());
  }

  public final Scheme register(Scheme paramScheme)
  {
    if (paramScheme == null)
      throw new IllegalArgumentException("Scheme must not be null.");
    return (Scheme)this.registeredSchemes.put(paramScheme.getName(), paramScheme);
  }

  public void setItems(Map<String, Scheme> paramMap)
  {
    if (paramMap == null)
      return;
    this.registeredSchemes.clear();
    this.registeredSchemes.putAll(paramMap);
  }

  public final Scheme unregister(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null.");
    return (Scheme)this.registeredSchemes.remove(paramString);
  }
}