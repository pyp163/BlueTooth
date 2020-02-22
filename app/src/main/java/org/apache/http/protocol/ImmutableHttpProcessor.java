package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

public final class ImmutableHttpProcessor
  implements HttpProcessor
{
  private final HttpRequestInterceptor[] requestInterceptors;
  private final HttpResponseInterceptor[] responseInterceptors;

  public ImmutableHttpProcessor(HttpRequestInterceptorList paramHttpRequestInterceptorList, HttpResponseInterceptorList paramHttpResponseInterceptorList)
  {
    int j = 0;
    int k;
    int i;
    if (paramHttpRequestInterceptorList != null)
    {
      k = paramHttpRequestInterceptorList.getRequestInterceptorCount();
      this.requestInterceptors = new HttpRequestInterceptor[k];
      i = 0;
      while (i < k)
      {
        this.requestInterceptors[i] = paramHttpRequestInterceptorList.getRequestInterceptor(i);
        i += 1;
      }
    }
    this.requestInterceptors = new HttpRequestInterceptor[0];
    if (paramHttpResponseInterceptorList != null)
    {
      k = paramHttpResponseInterceptorList.getResponseInterceptorCount();
      this.responseInterceptors = new HttpResponseInterceptor[k];
      i = j;
      while (i < k)
      {
        this.responseInterceptors[i] = paramHttpResponseInterceptorList.getResponseInterceptor(i);
        i += 1;
      }
    }
    this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor)
  {
    this(paramArrayOfHttpRequestInterceptor, null);
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor, HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    int j = 0;
    int k;
    int i;
    if (paramArrayOfHttpRequestInterceptor != null)
    {
      k = paramArrayOfHttpRequestInterceptor.length;
      this.requestInterceptors = new HttpRequestInterceptor[k];
      i = 0;
      while (i < k)
      {
        this.requestInterceptors[i] = paramArrayOfHttpRequestInterceptor[i];
        i += 1;
      }
    }
    this.requestInterceptors = new HttpRequestInterceptor[0];
    if (paramArrayOfHttpResponseInterceptor != null)
    {
      k = paramArrayOfHttpResponseInterceptor.length;
      this.responseInterceptors = new HttpResponseInterceptor[k];
      i = j;
      while (i < k)
      {
        this.responseInterceptors[i] = paramArrayOfHttpResponseInterceptor[i];
        i += 1;
      }
    }
    this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    this(null, paramArrayOfHttpResponseInterceptor);
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    int i = 0;
    while (i < this.requestInterceptors.length)
    {
      this.requestInterceptors[i].process(paramHttpRequest, paramHttpContext);
      i += 1;
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    int i = 0;
    while (i < this.responseInterceptors.length)
    {
      this.responseInterceptors[i].process(paramHttpResponse, paramHttpContext);
      i += 1;
    }
  }
}