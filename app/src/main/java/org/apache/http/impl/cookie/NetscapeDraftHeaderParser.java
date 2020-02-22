package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.annotation.Immutable;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class NetscapeDraftHeaderParser
{
  public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();

  private NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int j = paramParserCursor.getPos();
    int i = paramParserCursor.getPos();
    int n = paramParserCursor.getUpperBound();
    while (true)
    {
      m = 1;
      if (j >= n)
        break;
      k = paramCharArrayBuffer.charAt(j);
      if (k == 61)
        break;
      if (k == 59)
      {
        k = 1;
        break label70;
      }
      j += 1;
    }
    int k = 0;
    label70: String str;
    if (j == n)
    {
      str = paramCharArrayBuffer.substringTrimmed(i, n);
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
      return new BasicNameValuePair(str, null);
    }
    i = j;
    while (i < n)
    {
      if (paramCharArrayBuffer.charAt(i) == ';')
      {
        k = m;
        break;
      }
      i += 1;
    }
    while ((j < i) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(j))))
      j += 1;
    int m = i;
    while ((m > j) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(m - 1))))
      m -= 1;
    paramCharArrayBuffer = paramCharArrayBuffer.substring(j, m);
    j = i;
    if (k != 0)
      j = i + 1;
    paramParserCursor.updatePos(j);
    return new BasicNameValuePair(str, paramCharArrayBuffer);
  }

  public HeaderElement parseHeader(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    ArrayList localArrayList = new ArrayList();
    while (!paramParserCursor.atEnd())
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramParserCursor));
    return new BasicHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]));
  }
}