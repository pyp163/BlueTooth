package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.http.annotation.Immutable;

@Immutable
public class CookiePathComparator
  implements Serializable, Comparator<Cookie>
{
  private static final long serialVersionUID = 7523645369616405818L;

  private String normalizePath(Cookie paramCookie)
  {
    Object localObject = paramCookie.getPath();
    paramCookie = (Cookie)localObject;
    if (localObject == null)
      paramCookie = "/";
    localObject = paramCookie;
    if (!paramCookie.endsWith("/"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramCookie);
      ((StringBuilder)localObject).append('/');
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }

  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    paramCookie1 = normalizePath(paramCookie1);
    paramCookie2 = normalizePath(paramCookie2);
    if (paramCookie1.equals(paramCookie2))
      return 0;
    if (paramCookie1.startsWith(paramCookie2))
      return -1;
    if (paramCookie2.startsWith(paramCookie1))
      return 1;
    return 0;
  }
}