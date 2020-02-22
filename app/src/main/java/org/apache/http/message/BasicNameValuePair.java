package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;

public class BasicNameValuePair
  implements NameValuePair, Cloneable, Serializable
{
  private static final long serialVersionUID = -6437800749411518984L;
  private final String name;
  private final String value;

  public BasicNameValuePair(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if ((paramObject instanceof Serializable))
    {
      paramObject = (BasicNameValuePair)paramObject;
      return (this.name.equals(paramObject.name)) && (LangUtils.equals(this.value, paramObject.value));
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
  }

  public String toString()
  {
    if (this.value == null)
      return this.name;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(this.name.length() + 1 + this.value.length());
    localCharArrayBuffer.append(this.name);
    localCharArrayBuffer.append("=");
    localCharArrayBuffer.append(this.value);
    return localCharArrayBuffer.toString();
  }
}