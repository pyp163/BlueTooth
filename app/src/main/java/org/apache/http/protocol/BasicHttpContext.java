package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;

public class BasicHttpContext
  implements HttpContext
{
  private Map map = null;
  private final HttpContext parentContext;

  public BasicHttpContext()
  {
    this(null);
  }

  public BasicHttpContext(HttpContext paramHttpContext)
  {
    this.parentContext = paramHttpContext;
  }

  public Object getAttribute(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    Object localObject1 = null;
    if (this.map != null)
      localObject1 = this.map.get(paramString);
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = localObject1;
      if (this.parentContext != null)
        localObject2 = this.parentContext.getAttribute(paramString);
    }
    return localObject2;
  }

  public Object removeAttribute(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (this.map != null)
      return this.map.remove(paramString);
    return null;
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Id may not be null");
    if (this.map == null)
      this.map = new HashMap();
    this.map.put(paramString, paramObject);
  }
}