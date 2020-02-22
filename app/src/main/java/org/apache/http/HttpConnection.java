package org.apache.http;

import java.io.IOException;

public abstract interface HttpConnection
{
  public abstract void close()
    throws IOException;

  public abstract HttpConnectionMetrics getMetrics();

  public abstract int getSocketTimeout();

  public abstract boolean isOpen();

  public abstract boolean isStale();

  public abstract void setSocketTimeout(int paramInt);

  public abstract void shutdown()
    throws IOException;
}