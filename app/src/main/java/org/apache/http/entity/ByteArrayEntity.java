package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayEntity extends AbstractHttpEntity
  implements Cloneable
{
  protected final byte[] content;

  public ByteArrayEntity(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Source byte array may not be null");
    this.content = paramArrayOfByte;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public InputStream getContent()
  {
    return new ByteArrayInputStream(this.content);
  }

  public long getContentLength()
  {
    return this.content.length;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    paramOutputStream.write(this.content);
    paramOutputStream.flush();
  }
}