package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public class RequestWrapper extends AbstractHttpMessage
  implements HttpUriRequest
{
  private int execCount;
  private String method;
  private final HttpRequest original;
  private URI uri;
  private ProtocolVersion version;

  public RequestWrapper(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    this.original = paramHttpRequest;
    setParams(paramHttpRequest.getParams());
    setHeaders(paramHttpRequest.getAllHeaders());
    RequestLine localRequestLine;
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      paramHttpRequest = (HttpUriRequest)paramHttpRequest;
      this.uri = paramHttpRequest.getURI();
      this.method = paramHttpRequest.getMethod();
      this.version = null;
    }
    else
    {
      localRequestLine = paramHttpRequest.getRequestLine();
    }
    try
    {
      this.uri = new URI(localRequestLine.getUri());
      this.method = localRequestLine.getMethod();
      this.version = paramHttpRequest.getProtocolVersion();
      this.execCount = 0;
      return;
    }
    catch (URISyntaxException paramHttpRequest)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid request URI: ");
      localStringBuilder.append(localRequestLine.getUri());
      throw new ProtocolException(localStringBuilder.toString(), paramHttpRequest);
    }
  }

  public void abort()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }

  public int getExecCount()
  {
    return this.execCount;
  }

  public String getMethod()
  {
    return this.method;
  }

  public HttpRequest getOriginal()
  {
    return this.original;
  }

  public ProtocolVersion getProtocolVersion()
  {
    if (this.version == null)
      this.version = HttpProtocolParams.getVersion(getParams());
    return this.version;
  }

  public RequestLine getRequestLine()
  {
    String str3 = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    String str1;
    if (this.uri != null)
      str1 = this.uri.toASCIIString();
    else
      str1 = null;
    String str2;
    if (str1 != null)
    {
      str2 = str1;
      if (str1.length() != 0);
    }
    else
    {
      str2 = "/";
    }
    return new BasicRequestLine(str3, str2, localProtocolVersion);
  }

  public URI getURI()
  {
    return this.uri;
  }

  public void incrementExecCount()
  {
    this.execCount += 1;
  }

  public boolean isAborted()
  {
    return false;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public void resetHeaders()
  {
    this.headergroup.clear();
    setHeaders(this.original.getAllHeaders());
  }

  public void setMethod(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Method name may not be null");
    this.method = paramString;
  }

  public void setProtocolVersion(ProtocolVersion paramProtocolVersion)
  {
    this.version = paramProtocolVersion;
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }
}