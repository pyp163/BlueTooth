package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

@Immutable
public class BestMatchSpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      String[] arrayOfString = null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      if (localCollection != null)
        arrayOfString = (String[])localCollection.toArray(new String[localCollection.size()]);
      return new BestMatchSpec(arrayOfString, paramHttpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
    return new BestMatchSpec();
  }
}