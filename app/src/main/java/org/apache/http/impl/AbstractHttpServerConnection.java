package org.apache.http.impl;

import java.io.IOException;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.StatusLine;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.io.HttpRequestParser;
import org.apache.http.impl.io.HttpResponseWriter;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

public abstract class AbstractHttpServerConnection
  implements HttpServerConnection
{
  private final EntityDeserializer entitydeserializer = createEntityDeserializer();
  private final EntitySerializer entityserializer = createEntitySerializer();
  private EofSensor eofSensor = null;
  private SessionInputBuffer inbuffer = null;
  private HttpConnectionMetricsImpl metrics = null;
  private SessionOutputBuffer outbuffer = null;
  private HttpMessageParser requestParser = null;
  private HttpMessageWriter responseWriter = null;

  protected abstract void assertOpen()
    throws IllegalStateException;

  protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    return new HttpConnectionMetricsImpl(paramHttpTransportMetrics1, paramHttpTransportMetrics2);
  }

  protected EntityDeserializer createEntityDeserializer()
  {
    return new EntityDeserializer(new LaxContentLengthStrategy());
  }

  protected EntitySerializer createEntitySerializer()
  {
    return new EntitySerializer(new StrictContentLengthStrategy());
  }

  protected HttpRequestFactory createHttpRequestFactory()
  {
    return new DefaultHttpRequestFactory();
  }

  protected HttpMessageParser createRequestParser(SessionInputBuffer paramSessionInputBuffer, HttpRequestFactory paramHttpRequestFactory, HttpParams paramHttpParams)
  {
    return new HttpRequestParser(paramSessionInputBuffer, null, paramHttpRequestFactory, paramHttpParams);
  }

  protected HttpMessageWriter createResponseWriter(SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    return new HttpResponseWriter(paramSessionOutputBuffer, null, paramHttpParams);
  }

  protected void doFlush()
    throws IOException
  {
    this.outbuffer.flush();
  }

  public void flush()
    throws IOException
  {
    assertOpen();
    doFlush();
  }

  public HttpConnectionMetrics getMetrics()
  {
    return this.metrics;
  }

  protected void init(SessionInputBuffer paramSessionInputBuffer, SessionOutputBuffer paramSessionOutputBuffer, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Input session buffer may not be null");
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Output session buffer may not be null");
    this.inbuffer = paramSessionInputBuffer;
    this.outbuffer = paramSessionOutputBuffer;
    if ((paramSessionInputBuffer instanceof EofSensor))
      this.eofSensor = ((EofSensor)paramSessionInputBuffer);
    this.requestParser = createRequestParser(paramSessionInputBuffer, createHttpRequestFactory(), paramHttpParams);
    this.responseWriter = createResponseWriter(paramSessionOutputBuffer, paramHttpParams);
    this.metrics = createConnectionMetrics(paramSessionInputBuffer.getMetrics(), paramSessionOutputBuffer.getMetrics());
  }

  protected boolean isEof()
  {
    return (this.eofSensor != null) && (this.eofSensor.isEof());
  }

  public boolean isStale()
  {
    if (!isOpen())
      return true;
    if (isEof())
      return true;
    try
    {
      this.inbuffer.isDataAvailable(1);
      boolean bool = isEof();
      return bool;
    }
    catch (IOException localIOException)
    {
    }
    return true;
  }

  public void receiveRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    if (paramHttpEntityEnclosingRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    assertOpen();
    paramHttpEntityEnclosingRequest.setEntity(this.entitydeserializer.deserialize(this.inbuffer, paramHttpEntityEnclosingRequest));
  }

  public HttpRequest receiveRequestHeader()
    throws HttpException, IOException
  {
    assertOpen();
    HttpRequest localHttpRequest = (HttpRequest)this.requestParser.parse();
    this.metrics.incrementRequestCount();
    return localHttpRequest;
  }

  public void sendResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    if (paramHttpResponse.getEntity() == null)
      return;
    this.entityserializer.serialize(this.outbuffer, paramHttpResponse, paramHttpResponse.getEntity());
  }

  public void sendResponseHeader(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    assertOpen();
    this.responseWriter.write(paramHttpResponse);
    if (paramHttpResponse.getStatusLine().getStatusCode() >= 200)
      this.metrics.incrementResponseCount();
  }
}