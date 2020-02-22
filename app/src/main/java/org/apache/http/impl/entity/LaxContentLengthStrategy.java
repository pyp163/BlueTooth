package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpParams;

public class LaxContentLengthStrategy
  implements ContentLengthStrategy
{
  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    boolean bool = paramHttpMessage.getParams().isParameterTrue("http.protocol.strict-transfer-encoding");
    Object localObject1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    Object localObject2 = paramHttpMessage.getFirstHeader("Content-Length");
    int i;
    if (localObject1 != null)
      try
      {
        localObject2 = ((Header)localObject1).getElements();
        if (bool)
        {
          i = 0;
          while (i < localObject2.length)
          {
            paramHttpMessage = localObject2[i].getName();
            if ((paramHttpMessage != null) && (paramHttpMessage.length() > 0) && (!paramHttpMessage.equalsIgnoreCase("chunked")) && (!paramHttpMessage.equalsIgnoreCase("identity")))
            {
              localObject1 = new StringBuffer();
              ((StringBuffer)localObject1).append("Unsupported transfer encoding: ");
              ((StringBuffer)localObject1).append(paramHttpMessage);
              throw new ProtocolException(((StringBuffer)localObject1).toString());
            }
            i += 1;
          }
        }
        i = localObject2.length;
        if ("identity".equalsIgnoreCase(((Header)localObject1).getValue()))
          return -1L;
        if ((i > 0) && ("chunked".equalsIgnoreCase(localObject2[(i - 1)].getName())))
          return -2L;
        if (bool)
          throw new ProtocolException("Chunk-encoding must be the last one applied");
        return -1L;
      }
      catch (ParseException paramHttpMessage)
      {
        localObject2 = new StringBuffer();
        ((StringBuffer)localObject2).append("Invalid Transfer-Encoding header value: ");
        ((StringBuffer)localObject2).append(localObject1);
        throw new ProtocolException(((StringBuffer)localObject2).toString(), paramHttpMessage);
      }
    if (localObject2 != null)
    {
      localObject1 = paramHttpMessage.getHeaders("Content-Length");
      if ((bool) && (localObject1.length > 1))
        throw new ProtocolException("Multiple content length headers");
      i = localObject1.length - 1;
    }
    while (true)
    {
      if (i >= 0)
        paramHttpMessage = localObject1[i];
      try
      {
        long l = Long.parseLong(paramHttpMessage.getValue());
        break label389;
        label331: if (bool)
        {
          localObject1 = new StringBuffer();
          ((StringBuffer)localObject1).append("Invalid content length: ");
          ((StringBuffer)localObject1).append(paramHttpMessage.getValue());
          throw new ProtocolException(((StringBuffer)localObject1).toString());
        }
        i -= 1;
        continue;
        l = -1L;
        label389: if (l >= 0L)
          return l;
        return -1L;
        return -1L;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label331;
      }
    }
  }
}