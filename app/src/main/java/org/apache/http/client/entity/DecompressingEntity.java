package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

abstract class DecompressingEntity extends HttpEntityWrapper
{
  private static final int BUFFER_SIZE = 2048;
  private InputStream content;

  public DecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  public InputStream getContent()
    throws IOException
  {
    if (this.wrappedEntity.isStreaming())
    {
      if (this.content == null)
        this.content = getDecompressingInputStream(this.wrappedEntity.getContent());
      return this.content;
    }
    return getDecompressingInputStream(this.wrappedEntity.getContent());
  }

  abstract InputStream getDecompressingInputStream(InputStream paramInputStream)
    throws IOException;

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