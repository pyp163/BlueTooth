package org.apache.http.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected boolean chunked;
  protected Header contentEncoding;
  protected Header contentType;

  public void consumeContent()
    throws IOException
  {
  }

  public Header getContentEncoding()
  {
    return this.contentEncoding;
  }

  public Header getContentType()
  {
    return this.contentType;
  }

  public boolean isChunked()
  {
    return this.chunked;
  }

  public void setChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }

  public void setContentEncoding(String paramString)
  {
    if (paramString != null)
      paramString = new BasicHeader("Content-Encoding", paramString);
    else
      paramString = null;
    setContentEncoding(paramString);
  }

  public void setContentEncoding(Header paramHeader)
  {
    this.contentEncoding = paramHeader;
  }

  public void setContentType(String paramString)
  {
    if (paramString != null)
      paramString = new BasicHeader("Content-Type", paramString);
    else
      paramString = null;
    setContentType(paramString);
  }

  public void setContentType(Header paramHeader)
  {
    this.contentType = paramHeader;
  }
}