package org.apache.http.entity.mime.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringBody extends AbstractContentBody
{
  private final Charset charset;
  private final byte[] content;

  public StringBody(String paramString)
    throws UnsupportedEncodingException
  {
    this(paramString, "text/plain", null);
  }

  public StringBody(String paramString1, String paramString2, Charset paramCharset)
    throws UnsupportedEncodingException
  {
    super(paramString2);
    if (paramString1 == null)
      throw new IllegalArgumentException("Text may not be null");
    paramString2 = paramCharset;
    if (paramCharset == null)
      paramString2 = Charset.forName("US-ASCII");
    this.content = paramString1.getBytes(paramString2.name());
    this.charset = paramString2;
  }

  public StringBody(String paramString, Charset paramCharset)
    throws UnsupportedEncodingException
  {
    this(paramString, "text/plain", paramCharset);
  }

  public static StringBody create(String paramString)
    throws IllegalArgumentException
  {
    return create(paramString, null, null);
  }

  public static StringBody create(String paramString1, String paramString2, Charset paramCharset)
    throws IllegalArgumentException
  {
    try
    {
      paramString1 = new StringBody(paramString1, paramString2, paramCharset);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      paramString2 = new StringBuilder();
      paramString2.append("Charset ");
      paramString2.append(paramCharset);
      paramString2.append(" is not supported");
    }
    throw new IllegalArgumentException(paramString2.toString(), paramString1);
  }

  public static StringBody create(String paramString, Charset paramCharset)
    throws IllegalArgumentException
  {
    return create(paramString, null, paramCharset);
  }

  public String getCharset()
  {
    return this.charset.name();
  }

  public long getContentLength()
  {
    return this.content.length;
  }

  public String getFilename()
  {
    return null;
  }

  public Reader getReader()
  {
    return new InputStreamReader(new ByteArrayInputStream(this.content), this.charset);
  }

  public String getTransferEncoding()
  {
    return "8bit";
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.content);
    byte[] arrayOfByte = new byte[4096];
    while (true)
    {
      int i = localByteArrayInputStream.read(arrayOfByte);
      if (i == -1)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    paramOutputStream.flush();
  }

  @Deprecated
  public void writeTo(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    writeTo(paramOutputStream);
  }
}