package org.apache.http.impl;

import java.util.HashMap;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.io.HttpTransportMetrics;

public class HttpConnectionMetricsImpl
  implements HttpConnectionMetrics
{
  public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
  public static final String REQUEST_COUNT = "http.request-count";
  public static final String RESPONSE_COUNT = "http.response-count";
  public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
  private final HttpTransportMetrics inTransportMetric;
  private HashMap metricsCache;
  private final HttpTransportMetrics outTransportMetric;
  private long requestCount = 0L;
  private long responseCount = 0L;

  public HttpConnectionMetricsImpl(HttpTransportMetrics paramHttpTransportMetrics1, HttpTransportMetrics paramHttpTransportMetrics2)
  {
    this.inTransportMetric = paramHttpTransportMetrics1;
    this.outTransportMetric = paramHttpTransportMetrics2;
  }

  public Object getMetric(String paramString)
  {
    Object localObject;
    if (this.metricsCache != null)
      localObject = this.metricsCache.get(paramString);
    else
      localObject = null;
    if (localObject == null)
    {
      if ("http.request-count".equals(paramString))
        return new Long(this.requestCount);
      if ("http.response-count".equals(paramString))
        return new Long(this.responseCount);
      if ("http.received-bytes-count".equals(paramString))
      {
        if (this.inTransportMetric != null)
          return new Long(this.inTransportMetric.getBytesTransferred());
        return null;
      }
      if ("http.sent-bytes-count".equals(paramString))
      {
        if (this.outTransportMetric != null)
          return new Long(this.outTransportMetric.getBytesTransferred());
        return null;
      }
    }
    return localObject;
  }

  public long getReceivedBytesCount()
  {
    if (this.inTransportMetric != null)
      return this.inTransportMetric.getBytesTransferred();
    return -1L;
  }

  public long getRequestCount()
  {
    return this.requestCount;
  }

  public long getResponseCount()
  {
    return this.responseCount;
  }

  public long getSentBytesCount()
  {
    if (this.outTransportMetric != null)
      return this.outTransportMetric.getBytesTransferred();
    return -1L;
  }

  public void incrementRequestCount()
  {
    this.requestCount += 1L;
  }

  public void incrementResponseCount()
  {
    this.responseCount += 1L;
  }

  public void reset()
  {
    if (this.outTransportMetric != null)
      this.outTransportMetric.reset();
    if (this.inTransportMetric != null)
      this.inTransportMetric.reset();
    this.requestCount = 0L;
    this.responseCount = 0L;
    this.metricsCache = null;
  }

  public void setMetric(String paramString, Object paramObject)
  {
    if (this.metricsCache == null)
      this.metricsCache = new HashMap();
    this.metricsCache.put(paramString, paramObject);
  }
}