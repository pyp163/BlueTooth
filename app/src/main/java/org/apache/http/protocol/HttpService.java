package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpVersion;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

public class HttpService
{
  private volatile ConnectionReuseStrategy connStrategy = null;
  private volatile HttpExpectationVerifier expectationVerifier = null;
  private volatile HttpRequestHandlerResolver handlerResolver = null;
  private volatile HttpParams params = null;
  private volatile HttpProcessor processor = null;
  private volatile HttpResponseFactory responseFactory = null;

  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory)
  {
    setHttpProcessor(paramHttpProcessor);
    setConnReuseStrategy(paramConnectionReuseStrategy);
    setResponseFactory(paramHttpResponseFactory);
  }

  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory, HttpRequestHandlerResolver paramHttpRequestHandlerResolver, HttpParams paramHttpParams)
  {
    this(paramHttpProcessor, paramConnectionReuseStrategy, paramHttpResponseFactory, paramHttpRequestHandlerResolver, null, paramHttpParams);
  }

  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory, HttpRequestHandlerResolver paramHttpRequestHandlerResolver, HttpExpectationVerifier paramHttpExpectationVerifier, HttpParams paramHttpParams)
  {
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null");
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.processor = paramHttpProcessor;
    this.connStrategy = paramConnectionReuseStrategy;
    this.responseFactory = paramHttpResponseFactory;
    this.handlerResolver = paramHttpRequestHandlerResolver;
    this.expectationVerifier = paramHttpExpectationVerifier;
    this.params = paramHttpParams;
  }

  protected void doService(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    Object localObject;
    if (this.handlerResolver != null)
    {
      localObject = paramHttpRequest.getRequestLine().getUri();
      localObject = this.handlerResolver.lookup((String)localObject);
    }
    else
    {
      localObject = null;
    }
    if (localObject != null)
    {
      ((HttpRequestHandler)localObject).handle(paramHttpRequest, paramHttpResponse, paramHttpContext);
      return;
    }
    paramHttpResponse.setStatusCode(501);
  }

  public HttpParams getParams()
  {
    return this.params;
  }

  protected void handleException(HttpException paramHttpException, HttpResponse paramHttpResponse)
  {
    if ((paramHttpException instanceof MethodNotSupportedException))
      paramHttpResponse.setStatusCode(501);
    else if ((paramHttpException instanceof UnsupportedHttpVersionException))
      paramHttpResponse.setStatusCode(505);
    else if ((paramHttpException instanceof ProtocolException))
      paramHttpResponse.setStatusCode(400);
    else
      paramHttpResponse.setStatusCode(500);
    paramHttpException = new ByteArrayEntity(EncodingUtils.getAsciiBytes(paramHttpException.getMessage()));
    paramHttpException.setContentType("text/plain; charset=US-ASCII");
    paramHttpResponse.setEntity(paramHttpException);
  }

  public void handleRequest(HttpServerConnection paramHttpServerConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    paramHttpContext.setAttribute("http.connection", paramHttpServerConnection);
    label534: 
    while (true)
    {
      Object localObject1;
      try
      {
        HttpRequest localHttpRequest = paramHttpServerConnection.receiveRequestHeader();
        localHttpRequest.setParams(new DefaultedHttpParams(localHttpRequest.getParams(), this.params));
        localObject1 = localHttpRequest.getRequestLine().getProtocolVersion();
        Object localObject3 = localObject1;
        if (!((ProtocolVersion)localObject1).lessEquals(HttpVersion.HTTP_1_1))
          localObject3 = HttpVersion.HTTP_1_1;
        boolean bool = localHttpRequest instanceof HttpEntityEnclosingRequest;
        Object localObject2 = null;
        localObject1 = localObject2;
        if (bool)
          if (((HttpEntityEnclosingRequest)localHttpRequest).expectContinue())
          {
            HttpResponse localHttpResponse = this.responseFactory.newHttpResponse((ProtocolVersion)localObject3, 100, paramHttpContext);
            localHttpResponse.setParams(new DefaultedHttpParams(localHttpResponse.getParams(), this.params));
            HttpExpectationVerifier localHttpExpectationVerifier = this.expectationVerifier;
            localObject1 = localHttpResponse;
            if (localHttpExpectationVerifier != null)
              try
              {
                this.expectationVerifier.verify(localHttpRequest, localHttpResponse, paramHttpContext);
                localObject1 = localHttpResponse;
              }
              catch (HttpException localHttpException2)
              {
                localObject1 = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, paramHttpContext);
                ((HttpResponse)localObject1).setParams(new DefaultedHttpParams(((HttpResponse)localObject1).getParams(), this.params));
                handleException(localHttpException2, (HttpResponse)localObject1);
              }
            if (((HttpResponse)localObject1).getStatusLine().getStatusCode() >= 200)
              break label534;
            paramHttpServerConnection.sendResponseHeader((HttpResponse)localObject1);
            paramHttpServerConnection.flush();
            paramHttpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest)localHttpRequest);
            localObject1 = localObject2;
          }
          else
          {
            paramHttpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest)localHttpRequest);
            localObject1 = localObject2;
          }
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = this.responseFactory.newHttpResponse((ProtocolVersion)localObject3, 200, paramHttpContext);
          ((HttpResponse)localObject2).setParams(new DefaultedHttpParams(((HttpResponse)localObject2).getParams(), this.params));
          paramHttpContext.setAttribute("http.request", localHttpRequest);
          paramHttpContext.setAttribute("http.response", localObject2);
          this.processor.process(localHttpRequest, paramHttpContext);
          doService(localHttpRequest, (HttpResponse)localObject2, paramHttpContext);
        }
        localObject1 = localObject2;
        if ((localHttpRequest instanceof HttpEntityEnclosingRequest))
        {
          EntityUtils.consume(((HttpEntityEnclosingRequest)localHttpRequest).getEntity());
          localObject1 = localObject2;
        }
      }
      catch (HttpException localHttpException1)
      {
        localObject1 = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, paramHttpContext);
        ((HttpResponse)localObject1).setParams(new DefaultedHttpParams(((HttpResponse)localObject1).getParams(), this.params));
        handleException(localHttpException1, (HttpResponse)localObject1);
      }
      this.processor.process((HttpResponse)localObject1, paramHttpContext);
      paramHttpServerConnection.sendResponseHeader((HttpResponse)localObject1);
      paramHttpServerConnection.sendResponseEntity((HttpResponse)localObject1);
      paramHttpServerConnection.flush();
      if (!this.connStrategy.keepAlive((HttpResponse)localObject1, paramHttpContext))
        paramHttpServerConnection.close();
      return;
    }
  }

  public void setConnReuseStrategy(ConnectionReuseStrategy paramConnectionReuseStrategy)
  {
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null");
    this.connStrategy = paramConnectionReuseStrategy;
  }

  public void setExpectationVerifier(HttpExpectationVerifier paramHttpExpectationVerifier)
  {
    this.expectationVerifier = paramHttpExpectationVerifier;
  }

  public void setHandlerResolver(HttpRequestHandlerResolver paramHttpRequestHandlerResolver)
  {
    this.handlerResolver = paramHttpRequestHandlerResolver;
  }

  public void setHttpProcessor(HttpProcessor paramHttpProcessor)
  {
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    this.processor = paramHttpProcessor;
  }

  public void setParams(HttpParams paramHttpParams)
  {
    this.params = paramHttpParams;
  }

  public void setResponseFactory(HttpResponseFactory paramHttpResponseFactory)
  {
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
  }
}