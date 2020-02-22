package org.apache.http.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.util.CharArrayBuffer;

public class HeaderGroup
  implements Cloneable, Serializable
{
  private static final long serialVersionUID = 2608834160639271617L;
  private final List headers = new ArrayList(16);

  public void addHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    this.headers.add(paramHeader);
  }

  public void clear()
  {
    this.headers.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    HeaderGroup localHeaderGroup = (HeaderGroup)super.clone();
    localHeaderGroup.headers.clear();
    localHeaderGroup.headers.addAll(this.headers);
    return localHeaderGroup;
  }

  public boolean containsHeader(String paramString)
  {
    int i = 0;
    while (i < this.headers.size())
    {
      if (((Header)this.headers.get(i)).getName().equalsIgnoreCase(paramString))
        return true;
      i += 1;
    }
    return false;
  }

  public HeaderGroup copy()
  {
    HeaderGroup localHeaderGroup = new HeaderGroup();
    localHeaderGroup.headers.addAll(this.headers);
    return localHeaderGroup;
  }

  public Header[] getAllHeaders()
  {
    return (Header[])this.headers.toArray(new Header[this.headers.size()]);
  }

  public Header getCondensedHeader(String paramString)
  {
    Header[] arrayOfHeader = getHeaders(paramString);
    if (arrayOfHeader.length == 0)
      return null;
    int j = arrayOfHeader.length;
    int i = 1;
    if (j == 1)
      return arrayOfHeader[0];
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(128);
    localCharArrayBuffer.append(arrayOfHeader[0].getValue());
    while (i < arrayOfHeader.length)
    {
      localCharArrayBuffer.append(", ");
      localCharArrayBuffer.append(arrayOfHeader[i].getValue());
      i += 1;
    }
    return new BasicHeader(paramString.toLowerCase(Locale.ENGLISH), localCharArrayBuffer.toString());
  }

  public Header getFirstHeader(String paramString)
  {
    int i = 0;
    while (i < this.headers.size())
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return localHeader;
      i += 1;
    }
    return null;
  }

  public Header[] getHeaders(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.headers.size())
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        localArrayList.add(localHeader);
      i += 1;
    }
    return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
  }

  public Header getLastHeader(String paramString)
  {
    int i = this.headers.size() - 1;
    while (i >= 0)
    {
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        return localHeader;
      i -= 1;
    }
    return null;
  }

  public HeaderIterator iterator()
  {
    return new BasicListHeaderIterator(this.headers, null);
  }

  public HeaderIterator iterator(String paramString)
  {
    return new BasicListHeaderIterator(this.headers, paramString);
  }

  public void removeHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    this.headers.remove(paramHeader);
  }

  public void setHeaders(Header[] paramArrayOfHeader)
  {
    clear();
    if (paramArrayOfHeader == null)
      return;
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      this.headers.add(paramArrayOfHeader[i]);
      i += 1;
    }
  }

  public String toString()
  {
    return this.headers.toString();
  }

  public void updateHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    int i = 0;
    while (i < this.headers.size())
    {
      if (((Header)this.headers.get(i)).getName().equalsIgnoreCase(paramHeader.getName()))
      {
        this.headers.set(i, paramHeader);
        return;
      }
      i += 1;
    }
    this.headers.add(paramHeader);
  }
}