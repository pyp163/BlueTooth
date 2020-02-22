package org.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueParser
  implements HeaderValueParser
{
  private static final char[] ALL_DELIMITERS = { 59, 44 };
  public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
  private static final char ELEM_DELIMITER = ',';
  private static final char PARAM_DELIMITER = ';';

  private static boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
    {
      int i = 0;
      while (i < paramArrayOfChar.length)
      {
        if (paramChar == paramArrayOfChar[i])
          return true;
        i += 1;
      }
    }
    return false;
  }

  public static final HeaderElement[] parseElements(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    Object localObject = paramHeaderValueParser;
    if (paramHeaderValueParser == null)
      localObject = DEFAULT;
    paramHeaderValueParser = new CharArrayBuffer(paramString.length());
    paramHeaderValueParser.append(paramString);
    return ((HeaderValueParser)localObject).parseElements(paramHeaderValueParser, new ParserCursor(0, paramString.length()));
  }

  public static final HeaderElement parseHeaderElement(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    Object localObject = paramHeaderValueParser;
    if (paramHeaderValueParser == null)
      localObject = DEFAULT;
    paramHeaderValueParser = new CharArrayBuffer(paramString.length());
    paramHeaderValueParser.append(paramString);
    return ((HeaderValueParser)localObject).parseHeaderElement(paramHeaderValueParser, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair parseNameValuePair(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    Object localObject = paramHeaderValueParser;
    if (paramHeaderValueParser == null)
      localObject = DEFAULT;
    paramHeaderValueParser = new CharArrayBuffer(paramString.length());
    paramHeaderValueParser.append(paramString);
    return ((HeaderValueParser)localObject).parseNameValuePair(paramHeaderValueParser, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair[] parseParameters(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    Object localObject = paramHeaderValueParser;
    if (paramHeaderValueParser == null)
      localObject = DEFAULT;
    paramHeaderValueParser = new CharArrayBuffer(paramString.length());
    paramHeaderValueParser.append(paramString);
    return ((HeaderValueParser)localObject).parseParameters(paramHeaderValueParser, new ParserCursor(0, paramString.length()));
  }

  protected HeaderElement createHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    return new BasicHeaderElement(paramString1, paramString2, paramArrayOfNameValuePair);
  }

  protected NameValuePair createNameValuePair(String paramString1, String paramString2)
  {
    return new BasicNameValuePair(paramString1, paramString2);
  }

  public HeaderElement[] parseElements(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
    {
      HeaderElement localHeaderElement = parseHeaderElement(paramCharArrayBuffer, paramParserCursor);
      if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null))
        localArrayList.add(localHeaderElement);
    }
    return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
  }

  public HeaderElement parseHeaderElement(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!paramParserCursor.atEnd())
    {
      localObject1 = localObject2;
      if (paramCharArrayBuffer.charAt(paramParserCursor.getPos() - 1) != ',')
        localObject1 = parseParameters(paramCharArrayBuffer, paramParserCursor);
    }
    return createHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), (NameValuePair[])localObject1);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    return parseNameValuePair(paramCharArrayBuffer, paramParserCursor, ALL_DELIMITERS);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor, char[] paramArrayOfChar)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int j = paramParserCursor.getPos();
    int i = paramParserCursor.getPos();
    int i3 = paramParserCursor.getUpperBound();
    int i2;
    char c;
    while (true)
    {
      i2 = 1;
      if (j >= i3)
        break;
      c = paramCharArrayBuffer.charAt(j);
      if (c == '=')
        break;
      if (isOneOf(c, paramArrayOfChar))
      {
        k = 1;
        break label101;
      }
      j += 1;
    }
    int k = 0;
    label101: String str;
    if (j == i3)
    {
      str = paramCharArrayBuffer.substringTrimmed(i, i3);
      k = 1;
    }
    else
    {
      str = paramCharArrayBuffer.substringTrimmed(i, j);
      j += 1;
    }
    if (k != 0)
    {
      paramParserCursor.updatePos(j);
      return createNameValuePair(str, null);
    }
    i = j;
    int m = 0;
    for (int i1 = 0; i < i3; i1 = n)
    {
      c = paramCharArrayBuffer.charAt(i);
      n = i1;
      if (c == '"')
      {
        n = i1;
        if (m == 0)
          n = i1 ^ 0x1;
      }
      if ((n == 0) && (m == 0) && (isOneOf(c, paramArrayOfChar)))
      {
        m = i2;
        break label279;
      }
      if (m != 0);
      while ((n == 0) || (c != '\\'))
      {
        m = 0;
        break;
      }
      m = 1;
      i += 1;
    }
    m = k;
    label279: 
    while ((j < i) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(j))))
      j += 1;
    k = i;
    while ((k > j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(k - 1))))
      k -= 1;
    i1 = j;
    int n = k;
    if (k - j >= 2)
    {
      i1 = j;
      n = k;
      if (paramCharArrayBuffer.charAt(j) == '"')
      {
        i1 = j;
        n = k;
        if (paramCharArrayBuffer.charAt(k - 1) == '"')
        {
          i1 = j + 1;
          n = k - 1;
        }
      }
    }
    paramCharArrayBuffer = paramCharArrayBuffer.substring(i1, n);
    j = i;
    if (m != 0)
      j = i + 1;
    paramParserCursor.updatePos(j);
    return createNameValuePair(str, paramCharArrayBuffer);
  }

  public NameValuePair[] parseParameters(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while ((i < j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i))))
      i += 1;
    paramParserCursor.updatePos(i);
    if (paramParserCursor.atEnd())
      return new NameValuePair[0];
    ArrayList localArrayList = new ArrayList();
    do
    {
      if (paramParserCursor.atEnd())
        break;
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramParserCursor));
    }
    while (paramCharArrayBuffer.charAt(paramParserCursor.getPos() - 1) != ',');
    return (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]);
  }
}