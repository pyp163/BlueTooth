package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.http.annotation.Immutable;

@Immutable
public class CookieIdentityComparator
  implements Serializable, Comparator<Cookie>
{
  private static final long serialVersionUID = 4466565437490631532L;

  public int compare(Cookie paramCookie1, Cookie paramCookie2)
  {
    int j = paramCookie1.getName().compareTo(paramCookie2.getName());
    int i = j;
    Object localObject1;
    if (j == 0)
    {
      Object localObject2 = paramCookie1.getDomain();
      if (localObject2 == null)
      {
        localObject1 = "";
      }
      else
      {
        localObject1 = localObject2;
        if (((String)localObject2).indexOf('.') == -1)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append(".local");
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
      String str = paramCookie2.getDomain();
      if (str == null)
      {
        localObject2 = "";
      }
      else
      {
        localObject2 = str;
        if (str.indexOf('.') == -1)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(str);
          ((StringBuilder)localObject2).append(".local");
          localObject2 = ((StringBuilder)localObject2).toString();
        }
      }
      i = ((String)localObject1).compareToIgnoreCase((String)localObject2);
    }
    j = i;
    if (i == 0)
    {
      localObject1 = paramCookie1.getPath();
      paramCookie1 = (Cookie)localObject1;
      if (localObject1 == null)
        paramCookie1 = "/";
      localObject1 = paramCookie2.getPath();
      paramCookie2 = (Cookie)localObject1;
      if (localObject1 == null)
        paramCookie2 = "/";
      j = paramCookie1.compareTo(paramCookie2);
    }
    return j;
  }
}