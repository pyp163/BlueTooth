package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class StringEntity extends AbstractHttpEntity
  implements Cloneable
{
  protected final byte[] content;

  public StringEntity(String paramString)
    throws UnsupportedEncodingException
  {
    this(paramString, null);
  }

  public StringEntity(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    this(paramString1, null, paramString2);
  }

  public StringEntity(String paramString1, String paramString2, String paramString3)
    throws UnsupportedEncodingException
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Source string may not be null");
    String str = paramString2;
    if (paramString2 == null)
      str = "text/plain";
    paramString2 = paramString3;
    if (paramString3 == null)
      paramString2 = "ISO-8859-1";
    this.content = paramString1.getBytes(paramString2);
    paramString1 = new StringBuffer();
    paramString1.append(str);
    paramString1.append("; charset=");
    paramString1.append(paramString2);
    setContentType(paramString1.toString());
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public InputStream getContent()
    throws IOException
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