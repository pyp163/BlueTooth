package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class EofSensorInputStream extends InputStream
  implements ConnectionReleaseTrigger
{
  private final EofSensorWatcher eofWatcher;
  private boolean selfClosed;
  protected InputStream wrappedStream;

  public EofSensorInputStream(InputStream paramInputStream, EofSensorWatcher paramEofSensorWatcher)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Wrapped stream may not be null.");
    this.wrappedStream = paramInputStream;
    this.selfClosed = false;
    this.eofWatcher = paramEofSensorWatcher;
  }

  public void abortConnection()
    throws IOException
  {
    this.selfClosed = true;
    checkAbort();
  }

  public int available()
    throws IOException
  {
    if (isReadAllowed())
      try
      {
        int i = this.wrappedStream.available();
        return i;
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return 0;
  }

  protected void checkAbort()
    throws IOException
  {
    if (this.wrappedStream != null)
    {
      boolean bool = true;
      try
      {
        if (this.eofWatcher != null)
          bool = this.eofWatcher.streamAbort(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
        return;
      }
      finally
      {
        this.wrappedStream = null;
      }
    }
  }

  protected void checkClose()
    throws IOException
  {
    if (this.wrappedStream != null)
    {
      boolean bool = true;
      try
      {
        if (this.eofWatcher != null)
          bool = this.eofWatcher.streamClosed(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
        return;
      }
      finally
      {
        this.wrappedStream = null;
      }
    }
  }

  protected void checkEOF(int paramInt)
    throws IOException
  {
    if ((this.wrappedStream != null) && (paramInt < 0))
    {
      boolean bool = true;
      try
      {
        if (this.eofWatcher != null)
          bool = this.eofWatcher.eofDetected(this.wrappedStream);
        if (bool)
          this.wrappedStream.close();
        return;
      }
      finally
      {
        this.wrappedStream = null;
      }
    }
  }

  public void close()
    throws IOException
  {
    this.selfClosed = true;
    checkClose();
  }

  protected boolean isReadAllowed()
    throws IOException
  {
    if (this.selfClosed)
      throw new IOException("Attempted read on closed stream.");
    return this.wrappedStream != null;
  }

  public int read()
    throws IOException
  {
    if (isReadAllowed())
      try
      {
        int i = this.wrappedStream.read();
        checkEOF(i);
        return i;
      }
      catch (IOException localIOException)
      {
        checkAbort();
        throw localIOException;
      }
    return -1;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (isReadAllowed())
      try
      {
        int i = this.wrappedStream.read(paramArrayOfByte);
        checkEOF(i);
        return i;
      }
      catch (IOException paramArrayOfByte)
      {
        checkAbort();
        throw paramArrayOfByte;
      }
    return -1;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (isReadAllowed())
      try
      {
        paramInt1 = this.wrappedStream.read(paramArrayOfByte, paramInt1, paramInt2);
        checkEOF(paramInt1);
        return paramInt1;
      }
      catch (IOException paramArrayOfByte)
      {
        checkAbort();
        throw paramArrayOfByte;
      }
    return -1;
  }

  public void releaseConnection()
    throws IOException
  {
    close();
  }
}