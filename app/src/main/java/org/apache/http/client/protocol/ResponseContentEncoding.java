package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.protocol.HttpContext;

@Immutable
public class ResponseContentEncoding
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramHttpContext = paramHttpResponse.getEntity();
    if (paramHttpContext != null)
    {
      paramHttpContext = paramHttpContext.getContentEncoding();
      if (paramHttpContext != null)
      {
        paramHttpContext = paramHttpContext.getElements();
        if (paramHttpContext.length > 0)
        {
          paramHttpContext = paramHttpContext[0];
          String str = paramHttpContext.getName().toLowerCase(Locale.US);
          if ((!"gzip".equals(str)) && (!"x-gzip".equals(str)))
          {
            if ("deflate".equals(str))
            {
              paramHttpResponse.setEntity(new DeflateDecompressingEntity(paramHttpResponse.getEntity()));
              return;
            }
            if ("identity".equals(str))
              return;
            paramHttpResponse = new StringBuilder();
            paramHttpResponse.append("Unsupported Content-Coding: ");
            paramHttpResponse.append(paramHttpContext.getName());
            throw new HttpException(paramHttpResponse.toString());
          }
          paramHttpResponse.setEntity(new GzipDecompressingEntity(paramHttpResponse.getEntity()));
          return;
        }
      }
    }
  }
}