package org.apache.http.cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.params.HttpParams;

@ThreadSafe
public final class CookieSpecRegistry
{
  private final ConcurrentHashMap<String, CookieSpecFactory> registeredSpecs = new ConcurrentHashMap();

  public CookieSpec getCookieSpec(String paramString)
    throws IllegalStateException
  {
    return getCookieSpec(paramString, null);
  }

  public CookieSpec getCookieSpec(String paramString, HttpParams paramHttpParams)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    CookieSpecFactory localCookieSpecFactory = (CookieSpecFactory)this.registeredSpecs.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localCookieSpecFactory != null)
      return localCookieSpecFactory.newInstance(paramHttpParams);
    paramHttpParams = new StringBuilder();
    paramHttpParams.append("Unsupported cookie spec: ");
    paramHttpParams.append(paramString);
    throw new IllegalStateException(paramHttpParams.toString());
  }

  public List<String> getSpecNames()
  {
    return new ArrayList(this.registeredSpecs.keySet());
  }

  public void register(String paramString, CookieSpecFactory paramCookieSpecFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    if (paramCookieSpecFactory == null)
      throw new IllegalArgumentException("Cookie spec factory may not be null");
    this.registeredSpecs.put(paramString.toLowerCase(Locale.ENGLISH), paramCookieSpecFactory);
  }

  public void setItems(Map<String, CookieSpecFactory> paramMap)
  {
    if (paramMap == null)
      return;
    this.registeredSpecs.clear();
    this.registeredSpecs.putAll(paramMap);
  }

  public void unregister(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    this.registeredSpecs.remove(paramString.toLowerCase(Locale.ENGLISH));
  }
}