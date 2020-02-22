package org.apache.http.client.methods;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.utils.CloneUtils;

@NotThreadSafe
public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;

  public Object clone()
    throws CloneNotSupportedException
  {
    HttpEntityEnclosingRequestBase localHttpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase)super.clone();
    if (this.entity != null)
      localHttpEntityEnclosingRequestBase.entity = ((HttpEntity)CloneUtils.clone(this.entity));
    return localHttpEntityEnclosingRequestBase;
  }

  public boolean expectContinue()
  {
    Header localHeader = getFirstHeader("Expect");
    return (localHeader != null) && ("100-continue".equalsIgnoreCase(localHeader.getValue()));
  }

  public HttpEntity getEntity()
  {
    return this.entity;
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    this.entity = paramHttpEntity;
  }
}