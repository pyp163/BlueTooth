package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;

public class BasicHttpEntityEnclosingRequest extends BasicHttpRequest
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;

  public BasicHttpEntityEnclosingRequest(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public BasicHttpEntityEnclosingRequest(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    super(paramString1, paramString2, paramProtocolVersion);
  }

  public BasicHttpEntityEnclosingRequest(RequestLine paramRequestLine)
  {
    super(paramRequestLine);
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