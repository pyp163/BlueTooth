package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeader
  implements Header, Cloneable, Serializable
{
  private static final long serialVersionUID = -5427236326487562174L;
  private final String name;
  private final String value;

  public BasicHeader(String paramString1, String paramString2)
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

  public HeaderElement[] getElements()
    throws ParseException
  {
    if (this.value != null)
      return BasicHeaderValueParser.parseElements(this.value, null);
    return new HeaderElement[0];
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public String toString()
  {
    return BasicLineFormatter.DEFAULT.formatHeader(null, this).toString();
  }
}