package org.apache.http.impl.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;

@ThreadSafe
public class BasicCredentialsProvider
  implements CredentialsProvider
{
  private final ConcurrentHashMap<AuthScope, Credentials> credMap = new ConcurrentHashMap();

  private static Credentials matchCredentials(Map<AuthScope, Credentials> paramMap, AuthScope paramAuthScope)
  {
    Credentials localCredentials = (Credentials)paramMap.get(paramAuthScope);
    Object localObject2 = localCredentials;
    if (localCredentials == null)
    {
      int i = -1;
      Object localObject1 = null;
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (AuthScope)localIterator.next();
        int j = paramAuthScope.match((AuthScope)localObject2);
        if (j > i)
        {
          localObject1 = localObject2;
          i = j;
        }
      }
      localObject2 = localCredentials;
      if (localObject1 != null)
        localObject2 = (Credentials)paramMap.get(localObject1);
    }
    return localObject2;
  }

  public void clear()
  {
    this.credMap.clear();
  }

  public Credentials getCredentials(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Authentication scope may not be null");
    return matchCredentials(this.credMap, paramAuthScope);
  }

  public void setCredentials(AuthScope paramAuthScope, Credentials paramCredentials)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Authentication scope may not be null");
    this.credMap.put(paramAuthScope, paramCredentials);
  }

  public String toString()
  {
    return this.credMap.toString();
  }
}