package okhttp3;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class Headers
{
  private final String[] namesAndValues;

  Headers(Builder paramBuilder)
  {
    this.namesAndValues = ((String[])paramBuilder.namesAndValues.toArray(new String[paramBuilder.namesAndValues.size()]));
  }

  private Headers(String[] paramArrayOfString)
  {
    this.namesAndValues = paramArrayOfString;
  }

  static void checkName(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("name == null");
    if (paramString.isEmpty())
      throw new IllegalArgumentException("name is empty");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString.charAt(i);
      if ((k > 32) && (k < 127))
        i += 1;
      else
        throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString }));
    }
  }

  static void checkValue(String paramString1, String paramString2)
  {
    if (paramString1 == null)
    {
      paramString1 = new StringBuilder();
      paramString1.append("value for name ");
      paramString1.append(paramString2);
      paramString1.append(" == null");
      throw new NullPointerException(paramString1.toString());
    }
    int j = paramString1.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString1.charAt(i);
      if (((k <= 31) && (k != 9)) || (k >= 127))
        throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString2, paramString1 }));
      i += 1;
    }
  }

  @Nullable
  private static String get(String[] paramArrayOfString, String paramString)
  {
    int i = paramArrayOfString.length - 2;
    while (i >= 0)
    {
      if (paramString.equalsIgnoreCase(paramArrayOfString[i]))
        return paramArrayOfString[(i + 1)];
      i -= 2;
    }
    return null;
  }

  public static Headers of(Map<String, String> paramMap)
  {
    if (paramMap == null)
      throw new NullPointerException("headers == null");
    String[] arrayOfString = new String[paramMap.size() * 2];
    int i = 0;
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      if ((((Map.Entry)localObject).getKey() != null) && (((Map.Entry)localObject).getValue() != null))
      {
        String str = ((String)((Map.Entry)localObject).getKey()).trim();
        localObject = ((String)((Map.Entry)localObject).getValue()).trim();
        checkName(str);
        checkValue((String)localObject, str);
        arrayOfString[i] = str;
        arrayOfString[(i + 1)] = localObject;
        i += 2;
      }
      else
      {
        throw new IllegalArgumentException("Headers cannot be null");
      }
    }
    return new Headers(arrayOfString);
  }

  public static Headers of(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new NullPointerException("namesAndValues == null");
    if (paramArrayOfString.length % 2 != 0)
      throw new IllegalArgumentException("Expected alternating header names and values");
    paramArrayOfString = (String[])paramArrayOfString.clone();
    int k = 0;
    int i = 0;
    int j;
    while (true)
    {
      j = k;
      if (i >= paramArrayOfString.length)
        break;
      if (paramArrayOfString[i] == null)
        throw new IllegalArgumentException("Headers cannot be null");
      paramArrayOfString[i] = paramArrayOfString[i].trim();
      i += 1;
    }
    while (j < paramArrayOfString.length)
    {
      String str1 = paramArrayOfString[j];
      String str2 = paramArrayOfString[(j + 1)];
      checkName(str1);
      checkValue(str2, str1);
      j += 2;
    }
    return new Headers(paramArrayOfString);
  }

  public long byteCount()
  {
    long l = this.namesAndValues.length * 2;
    int j = this.namesAndValues.length;
    int i = 0;
    while (i < j)
    {
      l += this.namesAndValues[i].length();
      i += 1;
    }
    return l;
  }

  public boolean equals(@Nullable Object paramObject)
  {
    return ((paramObject instanceof Headers)) && (Arrays.equals(((Headers)paramObject).namesAndValues, this.namesAndValues));
  }

  @Nullable
  public String get(String paramString)
  {
    return get(this.namesAndValues, paramString);
  }

  @Nullable
  public Date getDate(String paramString)
  {
    paramString = get(paramString);
    if (paramString != null)
      return HttpDate.parse(paramString);
    return null;
  }

  @Nullable
  @IgnoreJRERequirement
  public Instant getInstant(String paramString)
  {
    paramString = getDate(paramString);
    if (paramString != null)
      return paramString.toInstant();
    return null;
  }

  public int hashCode()
  {
    return Arrays.hashCode(this.namesAndValues);
  }

  public String name(int paramInt)
  {
    return this.namesAndValues[(paramInt * 2)];
  }

  public Set<String> names()
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    int j = size();
    int i = 0;
    while (i < j)
    {
      localTreeSet.add(name(i));
      i += 1;
    }
    return Collections.unmodifiableSet(localTreeSet);
  }

  public Builder newBuilder()
  {
    Builder localBuilder = new Builder();
    Collections.addAll(localBuilder.namesAndValues, this.namesAndValues);
    return localBuilder;
  }

  public int size()
  {
    return this.namesAndValues.length / 2;
  }

  public Map<String, List<String>> toMultimap()
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    int j = size();
    int i = 0;
    while (i < j)
    {
      String str = name(i).toLowerCase(Locale.US);
      List localList = (List)localTreeMap.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList(2);
        localTreeMap.put(str, localObject);
      }
      ((List)localObject).add(value(i));
      i += 1;
    }
    return localTreeMap;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = size();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(name(i));
      localStringBuilder.append(": ");
      localStringBuilder.append(value(i));
      localStringBuilder.append("\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public String value(int paramInt)
  {
    return this.namesAndValues[(paramInt * 2 + 1)];
  }

  public List<String> values(String paramString)
  {
    int j = size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1;
      if (paramString.equalsIgnoreCase(name(i)))
      {
        localObject2 = localObject1;
        if (localObject1 == null)
          localObject2 = new ArrayList(2);
        ((List)localObject2).add(value(i));
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 != null)
      return Collections.unmodifiableList(localObject1);
    return Collections.emptyList();
  }

  public static final class Builder
  {
    final List<String> namesAndValues = new ArrayList(20);

    public Builder add(String paramString)
    {
      int i = paramString.indexOf(":");
      if (i == -1)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected header: ");
        localStringBuilder.append(paramString);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      return add(paramString.substring(0, i).trim(), paramString.substring(i + 1));
    }

    public Builder add(String paramString1, String paramString2)
    {
      Headers.checkName(paramString1);
      Headers.checkValue(paramString2, paramString1);
      return addLenient(paramString1, paramString2);
    }

    @IgnoreJRERequirement
    public Builder add(String paramString, Instant paramInstant)
    {
      if (paramInstant == null)
      {
        paramInstant = new StringBuilder();
        paramInstant.append("value for name ");
        paramInstant.append(paramString);
        paramInstant.append(" == null");
        throw new NullPointerException(paramInstant.toString());
      }
      return add(paramString, new Date(paramInstant.toEpochMilli()));
    }

    public Builder add(String paramString, Date paramDate)
    {
      if (paramDate == null)
      {
        paramDate = new StringBuilder();
        paramDate.append("value for name ");
        paramDate.append(paramString);
        paramDate.append(" == null");
        throw new NullPointerException(paramDate.toString());
      }
      add(paramString, HttpDate.format(paramDate));
      return this;
    }

    public Builder addAll(Headers paramHeaders)
    {
      int j = paramHeaders.size();
      int i = 0;
      while (i < j)
      {
        addLenient(paramHeaders.name(i), paramHeaders.value(i));
        i += 1;
      }
      return this;
    }

    Builder addLenient(String paramString)
    {
      int i = paramString.indexOf(":", 1);
      if (i != -1)
        return addLenient(paramString.substring(0, i), paramString.substring(i + 1));
      if (paramString.startsWith(":"))
        return addLenient("", paramString.substring(1));
      return addLenient("", paramString);
    }

    Builder addLenient(String paramString1, String paramString2)
    {
      this.namesAndValues.add(paramString1);
      this.namesAndValues.add(paramString2.trim());
      return this;
    }

    public Builder addUnsafeNonAscii(String paramString1, String paramString2)
    {
      Headers.checkName(paramString1);
      return addLenient(paramString1, paramString2);
    }

    public Headers build()
    {
      return new Headers(this);
    }

    @Nullable
    public String get(String paramString)
    {
      int i = this.namesAndValues.size() - 2;
      while (i >= 0)
      {
        if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
          return (String)this.namesAndValues.get(i + 1);
        i -= 2;
      }
      return null;
    }

    public Builder removeAll(String paramString)
    {
      int j;
      for (int i = 0; i < this.namesAndValues.size(); i = j + 2)
      {
        j = i;
        if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
        {
          this.namesAndValues.remove(i);
          this.namesAndValues.remove(i);
          j = i - 2;
        }
      }
      return this;
    }

    public Builder set(String paramString1, String paramString2)
    {
      Headers.checkName(paramString1);
      Headers.checkValue(paramString2, paramString1);
      removeAll(paramString1);
      addLenient(paramString1, paramString2);
      return this;
    }

    @IgnoreJRERequirement
    public Builder set(String paramString, Instant paramInstant)
    {
      if (paramInstant == null)
      {
        paramInstant = new StringBuilder();
        paramInstant.append("value for name ");
        paramInstant.append(paramString);
        paramInstant.append(" == null");
        throw new NullPointerException(paramInstant.toString());
      }
      return set(paramString, new Date(paramInstant.toEpochMilli()));
    }

    public Builder set(String paramString, Date paramDate)
    {
      if (paramDate == null)
      {
        paramDate = new StringBuilder();
        paramDate.append("value for name ");
        paramDate.append(paramString);
        paramDate.append(" == null");
        throw new NullPointerException(paramDate.toString());
      }
      set(paramString, HttpDate.format(paramDate));
      return this;
    }
  }
}