package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicEofSensorWatcher
  implements EofSensorWatcher
{
  protected final boolean attemptReuse;
  protected final ManagedClientConnection managedConn;

  public BasicEofSensorWatcher(ManagedClientConnection paramManagedClientConnection, boolean paramBoolean)
  {
    if (paramManagedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null.");
    this.managedConn = paramManagedClientConnection;
    this.attemptReuse = paramBoolean;
  }

  public boolean eofDetected(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if (this.attemptReuse)
      {
        paramInputStream.close();
        this.managedConn.markReusable();
      }
      return false;
    }
    finally
    {
      this.managedConn.releaseConnection();
    }
    throw paramInputStream;
  }

  public boolean streamAbort(InputStream paramInputStream)
    throws IOException
  {
    this.managedConn.abortConnection();
    return false;
  }

  public boolean streamClosed(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      if (this.attemptReuse)
      {
        paramInputStream.close();
        this.managedConn.markReusable();
      }
      return false;
    }
    finally
    {
      this.managedConn.releaseConnection();
    }
    throw paramInputStream;
  }
}