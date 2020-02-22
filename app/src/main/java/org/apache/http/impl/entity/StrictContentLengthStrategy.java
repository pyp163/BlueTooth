package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.ContentLengthStrategy;

public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    Object localObject = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    Header localHeader = paramHttpMessage.getFirstHeader("Content-Length");
    if (localObject != null)
    {
      localObject = ((Header)localObject).getValue();
      if ("chunked".equalsIgnoreCase((String)localObject))
      {
        if (paramHttpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0))
        {
          localObject = new StringBuffer();
          ((StringBuffer)localObject).append("Chunked transfer encoding not allowed for ");
          ((StringBuffer)localObject).append(paramHttpMessage.getProtocolVersion());
          throw new ProtocolException(((StringBuffer)localObject).toString());
        }
        return -2L;
      }
      if ("identity".equalsIgnoreCase((String)localObject))
        return -1L;
      paramHttpMessage = new StringBuffer();
      paramHttpMessage.append("Unsupported transfer encoding: ");
      paramHttpMessage.append((String)localObject);
      throw new ProtocolException(paramHttpMessage.toString());
    }
    if (localHeader != null)
      paramHttpMessage = localHeader.getValue();
    try
    {
      long l = Long.parseLong(paramHttpMessage);
      return l;
      label187: localObject = new StringBuffer();
      ((StringBuffer)localObject).append("Invalid content length: ");
      ((StringBuffer)localObject).append(paramHttpMessage);
      throw new ProtocolException(((StringBuffer)localObject).toString());
      return -1L;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label187;
    }
  }
}