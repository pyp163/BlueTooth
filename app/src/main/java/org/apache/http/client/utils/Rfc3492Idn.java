package org.apache.http.client.utils;

import java.util.StringTokenizer;
import org.apache.http.annotation.Immutable;

@Immutable
public class Rfc3492Idn
  implements Idn
{
  private static final String ACE_PREFIX = "xn--";
  private static final int base = 36;
  private static final int damp = 700;
  private static final char delimiter = '-';
  private static final int initial_bias = 72;
  private static final int initial_n = 128;
  private static final int skew = 38;
  private static final int tmax = 26;
  private static final int tmin = 1;

  private int adapt(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
      paramInt1 /= 700;
    else
      paramInt1 /= 2;
    paramInt2 = paramInt1 + paramInt1 / paramInt2;
    paramInt1 = 0;
    while (paramInt2 > 455)
    {
      paramInt2 /= 35;
      paramInt1 += 36;
    }
    return paramInt1 + paramInt2 * 36 / (paramInt2 + 38);
  }

  private int digit(char paramChar)
  {
    if ((paramChar >= 'A') && (paramChar <= 'Z'))
      return paramChar - 'A';
    if ((paramChar >= 'a') && (paramChar <= 'z'))
      return paramChar - 'a';
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0' + 26;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("illegal digit: ");
    localStringBuilder.append(paramChar);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  protected String decode(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int i = paramString.lastIndexOf('-');
    int k = 128;
    int m = 72;
    String str = paramString;
    if (i != -1)
    {
      localStringBuilder.append(paramString.subSequence(0, i));
      str = paramString.substring(i + 1);
    }
    i = 0;
    if (str.length() > 0)
    {
      int j = i;
      int i1 = 1;
      int n = 36;
      while (true)
      {
        int i2;
        if (str.length() != 0)
        {
          char c = str.charAt(0);
          str = str.substring(1);
          int i3 = digit(c);
          i2 = j + i3 * i1;
          if (n <= m + 1)
            j = 1;
          else if (n >= m + 26)
            j = 26;
          else
            j = n - m;
          if (i3 < j)
            j = i2;
        }
        else
        {
          m = localStringBuilder.length();
          boolean bool;
          if (i == 0)
            bool = true;
          else
            bool = false;
          m = adapt(j - i, m + 1, bool);
          k += j / (localStringBuilder.length() + 1);
          i = j % (localStringBuilder.length() + 1);
          localStringBuilder.insert(i, (char)k);
          i += 1;
          break;
        }
        i1 *= (36 - j);
        n += 36;
        j = i2;
      }
    }
    return localStringBuilder.toString();
  }

  public String toUnicode(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ".");
    while (localStringTokenizer.hasMoreTokens())
    {
      String str = localStringTokenizer.nextToken();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append('.');
      paramString = str;
      if (str.startsWith("xn--"))
        paramString = decode(str.substring(4));
      localStringBuilder.append(paramString);
    }
    return localStringBuilder.toString();
  }
}