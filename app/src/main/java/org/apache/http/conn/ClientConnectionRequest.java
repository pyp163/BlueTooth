package org.apache.http.conn;

import java.util.concurrent.TimeUnit;

public abstract interface ClientConnectionRequest
{
  public abstract void abortRequest();

  public abstract ManagedClientConnection getConnection(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ConnectionPoolTimeoutException;
}