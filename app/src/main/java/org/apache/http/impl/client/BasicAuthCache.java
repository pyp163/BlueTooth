package org.apache.http.impl.client;

import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthCache;

@NotThreadSafe
public class BasicAuthCache
  implements AuthCache
{
  private final HashMap<HttpHost, AuthScheme> map = new HashMap();

  public void clear()
  {
    this.map.clear();
  }

  public AuthScheme get(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    return (AuthScheme)this.map.get(paramHttpHost);
  }

  public void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.put(paramHttpHost, paramAuthScheme);
  }

  public void remove(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("HTTP host may not be null");
    this.map.remove(paramHttpHost);
  }

  public String toString()
  {
    return this.map.toString();
  }
}