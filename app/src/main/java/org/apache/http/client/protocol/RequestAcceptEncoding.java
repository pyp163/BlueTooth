package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.annotation.Immutable;
import org.apache.http.protocol.HttpContext;

@Immutable
public class RequestAcceptEncoding
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramHttpRequest.addHeader("Accept-Encoding", "gzip,deflate");
  }
}