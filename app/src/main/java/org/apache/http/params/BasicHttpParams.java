package org.apache.http.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class BasicHttpParams extends AbstractHttpParams
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = -7086398485908701455L;
  private final HashMap parameters = new HashMap();

  public void clear()
  {
    this.parameters.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpParams localBasicHttpParams = (BasicHttpParams)super.clone();
    copyParams(localBasicHttpParams);
    return localBasicHttpParams;
  }

  public HttpParams copy()
  {
    try
    {
      HttpParams localHttpParams = (HttpParams)clone();
      return localHttpParams;
      label10: throw new UnsupportedOperationException("Cloning not supported");
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label10;
    }
  }

  protected void copyParams(HttpParams paramHttpParams)
  {
    Iterator localIterator = this.parameters.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getKey() instanceof String))
        paramHttpParams.setParameter((String)localEntry.getKey(), localEntry.getValue());
    }
  }

  public Object getParameter(String paramString)
  {
    return this.parameters.get(paramString);
  }

  public boolean isParameterSet(String paramString)
  {
    return getParameter(paramString) != null;
  }

  public boolean isParameterSetLocally(String paramString)
  {
    return this.parameters.get(paramString) != null;
  }

  public boolean removeParameter(String paramString)
  {
    if (this.parameters.containsKey(paramString))
    {
      this.parameters.remove(paramString);
      return true;
    }
    return false;
  }

  public HttpParams setParameter(String paramString, Object paramObject)
  {
    this.parameters.put(paramString, paramObject);
    return this;
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      setParameter(paramArrayOfString[i], paramObject);
      i += 1;
    }
  }
}