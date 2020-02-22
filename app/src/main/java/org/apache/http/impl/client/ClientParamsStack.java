package org.apache.http.impl.client;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class ClientParamsStack extends AbstractHttpParams
{
  protected final HttpParams applicationParams;
  protected final HttpParams clientParams;
  protected final HttpParams overrideParams;
  protected final HttpParams requestParams;

  public ClientParamsStack(ClientParamsStack paramClientParamsStack)
  {
    this(paramClientParamsStack.getApplicationParams(), paramClientParamsStack.getClientParams(), paramClientParamsStack.getRequestParams(), paramClientParamsStack.getOverrideParams());
  }

  public ClientParamsStack(ClientParamsStack paramClientParamsStack, HttpParams paramHttpParams1, HttpParams paramHttpParams2, HttpParams paramHttpParams3, HttpParams paramHttpParams4)
  {
    this(paramHttpParams1, paramHttpParams2, paramHttpParams3, paramHttpParams4);
  }

  public ClientParamsStack(HttpParams paramHttpParams1, HttpParams paramHttpParams2, HttpParams paramHttpParams3, HttpParams paramHttpParams4)
  {
    this.applicationParams = paramHttpParams1;
    this.clientParams = paramHttpParams2;
    this.requestParams = paramHttpParams3;
    this.overrideParams = paramHttpParams4;
  }

  public HttpParams copy()
  {
    return this;
  }

  public final HttpParams getApplicationParams()
  {
    return this.applicationParams;
  }

  public final HttpParams getClientParams()
  {
    return this.clientParams;
  }

  public final HttpParams getOverrideParams()
  {
    return this.overrideParams;
  }

  public Object getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name must not be null.");
    Object localObject2 = null;
    if (this.overrideParams != null)
      localObject2 = this.overrideParams.getParameter(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (this.requestParams != null)
        localObject1 = this.requestParams.getParameter(paramString);
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = localObject1;
      if (this.clientParams != null)
        localObject2 = this.clientParams.getParameter(paramString);
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (this.applicationParams != null)
        localObject1 = this.applicationParams.getParameter(paramString);
    }
    return localObject1;
  }

  public final HttpParams getRequestParams()
  {
    return this.requestParams;
  }

  public boolean removeParameter(String paramString)
  {
    throw new UnsupportedOperationException("Removing parameters in a stack is not supported.");
  }

  public HttpParams setParameter(String paramString, Object paramObject)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
  }
}