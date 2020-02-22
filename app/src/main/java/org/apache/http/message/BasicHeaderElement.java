package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.LangUtils;

public class BasicHeaderElement
  implements HeaderElement, Cloneable
{
  private final String name;
  private final NameValuePair[] parameters;
  private final String value;

  public BasicHeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public BasicHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
    if (paramArrayOfNameValuePair != null)
    {
      this.parameters = paramArrayOfNameValuePair;
      return;
    }
    this.parameters = new NameValuePair[0];
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
    if ((paramObject instanceof Cloneable))
    {
      paramObject = (BasicHeaderElement)paramObject;
      return (this.name.equals(paramObject.name)) && (LangUtils.equals(this.value, paramObject.value)) && (LangUtils.equals(this.parameters, paramObject.parameters));
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public NameValuePair getParameter(int paramInt)
  {
    return this.parameters[paramInt];
  }

  public NameValuePair getParameterByName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    int i = 0;
    while (i < this.parameters.length)
    {
      NameValuePair localNameValuePair = this.parameters[i];
      if (localNameValuePair.getName().equalsIgnoreCase(paramString))
        return localNameValuePair;
      i += 1;
    }
    return null;
  }

  public int getParameterCount()
  {
    return this.parameters.length;
  }

  public NameValuePair[] getParameters()
  {
    return (NameValuePair[])this.parameters.clone();
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int j = LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
    int i = 0;
    while (i < this.parameters.length)
    {
      j = LangUtils.hashCode(j, this.parameters[i]);
      i += 1;
    }
    return j;
  }

  public String toString()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(64);
    localCharArrayBuffer.append(this.name);
    if (this.value != null)
    {
      localCharArrayBuffer.append("=");
      localCharArrayBuffer.append(this.value);
    }
    int i = 0;
    while (i < this.parameters.length)
    {
      localCharArrayBuffer.append("; ");
      localCharArrayBuffer.append(this.parameters[i]);
      i += 1;
    }
    return localCharArrayBuffer.toString();
  }
}