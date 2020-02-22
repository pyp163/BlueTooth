package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpRequestRetryHandler
  implements HttpRequestRetryHandler
{
  private final boolean requestSentRetryEnabled;
  private final int retryCount;

  public DefaultHttpRequestRetryHandler()
  {
    this(3, false);
  }

  public DefaultHttpRequestRetryHandler(int paramInt, boolean paramBoolean)
  {
    this.retryCount = paramInt;
    this.requestSentRetryEnabled = paramBoolean;
  }

  private boolean handleAsIdempotent(HttpRequest paramHttpRequest)
  {
    return !(paramHttpRequest instanceof HttpEntityEnclosingRequest);
  }

  public int getRetryCount()
  {
    return this.retryCount;
  }

  public boolean isRequestSentRetryEnabled()
  {
    return this.requestSentRetryEnabled;
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    if (paramIOException == null)
      throw new IllegalArgumentException("Exception parameter may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramInt > this.retryCount)
      return false;
    if ((paramIOException instanceof InterruptedIOException))
      return false;
    if ((paramIOException instanceof UnknownHostException))
      return false;
    if ((paramIOException instanceof ConnectException))
      return false;
    if ((paramIOException instanceof SSLException))
      return false;
    if (handleAsIdempotent((HttpRequest)paramHttpContext.getAttribute("http.request")))
      return true;
    paramIOException = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    if ((paramIOException != null) && (paramIOException.booleanValue()))
      paramInt = 1;
    else
      paramInt = 0;
    if (paramInt != 0)
      return this.requestSentRetryEnabled;
    return true;
  }
}