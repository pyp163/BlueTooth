package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

public final class BasicHttpProcessor
  implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable
{
  protected final List requestInterceptors = new ArrayList();
  protected final List responseInterceptors = new ArrayList();

  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    addRequestInterceptor(paramHttpRequestInterceptor);
  }

  public final void addInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    addRequestInterceptor(paramHttpRequestInterceptor, paramInt);
  }

  public final void addInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    addResponseInterceptor(paramHttpResponseInterceptor);
  }

  public final void addInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    addResponseInterceptor(paramHttpResponseInterceptor, paramInt);
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    if (paramHttpRequestInterceptor == null)
      return;
    this.requestInterceptors.add(paramHttpRequestInterceptor);
  }

  public void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt)
  {
    if (paramHttpRequestInterceptor == null)
      return;
    this.requestInterceptors.add(paramInt, paramHttpRequestInterceptor);
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    if (paramHttpResponseInterceptor == null)
      return;
    this.responseInterceptors.add(paramHttpResponseInterceptor);
  }

  public void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt)
  {
    if (paramHttpResponseInterceptor == null)
      return;
    this.responseInterceptors.add(paramInt, paramHttpResponseInterceptor);
  }

  public void clearInterceptors()
  {
    clearRequestInterceptors();
    clearResponseInterceptors();
  }

  public void clearRequestInterceptors()
  {
    this.requestInterceptors.clear();
  }

  public void clearResponseInterceptors()
  {
    this.responseInterceptors.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    BasicHttpProcessor localBasicHttpProcessor = (BasicHttpProcessor)super.clone();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }

  public BasicHttpProcessor copy()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    copyInterceptors(localBasicHttpProcessor);
    return localBasicHttpProcessor;
  }

  protected void copyInterceptors(BasicHttpProcessor paramBasicHttpProcessor)
  {
    paramBasicHttpProcessor.requestInterceptors.clear();
    paramBasicHttpProcessor.requestInterceptors.addAll(this.requestInterceptors);
    paramBasicHttpProcessor.responseInterceptors.clear();
    paramBasicHttpProcessor.responseInterceptors.addAll(this.responseInterceptors);
  }

  public HttpRequestInterceptor getRequestInterceptor(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.requestInterceptors.size()))
      return (HttpRequestInterceptor)this.requestInterceptors.get(paramInt);
    return null;
  }

  public int getRequestInterceptorCount()
  {
    return this.requestInterceptors.size();
  }

  public HttpResponseInterceptor getResponseInterceptor(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.responseInterceptors.size()))
      return (HttpResponseInterceptor)this.responseInterceptors.get(paramInt);
    return null;
  }

  public int getResponseInterceptorCount()
  {
    return this.responseInterceptors.size();
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    int i = 0;
    while (i < this.requestInterceptors.size())
    {
      ((HttpRequestInterceptor)this.requestInterceptors.get(i)).process(paramHttpRequest, paramHttpContext);
      i += 1;
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    int i = 0;
    while (i < this.responseInterceptors.size())
    {
      ((HttpResponseInterceptor)this.responseInterceptors.get(i)).process(paramHttpResponse, paramHttpContext);
      i += 1;
    }
  }

  public void removeRequestInterceptorByClass(Class paramClass)
  {
    Iterator localIterator = this.requestInterceptors.iterator();
    while (localIterator.hasNext())
      if (localIterator.next().getClass().equals(paramClass))
        localIterator.remove();
  }

  public void removeResponseInterceptorByClass(Class paramClass)
  {
    Iterator localIterator = this.responseInterceptors.iterator();
    while (localIterator.hasNext())
      if (localIterator.next().getClass().equals(paramClass))
        localIterator.remove();
  }

  public void setInterceptors(List paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List must not be null.");
    this.requestInterceptors.clear();
    this.responseInterceptors.clear();
    int i = 0;
    while (i < paramList.size())
    {
      Object localObject = paramList.get(i);
      if ((localObject instanceof HttpRequestInterceptor))
        addInterceptor((HttpRequestInterceptor)localObject);
      if ((localObject instanceof HttpResponseInterceptor))
        addInterceptor((HttpResponseInterceptor)localObject);
      i += 1;
    }
  }
}