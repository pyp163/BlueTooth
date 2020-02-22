package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;

public class HttpRequestWriter extends AbstractMessageWriter
{
  public HttpRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }

  protected void writeHeadLine(HttpMessage paramHttpMessage)
    throws IOException
  {
    this.lineFormatter.formatRequestLine(this.lineBuf, ((HttpRequest)paramHttpMessage).getRequestLine());
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}