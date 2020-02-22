package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.HashCodeUtil;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class BytesRange
{
  public static final int TO_END_OF_CONTENT = 2147483647;

  @Nullable
  private static Pattern sHeaderParsingRegEx;
  public final int from;
  public final int to;

  public BytesRange(int paramInt1, int paramInt2)
  {
    this.from = paramInt1;
    this.to = paramInt2;
  }

  public static BytesRange from(int paramInt)
  {
    boolean bool;
    if (paramInt >= 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    return new BytesRange(paramInt, 2147483647);
  }

  @Nullable
  public static BytesRange fromContentRangeHeader(@Nullable String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null)
      return null;
    if (sHeaderParsingRegEx == null)
      sHeaderParsingRegEx = Pattern.compile("[-/ ]");
    while (true)
    {
      try
      {
        Object localObject = sHeaderParsingRegEx.split(paramString);
        if (localObject.length == 4)
        {
          bool = true;
          Preconditions.checkArgument(bool);
          Preconditions.checkArgument(localObject[0].equals("bytes"));
          int i = Integer.parseInt(localObject[1]);
          int j = Integer.parseInt(localObject[2]);
          int k = Integer.parseInt(localObject[3]);
          if (j <= i)
            break label182;
          bool = true;
          Preconditions.checkArgument(bool);
          if (k <= j)
            break label188;
          bool = true;
          Preconditions.checkArgument(bool);
          if (j < k - 1)
            return new BytesRange(i, j);
          localObject = new BytesRange(i, 2147483647);
          return localObject;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new IllegalArgumentException(String.format((Locale)null, "Invalid Content-Range header value: \"%s\"", new Object[] { paramString }), localIllegalArgumentException);
      }
      boolean bool = false;
      continue;
      label182: bool = false;
      continue;
      label188: bool = false;
    }
  }

  public static BytesRange toMax(int paramInt)
  {
    boolean bool;
    if (paramInt > 0)
      bool = true;
    else
      bool = false;
    Preconditions.checkArgument(bool);
    return new BytesRange(0, paramInt);
  }

  private static String valueOrEmpty(int paramInt)
  {
    if (paramInt == 2147483647)
      return "";
    return Integer.toString(paramInt);
  }

  public boolean contains(@Nullable BytesRange paramBytesRange)
  {
    boolean bool2 = false;
    if (paramBytesRange == null)
      return false;
    boolean bool1 = bool2;
    if (this.from <= paramBytesRange.from)
    {
      bool1 = bool2;
      if (this.to >= paramBytesRange.to)
        bool1 = true;
    }
    return bool1;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof BytesRange))
      return false;
    paramObject = (BytesRange)paramObject;
    return (this.from == paramObject.from) && (this.to == paramObject.to);
  }

  public int hashCode()
  {
    return HashCodeUtil.hashCode(this.from, this.to);
  }

  public String toHttpRangeHeaderValue()
  {
    return String.format((Locale)null, "bytes=%s-%s", new Object[] { valueOrEmpty(this.from), valueOrEmpty(this.to) });
  }

  public String toString()
  {
    return String.format((Locale)null, "%s-%s", new Object[] { valueOrEmpty(this.from), valueOrEmpty(this.to) });
  }
}