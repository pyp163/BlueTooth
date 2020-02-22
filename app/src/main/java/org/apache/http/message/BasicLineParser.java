package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

public class BasicLineParser
  implements LineParser
{
  public static final BasicLineParser DEFAULT = new BasicLineParser();
  protected final ProtocolVersion protocol;

  public BasicLineParser()
  {
    this(null);
  }

  public BasicLineParser(ProtocolVersion paramProtocolVersion)
  {
    Object localObject = paramProtocolVersion;
    if (paramProtocolVersion == null)
      localObject = HttpVersion.HTTP_1_1;
    this.protocol = ((ProtocolVersion)localObject);
  }

  public static final Header parseHeader(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    Object localObject = paramLineParser;
    if (paramLineParser == null)
      localObject = DEFAULT;
    paramLineParser = new CharArrayBuffer(paramString.length());
    paramLineParser.append(paramString);
    return ((LineParser)localObject).parseHeader(paramLineParser);
  }

  public static final ProtocolVersion parseProtocolVersion(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    Object localObject = paramLineParser;
    if (paramLineParser == null)
      localObject = DEFAULT;
    paramLineParser = new CharArrayBuffer(paramString.length());
    paramLineParser.append(paramString);
    return ((LineParser)localObject).parseProtocolVersion(paramLineParser, new ParserCursor(0, paramString.length()));
  }

  public static final RequestLine parseRequestLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    Object localObject = paramLineParser;
    if (paramLineParser == null)
      localObject = DEFAULT;
    paramLineParser = new CharArrayBuffer(paramString.length());
    paramLineParser.append(paramString);
    return ((LineParser)localObject).parseRequestLine(paramLineParser, new ParserCursor(0, paramString.length()));
  }

  public static final StatusLine parseStatusLine(String paramString, LineParser paramLineParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null.");
    Object localObject = paramLineParser;
    if (paramLineParser == null)
      localObject = DEFAULT;
    paramLineParser = new CharArrayBuffer(paramString.length());
    paramLineParser.append(paramString);
    return ((LineParser)localObject).parseStatusLine(paramLineParser, new ParserCursor(0, paramString.length()));
  }

  protected ProtocolVersion createProtocolVersion(int paramInt1, int paramInt2)
  {
    return this.protocol.forVersion(paramInt1, paramInt2);
  }

  protected RequestLine createRequestLine(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    return new BasicRequestLine(paramString1, paramString2, paramProtocolVersion);
  }

  protected StatusLine createStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    return new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }

  public boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int j = paramParserCursor.getPos();
    paramParserCursor = this.protocol.getProtocol();
    int k = paramParserCursor.length();
    if (paramCharArrayBuffer.length() < k + 4)
      return false;
    int i;
    if (j < 0)
    {
      i = paramCharArrayBuffer.length() - 4 - k;
    }
    else
    {
      i = j;
      if (j == 0)
        while (true)
        {
          i = j;
          if (j >= paramCharArrayBuffer.length())
            break;
          i = j;
          if (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(j)))
            break;
          j += 1;
        }
    }
    int m = i + k;
    if (m + 4 > paramCharArrayBuffer.length())
      return false;
    boolean bool1 = true;
    j = 0;
    while ((bool1) && (j < k))
    {
      if (paramCharArrayBuffer.charAt(i + j) == paramParserCursor.charAt(j))
        bool1 = true;
      else
        bool1 = false;
      j += 1;
    }
    boolean bool2 = bool1;
    if (bool1)
    {
      if (paramCharArrayBuffer.charAt(m) == '/')
        return true;
      bool2 = false;
    }
    return bool2;
  }

  public Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    return new BufferedHeader(paramCharArrayBuffer);
  }

  public ProtocolVersion parseProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    String str = this.protocol.getProtocol();
    int n = str.length();
    int m = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    skipWhitespace(paramCharArrayBuffer, paramParserCursor);
    int i1 = paramParserCursor.getPos();
    int i2 = i1 + n;
    if (i2 + 4 > k)
    {
      paramParserCursor = new StringBuffer();
      paramParserCursor.append("Not a valid protocol version: ");
      paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
      throw new ParseException(paramParserCursor.toString());
    }
    int i = 1;
    int j = 0;
    while ((i != 0) && (j < n))
    {
      if (paramCharArrayBuffer.charAt(i1 + j) == str.charAt(j))
        i = 1;
      else
        i = 0;
      j += 1;
    }
    j = i;
    if (i != 0)
      if (paramCharArrayBuffer.charAt(i2) == '/')
        j = 1;
      else
        j = 0;
    if (j == 0)
    {
      paramParserCursor = new StringBuffer();
      paramParserCursor.append("Not a valid protocol version: ");
      paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
      throw new ParseException(paramParserCursor.toString());
    }
    j = i1 + (n + 1);
    i = paramCharArrayBuffer.indexOf(46, j, k);
    if (i == -1)
    {
      paramParserCursor = new StringBuffer();
      paramParserCursor.append("Invalid protocol version number: ");
      paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
      throw new ParseException(paramParserCursor.toString());
    }
    try
    {
      n = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(j, i));
      i1 = i + 1;
      j = paramCharArrayBuffer.indexOf(32, i1, k);
      i = j;
      if (j == -1)
        i = k;
    }
    catch (NumberFormatException paramParserCursor)
    {
      try
      {
        j = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(i1, i));
        paramParserCursor.updatePos(i);
        return createProtocolVersion(n, j);
        label379: paramParserCursor = new StringBuffer();
        paramParserCursor.append("Invalid protocol minor version number: ");
        paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
        throw new ParseException(paramParserCursor.toString());
        while (true)
        {
          paramParserCursor = new StringBuffer();
          paramParserCursor.append("Invalid protocol major version number: ");
          paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
          throw new ParseException(paramParserCursor.toString());
          paramParserCursor = paramParserCursor;
        }
      }
      catch (NumberFormatException paramParserCursor)
      {
        break label379;
      }
    }
  }

  public RequestLine parseRequestLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    try
    {
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      int k = paramParserCursor.getPos();
      int m = paramCharArrayBuffer.indexOf(32, k, j);
      if (m < 0)
      {
        paramParserCursor = new StringBuffer();
        paramParserCursor.append("Invalid request line: ");
        paramParserCursor.append(paramCharArrayBuffer.substring(i, j));
        throw new ParseException(paramParserCursor.toString());
      }
      String str1 = paramCharArrayBuffer.substringTrimmed(k, m);
      paramParserCursor.updatePos(m);
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      k = paramParserCursor.getPos();
      m = paramCharArrayBuffer.indexOf(32, k, j);
      if (m < 0)
      {
        paramParserCursor = new StringBuffer();
        paramParserCursor.append("Invalid request line: ");
        paramParserCursor.append(paramCharArrayBuffer.substring(i, j));
        throw new ParseException(paramParserCursor.toString());
      }
      String str2 = paramCharArrayBuffer.substringTrimmed(k, m);
      paramParserCursor.updatePos(m);
      ProtocolVersion localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      if (!paramParserCursor.atEnd())
      {
        paramParserCursor = new StringBuffer();
        paramParserCursor.append("Invalid request line: ");
        paramParserCursor.append(paramCharArrayBuffer.substring(i, j));
        throw new ParseException(paramParserCursor.toString());
      }
      paramParserCursor = createRequestLine(str1, str2, localProtocolVersion);
      return paramParserCursor;
      label280: paramParserCursor = new StringBuffer();
      paramParserCursor.append("Invalid request line: ");
      paramParserCursor.append(paramCharArrayBuffer.substring(i, j));
      throw new ParseException(paramParserCursor.toString());
    }
    catch (IndexOutOfBoundsException paramParserCursor)
    {
      break label280;
    }
  }

  public StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int m = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    try
    {
      localProtocolVersion = parseProtocolVersion(paramCharArrayBuffer, paramParserCursor);
      skipWhitespace(paramCharArrayBuffer, paramParserCursor);
      int n = paramParserCursor.getPos();
      j = paramCharArrayBuffer.indexOf(32, n, k);
      i = j;
      if (j < 0)
        i = k;
      paramParserCursor = paramCharArrayBuffer.substringTrimmed(n, i);
      j = 0;
      while (j < paramParserCursor.length())
      {
        if (!Character.isDigit(paramParserCursor.charAt(j)))
        {
          paramParserCursor = new StringBuffer();
          paramParserCursor.append("Status line contains invalid status code: ");
          paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
          throw new ParseException(paramParserCursor.toString());
        }
        j += 1;
      }
    }
    catch (IndexOutOfBoundsException paramParserCursor)
    {
      try
      {
        ProtocolVersion localProtocolVersion;
        int i;
        int j = Integer.parseInt(paramParserCursor);
        if (i < k)
        {
          paramParserCursor = paramCharArrayBuffer.substringTrimmed(i, k);
          return createStatusLine(localProtocolVersion, j, paramParserCursor);
          paramParserCursor = new StringBuffer();
          paramParserCursor.append("Status line contains invalid status code: ");
          paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
          throw new ParseException(paramParserCursor.toString());
          while (true)
          {
            paramParserCursor = new StringBuffer();
            paramParserCursor.append("Invalid status line: ");
            paramParserCursor.append(paramCharArrayBuffer.substring(m, k));
            throw new ParseException(paramParserCursor.toString());
            paramParserCursor = paramParserCursor;
          }
        }
      }
      catch (NumberFormatException paramParserCursor)
      {
        while (true)
        {
          continue;
          paramParserCursor = "";
        }
      }
    }
  }

  protected void skipWhitespace(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      i += 1;
    paramParserCursor.updatePos(i);
  }
}