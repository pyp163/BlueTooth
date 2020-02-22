package org.apache.http.impl.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageParser
  implements HttpMessageParser
{
  private static final int HEADERS = 1;
  private static final int HEAD_LINE = 0;
  private final List headerLines;
  protected final LineParser lineParser;
  private final int maxHeaderCount;
  private final int maxLineLen;
  private HttpMessage message;
  private final SessionInputBuffer sessionBuffer;
  private int state;

  public AbstractMessageParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.sessionBuffer = paramSessionInputBuffer;
    this.maxHeaderCount = paramHttpParams.getIntParameter("http.connection.max-header-count", -1);
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    if (paramLineParser == null)
      paramLineParser = BasicLineParser.DEFAULT;
    this.lineParser = paramLineParser;
    this.headerLines = new ArrayList();
    this.state = 0;
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser)
    throws HttpException, IOException
  {
    Object localObject = paramLineParser;
    if (paramLineParser == null)
      localObject = BasicLineParser.DEFAULT;
    return parseHeaders(paramSessionInputBuffer, paramInt1, paramInt2, (LineParser)localObject, new ArrayList());
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser, List paramList)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramLineParser == null)
      throw new IllegalArgumentException("Line parser may not be null");
    if (paramList == null)
      throw new IllegalArgumentException("Header line list may not be null");
    Object localObject1 = null;
    Object localObject3 = localObject1;
    int j;
    do
    {
      Object localObject2;
      Object localObject4;
      do
      {
        if (localObject1 == null)
          localObject1 = new CharArrayBuffer(64);
        else
          ((CharArrayBuffer)localObject1).clear();
        int k = paramSessionInputBuffer.readLine((CharArrayBuffer)localObject1);
        j = 0;
        int i = 0;
        if ((k == -1) || (((CharArrayBuffer)localObject1).length() < 1))
          break;
        if (((((CharArrayBuffer)localObject1).charAt(0) == ' ') || (((CharArrayBuffer)localObject1).charAt(0) == '\t')) && (localObject3 != null))
        {
          while (i < ((CharArrayBuffer)localObject1).length())
          {
            j = ((CharArrayBuffer)localObject1).charAt(i);
            if ((j != 32) && (j != 9))
              break;
            i += 1;
          }
          if ((paramInt2 > 0) && (localObject3.length() + 1 + ((CharArrayBuffer)localObject1).length() - i > paramInt2))
            throw new IOException("Maximum line length limit exceeded");
          localObject3.append(' ');
          localObject3.append((CharArrayBuffer)localObject1, i, ((CharArrayBuffer)localObject1).length() - i);
          localObject2 = localObject1;
          localObject4 = localObject3;
        }
        else
        {
          paramList.add(localObject1);
          localObject2 = null;
          localObject4 = localObject1;
        }
        localObject1 = localObject2;
        localObject3 = localObject4;
      }
      while (paramInt1 <= 0);
      localObject1 = localObject2;
      localObject3 = localObject4;
    }
    while (paramList.size() < paramInt1);
    throw new IOException("Maximum header count exceeded");
    paramSessionInputBuffer = new Header[paramList.size()];
    paramInt1 = j;
    while (paramInt1 < paramList.size())
    {
      localObject1 = (CharArrayBuffer)paramList.get(paramInt1);
      try
      {
        paramSessionInputBuffer[paramInt1] = paramLineParser.parseHeader((CharArrayBuffer)localObject1);
        paramInt1 += 1;
      }
      catch (ParseException paramSessionInputBuffer)
      {
        throw new ProtocolException(paramSessionInputBuffer.getMessage());
      }
    }
    return paramSessionInputBuffer;
  }

  public HttpMessage parse()
    throws IOException, HttpException
  {
    switch (this.state)
    {
    default:
      throw new IllegalStateException("Inconsistent parser state");
    case 0:
    case 1:
    }
    try
    {
      this.message = parseHead(this.sessionBuffer);
      this.state = 1;
      Object localObject = parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser, this.headerLines);
      this.message.setHeaders((Header[])localObject);
      localObject = this.message;
      this.message = null;
      this.headerLines.clear();
      this.state = 0;
      return localObject;
    }
    catch (ParseException localParseException)
    {
      throw new ProtocolException(localParseException.getMessage(), localParseException);
    }
  }

  protected abstract HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException;
}