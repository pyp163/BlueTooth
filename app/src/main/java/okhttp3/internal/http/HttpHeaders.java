package okhttp3.internal.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;

public final class HttpHeaders
{
  private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.encodeUtf8("\"\\");
  private static final ByteString TOKEN_DELIMITERS = ByteString.encodeUtf8("\t ,=");

  public static long contentLength(Headers paramHeaders)
  {
    return stringToLong(paramHeaders.get("Content-Length"));
  }

  public static long contentLength(Response paramResponse)
  {
    return contentLength(paramResponse.headers());
  }

  public static boolean hasBody(Response paramResponse)
  {
    if (paramResponse.request().method().equals("HEAD"))
      return false;
    int i = paramResponse.code();
    if (((i < 100) || (i >= 200)) && (i != 204) && (i != 304))
      return true;
    if (contentLength(paramResponse) == -1L)
      return "chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding"));
    return true;
  }

  public static boolean hasVaryAll(Headers paramHeaders)
  {
    return varyFields(paramHeaders).contains("*");
  }

  public static boolean hasVaryAll(Response paramResponse)
  {
    return hasVaryAll(paramResponse.headers());
  }

  private static void parseChallengeHeader(List<Challenge> paramList, Buffer paramBuffer)
  {
    Object localObject1;
    Object localObject2;
    String str;
    int i;
    while (true)
    {
      localObject1 = null;
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        skipWhitespaceAndCommas(paramBuffer);
        localObject1 = readToken(paramBuffer);
        localObject2 = localObject1;
        if (localObject1 == null)
          return;
      }
      boolean bool1 = skipWhitespaceAndCommas(paramBuffer);
      str = readToken(paramBuffer);
      if (str == null)
      {
        if (!paramBuffer.exhausted())
          return;
        paramList.add(new Challenge(localObject2, Collections.emptyMap()));
        return;
      }
      i = skipAll(paramBuffer, (byte)61);
      boolean bool2 = skipWhitespaceAndCommas(paramBuffer);
      if ((bool1) || ((!bool2) && (!paramBuffer.exhausted())))
        break;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(str);
      ((StringBuilder)localObject1).append(repeat('=', i));
      paramList.add(new Challenge(localObject2, Collections.singletonMap(null, ((StringBuilder)localObject1).toString())));
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    i += skipAll(paramBuffer, (byte)61);
    while (true)
    {
      localObject1 = str;
      if (str == null)
      {
        localObject1 = readToken(paramBuffer);
        if (!skipWhitespaceAndCommas(paramBuffer))
          i = skipAll(paramBuffer, (byte)61);
      }
      else
      {
        if (i != 0)
          break label238;
      }
      paramList.add(new Challenge(localObject2, localLinkedHashMap));
      break;
      label238: if (i > 1)
        return;
      if (skipWhitespaceAndCommas(paramBuffer))
        return;
      if ((!paramBuffer.exhausted()) && (paramBuffer.getByte(0L) == 34))
        str = readQuotedString(paramBuffer);
      else
        str = readToken(paramBuffer);
      if (str == null)
        return;
      if ((String)localLinkedHashMap.put(localObject1, str) != null)
        return;
      if ((!skipWhitespaceAndCommas(paramBuffer)) && (!paramBuffer.exhausted()))
        return;
      str = null;
    }
  }

  public static List<Challenge> parseChallenges(Headers paramHeaders, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramHeaders.size())
    {
      if (paramString.equalsIgnoreCase(paramHeaders.name(i)))
        parseChallengeHeader(localArrayList, new Buffer().writeUtf8(paramHeaders.value(i)));
      i += 1;
    }
    return localArrayList;
  }

  public static int parseSeconds(String paramString, int paramInt)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l > 2147483647L)
        return 2147483647;
      if (l < 0L)
        return 0;
      return (int)l;
    }
    catch (NumberFormatException paramString)
    {
    }
    return paramInt;
  }

  private static String readQuotedString(Buffer paramBuffer)
  {
    if (paramBuffer.readByte() != 34)
      throw new IllegalArgumentException();
    Buffer localBuffer = new Buffer();
    while (true)
    {
      long l = paramBuffer.indexOfElement(QUOTED_STRING_DELIMITERS);
      if (l == -1L)
        return null;
      if (paramBuffer.getByte(l) == 34)
      {
        localBuffer.write(paramBuffer, l);
        paramBuffer.readByte();
        return localBuffer.readUtf8();
      }
      if (paramBuffer.size() == l + 1L)
        return null;
      localBuffer.write(paramBuffer, l);
      paramBuffer.readByte();
      localBuffer.write(paramBuffer, 1L);
    }
  }

  private static String readToken(Buffer paramBuffer)
  {
    try
    {
      long l2 = paramBuffer.indexOfElement(TOKEN_DELIMITERS);
      long l1 = l2;
      if (l2 == -1L)
        l1 = paramBuffer.size();
      if (l1 != 0L)
      {
        paramBuffer = paramBuffer.readUtf8(l1);
        return paramBuffer;
      }
      return null;
      label39: throw new AssertionError();
    }
    catch (EOFException paramBuffer)
    {
      break label39;
    }
  }

  public static void receiveHeaders(CookieJar paramCookieJar, HttpUrl paramHttpUrl, Headers paramHeaders)
  {
    if (paramCookieJar == CookieJar.NO_COOKIES)
      return;
    paramHeaders = Cookie.parseAll(paramHttpUrl, paramHeaders);
    if (paramHeaders.isEmpty())
      return;
    paramCookieJar.saveFromResponse(paramHttpUrl, paramHeaders);
  }

  private static String repeat(char paramChar, int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    Arrays.fill(arrayOfChar, paramChar);
    return new String(arrayOfChar);
  }

  private static int skipAll(Buffer paramBuffer, byte paramByte)
  {
    int i = 0;
    while ((!paramBuffer.exhausted()) && (paramBuffer.getByte(0L) == paramByte))
    {
      i += 1;
      paramBuffer.readByte();
    }
    return i;
  }

  public static int skipUntil(String paramString1, int paramInt, String paramString2)
  {
    while (paramInt < paramString1.length())
    {
      if (paramString2.indexOf(paramString1.charAt(paramInt)) != -1)
        return paramInt;
      paramInt += 1;
    }
    return paramInt;
  }

  public static int skipWhitespace(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((i != 32) && (i != 9))
        return paramInt;
      paramInt += 1;
    }
    return paramInt;
  }

  private static boolean skipWhitespaceAndCommas(Buffer paramBuffer)
  {
    boolean bool = false;
    while (!paramBuffer.exhausted())
    {
      int i = paramBuffer.getByte(0L);
      if (i == 44)
      {
        paramBuffer.readByte();
        bool = true;
      }
      else
      {
        if ((i != 32) && (i != 9))
          break;
        paramBuffer.readByte();
      }
    }
    return bool;
  }

  private static long stringToLong(String paramString)
  {
    if (paramString == null)
      return -1L;
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString)
    {
    }
    return -1L;
  }

  public static Set<String> varyFields(Headers paramHeaders)
  {
    Object localObject2 = Collections.emptySet();
    int k = paramHeaders.size();
    int i = 0;
    while (i < k)
    {
      if ("Vary".equalsIgnoreCase(paramHeaders.name(i)))
      {
        Object localObject3 = paramHeaders.value(i);
        Object localObject1 = localObject2;
        if (((Set)localObject2).isEmpty())
          localObject1 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        localObject3 = ((String)localObject3).split(",");
        int m = localObject3.length;
        int j = 0;
        while (true)
        {
          localObject2 = localObject1;
          if (j >= m)
            break;
          ((Set)localObject1).add(localObject3[j].trim());
          j += 1;
        }
      }
      i += 1;
    }
    return localObject2;
  }

  private static Set<String> varyFields(Response paramResponse)
  {
    return varyFields(paramResponse.headers());
  }

  public static Headers varyHeaders(Headers paramHeaders1, Headers paramHeaders2)
  {
    paramHeaders2 = varyFields(paramHeaders2);
    if (paramHeaders2.isEmpty())
      return Util.EMPTY_HEADERS;
    Headers.Builder localBuilder = new Headers.Builder();
    int i = 0;
    int j = paramHeaders1.size();
    while (i < j)
    {
      String str = paramHeaders1.name(i);
      if (paramHeaders2.contains(str))
        localBuilder.add(str, paramHeaders1.value(i));
      i += 1;
    }
    return localBuilder.build();
  }

  public static Headers varyHeaders(Response paramResponse)
  {
    return varyHeaders(paramResponse.networkResponse().request().headers(), paramResponse.headers());
  }

  public static boolean varyMatches(Response paramResponse, Headers paramHeaders, Request paramRequest)
  {
    paramResponse = varyFields(paramResponse).iterator();
    while (paramResponse.hasNext())
    {
      String str = (String)paramResponse.next();
      if (!Objects.equals(paramHeaders.values(str), paramRequest.headers(str)))
        return false;
    }
    return true;
  }
}