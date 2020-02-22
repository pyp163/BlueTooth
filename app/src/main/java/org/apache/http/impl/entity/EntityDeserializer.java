package org.apache.http.impl.entity;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.io.SessionInputBuffer;

public class EntityDeserializer
{
  private final ContentLengthStrategy lenStrategy;

  public EntityDeserializer(ContentLengthStrategy paramContentLengthStrategy)
  {
    if (paramContentLengthStrategy == null)
      throw new IllegalArgumentException("Content length strategy may not be null");
    this.lenStrategy = paramContentLengthStrategy;
  }

  public HttpEntity deserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    return doDeserialize(paramSessionInputBuffer, paramHttpMessage);
  }

  protected BasicHttpEntity doDeserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    long l = this.lenStrategy.determineLength(paramHttpMessage);
    if (l == -2L)
    {
      localBasicHttpEntity.setChunked(true);
      localBasicHttpEntity.setContentLength(-1L);
      localBasicHttpEntity.setContent(new ChunkedInputStream(paramSessionInputBuffer));
    }
    else if (l == -1L)
    {
      localBasicHttpEntity.setChunked(false);
      localBasicHttpEntity.setContentLength(-1L);
      localBasicHttpEntity.setContent(new IdentityInputStream(paramSessionInputBuffer));
    }
    else
    {
      localBasicHttpEntity.setChunked(false);
      localBasicHttpEntity.setContentLength(l);
      localBasicHttpEntity.setContent(new ContentLengthInputStream(paramSessionInputBuffer, l));
    }
    paramSessionInputBuffer = paramHttpMessage.getFirstHeader("Content-Type");
    if (paramSessionInputBuffer != null)
      localBasicHttpEntity.setContentType(paramSessionInputBuffer);
    paramSessionInputBuffer = paramHttpMessage.getFirstHeader("Content-Encoding");
    if (paramSessionInputBuffer != null)
      localBasicHttpEntity.setContentEncoding(paramSessionInputBuffer);
    return localBasicHttpEntity;
  }
}