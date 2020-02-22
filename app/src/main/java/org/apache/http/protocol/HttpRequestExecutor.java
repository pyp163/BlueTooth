package org.apache.http.protocol;

import java.io.IOException;
import java.net.ProtocolException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

public class HttpRequestExecutor
{
  private static final void closeConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.close();
      return;
    }
    catch (IOException paramHttpClientConnection)
    {
    }
  }

  protected boolean canResponseHaveBody(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse)
  {
    boolean bool1 = "HEAD".equalsIgnoreCase(paramHttpRequest.getRequestLine().getMethod());
    boolean bool2 = false;
    if (bool1)
      return false;
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    bool1 = bool2;
    if (i >= 200)
    {
      bool1 = bool2;
      if (i != 204)
      {
        bool1 = bool2;
        if (i != 304)
        {
          bool1 = bool2;
          if (i != 205)
            bool1 = true;
        }
      }
    }
    return bool1;
  }

  protected HttpResponse doReceiveResponse(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext = null;
    for (int i = 0; ; i = paramHttpContext.getStatusLine().getStatusCode())
    {
      if ((paramHttpContext != null) && (i >= 200))
        return paramHttpContext;
      paramHttpContext = paramHttpClientConnection.receiveResponseHeader();
      if (canResponseHaveBody(paramHttpRequest, paramHttpContext))
        paramHttpClientConnection.receiveResponseEntity(paramHttpContext);
    }
  }

  protected HttpResponse doSendRequest(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("HTTP connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.connection", paramHttpClientConnection);
    paramHttpContext.setAttribute("http.request_sent", Boolean.FALSE);
    paramHttpClientConnection.sendRequestHeader(paramHttpRequest);
    boolean bool = paramHttpRequest instanceof HttpEntityEnclosingRequest;
    Object localObject2 = null;
    Object localObject3 = null;
    if (bool)
    {
      int j = 1;
      localObject2 = paramHttpRequest.getRequestLine().getProtocolVersion();
      HttpEntityEnclosingRequest localHttpEntityEnclosingRequest = (HttpEntityEnclosingRequest)paramHttpRequest;
      int i = j;
      Object localObject1 = localObject3;
      if (localHttpEntityEnclosingRequest.expectContinue())
      {
        i = j;
        localObject1 = localObject3;
        if (!((ProtocolVersion)localObject2).lessEquals(HttpVersion.HTTP_1_0))
        {
          paramHttpClientConnection.flush();
          i = j;
          localObject1 = localObject3;
          if (paramHttpClientConnection.isResponseAvailable(paramHttpRequest.getParams().getIntParameter("http.protocol.wait-for-continue", 2000)))
          {
            localObject2 = paramHttpClientConnection.receiveResponseHeader();
            if (canResponseHaveBody(paramHttpRequest, (HttpResponse)localObject2))
              paramHttpClientConnection.receiveResponseEntity((HttpResponse)localObject2);
            int k = ((HttpResponse)localObject2).getStatusLine().getStatusCode();
            if (k < 200)
            {
              i = j;
              localObject1 = localObject3;
              if (k != 100)
              {
                paramHttpRequest = new StringBuffer();
                paramHttpRequest.append("Unexpected response: ");
                paramHttpRequest.append(((HttpResponse)localObject2).getStatusLine());
                throw new ProtocolException(paramHttpRequest.toString());
              }
            }
            else
            {
              i = 0;
              localObject1 = localObject2;
            }
          }
        }
      }
      localObject2 = localObject1;
      if (i != 0)
      {
        paramHttpClientConnection.sendRequestEntity(localHttpEntityEnclosingRequest);
        localObject2 = localObject1;
      }
    }
    paramHttpClientConnection.flush();
    paramHttpContext.setAttribute("http.request_sent", Boolean.TRUE);
    return localObject2;
  }

  public HttpResponse execute(HttpRequest paramHttpRequest, HttpClientConnection paramHttpClientConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpClientConnection == null)
      throw new IllegalArgumentException("Client connection may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    try
    {
      HttpResponse localHttpResponse2 = doSendRequest(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      HttpResponse localHttpResponse1 = localHttpResponse2;
      if (localHttpResponse2 == null)
        localHttpResponse1 = doReceiveResponse(paramHttpRequest, paramHttpClientConnection, paramHttpContext);
      return localHttpResponse1;
    }
    catch (RuntimeException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
      throw paramHttpRequest;
    }
    catch (HttpException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
      throw paramHttpRequest;
    }
    catch (IOException paramHttpRequest)
    {
      closeConnection(paramHttpClientConnection);
    }
    throw paramHttpRequest;
  }

  public void postProcess(HttpResponse paramHttpResponse, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.response", paramHttpResponse);
    paramHttpProcessor.process(paramHttpResponse, paramHttpContext);
  }

  public void preProcess(HttpRequest paramHttpRequest, HttpProcessor paramHttpProcessor, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    paramHttpContext.setAttribute("http.request", paramHttpRequest);
    paramHttpProcessor.process(paramHttpRequest, paramHttpContext);
  }
}