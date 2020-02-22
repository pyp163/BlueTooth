package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.impl.io.AbstractMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

@ThreadSafe
public class DefaultResponseParser extends AbstractMessageParser
{
  private final CharArrayBuffer lineBuf;
  private final Log log = LogFactory.getLog(getClass());
  private final int maxGarbageLines;
  private final HttpResponseFactory responseFactory;

  public DefaultResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
    this.lineBuf = new CharArrayBuffer(128);
    this.maxGarbageLines = paramHttpParams.getIntParameter("http.connection.max-status-line-garbage", 2147483647);
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException
  {
    int i = 0;
    while (true)
    {
      this.lineBuf.clear();
      int j = paramSessionInputBuffer.readLine(this.lineBuf);
      if ((j == -1) && (i == 0))
        throw new NoHttpResponseException("The target server failed to respond");
      Object localObject = new ParserCursor(0, this.lineBuf.length());
      if (this.lineParser.hasProtocolVersion(this.lineBuf, (ParserCursor)localObject))
      {
        paramSessionInputBuffer = this.lineParser.parseStatusLine(this.lineBuf, (ParserCursor)localObject);
        return this.responseFactory.newHttpResponse(paramSessionInputBuffer, null);
      }
      if ((j == -1) || (i >= this.maxGarbageLines))
        break;
      if (this.log.isDebugEnabled())
      {
        localObject = this.log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Garbage in response: ");
        localStringBuilder.append(this.lineBuf.toString());
        ((Log)localObject).debug(localStringBuilder.toString());
      }
      i += 1;
    }
    throw new ProtocolException("The server failed to respond with a valid HTTP response");
  }
}