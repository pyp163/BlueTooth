package org.apache.http.message;

import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpMessage;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public abstract class AbstractHttpMessage
  implements HttpMessage
{
  protected HeaderGroup headergroup = new HeaderGroup();
  protected HttpParams params;

  protected AbstractHttpMessage()
  {
    this(null);
  }

  protected AbstractHttpMessage(HttpParams paramHttpParams)
  {
    this.params = paramHttpParams;
  }

  public void addHeader(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Header name may not be null");
    this.headergroup.addHeader(new BasicHeader(paramString1, paramString2));
  }

  public void addHeader(Header paramHeader)
  {
    this.headergroup.addHeader(paramHeader);
  }

  public boolean containsHeader(String paramString)
  {
    return this.headergroup.containsHeader(paramString);
  }

  public Header[] getAllHeaders()
  {
    return this.headergroup.getAllHeaders();
  }

  public Header getFirstHeader(String paramString)
  {
    return this.headergroup.getFirstHeader(paramString);
  }

  public Header[] getHeaders(String paramString)
  {
    return this.headergroup.getHeaders(paramString);
  }

  public Header getLastHeader(String paramString)
  {
    return this.headergroup.getLastHeader(paramString);
  }

  public HttpParams getParams()
  {
    if (this.params == null)
      this.params = new BasicHttpParams();
    return this.params;
  }

  public HeaderIterator headerIterator()
  {
    return this.headergroup.iterator();
  }

  public HeaderIterator headerIterator(String paramString)
  {
    return this.headergroup.iterator(paramString);
  }

  public void removeHeader(Header paramHeader)
  {
    this.headergroup.removeHeader(paramHeader);
  }

  public void removeHeaders(String paramString)
  {
    if (paramString == null)
      return;
    HeaderIterator localHeaderIterator = this.headergroup.iterator();
    while (localHeaderIterator.hasNext())
      if (paramString.equalsIgnoreCase(((Header)localHeaderIterator.next()).getName()))
        localHeaderIterator.remove();
  }

  public void setHeader(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Header name may not be null");
    this.headergroup.updateHeader(new BasicHeader(paramString1, paramString2));
  }

  public void setHeader(Header paramHeader)
  {
    this.headergroup.updateHeader(paramHeader);
  }

  public void setHeaders(Header[] paramArrayOfHeader)
  {
    this.headergroup.setHeaders(paramArrayOfHeader);
  }

  public void setParams(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.params = paramHttpParams;
  }
}