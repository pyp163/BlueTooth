package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueFormatter
  implements HeaderValueFormatter
{
  public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
  public static final String SEPARATORS = " ;,:@()<>\\\"/[]?={}\t";
  public static final String UNSAFE_CHARS = "\"\\";

  public static final String formatElements(HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    Object localObject = paramHeaderValueFormatter;
    if (paramHeaderValueFormatter == null)
      localObject = DEFAULT;
    return ((HeaderValueFormatter)localObject).formatElements(null, paramArrayOfHeaderElement, paramBoolean).toString();
  }

  public static final String formatHeaderElement(HeaderElement paramHeaderElement, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    Object localObject = paramHeaderValueFormatter;
    if (paramHeaderValueFormatter == null)
      localObject = DEFAULT;
    return ((HeaderValueFormatter)localObject).formatHeaderElement(null, paramHeaderElement, paramBoolean).toString();
  }

  public static final String formatNameValuePair(NameValuePair paramNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    Object localObject = paramHeaderValueFormatter;
    if (paramHeaderValueFormatter == null)
      localObject = DEFAULT;
    return ((HeaderValueFormatter)localObject).formatNameValuePair(null, paramNameValuePair, paramBoolean).toString();
  }

  public static final String formatParameters(NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean, HeaderValueFormatter paramHeaderValueFormatter)
  {
    Object localObject = paramHeaderValueFormatter;
    if (paramHeaderValueFormatter == null)
      localObject = DEFAULT;
    return ((HeaderValueFormatter)localObject).formatParameters(null, paramArrayOfNameValuePair, paramBoolean).toString();
  }

  protected void doFormatValue(CharArrayBuffer paramCharArrayBuffer, String paramString, boolean paramBoolean)
  {
    int j = 0;
    boolean bool = paramBoolean;
    if (!paramBoolean)
    {
      i = 0;
      while ((i < paramString.length()) && (!paramBoolean))
      {
        paramBoolean = isSeparator(paramString.charAt(i));
        i += 1;
      }
      bool = paramBoolean;
    }
    int i = j;
    if (bool)
    {
      paramCharArrayBuffer.append('"');
      i = j;
    }
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (isUnsafe(c))
        paramCharArrayBuffer.append('\\');
      paramCharArrayBuffer.append(c);
      i += 1;
    }
    if (bool)
      paramCharArrayBuffer.append('"');
  }

  protected int estimateElementsLen(HeaderElement[] paramArrayOfHeaderElement)
  {
    int i = 0;
    if (paramArrayOfHeaderElement != null)
    {
      if (paramArrayOfHeaderElement.length < 1)
        return 0;
      int j = (paramArrayOfHeaderElement.length - 1) * 2;
      while (i < paramArrayOfHeaderElement.length)
      {
        j += estimateHeaderElementLen(paramArrayOfHeaderElement[i]);
        i += 1;
      }
      return j;
    }
    return 0;
  }

  protected int estimateHeaderElementLen(HeaderElement paramHeaderElement)
  {
    int k = 0;
    if (paramHeaderElement == null)
      return 0;
    int j = paramHeaderElement.getName().length();
    String str = paramHeaderElement.getValue();
    int i = j;
    if (str != null)
      i = j + (str.length() + 3);
    int m = paramHeaderElement.getParameterCount();
    j = i;
    if (m > 0)
      while (true)
      {
        j = i;
        if (k >= m)
          break;
        i += estimateNameValuePairLen(paramHeaderElement.getParameter(k)) + 2;
        k += 1;
      }
    return j;
  }

  protected int estimateNameValuePairLen(NameValuePair paramNameValuePair)
  {
    if (paramNameValuePair == null)
      return 0;
    int j = paramNameValuePair.getName().length();
    paramNameValuePair = paramNameValuePair.getValue();
    int i = j;
    if (paramNameValuePair != null)
      i = j + (paramNameValuePair.length() + 3);
    return i;
  }

  protected int estimateParametersLen(NameValuePair[] paramArrayOfNameValuePair)
  {
    int i = 0;
    if (paramArrayOfNameValuePair != null)
    {
      if (paramArrayOfNameValuePair.length < 1)
        return 0;
      int j = (paramArrayOfNameValuePair.length - 1) * 2;
      while (i < paramArrayOfNameValuePair.length)
      {
        j += estimateNameValuePairLen(paramArrayOfNameValuePair[i]);
        i += 1;
      }
      return j;
    }
    return 0;
  }

  public CharArrayBuffer formatElements(CharArrayBuffer paramCharArrayBuffer, HeaderElement[] paramArrayOfHeaderElement, boolean paramBoolean)
  {
    if (paramArrayOfHeaderElement == null)
      throw new IllegalArgumentException("Header element array must not be null.");
    int i = estimateElementsLen(paramArrayOfHeaderElement);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    else
      paramCharArrayBuffer.ensureCapacity(i);
    i = 0;
    while (i < paramArrayOfHeaderElement.length)
    {
      if (i > 0)
        paramCharArrayBuffer.append(", ");
      formatHeaderElement(paramCharArrayBuffer, paramArrayOfHeaderElement[i], paramBoolean);
      i += 1;
    }
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer formatHeaderElement(CharArrayBuffer paramCharArrayBuffer, HeaderElement paramHeaderElement, boolean paramBoolean)
  {
    if (paramHeaderElement == null)
      throw new IllegalArgumentException("Header element must not be null.");
    int i = estimateHeaderElementLen(paramHeaderElement);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    else
      paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(paramHeaderElement.getName());
    String str = paramHeaderElement.getValue();
    if (str != null)
    {
      paramCharArrayBuffer.append('=');
      doFormatValue(paramCharArrayBuffer, str, paramBoolean);
    }
    int j = paramHeaderElement.getParameterCount();
    if (j > 0)
    {
      i = 0;
      while (i < j)
      {
        paramCharArrayBuffer.append("; ");
        formatNameValuePair(paramCharArrayBuffer, paramHeaderElement.getParameter(i), paramBoolean);
        i += 1;
      }
    }
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer formatNameValuePair(CharArrayBuffer paramCharArrayBuffer, NameValuePair paramNameValuePair, boolean paramBoolean)
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("NameValuePair must not be null.");
    int i = estimateNameValuePairLen(paramNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    else
      paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(paramNameValuePair.getName());
    paramNameValuePair = paramNameValuePair.getValue();
    if (paramNameValuePair != null)
    {
      paramCharArrayBuffer.append('=');
      doFormatValue(paramCharArrayBuffer, paramNameValuePair, paramBoolean);
    }
    return paramCharArrayBuffer;
  }

  public CharArrayBuffer formatParameters(CharArrayBuffer paramCharArrayBuffer, NameValuePair[] paramArrayOfNameValuePair, boolean paramBoolean)
  {
    if (paramArrayOfNameValuePair == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    int i = estimateParametersLen(paramArrayOfNameValuePair);
    if (paramCharArrayBuffer == null)
      paramCharArrayBuffer = new CharArrayBuffer(i);
    else
      paramCharArrayBuffer.ensureCapacity(i);
    i = 0;
    while (i < paramArrayOfNameValuePair.length)
    {
      if (i > 0)
        paramCharArrayBuffer.append("; ");
      formatNameValuePair(paramCharArrayBuffer, paramArrayOfNameValuePair[i], paramBoolean);
      i += 1;
    }
    return paramCharArrayBuffer;
  }

  protected boolean isSeparator(char paramChar)
  {
    return " ;,:@()<>\\\"/[]?={}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isUnsafe(char paramChar)
  {
    return "\"\\".indexOf(paramChar) >= 0;
  }
}