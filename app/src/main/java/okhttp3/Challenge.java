package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

public final class Challenge
{
  private final Map<String, String> authParams;
  private final String scheme;

  public Challenge(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new NullPointerException("scheme == null");
    if (paramString2 == null)
      throw new NullPointerException("realm == null");
    this.scheme = paramString1;
    this.authParams = Collections.singletonMap("realm", paramString2);
  }

  public Challenge(String paramString, Map<String, String> paramMap)
  {
    if (paramString == null)
      throw new NullPointerException("scheme == null");
    if (paramMap == null)
      throw new NullPointerException("authParams == null");
    this.scheme = paramString;
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (localEntry.getKey() == null)
        paramString = null;
      else
        paramString = ((String)localEntry.getKey()).toLowerCase(Locale.US);
      localLinkedHashMap.put(paramString, (String)localEntry.getValue());
    }
    this.authParams = Collections.unmodifiableMap(localLinkedHashMap);
  }

  public Map<String, String> authParams()
  {
    return this.authParams;
  }

  public Charset charset()
  {
    Object localObject = (String)this.authParams.get("charset");
    if (localObject != null);
    try
    {
      localObject = Charset.forName((String)localObject);
      return localObject;
      label26: return StandardCharsets.ISO_8859_1;
    }
    catch (Exception localException)
    {
      break label26;
    }
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Challenge))
    {
      paramObject = (Challenge)paramObject;
      if ((paramObject.scheme.equals(this.scheme)) && (paramObject.authParams.equals(this.authParams)))
        return true;
    }
    return false;
  }

  public int hashCode()
  {
    return (899 + this.scheme.hashCode()) * 31 + this.authParams.hashCode();
  }

  public String realm()
  {
    return (String)this.authParams.get("realm");
  }

  public String scheme()
  {
    return this.scheme;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.scheme);
    localStringBuilder.append(" authParams=");
    localStringBuilder.append(this.authParams);
    return localStringBuilder.toString();
  }

  public Challenge withCharset(Charset paramCharset)
  {
    if (paramCharset == null)
      throw new NullPointerException("charset == null");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(this.authParams);
    localLinkedHashMap.put("charset", paramCharset.name());
    return new Challenge(this.scheme, localLinkedHashMap);
  }
}