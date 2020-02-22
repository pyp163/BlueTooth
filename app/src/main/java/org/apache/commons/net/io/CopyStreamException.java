package org.apache.commons.net.io;

import java.io.IOException;

public class CopyStreamException extends IOException
{
  private static final long serialVersionUID = -2602899129433221532L;
  private final IOException ioException;
  private final long totalBytesTransferred;

  public CopyStreamException(String paramString, long paramLong, IOException paramIOException)
  {
    super(paramString);
    this.totalBytesTransferred = paramLong;
    this.ioException = paramIOException;
  }

  public IOException getIOException()
  {
    return this.ioException;
  }

  public long getTotalBytesTransferred()
  {
    return this.totalBytesTransferred;
  }
}