package org.apache.http.params;

public abstract class AbstractHttpParams
  implements HttpParams
{
  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramBoolean;
    return ((Boolean)paramString).booleanValue();
  }

  public double getDoubleParameter(String paramString, double paramDouble)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramDouble;
    return ((Double)paramString).doubleValue();
  }

  public int getIntParameter(String paramString, int paramInt)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramInt;
    return ((Integer)paramString).intValue();
  }

  public long getLongParameter(String paramString, long paramLong)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramLong;
    return ((Long)paramString).longValue();
  }

  public boolean isParameterFalse(String paramString)
  {
    return getBooleanParameter(paramString, false) ^ true;
  }

  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }

  public HttpParams setBooleanParameter(String paramString, boolean paramBoolean)
  {
    Boolean localBoolean;
    if (paramBoolean)
      localBoolean = Boolean.TRUE;
    else
      localBoolean = Boolean.FALSE;
    setParameter(paramString, localBoolean);
    return this;
  }

  public HttpParams setDoubleParameter(String paramString, double paramDouble)
  {
    setParameter(paramString, new Double(paramDouble));
    return this;
  }

  public HttpParams setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, new Integer(paramInt));
    return this;
  }

  public HttpParams setLongParameter(String paramString, long paramLong)
  {
    setParameter(paramString, new Long(paramLong));
    return this;
  }
}