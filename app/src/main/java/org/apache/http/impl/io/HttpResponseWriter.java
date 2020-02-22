package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;

public class HttpResponseWriter extends AbstractMessageWriter
{
  public HttpResponseWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }

  protected void writeHeadLine(HttpMessage paramHttpMessage)
    throws IOException
  {
    this.lineFormatter.formatStatusLine(this.lineBuf, ((HttpResponse)paramHttpMessage).getStatusLine());
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}