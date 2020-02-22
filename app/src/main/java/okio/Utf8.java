package okio;

public final class Utf8
{
  public static long size(String paramString)
  {
    return size(paramString, 0, paramString.length());
  }

  public static long size(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null)
      throw new IllegalArgumentException("string == null");
    if (paramInt1 < 0)
    {
      paramString = new StringBuilder();
      paramString.append("beginIndex < 0: ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    if (paramInt2 < paramInt1)
    {
      paramString = new StringBuilder();
      paramString.append("endIndex < beginIndex: ");
      paramString.append(paramInt2);
      paramString.append(" < ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    if (paramInt2 > paramString.length())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("endIndex > string.length: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(" > ");
      localStringBuilder.append(paramString.length());
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    long l = 0L;
    while (paramInt1 < paramInt2)
    {
      int k = paramString.charAt(paramInt1);
      if (k < 128)
      {
        l += 1L;
        paramInt1 += 1;
      }
      else if (k < 2048)
      {
        l += 2L;
        paramInt1 += 1;
      }
      else if ((k >= 55296) && (k <= 57343))
      {
        int j = paramInt1 + 1;
        int i;
        if (j < paramInt2)
          i = paramString.charAt(j);
        else
          i = 0;
        if ((k <= 56319) && (i >= 56320) && (i <= 57343))
        {
          l += 4L;
          paramInt1 += 2;
        }
        else
        {
          l += 1L;
          paramInt1 = j;
        }
      }
      else
      {
        l += 3L;
        paramInt1 += 1;
      }
    }
    return l;
  }
}