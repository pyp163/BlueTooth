package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity extends AbstractHttpEntity
{
  private InputStream content;
  private long length = -1L;

  public void consumeContent()
    throws IOException
  {
    if (this.content != null)
      this.content.close();
  }

  public InputStream getContent()
    throws IllegalStateException
  {
    if (this.content == null)
      throw new IllegalStateException("Content has not been provided");
    return this.content;
  }

  public long getContentLength()
  {
    return this.length;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public boolean isStreaming()
  {
    return this.content != null;
  }

  public void setContent(InputStream paramInputStream)
  {
    this.content = paramInputStream;
  }

  public void setContentLength(long paramLong)
  {
    this.length = paramLong;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = getContent();
    try
    {
      byte[] arrayOfByte = new byte[2048];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      return;
    }
    finally
    {
      localInputStream.close();
    }
    throw paramOutputStream;
  }
}