package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequestFactory;
import org.apache.http.ParseException;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public class HttpRequestParser extends AbstractMessageParser
{
  private final CharArrayBuffer lineBuf;
  private final HttpRequestFactory requestFactory;

  public HttpRequestParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpRequestFactory paramHttpRequestFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpRequestFactory == null)
      throw new IllegalArgumentException("Request factory may not be null");
    this.requestFactory = paramHttpRequestFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException
  {
    this.lineBuf.clear();
    if (paramSessionInputBuffer.readLine(this.lineBuf) == -1)
      throw new ConnectionClosedException("Client closed connection");
    paramSessionInputBuffer = new ParserCursor(0, this.lineBuf.length());
    paramSessionInputBuffer = this.lineParser.parseRequestLine(this.lineBuf, paramSessionInputBuffer);
    return this.requestFactory.newHttpRequest(paramSessionInputBuffer);
  }
}