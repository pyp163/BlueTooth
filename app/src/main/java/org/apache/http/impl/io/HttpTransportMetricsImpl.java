package org.apache.http.impl.io;

import org.apache.http.io.HttpTransportMetrics;

public class HttpTransportMetricsImpl
  implements HttpTransportMetrics
{
  private long bytesTransferred = 0L;

  public long getBytesTransferred()
  {
    return this.bytesTransferred;
  }

  public void incrementBytesTransferred(long paramLong)
  {
    this.bytesTransferred += paramLong;
  }

  public void reset()
  {
    this.bytesTransferred = 0L;
  }

  public void setBytesTransferred(long paramLong)
  {
    this.bytesTransferred = paramLong;
  }
}