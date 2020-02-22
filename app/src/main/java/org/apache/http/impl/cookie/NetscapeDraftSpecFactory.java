package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

@Immutable
public class NetscapeDraftSpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      Object localObject = null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      paramHttpParams = localObject;
      if (localCollection != null)
        paramHttpParams = (String[])localCollection.toArray(new String[localCollection.size()]);
      return new NetscapeDraftSpec(paramHttpParams);
    }
    return new NetscapeDraftSpec();
  }
}