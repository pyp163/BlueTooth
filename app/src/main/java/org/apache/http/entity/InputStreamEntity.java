package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamEntity extends AbstractHttpEntity
{
  private static final int BUFFER_SIZE = 2048;
  private final InputStream content;
  private final long length;

  public InputStreamEntity(InputStream paramInputStream, long paramLong)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Source input stream may not be null");
    this.content = paramInputStream;
    this.length = paramLong;
  }

  public void consumeContent()
    throws IOException
  {
    this.content.close();
  }

  public InputStream getContent()
    throws IOException
  {
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
    return true;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = this.content;
    try
    {
      byte[] arrayOfByte = new byte[2048];
      int i;
      if (this.length < 0L)
        while (true)
        {
          i = localInputStream.read(arrayOfByte);
          if (i == -1)
            break;
          paramOutputStream.write(arrayOfByte, 0, i);
        }
      for (long l = this.length; l > 0L; l -= i)
      {
        i = localInputStream.read(arrayOfByte, 0, (int)Math.min(2048L, l));
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