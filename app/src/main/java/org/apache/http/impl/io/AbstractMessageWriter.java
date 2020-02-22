package org.apache.http.impl.io;

import java.io.IOException;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageWriter
  implements HttpMessageWriter
{
  protected final CharArrayBuffer lineBuf;
  protected final LineFormatter lineFormatter;
  protected final SessionOutputBuffer sessionBuffer;

  public AbstractMessageWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.sessionBuffer = paramSessionOutputBuffer;
    this.lineBuf = new CharArrayBuffer(128);
    if (paramLineFormatter == null)
      paramLineFormatter = BasicLineFormatter.DEFAULT;
    this.lineFormatter = paramLineFormatter;
  }

  public void write(HttpMessage paramHttpMessage)
    throws IOException, HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    writeHeadLine(paramHttpMessage);
    paramHttpMessage = paramHttpMessage.headerIterator();
    while (paramHttpMessage.hasNext())
    {
      Header localHeader = (Header)paramHttpMessage.next();
      this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, localHeader));
    }
    this.lineBuf.clear();
    this.sessionBuffer.writeLine(this.lineBuf);
  }

  protected abstract void writeHeadLine(HttpMessage paramHttpMessage)
    throws IOException;
}